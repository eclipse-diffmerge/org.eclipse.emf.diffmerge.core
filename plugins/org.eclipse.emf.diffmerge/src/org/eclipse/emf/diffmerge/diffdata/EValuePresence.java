/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EValue Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence#isOrder <em>Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEValuePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence org.eclipse.emf.diffmerge.diffdata.IValuePresence"
 * @generated
 */
public interface EValuePresence extends EElementRelativePresence,
    IValuePresence {
  /**
   * Returns the value of the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' reference.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEValuePresence_Feature()
   * @model required="true" changeable="false"
   * @generated
   */
  EStructuralFeature getFeature();

  /**
   * Returns the value of the '<em><b>Order</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Order</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Order</em>' attribute.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEValuePresence_Order()
   * @model default="false" required="true" changeable="false"
   * @generated
   */
  boolean isOrder();

} // EValuePresence
