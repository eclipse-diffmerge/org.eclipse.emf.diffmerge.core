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

/**
 * A high-level, technology-agnostic definition of a scope of data.
 * Data is assumed to be structured as a set of elements that conforms to some schema.
 * A schema is composed of a set of attributes and a set of references which are
 * partial functions over the set of elements.
 * 
 * - An attribute which is applicable to an element associates the element with a list
 * (totally ordered bag) of elementary values which can be arbitrary Java objects but
 * cannot be elements of the scope.
 * 
 * - A reference which is applicable to an element associates the element with a list
 * of elements. Elements are thus organized as a graph where each edge is labeled with
 * a reference of the schema. No particular assumption is made on the graph.
 * 
 * All methods in this interface are assumed to have no impact on the observable state
 * of a data scope. The order in returned iterables is considered to be part of the
 * observable state. All returned iterables are assumed to not contain null values.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IDataScope<E, A, R> extends IRawDataScope<E> {
  
  /**
   * Return the set of attributes which are applicable to the given element.
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Iterable<A> getAttributes(E element_p);
  
  /**
   * Return the values of the given element w.r.t. the give attribute/
   * @param element_p a non-null element
   * @param attribute_p a non-null attribute which belongs to getAttributes(element_p)
   * @return a non-null, potentially empty, unmodifiable ordered bag
   */
  Iterable<?> getAttributeValues(E element_p, A attribute_p);
  
  /**
   * Return the set of references which are applicable to the given element.
   * @param element_p a non-null element which belongs to getReferences(element_p)
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Iterable<R> getReferences(E element_p);
  
  /**
   * Return the values of the given element w.r.t. the given reference.
   * @param element_p a non-null element
   * @param reference_p a non-null reference
   * @return a non-null, potentially empty, unmodifiable ordered bag
   */
  Iterable<E> getReferenceValues(E element_p, R reference_p);
  
}
