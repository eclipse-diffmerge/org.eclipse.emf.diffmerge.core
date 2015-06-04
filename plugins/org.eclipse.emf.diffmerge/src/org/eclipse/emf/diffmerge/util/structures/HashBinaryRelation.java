/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.util.structures;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * A simple implementation of a modifiable finitary binary relation based on
 * a HashMap of ArrayLists conforming to the IEqualityTester.
 * The default equality tester is by reference.
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public class HashBinaryRelation<T, U> extends AbstractBinaryRelation<T, U>
implements IRangedBinaryRelation.Editable<T, U> {
  
  /** The non-null internal encoding of the relation */
  protected final FHashMap<T, List<U>> _contents;
  
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public HashBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
    _contents = new FHashMap<T, List<U>>(getEqualityTester());
  }
  
  /**
   * Constructor
   */
  public HashBinaryRelation() {
    this(null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation.Editable#add(java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, U target_p) {
    assert source_p != null && target_p != null;
    boolean result = false;
    List<U> values = _contents.get(source_p);
    if (values == null) {
      values = new FArrayList<U>(getEqualityTester());
      _contents.put(source_p, values);
    }
    if (!values.contains(target_p))
      result = values.add(target_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation.Editable#clear()
   */
  public void clear() {
    _contents.clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation#get(Object)
   */
  public List<U> get(T element_p) {
    assert element_p != null;
    List<U> result;
    List<U> values = _contents.get(element_p);
    if (values == null)
      result = Collections.emptyList();
    else
      result = Collections.unmodifiableList(values);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IRangedBinaryRelation#isEmpty()
   */
  public boolean isEmpty() {
    return _contents.isEmpty();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p) {
    assert source_p != null && target_p != null;
    boolean result = false;
    List<U> values = _contents.get(source_p);
    if (values != null) {
      result = values.remove(target_p);
      if (values.isEmpty())
        _contents.removeKey(source_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IRangedBinaryRelation#getSources()
   */
  public Collection<T> getSources() {
    return Collections.unmodifiableSet(_contents.keySet());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IRangedBinaryRelation#getTargets()
   */
  public Collection<U> getTargets() {
    Set<U> result = new FHashSet<U>(getEqualityTester());
    for (List<U> valueList : _contents.values()) {
      result.addAll(valueList);
    }
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IRangedBinaryRelation.Editable#removeSource(java.lang.Object)
   */
  public boolean removeSource(T source_p) {
    assert source_p != null;
    List<U> values = _contents.removeKey(source_p);
    boolean result = values != null;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.structures.IRangedBinaryRelation.Editable#removeTarget(java.lang.Object)
   */
  public boolean removeTarget(U target_p) {
    boolean result = false;
    Collection<T> sources = new FArrayList<T>(getSources(), getEqualityTester());
    for (T source : sources) {
      result = result || remove(source, target_p);
    }
    return result;
  }
  
}
