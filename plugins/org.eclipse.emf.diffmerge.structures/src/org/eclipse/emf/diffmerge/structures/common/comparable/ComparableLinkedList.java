/**
 * <copyright>
 * 
 * Copyright (c) 2013-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.common.comparable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.diffmerge.structures.Structures;
import org.eclipse.emf.diffmerge.structures.common.comparable.IComparableStructure.IComparableList;


/**
 * A LinkedList which is Comparable based on its members.
 * Null values are accepted.
 * It is an extension of LinkedList which also supports using == instead of equals
 * similarly as BasicEList does.
 * Note that using == breaks the contract of java.util.Collection.
 * Methods impacted by using == include:
 *   contains(Object), indexOf(Object), lastIndexOf(Object), remove(Object).
 * @param <E> the type of the elements in the list
 * @author Olivier Constant
 */
public class ComparableLinkedList<E extends Comparable<?>> extends LinkedList<E>
implements IComparableList<E> {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** Whether equals rather than == must be used to compare members */
  private final boolean _useEquals;
  
  
  /**
   * Constructor for empty list with default equality tester
   * @see BasicEList#BasicEList()
   */
  public ComparableLinkedList() {
    this(true);
  }
  
  /**
   * Constructor for empty list
   * @param useEquals_p whether equals rather than == must be used to compare members
   * @see BasicEList#BasicEList()
   */
  public ComparableLinkedList(boolean useEquals_p) {
    super();
    _useEquals = useEquals_p;
  }
  
  /**
   * Constructor for a list filled with the elements of the given collection
   * @param collection_p a non-null, potentially empty collection
   * @param useEquals_p whether equals rather than == must be used to compare members
   */
  public ComparableLinkedList(Collection<? extends E> collection_p,
      boolean useEquals_p)  {
    super(collection_p);
    _useEquals = useEquals_p;
  }
  
  /**
   * Return whether the two given elements must be considered equal
   * @param o1_p a potentially null member
   * @param o2_p a potentially null member
   */
  protected boolean areEqual(Object o1_p, Object o2_p) {
    boolean result;
    if (useEquals()) {
      result = o1_p != null && o1_p.equals(o2_p) ||
        o1_p == null && o2_p == null;
    } else {
      result = o1_p == o2_p;
    }
    return result;
  }
  
  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(IComparableStructure<?> o_p) {
    return STRUCTURE_COMPARATOR.compare(this, o_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.common.comparable.IComparableStructure#getCompareIterator()
   */
  public Iterator<E> getCompareIterator() {
    return iterator();
  }
  
  /**
   * @see java.util.LinkedList#indexOf(Object)
   */
  @Override
  public int indexOf(Object object_p) {
    ListIterator<E> it = listIterator();
    int i = 0;
    while (it.hasNext()) {
      if (areEqual(object_p, it.next()))
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
      if (areEqual(object_p, it.previous()))
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
   * @see org.eclipse.emf.common.util.AbstractEList#toString()
   */
  @Override
  public String toString() {
    return Structures.toIterableString(this);
  }
  
  /**
   * Return whether equals rather than == must be used to compare members
   */
  protected boolean useEquals() {
    return _useEquals;
  }
  
}
