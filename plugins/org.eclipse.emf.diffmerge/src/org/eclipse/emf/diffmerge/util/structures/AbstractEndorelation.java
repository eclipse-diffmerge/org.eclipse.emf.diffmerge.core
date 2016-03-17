/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.util.structures;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * A base implementation for endorelations providing a few services.
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public abstract class AbstractEndorelation<T> extends AbstractBinaryRelation<T, T>
implements IEndorelation<T> {
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractEndorelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IEndorelation#getTransitiveClosure(Object)
   */
  public List<T> getTransitiveClosure(T element_p) {
    return getTransitiveClosure(Collections.singleton(element_p));
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IEndorelation#getTransitiveClosure(Collection)
   */
  public List<T> getTransitiveClosure(Collection<? extends T> elements_p) {
    // Implementation is iterative, not recursive, for scalability reasons.
    // We use LinkedLists instead of HashSets because we need to preserve a
    // partial order even though it has a cost in performance on contains(Object)
    List<T> result = new FLinkedList<T>(getEqualityTester());
    List<T> toExplore = new FLinkedList<T>(getEqualityTester());
    toExplore.addAll(elements_p);
    while (!toExplore.isEmpty()) {
      T current = toExplore.get(0);
      toExplore.remove(current);
      if (!result.contains(current)) {
        int index = minIndexOfMappingElements(current, result);
        result.add(index, current);
        toExplore.addAll(get(current));
      }
    }
    result.removeAll(elements_p);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the index of the first element in the given list which is mapped to the given element,
   * or the index of the last+1 element if none
   * @param element_p a non-null element
   * @param elements_p a non-null list of elements
   * @return the min of indexes or elements_p.size() if there exists no mapped element
   *         belonging to elements_p
   */
  private int minIndexOfMappingElements(T element_p, List<T> elements_p) {
    final int size = elements_p.size();
    int result = size;
    int currentIndex = 0;
    Iterator<T> it = elements_p.iterator();
    while (result == size && it.hasNext()) {
      T current = it.next();
      Collection<T> mapped = get(current);
      if (mapped.contains(element_p))
        result = currentIndex;
      currentIndex++;
    }
    return result;
  }
  
}
