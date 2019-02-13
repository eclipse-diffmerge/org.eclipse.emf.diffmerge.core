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

import java.util.Map;
import org.eclipse.emf.diffmerge.diffdata.DiffdataFactory;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EValuePresence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiffdataPackageImpl extends EPackageImpl
    implements DiffdataPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eComparisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eMatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eElementPresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eAttributeValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eReferenceValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass attributeToValueToDifferenceEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueToDifferenceEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass referenceToElementToDifferenceEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass referenceToOrderDifferenceEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementToDifferenceEntryEClass = null;

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DiffdataPackageImpl() {
    super(eNS_URI, DiffdataFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link DiffdataPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static DiffdataPackage init() {
    if (isInited)
      return (DiffdataPackage) EPackage.Registry.INSTANCE
          .getEPackage(DiffdataPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredDiffdataPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    DiffdataPackageImpl theDiffdataPackage = registeredDiffdataPackage instanceof DiffdataPackageImpl
        ? (DiffdataPackageImpl) registeredDiffdataPackage
        : new DiffdataPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.eINSTANCE
        .eClass();

    // Create package meta-data objects
    theDiffdataPackage.createPackageContents();

    // Initialize created meta-data
    theDiffdataPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDiffdataPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DiffdataPackage.eNS_URI, theDiffdataPackage);
    return theDiffdataPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEComparison() {
    return eComparisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEMapping() {
    return eMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEMatch() {
    return eMatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_Ancestor() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_Reference() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_Target() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ModifiableAttributeMap() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ModifiableReferenceMap() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ModifiableOrderReferenceMap() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEElementPresence() {
    return eElementPresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEValuePresence() {
    return eValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEValuePresence_Feature() {
    return (EReference) eValuePresenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEAttributeValuePresence() {
    return eAttributeValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEReferenceValuePresence() {
    return eReferenceValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEReferenceValuePresence_Value() {
    return (EReference) eReferenceValuePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttributeToValueToDifferenceEntry() {
    return attributeToValueToDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttributeToValueToDifferenceEntry_Key() {
    return (EReference) attributeToValueToDifferenceEntryEClass
        .getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttributeToValueToDifferenceEntry_Value() {
    return (EReference) attributeToValueToDifferenceEntryEClass
        .getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValueToDifferenceEntry() {
    return valueToDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getValueToDifferenceEntry_Key() {
    return (EAttribute) valueToDifferenceEntryEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getValueToDifferenceEntry_Value() {
    return (EReference) valueToDifferenceEntryEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getReferenceToElementToDifferenceEntry() {
    return referenceToElementToDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReferenceToElementToDifferenceEntry_Key() {
    return (EReference) referenceToElementToDifferenceEntryEClass
        .getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReferenceToElementToDifferenceEntry_Value() {
    return (EReference) referenceToElementToDifferenceEntryEClass
        .getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getReferenceToOrderDifferenceEntry() {
    return referenceToOrderDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReferenceToOrderDifferenceEntry_Key() {
    return (EReference) referenceToOrderDifferenceEntryEClass
        .getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReferenceToOrderDifferenceEntry_Value() {
    return (EReference) referenceToOrderDifferenceEntryEClass
        .getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementToDifferenceEntry() {
    return elementToDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementToDifferenceEntry_Key() {
    return (EReference) elementToDifferenceEntryEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementToDifferenceEntry_Value() {
    return (EReference) elementToDifferenceEntryEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffdataFactory getDiffdataFactory() {
    return (DiffdataFactory) getEFactoryInstance();
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
    eComparisonEClass = createEClass(ECOMPARISON);

    eMappingEClass = createEClass(EMAPPING);

    eMatchEClass = createEClass(EMATCH);
    createEReference(eMatchEClass, EMATCH__ANCESTOR);
    createEReference(eMatchEClass, EMATCH__REFERENCE);
    createEReference(eMatchEClass, EMATCH__TARGET);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_REFERENCE_MAP);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP);

    eElementPresenceEClass = createEClass(EELEMENT_PRESENCE);

    eValuePresenceEClass = createEClass(EVALUE_PRESENCE);
    createEReference(eValuePresenceEClass, EVALUE_PRESENCE__FEATURE);

    eAttributeValuePresenceEClass = createEClass(EATTRIBUTE_VALUE_PRESENCE);

    eReferenceValuePresenceEClass = createEClass(EREFERENCE_VALUE_PRESENCE);
    createEReference(eReferenceValuePresenceEClass,
        EREFERENCE_VALUE_PRESENCE__VALUE);

    attributeToValueToDifferenceEntryEClass = createEClass(
        ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY);
    createEReference(attributeToValueToDifferenceEntryEClass,
        ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(attributeToValueToDifferenceEntryEClass,
        ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__VALUE);

    valueToDifferenceEntryEClass = createEClass(VALUE_TO_DIFFERENCE_ENTRY);
    createEAttribute(valueToDifferenceEntryEClass,
        VALUE_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(valueToDifferenceEntryEClass,
        VALUE_TO_DIFFERENCE_ENTRY__VALUE);

    referenceToElementToDifferenceEntryEClass = createEClass(
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY);
    createEReference(referenceToElementToDifferenceEntryEClass,
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(referenceToElementToDifferenceEntryEClass,
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE);

    referenceToOrderDifferenceEntryEClass = createEClass(
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY);
    createEReference(referenceToOrderDifferenceEntryEClass,
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY);
    createEReference(referenceToOrderDifferenceEntryEClass,
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__VALUE);

    elementToDifferenceEntryEClass = createEClass(ELEMENT_TO_DIFFERENCE_ENTRY);
    createEReference(elementToDifferenceEntryEClass,
        ELEMENT_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(elementToDifferenceEntryEClass,
        ELEMENT_TO_DIFFERENCE_ENTRY__VALUE);
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
    org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage theGdiffdataPackage = (org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage) EPackage.Registry.INSTANCE
        .getEPackage(
            org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.eNS_URI);
    EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE
        .getEPackage(EcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    EGenericType g1 = createEGenericType(theGdiffdataPackage.getEComparison());
    EGenericType g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEMapping());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEMatch());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEElementPresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEAttributeValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getEReferenceValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEAttribute());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(theEcorePackage.getEReference());
    g1.getETypeArguments().add(g2);
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);

    // Initialize classes and features; add operations and parameters
    initEClass(eComparisonEClass, EComparison.class, "EComparison", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    addEOperation(eComparisonEClass, this.getEMapping(), "getMapping", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);

    initEClass(eMappingEClass, EMapping.class, "EMapping", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    addEOperation(eMappingEClass, this.getEComparison(), "getComparison", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);

    initEClass(eMatchEClass, EMatch.class, "EMatch", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEMatch_Ancestor(), ecorePackage.getEObject(), null,
        "ancestor", null, 0, 1, EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_Reference(), ecorePackage.getEObject(), null,
        "reference", null, 0, 1, EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_Target(), ecorePackage.getEObject(), null,
        "target", null, 0, 1, EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_ModifiableAttributeMap(),
        this.getAttributeToValueToDifferenceEntry(), null,
        "modifiableAttributeMap", null, 0, -1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_ModifiableReferenceMap(),
        this.getReferenceToElementToDifferenceEntry(), null,
        "modifiableReferenceMap", null, 0, -1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_ModifiableOrderReferenceMap(),
        this.getReferenceToOrderDifferenceEntry(), null,
        "modifiableOrderReferenceMap", null, 0, -1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    addEOperation(eMatchEClass, this.getEMapping(), "getMapping", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);

    initEClass(eElementPresenceEClass, EElementPresence.class,
        "EElementPresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(eValuePresenceEClass, EValuePresence.class, "EValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEValuePresence_Feature(),
        theEcorePackage.getEStructuralFeature(), null, "feature", null, 1, 1, //$NON-NLS-1$
        EValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(eAttributeValuePresenceEClass, EAttributeValuePresence.class,
        "EAttributeValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(eReferenceValuePresenceEClass, EReferenceValuePresence.class,
        "EReferenceValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEReferenceValuePresence_Value(),
        theEcorePackage.getEObject(), null, "value", null, 1, 1, //$NON-NLS-1$
        EReferenceValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(attributeToValueToDifferenceEntryEClass, Map.Entry.class,
        "AttributeToValueToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAttributeToValueToDifferenceEntry_Key(),
        theEcorePackage.getEAttribute(), null, "key", null, 1, 1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getAttributeToValueToDifferenceEntry_Value(),
        this.getValueToDifferenceEntry(), null, "value", null, 0, -1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(valueToDifferenceEntryEClass, Map.Entry.class,
        "ValueToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getValueToDifferenceEntry_Key(),
        theEcorePackage.getEJavaObject(), "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIAttributeValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    initEReference(getValueToDifferenceEntry_Value(), g1, null, "value", null, //$NON-NLS-1$
        1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(referenceToElementToDifferenceEntryEClass, Map.Entry.class,
        "ReferenceToElementToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEReference(getReferenceToElementToDifferenceEntry_Key(),
        theEcorePackage.getEReference(), null, "key", null, 1, 1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getReferenceToElementToDifferenceEntry_Value(),
        this.getElementToDifferenceEntry(), null, "value", null, 0, -1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(referenceToOrderDifferenceEntryEClass, Map.Entry.class,
        "ReferenceToOrderDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEReference(getReferenceToOrderDifferenceEntry_Key(),
        theEcorePackage.getEReference(), null, "key", null, 1, 1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIReferenceValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    initEReference(getReferenceToOrderDifferenceEntry_Value(), g1, null,
        "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementToDifferenceEntryEClass, Map.Entry.class,
        "ElementToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEReference(getElementToDifferenceEntry_Key(),
        theEcorePackage.getEObject(), null, "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIReferenceValuePresence());
    g2 = createEGenericType(theEcorePackage.getEObject());
    g1.getETypeArguments().add(g2);
    initEReference(getElementToDifferenceEntry_Value(), g1, null, "value", null, //$NON-NLS-1$
        1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //DiffdataPackageImpl
