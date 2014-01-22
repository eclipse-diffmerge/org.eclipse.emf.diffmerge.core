/**
 * <copyright>
 * 
 * Copyright (c) 2013 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.util.structures.comparable;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.emf.diffmerge.util.structures.StructuresUtil;
import org.eclipse.emf.diffmerge.util.structures.comparable.IComparableStructure.IComparableMap;


/**
 * A TreeMap which is Comparable based on its contents.
 * Null values are not accepted.
 * @param <K> the type of the keys
 * @param <V> the type of the values
 * @author Olivier Constant
 */
public class ComparableTreeMap<K extends Comparable<?>, V extends Comparable<?>>
extends TreeMap<K, V> implements IComparableMap<K, V> {
  
  private static final long serialVersionUID = 1L;
  
  
  /**
   * Constructor
   * @see TreeMap#TreeMap()
   */
  public ComparableTreeMap() {
    super();
  }
  
  /**
   * Constructor
   * @param m_p a non-null map
   * @see TreeMap#TreeMap(Map)
   */
  public ComparableTreeMap(Map<? extends K, ? extends V> m_p) {
    super(m_p);
  }
  
  /**
   * Constructor
   * @param m_p a non-null sorted map
   * @see TreeMap#TreeMap(SortedMap)
   */
  public ComparableTreeMap(SortedMap<K, ? extends V> m_p) {
    super(m_p);
  }
  
  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(IComparableStructure<?> o_p) {
    return STRUCTURE_COMPARATOR.compare(this, o_p);
  }
  
  /**
   * @see com.thalesgroup.mde.modelingaids.diffmerge.structures.IComparableStructure#getCompareIterator()
   */
  public Iterator<IComparableMapEntry<K, V>> getCompareIterator() {
    final Iterator<Map.Entry<K, V>> ascendingEntryIterator =
      entrySet().iterator(); // In ascending order
    return new Iterator<IComparableMapEntry<K,V>>() {
      /**
       * @see java.util.Iterator#hasNext()
       */
      public boolean hasNext() {
        return ascendingEntryIterator.hasNext();
      }
      /**
       * @see java.util.Iterator#next()
       */
      public IComparableMapEntry<K, V> next() {
        Map.Entry<K, V> next = ascendingEntryIterator.next();
        return new ComparableMapEntry<K, V>(next);
      }
      /**
       * @see java.util.Iterator#remove()
       */
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#toString()
   */
  @Override
  public String toString() {
    return StructuresUtil.toMapString(this);
  }
  
}
