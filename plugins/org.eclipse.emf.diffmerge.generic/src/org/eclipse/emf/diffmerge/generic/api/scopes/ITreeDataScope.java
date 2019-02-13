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
 * An IDataScope whose elements are organized as a forest (a set of disjoint trees).
 * A subset of the references are assumed to be 'containment' references, which means
 * that all edges of the graph labeled by these references belong to the forest.
 * All elements of the scope are assumed to be reachable by navigating the forest.
 * How the underlying data technology enforces, or not, the forest structure is
 * intentionally left undefined.
 * 
 * The forest structure can be used, e.g., for tree-based display in a GUI, and it may
 * have an impact on the behavior of the data scope when modified.
 * 
 * All methods in this interface are assumed to have no impact on the observable state
 * of a data scope.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface ITreeDataScope<E> extends IDataScope<E>,
IRawTreeDataScope<E> {
  
  /**
   * Return the containment reference through which the given element is
   * referenced by its parent, if any.
   * Result must be consistent with getContainer(EObject).
   * Postcondition: result == null || isContainment(result)
   * Postcondition: (result == null) == (getContainer(element_p) == null)
   * @param element_p a non-null element
   * @return a potentially null reference
   */
  Object getContainment(E element_p);
  
  /**
   * Return whether the given reference is a containment
   * @param reference_p a non-null reference that belongs to the schema of this scope
   */
  boolean isContainment(Object reference_p);
  
}
