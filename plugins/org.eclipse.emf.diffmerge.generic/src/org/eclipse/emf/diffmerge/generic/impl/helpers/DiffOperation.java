/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Jaafar Bouayad (Atos) - Bug #484803 - Conflict detection on deletion
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.helpers;

import static org.eclipse.emf.diffmerge.generic.api.Role.ANCESTOR;
import static org.eclipse.emf.diffmerge.generic.api.Role.REFERENCE;
import static org.eclipse.emf.diffmerge.generic.api.Role.TARGET;
import static org.eclipse.emf.diffmerge.structures.IEqualityTester.BY_REFERENCE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.generic.Messages;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;


/**
 * An operation which builds differences for a comparison.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class DiffOperation<E> extends AbstractExpensiveOperation {
  
  /** The non-null diff policy */
  private final IDiffPolicy<E> _diffPolicy;
  
  /** The non-null merge policy */
  private final IMergePolicy<E> _mergePolicy;
  
  /** The non-null comparison whose differences are being built */
  private final IComparison.Editable<E> _comparison;
  
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
  public DiffOperation(IComparison.Editable<E> comparison_p, IDiffPolicy<E> diffPolicy_p,
      IMergePolicy<E> mergePolicy_p) {
    super();
    _comparison = comparison_p;
    _diffPolicy = diffPolicy_p;
    _mergePolicy = mergePolicy_p;
    _isReferenceScopeReadOnly = getComparison().getScope(REFERENCE).isReadOnly();
    _isTargetScopeReadOnly = getComparison().getScope(TARGET).isReadOnly();
  }
  
  /**
   * Return whether the given attribute must be covered by the difference detection algorithm
   * @param attribute_p a non-null object
   * @param scopeOfAttribute_p the non-null scope the attribute is from
   */
  protected boolean coverAttribute(Object attribute_p, ITreeDataScope<E> scopeOfAttribute_p) {
    return getDiffPolicy().coverAttribute(attribute_p, scopeOfAttribute_p);
  }
  
  /**
   * Return whether the given reference must be covered by the difference detection algorithm
   * @param reference_p a non-null object
   * @param scopeOfReference_p the non-null scope the reference is from
   */
  protected boolean coverReference(Object reference_p, ITreeDataScope<E> scopeOfReference_p) {
    return !scopeOfReference_p.mIsContainerReference(reference_p) &&
        getDiffPolicy().coverReference(reference_p, scopeOfReference_p);
  }
  
  /**
   * Create the attribute order difference corresponding to the given link
   * (holder, reference, value1, value2) between the given roles
   * @param elementMatch_p a non-null match
   * @param attribute_p a non-null attribute
   * @param value1_p a non-null object
   * @param value2_p a non-null object
   * @param role1_p a non-null role which is TARGET or REFERENCE
   * @param role2_p a non-null role different from role1_p which is TARGET or REFERENCE
   */
  protected void createAttributeOrderDifference(IMatch<E> elementMatch_p, Object attribute_p,
      Object value1_p, Object value2_p, Role role1_p, Role role2_p) {
    createAttributeValueDifference(
        elementMatch_p, attribute_p, value1_p, role1_p, true);
    createAttributeValueDifference(
        elementMatch_p, attribute_p, value2_p, role2_p, true);
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
  protected IAttributeValuePresence<E> createAttributeValueDifference(
      IMatch<E> elementMatch_p, Object attribute_p, Object value_p,
      Role role_p, boolean isOrder_p) {
    IAttributeValuePresence<E> result = getComparison().newAttributeValuePresence(
            elementMatch_p, attribute_p, value_p, role_p, isOrder_p);
    IAttributeValuePresence<E> symmetrical = result.getSymmetrical();
    if (symmetrical != null) {
      setSymmetricalValuePresenceDependencies(result, symmetrical);
    }
    if (getComparison().isThreeWay()) {
      setThreeWayProperties(result);
    }
    return result;
  }
  
  /**
   * Create differences based on the mapping between the model scopes compared
   */
  protected void createDifferences() {
    for (IMatch<E> match : getMapping().getContents()) {
      checkProgress();
      if (getDiffPolicy().coverMatch(match)) {
        createTechnicalDifferences(match);
      }
      getMonitor().worked(1);
    }
  }
  
  /**
   * Create the reference order difference corresponding to the given link
   * (holder, reference, value)
   * @param elementMatch_p a non-null match
   * @param reference_p a non-null reference
   * @param value_p a value element, which is non-null unless valueMatch_p is not null
   * @param valueMatch_p an optional match
   */
  protected void createReferenceOrderDifference(IMatch<E> elementMatch_p,
      Object reference_p, E value_p, IMatch<E> valueMatch_p) {
    createReferenceValueDifference(elementMatch_p, reference_p, value_p,
        valueMatch_p, TARGET, true);
    createReferenceValueDifference(elementMatch_p, reference_p, value_p,
        valueMatch_p, REFERENCE, true);
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
  protected IReferenceValuePresence<E> createReferenceValueDifference(
      IMatch<E> elementMatch_p, Object reference_p, E value_p,
      IMatch<E> valueMatch_p, Role role_p, boolean isOrder_p) {
    IReferenceValuePresence<E> result = getComparison().newReferenceValuePresence(
        elementMatch_p, reference_p, value_p, valueMatch_p, role_p, isOrder_p);
    setReferencedValueDependencies(result);
    if (getComparison().isThreeWay()) {
      setThreeWayProperties(result);
    }
    return result;
  }
  
  /**
   * Create the technical differences corresponding to the given match
   * @param match_p a non-null match
   */
  protected void createTechnicalDifferences(IMatch<E> match_p) {
    assert match_p != null;
    if (match_p.isPartial()) {
      getOrCreateElementPresence(match_p);
    } else {
      detectContentDifferences(match_p, TARGET, REFERENCE, true);
    }
  }
  
  /**
   * Detect the differences related to the attributes for the given match
   * and the given roles
   * @param match_p a non-null match which is non-partial on the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectAllAttributeDifferences(IMatch<E> match_p, Role role1_p,
      Role role2_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial(role1_p, role2_p);
    boolean result = false;
    ITreeDataScope<E> scope1 = getComparison().getScope(role1_p);
    ITreeDataScope<E> scope2 = getComparison().getScope(role2_p);
    Set<Object> attributes1 = new LinkedHashSet<Object>(
        scope1.mGetAttributes(match_p.get(role1_p)));
    for (Object attribute : attributes1) {
      if (coverAttribute(attribute, scope1)) {
        result = detectAttributeDifferences(
            match_p, attribute, role1_p, role2_p, role1_p, create_p) || result;
      }
    }
    for (Object attribute : scope2.mGetAttributes(match_p.get(role2_p))) {
      if (!attributes1.contains(attribute) && coverAttribute(attribute, scope2)) {
        result = detectAttributeDifferences(
            match_p, attribute, role1_p, role2_p, role2_p, create_p) || result;
      }
    }
    return result;
  }
  
  /**
   * Detect the differences related to the non-container references for the
   * given match and the given roles
   * @param match_p a non-null match which is non-partial for the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectAllReferenceDifferences(IMatch<E> match_p, Role role1_p,
      Role role2_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial(role1_p, role2_p);
    boolean result = false;
    ITreeDataScope<E> scope1 = getComparison().getScope(role1_p);
    ITreeDataScope<E> scope2 = getComparison().getScope(role2_p);
    Set<Object> references1 = new LinkedHashSet<Object>(
        scope1.mGetReferences(match_p.get(role1_p)));
    for (Object reference : references1) {
      if (coverReference(reference, scope1)) {
        result = detectReferenceDifferences(
            match_p, reference, role1_p, role2_p, role1_p, create_p) || result;
      }
    }
    for (Object reference : scope2.mGetReferences(match_p.get(role2_p))) {
      if (!references1.contains(reference) && coverReference(reference, scope2)) {
        result = detectReferenceDifferences(
            match_p, reference, role1_p, role2_p, role2_p, create_p) || result;
      }
    }
    return result;
  }
  
  /**
   * Detect the differences related to the given attribute for the given match
   * and the given roles
   * @param match_p a non-null match which is non-partial on the given roles
   * @param attribute_p a non-null attribute
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param roleOfAttribute_p the role the attribute is from, which is role1_p or role2_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectAttributeDifferences(IMatch<E> match_p, Object attribute_p,
      Role role1_p, Role role2_p, Role roleOfAttribute_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial(role1_p, role2_p) && attribute_p != null;
    boolean result = false;
    ITreeDataScope<E> scope1 = getComparison().getScope(role1_p);
    ITreeDataScope<E> scope2 = getComparison().getScope(role2_p);
    ITreeDataScope<E> scopeOfAttribute = (roleOfAttribute_p == role1_p)? scope1: scope2;
    E element1 = match_p.get(role1_p);
    E element2 = match_p.get(role2_p);
    List<?> values1 = scope1.getAttributeValues(element1, attribute_p);
    List<?> values2 = scope2.getAttributeValues(element2, attribute_p);
    List<Object> remainingValues1 = new ArrayList<Object>(values1);
    List<Object> remainingValues2 = new ArrayList<Object>(values2);
    boolean checkOrder = scopeOfAttribute.mIsManyAttribute(attribute_p) &&
        getDiffPolicy().considerOrderedAttribute(attribute_p, scopeOfAttribute);
    int maxIndex = -1;
    for (Object value1 : values1) {
      ObjectAndIndex matchingValue2 =
          findEqualAttributeValue(attribute_p, value1, remainingValues2, scopeOfAttribute);
      if (matchingValue2.getObject() != null) {
        if (checkOrder) {
          if (matchingValue2.getIndex() < maxIndex) {
            // Ordering difference
            if (!create_p) {
              return true;
            }
            createAttributeOrderDifference(
                match_p, attribute_p, value1, matchingValue2.getObject(), role1_p, role2_p);
            result = true;
            checkOrder = false;
          } else {
            maxIndex = matchingValue2.getIndex();
          }
        }
        remainingValues1.remove(value1);
        remainingValues2.remove(matchingValue2.getObject());
      }
    }
    for (Object remainingValue1 : remainingValues1) {
      if (getDiffPolicy().coverValue(remainingValue1, attribute_p, scope1)){
        if (!create_p) {
          return true;
        }
        createAttributeValueDifference(match_p, attribute_p, remainingValue1, role1_p, false);
        result = true;
      }
    }
    for (Object remainingValue2 : remainingValues2) {
      if (getDiffPolicy().coverValue(remainingValue2, attribute_p, scope2)){
        if (!create_p) {
          return true;
        }
        createAttributeValueDifference(match_p, attribute_p, remainingValue2, role2_p, false);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Detect technical differences corresponding to the given non-partial
   * match between the given roles, focusing on the content of the elements
   * matched
   * @param match_p a non-null match which is non-partial for the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectContentDifferences(IMatch<E> match_p, Role role1_p,
      Role role2_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial(role1_p, role2_p);
    boolean result = detectAllAttributeDifferences(match_p, role1_p, role2_p, create_p);
    result = detectAllReferenceDifferences(match_p, role1_p, role2_p, create_p) || result;
    result = detectOwnershipDifferences(match_p, role1_p, role2_p, create_p) || result;
    return result;
  }
  
  /**
   * Detect the differences related to ownership if needed
   * @param match_p a non-null match which is non-partial for the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectOwnershipDifferences(IMatch<E> match_p, Role role1_p,
      Role role2_p, boolean create_p) {
    assert match_p != null && !match_p.isPartial(role1_p, role2_p);
    boolean result = false;
    for (Role role : Arrays.asList(role1_p, role2_p)) {
      IMatch<E> parentMatch = getComparison().getContainerOf(match_p, role);
      // An ownership difference needs only be created if the container
      // is unmatched, otherwise it is already handled by container refs
      if (parentMatch != null && parentMatch.isPartial(role1_p, role2_p)) {
        if (!create_p) {
          return true;
        }
        E element = match_p.get(role); // Non-null because match_p is not partial
        Object containment = getComparison().getScope(role).getContainment(element);
        createReferenceValueDifference(parentMatch, containment, element, match_p, role, false);
        result = true;
      }
    }
    return result;
  }
  
  /**
   * Detect the differences related to the given reference for the given match
   * and the given roles
   * @param match_p a non-null match which is non-partial for the given roles
   * @param reference_p a non-null, non-container reference
   * @param role1_p a non-null role
   * @param role2_p a non-null role different from role1_p
   * @param roleOfReference_p the role the reference is from, which is role1_p or role2_p
   * @param create_p whether differences must actually be created if the roles are TARGET and REFERENCE
   * @return whether at least one difference was detected
   */
  protected boolean detectReferenceDifferences(IMatch<E> match_p, Object reference_p,
      Role role1_p, Role role2_p, Role roleOfReference_p, boolean create_p) {
    assert roleOfReference_p == role1_p || roleOfReference_p == role2_p;
    assert match_p != null && !match_p.isPartial(role1_p, role2_p) && reference_p != null;
    assert !getComparison().getScope(roleOfReference_p).mIsContainerReference(reference_p);
    boolean result = false;
    IDiffPolicy<E> diffPolicy = getDiffPolicy();
    // Get reference values in different roles
    ITreeDataScope<E> scope1 = getComparison().getScope(role1_p);
    ITreeDataScope<E> scope2 = getComparison().getScope(role2_p);
    ITreeDataScope<E> scopeOfReference = (roleOfReference_p == role1_p)? scope1: scope2;
    E element1 = match_p.get(role1_p);
    E element2 = match_p.get(role2_p);
    List<E> values1 = scope1.getReferenceValues(element1, reference_p);
    List<E> values2 = scope2.getReferenceValues(element2, reference_p);
    List<E> remainingValues2 = new FArrayList<E>(values2, BY_REFERENCE);
    boolean checkOrder = scopeOfReference.mIsManyReference(reference_p) &&
        diffPolicy.considerOrderedReference(reference_p, scopeOfReference);
    int maxIndex = -1;
    // Check which ones match
    for (E value1 : values1) {
      // For every value in role1_p, get its corresponding match if in scope
      IMatch<E> valueMatch1 = getMapping().getMatchFor(value1, role1_p);
      // The role1_p value is covered if a match is found or it is a covered out-of-scope value
      boolean outsideScope1 = valueMatch1 == null;
      boolean coverValue1 =
          !outsideScope1 && diffPolicy.coverMatch(valueMatch1) ||
          outsideScope1 && diffPolicy.coverOutOfScopeValue(
              value1, reference_p, scope1);
      if (coverValue1) {
        // Check if matching value is present in scope2
        @SuppressWarnings("null") // OK due to the definition of outsideScope
        E matchValue2 = outsideScope1? value1: valueMatch1.get(role2_p);
        boolean isIsolated = matchValue2 == null;
        int index = -1;
        if (!isIsolated) {
          // Check value presence and ordering
          index = detectReferenceValueAmong(
              reference_p, matchValue2, remainingValues2, outsideScope1, scopeOfReference);
          isIsolated = index < 0;
          if (checkOrder && !isIsolated) {
            if (index < maxIndex) {
              // Ordering difference
              if (!create_p) {
                return true;
              }
              createReferenceOrderDifference(
                  match_p, reference_p, value1, valueMatch1);
              result = true;
              checkOrder = false;
            } else {
              maxIndex = index;
            }
          }
        }
        if (isIsolated) {
          // We have a covered unmatched presence in role1_p
          if (!create_p) {
            return true;
          }
          createReferenceValueDifference(
              match_p, reference_p, value1, valueMatch1, role1_p, false);
          result = true;
        } else {
          // Remove from the remaining values in role2_p
          if (index > -1) {
            remainingValues2.remove(index);
          } else {
            remainingValues2.remove(matchValue2);
          }
        }
      } // Else value1 is out of scope and not covered as such
    }
    // For every remaining value in role2_p, create a difference if covered
    for (E remainingValue2 : remainingValues2) {
      IMatch<E> valueMatch2 = getMapping().getMatchFor(remainingValue2, role2_p);
      boolean outsideReferenceScope = valueMatch2 == null;
      boolean coverReferenceValue =
          !outsideReferenceScope && diffPolicy.coverMatch(valueMatch2) ||
          outsideReferenceScope && diffPolicy.coverOutOfScopeValue(
              remainingValue2, reference_p, scopeOfReference);
      if (coverReferenceValue) {
        // We have a covered unmatched presence in role2_p
        if (!create_p) {
          return true;
        }
        createReferenceValueDifference(
            match_p, reference_p, remainingValue2, valueMatch2, role2_p, false);
        result = true;
      } // Else value2 is out of scope and not covered as such
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
   * @param scope_p the non-null scope the values belong to
   * @return a positive int or -1 if the element is not found
   */
  protected int detectReferenceValueAmong(Object reference_p, E value_p, List<E> values_p,
      boolean outsideScope_p, ITreeDataScope<E> scope_p) {
    int result = values_p.indexOf(value_p);
    if (result == -1 && outsideScope_p) {
      // Outside scope
      IDiffPolicy<E> diffPolicy = getDiffPolicy();
      int i = -1;
      for (E candidateValue : values_p) {
        i++;
        if (diffPolicy.considerEqualOutOfScope(
            value_p, candidateValue, reference_p, scope_p)) {
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
   * @param scope_p the non-null scope the attribute is from
   * @return a non-null object
   */
  protected ObjectAndIndex findEqualAttributeValue(Object attribute_p, Object value_p,
      Collection<? extends Object> candidates_p, ITreeDataScope<E> scope_p) {
    int i = 0;
    for (Object candidate : candidates_p) {
      if (getDiffPolicy().considerEqual(value_p, candidate, attribute_p, scope_p)) {
        return new ObjectAndIndex(candidate, i);
      }
      i++;
    }
    return new ObjectAndIndex();
  }
  
  /**
   * Return the comparison which is being built
   * @return a non-null comparison
   */
  public IComparison.Editable<E> getComparison() {
    return _comparison;
  }
  
  /**
   * Return the diff policy
   * @return a non-null diff policy
   */
  protected IDiffPolicy<E> getDiffPolicy() {
    return _diffPolicy;
  }
  
  /**
   * Return the merge policy
   * @return a non-null merge policy
   */
  protected IMergePolicy<E> getMergePolicy() {
    return _mergePolicy;
  }
  
  /**
   * Return the mapping of the comparison which is being built
   */
  protected IMapping<E> getMapping() {
    return getComparison().getMapping();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation#getOperationName()
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
  protected IElementPresence<E> getOrCreateElementPresence(IMatch<E> match_p) {
    assert match_p != null && match_p.isPartial();
    IElementPresence<E> result = match_p.getElementPresenceDifference();
    if (result == null && getDiffPolicy().coverMatch(match_p)) {
      Role presenceRole = match_p.getUncoveredRole().opposite();
      IMatch<E> ownerMatch = getComparison().getContainerOf(match_p, presenceRole);
      result = getComparison().newElementPresence(match_p, ownerMatch);
      setElementPresenceDependencies(result);
      if (getComparison().isThreeWay()) {
    	  setThreeWayProperties(result);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.helpers.AbstractExpensiveOperation#getWorkAmount()
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
  protected void markImplies(IMergeableDifference<E> source_p,
      IMergeableDifference<E> target_p, Role role_p) {
    if (!isReadOnly(role_p)) {
      ((IMergeableDifference.Editable<E>)source_p).markImplies(target_p, role_p);
    }
  }
  
  /**
   * Mark the given source difference as requiring the given target difference
   * when merged to the side of the given role
   * @param source_p a non-null difference
   * @param target_p a non-null difference
   * @param role_p a non-null role
   */
  protected void markRequires(IMergeableDifference<E> source_p,
      IMergeableDifference<E> target_p, Role role_p) {
    if (!isReadOnly(role_p)) {
      ((IMergeableDifference.Editable<E>)source_p).markRequires(target_p, role_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation#run()
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
  protected void setCyclicOwnershipDependencies(IReferenceValuePresence<E> presence_p) {
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
      final IComparison<E> comparison = getComparison();
      final IMatch <E>valueMatch = presence_p.getValueMatch();
      IMatch<E> oppositeAncestorMatch = valueMatch;
      do {
        // Going up on the non-ordering side
        oppositeAncestorMatch = comparison.getContainerOf(oppositeAncestorMatch, oppositeRole);
        if (oppositeAncestorMatch != null && oppositeAncestorMatch.isAMove()) {
          IReferenceValuePresence<E> cycleEnd = null;
          IMatch<E> orderingAncestorMatch = oppositeAncestorMatch;
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
  protected void setElementPresenceDependencies(IElementPresence<E> presence_p) {
    Role presenceRole = presence_p.getPresenceRole();
    if (!presence_p.isRoot()) {
      IMatch<E> ownerMatch = presence_p.getOwnerMatch();
      if (getMergePolicy().bindPresenceToOwnership(_comparison.getScope(presenceRole.opposite())) &&
          ownerMatch != null && ownerMatch.isPartial()) {
        IElementPresence<E> ownerPresence = getOrCreateElementPresence(ownerMatch);
        if (ownerPresence != null) {
          // Child addition requires container addition
          markRequires(presence_p, ownerPresence, presenceRole.opposite());
          // Container deletion requires child deletion
          markRequires(ownerPresence, presence_p, presenceRole);
        }
      }
    }
    // Grouped addition according to policy
    Collection<E> additionPeers = getMergePolicy().getAdditionGroup(
        presence_p.getElement(), presence_p.getPresenceScope());
    for (E peer : additionPeers) {
      IMatch<E> peerMatch = getMapping().getMatchFor(peer, presenceRole);
      if (peerMatch != null && peerMatch.isPartial()) {
        IElementPresence<E> peerPresence = getOrCreateElementPresence(peerMatch);
        if (peerPresence != null) {
          // Element addition requires group member addition
          markRequires(presence_p, peerPresence, presenceRole.opposite());
          // Group member deletion requires element deletion
          markRequires(peerPresence, presence_p, presenceRole);
        }
      }
    }
    // Grouped deletion according to policy
    Collection<E> deletionPeers = getMergePolicy().getDeletionGroup(
        presence_p.getElement(), presence_p.getPresenceScope());
    for (E peer : deletionPeers) {
      IMatch<E> peerMatch = getMapping().getMatchFor(peer, presenceRole);
      if (peerMatch != null && peerMatch.isPartial()) {
        IElementPresence<E> peerPresence = getOrCreateElementPresence(peerMatch);
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
      IReferenceValuePresence<E> first_p, IReferenceValuePresence<E> second_p) {
    assert first_p.isOppositeOf(second_p);
    // Opposite diffs are implicitly equivalent except for non-many references
    // which bring their own constraints and must therefore be merged explicitly
    Role presenceRole = first_p.getPresenceRole();
    if (second_p.getPresenceScope().mIsManyReference(second_p.getFeature())) {
      markImplies(first_p,second_p, presenceRole);
      markImplies(first_p, second_p, presenceRole.opposite());
    } else {
      markRequires(first_p, second_p, presenceRole);
      markRequires(first_p, second_p, presenceRole.opposite());
    }
    if (first_p.getPresenceScope().mIsManyReference(first_p.getFeature())) {
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
  protected void setOwnershipDependencies(IReferenceValuePresence<E> presence_p) {
    assert presence_p.isOwnership();
    IMatch<E> valueMatch = presence_p.getValueMatch();
    // Handling dependencies: move of value
    if (valueMatch == null || !valueMatch.isPartial()) {
      // Implicit global !isMany() to containers which are implicitly symmetric
      IReferenceValuePresence<E> symmetricalOwnership = presence_p.getSymmetricalOwnership();
      if (symmetricalOwnership != null) {
        setSymmetricalOwnershipDependencies(presence_p, symmetricalOwnership);
      }
      // "Cyclic" moves
      if (valueMatch != null && !presence_p.isOrder()) {
        setCyclicOwnershipDependencies(presence_p);
      }
    }
  }
  
  /**
   * Set dependencies between a reference value presence and the presence of the value
   * @param referenceDiff_p a non-null reference presence to a partial match
   */
  protected void setPartialReferencedValueDependencies(
      IReferenceValuePresence<E> referenceDiff_p) {
    assert referenceDiff_p.getValueMatch() != null;
    assert referenceDiff_p.getValueMatch().isPartial();
    IElementPresence<E> presence = getOrCreateElementPresence(
        referenceDiff_p.getValueMatch());
    if (presence != null) {
      Role presenceRole = referenceDiff_p.getPresenceRole();
      // Ref requires value presence, value absence requires no ref
      markRequires(referenceDiff_p, presence, presenceRole.opposite());
      markRequires(presence, referenceDiff_p, presenceRole);
      if (referenceDiff_p.getFeature() != null) {
        // If containment and presence requires ownership, value presence implies ref
        // and no ref implies value absence
        IEditableTreeDataScope<E> absenceScope = _comparison.getScope(presenceRole.opposite());
        if (referenceDiff_p.isOwnership() &&
            getMergePolicy().bindPresenceToOwnership(absenceScope)) {
          markImplies(presence, referenceDiff_p, presenceRole.opposite());
          markImplies(referenceDiff_p, presence, presenceRole);
        } else {
          // Not a containment or no ownership/presence coupling
          ITreeDataScope<E> presenceScope = referenceDiff_p.getPresenceScope();
          Object opposite = presenceScope.mGetOppositeReference(
              referenceDiff_p.getFeature());
          // If reference has an eOpposite which is mandatory for addition, then ...
          if (opposite != null && getMergePolicy().isMandatoryForAddition(
              referenceDiff_p.getElementMatch().get(presenceRole), opposite, presenceScope)) {
            // ... value presence requires ref
            markRequires(presence, referenceDiff_p, presenceRole.opposite());
            // ... and no ref requires value absence
            markRequires(referenceDiff_p, presence, presenceRole);
          }
          // isMandatoryForDeletion does not need to be used because references from absent
          // elements to present elements are handled implicitly when adding absent elements
        }
      }
    }
  }
  
  /**
   * Set dependencies between a reference value presence and the presence of the holder
   * @param referenceDiff_p a non-null reference presence to a partial match
   */
  protected void setPartialReferencingElementDependencies(
      IReferenceValuePresence<E> referenceDiff_p) {
    assert referenceDiff_p.getElementMatch().isPartial();
    IElementPresence<E> presence = getOrCreateElementPresence(
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
  protected void setReferencedValueDependencies(IReferenceValuePresence<E> presence_p) {
    IMatch<E> valueMatch = presence_p.getValueMatch();
    // Handling dependencies: links (non-container eOpposite)
    if (!presence_p.isOwnership()) {
      IReferenceValuePresence<E> oppositeDiff = presence_p.getOpposite();
      if (oppositeDiff != null) {
        setOppositeReferenceDependencies(presence_p, oppositeDiff);
      }
    }
    // Handling dependencies: symmetry (!isMany(), ordering)
    IReferenceValuePresence<E> symmetrical = presence_p.getSymmetrical();
    if (symmetrical != null) {
      setSymmetricalValuePresenceDependencies(presence_p, symmetrical);
    }
    // Handling dependencies: presence of element
    if (presence_p.getElementMatch().isPartial()) {
      setPartialReferencingElementDependencies(presence_p);
    }
    // Handling dependencies: presence of value
    if (valueMatch != null && valueMatch.isPartial()) {
      setPartialReferencedValueDependencies(presence_p);
    }
    // Handling dependencies: ownership
    if (presence_p.isOwnership()) {
      setOwnershipDependencies(presence_p);
    }
  }
  
  /**
   * Set dependencies between symmetrical ownership differences
   * @see IReferenceValuePresence#isSymmetricalOwnershipTo(IReferenceValuePresence)
   * @param first_p a non-null reference value presence
   * @param second_p a non-null reference value presence which is the
   *        symmetrical ownership to first_p
   */
  protected void setSymmetricalOwnershipDependencies(
      IReferenceValuePresence<E> first_p, IReferenceValuePresence<E> second_p) {
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
      IValuePresence<E> first_p, IValuePresence<E> second_p) {
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
  @SuppressWarnings("unchecked")
  protected void setThreeWayProperties(IElementPresence<E> presence_p) {
    IMatch<E> elementMatch = presence_p.getElementMatch();
    E ancestorElement = elementMatch.get(ANCESTOR);
    if (ancestorElement != null) {
      // Ancestor element present: try and detect differences between ANCESTOR and TARGET/REFERENCE
      boolean diffWithAncestor = detectContentDifferences(
          elementMatch, presence_p.getPresenceRole(), ANCESTOR, false);
      if (diffWithAncestor) {
        ((IDifference.Editable<E>)presence_p).markAsConflicting(); // Deleted and changed in parallel
      }
    } else {
      // Ancestor not present: presence is not aligned with ancestor
      ((IDifference.Editable<E>)presence_p).markAsDifferentFromAncestor();
    }
  }
  
  /**
   * Set the properties which are specific to three-way comparisons to the given difference.
   * Precondition: getComparison().isThreeWay()
   * @param presence_p a non-null attribute value presence
   */
  @SuppressWarnings("unchecked")
  protected void setThreeWayProperties(IAttributeValuePresence<E> presence_p) {
    E ancestorHolder = presence_p.getElementMatch().get(ANCESTOR);
    boolean aligned;
    if (ancestorHolder == null) {
      aligned = false;
    } else {
      Object attribute = presence_p.getFeature();
      ITreeDataScope<E> ancestorScope = _comparison.getScope(ANCESTOR);
      assert ancestorScope != null; // Thanks to call context
      List<?> valuesInAncestor = ancestorScope.getAttributeValues(ancestorHolder, attribute);
      if (presence_p.isOrder()) {
        Role presenceRole = presence_p.getPresenceRole();
        List<?> values = presence_p.getPresenceScope().getAttributeValues(
            presence_p.getElementMatch().get(presenceRole),
            presence_p.getFeature());
        int maxIndex = -1;
        aligned = true;
        for (Object value : values) {
          ObjectAndIndex matchingAncestorValue = findEqualAttributeValue(
              attribute, value, valuesInAncestor, ancestorScope);
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
            attribute, presence_p.getValue(), valuesInAncestor, ancestorScope);
        aligned = equalInAncestor.getObject() != null;
      }
    }
    if (!aligned) {
      // Not aligned with ancestor
      IAttributeValuePresence<E> symmetrical = presence_p.getSymmetrical();
      if (symmetrical != null && !symmetrical.isAlignedWithAncestor()) {
        // Symmetrical is not aligned either: mark both as conflicting
        ((IDifference.Editable<E>)presence_p).markAsConflicting();
        ((IDifference.Editable<E>)symmetrical).markAsConflicting();
      } else {
        // No symmetrical or symmetrical aligned: just mark diff as not aligned
        ((IDifference.Editable<E>)presence_p).markAsDifferentFromAncestor();
      }
    }
  }
  
  /**
   * Set the properties which are specific to three-way comparisons to the given difference.
   * Precondition: getComparison().isThreeWay()
   * @param presence_p a non-null reference value presence
   */
  @SuppressWarnings("unchecked")
  protected void setThreeWayProperties(IReferenceValuePresence<E> presence_p) {
    E ancestorHolder = presence_p.getElementMatch().get(ANCESTOR);
    boolean aligned; // Aligned with ancestor?
    if (ancestorHolder == null) {
      aligned = false;
    } else {
      IMatch<E> valueMatch = presence_p.getValueMatch();
      E ancestorValue =
          valueMatch == null? null: valueMatch.get(ANCESTOR); // May be null
      ITreeDataScope<E> ancestorScope = _comparison.getScope(ANCESTOR);
      assert ancestorScope != null; // Thanks to call context
      List<E> ancestorValues = new FArrayList<E>(
          ancestorScope.getReferenceValues(ancestorHolder, presence_p.getFeature()),
          IEqualityTester.BY_REFERENCE);
      if (presence_p.isOrder()) {
        // Order
        Object reference = presence_p.getFeature();
        Role presenceRole = presence_p.getPresenceRole();
        List<E> values = presence_p.getPresenceScope().getReferenceValues(
            presence_p.getElementMatch().get(presenceRole), reference);
        int maxIndex = -1;
        aligned = true;
        for (E value : values) {
          IMatch<E> currentValueMatch = getMapping().getMatchFor(value, presenceRole);
          if (currentValueMatch != null) {
            E matchAncestor = currentValueMatch.get(ANCESTOR);
            //TODO handle ancestor out-of-scope value
            if (matchAncestor != null) {
              int index = detectReferenceValueAmong(
                  reference, matchAncestor, ancestorValues, false, ancestorScope);
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
      IReferenceValuePresence<E> symmetrical = presence_p.getSymmetrical();
      if (symmetrical != null && !symmetrical.isAlignedWithAncestor()) {
        // Symmetrical is not aligned either: mark both as conflicting
        ((IDifference.Editable<E>)presence_p).markAsConflicting();
        ((IDifference.Editable<E>)symmetrical).markAsConflicting();
      } else {
        // No symmetrical or symmetrical aligned: just mark diff as not aligned
        ((IDifference.Editable<E>)presence_p).markAsDifferentFromAncestor();
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
