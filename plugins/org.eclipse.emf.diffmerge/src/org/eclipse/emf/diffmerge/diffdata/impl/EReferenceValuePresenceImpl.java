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
package org.eclipse.emf.diffmerge.diffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EReferenceValuePresenceImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl<EObject, EAttribute, EReference>
    implements EReferenceValuePresence {
  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected EReference reference;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected EObject value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EReferenceValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param reference_p the non-null reference holding the value
   * @param value_p the value element, which is non-null unless valueMatch_p is not null
   * @param valueMatch_p the optional match corresponding to the value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public EReferenceValuePresenceImpl(EMatch elementMatch_p,
      EReference reference_p, EObject value_p, EMatch valueMatch_p,
      Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, reference_p, value_p, valueMatch_p, presenceRole_p,
        isOrder_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EREFERENCE_VALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReference() {
    if (reference != null && reference.eIsProxy()) {
      InternalEObject oldReference = (InternalEObject) reference;
      reference = (EReference) eResolveProxy(oldReference);
      if (reference != oldReference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE,
              oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference basicGetReference() {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setReference(EReference newReference) {
    EReference oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE, oldReference,
          reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getValue() {
    if (value != null && value.eIsProxy()) {
      InternalEObject oldValue = (InternalEObject) value;
      value = eResolveProxy(oldValue);
      if (value != oldValue) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE, oldValue,
              value));
      }
    }
    return value;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#getFeature()
   * @generated NOT
   */
  @Override
  public EReference getFeature() {
    return getReference();
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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setValue(EObject newValue) {
    EObject oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE, oldValue, value));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public EReferenceValuePresence getSymmetrical() {
    return (EReferenceValuePresence) super.getSymmetrical();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getSymmetricalOwnership()
   * @generated NOT
   */
  @Override
  public EReferenceValuePresence getSymmetricalOwnership() {
    return (EReferenceValuePresence) super.getSymmetricalOwnership();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getValueMatch()
   * @generated NOT
   */
  @Override
  public EMatch getValueMatch() {
    return (EMatch) super.getValueMatch();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE:
      if (resolve)
        return getReference();
      return basicGetReference();
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      if (resolve)
        return getValue();
      return basicGetValue();
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
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE:
      setReference((EReference) newValue);
      return;
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      setValue((EObject) newValue);
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
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE:
      setReference((EReference) null);
      return;
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      setValue((EObject) null);
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
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__REFERENCE:
      return reference != null;
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      return value != null;
    }
    return super.eIsSet(featureID);
  }

} //EReferenceValuePresenceImpl
