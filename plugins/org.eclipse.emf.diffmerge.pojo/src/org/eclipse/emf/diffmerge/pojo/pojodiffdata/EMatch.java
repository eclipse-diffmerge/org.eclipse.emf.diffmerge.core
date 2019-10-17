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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GMatch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface EMatch<E extends Object>
    extends GMatch<E, Object, Object>, EComparisonElement<E> {
  /**
   * Returns the value of the '<em><b>Modifiable Attribute Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type list of {@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence<?>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Attribute Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Attribute Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_ModifiableAttributeMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.AttributeToDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;?&gt;&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<Object, EList<IAttributeValuePresence<?>>> getModifiableAttributeMap();

  /**
   * Returns the value of the '<em><b>Modifiable Reference Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type list of {@link java.util.Map.Entry<java.lang.Object, org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence<?>>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Reference Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Reference Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_ModifiableReferenceMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ReferenceToElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToDifferenceEntry&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<Object, EMap<Object, IReferenceValuePresence<?>>> getModifiableReferenceMap();

  /**
   * Returns the value of the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type list of {@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence<?>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiable Order Reference Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiable Order Reference Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_ModifiableOrderReferenceMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ReferenceToOrderDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;&gt;"
   * @generated
   */
  @SuppressWarnings("javadoc")
  EMap<Object, EList<IReferenceValuePresence<?>>> getModifiableOrderReferenceMap();

  /**
   * Returns the value of the '<em><b>Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor</em>' attribute.
   * @see #setAncestor(Object)
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_Ancestor()
   * @model
   * @generated
   */
  E getAncestor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getAncestor <em>Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor</em>' attribute.
   * @see #getAncestor()
   * @generated
   */
  void setAncestor(E value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' attribute.
   * @see #setReference(Object)
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_Reference()
   * @model
   * @generated
   */
  E getReference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getReference <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' attribute.
   * @see #getReference()
   * @generated
   */
  void setReference(E value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' attribute.
   * @see #setTarget(Object)
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMatch_Target()
   * @model
   * @generated
   */
  E getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getTarget <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' attribute.
   * @see #getTarget()
   * @generated
   */
  void setTarget(E value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EMapping<E> getMapping();

} // GMatch
