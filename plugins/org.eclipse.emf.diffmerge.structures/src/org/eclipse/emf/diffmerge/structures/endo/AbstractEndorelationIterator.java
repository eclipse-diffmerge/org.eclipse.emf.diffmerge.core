/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * An iterator over a given recursively-defined endorelation.
 * If needed, it may ensure that every element is returned exactly once. This is
 * relevant only if the endorelation is not injective and may have cycles.
 * 
 * @author Olivier Constant
 */
public abstract class AbstractEndorelationIterator<T> implements IGraphIterator<T> {
  
  /** The non-null endorelation */
  protected final IRecursivelyDefinedEndorelation<T> _endorelation;
  
  /** Whether to explicitly ensure that every element is returned exactly once
   * (it has a cost in memory usage), which only has an impact if an element
   * may have several inverse elements or there can be cycles */
  protected final boolean _ensureUniqueness;
  
  /** The non-null set of elements already returned or identified as next
   * (used only if _ensureUniqueness) */
  protected final Set<T> _returnedElementsAndNext;
  
  /** The last depth of the iterator (that of the last element returned) */
  private long _lastDepth;
  
  /** The next depth of the iterator (that of the next element) */
  protected long _nextDepth;
  
  /** The greatest depth the iterator has reached so far */
  private long _maxDepth;
  
  /** Whether prune() has been called and must still be taken into account */
  protected boolean _prune;
  
  /** The non-null set of maximal elements found */
  protected final Collection<T> _maximalElements;
  
  /** The non-null set of duplicates found */
  protected final Collection<T> _recurrentElements;
  
  /** The potentially null next element, where (_next == null) == !hasNext() */
  protected T _next;
  
  
  /**
   * Constructor
   * @param endorelation_p the non-null endorelation
   */
  public AbstractEndorelationIterator(IRecursivelyDefinedEndorelation<T> endorelation_p) {
    _endorelation = endorelation_p;
    _ensureUniqueness = mustEnsureUniqueness(endorelation_p);
    _maximalElements = new FHashSet<T>(_endorelation.getEqualityTester());
    _recurrentElements = new FHashSet<T>(_endorelation.getEqualityTester());
    _returnedElementsAndNext = new FHashSet<T>(endorelation_p.getEqualityTester());
    _next = null;
    _prune = false;
    _lastDepth = -1;
    _nextDepth = -1;
    _maxDepth = -1;
  }
  
  /**
   * Return a new iterator over the empty set
   * @return a non-null iterator such that !hasNext()
   */
  protected Iterator<T> emptyIterator() {
    return Collections.<T>emptySet().iterator();
  }
  
  /**
   * Return the next eligible element through the given iterator, if any
   * @param iterator_p a non-null iterator
   * @return a potentially null element
   */
  protected T getNextThrough(Iterator<T> iterator_p) {
    T result = null;
    while (result == null && iterator_p.hasNext()) {
      T next = iterator_p.next();
      if (!_ensureUniqueness) {
        result = next;
      } else {
        boolean added = _returnedElementsAndNext.add(next);
        if (added) {
          // Not already returned
          result = next;
        } else if (trackRecurrentElements()) {
          // Already returned: register as recurrent element and iterate
          _recurrentElements.add(next);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the set of "recurrent" elements that have been found so far, in the
   * order in which they have been found.
   * A recurrent element is an element that can be reached by more than one path
   * from the set of origin elements.
   * It is the complete set of recurrent elements of the endorelation if !hasNext().
   * It is always empty if the endorelation is injective and does not have cycles.
   * @return a non-null, potentially empty collection
   */
  public Collection<T> getRecurrentElements() {
    return Collections.unmodifiableCollection(_recurrentElements);
  }
  
  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    return _next != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#lastDepth()
   */
  public long lastDepth() {
    return _lastDepth;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#maxDepth()
   */
  public long maxDepth() {
    return _maxDepth;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#maximalElements()
   */
  public Collection<T> maximalElements() {
    return Collections.unmodifiableCollection(_maximalElements);
  }
  
  /**
   * Return whether it is necessary to ensure that every element is returned
   * exactly once given the provided endorelation
   * @param endorelation_p a non-null endorelation
   */
  protected boolean mustEnsureUniqueness(IEndorelation<?> endorelation_p) {
    boolean result = true;
    if (endorelation_p instanceof IEndorelation.WithProperties<?>) {
      IEndorelation.WithProperties<?> pEndorelation =
          (IEndorelation.WithProperties<?>)endorelation_p;
      boolean injective = pEndorelation.isInjective().is(Boolean.TRUE);
      if (injective) {
        boolean noCycles = pEndorelation.isWithoutCycles().is(Boolean.TRUE);
        result = !noCycles; // Ensure uniqueness unless there is no cycle
      }
    }
    return result;
  }
  
  /**
   * @see java.util.Iterator#next()
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public T next() {
    if (!hasNext())
      throw new NoSuchElementException();
    T result = _next;
    _next = null;
    updateCurrentDepth();
    update();
    if (!hasNext() && _endorelation instanceof IIterableEndorelation.Internal<?>)
      ((IIterableEndorelation.Internal)_endorelation).notifyExplored(this);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IGraphIterator#nextDepth()
   */
  public long nextDepth() {
    return _nextDepth;
  }
  
  /**
   * @see java.util.Iterator#remove()
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * @see java.lang.Object#toString()
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getClass().getSimpleName());
    builder.append('@');
    builder.append(Integer.toHexString(hashCode()));
    builder.append('[');
    builder.append("Next(");
    builder.append(_next);
    builder.append("), CurrentDepth(");
    builder.append(lastDepth());
    builder.append("), NextDepth(");
    builder.append(nextDepth());
    // Next Path
    builder.append("), NextPath(");
    boolean first = true;
    try {
      for (Object val : nextPath()) {
        if (first)
          first = false;
        else
          builder.append(',');
        builder.append(val);
      }
    } catch (UnsupportedOperationException e) {
      builder.append('?');
    }
    builder.append(")]");
    return builder.toString();
  }
  
  /**
   * Return whether recurrent elements must be tracked (consumes memory)
   */
  protected boolean trackRecurrentElements() {
    return false;
  }
  
  /**
   * Update the next element if needed
   */
  protected abstract void update();
  
  /**
   * Change the current depth due to a call to next()
   */
  private void updateCurrentDepth() {
    _lastDepth = _nextDepth;
    _maxDepth = Math.max(_maxDepth, _lastDepth);
  }
  
}