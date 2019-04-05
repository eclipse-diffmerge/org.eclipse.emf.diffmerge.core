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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute To Difference Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeToDifferenceEntryImpl extends EObjectImpl
    implements BasicEMap.Entry<Object, EList<IAttributeValuePresence<?>>> {
  /**
   * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected static final Object KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected Object key = KEY_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypedValue() <em>Value</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedValue()
   * @generated
   * @ordered
   */
  protected EList<IAttributeValuePresence<?>> value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeToDifferenceEntryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.ATTRIBUTE_TO_DIFFERENCE_ENTRY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getTypedKey() {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypedKey(Object newKey) {
    Object oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY, oldKey, key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IAttributeValuePresence<?>> getTypedValue() {
    if (value == null) {
      value = new EObjectContainmentEList<IAttributeValuePresence<?>>(
          IAttributeValuePresence.class, this,
          PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE);
    }
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE:
      return ((InternalEList<?>) getTypedValue()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY:
      return getTypedKey();
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE:
      return getTypedValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY:
      setTypedKey(newValue);
      return;
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE:
      getTypedValue().clear();
      getTypedValue()
          .addAll((Collection<? extends IAttributeValuePresence<?>>) newValue);
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
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY:
      setTypedKey(KEY_EDEFAULT);
      return;
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE:
      getTypedValue().clear();
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
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY:
      return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE:
      return value != null && !value.isEmpty();
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
    result.append(" (key: "); //$NON-NLS-1$
    result.append(key);
    result.append(')');
    return result.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected int hash = -1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getHash() {
    if (hash == -1) {
      Object theKey = getKey();
      hash = (theKey == null ? 0 : theKey.hashCode());
    }
    return hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHash(int hash) {
    this.hash = hash;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getKey() {
    return getTypedKey();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(Object key) {
    setTypedKey(key);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IAttributeValuePresence<?>> getValue() {
    return getTypedValue();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IAttributeValuePresence<?>> setValue(
      EList<IAttributeValuePresence<?>> value) {
    EList<IAttributeValuePresence<?>> oldValue = getValue();
    getTypedValue().clear();
    getTypedValue().addAll(value);
    return oldValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public EMap<Object, EList<IAttributeValuePresence<?>>> getEMap() {
    EObject container = eContainer();
    return container == null ? null
        : (EMap<Object, EList<IAttributeValuePresence<?>>>) container
            .eGet(eContainmentFeature());
  }

} //AttributeToDifferenceEntryImpl
