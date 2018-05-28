/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
import static org.eclipse.emf.diffmerge.structures.Relations.endoTransitiveClosure;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation;


/**
 * A base implementation for endorelations.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public abstract class AbstractEndorelation<T> extends AbstractBinaryRelation<T, T>
implements IEndorelation.WithProperties<T> {
  
  /** @see IEndorelation.WithProperties#propertyIsIrreflexive() */
  public static final IProperty<Boolean> PROPERTY_IS_IRREFLEXIVE =
      new IProperty<Boolean>() {/**/};
  
  /** @see IEndorelation.WithProperties#propertyIsWithoutCycles() */
  public static final IProperty<Boolean> PROPERTY_IS_WITHOUT_CYCLES =
      new IProperty<Boolean>() {/**/};
      
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractEndorelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation#getTransitiveClosure(Object)
   */
  public List<T> getTransitiveClosure(T element_p) {
    return endoTransitiveClosure(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation#getTransitiveClosure(Collection)
   */
  public List<T> getTransitiveClosure(Collection<? extends T> elements_p) {
    return endoTransitiveClosure(this, elements_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#isIrreflexive()
   */
  public IPropertyValue<Boolean> isIrreflexive() {
    return unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#isWithoutCycles()
   */
  public IPropertyValue<Boolean> isWithoutCycles() {
    return unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#propertyIsIrreflexive()
   */
  public IProperty<Boolean> propertyIsIrreflexive() {
    return PROPERTY_IS_IRREFLEXIVE;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#propertyIsWithoutCycles()
   */
  public IProperty<Boolean> propertyIsWithoutCycles() {
    return PROPERTY_IS_WITHOUT_CYCLES;
  }
  
}
