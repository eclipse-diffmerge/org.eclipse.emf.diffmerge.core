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
package org.eclipse.emf.diffmerge.pojo.jdiffdata;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EValue Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage#getJValuePresence()
 * @model interface="true" abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface JValuePresence<E extends Object>
    extends GValuePresence<E, Object, Object>, JElementRelativePresence<E> {
  /**
   * Returns the value of the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' attribute.
   * @see #setFeature(Object)
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage#getJValuePresence_Feature()
   * @model required="true"
   * @generated
   */
  Object getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence#getFeature <em>Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' attribute.
   * @see #getFeature()
   * @generated
   */
  void setFeature(Object value);

} // GValuePresence
