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
package org.eclipse.emf.diffmerge.generic.api.scopes;

import java.util.Iterator;


/**
 * A high-level, technology-agnostic definition of a scope of data.
 * Data is not assumed to have any structure other than a decomposition into
 * atomic elements.
 * 
 * All methods in this interface are assumed to have no impact on the observable state
 * of a raw data scope. The order in returned iterables is considered to be part of the
 * observable state. All returned iterables are assumed to not contain null values.
 * 
 * @param <E> The type of the elements of data scopes.
 * 
 * @author Olivier Constant
 */
public interface IRawDataScope<E> extends Iterable<E> {
  
  /**
   * Return whether the given element belongs to this set.
   * This operation is allowed to be computationally expensive.
   * Postcondition: true is returned if and only if the element is the same as
   * one of the elements covered by an iterator returned by iterator().
   * Whether equals(...) or == determines that two elements are the same
   * is left to the data scope.
   * @param element_p a non-null element
   */
  default boolean covers(E element_p) {
    // Operator == is used by default for element identification
    for (E current : this) {
      if (current == element_p)
        return true;
    }
    return false;
  }
  
  /**
   * Optionally return an object that characterizes or identifies this data scope.
   * @return a potentially null object
   */
  Object getOriginator();
  
  /**
   * @see java.lang.Iterable#iterator()
   * Postcondition: remove() is not assumed to be supported by the returned iterator.
   */
  Iterator<E> iterator();
  
  /**
   * Return the number of elements in this data scope.
   * This operation is allowed to be computationally expensive.
   */
  default int size() {
    int result = 0;
    for (@SuppressWarnings("unused") E element : this) {
      result++;
    }
    return result;
  }
  
  
  /**
   * A raw data scope which has the ability to be modified.
   * @author Olivier Constant
   */
  interface Editable<E> extends IRawDataScope<E> {
    /**
     * Add the given element to the scope.
     * Whether its contents belong to the scope after execution is intentionally undefined.
     * If the element already belongs to the scope, then the behavior of this operation is
     * undefined.
     * Postcondition: !result || covers(element_p)
     * @param element_p a non-null element
     * @return whether the operation succeeded
     */
    boolean add(E element_p);
    
   /**
     * Return whether this scope is read-only, which means that modification operations have
     * no effect and always return false
     */
    boolean isReadOnly();
    
    /**
     * Remove the given element from this scope.
     * Whether its contents still belong to the scope after execution is intentionally undefined.
     * Precondition: covers(element_p)
     * Postcondition: !result || !covers(element_p)
     * @param element_p a non-null element within the scope
     * @return whether the operation succeeded
     */
    boolean remove(E element_p);
  }
  
}
