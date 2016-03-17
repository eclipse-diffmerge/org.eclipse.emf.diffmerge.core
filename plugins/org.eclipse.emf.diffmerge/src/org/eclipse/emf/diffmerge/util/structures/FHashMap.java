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

import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;


/**
 * This class is an extension of BasicEMap which provides support for user-defined
 * equality functions. Note that it thus breaks the contract of java.util.Map, as does
 * BasicEMap when useEqualsForKey() returns false.
 * The default behavior (no equality function specified) is equality by object reference.
 * Methods containsValue(Object) and equals(Object) are always based on equality by
 * object reference.
 * The "F" prefix stands for "Flexible".
 * @author Olivier Constant
 */
public class FHashMap<K, V> extends BasicEMap<K,V>
implements IEqualityBasedStructure {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** The non-null equality function */
  private final IEqualityTester _equalityTester;
  
  
  /**
   * Constructor with default equality tester
   */
  public FHashMap() {
    this(null);
  }
  
  /**
   * Constructor
   * @param tester_p an equality tester (null stands for default)
   */
  public FHashMap(IEqualityTester tester_p) {
    super();
    _equalityTester = (tester_p != null)? tester_p: DEFAULT_TESTER;
  }
  
  /**
   * Constructor
   * @param initialCapacity_p a positive initial capacity
   * @param tester_p an equality tester (null stands for default)
   */
  public FHashMap(int initialCapacity_p, IEqualityTester tester_p) {
    super(initialCapacity_p);
    _equalityTester = (tester_p != null)? tester_p: DEFAULT_TESTER;
  }
  
  /**
   * Constructor
   * @param map_p the non-null initial contents
   * @param tester_p an equality tester (null stands for default)
   */
  public FHashMap(Map<? extends K, ? extends V> map_p, IEqualityTester tester_p) {
    super(2 * map_p.size()); // Cannot invoke similar super constructor
    _equalityTester = (tester_p != null)? tester_p: DEFAULT_TESTER;
    putAll(map_p);
  }
  
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#entryForKey(int, int, Object)
   */
  @Override
  protected Entry<K, V> entryForKey(int index_p, int hash_p, Object key_p) {
    BasicEList<Entry<K, V>> eList = entryData[index_p];
    if (eList != null) {
      for (int j = 0; j < eList.size(); ++j) {
        Entry<K, V> entry = eList.basicGet(j);
        if (entry.getHash() == hash_p &&
            getEqualityTester().areEqual(key_p, entry.getKey()))
          return entry;
      }
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#entryIndexForKey(int, int, Object)
   */
  @Override
  protected int entryIndexForKey(int index_p, int hash_p, Object key_p) {
    BasicEList<Entry<K, V>> eList = entryData[index_p];
    if (eList != null) {
      for (int j = 0; j < eList.size(); ++j) {
        Entry<K, V> entry = eList.basicGet(j);
        if (entry.getHash() == hash_p &&
            getEqualityTester().areEqual(key_p, entry.getKey()))
          return j;
      }
    }
    return -1;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _equalityTester;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#hashOf(Object)
   */
  @Override
  protected int hashOf(Object key_p) {
    return key_p == null ? 0 : getEqualityTester().hashCodeFor(key_p);
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#indexOfKey(Object)
   */
  @Override
  public int indexOfKey(Object key_p) {
    size = delegateEList.size();
    for (int i = 0; i < size; ++i) {
      Entry<K, V> entry = delegateEList.get(i);
      if (getEqualityTester().areEqual(key_p, entry.getKey())) 
        return i;
    }
    return -1;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
   */
  @Override
  protected final boolean useEqualsForKey() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
   */
  @Override
  protected final boolean useEqualsForValue() {
    return false;
  }
  
}
