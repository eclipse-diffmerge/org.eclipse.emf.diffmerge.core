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
import org.eclipse.emf.diffmerge.api.IMapping.Editable;
import org.eclipse.emf.diffmerge.api.IMatch;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EIdentified org.eclipse.emf.diffmerge.diffdata.IEditableMapping"
 * @generated
 */
public interface EMapping extends EIdentified, Editable {
  /**
   * Returns the value of the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.diffdata.EMatch}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Contents</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Contents</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_ModifiableContents()
   * @model containment="true"
   * @generated
   */
  EList<EMatch> getModifiableContents();

  /**
   * Returns the value of the '<em><b>Reference Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.api.IMatch}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_ReferenceCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.diffdata.IMatch" resolveProxies="false"
   * @generated
   */
  EList<IMatch> getReferenceCompletedMatches();

  /**
   * Returns the value of the '<em><b>Target Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.api.IMatch}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_TargetCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.diffdata.IMatch" resolveProxies="false"
   * @generated
   */
  EList<IMatch> getTargetCompletedMatches();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EComparison getComparison();

} // EMapping
