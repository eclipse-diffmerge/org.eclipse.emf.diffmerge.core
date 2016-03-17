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
package org.eclipse.emf.diffmerge.tests.elements.Elements;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identified Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.IdentifiedElement#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getIdentifiedElement()
 * @model abstract="true"
 * @generated
 */
public interface IdentifiedElement extends EObject {
	/**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getIdentifiedElement_Id()
   * @model id="true" required="true"
   * @generated
   */
	String getId();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.IdentifiedElement#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
	void setId(String value);

} // IdentifiedElement
