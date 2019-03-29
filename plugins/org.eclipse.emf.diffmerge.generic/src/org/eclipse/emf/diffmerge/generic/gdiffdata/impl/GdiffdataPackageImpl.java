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
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataFactory;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class GdiffdataPackageImpl extends EPackageImpl
    implements GdiffdataPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eIdentifiedEClass = null;

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
  private EClass iComparisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iEditableComparisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iEditableMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iMatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iEditableMatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iMergeableDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iEditableMergeableDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iElementRelativePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iElementPresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iAttributeValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iReferenceValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType iEditableTreeDataScopeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType iMatchPolicyEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType iDiffPolicyEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType iMergePolicyEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType roleEDataType = null;

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private GdiffdataPackageImpl() {
    super(eNS_URI, GdiffdataFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link GdiffdataPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static GdiffdataPackage init() {
    if (isInited)
      return (GdiffdataPackage) EPackage.Registry.INSTANCE
          .getEPackage(GdiffdataPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredGdiffdataPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    GdiffdataPackageImpl theGdiffdataPackage = registeredGdiffdataPackage instanceof GdiffdataPackageImpl
        ? (GdiffdataPackageImpl) registeredGdiffdataPackage
        : new GdiffdataPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theGdiffdataPackage.createPackageContents();

    // Initialize created meta-data
    theGdiffdataPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theGdiffdataPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(GdiffdataPackage.eNS_URI,
        theGdiffdataPackage);
    return theGdiffdataPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEIdentified() {
    return eIdentifiedEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEIdentified_Id() {
    return (EAttribute) eIdentifiedEClass.getEStructuralFeatures().get(0);
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
  public EAttribute getEComparison_AncestorScope() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEComparison_ReferenceScope() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEComparison_TargetScope() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEComparison_LastMatchPolicy() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEComparison_LastDiffPolicy() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEComparison_LastMergePolicy() {
    return (EAttribute) eComparisonEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEComparison_Mapping() {
    return (EReference) eComparisonEClass.getEStructuralFeatures().get(6);
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
  public EReference getEMapping_ModifiableContents() {
    return (EReference) eMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMapping_ReferenceCompletedMatches() {
    return (EReference) eMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMapping_TargetCompletedMatches() {
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
  public EAttribute getEMatch_MatchID() {
    return (EAttribute) eMatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ModifiableRelatedDifferences() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ElementPresenceDifference() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_ReferenceOwnershipDifference() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMatch_TargetOwnershipDifference() {
    return (EReference) eMatchEClass.getEStructuralFeatures().get(4);
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
  public EAttribute getEMergeableDifference_AlignedWithAncestor() {
    return (EAttribute) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMergeableDifference_Conflicting() {
    return (EAttribute) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMergeableDifference_Ignored() {
    return (EAttribute) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMergeableDifference_MergeDestination() {
    return (EAttribute) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEMergeableDifference_PossibleMergeDestinations() {
    return (EAttribute) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMergeableDifference_ExplicitDependenciesForTarget() {
    return (EReference) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMergeableDifference_ExplicitDependenciesForReference() {
    return (EReference) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMergeableDifference_ImplicitDependenciesForTarget() {
    return (EReference) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEMergeableDifference_ImplicitDependenciesForReference() {
    return (EReference) eMergeableDifferenceEClass.getEStructuralFeatures()
        .get(8);
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
  public EReference getEElementRelativePresence_ElementMatch() {
    return (EReference) eElementRelativePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEElementRelativePresence_PresenceRole() {
    return (EAttribute) eElementRelativePresenceEClass.getEStructuralFeatures()
        .get(1);
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
  public EReference getEElementPresence_OwnerMatch() {
    return (EReference) eElementPresenceEClass.getEStructuralFeatures().get(0);
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
  public EAttribute getEValuePresence_Order() {
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
  public EClass getEReferenceValuePresence() {
    return eReferenceValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEReferenceValuePresence_ValueMatch() {
    return (EReference) eReferenceValuePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIComparison() {
    return iComparisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIEditableComparison() {
    return iEditableComparisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIMapping() {
    return iMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIEditableMapping() {
    return iEditableMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIMatch() {
    return iMatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIEditableMatch() {
    return iEditableMatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIMergeableDifference() {
    return iMergeableDifferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIEditableMergeableDifference() {
    return iEditableMergeableDifferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIElementRelativePresence() {
    return iElementRelativePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIElementPresence() {
    return iElementPresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIValuePresence() {
    return iValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIAttributeValuePresence() {
    return iAttributeValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIReferenceValuePresence() {
    return iReferenceValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIEditableTreeDataScope() {
    return iEditableTreeDataScopeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIMatchPolicy() {
    return iMatchPolicyEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIDiffPolicy() {
    return iDiffPolicyEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIMergePolicy() {
    return iMergePolicyEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRole() {
    return roleEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GdiffdataFactory getGdiffdataFactory() {
    return (GdiffdataFactory) getEFactoryInstance();
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
    eIdentifiedEClass = createEClass(EIDENTIFIED);
    createEAttribute(eIdentifiedEClass, EIDENTIFIED__ID);

    eComparisonEClass = createEClass(ECOMPARISON);
    createEAttribute(eComparisonEClass, ECOMPARISON__ANCESTOR_SCOPE);
    createEAttribute(eComparisonEClass, ECOMPARISON__REFERENCE_SCOPE);
    createEAttribute(eComparisonEClass, ECOMPARISON__TARGET_SCOPE);
    createEAttribute(eComparisonEClass, ECOMPARISON__LAST_MATCH_POLICY);
    createEAttribute(eComparisonEClass, ECOMPARISON__LAST_DIFF_POLICY);
    createEAttribute(eComparisonEClass, ECOMPARISON__LAST_MERGE_POLICY);
    createEReference(eComparisonEClass, ECOMPARISON__MAPPING);

    eComparisonElementEClass = createEClass(ECOMPARISON_ELEMENT);

    eMappingEClass = createEClass(EMAPPING);
    createEReference(eMappingEClass, EMAPPING__MODIFIABLE_CONTENTS);
    createEReference(eMappingEClass, EMAPPING__REFERENCE_COMPLETED_MATCHES);
    createEReference(eMappingEClass, EMAPPING__TARGET_COMPLETED_MATCHES);

    eMatchEClass = createEClass(EMATCH);
    createEAttribute(eMatchEClass, EMATCH__MATCH_ID);
    createEReference(eMatchEClass, EMATCH__MODIFIABLE_RELATED_DIFFERENCES);
    createEReference(eMatchEClass, EMATCH__ELEMENT_PRESENCE_DIFFERENCE);
    createEReference(eMatchEClass, EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE);
    createEReference(eMatchEClass, EMATCH__TARGET_OWNERSHIP_DIFFERENCE);

    eMergeableDifferenceEClass = createEClass(EMERGEABLE_DIFFERENCE);
    createEAttribute(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR);
    createEAttribute(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__CONFLICTING);
    createEAttribute(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__IGNORED);
    createEAttribute(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__MERGE_DESTINATION);
    createEAttribute(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS);
    createEReference(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET);
    createEReference(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE);
    createEReference(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET);
    createEReference(eMergeableDifferenceEClass,
        EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE);

    eElementRelativePresenceEClass = createEClass(EELEMENT_RELATIVE_PRESENCE);
    createEReference(eElementRelativePresenceEClass,
        EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH);
    createEAttribute(eElementRelativePresenceEClass,
        EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE);

    eElementPresenceEClass = createEClass(EELEMENT_PRESENCE);
    createEReference(eElementPresenceEClass, EELEMENT_PRESENCE__OWNER_MATCH);

    eValuePresenceEClass = createEClass(EVALUE_PRESENCE);
    createEAttribute(eValuePresenceEClass, EVALUE_PRESENCE__ORDER);

    eAttributeValuePresenceEClass = createEClass(EATTRIBUTE_VALUE_PRESENCE);

    eReferenceValuePresenceEClass = createEClass(EREFERENCE_VALUE_PRESENCE);
    createEReference(eReferenceValuePresenceEClass,
        EREFERENCE_VALUE_PRESENCE__VALUE_MATCH);

    iComparisonEClass = createEClass(ICOMPARISON);

    iEditableComparisonEClass = createEClass(IEDITABLE_COMPARISON);

    iMappingEClass = createEClass(IMAPPING);

    iEditableMappingEClass = createEClass(IEDITABLE_MAPPING);

    iMatchEClass = createEClass(IMATCH);

    iEditableMatchEClass = createEClass(IEDITABLE_MATCH);

    iMergeableDifferenceEClass = createEClass(IMERGEABLE_DIFFERENCE);

    iEditableMergeableDifferenceEClass = createEClass(
        IEDITABLE_MERGEABLE_DIFFERENCE);

    iElementRelativePresenceEClass = createEClass(IELEMENT_RELATIVE_PRESENCE);

    iElementPresenceEClass = createEClass(IELEMENT_PRESENCE);

    iValuePresenceEClass = createEClass(IVALUE_PRESENCE);

    iAttributeValuePresenceEClass = createEClass(IATTRIBUTE_VALUE_PRESENCE);

    iReferenceValuePresenceEClass = createEClass(IREFERENCE_VALUE_PRESENCE);

    // Create data types
    iEditableTreeDataScopeEDataType = createEDataType(
        IEDITABLE_TREE_DATA_SCOPE);
    iMatchPolicyEDataType = createEDataType(IMATCH_POLICY);
    iDiffPolicyEDataType = createEDataType(IDIFF_POLICY);
    iMergePolicyEDataType = createEDataType(IMERGE_POLICY);
    roleEDataType = createEDataType(ROLE);
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

    // Create type parameters
    ETypeParameter eComparisonEClass_E = addETypeParameter(eComparisonEClass,
        "E"); //$NON-NLS-1$
    ETypeParameter eComparisonEClass_A = addETypeParameter(eComparisonEClass,
        "A"); //$NON-NLS-1$
    ETypeParameter eComparisonEClass_R = addETypeParameter(eComparisonEClass,
        "R"); //$NON-NLS-1$
    ETypeParameter eComparisonElementEClass_E = addETypeParameter(
        eComparisonElementEClass, "E"); //$NON-NLS-1$
    ETypeParameter eComparisonElementEClass_A = addETypeParameter(
        eComparisonElementEClass, "A"); //$NON-NLS-1$
    ETypeParameter eComparisonElementEClass_R = addETypeParameter(
        eComparisonElementEClass, "R"); //$NON-NLS-1$
    ETypeParameter eMappingEClass_E = addETypeParameter(eMappingEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMappingEClass_A = addETypeParameter(eMappingEClass, "A"); //$NON-NLS-1$
    ETypeParameter eMappingEClass_R = addETypeParameter(eMappingEClass, "R"); //$NON-NLS-1$
    ETypeParameter eMatchEClass_E = addETypeParameter(eMatchEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMatchEClass_A = addETypeParameter(eMatchEClass, "A"); //$NON-NLS-1$
    ETypeParameter eMatchEClass_R = addETypeParameter(eMatchEClass, "R"); //$NON-NLS-1$
    ETypeParameter eMergeableDifferenceEClass_E = addETypeParameter(
        eMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eMergeableDifferenceEClass_A = addETypeParameter(
        eMergeableDifferenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eMergeableDifferenceEClass_R = addETypeParameter(
        eMergeableDifferenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter eElementRelativePresenceEClass_E = addETypeParameter(
        eElementRelativePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eElementRelativePresenceEClass_A = addETypeParameter(
        eElementRelativePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eElementRelativePresenceEClass_R = addETypeParameter(
        eElementRelativePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter eElementPresenceEClass_E = addETypeParameter(
        eElementPresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eElementPresenceEClass_A = addETypeParameter(
        eElementPresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eElementPresenceEClass_R = addETypeParameter(
        eElementPresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter eValuePresenceEClass_E = addETypeParameter(
        eValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eValuePresenceEClass_A = addETypeParameter(
        eValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eValuePresenceEClass_R = addETypeParameter(
        eValuePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter eAttributeValuePresenceEClass_E = addETypeParameter(
        eAttributeValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eAttributeValuePresenceEClass_A = addETypeParameter(
        eAttributeValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eAttributeValuePresenceEClass_R = addETypeParameter(
        eAttributeValuePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter eReferenceValuePresenceEClass_E = addETypeParameter(
        eReferenceValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter eReferenceValuePresenceEClass_A = addETypeParameter(
        eReferenceValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter eReferenceValuePresenceEClass_R = addETypeParameter(
        eReferenceValuePresenceEClass, "R"); //$NON-NLS-1$
    addETypeParameter(iComparisonEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iEditableComparisonEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iMappingEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iEditableMappingEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iMatchEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iEditableMatchEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iEditableMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iElementRelativePresenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iElementPresenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iValuePresenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iAttributeValuePresenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iReferenceValuePresenceEClass, "E"); //$NON-NLS-1$
    addETypeParameter(iEditableTreeDataScopeEDataType, "E"); //$NON-NLS-1$
    addETypeParameter(iMatchPolicyEDataType, "E"); //$NON-NLS-1$
    addETypeParameter(iDiffPolicyEDataType, "E"); //$NON-NLS-1$
    addETypeParameter(iMergePolicyEDataType, "E"); //$NON-NLS-1$

    // Set bounds for type parameters

    // Add supertypes to classes
    EGenericType g1 = createEGenericType(this.getEIdentified());
    eComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableComparison());
    EGenericType g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    eComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEIdentified());
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMappingEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMappingEClass_R);
    g1.getETypeArguments().add(g2);
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMapping());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    eMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEIdentified());
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMatchEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMatchEClass_R);
    g1.getETypeArguments().add(g2);
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMatch());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    eMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEIdentified());
    eMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEComparisonElement());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMergeableDifferenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMergeableDifferenceEClass_R);
    g1.getETypeArguments().add(g2);
    eMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    eMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEMergeableDifference());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementRelativePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementRelativePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    eElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIElementRelativePresence());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEElementRelativePresence());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementPresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementPresenceEClass_R);
    g1.getETypeArguments().add(g2);
    eElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIElementPresence());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEElementRelativePresence());
    g2 = createEGenericType(eValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    eValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIValuePresence());
    g2 = createEGenericType(eValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    g2 = createEGenericType(eAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eAttributeValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eAttributeValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIAttributeValuePresence());
    g2 = createEGenericType(eAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getEValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eReferenceValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eReferenceValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    eReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);

    // Initialize classes and features; add operations and parameters
    initEClass(eIdentifiedEClass, EIdentified.class, "EIdentified", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEIdentified_Id(), ecorePackage.getEString(), "id", null, //$NON-NLS-1$
        0, 1, EIdentified.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eComparisonEClass, EComparison.class, "EComparison", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_AncestorScope(), g1, "ancestorScope", null, 0, //$NON-NLS-1$
        1, EComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_ReferenceScope(), g1, "referenceScope", null, //$NON-NLS-1$
        1, 1, EComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_TargetScope(), g1, "targetScope", null, 1, 1, //$NON-NLS-1$
        EComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatchPolicy());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_LastMatchPolicy(), g1, "lastMatchPolicy", //$NON-NLS-1$
        null, 0, 1, EComparison.class, IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getIDiffPolicy());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_LastDiffPolicy(), g1, "lastDiffPolicy", null, //$NON-NLS-1$
        0, 1, EComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergePolicy());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getEComparison_LastMergePolicy(), g1, "lastMergePolicy", //$NON-NLS-1$
        null, 0, 1, EComparison.class, IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getEMapping());
    g2 = createEGenericType(eComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eComparisonEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eComparisonEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEComparison_Mapping(), g1, null, "mapping", null, 1, 1, //$NON-NLS-1$
        EComparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(eComparisonElementEClass, EComparisonElement.class,
        "EComparisonElement", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    EOperation op = addEOperation(eComparisonElementEClass, null,
        "getComparison", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getEComparison());
    g2 = createEGenericType(eComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eComparisonElementEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eComparisonElementEClass_R);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(eMappingEClass, EMapping.class, "EMapping", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMappingEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMappingEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEMapping_ModifiableContents(), g1, null,
        "modifiableContents", null, 0, -1, EMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatch());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMapping_ReferenceCompletedMatches(), g1, null,
        "referenceCompletedMatches", null, 0, -1, EMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatch());
    g2 = createEGenericType(eMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMapping_TargetCompletedMatches(), g1, null,
        "targetCompletedMatches", null, 0, -1, EMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(eMappingEClass, ecorePackage.getEBoolean(),
        "isIgnoredReferenceValue", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(eMappingEClass_E);
    addEParameter(op, g1, "source", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(eMappingEClass_R);
    addEParameter(op, g1, "reference", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(eMappingEClass_E);
    addEParameter(op, g1, "value", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, this.getRole(), "role", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(eMappingEClass, ecorePackage.getEBoolean(),
        "removeDependencies", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, this.getRole(), "role", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(eMappingEClass_E);
    addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(eMatchEClass, EMatch.class, "EMatch", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEMatch_MatchID(), ecorePackage.getEJavaObject(),
        "matchID", null, 0, 1, EMatch.class, IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getEMergeableDifference());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMatchEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eMatchEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEMatch_ModifiableRelatedDifferences(), g1, null,
        "modifiableRelatedDifferences", null, 0, -1, EMatch.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
        !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getIElementPresence());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMatch_ElementPresenceDifference(), g1, null,
        "elementPresenceDifference", null, 0, 1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMatch_ReferenceOwnershipDifference(), g1, null,
        "referenceOwnershipDifference", null, 0, 1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(eMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMatch_TargetOwnershipDifference(), g1, null,
        "targetOwnershipDifference", null, 0, 1, EMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(eMatchEClass, null, "getAncestor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(eMatchEClass, null, "getReference", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(eMatchEClass, null, "getTarget", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(eMatchEClass, null, "setAncestor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(eMatchEClass, null, "setReference", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(eMatchEClass, null, "setTarget", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(eMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(eMergeableDifferenceEClass, EMergeableDifference.class,
        "EMergeableDifference", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEMergeableDifference_AlignedWithAncestor(),
        ecorePackage.getEBoolean(), "alignedWithAncestor", "true", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEMergeableDifference_Conflicting(),
        ecorePackage.getEBoolean(), "conflicting", "false", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEMergeableDifference_Ignored(),
        ecorePackage.getEBoolean(), "ignored", null, 1, 1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEMergeableDifference_MergeDestination(), this.getRole(),
        "mergeDestination", null, 0, 1, EMergeableDifference.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEMergeableDifference_PossibleMergeDestinations(),
        this.getRole(), "possibleMergeDestinations", null, 0, -1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMergeableDifference_ExplicitDependenciesForTarget(), g1,
        null, "explicitDependenciesForTarget", null, 0, -1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMergeableDifference_ExplicitDependenciesForReference(),
        g1, null, "explicitDependenciesForReference", null, 0, -1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMergeableDifference_ImplicitDependenciesForTarget(), g1,
        null, "implicitDependenciesForTarget", null, 0, -1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(eMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getEMergeableDifference_ImplicitDependenciesForReference(),
        g1, null, "implicitDependenciesForReference", null, 0, -1, //$NON-NLS-1$
        EMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(eElementRelativePresenceEClass, EElementRelativePresence.class,
        "EElementRelativePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementRelativePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementRelativePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEElementRelativePresence_ElementMatch(), g1, null,
        "elementMatch", null, 1, 1, EElementRelativePresence.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEElementRelativePresence_PresenceRole(), this.getRole(),
        "presenceRole", null, 1, 1, EElementRelativePresence.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eElementPresenceEClass, EElementPresence.class,
        "EElementPresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementPresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eElementPresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEElementPresence_OwnerMatch(), g1, null, "ownerMatch", //$NON-NLS-1$
        null, 1, 1, EElementPresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eValuePresenceEClass, EValuePresence.class, "EValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEValuePresence_Order(), ecorePackage.getEBoolean(),
        "order", "false", 1, 1, EValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    addEOperation(eValuePresenceEClass, ecorePackage.getEJavaObject(),
        "getFeature", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(eAttributeValuePresenceEClass, EAttributeValuePresence.class,
        "EAttributeValuePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(eAttributeValuePresenceEClass, null, "getFeature", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(eAttributeValuePresenceEClass_A);
    initEOperation(op, g1);

    op = addEOperation(eAttributeValuePresenceEClass, null, "setAttribute", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(eAttributeValuePresenceEClass_A);
    addEParameter(op, g1, "attribute", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(eAttributeValuePresenceEClass, null, "setValue", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    initEClass(eReferenceValuePresenceEClass, EReferenceValuePresence.class,
        "EReferenceValuePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getEMatch());
    g2 = createEGenericType(eReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eReferenceValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(eReferenceValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getEReferenceValuePresence_ValueMatch(), g1, null,
        "valueMatch", null, 0, 1, EReferenceValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(eReferenceValuePresenceEClass, null, "getFeature", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(eReferenceValuePresenceEClass_R);
    initEOperation(op, g1);

    op = addEOperation(eReferenceValuePresenceEClass, null, "setReference", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(eReferenceValuePresenceEClass_R);
    addEParameter(op, g1, "reference", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(eReferenceValuePresenceEClass, null, "setValue", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(eReferenceValuePresenceEClass_E);
    addEParameter(op, g1, "value", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(iComparisonEClass, IComparison.class, "IComparison", IS_ABSTRACT, //$NON-NLS-1$
        IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iEditableComparisonEClass, Editable.class, "IEditableComparison", //$NON-NLS-1$
        IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iMappingEClass, IMapping.class, "IMapping", IS_ABSTRACT, //$NON-NLS-1$
        IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iEditableMappingEClass,
        org.eclipse.emf.diffmerge.generic.api.IMapping.Editable.class,
        "IEditableMapping", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iMatchEClass, IMatch.class, "IMatch", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iEditableMatchEClass,
        org.eclipse.emf.diffmerge.generic.api.IMatch.Editable.class,
        "IEditableMatch", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iMergeableDifferenceEClass, IMergeableDifference.class,
        "IMergeableDifference", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iEditableMergeableDifferenceEClass,
        org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable.class,
        "IEditableMergeableDifference", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iElementRelativePresenceEClass, IElementRelativePresence.class,
        "IElementRelativePresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iElementPresenceEClass, IElementPresence.class,
        "IElementPresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iValuePresenceEClass, IValuePresence.class, "IValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iAttributeValuePresenceEClass, IAttributeValuePresence.class,
        "IAttributeValuePresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    initEClass(iReferenceValuePresenceEClass, IReferenceValuePresence.class,
        "IReferenceValuePresence", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    // Initialize data types
    initEDataType(iEditableTreeDataScopeEDataType, IEditableTreeDataScope.class,
        "IEditableTreeDataScope", IS_SERIALIZABLE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(iMatchPolicyEDataType, IMatchPolicy.class, "IMatchPolicy", //$NON-NLS-1$
        IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(iDiffPolicyEDataType, IDiffPolicy.class, "IDiffPolicy", //$NON-NLS-1$
        IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(iMergePolicyEDataType, IMergePolicy.class, "IMergePolicy", //$NON-NLS-1$
        IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(roleEDataType, Role.class, "Role", IS_SERIALIZABLE, //$NON-NLS-1$
        !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //GdiffdataPackageImpl
