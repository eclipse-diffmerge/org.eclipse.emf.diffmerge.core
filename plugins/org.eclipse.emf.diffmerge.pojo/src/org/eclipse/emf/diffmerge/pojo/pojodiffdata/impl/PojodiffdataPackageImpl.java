/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl;

import java.util.Map;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataFactory;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PojodiffdataPackageImpl extends EPackageImpl
    implements PojodiffdataPackage {
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
  private EClass eComparisonElementEClass = null;

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
  private EClass eMergeableDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eElementRelativePresenceEClass = null;

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
  private EClass attributeToDifferenceEntryEClass = null;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementToMatchEntryEClass = null;

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
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private PojodiffdataPackageImpl() {
    super(eNS_URI, PojodiffdataFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link PojodiffdataPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static PojodiffdataPackage init() {
    if (isInited)
      return (PojodiffdataPackage) EPackage.Registry.INSTANCE
          .getEPackage(PojodiffdataPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredPojodiffdataPackage = EPackage.Registry.INSTANCE
        .get(eNS_URI);
    PojodiffdataPackageImpl thePojodiffdataPackage = registeredPojodiffdataPackage instanceof PojodiffdataPackageImpl
        ? (PojodiffdataPackageImpl) registeredPojodiffdataPackage
        : new PojodiffdataPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    GdiffdataPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    thePojodiffdataPackage.createPackageContents();

    // Initialize created meta-data
    thePojodiffdataPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    thePojodiffdataPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(PojodiffdataPackage.eNS_URI,
        thePojodiffdataPackage);
    return thePojodiffdataPackage;
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
  public EClass getEComparisonElement() {
    return eComparisonElementEClass;
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
  public EReference getEMapping_AncestorMatchMap() {
    return (EReference) eMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMapping_ReferenceMatchMap() {
    return (EReference) eMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMapping_TargetMatchMap() {
    return (EReference) eMappingEClass.getEStructuralFeatures().get(2);
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
  public EAttribute getEMatch_Ancestor() {
    return (EAttribute) eMatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMatch_Reference() {
    return (EAttribute) eMatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMatch_Target() {
    return (EAttribute) eMatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEMergeableDifference() {
    return eMergeableDifferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEElementRelativePresence() {
    return eElementRelativePresenceEClass;
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
  public EAttribute getEValuePresence_Feature() {
    return (EAttribute) eValuePresenceEClass.getEStructuralFeatures().get(0);
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
  public EAttribute getEAttributeValuePresence_Value() {
    return (EAttribute) eAttributeValuePresenceEClass.getEStructuralFeatures()
        .get(0);
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
  public EAttribute getEReferenceValuePresence_Value() {
    return (EAttribute) eReferenceValuePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttributeToDifferenceEntry() {
    return attributeToDifferenceEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAttributeToDifferenceEntry_Key() {
    return (EAttribute) attributeToDifferenceEntryEClass
        .getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttributeToDifferenceEntry_Value() {
    return (EReference) attributeToDifferenceEntryEClass
        .getEStructuralFeatures().get(1);
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
  public EAttribute getReferenceToElementToDifferenceEntry_Key() {
    return (EAttribute) referenceToElementToDifferenceEntryEClass
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
  public EAttribute getReferenceToOrderDifferenceEntry_Key() {
    return (EAttribute) referenceToOrderDifferenceEntryEClass
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
  public EAttribute getElementToDifferenceEntry_Key() {
    return (EAttribute) elementToDifferenceEntryEClass.getEStructuralFeatures()
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
  public EClass getElementToMatchEntry() {
    return elementToMatchEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementToMatchEntry_Key() {
    return (EAttribute) elementToMatchEntryEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementToMatchEntry_Value() {
    return (EReference) elementToMatchEntryEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojodiffdataFactory getPojodiffdataFactory() {
    return (PojodiffdataFactory) getEFactoryInstance();
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

    eComparisonElementEClass = createEClass(ECOMPARISON_ELEMENT);

    eMappingEClass = createEClass(EMAPPING);
    createEReference(eMappingEClass, EMAPPING__ANCESTOR_MATCH_MAP);
    createEReference(eMappingEClass, EMAPPING__REFERENCE_MATCH_MAP);
    createEReference(eMappingEClass, EMAPPING__TARGET_MATCH_MAP);

    eMatchEClass = createEClass(EMATCH);
    createEAttribute(eMatchEClass, EMATCH__ANCESTOR);
    createEAttribute(eMatchEClass, EMATCH__REFERENCE);
    createEAttribute(eMatchEClass, EMATCH__TARGET);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_REFERENCE_MAP);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP);

    eMergeableDifferenceEClass = createEClass(EMERGEABLE_DIFFERENCE);

    eElementRelativePresenceEClass = createEClass(EELEMENT_RELATIVE_PRESENCE);

    eElementPresenceEClass = createEClass(EELEMENT_PRESENCE);

    eValuePresenceEClass = createEClass(EVALUE_PRESENCE);
    createEAttribute(eValuePresenceEClass, EVALUE_PRESENCE__FEATURE);

    eAttributeValuePresenceEClass = createEClass(EATTRIBUTE_VALUE_PRESENCE);
    createEAttribute(eAttributeValuePresenceEClass,
        EATTRIBUTE_VALUE_PRESENCE__VALUE);

    eReferenceValuePresenceEClass = createEClass(EREFERENCE_VALUE_PRESENCE);
    createEAttribute(eReferenceValuePresenceEClass,
        EREFERENCE_VALUE_PRESENCE__VALUE);

    attributeToDifferenceEntryEClass = createEClass(
        ATTRIBUTE_TO_DIFFERENCE_ENTRY);
    createEAttribute(attributeToDifferenceEntryEClass,
        ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(attributeToDifferenceEntryEClass,
        ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE);

    referenceToElementToDifferenceEntryEClass = createEClass(
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY);
    createEAttribute(referenceToElementToDifferenceEntryEClass,
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(referenceToElementToDifferenceEntryEClass,
        REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE);

    referenceToOrderDifferenceEntryEClass = createEClass(
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY);
    createEAttribute(referenceToOrderDifferenceEntryEClass,
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY);
    createEReference(referenceToOrderDifferenceEntryEClass,
        REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__VALUE);

    elementToDifferenceEntryEClass = createEClass(ELEMENT_TO_DIFFERENCE_ENTRY);
    createEAttribute(elementToDifferenceEntryEClass,
        ELEMENT_TO_DIFFERENCE_ENTRY__KEY);
    createEReference(elementToDifferenceEntryEClass,
        ELEMENT_TO_DIFFERENCE_ENTRY__VALUE);

    elementToMatchEntryEClass = createEClass(ELEMENT_TO_MATCH_ENTRY);
    createEAttribute(elementToMatchEntryEClass, ELEMENT_TO_MATCH_ENTRY__KEY);
    createEReference(elementToMatchEntryEClass, ELEMENT_TO_MATCH_ENTRY__VALUE);
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

    // Create type parameters
    ETypeParameter eComparisonEClass_E = addETypeParameter(eComparisonEClass,
        "E"); //$NON-NLS-1$
    ETypeParameter eComparisonElementEClass_E = addETypeParameter(
        eComparisonElementEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMappingEClass_E = addETypeParameter(eMappingEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMatchEClass_E = addETypeParameter(eMatchEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMergeableDifferenceEClass_E = addETypeParameter(
        eMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eElementRelativePresenceEClass_E = addETypeParameter(
        eElementRelativePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eElementPresenceEClass_E = addETypeParameter(
        eElementPresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eValuePresenceEClass_E = addETypeParameter(
        eValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eAttributeValuePresenceEClass_E = addETypeParameter(
        eAttributeValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eReferenceValuePresenceEClass_E = addETypeParameter(
        eReferenceValuePresenceEClass, "E"); //$NON-NLS-1$

    // Set bounds for type parameters
    EGenericType g1 = createEGenericType(ecorePackage.getEJavaObject());
    eComparisonEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eComparisonElementEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eMappingEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eMatchEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eMergeableDifferenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eElementRelativePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eElementPresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eValuePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eAttributeValuePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    eReferenceValuePresenceEClass_E.getEBounds().add(g1);

    // Add supertypes to classes
    g1 = createEGenericType(theGdiffdataPackage.getGComparison());
    EGenericType g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGComparisonElement());
    g2 = createEGenericType(eComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eComparisonElementEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMapping());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMatch());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    eMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGElementRelativePresence());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEMergeableDifference());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGElementPresence());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEElementRelativePresence());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGValuePresence());
    g2 = createEGenericType(eValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEElementRelativePresence());
    g2 = createEGenericType(eValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGAttributeValuePresence());
    g2 = createEGenericType(eAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    g2 = createEGenericType(eAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGReferenceValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);

    // Initialize classes and features; add operations and parameters
    initEClass(eComparisonEClass, EComparison.class, "EComparison", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    EOperation op = addEOperation(eComparisonEClass, null, "getMapping", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEMapping());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eComparisonElementEClass, EComparisonElement.class,
        "EComparisonElement", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(eComparisonElementEClass, null, "getComparison", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEComparison());
    g2 = createEGenericType(eComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eMappingEClass, EMapping.class, "EMapping", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEMapping_AncestorMatchMap(),
        this.getElementToMatchEntry(), null, "ancestorMatchMap", null, 0, -1, //$NON-NLS-1$
        EMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        IS_DERIVED, IS_ORDERED);
    initEReference(getEMapping_ReferenceMatchMap(),
        this.getElementToMatchEntry(), null, "referenceMatchMap", null, 0, -1, //$NON-NLS-1$
        EMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        IS_DERIVED, IS_ORDERED);
    initEReference(getEMapping_TargetMatchMap(), this.getElementToMatchEntry(),
        null, "targetMatchMap", null, 0, -1, EMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    op = addEOperation(eMappingEClass, null, "getMatchFor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    addEParameter(op, ecorePackage.getEJavaObject(), "potentialElement", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(eMappingEClass, this.getElementToMatchEntry(),
        "getMatchMap", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    op = addEOperation(eMappingEClass, null, "map", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMappingEClass_E);
    addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eMatchEClass, EMatch.class, "EMatch", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(eMatchEClass_E);
    initEAttribute(getEMatch_Ancestor(), g1, "ancestor", null, 0, 1, //$NON-NLS-1$
        EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    initEAttribute(getEMatch_Reference(), g1, "reference", null, 0, 1, //$NON-NLS-1$
        EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    initEAttribute(getEMatch_Target(), g1, "target", null, 0, 1, EMatch.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEMatch_ModifiableAttributeMap(),
        this.getAttributeToDifferenceEntry(), null, "modifiableAttributeMap", //$NON-NLS-1$
        null, 0, -1, EMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
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

    op = addEOperation(eMatchEClass, null, "getMapping", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getEMapping());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eMergeableDifferenceEClass, EMergeableDifference.class,
        "EMergeableDifference", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(eElementRelativePresenceEClass, EElementRelativePresence.class,
        "EElementRelativePresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(eElementRelativePresenceEClass, null, "getElementMatch", //$NON-NLS-1$
        1, 1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eElementPresenceEClass, EElementPresence.class,
        "EElementPresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(eElementPresenceEClass, null, "getOwnerMatch", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eValuePresenceEClass, EValuePresence.class, "EValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEValuePresence_Feature(), ecorePackage.getEJavaObject(),
        "feature", null, 1, 1, EValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(eAttributeValuePresenceEClass, EAttributeValuePresence.class,
        "EAttributeValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEAttributeValuePresence_Value(),
        ecorePackage.getEJavaObject(), "value", null, 1, 1, //$NON-NLS-1$
        EAttributeValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    initEClass(eReferenceValuePresenceEClass, EReferenceValuePresence.class,
        "EReferenceValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(eReferenceValuePresenceEClass_E);
    initEAttribute(getEReferenceValuePresence_Value(), g1, "value", null, 1, 1, //$NON-NLS-1$
        EReferenceValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    op = addEOperation(eReferenceValuePresenceEClass, null, "getSymmetrical", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEReferenceValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(eReferenceValuePresenceEClass, null,
        "getSymmetricalOwnership", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getEReferenceValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(eReferenceValuePresenceEClass, null, "getValueMatch", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(attributeToDifferenceEntryEClass, Map.Entry.class,
        "AttributeToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAttributeToDifferenceEntry_Key(),
        ecorePackage.getEJavaObject(), "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIAttributeValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getAttributeToDifferenceEntry_Value(), g1, null, "value", //$NON-NLS-1$
        null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(referenceToElementToDifferenceEntryEClass, Map.Entry.class,
        "ReferenceToElementToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getReferenceToElementToDifferenceEntry_Key(),
        ecorePackage.getEJavaObject(), "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getReferenceToElementToDifferenceEntry_Value(),
        this.getElementToDifferenceEntry(), null, "value", null, 0, -1, //$NON-NLS-1$
        Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(referenceToOrderDifferenceEntryEClass, Map.Entry.class,
        "ReferenceToOrderDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getReferenceToOrderDifferenceEntry_Key(),
        ecorePackage.getEJavaObject(), "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIReferenceValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getReferenceToOrderDifferenceEntry_Value(), g1, null,
        "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementToDifferenceEntryEClass, Map.Entry.class,
        "ElementToDifferenceEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getElementToDifferenceEntry_Key(),
        ecorePackage.getEJavaObject(), "key", null, 1, 1, Map.Entry.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(theGdiffdataPackage.getIReferenceValuePresence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getElementToDifferenceEntry_Value(), g1, null, "value", null, //$NON-NLS-1$
        1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(elementToMatchEntryEClass, Map.Entry.class,
        "ElementToMatchEntry", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getElementToMatchEntry_Key(), ecorePackage.getEJavaObject(),
        "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getElementToMatchEntry_Value(), g1, null, "value", null, 1, //$NON-NLS-1$
        1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //PojodiffdataPackageImpl
