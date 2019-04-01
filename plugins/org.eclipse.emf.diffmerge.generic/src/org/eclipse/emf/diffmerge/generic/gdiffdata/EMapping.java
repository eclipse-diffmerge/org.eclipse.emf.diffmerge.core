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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.IMapping.Editable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMapping()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableMapping&lt;E&gt;"
 * @generated
 */
public interface EMapping<E, A, R>
    extends EIdentified, EComparisonElement<E, A, R>, Editable<E> {
  /**
   * Returns the value of the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch}<code>&lt;E, A, R&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Contents</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Contents</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMapping_ModifiableContents()
   * @model containment="true"
   * @generated
   */
  EList<EMatch<E, A, R>> getModifiableContents();

  /**
   * Returns the value of the '<em><b>Reference Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMapping_ReferenceCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;E&gt;" resolveProxies="false"
   * @generated
   */
  EList<IMatch<E>> getReferenceCompletedMatches();

  /**
   * Returns the value of the '<em><b>Target Completed Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Completed Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Completed Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMapping_TargetCompletedMatches()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;E&gt;" resolveProxies="false"
   * @generated
   */
  EList<IMatch<E>> getTargetCompletedMatches();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true" elementRequired="true"
   * @generated
   */
  boolean disconnect(Role role, E element);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" sourceRequired="true" referenceRequired="true" valueRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  boolean isIgnoredReferenceValue(E source, R reference, E value, Role role);

} // EMapping
