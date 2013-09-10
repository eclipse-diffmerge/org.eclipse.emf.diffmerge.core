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

import org.eclipse.emf.diffmerge.api.diff.IElementPresence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EElementPresence#getOwnerMatch <em>Owner Match</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementPresence()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence org.eclipse.emf.diffmerge.diffdata.IElementPresence"
 * @generated
 */
public interface EElementPresence extends EElementRelativePresence,
    IElementPresence {
  /**
   * Returns the value of the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owner Match</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owner Match</em>' reference.
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementPresence_OwnerMatch()
   * @model required="true" changeable="false"
   * @generated
   */
  EMatch getOwnerMatch();

} // EElementPresence
