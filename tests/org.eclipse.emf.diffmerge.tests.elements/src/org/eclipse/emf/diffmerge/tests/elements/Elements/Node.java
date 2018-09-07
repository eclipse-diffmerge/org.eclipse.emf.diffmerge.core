/*********************************************************************
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.tests.elements.Elements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getSubNodes <em>Sub Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends NamedElement {
	/**
   * Returns the value of the '<em><b>Incoming</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Incoming</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getNode_Incoming()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getTarget
   * @model opposite="target"
   * @generated
   */
	EList<Edge> getIncoming();

	/**
   * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outgoing</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getNode_Outgoing()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getSource
   * @model opposite="source"
   * @generated
   */
	EList<Edge> getOutgoing();

	/**
   * Returns the value of the '<em><b>Sub Nodes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Nodes</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getNode_SubNodes()
   * @model containment="true"
   * @generated
   */
	EList<NamedElement> getSubNodes();

} // Node
