/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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

import java.util.List;


/**
 * An extension of HashBinaryRelation which is invertible thanks to an
 * aggregated inverse HashBinaryRelation.
 * @param T the type of the domain elements
 * @param U the type of the codomain elements
 * @author Olivier Constant
 */
public class HashInvertibleBinaryRelation<T, U> extends HashBinaryRelation<T, U>
implements IInvertibleBinaryRelation<T, U> {
  
  /** The non-null inverse relation */
  protected final IModifiableBinaryRelation<U, T> _inverse;
  
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public HashInvertibleBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _inverse = new HashBinaryRelation<U, T>(tester_p);
  }
  
  /**
   * Constructor
   */
  public HashInvertibleBinaryRelation() {
    this(null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.HashBinaryRelation#add(Object, Object)
   */
  @Override
  public void add(T source_p, U target_p) {
    super.add(source_p, target_p);
    _inverse.add(target_p, source_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.HashBinaryRelation#clear()
   */
  @Override
  public void clear() {
    super.clear();
    _inverse.clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IInvertibleBinaryRelation#getInverse(Object)
   */
  public List<T> getInverse(U element_p) {
    return _inverse.get(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.HashBinaryRelation#remove(Object, Object)
   */
  @Override
  public void remove(T source_p, U target_p) {
    super.remove(source_p, target_p);
    _inverse.remove(target_p, source_p);
  }
  
}
