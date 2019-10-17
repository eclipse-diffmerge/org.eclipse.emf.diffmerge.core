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
import org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence;
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
  private EClass gIdentifiedEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gComparisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gComparisonElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gMatchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gMergeableDifferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gElementRelativePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gElementPresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gAttributeValuePresenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass gReferenceValuePresenceEClass = null;

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
  public EClass getGIdentified() {
    return gIdentifiedEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGIdentified_Id() {
    return (EAttribute) gIdentifiedEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGComparison() {
    return gComparisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_AncestorScope() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_ReferenceScope() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_TargetScope() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_LastMatchPolicy() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_LastDiffPolicy() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGComparison_LastMergePolicy() {
    return (EAttribute) gComparisonEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGComparison_Mapping() {
    return (EReference) gComparisonEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGComparisonElement() {
    return gComparisonElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGMapping() {
    return gMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMapping_ModifiableContents() {
    return (EReference) gMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMapping_ReferenceCompletedMatches() {
    return (EReference) gMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMapping_TargetCompletedMatches() {
    return (EReference) gMappingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGMatch() {
    return gMatchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMatch_MatchID() {
    return (EAttribute) gMatchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMatch_ModifiableRelatedDifferences() {
    return (EReference) gMatchEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMatch_ElementPresenceDifference() {
    return (EReference) gMatchEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMatch_ReferenceOwnershipDifference() {
    return (EReference) gMatchEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMatch_TargetOwnershipDifference() {
    return (EReference) gMatchEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGMergeableDifference() {
    return gMergeableDifferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMergeableDifference_AlignedWithAncestor() {
    return (EAttribute) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMergeableDifference_Conflicting() {
    return (EAttribute) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMergeableDifference_Ignored() {
    return (EAttribute) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMergeableDifference_MergeDestination() {
    return (EAttribute) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGMergeableDifference_PossibleMergeDestinations() {
    return (EAttribute) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMergeableDifference_ExplicitDependenciesForTarget() {
    return (EReference) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMergeableDifference_ExplicitDependenciesForReference() {
    return (EReference) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMergeableDifference_ImplicitDependenciesForTarget() {
    return (EReference) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGMergeableDifference_ImplicitDependenciesForReference() {
    return (EReference) gMergeableDifferenceEClass.getEStructuralFeatures()
        .get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGElementRelativePresence() {
    return gElementRelativePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGElementRelativePresence_ElementMatch() {
    return (EReference) gElementRelativePresenceEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGElementRelativePresence_PresenceRole() {
    return (EAttribute) gElementRelativePresenceEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGElementPresence() {
    return gElementPresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGElementPresence_OwnerMatch() {
    return (EReference) gElementPresenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGValuePresence() {
    return gValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGValuePresence_Order() {
    return (EAttribute) gValuePresenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGAttributeValuePresence() {
    return gAttributeValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGReferenceValuePresence() {
    return gReferenceValuePresenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGReferenceValuePresence_ValueMatch() {
    return (EReference) gReferenceValuePresenceEClass.getEStructuralFeatures()
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
    gIdentifiedEClass = createEClass(GIDENTIFIED);
    createEAttribute(gIdentifiedEClass, GIDENTIFIED__ID);

    gComparisonEClass = createEClass(GCOMPARISON);
    createEAttribute(gComparisonEClass, GCOMPARISON__ANCESTOR_SCOPE);
    createEAttribute(gComparisonEClass, GCOMPARISON__REFERENCE_SCOPE);
    createEAttribute(gComparisonEClass, GCOMPARISON__TARGET_SCOPE);
    createEAttribute(gComparisonEClass, GCOMPARISON__LAST_MATCH_POLICY);
    createEAttribute(gComparisonEClass, GCOMPARISON__LAST_DIFF_POLICY);
    createEAttribute(gComparisonEClass, GCOMPARISON__LAST_MERGE_POLICY);
    createEReference(gComparisonEClass, GCOMPARISON__MAPPING);

    gComparisonElementEClass = createEClass(GCOMPARISON_ELEMENT);

    gMappingEClass = createEClass(GMAPPING);
    createEReference(gMappingEClass, GMAPPING__MODIFIABLE_CONTENTS);
    createEReference(gMappingEClass, GMAPPING__REFERENCE_COMPLETED_MATCHES);
    createEReference(gMappingEClass, GMAPPING__TARGET_COMPLETED_MATCHES);

    gMatchEClass = createEClass(GMATCH);
    createEAttribute(gMatchEClass, GMATCH__MATCH_ID);
    createEReference(gMatchEClass, GMATCH__MODIFIABLE_RELATED_DIFFERENCES);
    createEReference(gMatchEClass, GMATCH__ELEMENT_PRESENCE_DIFFERENCE);
    createEReference(gMatchEClass, GMATCH__REFERENCE_OWNERSHIP_DIFFERENCE);
    createEReference(gMatchEClass, GMATCH__TARGET_OWNERSHIP_DIFFERENCE);

    gMergeableDifferenceEClass = createEClass(GMERGEABLE_DIFFERENCE);
    createEAttribute(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR);
    createEAttribute(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__CONFLICTING);
    createEAttribute(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__IGNORED);
    createEAttribute(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__MERGE_DESTINATION);
    createEAttribute(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS);
    createEReference(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET);
    createEReference(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE);
    createEReference(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET);
    createEReference(gMergeableDifferenceEClass,
        GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE);

    gElementRelativePresenceEClass = createEClass(GELEMENT_RELATIVE_PRESENCE);
    createEReference(gElementRelativePresenceEClass,
        GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH);
    createEAttribute(gElementRelativePresenceEClass,
        GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE);

    gElementPresenceEClass = createEClass(GELEMENT_PRESENCE);
    createEReference(gElementPresenceEClass, GELEMENT_PRESENCE__OWNER_MATCH);

    gValuePresenceEClass = createEClass(GVALUE_PRESENCE);
    createEAttribute(gValuePresenceEClass, GVALUE_PRESENCE__ORDER);

    gAttributeValuePresenceEClass = createEClass(GATTRIBUTE_VALUE_PRESENCE);

    gReferenceValuePresenceEClass = createEClass(GREFERENCE_VALUE_PRESENCE);
    createEReference(gReferenceValuePresenceEClass,
        GREFERENCE_VALUE_PRESENCE__VALUE_MATCH);

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
    ETypeParameter gComparisonEClass_E = addETypeParameter(gComparisonEClass,
        "E"); //$NON-NLS-1$
    ETypeParameter gComparisonEClass_A = addETypeParameter(gComparisonEClass,
        "A"); //$NON-NLS-1$
    ETypeParameter gComparisonEClass_R = addETypeParameter(gComparisonEClass,
        "R"); //$NON-NLS-1$
    ETypeParameter gComparisonElementEClass_E = addETypeParameter(
        gComparisonElementEClass, "E"); //$NON-NLS-1$
    ETypeParameter gComparisonElementEClass_A = addETypeParameter(
        gComparisonElementEClass, "A"); //$NON-NLS-1$
    ETypeParameter gComparisonElementEClass_R = addETypeParameter(
        gComparisonElementEClass, "R"); //$NON-NLS-1$
    ETypeParameter gMappingEClass_E = addETypeParameter(gMappingEClass, "E"); //$NON-NLS-1$
    ETypeParameter gMappingEClass_A = addETypeParameter(gMappingEClass, "A"); //$NON-NLS-1$
    ETypeParameter gMappingEClass_R = addETypeParameter(gMappingEClass, "R"); //$NON-NLS-1$
    ETypeParameter gMatchEClass_E = addETypeParameter(gMatchEClass, "E"); //$NON-NLS-1$
    ETypeParameter gMatchEClass_A = addETypeParameter(gMatchEClass, "A"); //$NON-NLS-1$
    ETypeParameter gMatchEClass_R = addETypeParameter(gMatchEClass, "R"); //$NON-NLS-1$
    ETypeParameter gMergeableDifferenceEClass_E = addETypeParameter(
        gMergeableDifferenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gMergeableDifferenceEClass_A = addETypeParameter(
        gMergeableDifferenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gMergeableDifferenceEClass_R = addETypeParameter(
        gMergeableDifferenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter gElementRelativePresenceEClass_E = addETypeParameter(
        gElementRelativePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gElementRelativePresenceEClass_A = addETypeParameter(
        gElementRelativePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gElementRelativePresenceEClass_R = addETypeParameter(
        gElementRelativePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter gElementPresenceEClass_E = addETypeParameter(
        gElementPresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gElementPresenceEClass_A = addETypeParameter(
        gElementPresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gElementPresenceEClass_R = addETypeParameter(
        gElementPresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter gValuePresenceEClass_E = addETypeParameter(
        gValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gValuePresenceEClass_A = addETypeParameter(
        gValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gValuePresenceEClass_R = addETypeParameter(
        gValuePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter gAttributeValuePresenceEClass_E = addETypeParameter(
        gAttributeValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gAttributeValuePresenceEClass_A = addETypeParameter(
        gAttributeValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gAttributeValuePresenceEClass_R = addETypeParameter(
        gAttributeValuePresenceEClass, "R"); //$NON-NLS-1$
    ETypeParameter gReferenceValuePresenceEClass_E = addETypeParameter(
        gReferenceValuePresenceEClass, "E"); //$NON-NLS-1$
    ETypeParameter gReferenceValuePresenceEClass_A = addETypeParameter(
        gReferenceValuePresenceEClass, "A"); //$NON-NLS-1$
    ETypeParameter gReferenceValuePresenceEClass_R = addETypeParameter(
        gReferenceValuePresenceEClass, "R"); //$NON-NLS-1$
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
    EGenericType g1 = createEGenericType(this.getGIdentified());
    gComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableComparison());
    EGenericType g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    gComparisonEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGIdentified());
    gMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGComparisonElement());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_R);
    g1.getETypeArguments().add(g2);
    gMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMapping());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    gMappingEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGIdentified());
    gMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGComparisonElement());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_R);
    g1.getETypeArguments().add(g2);
    gMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMatch());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    gMatchEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGIdentified());
    gMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGComparisonElement());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMergeableDifferenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMergeableDifferenceEClass_R);
    g1.getETypeArguments().add(g2);
    gMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIEditableMergeableDifference());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    gMergeableDifferenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGMergeableDifference());
    g2 = createEGenericType(gElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementRelativePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementRelativePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    gElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIElementRelativePresence());
    g2 = createEGenericType(gElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    gElementRelativePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGElementRelativePresence());
    g2 = createEGenericType(gElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementPresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementPresenceEClass_R);
    g1.getETypeArguments().add(g2);
    gElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIElementPresence());
    g2 = createEGenericType(gElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    gElementPresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGElementRelativePresence());
    g2 = createEGenericType(gValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    gValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIValuePresence());
    g2 = createEGenericType(gValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    gValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGValuePresence());
    g2 = createEGenericType(gAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gAttributeValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gAttributeValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    gAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIAttributeValuePresence());
    g2 = createEGenericType(gAttributeValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    gAttributeValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getGValuePresence());
    g2 = createEGenericType(gReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gReferenceValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gReferenceValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    gReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(gReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    gReferenceValuePresenceEClass.getEGenericSuperTypes().add(g1);

    // Initialize classes and features; add operations and parameters
    initEClass(gIdentifiedEClass, GIdentified.class, "GIdentified", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGIdentified_Id(), ecorePackage.getEString(), "id", null, //$NON-NLS-1$
        0, 1, GIdentified.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(gComparisonEClass, GComparison.class, "GComparison", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_AncestorScope(), g1, "ancestorScope", null, 0, //$NON-NLS-1$
        1, GComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_ReferenceScope(), g1, "referenceScope", null, //$NON-NLS-1$
        1, 1, GComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIEditableTreeDataScope());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_TargetScope(), g1, "targetScope", null, 1, 1, //$NON-NLS-1$
        GComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatchPolicy());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_LastMatchPolicy(), g1, "lastMatchPolicy", //$NON-NLS-1$
        null, 0, 1, GComparison.class, IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getIDiffPolicy());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_LastDiffPolicy(), g1, "lastDiffPolicy", null, //$NON-NLS-1$
        0, 1, GComparison.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergePolicy());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    initEAttribute(getGComparison_LastMergePolicy(), g1, "lastMergePolicy", //$NON-NLS-1$
        null, 0, 1, GComparison.class, IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getGMapping());
    g2 = createEGenericType(gComparisonEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gComparisonEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gComparisonEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGComparison_Mapping(), g1, null, "mapping", null, 1, 1, //$NON-NLS-1$
        GComparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(gComparisonElementEClass, GComparisonElement.class,
        "GComparisonElement", IS_ABSTRACT, IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    EOperation op = addEOperation(gComparisonElementEClass, null,
        "getComparison", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getGComparison());
    g2 = createEGenericType(gComparisonElementEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gComparisonElementEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gComparisonElementEClass_R);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(gMappingEClass, GMapping.class, "GMapping", IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getGMatch());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGMapping_ModifiableContents(), g1, null,
        "modifiableContents", null, 0, -1, GMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatch());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMapping_ReferenceCompletedMatches(), g1, null,
        "referenceCompletedMatches", null, 0, -1, GMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMatch());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMapping_TargetCompletedMatches(), g1, null,
        "targetCompletedMatches", null, 0, -1, GMapping.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(gMappingEClass, ecorePackage.getEBoolean(), "disconnect", //$NON-NLS-1$
        1, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, this.getRole(), "role", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(gMappingEClass_E);
    addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gMappingEClass, ecorePackage.getEBoolean(),
        "isIgnoredReferenceValue", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(gMappingEClass_E);
    addEParameter(op, g1, "source", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(gMappingEClass_R);
    addEParameter(op, g1, "reference", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(gMappingEClass_E);
    addEParameter(op, g1, "value", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, this.getRole(), "role", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gMappingEClass, null, "map", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMappingEClass_E);
    addEParameter(op, g1, "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    addEParameter(op, this.getRole(), "role", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(this.getGMatch());
    g2 = createEGenericType(gMappingEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMappingEClass_R);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(gMatchEClass, GMatch.class, "GMatch", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGMatch_MatchID(), ecorePackage.getEJavaObject(),
        "matchID", null, 0, 1, GMatch.class, IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getGMergeableDifference());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGMatch_ModifiableRelatedDifferences(), g1, null,
        "modifiableRelatedDifferences", null, 0, -1, GMatch.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
        !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    g1 = createEGenericType(this.getIElementPresence());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMatch_ElementPresenceDifference(), g1, null,
        "elementPresenceDifference", null, 0, 1, GMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMatch_ReferenceOwnershipDifference(), g1, null,
        "referenceOwnershipDifference", null, 0, 1, GMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIReferenceValuePresence());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMatch_TargetOwnershipDifference(), g1, null,
        "targetOwnershipDifference", null, 0, 1, GMatch.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(gMatchEClass, null, "getAncestor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(gMatchEClass, null, "getReference", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(gMatchEClass, null, "getTarget", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    initEOperation(op, g1);

    op = addEOperation(gMatchEClass, null, "setAncestor", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gMatchEClass, null, "setReference", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gMatchEClass, null, "setTarget", 0, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(gMatchEClass_E);
    addEParameter(op, g1, "e", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gMatchEClass, null, "getMapping", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    g1 = createEGenericType(this.getGMapping());
    g2 = createEGenericType(gMatchEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gMatchEClass_R);
    g1.getETypeArguments().add(g2);
    initEOperation(op, g1);

    initEClass(gMergeableDifferenceEClass, GMergeableDifference.class,
        "GMergeableDifference", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGMergeableDifference_AlignedWithAncestor(),
        ecorePackage.getEBoolean(), "alignedWithAncestor", "true", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGMergeableDifference_Conflicting(),
        ecorePackage.getEBoolean(), "conflicting", "false", 1, 1, //$NON-NLS-1$//$NON-NLS-2$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGMergeableDifference_Ignored(),
        ecorePackage.getEBoolean(), "ignored", null, 1, 1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGMergeableDifference_MergeDestination(), this.getRole(),
        "mergeDestination", null, 0, 1, GMergeableDifference.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGMergeableDifference_PossibleMergeDestinations(),
        this.getRole(), "possibleMergeDestinations", null, 0, -1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMergeableDifference_ExplicitDependenciesForTarget(), g1,
        null, "explicitDependenciesForTarget", null, 0, -1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMergeableDifference_ExplicitDependenciesForReference(),
        g1, null, "explicitDependenciesForReference", null, 0, -1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMergeableDifference_ImplicitDependenciesForTarget(), g1,
        null, "implicitDependenciesForTarget", null, 0, -1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    g1 = createEGenericType(this.getIMergeableDifference());
    g2 = createEGenericType(gMergeableDifferenceEClass_E);
    g1.getETypeArguments().add(g2);
    initEReference(getGMergeableDifference_ImplicitDependenciesForReference(),
        g1, null, "implicitDependenciesForReference", null, 0, -1, //$NON-NLS-1$
        GMergeableDifference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(gElementRelativePresenceEClass, GElementRelativePresence.class,
        "GElementRelativePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getGMatch());
    g2 = createEGenericType(gElementRelativePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementRelativePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementRelativePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGElementRelativePresence_ElementMatch(), g1, null,
        "elementMatch", null, 1, 1, GElementRelativePresence.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGElementRelativePresence_PresenceRole(), this.getRole(),
        "presenceRole", null, 1, 1, GElementRelativePresence.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(gElementPresenceEClass, GElementPresence.class,
        "GElementPresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getGMatch());
    g2 = createEGenericType(gElementPresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementPresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gElementPresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGElementPresence_OwnerMatch(), g1, null, "ownerMatch", //$NON-NLS-1$
        null, 1, 1, GElementPresence.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(gValuePresenceEClass, GValuePresence.class, "GValuePresence", //$NON-NLS-1$
        IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGValuePresence_Order(), ecorePackage.getEBoolean(),
        "order", "false", 1, 1, GValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    addEOperation(gValuePresenceEClass, ecorePackage.getEJavaObject(),
        "getFeature", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(gAttributeValuePresenceEClass, GAttributeValuePresence.class,
        "GAttributeValuePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    op = addEOperation(gAttributeValuePresenceEClass, null, "getFeature", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(gAttributeValuePresenceEClass_A);
    initEOperation(op, g1);

    op = addEOperation(gAttributeValuePresenceEClass, null, "setAttribute", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(gAttributeValuePresenceEClass_A);
    addEParameter(op, g1, "attribute", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gAttributeValuePresenceEClass, null, "setValue", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEJavaObject(), "value", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    initEClass(gReferenceValuePresenceEClass, GReferenceValuePresence.class,
        "GReferenceValuePresence", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    g1 = createEGenericType(this.getGMatch());
    g2 = createEGenericType(gReferenceValuePresenceEClass_E);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gReferenceValuePresenceEClass_A);
    g1.getETypeArguments().add(g2);
    g2 = createEGenericType(gReferenceValuePresenceEClass_R);
    g1.getETypeArguments().add(g2);
    initEReference(getGReferenceValuePresence_ValueMatch(), g1, null,
        "valueMatch", null, 0, 1, GReferenceValuePresence.class, !IS_TRANSIENT, //$NON-NLS-1$
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    op = addEOperation(gReferenceValuePresenceEClass, null, "getFeature", 1, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(gReferenceValuePresenceEClass_R);
    initEOperation(op, g1);

    op = addEOperation(gReferenceValuePresenceEClass, null, "setReference", 0, //$NON-NLS-1$
        1, IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(gReferenceValuePresenceEClass_R);
    addEParameter(op, g1, "reference", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(gReferenceValuePresenceEClass, null, "setValue", 0, 1, //$NON-NLS-1$
        IS_UNIQUE, IS_ORDERED);
    g1 = createEGenericType(gReferenceValuePresenceEClass_E);
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
