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

import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Relative Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage#getJElementRelativePresence()
 * @model interface="true" abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface JElementRelativePresence<E extends Object> extends
    GElementRelativePresence<E, Object, Object>, JMergeableDifference<E> {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  JMatch<E> getElementMatch();

} // GElementRelativePresence
