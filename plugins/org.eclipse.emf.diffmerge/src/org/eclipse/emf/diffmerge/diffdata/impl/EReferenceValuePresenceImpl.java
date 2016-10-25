/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl#getValueMatch <em>Value Match</em>}</li>
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
  protected EObject value;

  /**
   * The cached value of the '{@link #getValueMatch() <em>Value Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueMatch()
   * @generated
   * @ordered
   */
  protected EMatch valueMatch;

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
   * @param value_p the value element, which is non-null unless valueMatch_p is not null
   * @param valueMatch_p the optional match corresponding to the value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public EReferenceValuePresenceImpl(EComparison comparison_p,
      EMatch elementMatch_p, EReference reference_p, EObject value_p,
      EMatch valueMatch_p, Role presenceRole_p, boolean isOrder_p) {
    super(comparison_p, elementMatch_p, reference_p, presenceRole_p, isOrder_p);
    assert valueMatch_p != null || value_p != null;
    valueMatch = valueMatch_p;
    value = (value_p != null)? value_p: valueMatch_p.get(presenceRole_p);
    assert value != null;
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
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#getFeature()
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
  public EObject basicGetValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch getValueMatch() {
    if (valueMatch != null && valueMatch.eIsProxy()) {
      InternalEObject oldValueMatch = (InternalEObject) valueMatch;
      valueMatch = (EMatch) eResolveProxy(oldValueMatch);
      if (valueMatch != oldValueMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH,
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
  public EMatch basicGetValueMatch() {
    return valueMatch;
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
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
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
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
      return value != null;
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH:
      return valueMatch != null;
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
    if (opposite != null) {
      IMatch valueMatch = getValueMatch();
      if (valueMatch != null) {
        result = valueMatch.getReferenceValueDifference(opposite,
            getElementMatch());
      }
    }
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
      result = (IReferenceValuePresence) getElementMatch()
          .getOrderDifference(getFeature(), getAbsenceRole());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#getSymmetricalOwnership()
   * @generated NOT
   */
  public IReferenceValuePresence getSymmetricalOwnership() {
    IReferenceValuePresence result = null;
    IMatch valueMatch = getValueMatch();
    if (valueMatch != null)
      result = valueMatch.getOwnershipDifference(getAbsenceRole());
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
        && getElementMatch() == peer_p.getValueMatch()
        && getValue() == peer_p.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#isOutOfScope()
   * @generated NOT
   */
  public boolean isOutOfScope() {
    return getValueMatch() == null;
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
        && getValueMatch() != null && getValueMatch() == peer_p.getValueMatch();
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
    List<EObject> sourceValues = getPresenceScope().get(sourceHolder,
        reference);
    for (int i = sourceValues.size() - 1; i >= 0; i--) {
      EObject sourceValue = sourceValues.get(i);
      IMatch valueMatch = owningComparison.getMapping().getMatchFor(sourceValue,
          destination.opposite());
      boolean coverValue = valueMatch != null ||
          getFeature() != null &&
          getComparison().getLastDiffPolicy().coverOutOfScopeValue(sourceValue, getFeature());
      if (coverValue) {
        EObject destinationValue = valueMatch != null?
            valueMatch.get(destination): sourceValue;
        if (destinationValue != null) {
          int index = mergePolicy.getDesiredValuePosition(owningComparison,
              destination, holderMatch, reference, sourceValue);
          if (index >= 0) {
            List<EObject> updatedDestinationValues = absenceScope
                .get(destinationHolder, reference);
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
    IMatch valueMatch = getValueMatch();
    EObject destinationValue;
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
      BidirectionalComparisonCopier.handleIDCopy(getValue(),
          getPresenceScope(), destinationValue, getAbsenceScope(), mergePolicy);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueRemoval()
   * @generated NOT
   */
  @Override
  protected final void mergeValueRemoval() {
    if (isOutOfScope()) {
      // Out of scope
      getPresenceScope().remove(getHolder(), getFeature(), getValue());
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
    IEditableModelScope presenceScope = getPresenceScope();
    if (getSymmetrical() == null
        && !(hasStrongerOpposite() && !getValueMatch().isPartial())) {
      EObject valueElement = getValue();
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
        if (!getComparison().getLastMergePolicy()
            .bindPresenceToOwnership(presenceScope)) {
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
