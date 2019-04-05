/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.pojodiffdata;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEReferenceValuePresence()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface EReferenceValuePresence<E extends Object> extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence<E, Object, Object>,
    EValuePresence<E> {
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(Object)
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEReferenceValuePresence_Value()
   * @model required="true"
   * @generated
   */
  E getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(E value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EReferenceValuePresence<E> getSymmetrical();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EReferenceValuePresence<E> getSymmetricalOwnership();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EMatch<E> getValueMatch();

} // EReferenceValuePresence
