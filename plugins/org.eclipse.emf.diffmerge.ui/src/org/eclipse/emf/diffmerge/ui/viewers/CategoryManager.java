/**
 * <copyright>
 * 
 * Copyright (c) 2016 Thales Global Services S.A.S.
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.util.structures.FHashMap;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.TreePath;


/**
 * A manager of difference categories for a given diff node.
 * @author Olivier Constant
 */
public class CategoryManager {
  
  /** The non-null diff node that is the context of this manager */
  protected final EMFDiffNode _node;
  
  /** The modifiable set of (ID, category) registered categories */
  private final Map<String, IDifferenceCategory> _categories;
  
  /** The modifiable set of active categories among the registered ones */
  protected final Set<IDifferenceCategory> _activeCategories;
  
  /** The modifiable list of root category items that should be visible in the UI */
  protected final List<IDifferenceCategoryItem> _uiRootItems;
  
  /** The modifiable (parent, children) map of category items that should be visible in the UI */
  protected final Map<IDifferenceCategorySet, Collection<IDifferenceCategoryItem>> _uiChildrenItems;
  
  /** The map from matches to difference numbers */
  private final EMap<EMatch, Integer> _matchToNb;
  
  
  /**
   * Constructor
   * @param node_p a non-null diff node as context
   */
  public CategoryManager(EMFDiffNode node_p) {
    _node = node_p;
    _categories = new LinkedHashMap<String, IDifferenceCategory>();
    _activeCategories = new HashSet<IDifferenceCategory>();
    _uiRootItems = new ArrayList<IDifferenceCategoryItem>();
    _uiChildrenItems = new HashMap<IDifferenceCategorySet, Collection<IDifferenceCategoryItem>>();
    _matchToNb = new FHashMap<EMatch, Integer>(IEqualityTester.BY_EQUALS);
  }
  
  /**
   * Add and register the categories that are recursively associated to the given
   * category set.
   * @param categorySet_p a non-null category set
   * @return whether no already-registered category was unregistered as a result
   */
  public boolean addCategories(IDifferenceCategorySet categorySet_p) {
    boolean result = true;
    for (IDifferenceCategoryItem categoryItem : categorySet_p.getChildren()) {
      result = result && addCategoryItem(categoryItem);
    }
    return result;
  }
  
  /**
   * Add and register the given category to this manager.
   * If a category with the same ID was already registered, then it is unregistered.
   * @param category_p a non-null category
   * @return whether the operation had no side effect, i.e., no other category was unregistered
   */
  public boolean addCategory(IDifferenceCategory category_p) {
    IDifferenceCategory squatter = _categories.put(category_p.getID(), category_p);
    return squatter == null || squatter == category_p;
  }
  
  /**
   * Add and register the categories that are recursively associated to the given
   * category item.
   * @param categoryItem_p a non-null category set
   * @return whether no already-registered category was unregistered as a result
   */
  protected boolean addCategoryItem(IDifferenceCategoryItem categoryItem_p) {
    boolean result = true;
    if (categoryItem_p instanceof IDifferenceCategory)
      result = addCategory((IDifferenceCategory)categoryItem_p);
    else if (categoryItem_p instanceof IDifferenceCategorySet)
      result = addCategories((IDifferenceCategorySet)categoryItem_p);
    return result;
  }
  
  /**
   * Count and return the number of differences on the given match,
   * excluding differences on children
   * @param match_p a non-null match
   * @param withFilters_p whether filters must be taken into account
   * @return a positive int
   */
  protected int countDifferences(IMatch match_p, boolean withFilters_p) {
    // Non-containment differences
    int result = countNonContainmentDifferences(match_p, withFilters_p);
    // Move
    if (isMove(match_p, true))
      result++;
    // Addition/deletion
    if (isUnmatched(match_p, true))
      result++;
    return result;
  }
  
  /**
   * Count and return the number of non-containment differences on the given match
   * @param match_p a non-null match
   * @param withFilters_p whether filters must be taken into account
   * @return a positive int or 0
   */
  protected int countNonContainmentDifferences(IMatch match_p, boolean withFilters_p) {
    int result = 0;
    if (!match_p.isPartial()) {
      Set<EStructuralFeature> uniFeatures = new HashSet<EStructuralFeature>();
      for (IDifference difference : match_p.getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference &&
            (!withFilters_p || !isFiltered(difference))) {
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
   * Return the set of registered difference categories
   * @return a non-null, non-modifiable collection
   */
  public Collection<IDifferenceCategory> getCategories() {
    return _categories.values();
  }
  
  /**
   * Return the registered category of the given ID, if any
   * @param categoryID_p a potentially null category ID
   * @return a potentially null category
   */
  public IDifferenceCategory getCategory(String categoryID_p) {
    IDifferenceCategory result = _categories.get(categoryID_p);
    return result;
  }
  
  /**
  /**
   * Return the list of visible children for merge of the given match
   * @param match_p a non-null match
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IMatch> getChildrenForMerge(IMatch match_p) {
    List<IMatch> candidates = _node.getActualComparison().getContentsOf(match_p);
    List<IMatch> result = new FOrderedSet<IMatch>();
    IComparison comparison = match_p.getMapping().getComparison();
    for (IMatch candidate : candidates) {
      if (isMove(candidate, false) &&
          comparison.getContainerOf(candidate, _node.getDrivingRole().opposite()) == match_p)
        continue; // Move origin
      if (getDifferenceNumber(candidate) > 0)
        result.add(candidate);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the difference kind of the given match with filtering
   * @param match_p a non-null match
   * @return FROM_LEFT*, FROM_RIGHT*, CONFLICT, MODIFIED, COUNTED or NONE
   */
  public DifferenceKind getDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    IElementPresence presence = match_p.getElementPresenceDifference();
    boolean considerReference = _node.getReferenceRole() != null;
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
          IValuePresence orderDiff = maf_p.getMatch().getOrderDifference(feature, _node.getDrivingRole());
          if (orderDiff != null)
            ((List)presences).add(orderDiff);
          orderDiff = maf_p.getMatch().getOrderDifference(feature, _node.getDrivingRole().opposite());
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
    if (!isFiltered(difference_p)) {
      if (difference_p.isConflicting()) {
        result = DifferenceKind.CONFLICT;
      } else if (difference_p instanceof IPresenceDifference) {
        IPresenceDifference presence = (IPresenceDifference)difference_p;
        boolean isMany = isMany(presence);
        if (presence.getPresenceRole() == _node.getRoleForSide(true)) {
          // Present on the left
          if (isAlignedWithReference(presence))
            result = DifferenceKind.FROM_RIGHT_DEL;
          else
            if (isMany || _node.getReferenceRole() != null)
              result = DifferenceKind.FROM_LEFT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        } else {
          // Present on the right
          if (isAlignedWithReference(presence))
            result = DifferenceKind.FROM_LEFT_DEL;
          else
            if (isMany || _node.getReferenceRole() != null)
              result = DifferenceKind.FROM_RIGHT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the number of differences associated to the given match, without recounting
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
   * Return the list of category items that should be visible in the UI as children of
   * the given category set
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IDifferenceCategoryItem> getUIChildrenItems(IDifferenceCategorySet categorySet_p) {
    List<IDifferenceCategoryItem> result = new ArrayList<IDifferenceCategoryItem>(
        categorySet_p.getChildren());
    Collection<IDifferenceCategoryItem> visibleChildren = _uiChildrenItems.get(categorySet_p);
    if (visibleChildren != null) {
      result.retainAll(visibleChildren);
      result = Collections.unmodifiableList(result);
    } else {
      result = Collections.emptyList();
    }
    return result;
  }
  
  /**
   * Return the number of differences associated to the given match from a user's perspective,
   * without recounting
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  public int getUIDifferenceNumber(IMatch match_p) {
    if (_node.isHideDifferenceNumbers()) return 0;
    int result = getDifferenceNumber(match_p);
    IElementPresence eltPresence = match_p.getElementPresenceDifference();
    if (eltPresence != null && !isFiltered(eltPresence))
      result--;
    return result;
  }
  
  /**
   * Return the root category items that should be visible in the UI
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IDifferenceCategoryItem> getUIRootItems() {
    return Collections.unmodifiableList(_uiRootItems);
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
    boolean considerReference = _node.getReferenceRole() != null;
    IElementPresence eltPresence = match_p.getElementPresenceDifference();
    if (eltPresence == null || isFiltered(eltPresence)) {
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
   * Return the difference kind for the ownership of the given match
   * @param match_p a non-null match
   * @return a non-null difference kind
   */
  protected DifferenceKind getOwnershipDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (representAsMove(match_p)) {
      result = DifferenceKind.MODIFIED;
      if (_node.getReferenceRole() != null) {
        IReferenceValuePresence onLeft = match_p.getOwnershipDifference(_node.getRoleForSide(true));
        boolean fromLeft = onLeft != null && !isAlignedWithReference(onLeft);
        IReferenceValuePresence onRight = match_p.getOwnershipDifference(_node.getRoleForSide(false));
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
   * Return the differences within the given set that are not filtered and that
   * can be processed by the user
   * @param differences_p a non-null set of differences
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IDifference> getPendingDifferencesFiltered(Iterable<? extends IDifference> differences_p) {
    List<IDifference> result = new ArrayList<IDifference>();
    for (IDifference difference : differences_p) {
      if (isPending(difference) && !isFiltered(difference))
        result.add(difference);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return whether the given match has visible children for merge
   * @param match_p a non-null match
   */
  public boolean hasChildrenForMergeFiltered(IMatch match_p) {
    EComparison comparison = _node.getActualComparison();
    List<IMatch> candidates = comparison.getContentsOf(match_p, _node.getDrivingRole());
    for (IMatch candidate : candidates) {
      if (getDifferenceNumber(candidate) > 0)
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given match has visible non-containment differences for merge
   * @param match_p a non-null match
   */
  public boolean hasNonContainmentDifferencesForMergeFiltered(IMatch match_p) {
    if (!match_p.isPartial()) {
      for (IDifference difference : match_p.getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference && !isFiltered(difference)) {
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
      EMatch current = _node.getContainerOf(match_p);
        while (current != null) {
          incrementDifferenceNumbers(current, increment_p);
          current = _node.getContainerOf(current);
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
    if (_node.isThreeWay())
      result = presence_p.isAlignedWithAncestor();
    else
      result = presence_p.getPresenceRole() == _node.getReferenceRole();
    return result;
  }
  
  /**
   * Return whether the are still differences that the user has to handle
   */
  public boolean isEmpty() {
    IComparison comparison = _node.getActualComparison();
    if (comparison != null) {
      for (IMatch match : comparison.getMapping().getContents()) {
        for (IDifference difference : match.getAllDifferences()) {
          if (isPending(difference) && !isFiltered(difference))
            return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Return whether the given difference is filtered out by categories
   * @param difference_p a non-null difference
   */
  public boolean isFiltered(IDifference difference_p) {
    for (IDifferenceCategory category : _activeCategories) {
      boolean covered = category.covers(difference_p, _node);
      boolean focus = category.isInFocusMode();
      if (covered && !focus || !covered && focus)
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given difference is being explicitly ignored by the user
   * @param difference_p a non-null difference
   */
  public boolean isIgnored(IDifference difference_p) {
    return _node.getUIComparison().getDifferencesToIgnore().contains(difference_p);
  }
  
  /**
   * Return whether the given difference represents a value presence with no
   * multiplicity constraint
   * @param difference_p a non-null difference
   */
  public boolean isMany(IDifference difference_p) {
    boolean result = true;
    if (difference_p instanceof IValuePresence) {
      IValuePresence valuePresence = (IValuePresence)difference_p;
      if (!valuePresence.isOrder()) {
        EStructuralFeature feature = valuePresence.getFeature();
        result = (feature == null || feature.isMany());
      }
    }
    return result;
  }
  
  /**
   * Return whether the given difference can still be merged
   * @param difference_p a non-null difference
   */
  public boolean isMergeable(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IMergeableDifference) {
      IMergeableDifference casted = (IMergeableDifference)difference_p;
      result = casted.canMergeTo(_node.getRoleForSide(true)) && _node.isEditable(true) ||
          casted.canMergeTo(_node.getRoleForSide(false)) && _node.isEditable(false);
    }
    return result;
  }
  
  /**
   * Return whether the given difference represents a move
   * @param difference_p a potentially null difference
   */
  public boolean isMove(IDifference difference_p) {
    return isOwnership(difference_p) &&
        !((IReferenceValuePresence)difference_p).getValue().isPartial();
  }
  
  /**
   * Return whether the given match represents a moved element
   * @param match_p a non-null match
   * @param withFilters_p whether filters must be taken into account
   */
  public boolean isMove(IMatch match_p, boolean withFilters_p) {
    boolean result = false;
    if (!match_p.isPartial()) {
      IReferenceValuePresence onTarget = match_p.getOwnershipDifference(Role.TARGET);
      IReferenceValuePresence onReference = match_p.getOwnershipDifference(Role.REFERENCE);
      result = (onTarget != null && (!withFilters_p || !isFiltered(onTarget))) ||
          (onReference != null && (!withFilters_p || !isFiltered(onReference)));
    }
    return result;
  }
  
  /**
   * Return whether the given path represents a moved element on the side of
   * the source of the move, where source corresponds to the opposite of the driving role
   * @param path_p a non-null path
   */
  public boolean isMoveOrigin(TreePath path_p) {
    boolean result = false;
    IMatch end = (IMatch)path_p.getLastSegment();
    if (end != null && isMove(end, false)) {
      TreePath parentPath = path_p.getParentPath();
      IMatch father =
        parentPath == null? null: (IMatch)parentPath.getLastSegment();
      IComparison comparison = end.getMapping().getComparison();
      Role drivingRole = _node.getDrivingRole();
      result = comparison.getContainerOf(end, drivingRole.opposite()) == father &&
          comparison.getContainerOf(end, drivingRole) != father;
    }
    return result;
  }
  
  /**
   * Return whether the given difference represents an ordering difference
   * @param difference_p a non-null difference
   */
  public boolean isOrder(IDifference difference_p) {
    return difference_p instanceof IValuePresence && ((IValuePresence)difference_p).isOrder();
  }
  
  /**
   * Return whether the given difference represents an ownership
   * @param difference_p a potentially null difference
   */
  public boolean isOwnership(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence presence = (IReferenceValuePresence)difference_p;
      if (!presence.isOrder()) {
        EReference ref = presence.getFeature();
        result = ref == null || ref.isContainment();
      }
    }
    return result;
  }
  
  /**
   * Return whether the given difference is still pending for the user
   * (mergeable, not merged, not ignored)
   * @param difference_p a non-null difference
   */
  public boolean isPending(IDifference difference_p) {
    return isMergeable(difference_p) && !isIgnored(difference_p);
  }
  
  /**
   * Return whether pending differences are filtered from a user point of view,
   * that is, categories that are visible, modifiable and active may be filtering out
   * pending differences
   */
  public boolean isUIFiltering() {
    for (IDifferenceCategory category : _activeCategories) {
      if (isUIFiltering(category))
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given category is filtering from a user point of view,
   * that is, categories that are visible, modifiable and active may be filtering out
   * pending differences
   */
  public boolean isUIFiltering(IDifferenceCategory category_p) {
    return category_p.mayCoverPendingDifferences() && category_p.isVisible()
        && category_p.isModifiable() && _activeCategories.contains(category_p);
  }
  
  /**
   * Return whether the given match represents an element addition/deletion
   * @param match_p a non-null match
   * @param withFilters_p whether filters must be taken into account
   */
  public boolean isUnmatched(IMatch match_p, boolean withFilters_p) {
    IElementPresence presence = match_p.getElementPresenceDifference();
    return presence != null && (!withFilters_p || !isFiltered(presence));
  }
  
  /**
   * Remove (i.e., unregister) the category of the given ID
   * @param categoryID_p a potentially null category ID
   * @return whether the category was successfully found and removed
   */
  public boolean removeCategory(String categoryID_p) {
    IDifferenceCategory category = _categories.remove(categoryID_p);
    return category != null;
  }
  
  /**
   * Return whether the given match is represented as a modification
   * @param match_p a non-null match
   */
  public boolean representAsModification(IMatch match_p) {
    IElementPresence eltPresence = match_p.getElementPresenceDifference();
    if (eltPresence != null && !isFiltered(eltPresence))
      return false;
    for (IDifference diff : match_p.getRelatedDifferences()) {
      if (representAsModificationDifference(diff))
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given difference is represented as a modification
   * of the corresponding element
   * @param difference_p a non-null difference
   */
  protected boolean representAsModificationDifference(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IElementRelativePresence) {
      IElementRelativePresence presence = (IElementRelativePresence)difference_p;
      return presence.isUnrelatedToContainmentTree() && !isFiltered(difference_p);
    }
    return result;
  }
  
  /**
   * Return whether the given match is represented as a move
   * @param match_p a non-null match
   */
  public boolean representAsMove(IMatch match_p) {
    return isMove(match_p, true);
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
   * Return whether the given match contains differences to represent
   * @param match_p a non-null match
   */
  public boolean representAsUserDifference(IMatch match_p) {
    DifferenceKind kind = getDifferenceKind(match_p);
    boolean result = kind != DifferenceKind.NONE &&
      kind != DifferenceKind.COUNTED;
    return result;
  }
  
  /**
   * Return whether the given path contains differences to represent
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
   * Re-compute filtering and differences numbers
   */
  public void update() {
    updateActiveCategories();
    updateUIItems();
    getMatchToNb().clear();
    for (IMatch match : _node.getActualComparison().getMapping().getContents()) {
      int nb = countDifferences(match, true);
      incrementDifferenceNumbersInHierarchy((EMatch)match, nb);
    }
  }
  
  /**
   * Re-compute the set of active categories
   */
  protected void updateActiveCategories() {
    _activeCategories.clear();
    for (IDifferenceCategory category : getCategories()) {
      if (category.isApplicable(_node) && category.isActive())
        _activeCategories.add(category);
    }
  }
  
  /**
   * Re-compute the forest of category items that should be visible in the UI
   */
  protected void updateUIItems() {
    _uiRootItems.clear();
    _uiChildrenItems.clear();
    List<IDifferenceCategoryItem> visibleItems = new LinkedList<IDifferenceCategoryItem>();
    for (IDifferenceCategory category : getCategories()) {
      if (category.isVisible() && category.isApplicable(_node)) {
        // Category must be visible in the UI
        visibleItems.add(category);
      }
    }
    while (!visibleItems.isEmpty()) {
      IDifferenceCategoryItem visibleItem = visibleItems.get(0);
      IDifferenceCategorySet parent = visibleItem.getParent();
      if (parent == null) {
        // It is a root
        if (!_uiRootItems.contains(visibleItem))
          _uiRootItems.add(visibleItem);
      } else {
        // It is a child: remember its parent
        Collection<IDifferenceCategoryItem> children = _uiChildrenItems.get(parent);
        if (children == null) {
          children = new HashSet<IDifferenceCategoryItem>();
          _uiChildrenItems.put(parent, children);
        }
        children.add(visibleItem);
        visibleItems.add(parent);
      }
      visibleItems.remove(visibleItem);
    }
  }
  
}
