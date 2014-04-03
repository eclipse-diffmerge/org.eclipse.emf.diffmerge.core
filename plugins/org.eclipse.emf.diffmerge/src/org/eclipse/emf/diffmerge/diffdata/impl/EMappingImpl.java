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
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FHashMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
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
   * A non-null, stateless copier for completing partial matches
   * @generated NOT
   */
  private final BidirectionalComparisonCopier _copier;

  /**
   * A non-null, stateful but frozen cross-referencer for the TARGET scope
   * @generated NOT
   */
  private final ScopeCrossReferencer _targetCrossReferencer;

  /**
   * A non-null, stateful but frozen cross-referencer for the REFERENCE scope
   * @generated NOT
   */
  private final ScopeCrossReferencer _referenceCrossReferencer;

  /**
   * A non-null cross reference adapter on this mapping for retrieving matches from model elements
   * @generated NOT
   */
  private MatchCrossReferenceAdapter _matchAdapter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EMappingImpl() {
    super();
    _copier = new BidirectionalComparisonCopier();
    _targetCrossReferencer = new ScopeCrossReferencer(Role.TARGET);
    _referenceCrossReferencer = new ScopeCrossReferencer(Role.REFERENCE);
    _matchAdapter = new MatchCrossReferenceAdapter();
    eAdapters().add(_matchAdapter);
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
  public EList<EMatch> getModifiableContents() {
    if (modifiableContents == null) {
      modifiableContents = new EObjectContainmentEList<EMatch>(EMatch.class,
          this, DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS);
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
      targetCompletedMatches = new EObjectResolvingEList<IMatch>(IMatch.class,
          this, DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES);
    }
    return targetCompletedMatches;
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
    case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      return ((InternalEList<?>) getModifiableContents()).basicRemove(otherEnd,
          msgs);
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
    case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      return getModifiableContents();
    case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      return getReferenceCompletedMatches();
    case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
      return getTargetCompletedMatches();
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
    case DiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      return modifiableContents != null && !modifiableContents.isEmpty();
    case DiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      return referenceCompletedMatches != null
          && !referenceCompletedMatches.isEmpty();
    case DiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
      return targetCompletedMatches != null
          && !targetCompletedMatches.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#clear()
   * @generated NOT
   */
  public void clear() {
    getModifiableContents().clear();
    getTargetCompletedMatches().clear();
    getReferenceCompletedMatches().clear();
    _targetCrossReferencer.clear();
    _referenceCrossReferencer.clear();
    eAdapters().remove(_matchAdapter);
    _matchAdapter = new MatchCrossReferenceAdapter();
    eAdapters().add(_matchAdapter);
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
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#crossReference(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public void crossReference(Role role_p) {
    ScopeCrossReferencer referencer = null;
    if (role_p == Role.TARGET)
      referencer = _targetCrossReferencer;
    else if (role_p == Role.REFERENCE)
      referencer = _referenceCrossReferencer;
    if (referencer != null)
      referencer.crossReference();
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
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getComparison()
   * @generated NOT
   */
  public EComparison getComparison() {
    EComparison result = null;
    EObject container = eContainer();
    if (container instanceof EComparison)
      result = (EComparison) container;
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping#getContents()
   * @generated NOT
   */
  public Collection<IMatch> getContents() {
    return Collections.<IMatch> unmodifiableCollection(getModifiableContents());
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping#getMatchFor(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IMatch getMatchFor(EObject element_p, Role role_p) {
    IMatch result = null;
    EReference matchReference;
    switch (role_p) {
    case ANCESTOR:
      matchReference = DiffdataPackage.eINSTANCE.getEMatch_Ancestor();
      break;
    case REFERENCE:
      matchReference = DiffdataPackage.eINSTANCE.getEMatch_Reference();
      break;
    default:
      matchReference = DiffdataPackage.eINSTANCE.getEMatch_Target();
    }
    if (element_p != null && role_p != null) {
      Collection<Setting> settings = _matchAdapter
          .getNonNavigableInverseReferences(element_p);
      for (Setting setting : settings) {
        if (setting.getEStructuralFeature() == matchReference) {
          result = (EMatch) setting.getEObject();
          break;
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#getModifiableCompletedMatches(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public Collection<IMatch> getModifiableCompletedMatches(Role destinationRole_p) {
    return Role.TARGET == destinationRole_p ? getTargetCompletedMatches()
        : getReferenceCompletedMatches();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#getModifiableMatchMap(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  //  public EMap<EObject, IMatch> getModifiableMatchMap(Role role_p) {
  //    assert role_p != null;
  //    EMap<EObject, IMatch> result;
  //    switch (role_p) {
  //    case TARGET:
  //      result = getTargetMatches();
  //      break;
  //    case REFERENCE:
  //      result = getReferenceMatches();
  //      break;
  //    default:
  //      result = getAncestorMatches();
  //    }
  //    return result;
  //  }

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
   * @see org.eclipse.emf.diffmerge.api.IMapping#getCrossReferences(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public Collection<Setting> getCrossReferences(EObject element_p, Role role_p) {
    Collection<Setting> result = null;
    ScopeCrossReferencer referencer = null;
    if (role_p == Role.TARGET)
      referencer = _targetCrossReferencer;
    else if (role_p == Role.REFERENCE)
      referencer = _referenceCrossReferencer;
    if (referencer != null)
      result = referencer.get(element_p);
    if (result == null)
      result = Collections.emptyList();
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
    return getModifiableContents().isEmpty();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#map(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IMatch.Editable map(EObject element_p, Role role_p) {
    assert element_p != null && role_p != null;
    IMatch previous = getMatchFor(element_p, role_p);
    // Enforce consistency by removing previous match if any
    if (previous != null)
      getModifiableContents().remove(previous);
    EMatch result = (EMatch) getComparison().newMatch(
        (Role.TARGET == role_p ? element_p : null),
        (Role.REFERENCE == role_p ? element_p : null),
        (Role.ANCESTOR == role_p ? element_p : null));
    getModifiableContents().add(result);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable#mapIncrementally(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public boolean mapIncrementally(EObject element1_p, Role role1_p,
      EObject element2_p, Role role2_p) {
    assert role1_p != null && role2_p != null && role1_p != role2_p;
    IMatch newMatch = null;
    boolean result = false;
    Map<Role, EObject> elements = new HashMap<Role, EObject>(3);
    elements.put(role1_p, element1_p);
    elements.put(role2_p, element2_p);
    Role role3 = Role.otherThan(role1_p, role2_p);
    EObject element3 = null;
    // Checking existing match in role1_p
    if (element1_p != null) {
      newMatch = getMatchFor(element1_p, role1_p);
      if (newMatch != null) {
        element3 = newMatch.get(role3);
        EObject foundElement2 = newMatch.get(role2_p);
        result = foundElement2 != null && foundElement2 != element2_p;
      }
    }
    // Checking existing match in role2_p
    if (element2_p != null) {
      IMatch found = getMatchFor(element2_p, role2_p);
      if (found != null) {
        EObject foundElement1 = found.get(role1_p);
        result = result || foundElement1 != null && foundElement1 != element1_p;
        EObject inRole3 = found.get(role3);
        if (inRole3 != null)
          element3 = inRole3;
        // Match found for role2_p which is different from that of role1_p
        if (newMatch != null && newMatch != found)
          getModifiableContents().remove(found);
        else
          newMatch = found;
      }
    }
    elements.put(role3, element3);
    if (newMatch == null) {
      newMatch = getComparison().newMatch(elements.get(Role.TARGET),
          elements.get(Role.REFERENCE), elements.get(Role.ANCESTOR));
      // We assume the type of the match is compatible with the mapping
      getModifiableContents().add((EMatch) newMatch);
    } else {
      ((IMatch.Editable) newMatch).reset(elements.get(Role.TARGET),
          elements.get(Role.REFERENCE), elements.get(Role.ANCESTOR));
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
  public boolean maps(EObject target_p, EObject reference_p, EObject ancestor_p) {
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
      IMatch fromElement1 = getMatchFor(element1_p, role1_p);
      result = fromElement1 != null && fromElement1.get(role2_p) == element2_p;
    } else if (element2_p != null) {
      IMatch fromElement2 = getMatchFor(element2_p, role2_p);
      result = fromElement2 != null && fromElement2.get(role1_p) == element1_p;
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
    int result = 0;
    for (EMatch match : getModifiableContents()) {
      if (match.get(role_p) != null)
        result++;
    }
    return result;
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

  /**
   * A cross reference adapter for retrieving matches from model elements.
   * @generated NOT
   */
  protected static class MatchCrossReferenceAdapter extends
      ECrossReferenceAdapter {
    /**
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isIncluded(org.eclipse.emf.ecore.EReference)
     */
    @Override
    protected boolean isIncluded(EReference reference_p) {
      return reference_p == DiffdataPackage.eINSTANCE.getEMatch_Ancestor()
          || reference_p == DiffdataPackage.eINSTANCE.getEMatch_Reference()
          || reference_p == DiffdataPackage.eINSTANCE.getEMatch_Target();
    }
  }

  /**
   * A cross-referencer for handling cross-references that are not covered by differences.
   * @generated NOT
   */
  protected class ScopeCrossReferencer extends EcoreUtil.CrossReferencer {
    private static final long serialVersionUID = 1L;
    /** The non-null role played by the scope to cross-reference */
    protected final Role _role;

    /**
     * Constructor
     * @param role_p a role which is TARGET or REFERENCE
     */
    public ScopeCrossReferencer(Role role_p) {
      super(Collections.emptyList());
      _role = role_p;
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference()
     */
    @Override
    public void crossReference() { // Increases visibility
      super.crossReference();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected boolean crossReference(EObject element_p, EReference reference_p,
        EObject crossReferenced_p) {
      boolean result = false;
      if (reference_p.isChangeable() && !reference_p.isDerived()) {
        IMatch referencingMatch = getMatchFor(element_p, _role);
        IMatch referencedMatch = getMatchFor(crossReferenced_p, _role);
        // Unidirectional, modifiable cross-references between unmatched elements
        if (referencingMatch != null && referencedMatch != null)
          result = referencingMatch.isPartial() && referencedMatch.isPartial();
      }
      return result;
    }

    /**
     * Return the role covered by this cross-referencer
     * @return TARGET or REFERENCE
     */
    public Role getRole() {
      return _role;
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#newCollection()
     */
    @Override
    protected Collection<EStructuralFeature.Setting> newCollection() {
      return new FArrayList<EStructuralFeature.Setting>();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#newContentsIterator()
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected TreeIterator<Notifier> newContentsIterator() {
      return (TreeIterator) getComparison().getScope(_role).getAllContents();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#resolve()
     */
    @Override
    protected boolean resolve() {
      return false;
    }
  }

} //EMappingImpl
