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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence#getValueMatch <em>Value Match</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEReferenceValuePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;E&gt;"
 * @generated
 */
public interface EReferenceValuePresence<E, A, R>
    extends EValuePresence<E, A, R>, IReferenceValuePresence<E> {

  /**
   * Returns the value of the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Match</em>' reference.
   * @see #setValueMatch(EMatch)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEReferenceValuePresence_ValueMatch()
   * @model
   * @generated
   */
  EMatch<E, A, R> getValueMatch();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence#getValueMatch <em>Value Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value Match</em>' reference.
   * @see #getValueMatch()
   * @generated
   */
  void setValueMatch(EMatch<E, A, R> value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  R getFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model referenceRequired="true"
   * @generated
   */
  void setReference(R reference);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model valueRequired="true"
   * @generated
   */
  void setValue(E value);
  // Nothing needed
} // EReferenceValuePresence
