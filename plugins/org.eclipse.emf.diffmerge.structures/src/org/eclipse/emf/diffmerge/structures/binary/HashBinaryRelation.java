/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import static org.eclipse.emf.diffmerge.structures.Relations.rangedIsFunctional;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * A simple implementation of a modifiable finite binary relation whose
 * (source, target) couples are defined explicitly.
 * It is based on a map of collections conforming to the IEqualityTester.
 * The default equality tester is by reference.
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public class HashBinaryRelation<T, U> extends AbstractBinaryRelation<T, U>
implements IRangedBinaryRelation.Editable<T, U> {
  
  /** The data structure that encodes the relation,
   *  non-null unless getContents() is overridden */
  private final EMap<T, Collection<U>> _contents;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public HashBinaryRelation() {
    this(null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public HashBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _contents = newContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#add(java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, U target_p) {
    assert source_p != null && target_p != null;
    boolean result = false;
    Collection<U> values = getContents().get(source_p);
    if (values == null) {
      values = newTargetCollection();
      getContents().put(source_p, values);
    }
    result = values.add(target_p); // OK because we have a Set
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection)
   */
  public boolean addAll(T source_p, Collection<? extends U> targets_p) {
    assert source_p != null && targets_p != null;
    boolean result = false;
    if (!targets_p.isEmpty()) {
      Collection<U> values = getContents().get(source_p);
      if (values == null) {
        values = newTargetCollection();
        getContents().put(source_p, values);
      }
      result = values.addAll(targets_p); // OK because we have a Set
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#clear()
   */
  public void clear() {
    getContents().clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(Object)
   */
  public Collection<U> get(T element_p) {
    assert element_p != null;
    Collection<U> result;
    Collection<U> values = getContents().get(element_p);
    if (values == null)
      result = Collections.emptyList();
    else
      result = Collections.unmodifiableCollection(values);
    return result;
  }
  
  /**
   * Return the low-level data structure that encodes the relation
   * @return a non-null object
   */
  protected EMap<T, Collection<U>> getContents() {
    return _contents;
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
    Set<U> result = new FHashSet<U>(getEqualityTester());
    for (Collection<U> valueList : getContents().values()) {
      result.addAll(valueList);
    }
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return getContents().isEmpty();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isFunctional()
   */
  @Override
  public IPropertyValue<Boolean> isFunctional() {
    boolean result = rangedIsFunctional(this);
    return PropertyValue.valueOf(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isInjective()
   */
  @Override
  public IPropertyValue<Boolean> isInjective() {
    Set<U> targets = new FHashSet<U>(getEqualityTester());
    for (Collection<U> valueList : getContents().values()) {
      for (U value : valueList) {
        boolean added = targets.add(value);
        if (!added)
          return PropertyValue.falseValue();
      }
    }
    return PropertyValue.trueValue();
  }
  
  /**
   * Create and return the low-level data structure of the relation
   * @return an object that is non-null unless getContents() is overridden
   */
  protected EMap<T, Collection<U>> newContents() {
    return new FHashMap<T, Collection<U>>(getEqualityTester());
  }
  
  /**
   * Create and return a new collection for storing target elements
   * @return a non-null, empty, modifiable list
   */
  protected Collection<U> newTargetCollection() {
    return new FHashSet<U>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p) {
    assert source_p != null && target_p != null;
    boolean result = false;
    Collection<U> values = getContents().get(source_p);
    if (values != null) {
      result = values.remove(target_p);
      if (values.isEmpty())
        getContents().removeKey(source_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeSource(java.lang.Object)
   */
  public boolean removeSource(T source_p) {
    assert source_p != null;
    Collection<U> values = getContents().removeKey(source_p);
    boolean result = values != null;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeTarget(java.lang.Object)
   */
  public boolean removeTarget(U target_p) {
    boolean result = false;
    Collection<T> sources = new FArrayList<T>(getSources(), getEqualityTester());
    for (T source : sources) {
      boolean justRemoved = remove(source, target_p);
      result = result || justRemoved;
    }
    return result;
  }
  
}
