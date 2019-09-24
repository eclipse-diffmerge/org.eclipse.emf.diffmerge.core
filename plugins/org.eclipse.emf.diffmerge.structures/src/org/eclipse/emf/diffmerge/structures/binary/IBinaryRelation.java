/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.binary;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityBasedStructure;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.IStructureWithProperties;


/**
 * A binary relation from type T to type U.
 * It associates source (domain) elements with target (codomain) elements.
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public interface IBinaryRelation<T, U> extends IEqualityBasedStructure {
  
  /**
   * Return the target elements which are mapped to the given source element.
   * Postcondition: !isEmpty() || result.isEmpty()
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable set which is not guaranteed
   *         to remain in sync with the relation
   */
  Collection<U> get(T element_p);
  
  /**
   * Return whether the relation is empty, i.e., it is made of zero couple
   */
  boolean isEmpty();
  
  /**
   * Return whether the given couple is present in this relation.
   * Class invariant: maps(e1, e2) == get(e1).contains(e2)
   * @param source_p a non-null element
   * @param target_p a non-null element which is allowed to be source_p
   */
  boolean maps(T source_p, U target_p);
  
  
  /**
   * A modifiable binary relation.
   * The order in which couples are introduced is assumed to be preserved (add/get consistency).
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface Editable<T, U> extends IBinaryRelation<T, U> {
    /**
     * Clear the set of couples of this relation
     */
    void clear();
    
    /**
     * Add the given couple to the relation
     * @param source_p a non-null element
     * @param target_p a non-null element which may or not differ from source_p
     * @return whether the couple was not present already so the operation has an actual effect
     */
    boolean add(T source_p, U target_p);
    
    /**
     * Add to the relation all the couples obtained by mapping the given
     * source element to the given target elements
     * @param source_p a non-null element
     * @param targets_p a non-null, potentially empty set (a collection without duplicates)
     * @return whether the operation has an actual effect
     */
    boolean addAll(T source_p, Collection<? extends U> targets_p);
    
    /**
     * Refined. When iterating the returned collection, elements are returned in the
     * order in which couples were introduced (add/get consistency).
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(Object)
     */
    Collection<U> get(T element_p);
    
    /**
     * Remove the given couple from the relation if present
     * @param source_p a non-null element
     * @param target_p a non-null element which may or not differ from source_p
     * @return whether the couple was present so the operation had an actual effect
     */
    boolean remove(T source_p, U target_p);
  }
  
  
  /**
   * A binary relation in types (T, U) which supports inverse navigation.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface Invertible<T, U> extends IBinaryRelation<T, U> {
    /**
     * Return the source elements which are mapped to the given target element
     * @param element_p a non-null element
     * @return a non-null, potentially empty unmodifiable set which is not guaranteed
     *         to be kept in sync with the relation
     */
    Collection<T> getInverse(U element_p);
  }
  
  
  /**
   * A binary relation whose domain and codomain are explicitly defined as
   * runtime types.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface RuntimeTyped<T, U> extends IBinaryRelation<T, U> {
    /**
     * Return the type of the source (domain) elements
     * @return a non-null Java class
     */
    Class<T> getSourceType();
    
    /**
     * Return the type of the target (codomain)  elements
     * @return a non-null Java class
     */
    Class<U> getTargetType();
  }
  
  
  /**
   * A binary relation in types (T, U) with explicit properties and related
   * convenience methods.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface WithProperties<T, U> extends IBinaryRelation<T, U>,
  IStructureWithProperties {
    /**
     * Return the value of the corresponding property
     * @see IBinaryRelation.WithProperties#propertyIsFunctional()
     * @return a non-null property value
     */
    IPropertyValue<Boolean> isFunctional();
    
    /**
     * Return the value of the corresponding property
     * @see IBinaryRelation.WithProperties#propertyIsInjective()
     * @return a non-null property value
     */
    IPropertyValue<Boolean> isInjective();
    
    /**
     * Return the value of the corresponding property
     * @see IBinaryRelation.WithProperties#propertyIsOneToOne()
     * @return a non-null property value
     */
    IPropertyValue<Boolean> isOneToOne();
    
    /**
     * Whether the relation is functional, i.e., a source element may not
     * be mapped to more than one target element
     * @return a non-null property
     */
    IProperty<Boolean> propertyIsFunctional();
    
    /**
     * Whether the relation is injective, i.e., a target element may not
     * be mapped to more than one source element
     * @return a non-null property
     */
    IProperty<Boolean> propertyIsInjective();
    
    /**
     * Whether the relation is one-to-one (functional and injective), i.e.,
     * a given source or target element is mapped to zero or one element.
     * @return a non-null property
     */
    IProperty<Boolean> propertyIsOneToOne();
  }
  
}
