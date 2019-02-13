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
 * @param <E> The type of data elements.
 * 
 * @author Matthieu Helleboid
 * @author Olivier Constant
 */
public interface IScopePolicy<E> {
  
  /**
   * Return the opposite of the given reference, if any.
   * Opposite references manifest bidirectional links between elements and are
   * thus assumed to be automatically kept in sync by the data technology.
   * @param reference_p a non-null reference
   * @return a potentially null reference
   */
  Object getOppositeReference(Object reference_p);
  
  /**
   * Return whether the given reference is the opposite of a containment.
   * Class invariant: isContainerReference(r) ==
   *  getOppositeReference(r) != null &&
   *    isContainmentReference(getOppositeReference(r))
   * @see IScopePolicy#isContainmentReference(Object)
   * @see IScopePolicy#getOppositeReference(Object)
   * @param reference_p a non-null reference
   */
  boolean isContainerReference(Object reference_p);
  
  /**
   * Return whether the given reference manifests a parent-child relationship
   * in a forest of elements. The data technology is assumed to automatically
   * preserve the forest structure by, e.g., removing reference values and moving
   * elements as a result of other changes.
   * @param reference_p a non-null reference
   */
  boolean isContainmentReference(Object reference_p);
  
  /**
   * Return whether the given attribute tolerates that its values be changed
   * @param attribute_p a non-null attribute
   */
  boolean isChangeableAttribute(Object attribute_p);
  
  /**
   * Return whether the given reference tolerates that its values be changed
   * @param reference_p a non-null reference
   */
  boolean isChangeableReference(Object reference_p);
  
  /**
   * Return whether the given attribute tolerates more than one value on the
   * same element
   * @param attribute_p a non-null attribute
   */
  boolean isManyAttribute(Object attribute_p);
  
  /**
   * Return whether the given reference tolerates more than one value on the
   * same element
   * @param reference_p a non-null reference
   */
  boolean isManyReference(Object reference_p);
  
  /**
   * Return whether the given attribute stores intrinsic IDs, that is,
   * IDs for the owning elements that are unique within the data scope.
   * @param attribute_p a non-null attribute
   * Class invariant: an ID attribute cannot accept several values, i.e.,
   *  !isID(a) || !isManyAttribute(a)
   */
  boolean isIDAttribute(Object attribute_p);
  
}
