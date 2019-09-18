/*********************************************************************
 * Copyright (c) 2016-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo;

import static org.eclipse.emf.diffmerge.structures.PropertyValue.unknownValue;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * A base implementation of recursively-defined endorelations.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public abstract class AbstractRecursivelyDefinedEndorelation<T>
extends AbstractIterableEndorelation<T>
implements IRecursivelyDefinedEndorelation.WithProperties<T> {
  
  /** @see IRecursivelyDefinedEndorelation.WithProperties#propertyDepth() */
  public static final IProperty<Collection<?>> PROPERTY_DEPTH =
      new IProperty<Collection<?>>() {/**/};
  
  /** The non-null set of origin elements */
  protected final Collection<T> _origins;
  
  /** Whether elements may not have several inverse elements in the relation
   * and there cannot be cycles. If true, then the endorelation is assumed to be
   * injective and its transitive closure antisymmetric. */
  protected final boolean _noMultipleInverseOrCycles;
  
  /** The value of the 'depth' property */
  protected IPropertyValue<Long> _depth;
  
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractRecursivelyDefinedEndorelation(
      Collection<? extends T> origins_p, IEqualityTester tester_p) {
    this(origins_p, false, tester_p);
  }
  
  /**
   * Constructor
   * @param origins_p the non-null set of origin elements
   * @param noMultipleInverseOrCycles_p whether elements may not have several
   *          inverse elements in the relation and there cannot be cycles; this
   *          is used for optimization. True means that the endorelation is
   *          injective and its transitive closure is antisymmetric.
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  protected AbstractRecursivelyDefinedEndorelation(
      Collection<? extends T> origins_p, boolean noMultipleInverseOrCycles_p,
      IEqualityTester tester_p) {
    super(tester_p);
    _origins = new FHashSet<T>(origins_p, tester_p);
    _noMultipleInverseOrCycles = noMultipleInverseOrCycles_p;
    _depth = unknownValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation.WithProperties#getDepth()
   */
  public IPropertyValue<Long> getDepth() {
    return _depth;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation#getOrigins()
   */
  public Collection<T> getOrigins() {
    return Collections.unmodifiableCollection(_origins);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelation#isWithoutCycles()
   */
  @Override
  public IPropertyValue<Boolean> isWithoutCycles() {
    IPropertyValue<Boolean> result;
    if (_noMultipleInverseOrCycles)
      result = PropertyValue.trueValue();
    else
      result = super.isWithoutCycles();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isInjective()
   */
  @Override
  public IPropertyValue<Boolean> isInjective() {
    IPropertyValue<Boolean> result;
    if (_noMultipleInverseOrCycles)
      result = PropertyValue.trueValue();
    else
      result = super.isInjective();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation#iterator()
   * The iteration order is depth-first.
   */
  public IGraphIterator<T> iterator() {
    return new DepthFirstIterator<T>(this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation#iterator(boolean)
   */
  public IGraphIterator<T> iterator(boolean depthFirst_p) {
    return depthFirst_p? new DepthFirstIterator<T>(this):
      new BreadthFirstIterator<T>(this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractIterableEndorelation#notifyExplored(java.util.Iterator)
   */
  @Override
  public void notifyExplored(Iterator<T> iterator_p) {
    super.notifyExplored(iterator_p);
    if (iterator_p instanceof IGraphIterator<?>) {
      IGraphIterator<T> iterator = (IGraphIterator<T>)iterator_p;
      _depth = new PropertyValue<Long>(Long.valueOf(iterator.maxDepth()));
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation.WithProperties#propertyDepth()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public IProperty<Collection<T>> propertyDepth() {
    return (IProperty)PROPERTY_DEPTH;
  }
  
}
