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

import static org.eclipse.emf.diffmerge.generic.api.Role.ANCESTOR;
import static org.eclipse.emf.diffmerge.generic.api.Role.REFERENCE;
import static org.eclipse.emf.diffmerge.generic.api.Role.TARGET;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IPureMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMatch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getMatchID <em>Match ID</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getElementPresenceDifference <em>Element Presence Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl#getTargetOwnershipDifference <em>Target Ownership Difference</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EMatchImpl<E, A, R, S> extends EIdentifiedImpl
    implements EMatch<E, A, R, S> {

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
   * The cached value of the '{@link #getModifiableRelatedDifferences() <em>Modifiable Related Differences</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableRelatedDifferences()
   * @generated
   * @ordered
   */
  protected EList<EMergeableDifference<E, A, R, S>> modifiableRelatedDifferences;

  /**
   * The cached value of the '{@link #getElementPresenceDifference() <em>Element Presence Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementPresenceDifference()
   * @generated
   * @ordered
   */
  protected IElementPresence<E> elementPresenceDifference;

  /**
   * The cached value of the '{@link #getReferenceOwnershipDifference() <em>Reference Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceOwnershipDifference()
   * @generated
   * @ordered
   */
  protected IReferenceValuePresence<E> referenceOwnershipDifference;

  /**
   * The cached value of the '{@link #getTargetOwnershipDifference() <em>Target Ownership Difference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetOwnershipDifference()
   * @generated
   * @ordered
   */
  protected IReferenceValuePresence<E> targetOwnershipDifference;

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
  public EMatchImpl(E target_p, E reference_p, E ancestor_p) {
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
    return GdiffdataPackage.Literals.EMATCH;
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
          GdiffdataPackage.EMATCH__MATCH_ID, oldMatchID, matchID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EMergeableDifference<E, A, R, S>> getModifiableRelatedDifferences() {
    if (modifiableRelatedDifferences == null) {
      modifiableRelatedDifferences = new EObjectContainmentEList<EMergeableDifference<E, A, R, S>>(
          EMergeableDifference.class, this,
          GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES);
    }
    return modifiableRelatedDifferences;
  }

  /**
   * @see EMatch#getMapping()
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  public EMapping<E, A, R, S> getMapping() {
    EMapping<E, A, R, S> result = null;
    EObject container = eContainer();
    if (container instanceof EMapping) {
      result = (EMapping<E, A, R, S>) container;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getAncestor()
   * @generated NOT
   */
  public abstract E getAncestor();

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReference()
   * @generated NOT
   */
  public abstract E getReference();

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTarget()
   * @generated NOT
   */
  public abstract E getTarget();

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#setAncestor(java.lang.Object)
   * @generated NOT
   */
  public abstract void setAncestor(E e);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#setReference(java.lang.Object)
   * @generated NOT
   */
  public abstract void setReference(E e);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#setTarget(java.lang.Object)
   * @generated NOT
   */
  public abstract void setTarget(E e);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement#getComparison()
   * @generated NOT
   */
  public EComparison<E, A, R, S> getComparison() {
    EComparison<E, A, R, S> result = null;
    EMapping<E, A, R, S> mapping = getMapping();
    if (mapping != null) {
      result = mapping.getComparison();
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public IElementPresence<E> getElementPresenceDifference() {
    if (elementPresenceDifference != null
        && ((EObject) elementPresenceDifference).eIsProxy()) {
      InternalEObject oldElementPresenceDifference = (InternalEObject) elementPresenceDifference;
      elementPresenceDifference = (IElementPresence<E>) eResolveProxy(
          oldElementPresenceDifference);
      if (elementPresenceDifference != oldElementPresenceDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE,
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
  public IElementPresence<E> basicGetElementPresenceDifference() {
    return elementPresenceDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementPresenceDifference(
      IElementPresence<E> newElementPresenceDifference) {
    IElementPresence<E> oldElementPresenceDifference = elementPresenceDifference;
    elementPresenceDifference = newElementPresenceDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE,
          oldElementPresenceDifference, elementPresenceDifference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public IReferenceValuePresence<E> getReferenceOwnershipDifference() {
    if (referenceOwnershipDifference != null
        && ((EObject) referenceOwnershipDifference).eIsProxy()) {
      InternalEObject oldReferenceOwnershipDifference = (InternalEObject) referenceOwnershipDifference;
      referenceOwnershipDifference = (IReferenceValuePresence<E>) eResolveProxy(
          oldReferenceOwnershipDifference);
      if (referenceOwnershipDifference != oldReferenceOwnershipDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE,
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
  public IReferenceValuePresence<E> basicGetReferenceOwnershipDifference() {
    return referenceOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceOwnershipDifference(
      IReferenceValuePresence<E> newReferenceOwnershipDifference) {
    IReferenceValuePresence<E> oldReferenceOwnershipDifference = referenceOwnershipDifference;
    referenceOwnershipDifference = newReferenceOwnershipDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE,
          oldReferenceOwnershipDifference, referenceOwnershipDifference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public IReferenceValuePresence<E> getTargetOwnershipDifference() {
    if (targetOwnershipDifference != null
        && ((EObject) targetOwnershipDifference).eIsProxy()) {
      InternalEObject oldTargetOwnershipDifference = (InternalEObject) targetOwnershipDifference;
      targetOwnershipDifference = (IReferenceValuePresence<E>) eResolveProxy(
          oldTargetOwnershipDifference);
      if (targetOwnershipDifference != oldTargetOwnershipDifference) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE,
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
  public IReferenceValuePresence<E> basicGetTargetOwnershipDifference() {
    return targetOwnershipDifference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetOwnershipDifference(
      IReferenceValuePresence<E> newTargetOwnershipDifference) {
    IReferenceValuePresence<E> oldTargetOwnershipDifference = targetOwnershipDifference;
    targetOwnershipDifference = newTargetOwnershipDifference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE,
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
    case GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return ((InternalEList<?>) getModifiableRelatedDifferences())
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
    case GdiffdataPackage.EMATCH__MATCH_ID:
      return getMatchID();
    case GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return getModifiableRelatedDifferences();
    case GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      if (resolve)
        return getElementPresenceDifference();
      return basicGetElementPresenceDifference();
    case GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      if (resolve)
        return getReferenceOwnershipDifference();
      return basicGetReferenceOwnershipDifference();
    case GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
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
    case GdiffdataPackage.EMATCH__MATCH_ID:
      setMatchID(newValue);
      return;
    case GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      getModifiableRelatedDifferences().clear();
      getModifiableRelatedDifferences().addAll(
          (Collection<? extends EMergeableDifference<E, A, R, S>>) newValue);
      return;
    case GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      setElementPresenceDifference((IElementPresence<E>) newValue);
      return;
    case GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      setReferenceOwnershipDifference((IReferenceValuePresence<E>) newValue);
      return;
    case GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      setTargetOwnershipDifference((IReferenceValuePresence<E>) newValue);
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
    case GdiffdataPackage.EMATCH__MATCH_ID:
      setMatchID(MATCH_ID_EDEFAULT);
      return;
    case GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      getModifiableRelatedDifferences().clear();
      return;
    case GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      setElementPresenceDifference((IElementPresence<E>) null);
      return;
    case GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      setReferenceOwnershipDifference((IReferenceValuePresence<E>) null);
      return;
    case GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
      setTargetOwnershipDifference((IReferenceValuePresence<E>) null);
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
    case GdiffdataPackage.EMATCH__MATCH_ID:
      return MATCH_ID_EDEFAULT == null ? matchID != null
          : !MATCH_ID_EDEFAULT.equals(matchID);
    case GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES:
      return modifiableRelatedDifferences != null
          && !modifiableRelatedDifferences.isEmpty();
    case GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE:
      return elementPresenceDifference != null;
    case GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE:
      return referenceOwnershipDifference != null;
    case GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE:
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

    StringBuilder result = new StringBuilder(super.toString());
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
  @SuppressWarnings("unchecked")
  protected void addAttributeValuePresence(
      IAttributeValuePresence<E> presence_p) {
    EMap<A, EMap<Object, IAttributeValuePresence<E>>> attributeMap = getModifiableAttributeMap(
        true);
    EMap<Object, IAttributeValuePresence<E>> forAttribute = attributeMap
        .get(presence_p.getFeature());
    if (forAttribute == null) {
      forAttribute = newAttributeValueToPresenceMap((A) presence_p.getFeature());
    }
    Object key;
    if (presence_p.isOrder()) {
      key = presence_p.getPresenceRole() == Role.TARGET
          ? ATTRIBUTE_ORDER_KEY_TARGET
          : ATTRIBUTE_ORDER_KEY_REFERENCE;
    } else {
      key = presence_p.getValue();
    }
    forAttribute.put(key, presence_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable#addOwnershipDifference(org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public void addOwnershipDifference(IReferenceValuePresence<E> presence_p) {
    if (Role.TARGET == presence_p.getPresenceRole()) {
      setTargetOwnershipDifference(presence_p);
    } else {
      setReferenceOwnershipDifference(presence_p);
    }
  }

  /**
   * Register the given order difference for fast retrieval
   * @param presence_p a non-null difference representing a value presence
   *        which is such that presence_p.getElementMatch() == this
   *        and isOrder() is true
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  protected void addReferenceOrderDifference(
      IReferenceValuePresence<E> presence_p) {
    assert presence_p.getElementMatch() == this && presence_p.isOrder();
    EMap<R, EList<IReferenceValuePresence<E>>> referenceMap = getModifiableOrderReferenceMap(
        true);
    List<IReferenceValuePresence<E>> forReference = referenceMap
        .get(presence_p.getFeature());
    if (forReference == null) {
      forReference = newReferenceOrderDifferenceList((R) presence_p.getFeature());
    }
    forReference.add(presence_p);
  }

  /**
   * Register the given non-order reference value presence for fast retrieval
   * @param presence_p a non-null difference representing a value presence
   *        which is such that presence_p.getElementMatch() == this
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  protected void addReferenceValuePresence(
      IReferenceValuePresence<E> presence_p) {
    assert presence_p.getElementMatch() == this && !presence_p.isOrder();
    EMap<R, EMap<E, IReferenceValuePresence<E>>> referenceMap = getModifiableReferenceMap(
        true);
    EMap<E, IReferenceValuePresence<E>> forReference = referenceMap
        .get(presence_p.getFeature());
    if (forReference == null) {
      forReference = newReferenceValueToPresenceMap((R) presence_p.getFeature());
    }
    E key = presence_p.getValue();
    forReference.put(key, presence_p); // key cannot be null
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable#addRelatedDifference(org.eclipse.emf.diffmerge.generic.api.diff.IDifference)
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  public void addRelatedDifference(IDifference<E> difference_p) {
    assert difference_p != null;
    Collection<? extends IDifference<E>> differences = getModifiableRelatedDifferences(
        true);
    if (!differences.contains(difference_p)) {
      ((Collection<IDifference<E>>) differences).add(difference_p); // Difference is supposed compatible
      if (difference_p instanceof IElementPresence) {
        setElementPresenceDifference((IElementPresence<E>) difference_p);
      } else if (difference_p instanceof IReferenceValuePresence) {
        IReferenceValuePresence<E> presence = (IReferenceValuePresence<E>) difference_p;
        if (presence.isOrder()) {
          // Order ref difference
          addReferenceOrderDifference(presence);
        } else {
          // Non-order ref difference
          addReferenceValuePresence(presence);
          // If relevant, register implicit universal container reference on value
          if (presence.isOwnership()) {
            IMatch<E> valueMatch = presence.getValueMatch();
            if (valueMatch != null) {
              ((IMatch.Editable<E>) valueMatch)
                  .addOwnershipDifference(presence);
            }
          }
        }
      } else if (difference_p instanceof IAttributeValuePresence<?>) {
        addAttributeValuePresence((IAttributeValuePresence<E>) difference_p);
      }
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#coversRole(org.eclipse.emf.diffmerge.generic.api.Role)
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
      IPureMatch<?> peer = (IPureMatch<?>) object_p;
      result = // Equality by reference of elements
          peer == this || peer.get(TARGET) == get(TARGET)
              && peer.get(REFERENCE) == get(REFERENCE)
              && peer.get(ANCESTOR) == get(ANCESTOR);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#get(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public E get(Role role_p) {
    E result;
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
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getAllDifferences()
   * @generated NOT
   */
  public List<IDifference<E>> getAllDifferences() {
    List<IDifference<E>> result = new FArrayList<IDifference<E>>(
        getRelatedDifferences(), null);
    IDifference<E> targetOwnership = getOwnershipDifference(Role.TARGET);
    if (targetOwnership != null) {
      result.add(targetOwnership);
    }
    IDifference<E> referenceOwnership = getOwnershipDifference(Role.REFERENCE);
    if (referenceOwnership != null) {
      result.add(referenceOwnership);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getAttributeDifferences(java.lang.Object)
   * @generated NOT
   */
  public Collection<IAttributeValuePresence<E>> getAttributeDifferences(
      Object attribute_p) {
    Collection<IAttributeValuePresence<E>> result = null;
    if (getModifiableAttributeMap(false) != null) {
      EMap<Object, IAttributeValuePresence<E>> forAttribute = getModifiableAttributeMap(
          false).get(attribute_p);
      if (forAttribute != null) {
        result = Collections.unmodifiableCollection(forAttribute.values());
      }
    }
    if (result == null) {
      result = Collections.emptyList();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getAttributeOrderDifference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IAttributeValuePresence<E> getAttributeOrderDifference(
      Object attribute_p, Role role_p) {
    IAttributeValuePresence<E> result = null;
    EMap<A, EMap<Object, IAttributeValuePresence<E>>> attributeMap = getModifiableAttributeMap(
        false);
    if (attributeMap != null) {
      EMap<Object, IAttributeValuePresence<E>> forAttribute = attributeMap
          .get(attribute_p);
      if (forAttribute != null) {
        result = forAttribute
            .get(role_p == Role.TARGET ? ATTRIBUTE_ORDER_KEY_TARGET
                : ATTRIBUTE_ORDER_KEY_REFERENCE);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getAttributeValueDifference(java.lang.Object, java.lang.Object)
   * @generated NOT
   */
  public IAttributeValuePresence<E> getAttributeValueDifference(
      Object attribute_p, Object value_p) {
    IAttributeValuePresence<E> result = null;
    if (getModifiableAttributeMap(false) != null) {
      EMap<Object, IAttributeValuePresence<E>> forAttribute = getModifiableAttributeMap(
          false).get(attribute_p);
      if (forAttribute != null) {
        result = forAttribute.get(value_p);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getAttributesWithDifferences()
   * @generated NOT
   */
  public Collection<Object> getAttributesWithDifferences() {
    Set<Object> result;
    if (getModifiableAttributeMap(false) == null) {
      result = Collections.emptySet();
    } else {
      result = Collections
          .unmodifiableSet(getModifiableAttributeMap(false).keySet());
    }
    return result;
  }

  /**
   * Return the double map: attribute -> value -> difference
   * @param create_p whether the map must be created if it does not exist
   * @return a modifiable map which is not null if create_p
   * @generated NOT
   */
  protected abstract EMap<A, EMap<Object, IAttributeValuePresence<E>>> getModifiableAttributeMap(
      boolean create_p);

  /**
   * Return the reference -> order differences map
   * @param create_p whether the map must be created if it does not exist
   * @return a modifiable map which is not null if create_p
   * @generated NOT
   */
  protected abstract EMap<R, EList<IReferenceValuePresence<E>>> getModifiableOrderReferenceMap(
      boolean create_p);

  /**
   * Return the double map: reference -> value -> difference
   * @param create_p whether the map must be created if it does not exist
   * @return a modifiable map which is not null if create_p
   * @generated NOT
   */
  protected abstract EMap<R, EMap<E, IReferenceValuePresence<E>>> getModifiableReferenceMap(
      boolean create_p);

  /**
   * Return the related differences as a modifiable collection
   * @param create_p whether the collection must be created if it does not exist
   * @return a modifiable collection which is not null if create_p
   * @generated NOT
   */
  protected List<EMergeableDifference<E, A, R, S>> getModifiableRelatedDifferences(
      boolean create_p) {
    return create_p ? getModifiableRelatedDifferences()
        : modifiableRelatedDifferences;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getNbNoContainmentDifferences()
   * @generated NOT
   */
  public int getNbNoContainmentDifferences() {
    int result = 0;
    if (!isPartial()) {
      for (IDifference<E> difference : getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference
            && ((IElementRelativeDifference<E>) difference)
                .isUnrelatedToContainmentTree()
            && !difference.isMerged()) {
          result++;
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getOwnershipDifference(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IReferenceValuePresence<E> getOwnershipDifference(Role role_p) {
    return (Role.TARGET == role_p) ? getTargetOwnershipDifference()
        : getReferenceOwnershipDifference();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getPresenceDifferencesIn(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<IDifference<E>> getPresenceDifferencesIn(Role role_p) {
    Collection<IDifference<E>> result = new ArrayList<IDifference<E>>();
    for (IDifference<E> difference : getRelatedDifferences()) {
      if (difference instanceof IPresenceDifference
          && ((IPresenceDifference<E>) difference)
              .getPresenceRole() == role_p) {
        result.add(difference);
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getReferenceDifferences(java.lang.Object)
   * @generated NOT
   */
  public Collection<IReferenceValuePresence<E>> getReferenceDifferences(
      Object reference_p) {
    Collection<IReferenceValuePresence<E>> result = null;
    if (getModifiableReferenceMap(false) != null) {
      EMap<E, IReferenceValuePresence<E>> forReference = getModifiableReferenceMap(
          false).get(reference_p);
      if (forReference != null) {
        result = Collections.unmodifiableCollection(forReference.values());
      }
    }
    if (result == null) {
      result = Collections.emptyList();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getReferenceOrderDifference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IReferenceValuePresence<E> getReferenceOrderDifference(
      Object reference_p, Role role_p) {
    EMap<R, EList<IReferenceValuePresence<E>>> referenceMap = getModifiableOrderReferenceMap(
        false);
    if (referenceMap != null) {
      List<IReferenceValuePresence<E>> forReference = referenceMap
          .get(reference_p);
      for (IReferenceValuePresence<E> orderDifference : forReference) {
        if (orderDifference.getPresenceRole() == role_p) {
          return orderDifference;
        }
      }
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getReferenceValueDifference(java.lang.Object, java.lang.Object)
   * @generated NOT
   */
  public IReferenceValuePresence<E> getReferenceValueDifference(
      Object reference_p, E value_p) {
    IReferenceValuePresence<E> result = null;
    if (getModifiableReferenceMap(false) != null) {
      EMap<E, IReferenceValuePresence<E>> forReference = getModifiableReferenceMap(
          false).get(reference_p);
      if (forReference != null) {
        result = forReference.get(value_p);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getReferencesWithDifferences()
   * @generated NOT
   */
  public Collection<Object> getReferencesWithDifferences() {
    Set<Object> result;
    if (getModifiableReferenceMap(false) == null) {
      result = Collections.emptySet();
    } else {
      result = Collections
          .unmodifiableSet(getModifiableReferenceMap(false).keySet());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#getRelatedDifferences()
   * @generated NOT
   */
  public List<IDifference<E>> getRelatedDifferences() {
    List<IDifference<E>> result;
    List<EMergeableDifference<E, A, R, S>> modifiable = getModifiableRelatedDifferences(
        false);
    if (modifiable == null) {
      result = Collections.emptyList();
    } else {
      result = Collections.<IDifference<E>> unmodifiableList(modifiable);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#getUncoveredRole()
   * @generated NOT
   */
  public Role getUncoveredRole() {
    Role result = null;
    if (!coversRole(TARGET)) {
      result = TARGET;
    } else if (!coversRole(REFERENCE)) {
      result = REFERENCE;
    }
    return result;
  }

  /**
   * @see Object#hashCode()
   * @generated NOT
   */
  @Override
  public int hashCode() {
    int result = 0;
    if (get(TARGET) != null) {
      result += get(TARGET).hashCode();
    }
    if (get(REFERENCE) != null) {
      result += get(REFERENCE).hashCode();
    }
    if (get(ANCESTOR) != null) {
      result += get(ANCESTOR).hashCode();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch#isAMove()
   * @generated NOT
   */
  public boolean isAMove() {
    boolean result = false;
    if (!isPartial() && getElementPresenceDifference() == null) {
      IReferenceValuePresence<E> onTarget = getOwnershipDifference(Role.TARGET);
      IReferenceValuePresence<E> onReference = getOwnershipDifference(
          Role.REFERENCE);
      result = onTarget != null || onReference != null;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#isPartial()
   * @generated NOT
   */
  public boolean isPartial() {
    return getUncoveredRole() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#isPartial(org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public boolean isPartial(Role role1_p, Role role2_p) {
    return !coversRole(role1_p) || !coversRole(role2_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#maps(java.lang.Object, java.lang.Object)
   * @generated NOT
   */
  public boolean maps(E target_p, E reference_p) {
    return get(TARGET) == target_p && get(REFERENCE) == reference_p;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   * @generated NOT
   */
  public boolean maps(E target_p, E reference_p, E ancestor_p) {
    return maps(target_p, reference_p) && get(ANCESTOR) == ancestor_p;
  }

  /**
   * Create and return a map for: attribute value -> difference
   * @param attribute_p the non-null attribute
   * @return a non-null, empty, modifiable map
   * @generated NOT
   */
  protected abstract EMap<Object, IAttributeValuePresence<E>> newAttributeValueToPresenceMap(
      A attribute_p);

  /**
   * Create and return a list for storing order differences, and add it
   * to the modifiable order difference map
   * @param reference_p the non-null reference
   * @return a non-null, empty, modifiable map
   * @generated NOT
   */
  protected abstract List<IReferenceValuePresence<E>> newReferenceOrderDifferenceList(
      R reference_p);

  /**
   * Create and return a map for: reference value -> difference, and add it
   * to the modifiable reference map
   * @param reference_p the non-null reference
   * @return a non-null, empty, modifiable map
   * @generated NOT
   */
  protected abstract EMap<E, IReferenceValuePresence<E>> newReferenceValueToPresenceMap(
      R reference_p);

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch.Editable#reset(java.lang.Object, java.lang.Object, java.lang.Object)
   * @generated NOT
   */
  public void reset(E target_p, E reference_p, E ancestor_p) {
    assert target_p != null || reference_p != null || ancestor_p != null;
    set(TARGET, target_p);
    set(REFERENCE, reference_p);
    set(ANCESTOR, ancestor_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IPureMatch.Editable#set(org.eclipse.emf.diffmerge.generic.api.Role, java.lang.Object)
   * @generated NOT
   */
  public void set(Role role_p, E element_p) {
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
