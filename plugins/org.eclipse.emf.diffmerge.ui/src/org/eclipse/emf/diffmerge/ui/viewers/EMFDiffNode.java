/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl;
import org.eclipse.emf.diffmerge.ui.setup.EMFDiffMergeEditorInput.ScopeTypedElementWrapper;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.util.structures.FHashMap;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.services.IDisposable;


/**
 * An ICompareInput that wraps a model comparison.
 * @author Olivier Constant
 */
public class EMFDiffNode extends DiffNode implements IDisposable, IEditingDomainProvider {
  
  /**
   * The kinds of user-level differences.
   */
  public static enum UserDifferenceKind {
    PRESENCE_LEFT,  // The unmatched presence of an element on the left
    PRESENCE_RIGHT, // The unmatched presence of an element on the right
    MOVE,           // A move
    NO_CONTAINMENT  // A difference which is unrelated to the containment tree
  }
  
  
  /** The resource manager */
  private final ComparisonResourceManager _resourceManager;
  
  /** The non-null comparison-related contents */
  private final UIComparison _contents;
  
  /** The optional editing domain */
  private final EditingDomain _editingDomain;
  
  /** The role that drives the representation of the comparison */
  private Role _drivingRole;
  
  /** The non-null role on the left-hand side */
  private Role _leftRole;
  
  /** The potentially null role to use as a reference in a two-way comparison */
  private Role _twoWayReferenceRole;
  
  /** The set of difference kinds which are counted */
  private final Set<UserDifferenceKind> _countedKinds;
  
  /** The map from matches to difference numbers */
  private final EMap<EMatch, Integer> _matchToNb;
  
  /** Whether to use custom icons for differences */
  private boolean _useCustomIcons;
  
  /** Whether to use custom labels for differences */
  private boolean _useCustomLabels;
  
  /** Whether the left model is editable */
  private boolean _isTargetEditable;
  
  /** Whether the right model is editable */
  private boolean _isReferenceEditable;
  
  /** Whether editing the target scope is possible at all */
  private final boolean _isTargetEditionPossible;
  
  /** Whether editing the reference scope is possible at all */
  private final boolean _isReferenceEditionPossible;
  
  /** Whether the left model has been modified */
  private boolean _isTargetModified;
  
  /** Whether the right model has been modified */
  private boolean _isReferenceModified;
  
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
        comparison_p.isThreeWay()? new ScopeTypedElementWrapper(
            comparison_p.getScope(Role.ANCESTOR)): null,
        new ScopeTypedElementWrapper(comparison_p.getScope(Role.TARGET)),
        new ScopeTypedElementWrapper(comparison_p.getScope(Role.REFERENCE)));
    _resourceManager = new ComparisonResourceManager();
    _contents = new UIComparisonImpl(comparison_p);
    _editingDomain = domain_p;
    _leftRole = EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
    _drivingRole = _leftRole;
    _twoWayReferenceRole = null;
    _differenceColors = new HashMap<EMFDiffMergeUIPlugin.DifferenceColorKind, Integer>();
    initializeDifferenceColors(_differenceColors);
    _matchToNb = new FHashMap<EMatch, Integer>(IEqualityTester.BY_EQUALS);
    _countedKinds = new HashSet<UserDifferenceKind>(
        Arrays.asList(UserDifferenceKind.values()));
    _useCustomIcons = true;
    _useCustomLabels = false;
    _isTargetEditionPossible = isLeftEditionPossible_p;
    _isReferenceEditionPossible = isRightEditionPossible_p;
    _isTargetEditable = _isTargetEditionPossible;
    _isReferenceEditable = _isReferenceEditionPossible;
    _isTargetModified = false;
    _isReferenceModified = false;
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
    return getRoleForSide(left_p) == Role.TARGET? _isTargetEditable:
      _isReferenceEditable;
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
   * Count and return the number of significant differences on the given match
   * @param match_p a non-null match
   * @return a positive int
   */
  protected int countDifferenceNumber(IMatch match_p) {
    int result = 0;
    if (counts(UserDifferenceKind.NO_CONTAINMENT))
     result += countNonContainmentDifferenceNumber(match_p);
    if (counts(UserDifferenceKind.MOVE) && isAMove(match_p, false, false))
      result++;
    IElementPresence presence = match_p.getElementPresenceDifference();
    if (presence != null && !shouldBeIgnored(presence)) {
      boolean isLeftPresence = presence.getPresenceRole() == getRoleForSide(true);
      boolean countPresence = isLeftPresence? counts(UserDifferenceKind.PRESENCE_LEFT):
        counts(UserDifferenceKind.PRESENCE_RIGHT);
      if (countPresence)
        result++;
    }
    return result;
  }
  
  /**
   * Return the number of non-containment differences at a user level on the given match
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  protected int countNonContainmentDifferenceNumber(IMatch match_p) {
    int result = 0;
    if (!match_p.isPartial()) {
      Set<EStructuralFeature> uniFeatures = new HashSet<EStructuralFeature>();
      for (IDifference difference : match_p.getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference && !shouldBeIgnored(difference)) {
          IElementRelativeDifference eltDiff = (IElementRelativeDifference)difference;
          if (eltDiff.isUnrelatedToContainmentTree()) {
            if (eltDiff instanceof IValuePresence) {
              EStructuralFeature feature = ((IValuePresence)eltDiff).getFeature();
              if (feature != null &&
                  (!feature.isMany() || ((IValuePresence)eltDiff).isOrder())) {
                // A value presence on a non-many feature
                if (!uniFeatures.contains(feature)) {
                  // Not counted yet
                  result++;
                  uniFeatures.add(feature);
                }
              } else {
                // A value presence on a feature such that isMany()
                result++;
              }
            } else {
              // Not a value presence
              result++;
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the given difference kind is being counted
   * @param kind_p a non-null difference kind
   */
  public boolean counts(UserDifferenceKind kind_p) {
    return _countedKinds.contains(kind_p);
  }
  
  /**
   * @see org.eclipse.ui.services.IDisposable#dispose()
   */
  public void dispose() {
    _resourceManager.dispose();
  }
  
  /**
   * Return the model comparison of this node
   * @return a non-null comparison
   */
  public EComparison getActualComparison() {
    return getUIComparison().getActualComparison();
  }
  
  /**
   * Return the container of the given match from a GUI perspective
   * @param match_p a non-null match
   * @return a potentially null match
   */
  protected EMatch getContainerOf(EMatch match_p) {
    Role containerSide;
    if (match_p.getUncoveredRole() == getDrivingRole())
      containerSide = getDrivingRole().opposite();
    else
      containerSide = getDrivingRole();
    EMatch result = (EMatch)getActualComparison().getContainerOf(match_p, containerSide);
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
   * Return the difference kind of the given match with filtering
   * @param match_p a non-null match
   * @return FROM_LEFT*, FROM_RIGHT*, CONFLICT, MODIFIED, COUNTED or NONE
   */
  public DifferenceKind getDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    IElementPresence presence = match_p.getElementPresenceDifference();
    boolean considerReference = getReferenceRole() != null;
    if (presence != null) {
      result = getDifferenceKind(presence);
    } else {
      result = getModificationKind(match_p);
      result = result.with(getOwnershipDifferenceKind(match_p), considerReference);
      result = result.keepOnlyDirection(considerReference);
    }
    if (result == DifferenceKind.NONE && getDifferenceNumber(match_p) > 0)
      result = DifferenceKind.COUNTED;
    return result;
  }
  
  /**
   * Return the difference kind of the given match and feature with filtering
   * @param maf_p a non-null match and feature
   * @return FROM_LEFT*, FROM_RIGHT*, CONFLICT, MODIFIED, COUNTED or NONE
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public DifferenceKind getDifferenceKind(MatchAndFeature maf_p) {
    DifferenceKind result = DifferenceKind.NONE;
    EStructuralFeature feature = maf_p.getFeature();
    if (feature == EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature()) {
      // Ownership feature (move)
      result = getOwnershipDifferenceKind(maf_p.getMatch());
    } else {
      // Standard feature
      Collection<? extends IValuePresence> presences;
      if (feature instanceof EReference) {
        if (((EReference)feature).isContainment()) {
          // Containment on feature which is not ownership:
          // consider order only
          presences = new ArrayList<IValuePresence>();
          IValuePresence orderDiff = maf_p.getMatch().getOrderDifference(feature, getDrivingRole());
          if (orderDiff != null)
            ((List)presences).add(orderDiff);
          orderDiff = maf_p.getMatch().getOrderDifference(feature, getDrivingRole().opposite());
          if (orderDiff != null)
            ((List)presences).add(orderDiff);
        } else {
          presences = maf_p.getMatch().getReferenceDifferences((EReference)feature);
        }
      } else {
        presences = maf_p.getMatch().getAttributeDifferences((EAttribute)feature);
      }
      Iterator<? extends IValuePresence> it = presences.iterator();
      while (it.hasNext() && result != DifferenceKind.CONFLICT &&
          result != DifferenceKind.FROM_BOTH && result != DifferenceKind.MODIFIED) {
        DifferenceKind current = getDifferenceKind(it.next());
        result = result.with(current, true);
      }
    }
    return result;
  }
  
  /**
   * Return the kind of the given difference with filtering
   * @param difference_p a non-null difference
   * @return FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
   */
  public DifferenceKind getDifferenceKind(IDifference difference_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (!shouldBeIgnored(difference_p)) {
      if (difference_p.isConflicting()) {
        result = DifferenceKind.CONFLICT;
      } else if (difference_p instanceof IPresenceDifference) {
        IPresenceDifference presence = (IPresenceDifference)difference_p;
        boolean isMany = isMany(presence);
        if (presence.getPresenceRole() == getRoleForSide(true)) {
          // Present on the left
          if (isAlignedWithReference(presence))
            result = DifferenceKind.FROM_RIGHT_DEL;
          else
            if (isMany || getReferenceRole() != null)
              result = DifferenceKind.FROM_LEFT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        } else {
          // Present on the right
          if (isAlignedWithReference(presence))
            result = DifferenceKind.FROM_LEFT_DEL;
          else
            if (isMany || getReferenceRole() != null)
              result = DifferenceKind.FROM_RIGHT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the number of differences associated to the given match
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  public int getDifferenceNumber(IMatch match_p) {
    Integer currentNb = getMatchToNb().get(match_p);
    if (currentNb == null)
      currentNb = Integer.valueOf(0);
    return currentNb.intValue();
  }
  
  /**
   * Return the role that drives the representation of the model comparison
   * @return a non-null role which is TARGET or REFERENCE
   */
  public Role getDrivingRole() {
    return _drivingRole;
  }
  
  /**
   * Return the list of visible children for merge of the given match
   * @param match_p a non-null match
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IMatch> getChildrenForMerge(IMatch match_p) {
    List<IMatch> candidates = getActualComparison().getContentsOf(match_p);
    List<IMatch> result = new FOrderedSet<IMatch>();
    IComparison comparison = match_p.getMapping().getComparison();
    for (IMatch candidate : candidates) {
      if (isAMove(candidate, false, false) &&
          comparison.getContainerOf(candidate, getDrivingRole().opposite()) == match_p)
        continue; // Move origin
      if (getDifferenceNumber(candidate) > 0)
        result.add(candidate);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the editing domain for merge operations, if any
   * @return a potentially null editing domain
   */
  public EditingDomain getEditingDomain() {
    return _editingDomain;
  }
  
  /**
   * Return the map from matches to differences numbers
   * @return a non-null, modifiable map
   */
  protected EMap<EMatch, Integer> getMatchToNb() {
    return _matchToNb;
  }
  
  /**
   * Return the modification status of the given match with filtering
   * @param match_p a non-null match
   * @return a non-null kind
   */
  protected DifferenceKind getModificationKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    boolean considerReference = getReferenceRole() != null;
    if (counts(UserDifferenceKind.NO_CONTAINMENT) &&
        match_p.getElementPresenceDifference() == null) {
      for (IDifference diff : match_p.getRelatedDifferences()) {
        if (diff instanceof IElementRelativeDifference &&
            ((IElementRelativeDifference)diff).isUnrelatedToContainmentTree()) {
          DifferenceKind diffKind = getDifferenceKind(diff);
          result = result.with(diffKind, considerReference);
          if (result == DifferenceKind.CONFLICT)
            break;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the subset of the given set of differences which should not be ignored
   * given the current representation configuration
   * @param differences_p a non-null set
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IDifference> getNonIgnoredDifferences(Iterable<? extends IDifference> differences_p) {
    List<IDifference> result = new ArrayList<IDifference>();
    for (IDifference difference : differences_p) {
      if (!shouldBeIgnored(difference))
        result.add(difference);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the difference kind for the ownership of the given match
   * @param match_p a non-null match
   * @return a non-null difference kind
   */
  protected DifferenceKind getOwnershipDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (representAsMove(match_p)) {
      result = DifferenceKind.MODIFIED;
      if (getReferenceRole() != null) {
        IReferenceValuePresence onLeft = match_p.getOwnershipDifference(getRoleForSide(true));
        boolean fromLeft = onLeft != null && !isAlignedWithReference(onLeft);
        IReferenceValuePresence onRight = match_p.getOwnershipDifference(getRoleForSide(false));
        boolean fromRight = onRight != null && !isAlignedWithReference(onRight);
        if (fromLeft && fromRight)
          result = DifferenceKind.CONFLICT;
        else if (fromLeft)
          result = DifferenceKind.FROM_LEFT;
        else if (fromRight)
          result = DifferenceKind.FROM_RIGHT;
      }
    }
    return result;
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
   * Return the number of differences associated to the given match from a
   * UI perspective
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  public int getUIDifferenceNumber(EMatch match_p) {
    Role leftRole = getRoleForSide(true);
    int result = getDifferenceNumber(match_p);
    if (match_p.getUncoveredRole() == leftRole
          && counts(UserDifferenceKind.PRESENCE_RIGHT) ||
        match_p.getUncoveredRole() == leftRole.opposite()
          && counts(UserDifferenceKind.PRESENCE_LEFT))
      result--;
    return result;
  }
  
  /**
   * @see org.eclipse.compare.structuremergeviewer.DiffContainer#hasChildren()
   */
  @Override
  public boolean hasChildren() {
    // Is there content?
    return getUIComparison().getActualComparison().hasRemainingDifferences();
  }
  
  /**
   * Return whether the given match has visible children for merge
   * @param match_p a non-null match
   */
  public boolean hasChildrenForMerge(IMatch match_p) {
    List<IMatch> candidates = getActualComparison().getContentsOf(match_p);
    IComparison comparison = match_p.getMapping().getComparison();
    for (IMatch candidate : candidates) {
      if (isAMove(candidate, false, false) &&
          comparison.getContainerOf(candidate, getDrivingRole().opposite()) == match_p)
        continue; // Move origin
      if (getDifferenceNumber(candidate) > 0)
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given match has visible non-containment differences for merge
   * @param match_p a non-null match
   */
  public boolean hasNonContainmentDifferencesForMerge(IMatch match_p) {
    if (!match_p.isPartial()) {
      for (IDifference difference : match_p.getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference && !shouldBeIgnored(difference)) {
          IElementRelativeDifference eltDiff = (IElementRelativeDifference)difference;
          if (eltDiff.isUnrelatedToContainmentTree())
            return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Increment the number of differences by the given increment for the given match
   * @param match_p a non-null match
   * @param increment_p a positive int
   */
  protected void incrementDifferenceNumbers(EMatch match_p, int increment_p) {
    int currentNb = getDifferenceNumber(match_p);
    Integer newNb = Integer.valueOf(increment_p + currentNb);
    getMatchToNb().put(match_p, newNb);
  }
  
  /**
   * Increment the number of differences by the given increment for the given match
   * and its parents according to the driving role
   * @param match_p a non-null match
   * @param increment_p a positive int
   */
  protected void incrementDifferenceNumbersInHierarchy(EMatch match_p, int increment_p) {
    if (increment_p > 0) {
      incrementDifferenceNumbers(match_p, increment_p);
      EMatch current = getContainerOf(match_p);
        while (current != null) {
          incrementDifferenceNumbers(current, increment_p);
          current = getContainerOf(current);
        }
    }
  }
  
  /**
   * Return whether the given difference is aligned with the reference model if any.
   * If there is no reference model, then false is returned.
   * @see EMFDiffNode#getReferenceRole()
   * @param presence_p a non-null difference
   */
  protected boolean isAlignedWithReference(IPresenceDifference presence_p) {
    boolean result;
    if (isThreeWay())
      result = presence_p.isAlignedWithAncestor();
    else
      result = presence_p.getPresenceRole() == getReferenceRole();
    return result;
  }
  
  /**
   * Return whether the given match corresponds to a moved element
   * @param match_p a non-null match
   * @param considerMerged_p whether even a merged move must be considered
   * @param considerIgnored_p whether even an ignored move must be considered
   */
  public boolean isAMove(IMatch match_p, boolean considerMerged_p, boolean considerIgnored_p) {
    boolean result = false;
    if (!match_p.isPartial()) {
      IReferenceValuePresence onTarget = match_p.getOwnershipDifference(Role.TARGET);
      IReferenceValuePresence onReference = match_p.getOwnershipDifference(Role.REFERENCE);
      result =
        (onTarget != null && (considerMerged_p || !onTarget.isMerged()) &&
            (considerIgnored_p || !getUIComparison().getDifferencesToIgnore().contains(onTarget))) ||
        (onReference != null && (considerMerged_p || !onReference.isMerged()) &&
            (considerIgnored_p || !getUIComparison().getDifferencesToIgnore().contains(onReference)));
    }
    return result;
  }
  
  /**
   * Return whether the are still differences that the user has to handle
   */
  public boolean isEmpty() {
    IComparison comparison = getActualComparison();
    if (comparison != null) {
      for (IMatch match : comparison.getMapping().getContents()) {
        for (IDifference difference : match.getAllDifferences()) {
          if (!shouldBeIgnored(difference))
            return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Return whether differences are being filtered
   */
  public boolean isFiltering() {
    return UserDifferenceKind.values().length != _countedKinds.size();
  }
  
  /**
   * Return whether the given difference must be considered as a presence with no
   * multiplicity constraint
   * @param presence_p a non-null difference
   */
  public boolean isMany(IPresenceDifference presence_p) {
    boolean result = true;
    if (presence_p instanceof IValuePresence) {
      IValuePresence valuePresence = (IValuePresence)presence_p;
      EStructuralFeature feature = valuePresence.getFeature();
      result = (feature == null || feature.isMany()) && !valuePresence.isOrder();
    }
    return result;
  }
  
  /**
   * Return whether the given path corresponds to a moved element on the side of
   * the source of the move, where source corresponds to the opposite of the driving role
   * @param path_p a non-null path
   */
  public boolean isMoveOrigin(TreePath path_p) {
    boolean result = false;
    IMatch end = (IMatch)path_p.getLastSegment();
    if (end != null && isAMove(end, false, true)) {
      TreePath parentPath = path_p.getParentPath();
      IMatch father =
        parentPath == null? null: (IMatch)parentPath.getLastSegment();
      IComparison comparison = end.getMapping().getComparison();
      result = comparison.getContainerOf(end, getDrivingRole().opposite()) == father &&
          comparison.getContainerOf(end, getDrivingRole()) != father;
    }
    return result;
  }
  
  /**
   * Return whether the given difference is conceptually the opposite to ownership
   * @param difference_p a non-null difference
   */
  public boolean isOwnershipOpposite(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence valuePresence = (IReferenceValuePresence)difference_p;
      EReference ref = valuePresence.getFeature();
      result = ref == null || ref.isContainment();
    }
    return result;
  }
  
  /**
   * Return whether this comparison is 3-way
   */
  public boolean isThreeWay() {
    return getActualComparison().isThreeWay();
  }
  
  /**
   * Return whether the given match is modified in the comparison
   * @param match_p a non-null match
   */
  public boolean representAsModification(IMatch match_p) {
    if (!counts(UserDifferenceKind.NO_CONTAINMENT) || match_p.getElementPresenceDifference() != null)
      return false;
    for (IDifference diff : match_p.getRelatedDifferences()) {
      if (representAsModificationDifference(diff))
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given difference can be considered as a modification
   * of the corresponding element
   * @param diff_p a non-null difference
   */
  protected boolean representAsModificationDifference(IDifference diff_p) {
    boolean result = false;
    if (diff_p instanceof IElementRelativePresence) {
      IElementRelativePresence presence = (IElementRelativePresence)diff_p;
      return presence.isUnrelatedToContainmentTree() && !shouldBeIgnored(diff_p);
    }
    return result;
  }
  
  /**
   * Return whether the given match is represented as a move in the comparison
   * @param match_p a non-null match
   */
  public boolean representAsMove(IMatch match_p) {
    boolean result = false;
    if (counts(UserDifferenceKind.MOVE) && !match_p.isPartial()) {
      IReferenceValuePresence drivingOwnership = match_p.getOwnershipDifference(
          getDrivingRole());
      IReferenceValuePresence nonDrivingOwnership = match_p.getOwnershipDifference(
          getDrivingRole().opposite());
      result = drivingOwnership != null && !shouldBeIgnored(drivingOwnership) ||
        nonDrivingOwnership != null && !shouldBeIgnored(nonDrivingOwnership);
    }
    return result;
  }
  
  /**
   * Return whether the given path is represented as a moved element on the side of
   * the source of the move
   * @param path_p a non-null path
   */
  public boolean representAsMoveOrigin(TreePath path_p) {
    IMatch end = (IMatch)path_p.getLastSegment();
    return representAsMove(end) && isMoveOrigin(path_p);
  }
  
  /**
   * Return whether the given match contains differences to represent from the user's point of view
   * @param match_p a non-null match
   */
  public boolean representAsUserDifference(IMatch match_p) {
    DifferenceKind kind = getDifferenceKind(match_p);
    boolean result = kind != DifferenceKind.NONE &&
      kind != DifferenceKind.COUNTED;
    return result;
  }
  
  /**
   * Return whether the given path contains differences to represent from the user's point of view
   * @param path_p a non-null path
   */
  public boolean representAsUserDifference(TreePath path_p) {
    boolean result = false;
    IMatch end = (IMatch)path_p.getLastSegment();
    if (end != null)
      result = representAsUserDifference(end) && !representAsMoveOrigin(path_p);
    return result;
  }
  
  /**
   * Specify whether the given difference kind should be counted
   * @param kind_p a non-null difference kind
   * @param count_p whether kind_p should be counted
   */
  public void setCount(UserDifferenceKind kind_p, boolean count_p) {
    if (count_p)
      _countedKinds.add(kind_p);
    else
      _countedKinds.remove(kind_p);
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
    if (!isThreeWay() && (Role.TARGET == role_p || Role.REFERENCE == role_p))
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
   * Return whether the given difference should be ignored
   * @param difference_p a non-null difference
   */
  public boolean shouldBeIgnored(IDifference difference_p) {
    Role leftRole = getRoleForSide(true);
    return
      difference_p.isMerged() ||
      getUIComparison().getDifferencesToIgnore().contains(difference_p) ||
      (difference_p instanceof IValuePresence &&
        ((IValuePresence)difference_p).isUnrelatedToContainmentTree() && !counts(UserDifferenceKind.NO_CONTAINMENT)) ||
      difference_p instanceof IElementPresence &&
        (((IElementPresence)difference_p).getPresenceRole() == leftRole &&
            !counts(UserDifferenceKind.PRESENCE_LEFT) ||
         ((IElementPresence)difference_p).getPresenceRole() == leftRole.opposite() &&
            !counts(UserDifferenceKind.PRESENCE_RIGHT));
  }
  
  /**
   * Re-compute the number of differences per match
   */
  public void updateDifferenceNumbers() {
    getMatchToNb().clear();
    for (IMatch match : getActualComparison().getMapping().getContents()) {
      int nb = countDifferenceNumber(match);
      incrementDifferenceNumbersInHierarchy((EMatch)match, nb);
    }
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
  
}