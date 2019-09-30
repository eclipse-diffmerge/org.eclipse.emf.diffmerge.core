/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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
import java.util.Set;
import java.util.Spliterator;

import org.eclipse.emf.common.util.AbstractEList;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;


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
  
  /**
   * @see java.util.Collection#spliterator()
   */
  public Spliterator<E> spliterator() {
    return super.spliterator();
  }
  
}
