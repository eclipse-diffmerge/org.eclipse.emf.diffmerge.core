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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getAncestorMatchMap <em>Ancestor Match Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getReferenceMatchMap <em>Reference Match Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getTargetMatchMap <em>Target Match Map</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMapping()
 * @model superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping&lt;E, org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.ecore.EJavaObject&gt; org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement&lt;E&gt;" EBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
@SuppressWarnings("javadoc")
public interface EMapping<E extends Object>
    extends GMapping<E, Object, Object>, EComparisonElement<E> {
  /**
   * Returns the value of the '<em><b>Ancestor Match Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type {@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch<?>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor Match Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor Match Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMapping_AncestorMatchMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToMatchEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch&lt;?&gt;&gt;" derived="true"
   * @generated
   */
  EMap<Object, EMatch<?>> getAncestorMatchMap();

  /**
   * Returns the value of the '<em><b>Reference Match Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type {@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch<?>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Match Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Match Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMapping_ReferenceMatchMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToMatchEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch&lt;?&gt;&gt;" derived="true"
   * @generated
   */
  EMap<Object, EMatch<?>> getReferenceMatchMap();

  /**
   * Returns the value of the '<em><b>Target Match Map</b></em>' map.
   * The key is of type {@link java.lang.Object},
   * and the value is of type {@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch<?>},
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Match Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Match Map</em>' map.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#getEMapping_TargetMatchMap()
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToMatchEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch&lt;?&gt;&gt;" derived="true"
   * @generated
   */
  EMap<Object, EMatch<?>> getTargetMatchMap();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model potentialElementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EMatch<E> getMatchFor(Object potentialElement, Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model mapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToMatchEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch&lt;?&gt;&gt;" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EMap<Object, EMatch<?>> getMatchMap(Role role);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model required="true" elementRequired="true" roleDataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" roleRequired="true"
   * @generated
   */
  EMatch<E> map(E element, Role role);

} // GMapping
