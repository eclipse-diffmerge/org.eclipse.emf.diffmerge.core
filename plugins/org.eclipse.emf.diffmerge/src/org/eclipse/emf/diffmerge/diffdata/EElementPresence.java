/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.diffmerge.api.diff.IElementPresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EElementPresence#getOwnerMatch <em>Owner Match</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementPresence()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence org.eclipse.emf.diffmerge.diffdata.IElementPresence"
 * @generated
 */
public interface EElementPresence
    extends EElementRelativePresence, IElementPresence {
  /**
   * Returns the value of the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owner Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owner Match</em>' reference.
   * @see #setOwnerMatch(EMatch)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementPresence_OwnerMatch()
   * @model required="true"
   * @generated
   */
  EMatch getOwnerMatch();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EElementPresence#getOwnerMatch <em>Owner Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owner Match</em>' reference.
   * @see #getOwnerMatch()
   * @generated
   */
  void setOwnerMatch(EMatch value);

} // EElementPresence
