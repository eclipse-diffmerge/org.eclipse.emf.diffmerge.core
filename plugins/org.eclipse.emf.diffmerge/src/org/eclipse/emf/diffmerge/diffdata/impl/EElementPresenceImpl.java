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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.impl.helpers.BidirectionalComparisonCopier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl#getOwnerMatch <em>Owner Match</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EElementPresenceImpl extends EElementRelativePresenceImpl
    implements EElementPresence {
  /**
   * The cached value of the '{@link #getOwnerMatch() <em>Owner Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOwnerMatch()
   * @generated
   * @ordered
   */
  protected EMatch ownerMatch;

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
  public EElementPresenceImpl(EComparison comparison_p, EMatch match_p,
      EMatch ownerMatch_p) {
    super(comparison_p, match_p, match_p.getUncoveredRole().opposite());
    ownerMatch = ownerMatch_p;
    ((IMatch.Editable) elementMatch).addRelatedDifference(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EELEMENT_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch getOwnerMatch() {
    if (ownerMatch != null && ownerMatch.eIsProxy()) {
      InternalEObject oldOwnerMatch = (InternalEObject) ownerMatch;
      ownerMatch = (EMatch) eResolveProxy(oldOwnerMatch);
      if (ownerMatch != oldOwnerMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH, oldOwnerMatch,
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
  public EMatch basicGetOwnerMatch() {
    return ownerMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
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
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case DiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH:
      return ownerMatch != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IElementPresence#getElement()
   * @generated NOT
   */
  public EObject getElement() {
    return getElementMatch().get(getPresenceRole());
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IElementPresence#isRoot()
   * @generated NOT
   */
  public boolean isRoot() {
    return getOwnerMatch() == null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#mergeAddition()
   * @generated NOT
   */
  @Override
  protected void mergeAddition() {
    IMapping.Editable mapping = getComparison().getMapping();
    IMatch eltMatch = getElementMatch();
    EObject clone = eltMatch.isPartial() ? mapping.completeMatch(eltMatch)
        : eltMatch.get(getAbsenceRole());
    boolean addedToScope = false;
    boolean actuallyAdded = false;
    IMergePolicy mergePolicy = getComparison().getLastMergePolicy();
    if (getComparison().getLastMergePolicy().bindPresenceToOwnership(getAbsenceScope())
        && !isRoot()) {
      EObject container = getOwnerMatch().get(getAbsenceRole());
      if (container != null) {
        EReference containment = getPresenceScope()
            .getContainment(getElement());
        actuallyAdded = getAbsenceScope().add(container, containment, clone);
        addedToScope = true; // Even if !actuallyAdded
        // Order handling
        IDiffPolicy diffPolicy = getComparison().getLastDiffPolicy();
        if (diffPolicy != null && actuallyAdded
            && diffPolicy.considerOrdered(containment)) {
          // Move added value if required
          int index = mergePolicy.getDesiredValuePosition(getComparison(),
              getAbsenceRole(), getOwnerMatch(), containment, eltMatch);
          if (index >= 0)
            getAbsenceScope().move(container, containment, index, -1);
        }
      }
      // Else container will be created and ownership automatically applied by the copier,
      // if containment tree of the scope is consistent with matching
    }
    if (!addedToScope)
      actuallyAdded = getAbsenceScope().add(clone);
    if (actuallyAdded)
      BidirectionalComparisonCopier.handleIDCopy(
          getElement(), getPresenceScope(), clone, getAbsenceScope(), mergePolicy);
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#mergeRemoval()
   * @generated NOT
   */
  @Override
  protected void mergeRemoval() {
    if (isRoot()
        || getElementMatch().getOwnershipDifference(getPresenceRole()) == null) {
      IEditableModelScope presenceScope = getPresenceScope();
      EObject element = getElement();
      presenceScope.remove(element);
      // Delete element
      for (EStructuralFeature.Setting setting : getComparison().getMapping()
          .getCrossReferences(element, getPresenceRole())) {
        presenceScope.remove(setting.getEObject(),
            (EReference) setting.getEStructuralFeature(), element);
      }
    } // Else handled by ownership
  }

} //EElementPresenceImpl
