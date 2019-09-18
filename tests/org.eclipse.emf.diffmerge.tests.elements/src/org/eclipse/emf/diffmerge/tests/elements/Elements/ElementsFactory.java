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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage
 * @generated
 */
public interface ElementsFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	ElementsFactory eINSTANCE = org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementsFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Root</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Root</em>'.
   * @generated
   */
	Root createRoot();

	/**
   * Returns a new object of class '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Element</em>'.
   * @generated
   */
	Element createElement();

	/**
   * Returns a new object of class '<em>Strict Element</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Strict Element</em>'.
   * @generated
   */
	StrictElement createStrictElement();

	/**
   * Returns a new object of class '<em>Node</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Node</em>'.
   * @generated
   */
	Node createNode();

	/**
   * Returns a new object of class '<em>Edge</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Edge</em>'.
   * @generated
   */
	Edge createEdge();

	/**
   * Returns a new object of class '<em>Referencing Node</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Referencing Node</em>'.
   * @generated
   */
	ReferencingNode createReferencingNode();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	ElementsPackage getElementsPackage();

} //ElementsFactory
