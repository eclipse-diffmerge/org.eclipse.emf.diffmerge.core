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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping()
 * @model
 * @generated
 */
public interface EMapping extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping<EObject, EAttribute, EReference>,
    EComparisonElement {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  void crossReference(Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.Setting" elementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EList<EStructuralFeature.Setting> getCrossReferences(EObject element,
      Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model potentialElementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EMatch getMatchFor(Object potentialElement, Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" elementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EMatch map(EObject element, Role role);

} // EMapping
