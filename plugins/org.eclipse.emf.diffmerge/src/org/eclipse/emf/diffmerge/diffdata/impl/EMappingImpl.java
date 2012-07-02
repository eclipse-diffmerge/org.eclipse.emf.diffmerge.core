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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.util.structures.FHashMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getComparison <em>Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getAncestorMatches <em>Ancestor Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getReferenceMatches <em>Reference Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getTargetMatches <em>Target Matches</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EMappingImpl extends EObjectImpl implements EMapping {
	/**
	 * The cached value of the '{@link #getModifiableContents() <em>Modifiable Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifiableContents()
	 * @generated
	 * @ordered
	 */
	protected EList<EMatch> modifiableContents;

	/**
	 * The cached value of the '{@link #getReferenceCompletedMatches() <em>Reference Completed Matches</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceCompletedMatches()
	 * @generated
	 * @ordered
	 */
	protected EList<IMatch> referenceCompletedMatches;

	/**
	 * The cached value of the '{@link #getTargetCompletedMatches() <em>Target Completed Matches</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetCompletedMatches()
	 * @generated
	 * @ordered
	 */
	protected EList<IMatch> targetCompletedMatches;

	/**
	 * The cached value of the '{@link #getAncestorMatches() <em>Ancestor Matches</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAncestorMatches()
	 * @generated
	 * @ordered
	 */
	protected EMap<EObject, IMatch> ancestorMatches;

	/**
	 * The cached value of the '{@link #getReferenceMatches() <em>Reference Matches</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceMatches()
	 * @generated
	 * @ordered
	 */
	protected EMap<EObject, IMatch> referenceMatches;

	/**
	 * The cached value of the '{@link #getTargetMatches() <em>Target Matches</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetMatches()
	 * @generated
	 * @ordered
	 */
	protected EMap<EObject, IMatch> targetMatches;

	/**
	 * A non-null, stateless copier for completing partial matches
	 * @generated NOT
	 */
	private final BidirectionalComparisonCopier _copier;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected EMappingImpl() {
		super();
		_copier = new BidirectionalComparisonCopier();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiffdataPackage.Literals.EMAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EComparison getComparison() {
		if (eContainerFeatureID() != DiffdataPackage.EMAPPING__COMPARISON)
			return null;
		return (EComparison) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMatch> getModifiableContents() {
		if (modifiableContents == null) {
			modifiableContents = new EObjectContainmentEList<EMatch>(
					EMatch.class, this,
					DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS);
		}
		return modifiableContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IMatch> getReferenceCompletedMatches() {
		if (referenceCompletedMatches == null) {
			referenceCompletedMatches = new EObjectResolvingEList<IMatch>(
					IMatch.class, this,
					DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES);
		}
		return referenceCompletedMatches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IMatch> getTargetCompletedMatches() {
		if (targetCompletedMatches == null) {
			targetCompletedMatches = new EObjectResolvingEList<IMatch>(
					IMatch.class, this,
					DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES);
		}
		return targetCompletedMatches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<EObject, IMatch> getAncestorMatches() {
		if (ancestorMatches == null) {
			ancestorMatches = new EcoreEMap<EObject, IMatch>(
					DiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
					ElementToMatchEntryImpl.class, this,
					DiffdataPackage.EMAPPING__ANCESTOR_MATCHES);
		}
		return ancestorMatches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<EObject, IMatch> getReferenceMatches() {
		if (referenceMatches == null) {
			referenceMatches = new EcoreEMap<EObject, IMatch>(
					DiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
					ElementToMatchEntryImpl.class, this,
					DiffdataPackage.EMAPPING__REFERENCE_MATCHES);
		}
		return referenceMatches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<EObject, IMatch> getTargetMatches() {
		if (targetMatches == null) {
			targetMatches = new EcoreEMap<EObject, IMatch>(
					DiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
					ElementToMatchEntryImpl.class, this,
					DiffdataPackage.EMAPPING__TARGET_MATCHES);
		}
		return targetMatches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs_p) {
	  NotificationChain msgs = msgs_p;
		switch (featureID) {
		case DiffdataPackage.EMAPPING__COMPARISON:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return eBasicSetContainer(otherEnd,
					DiffdataPackage.EMAPPING__COMPARISON, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case DiffdataPackage.EMAPPING__COMPARISON:
			return eBasicSetContainer(null,
					DiffdataPackage.EMAPPING__COMPARISON, msgs);
		case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
			return ((InternalEList<?>) getModifiableContents()).basicRemove(
					otherEnd, msgs);
		case DiffdataPackage.EMAPPING__ANCESTOR_MATCHES:
			return ((InternalEList<?>) getAncestorMatches()).basicRemove(
					otherEnd, msgs);
		case DiffdataPackage.EMAPPING__REFERENCE_MATCHES:
			return ((InternalEList<?>) getReferenceMatches()).basicRemove(
					otherEnd, msgs);
		case DiffdataPackage.EMAPPING__TARGET_MATCHES:
			return ((InternalEList<?>) getTargetMatches()).basicRemove(
					otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case DiffdataPackage.EMAPPING__COMPARISON:
			return eInternalContainer().eInverseRemove(this,
					DiffdataPackage.ECOMPARISON__MAPPING, EComparison.class,
					msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DiffdataPackage.EMAPPING__COMPARISON:
			return getComparison();
		case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
			return getModifiableContents();
		case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
			return getReferenceCompletedMatches();
		case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
			return getTargetCompletedMatches();
		case DiffdataPackage.EMAPPING__ANCESTOR_MATCHES:
			if (coreType)
				return getAncestorMatches();
			return getAncestorMatches().map();
		case DiffdataPackage.EMAPPING__REFERENCE_MATCHES:
			if (coreType)
				return getReferenceMatches();
			return getReferenceMatches().map();
		case DiffdataPackage.EMAPPING__TARGET_MATCHES:
			if (coreType)
				return getTargetMatches();
			return getTargetMatches().map();
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
		case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
			getReferenceCompletedMatches().clear();
			getReferenceCompletedMatches().addAll(
					(Collection<? extends IMatch>) newValue);
			return;
		case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
			getTargetCompletedMatches().clear();
			getTargetCompletedMatches().addAll(
					(Collection<? extends IMatch>) newValue);
			return;
		case DiffdataPackage.EMAPPING__ANCESTOR_MATCHES:
			((EStructuralFeature.Setting) getAncestorMatches()).set(newValue);
			return;
		case DiffdataPackage.EMAPPING__REFERENCE_MATCHES:
			((EStructuralFeature.Setting) getReferenceMatches()).set(newValue);
			return;
		case DiffdataPackage.EMAPPING__TARGET_MATCHES:
			((EStructuralFeature.Setting) getTargetMatches()).set(newValue);
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
		case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
			getReferenceCompletedMatches().clear();
			return;
		case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
			getTargetCompletedMatches().clear();
			return;
		case DiffdataPackage.EMAPPING__ANCESTOR_MATCHES:
			getAncestorMatches().clear();
			return;
		case DiffdataPackage.EMAPPING__REFERENCE_MATCHES:
			getReferenceMatches().clear();
			return;
		case DiffdataPackage.EMAPPING__TARGET_MATCHES:
			getTargetMatches().clear();
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
		case DiffdataPackage.EMAPPING__COMPARISON:
			return getComparison() != null;
		case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
			return modifiableContents != null && !modifiableContents.isEmpty();
		case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
			return referenceCompletedMatches != null
					&& !referenceCompletedMatches.isEmpty();
		case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
			return targetCompletedMatches != null
					&& !targetCompletedMatches.isEmpty();
		case DiffdataPackage.EMAPPING__ANCESTOR_MATCHES:
			return ancestorMatches != null && !ancestorMatches.isEmpty();
		case DiffdataPackage.EMAPPING__REFERENCE_MATCHES:
			return referenceMatches != null && !referenceMatches.isEmpty();
		case DiffdataPackage.EMAPPING__TARGET_MATCHES:
			return targetMatches != null && !targetMatches.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#clear()
	 * @generated NOT
	 */
	public void clear() {
		getModifiableContents().clear();
		getTargetMatches().clear();
		getReferenceMatches().clear();
		getAncestorMatches().clear();
		getTargetCompletedMatches().clear();
		getReferenceCompletedMatches().clear();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#completeMatch(org.eclipse.emf.diffmerge.api.IMatch)
	 * @generated NOT
	 */
	public EObject completeMatch(IMatch partialMatch_p) {
		return _copier.completeMatch(this, partialMatch_p);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#completeReferences(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public void completeReferences(Role role_p) {
		_copier.completeReferences(this, role_p);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#covers(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public boolean covers(EObject element_p, Role role_p) {
		return getMatchFor(element_p, role_p) != null;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#getCompletedMatches(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public Collection<IMatch> getCompletedMatches(Role destinationRole_p) {
		return Collections
				.unmodifiableCollection(getModifiableCompletedMatches(destinationRole_p));
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#getContents()
	 * @generated NOT
	 */
	public Collection<IMatch> getContents() {
		return Collections
				.<IMatch> unmodifiableCollection(getModifiableContents());
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#getMatchFor(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public IMatch getMatchFor(EObject element_p, Role role_p) {
		if (element_p == null || role_p == null)
			return null;
		IMatch result = getModifiableMatchMap(role_p).get(element_p);
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#getModifiableCompletedMatches(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public Collection<IMatch> getModifiableCompletedMatches(
			Role destinationRole_p) {
		return Role.TARGET == destinationRole_p ? getTargetCompletedMatches()
				: getReferenceCompletedMatches();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#getModifiableMatchMap(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public EMap<EObject, IMatch> getModifiableMatchMap(Role role_p) {
		assert role_p != null;
		EMap<EObject, IMatch> result;
		switch (role_p) {
		case TARGET:
			result = getTargetMatches();
			break;
		case REFERENCE:
			result = getReferenceMatches();
			break;
		default:
			result = getAncestorMatches();
		}
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#getNbFullMatches()
	 * @generated NOT
	 */
	public int getNbFullMatches() {
		int result = 0;
		for (IMatch match : getContents())
			if (!match.isPartial())
				result++;
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#getNbPartialMatches(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public int getNbPartialMatches(Role covered_p) {
		int result = 0;
		for (IMatch match : getContents())
			if (match.isPartial()
					&& (covered_p == null || match.coversRole(covered_p)))
				result++;
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#isCompleteFor(org.eclipse.emf.diffmerge.api.scopes.IModelScope, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public boolean isCompleteFor(IModelScope scope_p, Role role_p) {
		Iterator<EObject> it = scope_p.getAllContents();
		while (it.hasNext()) {
			EObject currentInScope = it.next();
			if (!covers(currentInScope, role_p))
				return false;
		}
		return true;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#isEmpty()
	 * @generated NOT
	 */
	public boolean isEmpty() {
		return getModifiableMatchMap(Role.TARGET).isEmpty()
				&& getModifiableMatchMap(Role.REFERENCE).isEmpty()
				&& getModifiableMatchMap(Role.ANCESTOR).isEmpty();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#map(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public IMatch map(EObject element_p, Role role_p) {
		assert element_p != null && role_p != null;
		EMatch result = (EMatch) getComparison().newMatch(
				(Role.TARGET == role_p ? element_p : null),
				(Role.REFERENCE == role_p ? element_p : null),
				(Role.ANCESTOR == role_p ? element_p : null));
		// We assume the type of the match is compatible with the mapping
		getModifiableContents().add(result);
		EMap<EObject, IMatch> map = getModifiableMatchMap(role_p);
		IMatch found = map.put(element_p, result);
		if (found != null)
			getModifiableContents().remove(found);
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#mapIncrementally(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public IMatch mapIncrementally(EObject element1_p, Role role1_p,
			EObject element2_p, Role role2_p) {
		assert role1_p != null && role2_p != null && role1_p != role2_p;
		IMatch result = null;
		Map<Role, EObject> elements = new HashMap<Role, EObject>(3);
		elements.put(role1_p, element1_p);
		elements.put(role2_p, element2_p);
		Role role3 = Role.otherThan(role1_p, role2_p);
		EObject element3 = null;
		// Checking existing match in role1_p
		if (element1_p != null) {
			EMap<EObject, IMatch> map1 = getModifiableMatchMap(role1_p);
			result = map1.get(element1_p);
			if (result != null) {
				element3 = result.get(role3);
			}
		}
		// Checking existing match in role2_p
		if (element2_p != null) {
			EMap<EObject, IMatch> map2 = getModifiableMatchMap(role2_p);
			IMatch found = map2.get(element2_p);
			if (found != null) {
				EObject inRole3 = found.get(role3);
				if (inRole3 != null)
					element3 = inRole3;
				if (result != null && result != found)
					getModifiableContents().remove(found);
				else
					result = found;
			}
		}
		elements.put(role3, element3);
		if (result == null) {
			result = getComparison().newMatch(elements.get(Role.TARGET),
					elements.get(Role.REFERENCE), elements.get(Role.ANCESTOR));
			// We assume the type of the match is compatible with the mapping
			getModifiableContents().add((EMatch) result);
		} else {
			((IMatch.Editable) result).reset(elements.get(Role.TARGET),
					elements.get(Role.REFERENCE), elements.get(Role.ANCESTOR));
		}
		for (Role role : Role.values()) {
			EObject element = elements.get(role);
			if (element != null)
				getModifiableMatchMap(role).put(element, result);
		}
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#maps(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated NOT
	 */
	public boolean maps(EObject target_p, EObject reference_p) {
		return maps(target_p, Role.TARGET, reference_p, Role.REFERENCE);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#maps(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated NOT
	 */
	public boolean maps(EObject target_p, EObject reference_p,
			EObject ancestor_p) {
		return maps(ancestor_p, Role.ANCESTOR, target_p, Role.TARGET)
				&& maps(ancestor_p, Role.ANCESTOR, reference_p, Role.REFERENCE)
				&& maps(target_p, Role.TARGET, reference_p, Role.REFERENCE);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#maps(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public boolean maps(EObject element1_p, Role role1_p, EObject element2_p,
			Role role2_p) {
		boolean result = false;
		if (element1_p != null) {
			IMatch fromElement1 = getModifiableMatchMap(role1_p)
					.get(element1_p);
			result = fromElement1 != null
					&& fromElement1.get(role2_p) == element2_p;
		} else if (element2_p != null) {
			IMatch fromElement2 = getModifiableMatchMap(role2_p)
					.get(element2_p);
			result = fromElement2 != null
					&& fromElement2.get(role1_p) == element1_p;
		}
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#size()
	 * @generated NOT
	 */
	public int size() {
		return getContents().size();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#size(org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public int size(Role role_p) {
		return getModifiableMatchMap(role_p).size();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.IMapping#toMap(org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.api.Role)
	 * @generated NOT
	 */
	public EMap<EObject, EObject> toMap(Role keyRole_p, Role valueRole_p) {
		EMap<EObject, EObject> result = new FHashMap<EObject, EObject>();
		for (IMatch match : getContents()) {
			EObject key = match.get(keyRole_p);
			if (key != null)
				result.put(key, match.get(valueRole_p));
		}
		return ECollections.unmodifiableEMap(result);
	}

} //EMappingImpl
