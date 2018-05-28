/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.diffuidata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UI Comparison</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl#getActualComparison <em>Actual Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl#getDifferencesToIgnore <em>Differences To Ignore</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl#getLastActionSelection <em>Last Action Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIComparisonImpl extends EObjectImpl implements UIComparison {
	/**
	 * The cached value of the '{@link #getActualComparison() <em>Actual Comparison</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualComparison()
	 * @generated
	 * @ordered
	 */
	protected EComparison actualComparison;

	/**
	 * The cached value of the '{@link #getDifferencesToIgnore() <em>Differences To Ignore</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDifferencesToIgnore()
	 * @generated
	 * @ordered
	 */
	protected EList<EMergeableDifference> differencesToIgnore;

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
	public UIComparisonImpl(EComparison comparison_p) {
	  actualComparison = comparison_p;
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
	public EComparison getActualComparison() {
		return actualComparison;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NotificationChain basicSetActualComparison(EComparison newActualComparison, NotificationChain msgs_p) {
	  NotificationChain msgs = msgs_p;
		EComparison oldActualComparison = actualComparison;
		actualComparison = newActualComparison;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UidiffdataPackage.UI_COMPARISON__ACTUAL_COMPARISON, oldActualComparison, newActualComparison);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EMergeableDifference> getDifferencesToIgnore() {
		if (differencesToIgnore == null) {
			differencesToIgnore = new EObjectResolvingEList<EMergeableDifference>(EMergeableDifference.class, this, DiffuidataPackage.UI_COMPARISON__DIFFERENCES_TO_IGNORE);
		}
		return differencesToIgnore;
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
	public NotificationChain basicSetLastActionSelection(ComparisonSelection newLastActionSelection, NotificationChain msgs_p) {
	  NotificationChain msgs = msgs_p;
		ComparisonSelection oldLastActionSelection = lastActionSelection;
		lastActionSelection = newLastActionSelection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UidiffdataPackage.UI_COMPARISON__LAST_ACTION_SELECTION, oldLastActionSelection, newLastActionSelection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastActionSelection(ComparisonSelection newLastActionSelection) {
		if (newLastActionSelection != lastActionSelection) {
			NotificationChain msgs = null;
			if (lastActionSelection != null)
				msgs = ((InternalEObject)lastActionSelection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION, null, msgs);
			if (newLastActionSelection != null)
				msgs = ((InternalEObject)newLastActionSelection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION, null, msgs);
			msgs = basicSetLastActionSelection(newLastActionSelection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION, newLastActionSelection, newLastActionSelection));
	}
	
	/**
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#clear()
   * @generated NOT
	 */
	public void clear() {
    if (actualComparison != null)
      actualComparison.clear();
    if (differencesToIgnore != null)
      differencesToIgnore.clear();
    if (lastActionSelection != null) {
      lastActionSelection.dispose();
      lastActionSelection = null;
    }
	}
	
  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#dispose()
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiffuidataPackage.UI_COMPARISON__ACTUAL_COMPARISON:
				return basicSetActualComparison(null, msgs);
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
				return getActualComparison();
			case DiffuidataPackage.UI_COMPARISON__DIFFERENCES_TO_IGNORE:
				return getDifferencesToIgnore();
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DiffuidataPackage.UI_COMPARISON__DIFFERENCES_TO_IGNORE:
				getDifferencesToIgnore().clear();
				getDifferencesToIgnore().addAll((Collection<? extends EMergeableDifference>)newValue);
				return;
			case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
				setLastActionSelection((ComparisonSelection)newValue);
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
			case DiffuidataPackage.UI_COMPARISON__DIFFERENCES_TO_IGNORE:
				getDifferencesToIgnore().clear();
				return;
			case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
				setLastActionSelection((ComparisonSelection)null);
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
			case DiffuidataPackage.UI_COMPARISON__DIFFERENCES_TO_IGNORE:
				return differencesToIgnore != null && !differencesToIgnore.isEmpty();
			case DiffuidataPackage.UI_COMPARISON__LAST_ACTION_SELECTION:
				return lastActionSelection != null;
		}
		return super.eIsSet(featureID);
	}
	
} //UIComparisonImpl
