/**
 * <copyright>
 * 
 * Copyright (c) 2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.model.egraphs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage
 * @generated
 */
public interface EGraphsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EGraphsFactory eINSTANCE = org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EGraph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EGraph</em>'.
	 * @generated
	 */
	EGraph createEGraph();

	/**
	 * Returns a new object of class '<em>ENode</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ENode</em>'.
	 * @generated
	 */
	ENode createENode();

	/**
	 * Returns a new object of class '<em>EHyper Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EHyper Edge</em>'.
	 * @generated
	 */
	EHyperEdge createEHyperEdge();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EGraphsPackage getEGraphsPackage();

} //EGraphsFactory
