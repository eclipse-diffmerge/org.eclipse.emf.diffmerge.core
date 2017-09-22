/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S and others.
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl;
import org.eclipse.emf.diffmerge.ui.setup.ModelScopeTypedElement;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorInput;


/**
 * An ICompareInput that wraps a model comparison.
 * @author Olivier Constant
 */
public class EMFDiffNode extends DiffNode implements IDisposable, IEditingDomainProvider {
  
  /** The resource manager */
  private final ComparisonResourceManager _resourceManager;
  
  /** The non-null comparison-related contents */
  private final UIComparison _contents;
  
  /** The optional editing domain */
  private final EditingDomain _editingDomain;
  
  /** The optional associated editor input */
  private IEditorInput _editorInput;
  
  /** The role that drives the representation of the comparison */
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
  
  /** A map from color kind to SWT color code from the SWT class */
  private final Map<DifferenceColorKind, Integer> _differenceColors;
  
  
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
    super(
        Differencer.CHANGE,
        comparison_p.isThreeWay()? new ModelScopeTypedElement(
            comparison_p.getScope(Role.ANCESTOR)): null,
        new ModelScopeTypedElement(comparison_p.getScope(Role.TARGET)),
        new ModelScopeTypedElement(comparison_p.getScope(Role.REFERENCE)));
    _resourceManager = new ComparisonResourceManager();
    _contents = new UIComparisonImpl(comparison_p);
    _editingDomain = domain_p;
    _editorInput = null;
    _leftRole = EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
    _drivingRole = _leftRole;
    _twoWayReferenceRole = null;
    _categoryManager = new CategoryManager(this);
    _differenceColors = new HashMap<EMFDiffMergeUIPlugin.DifferenceColorKind, Integer>();
    initializeDifferenceColors(_differenceColors);
    _useCustomIcons = true;
    _useCustomLabels = false;
    _isTargetEditionPossible = isLeftEditionPossible_p;
    _isReferenceEditionPossible = isRightEditionPossible_p;
    _isTargetEditable = true;
    _isReferenceEditable = true;
    _isTargetModified = false;
    _isReferenceModified = false;
    _isHideDifferenceNumbers = false;
    _isShowMergeImpact = true;
    _isUndoRedoSupported = _editingDomain != null;
    _isLogEvents = false;
    _defaultShowMergeImpact = _isShowMergeImpact;
    _defaultCoverChildren = true;
    _defaultIncrementalMode = false;
  }
  
  /**
   * Initialize the given map from color kinds to SWT color codes
   * @param differenceColorsMap_p a non-null, modifiable map
   */
  protected void initializeDifferenceColors(
      Map<DifferenceColorKind, Integer> differenceColorsMap_p) {
    differenceColorsMap_p.put(DifferenceColorKind.LEFT, Integer.valueOf(SWT.COLOR_DARK_RED));
    differenceColorsMap_p.put(DifferenceColorKind.RIGHT, Integer.valueOf(SWT.COLOR_BLUE));
    differenceColorsMap_p.put(DifferenceColorKind.BOTH, Integer.valueOf(SWT.COLOR_DARK_MAGENTA));
    differenceColorsMap_p.put(DifferenceColorKind.NONE, Integer.valueOf(SWT.COLOR_GRAY));
    differenceColorsMap_p.put(DifferenceColorKind.CONFLICT, Integer.valueOf(SWT.COLOR_RED));
    differenceColorsMap_p.put(DifferenceColorKind.DEFAULT, Integer.valueOf(SWT.COLOR_BLACK));
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
   * Return whether to support undo/redo (cost in memory usage and response time)
   */
  public boolean isUndoRedoSupported() {
    return _isUndoRedoSupported;
  }
  
  /**
   * Return whether events must be logged
   */
  public boolean isLogEvents() {
    return _isLogEvents;
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
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    _resourceManager.dispose();
    _editorInput = null;
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
   * Return the color that corresponds to the given color kind
   * @param colorKind_p a non-null color kind
   * @return a non-null color
   */
  public Color getDifferenceColor(DifferenceColorKind colorKind_p) {
    int colorCode = SWT.COLOR_BLACK;
    Integer colorCodeI = _differenceColors.get(colorKind_p);
    if (colorCodeI != null)
      colorCode = colorCodeI.intValue();
    return UIUtil.getColor(colorCode);
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
   * Return the role which is used as the reference role, if any.
   * The reference role determines that all differences should be represented
   * in a way which is relative to it. In a three-way comparison, it is always
   * ANCESTOR. In a two-way comparison, it can naturally be REFERENCE but it
   * does not have to. If null, then both sides in the two-way comparison
   * are represented in a symmetric way.
   * @return ANCESTOR, TARGET, REFERENCE, or null
   */
  public Role getReferenceRole() {
    return isThreeWay()? Role.ANCESTOR: _twoWayReferenceRole;
  }
  
  /**
   * Return the editor input associated to this node, if any
   * @return a potentially null editor input
   */
  public IEditorInput getEditorInput() {
    return _editorInput;
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
   * @see org.eclipse.compare.structuremergeviewer.DiffContainer#hasChildren()
   */
  @Override
  public boolean hasChildren() {
    // Is there content?
    IComparison comparison = getActualComparison();
    return comparison != null? comparison.hasRemainingDifferences(): false;
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
   * Return whether this comparison is 3-way
   */
  public boolean isThreeWay() {
    IComparison comparison = getActualComparison();
    return comparison != null? comparison.isThreeWay(): false;
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
   * Set the color that corresponds to the given color kind
   * @param colorKind_p a non-null color kind
   * @param swtColor_p an identifier of an SWT color from class SWT
   */
  public void setDifferenceColor(DifferenceColorKind colorKind_p, int swtColor_p) {
    _differenceColors.put(colorKind_p, new Integer(swtColor_p));
  }
  
  /**
   * Set the role that drives the representation of the model comparison
   * @param drivingRole_p a non-null role which is TARGET or REFERENCE
   */
  public void setDrivingRole(Role drivingRole_p) {
    if (Role.TARGET == drivingRole_p || Role.REFERENCE == drivingRole_p)
      _drivingRole = drivingRole_p;
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
   * @see EMFDiffNode#getReferenceRole()
   * @param role_p TARGET, REFERENCE, or null
   */
  public void setReferenceRole(Role role_p) {
    if (!isThreeWay())
      _twoWayReferenceRole = role_p;
  }
  
  /**
   * Set whether an impact dialog must be shown at merge time
   */
  public void setShowMergeImpact(boolean showMergeImpact_p) {
    _isShowMergeImpact = showMergeImpact_p;
  }
  
  /**
   * Set whether to support undo/redo (cost in memory usage and response time).
   * Undo/redo may only be supported if the editing domain is known (see getEditingDomain()).
   */
  public void setUndoRedoSupported(boolean supportUndoRedo_p) {
    _isUndoRedoSupported = getEditingDomain() != null && supportUndoRedo_p;
  }
  
  /**
   * Set whether this viewer should use custom icons to represent differences
   */
  public void setUseCustomIcons(boolean useCustom_p) {
    _useCustomIcons = useCustom_p;
  }
  
  /**
   * Set whether this viewer should use custom labels to represent differences
   */
  public void setUseCustomLabels(boolean useCustom_p) {
    _useCustomLabels = useCustom_p;
  }
  
  /**
   * Return whether this viewer uses custom icons to represent differences
   */
  public boolean usesCustomIcons() {
    return _useCustomIcons;
  }
  
  /**
   * Return whether this viewer uses custom labels to represent differences
   */
  public boolean usesCustomLabels() {
    return _useCustomLabels;
  }
  
  /**
   * Re-compute filtering and differences numbers
   */
  public void updateDifferenceNumbers() {
    getCategoryManager().update();
    fireChange();
  }
  
}