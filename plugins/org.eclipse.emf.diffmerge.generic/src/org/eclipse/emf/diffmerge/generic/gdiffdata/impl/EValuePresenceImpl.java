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
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EValue Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#isOrder <em>Order</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EValuePresenceImpl<E, A, R> extends
    EElementRelativePresenceImpl<E, A, R> implements EValuePresence<E, A, R> {
  /**
   * The default value of the '{@link #isOrder() <em>Order</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOrder()
   * @generated
   * @ordered
   */
  protected static final boolean ORDER_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOrder() <em>Order</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOrder()
   * @generated
   * @ordered
   */
  protected boolean order = ORDER_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  protected EValuePresenceImpl(EMatch<E, A, R> elementMatch_p,
      Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, presenceRole_p);
    setOrder(isOrder_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.EVALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOrder() {
    return order;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOrder(boolean newOrder) {
    boolean oldOrder = order;
    order = newOrder;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EVALUE_PRESENCE__ORDER, oldOrder, order));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public abstract Object getFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("boxing")
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.EVALUE_PRESENCE__ORDER:
      return isOrder();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("boxing")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case GdiffdataPackage.EVALUE_PRESENCE__ORDER:
      setOrder((Boolean) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.EVALUE_PRESENCE__ORDER:
      setOrder(ORDER_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.EVALUE_PRESENCE__ORDER:
      return order != ORDER_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (order: "); //$NON-NLS-1$
    result.append(order);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl#canMergeTo(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @Override
  public boolean canMergeTo(Role destination_p) {
    return super.canMergeTo(destination_p) && isChangeableFeature();
  }

  /**
   * Return the non-null element holding the value in the presence role
   * @generated NOT
   */
  public E getHolder() {
    return getElementMatch().get(getPresenceRole());
  }

  /**
   * Return the element, in the opposite of the presence role, which matches the element
   * holding the value
   * @return a potentially null element in the getAbsenceRole() role
   * @generated NOT
   */
  public E getMatchOfHolder() {
    return getElementMatch().get(getAbsenceRole());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#mergeAddition()
   * @generated NOT
   */
  @Override
  protected void mergeAddition() {
    if (isOrder()) {
      mergeOrder();
    } else {
      mergeValueAddition();
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#mergeRemoval()
   * @generated NOT
   */
  @Override
  protected void mergeRemoval() {
    if (!isOrder()) {
      mergeValueRemoval();
    }
  }

  /**
   * Copy the order to the opposite scope
   * @generated NOT
   */
  protected abstract void mergeOrder();

  /**
   * Add the value to the absence scope
   * @generated NOT
   */
  protected abstract void mergeValueAddition();

  /**
   * Remove the value from the presence scope
   * @generated NOT
   */
  protected abstract void mergeValueRemoval();

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getSymmetrical()
   * @generated NOT
   */
  public abstract IValuePresence<E> getSymmetrical();

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#isSymmetricalTo(org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence)
   * @generated NOT
   */
  public boolean isSymmetricalTo(IValuePresence<E> peer_p) {
    return getAbsenceRole() == peer_p.getPresenceRole()
        && getFeature() == peer_p.getFeature()
        && (isOrder() && peer_p.isOrder() || !isManyFeature())
        && getElementMatch() == peer_p.getElementMatch();
  }

} //EValuePresenceImpl
