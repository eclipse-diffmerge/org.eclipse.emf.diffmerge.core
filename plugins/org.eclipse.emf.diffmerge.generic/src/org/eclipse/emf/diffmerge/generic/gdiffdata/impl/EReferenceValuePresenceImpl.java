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
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
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
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getValueMatch <em>Value Match</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EReferenceValuePresenceImpl<E, A, R> extends
    EValuePresenceImpl<E, A, R> implements EReferenceValuePresence<E, A, R> {
  /**
   * The cached value of the '{@link #getValueMatch() <em>Value Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueMatch()
   * @generated
   * @ordered
   */
  protected EMatch<E, A, R> valueMatch;

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
  public EReferenceValuePresenceImpl(EMatch<E, A, R> elementMatch_p,
      R reference_p, E value_p, EMatch<E, A, R> valueMatch_p,
      Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, presenceRole_p, isOrder_p);
    assert valueMatch_p != null || value_p != null;
    setValueMatch(valueMatch_p);
    setValue((value_p != null) ? value_p : valueMatch_p.get(presenceRole_p));
    assert getValue() != null;
    setReference(reference_p);
    ((IMatch.Editable<E>) elementMatch).addRelatedDifference(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.EREFERENCE_VALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public EMatch<E, A, R> getValueMatch() {
    if (valueMatch != null && valueMatch.eIsProxy()) {
      InternalEObject oldValueMatch = (InternalEObject) valueMatch;
      valueMatch = (EMatch<E, A, R>) eResolveProxy(oldValueMatch);
      if (valueMatch != oldValueMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH,
              oldValueMatch, valueMatch));
      }
    }
    return valueMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch<E, A, R> basicGetValueMatch() {
    return valueMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValueMatch(EMatch<E, A, R> newValueMatch) {
    EMatch<E, A, R> oldValueMatch = valueMatch;
    valueMatch = newValueMatch;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH,
          oldValueMatch, valueMatch));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#getFeature()
   * @generated NOT
   */
  @Override
  public abstract R getFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public abstract void setReference(R reference);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence#setValue(java.lang.Object)
   * @generated NOT
   */
  public abstract void setValue(E value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
      if (resolve)
        return getValueMatch();
      return basicGetValueMatch();
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
    case GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
      setValueMatch((EMatch<E, A, R>) newValue);
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
    case GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
      setValueMatch((EMatch<E, A, R>) null);
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
    case GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
      return valueMatch != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#getOpposite()
   * @generated NOT
   */
  public IReferenceValuePresence<E> getOpposite() {
    IReferenceValuePresence<E> result = null;
    Object opposite = getPresenceScope().mGetOppositeReference(getFeature());
    if (opposite != null) {
      IMatch<E> valueMatch = getValueMatch();
      if (valueMatch != null) {
        result = valueMatch.getReferenceValueDifference(opposite,
            getElementMatch().get(getPresenceRole()));
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public IReferenceValuePresence<E> getSymmetrical() {
    IReferenceValuePresence<E> result = null;
    if (!isManyFeature()) {
      Collection<IReferenceValuePresence<E>> candidates = getElementMatch()
          .getReferenceDifferences(getFeature());
      assert candidates.size() <= 2; // Because !isMany()
      for (IReferenceValuePresence<E> candidate : candidates) {
        if (candidate.getPresenceRole() == getAbsenceRole()) {
          result = candidate;
          break;
        }
      }
    } else if (isOrder()) {
      result = getElementMatch().getReferenceOrderDifference(getFeature(),
          getAbsenceRole());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#getSymmetricalOwnership()
   * @generated NOT
   */
  public IReferenceValuePresence<E> getSymmetricalOwnership() {
    IReferenceValuePresence<E> result = null;
    IMatch<E> valueMatch = getValueMatch();
    if (valueMatch != null) {
      result = valueMatch.getOwnershipDifference(getAbsenceRole());
    }
    return result;
  }

  /**
   * Return whether this difference has an opposite with stronger constraints
   * @generated NOT
   */
  protected boolean hasStrongerOpposite() {
    boolean result = false;
    if (isManyFeature()) {
      IDataScope<E> presenceScope = getPresenceScope();
      Object opposite = presenceScope.mGetOppositeReference(getFeature());
      result = opposite != null && !presenceScope.mIsManyReference(opposite);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#isContainment()
   * @generated NOT
   */
  public boolean isContainment() {
    return getPresenceScope().mIsContainmentReference(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#isManyFeature()
   * @generated NOT
   */
  @Override
  public boolean isManyFeature() {
    return getPresenceScope().mIsManyReference(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#isOppositeOf(org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public boolean isOppositeOf(IReferenceValuePresence<E> peer_p) {
    return getPresenceRole() == peer_p.getPresenceRole()
        && getPresenceScope().mGetOppositeReference(getFeature()) == peer_p
            .getFeature()
        && getElementMatch() == peer_p.getValueMatch()
        && getValueMatch() == peer_p.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#isChangeableFeature()
   * @generated NOT
   */
  public boolean isChangeableFeature() {
    return getPresenceScope().mIsChangeableReference(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#isOutOfScope()
   * @generated NOT
   */
  public boolean isOutOfScope() {
    return getValueMatch() == null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#isOwnership()
   * @generated NOT
   */
  public boolean isOwnership() {
    return !isOrder() && isContainment();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence#isSymmetricalOwnershipTo(org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public boolean isSymmetricalOwnershipTo(IReferenceValuePresence<E> peer_p) {
    return getAbsenceRole() == peer_p.getPresenceRole() && isOwnership()
        && peer_p.isOwnership() && getValueMatch() != null
        && getValueMatch() == peer_p.getValueMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return !isOwnership();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeOrder()
   * @generated NOT
   */
  @Override
  protected void mergeOrder() {
    E sourceHolder = getHolder();
    E destinationHolder = getMatchOfHolder();
    Object reference = getFeature();
    assert sourceHolder != null && destinationHolder != null; // Otherwise order change would not have been detected
    assert getFeature() != null; // Order merge does not cover root containment at this time
    IEditableTreeDataScope<E> absenceScope = getAbsenceScope();
    IEditableTreeDataScope<E> presenceScope = getPresenceScope();
    IMergePolicy<E> mergePolicy = getComparison().getLastMergePolicy();
    Role destination = getAbsenceRole();
    IMatch<E> holderMatch = getElementMatch();
    IComparison<E> owningComparison = getComparison();
    List<E> sourceValues = presenceScope.getReferenceValues(sourceHolder,
        reference);
    for (int i = sourceValues.size() - 1; i >= 0; i--) {
      E sourceValue = sourceValues.get(i);
      IMatch<E> valueMatch = owningComparison.getMapping()
          .getMatchFor(sourceValue, destination.opposite());
      boolean coverValue = valueMatch != null
          || getFeature() != null && getComparison().getLastDiffPolicy()
              .coverOutOfScopeValue(sourceValue, getFeature(), presenceScope);
      if (coverValue) {
        E destinationValue = valueMatch != null ? valueMatch.get(destination)
            : sourceValue;
        if (destinationValue != null) {
          int index = mergePolicy.getDesiredValuePosition(owningComparison,
              destination, holderMatch, reference, sourceValue);
          if (index >= 0) {
            List<E> updatedDestinationValues = absenceScope
                .getReferenceValues(destinationHolder, reference);
            int oldIndex = updatedDestinationValues.indexOf(destinationValue);
            absenceScope.moveReferenceValue(destinationHolder, reference, index,
                oldIndex);
          }
        }
      }
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeValueAddition()
   * @generated NOT
   */
  @Override
  protected void mergeValueAddition() {
    IEditableTreeDataScope<E> absenceScope = getAbsenceScope();
    IEditableTreeDataScope<E> presenceScope = getPresenceScope();
    E destinationHolder = getMatchOfHolder();
    IMatch<E> valueMatch = getValueMatch();
    E destinationValue;
    boolean cloned;
    if (valueMatch == null) {
      // Out of scope
      destinationValue = getValue(); // Keep as-is
      cloned = false;
    } else if (valueMatch.isPartial()) {
      // Within scope, value not present in absence scope
      destinationValue = getComparison().getMapping().completeMatch(valueMatch);
      cloned = true;
    } else {
      // Within scope, value present in absence scope
      destinationValue = valueMatch.get(getAbsenceRole());
      cloned = false;
    }
    // Assertions are assumed to be enforced by diff dependency handling
    assert destinationHolder != null && destinationValue != null;
    boolean actuallyAdded = absenceScope.addReferenceValue(destinationHolder,
        getFeature(), destinationValue);
    // Order handling
    IDiffPolicy<E> diffPolicy = getComparison().getLastDiffPolicy();
    IMergePolicy<E> mergePolicy = getComparison().getLastMergePolicy();
    if (diffPolicy != null && actuallyAdded
        && diffPolicy.considerOrderedReference(getFeature(), absenceScope)) {
      // Move added value if required
      int index = mergePolicy.getDesiredValuePosition(getComparison(),
          getAbsenceRole(), getElementMatch(), getFeature(), getValue());
      if (index >= 0) {
        absenceScope.moveReferenceValue(destinationHolder, getFeature(), index,
            -1);
      }
    }
    // ID enforcement
    if (cloned && actuallyAdded) {
      mergePolicy.setID(getValue(), presenceScope, destinationValue,
          absenceScope);
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl#mergeValueRemoval()
   * @generated NOT
   */
  @Override
  protected final void mergeValueRemoval() {
    if (isOutOfScope()) {
      // Out of scope
      getPresenceScope().removeReferenceValue(getHolder(), getFeature(),
          getValue());
    } else {
      // Within scope
      mergeValueRemovalWithinScope();
    }
  }

  /**
   * Remove the in-presence-scope value from the presence scope.
   * Precondition: !isOutOfScope()
   * @generated NOT
   */
  protected void mergeValueRemovalWithinScope() {
    IEditableTreeDataScope<E> presenceScope = getPresenceScope();
    if (getSymmetrical() == null
        && !(hasStrongerOpposite() && !getValueMatch().isPartial())) {
      E valueElement = getValue();
      if (isOwnership()) {
        // Value must be removed from its ownership so it is an element removal: disconnect element
        getElementMatch().getMapping().disconnect(getPresenceRole(),
            valueElement);
      }
      if (getFeature() != null) {
        presenceScope.removeReferenceValue(getHolder(), getFeature(),
            valueElement);
      } else {
        presenceScope.remove(valueElement);
      }
      if (isOwnership() && !getComparison().getLastMergePolicy()
          .bindPresenceToOwnership(presenceScope)) {
        // Re-integrate direct children in scope
        for (E child : presenceScope.getContents(valueElement)) {
          presenceScope.add(child);
        }
      }
    }
    // Otherwise, we know this difference will be merged implicitly because of dependencies
    // since a required difference implies this difference
  }

} //EReferenceValuePresenceImpl
