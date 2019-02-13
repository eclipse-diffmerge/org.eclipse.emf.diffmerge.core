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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataFactory
 * @model kind="package"
 * @generated
 */
public interface DiffdataPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "diffdata"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/1.0.0/diffdata"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.diffmerge"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DiffdataPackage eINSTANCE = org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl
      .init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEComparison()
   * @generated
   */
  int ECOMPARISON = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__ID;

  /**
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ANCESTOR_SCOPE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__REFERENCE_SCOPE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__TARGET_SCOPE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__TARGET_SCOPE;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MATCH_POLICY = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_DIFF_POLICY = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MERGE_POLICY = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__MAPPING = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON__MAPPING;

  /**
   * The number of structural features of the '<em>EComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_FEATURE_COUNT = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.ECOMPARISON_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl <em>EMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMapping()
   * @generated
   */
  int EMAPPING = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMAPPING__ID;

  /**
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__MODIFIABLE_CONTENTS = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__REFERENCE_COMPLETED_MATCHES = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__TARGET_COMPLETED_MATCHES = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES;

  /**
   * The number of structural features of the '<em>EMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING_FEATURE_COUNT = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMAPPING_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl <em>EMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMatch()
   * @generated
   */
  int EMATCH = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__ID;

  /**
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MATCH_ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__MATCH_ID;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_RELATED_DIFFERENCES = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ELEMENT_PRESENCE_DIFFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET_OWNERSHIP_DIFFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ANCESTOR = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 2;

  /**
   * The feature id for the '<em><b>Modifiable Attribute Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ATTRIBUTE_MAP = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 3;

  /**
   * The feature id for the '<em><b>Modifiable Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_REFERENCE_MAP = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 5;

  /**
   * The number of structural features of the '<em>EMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH_FEATURE_COUNT = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementPresence()
   * @generated
   */
  int EELEMENT_PRESENCE = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__COMPARISON = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__COMPARISON;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__CONFLICTING = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IGNORED = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__MERGE_DESTINATION = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ELEMENT_MATCH = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__PRESENCE_ROLE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__OWNER_MATCH = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH;

  /**
   * The number of structural features of the '<em>EElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE_FEATURE_COUNT = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EELEMENT_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl <em>EValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEValuePresence()
   * @generated
   */
  int EVALUE_PRESENCE = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ID = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__COMPARISON = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__COMPARISON;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__CONFLICTING = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IGNORED = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__MERGE_DESTINATION = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ELEMENT_MATCH = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__PRESENCE_ROLE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ORDER = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__FEATURE = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>EValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE_FEATURE_COUNT = org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage.EVALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEAttributeValuePresence()
   * @generated
   */
  int EATTRIBUTE_VALUE_PRESENCE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ID = EVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__COMPARISON = EVALUE_PRESENCE__COMPARISON;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__CONFLICTING = EVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IGNORED = EVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION = EVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH = EVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE = EVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ORDER = EVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__FEATURE = EVALUE_PRESENCE__FEATURE;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__VALUE = EVALUE_PRESENCE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = EVALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEReferenceValuePresence()
   * @generated
   */
  int EREFERENCE_VALUE_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ID = EVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__COMPARISON = EVALUE_PRESENCE__COMPARISON;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__CONFLICTING = EVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IGNORED = EVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION = EVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH = EVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE = EVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ORDER = EVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__FEATURE = EVALUE_PRESENCE__FEATURE;

  /**
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE = EVALUE_PRESENCE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = EVALUE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.AttributeToValueToDifferenceEntryImpl <em>Attribute To Value To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.AttributeToValueToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getAttributeToValueToDifferenceEntry()
   * @generated
   */
  int ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY = 7;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Attribute To Value To Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ValueToDifferenceEntryImpl <em>Value To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ValueToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getValueToDifferenceEntry()
   * @generated
   */
  int VALUE_TO_DIFFERENCE_ENTRY = 8;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_TO_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Value To Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_TO_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
   * @generated
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY = 9;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Reference To Element To Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToOrderDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
   * @generated
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY = 10;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Reference To Order Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getElementToDifferenceEntry()
   * @generated
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY = 11;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Element To Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison
   * @generated
   */
  EClass getEComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMapping</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping
   * @generated
   */
  EClass getEMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMatch</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch
   * @generated
   */
  EClass getEMatch();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getAncestor <em>Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getAncestor()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_Ancestor();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getReference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_Reference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getTarget()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_Target();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Attribute Map</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableAttributeMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableAttributeMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableReferenceMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableReferenceMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Order Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableOrderReferenceMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableOrderReferenceMap();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EElementPresence
   * @generated
   */
  EClass getEElementPresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EValuePresence
   * @generated
   */
  EClass getEValuePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Feature</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EValuePresence#getFeature()
   * @see #getEValuePresence()
   * @generated
   */
  EReference getEValuePresence_Feature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence
   * @generated
   */
  EClass getEAttributeValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence
   * @generated
   */
  EClass getEReferenceValuePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValue()
   * @see #getEReferenceValuePresence()
   * @generated
   */
  EReference getEReferenceValuePresence_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute To Value To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute To Value To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EAttribute" keyRequired="true"
   *        valueMapType="org.eclipse.emf.diffmerge.diffdata.ValueToDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;&gt;"
   * @generated
   */
  EClass getAttributeToValueToDifferenceEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getAttributeToValueToDifferenceEntry()
   * @generated
   */
  EReference getAttributeToValueToDifferenceEntry_Key();

  /**
   * Returns the meta object for the map '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getAttributeToValueToDifferenceEntry()
   * @generated
   */
  EReference getAttributeToValueToDifferenceEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Value To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;" valueRequired="true"
   * @generated
   */
  EClass getValueToDifferenceEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getValueToDifferenceEntry()
   * @generated
   */
  EAttribute getValueToDifferenceEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getValueToDifferenceEntry()
   * @generated
   */
  EReference getValueToDifferenceEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Reference To Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference To Element To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EReference" keyRequired="true"
   *        valueMapType="org.eclipse.emf.diffmerge.diffdata.ElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;&gt;"
   * @generated
   */
  EClass getReferenceToElementToDifferenceEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToElementToDifferenceEntry()
   * @generated
   */
  EReference getReferenceToElementToDifferenceEntry_Key();

  /**
   * Returns the meta object for the map '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToElementToDifferenceEntry()
   * @generated
   */
  EReference getReferenceToElementToDifferenceEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Reference To Order Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference To Order Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EReference" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;" valueContainment="true" valueMany="true"
   * @generated
   */
  EClass getReferenceToOrderDifferenceEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToOrderDifferenceEntry()
   * @generated
   */
  EReference getReferenceToOrderDifferenceEntry_Key();

  /**
   * Returns the meta object for the containment reference list '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToOrderDifferenceEntry()
   * @generated
   */
  EReference getReferenceToOrderDifferenceEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;" valueRequired="true"
   * @generated
   */
  EClass getElementToDifferenceEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getElementToDifferenceEntry()
   * @generated
   */
  EReference getElementToDifferenceEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getElementToDifferenceEntry()
   * @generated
   */
  EReference getElementToDifferenceEntry_Value();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DiffdataFactory getDiffdataFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEComparison()
     * @generated
     */
    EClass ECOMPARISON = eINSTANCE.getEComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl <em>EMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMapping()
     * @generated
     */
    EClass EMAPPING = eINSTANCE.getEMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl <em>EMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMatch()
     * @generated
     */
    EClass EMATCH = eINSTANCE.getEMatch();

    /**
     * The meta object literal for the '<em><b>Ancestor</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__ANCESTOR = eINSTANCE.getEMatch_Ancestor();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__REFERENCE = eINSTANCE.getEMatch_Reference();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__TARGET = eINSTANCE.getEMatch_Target();

    /**
     * The meta object literal for the '<em><b>Modifiable Attribute Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__MODIFIABLE_ATTRIBUTE_MAP = eINSTANCE
        .getEMatch_ModifiableAttributeMap();

    /**
     * The meta object literal for the '<em><b>Modifiable Reference Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__MODIFIABLE_REFERENCE_MAP = eINSTANCE
        .getEMatch_ModifiableReferenceMap();

    /**
     * The meta object literal for the '<em><b>Modifiable Order Reference Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = eINSTANCE
        .getEMatch_ModifiableOrderReferenceMap();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementPresence()
     * @generated
     */
    EClass EELEMENT_PRESENCE = eINSTANCE.getEElementPresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl <em>EValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEValuePresence()
     * @generated
     */
    EClass EVALUE_PRESENCE = eINSTANCE.getEValuePresence();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EVALUE_PRESENCE__FEATURE = eINSTANCE.getEValuePresence_Feature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEAttributeValuePresence()
     * @generated
     */
    EClass EATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getEAttributeValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEReferenceValuePresence()
     * @generated
     */
    EClass EREFERENCE_VALUE_PRESENCE = eINSTANCE.getEReferenceValuePresence();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEReferenceValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.AttributeToValueToDifferenceEntryImpl <em>Attribute To Value To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.AttributeToValueToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getAttributeToValueToDifferenceEntry()
     * @generated
     */
    EClass ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getAttributeToValueToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getAttributeToValueToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getAttributeToValueToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ValueToDifferenceEntryImpl <em>Value To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.ValueToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getValueToDifferenceEntry()
     * @generated
     */
    EClass VALUE_TO_DIFFERENCE_ENTRY = eINSTANCE.getValueToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VALUE_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getValueToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VALUE_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getValueToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
     * @generated
     */
    EClass REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getReferenceToElementToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getReferenceToElementToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getReferenceToElementToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToOrderDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
     * @generated
     */
    EClass REFERENCE_TO_ORDER_DIFFERENCE_ENTRY = eINSTANCE
        .getReferenceToOrderDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getReferenceToOrderDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getReferenceToOrderDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getElementToDifferenceEntry()
     * @generated
     */
    EClass ELEMENT_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getElementToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getElementToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getElementToDifferenceEntry_Value();

  }

} //DiffdataPackage
