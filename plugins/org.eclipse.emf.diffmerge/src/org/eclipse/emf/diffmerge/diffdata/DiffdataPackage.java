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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
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
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/diffdata/1.0.0"; //$NON-NLS-1$

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.EComparisonElement <em>EComparison Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.EComparisonElement
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEComparisonElement()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl <em>EMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMapping()
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
   * The number of structural features of the '<em>EMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING_FEATURE_COUNT = GdiffdataPackage.EMAPPING_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl <em>EMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMatch()
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
   * The feature id for the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ANCESTOR = GdiffdataPackage.EMATCH_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE = GdiffdataPackage.EMATCH_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET = GdiffdataPackage.EMATCH_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Modifiable Attribute Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ATTRIBUTE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 3;

  /**
   * The feature id for the '<em><b>Modifiable Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_REFERENCE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = GdiffdataPackage.EMATCH_FEATURE_COUNT
      + 5;

  /**
   * The number of structural features of the '<em>EMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH_FEATURE_COUNT = GdiffdataPackage.EMATCH_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference <em>EMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMergeableDifference()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence <em>EElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementRelativePresence()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementPresence()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence <em>EValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.EValuePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEValuePresence()
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
   * The number of structural features of the '<em>EValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEAttributeValuePresence()
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
   * The feature id for the '<em><b>Attribute</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ATTRIBUTE = GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEReferenceValuePresence()
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
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__REFERENCE = GdiffdataPackage.EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.AttributeToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getAttributeToDifferenceEntry()
   * @generated
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY = 10;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference list.
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ReferenceToElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
   * @generated
   */
  int REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY = 11;

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
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY = 12;

  /**
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference list.
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
  int ELEMENT_TO_DIFFERENCE_ENTRY = 13;

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
   * The meta object id for the '<em>Setting</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EStructuralFeature.Setting
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getSetting()
   * @generated
   */
  int SETTING = 14;

  /**
   * The meta object id for the '<em>IEditable Model Scope</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableModelScope()
   * @generated
   */
  int IEDITABLE_MODEL_SCOPE = 15;

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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EComparisonElement <em>EComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison Element</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparisonElement
   * @generated
   */
  EClass getEComparisonElement();

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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference
   * @generated
   */
  EClass getEMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence
   * @generated
   */
  EClass getEElementRelativePresence();

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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence
   * @generated
   */
  EClass getEAttributeValuePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Attribute</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getAttribute()
   * @see #getEAttributeValuePresence()
   * @generated
   */
  EReference getEAttributeValuePresence_Attribute();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence#getValue()
   * @see #getEAttributeValuePresence()
   * @generated
   */
  EAttribute getEAttributeValuePresence_Value();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getReference()
   * @see #getEReferenceValuePresence()
   * @generated
   */
  EReference getEReferenceValuePresence_Reference();

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
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EAttribute" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;" valueMany="true"
   * @generated
   */
  EClass getAttributeToDifferenceEntry();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getAttributeToDifferenceEntry()
   * @generated
   */
  EReference getAttributeToDifferenceEntry_Key();

  /**
   * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Value</em>'.
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
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;org.eclipse.emf.ecore.EObject&gt;" valueMany="true"
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
   * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Value</em>'.
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
   * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EStructuralFeature.Setting <em>Setting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Setting</em>'.
   * @see org.eclipse.emf.ecore.EStructuralFeature.Setting
   * @model instanceClass="org.eclipse.emf.ecore.EStructuralFeature$Setting"
   * @generated
   */
  EDataType getSetting();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope <em>IEditable Model Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IEditable Model Scope</em>'.
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
   * @model instanceClass="org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope"
   * @generated
   */
  EDataType getIEditableModelScope();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.EComparisonElement <em>EComparison Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.EComparisonElement
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEComparisonElement()
     * @generated
     */
    EClass ECOMPARISON_ELEMENT = eINSTANCE.getEComparisonElement();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference <em>EMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMergeableDifference()
     * @generated
     */
    EClass EMERGEABLE_DIFFERENCE = eINSTANCE.getEMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence <em>EElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementRelativePresence()
     * @generated
     */
    EClass EELEMENT_RELATIVE_PRESENCE = eINSTANCE.getEElementRelativePresence();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence <em>EValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.EValuePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEValuePresence()
     * @generated
     */
    EClass EVALUE_PRESENCE = eINSTANCE.getEValuePresence();

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
     * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EATTRIBUTE_VALUE_PRESENCE__ATTRIBUTE = eINSTANCE
        .getEAttributeValuePresence_Attribute();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EATTRIBUTE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEAttributeValuePresence_Value();

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
     * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__REFERENCE = eINSTANCE
        .getEReferenceValuePresence_Reference();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEReferenceValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.AttributeToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getAttributeToDifferenceEntry()
     * @generated
     */
    EClass ATTRIBUTE_TO_DIFFERENCE_ENTRY = eINSTANCE
        .getAttributeToDifferenceEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_DIFFERENCE_ENTRY__KEY = eINSTANCE
        .getAttributeToDifferenceEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getAttributeToDifferenceEntry_Value();

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
     * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
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

    /**
     * The meta object literal for the '<em>Setting</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EStructuralFeature.Setting
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getSetting()
     * @generated
     */
    EDataType SETTING = eINSTANCE.getSetting();

    /**
     * The meta object literal for the '<em>IEditable Model Scope</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableModelScope()
     * @generated
     */
    EDataType IEDITABLE_MODEL_SCOPE = eINSTANCE.getIEditableModelScope();

  }

} //DiffdataPackage
