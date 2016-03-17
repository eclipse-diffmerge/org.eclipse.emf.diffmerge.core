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

import java.util.Collection;


/**
 * A base implementation for binary relations which provides a few services.
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public abstract class AbstractBinaryRelation<T, U> implements IBinaryRelation<T, U> {
  
  /** The non-null equality function */
  private final IEqualityTester _tester;
  
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractBinaryRelation(IEqualityTester tester_p) {
    _tester = tester_p != null? tester_p: IEqualityBasedStructure.DEFAULT_TESTER;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _tester;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation#maps(Object, Object)
   */
  public boolean maps(T source_p, U target_p) {
    assert source_p != null && target_p != null;
    boolean result = false;
    Collection<U> values = get(source_p);
    if (values != null) {
      result = values.contains(target_p);
    }
    return result;
  }
  
}
