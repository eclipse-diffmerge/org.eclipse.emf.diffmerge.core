/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.diffuidata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A wrapper for EComparison which contains GUI-related data.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getActualComparison <em>Actual Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getDifferencesToIgnore <em>Differences To Ignore</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getLastActionSelection <em>Last Action Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getUIComparison()
 * @model
 * @generated
 */
public interface UIComparison extends EObject {
	/**
	 * Returns the value of the '<em><b>Actual Comparison</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Comparison</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Comparison</em>' containment reference.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getUIComparison_ActualComparison()
	 * @model containment="true" required="true" changeable="false"
	 * @generated
	 */
	EComparison getActualComparison();

	/**
	 * Returns the value of the '<em><b>Differences To Ignore</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference}.
	 * <!-- begin-user-doc -->
	 * The differences which should not be represented
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Differences To Ignore</em>' reference list.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getUIComparison_DifferencesToIgnore()
	 * @model ordered="false"
	 * @generated
	 */
	EList<EMergeableDifference> getDifferencesToIgnore();

	/**
	 * Returns the value of the '<em><b>Last Action Selection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * The user selection when the user last made an action
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Action Selection</em>' containment reference.
	 * @see #setLastActionSelection(ComparisonSelection)
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getUIComparison_LastActionSelection()
	 * @model containment="true"
	 * @generated
	 */
	ComparisonSelection getLastActionSelection();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getLastActionSelection <em>Last Action Selection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * Set selection that was effective when the user last made an action
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Action Selection</em>' containment reference.
	 * @see #getLastActionSelection()
	 * @generated
	 */
	void setLastActionSelection(ComparisonSelection value);

  /**
   * <!-- begin-user-doc -->
   * Clear the receiver
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void clear();

	/**
	 * <!-- begin-user-doc -->
	 * Dispose the receiver
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void dispose();

} // UIComparison
