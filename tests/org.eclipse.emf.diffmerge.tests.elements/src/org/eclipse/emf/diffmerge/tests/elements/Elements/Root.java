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
package org.eclipse.emf.diffmerge.tests.elements.Elements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Root#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Root#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends IdentifiedElement {
	/**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getRoot_Name()
   * @model
   * @generated
   */
	String getName();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Root#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
	void setName(String value);

	/**
   * Returns the value of the '<em><b>Content</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getRoot_Content()
   * @model containment="true"
   * @generated
   */
	EList<NamedElement> getContent();

} // Root
