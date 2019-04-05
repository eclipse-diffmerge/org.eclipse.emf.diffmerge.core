/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EElementPresenceImpl<E extends Object> extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl<E, Object, Object>
    implements EElementPresence<E> {
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
  public EElementPresenceImpl(EMatch<E> match_p, EMatch<E> ownerMatch_p) {
    super(match_p, ownerMatch_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.EELEMENT_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl#getOwnerMatch()
   * @generated NOT
   */
  @Override
  public EMatch<E> getOwnerMatch() {
    return (EMatch<E>) super.getOwnerMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#getElementMatch()
   * @generated NOT
   */
  @Override
  public EMatch<E> getElementMatch() {
    return (EMatch<E>) super.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl#getComparison()
   * @generated NOT
   */
  @Override
  public EComparison<E> getComparison() {
    return (EComparison<E>) super.getComparison();
  }

} //EElementPresenceImpl
