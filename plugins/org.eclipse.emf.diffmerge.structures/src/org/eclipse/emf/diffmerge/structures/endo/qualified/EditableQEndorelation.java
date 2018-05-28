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
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation;


/**
 * An implementation of modifiable finite, qualified endorelations.
 * @see org.eclipse.emf.diffmerge.structures.endo.qualified.IQEndorelation
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class EditableQEndorelation<T, Q> extends EditableEndorelation<T>
implements IRangedQEndorelation.Editable<T, Q> {
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public EditableQEndorelation() {
    super();
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public EditableQEndorelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor
   * @param state_p a non-null editable binary relation to use as internal state
   */
  public EditableQEndorelation(IRangedQBinaryRelation.Editable<T, T, Q> state_p) {
    super(state_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#add(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, T target_p, Q qualifier_p) {
    return getContents().add(source_p, target_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection, java.lang.Object)
   */
  public boolean addAll(T source_p, Collection<? extends T> targets_p, Q qualifier_p) {
    return getContents().addAll(source_p, targets_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#defaultQualifier()
   */
  public Q defaultQualifier() {
    return getContents().defaultQualifier();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#get(java.lang.Object, java.lang.Object)
   */
  public Collection<T> get(T element_p, Q qualifier_p) {
    return getContents().get(element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#getContents()
   */
  @Override
  @SuppressWarnings("unchecked")
  protected IRangedQBinaryRelation.Editable<T, T, Q> getContents() {
    return (IRangedQBinaryRelation.Editable<T, T, Q>)super.getContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation#getQualifiers()
   */
  public Collection<Q> getQualifiers() {
    return getContents().getQualifiers();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object)
   */
  public Collection<Q> getQualifiers(T element_p) {
    return getContents().getQualifiers(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object, java.lang.Object)
   */
  public Collection<Q> getQualifiers(T source_p, T target_p) {
    return getContents().getQualifiers(source_p, target_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getWithDetails(T element_p) {
    return getContents().getWithDetails(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean maps(T source_p, T target_p, Q qualifier_p) {
    return getContents().maps(source_p, target_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#newContents(org.eclipse.emf.diffmerge.structures.IEqualityTester)
   */
  @Override
  protected IRangedQBinaryRelation.Editable<T, T, Q> newContents(IEqualityTester tester_p) {
    return new HashQBinaryRelation<T, T, Q>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, T target_p, Q qualifier_p) {
    return getContents().remove(source_p, target_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation.Editable#removeQualifier(java.lang.Object)
   */
  public boolean removeQualifier(Q qualifier_p) {
    return getContents().removeQualifier(qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Editable#removeQualifier(java.lang.Object, java.lang.Object)
   */
  public boolean removeQualifier(T source_p, Q qualifier_p) {
    return getContents().removeQualifier(source_p, qualifier_p);
  }
  
}
