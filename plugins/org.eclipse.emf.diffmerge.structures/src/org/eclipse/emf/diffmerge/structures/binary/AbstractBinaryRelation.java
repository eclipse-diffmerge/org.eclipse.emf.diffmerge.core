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

import static org.eclipse.emf.diffmerge.structures.PropertyValue.unknownValue;
import static org.eclipse.emf.diffmerge.structures.Relations.binaryGetProperty;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;


/**
 * A base implementation for binary relations which provides a few services.
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public abstract class AbstractBinaryRelation<T, U>
implements IBinaryRelation.WithProperties<T, U> {
  
  /** @see IBinaryRelation.WithProperties#propertyIsFunctional() */
  public static final IProperty<Boolean> PROPERTY_IS_FUNCTIONAL =
      new IProperty<Boolean>() {/**/};
  
  /** @see IBinaryRelation.WithProperties#propertyIsInjective() */
  public static final IProperty<Boolean> PROPERTY_IS_INJECTIVE =
      new IProperty<Boolean>() {/**/};
  
  /** @see IBinaryRelation.WithProperties#propertyIsOneToOne() */
  public static final IProperty<Boolean> PROPERTY_IS_ONE_TO_ONE =
      new IProperty<Boolean>() {/**/};
  
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
   * @see org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure#getEqualityTester()
   */
  public IEqualityTester getEqualityTester() {
    return _tester;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IStructureWithProperties#getProperty(org.eclipse.emf.diffmerge.structures.IProperty)
   */
  public <V> IPropertyValue<V> getProperty(IProperty<V> property_p) {
    return binaryGetProperty(this, property_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#isEmpty()
   */
  public boolean isEmpty() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#isFunctional()
   */
  public IPropertyValue<Boolean> isFunctional() {
    return unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#isInjective()
   */
  public IPropertyValue<Boolean> isInjective() {
    return unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#isOneToOne()
   */
  public IPropertyValue<Boolean> isOneToOne() {
    IPropertyValue<Boolean> result = isFunctional();
    if (result.is(Boolean.TRUE))
      result = isInjective();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#maps(Object, Object)
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
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#propertyIsFunctional()
   */
  public IProperty<Boolean> propertyIsFunctional() {
    return PROPERTY_IS_FUNCTIONAL;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#propertyIsInjective()
   */
  public IProperty<Boolean> propertyIsInjective() {
    return PROPERTY_IS_INJECTIVE;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#propertyIsOneToOne()
   */
  public IProperty<Boolean> propertyIsOneToOne() {
    return PROPERTY_IS_ONE_TO_ONE;
  }
  
}
