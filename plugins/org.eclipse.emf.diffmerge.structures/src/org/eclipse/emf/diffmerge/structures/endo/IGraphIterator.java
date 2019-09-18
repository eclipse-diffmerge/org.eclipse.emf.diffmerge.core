/*********************************************************************
 * Copyright (c) 2016-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;


/**
 * An iterator for directed graphs. The graph is assumed to have a set of
 * "origin elements" at which iteration starts.
 * 
 * @param <E> the type of the elements returned by this iterator
 * @author Olivier Constant
 */
public interface IGraphIterator<E> extends TreeIterator<E> {
  
  /**
   * Return the depth of the element lastly returned, or -1L if none.
   * An origin element that is minimal has a depth of 0.
   * @return a positive long or -1L
   */
  long lastDepth();
  
  /**
   * Return the path to the element lastly returned
   * @return a non-null, unmodifiable, potentially empty list which is empty
   *          if and only if next() has not been called
   * @throws UnsupportedOperationException if the operation is not supported
   */
  List<E> lastPath();
  
  /**
   * Return the maximal depth that has been reached so far
   * @return a positive long, or -1L if and only if no depth has been reached
   */
  long maxDepth();
  
  /**
   * Return the set of maximal elements that have been found so far, in the order
   * in which they have been found. A maximal element is an element that has no
   * successor (no target) in the endorelation.
   * It is the complete set of maximal elements of the endorelation if !hasNext().
   * @return a non-null, potentially empty collection
   */
  Collection<E> maximalElements();
  
  /**
   * Return the same information as currentDepth(), except that it is about
   * the next element instead of the one lastly returned
   * @return a positive long, or -1L if and only if !hasNext()
   */
  long nextDepth();
  
  /**
   * Return the path to the next element
   * @return a non-null, unmodifiable list which is empty if and only if !hasNext(),
   *          whose last element is otherwise next()
   * @throws UnsupportedOperationException if the operation is not supported
   */
  List<E> nextPath();
  
}
