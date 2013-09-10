/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A policy that defines how merges are performed in a comparison.
 * @author Olivier Constant
 */
public interface IMergePolicy {
  
  /**
   * Return whether a non-root element is present in its scope if and only if its ownership
   * (container, containment setting) is present.
   */
  boolean bindPresenceToOwnership();
  
  /**
   * Return whether the given structural feature must be copied when elements are being copied.
   * @param feature_p a non-null reference or attribute
   */
  boolean copyFeature(EStructuralFeature feature_p);
  
  /**
   * Modify the ID of the given target element according to that of the given source element,
   * if relevant and possible.
   * This operation is called after the target element has been added to a scope as a copy
   * of the source element from the opposite scope.
   * Calling this operation multiple times on the same elements must have the same effect as
   * calling it once (idempotency).
   * @param source_p a non-null element
   * @param target_p a non-null element
   */
  void copyID(EObject source_p, EObject target_p);
  
  /**
   * Return whether cross-references outside the destination scope should be copied when
   * elements are being copied.
   */
  boolean copyOutOfScopeCrossReferences();
  
  /**
   * Return the set of elements which are essential to the given one, i.e., adding the
   * given element requires to add the set of elements; conversely, deleting any element
   * of the set requires to delete the given element
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which element_p belongs
   * @return a non-null, potentially unmodifiable and empty set of elements
   *         belonging to scope_p
   */
  Collection<EObject> getAdditionGroup(EObject element_p, IFeaturedModelScope scope_p);
  
  /**
   * Return the set of elements which must be deleted if the given element is being
   * deleted, in addition to those the given element is essential to as defined by
   * getAdditionGroup
   * @see IMergePolicy#getAdditionGroup(EObject, IFeaturedModelScope)
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which element_p belongs
   * @return a non-null, potentially unmodifiable and empty set of elements
   *         belonging to scope_p
   */
  Collection<EObject> getDeletionGroup(EObject element_p, IFeaturedModelScope scope_p);
  
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
  int getDesiredValuePosition(IComparison comparison_p, Role destination_p,
      IMatch source_p, EReference reference_p, IMatch value_p);
  
  /**
   * Return whether the given reference is essential to its owner, i.e., adding the reference owner
   * requires to also add its reference to the values, whether the values are already present or not;
   * conversely, deleting a reference to any of the values requires to delete the reference owner.
   * Operation is a special case of IMergePolicy#getAdditionGroup(EObject, IFeaturedModelScope)
   * when the values are not present, and must be consistent with that operation.
   * If bindPresenceToOwnership(), operation is only called on cross-references.
   * @see IMergePolicy#bindPresenceToOwnership()
   * @param reference_p a non-null, non-derived, non-container reference
   */
  boolean isMandatoryForAddition(EReference reference_p);
  
  /**
   * Return whether the given reference is mandatory for deletion, i.e., removing the owner
   * requires to remove the value(s).
   * Operation is a special case of IMergePolicy#getDeletionGroup(EObject, IFeaturedModelScope)
   * and must be consistent with that operation.
   * @see IMergePolicy#getDeletionGroup(EObject, IFeaturedModelScope)
   * @param reference_p a non-null, non-derived, non-container reference
   */
  boolean isMandatoryForDeletion(EReference reference_p);
  
}
