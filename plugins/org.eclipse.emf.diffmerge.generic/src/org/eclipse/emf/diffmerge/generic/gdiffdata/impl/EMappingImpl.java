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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IRawDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.generic.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl#getModifiableContents <em>Modifiable Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl#getReferenceCompletedMatches <em>Reference Completed Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl#getTargetCompletedMatches <em>Target Completed Matches</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EMappingImpl<E, A, R> extends EIdentifiedImpl
    implements EMapping<E, A, R> {
  /**
   * The cached value of the '{@link #getModifiableContents() <em>Modifiable Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableContents()
   * @generated
   * @ordered
   */
  protected EList<EMatch<E, A, R>> modifiableContents;

  /**
   * The cached value of the '{@link #getReferenceCompletedMatches() <em>Reference Completed Matches</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceCompletedMatches()
   * @generated
   * @ordered
   */
  protected EList<IMatch<E>> referenceCompletedMatches;

  /**
   * The cached value of the '{@link #getTargetCompletedMatches() <em>Target Completed Matches</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetCompletedMatches()
   * @generated
   * @ordered
   */
  protected EList<IMatch<E>> targetCompletedMatches;

  /**
   * A non-null, stateless copier for completing partial matches
   * @generated NOT
   */
  private final BidirectionalComparisonCopier<E> _copier;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EMappingImpl() {
    super();
    _copier = new BidirectionalComparisonCopier<E>();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.EMAPPING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EMatch<E, A, R>> getModifiableContents() {
    if (modifiableContents == null) {
      modifiableContents = new EObjectContainmentEList<EMatch<E, A, R>>(
          EMatch.class, this, GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS);
    }
    return modifiableContents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMatch<E>> getReferenceCompletedMatches() {
    if (referenceCompletedMatches == null) {
      referenceCompletedMatches = new EObjectEList<IMatch<E>>(IMatch.class,
          this, GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES);
    }
    return referenceCompletedMatches;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMatch<E>> getTargetCompletedMatches() {
    if (targetCompletedMatches == null) {
      targetCompletedMatches = new EObjectEList<IMatch<E>>(IMatch.class, this,
          GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES);
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
    case GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
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
    case GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      return getModifiableContents();
    case GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      return getReferenceCompletedMatches();
    case GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
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
    case GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      getModifiableContents().clear();
      getModifiableContents()
          .addAll((Collection<? extends EMatch<E, A, R>>) newValue);
      return;
    case GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      getReferenceCompletedMatches().clear();
      getReferenceCompletedMatches()
          .addAll((Collection<? extends IMatch<E>>) newValue);
      return;
    case GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
      getTargetCompletedMatches().clear();
      getTargetCompletedMatches()
          .addAll((Collection<? extends IMatch<E>>) newValue);
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
    case GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      getModifiableContents().clear();
      return;
    case GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      getReferenceCompletedMatches().clear();
      return;
    case GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
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
    case GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS:
      return modifiableContents != null && !modifiableContents.isEmpty();
    case GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES:
      return referenceCompletedMatches != null
          && !referenceCompletedMatches.isEmpty();
    case GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES:
      return targetCompletedMatches != null
          && !targetCompletedMatches.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable#clear()
   * @generated NOT
   */
  public void clear() {
    getModifiableContents().clear();
    getTargetCompletedMatches().clear();
    getReferenceCompletedMatches().clear();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable#completeMatch(org.eclipse.emf.diffmerge.generic.api.IMatch)
   * @generated NOT
   */
  public E completeMatch(IMatch<E> partialMatch_p) {
    return _copier.completeMatch(this, partialMatch_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable#completeReferences(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public void completeReferences(Role role_p) {
    _copier.completeReferences(this, role_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#covers(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public boolean covers(E element_p, Role role_p) {
    return getMatchFor(element_p, role_p) != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getCompletedMatches(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<IMatch<E>> getCompletedMatches(Role destinationRole_p) {
    return Collections.unmodifiableCollection(
        getModifiableCompletedMatches(destinationRole_p));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement#getComparison()
   * @generated OT
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public EComparison<E, A, R> getComparison() {
    EComparison<E, A, R> result = null;
    EObject container = eContainer();
    if (container instanceof EComparison) {
      result = (EComparison) container;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getContents()
   * @generated NOT
   */
  public Collection<IMatch<E>> getContents() {
    return Collections
        .<IMatch<E>> unmodifiableCollection(getModifiableContents());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable#getModifiableCompletedMatches(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<IMatch<E>> getModifiableCompletedMatches(
      Role destinationRole_p) {
    return Role.TARGET == destinationRole_p ? getTargetCompletedMatches()
        : getReferenceCompletedMatches();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getNbFullMatches()
   * @generated NOT
   */
  public int getNbFullMatches() {
    int result = 0;
    for (IMatch<E> match : getContents())
      if (!match.isPartial())
        result++;
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getNbPartialMatches(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public int getNbPartialMatches(Role covered_p) {
    int result = 0;
    for (IMatch<E> match : getContents())
      if (match.isPartial()
          && (covered_p == null || match.coversRole(covered_p)))
        result++;
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getOrderingRole()
   * @generated NOT
   */
  public Role getOrderingRole() {
    return Role.TARGET;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#isCompleteFor(org.eclipse.emf.diffmerge.generic.api.scopes.IRawDataScope, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public boolean isCompleteFor(IRawDataScope<E> scope_p, Role role_p) {
    Iterator<E> it = scope_p.iterator();
    while (it.hasNext()) {
      E currentInScope = it.next();
      if (!covers(currentInScope, role_p))
        return false;
    }
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#isEmpty()
   * @generated NOT
   */
  public boolean isEmpty() {
    return getModifiableContents().isEmpty();
  }

  /**
   * Return whether the given reference value on the given side is ignored due to
   * being between partial matches
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @param value_p a non-null element
   * @param role_p a non-null role
   * @generated NOT
   */
  public boolean isIgnoredReferenceValue(E source_p, R reference_p, E value_p,
      Role role_p) {
    boolean result = false;
    IMatch<E> referencingMatch = getMatchFor(source_p, role_p);
    IMatch<E> referencedMatch = getMatchFor(value_p, role_p);
    // References between unmatched elements
    if (referencingMatch != null && referencedMatch != null) {
      result = referencingMatch.isPartial() && referencedMatch.isPartial();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#disconnect(org.eclipse.emf.diffmerge.generic.api.Role, java.lang.Object)
   * @generated NOT
   */
  public boolean disconnect(Role role_p, E element_p) {
    boolean result = true;
    IEditableTreeDataScope<E> scope = getComparison().getScope(role_p);
    if (scope.tIsElementDisconnectionRequired()) {
      result = doDisconnect(role_p, element_p);
    }
    return result;
  }

  /**
   * Remove dependencies (reference values) to the given element so that its removal
   * from the scope of the given role be possible, as required by the scope.
   * Precondition: getComparison().getScope(role_p).tIsElementDisconnectionRequired()
   * @see EMapping#disconnect(Role, Object)
   * @generated NOT
   */
  protected abstract boolean doDisconnect(Role role_p, E element_p);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#map(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public EMatch<E, A, R> map(E element_p, Role role_p) {
    assert element_p != null && role_p != null;
    IMatch<E> previous = getMatchFor(element_p, role_p);
    // Enforce consistency by removing previous match if any
    if (previous != null) {
      getModifiableContents().remove(previous);
    }
    @SuppressWarnings("unchecked")
    EMatch<E, A, R> result = (EMatch<E, A, R>) getComparison().newMatch(
        (Role.TARGET == role_p ? element_p : null),
        (Role.REFERENCE == role_p ? element_p : null),
        (Role.ANCESTOR == role_p ? element_p : null));
    getModifiableContents().add(result);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable#mapIncrementally(Object, org.eclipse.emf.diffmerge.generic.api.Role, Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public boolean mapIncrementally(E element1_p, Role role1_p, E element2_p,
      Role role2_p) {
    assert role1_p != null && role2_p != null && role1_p != role2_p;
    IMatch<E> newMatch = null;
    boolean result = false;
    Map<Role, E> elements = new HashMap<Role, E>(3);
    elements.put(role1_p, element1_p);
    elements.put(role2_p, element2_p);
    Role role3 = Role.otherThan(role1_p, role2_p);
    E element3 = null;
    // Checking existing match in role1_p
    if (element1_p != null) {
      newMatch = getMatchFor(element1_p, role1_p);
      if (newMatch != null) {
        element3 = newMatch.get(role3);
        E foundElement2 = newMatch.get(role2_p);
        result = foundElement2 != null && foundElement2 != element2_p;
      }
    }
    // Checking existing match in role2_p
    if (element2_p != null) {
      IMatch<E> found = getMatchFor(element2_p, role2_p);
      if (found != null) {
        E foundElement1 = found.get(role1_p);
        result = result || foundElement1 != null && foundElement1 != element1_p;
        E inRole3 = found.get(role3);
        if (inRole3 != null) {
          element3 = inRole3;
        }
        // Match found for role2_p which is different from that of role1_p
        if (newMatch != null && newMatch != found) {
          getModifiableContents().remove(found);
        } else {
          newMatch = found;
        }
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
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#maps(Object, Object)
   * @generated NOT
   */
  public boolean maps(E target_p, E reference_p) {
    return maps(target_p, Role.TARGET, reference_p, Role.REFERENCE);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#maps(Object, Object, Object)
   * @generated NOT
   */
  public boolean maps(E target_p, E reference_p, E ancestor_p) {
    return maps(ancestor_p, Role.ANCESTOR, target_p, Role.TARGET)
        && maps(ancestor_p, Role.ANCESTOR, reference_p, Role.REFERENCE)
        && maps(target_p, Role.TARGET, reference_p, Role.REFERENCE);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#maps(Object, org.eclipse.emf.diffmerge.generic.api.Role, Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public boolean maps(E element1_p, Role role1_p, E element2_p, Role role2_p) {
    boolean result = false;
    if (element1_p != null) {
      IMatch<E> fromElement1 = getMatchFor(element1_p, role1_p);
      result = fromElement1 != null && fromElement1.get(role2_p) == element2_p;
    } else if (element2_p != null) {
      IMatch<E> fromElement2 = getMatchFor(element2_p, role2_p);
      result = fromElement2 != null && fromElement2.get(role1_p) == element1_p;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#size()
   * @generated NOT
   */
  public int size() {
    return getContents().size();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#size(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public int size(Role role_p) {
    int result = 0;
    for (EMatch<E, A, R> match : getModifiableContents()) {
      if (match.get(role_p) != null)
        result++;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#toMap(org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public EMap<E, E> toMap(Role keyRole_p, Role valueRole_p) {
    EMap<E, E> result = new FHashMap<E, E>();
    for (IMatch<E> match : getContents()) {
      E key = match.get(keyRole_p);
      if (key != null) {
        result.put(key, match.get(valueRole_p));
      }
    }
    return ECollections.unmodifiableEMap(result);
  }

} //EMappingImpl
