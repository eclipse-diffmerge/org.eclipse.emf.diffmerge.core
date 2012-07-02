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

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EReferenceValuePresenceImpl extends EValuePresenceImpl implements
		EReferenceValuePresence {
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
		super(comparison_p, elementMatch_p, reference_p, value_p,
				presenceRole_p, isOrder_p);
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
	 * @see org.eclipse.emf.diffmerge.impl.base.AbstractValuePresence#getFeature()
	 * @generated NOT
	 */
	@Override
	public EReference getFeature() {
		return (EReference) super.getFeature();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.impl.base.AbstractValuePresence#getValue()
	 * @generated NOT
	 */
	@Override
	public EMatch getValue() {
		return (EMatch) super.getValue();
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
			EReference ref = (EReference)ownfeature;
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
	 * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference#isProperToElement()
	 * @generated NOT
	 */
	public boolean isProperToElement() {
		return !getFeature().isContainment() || isOrder();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence#isSymmetricalOwnershipTo(org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence)
	 * @generated NOT
	 */
	public boolean isSymmetricalOwnershipTo(IReferenceValuePresence peer_p) {
		return getAbsenceRole() == peer_p.getPresenceRole()
				&& getFeature().isContainment()
				&& peer_p.getFeature().isContainment()
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
		IFeaturedModelScope absenceScope = getAbsenceScope();
		IMergePolicy mergePolicy = getComparison().getLastMergePolicy();
		Role destination = getAbsenceRole();
		IMatch holderMatch = getElementMatch();
		IComparison owningComparison = getComparison();
		List<EObject> sourceValues = getPresenceScope().get(sourceHolder,
				reference);
		for (int i = sourceValues.size() - 1; i >= 0; i--) {
			EObject sourceValue = sourceValues.get(i);
			IMatch valueMatch = owningComparison.getMapping().getMatchFor(
					sourceValue, destination.opposite());
			if (valueMatch != null) { // Should be true since scope must be complete
				EObject destinationValue = valueMatch.get(destination);
				if (destinationValue != null) {
					int index = mergePolicy.getDesiredValuePosition(
					    owningComparison, destination, holderMatch, reference, valueMatch);
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
		IFeaturedModelScope absenceScope = getAbsenceScope();
		EObject destinationHolder = getMatchOfHolder();
		IMatch match = getValue();
		EObject destinationValue =
		  match.isPartial()? getComparison().getMapping().completeMatch(match):
		    match.get(getAbsenceRole());
		// Assertions are assumed to be enforced by diff dependency handling
		assert destinationHolder != null && destinationValue != null;
		boolean added = absenceScope.add(destinationHolder, getFeature(), destinationValue);
		// Order handling
		IDiffPolicy diffPolicy = getComparison().getLastDiffPolicy();
		IMergePolicy mergePolicy = getComparison().getLastMergePolicy();
		if (diffPolicy != null && added && diffPolicy.considerOrdered(getFeature())) {
			// Move added value if required
			int index = mergePolicy.getDesiredValuePosition(
			    getComparison(), getAbsenceRole(), getElementMatch(), getFeature(), getValue());
			if (index >= 0)
				absenceScope.move(destinationHolder, getFeature(), index, -1);
		}
		// ID enforcement
		mergePolicy.copyId(match.get(getPresenceRole()), destinationValue);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueRemoval()
	 * @generated NOT
	 */
	@Override
	protected final void mergeValueRemoval() {
		if (getSymmetrical() == null
				&& !(hasStrongerOpposite() && !getValue().isPartial())) {
			IFeaturedModelScope presenceScope = getPresenceScope();
			EObject valueElement = getValue().get(getPresenceRole());
			presenceScope.remove(getHolder(), getFeature(), valueElement);
			// If presence is independent of ownership, re-integrate direct children in scope
			if (!getComparison().getLastMergePolicy().bindPresenceToOwnership()) {
				for (EObject child : presenceScope.getContents(valueElement)) {
					presenceScope.add(child);
				}
			}
		}
		// Otherwise, we know this difference will be merged implicitly because of dependencies
		// since a required difference implies this difference
	}

} //EReferenceValuePresenceImpl
