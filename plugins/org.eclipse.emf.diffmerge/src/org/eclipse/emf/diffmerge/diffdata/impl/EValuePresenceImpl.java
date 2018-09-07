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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EValuePresence;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EValue Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#isOrder <em>Order</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EValuePresenceImpl extends EElementRelativePresenceImpl
    implements EValuePresence {
  /**
   * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected EStructuralFeature feature;

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
   * @param comparison_p the non-null comparison to which this difference belongs
   * @param elementMatch_p the non-null match for the element holding the value
   * @param feature_p the non-null feature holding the value
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  protected EValuePresenceImpl(EComparison comparison_p, EMatch elementMatch_p,
      EStructuralFeature feature_p, Role presenceRole_p, boolean isOrder_p) {
    super(comparison_p, elementMatch_p, presenceRole_p);
    setFeature(feature_p);
    setOrder(isOrder_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EVALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature getFeature() {
    if (feature != null && feature.eIsProxy()) {
      InternalEObject oldFeature = (InternalEObject) feature;
      feature = (EStructuralFeature) eResolveProxy(oldFeature);
      if (feature != oldFeature) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EVALUE_PRESENCE__FEATURE, oldFeature, feature));
      }
    }
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature basicGetFeature() {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeature(EStructuralFeature newFeature) {
    EStructuralFeature oldFeature = feature;
    feature = newFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EVALUE_PRESENCE__FEATURE, oldFeature, feature));
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
          DiffdataPackage.EVALUE_PRESENCE__ORDER, oldOrder, order));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("boxing")
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffdataPackage.EVALUE_PRESENCE__FEATURE:
      if (resolve)
        return getFeature();
      return basicGetFeature();
    case DiffdataPackage.EVALUE_PRESENCE__ORDER:
      return isOrder();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case DiffdataPackage.EVALUE_PRESENCE__FEATURE:
      setFeature((EStructuralFeature) newValue);
      return;
    case DiffdataPackage.EVALUE_PRESENCE__ORDER:
      setOrder(((Boolean) newValue).booleanValue());
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
    case DiffdataPackage.EVALUE_PRESENCE__FEATURE:
      setFeature((EStructuralFeature) null);
      return;
    case DiffdataPackage.EVALUE_PRESENCE__ORDER:
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
    case DiffdataPackage.EVALUE_PRESENCE__FEATURE:
      return feature != null;
    case DiffdataPackage.EVALUE_PRESENCE__ORDER:
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (order: "); //$NON-NLS-1$
    result.append(order);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#canMergeTo(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  @Override
  public boolean canMergeTo(Role destination_p) {
    return super.canMergeTo(destination_p) && getFeature().isChangeable();
  }

  /**
   * Return the non-null element holding the value in the presence role
   * @generated NOT
   */
  public final EObject getHolder() {
    return getElementMatch().get(getPresenceRole());
  }

  /**
   * Return the element, in the opposite of the presence role, which matches the element
   * holding the value
   * @return a potentially null element in the getAbsenceRole() role
   * @generated NOT
   */
  public final EObject getMatchOfHolder() {
    return getElementMatch().get(getAbsenceRole());
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#mergeAddition()
   * @generated NOT
   */
  @Override
  protected final void mergeAddition() {
    if (isOrder())
      mergeOrder();
    else
      mergeValueAddition();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#mergeRemoval()
   * @generated NOT
   */
  @Override
  protected final void mergeRemoval() {
    if (!isOrder())
      mergeValueRemoval();
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
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getSymmetrical()
   * @generated NOT
   */
  public abstract IValuePresence getSymmetrical();

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#isSymmetricalTo(org.eclipse.emf.diffmerge.api.diff.IValuePresence)
   * @generated NOT
   */
  public boolean isSymmetricalTo(IValuePresence peer_p) {
    return getAbsenceRole() == peer_p.getPresenceRole()
        && getFeature() == peer_p.getFeature()
        && (getFeature().getUpperBound() == 1 || isOrder() && peer_p.isOrder())
        && getElementMatch() == peer_p.getElementMatch();
  }

} //EValuePresenceImpl
