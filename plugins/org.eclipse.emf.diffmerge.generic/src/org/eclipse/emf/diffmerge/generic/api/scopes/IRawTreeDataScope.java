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

import org.eclipse.emf.common.util.TreeIterator;


/**
 * An IRawDataScope whose elements are organized as a forest (a set of disjoint trees).
 * All elements of the data set are assumed to be reachable by navigating the forest.
 * How the underlying data technology enforces, or not, the forest structure is
 * intentionally left undefined.
 * 
 * The forest structure can be used, e.g., for tree-based display in a GUI, and it may
 * have an impact on the behavior of the data set when modified.
 * 
 * All methods in this interface are assumed to have no impact on the observable state
 * of a data set.
 * 
 * @param <E> The type of the elements of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IRawTreeDataScope<E> extends IRawDataScope<E> {
  
  /**
   * Return an iterator over the subtree rooted at the given element,.
   * @param element_p a non-null element that belongs to this data scope
   * @return a non-null iterator that does not support removal
   */
  TreeIterator<E> getAllContents(E element_p);
  
  /**
   * Return the children of the given element.
   * @param element_p a non-null element that belongs to this data scope
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Iterable<E> getContents(E element_p);
  
  /**
   * Return the element whose children include the given element, if any.
   * The returned element must belong to the data scope.
   * Result must be consistent with getChildren(EObject).
   * Operation should not be expensive.
   * Class invariant:
   *   (this.covers(X) && getContainer(X) == null) == this.getRoots().contains(X)
   * @param element_p a non-null element which belongs to this data scope
   * @return a potentially null element
   */
  E getContainer(E element_p);
  
  /**
   * Return the root elements of this data scope.
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Iterable<E> getRoots();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IRawDataScope#iterator()
   */
  TreeIterator<E> iterator();
  
}
