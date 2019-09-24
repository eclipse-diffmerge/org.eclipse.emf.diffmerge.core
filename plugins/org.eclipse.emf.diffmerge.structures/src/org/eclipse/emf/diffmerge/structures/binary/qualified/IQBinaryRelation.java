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
package org.eclipse.emf.diffmerge.structures.binary.qualified;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;


/**
 * A binary relation which associates elements through a qualifier.
 * Two elements can be associated several times through different qualifiers,
 * but not through the same one.
 * It is actually a ternary relation whose third set is handled a bit differently
 * from the others (the APIs are not symmetric w.r.t. the three sets).
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface IQBinaryRelation<T, U, Q> extends IBinaryRelation<T, U> {
  
  /**
   * Return the default qualifier, which applies to elements that have
   * been mapped without a qualifier.
   * If null is returned, then add(T, U) will not be applicable
   * @return a potentially null object
   */
  Q defaultQualifier();
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   * Refined. Covers all qualifiers. The returned collection is still conceptually
   * a set, i.e., it contains no duplicates.
   */
  Collection<U> get(T element_p);
  
  /**
   * Return the set of target elements which are mapped to the given source element
   * with the given qualifier.
   * Postcondition: !isEmpty() || result.isEmpty()
   * @param element_p a non-null element
   * @param qualifier_p a non-null qualifier
   * @return a non-null, potentially empty, unmodifiable set which is not guaranteed
   *         to remain in sync with the relation
   */
  Collection<U> get(T element_p, Q qualifier_p);
  
  /**
   * Return the set of qualifiers that associate target elements (at least one)
   * to the given source element.
   * Class invariant: it is the set of all qualifiers returned by
   * getWithDetails(element_p).
   * @return a non-null, potentially empty, unmodifiable set
   */
  Collection<Q> getQualifiers(T element_p);
  
  /**
   * Return the set of qualifiers between the given source and target elements
   * @param source_p a non-null element
   * @param target_p a non-null element
   * @return a non-null, potentially empty, unmodifiable set
   */
  Collection<Q> getQualifiers(T source_p, U target_p);
  
  /**
   * Return all target elements which are mapped to the given source element with
   * the associated qualifiers (including null). The same target element can be present
   * in several of the map values.
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable set which is not guaranteed
   *         to remain in sync with the relation
   */
  Map<Q, Collection<U>> getWithDetails(T element_p);
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#maps(java.lang.Object, java.lang.Object)
   * Refined. Returns whether the given elements are mapped through any qualifier.
   */
  boolean maps(T source_p, U target_p);
  
  /**
   * Return whether the given couple is present in this relation.
   * Class invariant: maps(e1, e2) == get(e1).contains(e2)
   * @param source_p a non-null element
   * @param target_p a non-null element which is allowed to be source_p
   * @param qualifier_p a potentially null qualifier
   */
  boolean maps(T source_p, U target_p, Q qualifier_p);
  
  
  /**
   * A modifiable binary relation with qualifiers.
   * The order in which couples are introduced is assumed to be preserved (add/get consistency).
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   * @param <Q> the type of the qualifiers
   */
  public interface Editable<T, U, Q> extends IQBinaryRelation<T, U, Q>,
  IBinaryRelation.Editable<T, U> {
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#add(java.lang.Object, java.lang.Object)
     * Refined. The default qualifier is used, so it is equivalent to
     * add(source_p, target_p, defaultQualifier()).
     * @throws UnsupportedOperationException if defaultQualifier() returns null
     */
    boolean add(T source_p, U target_p);
    
    /**
     * Add the given couple to the relation through the given qualifier
     * @param source_p a non-null element
     * @param target_p a non-null element which may or not differ from source_p
     * @param qualifier_p a non-null qualifier
     * @return whether the operation has an actual effect
     */
    boolean add(T source_p, U target_p, Q qualifier_p);
    
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection)
     * Refined. The default qualifier is used, so it is equivalent to
     * addAll(source_p, targets_p, defaultQualifier()).
     */
    boolean addAll(T source_p, Collection<? extends U> targets_p);
    
    /**
     * Add to the relation all the couples obtained by mapping the given
     * source element to the given target elements through the given qualifier
     * @param source_p a non-null element
     * @param targets_p a non-null, potentially empty set (a collection without duplicates)
     * @param qualifier_p a non-null qualifier
     * @return whether the operation has an actual effect
     */
    boolean addAll(T source_p, Collection<? extends U> targets_p, Q qualifier_p);
    
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
     * Refined. Covers all qualifiers.
     */
    boolean remove(T source_p, U target_p);
    
    /**
     * Remove the given couple through the given qualifier from the relation, if present
     * @param source_p a non-null element
     * @param target_p a non-null element which may or not differ from source_p
     * @param qualifier_p a non-null qualifier
     * @return whether the operation had an actual effect
     */
    boolean remove(T source_p, U target_p, Q qualifier_p);
    
    /**
     * Remove from the relation all couples that map the given element to any target
     * element through the given qualifier
     * @param source_p a non-null element
     * @param qualifier_p a non-null qualifier
     * @return whether the operation had an actual effect
     */
    boolean removeQualifier(T source_p, Q qualifier_p);
  }
  
  
  /**
   * A qualified binary relation in types (T, U) which supports inverse navigation.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   * @param <Q> the type of the qualifiers
   */
  public interface Invertible<T, U, Q> extends IQBinaryRelation<T, U, Q>,
  IBinaryRelation.Invertible<T, U> {
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(java.lang.Object)
     * Refined. Covers all qualifiers.
     */
    Collection<T> getInverse(U element_p);
    
    /**
     * Return the source elements which are mapped to the given target element
     * through the given qualifier
     * @param element_p a non-null element
     * @param qualifier_p a non-null qualifier
     * @return a non-null, potentially empty unmodifiable set which is not guaranteed
     *         to be kept in sync with the relation
     */
    Collection<T> getInverse(U element_p, Q qualifier_p);
    
    /**
     * Return the set of qualifiers that associate source elements (at least one)
     * to the given target element
     * @return a non-null, potentially empty, unmodifiable set
     */
    Collection<Q> getInverseQualifiers(U element_p);
    
    /**
     * Return all source elements which are mapped to the given target element with
     * the associated non-null qualifiers.
     * @param element_p a non-null element
     * @return a non-null, potentially empty, unmodifiable set which is not guaranteed
     *         to remain in sync with the relation
     */
    Map<Q, Collection<T>> getInverseWithDetails(U element_p);
  }
  
}
