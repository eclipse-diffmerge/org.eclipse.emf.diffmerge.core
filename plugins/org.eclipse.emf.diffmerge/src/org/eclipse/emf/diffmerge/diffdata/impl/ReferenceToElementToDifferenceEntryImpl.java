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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference To Element To Difference Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceToElementToDifferenceEntryImpl extends EObjectImpl
    implements
    BasicEMap.Entry<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> {
  /**
   * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedKey()
   * @generated
   * @ordered
   */
  protected EReference key;

  /**
   * The cached value of the '{@link #getTypedValue() <em>Value</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedValue()
   * @generated
   * @ordered
   */
  protected EMap<EObject, IReferenceValuePresence<EObject>> value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReferenceToElementToDifferenceEntryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTypedKey() {
    if (key != null && key.eIsProxy()) {
      InternalEObject oldKey = (InternalEObject) key;
      key = (EReference) eResolveProxy(oldKey);
      if (key != oldKey) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY,
              oldKey, key));
      }
    }
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference basicGetTypedKey() {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypedKey(EReference newKey) {
    EReference oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY, oldKey,
          key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<EObject, IReferenceValuePresence<EObject>> getTypedValue() {
    if (value == null) {
      value = new EcoreEMap<EObject, IReferenceValuePresence<EObject>>(
          DiffdataPackage.Literals.ELEMENT_TO_DIFFERENCE_ENTRY,
          ElementToDifferenceEntryImpl.class, this,
          DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE);
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
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE:
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
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY:
      if (resolve)
        return getTypedKey();
      return basicGetTypedKey();
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE:
      if (coreType)
        return getTypedValue();
      else
        return getTypedValue().map();
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
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY:
      setTypedKey((EReference) newValue);
      return;
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE:
      ((EStructuralFeature.Setting) getTypedValue()).set(newValue);
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
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY:
      setTypedKey((EReference) null);
      return;
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE:
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
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY:
      return key != null;
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE:
      return value != null && !value.isEmpty();
    }
    return super.eIsSet(featureID);
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
  public EReference getKey() {
    return getTypedKey();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(EReference key) {
    setTypedKey(key);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<EObject, IReferenceValuePresence<EObject>> getValue() {
    return getTypedValue();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<EObject, IReferenceValuePresence<EObject>> setValue(
      EMap<EObject, IReferenceValuePresence<EObject>> value) {
    EMap<EObject, IReferenceValuePresence<EObject>> oldValue = getValue();
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
  public EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> getEMap() {
    EObject container = eContainer();
    return container == null ? null
        : (EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>>) container
            .eGet(eContainmentFeature());
  }

} //ReferenceToElementToDifferenceEntryImpl
