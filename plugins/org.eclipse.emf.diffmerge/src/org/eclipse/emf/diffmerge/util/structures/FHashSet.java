/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;


/**
 * This class is similar in intent to java.util.HashSet except that it accepts a
 * user-defined equality function.
 * Note that it thus breaks the contract of java.util.Collection.
 * It is also not Cloneable nor Serializable.
 * The default behavior (no equality function specified) is equality by object reference.
 * The "F" prefix stands for "Flexible".
 * @author Olivier Constant
 */
public class FHashSet<E> extends AbstractSet<E> implements IEqualityBasedStructure {
  
  /** A map which is used to guarantee uniqueness of the elements, by using
      them as keys */
  private FHashMap<E,Object> _mappedContents;

  /** An arbitrary object to use as unique value of the map; it must not be
      null because get(key) would not allow to detect the presence of key */
  private static final Object PRESENT = new Object();
  
  
  /**
   * Constructor with default equality tester
   */
  public FHashSet() {
    this(null);
  }
  
  /**
   * Constructor
   * @param tester_p the equality tester
   *        (null for default tester as defined in IEqualityBasedStructure)
   */
  public FHashSet(IEqualityTester tester_p) {
    _mappedContents = new FHashMap<E,Object>(tester_p);
  }
  
  /**
   * Constructor
   * @param collection_p the initial content of the set
   * @param tester_p the equality tester (null for default tester)
   */
  public FHashSet(Collection<? extends E> collection_p,
      IEqualityTester tester_p) {
    _mappedContents = new FHashMap<E,Object>(
        Math.max((int) (collection_p.size()/.75f) + 1, 16), tester_p);
    addAll(collection_p);
  }
  
  /**
   * Constructor
   * @param initialCapacity_p the initial capacity of the set
   * @param tester_p the equality tester (null for default tester)
   */
  public FHashSet(int initialCapacity_p, IEqualityTester tester_p) {
    _mappedContents = new FHashMap<E,Object>(initialCapacity_p, tester_p);
  }
  
  
  /**
   * @see java.util.AbstractCollection#add(Object)
   */
  @Override
  public boolean add(E element_p) {
    return _mappedContents.put(element_p, PRESENT) == null;
  }
  
  /**
   * @see java.util.AbstractCollection#clear()
   */
  @Override
  public void clear() {
    _mappedContents.clear();
  }

  /**
   * @see java.util.AbstractCollection#contains(Object)
   */
  @Override
  public boolean contains(Object element_p) {
    return _mappedContents.containsKey(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _mappedContents.getEqualityTester();
  }
  
  /**
   * @see java.util.AbstractCollection#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return _mappedContents.isEmpty();
  }
  
  /**
   * @see java.util.AbstractCollection#iterator()
   */
  @Override
  public Iterator<E> iterator() {
    return _mappedContents.keySet().iterator();
  }
  
  /**
   * @see java.util.AbstractCollection#remove(Object)
   */
  @Override
  public boolean remove(Object element_p) {
    return _mappedContents.removeKey(element_p) == PRESENT;
  }
  
  /**
   * @see java.util.AbstractCollection#size()
   */
  @Override
  public int size() {
    return _mappedContents.size();
  }
  
}
