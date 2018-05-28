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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;


/**
 * An endorelation over a subset of type T that can be iterated upon.
 * It does not have to be finite, although this is a requirement for the
 * computation of most of the properties of the endorelation.
 * The provided iterator always returns every element exactly once if the
 * endorelation is finite and enough memory is available.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface IIterableEndorelation<T> extends IEndorelation<T>,
Iterable<T> {
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   * If element_p does not belong to this endorelation, then the results are undefined.
   */
  Collection<T> get(T element_p);
  
  /**
   * @see java.lang.Iterable#iterator()
   * The returned iterator iterates over all elements in the endorelation exactly
   * once in deterministic but otherwise unspecified order.
   * In the case where the relation is large or not guaranteed to be finite, care should
   * be taken w.r.t. memory consumption.
   * @return a non-null iterator
   */
  Iterator<T> iterator();
  
  
  /**
   * Internal behavior for iterable endorelations with properties.
   * 
   * @param <T> the type of the elements
   */
  public interface Internal<T> extends WithProperties<T> {
    /**
     * Notify that the given iterator has fully explored the endorelation
     * (which implies that it is finite)
     * @param iterator_p a non-null iterator such that !iterator_p.hasNext()
     */
    void notifyExplored(Iterator<T> iterator_p);
  }
  
  
  /**
   * An iterable endorelation which explicit properties and related convenience methods.
   * 
   * @param <T> the type of the elements
   */
  public interface WithProperties<T> extends IIterableEndorelation<T>,
  IEndorelation.WithProperties<T> {
    /**
     * Return the value of the corresponding property
     * @see IIterableEndorelation.WithProperties#propertyMaximalElements()
     * @return a non-null property value
     */
    IPropertyValue<Collection<T>> getMaximalElements();
    
    /**
     * Return the value of the corresponding property
     * @see IIterableEndorelation.WithProperties#propertyMinimalElements()
     * @return a non-null property value
     */
    IPropertyValue<Collection<T>> getMinimalElements();
    
    /**
     * The elements in the endorelation which are maximal, i.e.,
     * the non-null, potentially empty set of all elements which
     * are returned by the iterator and do not have any successor
     * @return a non-null property
     */
    IProperty<Collection<T>> propertyMaximalElements();
    
    /**
     * The elements in the endorelation which are minimal, i.e.,
     * the non-null, potentially empty set of all elements which
     * are returned by the iterator and do not have any predecessor
     * (inverse)
     * @return a non-null property
     */
    IProperty<Collection<T>> propertyMinimalElements();
  }
  
}
