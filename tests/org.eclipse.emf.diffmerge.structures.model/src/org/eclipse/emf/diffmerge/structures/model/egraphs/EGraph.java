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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EGraph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEGraph()
 * @model
 * @generated
 */
public interface EGraph extends EObject {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#getEGraph_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<ENode> getContents();

} // EGraph
