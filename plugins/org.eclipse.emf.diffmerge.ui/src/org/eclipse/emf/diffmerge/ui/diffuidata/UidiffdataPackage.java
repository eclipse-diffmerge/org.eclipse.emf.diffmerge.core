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
package org.eclipse.emf.diffmerge.ui.diffuidata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataFactory
 * @model kind="package"
 * @generated
 */
@SuppressWarnings("hiding")
public interface UidiffdataPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uidiffdata"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.thalesgroup.com/mde/shared/consonance/ui/uidiffdata/1.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.emf.diffmerge.ui"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UidiffdataPackage eINSTANCE = org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl <em>UI Comparison</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getUIComparison()
	 * @generated
	 */
	int UI_COMPARISON = 0;

	/**
	 * The feature id for the '<em><b>Actual Comparison</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_COMPARISON__ACTUAL_COMPARISON = 0;

	/**
	 * The feature id for the '<em><b>Differences To Ignore</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_COMPARISON__DIFFERENCES_TO_IGNORE = 1;

	/**
	 * The feature id for the '<em><b>Last Action Selection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_COMPARISON__LAST_ACTION_SELECTION = 2;

	/**
	 * The number of structural features of the '<em>UI Comparison</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_COMPARISON_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jface.viewers.IStructuredSelection <em>IStructured Selection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jface.viewers.IStructuredSelection
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getIStructuredSelection()
	 * @generated
	 */
	int ISTRUCTURED_SELECTION = 4;

	/**
	 * The number of structural features of the '<em>IStructured Selection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTRUCTURED_SELECTION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl <em>Comparison Selection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getComparisonSelection()
	 * @generated
	 */
	int COMPARISON_SELECTION = 1;

	/**
	 * The feature id for the '<em><b>Selected Matches</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_SELECTION__SELECTED_MATCHES = ISTRUCTURED_SELECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Selected Match And Feature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE = ISTRUCTURED_SELECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Selected Tree Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_SELECTION__SELECTED_TREE_PATH = ISTRUCTURED_SELECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Selected Value Presences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES = ISTRUCTURED_SELECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Comparison Selection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_SELECTION_FEATURE_COUNT = ISTRUCTURED_SELECTION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl <em>Match And Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getMatchAndFeature()
	 * @generated
	 */
	int MATCH_AND_FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Match</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_AND_FEATURE__MATCH = 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_AND_FEATURE__FEATURE = 1;

	/**
	 * The number of structural features of the '<em>Match And Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_AND_FEATURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchToNbEntryImpl <em>Match To Nb Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchToNbEntryImpl
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getMatchToNbEntry()
	 * @generated
	 */
	int MATCH_TO_NB_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_TO_NB_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_TO_NB_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Match To Nb Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCH_TO_NB_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>Tree Path</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jface.viewers.TreePath
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getTreePath()
	 * @generated
	 */
	int TREE_PATH = 5;


	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison <em>UI Comparison</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UI Comparison</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison
	 * @generated
	 */
	EClass getUIComparison();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getActualComparison <em>Actual Comparison</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Actual Comparison</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getActualComparison()
	 * @see #getUIComparison()
	 * @generated
	 */
	EReference getUIComparison_ActualComparison();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getLastActionSelection <em>Last Action Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Last Action Selection</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison#getLastActionSelection()
	 * @see #getUIComparison()
	 * @generated
	 */
	EReference getUIComparison_LastActionSelection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection <em>Comparison Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison Selection</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection
	 * @generated
	 */
	EClass getComparisonSelection();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatches <em>Selected Matches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Matches</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatches()
	 * @see #getComparisonSelection()
	 * @generated
	 */
	EReference getComparisonSelection_SelectedMatches();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatchAndFeature <em>Selected Match And Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Selected Match And Feature</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatchAndFeature()
	 * @see #getComparisonSelection()
	 * @generated
	 */
	EReference getComparisonSelection_SelectedMatchAndFeature();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedTreePath <em>Selected Tree Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Tree Path</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedTreePath()
	 * @see #getComparisonSelection()
	 * @generated
	 */
	EReference getComparisonSelection_SelectedTreePath();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedValuePresences <em>Selected Value Presences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Value Presences</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedValuePresences()
	 * @see #getComparisonSelection()
	 * @generated
	 */
	EReference getComparisonSelection_SelectedValuePresences();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature <em>Match And Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Match And Feature</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature
	 * @generated
	 */
	EClass getMatchAndFeature();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getMatch <em>Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Match</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getMatch()
	 * @see #getMatchAndFeature()
	 * @generated
	 */
	EReference getMatchAndFeature_Match();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getFeature()
	 * @see #getMatchAndFeature()
	 * @generated
	 */
	EReference getMatchAndFeature_Feature();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Match To Nb Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Match To Nb Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.eclipse.emf.diffmerge.diffdata.EMatch" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EIntegerObject" valueRequired="true"
	 * @generated
	 */
	EClass getMatchToNbEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatchToNbEntry()
	 * @generated
	 */
	EReference getMatchToNbEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatchToNbEntry()
	 * @generated
	 */
	EAttribute getMatchToNbEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jface.viewers.IStructuredSelection <em>IStructured Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IStructured Selection</em>'.
	 * @see org.eclipse.jface.viewers.IStructuredSelection
	 * @model instanceClass="org.eclipse.jface.viewers.IStructuredSelection"
	 * @generated
	 */
	EClass getIStructuredSelection();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jface.viewers.TreePath <em>Tree Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tree Path</em>'.
	 * @see org.eclipse.jface.viewers.TreePath
	 * @model instanceClass="org.eclipse.jface.viewers.TreePath"
	 * @generated
	 */
	EDataType getTreePath();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UidiffdataFactory getUidiffdataFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl <em>UI Comparison</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UIComparisonImpl
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getUIComparison()
		 * @generated
		 */
		EClass UI_COMPARISON = eINSTANCE.getUIComparison();

		/**
		 * The meta object literal for the '<em><b>Actual Comparison</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_COMPARISON__ACTUAL_COMPARISON = eINSTANCE.getUIComparison_ActualComparison();

		/**
		 * The meta object literal for the '<em><b>Last Action Selection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_COMPARISON__LAST_ACTION_SELECTION = eINSTANCE.getUIComparison_LastActionSelection();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl <em>Comparison Selection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getComparisonSelection()
		 * @generated
		 */
		EClass COMPARISON_SELECTION = eINSTANCE.getComparisonSelection();

		/**
		 * The meta object literal for the '<em><b>Selected Matches</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_SELECTION__SELECTED_MATCHES = eINSTANCE.getComparisonSelection_SelectedMatches();

		/**
		 * The meta object literal for the '<em><b>Selected Match And Feature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE = eINSTANCE.getComparisonSelection_SelectedMatchAndFeature();

		/**
		 * The meta object literal for the '<em><b>Selected Tree Path</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_SELECTION__SELECTED_TREE_PATH = eINSTANCE.getComparisonSelection_SelectedTreePath();

		/**
		 * The meta object literal for the '<em><b>Selected Value Presences</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES = eINSTANCE.getComparisonSelection_SelectedValuePresences();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl <em>Match And Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getMatchAndFeature()
		 * @generated
		 */
		EClass MATCH_AND_FEATURE = eINSTANCE.getMatchAndFeature();

		/**
		 * The meta object literal for the '<em><b>Match</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATCH_AND_FEATURE__MATCH = eINSTANCE.getMatchAndFeature_Match();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATCH_AND_FEATURE__FEATURE = eINSTANCE.getMatchAndFeature_Feature();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchToNbEntryImpl <em>Match To Nb Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchToNbEntryImpl
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getMatchToNbEntry()
		 * @generated
		 */
		EClass MATCH_TO_NB_ENTRY = eINSTANCE.getMatchToNbEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATCH_TO_NB_ENTRY__KEY = eINSTANCE.getMatchToNbEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATCH_TO_NB_ENTRY__VALUE = eINSTANCE.getMatchToNbEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jface.viewers.IStructuredSelection <em>IStructured Selection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jface.viewers.IStructuredSelection
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getIStructuredSelection()
		 * @generated
		 */
		EClass ISTRUCTURED_SELECTION = eINSTANCE.getIStructuredSelection();

		/**
		 * The meta object literal for the '<em>Tree Path</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jface.viewers.TreePath
		 * @see org.eclipse.emf.diffmerge.ui.diffuidata.impl.UidiffdataPackageImpl#getTreePath()
		 * @generated
		 */
		EDataType TREE_PATH = eINSTANCE.getTreePath();

	}

} //UidiffdataPackage
