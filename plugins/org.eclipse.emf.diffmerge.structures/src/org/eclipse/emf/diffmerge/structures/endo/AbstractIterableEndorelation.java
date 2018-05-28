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
package org.eclipse.emf.diffmerge.structures.endo;

import static org.eclipse.emf.diffmerge.structures.PropertyValue.unknownValue;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;


/**
 * A base implementation for iterable endorelations.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public abstract class AbstractIterableEndorelation<T>
extends AbstractEndorelation<T> implements IIterableEndorelation.Internal<T> {
  
  /** @see IIterableEndorelation.WithProperties#propertyMaximalElements() */
  public static final IProperty<Collection<?>> PROPERTY_MAXIMAL_ELEMENTS =
      new IProperty<Collection<?>>() {/**/};
  
  /** @see IIterableEndorelation.WithProperties#propertyMinimalElements() */
  public static final IProperty<Collection<?>> PROPERTY_MINIMAL_ELEMENTS =
      new IProperty<Collection<?>>() {/**/};
  
  /** The value of the 'maximal elements' property */
  protected IPropertyValue<Collection<T>> _maximalElements;
  
  
  /**
   * Constructor (enforces no particular constraint)
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractIterableEndorelation(IEqualityTester tester_p) {
    super(tester_p);
    _maximalElements = unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#getMaximalElements()
   */
  public IPropertyValue<Collection<T>> getMaximalElements() {
    return _maximalElements;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#getMinimalElements()
   */
  public IPropertyValue<Collection<T>> getMinimalElements() {
    return unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.Internal#notifyExplored(java.util.Iterator)
   */
  public void notifyExplored(Iterator<T> iterator_p) {
    if (iterator_p instanceof IGraphIterator<?>) {
      IGraphIterator<T> iterator = (IGraphIterator<T>)iterator_p;
      _maximalElements = new PropertyValue<Collection<T>>(iterator.maximalElements());
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#propertyMaximalElements()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public IProperty<Collection<T>> propertyMaximalElements() {
    return (IProperty)PROPERTY_MAXIMAL_ELEMENTS;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#propertyMinimalElements()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public IProperty<Collection<T>> propertyMinimalElements() {
    return (IProperty)PROPERTY_MINIMAL_ELEMENTS;
  }
  
}
