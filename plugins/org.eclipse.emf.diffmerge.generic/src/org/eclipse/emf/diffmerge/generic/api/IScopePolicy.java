/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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


/**
 * A policy related to the data technology underlying data scopes.
 * It specifies structural constraints and other meta information that universally
 * apply to data scopes by default. It also provides facilities for element creation.
 * Data scopes can further customize their own behavior in order to raise their level
 * of abstraction w.r.t. their underlying technology. 
 * 
 * @param <E> The type of the elements of applicable data scopes.
 * @param <A> The type of the attributes of applicable data scopes.
 * @param <R> The type of the references of applicable data scopes.
 * 
 * @author Matthieu Helleboid
 * @author Olivier Constant
 */
public interface IScopePolicy<E, A, R> {
  
  /**
   * Return the opposite of the given reference, if any.
   * Opposite references manifest bidirectional links between elements and are
   * thus assumed to be automatically kept in sync by the data technology.
   * @param reference_p a non-null reference
   * @return a potentially null reference
   */
  R getOppositeReference(R reference_p);
  
  /**
   * Return whether the given reference is the opposite of a containment.
   * Class invariant: isContainerReference(r) ==
   *  getOppositeReference(r) != null &&
   *    isContainmentReference(getOppositeReference(r))
   * @see IScopePolicy#isContainmentReference(Object)
   * @see IScopePolicy#getOppositeReference(Object)
   * @param reference_p a non-null reference
   */
  boolean isContainerReference(R reference_p);
  
  /**
   * Return whether the given reference manifests a parent-child relationship
   * in a forest of elements. The data technology is assumed to automatically
   * preserve the forest structure by, e.g., removing reference values and moving
   * elements as a result of other changes.
   * @param reference_p a non-null reference
   */
  boolean isContainmentReference(R reference_p);
  
  /**
   * Return whether the given attribute tolerates that its values be changed
   * @param attribute_p a non-null attribute
   */
  boolean isChangeableAttribute(A attribute_p);
  
  /**
   * Return whether the given reference tolerates that its values be changed
   * @param reference_p a non-null reference
   */
  boolean isChangeableReference(R reference_p);
  
  /**
   * Return whether the given attribute tolerates more than one value on the
   * same element
   * @param attribute_p a non-null attribute
   */
  boolean isManyAttribute(A attribute_p);
  
  /**
   * Return whether the given reference tolerates more than one value on the
   * same element
   * @param reference_p a non-null reference
   */
  boolean isManyReference(R reference_p);
  
  /**
   * Return whether the given attribute stores intrinsic IDs, that is,
   * IDs for the owning elements that are unique within the data scope.
   * @param attribute_p a non-null attribute
   * Class invariant: an ID attribute cannot accept several values, i.e.,
   *  !isID(a) || !isManyAttribute(a)
   */
  boolean isIDAttribute(A attribute_p);
  
}
