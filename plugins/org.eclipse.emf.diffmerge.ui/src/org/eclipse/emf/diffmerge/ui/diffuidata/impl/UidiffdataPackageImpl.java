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

import java.util.Map;

import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataFactory;
import org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UidiffdataPackageImpl extends EPackageImpl implements UidiffdataPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uiComparisonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparisonSelectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matchAndFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matchToNbEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStructuredSelectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType treePathEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UidiffdataPackageImpl() {
		super(eNS_URI, UidiffdataFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UidiffdataPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UidiffdataPackage init() {
		if (isInited) return (UidiffdataPackage)EPackage.Registry.INSTANCE.getEPackage(UidiffdataPackage.eNS_URI);

		// Obtain or create and register package
		UidiffdataPackageImpl theUidiffdataPackage = (UidiffdataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UidiffdataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UidiffdataPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DiffdataPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUidiffdataPackage.createPackageContents();

		// Initialize created meta-data
		theUidiffdataPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUidiffdataPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UidiffdataPackage.eNS_URI, theUidiffdataPackage);
		return theUidiffdataPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIComparison() {
		return uiComparisonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIComparison_ActualComparison() {
		return (EReference)uiComparisonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIComparison_DifferencesToIgnore() {
		return (EReference)uiComparisonEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIComparison_LastActionSelection() {
		return (EReference)uiComparisonEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComparisonSelection() {
		return comparisonSelectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonSelection_SelectedMatches() {
		return (EReference)comparisonSelectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonSelection_SelectedMatchAndFeature() {
		return (EReference)comparisonSelectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonSelection_SelectedTreePath() {
		return (EReference)comparisonSelectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComparisonSelection_SelectedValuePresences() {
		return (EReference)comparisonSelectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMatchAndFeature() {
		return matchAndFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMatchAndFeature_Match() {
		return (EReference)matchAndFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMatchAndFeature_Feature() {
		return (EReference)matchAndFeatureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMatchToNbEntry() {
		return matchToNbEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMatchToNbEntry_Key() {
		return (EReference)matchToNbEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMatchToNbEntry_Value() {
		return (EAttribute)matchToNbEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIStructuredSelection() {
		return iStructuredSelectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTreePath() {
		return treePathEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UidiffdataFactory getUidiffdataFactory() {
		return (UidiffdataFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		uiComparisonEClass = createEClass(UI_COMPARISON);
		createEReference(uiComparisonEClass, UI_COMPARISON__ACTUAL_COMPARISON);
		createEReference(uiComparisonEClass, UI_COMPARISON__DIFFERENCES_TO_IGNORE);
		createEReference(uiComparisonEClass, UI_COMPARISON__LAST_ACTION_SELECTION);

		comparisonSelectionEClass = createEClass(COMPARISON_SELECTION);
		createEReference(comparisonSelectionEClass, COMPARISON_SELECTION__SELECTED_MATCHES);
		createEReference(comparisonSelectionEClass, COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE);
		createEReference(comparisonSelectionEClass, COMPARISON_SELECTION__SELECTED_TREE_PATH);
		createEReference(comparisonSelectionEClass, COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES);

		matchAndFeatureEClass = createEClass(MATCH_AND_FEATURE);
		createEReference(matchAndFeatureEClass, MATCH_AND_FEATURE__MATCH);
		createEReference(matchAndFeatureEClass, MATCH_AND_FEATURE__FEATURE);

		matchToNbEntryEClass = createEClass(MATCH_TO_NB_ENTRY);
		createEReference(matchToNbEntryEClass, MATCH_TO_NB_ENTRY__KEY);
		createEAttribute(matchToNbEntryEClass, MATCH_TO_NB_ENTRY__VALUE);

		iStructuredSelectionEClass = createEClass(ISTRUCTURED_SELECTION);

		// Create data types
		treePathEDataType = createEDataType(TREE_PATH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		DiffdataPackage theDiffdataPackage = (DiffdataPackage)EPackage.Registry.INSTANCE.getEPackage(DiffdataPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		comparisonSelectionEClass.getESuperTypes().add(this.getIStructuredSelection());

		// Initialize classes and features; add operations and parameters
		initEClass(uiComparisonEClass, UIComparison.class, "UIComparison", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getUIComparison_ActualComparison(), theDiffdataPackage.getEComparison(), null, "actualComparison", null, 1, 1, UIComparison.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getUIComparison_DifferencesToIgnore(), theDiffdataPackage.getEMergeableDifference(), null, "differencesToIgnore", null, 0, -1, UIComparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getUIComparison_LastActionSelection(), this.getComparisonSelection(), null, "lastActionSelection", null, 0, 1, UIComparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(uiComparisonEClass, null, "dispose", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(comparisonSelectionEClass, ComparisonSelection.class, "ComparisonSelection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getComparisonSelection_SelectedMatches(), theDiffdataPackage.getEMatch(), null, "selectedMatches", null, 0, -1, ComparisonSelection.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getComparisonSelection_SelectedMatchAndFeature(), this.getMatchAndFeature(), null, "selectedMatchAndFeature", null, 0, 1, ComparisonSelection.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getComparisonSelection_SelectedTreePath(), theDiffdataPackage.getEMatch(), null, "selectedTreePath", null, 0, -1, ComparisonSelection.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getComparisonSelection_SelectedValuePresences(), theDiffdataPackage.getEValuePresence(), null, "selectedValuePresences", null, 0, -1, ComparisonSelection.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theDiffdataPackage.getEMergeableDifference(), "asDifferencesToMerge", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theEcorePackage.getEStructuralFeature(), "asFeature", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theDiffdataPackage.getEMatch(), "asMatch", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theDiffdataPackage.getEMatch(), "asMatches", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, this.getTreePath(), "asMatchPath", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theDiffdataPackage.getEValuePresence(), "asValuePresence", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, theDiffdataPackage.getEValuePresence(), "asValuePresences", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(comparisonSelectionEClass, null, "dispose", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(matchAndFeatureEClass, MatchAndFeature.class, "MatchAndFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMatchAndFeature_Match(), theDiffdataPackage.getEMatch(), null, "match", null, 1, 1, MatchAndFeature.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMatchAndFeature_Feature(), theEcorePackage.getEStructuralFeature(), null, "feature", null, 1, 1, MatchAndFeature.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(matchToNbEntryEClass, Map.Entry.class, "MatchToNbEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMatchToNbEntry_Key(), theDiffdataPackage.getEMatch(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMatchToNbEntry_Value(), theEcorePackage.getEIntegerObject(), "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(iStructuredSelectionEClass, IStructuredSelection.class, "IStructuredSelection", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Initialize data types
		initEDataType(treePathEDataType, TreePath.class, "TreePath", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //UidiffdataPackageImpl
