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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence()
 * @model
 * @generated
 */
public interface EReferenceValuePresence extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence<EObject, EAttribute, EReference>,
    EValuePresence {

  /**
   * Returns the value of the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' reference.
   * @see #setReference(EReference)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence_Reference()
   * @model required="true"
   * @generated
   */
  EReference getReference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(EReference value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' reference.
   * @see #setValue(EObject)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence_Value()
   * @model required="true"
   * @generated
   */
  EObject getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValue <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' reference.
   * @see #getValue()
   * @generated
   */
  void setValue(EObject value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EReferenceValuePresence getSymmetrical();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EReferenceValuePresence getSymmetricalOwnership();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EMatch getValueMatch();
  // Nothing needed
} // EReferenceValuePresence
