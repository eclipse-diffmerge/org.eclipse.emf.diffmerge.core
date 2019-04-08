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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EMatchImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl<EObject, EAttribute, EReference>
    implements EMatch {

  /**
   * The cached value of the '{@link #getAncestor() <em>Ancestor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestor()
   * @generated
   * @ordered
   */
  protected EObject ancestor;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected EObject reference;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected EObject target;

  /**
   * The cached value of the '{@link #getModifiableAttributeMap() <em>Modifiable Attribute Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableAttributeMap()
   * @generated
   * @ordered
   */
  protected EMap<EAttribute, EList<IAttributeValuePresence<EObject>>> modifiableAttributeMap;

  /**
   * The cached value of the '{@link #getModifiableReferenceMap() <em>Modifiable Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> modifiableReferenceMap;

  /**
   * The cached value of the '{@link #getModifiableOrderReferenceMap() <em>Modifiable Order Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableOrderReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<EReference, EList<IReferenceValuePresence<EObject>>> modifiableOrderReferenceMap;

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
  public EMatchImpl(EObject target_p, EObject reference_p, EObject ancestor_p) {
    super(target_p, reference_p, ancestor_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EMATCH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject getAncestor() {
    if (ancestor != null && ancestor.eIsProxy()) {
      InternalEObject oldAncestor = (InternalEObject) ancestor;
      ancestor = eResolveProxy(oldAncestor);
      if (ancestor != oldAncestor) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__ANCESTOR, oldAncestor, ancestor));
      }
    }
    return ancestor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetAncestor() {
    return ancestor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAncestor(EObject newAncestor) {
    EObject oldAncestor = ancestor;
    ancestor = newAncestor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__ANCESTOR, oldAncestor, ancestor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject getReference() {
    if (reference != null && reference.eIsProxy()) {
      InternalEObject oldReference = (InternalEObject) reference;
      reference = eResolveProxy(oldReference);
      if (reference != oldReference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__REFERENCE, oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetReference() {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setReference(EObject newReference) {
    EObject oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__REFERENCE, oldReference, reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject getTarget() {
    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject) target;
      target = eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetTarget() {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(EObject newTarget) {
    EObject oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__TARGET, oldTarget, target));
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
  public EMap<EAttribute, EList<IAttributeValuePresence<EObject>>> getModifiableAttributeMap() {
    if (modifiableAttributeMap == null) {
      modifiableAttributeMap = new EcoreEMap<EAttribute, EList<IAttributeValuePresence<EObject>>>(
          DiffdataPackage.Literals.ATTRIBUTE_TO_DIFFERENCE_ENTRY,
          AttributeToDifferenceEntryImpl.class, this,
          DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    }
    return modifiableAttributeMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> getModifiableReferenceMap() {
    if (modifiableReferenceMap == null) {
      modifiableReferenceMap = new EcoreEMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>>(
          DiffdataPackage.Literals.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY,
          ReferenceToElementToDifferenceEntryImpl.class, this,
          DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP) {
        private static final long serialVersionUID = 1L;

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
         */
        @Override
        protected boolean useEqualsForKey() {
          return false;
        }

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
         */
        @Override
        protected boolean useEqualsForValue() {
          return false;
        }
      };
    }
    return modifiableReferenceMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EMap<EReference, EList<IReferenceValuePresence<EObject>>> getModifiableOrderReferenceMap() {
    if (modifiableOrderReferenceMap == null) {
      modifiableOrderReferenceMap = new EcoreEMap<EReference, EList<IReferenceValuePresence<EObject>>>(
          DiffdataPackage.Literals.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY,
          ReferenceToOrderDifferenceEntryImpl.class, this,
          DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP) {
        private static final long serialVersionUID = 1L;

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
         */
        @Override
        protected boolean useEqualsForKey() {
          return false;
        }

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
         */
        @Override
        protected boolean useEqualsForValue() {
          return false;
        }
      };
    }
    return modifiableOrderReferenceMap;
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
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return ((InternalEList<?>) getModifiableAttributeMap())
          .basicRemove(otherEnd, msgs);
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableReferenceMap())
          .basicRemove(otherEnd, msgs);
    case DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
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
    case DiffdataPackage.EMATCH__ANCESTOR:
      if (resolve)
        return getAncestor();
      return basicGetAncestor();
    case DiffdataPackage.EMATCH__REFERENCE:
      if (resolve)
        return getReference();
      return basicGetReference();
    case DiffdataPackage.EMATCH__TARGET:
      if (resolve)
        return getTarget();
      return basicGetTarget();
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      if (coreType)
        return getModifiableAttributeMap();
      else
        return getModifiableAttributeMap().map();
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      if (coreType)
        return getModifiableReferenceMap();
      else
        return getModifiableReferenceMap().map();
    case DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      if (coreType)
        return getModifiableOrderReferenceMap();
      else
        return getModifiableOrderReferenceMap().map();
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
    case DiffdataPackage.EMATCH__ANCESTOR:
      setAncestor((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__REFERENCE:
      setReference((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__TARGET:
      setTarget((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      ((EStructuralFeature.Setting) getModifiableAttributeMap()).set(newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableReferenceMap()).set(newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableOrderReferenceMap())
          .set(newValue);
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
    case DiffdataPackage.EMATCH__ANCESTOR:
      setAncestor((EObject) null);
      return;
    case DiffdataPackage.EMATCH__REFERENCE:
      setReference((EObject) null);
      return;
    case DiffdataPackage.EMATCH__TARGET:
      setTarget((EObject) null);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      getModifiableAttributeMap().clear();
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      getModifiableReferenceMap().clear();
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      getModifiableOrderReferenceMap().clear();
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
    case DiffdataPackage.EMATCH__ANCESTOR:
      return ancestor != null;
    case DiffdataPackage.EMATCH__REFERENCE:
      return reference != null;
    case DiffdataPackage.EMATCH__TARGET:
      return target != null;
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return modifiableAttributeMap != null
          && !modifiableAttributeMap.isEmpty();
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return modifiableReferenceMap != null
          && !modifiableReferenceMap.isEmpty();
    case DiffdataPackage.EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      return modifiableOrderReferenceMap != null
          && !modifiableOrderReferenceMap.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableAttributeMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<EAttribute, EList<IAttributeValuePresence<EObject>>> getModifiableAttributeMap(
      boolean create_p) {
    return create_p ? getModifiableAttributeMap() : modifiableAttributeMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableOrderReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<EReference, EList<IReferenceValuePresence<EObject>>> getModifiableOrderReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableOrderReferenceMap()
        : modifiableOrderReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> getModifiableReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableReferenceMap() : modifiableReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#newAttributeValuePresenceList(java.lang.Object)
   * @generated NOT
   */
  @Override
  protected EList<IAttributeValuePresence<EObject>> newAttributeValuePresenceList(
      EAttribute attribute_p) {
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
  protected EList<IReferenceValuePresence<EObject>> newReferenceOrderDifferenceList(
      EReference reference_p) {
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
  protected EMap<EObject, IReferenceValuePresence<EObject>> newReferenceValueToPresenceMap(
      EReference reference_p) {
    ReferenceToElementToDifferenceEntryImpl entry = new ReferenceToElementToDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableReferenceMap(true).add(entry);
    return entry.getValue();
  }

} //EMatchImpl
