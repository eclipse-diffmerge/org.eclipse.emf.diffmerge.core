/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.HashInvertibleBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;


/**
 * An extension of EditableEndorelation which is invertible.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class EditableInvertibleEndorelation<T> extends EditableEndorelation<T>
implements IRangedEndorelation.InvertibleEditable<T> {
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public EditableInvertibleEndorelation() {
    super();
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public EditableInvertibleEndorelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor
   * @param state_p a non-null editable binary relation to use as internal state
   */
  public EditableInvertibleEndorelation(IRangedBinaryRelation.InvertibleEditable<T, T> state_p) {
    super(state_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IEndorelation.Invertible#getInverse(java.lang.Object)
   */
  public Collection<T> getInverse(T element_p) {
    return getContents().getInverse(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#getContents()
   */
  @Override
  protected IRangedBinaryRelation.InvertibleEditable<T, T> getContents() {
    return (IRangedBinaryRelation.InvertibleEditable<T, T>)super.getContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.EditableEndorelation#newContents(org.eclipse.emf.diffmerge.structures.IEqualityTester)
   */
  @Override
  protected IRangedBinaryRelation.InvertibleEditable<T, T> newContents(
      IEqualityTester tester_p) {
    return new HashInvertibleBinaryRelation<T, T>(tester_p);
  }
  
}
