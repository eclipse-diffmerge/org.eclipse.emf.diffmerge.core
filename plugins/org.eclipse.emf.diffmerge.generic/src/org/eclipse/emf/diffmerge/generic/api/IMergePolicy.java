/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.api;

import java.util.Collection;

import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A policy that defines how merges are performed in a comparison.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IMergePolicy<E, A, R> {
  
  /**
   * Create and return a base copy of the given element, i.e., without copying the values
   * of its attributes or references
   * @param element_p a non-null element
   * @return a non-null element
   */
  E baseCopy(E element_p);
  
  /**
   * Return whether a non-root element may only be present in the given scope if its ownership
   * (value in containment setting) is present
   * @param scope_p a non-null scope
   */
  boolean bindPresenceToOwnership(ITreeDataScope<E, A, R> scope_p);
  
  /**
   * Return whether the given attribute must be copied when elements are being copied
   * to the given scope.
   * Note that if false is returned for some attributes while the diff policy specifies that
   * those attributes are covered by the diff phase, then copying an element to the given scope
   * may result in new differences when the comparison is re-computed.
   * @see IDiffPolicy#coverAttribute(Object)
   * @param attibute_p a non-null attribute
   * @param scope_p a non-null scope
   */
  boolean copyAttribute(A attibute_p, IDataScope<E, A, R> scope_p);
  
  /**
   * Return whether the given reference must be copied when elements are being copied
   * to the given scope.
   * Note that if false is returned for some references while the diff policy specifies that
   * those references are covered by the diff phase, then copying an element to the given scope
   * may result in new differences when the comparison is re-computed.
   * @see IDiffPolicy#coverReference(Object)
   * @param reference_p a non-null reference 
   * @param scope_p a non-null scope
   */
  boolean copyReference(R reference_p, IDataScope<E, A, R> scope_p);
  
  /**
   * Return whether the extrinsic IDs of elements must be kept when elements
   * are copied from the given source scope to the given target scope,
   * if applicable
   * @param sourceScope_p a non-null scope
   * @param targetScope_p a non-null scope
   */
  boolean copyExtrinsicIDs(IDataScope<E, A, R> sourceScope_p,
      IDataScope<E, A, R> targetScope_p);
  
  /**
   * Return whether cross-references outside the given source scope must be copied when
   * elements are being copied from the source scope to the given target scope.
   * @param sourceScope_p a non-null scope
   * @param targetScope_p a non-null scope
   */
  boolean copyOutOfScopeCrossReferences(IDataScope<E, A, R> sourceScope_p,
      IDataScope<E, A, R> targetScope_p);
  
  /**
   * Return the set of elements which are essential to the given one, i.e., adding the
   * given element requires to add the set of elements; conversely, deleting any element
   * of the set requires to delete the given element
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which element_p belongs
   * @return a non-null, potentially unmodifiable and empty set of elements
   *         belonging to scope_p
   */
  Collection<E> getAdditionGroup(E element_p, IDataScope<E, A, R> scope_p);
  
  /**
   * Return the set of elements which must be deleted if the given element is being
   * deleted, in addition to those the given element is essential to as defined by
   * getAdditionGroup
   * @see IMergePolicy#getAdditionGroup(Object, IDataScope)
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which element_p belongs
   * @return a non-null, potentially unmodifiable and empty set of elements
   *         belonging to scope_p
   */
  Collection<E> getDeletionGroup(E element_p, IDataScope<E, A, R> scope_p);
  
  /**
   * Return the position that the given value should have among values held by the
   * given source via the given reference in the given destination role, according to
   * the position it has in the opposite role in the given comparison
   * @param comparison_p a non-null comparison
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @param source_p a non-null match
   * @param reference_p a non-null reference
   * @param value_p a non-null value
   * @return a positive integer (0 inclusive) or -1 if no position could be determined
   */
  int getDesiredValuePosition(IComparison<E, A, R> comparison_p, Role destination_p,
      IMatch<E, A, R> source_p, R reference_p, E value_p);
  
  /**
   * Return whether the given reference is essential to its owner, i.e., adding the reference owner
   * requires to also add its reference to the values, whether the values are already present or not;
   * conversely, deleting a reference to any of the values requires to delete the reference owner.
   * Operation is a special case of IMergePolicy#getAdditionGroup(EObject, IFeaturedModelScope)
   * when the values are not present, and must be consistent with that operation.
   * If bindPresenceToOwnership(), operation is only called on cross-references.
   * @see IMergePolicy#bindPresenceToOwnership(ITreeDataScope)
   * @param reference_p a non-null, non-derived, non-container reference
   */
  boolean isMandatoryForAddition(R reference_p);
  
  /**
   * Return whether the given reference is mandatory for deletion, i.e., removing the owner
   * requires to remove the value(s).
   * Operation is a special case of IMergePolicy#getDeletionGroup(EObject, IFeaturedModelScope)
   * and must be consistent with that operation.
   * @see IMergePolicy#getDeletionGroup(Object, IDataScope)
   * @param reference_p a non-null, non-derived, non-container reference
   */
  boolean isMandatoryForDeletion(R reference_p);
  
  /**
   * Set the extrinsic and/or ID of the given target element, if possible and relevant.
   * This operation is called after the target element has been added to the given target
   * scope as a copy of the given source element from the given source scope.
   * Calling this operation multiple times on the same elements must have the same effect as
   * calling it once (idempotency).
   * Examples of possible behaviors include: copying the extrinsic ID from the source element
   * to the target element, creating a new ID for the target element, or fully delegating to
   * the underlying storage technology.
   * @param source_p a non-null element
   * @param sourceScope_p a non-null scope
   * @param target_p a non-null element
   * @param targetScope_p a non-null scope
   */
  void setExtrinsicID(E source_p, IDataScope<E, A, R> sourceScope_p,
      E target_p, IDataScope<E, A, R> targetScope_p);
  
  /**
   * Set the ID of the given target element, if possible and relevant.
   * This operation covers intrinsic (attribute-based) and extrinsic (persistence-specific) IDs.
   * It is called after the target element has been added to the given target scope as a copy
   * of the given source element from the given source scope.
   * Calling this operation multiple times on the same elements must have the same effect as
   * calling it once (idempotency).
   * Examples of possible behaviors include: copying ID(s) from the source element to the target
   * element, creating a new ID for the target element, or, for extrinsic IDs, fully delegating
   * to the underlying persistence mechanism.
   * Note that if intrinsic IDs are not copied and the diff policy specifies that ID attributes
   * are covered by the diff phase, then copying an element to the given target scope will result
   * in new differences when the comparison is re-computed.
   * @see IScopePolicy#isIDAttribute(Object)
   * @see IDiffPolicy#coverAttribute(Object)
   * @param source_p a non-null element
   * @param sourceScope_p a non-null scope
   * @param target_p a non-null element
   * @param targetScope_p a non-null scope
   */
  void setID(E source_p, IDataScope<E, A, R> sourceScope_p, E target_p,
      IDataScope<E, A, R> targetScope_p);
  
}
