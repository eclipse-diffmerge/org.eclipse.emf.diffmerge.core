/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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

import static org.eclipse.emf.diffmerge.structures.Relations.endoTransitiveClosure;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.TypeRestrictedBinaryRelation;


/**
 * A wrapper around a binary relation that changes the types it operates on
 * so as to become an endorelation.
 * Target elements outside the specified type are filtered out.
 *
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class TypeAdaptedEndorelation<T> extends TypeRestrictedBinaryRelation<T, T>
implements IEndorelation.WithProperties<T>, IEndorelation.RuntimeTyped<T> {
  
  /**
   * Constructor
   * @param toAdapt_p the non-null binary relation to adapt
   * @param type_p the new non-null runtime type of the elements
   */
  public TypeAdaptedEndorelation(IBinaryRelation<?, ?> toAdapt_p, Class<T> type_p) {
    super(toAdapt_p, type_p, type_p);
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
    return trueForAdaptedOrUnknown(propertyIsIrreflexive());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#isWithoutCycles()
   */
  public IPropertyValue<Boolean> isWithoutCycles() {
    return trueForAdaptedOrUnknown(propertyIsWithoutCycles());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#propertyIsIrreflexive()
   */
  public IProperty<Boolean> propertyIsIrreflexive() {
    return AbstractEndorelation.PROPERTY_IS_IRREFLEXIVE;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.WithProperties#propertyIsWithoutCycles()
   */
  public IProperty<Boolean> propertyIsWithoutCycles() {
    return AbstractEndorelation.PROPERTY_IS_WITHOUT_CYCLES;
  }
  
}
