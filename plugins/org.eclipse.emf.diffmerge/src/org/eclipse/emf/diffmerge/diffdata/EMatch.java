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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GMatch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch()
 * @model
 * @generated
 */
public interface EMatch
    extends GMatch<EObject, EAttribute, EReference>, EComparisonElement {
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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_Ancestor()
   * @model
   * @generated
   */
  EObject getAncestor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getAncestor <em>Ancestor</em>}' reference.
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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_Reference()
   * @model
   * @generated
   */
  EObject getReference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getReference <em>Reference</em>}' reference.
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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_Target()
   * @model
   * @generated
   */
  EObject getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(EObject value);

  /**
   * Returns the value of the '<em><b>Modifiable Attribute Map</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EAttribute},
   * and the value is of type list of {@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence<org.eclipse.emf.ecore.EObject>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Attribute Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Attribute Map</em>' map.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_ModifiableAttributeMap()
   * @model mapType="org.eclipse.emf.diffmerge.diffdata.AttributeToDifferenceEntry&lt;org.eclipse.emf.ecore.EAttribute, org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<EAttribute, EList<IAttributeValuePresence<EObject>>> getModifiableAttributeMap();

  /**
   * Returns the value of the '<em><b>Modifiable Reference Map</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EReference},
   * and the value is of type list of {@link java.util.Map.Entry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence<org.eclipse.emf.ecore.EObject>>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Reference Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Reference Map</em>' map.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_ModifiableReferenceMap()
   * @model mapType="org.eclipse.emf.diffmerge.diffdata.ReferenceToElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.diffdata.ElementToDifferenceEntry&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> getModifiableReferenceMap();

  /**
   * Returns the value of the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * The key is of type {@link org.eclipse.emf.ecore.EReference},
   * and the value is of type list of {@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence<org.eclipse.emf.ecore.EObject>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Order Reference Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Order Reference Map</em>' map.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMatch_ModifiableOrderReferenceMap()
   * @model mapType="org.eclipse.emf.diffmerge.diffdata.ReferenceToOrderDifferenceEntry&lt;org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<EReference, EList<IReferenceValuePresence<EObject>>> getModifiableOrderReferenceMap();

} // GMatch
