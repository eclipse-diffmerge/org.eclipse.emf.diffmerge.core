/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.structures.common.FLinkedList;
import org.eclipse.emf.diffmerge.structures.endo.IEndorelation;
import org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation;
import org.eclipse.emf.diffmerge.structures.endo.IRecursivelyDefinedEndorelation;


/**
 * A utility class related to relations.
 * 
 * @author Olivier Constant
 */
public final class Relations {
  
  /**
   * Copy the contents of the given ranged binary relation into the given
   * editable binary relation
   * @param sourceRelation_p a non-null ranged binary relation
   * @param targetRelation_p a non-null editable binary relation
   */
  public static <T, U> void binaryCopyInto(
      IRangedBinaryRelation<T, U> sourceRelation_p,
      IBinaryRelation.Editable<T, U> targetRelation_p) {
    for (T source : sourceRelation_p.getSources()) {
      Collection<U> targets = sourceRelation_p.get(source);
      targetRelation_p.addAll(source, targets);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.WithProperties#getProperty(IProperty)
   */
  @SuppressWarnings("unchecked")
  public static <V> IPropertyValue<V> binaryGetProperty(
      IBinaryRelation<?,?> relation_p, IProperty<V> property_p) {
    IPropertyValue<V> result = PropertyValue.unknownValue();
    if (relation_p instanceof IBinaryRelation.WithProperties<?,?>) {
      // ** Binary relation
      IBinaryRelation.WithProperties<?,?> binary =
          (IBinaryRelation.WithProperties<?,?>)relation_p;
      if (property_p == binary.propertyIsFunctional()) {
        // Functional
        result = (IPropertyValue<V>)binary.isFunctional();
      } else if (property_p == binary.propertyIsInjective()) {
        // Injective
        result = (IPropertyValue<V>)binary.isInjective();
      } else if (property_p == binary.propertyIsOneToOne()) {
        // One-to-one
        result = (IPropertyValue<V>)binary.isOneToOne();
      } else if (relation_p instanceof IEndorelation.WithProperties<?>) {
        // ** Endorelation
        IEndorelation.WithProperties<?> endo =
            (IEndorelation.WithProperties<?>)relation_p;
        if (property_p == endo.propertyIsIrreflexive()) {
          // Irreflexive
          result = (IPropertyValue<V>)endo.isIrreflexive();
        } else if (property_p == endo.propertyIsWithoutCycles()) {
          // Without cycles
          result = (IPropertyValue<V>)endo.isWithoutCycles();
        } else if (relation_p instanceof IIterableEndorelation.WithProperties<?>) {
          // ** Iterable endorelation
          IIterableEndorelation.WithProperties<?> iterable =
              (IIterableEndorelation.WithProperties<?>)relation_p;
          if (property_p == iterable.propertyMaximalElements()) {
            // Maximal elements
            result = (IPropertyValue<V>)iterable.getMaximalElements();
          } else if (property_p == iterable.propertyMinimalElements()) {
            // Minimal elements
            result = (IPropertyValue<V>)iterable.getMinimalElements();
          } else if (relation_p instanceof IRecursivelyDefinedEndorelation.WithProperties<?>) {
            // ** Recursively-defined endorelation
            IRecursivelyDefinedEndorelation.WithProperties<?> recursive =
                (IRecursivelyDefinedEndorelation.WithProperties<?>)relation_p;
            // Depth
            if (property_p == recursive.propertyDepth())
              result = (IPropertyValue<V>)recursive.getDepth();
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return the index of the first element in the given list which is mapped to
   * the given element by the given endorelation, or the index of the last+1 element
   * if none.
   * This algorithm solely relies on IEndorelation#get(Object).
   * @param endorelation_p a non-null endorelation
   * @param element_p a non-null element
   * @param elements_p a non-null list of elements
   * @return the min of indexes or elements_p.size() if there exists no mapped element
   *         belonging to elements_p
   */
  protected static <T> int endoMinIndexOfMappingElements(IEndorelation<T> endorelation_p,
      T element_p, List<T> elements_p) {
    final int size = elements_p.size();
    int result = size;
    int currentIndex = 0;
    Iterator<T> it = elements_p.iterator();
    while (result == size && it.hasNext()) {
      T current = it.next();
      Collection<T> mapped = endorelation_p.get(current);
      if (mapped.contains(element_p))
        result = currentIndex;
      currentIndex++;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation#getTransitiveClosure(Object)
   * This algorithm solely relies on IEndorelation#get(Object).
   */
  public static <T> List<T> endoTransitiveClosure(IEndorelation<T> endorelation_p,
      T element_p) {
    return endoTransitiveClosure(endorelation_p, Collections.singleton(element_p));
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation#getTransitiveClosure(Collection)
   * This algorithm solely relies on IEndorelation#get(Object).
   */
  public static <T> List<T> endoTransitiveClosure(IEndorelation<T> endorelation_p,
      Collection<? extends T> elements_p) {
    // Implementation is iterative, not recursive, for scalability reasons.
    // We use LinkedLists instead of HashSets because we need to preserve a
    // partial order even though it has a cost in performance on contains(Object)
    IEqualityTester tester = endorelation_p.getEqualityTester();
    List<T> result = new FLinkedList<T>(tester);
    List<T> toExplore = new FLinkedList<T>(tester);
    toExplore.addAll(elements_p);
    while (!toExplore.isEmpty()) {
      T current = toExplore.get(0);
      toExplore.remove(current);
      if (!result.contains(current)) {
        int index = endoMinIndexOfMappingElements(endorelation_p, current, result);
        result.add(index, current);
        Collection<T> targets = endorelation_p.get(current);
        toExplore.addAll(targets);
      }
    }
    result.removeAll(elements_p);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Copy the contents of the given ranged qualified binary relation into
   * the given qualified editable binary relation
   * @param sourceRelation_p a non-null ranged, qualified binary relation
   * @param targetRelation_p a non-null qualified, editable binary relation
   */
  public static <T, U, Q> void qualifiedCopyInto(
      IRangedQBinaryRelation<T, U, Q> sourceRelation_p,
      IQBinaryRelation.Editable<T, U, Q> targetRelation_p) {
    for (T source : sourceRelation_p.getSources()) {
      Map<Q, Collection<U>> sourceData = sourceRelation_p.getWithDetails(source);
      for (Map.Entry<Q, Collection<U>> entry : sourceData.entrySet()) {
        targetRelation_p.addAll(source, entry.getValue(), entry.getKey());
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   * This algorithm solely relies on IQBinaryRelation#getWithDetails(Object).
   */
  public static <T, U, Q> Collection<U> qualifiedGet(
      IQBinaryRelation<T, U, Q> relation_p, T element_p) {
    Collection<U> result = new FHashSet<U>(relation_p.getEqualityTester());
    Map<Q, Collection<U>> sourceData = relation_p.getWithDetails(element_p);
    for (Collection<U> targets : sourceData.values()) {
      result.addAll(targets);
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#get(java.lang.Object, java.lang.Object)
   * This algorithm solely relies on IQBinaryRelation#getWithDetails(Object).
   */
  public static <T, U, Q> Collection<U> qualifiedGet(
      IQBinaryRelation<T, U, Q> relation_p, T element_p, Q qualifier_p) {
    Collection<U> result = Collections.emptySet();
    Map<Q, Collection<U>> sourceData = relation_p.getWithDetails(element_p);
    if (sourceData != null) {
      Collection<U> targets = sourceData.get(qualifier_p);
      if (targets != null)
        result = Collections.unmodifiableCollection(targets);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object)
   * This algorithm solely relies on IQBinaryRelation#getWithDetails(Object).
   */
  public static <T, U, Q> Collection<Q> qualifiedGetQualifiers(
      IQBinaryRelation<T, U, Q> relation_p, T element_p) {
    List<Q> result = Collections.emptyList();
    Map<Q, Collection<U>> elementData = relation_p.getWithDetails(element_p);
    if (elementData != null) {
      result = new FLinkedList<Q>(elementData.keySet(), relation_p.getEqualityTester());
      result = Collections.unmodifiableList(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object, java.lang.Object)
   * This algorithm solely relies on IQBinaryRelation#getWithDetails(Object).
   */
  public static <T, U, Q> Collection<Q> qualifiedGetQualifiers(
      IQBinaryRelation<T, U, Q> relation_p, T source_p, U target_p) {
    List<Q> result = Collections.emptyList();
    Map<Q, Collection<U>> elementData = relation_p.getWithDetails(source_p);
    if (elementData != null) {
      result = new FLinkedList<Q>(relation_p.getEqualityTester());
      for (Map.Entry<Q, Collection<U>> entry : elementData.entrySet()) {
        if (entry.getValue().contains(target_p))
          result.add(entry.getKey());
      }
      result = Collections.unmodifiableList(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   * This algorithm solely relies on IQBinaryRelation#getWithDetails(Object).
   */
  public static <T, U, Q> boolean qualifiedMaps(IQBinaryRelation<T, U, Q> relation_p,
      T source_p, U target_p, Q qualifier_p) {
    boolean result = false;
    Map<Q, Collection<U>> sourceData = relation_p.getWithDetails(source_p);
    if (sourceData != null) {
      Collection<U> targets = sourceData.get(qualifier_p);
      if (targets != null)
        result = targets.contains(target_p);
    }
    return result;
  }
  
  /**
   * Return whether the given ranged relation is functional
   * @param relation_p a non-null relation
   */
  public static <T, U> boolean rangedIsFunctional(
      IRangedBinaryRelation<T, U> relation_p) {
    for (T source : relation_p.getSources()) {
      Collection<U> targets = relation_p.get(source);
      if (targets.size() > 1)
        return false;
    }
    return true;
  }
  
  /**
   * Return whether the given invertible ranged relation is injective
   * @param relation_p a non-null relation
   */
  public static <T, U> boolean rangedIsInjective(
      IRangedBinaryRelation.Invertible<T, U> relation_p) {
    for (U target : relation_p.getTargets()) {
      Collection<T> sources = relation_p.getInverse(target);
      if (sources.size() > 1)
        return false;
    }
    return true;
  }
  
}