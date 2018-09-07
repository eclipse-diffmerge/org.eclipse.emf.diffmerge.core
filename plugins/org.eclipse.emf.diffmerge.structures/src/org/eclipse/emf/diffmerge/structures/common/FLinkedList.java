/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

import org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;


/**
 * This class is an extension of LinkedList which provides support for user-defined
 * equality functions. Note that it thus breaks the contract of java.util.Collection.
 * The default behavior (no equality function specified) is equality by object reference.
 * Impacted methods include:
 *   contains(Object), indexOf(Object), lastIndexOf(Object), remove(Object).
 * The "F" prefix stands for "Flexible".
 * @author Olivier Constant
 */
public class FLinkedList<E> extends LinkedList<E> implements IEqualityBasedStructure {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** The non-null equality function */
  private IEqualityTester _equalityTester;
  
  
  /**
   * Constructor for empty list with default equality tester
   * @see java.util.LinkedList#LinkedList()
   */
  public FLinkedList() {
    this(null);
  }
  
  /**
   * Constructor for empty list
   * @see java.util.LinkedList#LinkedList()
   * @param tester_p the equality tester (null for default tester)
   */
  public FLinkedList(IEqualityTester tester_p) {
    super();
    setEqualityTester(tester_p);
  }
  
  /**
   * Constructor for a list filled with the elements of the given collection
   * @param collection_p a non-null, potentially empty collection
   * @see java.util.LinkedList#LinkedList(Collection)
   */
  public FLinkedList(Collection<? extends E> collection_p,
      IEqualityTester tester_p)  {
    super(collection_p);
    setEqualityTester(tester_p);
  }
  
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _equalityTester;
  }
  
  /**
   * @see java.util.LinkedList#indexOf(Object)
   */
  @Override
  public int indexOf(Object object_p) {
    ListIterator<E> it = listIterator();
    int i = 0;
    while (it.hasNext()) {
      if (getEqualityTester().areEqual(object_p, it.next()))
        return i;
      i++;
    }
    return -1;
  }
  
  /**
   * @see java.util.LinkedList#lastIndexOf(Object)
   */
  @Override
  public int lastIndexOf(Object object_p) {
    int i = size();
    ListIterator<E> it = listIterator(i);
    while (it.hasPrevious()) {
      i--;
      if (getEqualityTester().areEqual(object_p, it.previous()))
        return i;
    }
    return -1;
  }
  
  /**
   * @see java.util.LinkedList#remove(Object)
   */
  @Override
  public boolean remove(Object object_p) {
    int i = indexOf(object_p);
    boolean result = i >= 0;
    if (result)
      remove(i);
    return result;
  }
  
  /**
   * Set the equality tester of this list.
   * @param tester_p an equality tester (null stands for default)
   */
  private void setEqualityTester(IEqualityTester tester_p) {
    _equalityTester = (tester_p != null)? tester_p: DEFAULT_TESTER;
  }
  
}
