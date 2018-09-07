/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.qualified.HashInvertibleQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;


/**
 * An implementation of invertible modifiable, finite, qualified endorelations.
 * @see IRangedQEndorelation.Invertible
 *
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class EditableInvertibleQEndorelation<T, Q> extends EditableQEndorelation<T, Q>
implements IRangedQEndorelation.InvertibleEditable<T, Q> {
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public EditableInvertibleQEndorelation() {
    super();
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public EditableInvertibleQEndorelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor
   * @param state_p a non-null editable binary relation to use as internal state
   */
  public EditableInvertibleQEndorelation(
      IRangedQBinaryRelation.InvertibleEditable<T, T, Q> state_p) {
    super(state_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#getContents()
   */
  @Override
  protected IRangedQBinaryRelation.InvertibleEditable<T, T, Q> getContents() {
    return (IRangedQBinaryRelation.InvertibleEditable<T, T, Q>)super.getContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.Invertible#getInverse(java.lang.Object)
   */
  public Collection<T> getInverse(T element_p) {
    return getContents().getInverse(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverse(java.lang.Object, java.lang.Object)
   */
  public Collection<T> getInverse(T element_p, Q qualifier_p) {
    return getContents().getInverse(element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseQualifiers(java.lang.Object)
   */
  public Collection<Q> getInverseQualifiers(T element_p) {
    return getContents().getInverseQualifiers(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getInverseWithDetails(T element_p) {
    return getContents().getInverseWithDetails(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#newContents(org.eclipse.emf.diffmerge.structures.IEqualityTester)
   */
  @Override
  protected IRangedQBinaryRelation.InvertibleEditable<T, T, Q> newContents(
      IEqualityTester tester_p) {
    return new HashInvertibleQBinaryRelation<T, T, Q>(tester_p);
  }
  
}
