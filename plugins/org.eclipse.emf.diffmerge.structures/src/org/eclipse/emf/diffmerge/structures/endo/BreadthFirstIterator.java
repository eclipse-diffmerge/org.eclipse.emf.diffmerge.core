/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.diffmerge.structures.common.FLinkedList;


/**
 * An iterator over a given recursively-defined endorelation in breadth-first order.
 * 
 * @author Olivier Constant
 */
public class BreadthFirstIterator<T> extends AbstractEndorelationIterator<T> {
  
  /** The non-null iterator over the current set of candidates */
  protected Iterator<T> _nextCandidateIterator;
  
  /** The (initially null) element lastly returned */
  protected T _last;
  
  /** The non-null set of returned but non-explored elements at the previous depth */
  protected Deque<T> _uncoveredPreviousDepth;
  
  /** The non-null set of returned but non-explored elements at the current depth */
  protected Deque<T> _uncoveredCurrentDepth;
  
  
  /**
   * Constructor
   * @param endorelation_p the non-null endorelation
   */
  public BreadthFirstIterator(IRecursivelyDefinedEndorelation<T> endorelation_p) {
    super(endorelation_p);
    _uncoveredPreviousDepth = new FLinkedList<T>(_endorelation.getEqualityTester());
    _uncoveredCurrentDepth = new FLinkedList<T>(_endorelation.getEqualityTester());
    _nextCandidateIterator = _endorelation.getOrigins().iterator();
    _last = null;
    if (_nextCandidateIterator.hasNext())
      _nextDepth++;
    update();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#lastPath()
   */
  public List<T> lastPath() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelationIterator#next()
   */
  @Override
  public T next() {
    T result = super.next();
    _last = result;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#nextPath()
   */
  public List<T> nextPath() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * @see org.eclipse.emf.common.util.TreeIterator#prune()
   */
  public void prune() {
    boolean removed = _uncoveredCurrentDepth.remove(_last);
    if (!removed)
      _uncoveredPreviousDepth.remove(_last);
    if (!removed) {
      // The next elements are targets of the last one
      _nextCandidateIterator = emptyIterator();
      _uncoveredCurrentDepth.remove(_next);
      _returnedElementsAndNext.remove(_next);
      _next = null;
    }
    update();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelationIterator#update()
   */
  @Override
  protected void update() {
    // Breadth-first exploration
    while (_next == null && (_nextCandidateIterator.hasNext() ||
        !_uncoveredPreviousDepth.isEmpty() || !_uncoveredCurrentDepth.isEmpty())) {
      _next = getNextThrough(_nextCandidateIterator);
      if (_next != null) {
        // Still iterating though the current iterator
        _uncoveredCurrentDepth.addLast(_next); // Remember to navigate its targets later
      } else {
        // Current iterator does not have a relevant next element:
        // update detailed state and iterate
        if (_uncoveredPreviousDepth.isEmpty()) {
          // Explore next depth: swap collections
          Deque<T> tmp = _uncoveredPreviousDepth;
          _uncoveredPreviousDepth = _uncoveredCurrentDepth;
          _uncoveredCurrentDepth = tmp;
          _nextDepth++;
        }
        if (!_uncoveredPreviousDepth.isEmpty()) {
          T currentUncovered = _uncoveredPreviousDepth.removeFirst();
          Collection<T> targets = _endorelation.get(currentUncovered);
          _nextCandidateIterator = targets.iterator();
          if (!_nextCandidateIterator.hasNext())  // We have found a maximal element
            _maximalElements.add(currentUncovered);
        } // Else done: no more element
      } // End of alternative block
    } // End of main loop
    if (_next == null)
      _nextDepth = -1;
  }
  
}