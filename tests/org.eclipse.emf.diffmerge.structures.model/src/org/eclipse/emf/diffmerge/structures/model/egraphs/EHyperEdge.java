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
 * A representation of the model object '<em><b>EHyper Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEHyperEdge()
 * @model
 * @generated
 */
public interface EHyperEdge extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEHyperEdge_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(ENode)
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEHyperEdge_Source()
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getOutgoing
	 * @model opposite="outgoing" required="true" transient="false"
	 * @generated
	 */
	ENode getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ENode value);

	/**
	 * Returns the value of the '<em><b>Targets</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Targets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Targets</em>' reference list.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEHyperEdge_Targets()
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getIncoming
	 * @model opposite="incoming"
	 * @generated
	 */
	EList<ENode> getTargets();

} // EHyperEdge
