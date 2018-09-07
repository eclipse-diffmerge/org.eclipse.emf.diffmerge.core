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
package org.eclipse.emf.diffmerge.diffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EElement Relative Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#getElementMatch <em>Element Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl#getPresenceRole <em>Presence Role</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EElementRelativePresenceImpl
    extends EMergeableDifferenceImpl implements EElementRelativePresence {
  /**
   * The cached value of the '{@link #getElementMatch() <em>Element Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementMatch()
   * @generated
   * @ordered
   */
  protected EMatch elementMatch;

  /**
   * The default value of the '{@link #getPresenceRole() <em>Presence Role</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPresenceRole()
   * @generated
   * @ordered
   */
  protected static final Role PRESENCE_ROLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPresenceRole() <em>Presence Role</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPresenceRole()
   * @generated
   * @ordered
   */
  protected Role presenceRole = PRESENCE_ROLE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EElementRelativePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param comparison_p the non-null comparison to which this difference belongs
   * @param match_p the non-null match to which this difference is relative
   * @param presenceRole_p the role of the presence: TARGET or REFERENCE
   * @generated NOT
   */
  protected EElementRelativePresenceImpl(EComparison comparison_p,
      EMatch match_p, Role presenceRole_p) {
    super(false);
    setComparison(comparison_p);
    setElementMatch(match_p);
    setPresenceRole(presenceRole_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EELEMENT_RELATIVE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch getElementMatch() {
    if (elementMatch != null && elementMatch.eIsProxy()) {
      InternalEObject oldElementMatch = (InternalEObject) elementMatch;
      elementMatch = (EMatch) eResolveProxy(oldElementMatch);
      if (elementMatch != oldElementMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH,
              oldElementMatch, elementMatch));
      }
    }
    return elementMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch basicGetElementMatch() {
    return elementMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementMatch(EMatch newElementMatch) {
    EMatch oldElementMatch = elementMatch;
    elementMatch = newElementMatch;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH,
          oldElementMatch, elementMatch));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Role getPresenceRole() {
    return presenceRole;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPresenceRole(Role newPresenceRole) {
    Role oldPresenceRole = presenceRole;
    presenceRole = newPresenceRole;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE,
          oldPresenceRole, presenceRole));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH:
      if (resolve)
        return getElementMatch();
      return basicGetElementMatch();
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE:
      return getPresenceRole();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH:
      setElementMatch((EMatch) newValue);
      return;
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE:
      setPresenceRole((Role) newValue);
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
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH:
      setElementMatch((EMatch) null);
      return;
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE:
      setPresenceRole(PRESENCE_ROLE_EDEFAULT);
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
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH:
      return elementMatch != null;
    case DiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE:
      return PRESENCE_ROLE_EDEFAULT == null ? presenceRole != null
          : !PRESENCE_ROLE_EDEFAULT.equals(presenceRole);
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (presenceRole: "); //$NON-NLS-1$
    result.append(presenceRole);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#doMergeIn(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  @Override
  public final void doMergeIn(Role destination_p) {
    if (destination_p == getPresenceRole())
      mergeRemoval();
    else
      mergeAddition();
  }

  /**
   * Return the role which is opposite to the presence role
   * @return a non-null role which is TARGET or REFERENCE
   * @generated NOT
   */
  protected final Role getAbsenceRole() {
    return getPresenceRole().opposite();
  }

  /**
   * Return the scope of the role opposite to the presence role
   * @return a non-null scope
   * @generated NOT
   */
  protected final IEditableModelScope getAbsenceScope() {
    IEditableModelScope result = getComparison()
        .getScope(getPresenceRole().opposite());
    assert result != null;
    return result;
  }

  /**
   * Return the scope of the presence role
   * @return a non-null scope
   * @generated NOT
   */
  protected final IEditableModelScope getPresenceScope() {
    IEditableModelScope result = getComparison().getScope(getPresenceRole());
    assert result != null;
    return result;
  }

  /**
   * Add the element or value in the opposite role
   * @generated NOT
   */
  protected abstract void mergeAddition();

  /**
   * Remove the element or value from the presence role
   * @generated NOT
   */
  protected abstract void mergeRemoval();

} //EElementRelativePresenceImpl
