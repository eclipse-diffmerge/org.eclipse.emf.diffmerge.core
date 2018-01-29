/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Jaafar Bouayad (Atos) - Bug #484803 - Conflict detection on deletion
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.impl.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * An operation which builds differences for a comparison.
 * @author Olivier Constant
 */
public class DiffOperation extends AbstractExpensiveOperation {
  
  /** The non-null diff policy */
  private final IDiffPolicy _diffPolicy;
  
  /** The non-null merge policy */
  private final IMergePolicy _mergePolicy;
  
  /** The non-null comparison whose differences are being built */
  private final IComparison.Editable _comparison;
  
  /** Whether the scope on the REFERENCE side is read-only */
  protected final boolean _isReferenceScopeReadOnly;
  
  /** Whether the scope on the TARGET side is read-only */
  protected final boolean _isTargetScopeReadOnly;
  
  
  /**
   * Constructor based on a comparison with a predefined mapping
   * @param comparison_p a non-null comparison whose mapping is already built
   * @param diffPolicy_p a non-null diff policy
   * @param mergePolicy_p a non-null merge policy
   */
  public DiffOperation(IComparison.Editable comparison_p, IDiffPolicy diffPolicy_p,
      IMergePolicy mergePolicy_p) {
    super();
    _comparison = comparison_p;
    _diffPolicy = diffPolicy_p;
    _mergePolicy = mergePolicy_p;
    _isReferenceScopeReadOnly = getComparison().getScope(Role.REFERENCE).isReadOnly();
    _isTargetScopeReadOnly = getComparison().getScope(Role.TARGET).isReadOnly();
  }
  
  /**
   * Create the attribute order difference corresponding to the given link
   * (holder, reference, value).
   * @param elementMatch_p a non-null match
   * @param attribute_p a non-null attribute
   * @param targetValue_p a non-null object
   * @param referenceValue_p a non-null object
   */
  protected void createAttributeOrderDifference(IMatch elementMatch_p,
      EAttribute attribute_p, Object targetValue_p, Object referenceValue_p) {
    createAttributeValueDifference(elementMatch_p, attribute_p, targetValue_p,
        Role.TARGET, true);
    createAttributeValueDifference(elementMatch_p, attribute_p, referenceValue_p,
        Role.REFERENCE, true);
  }
  
  /**
   * Create the attribute difference corresponding to the given link (holder,
   * attribute, value) on the given role.
   * @param elementMatch_p a non-null, non-partial match
   * @param attribute_p a non-null attribute
   * @param value_p a non-null value
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @return a non-null attribute value presence
   */
  protected IAttributeValuePresence createAttributeValueDifference(
      IMatch elementMatch_p, EAttribute attribute_p, Object value_p, Role role_p, boolean isOrder_p) {
    IAttributeValuePresence result = getComparison().newAttributeValuePresence(
            elementMatch_p, attribute_p, value_p, role_p, isOrder_p);
    IAttributeValuePresence symmetrical = result.getSymmetrical();
    if (symmetrical != null)
      setSymmetricalValuePresenceDependencies(result, symmetrical);
    if (getComparison().isThreeWay())
      setThreeWayProperties(result);
    return result;
  }
  
  /**
   * Create differences based on the mapping between the model scopes compared
   */
  protected void createDifferences() {
    for (IMatch match : getMapping().getContents()) {
      checkProgress();
      if (getDiffPolicy().coverMatch(match))
        createTechnicalDifferences(match);
      getMonitor().worked(1);
    }
  }
  
  /**
   * Create the reference order difference corresponding to the given link
   * (holder, reference, value).
   * @param elementMatch_p a non-null match
   * @param reference_p a non-null reference
   * @param value_p a value element, which is non-null unless valueMatch_p is not null
   * @param valueMatch_p an optional match
   */
  protected void createReferenceOrderDifference(IMatch elementMatch_p,
      EReference reference_p, EObject value_p, IMatch valueMatch_p) {
    createReferenceValueDifference(elementMatch_p, reference_p, value_p,
        valueMatch_p, Role.TARGET, true);
    createReferenceValueDifference(elementMatch_p, reference_p, value_p,
        valueMatch_p, Role.REFERENCE, true);
  }
  
  /**
   * Create the reference difference corresponding to the given link (holder,
   * reference, value) on the given role.
   * @param elementMatch_p a non-null match
   * @param reference_p a potentially null reference (null stands for root containment)
   * @param value_p the value element, which may only be null if valueMatch_p is not null
   * @param valueMatch_p an optional match, which cannot be null if value_p or reference_p is null
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @return a non-null reference value presence
   */
  protected IReferenceValuePresence createReferenceValueDifference(
      IMatch elementMatch_p, EReference reference_p, EObject value_p,
      IMatch valueMatch_p, Role role_p, boolean isOrder_p) {
    IReferenceValuePresence result = getComparison().newReferenceValuePresence(
        elementMatch_p, reference_p, value_p, valueMatch_p, role_p, isOrder_p);
    setReferencedValueDependencies(result);
    if (getComparison().isThreeWay())
      setThreeWayProperties(result);
    return result;
  }
  
  /**
   * Create the technical differences corresponding to the given match
   * @param match_p a non-null match
   */
  protected void createTechnicalDifferences(IMatch match_p) {
    assert match_p != null;
    if (match_p.isPartial()) {
      getOrCreateElementPresence(match_p);
    } else {
      detectContentDifferences(match_p, true);
    }
  }
  
  /**
   * Detect the differences related to the attributes for the given match
   * @param match_p a non-null, non-partial match
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectAllAttributeDifferences(IMatch match_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial();
    EClass eClass = match_p.get(Role.TARGET).eClass();
    boolean result = false;
    for (EAttribute attribute : eClass.getEAllAttributes()) {
      if (getDiffPolicy().coverFeature(attribute))
        result = detectAttributeDifferences(match_p, attribute, create_p) || result;
    }
    return result;
  }
  
  /**
   * Detect the differences related to the non-container references for the
   * given match
   * @param match_p a non-null, non-partial match
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectAllReferenceDifferences(IMatch match_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial();
    EClass eClass = match_p.get(Role.TARGET).eClass();
    boolean result = false;
    for (EReference reference : eClass.getEAllReferences()) {
      if (!reference.isContainer() && getDiffPolicy().coverFeature(reference))
        result = detectReferenceDifferences(match_p, reference, create_p) || result;
    }
    return result;
  }
  
  /**
   * Detect the differences related to the given attribute for the given match
   * @param match_p a non-null, non-partial match
   * @param attribute_p a non-null attribute
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectAttributeDifferences(IMatch match_p, EAttribute attribute_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial() && attribute_p != null;
    boolean result = false;
    IFeaturedModelScope targetScope = getComparison().getScope(Role.TARGET);
    IFeaturedModelScope referenceScope = getComparison().getScope(Role.REFERENCE);
    EObject target = match_p.get(Role.TARGET);
    EObject reference = match_p.get(Role.REFERENCE);
    List<Object> targetValues = targetScope.get(target, attribute_p);
    List<Object> referenceValues = referenceScope.get(reference, attribute_p);
    List<Object> remainingTargetValues = new ArrayList<Object>(targetValues);
    List<Object> remainingReferenceValues = new ArrayList<Object>(referenceValues);
    boolean checkOrder = attribute_p.isMany() && getDiffPolicy().considerOrdered(attribute_p);
    int maxIndex = -1;
    for (Object targetValue : targetValues) {
      ObjectAndIndex matchingReferenceValue = findEqualAttributeValue(
          attribute_p, targetValue, remainingReferenceValues);
      if (matchingReferenceValue.getObject() != null) {
        if (checkOrder) {
          if (matchingReferenceValue.getIndex() < maxIndex) {
            // Ordering difference
            if (!create_p)
              return true;
            createAttributeOrderDifference(
                match_p, attribute_p, targetValue, matchingReferenceValue.getObject());
            result = true;
            checkOrder = false;
          } else {
            maxIndex = matchingReferenceValue.getIndex();
          }
        }
        remainingTargetValues.remove(targetValue);
        remainingReferenceValues.remove(matchingReferenceValue.getObject());
      }
    }
    for (Object remainingTargetValue : remainingTargetValues) {
      if (getDiffPolicy().coverValue(remainingTargetValue, attribute_p)){
        if (!create_p)
          return true;
        createAttributeValueDifference(match_p, attribute_p, remainingTargetValue,
            Role.TARGET, false);
        result = true;
      }
    }
    for (Object remainingReferenceValue : remainingReferenceValues) {
      if (getDiffPolicy().coverValue(remainingReferenceValue, attribute_p)){
        if (!create_p)
          return true;
        createAttributeValueDifference(match_p, attribute_p, remainingReferenceValue,
            Role.REFERENCE, false);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Detect technical differences corresponding to the given non-partial
   * match, focusing on the content of the elements matched
   * @param match_p a non-null, non-partial match
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectContentDifferences(IMatch match_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial();
    boolean result = detectAllAttributeDifferences(match_p, create_p);
    result = detectAllReferenceDifferences(match_p, create_p) || result;
    result = detectOwnershipDifferences(match_p, create_p) || result;
    return result;
  }
  
  /**
   * Detect the differences related to ownership if needed
   * @param match_p a non-null, non-partial match
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectOwnershipDifferences(IMatch match_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial();
    boolean result = false;
    for (Role role : Arrays.asList(Role.TARGET, Role.REFERENCE)) {
      IMatch parentMatch = getComparison().getContainerOf(match_p, role);
      // An ownership difference needs only be created if the container
      // is unmatched, otherwise it is already handled by container refs
      if (parentMatch != null && parentMatch.isPartial()) {
        if (!create_p)
          return true;
        EObject element = match_p.get(role); // Non-null because match_p is not partial
        EReference containment = getComparison().getScope(role).getContainment(element);
        createReferenceValueDifference(parentMatch, containment, element, match_p, role, false);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Detect the differences related to the given reference for the given match
   * @param match_p a non-null, non-partial match
   * @param reference_p a non-null, non-container reference
   * @param create_p whether differences must actually be created
   * @return whether at least one difference was detected
   */
  protected boolean detectReferenceDifferences(IMatch match_p, EReference reference_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial() && reference_p != null;
    assert !reference_p.isContainer();
    boolean result = false;
    IDiffPolicy diffPolicy = getDiffPolicy();
    // Get reference values in different roles
    IFeaturedModelScope targetScope = getComparison().getScope(Role.TARGET);
    IFeaturedModelScope referenceScope = getComparison().getScope(Role.REFERENCE);
    EObject targetElement = match_p.get(Role.TARGET);
    EObject referenceElement = match_p.get(Role.REFERENCE);
    List<EObject> targetValues = targetScope.get(targetElement, reference_p);
    List<EObject> referenceValues = referenceScope.get(referenceElement, reference_p);
    List<EObject> remainingReferenceValues = new FArrayList<EObject>(
        referenceValues, IEqualityTester.BY_REFERENCE);
    boolean checkOrder = reference_p.isMany() && diffPolicy.considerOrdered(reference_p);
    int maxIndex = -1;
    // Check which ones match
    for (EObject targetValue : targetValues) {
      // For every value in TARGET, get its corresponding match if in scope
      IMatch targetValueMatch = getMapping().getMatchFor(targetValue, Role.TARGET);
      // The TARGET value is covered if a match is found or it is a covered out-of-scope value
      boolean outsideTargetScope = targetValueMatch == null;
      boolean coverTargetValue =
          !outsideTargetScope && diffPolicy.coverMatch(targetValueMatch) ||
          outsideTargetScope && diffPolicy.coverOutOfScopeValue(targetValue, reference_p);
      if (coverTargetValue) {
        // Check if matching value is present in REFERENCE
        @SuppressWarnings("null") // OK due to the definition of outsideScope
        EObject matchReferenceValue = outsideTargetScope? targetValue:
          targetValueMatch.get(Role.REFERENCE);
        boolean isIsolated = matchReferenceValue == null;
        int index = -1;
        if (!isIsolated) {
          // Check value presence and ordering
          index = detectReferenceValueAmong(reference_p, matchReferenceValue,
              remainingReferenceValues, outsideTargetScope);
          isIsolated = index < 0;
          if (checkOrder && !isIsolated) {
            if (index < maxIndex) {
              // Ordering difference
              if (!create_p)
                return true;
              createReferenceOrderDifference(
                  match_p, reference_p, targetValue, targetValueMatch);
              result = true;
              checkOrder = false;
            } else {
              maxIndex = index;
            }
          }
        }
        if (isIsolated) {
          // We have a covered unmatched presence on the TARGET side
          if (!create_p)
            return true;
          createReferenceValueDifference(
              match_p, reference_p, targetValue, targetValueMatch, Role.TARGET, false);
          result = true;
        } else {
          // Remove from the remaining values on the REFERENCE side
          if (index > -1)
            remainingReferenceValues.remove(index);
          else
            remainingReferenceValues.remove(matchReferenceValue);
        }
      } // Else TARGET value is out of scope and not covered as such
    }
    // For every remaining value in REFERENCE, create a difference if covered
    for (EObject remainingReferenceValue : remainingReferenceValues) {
      IMatch referenceValueMatch = getMapping().getMatchFor(
          remainingReferenceValue, Role.REFERENCE);
      boolean outsideReferenceScope = referenceValueMatch == null;
      boolean coverReferenceValue =
          !outsideReferenceScope && diffPolicy.coverMatch(referenceValueMatch) ||
          outsideReferenceScope && diffPolicy.coverOutOfScopeValue(
              remainingReferenceValue, reference_p);
      if (coverReferenceValue) {
        // We have a covered unmatched presence on the REFERENCE side
        if (!create_p)
          return true;
        createReferenceValueDifference(match_p, reference_p, remainingReferenceValue,
            referenceValueMatch, Role.REFERENCE, false);
        result = true;
      } // Else REFERENCE value is out of scope and not covered as such
    }
    return result;
  }
  
  /**
   * Return the position of the given reference value among the given list of values,
   * given that it should or not be considered as an out-of-scope value
   * @param reference_p a non-null reference
   * @param value_p a non-null element
   * @param values_p a non-null, potentially empty list
   * @param outsideScope_p whether the value is out-of-scope
   * @return a positive int or -1 if the element is not found
   */
  protected int detectReferenceValueAmong(EReference reference_p,
      EObject value_p, List<EObject> values_p, boolean outsideScope_p) {
    int result = values_p.indexOf(value_p);
    if (result == -1 && outsideScope_p) {
      // Outside scope
      IDiffPolicy diffPolicy = getDiffPolicy();
      int i = -1;
      for (EObject candidateValue : values_p) {
        i++;
        if (diffPolicy.considerEqualOutOfScope(value_p, candidateValue, reference_p)) {
          result = i;
          break;
        }
      }
    }
    return result;
  }
  
  /**
   * Return a value in the given collection of values which is considered equal
   * to the given value for the given attribute and its index in the collection
   * @param attribute_p a non-null attribute
   * @param value_p a non-null value which is type-compatible with the attribute
   * @param candidates_p a non-null collection of candidate values
   * @return a non-null object
   */
  protected ObjectAndIndex findEqualAttributeValue(EAttribute attribute_p, Object value_p,
      Collection<? extends Object> candidates_p) {
    int i = 0;
    for (Object candidate : candidates_p) {
      if (getDiffPolicy().considerEqual(value_p, candidate, attribute_p))
        return new ObjectAndIndex(candidate, i);
      i++;
    }
    return new ObjectAndIndex();
  }
  
  /**
   * Return the comparison which is being built
   * @return a non-null comparison
   */
  public IComparison.Editable getComparison() {
    return _comparison;
  }
  
  /**
   * Return the diff policy
   * @return a non-null diff policy
   */
  protected IDiffPolicy getDiffPolicy() {
    return _diffPolicy;
  }
  
  /**
   * Return the merge policy
   * @return a non-null merge policy
   */
  protected IMergePolicy getMergePolicy() {
    return _mergePolicy;
  }
  
  /**
   * Return the mapping of the comparison which is being built
   */
  protected IMapping getMapping() {
    return getComparison().getMapping();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#getOperationName()
   */
  public String getOperationName() {
    return Messages.DiffBuilder_Task_Main;
  }
  
  /**
   * Create and return the element presence difference corresponding to
   * the given partial match, if allowed. If it already exists, just return it.
   * @param match_p a non-null, partial match
   * @return a potentially null element presence
   */
  protected IElementPresence getOrCreateElementPresence(IMatch match_p) {
    assert match_p != null && match_p.isPartial();
    IElementPresence result = match_p.getElementPresenceDifference();
    if (result == null && getDiffPolicy().coverMatch(match_p)) {
      Role presenceRole = match_p.getUncoveredRole().opposite();
      IMatch ownerMatch = getComparison().getContainerOf(match_p, presenceRole);
      result = getComparison().newElementPresence(match_p, ownerMatch);
      setElementPresenceDependencies(result);
      if (getComparison().isThreeWay())
    	  setThreeWayProperties(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.helpers.AbstractExpensiveOperation#getWorkAmount()
   */
  @Override
  protected int getWorkAmount() {
    return 1 + getMapping().size();
  }
  
  /**
   * Return whether the scope of the given role is read-only.
   * If no scope has the given role, then true is returned.
   * @param role_p a non-null role
   */
  protected boolean isReadOnly(Role role_p) {
    boolean result;
    switch (role_p) {
    case REFERENCE:
      result = _isReferenceScopeReadOnly;
      break;
    case TARGET:
      result = _isTargetScopeReadOnly;
      break;
    default:
      result = true;
    }
    return result;
  }
  
  /**
   * Mark the given source difference as implying the given target difference
   * when merged to the side of the given role
   * @param source_p a non-null difference
   * @param target_p a non-null difference
   * @param role_p a non-null role
   */
  protected void markImplies(IMergeableDifference source_p, IMergeableDifference target_p,
      Role role_p) {
    if (!isReadOnly(role_p)) {
      ((IMergeableDifference.Editable)source_p).markImplies(target_p, role_p);
    }
  }
  
  /**
   * Mark the given source difference as requiring the given target difference
   * when merged to the side of the given role
   * @param source_p a non-null difference
   * @param target_p a non-null difference
   * @param role_p a non-null role
   */
  protected void markRequires(IMergeableDifference source_p, IMergeableDifference target_p,
      Role role_p) {
    if (!isReadOnly(role_p)) {
      ((IMergeableDifference.Editable)source_p).markRequires(target_p, role_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#run()
   */
  public IStatus run() {
    getMonitor().worked(1);
    createDifferences();
    return Status.OK_STATUS;
  }
  
  /**
   * Set dependencies on the given presence difference related to cyclic moves, if needed
   * @param presence_p a non-null containment value presence whose value match is non-null
   *        and not partial
   */
  protected void setCyclicOwnershipDependencies(IReferenceValuePresence presence_p) {
    // Preconditions
    assert presence_p.getValueMatch() != null;
    assert !presence_p.isOrder();
    assert presence_p.getValueMatch().isAMove(); //(1)
    assert presence_p.isOwnership(); // Normally implied by (1)
    assert !presence_p.getValueMatch().isPartial(); // Normally implied by (1)
    // Behavior
    final Role orderingRole = getComparison().getMapping().getOrderingRole();
    final Role oppositeRole = orderingRole.opposite();
    if (presence_p.getPresenceRole() == oppositeRole) {
      final IComparison comparison = getComparison();
      final IMatch valueMatch = presence_p.getValueMatch();
      IMatch oppositeAncestorMatch = valueMatch;
      do {
        // Going up on the non-ordering side
        oppositeAncestorMatch = comparison.getContainerOf(oppositeAncestorMatch, oppositeRole);
        if (oppositeAncestorMatch != null && oppositeAncestorMatch.isAMove()) {
          IReferenceValuePresence cycleEnd = null;
          IMatch orderingAncestorMatch = oppositeAncestorMatch;
          do {
            // Going up on the ordering side
            orderingAncestorMatch =
                comparison.getContainerOf(orderingAncestorMatch, orderingRole);
            if (orderingAncestorMatch == valueMatch)
              cycleEnd = oppositeAncestorMatch.getOwnershipDifference(orderingRole);
          } while (orderingAncestorMatch != null && cycleEnd == null);
          // Setting cycle dependencies
          if (cycleEnd != null) {
            // Breaking cycle on the opposite role, where cycleEnd is the ancestor
            markRequires(cycleEnd, presence_p, oppositeRole);
            // Breaking cycle on the ordering role, where presence_p is the ancestor
            markRequires(presence_p, cycleEnd, orderingRole);
          }
        }
      } while (oppositeAncestorMatch != null);
    }
  }
  
  /**
   * Set dependencies between differences of type element presence exclusively
   * @param presence_p a non-null element presence
   */
  protected void setElementPresenceDependencies(IElementPresence presence_p) {
    Role presenceRole = presence_p.getPresenceRole();
    if (!presence_p.isRoot()) {
      IMatch ownerMatch = presence_p.getOwnerMatch();
      if (getMergePolicy().bindPresenceToOwnership(_comparison.getScope(presenceRole.opposite())) &&
          ownerMatch != null && ownerMatch.isPartial()) {
        IElementPresence ownerPresence = getOrCreateElementPresence(ownerMatch);
        if (ownerPresence != null) {
          // Child addition requires container addition
          markRequires(presence_p, ownerPresence, presenceRole.opposite());
          // Container deletion requires child deletion
          markRequires(ownerPresence, presence_p, presenceRole);
        }
      }
    }
    // Grouped addition according to policy
    Collection<EObject> additionPeers = getMergePolicy().getAdditionGroup(
        presence_p.getElement(), getComparison().getScope(presenceRole));
    for (EObject peer : additionPeers) {
      IMatch peerMatch = getMapping().getMatchFor(peer, presenceRole);
      if (peerMatch != null && peerMatch.isPartial()) {
        IElementPresence peerPresence = getOrCreateElementPresence(peerMatch);
        if (peerPresence != null) {
          // Element addition requires group member addition
          markRequires(presence_p, peerPresence, presenceRole.opposite());
          // Group member deletion requires element deletion
          markRequires(peerPresence, presence_p, presenceRole);
        }
      }
    }
    // Grouped deletion according to policy
    Collection<EObject> deletionPeers = getMergePolicy().getDeletionGroup(
        presence_p.getElement(), getComparison().getScope(presenceRole));
    for (EObject peer : deletionPeers) {
      IMatch peerMatch = getMapping().getMatchFor(peer, presenceRole);
      if (peerMatch != null && peerMatch.isPartial()) {
        IElementPresence peerPresence = getOrCreateElementPresence(peerMatch);
        if (peerPresence != null) {
          // Element deletion requires group member deletion
          markRequires(presence_p, peerPresence, presenceRole);
        }
      }
    }
  }
  
  /**
   * Set dependencies between opposite differences of type reference value presence,
   * that is, reference differences on the same link
   * @param first_p a non-null reference value presence
   * @param second_p a non-null reference value presence which is opposite to first_p
   */
  protected void setOppositeReferenceDependencies(
      IReferenceValuePresence first_p, IReferenceValuePresence second_p) {
    assert first_p.isOppositeOf(second_p);
    // Opposite diffs are implicitly equivalent except for non-many references
    // which bring their own constraints and must therefore be merged explicitly
    Role presenceRole = first_p.getPresenceRole();
    if (second_p.getFeature().isMany()) {
      markImplies(first_p,second_p, presenceRole);
      markImplies(first_p, second_p, presenceRole.opposite());
    } else {
      markRequires(first_p, second_p, presenceRole);
      markRequires(first_p, second_p, presenceRole.opposite());
    }
    if (first_p.getFeature().isMany()) {
      markImplies(second_p, first_p, presenceRole);
      markImplies(second_p, first_p, presenceRole.opposite());
    } else {
      markRequires(second_p, first_p, presenceRole);
      markRequires(second_p, first_p, presenceRole.opposite());
    }
  }
  
  /**
   * Set the dependencies of an ownership, excluding those of classical
   * reference value presences
   * @param presence_p a non-null ownership reference value presence
   */
  protected void setOwnershipDependencies(IReferenceValuePresence presence_p) {
    assert presence_p.isOwnership();
    IMatch valueMatch = presence_p.getValueMatch();
    // Handling dependencies: move of value
    if (valueMatch == null || !valueMatch.isPartial()) {
      // Implicit global !isMany() to containers which are implicitly symmetric
      IReferenceValuePresence symmetricalOwnership = presence_p.getSymmetricalOwnership();
      if (symmetricalOwnership != null)
        setSymmetricalOwnershipDependencies(presence_p, symmetricalOwnership);
      // "Cyclic" moves
      if (valueMatch != null && !presence_p.isOrder())
        setCyclicOwnershipDependencies(presence_p);
    }
  }
  
  /**
   * Set dependencies between a reference value presence and the presence of the value
   * @param referenceDiff_p a non-null reference presence to a partial match
   */
  protected void setPartialReferencedValueDependencies(
      IReferenceValuePresence referenceDiff_p) {
    assert referenceDiff_p.getValueMatch() != null;
    assert referenceDiff_p.getValueMatch().isPartial();
    IElementPresence presence = getOrCreateElementPresence(
        referenceDiff_p.getValueMatch());
    if (presence != null) {
      Role presenceRole = referenceDiff_p.getPresenceRole();
      // Ref requires value presence, value absence requires no ref
      markRequires(referenceDiff_p, presence, presenceRole.opposite());
      markRequires(presence, referenceDiff_p, presenceRole);
      if (referenceDiff_p.getFeature() != null) {
        // If containment and presence requires ownership, value presence implies ref
        // and no ref implies value absence
        if (referenceDiff_p.isOwnership() &&
            getMergePolicy().bindPresenceToOwnership(
                _comparison.getScope(presenceRole.opposite()))) {
          markImplies(presence, referenceDiff_p, presenceRole.opposite());
          markImplies(referenceDiff_p, presence, presenceRole);
        } else {
          // Not a containment or no ownership/presence coupling
          EReference opposite = referenceDiff_p.getFeature().getEOpposite();
          // If reference has an eOpposite which is mandatory for addition, then ...
          if (opposite != null && getMergePolicy().isMandatoryForAddition(opposite)) {
            // ... value presence requires ref
            markRequires(presence, referenceDiff_p, presenceRole.opposite());
            // ... and no ref requires value absence
            markRequires(referenceDiff_p, presence, presenceRole);
          }
        }
      }
    }
  }
  
  /**
   * Set dependencies between a reference value presence and the presence of the holder
   * @param referenceDiff_p a non-null reference presence to a partial match
   */
  protected void setPartialReferencingElementDependencies(
      IReferenceValuePresence referenceDiff_p) {
    assert referenceDiff_p.getElementMatch().isPartial();
    IElementPresence presence = getOrCreateElementPresence(
        referenceDiff_p.getElementMatch());
    if (presence != null) {
      Role presenceRole = referenceDiff_p.getPresenceRole();
      // Ref requires element presence, element absence requires no ref
      markRequires(referenceDiff_p, presence, presenceRole.opposite());
      markRequires(presence, referenceDiff_p, presenceRole);
    }
  }
  
  /**
   * Set the dependencies of a reference value presence
   * @param presence_p a non-null reference value presence
   */
  protected void setReferencedValueDependencies(IReferenceValuePresence presence_p) {
    IMatch valueMatch = presence_p.getValueMatch();
    // Handling dependencies: links (non-container eOpposite)
    if (!presence_p.isOwnership()) {
      IReferenceValuePresence oppositeDiff = presence_p.getOpposite();
      if (oppositeDiff != null)
        setOppositeReferenceDependencies(presence_p, oppositeDiff);
    }
    // Handling dependencies: symmetry (!isMany(), ordering)
    IReferenceValuePresence symmetrical = presence_p.getSymmetrical();
    if (symmetrical != null)
      setSymmetricalValuePresenceDependencies(presence_p, symmetrical);
    // Handling dependencies: presence of element
    if (presence_p.getElementMatch().isPartial())
      setPartialReferencingElementDependencies(presence_p);
    // Handling dependencies: presence of value
    if (valueMatch != null && valueMatch.isPartial())
      setPartialReferencedValueDependencies(presence_p);
    // Handling dependencies: ownership
    if (presence_p.isOwnership())
      setOwnershipDependencies(presence_p);
  }
  
  /**
   * Set dependencies between symmetrical ownership differences
   * @see IReferenceValuePresence#isSymmetricalOwnershipTo(IReferenceValuePresence)
   * @param first_p a non-null reference value presence
   * @param second_p a non-null reference value presence which is the
   *        symmetrical ownership to first_p
   */
  protected void setSymmetricalOwnershipDependencies(
      IReferenceValuePresence first_p, IReferenceValuePresence second_p) {
    assert first_p.isSymmetricalOwnershipTo(second_p);
    // Symmetrical ownership presence is implicit on addition...
    markImplies(first_p, second_p, second_p.getPresenceRole());
    markImplies(second_p, first_p, first_p.getPresenceRole());
    // ... and explicit on removal
    markRequires(first_p, second_p, first_p.getPresenceRole());
    markRequires(second_p, first_p, second_p.getPresenceRole());
  }
  
  /**
   * Set dependencies between symmetrical differences of type value presence
   * @see IValuePresence#isSymmetricalTo(IValuePresence)
   * @param first_p a non-null value presence
   * @param second_p a non-null value presence which is symmetrical to first_p
   */
  protected void setSymmetricalValuePresenceDependencies(
      IValuePresence first_p, IValuePresence second_p) {
    assert first_p.isSymmetricalTo(second_p);
    // Symmetrical diffs are implicitly dependent on addition
    markImplies(first_p, second_p, second_p.getPresenceRole());
    markImplies(second_p, first_p, first_p.getPresenceRole());
    // Symmetrical diffs are explicitly dependent on removal
    markRequires(first_p, second_p, first_p.getPresenceRole());
    markRequires(second_p, first_p, second_p.getPresenceRole());
  }
  
  /**
   * Set the properties which are specific to three-way comparisons to the given difference.
   * Precondition: getComparison().isThreeWay()
   * @param presence_p a non-null element presence
   */
  protected void setThreeWayProperties(IElementPresence presence_p) {
    EObject ancestorElement = presence_p.getElementMatch().get(Role.ANCESTOR);
    if (ancestorElement != null) {
      // Ancestor element present: try and detect differences between ANCESTOR and TARGET/REFERENCE
      // by assigning the TARGET role to the ancestor element locally
      EObject sideElement = presence_p.getElement();
      IMatch tempMach = getComparison().newMatch(ancestorElement, sideElement, null);
      boolean diffWithAncestor = detectContentDifferences(tempMach, false);
      if (diffWithAncestor)
        ((IDifference.Editable)presence_p).markAsConflicting(); // Deleted and changed in parallel
    } else {
      // Ancestor not present: presence is not aligned with ancestor
      ((IDifference.Editable)presence_p).markAsDifferentFromAncestor();
    }
  }
  
  /**
   * Set the properties which are specific to three-way comparisons to the given
   * difference.
   * Precondition: getComparison().isThreeWay()
   * @param presence_p a non-null attribute value presence
   */
  protected void setThreeWayProperties(IAttributeValuePresence presence_p) {
    EObject ancestorHolder = presence_p.getElementMatch().get(Role.ANCESTOR);
    boolean aligned;
    if (ancestorHolder == null) {
      aligned = false;
    } else {
      EAttribute attribute = presence_p.getFeature();
      IFeaturedModelScope ancestorScope = _comparison.getScope(Role.ANCESTOR);
      assert ancestorScope != null; // Thanks to call context
      List<Object> valuesInAncestor = ancestorScope.get(ancestorHolder, attribute);
      if (presence_p.isOrder()) {
        Role presenceRole = presence_p.getPresenceRole();
        List<Object> values = _comparison.getScope(presenceRole).get(
            presence_p.getElementMatch().get(presenceRole),
            presence_p.getFeature());
        int maxIndex = -1;
        aligned = true;
        for (Object value : values) {
          ObjectAndIndex matchingAncestorValue = findEqualAttributeValue(
              attribute, value, valuesInAncestor);
          if (matchingAncestorValue.getObject() != null) {
            if (matchingAncestorValue.getIndex() < maxIndex) {
              // Ordering difference
              aligned = false;
              break;
            }
            maxIndex = matchingAncestorValue.getIndex();
          }
        }
      } else {
        // Not an order
        ObjectAndIndex equalInAncestor = findEqualAttributeValue(
            attribute, presence_p.getValue(), valuesInAncestor);
        aligned = equalInAncestor.getObject() != null;
      }
    }
    if (!aligned) {
      // Not aligned with ancestor
      IAttributeValuePresence symmetrical = presence_p.getSymmetrical();
      if (symmetrical != null && !symmetrical.isAlignedWithAncestor()) {
        // Symmetrical is not aligned either: mark both as conflicting
        ((IDifference.Editable)presence_p).markAsConflicting();
        ((IDifference.Editable)symmetrical).markAsConflicting();
      } else {
        // No symmetrical or symmetrical aligned: just mark diff as not aligned
        ((IDifference.Editable)presence_p).markAsDifferentFromAncestor();
      }
    }
  }
  
  /**
   * Set the properties which are specific to three-way comparisons to the given difference.
   * Precondition: getComparison().isThreeWay()
   * @param presence_p a non-null reference value presence
   */
  protected void setThreeWayProperties(IReferenceValuePresence presence_p) {
    EObject ancestorHolder = presence_p.getElementMatch().get(Role.ANCESTOR);
    boolean aligned; // Aligned with ancestor?
    if (ancestorHolder == null) {
      aligned = false;
    } else {
      IMatch valueMatch = presence_p.getValueMatch();
      EObject ancestorValue =
          valueMatch == null? null: valueMatch.get(Role.ANCESTOR); // May be null
      IFeaturedModelScope ancestorScope = _comparison.getScope(Role.ANCESTOR);
      assert ancestorScope != null; // Thanks to call context
      List<EObject> ancestorValues = new FArrayList<EObject>(
          ancestorScope.get(ancestorHolder, presence_p.getFeature()),
          IEqualityTester.BY_REFERENCE);
      if (presence_p.isOrder()) {
        // Order
        EReference reference = presence_p.getFeature();
        Role presenceRole = presence_p.getPresenceRole();
        List<EObject> values = _comparison.getScope(presenceRole).get(
            presence_p.getElementMatch().get(presenceRole), reference);
        int maxIndex = -1;
        aligned = true;
        for (EObject value : values) {
          IMatch currentValueMatch = getMapping().getMatchFor(value, presenceRole);
          if (currentValueMatch != null) {
            EObject matchAncestor = currentValueMatch.get(Role.ANCESTOR);
            //TODO handle ancestor out-of-scope value
            if (matchAncestor != null) {
              int index = detectReferenceValueAmong(
                  reference, matchAncestor, ancestorValues, false);
              if (index >= 0) {
                if (index < maxIndex) {
                  // Ordering difference
                  aligned = false;
                  break;
                }
                maxIndex = index;
              }
            }
          }
        }
      } else {
        // Not an order
        aligned = ancestorValues.contains(ancestorValue);
      }
    }
    if (!aligned) {
      // Not aligned with ancestor
      IReferenceValuePresence symmetrical = presence_p.getSymmetrical();
      if (symmetrical != null && !symmetrical.isAlignedWithAncestor()) {
        // Symmetrical is not aligned either: mark both as conflicting
        ((IDifference.Editable)presence_p).markAsConflicting();
        ((IDifference.Editable)symmetrical).markAsConflicting();
      } else {
        // No symmetrical or symmetrical aligned: just mark diff as not aligned
        ((IDifference.Editable)presence_p).markAsDifferentFromAncestor();
      }
    }
  }
  
  
  /**
   * A trivial data structure that associates an object and an index.
   * Either the object is not null and the index is greater than or equal to 0,
   * or the structure is (null, -1).
   */
  protected static class ObjectAndIndex {
    /** The potentially null object */
    private Object _object;
    /** The index ranging from -1 to max int, inclusive */
    private int _index;
    /**
     * Full constructor
     * @param object_p a non-null object
     * @param index_p a positive int or 0
     */
    public ObjectAndIndex(Object object_p, int index_p) {
      assert object_p != null && index_p >= 0;
      _object = object_p;
      _index = index_p;
    }
    /**
     * Constructor for no object and -1 as index
     */
    public ObjectAndIndex() {
      _object = null;
      _index = -1;
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object peer_p) {
      boolean result = false;
      if (peer_p instanceof ObjectAndIndex) {
        ObjectAndIndex peer = (ObjectAndIndex)peer_p;
        result = _object == null && peer.getObject() == null ||
          _object != null && _object.equals(peer.getObject());
        result = result && _index == peer.getIndex();
      }
      return result;
    }
    /**
     * Return the object
     * @return a potentially null object
     */
    public Object getObject() {
      return _object;
    }
    /**
     * Return the index
     * @return an int ranging from -1 to max int, inclusive
     */
    public int getIndex() {
      return _index;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return (_object != null? _object.hashCode(): 0) +
        Integer.valueOf(_index).hashCode();
    }
  }
  
}
