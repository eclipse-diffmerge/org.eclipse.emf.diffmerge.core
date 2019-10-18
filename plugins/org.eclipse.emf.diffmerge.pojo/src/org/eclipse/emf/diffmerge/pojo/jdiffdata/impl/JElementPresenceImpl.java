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
package org.eclipse.emf.diffmerge.pojo.jdiffdata.impl;

import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class JElementPresenceImpl<E extends Object> extends
    GElementPresenceImpl<E, Object, Object> implements JElementPresence<E> {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JElementPresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param match_p the non-null match to which this difference is relative
   * @param ownerMatch_p a potentially null match for the owner of the element
   * @param presenceRole_p the non-null role in which the element is present: TARGET or REFERENCE
   * @generated NOT
   */
  public JElementPresenceImpl(JMatch<E> match_p, JMatch<E> ownerMatch_p,
      Role presenceRole_p) {
    super(match_p, ownerMatch_p, presenceRole_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return JdiffdataPackage.Literals.JELEMENT_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl#getOwnerMatch()
   * @generated NOT
   */
  @Override
  public JMatch<E> getOwnerMatch() {
    return (JMatch<E>) super.getOwnerMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl#getElementMatch()
   * @generated NOT
   */
  @Override
  public JMatch<E> getElementMatch() {
    return (JMatch<E>) super.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl#getComparison()
   * @generated NOT
   */
  @Override
  public JComparison<E> getComparison() {
    return (JComparison<E>) super.getComparison();
  }

} //GElementPresenceImpl
