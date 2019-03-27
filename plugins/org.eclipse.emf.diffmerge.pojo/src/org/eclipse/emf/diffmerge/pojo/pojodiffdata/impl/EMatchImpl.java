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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMatch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EMatchImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl<Object, Object, Object>
    implements EMatch {
  /**
   * The cached value of the '{@link #getModifiableAttributeMap() <em>Modifiable Attribute Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableAttributeMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EList<IAttributeValuePresence<Object>>> modifiableAttributeMap;

  /**
   * The cached value of the '{@link #getModifiableReferenceMap() <em>Modifiable Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EMap<Object, IReferenceValuePresence<Object>>> modifiableReferenceMap;

  /**
   * The cached value of the '{@link #getModifiableOrderReferenceMap() <em>Modifiable Order Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableOrderReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EList<IReferenceValuePresence<Object>>> modifiableOrderReferenceMap;

  /**
   * The default value of the '{@link #getAncestor() <em>Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestor()
   * @generated
   * @ordered
   */
  protected static final Object ANCESTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAncestor() <em>Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestor()
   * @generated
   * @ordered
   */
  protected Object ancestor = ANCESTOR_EDEFAULT;

  /**
   * The default value of the '{@link #getReference() <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected static final Object REFERENCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected Object reference = REFERENCE_EDEFAULT;

  /**
   * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected static final Object TARGET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected Object target = TARGET_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EMatchImpl() {
    super();
  }

  /**
   * Constructor
   * Precondition: at least one of the given elements is not null.
   * @param target_p the optional element on the TARGET side
   * @param reference_p the optional element on the REFERENCE side
   * @param ancestor_p the optional element on the ANCESTOR side
   * @generated NOT
   */
  public EMatchImpl(Object target_p, Object reference_p, Object ancestor_p) {
    super(target_p, reference_p, ancestor_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.EMATCH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EList<IAttributeValuePresence<Object>>> getModifiableAttributeMap() {
    if (modifiableAttributeMap == null) {
      modifiableAttributeMap = new EcoreEMap<Object, EList<IAttributeValuePresence<Object>>>(
          PojodiffdataPackage.Literals.ATTRIBUTE_TO_DIFFERENCE_ENTRY,
          AttributeToDifferenceEntryImpl.class, this,
          PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    }
    return modifiableAttributeMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EMap<Object, IReferenceValuePresence<Object>>> getModifiableReferenceMap() {
    if (modifiableReferenceMap == null) {
      modifiableReferenceMap = new EcoreEMap<Object, EMap<Object, IReferenceValuePresence<Object>>>(
          PojodiffdataPackage.Literals.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY,
          ReferenceToElementToDifferenceEntryImpl.class, this,
          PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP);
    }
    return modifiableReferenceMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EList<IReferenceValuePresence<Object>>> getModifiableOrderReferenceMap() {
    if (modifiableOrderReferenceMap == null) {
      modifiableOrderReferenceMap = new EcoreEMap<Object, EList<IReferenceValuePresence<Object>>>(
          PojodiffdataPackage.Literals.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY,
          ReferenceToOrderDifferenceEntryImpl.class, this,
          PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP);
    }
    return modifiableOrderReferenceMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getAncestor() {
    return ancestor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAncestor(Object newAncestor) {
    Object oldAncestor = ancestor;
    ancestor = newAncestor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          PojodiffdataPackage.EMATCH__ANCESTOR, oldAncestor, ancestor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getReference() {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setReference(Object newReference) {
    Object oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          PojodiffdataPackage.EMATCH__REFERENCE, oldReference, reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getTarget() {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(Object newTarget) {
    Object oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          PojodiffdataPackage.EMATCH__TARGET, oldTarget, target));
  }

  /**
   * @see EMatch#getMapping()
   * @generated NOT
   */
  @Override
  public EMapping getMapping() {
    return (EMapping) super.getMapping();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getComparison()
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
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return ((InternalEList<?>) getModifiableAttributeMap())
          .basicRemove(otherEnd, msgs);
    case PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableReferenceMap())
          .basicRemove(otherEnd, msgs);
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableOrderReferenceMap())
          .basicRemove(otherEnd, msgs);
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
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      if (coreType)
        return getModifiableAttributeMap();
      else
        return getModifiableAttributeMap().map();
    case PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      if (coreType)
        return getModifiableReferenceMap();
      else
        return getModifiableReferenceMap().map();
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      if (coreType)
        return getModifiableOrderReferenceMap();
      else
        return getModifiableOrderReferenceMap().map();
    case PojodiffdataPackage.EMATCH__ANCESTOR:
      return getAncestor();
    case PojodiffdataPackage.EMATCH__REFERENCE:
      return getReference();
    case PojodiffdataPackage.EMATCH__TARGET:
      return getTarget();
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
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      ((EStructuralFeature.Setting) getModifiableAttributeMap()).set(newValue);
      return;
    case PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableReferenceMap()).set(newValue);
      return;
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableOrderReferenceMap())
          .set(newValue);
      return;
    case PojodiffdataPackage.EMATCH__ANCESTOR:
      setAncestor(newValue);
      return;
    case PojodiffdataPackage.EMATCH__REFERENCE:
      setReference(newValue);
      return;
    case PojodiffdataPackage.EMATCH__TARGET:
      setTarget(newValue);
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
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      getModifiableAttributeMap().clear();
      return;
    case PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      getModifiableReferenceMap().clear();
      return;
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      getModifiableOrderReferenceMap().clear();
      return;
    case PojodiffdataPackage.EMATCH__ANCESTOR:
      setAncestor(ANCESTOR_EDEFAULT);
      return;
    case PojodiffdataPackage.EMATCH__REFERENCE:
      setReference(REFERENCE_EDEFAULT);
      return;
    case PojodiffdataPackage.EMATCH__TARGET:
      setTarget(TARGET_EDEFAULT);
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
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return modifiableAttributeMap != null
          && !modifiableAttributeMap.isEmpty();
    case PojodiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return modifiableReferenceMap != null
          && !modifiableReferenceMap.isEmpty();
    case PojodiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      return modifiableOrderReferenceMap != null
          && !modifiableOrderReferenceMap.isEmpty();
    case PojodiffdataPackage.EMATCH__ANCESTOR:
      return ANCESTOR_EDEFAULT == null ? ancestor != null
          : !ANCESTOR_EDEFAULT.equals(ancestor);
    case PojodiffdataPackage.EMATCH__REFERENCE:
      return REFERENCE_EDEFAULT == null ? reference != null
          : !REFERENCE_EDEFAULT.equals(reference);
    case PojodiffdataPackage.EMATCH__TARGET:
      return TARGET_EDEFAULT == null ? target != null
          : !TARGET_EDEFAULT.equals(target);
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
    result.append(" (ancestor: "); //$NON-NLS-1$
    result.append(ancestor);
    result.append(", reference: "); //$NON-NLS-1$
    result.append(reference);
    result.append(", target: "); //$NON-NLS-1$
    result.append(target);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableAttributeMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EList<IAttributeValuePresence<Object>>> getModifiableAttributeMap(
      boolean create_p) {
    return create_p ? getModifiableAttributeMap() : modifiableAttributeMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableOrderReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EList<IReferenceValuePresence<Object>>> getModifiableOrderReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableOrderReferenceMap()
        : modifiableOrderReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EMap<Object, IReferenceValuePresence<Object>>> getModifiableReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableReferenceMap() : modifiableReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#newAttributeValuePresenceList(java.lang.Object)
   * @generated NOT
   */
  @Override
  protected EList<IAttributeValuePresence<Object>> newAttributeValuePresenceList(
      Object attribute_p) {
    AttributeToDifferenceEntryImpl entry = new AttributeToDifferenceEntryImpl();
    entry.setKey(attribute_p);
    getModifiableAttributeMap(true).add(entry);
    return entry.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#newReferenceOrderDifferenceList(java.lang.Object)
   * @generated NOT
   */
  @Override
  protected EList<IReferenceValuePresence<Object>> newReferenceOrderDifferenceList(
      Object reference_p) {
    ReferenceToOrderDifferenceEntryImpl entry = new ReferenceToOrderDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableOrderReferenceMap(true).add(entry);
    return entry.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#newReferenceValueToPresenceMap(java.lang.Object)
   * @generated NOT
   */
  @Override
  protected EMap<Object, IReferenceValuePresence<Object>> newReferenceValueToPresenceMap(
      Object reference_p) {
    ReferenceToElementToDifferenceEntryImpl entry = new ReferenceToElementToDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableReferenceMap(true).add(entry);
    return entry.getValue();
  }

} //EMatchImpl
