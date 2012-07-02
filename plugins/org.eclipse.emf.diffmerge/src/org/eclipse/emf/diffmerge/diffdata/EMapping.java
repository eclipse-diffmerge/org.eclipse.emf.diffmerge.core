/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMapping.Editable;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getComparison <em>Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getAncestorMatches <em>Ancestor Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getReferenceMatches <em>Reference Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getTargetMatches <em>Target Matches</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.IEditableMapping"
 * @generated
 */
public interface EMapping extends EObject, Editable {
	/**
	 * Returns the value of the '<em><b>Comparison</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comparison</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comparison</em>' container reference.
	 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_Comparison()
	 * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping
	 * @model opposite="mapping" required="true" transient="false" changeable="false"
	 * @generated
	 */
	EComparison getComparison();

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
	 * @model containment="true" changeable="false"
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
	 * @model type="org.eclipse.emf.diffmerge.diffdata.IMatch"
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
	 * @model type="org.eclipse.emf.diffmerge.diffdata.IMatch"
	 * @generated
	 */
	EList<IMatch> getTargetCompletedMatches();

	/**
	 * Returns the value of the '<em><b>Ancestor Matches</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EObject},
	 * and the value is of type {@link org.eclipse.emf.diffmerge.api.IMatch},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ancestor Matches</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ancestor Matches</em>' map.
	 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_AncestorMatches()
	 * @model mapType="org.eclipse.emf.diffmerge.diffdata.ElementToMatchEntry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.diffdata.IMatch>"
	 * @generated
	 */
	EMap<EObject, IMatch> getAncestorMatches();

	/**
	 * Returns the value of the '<em><b>Reference Matches</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EObject},
	 * and the value is of type {@link org.eclipse.emf.diffmerge.api.IMatch},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Matches</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Matches</em>' map.
	 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_ReferenceMatches()
	 * @model mapType="org.eclipse.emf.diffmerge.diffdata.ElementToMatchEntry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.diffdata.IMatch>"
	 * @generated
	 */
	EMap<EObject, IMatch> getReferenceMatches();

	/**
	 * Returns the value of the '<em><b>Target Matches</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EObject},
	 * and the value is of type {@link org.eclipse.emf.diffmerge.api.IMatch},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Matches</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Matches</em>' map.
	 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEMapping_TargetMatches()
	 * @model mapType="org.eclipse.emf.diffmerge.diffdata.ElementToMatchEntry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.diffdata.IMatch>"
	 * @generated
	 */
	EMap<EObject, IMatch> getTargetMatches();

} // EMapping
