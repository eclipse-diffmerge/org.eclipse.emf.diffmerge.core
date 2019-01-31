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
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IMatch.Editable;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

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
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableMatch"
 * @generated
 */
public interface EMatch extends EIdentified, Editable {
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
   * <p>
   * If the meaning of the '<em>Mapping</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EMapping getMapping();

  /**
   * Returns the value of the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor</em>' reference.
   * @see #setAncestor(EObject)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_Ancestor()
   * @model
   * @generated
   */
  EObject getAncestor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getAncestor <em>Ancestor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor</em>' reference.
   * @see #getAncestor()
   * @generated
   */
  void setAncestor(EObject value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' reference.
   * @see #setReference(EObject)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_Reference()
   * @model
   * @generated
   */
  EObject getReference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(EObject value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(EObject)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_Target()
   * @model
   * @generated
   */
  EObject getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(EObject value);

  /**
   * Returns the value of the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference}.
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
  EList<EMergeableDifference> getModifiableRelatedDifferences();

  /**
   * Returns the value of the '<em><b>Modifiable Attribute Map</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EAttribute},
   * and the value is of type list of {@link java.util.Map.Entry<java.lang.Object, org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Attribute Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Attribute Map</em>' map.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_ModifiableAttributeMap()
   * @model mapType="org.eclipse.emf.diffmerge.generic.gdiffdata.AttributeToValueToDifferenceEntry&lt;org.eclipse.emf.ecore.EAttribute, org.eclipse.emf.diffmerge.generic.gdiffdata.ValueToDifferenceEntry&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<EAttribute, EMap<Object, IAttributeValuePresence>> getModifiableAttributeMap();

  /**
   * Returns the value of the '<em><b>Modifiable Reference Map</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EReference},
   * and the value is of type list of {@link java.util.Map.Entry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Reference Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Reference Map</em>' map.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMatch_ModifiableReferenceMap()
   * @model mapType="org.eclipse.emf.diffmerge.generic.gdiffdata.ReferenceToElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.generic.gdiffdata.ElementToDifferenceEntry&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<EReference, EMap<EObject, IReferenceValuePresence>> getModifiableReferenceMap();

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
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IElementPresence"
   * @generated
   */
  IElementPresence getElementPresenceDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element Presence Difference</em>' reference.
   * @see #getElementPresenceDifference()
   * @generated
   */
  void setElementPresenceDifference(IElementPresence value);

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
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence"
   * @generated
   */
  IReferenceValuePresence getReferenceOwnershipDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Ownership Difference</em>' reference.
   * @see #getReferenceOwnershipDifference()
   * @generated
   */
  void setReferenceOwnershipDifference(IReferenceValuePresence value);

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
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence"
   * @generated
   */
  IReferenceValuePresence getTargetOwnershipDifference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Ownership Difference</em>' reference.
   * @see #getTargetOwnershipDifference()
   * @generated
   */
  void setTargetOwnershipDifference(IReferenceValuePresence value);

} // EMatch
