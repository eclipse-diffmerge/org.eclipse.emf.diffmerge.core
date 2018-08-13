/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - Bug #442492 : hide number of differences in the UI
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl;
import org.eclipse.emf.diffmerge.ui.setup.ModelScopeTypedElement;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.util.CompositeUndoContext;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;


/**
 * An ICompareInput that wraps a model comparison.
 * @author Olivier Constant
 */
public class EMFDiffNode extends DiffNode implements IDisposable, IEditingDomainProvider,
IPropertyChangeNotifier {
  
  /** The name of the "use technical labels" property */
  public static final String PROPERTY_TECHNICAL_LABELS = "PROPERTY_TECHNICAL_LABELS"; //$NON-NLS-1$
  
  /** The resource manager */
  private final ComparisonResourceManager _resourceManager;
  
  /** The non-null comparison-related contents */
  private final UIComparison _contents;
  
  /** The optional editing domain */
  private final EditingDomain _editingDomain;
  
  /** The optional listener that reacts to changes on the editing domain */
  protected IOperationHistoryListener _domainChangeListener;
  
  /** The non-null set of property change listeners */
  private final Set<IPropertyChangeListener> _changeListeners;
  
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
  
  /** Whether to use custom icons for differences */
  private boolean _useCustomIcons;
  
  /** Whether to use custom labels for differences */
  private boolean _useCustomLabels;
  
  /** Whether to use technical (vs. simplified) labels to represent, 
   * in particular, meta elements */
  private boolean _useTechicalLabels;
  
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
  
  /** Whether differences number must be hidden */
  private boolean _isHideDifferenceNumbers;
  
  /** Whether an impact dialog must be shown at merge time */
  private boolean _isShowMergeImpact;
  
  /** Whether the left and right sides may be shown */
  private boolean _isShowSidesPossible;
  
  /** Whether to support undo/redo (cost in memory usage and response time) */
  private boolean _isUndoRedoSupported;
  
  /** Whether events must be logged */
  private boolean _isLogEvents;
  
  /** The default value for the "cover children" property as proposed to the user when merging */
  private boolean _defaultCoverChildren;
  
  /** The default value for the "incremental mode" property as proposed to the user when merging */
  private boolean _defaultIncrementalMode;
  
  /** The default value for "show merge impact" property as proposed to the user when merging */
  private boolean _defaultShowMergeImpact;
  
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   */
  public EMFDiffNode(EComparison comparison_p) {
    this(comparison_p, null);
  }
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @param domain_p the optional editing domain for undo/redo
   */
  public EMFDiffNode(EComparison comparison_p, EditingDomain domain_p) {
    this(comparison_p, domain_p, true, true);
  }
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @param domain_p the optional editing domain for undo/redo
   * @param isLeftEditionPossible_p whether edition on the left is possible at all
   * @param isRightEditionPossible_p whether edition on the right is possible at all
   */
  public EMFDiffNode(EComparison comparison_p, EditingDomain domain_p,
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
  public EMFDiffNode(EComparison comparison_p, EditingDomain domain_p,
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
    _drivingRole = _leftRole;
    _twoWayReferenceRole = null;
    _categoryManager = new CategoryManager(this);
    _useCustomIcons = true;
    _useCustomLabels = false;
    _useTechicalLabels = false;
    _isTargetEditionPossible = (leftRole_p == Role.TARGET)? isLeftEditionPossible_p:
      isRightEditionPossible_p;
    _isReferenceEditionPossible = (leftRole_p == Role.TARGET)? isRightEditionPossible_p:
      isLeftEditionPossible_p;
    _isTargetEditable = true;
    _isReferenceEditable = true;
    _isTargetModified = false;
    _isReferenceModified = false;
    _isHideDifferenceNumbers = false;
    _isShowMergeImpact = false;
    _isShowSidesPossible = true;
    _isUndoRedoSupported = _editingDomain != null;
    _isLogEvents = false;
    _defaultShowMergeImpact = _isShowMergeImpact;
    _defaultCoverChildren = true;
    _defaultIncrementalMode = false;
    _domainChangeListener = (domain_p == null)? null: createDomainListener(domain_p);
    _changeListeners = new HashSet<IPropertyChangeListener>(1);
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.add(listener_p);
  }
  
  /**
   * Create and return the UI comparison contents of this node
   * @param comparison_p a non-null comparison
   * @return a non-null UI comparison
   */
  protected UIComparison createContents(EComparison comparison_p) {
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
          if (event_p.getOperation().hasContext(getUndoContext())) {
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
    _resourceManager.dispose();
    EditingDomain domain = getEditingDomain();
    if (domain != null && _domainChangeListener != null) {
      IOperationHistory opHistory =
          ((IWorkspaceCommandStack)domain.getCommandStack()).getOperationHistory();
      opHistory.removeOperationHistoryListener(_domainChangeListener);
      _domainChangeListener = null;
    }
    _editorInput = null;
    _changeListeners.clear();
  }
  
  /**
   * Notify listeners of a property change event
   * @param propertyName_p the non-null name of the property
   * @param newValue_p the potentially null, new value of the property
   */
  protected void firePropertyChangeEvent(String propertyName_p, Object newValue_p) {
    PropertyChangeEvent event = new PropertyChangeEvent(
        this, propertyName_p, null, newValue_p);
    for (IPropertyChangeListener listener : _changeListeners) {
      listener.propertyChange(event);
    }
  }
  
  /**
   * Return the model comparison of this node
   * @return a non-null comparison, unless the UI comparison has been disposed
   */
  public EComparison getActualComparison() {
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
  protected EMatch getContainerOf(EMatch match_p) {
    EMatch result = null;
    IComparison comparison = getActualComparison();
    if (comparison != null) {
      Role containerSide;
      Role drivingRole = getDrivingRole();
      if (match_p.getUncoveredRole() == drivingRole)
        containerSide = drivingRole.opposite();
      else
        containerSide = drivingRole;
      result = (EMatch)comparison.getContainerOf(match_p, containerSide);
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
      TransactionalEditingDomain tDomain = (TransactionalEditingDomain)getEditingDomain();
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
   * @see org.eclipse.compare.structuremergeviewer.DiffContainer#hasChildren()
   */
  @Override
  public boolean hasChildren() {
    // Is there content?
    IComparison comparison = getActualComparison();
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
   * Return whether the given structural feature must be considered as a containment
   * @param feature_p a potentially null feature
   */
  public boolean isContainment(EStructuralFeature feature_p) {
    boolean result = false;
    if (feature_p instanceof EReference) {
      EReference reference = (EReference)feature_p;
      IComparison comparison = getActualComparison();
      if (comparison != null) {
        IFeaturedModelScope scope = comparison.getScope(getDrivingRole());
        result = scope.isContainment(reference);
      } else {
        result = reference.isContainment();
      }
    }
    return result;
  }
  
  /**
   * Return the default value for the "cover children" property as proposed to the user when merging 
   */
  public boolean isDefaultCoverChildren() {
    return _defaultCoverChildren;
  }
  
  /**
   * Return the default value for the "incremental mode" property as proposed to the user when merging 
   */
  public boolean isDefaultIncrementalMode() {
    return _defaultIncrementalMode;
  }
  
  /**
   * Return the default value for the "show merge impact" property as proposed to the user when merging 
   */
  public boolean isDefaultShowImpact() {
    return _defaultShowMergeImpact;
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
   * Return whether this viewer displays difference numbers
   */
  public boolean isHideDifferenceNumbers() {
    return _isHideDifferenceNumbers;
  }
  
  /**
   * Return whether events must be logged
   */
  public boolean isLogEvents() {
    return _isLogEvents;
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
   * Return whether an impact dialog must be shown at merge time
   */
  public boolean isShowMergeImpact() {
    return _isShowMergeImpact;
  }
  
  /**
   * Return whether the left and right sides may be shown
   */
  public boolean isShowSidesPossible() {
    return _isShowSidesPossible;
  }
  
  /**
   * Return whether this comparison is 3-way
   */
  public boolean isThreeWay() {
    IComparison comparison = getActualComparison();
    return comparison != null? comparison.isThreeWay(): false;
  }
  
  /**
   * Return whether to support undo/redo (cost in memory usage and response time)
   */
  public boolean isUndoRedoSupported() {
    return _isUndoRedoSupported;
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.remove(listener_p);
  }
  
  /**
   * Set the default value for the "cover children" property as proposed to the user when merging 
   */
  public void setDefaultCoverChildren(boolean coverChildren_p) {
    _defaultCoverChildren = coverChildren_p;
  }
  
  /**
   * Set the default value for the "incremental mode" property as proposed to the user when merging 
   */
  public void setDefaultIncrementalMode(boolean isIncrementalMode_p) {
    _defaultIncrementalMode = isIncrementalMode_p;
  }
  
  /**
   * Set the default value for the "show merge impact" property as proposed to the user when merging 
   */
  public void setDefaultShowImpact(boolean showImpact_p) {
    _defaultShowMergeImpact = showImpact_p;
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
   * Set whether this viewer should display differences numbers
   */
  public void setHideDifferenceNumbers(boolean hideDifferenceNumbers_p) {
    _isHideDifferenceNumbers = hideDifferenceNumbers_p;
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
   * Set whether events must be logged
   */
  public void setLogEvents(boolean logEvents_p) {
    _isLogEvents = logEvents_p;
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
   * Set whether an impact dialog must be shown at merge time
   */
  public void setShowMergeImpact(boolean showMergeImpact_p) {
    _isShowMergeImpact = showMergeImpact_p;
  }
  
  /**
   * Set whether the left and right sides may be shown
   */
  public void setShowSidesPossible(boolean showSidesPossible_p) {
    _isShowSidesPossible = showSidesPossible_p;
  }
  
  /**
   * Set whether to support undo/redo (cost in memory usage and response time).
   * Undo/redo may only be supported if the editing domain is known (see getEditingDomain()).
   */
  public void setUndoRedoSupported(boolean supportUndoRedo_p) {
    _isUndoRedoSupported = getEditingDomain() != null && supportUndoRedo_p;
  }
  
  /**
   * Set whether viewers must use custom icons to represent differences
   */
  public void setUseCustomIcons(boolean useCustom_p) {
    _useCustomIcons = useCustom_p;
  }
  
  /**
   * Set whether viewers must use custom labels to represent differences
   */
  public void setUseCustomLabels(boolean useCustom_p) {
    _useCustomLabels = useCustom_p;
  }
  
  /**
   * Set whether viewers must use technical (vs. simplified) labels to represent,
   * in particular, meta elements
   * @param useTechicalLabels_p whether techical labels must be used
   */
  public void setUseTechicalLabels(boolean useTechicalLabels_p) {
    _useTechicalLabels = useTechicalLabels_p;
    firePropertyChangeEvent(PROPERTY_TECHNICAL_LABELS, Boolean.valueOf(useTechicalLabels_p));
  }
  
  /**
   * Re-compute filtering and differences numbers
   */
  public void updateDifferenceNumbers() {
    getCategoryManager().update();
    fireChange();
  }
  
  /**
   * Return whether this viewer uses custom icons to represent differences
   */
  public boolean usesCustomIcons() {
    return _useCustomIcons;
  }
  
  /**
   * Return whether viewers must custom labels to represent differences
   */
  public boolean usesCustomLabels() {
    return _useCustomLabels;
  }
  
  /**
   * Return whether viewers must use technical (vs. simplified) labels to represent,
   * in particular, meta elements
   */
  public boolean usesTechicalLabels() {
    return _useTechicalLabels;
  }
  
}