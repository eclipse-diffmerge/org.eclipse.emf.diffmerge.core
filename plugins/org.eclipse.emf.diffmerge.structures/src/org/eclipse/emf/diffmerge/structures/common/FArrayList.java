/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.common;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;


/**
 * This class is an extension of BasicEList which provides support for user-defined
 * equality functions. Note that it thus breaks the contract of java.util.Collection,
 * as does BasicEList when useEquals() returns false.
 * The default behavior (no equality function specified) is equality by object reference.
 * Impacted methods include:
 *   contains(Object), indexOf(Object), lastIndexOf(Object), remove(Object).
 * The "F" prefix stands for "Flexible".
 * @author Olivier Constant
 */
public class FArrayList<E> extends BasicEList<E> implements IEqualityBasedStructure {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** The non-null equality function */
  private IEqualityTester _equalityTester;
  
  
  /**
   * Constructor for empty list with default equality tester
   * @see BasicEList#BasicEList()
   */
  public FArrayList() {
    this(null);
  }
  
  /**
   * Constructor for empty list
   * @see BasicEList#BasicEList()
   * @param tester_p the equality tester (null for default tester)
   */
  public FArrayList(IEqualityTester tester_p) {
    super();
    setEqualityTester(tester_p);
  }
  
  /**
   * Constructor for empty list with the given initial capacity
   * @see BasicEList#BasicEList(int)
   * @param tester_p the equality tester (null for default tester)
   */
  public FArrayList(int initialCapacity_p, IEqualityTester tester_p) {
    super(initialCapacity_p);
    setEqualityTester(tester_p);
  }
  
  /**
   * Constructor for a list filled with the elements of the given collection
   * @param collection_p a non-null, potentially empty collection
   * @param tester_p the equality tester (null for default tester)
   */
  public FArrayList(Collection<? extends E> collection_p,
      IEqualityTester tester_p)  {
    super(collection_p);
    setEqualityTester(tester_p);
  }
  
  
  /**
   * @see org.eclipse.emf.common.util.BasicEList#contains(java.lang.Object)
   */
  @Override
  public boolean contains(Object object_p) {
    return indexOf(object_p) >= 0;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEList#equalObjects(java.lang.Object, java.lang.Object)
   */
  @Override
  protected boolean equalObjects(Object firstObject_p, Object secondObject_p) {
    return _equalityTester.areEqual(firstObject_p, secondObject_p);
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#getDuplicates(java.util.Collection)
   */
  @Override
  protected Collection<E> getDuplicates(Collection<?> collection_p) {
    Collection<E> result;
    if (collection_p.isEmpty()) {
      result = ECollections.emptyEList();
    } else {
      result = new FArrayList<E>(
          collection_p.size(), getEqualityTester());
      for (E object : this) {
        if (collection_p.contains(object))
          result.add(object);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _equalityTester;
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#getNonDuplicates(java.util.Collection)
   */
  @Override
  protected Collection<E> getNonDuplicates(Collection<? extends E> collection_p) {
    Collection<E> result = new FArrayList<E>(collection_p.size(), getEqualityTester());
    for (E object : collection_p) {
      if (!contains(object))
        result.add(object);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEList#indexOf(java.lang.Object)
   */
  @Override
  public int indexOf(Object object_p) {
    for (int i = 0; i < size; ++i) {
      if (equalObjects(object_p, data[i]))
        return i;
    }
    return -1;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEList#lastIndexOf(java.lang.Object)
   */
  @Override
  public int lastIndexOf(Object object_p) {
    for (int i = size - 1; i >= 0; --i) {
      if (equalObjects(object_p, data[i]))
        return i;
    }
    return -1;
  }
  
  /**
   * Set the equality tester of this list.
   * @param tester_p an equality tester (null stands for default)
   */
  private void setEqualityTester(IEqualityTester tester_p) {
    _equalityTester = (tester_p != null)? tester_p: DEFAULT_TESTER;
  }
  
  /**
   * @see org.eclipse.emf.common.util.BasicEList#useEquals()
   */
  @Override
  protected final boolean useEquals() {
    return false;
  }
  
}
