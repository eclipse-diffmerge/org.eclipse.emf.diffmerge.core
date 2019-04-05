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
package org.eclipse.emf.diffmerge.pojo.pojodiffdata;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;

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
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataFactory
 * @model kind="package"
 * @generated
 */
public interface PojodiffdataPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "pojodiffdata"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/1.0.0/pojodiffdata"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.diffmerge.pojo"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PojodiffdataPackage eINSTANCE = org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl
      .init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EComparisonImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEComparison()
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
  int ECOMPARISON__ID = GdiffdataPackage.ECOMPARISON__ID;

  /**
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ANCESTOR_SCOPE = GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__REFERENCE_SCOPE = GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__TARGET_SCOPE = GdiffdataPackage.ECOMPARISON__TARGET_SCOPE;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MATCH_POLICY = GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_DIFF_POLICY = GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MERGE_POLICY = GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__MAPPING = GdiffdataPackage.ECOMPARISON__MAPPING;

  /**
   * The number of structural features of the '<em>EComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_FEATURE_COUNT = GdiffdataPackage.ECOMPARISON_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement <em>EComparison Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEComparisonElement()
   * @generated
   */
  int ECOMPARISON_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>EComparison Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_ELEMENT_FEATURE_COUNT = GdiffdataPackage.ECOMPARISON_ELEMENT_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl <em>EMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMapping()
   * @generated
   */
  int EMAPPING = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__ID = GdiffdataPackage.EMAPPING__ID;

  /**
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__MODIFIABLE_CONTENTS = GdiffdataPackage.EMAPPING__MODIFIABLE_CONTENTS;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__REFERENCE_COMPLETED_MATCHES = GdiffdataPackage.EMAPPING__REFERENCE_COMPLETED_MATCHES;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__TARGET_COMPLETED_MATCHES = GdiffdataPackage.EMAPPING__TARGET_COMPLETED_MATCHES;

  /**
   * The feature id for the '<em><b>Ancestor Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__ANCESTOR_MATCH_MAP = GdiffdataPackage.EMAPPING_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Reference Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__REFERENCE_MATCH_MAP = GdiffdataPackage.EMAPPING_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Target Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__TARGET_MATCH_MAP = GdiffdataPackage.EMAPPING_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>EMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING_FEATURE_COUNT = GdiffdataPackage.EMAPPING_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl <em>EMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMatch()
   * @generated
   */
  int EMATCH = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ID = GdiffdataPackage.EMATCH__ID;

  /**
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MATCH_ID = GdiffdataPackage.EMATCH__MATCH_ID;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_RELATED_DIFFERENCES = GdiffdataPackage.EMATCH__MODIFIABLE_RELATED_DIFFERENCES;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ELEMENT_PRESENCE_DIFFERENCE = GdiffdataPackage.EMATCH__ELEMENT_PRESENCE_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = GdiffdataPackage.EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET_OWNERSHIP_DIFFERENCE = GdiffdataPackage.EMATCH__TARGET_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Modifiable Attribute Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ATTRIBUTE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Modifiable Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_REFERENCE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 2;

  /**
   * The feature id for the '<em><b>Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ANCESTOR = GdiffdataPackage.EMATCH_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE = GdiffdataPackage.EMATCH_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET = GdiffdataPackage.EMATCH_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>EMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH_FEATURE_COUNT = GdiffdataPackage.EMATCH_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference <em>EMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMergeableDifference()
   * @generated
   */
  int EMERGEABLE_DIFFERENCE = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__ID = GdiffdataPackage.EMERGEABLE_DIFFERENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__CONFLICTING = GdiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IGNORED = GdiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__MERGE_DESTINATION = GdiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The number of structural features of the '<em>EMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE_FEATURE_COUNT = GdiffdataPackage.EMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEElementRelativePresence()
   * @generated
   */
  int EELEMENT_RELATIVE_PRESENCE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ID = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__CONFLICTING = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IGNORED = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The number of structural features of the '<em>EElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEElementPresence()
   * @generated
   */
  int EELEMENT_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ID = GdiffdataPackage.EELEMENT_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__CONFLICTING = GdiffdataPackage.EELEMENT_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IGNORED = GdiffdataPackage.EELEMENT_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.EELEMENT_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.EELEMENT_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.EELEMENT_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__OWNER_MATCH = GdiffdataPackage.EELEMENT_PRESENCE__OWNER_MATCH;

  /**
   * The number of structural features of the '<em>EElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EELEMENT_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence <em>EValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEValuePresence()
   * @generated
   */
  int EVALUE_PRESENCE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ID = GdiffdataPackage.EVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__CONFLICTING = GdiffdataPackage.EVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IGNORED = GdiffdataPackage.EVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.EVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.EVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.EVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ORDER = GdiffdataPackage.EVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__FEATURE = GdiffdataPackage.EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>EValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EVALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEAttributeValuePresence()
   * @generated
   */
  int EATTRIBUTE_VALUE_PRESENCE = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ID = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__CONFLICTING = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IGNORED = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ORDER = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__FEATURE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__VALUE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>EAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEReferenceValuePresence()
   * @generated
   */
  int EREFERENCE_VALUE_PRESENCE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ID = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__CONFLICTING = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IGNORED = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ORDER = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE_MATCH;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__FEATURE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>EReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getAttributeToDifferenceEntry()
   * @generated
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY = 10;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Attribute To Difference Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
   * @generated
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY = 11;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToOrderDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
   * @generated
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY = 12;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getElementToDifferenceEntry()
   * @generated
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY = 13;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToMatchEntryImpl <em>Element To Match Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToMatchEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getElementToMatchEntry()
   * @generated
   */
  int ELEMENT_TO_MATCH_ENTRY = 14;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_MATCH_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_MATCH_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>Element To Match Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_TO_MATCH_ENTRY_FEATURE_COUNT = 2;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison
   * @generated
   */
  EClass getEComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement <em>EComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison Element</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement
   * @generated
   */
  EClass getEComparisonElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMapping</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping
   * @generated
   */
  EClass getEMapping();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getAncestorMatchMap <em>Ancestor Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Ancestor Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getAncestorMatchMap()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_AncestorMatchMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getReferenceMatchMap <em>Reference Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Reference Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getReferenceMatchMap()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_ReferenceMatchMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getTargetMatchMap <em>Target Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Target Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getTargetMatchMap()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_TargetMatchMap();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMatch</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch
   * @generated
   */
  EClass getEMatch();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Attribute Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableAttributeMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableAttributeMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableReferenceMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableReferenceMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Order Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getModifiableOrderReferenceMap()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableOrderReferenceMap();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getAncestor <em>Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getAncestor()
   * @see #getEMatch()
   * @generated
   */
  EAttribute getEMatch_Ancestor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getReference()
   * @see #getEMatch()
   * @generated
   */
  EAttribute getEMatch_Reference();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch#getTarget()
   * @see #getEMatch()
   * @generated
   */
  EAttribute getEMatch_Target();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference
   * @generated
   */
  EClass getEMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence
   * @generated
   */
  EClass getEElementRelativePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence
   * @generated
   */
  EClass getEElementPresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence
   * @generated
   */
  EClass getEValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence#getFeature()
   * @see #getEValuePresence()
   * @generated
   */
  EAttribute getEValuePresence_Feature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence
   * @generated
   */
  EClass getEAttributeValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence#getValue()
   * @see #getEAttributeValuePresence()
   * @generated
   */
  EAttribute getEAttributeValuePresence_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence
   * @generated
   */
  EClass getEReferenceValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence#getValue()
   * @see #getEReferenceValuePresence()
   * @generated
   */
  EAttribute getEReferenceValuePresence_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;?&gt;" valueContainment="true" valueMany="true"
   * @generated
   */
  EClass getAttributeToDifferenceEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getAttributeToDifferenceEntry()
   * @generated
   */
  EAttribute getAttributeToDifferenceEntry_Key();

  /**
   * Returns the meta object for the containment reference list '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getAttributeToDifferenceEntry()
   * @generated
   */
  EReference getAttributeToDifferenceEntry_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Reference To Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference To Element To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueMapType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.ElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;&gt;"
   * @generated
   */
  EClass getReferenceToElementToDifferenceEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToElementToDifferenceEntry()
   * @generated
   */
  EAttribute getReferenceToElementToDifferenceEntry_Key();

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
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;" valueContainment="true" valueMany="true"
   * @generated
   */
  EClass getReferenceToOrderDifferenceEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getReferenceToOrderDifferenceEntry()
   * @generated
   */
  EAttribute getReferenceToOrderDifferenceEntry_Key();

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
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;" valueRequired="true"
   * @generated
   */
  EClass getElementToDifferenceEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getElementToDifferenceEntry()
   * @generated
   */
  EAttribute getElementToDifferenceEntry_Key();

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
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Element To Match Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element To Match Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch&lt;?&gt;" valueRequired="true"
   * @generated
   */
  EClass getElementToMatchEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getElementToMatchEntry()
   * @generated
   */
  EAttribute getElementToMatchEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getElementToMatchEntry()
   * @generated
   */
  EReference getElementToMatchEntry_Value();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  PojodiffdataFactory getPojodiffdataFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EComparisonImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEComparison()
     * @generated
     */
    EClass ECOMPARISON = eINSTANCE.getEComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement <em>EComparison Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEComparisonElement()
     * @generated
     */
    EClass ECOMPARISON_ELEMENT = eINSTANCE.getEComparisonElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl <em>EMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMapping()
     * @generated
     */
    EClass EMAPPING = eINSTANCE.getEMapping();

    /**
     * The meta object literal for the '<em><b>Ancestor Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__ANCESTOR_MATCH_MAP = eINSTANCE
        .getEMapping_AncestorMatchMap();

    /**
     * The meta object literal for the '<em><b>Reference Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__REFERENCE_MATCH_MAP = eINSTANCE
        .getEMapping_ReferenceMatchMap();

    /**
     * The meta object literal for the '<em><b>Target Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__TARGET_MATCH_MAP = eINSTANCE
        .getEMapping_TargetMatchMap();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl <em>EMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMatchImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMatch()
     * @generated
     */
    EClass EMATCH = eINSTANCE.getEMatch();

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
     * The meta object literal for the '<em><b>Ancestor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMATCH__ANCESTOR = eINSTANCE.getEMatch_Ancestor();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMATCH__REFERENCE = eINSTANCE.getEMatch_Reference();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMATCH__TARGET = eINSTANCE.getEMatch_Target();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference <em>EMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEMergeableDifference()
     * @generated
     */
    EClass EMERGEABLE_DIFFERENCE = eINSTANCE.getEMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEElementRelativePresence()
     * @generated
     */
    EClass EELEMENT_RELATIVE_PRESENCE = eINSTANCE.getEElementRelativePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEElementPresence()
     * @generated
     */
    EClass EELEMENT_PRESENCE = eINSTANCE.getEElementPresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence <em>EValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEValuePresence()
     * @generated
     */
    EClass EVALUE_PRESENCE = eINSTANCE.getEValuePresence();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EVALUE_PRESENCE__FEATURE = eINSTANCE.getEValuePresence_Feature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EAttributeValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEAttributeValuePresence()
     * @generated
     */
    EClass EATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getEAttributeValuePresence();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EATTRIBUTE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEAttributeValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getEReferenceValuePresence()
     * @generated
     */
    EClass EREFERENCE_VALUE_PRESENCE = eINSTANCE.getEReferenceValuePresence();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EREFERENCE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEReferenceValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.AttributeToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getAttributeToDifferenceEntry()
     * @generated
     */
    EClass ATTRIBUTE_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getAttributeToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getAttributeToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getAttributeToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
     * @generated
     */
    EClass REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getReferenceToElementToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ReferenceToOrderDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
     * @generated
     */
    EClass REFERENCE_TO_ORDER_DIFFERENCE_ENTRY = eINSTANCE
        .getReferenceToOrderDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY = eINSTANCE
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getElementToDifferenceEntry()
     * @generated
     */
    EClass ELEMENT_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getElementToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getElementToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getElementToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToMatchEntryImpl <em>Element To Match Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.ElementToMatchEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataPackageImpl#getElementToMatchEntry()
     * @generated
     */
    EClass ELEMENT_TO_MATCH_ENTRY = eINSTANCE.getElementToMatchEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_TO_MATCH_ENTRY__KEY = eINSTANCE
        .getElementToMatchEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_TO_MATCH_ENTRY__VALUE = eINSTANCE
        .getElementToMatchEntry_Value();

  }

} //PojodiffdataPackage
