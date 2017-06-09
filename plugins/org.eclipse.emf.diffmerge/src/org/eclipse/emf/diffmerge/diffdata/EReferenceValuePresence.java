/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValueMatch <em>Value Match</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EValuePresence org.eclipse.emf.diffmerge.diffdata.IReferenceValuePresence"
 * @generated
 */
public interface EReferenceValuePresence extends EValuePresence,
    IReferenceValuePresence {

  /**
   * Returns the value of the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' reference.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence_Value()
   * @model required="true" changeable="false"
   * @generated
   */
  EObject getValue();

  /**
   * Returns the value of the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Match</em>' reference.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEReferenceValuePresence_ValueMatch()
   * @model required="true" changeable="false"
   * @generated
   */
  EMatch getValueMatch();
  // Nothing needed
} // EReferenceValuePresence
