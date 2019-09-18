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
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.diffmerge.structures.common.FLinkedList;


/**
 * An iterator over a given recursively-defined endorelation in depth-first order.
 * 
 * @author Olivier Constant
 */
public class DepthFirstIterator<T> extends AbstractEndorelationIterator<T> {
  
  /** The non-null stack of iterators on mapped elements */
  protected final Deque<Iterator<T>> _iterationStack;
  
  /** The path to the element lastly returned */
  protected final LinkedList<T> _lastPath; // LinkedList: List and Deque
  
  /** The path to the next element */
  protected final LinkedList<T> _nextPath; // LinkedList: List and Deque
  
  
  /**
   * Constructor
   * @param endorelation_p the non-null endorelation
   */
  public DepthFirstIterator(IRecursivelyDefinedEndorelation<T> endorelation_p) {
    super(endorelation_p);
    _iterationStack = new LinkedList<Iterator<T>>();
    Iterator<T> originsIterator = _endorelation.getOrigins().iterator();
    _iterationStack.add(originsIterator);
    _lastPath = new FLinkedList<T>(_endorelation.getEqualityTester());
    _nextPath = new FLinkedList<T>(_endorelation.getEqualityTester());
    update();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#lastPath()
   */
  public List<T> lastPath() {
    return Collections.unmodifiableList(_lastPath);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelationIterator#next()
   */
  @Override
  public T next() {
    _lastPath.clear();
    _lastPath.addAll(_nextPath);
    T result = super.next();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#nextPath()
   */
  public List<T> nextPath() {
    return Collections.unmodifiableList(_nextPath);
  }
  
  /**
   * @see org.eclipse.emf.common.util.TreeIterator#prune()
   */
  public void prune() {
    if (nextDepth() > lastDepth()) {
      _iterationStack.removeFirst();
      _iterationStack.addFirst(emptyIterator());
      _next = null;
      update();
    } // Otherwise no consequences
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelationIterator#update()
   */
  @Override
  protected void update() {
    while (_next == null && !_iterationStack.isEmpty()) {
      Iterator<T> topIterator = _iterationStack.peekFirst();
      _next = getNextThrough(topIterator);
      if (_next == null) {
        // No element found: Pop (backtrack) and iterate
        _iterationStack.removeFirst();
        _nextDepth--;
        if (!_nextPath.isEmpty())
          _nextPath.removeLast();
      } else {
        // Element found: Push (go deeper) and iterate
        Collection<T> nextElements = _endorelation.get(_next);
        Iterator<T> newTopIterator = nextElements.iterator();
        _iterationStack.addFirst(newTopIterator); // Depth-first
        if (!newTopIterator.hasNext()) {
          // We have found a maximal element
          _maximalElements.add(_next);
        }
      } // Else iterate to backtrack further
      if (_next != null) {
        _nextDepth++;
        _nextPath.addLast(_next);
      }
    } // End of main loop
    if (_next == null)
      _nextDepth = -1;
  }
  
}