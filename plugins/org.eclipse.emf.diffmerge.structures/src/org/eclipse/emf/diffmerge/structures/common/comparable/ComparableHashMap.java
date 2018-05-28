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
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.diffmerge.structures.Structures;
import org.eclipse.emf.diffmerge.structures.common.comparable.IComparableStructure.IComparableMapEntry;


/**
 * A BasicEMap of Comparable elements which is itself Comparable.
 * Null keys and values are accepted by default.
 * The natural order of maps is derived from the natural order of their entries,
 * at the cost of ordering the entries (n*log(n) performance).
 * @param <K> the type of the keys
 * @param <V> the type of the values
 * @author Olivier Constant
 */
public class ComparableHashMap<K extends Comparable<?>, V extends Comparable<?>>
extends BasicEMap<K, V> implements IComparableStructure<IComparableMapEntry<K, V>> {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** Whether equals rather than == must be used to compare keys */
  private final boolean _useEqualsForKeys;
  
  /** Whether equals rather than == must be used to compare values */
  private final boolean _useEqualsForValues;
  
  
  /**
   * Constructor for empty map and usage of == instead of equals for keys and values
   */
  public ComparableHashMap() {
    this(true, true);
  }
  
  /**
   * Constructor for empty map
   * @param useEqualsForKeys_p whether equals rather than == must be used to compare keys
   * @param useEqualsForValues_p whether equals rather than == must be used to compare values
   */
  public ComparableHashMap(boolean useEqualsForKeys_p, boolean useEqualsForValues_p) {
    super();
    _useEqualsForKeys = useEqualsForKeys_p;
    _useEqualsForValues = useEqualsForValues_p;
  }
  
  /**
   * Constructor for empty map with the given initial capacity
   * @param initialCapacity_p a positive int
   * @param useEqualsForKeys_p whether equals rather than == must be used to compare keys
   * @param useEqualsForValues_p whether equals rather than == must be used to compare values
   */
  public ComparableHashMap(int initialCapacity_p, boolean useEqualsForKeys_p,
      boolean useEqualsForValues_p) {
    super(initialCapacity_p);
    _useEqualsForKeys = useEqualsForKeys_p;
    _useEqualsForValues = useEqualsForValues_p;
  }
  
  /**
   * Constructor for a map filled with the content of the given map
   * @param map_p a non-null, potentially empty map
   * @param useEqualsForKeys_p whether equals rather than == must be used to compare keys
   * @param useEqualsForValues_p whether equals rather than == must be used to compare values
   */
  public ComparableHashMap(Map<? extends K, ? extends V> map_p,
      boolean useEqualsForKeys_p, boolean useEqualsForValues_p)  {
    super(map_p);
    _useEqualsForKeys = useEqualsForKeys_p;
    _useEqualsForValues = useEqualsForValues_p;
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
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Iterator<IComparableMapEntry<K, V>> getCompareIterator() {
    // Entries are sorted by natural order
    Object[] contents = toArray();
    Arrays.sort(contents);
    return (Iterator)Arrays.asList(contents).iterator();
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#newEntry(int, java.lang.Object, java.lang.Object)
   */
  @Override
  protected BasicEMap.Entry<K, V> newEntry(int hash, K key, V value) {
    validateKey(key);
    validateValue(value);
    return new ComparableEntryImpl(hash, key, value);
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#newList()
   */
  @Override
  protected BasicEList<BasicEMap.Entry<K, V>> newList() {
    return
      new BasicEList<BasicEMap.Entry<K, V>>() {
        private static final long serialVersionUID = 1L;
        @Override
        public Object [] newData(int listCapacity) {
          return new ComparableHashMap.ComparableEntryImpl[listCapacity];
        }
      };
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#toString()
   */
  @Override
  public String toString() {
    return Structures.toIterableString(this);
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
   */
  @Override
  protected boolean useEqualsForKey() {
    return _useEqualsForKeys;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
   */
  @Override
  protected boolean useEqualsForValue() {
    return _useEqualsForValues;
  }
  
  
  /**
   * An extension of BasicEMap.EntryImpl which is Comparable.
   */
  protected class ComparableEntryImpl extends EntryImpl implements IComparableMapEntry<K, V> {
    /**
     * Constructor
     * @param hash_p the hash code of the key
     * @param key_p the key
     * @param value_p the value
     */
    public ComparableEntryImpl(int hash_p, K key_p, V value_p) {
      super(hash_p, key_p, value_p);
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
    public Iterator<Comparable<?>> getCompareIterator() {
      return Arrays.asList(getKey(), getValue()).iterator();
    }
    /**
     * @see org.eclipse.emf.common.util.BasicEMap.EntryImpl#toString()
     */
    @Override
    public String toString() {
      return Structures.toMapEntryString(this);
    }
  }
  
}
