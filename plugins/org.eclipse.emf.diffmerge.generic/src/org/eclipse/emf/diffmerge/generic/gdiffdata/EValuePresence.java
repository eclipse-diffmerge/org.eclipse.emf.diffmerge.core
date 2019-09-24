/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EValue Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence#isOrder <em>Order</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEValuePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IValuePresence&lt;E&gt;"
 * @generated
 */
public interface EValuePresence<E, A, R>
    extends EElementRelativePresence<E, A, R>, IValuePresence<E> {
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
   * @see #setOrder(boolean)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEValuePresence_Order()
   * @model default="false" required="true"
   * @generated
   */
  boolean isOrder();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence#isOrder <em>Order</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Order</em>' attribute.
   * @see #isOrder()
   * @generated
   */
  void setOrder(boolean value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  Object getFeature();

} // EValuePresence
