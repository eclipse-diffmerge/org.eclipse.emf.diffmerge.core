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
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;


/**
 * An endorelation over type T.
 * It has the ability to compute transitive closures.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface IEndorelation<T> extends IBinaryRelation<T, T> {
  
  /**
   * Compute the transitive closure of this relation starting on the given element,
   * encompassing cycles if there are any
   * @param element_p a non-null element
   * @return a non-null, unmodifiable set of elements excluding element_p
   *         whose order is that of a breadth-first exploration of the relation graph
   */
  List<T> getTransitiveClosure(T element_p);
  
  /**
   * Compute the transitive closure of this relation starting on the given elements,
   * encompassing cycles if there are any
   * @param elements_p a non-null, potentially empty collection
   * @return a non-null, unmodifiable set of elements excluding elements_p
   *         whose order is that of a breadth-first exploration of the relation graph
   */
  List<T> getTransitiveClosure(Collection<? extends T> elements_p);
  
  
  /**
   * An endorelation which supports inverse navigation.
   * 
   * @param <T> the type of the elements
   */
  public interface Invertible<T> extends IEndorelation<T>,
  IBinaryRelation.Invertible<T, T> {
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(java.lang.Object)
     */
    Collection<T> getInverse(T element_p);
  }
  
  
  /**
   * An endorelation whose domain is explicitly defined as a runtime type.
   * 
   * @param <T> the type of the elements
   */
  public interface RuntimeTyped<T> extends IEndorelation<T>,
  IBinaryRelation.RuntimeTyped<T, T> {
    // Class invariant: getSourceType() == getTargetType()
  }
  
  
  /**
   * An endorelation which explicit properties and related convenience methods.
   * 
   * @param <T> the type of the elements
   */
  public interface WithProperties<T> extends IEndorelation<T>,
  IBinaryRelation.WithProperties<T, T> {
    /**
     * Return the value of the corresponding property
     * @see IEndorelation.WithProperties#propertyIsIrreflexive()
     * @return a non-null property value
     */
    IPropertyValue<Boolean> isIrreflexive();
    
    /**
     * Return the value of the corresponding property
     * @see IEndorelation.WithProperties#propertyIsWithoutCycles()
     * @return a non-null property value
     */
    IPropertyValue<Boolean> isWithoutCycles();
    
    /**
     * Whether the endorelation is irreflexive, i.e., an element cannot belong to
     * its targets
     * Class invariant: isIrreflexive() || hasCycles()
     * @return a non-null property
     */
    IProperty<Boolean> propertyIsIrreflexive();
    
    /**
     * Whether the endorelation has no cycles, i.e., its transitive closure
     * is antisymmetric
     * @return a non-null property
     */
    IProperty<Boolean> propertyIsWithoutCycles();
  }
  
}
