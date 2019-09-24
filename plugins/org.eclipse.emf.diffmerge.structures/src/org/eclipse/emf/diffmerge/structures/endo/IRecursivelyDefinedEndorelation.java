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

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;


/**
 * An endorelation that is defined as a set of "origin" elements of type T
 * and their mapped elements of the same type, recursively.
 * No particular assumption is made on the origin elements.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface IRecursivelyDefinedEndorelation<T> extends IIterableEndorelation<T> {
  
  /**
   * Return the origin elements of the recursive definition.
   * Note that this set is automatically a superset of the minimal elements.
   * @return a non-null, potentially empty, potentially unmodifiable set
   */
  Collection<T> getOrigins();
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation#iterator()
   * Refined.
   */
  IGraphIterator<T> iterator();
  
  /**
   * The returned iterator iterates over all elements in the endorelation exactly
   * once in the specified exploration order.
   * In the case where the relation is large or not guaranteed to be finite, care should
   * be taken w.r.t. memory consumption.
   * @param depthFirst_p whether the exploration is depth-first or breadth-first
   * @return a non-null iterator
   */
  IGraphIterator<T> iterator(boolean depthFirst_p);
  

  /**
   * An iterable endorelation which explicit properties and related convenience methods.
   * 
   * @param <T> the type of the elements
   */
  public interface WithProperties<T> extends IRecursivelyDefinedEndorelation<T>,
  IIterableEndorelation.WithProperties<T> {
    /**
     * Return the value of the corresponding property
     * @see IRecursivelyDefinedEndorelation.WithProperties#propertyDepth()
     * @return a non-null property value
     */
    IPropertyValue<Long> getDepth();
    
    /**
     * The "depth" of the endorelation, i.e., the length of the longest path from any of
     * the origin elements to any of the maximal elements, assuming the origin elements
     * are minimal.
     * @return a non-null property
     */
    IProperty<Collection<T>> propertyDepth();
  }
  
}
