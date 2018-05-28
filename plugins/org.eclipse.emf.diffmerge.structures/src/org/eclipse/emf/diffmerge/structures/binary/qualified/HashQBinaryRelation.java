/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.binary.qualified;

import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedCopyInto;

import java.util.Collection;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;


/**
 * An implementation of modifiable finite, qualified binary relations.
 * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class HashQBinaryRelation<T, U, Q> extends AbstractMapQBinaryRelation<T, U, Q> {
  
  /** The data structure that encodes the relation,
   *  non-null unless getContents() is overridden */
  private final EMap<T, EMap<Q, Collection<U>>> _contents;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public HashQBinaryRelation() {
    this((IEqualityTester)null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public HashQBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _contents = newContents();
  }
  
  /**
   * Constructor
   * @param initialContents_p a non-null ranged qualified binary relation defining the
   *        initial contents of this relation
   */
  public HashQBinaryRelation(
      IRangedQBinaryRelation<T, U, Q> initialContents_p) {
    this(initialContents_p.getEqualityTester());
    qualifiedCopyInto(initialContents_p, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.AbstractMapQBinaryRelation#getContents()
   */
  @Override
  protected EMap<T, EMap<Q, Collection<U>>> getContents() {
    return _contents;
  }
  
  /**
   * Create and return the low-level data structure of the relation
   * @return an object that is non-null unless getContents() is overridden
   */
  protected EMap<T, EMap<Q, Collection<U>>> newContents() {
    return new FHashMap<T, EMap<Q, Collection<U>>>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.AbstractMapQBinaryRelation#newSourceData()
   */
  @Override
  protected EMap<Q, Collection<U>> newSourceData() {
    return new FHashMap<Q, Collection<U>>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.AbstractMapQBinaryRelation#newTargetCollection()
   */
  @Override
  protected Collection<U> newTargetCollection() {
    return new FOrderedSet<U>(getEqualityTester());
  }
  
}
