/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * A simple implementation of a modifiable finite endorelation.
 * The default equality tester is by reference.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class EditableEndorelation<T> extends AbstractEndorelation<T>
implements IRangedEndorelation.Editable<T>, IIterableEndorelation.WithProperties<T> {
  
  /** The non-null state of the endorelation defined as an editable binary relation */
  private final IRangedBinaryRelation.Editable<T, T> _contents;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public EditableEndorelation() {
    this((IEqualityTester)null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public EditableEndorelation(IEqualityTester tester_p) {
    super(tester_p);
    _contents = newContents(tester_p);
  }
  
  /**
   * Constructor
   * @param state_p a non-null editable binary relation to use as internal state
   */
  public EditableEndorelation(IRangedBinaryRelation.Editable<T, T> state_p) {
    super(state_p.getEqualityTester());
    _contents = state_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#add(java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, T target_p) {
    return getContents().add(source_p, target_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection)
   */
  public boolean addAll(T source_p, Collection<? extends T> targets_p) {
    return getContents().addAll(source_p, targets_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#clear()
   */
  public void clear() {
    getContents().clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   */
  public Collection<T> get(T element_p) {
    return getContents().get(element_p);
  }
  
  /**
   * Return the internal state of the endorelation defined as an editable binary relation
   * @return a non-null binary relation
   */
  protected IRangedBinaryRelation.Editable<T, T> getContents() {
    return _contents;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRangedEndorelation#getElements()
   */
  public Collection<T> getElements() {
    Set<T> result = new FHashSet<T>(getEqualityTester());
    result.addAll(getContents().getSources());
    result.addAll(getContents().getTargets());
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#getMaximalElements()
   */
  public IPropertyValue<Collection<T>> getMaximalElements() {
    Collection<T> sources = getSources();
    Collection<T> targets = getTargets();
    Collection<T> result = new FHashSet<T>(targets, getEqualityTester());
    result.removeAll(sources);
    return new PropertyValue<Collection<T>>(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#getMinimalElements()
   */
  public IPropertyValue<Collection<T>> getMinimalElements() {
    Collection<T> sources = getSources();
    Collection<T> targets = getTargets();
    Collection<T> result = new FHashSet<T>(sources, getEqualityTester());
    result.removeAll(targets);
    return new PropertyValue<Collection<T>>(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getSources()
   */
  public Collection<T> getSources() {
    return getContents().getSources();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getTargets()
   */
  public Collection<T> getTargets() {
    return getContents().getTargets();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation#iterator()
   */
  public Iterator<T> iterator() {
    return getElements().iterator();
  }
  
  /**
   * Return a new internal state for the endorelation
   * @param tester_p a potentially null equality tester for comparing elements
   * @return a non-null binary relation
   */
  protected IRangedBinaryRelation.Editable<T, T> newContents(IEqualityTester tester_p) {
    return new HashBinaryRelation<T, T>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#propertyMaximalElements()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public IProperty<Collection<T>> propertyMaximalElements() {
    return (IProperty)AbstractIterableEndorelation.PROPERTY_MAXIMAL_ELEMENTS;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation.WithProperties#propertyMinimalElements()
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public IProperty<Collection<T>> propertyMinimalElements() {
    return (IProperty)AbstractIterableEndorelation.PROPERTY_MINIMAL_ELEMENTS;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, T target_p) {
    return getContents().remove(source_p, target_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRangedEndorelation.Editable#removeElement(java.lang.Object)
   */
  public boolean removeElement(T element_p) {
    boolean sourcesModified = removeSource(element_p);
    boolean targetsModified = removeTarget(element_p);
    return sourcesModified || targetsModified;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeSource(java.lang.Object)
   */
  public boolean removeSource(T source_p) {
    return getContents().removeSource(source_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation.Editable#removeTarget(java.lang.Object)
   */
  public boolean removeTarget(T target_p) {
    return getContents().removeTarget(target_p);
  }
  
}
