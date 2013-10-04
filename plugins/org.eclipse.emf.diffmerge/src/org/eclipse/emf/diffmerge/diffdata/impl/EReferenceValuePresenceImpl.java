/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EReferenceValuePresenceImpl extends EValuePresenceImpl implements
    EReferenceValuePresence {
  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected EMatch value;

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
   * @param comparison_p the non-null comparison to which this difference belongs
   * @param elementMatch_p the non-null match for the element holding the value
   * @param reference_p the non-null reference holding the value
   * @param value_p the non-null value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public EReferenceValuePresenceImpl(EComparison comparison_p,
      EMatch elementMatch_p, EReference reference_p, EMatch value_p,
      Role presenceRole_p, boolean isOrder_p) {
    super(comparison_p, elementMatch_p, reference_p, presenceRole_p, isOrder_p);
    value = value_p;
    ((IMatch.Editable) elementMatch).addRelatedDifference(this);
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
  public EMatch getValue() {
    if (value != null && value.eIsProxy()) {
      InternalEObject oldValue = (InternalEObject) value;
      value = (EMatch) eResolveProxy(oldValue);
      if (value != oldValue) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE, oldValue, value));
      }
    }
    return value;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.base.AbstractValuePresence#getFeature()
   * @generated NOT
   */
  @Override
  public EReference getFeature() {
    return (EReference) super.getFeature();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch basicGetValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
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
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      return value != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#getOpposite()
   * @generated NOT
   */
  public IReferenceValuePresence getOpposite() {
    IReferenceValuePresence result = null;
    EReference opposite = getFeature().getEOpposite();
    if (opposite != null)
      result = getValue().getReferenceValueDifference(opposite,
          getElementMatch());
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public IReferenceValuePresence getSymmetrical() {
    IReferenceValuePresence result = null;
    if (!getFeature().isMany()) {
      Collection<IReferenceValuePresence> candidates = getElementMatch()
          .getReferenceDifferences(getFeature());
      assert candidates.size() <= 2; // Because !isMany()
      for (IReferenceValuePresence candidate : candidates) {
        if (candidate.getPresenceRole() == getAbsenceRole()) {
          result = candidate;
          break;
        }
      }
    } else if (isOrder()) {
      result = (IReferenceValuePresence) getElementMatch().getOrderDifference(
          getFeature(), getAbsenceRole());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#getSymmetricalOwnership()
   * @generated NOT
   */
  public IReferenceValuePresence getSymmetricalOwnership() {
    IReferenceValuePresence result = getValue().getOwnershipDifference(
        getAbsenceRole());
    return result;
  }

  /**
   * Return whether this difference has an opposite with stronger constraints
   * @generated NOT
   */
  protected boolean hasStrongerOpposite() {
    boolean result = false;
    EStructuralFeature ownfeature = getFeature();
    if (ownfeature instanceof EReference) {
      EReference ref = (EReference) ownfeature;
      if (ref.isMany()) {
        EReference opposite = ref.getEOpposite();
        result = opposite != null && !opposite.isMany();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#isOppositeOf(org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public boolean isOppositeOf(IReferenceValuePresence peer_p) {
    return getPresenceRole() == peer_p.getPresenceRole()
        && getFeature().getEOpposite() == peer_p.getFeature()
        && getElementMatch() == peer_p.getValue()
        && getValue() == peer_p.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return !getFeature().isContainment() || isOrder();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#isSymmetricalOwnershipTo(org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence)
   * @generated NOT
   */
  public boolean isSymmetricalOwnershipTo(IReferenceValuePresence peer_p) {
    return getAbsenceRole() == peer_p.getPresenceRole()
        && getFeature().isContainment() && peer_p.getFeature().isContainment()
        && getValue() == peer_p.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeOrder()
   * @generated NOT
   */
  @Override
  protected void mergeOrder() {
    EObject sourceHolder = getHolder();
    EObject destinationHolder = getMatchOfHolder();
    EReference reference = getFeature();
    assert sourceHolder != null && destinationHolder != null; // Otherwise order change would not have been detected
    assert getFeature() != null; // Order merge does not cover root containment at this time
    IEditableModelScope absenceScope = getAbsenceScope();
    IMergePolicy mergePolicy = getComparison().getLastMergePolicy();
    Role destination = getAbsenceRole();
    IMatch holderMatch = getElementMatch();
    IComparison owningComparison = getComparison();
    List<EObject> sourceValues = getPresenceScope()
        .get(sourceHolder, reference);
    for (int i = sourceValues.size() - 1; i >= 0; i--) {
      EObject sourceValue = sourceValues.get(i);
      IMatch valueMatch = owningComparison.getMapping().getMatchFor(
          sourceValue, destination.opposite());
      if (valueMatch != null) { // Should be true since scope must be complete
        EObject destinationValue = valueMatch.get(destination);
        if (destinationValue != null) {
          int index = mergePolicy.getDesiredValuePosition(owningComparison,
              destination, holderMatch, reference, valueMatch);
          if (index >= 0) {
            List<EObject> updatedDestinationValues = absenceScope.get(
                destinationHolder, reference);
            int oldIndex = updatedDestinationValues.indexOf(destinationValue);
            absenceScope.move(destinationHolder, reference, index, oldIndex);
          }
        }
      }
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueAddition()
   * @generated NOT
   */
  @Override
  protected void mergeValueAddition() {
    IEditableModelScope absenceScope = getAbsenceScope();
    EObject destinationHolder = getMatchOfHolder();
    IMatch match = getValue();
    EObject destinationValue;
    boolean cloned;
    if (match.isPartial()) {
      destinationValue = getComparison().getMapping().completeMatch(match);
      cloned = true;
    } else {
      destinationValue = match.get(getAbsenceRole());
      cloned = false;
    }
    // Assertions are assumed to be enforced by diff dependency handling
    assert destinationHolder != null && destinationValue != null;
    boolean actuallyAdded = absenceScope.add(destinationHolder, getFeature(),
        destinationValue);
    // Order handling
    IDiffPolicy diffPolicy = getComparison().getLastDiffPolicy();
    IMergePolicy mergePolicy = getComparison().getLastMergePolicy();
    if (diffPolicy != null && actuallyAdded
        && diffPolicy.considerOrdered(getFeature())) {
      // Move added value if required
      int index = mergePolicy.getDesiredValuePosition(getComparison(),
          getAbsenceRole(), getElementMatch(), getFeature(), getValue());
      if (index >= 0)
        absenceScope.move(destinationHolder, getFeature(), index, -1);
    }
    // ID enforcement
    if (cloned && actuallyAdded)
      BidirectionalComparisonCopier.handleIDCopy(
          match.get(getPresenceRole()), getPresenceScope(),
          destinationValue, getAbsenceScope(), mergePolicy);
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueRemoval()
   * @generated NOT
   */
  @Override
  protected final void mergeValueRemoval() {
    if (getSymmetrical() == null
        && !(hasStrongerOpposite() && !getValue().isPartial())) {
      IEditableModelScope presenceScope = getPresenceScope();
      EObject valueElement = getValue().get(getPresenceRole());
      if (getFeature() != null)
        presenceScope.remove(getHolder(), getFeature(), valueElement);
      else
        presenceScope.remove(valueElement);
      if (getFeature() == null || getFeature().isContainment()) {
        // Value has been removed from its containment: delete element
        for (EStructuralFeature.Setting setting : getComparison().getMapping()
            .getCrossReferences(valueElement, getPresenceRole())) {
          presenceScope.remove(setting.getEObject(),
              (EReference) setting.getEStructuralFeature(), valueElement);
        }
        if (!getComparison().getLastMergePolicy().bindPresenceToOwnership(presenceScope)) {
          // Re-integrate direct children in scope
          for (EObject child : presenceScope.getContents(valueElement)) {
            presenceScope.add(child);
          }
        }
      }
    }
    // Otherwise, we know this difference will be merged implicitly because of dependencies
    // since a required difference implies this difference
  }

} //EReferenceValuePresenceImpl
