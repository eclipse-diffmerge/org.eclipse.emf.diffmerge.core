/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.model.egraphs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ENode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getIncoming <em>Incoming</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getENode()
 * @model
 * @generated
 */
public interface ENode extends EObject {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' attribute.
	 * @see #setElement(String)
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getENode_Element()
	 * @model required="true"
	 * @generated
	 */
	String getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getElement <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' attribute.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(String value);

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' containment reference list.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getENode_Outgoing()
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<EHyperEdge> getOutgoing();

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getENode_Incoming()
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getTargets
	 * @model opposite="targets"
	 * @generated
	 */
	EList<EHyperEdge> getIncoming();

} // ENode
