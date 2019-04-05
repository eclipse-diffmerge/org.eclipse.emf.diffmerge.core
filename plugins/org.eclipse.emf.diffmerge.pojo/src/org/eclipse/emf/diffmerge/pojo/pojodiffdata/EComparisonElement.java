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
 * A representation of the model object '<em><b>EComparison Element</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEComparisonElement()
 * @model interface="true" abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface EComparisonElement<E extends Object> extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement<E, Object, Object> {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  EComparison<E> getComparison();

} // EComparisonElement
