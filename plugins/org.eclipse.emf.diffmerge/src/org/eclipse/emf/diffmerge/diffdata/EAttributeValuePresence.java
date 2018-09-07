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

import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEAttributeValuePresence()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EValuePresence org.eclipse.emf.diffmerge.diffdata.IAttributeValuePresence"
 * @generated
 */
public interface EAttributeValuePresence
    extends EValuePresence, IAttributeValuePresence {

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEAttributeValuePresence_Value()
   * @model required="true"
   * @generated
   */
  Object getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(Object value);
  // Nothing needed
} // EAttributeValuePresence
