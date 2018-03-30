/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.diffdata.impl;

import static org.eclipse.emf.diffmerge.api.Role.ANCESTOR;
import static org.eclipse.emf.diffmerge.api.Role.REFERENCE;
import static org.eclipse.emf.diffmerge.api.Role.TARGET;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IPureMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getMatchID <em>Match ID</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getElementPresenceDifference <em>Element Presence Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl#getTargetOwnershipDifference <em>Target Ownership Difference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EMatchImpl extends EObjectImpl implements EMatch {

  /**
   * The default value of the '{@link #getMatchID() <em>Match ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMatchID()
   * @generated
   * @ordered
   */
  protected static final Object MATCH_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMatchID() <em>Match ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMatchID()
   * @generated
   * @ordered
   */
  protected Object matchID = MATCH_ID_EDEFAULT;

  /**
   * A constant key representing order in the TARGET side in (reference, value presence) maps
   * @generated NOT
   */
  protected static final EObject REFERENCE_ORDER_KEY_TARGET = EcoreFactory.eINSTANCE
      .createEObject();

  /**
   * A constant key representing order in the REFERENCE side in (reference, value presence) maps
   * @generated NOT
   */
  protected static final EObject REFERENCE_ORDER_KEY_REFERENCE = EcoreFactory.eINSTANCE
      .createEObject();

  /**
   * A constant key representing order in the TARGET side in (attribute, value presence) maps
   * @generated NOT
   */
  protected static final Object ATTRIBUTE_ORDER_KEY_TARGET = new Object();

  /**
   * A constant key representing order in the REFERENCE side in (attribute, value presence) maps
   * @generated NOT
   */
  protected static final Object ATTRIBUTE_ORDER_KEY_REFERENCE = new Object();

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
   * The cached value of the '{@link #getModifiableRelatedDifferences() <em>Modifiable Related Differences</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableRelatedDifferences()
   * @generated
   * @ordered
   */
  protected EList<EMergeableDifference> modifiableRelatedDifferences;

  /**
   * The cached value of the '{@link #getModifiableAttributeMap() <em>Modifiable Attribute Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableAttributeMap()
   * @generated
   * @ordered
   */
  protected EMap<EAttribute, EMap<Object, IAttributeValuePresence>> modifiableAttributeMap;

  /**
   * The cached value of the '{@link #getModifiableReferenceMap() <em>Modifiable Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<EReference, EMap<EObject, IReferenceValuePresence>> modifiableReferenceMap;

  /**
   * The cached value of the '{@link #getElementPresenceDifference() <em>Element Presence Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementPresenceDifference()
   * @generated
   * @ordered
   */
  protected IElementPresence elementPresenceDifference;

  /**
   * The cached value of the '{@link #getReferenceOwnershipDifference() <em>Reference Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceOwnershipDifference()
   * @generated
   * @ordered
   */
  protected IReferenceValuePresence referenceOwnershipDifference;

  /**
   * The cached value of the '{@link #getTargetOwnershipDifference() <em>Target Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetOwnershipDifference()
   * @generated
   * @ordered
   */
  protected IReferenceValuePresence targetOwnershipDifference;

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
    this();
    setTarget(target_p);
    setReference(reference_p);
    setAncestor(ancestor_p);
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
  public Object getMatchID() {
    return matchID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMatchID(Object newMatchID) {
    Object oldMatchID = matchID;
    matchID = newMatchID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__MATCH_ID, oldMatchID, matchID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
  public void setTarget(EObject newTarget) {
    EObject oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EMergeableDifference> getModifiableRelatedDifferences() {
    if (modifiableRelatedDifferences == null) {
      modifiableRelatedDifferences = new EObjectContainmentEList<EMergeableDifference>(
          EMergeableDifference.class, this,
          DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES);
    }
    return modifiableRelatedDifferences;
  }

  /**
   * @see EMatch#getMapping()
   * @generated NOT
   */
  public EMapping getMapping() {
    EMapping result = null;
    EObject container = eContainer();
    if (container instanceof EMapping)
      result = (EMapping) container;
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<EAttribute, EMap<Object, IAttributeValuePresence>> getModifiableAttributeMap() {
    if (modifiableAttributeMap == null) {
      modifiableAttributeMap = new EcoreEMap<EAttribute, EMap<Object, IAttributeValuePresence>>(
          DiffdataPackage.Literals.ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY,
          AttributeToValueToDifferenceEntryImpl.class, this,
          DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    }
    return modifiableAttributeMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EMap<EReference, EMap<EObject, IReferenceValuePresence>> getModifiableReferenceMap() {
    if (modifiableReferenceMap == null) {
      modifiableReferenceMap = new EcoreEMap<EReference, EMap<EObject, IReferenceValuePresence>>(
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
   * @generated
   */
  public IElementPresence getElementPresenceDifference() {
    if (elementPresenceDifference != null
        && ((EObject) elementPresenceDifference).eIsProxy()) {
      InternalEObject oldElementPresenceDifference = (InternalEObject) elementPresenceDifference;
      elementPresenceDifference = (IElementPresence) eResolveProxy(
          oldElementPresenceDifference);
      if (elementPresenceDifference != oldElementPresenceDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE,
              oldElementPresenceDifference, elementPresenceDifference));
      }
    }
    return elementPresenceDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IElementPresence basicGetElementPresenceDifference() {
    return elementPresenceDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementPresenceDifference(
      IElementPresence newElementPresenceDifference) {
    IElementPresence oldElementPresenceDifference = elementPresenceDifference;
    elementPresenceDifference = newElementPresenceDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE,
          oldElementPresenceDifference, elementPresenceDifference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IReferenceValuePresence getReferenceOwnershipDifference() {
    if (referenceOwnershipDifference != null
        && ((EObject) referenceOwnershipDifference).eIsProxy()) {
      InternalEObject oldReferenceOwnershipDifference = (InternalEObject) referenceOwnershipDifference;
      referenceOwnershipDifference = (IReferenceValuePresence) eResolveProxy(
          oldReferenceOwnershipDifference);
      if (referenceOwnershipDifference != oldReferenceOwnershipDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE,
              oldReferenceOwnershipDifference, referenceOwnershipDifference));
      }
    }
    return referenceOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IReferenceValuePresence basicGetReferenceOwnershipDifference() {
    return referenceOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceOwnershipDifference(
      IReferenceValuePresence newReferenceOwnershipDifference) {
    IReferenceValuePresence oldReferenceOwnershipDifference = referenceOwnershipDifference;
    referenceOwnershipDifference = newReferenceOwnershipDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE,
          oldReferenceOwnershipDifference, referenceOwnershipDifference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IReferenceValuePresence getTargetOwnershipDifference() {
    if (targetOwnershipDifference != null
        && ((EObject) targetOwnershipDifference).eIsProxy()) {
      InternalEObject oldTargetOwnershipDifference = (InternalEObject) targetOwnershipDifference;
      targetOwnershipDifference = (IReferenceValuePresence) eResolveProxy(
          oldTargetOwnershipDifference);
      if (targetOwnershipDifference != oldTargetOwnershipDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE,
              oldTargetOwnershipDifference, targetOwnershipDifference));
      }
    }
    return targetOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IReferenceValuePresence basicGetTargetOwnershipDifference() {
    return targetOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetOwnershipDifference(
      IReferenceValuePresence newTargetOwnershipDifference) {
    IReferenceValuePresence oldTargetOwnershipDifference = targetOwnershipDifference;
    targetOwnershipDifference = newTargetOwnershipDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE,
          oldTargetOwnershipDifference, targetOwnershipDifference));
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
    case DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return ((InternalEList<?>) getModifiableRelatedDifferences())
          .basicRemove(otherEnd, msgs);
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return ((InternalEList<?>) getModifiableAttributeMap())
          .basicRemove(otherEnd, msgs);
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableReferenceMap())
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
    case DiffdataPackage.EMATCH__MATCH_ID:
      return getMatchID();
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
    case DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return getModifiableRelatedDifferences();
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
    case DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      if (resolve)
        return getElementPresenceDifference();
      return basicGetElementPresenceDifference();
    case DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      if (resolve)
        return getReferenceOwnershipDifference();
      return basicGetReferenceOwnershipDifference();
    case DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      if (resolve)
        return getTargetOwnershipDifference();
      return basicGetTargetOwnershipDifference();
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
    case DiffdataPackage.EMATCH__MATCH_ID:
      setMatchID(newValue);
      return;
    case DiffdataPackage.EMATCH__ANCESTOR:
      setAncestor((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__REFERENCE:
      setReference((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__TARGET:
      setTarget((EObject) newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      getModifiableRelatedDifferences().clear();
      getModifiableRelatedDifferences()
          .addAll((Collection<? extends EMergeableDifference>) newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      ((EStructuralFeature.Setting) getModifiableAttributeMap()).set(newValue);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableReferenceMap()).set(newValue);
      return;
    case DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      setElementPresenceDifference((IElementPresence) newValue);
      return;
    case DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      setReferenceOwnershipDifference((IReferenceValuePresence) newValue);
      return;
    case DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      setTargetOwnershipDifference((IReferenceValuePresence) newValue);
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
    case DiffdataPackage.EMATCH__MATCH_ID:
      setMatchID(MATCH_ID_EDEFAULT);
      return;
    case DiffdataPackage.EMATCH__ANCESTOR:
      setAncestor((EObject) null);
      return;
    case DiffdataPackage.EMATCH__REFERENCE:
      setReference((EObject) null);
      return;
    case DiffdataPackage.EMATCH__TARGET:
      setTarget((EObject) null);
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      getModifiableRelatedDifferences().clear();
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      getModifiableAttributeMap().clear();
      return;
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      getModifiableReferenceMap().clear();
      return;
    case DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      setElementPresenceDifference((IElementPresence) null);
      return;
    case DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      setReferenceOwnershipDifference((IReferenceValuePresence) null);
      return;
    case DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      setTargetOwnershipDifference((IReferenceValuePresence) null);
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
    case DiffdataPackage.EMATCH__MATCH_ID:
      return MATCH_ID_EDEFAULT == null ? matchID != null
          : !MATCH_ID_EDEFAULT.equals(matchID);
    case DiffdataPackage.EMATCH__ANCESTOR:
      return ancestor != null;
    case DiffdataPackage.EMATCH__REFERENCE:
      return reference != null;
    case DiffdataPackage.EMATCH__TARGET:
      return target != null;
    case DiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return modifiableRelatedDifferences != null
          && !modifiableRelatedDifferences.isEmpty();
    case DiffdataPackage.EMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return modifiableAttributeMap != null
          && !modifiableAttributeMap.isEmpty();
    case DiffdataPackage.EMATCH__MODIFIABLE_REFERENCE_MAP:
      return modifiableReferenceMap != null
          && !modifiableReferenceMap.isEmpty();
    case DiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      return elementPresenceDifference != null;
    case DiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      return referenceOwnershipDifference != null;
    case DiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      return targetOwnershipDifference != null;
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
    result.append(" (matchID: "); //$NON-NLS-1$
    result.append(matchID);
    result.append(')');
    return result.toString();
  }

  /**
   * Register the given attribute value presence for fast retrieval
   * @param presence_p a non-null difference representing a value presence
   *        which is such that presence_p.getElementMatch() == this
   * @generated NOT
   */
  protected void addAttributeValuePresence(IAttributeValuePresence presence_p) {
    EMap<EAttribute, EMap<Object, IAttributeValuePresence>> attributeMap = getModifiableAttributeMap(
        true);
    EMap<Object, IAttributeValuePresence> forAttribute = attributeMap
        .get(presence_p.getFeature());
    if (forAttribute == null)
      forAttribute = newAttributeValueToPresenceMap(presence_p.getFeature());
    Object key;
    if (presence_p.isOrder())
      key = presence_p.getPresenceRole() == Role.TARGET
          ? ATTRIBUTE_ORDER_KEY_TARGET
          : ATTRIBUTE_ORDER_KEY_REFERENCE;
    else
      key = presence_p.getValue();
    forAttribute.put(key, presence_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch.Editable#addOwnershipDifference(org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public void addOwnershipDifference(IReferenceValuePresence presence_p) {
    if (Role.TARGET == presence_p.getPresenceRole())
      setTargetOwnershipDifference(presence_p);
    else
      setReferenceOwnershipDifference(presence_p);
  }

  /**
   * Register the given reference value presence for fast retrieval
   * @param presence_p a non-null difference representing a value presence
   *        which is such that presence_p.getElementMatch() == this
   * @generated NOT
   */
  protected void addReferenceValuePresence(IReferenceValuePresence presence_p) {
    assert presence_p.getElementMatch() == this;
    EMap<EReference, EMap<EObject, IReferenceValuePresence>> referenceMap = getModifiableReferenceMap(
        true);
    EMap<EObject, IReferenceValuePresence> forReference = referenceMap
        .get(presence_p.getFeature());
    if (forReference == null)
      forReference = newReferenceValueToPresenceMap(presence_p.getFeature());
    EObject key;
    if (presence_p.isOrder())
      key = presence_p.getPresenceRole() == Role.TARGET
          ? REFERENCE_ORDER_KEY_TARGET
          : REFERENCE_ORDER_KEY_REFERENCE;
    else
      key = presence_p.getValue();
    forReference.put(key, presence_p); // key cannot be null
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch.Editable#addRelatedDifference(org.eclipse.emf.diffmerge.api.diff.IDifference)
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  public void addRelatedDifference(IDifference difference_p) {
    assert difference_p != null;
    Collection<? extends IDifference> differences = getModifiableRelatedDifferences(
        true);
    if (!differences.contains(difference_p)) {
      ((Collection<IDifference>) differences).add(difference_p); // Difference is supposed compatible
      if (difference_p instanceof IElementPresence) {
        setElementPresenceDifference((IElementPresence) difference_p);
      } else if (difference_p instanceof IReferenceValuePresence) {
        IReferenceValuePresence presence = (IReferenceValuePresence) difference_p;
        addReferenceValuePresence(presence);
        // If relevant, register implicit universal container reference on value
        if (presence.isOwnership()) {
          IMatch valueMatch = presence.getValueMatch();
          if (valueMatch != null)
            ((IMatch.Editable) valueMatch).addOwnershipDifference(presence);
        }
      } else if (difference_p instanceof IAttributeValuePresence) {
        addAttributeValuePresence((IAttributeValuePresence) difference_p);
      }
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#coversRole(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public boolean coversRole(Role role_p) {
    return get(role_p) != null;
  }

  /**
   * @see Object#equals(Object)
   * @generated NOT
   */
  @Override
  public boolean equals(Object object_p) {
    boolean result = false;
    if (object_p instanceof IPureMatch) {
      IPureMatch peer = (IPureMatch) object_p;
      result = // Equality by reference of elements
          peer == this || peer.get(TARGET) == get(TARGET)
              && peer.get(REFERENCE) == get(REFERENCE)
              && peer.get(ANCESTOR) == get(ANCESTOR);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#get(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public EObject get(Role role_p) {
    EObject result;
    switch (role_p) {
    case TARGET:
      result = getTarget();
      break;
    case REFERENCE:
      result = getReference();
      break;
    default:
      result = getAncestor();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getAllDifferences()
   * @generated NOT
   */
  public List<IDifference> getAllDifferences() {
    List<IDifference> result = new FArrayList<IDifference>(
        getRelatedDifferences(), null);
    IDifference targetOwnership = getOwnershipDifference(Role.TARGET);
    if (targetOwnership != null)
      result.add(targetOwnership);
    IDifference referenceOwnership = getOwnershipDifference(Role.REFERENCE);
    if (referenceOwnership != null)
      result.add(referenceOwnership);
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getAttributeDifferences(org.eclipse.emf.ecore.EAttribute)
   * @generated NOT
   */
  public Collection<IAttributeValuePresence> getAttributeDifferences(
      EAttribute attribute_p) {
    Collection<IAttributeValuePresence> result = null;
    if (getModifiableAttributeMap(false) != null) {
      EMap<Object, IAttributeValuePresence> forAttribute = getModifiableAttributeMap(
          false).get(attribute_p);
      if (forAttribute != null)
        result = Collections.unmodifiableCollection(forAttribute.values());
    }
    if (result == null)
      result = Collections.emptyList();
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getAttributeValueDifference(org.eclipse.emf.ecore.EAttribute, java.lang.Object)
   * @generated NOT
   */
  public IAttributeValuePresence getAttributeValueDifference(
      EAttribute attribute_p, Object value_p) {
    IAttributeValuePresence result = null;
    if (getModifiableAttributeMap(false) != null) {
      EMap<Object, IAttributeValuePresence> forAttribute = getModifiableAttributeMap(
          false).get(attribute_p);
      if (forAttribute != null)
        result = forAttribute.get(value_p);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getAttributesWithDifferences()
   * @generated NOT
   */
  public Collection<EAttribute> getAttributesWithDifferences() {
    Set<EAttribute> result;
    if (getModifiableAttributeMap(false) == null)
      result = Collections.emptySet();
    else
      result = Collections
          .unmodifiableSet(getModifiableAttributeMap(false).keySet());
    return result;
  }

  /**
   * Return the double map: attribute -> value -> difference
   * @param create_p whether the map must be created if it does not exist
   * @return a modifiable map which is not null if create_p
   * @generated NOT
   */
  protected EMap<EAttribute, EMap<Object, IAttributeValuePresence>> getModifiableAttributeMap(
      boolean create_p) {
    return create_p ? getModifiableAttributeMap() : modifiableAttributeMap;
  }

  /**
   * Return the double map: reference -> value -> difference
   * @param create_p whether the map must be created if it does not exist
   * @return a modifiable map which is not null if create_p
   * @generated NOT
   */
  protected EMap<EReference, EMap<EObject, IReferenceValuePresence>> getModifiableReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableReferenceMap() : modifiableReferenceMap;
  }

  /**
   * Return the related differences as a modifiable collection
   * @param create_p whether the collection must be created if it does not exist
   * @return a modifiable collection which is not null if create_p
   * @generated NOT
   */
  protected List<EMergeableDifference> getModifiableRelatedDifferences(
      boolean create_p) {
    return create_p ? getModifiableRelatedDifferences()
        : modifiableRelatedDifferences;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getNbNoContainmentDifferences()
   * @generated NOT
   */
  public int getNbNoContainmentDifferences() {
    int result = 0;
    if (!isPartial())
      for (IDifference difference : getRelatedDifferences())
        if (difference instanceof IElementRelativeDifference
            && ((IElementRelativeDifference) difference)
                .isUnrelatedToContainmentTree()
            && !difference.isMerged())
          result++;
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getOrderDifference(org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IValuePresence getOrderDifference(EStructuralFeature feature_p,
      Role role_p) {
    IValuePresence result = null;
    if (feature_p instanceof EAttribute) {
      EMap<EAttribute, EMap<Object, IAttributeValuePresence>> attributeMap = getModifiableAttributeMap(
          false);
      if (attributeMap != null) {
        EMap<Object, IAttributeValuePresence> forAttribute = attributeMap
            .get(feature_p);
        if (forAttribute != null)
          result = forAttribute
              .get(role_p == Role.TARGET ? ATTRIBUTE_ORDER_KEY_TARGET
                  : ATTRIBUTE_ORDER_KEY_REFERENCE);
      }
    } else if (feature_p instanceof EReference) {
      EMap<EReference, EMap<EObject, IReferenceValuePresence>> referenceMap = getModifiableReferenceMap(
          false);
      if (referenceMap != null) {
        EMap<EObject, IReferenceValuePresence> forReference = referenceMap
            .get(feature_p);
        if (forReference != null)
          result = forReference
              .get(role_p == Role.TARGET ? REFERENCE_ORDER_KEY_TARGET
                  : REFERENCE_ORDER_KEY_REFERENCE);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getOwnershipDifference(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IReferenceValuePresence getOwnershipDifference(Role role_p) {
    return (Role.TARGET == role_p) ? getTargetOwnershipDifference()
        : getReferenceOwnershipDifference();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getPresenceDifferencesIn(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public Collection<IDifference> getPresenceDifferencesIn(Role role_p) {
    Collection<IDifference> result = new ArrayList<IDifference>();
    for (IDifference difference : getRelatedDifferences()) {
      if (difference instanceof IPresenceDifference
          && ((IPresenceDifference) difference).getPresenceRole() == role_p)
        result.add(difference);
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getReferenceDifferences(org.eclipse.emf.ecore.EReference)
   * @generated NOT
   */
  public Collection<IReferenceValuePresence> getReferenceDifferences(
      EReference reference_p) {
    Collection<IReferenceValuePresence> result = null;
    if (getModifiableReferenceMap(false) != null) {
      EMap<EObject, IReferenceValuePresence> forReference = getModifiableReferenceMap(
          false).get(reference_p);
      if (forReference != null)
        result = Collections.unmodifiableCollection(forReference.values());
    }
    if (result == null)
      result = Collections.emptyList();
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getReferenceValueDifference(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public IReferenceValuePresence getReferenceValueDifference(
      EReference reference_p, EObject value_p) {
    IReferenceValuePresence result = null;
    if (getModifiableReferenceMap(false) != null) {
      EMap<EObject, IReferenceValuePresence> forReference = getModifiableReferenceMap(
          false).get(reference_p);
      if (forReference != null)
        result = forReference.get(value_p);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getReferencesWithDifferences()
   * @generated NOT
   */
  public Collection<EReference> getReferencesWithDifferences() {
    Set<EReference> result;
    if (getModifiableReferenceMap(false) == null)
      result = Collections.emptySet();
    else
      result = Collections
          .unmodifiableSet(getModifiableReferenceMap(false).keySet());
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#getRelatedDifferences()
   * @generated NOT
   */
  public List<IDifference> getRelatedDifferences() {
    List<IDifference> result;
    List<EMergeableDifference> modifiable = getModifiableRelatedDifferences(
        false);
    if (modifiable == null)
      result = Collections.emptyList();
    else
      result = Collections.<IDifference> unmodifiableList(modifiable);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#getUncoveredRole()
   * @generated NOT
   */
  public Role getUncoveredRole() {
    Role result = null;
    if (!coversRole(TARGET))
      result = TARGET;
    else if (!coversRole(REFERENCE))
      result = REFERENCE;
    return result;
  }

  /**
   * @see Object#hashCode()
   * @generated NOT
   */
  @Override
  public int hashCode() {
    int result = 0;
    if (get(TARGET) != null)
      result += get(TARGET).hashCode();
    if (get(REFERENCE) != null)
      result += get(REFERENCE).hashCode();
    if (get(ANCESTOR) != null)
      result += get(ANCESTOR).hashCode();
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#involves(org.eclipse.emf.ecore.resource.Resource)
   * @generated NOT
   */
  public boolean involves(Resource resource_p) {
    return get(TARGET) != null && get(TARGET).eResource() == resource_p
        || get(REFERENCE) != null && get(REFERENCE).eResource() == resource_p
        || get(ANCESTOR) != null && get(ANCESTOR).eResource() == resource_p;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMatch#isAMove()
   * @generated NOT
   */
  public boolean isAMove() {
    boolean result = false;
    if (!isPartial() && getElementPresenceDifference() == null) {
      IReferenceValuePresence onTarget = getOwnershipDifference(Role.TARGET);
      IReferenceValuePresence onReference = getOwnershipDifference(
          Role.REFERENCE);
      result = onTarget != null || onReference != null;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#isPartial()
   * @generated NOT
   */
  public boolean isPartial() {
    return getUncoveredRole() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#maps(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public boolean maps(EObject target_p, EObject reference_p) {
    return get(TARGET) == target_p && get(REFERENCE) == reference_p;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch#maps(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public boolean maps(EObject target_p, EObject reference_p,
      EObject ancestor_p) {
    return maps(target_p, reference_p) && get(ANCESTOR) == ancestor_p;
  }

  /**
   * Create and return a map for: attribute value -> difference
   * @param attribute_p the non-null attribute
   * @return a non-null, empty, modifiable map
   * @generated NOT
   */
  protected EMap<Object, IAttributeValuePresence> newAttributeValueToPresenceMap(
      EAttribute attribute_p) {
    AttributeToValueToDifferenceEntryImpl entry = new AttributeToValueToDifferenceEntryImpl();
    entry.setKey(attribute_p);
    getModifiableAttributeMap(true).add(entry);
    return entry.getValue();
  }

  /**
   * Create and return a map for: reference value -> difference
   * @param reference_p the non-null reference
   * @return a non-null, empty, modifiable map
   * @generated NOT
   */
  protected EMap<EObject, IReferenceValuePresence> newReferenceValueToPresenceMap(
      EReference reference_p) {
    ReferenceToElementToDifferenceEntryImpl entry = new ReferenceToElementToDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableReferenceMap(true).add(entry);
    return entry.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch.Editable#reset(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public void reset(EObject target_p, EObject reference_p, EObject ancestor_p) {
    assert target_p != null || reference_p != null || ancestor_p != null;
    set(TARGET, target_p);
    set(REFERENCE, reference_p);
    set(ANCESTOR, ancestor_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IPureMatch.Editable#set(org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public void set(Role role_p, EObject element_p) {
    switch (role_p) {
    case TARGET:
      setTarget(element_p);
      break;
    case REFERENCE:
      setReference(element_p);
      break;
    default:
      setAncestor(element_p);
    }
  }

} //EMatchImpl
