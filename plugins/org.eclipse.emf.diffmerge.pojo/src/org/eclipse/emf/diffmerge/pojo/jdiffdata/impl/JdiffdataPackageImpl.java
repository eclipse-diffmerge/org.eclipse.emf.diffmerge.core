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
package org.eclipse.emf.diffmerge.pojo.jdiffdata.impl;

import java.util.Map;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataFactory;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage;
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
public class JdiffdataPackageImpl extends EPackageImpl
    implements JdiffdataPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jComparisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jComparisonElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jMatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jMergeableDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jElementRelativePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jElementPresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jAttributeValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jReferenceValuePresenceEClass = null;

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
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private JdiffdataPackageImpl() {
    super(eNS_URI, JdiffdataFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link JdiffdataPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static JdiffdataPackage init() {
    if (isInited)
      return (JdiffdataPackage) EPackage.Registry.INSTANCE
          .getEPackage(JdiffdataPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredJdiffdataPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    JdiffdataPackageImpl theJdiffdataPackage = registeredJdiffdataPackage instanceof JdiffdataPackageImpl
        ? (JdiffdataPackageImpl) registeredJdiffdataPackage
        : new JdiffdataPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    GdiffdataPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theJdiffdataPackage.createPackageContents();

    // Initialize created meta-data
    theJdiffdataPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theJdiffdataPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(JdiffdataPackage.eNS_URI,
        theJdiffdataPackage);
    return theJdiffdataPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJComparison() {
    return jComparisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJComparisonElement() {
    return jComparisonElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJMapping() {
    return jMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMapping_AncestorMatchMap() {
    return (EReference) jMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMapping_ReferenceMatchMap() {
    return (EReference) jMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMapping_TargetMatchMap() {
    return (EReference) jMappingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJMatch() {
    return jMatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMatch_Ancestor() {
    return (EAttribute) jMatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMatch_Reference() {
    return (EAttribute) jMatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJMatch_Target() {
    return (EAttribute) jMatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMatch_ModifiableAttributeMap() {
    return (EReference) jMatchEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMatch_ModifiableReferenceMap() {
    return (EReference) jMatchEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJMatch_ModifiableOrderReferenceMap() {
    return (EReference) jMatchEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJMergeableDifference() {
    return jMergeableDifferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJElementRelativePresence() {
    return jElementRelativePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJElementPresence() {
    return jElementPresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJValuePresence() {
    return jValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJValuePresence_Feature() {
    return (EAttribute) jValuePresenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJAttributeValuePresence() {
    return jAttributeValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJAttributeValuePresence_Value() {
    return (EAttribute) jAttributeValuePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJReferenceValuePresence() {
    return jReferenceValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJReferenceValuePresence_Value() {
    return (EAttribute) jReferenceValuePresenceEClass.getEStructuralFeatures()
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
  public JdiffdataFactory getJdiffdataFactory() {
    return (JdiffdataFactory) getEFactoryInstance();
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
    jComparisonEClass = createEClass(JCOMPARISON);

    jComparisonElementEClass = createEClass(JCOMPARISON_ELEMENT);

    jMappingEClass = createEClass(JMAPPING);
    createEReference(jMappingEClass, JMAPPING__ANCESTOR_MATCH_MAP);
    createEReference(jMappingEClass, JMAPPING__REFERENCE_MATCH_MAP);
    createEReference(jMappingEClass, JMAPPING__TARGET_MATCH_MAP);

    jMatchEClass = createEClass(JMATCH);
    createEAttribute(jMatchEClass, JMATCH__ANCESTOR);
    createEAttribute(jMatchEClass, JMATCH__REFERENCE);
    createEAttribute(jMatchEClass, JMATCH__TARGET);
    createEReference(jMatchEClass, JMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    createEReference(jMatchEClass, JMATCH__MODIFIABLE_REFERENCE_MAP);
    createEReference(jMatchEClass, JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP);

    jMergeableDifferenceEClass = createEClass(JMERGEABLE_DIFFERENCE);

    jElementRelativePresenceEClass = createEClass(JELEMENT_RELATIVE_PRESENCE);

    jElementPresenceEClass = createEClass(JELEMENT_PRESENCE);

    jValuePresenceEClass = createEClass(JVALUE_PRESENCE);
    createEAttribute(jValuePresenceEClass, JVALUE_PRESENCE__FEATURE);

    jAttributeValuePresenceEClass = createEClass(JATTRIBUTE_VALUE_PRESENCE);
    createEAttribute(jAttributeValuePresenceEClass,
        JATTRIBUTE_VALUE_PRESENCE__VALUE);

    jReferenceValuePresenceEClass = createEClass(JREFERENCE_VALUE_PRESENCE);
    createEAttribute(jReferenceValuePresenceEClass,
        JREFERENCE_VALUE_PRESENCE__VALUE);

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
    ETypeParameter jComparisonEClass_E = addETypeParameter(jComparisonEClass,
        "E"); //$NON-NLS-1$
    ETypeParameter jComparisonElementEClass_E = addETypeParameter(
        jComparisonElementEClass, "E"); //$NON-NLS-1$
    ETypeParameter jMappingEClass_E = addETypeParameter(jMappingEClass, "E"); //$NON-NLS-1$
    ETypeParameter jMatchEClass_E = addETypeParameter(jMatchEClass, "E"); //$NON-NLS-1$
    ETypeParameter jMergeableDifferenceEClass_E = addETypeParameter(
        jMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter jElementRelativePresenceEClass_E = addETypeParameter(
        jElementRelativePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter jElementPresenceEClass_E = addETypeParameter(
        jElementPresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter jValuePresenceEClass_E = addETypeParameter(
        jValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter jAttributeValuePresenceEClass_E = addETypeParameter(
        jAttributeValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter jReferenceValuePresenceEClass_E = addETypeParameter(
        jReferenceValuePresenceEClass, "E"); //$NON-NLS-1$

    // Set bounds for type parameters
    EGenericType g1 = createEGenericType(ecorePackage.getEJavaObject());
    jComparisonEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jComparisonElementEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jMappingEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jMatchEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jMergeableDifferenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jElementRelativePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jElementPresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jValuePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jAttributeValuePresenceEClass_E.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaObject());
    jReferenceValuePresenceEClass_E.getEBounds().add(g1);

    // Add supertypes to classes
    g1 = createEGenericType(theGdiffdataPackage.getGComparison());
    EGenericType g2 = createEGenericType(jComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGComparisonElement());
    g2 = createEGenericType(jComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jComparisonElementEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMapping());
    g2 = createEGenericType(jMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJComparisonElement());
    g2 = createEGenericType(jMappingEClass_E);
    g1.getETypeArguments().add(g2);
    jMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMatch());
    g2 = createEGenericType(jMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJComparisonElement());
    g2 = createEGenericType(jMatchEClass_E);
    g1.getETypeArguments().add(g2);
    jMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGMergeableDifference());
    g2 = createEGenericType(jMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJComparisonElement());
    g2 = createEGenericType(jMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    jMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGElementRelativePresence());
    g2 = createEGenericType(jElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJMergeableDifference());
    g2 = createEGenericType(jElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    jElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGElementPresence());
    g2 = createEGenericType(jElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJElementRelativePresence());
    g2 = createEGenericType(jElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    jElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGValuePresence());
    g2 = createEGenericType(jValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJElementRelativePresence());
    g2 = createEGenericType(jValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    jValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGAttributeValuePresence());
    g2 = createEGenericType(jAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJValuePresence());
    g2 = createEGenericType(jAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    jAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(theGdiffdataPackage.getGReferenceValuePresence());
    g2 = createEGenericType(jReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(ecorePackage.getEJavaObject());
    g1.getETypeArguments().add(g2);
    jReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getJValuePresence());
    g2 = createEGenericType(jReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    jReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);

    // Initialize classes and features; add operations and parameters
    initEClass(jComparisonEClass, JComparison.class, "JComparison", //$NON-NLS-1$
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    EOperation op = addEOperation(jComparisonEClass, null, "getMapping", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJMapping());
    g2 = createEGenericType(jComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jComparisonElementEClass, JComparisonElement.class,
        "JComparisonElement", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(jComparisonElementEClass, null, "getComparison", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJComparison());
    g2 = createEGenericType(jComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jMappingEClass, JMapping.class, "JMapping", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getJMapping_AncestorMatchMap(),
        this.getElementToMatchEntry(), null, "ancestorMatchMap", null, 0, -1, //$NON-NLS-1$
        JMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        IS_DERIVED, IS_ORDERED);
    initEReference(getJMapping_ReferenceMatchMap(),
        this.getElementToMatchEntry(), null, "referenceMatchMap", null, 0, -1, //$NON-NLS-1$
        JMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        IS_DERIVED, IS_ORDERED);
    initEReference(getJMapping_TargetMatchMap(), this.getElementToMatchEntry(),
        null, "targetMatchMap", null, 0, -1, JMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

    op = addEOperation(jMappingEClass, null, "getMatchFor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    addEParameter(op, ecorePackage.getEJavaObject(), "potentialElement", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType(jMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(jMappingEClass, this.getElementToMatchEntry(),
        "getMatchMap", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    op = addEOperation(jMappingEClass, null, "map", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(jMappingEClass_E);
    addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, theGdiffdataPackage.getRole(), "role", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType(jMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jMatchEClass, JMatch.class, "JMatch", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(jMatchEClass_E);
    initEAttribute(getJMatch_Ancestor(), g1, "ancestor", null, 0, 1, //$NON-NLS-1$
        JMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(jMatchEClass_E);
    initEAttribute(getJMatch_Reference(), g1, "reference", null, 0, 1, //$NON-NLS-1$
        JMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(jMatchEClass_E);
    initEAttribute(getJMatch_Target(), g1, "target", null, 0, 1, JMatch.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMatch_ModifiableAttributeMap(),
        this.getAttributeToDifferenceEntry(), null, "modifiableAttributeMap", //$NON-NLS-1$
        null, 0, -1, JMatch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getJMatch_ModifiableReferenceMap(),
        this.getReferenceToElementToDifferenceEntry(), null,
        "modifiableReferenceMap", null, 0, -1, JMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getJMatch_ModifiableOrderReferenceMap(),
        this.getReferenceToOrderDifferenceEntry(), null,
        "modifiableOrderReferenceMap", null, 0, -1, JMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(jMatchEClass, null, "getMapping", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getJMapping());
    g2 = createEGenericType(jMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jMergeableDifferenceEClass, JMergeableDifference.class,
        "JMergeableDifference", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(jElementRelativePresenceEClass, JElementRelativePresence.class,
        "JElementRelativePresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(jElementRelativePresenceEClass, null, "getElementMatch", //$NON-NLS-1$
        1, 1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType(jElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jElementPresenceEClass, JElementPresence.class,
        "JElementPresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(jElementPresenceEClass, null, "getOwnerMatch", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType(jElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(jValuePresenceEClass, JValuePresence.class, "JValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJValuePresence_Feature(), ecorePackage.getEJavaObject(),
        "feature", null, 1, 1, JValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(jAttributeValuePresenceEClass, JAttributeValuePresence.class,
        "JAttributeValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJAttributeValuePresence_Value(),
        ecorePackage.getEJavaObject(), "value", null, 1, 1, //$NON-NLS-1$
        JAttributeValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    initEClass(jReferenceValuePresenceEClass, JReferenceValuePresence.class,
        "JReferenceValuePresence", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(jReferenceValuePresenceEClass_E);
    initEAttribute(getJReferenceValuePresence_Value(), g1, "value", null, 1, 1, //$NON-NLS-1$
        JReferenceValuePresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    op = addEOperation(jReferenceValuePresenceEClass, null, "getSymmetrical", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJReferenceValuePresence());
    g2 = createEGenericType(jReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(jReferenceValuePresenceEClass, null,
        "getSymmetricalOwnership", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getJReferenceValuePresence());
    g2 = createEGenericType(jReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    op = addEOperation(jReferenceValuePresenceEClass, null, "getValueMatch", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType(jReferenceValuePresenceEClass_E);
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
    g1 = createEGenericType(this.getJMatch());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    initEReference(getElementToMatchEntry_Value(), g1, null, "value", null, 1, //$NON-NLS-1$
        1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //JdiffdataPackageImpl
