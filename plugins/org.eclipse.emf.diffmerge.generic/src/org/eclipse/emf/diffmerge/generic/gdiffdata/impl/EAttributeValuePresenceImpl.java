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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EAttributeValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EAttributeValuePresenceImpl<E, A, R> extends
    EValuePresenceImpl<E, A, R> implements EAttributeValuePresence<E, A, R> {
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final Object VALUE_EDEFAULT = null;
  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected Object value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EAttributeValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param attribute_p the non-null attribute holding the value
   * @param value_p the non-null value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public EAttributeValuePresenceImpl(EMatch<E, A, R> elementMatch_p,
      A attribute_p, Object value_p, Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, presenceRole_p, isOrder_p);
    setValue(value_p);
    setAttribute(attribute_p);
    ((IMatch.Editable<E>) elementMatch).addRelatedDifference(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.EATTRIBUTE_VALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(Object newValue) {
    Object oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public abstract void setAttribute(A attribute);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__VALUE:
      return getValue();
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
    case GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__VALUE:
      setValue(newValue);
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
    case GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__VALUE:
      setValue(VALUE_EDEFAULT);
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
    case GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__VALUE:
      return VALUE_EDEFAULT == null ? value != null
          : !VALUE_EDEFAULT.equals(value);
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
    result.append(" (value: "); //$NON-NLS-1$
    result.append(value);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public IAttributeValuePresence<E> getSymmetrical() {
    IAttributeValuePresence<E> result = null;
    if (!isManyFeature()) {
      Collection<IAttributeValuePresence<E>> candidates = getElementMatch()
          .getAttributeDifferences(getFeature());
      assert candidates.size() <= 2; // Because !isMany()
      for (IAttributeValuePresence<E> candidate : candidates) {
        if (candidate.getPresenceRole() == getAbsenceRole()) {
          result = candidate;
          break;
        }
      }
    } else if (isOrder()) {
      result = getElementMatch().getAttributeValueDifference(getFeature(),
          null);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#isChangeableFeature()
   * @generated NOT
   */
  public boolean isChangeableFeature() {
    return getPresenceScope().getScopePolicy()
        .isChangeableAttribute(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#isManyFeature()
   * @generated NOT
   */
  @Override
  public boolean isManyFeature() {
    return getPresenceScope().getScopePolicy().isManyAttribute(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeOrder()
   * @generated NOT
   */
  @Override
  protected void mergeOrder() {
    // TODO Implement
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeValueAddition()
   * @generated NOT
   */
  @Override
  protected void mergeValueAddition() {
    IEditableTreeDataScope<E> absenceScope = getAbsenceScope();
    E holderMatch = getMatchOfHolder();
    assert holderMatch != null; // Must be guaranteed by diff dependency handling
    absenceScope.addAttributeValue(holderMatch, getFeature(), getValue());
    IDiffPolicy<E> diffPolicy = getComparison().getLastDiffPolicy();
    if (diffPolicy != null
        && diffPolicy.considerOrderedAttribute(getFeature())) {
      // TODO Implement
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeValueRemoval()
   * @generated NOT
   */
  @Override
  public void mergeValueRemoval() {
    IEditableTreeDataScope<E> presenceScope = getPresenceScope();
    presenceScope.removeAttributeValue(getHolder(), getFeature(), getValue());
  }

} //EAttributeValuePresenceImpl
