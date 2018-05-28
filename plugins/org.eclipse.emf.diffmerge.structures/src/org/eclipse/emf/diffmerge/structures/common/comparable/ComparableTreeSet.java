/**
 * <copyright>
 * 
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
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
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.diffmerge.structures.Structures;
import org.eclipse.emf.diffmerge.structures.common.comparable.IComparableStructure.IComparableSet;


/**
 * A TreeSet which is Comparable based on its contents.
 * Null values are not accepted.
 * @param <E> the type of the elements in the set
 * @author Olivier Constant
 */
public class ComparableTreeSet<E extends Comparable<?>>
extends TreeSet<E> implements IComparableSet<E> {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /**
   * Constructor
   * @see TreeSet#TreeSet()
   */
  public ComparableTreeSet() {
    super();
  }
  
  /**
   * Constructor
   * @see TreeSet#TreeSet(Collection)
   * @param c_p a non-null collection
   */
  public ComparableTreeSet(Collection<? extends E> c_p) {
    super(c_p);
  }
  
  /**
   * Constructor
   * @see TreeSet#TreeSet(SortedSet)
   * @param c_p a non-null sorted set
   */
  public ComparableTreeSet(SortedSet<E> c_p) {
    super(c_p);
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
    return iterator(); // In ascending order
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#toString()
   */
  @Override
  public String toString() {
    return Structures.toIterableString(this);
  }
  
}
