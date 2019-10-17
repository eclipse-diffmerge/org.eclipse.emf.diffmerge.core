/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - Bug #442492 : hide number of differences in the UI
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SUPPORT_UNDO_REDO;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl;
import org.eclipse.emf.diffmerge.ui.setup.ModelScopeTypedElement;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.util.CompositeUndoContext;
import org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner;
import org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier;
import org.eclipse.emf.diffmerge.ui.util.UserPropertyOwner;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IEditorInput;


/**
 * An ICompareInput that wraps a model comparison.
 * @author Olivier Constant
 */
public class EMFDiffNode extends DiffNode implements IEditingDomainProvider,
IUserPropertyOwner {
  
  /** The resource manager */
  private final ComparisonResourceManager _resourceManager;
  
  /** The non-null comparison-related contents */
  private final UIComparison _contents;
  
  /** The optional editing domain */
  private final EditingDomain _editingDomain;
  
  /** The optional listener that reacts to changes on the editing domain */
  protected IOperationHistoryListener _domainChangeListener;
  
  /** The optional associated editor input */
  private IEditorInput _editorInput;
  
  /** The non-null role that drives the representation of the comparison */
  private Role _drivingRole;
  
  /** The non-null role on the left-hand side */
  private Role _leftRole;
  
  /** The potentially null role to use as a reference in a two-way comparison */
  private Role _twoWayReferenceRole;
  
  /** The non-null difference category manager */
  private final CategoryManager _categoryManager;
  
  /** Whether the left model is editable */
  private boolean _isTargetEditable;
  
  /** Whether the right model is editable */
  private boolean _isReferenceEditable;
  
  /** Whether editing the target scope is possible at all */
  private boolean _isTargetEditionPossible;
  
  /** Whether editing the reference scope is possible at all */
  private boolean _isReferenceEditionPossible;
  
  /** Whether the left model has been modified */
  private boolean _isTargetModified;
  
  /** Whether the right model has been modified */
  private boolean _isReferenceModified;
  
  /** The user property owner for delegation */
  private final IUserPropertyOwner _userPropertyOwnerDelegate;
  
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   */
  public EMFDiffNode(GComparison<?,?,?> comparison_p) {
    this(comparison_p, null);
  }
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @param domain_p the optional editing domain for undo/redo
   */
  public EMFDiffNode(GComparison<?,?,?> comparison_p, EditingDomain domain_p) {
    this(comparison_p, domain_p, true, true);
  }
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @param domain_p the optional editing domain for undo/redo
   * @param isLeftEditionPossible_p whether edition on the left is possible at all
   * @param isRightEditionPossible_p whether edition on the right is possible at all
   */
  public EMFDiffNode(GComparison<?,?,?> comparison_p, EditingDomain domain_p,
      boolean isLeftEditionPossible_p, boolean isRightEditionPossible_p) {
    this(comparison_p, domain_p, isLeftEditionPossible_p, isRightEditionPossible_p,
        EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole());
  }
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @param domain_p the optional editing domain for undo/redo
   * @param isLeftEditionPossible_p whether edition on the left is possible at all
   * @param isRightEditionPossible_p whether edition on the right is possible at all
   * @param leftRole_p the non-null role on the left-hand side
   */
  public EMFDiffNode(GComparison<?,?,?> comparison_p, EditingDomain domain_p,
      boolean isLeftEditionPossible_p, boolean isRightEditionPossible_p,
      Role leftRole_p) {
    super(
        Differencer.CHANGE,
        comparison_p.isThreeWay()? new ModelScopeTypedElement(
            comparison_p.getScope(Role.ANCESTOR)): null,
        new ModelScopeTypedElement(comparison_p.getScope(leftRole_p)),
        new ModelScopeTypedElement(comparison_p.getScope(leftRole_p.opposite())));
    _resourceManager = new ComparisonResourceManager();
    _contents = createContents(comparison_p);
    _editingDomain = domain_p;
    _editorInput = null;
    _leftRole = leftRole_p;
    _twoWayReferenceRole = null;
    _categoryManager = new CategoryManager(this);
    _isTargetEditionPossible = (leftRole_p == Role.TARGET)? isLeftEditionPossible_p:
      isRightEditionPossible_p;
    _isReferenceEditionPossible = (leftRole_p == Role.TARGET)? isRightEditionPossible_p:
      isLeftEditionPossible_p;
    _isTargetEditable = true;
    _isReferenceEditable = true;
    _isTargetModified = false;
    _isReferenceModified = false;
    _drivingRole = getTargetRole() != null? getTargetRole(): _leftRole;
    _domainChangeListener = (domain_p == null)? null: createDomainListener(domain_p);
    _userPropertyOwnerDelegate = new UserPropertyOwner();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#addUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, java.lang.Object)
   */
  public <T> boolean addUserProperty(Identifier<T> id_p, T initialValue_p) {
    return getUserPropertyOwnerDelegate().addUserProperty(id_p, initialValue_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#addUserPropertyChangeListener(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, org.eclipse.jface.util.IPropertyChangeListener)
   */
  public boolean addUserPropertyChangeListener(Identifier<?> id_p,
      IPropertyChangeListener listener_p) {
    return getUserPropertyOwnerDelegate().addUserPropertyChangeListener(id_p, listener_p);
  }
  
  /**
   * Create and return the UI comparison contents of this node
   * @param comparison_p a non-null comparison
   * @return a non-null UI comparison
   */
  protected UIComparison createContents(GComparison<?,?,?> comparison_p) {
    return new UIComparisonImpl(comparison_p);
  }
  
  /**
   * Create and return a listener for the given editing domain that ensures
   * this node is kept in sync, if possible, otherwise return null
   * @param domain_p a non-null editing domain
   * @return a potentially null object
   */
  protected IOperationHistoryListener createDomainListener(EditingDomain domain_p) {
    IOperationHistoryListener result = null;
    if (domain_p.getCommandStack() instanceof IWorkspaceCommandStack) {
      IOperationHistory opHistory =
          ((IWorkspaceCommandStack)domain_p.getCommandStack()).getOperationHistory();
      result = new IOperationHistoryListener() {
        /**
         * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
         */
        public void historyNotification(OperationHistoryEvent event_p) {
          IUndoContext undoContext = getUndoContext();
          if (undoContext != null && event_p.getOperation().hasContext(undoContext)) {
            switch (event_p.getEventType()) {
            case OperationHistoryEvent.OPERATION_ADDED:
            case OperationHistoryEvent.REDONE:
            case OperationHistoryEvent.UNDONE:
              updateDifferenceNumbers();
              break;
            default: // Ignore
            }
          }
        }
      };
      opHistory.addOperationHistoryListener(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    // Resource manager
    _resourceManager.dispose();
    // User properties
    getUserPropertyOwnerDelegate().dispose();
    // Command stack
    EditingDomain domain = getEditingDomain();
    if (domain != null && _domainChangeListener != null) {
      IOperationHistory opHistory =
          ((IWorkspaceCommandStack)domain.getCommandStack()).getOperationHistory();
      opHistory.removeOperationHistoryListener(_domainChangeListener);
      _domainChangeListener = null;
    }
    // Input
    _editorInput = null;
  }
  
  /**
   * Return the model comparison of this node
   * @return a non-null comparison, unless the UI comparison has been disposed
   */
  @SuppressWarnings("rawtypes")
  public GComparison getActualComparison() {
    return getUIComparison().getActualComparison();
  }
  
  /**
   * Return the category manager for this diff node
   * @return a non-null object
   */
  public CategoryManager getCategoryManager() {
    return _categoryManager;
  }
  
  /**
   * Return the container of the given match from a UI perspective
   * @param match_p a non-null match
   * @return a potentially null match
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected IMatch<?> getContainerOf(IMatch<?> match_p) {
    IMatch<?> result = null;
    IComparison<?> comparison = getActualComparison();
    if (comparison != null) {
      Role containerSide;
      Role drivingRole = getDrivingRole();
      if (match_p.getUncoveredRole() == drivingRole) {
        containerSide = drivingRole.opposite();
      } else {
        containerSide = drivingRole;
      }
      result = comparison.getContainerOf((IMatch)match_p, containerSide);
    }
    return result;
  }
  
  /**
   * Return the role that drives the representation of the model comparison
   * @return a non-null role which is TARGET or REFERENCE
   */
  public Role getDrivingRole() {
    return _drivingRole;
  }
  
  /**
   * Return the editing domain for merge operations, if any
   * @return a potentially null editing domain
   */
  public EditingDomain getEditingDomain() {
    return _editingDomain;
  }
  
  /**
   * Return the editor input associated to this node, if any
   * @return a potentially null editor input
   */
  public IEditorInput getEditorInput() {
    return _editorInput;
  }
  
  /**
   * Return the role which is used as the reference role, if any.
   * @see IComparisonMethod#setTwoWayReferenceRole(Role)
   * @return ANCESTOR, TARGET, REFERENCE, or null
   */
  public Role getReferenceRole() {
    return isThreeWay()? Role.ANCESTOR: _twoWayReferenceRole;
  }
  
  /**
   * Return the resource manager for this node
   * @return a non-null resource manager
   */
  public ComparisonResourceManager getResourceManager() {
    return _resourceManager;
  }
  
  /**
   * Return the role that corresponds to the given side
   * @param left_p whether the side to consider is left or right
   * @return a non-null role
   */
  public Role getRoleForSide(boolean left_p) {
    return left_p? _leftRole: _leftRole.opposite();
  }
  
  /**
   * Return the scope o the given side
   * @param left_p whether the side is left or right
   * @return a non-null scope, unless the UI comparison has been disposed
   */
  public IEditableTreeDataScope<?> getScope(boolean left_p) {
    IComparison.Editable<?> comparison = getActualComparison();
    return comparison != null?
        comparison.getScope(getRoleForSide(left_p)): null;
  }
  
  /**
   * Return the target role of the merge, if defined
   * @return a potentially null role
   */
  public Role getTargetRole() {
    Role result = null;
    boolean leftEditable = isEditable(true);
    boolean rightEditable = isEditable(false);
    if (leftEditable && !rightEditable) {
      result = getRoleForSide(true);
    } else if (!leftEditable && rightEditable) {
      result = getRoleForSide(false);
    }
    return result;
  }
  
  /**
   * Return the UI comparison of this node
   * @return a non-null UI comparison
   */
  public UIComparison getUIComparison() {
    return _contents;
  }
  
  /**
   * Return the undo context for this node, if applicable.
   * Class invariant: getUndoContext() == null || isReactive()
   * @return a potentially null undo context
   */
  public IUndoContext getUndoContext() {
    IUndoContext result = null;
    if (isReactive()) {
      Collection<IUndoContext> contexts = new HashSet<IUndoContext>();
      // isReactive() implies that the editing domain is non-null and its command stack is a
      // IWorkspaceCommandStack, which implies that the editing domain is a TransactionalEditingDomain
      TransactionalEditingDomain tDomain = (TransactionalEditingDomain)getEditingDomain();
      IWorkspaceCommandStack cmdStack = (IWorkspaceCommandStack)tDomain.getCommandStack();
      IUndoContext defaultContext = cmdStack.getDefaultUndoContext();
      if (defaultContext != null) {
        contexts.add(defaultContext);
      }
      Resource uiResource = getUIComparison().eResource();
      if (uiResource != null) {
        contexts.add(new ResourceUndoContext(tDomain, uiResource));
      }
      Resource coreResource = getActualComparison().eResource();
      if (coreResource != null) {
        contexts.add(new ResourceUndoContext(tDomain, coreResource));
      }
      result = new CompositeUndoContext(contexts);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#getUserProperties()
   */
  public Collection<Identifier<?>> getUserProperties() {
    return getUserPropertyOwnerDelegate().getUserProperties();
  }
  
  /**
   * Return the delegate user property owner
   * @return a non-null object
   */
  protected IUserPropertyOwner getUserPropertyOwnerDelegate() {
    return _userPropertyOwnerDelegate;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#getUserPropertyValue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public <T> T getUserPropertyValue(Identifier<T> id_p) {
    return getUserPropertyOwnerDelegate().getUserPropertyValue(id_p);
  }
  
  /**
   * @see org.eclipse.compare.structuremergeviewer.DiffContainer#hasChildren()
   */
  @Override
  public boolean hasChildren() {
    // Is there content?
    IComparison<?> comparison = getActualComparison();
    return comparison != null? comparison.hasRemainingDifferences(): false;
  }
  
  /**
   * Return whether this node is able to react to changes
   */
  public boolean isReactive() {
    boolean result = _domainChangeListener != null;
    if (result) {
      Resource mainResource = getUIComparison().eResource();
      result = mainResource != null &&
          getEditingDomain().getResourceSet().getResources().contains(mainResource);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#hasUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean hasUserProperty(Identifier<?> id_p) {
    return getUserPropertyOwnerDelegate().hasUserProperty(id_p);
  }
  
  /**
   * Silently mark the given set of differences as ignored
   * @param differences_p a non-null, potentially empty collection
   */
  public void ignore(Collection<? extends IDifference<?>> differences_p) {
    for (IDifference<?> difference : differences_p) {
      if (difference instanceof IDifference.Editable) {
        ((IDifference.Editable<?>)difference).setIgnored(true);
        // Also on symmetrical if any
        if (difference instanceof IValuePresence) {
          IValuePresence<?> symmetrical = ((IValuePresence<?>)difference).getSymmetrical();
          if (symmetrical instanceof IDifference.Editable) {
            ((IDifference.Editable<?>)symmetrical).setIgnored(true);
          }
          // Also on symmetrical ownership if any
          if (difference instanceof IReferenceValuePresence) {
            IReferenceValuePresence<?> symmetricalOwnership =
                ((IReferenceValuePresence<?>)difference).getSymmetricalOwnership();
            if (symmetricalOwnership instanceof IDifference.Editable) {
              ((IDifference.Editable<?>)symmetricalOwnership).setIgnored(true);
            }
          }
        }
      }
    }
  }
  
  /**
   * Return whether the given reference must be considered as a containment
   * @param reference_p a potentially null feature
   */
  public boolean isContainer(Object reference_p) {
    boolean result = false;
    IComparison<?> comparison = getActualComparison();
    if (comparison != null) {
      ITreeDataScope<?> scope = comparison.getScope(getDrivingRole());
      result = scope.mIsContainerReference(reference_p);
    }
    return result;
  }
  
  /**
   * Return whether the given reference must be considered as a containment
   * @param reference_p a potentially null feature
   */
  public boolean isContainment(Object reference_p) {
    boolean result = false;
    IComparison<?> comparison = getActualComparison();
    if (comparison != null) {
      ITreeDataScope<?> scope = comparison.getScope(getDrivingRole());
      result = scope.mIsContainmentReference(reference_p);
    }
    return result;
  }
  
  /**
   * Return whether edition of the given side is enabled
   * @param left_p whether the side is left or right
   */
  public boolean isEditable(boolean left_p) {
    boolean result = isEditionPossible(left_p);
    if (result) {
      if (getRoleForSide(left_p) == Role.TARGET) {
        result = _isTargetEditable;
      } else {
        result = _isReferenceEditable;
      }
    }
    return result;
  }
  
  /**
   * Return whether editing the scope of the given side is possible at all
   * @param left_p whether the side is left or right
   */
  public boolean isEditionPossible(boolean left_p) {
    return getRoleForSide(left_p) == Role.TARGET? _isTargetEditionPossible:
      _isReferenceEditionPossible;
  }
  
  /**
   * Return whether the are still differences that the user has to handle
   */
  public boolean isEmpty() {
    return getCategoryManager().isEmpty();
  }
  
  /**
   * Return whether the given side has been modified
   * @param left_p whether the side is left or right
   */
  public boolean isModified(boolean left_p) {
    return getRoleForSide(left_p) == Role.TARGET? _isTargetModified:
      _isReferenceModified;
  }
  
  /**
   * Return whether this comparison is 3-way
   */
  public boolean isThreeWay() {
    IComparison<?> comparison = getActualComparison();
    return comparison != null? comparison.isThreeWay(): false;
  }
  
  /**
   * Return whether to support undo/redo (cost in memory usage and response time)
   */
  public boolean isUndoRedoSupported() {
    return getEditingDomain() != null && (!hasUserProperty(P_SUPPORT_UNDO_REDO) ||
        isUserPropertyTrue(P_SUPPORT_UNDO_REDO));
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#isUserPropertyFalse(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean isUserPropertyFalse(Identifier<Boolean> id_p) {
    return getUserPropertyOwnerDelegate().isUserPropertyFalse(id_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#isUserPropertyTrue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean isUserPropertyTrue(Identifier<Boolean> id_p) {
    return getUserPropertyOwnerDelegate().isUserPropertyTrue(id_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean removeUserProperty(Identifier<?> id_p) {
    return getUserPropertyOwnerDelegate().removeUserProperty(id_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserPropertyChangeListener(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, org.eclipse.jface.util.IPropertyChangeListener)
   */
  public boolean removeUserPropertyChangeListener(Identifier<?> id_p,
      IPropertyChangeListener listener_p) {
    return getUserPropertyOwnerDelegate().removeUserPropertyChangeListener(id_p, listener_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removeUserPropertyChangeListener(IPropertyChangeListener listener_p) {
    getUserPropertyOwnerDelegate().removeUserPropertyChangeListener(listener_p);
  }
  
  /**
   * Set the role that drives the representation of the model comparison
   * @param drivingRole_p a non-null role which is TARGET or REFERENCE
   */
  public void setDrivingRole(Role drivingRole_p) {
    if (Role.TARGET == drivingRole_p || Role.REFERENCE == drivingRole_p) {
      _drivingRole = drivingRole_p;
    }
  }
  
  /**
   * Set whether the given side is editable
   * @param isEditable_p whether it is editable
   * @param left_p whether the side is left or right
   */
  public void setEditable(boolean isEditable_p, boolean left_p) {
    if (isEditionPossible(left_p)) {
      if (getRoleForSide(left_p) == Role.TARGET)
        _isTargetEditable = isEditable_p;
      else
        _isReferenceEditable = isEditable_p;
    }
  }
  
  /**
   * Set whether editing the scope of the given side is possible at all
   * @param possible_p whether it is possible
   * @param left_p whether the side is left or right
   */
  public void setEditionPossible(boolean possible_p, boolean left_p) {
    if (getRoleForSide(left_p) == Role.TARGET) {
      _isTargetEditionPossible = possible_p;
    } else {
      _isReferenceEditionPossible = possible_p;
    }
  }
  
  /**
   * Set the editor input associated to this node
   * @param editorInput_p a potentially null editor input
   */
  public void setEditorInput(IEditorInput editorInput_p) {
    _editorInput = editorInput_p;
  }
  
  /**
   * Set the role on the left-hand side
   * @param leftRole_p a non-null role which is TARGET or REFERENCE
   */
  public void setLeftRole(Role leftRole_p) {
    if (Role.TARGET == leftRole_p || Role.REFERENCE == leftRole_p)
      _leftRole = leftRole_p;
  }
  
  /**
   * Set whether the given side has been modified
   * @param isModified_p whether it has been modified
   * @param left_p whether the side is left or right
   */
  public void setModified(boolean isModified_p, boolean left_p) {
    if (getRoleForSide(left_p) == Role.TARGET)
      _isTargetModified = isModified_p;
    else
      _isReferenceModified = isModified_p;
  }
  
  /**
   * Set the role which is used as the reference role.
   * In a three-way comparison, this operation has no effect.
   * @see IComparisonMethod#setTwoWayReferenceRole(Role)
   * @param role_p TARGET, REFERENCE, or null
   */
  public void setReferenceRole(Role role_p) {
    if (!isThreeWay()) {
      _twoWayReferenceRole = role_p;
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#setUserPropertyValue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, java.lang.Object)
   */
  public <T> boolean setUserPropertyValue(Identifier<T> id_p, T newValue_p) {
    return getUserPropertyOwnerDelegate().setUserPropertyValue(id_p, newValue_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#setUserPropertyValue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, boolean)
   */
  public boolean setUserPropertyValue(Identifier<Boolean> id_p, boolean newValue_p) {
    return getUserPropertyOwnerDelegate().setUserPropertyValue(id_p, newValue_p);
  }
  
  /**
   * Re-compute filtering and differences numbers
   */
  public void updateDifferenceNumbers() {
    getCategoryManager().update();
    fireChange();
  }
  
}