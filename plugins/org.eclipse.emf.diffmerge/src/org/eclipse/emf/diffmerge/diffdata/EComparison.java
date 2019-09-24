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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison()
 * @model
 * @generated
 */
public interface EComparison extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison<EObject, EAttribute, EReference> {
  /**
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  EMapping getMapping();

} // EComparison
