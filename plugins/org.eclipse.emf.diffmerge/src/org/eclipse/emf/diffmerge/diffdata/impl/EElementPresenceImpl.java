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
package org.eclipse.emf.diffmerge.diffdata.impl;

import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EElementPresenceImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl<EObject, EAttribute, EReference>
    implements EElementPresence {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EElementPresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param match_p the non-null match to which this difference is relative
   * @param ownerMatch_p a potentially null match for the owner of the element
   * @generated NOT
   */
  public EElementPresenceImpl(EMatch match_p, EMatch ownerMatch_p) {
    super(match_p, ownerMatch_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EELEMENT_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl#getOwnerMatch()
   * @generated NOT
   */
  @Override
  public EMatch getOwnerMatch() {
    return (EMatch) super.getOwnerMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#getElementMatch()
   * @generated NOT
   */
  @Override
  public EMatch getElementMatch() {
    return (EMatch) super.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl#getComparison()
   * @generated NOT
   */
  @Override
  public EComparison getComparison() {
    return (EComparison) super.getComparison();
  }

} //EElementPresenceImpl
