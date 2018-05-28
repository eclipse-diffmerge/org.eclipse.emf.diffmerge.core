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

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.diffmerge.structures.Structures;


/**
 * A structure which is Comparable based on its contents.
 * @param <E> the type of the comparable contents
 * @author Olivier Constant
 */
public interface IComparableStructure<E extends Comparable<?>>
extends Comparable<IComparableStructure<?>> {
  
  /**
   * Return an iterator over the members of this structure; the order of the
   * members defined by this iterator is used for comparing the structure
   * with others. Null values can be encountered during iteration.
   * @return a non-null iterator
   */
  Iterator<E> getCompareIterator();
  
  
  /**
   * A List which is an IComparableStructure.
   */
  interface IComparableList<E extends Comparable<?>> extends List<E>,
  IComparableStructure<E> {
    // Nothing to add
  }
  
  /**
   * A Set which is an IComparableStructure.
   */
  interface IComparableSet<E extends Comparable<?>> extends Set<E>,
  IComparableStructure<E> {
    // Nothing to add
  }
  
  /**
   * A Map.Entry which is an IComparableStructure.
   * @param <K> the type of the keys
   * @param <V> the type of the values
   */
  interface IComparableMapEntry<K extends Comparable<?>, V extends Comparable<?>>
  extends Map.Entry<K, V>, IComparableStructure<Comparable<?>> {
    // Nothing to add
  }
  
  /**
   * A Map which is an IComparableStructure.
   */
  interface IComparableMap<K extends Comparable<?>, V extends Comparable<?>>
  extends Map<K, V>, IComparableStructure<IComparableMapEntry<K, V>> {
    // Nothing to add
  }
  
  
  /**
   * A default Comparator for IComparableStructures.
   * The natural order of IComparableStructures is derived from the natural order
   * of their elements, if available, similarly as the order of Strings is derived
   * from the alphanumeric order of Characters.
   * Structures of different types are ordered based on their class name.
   * Structures of the same type but whose contents are not mutually comparable are
   * considered equal. In this situation, this comparator is thus not consistent
   * with equals.
   */
  Comparator<IComparableStructure<?>> STRUCTURE_COMPARATOR =
    new Comparator<IComparableStructure<?>>() {
    public int compare(IComparableStructure<?> o1_p, IComparableStructure<?> o2_p) {
      // Objects of different types: arbitrarily order by type name
      if (o1_p.getClass() != o2_p.getClass()) {
        return o1_p.getClass().getName().compareTo(
            o2_p.getClass().getName());
      }
      // Objects of the same type
      try {
        Iterator<? extends Comparable<?>> it1 = o1_p.getCompareIterator();
        Iterator<? extends Comparable<?>> it2 = o2_p.getCompareIterator();
        // Compare contents
        while (it1.hasNext() && it2.hasNext()) {
          @SuppressWarnings("unchecked")
          Comparable<Object> member1 = (Comparable<Object>)it1.next();
          @SuppressWarnings("unchecked")
          Comparable<Object> member2 = (Comparable<Object>)it2.next();
          if (member1 != null && member2 != null) {
            int result = member1.compareTo(member2);
            if (result != 0)
              return result;
          } else if (member1 == null && member2 != null) {
            return -1; // null is considered "smaller than" anything else
          } else if (member1 != null) {
            return 1; // Only member 2 is null
          } // Else both are null: keep iterating
        }
        return (!it1.hasNext() && !it2.hasNext())? 0: it1.hasNext()? 1: -1;
      } catch (ClassCastException e) {
        // Members cannot be compared
        return 0;
      }
    }
  };
  
  
  /**
   * A simple implementation of IComparableMapEntry that wraps another Entry.
   * @param <K> the type of the keys
   * @param <V> the type of the values
   */
  public static class ComparableMapEntry<K extends Comparable<?>, V extends Comparable<?>>
  implements IComparableMapEntry<K, V> {
    /** The non-null wrapped entry */
    protected final Map.Entry<K, V> _wrappedEntry;
    /**
     * Constructor
     * @param entry_p a non-null map entry
     */
    public ComparableMapEntry(Map.Entry<K, V> entry_p) {
      _wrappedEntry = entry_p;
    }
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(IComparableStructure<?> o_p) {
      return STRUCTURE_COMPARATOR.compare(this, o_p);
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o_p) {
      return _wrappedEntry.equals(o_p);
    }
    /**
     * @see org.eclipse.emf.diffmerge.structures.common.comparable.IComparableStructure#getCompareIterator()
     */
    public Iterator<Comparable<?>> getCompareIterator() {
      return Arrays.asList(getKey(), getValue()).iterator();
    }
    /**
     * @see java.util.Map.Entry#getKey()
     */
    public K getKey() {
      return _wrappedEntry.getKey();
    }
    /**
     * @see java.util.Map.Entry#getValue()
     */
    public V getValue() {
      return _wrappedEntry.getValue();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return _wrappedEntry.hashCode();
    }
    /**
     * @see java.util.Map.Entry#setValue(java.lang.Object)
     */
    public V setValue(V value_p) {
      return _wrappedEntry.setValue(value_p);
    }
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return Structures.toMapEntryString(this);
    }
  }
  
}
