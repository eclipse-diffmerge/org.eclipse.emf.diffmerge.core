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
 * A representation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEElementPresence()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface EElementPresence<E extends Object> extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence<E, Object, Object>,
    EElementRelativePresence<E> {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  EMatch<E> getOwnerMatch();

} // EElementPresence
