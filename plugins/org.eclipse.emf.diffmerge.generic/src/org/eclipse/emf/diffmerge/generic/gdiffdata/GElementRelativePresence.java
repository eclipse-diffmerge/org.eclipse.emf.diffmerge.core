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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Relative Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getElementMatch <em>Element Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getPresenceRole <em>Presence Role</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGElementRelativePresence()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IElementRelativePresence&lt;E&gt;"
 * @generated
 */
public interface GElementRelativePresence<E, A, R>
    extends GMergeableDifference<E, A, R>, IElementRelativePresence<E> {
  /**
   * Returns the value of the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element Match</em>' reference.
   * @see #setElementMatch(GMatch)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGElementRelativePresence_ElementMatch()
   * @model required="true"
   * @generated
   */
  GMatch<E, A, R> getElementMatch();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getElementMatch <em>Element Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element Match</em>' reference.
   * @see #getElementMatch()
   * @generated
   */
  void setElementMatch(GMatch<E, A, R> value);

  /**
   * Returns the value of the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Presence Role</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Presence Role</em>' attribute.
   * @see #setPresenceRole(Role)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGElementRelativePresence_PresenceRole()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role" required="true"
   * @generated
   */
  Role getPresenceRole();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getPresenceRole <em>Presence Role</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Presence Role</em>' attribute.
   * @see #getPresenceRole()
   * @generated
   */
  void setPresenceRole(Role value);

} // GElementRelativePresence
