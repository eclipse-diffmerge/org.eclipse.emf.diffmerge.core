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
package org.eclipse.emf.diffmerge.structures;

/**
 * An implementation of IPropertyValue.
 * 
 * @param <V> the type of values of the property
 * @author Olivier Constant
 */
public class PropertyValue<V> implements IPropertyValue<V> {
  
  /** The unknown value */
  private static final PropertyValue<Object> UNKNOWN_VALUE =
      new PropertyValue<Object>();
  
  /** The false value */
  private static final PropertyValue<Boolean> FALSE_VALUE =
      new PropertyValue<Boolean>(Boolean.FALSE);
  
  /** The true value */
  private static final PropertyValue<Boolean> TRUE_VALUE =
      new PropertyValue<Boolean>(Boolean.TRUE);
  
  
  /** Whether the actual value is unknown */
  private final boolean _isUnknown;
  
  /** The potentially null value */
  private final V _value;
  
  
  /**
   * Constructor for unknown values
   */
  protected PropertyValue() {
    _isUnknown = true;
    _value = null;
  }
  
  /**
   * Constructor for known values
   * @param value_p the potentially null value of the property
   */
  public PropertyValue(V value_p) {
    _isUnknown = false;
    _value = value_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#cannotBe(java.lang.Object)
   */
  public boolean cannotBe(V value_p) {
    return isKnown() && !is(value_p);
  }
  
  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj_p) {
    boolean result;
    if (!(obj_p instanceof IPropertyValue<?>)) {
      result = false;
    } else {
      IPropertyValue<?> peer = (IPropertyValue<?>)obj_p;
      if (isUnknown()) {
        result = peer.isUnknown();
      } else {
        V actualValue = getValue();
        Object peerValue = peer.getValue();
        if (actualValue == null) {
          result = peerValue == null;
        } else {
          result = actualValue.equals(peerValue);
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#getValue()
   */
  public V getValue() {
    return _value;
  }
  
  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return 13 + (_value == null? 0: _value.hashCode());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#is(java.lang.Object)
   */
  public boolean is(V value_p) {
    boolean result = false;
    if (isKnown()) {
      V actualValue = getValue();
      if (actualValue == null)
        result = value_p == null;
      else
        result = actualValue.equals(value_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#isKnown()
   */
  public boolean isKnown() {
    return !isUnknown();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#isUnknown()
   */
  public boolean isUnknown() {
    return _isUnknown;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.IPropertyValue#mayBe(java.lang.Object)
   */
  public boolean mayBe(V value_p) {
    return isUnknown() || is(value_p);
  }
  
  /**
   * Return whether all the given structures have the given property
   * @param property_p a non-null property
   * @param structures_p a non-null, potentially empty set of structures
   *        (the empty set is assumed to have all properties)
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> all(
      Iterable<? extends IEqualityBasedStructure> structures_p, IProperty<Boolean> property_p) {
    IPropertyValue<Boolean> result = PropertyValue.trueValue();
    for (IEqualityBasedStructure structure : structures_p) {
      IPropertyValue<Boolean> partialResult = unknownValue();
      if (structure instanceof IStructureWithProperties)
        partialResult = ((IStructureWithProperties)structure).getProperty(property_p);
      result = PropertyValue.and(result, partialResult);
      if (result.is(Boolean.FALSE))
        break;
    }
    return result;
  }
  
  /**
   * Return the conjunction of the given boolean property values
   * @param p1_p a non-null property value
   * @param p2_p a non-null property value
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> and(IPropertyValue<Boolean> p1_p,
      IPropertyValue<Boolean> p2_p) {
    IPropertyValue<Boolean> result;
    if (p1_p.isUnknown() || p2_p.isUnknown())
      result = unknownValue();
    else
      result = p1_p.getValue().booleanValue() && p2_p.getValue().booleanValue()?
          trueValue(): falseValue();
    return result;
  }
  
  /**
   * Return the false boolean property value
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> falseValue() {
    return FALSE_VALUE;
  }
  
  /**
   * Return the negation of the given boolean property value
   * @param p1_p a non-null property value
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> not(IPropertyValue<Boolean> p1_p) {
    IPropertyValue<Boolean> result;
    if (p1_p.isUnknown())
      result = unknownValue();
    else
      result = p1_p.is(Boolean.TRUE)? falseValue(): trueValue();
    return result;
  }
  
  /**
   * Return the disjunction of the given boolean property values
   * @param p1_p a non-null property value
   * @param p2_p a non-null property value
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> or(IPropertyValue<Boolean> p1_p,
      IPropertyValue<Boolean> p2_p) {
    IPropertyValue<Boolean> result;
    if (p1_p.isUnknown())
      if (p2_p.isUnknown() || p2_p.is(Boolean.TRUE))
        result = p2_p; // U,U->U ; U,T->T
      else
        result = p1_p; // U,F->U
    else if (p2_p.isUnknown())
      if (p1_p.is(Boolean.TRUE))
        result = p1_p; // T,U->T
      else
        result = p2_p; // F,U->U
    else
      result = p1_p.getValue().booleanValue() || p2_p.getValue().booleanValue()?
          trueValue(): falseValue();
    return result;
  }
  
  /**
   * Return the false boolean property value
   * @return a non-null property value
   */
  public static IPropertyValue<Boolean> trueValue() {
    return TRUE_VALUE;
  }
  
  /**
   * Return an unknown property value
   * @param <V> the type of the possible values of the property
   * @return a non-null property value
   */
  @SuppressWarnings("unchecked")
  public static <V> IPropertyValue<V> unknownValue() {
    return (IPropertyValue<V>)UNKNOWN_VALUE;
  }
  
  /**
   * Return a boolean property value for the given boolean value
   * @param value_p the boolean value
   * @return a non-null property value which is trueValue() or falseValue()
   */
  public static IPropertyValue<Boolean> valueOf(boolean value_p) {
    return value_p? trueValue(): falseValue();
  }
  
}
