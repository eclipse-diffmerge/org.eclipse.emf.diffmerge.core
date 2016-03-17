/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.diffuidata;


import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A simple data structure that aggregates an IMatch and an EStructuralFeature.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getMatch <em>Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature()
 * @model
 * @generated
 */
public interface MatchAndFeature extends EObject {
	/**
	 * Returns the value of the '<em><b>Match</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * Returns the non-null match
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match</em>' reference.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature_Match()
	 * @model required="true" changeable="false"
	 * @generated
	 */
	EMatch getMatch();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
   * Returns the non-null feature
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature_Feature()
	 * @model required="true" changeable="false"
	 * @generated
	 */
	EStructuralFeature getFeature();

} // MatchAndFeature
