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
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * An base implementation of modifiable finite, qualified binary relations whose
 * internal state is defined as a map.
 * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public abstract class AbstractMapQBinaryRelation<T, U, Q> extends AbstractQBinaryRelation<T, U, Q> {
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public AbstractMapQBinaryRelation() {
    this((IEqualityTester)null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public AbstractMapQBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor
   * @param initialContents_p a non-null ranged qualified binary relation defining the
   *        initial contents of this relation
   */
  public AbstractMapQBinaryRelation(
      IRangedQBinaryRelation<T, U, Q> initialContents_p) {
    super(initialContents_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#add(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, U target_p, Q qualifier_p) {
    Collection<U> targets = prepareAddition(source_p, qualifier_p);
    boolean result = targets.add(target_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection, java.lang.Object)
   */
  public boolean addAll(T source_p, Collection<? extends U> targets_p,
      Q qualifier_p) {
    Collection<U> targets = prepareAddition(source_p, qualifier_p);
    boolean result = targets.addAll(targets_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#clear()
   */
  public void clear() {
    getContents().clear();
  }
  
  /**
   * Return the low-level data structure that encodes the relation
   * @return a non-null object
   */
  protected abstract EMap<T, EMap<Q, Collection<U>>> getContents();
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation#getQualifiers()
   */
  public Collection<Q> getQualifiers() {
    Set<Q> result = new FHashSet<Q>(getEqualityTester());
    for (EMap<Q, Collection<U>> elementData : getContents().values()) {
      result.addAll(elementData.keySet());
    }
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getSources()
   */
  public Collection<T> getSources() {
    return Collections.unmodifiableSet(getContents().keySet());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getTargets()
   */
  public Collection<U> getTargets() {
    Collection<U> result = new FHashSet<U>(getEqualityTester());
    for (EMap<Q, Collection<U>> sourceData : getContents().values()) {
      for (Collection<U> targets : sourceData.values()) {
        result.addAll(targets);
      }
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<U>> getWithDetails(T element_p) {
    Map<Q, Collection<U>> result;
    EMap<Q, Collection<U>> sourceData = getContents().get(element_p);
    if (sourceData != null) {
      result = Collections.unmodifiableMap(sourceData.map());
    } else {
      result = Collections.emptyMap();
    }
    return result;
  }
  
  /**
   * Create and return a map for storing data about source elements
   * @return a non-null map
   */
  protected abstract EMap<Q, Collection<U>> newSourceData();
  
  /**
   * Create and return a new collection for storing target elements.
   * The collection is responsible for ensuring uniqueness of its elements.
   * @return a non-null, empty, modifiable list
   */
  protected abstract Collection<U> newTargetCollection();
  
  /**
   * Return the collection to store targets for the given element through
   * the given qualifier
   * @param source_p a non-null element
   * @param qualifier_p a non-null qualifier
   * @return a non-null, potentially empty, modifiable collection for storing targets
   */
  protected Collection<U> prepareAddition(T source_p, Q qualifier_p) {
    EMap<Q, Collection<U>> sourceData = getContents().get(source_p);
    if (sourceData == null) {
      sourceData = newSourceData();
      getContents().put(source_p, sourceData);
    }
    Collection<U> targets = sourceData.get(qualifier_p);
    if (targets == null) {
      targets = newTargetCollection();
      sourceData.put(qualifier_p, targets);
    }
    return targets;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p) {
    boolean result = false;
    EMap<Q, Collection<U>> sourceData = getContents().get(source_p);
    if (sourceData != null) {
      Iterator<Map.Entry<Q, Collection<U>>> entriesIterator =
          sourceData.entrySet().iterator();
      while (entriesIterator.hasNext()) {
        Map.Entry<Q, Collection<U>> entry = entriesIterator.next();
        Collection<U> targets = entry.getValue();
        boolean removed = targets.remove(target_p);
        result = result || removed;
        if (removed && targets.isEmpty())
          entriesIterator.remove();
      }
      if (sourceData.isEmpty())
        getContents().removeKey(source_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p, Q qualifier_p) {
    boolean result = false;
    EMap<Q, Collection<U>> sourceData = getContents().get(source_p);
    if (sourceData != null) {
      Collection<U> targets = sourceData.get(qualifier_p);
      if (targets != null) {
        result = targets.remove(target_p);
        if (result && targets.isEmpty()) {
          sourceData.removeKey(qualifier_p);
          if (sourceData.isEmpty())
            getContents().removeKey(source_p);
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation.Editable#removeQualifier(java.lang.Object)
   */
  public boolean removeQualifier(Q qualifier_p) {
    boolean result = false;
    Iterator<Map.Entry<T, EMap<Q, Collection<U>>>> elementIterator =
        getContents().entrySet().iterator();
    while (elementIterator.hasNext()) {
      Map.Entry<T, EMap<Q, Collection<U>>> elementEntry = elementIterator.next();
      EMap<Q, Collection<U>> elementData = elementEntry.getValue();
      Object removed = elementData.removeKey(qualifier_p);
      result = result || removed != null;
      if (elementData.isEmpty())
        elementIterator.remove();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#removeQualifier(java.lang.Object, java.lang.Object)
   */
  public boolean removeQualifier(T source_p, Q qualifier_p) {
    boolean result = false;
    EMap<Q, Collection<U>> elementData = getContents().get(source_p);
    if (elementData != null) {
      Object removed = elementData.removeKey(qualifier_p);
      result = result || removed != null;
      if (elementData.isEmpty())
        getContents().remove(source_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeSource(java.lang.Object)
   */
  public boolean removeSource(T source_p) {
    assert source_p != null;
    EMap<Q, Collection<U>> sourceData = getContents().removeKey(source_p);
    boolean result = sourceData != null;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeTarget(java.lang.Object)
   */
  public boolean removeTarget(U target_p) {
    boolean result = false;
    Iterator<Map.Entry<T, EMap<Q, Collection<U>>>> elementIterator =
        getContents().entrySet().iterator();
    while (elementIterator.hasNext()) {
      Map.Entry<T, EMap<Q, Collection<U>>> elementEntry = elementIterator.next();
      Iterator<Map.Entry<Q, Collection<U>>> qualifierIterator =
          elementEntry.getValue().entrySet().iterator();
      while (qualifierIterator.hasNext()) {
        Map.Entry<Q, Collection<U>> qualifierEntry = qualifierIterator.next();
        Collection<U> targets = qualifierEntry.getValue();
        boolean removed = targets.remove(target_p);
        result = result || removed;
        if (removed && targets.isEmpty())
          qualifierIterator.remove();
      }
      if (elementEntry.getValue().isEmpty())
        elementIterator.remove();
    }
    return result;
  }
  
}
