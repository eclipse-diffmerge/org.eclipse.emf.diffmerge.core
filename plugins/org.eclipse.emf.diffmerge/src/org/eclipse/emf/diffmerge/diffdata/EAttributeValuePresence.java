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

import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getAttribute <em>Attribute</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEAttributeValuePresence()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence&lt;org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EAttribute, org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.diffdata.IEditableModelScope&gt; org.eclipse.emf.diffmerge.diffdata.EValuePresence"
 * @generated
 */
public interface EAttributeValuePresence extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence<EObject, EAttribute, EReference, IEditableModelScope>,
    EValuePresence {

  /**
   * Returns the value of the '<em><b>Attribute</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' reference.
   * @see #setAttribute(EAttribute)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEAttributeValuePresence_Attribute()
   * @model required="true"
   * @generated
   */
  EAttribute getAttribute();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getAttribute <em>Attribute</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute</em>' reference.
   * @see #getAttribute()
   * @generated
   */
  void setAttribute(EAttribute value);
  // Nothing needed
} // EAttributeValuePresence
