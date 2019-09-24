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
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataFactory;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;

import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiffuidataPackageImpl extends EPackageImpl
    implements DiffuidataPackage {
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
  private EClass iDisposableEClass = null;

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
  private EClass iDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType treePathEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType emfDiffNodeEDataType = null;

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
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DiffuidataPackageImpl() {
    super(eNS_URI, DiffuidataFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link DiffuidataPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static DiffuidataPackage init() {
    if (isInited)
      return (DiffuidataPackage) EPackage.Registry.INSTANCE
          .getEPackage(DiffuidataPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredDiffuidataPackage = EPackage.Registry.INSTANCE
        .get(eNS_URI);
    DiffuidataPackageImpl theDiffuidataPackage = registeredDiffuidataPackage instanceof DiffuidataPackageImpl
        ? (DiffuidataPackageImpl) registeredDiffuidataPackage
        : new DiffuidataPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    GdiffdataPackage.eINSTANCE.eClass();
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theDiffuidataPackage.createPackageContents();

    // Initialize created meta-data
    theDiffuidataPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDiffuidataPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DiffuidataPackage.eNS_URI,
        theDiffuidataPackage);
    return theDiffuidataPackage;
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
    return (EReference) uiComparisonEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUIComparison_LastActionSelection() {
    return (EReference) uiComparisonEClass.getEStructuralFeatures().get(1);
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
  public EAttribute getComparisonSelection_DiffNode() {
    return (EAttribute) comparisonSelectionEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComparisonSelection_SelectedMatches() {
    return (EReference) comparisonSelectionEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComparisonSelection_SelectedMatchAndFeature() {
    return (EReference) comparisonSelectionEClass.getEStructuralFeatures()
        .get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComparisonSelection_SelectedTreePath() {
    return (EReference) comparisonSelectionEClass.getEStructuralFeatures()
        .get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComparisonSelection_SelectedValuePresences() {
    return (EReference) comparisonSelectionEClass.getEStructuralFeatures()
        .get(4);
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
  public EAttribute getMatchAndFeature_Attribute() {
    return (EAttribute) matchAndFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMatchAndFeature_Match() {
    return (EReference) matchAndFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMatchAndFeature_Feature() {
    return (EAttribute) matchAndFeatureEClass.getEStructuralFeatures().get(2);
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
    return (EReference) matchToNbEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMatchToNbEntry_Value() {
    return (EAttribute) matchToNbEntryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIDisposable() {
    return iDisposableEClass;
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
  public EClass getIDifference() {
    return iDifferenceEClass;
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
  public EDataType getEMFDiffNode() {
    return emfDiffNodeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffuidataFactory getDiffuidataFactory() {
    return (DiffuidataFactory) getEFactoryInstance();
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
    if (isCreated)
      return;
    isCreated = true;

    // Create classes and their features
    uiComparisonEClass = createEClass(UI_COMPARISON);
    createEReference(uiComparisonEClass, UI_COMPARISON__ACTUAL_COMPARISON);
    createEReference(uiComparisonEClass, UI_COMPARISON__LAST_ACTION_SELECTION);

    comparisonSelectionEClass = createEClass(COMPARISON_SELECTION);
    createEAttribute(comparisonSelectionEClass,
        COMPARISON_SELECTION__DIFF_NODE);
    createEReference(comparisonSelectionEClass,
        COMPARISON_SELECTION__SELECTED_MATCHES);
    createEReference(comparisonSelectionEClass,
        COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE);
    createEReference(comparisonSelectionEClass,
        COMPARISON_SELECTION__SELECTED_TREE_PATH);
    createEReference(comparisonSelectionEClass,
        COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES);

    matchAndFeatureEClass = createEClass(MATCH_AND_FEATURE);
    createEAttribute(matchAndFeatureEClass, MATCH_AND_FEATURE__ATTRIBUTE);
    createEReference(matchAndFeatureEClass, MATCH_AND_FEATURE__MATCH);
    createEAttribute(matchAndFeatureEClass, MATCH_AND_FEATURE__FEATURE);

    matchToNbEntryEClass = createEClass(MATCH_TO_NB_ENTRY);
    createEReference(matchToNbEntryEClass, MATCH_TO_NB_ENTRY__KEY);
    createEAttribute(matchToNbEntryEClass, MATCH_TO_NB_ENTRY__VALUE);

    iDisposableEClass = createEClass(IDISPOSABLE);

    iStructuredSelectionEClass = createEClass(ISTRUCTURED_SELECTION);

    iDifferenceEClass = createEClass(IDIFFERENCE);

    // Create data types
    treePathEDataType = createEDataType(TREE_PATH);
    emfDiffNodeEDataType = createEDataType(EMF_DIFF_NODE);
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
    if (isInitialized)
      return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    GdiffdataPackage theGdiffdataPackage = (GdiffdataPackage) EPackage.Registry.INSTANCE
        .getEPackage(GdiffdataPackage.eNS_URI);
    EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE
        .getEPackage(EcorePackage.eNS_URI);

    // Create type parameters
    addETypeParameter(iDifferenceEClass, "E"); //$NON-NLS-1$

    // Set bounds for type parameters

    // Add supertypes to classes
    uiComparisonEClass.getESuperTypes().add(this.getIDisposable());
    comparisonSelectionEClass.getESuperTypes()
        .add(this.getIStructuredSelection());

    // Initialize classes and features; add operations and parameters
    initEClass(uiComparisonEClass, UIComparison.class, "UIComparison", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    EGenericType g1 = createEGenericType(theGdiffdataPackage.getEComparison());
    EGenericType g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getUIComparison_ActualComparison(), g1, null,
        "actualComparison", null, 1, 1, UIComparison.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUIComparison_LastActionSelection(),
        this.getComparisonSelection(), null, "lastActionSelection", null, 0, 1, //$NON-NLS-1$
        UIComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    addEOperation(uiComparisonEClass, null, "clear", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    initEClass(comparisonSelectionEClass, ComparisonSelection.class,
        "ComparisonSelection", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getComparisonSelection_DiffNode(), this.getEMFDiffNode(),
        "diffNode", null, 1, 1, ComparisonSelection.class, IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getComparisonSelection_SelectedMatches(), g1, null,
        "selectedMatches", null, 0, -1, ComparisonSelection.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComparisonSelection_SelectedMatchAndFeature(),
        this.getMatchAndFeature(), null, "selectedMatchAndFeature", null, 0, 1, //$NON-NLS-1$
        ComparisonSelection.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getComparisonSelection_SelectedTreePath(), g1, null,
        "selectedTreePath", null, 0, -1, ComparisonSelection.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getComparisonSelection_SelectedValuePresences(), g1, null,
        "selectedValuePresences", null, 0, -1, ComparisonSelection.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    EOperation op = addEOperation(comparisonSelectionEClass, null,
        "asDifferencesToMerge", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getIDifference());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    addEOperation(comparisonSelectionEClass, ecorePackage.getEJavaObject(),
        "asFeature", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    addEOperation(comparisonSelectionEClass, this.getMatchAndFeature(),
        "asMatchAndFeature", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(comparisonSelectionEClass, null, "asMatch", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(comparisonSelectionEClass, null, "asMatches", 0, -1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    addEOperation(comparisonSelectionEClass, this.getTreePath(), "asMatchPath", //$NON-NLS-1$
        0, 1, IS_UNIQUE, IS_ORDERED);

    op = addEOperation(comparisonSelectionEClass, null, "asValuePresence", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(comparisonSelectionEClass, null, "asValuePresences", 0, //$NON-NLS-1$
        -1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    addEOperation(comparisonSelectionEClass, null, "dispose", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    initEClass(matchAndFeatureEClass, MatchAndFeature.class, "MatchAndFeature", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMatchAndFeature_Attribute(), ecorePackage.getEBoolean(),
        "attribute", null, 1, 1, MatchAndFeature.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getMatchAndFeature_Match(), g1, null, "match", null, 1, 1, //$NON-NLS-1$
        MatchAndFeature.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMatchAndFeature_Feature(), ecorePackage.getEJavaObject(),
        "feature", null, 1, 1, MatchAndFeature.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(matchToNbEntryEClass, Map.Entry.class, "MatchToNbEntry", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(theGdiffdataPackage.getIMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getMatchToNbEntry_Key(), g1, null, "key", null, 1, 1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMatchToNbEntry_Value(),
        theEcorePackage.getEIntegerObject(), "value", null, 1, 1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(iDisposableEClass, IDisposable.class, "IDisposable", IS_ABSTRACT, //$NON-NLS-1$
        IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iStructuredSelectionEClass, IStructuredSelection.class,
        "IStructuredSelection", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iDifferenceEClass, IDifference.class, "IDifference", IS_ABSTRACT, //$NON-NLS-1$
        IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    // Initialize data types
    initEDataType(treePathEDataType, TreePath.class, "TreePath", //$NON-NLS-1$
        IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(emfDiffNodeEDataType, EMFDiffNode.class, "EMFDiffNode", //$NON-NLS-1$
        !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //DiffuidataPackageImpl
