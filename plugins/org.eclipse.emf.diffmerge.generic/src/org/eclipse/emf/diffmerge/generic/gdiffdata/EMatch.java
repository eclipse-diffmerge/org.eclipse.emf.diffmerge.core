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
import org.eclipse.emf.diffmerge.generic.api.IMatch.Editable;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMatch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getMatchID <em>Match ID</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableMatch&lt;E&gt;"
 * @generated
 */
public interface EMatch<E, A, R> extends EIdentified, Editable<E> {
  /**
   * Returns the value of the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Match ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Match ID</em>' attribute.
   * @see #setMatchID(Object)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_MatchID()
   * @model transient="true"
   * @generated
   */
  Object getMatchID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getMatchID <em>Match ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Match ID</em>' attribute.
   * @see #getMatchID()
   * @generated
   */
  void setMatchID(Object value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  E getAncestor();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  E getReference();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  E getTarget();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void setAncestor(E e);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void setReference(E e);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void setTarget(E e);

  /**
   * Returns the value of the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference}<code>&lt;E, A, R&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Related Differences</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Related Differences</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_ModifiableRelatedDifferences()
   * @model containment="true"
   * @generated
   */
  EList<EMergeableDifference<E, A, R>> getModifiableRelatedDifferences();

  /**
   * Returns the value of the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element Presence Difference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Presence Difference</em>' reference.
   * @see #setElementPresenceDifference(IElementPresence)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_ElementPresenceDifference()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IElementPresence&lt;E&gt;"
   * @generated
   */
  IElementPresence<E> getElementPresenceDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element Presence Difference</em>' reference.
   * @see #getElementPresenceDifference()
   * @generated
   */
  void setElementPresenceDifference(IElementPresence<E> value);

  /**
   * Returns the value of the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Ownership Difference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Ownership Difference</em>' reference.
   * @see #setReferenceOwnershipDifference(IReferenceValuePresence)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_ReferenceOwnershipDifference()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;E&gt;"
   * @generated
   */
  IReferenceValuePresence<E> getReferenceOwnershipDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Ownership Difference</em>' reference.
   * @see #getReferenceOwnershipDifference()
   * @generated
   */
  void setReferenceOwnershipDifference(IReferenceValuePresence<E> value);

  /**
   * Returns the value of the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Ownership Difference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Ownership Difference</em>' reference.
   * @see #setTargetOwnershipDifference(IReferenceValuePresence)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_TargetOwnershipDifference()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;E&gt;"
   * @generated
   */
  IReferenceValuePresence<E> getTargetOwnershipDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ownership Difference</em>' reference.
   * @see #getTargetOwnershipDifference()
   * @generated
   */
  void setTargetOwnershipDifference(IReferenceValuePresence<E> value);

} // EMatch
