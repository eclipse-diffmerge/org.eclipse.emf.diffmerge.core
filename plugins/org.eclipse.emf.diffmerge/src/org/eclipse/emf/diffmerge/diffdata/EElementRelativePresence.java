/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Relative Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getElementMatch <em>Element Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getPresenceRole <em>Presence Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementRelativePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.diffdata.EMergeableDifference org.eclipse.emf.diffmerge.diffdata.IElementRelativePresence"
 * @generated
 */
public interface EElementRelativePresence extends EMergeableDifference,
    IElementRelativePresence {
  /**
   * Returns the value of the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Match</em>' reference.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementRelativePresence_ElementMatch()
   * @model required="true" changeable="false"
   * @generated
   */
  EMatch getElementMatch();

  /**
   * Returns the value of the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Presence Role</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Presence Role</em>' attribute.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementRelativePresence_PresenceRole()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.Role" required="true" changeable="false"
   * @generated
   */
  Role getPresenceRole();

} // EElementRelativePresence
