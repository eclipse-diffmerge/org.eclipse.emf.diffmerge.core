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

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractEList;


/**
 * An array list which supports user-defined equality functions and ensures
 * the absence of duplicates at the cost of O(n) on add(E).
 * The "F" prefix stands for "Flexible".
 * @see FArrayList
 * @see AbstractEList#isUnique()
 * @author Olivier Constant
 */
public class FOrderedSet<E> extends FArrayList<E> implements Set<E> {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /**
   * Constructor for empty set with default equality tester
   */
  public FOrderedSet() {
    super();
  }
  
  /**
   * Constructor for empty set
   * @param tester_p the equality tester (null for default tester)
   */
  public FOrderedSet(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor for empty set with the given initial capacity
   * @param tester_p the equality tester (null for default tester)
   */
  public FOrderedSet(int initialCapacity_p, IEqualityTester tester_p) {
    super(initialCapacity_p, tester_p);
  }
  
  /**
   * Constructor for a set filled with the elements of the given collection
   * @param collection_p a non-null, potentially empty collection
   */
  public FOrderedSet(Collection<? extends E> collection_p)  {
    super(collection_p, null);
  }
  
  /**
   * Constructor for a set with the default equality tester filled with
   * the elements of the given collection
   * @param collection_p a non-null, potentially empty collection
   * @param tester_p the equality tester (null for default tester)
   */
  public FOrderedSet(Collection<? extends E> collection_p,
      IEqualityTester tester_p)  {
    super(collection_p, tester_p);
  }
  
  /**
   * @see org.eclipse.emf.common.util.AbstractEList#isUnique()
   */
  @Override
  protected final boolean isUnique() {
    return true;
  }
  
}
