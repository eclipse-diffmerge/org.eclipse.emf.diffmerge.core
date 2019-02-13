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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl#getOwnerMatch <em>Owner Match</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EElementPresenceImpl<E, A, R> extends
    EElementRelativePresenceImpl<E, A, R> implements EElementPresence<E, A, R> {
  /**
   * The cached value of the '{@link #getOwnerMatch() <em>Owner Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnerMatch()
   * @generated
   * @ordered
   */
  protected EMatch<E, A, R> ownerMatch;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EElementPresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param comparison_p the non-null comparison to which this difference belongs
   * @param match_p the non-null match to which this difference is relative
   * @param ownerMatch_p a potentially null match for the owner of the element
   * @generated NOT
   */
  public EElementPresenceImpl(EComparison<E, A, R> comparison_p,
      EMatch<E, A, R> match_p, EMatch<E, A, R> ownerMatch_p) {
    super(comparison_p, match_p, match_p.getUncoveredRole().opposite());
    setOwnerMatch(ownerMatch_p);
    ((IMatch.Editable<E>) elementMatch).addRelatedDifference(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.EELEMENT_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public EMatch<E, A, R> getOwnerMatch() {
    if (ownerMatch != null && ownerMatch.eIsProxy()) {
      InternalEObject oldOwnerMatch = (InternalEObject) ownerMatch;
      ownerMatch = (EMatch<E, A, R>) eResolveProxy(oldOwnerMatch);
      if (ownerMatch != oldOwnerMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH, oldOwnerMatch,
              ownerMatch));
      }
    }
    return ownerMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch<E, A, R> basicGetOwnerMatch() {
    return ownerMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOwnerMatch(EMatch<E, A, R> newOwnerMatch) {
    EMatch<E, A, R> oldOwnerMatch = ownerMatch;
    ownerMatch = newOwnerMatch;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH, oldOwnerMatch,
          ownerMatch));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
      if (resolve)
        return getOwnerMatch();
      return basicGetOwnerMatch();
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
    case GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
      setOwnerMatch((EMatch<E, A, R>) newValue);
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
    case GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
      setOwnerMatch((EMatch<E, A, R>) null);
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
    case GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
      return ownerMatch != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence#getElement()
   * @generated NOT
   */
  public E getElement() {
    return getElementMatch().get(getPresenceRole());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence#isRoot()
   * @generated NOT
   */
  public boolean isRoot() {
    return getOwnerMatch() == null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#mergeAddition()
   * @generated NOT
   */
  @Override
  protected void mergeAddition() {
    IMapping.Editable<E> mapping = getComparison().getMapping();
    IMatch<E> eltMatch = getElementMatch();
    E clone = eltMatch.isPartial() ? mapping.completeMatch(eltMatch)
        : eltMatch.get(getAbsenceRole());
    boolean addedToScope = false;
    boolean actuallyAdded = false;
    IMergePolicy<E> mergePolicy = getComparison().getLastMergePolicy();
    if (getComparison().getLastMergePolicy()
        .bindPresenceToOwnership(getAbsenceScope()) && !isRoot()) {
      E container = getOwnerMatch().get(getAbsenceRole());
      if (container != null) {
        Object containment = getPresenceScope().getContainment(getElement());
        actuallyAdded = getAbsenceScope().addReferenceValue(container,
            containment, clone);
        addedToScope = true; // Even if !actuallyAdded
        // Order handling
        IDiffPolicy<E> diffPolicy = getComparison().getLastDiffPolicy();
        if (diffPolicy != null && actuallyAdded
            && diffPolicy.considerOrderedReference(containment)) {
          // Move added value if required
          int index = mergePolicy.getDesiredValuePosition(getComparison(),
              getAbsenceRole(), getOwnerMatch(), containment, getElement());
          if (index >= 0)
            getAbsenceScope().moveReferenceValue(container, containment, index,
                -1);
        }
      }
      // Else container will be created and ownership automatically applied by the copier,
      // if containment tree of the scope is consistent with matching
    }
    if (!addedToScope) {
      actuallyAdded = getAbsenceScope().add(clone);
    }
    if (actuallyAdded) {
      mergePolicy.setID(getElement(), getPresenceScope(), clone,
          getAbsenceScope());
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#mergeRemoval()
   * @generated NOT
   */
  @Override
  protected void mergeRemoval() {
    if (isRoot() || getElementMatch()
        .getOwnershipDifference(getPresenceRole()) == null) {
      IEditableTreeDataScope<E> presenceScope = getPresenceScope();
      E element = getElement();
      presenceScope.remove(element);
      removeDependencies(element, presenceScope);
    } // Else handled by ownership
  }

  /**
   * Remove dependencies to the given element after its removal from the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @generated NOT
   */
  protected abstract void removeDependencies(E element_p,
      IEditableTreeDataScope<E> scope_p);

} //EElementPresenceImpl
