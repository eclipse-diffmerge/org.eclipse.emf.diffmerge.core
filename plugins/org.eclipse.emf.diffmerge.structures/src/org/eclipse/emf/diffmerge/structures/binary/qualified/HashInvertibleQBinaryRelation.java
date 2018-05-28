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

import static org.eclipse.emf.diffmerge.structures.Relations.rangedIsInjective;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;


/**
 * An implementation of invertible modifiable, finite, qualified binary relations.
 * @see IRangedQBinaryRelation.Invertible
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class HashInvertibleQBinaryRelation<T, U, Q> extends HashQBinaryRelation<T, U, Q>
implements IRangedQBinaryRelation.InvertibleEditable<T, U, Q> {
  
  /** The inverse relation, non-null unless getInverser() is overridden */
  private final IRangedQBinaryRelation.Editable<U, T, Q> _inverser;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public HashInvertibleQBinaryRelation() {
    this(null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public HashInvertibleQBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _inverser = newInverser();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#add(java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean add(T source_p, U target_p) {
    boolean result = super.add(source_p, target_p);
    if (result)
      getInverser().add(target_p, source_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#add(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean add(T source_p, U target_p, Q qualifier_p) {
    boolean result = super.add(source_p, target_p, qualifier_p);
    if (result)
      getInverser().add(target_p, source_p, qualifier_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#addAll(java.lang.Object, java.util.Collection)
   */
  @Override
  public boolean addAll(T source_p, Collection<? extends U> targets_p) {
    boolean result = super.addAll(source_p, targets_p);
    if (result) {
      IRangedQBinaryRelation.Editable<U, T, Q> inverser = getInverser();
      for (U target : targets_p) {
        inverser.add(target, source_p);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#addAll(java.lang.Object, java.util.Collection, java.lang.Object)
   */
  @Override
  public boolean addAll(T source_p, Collection<? extends U> targets_p, Q qualifier_p) {
    boolean result = super.addAll(source_p, targets_p, qualifier_p);
    if (result) {
      IRangedQBinaryRelation.Editable<U, T, Q> inverser = getInverser();
      for (U target : targets_p) {
        inverser.add(target, source_p, qualifier_p);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#clear()
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
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverse(java.lang.Object, java.lang.Object)
   */
  public Collection<T> getInverse(U element_p, Q qualifier_p) {
    return getInverser().get(element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseQualifiers(java.lang.Object)
   */
  public Collection<Q> getInverseQualifiers(U element_p) {
    return getInverser().getQualifiers(element_p);
  }
  
  /**
   * Return the inverse relation
   * @return a non-null relation
   */
  protected IRangedQBinaryRelation.Editable<U, T, Q> getInverser() {
    return _inverser;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getInverseWithDetails(U element_p) {
    return getInverser().getWithDetails(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#getTargets()
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
  protected IRangedQBinaryRelation.Editable<U, T, Q> newInverser() {
    return new HashQBinaryRelation<U, T, Q>(getEqualityTester()) {
      /**
       * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#defaultQualifier()
       */
      @Override
      public Q defaultQualifier() {
        return HashInvertibleQBinaryRelation.this.defaultQualifier();
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#remove(java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean remove(T source_p, U target_p) {
    boolean result = super.remove(source_p, target_p);
    if (result)
      getInverser().remove(target_p, source_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#remove(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean remove(T source_p, U target_p, Q qualifier_p) {
    boolean result = super.remove(source_p, target_p, qualifier_p);
    if (result)
      getInverser().remove(target_p, source_p, qualifier_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.AbstractMapQBinaryRelation#removeQualifier(java.lang.Object)
   */
  @Override
  public boolean removeQualifier(Q qualifier_p) {
    boolean result = super.removeQualifier(qualifier_p);
    if (result)
      getInverser().removeQualifier(qualifier_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#removeSource(java.lang.Object)
   */
  @Override
  public boolean removeSource(T source_p) {
    Collection<U> targets = new FArrayList<U>(get(source_p), getEqualityTester());
    boolean result = super.removeSource(source_p);
    if (result) {
      IRangedQBinaryRelation.Editable<U, T, Q> inverser = getInverser();
      for (U target : targets) {
        inverser.remove(target, source_p);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#removeTarget(java.lang.Object)
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
