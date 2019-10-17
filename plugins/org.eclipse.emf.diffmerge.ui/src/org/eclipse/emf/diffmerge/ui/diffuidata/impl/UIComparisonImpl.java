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
package org.eclipse.emf.diffmerge.ui.diffuidata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UI Comparison</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl#getActualComparison <em>Actual Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl#getLastActionSelection <em>Last Action Selection</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UIComparisonImpl extends EObjectImpl implements UIComparison {
  /**
   * The cached value of the '{@link #getActualComparison() <em>Actual Comparison</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActualComparison()
   * @generated
   * @ordered
   */
  protected GComparison<?, ?, ?> actualComparison;

  /**
   * The cached value of the '{@link #getLastActionSelection() <em>Last Action Selection</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastActionSelection()
   * @generated
   * @ordered
   */
  protected ComparisonSelection lastActionSelection;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UIComparisonImpl() {
    super();
  }

  /**
   * Constructor
   * @param comparison_p a non-null comparison
   * @generated NOT
   */
  public UIComparisonImpl(GComparison<?, ?, ?> comparison_p) {
    setActualComparison(comparison_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffuidataPackage.Literals.UI_COMPARISON;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GComparison<?, ?, ?> getActualComparison() {
    if (actualComparison != null && actualComparison.eIsProxy()) {
      InternalEObject oldActualComparison = (InternalEObject) actualComparison;
      actualComparison = (GComparison<?, ?, ?>) eResolveProxy(
          oldActualComparison);
      if (actualComparison != oldActualComparison) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON,
              oldActualComparison, actualComparison));
      }
    }
    return actualComparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GComparison<?, ?, ?> basicGetActualComparison() {
    return actualComparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActualComparison(GComparison<?, ?, ?> newActualComparison) {
    GComparison<?, ?, ?> oldActualComparison = actualComparison;
    actualComparison = newActualComparison;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON,
          oldActualComparison, actualComparison));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComparisonSelection getLastActionSelection() {
    return lastActionSelection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetLastActionSelection(
      ComparisonSelection newLastActionSelection, NotificationChain msgs_p) {
    NotificationChain msgs = msgs_p;
    ComparisonSelection oldLastActionSelection = lastActionSelection;
    lastActionSelection = newLastActionSelection;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET,
          DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION,
          oldLastActionSelection, newLastActionSelection);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastActionSelection(
      ComparisonSelection newLastActionSelection) {
    if (newLastActionSelection != lastActionSelection) {
      NotificationChain msgs = null;
      if (lastActionSelection != null)
        msgs = ((InternalEObject) lastActionSelection).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE
                - DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION,
            null, msgs);
      if (newLastActionSelection != null)
        msgs = ((InternalEObject) newLastActionSelection).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE
                - DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION,
            null, msgs);
      msgs = basicSetLastActionSelection(newLastActionSelection, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION,
          newLastActionSelection, newLastActionSelection));
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#clear()
   * @generated NOT
   */
  public void clear() {
    if (actualComparison != null)
      actualComparison.clear();
    if (lastActionSelection != null) {
      lastActionSelection.dispose();
      lastActionSelection = null;
    }
  }

  /**
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   * @generated NOT
   */
  public void dispose() {
    clear();
    actualComparison = null;
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
    case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
      return basicSetLastActionSelection(null, msgs);
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
    case DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON:
      if (resolve)
        return getActualComparison();
      return basicGetActualComparison();
    case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
      return getLastActionSelection();
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
    case DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON:
      setActualComparison((GComparison<?, ?, ?>) newValue);
      return;
    case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
      setLastActionSelection((ComparisonSelection) newValue);
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
    case DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON:
      setActualComparison((GComparison<?, ?, ?>) null);
      return;
    case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
      setLastActionSelection((ComparisonSelection) null);
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
    case DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON:
      return actualComparison != null;
    case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
      return lastActionSelection != null;
    }
    return super.eIsSet(featureID);
  }

} //UIComparisonImpl
