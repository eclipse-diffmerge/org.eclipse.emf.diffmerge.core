/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.binary;

import static org.eclipse.emf.diffmerge.structures.Relations.rangedIsInjective;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;


/**
 * An extension of HashBinaryRelation which is invertible thanks to an
 * aggregated inverse HashBinaryRelation.
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public class HashInvertibleBinaryRelation<T, U> extends HashBinaryRelation<T, U>
implements IRangedBinaryRelation.InvertibleEditable<T, U> {
  
  /** The inverse relation, non-null unless getInverser() is overridden */
  private final IRangedBinaryRelation.Editable<U, T> _inverser;
  
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public HashInvertibleBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _inverser = newInverser();
  }
  
  /**
   * Constructor
   */
  public HashInvertibleBinaryRelation() {
    this(null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#add(Object, Object)
   */
  @Override
  public boolean add(T source_p, U target_p) {
    boolean result = super.add(source_p, target_p);
    if (result)
      getInverser().add(target_p, source_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#clear()
   */
  @Override
  public void clear() {
    super.clear();
    getInverser().clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(Object)
   */
  public Collection<T> getInverse(U element_p) {
    return getInverser().get(element_p);
  }
  
  /**
   * Return the inverse relation
   * @return a non-null relation
   */
  protected IRangedBinaryRelation.Editable<U, T> getInverser() {
    return _inverser;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#getTargets()
   */
  @Override
  public Collection<U> getTargets() {
    return getInverser().getSources();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isInjective()
   */
  @Override
  public IPropertyValue<Boolean> isInjective() {
    boolean result = rangedIsInjective(this);
    return PropertyValue.valueOf(result);
  }
  
  /**
   * Create and return the inverse relation
   * @return an object that is non-null unless getInverser() is overridden
   */
  protected IRangedBinaryRelation.Editable<U, T> newInverser() {
    return new HashBinaryRelation<U, T>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#remove(Object, Object)
   */
  @Override
  public boolean remove(T source_p, U target_p) {
    boolean result = super.remove(source_p, target_p);
    if (result)
      getInverser().remove(target_p, source_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#removeSource(java.lang.Object)
   */
  @Override
  public boolean removeSource(T source_p) {
    Collection<U> targets = new FArrayList<U>(get(source_p), getEqualityTester());
    boolean result = super.removeSource(source_p);
    if (result) {
      IRangedBinaryRelation.Editable<U, T> inverser = getInverser();
      for (U target : targets) {
        inverser.remove(target, source_p);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation#removeTarget(java.lang.Object)
   */
  @Override
  public boolean removeTarget(U target_p) {
    Collection<T> sources = new FArrayList<T>(getInverse(target_p), getEqualityTester());
    boolean result = getInverser().removeSource(target_p);
    if (result) {
      for (T source : sources) {
        remove(source, target_p);
      }
    }
    return result;
  }
  
}
