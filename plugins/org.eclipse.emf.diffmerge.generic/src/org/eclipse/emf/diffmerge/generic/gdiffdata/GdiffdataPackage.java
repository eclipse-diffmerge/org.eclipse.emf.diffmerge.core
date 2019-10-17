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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

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
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataFactory
 * @model kind="package"
 * @generated
 */
public interface GdiffdataPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "gdiffdata"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/generic/gdiffdata/1.0.0"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.emf.diffmerge.generic"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GdiffdataPackage eINSTANCE = org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl
      .init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GIdentifiedImpl <em>GIdentified</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GIdentifiedImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGIdentified()
   * @generated
   */
  int GIDENTIFIED = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GIDENTIFIED__ID = 0;

  /**
   * The number of structural features of the '<em>GIdentified</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GIDENTIFIED_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl <em>GComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGComparison()
   * @generated
   */
  int GCOMPARISON = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__ID = GIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__ANCESTOR_SCOPE = GIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__REFERENCE_SCOPE = GIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__TARGET_SCOPE = GIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__LAST_MATCH_POLICY = GIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__LAST_DIFF_POLICY = GIDENTIFIED_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__LAST_MERGE_POLICY = GIDENTIFIED_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON__MAPPING = GIDENTIFIED_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>GComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON_FEATURE_COUNT = GIDENTIFIED_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement <em>GComparison Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGComparisonElement()
   * @generated
   */
  int GCOMPARISON_ELEMENT = 2;

  /**
   * The number of structural features of the '<em>GComparison Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GCOMPARISON_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl <em>GMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMapping()
   * @generated
   */
  int GMAPPING = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMAPPING__ID = GIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMAPPING__MODIFIABLE_CONTENTS = GIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMAPPING__REFERENCE_COMPLETED_MATCHES = GIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMAPPING__TARGET_COMPLETED_MATCHES = GIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>GMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMAPPING_FEATURE_COUNT = GIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl <em>GMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMatch()
   * @generated
   */
  int GMATCH = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__ID = GIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__MATCH_ID = GIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__MODIFIABLE_RELATED_DIFFERENCES = GIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__ELEMENT_PRESENCE_DIFFERENCE = GIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = GIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH__TARGET_OWNERSHIP_DIFFERENCE = GIDENTIFIED_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>GMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMATCH_FEATURE_COUNT = GIDENTIFIED_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl <em>GMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMergeableDifference()
   * @generated
   */
  int GMERGEABLE_DIFFERENCE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__ID = GIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = GIDENTIFIED_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__CONFLICTING = GIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__IGNORED = GIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__MERGE_DESTINATION = GIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = GIDENTIFIED_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GIDENTIFIED_FEATURE_COUNT
      + 5;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GIDENTIFIED_FEATURE_COUNT
      + 6;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GIDENTIFIED_FEATURE_COUNT
      + 7;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GIDENTIFIED_FEATURE_COUNT
      + 8;

  /**
   * The number of structural features of the '<em>GMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GMERGEABLE_DIFFERENCE_FEATURE_COUNT = GIDENTIFIED_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl <em>GElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGElementRelativePresence()
   * @generated
   */
  int GELEMENT_RELATIVE_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__ID = GMERGEABLE_DIFFERENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR = GMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__CONFLICTING = GMERGEABLE_DIFFERENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__IGNORED = GMERGEABLE_DIFFERENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION = GMERGEABLE_DIFFERENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = GMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = GMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>GElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = GMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl <em>GElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGElementPresence()
   * @generated
   */
  int GELEMENT_PRESENCE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__ID = GELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR = GELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__CONFLICTING = GELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__IGNORED = GELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__MERGE_DESTINATION = GELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__ELEMENT_MATCH = GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__PRESENCE_ROLE = GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE__OWNER_MATCH = GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>GElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GELEMENT_PRESENCE_FEATURE_COUNT = GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl <em>GValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGValuePresence()
   * @generated
   */
  int GVALUE_PRESENCE = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__ID = GELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__CONFLICTING = GELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__IGNORED = GELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__MERGE_DESTINATION = GELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__ELEMENT_MATCH = GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__PRESENCE_ROLE = GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE__ORDER = GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>GValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GVALUE_PRESENCE_FEATURE_COUNT = GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl <em>GAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGAttributeValuePresence()
   * @generated
   */
  int GATTRIBUTE_VALUE_PRESENCE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__ID = GVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__CONFLICTING = GVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__IGNORED = GVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION = GVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH = GVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE = GVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE__ORDER = GVALUE_PRESENCE__ORDER;

  /**
   * The number of structural features of the '<em>GAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = GVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl <em>GReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGReferenceValuePresence()
   * @generated
   */
  int GREFERENCE_VALUE_PRESENCE = 10;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__ID = GVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__CONFLICTING = GVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__IGNORED = GVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION = GVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH = GVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE = GVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__ORDER = GVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE__VALUE_MATCH = GVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>GReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = GVALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IComparison.Editable <em>IEditable Comparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableComparison()
   * @generated
   */
  int IEDITABLE_COMPARISON = 12;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IMapping.Editable <em>IEditable Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMapping()
   * @generated
   */
  int IEDITABLE_MAPPING = 14;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IMatch.Editable <em>IEditable Match</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMatch()
   * @generated
   */
  int IEDITABLE_MATCH = 16;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMergeableDifference()
   * @generated
   */
  int IEDITABLE_MERGEABLE_DIFFERENCE = 18;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IComparison <em>IComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIComparison()
   * @generated
   */
  int ICOMPARISON = 11;

  /**
   * The number of structural features of the '<em>IComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ICOMPARISON_FEATURE_COUNT = 0;

  /**
   * The number of structural features of the '<em>IEditable Comparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_COMPARISON_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IMapping <em>IMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMapping()
   * @generated
   */
  int IMAPPING = 13;

  /**
   * The number of structural features of the '<em>IMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMAPPING_FEATURE_COUNT = 0;

  /**
   * The number of structural features of the '<em>IEditable Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MAPPING_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.IMatch <em>IMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMatch()
   * @generated
   */
  int IMATCH = 15;

  /**
   * The number of structural features of the '<em>IMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMATCH_FEATURE_COUNT = 0;

  /**
   * The number of structural features of the '<em>IEditable Match</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MATCH_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference <em>IMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMergeableDifference()
   * @generated
   */
  int IMERGEABLE_DIFFERENCE = 17;

  /**
   * The number of structural features of the '<em>IMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMERGEABLE_DIFFERENCE_FEATURE_COUNT = 0;

  /**
   * The number of structural features of the '<em>IEditable Mergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIElementRelativePresence()
   * @generated
   */
  int IELEMENT_RELATIVE_PRESENCE = 19;

  /**
   * The number of structural features of the '<em>IElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence <em>IElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIElementPresence()
   * @generated
   */
  int IELEMENT_PRESENCE = 20;

  /**
   * The number of structural features of the '<em>IElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IELEMENT_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence <em>IValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIValuePresence()
   * @generated
   */
  int IVALUE_PRESENCE = 21;

  /**
   * The number of structural features of the '<em>IValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IVALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIAttributeValuePresence()
   * @generated
   */
  int IATTRIBUTE_VALUE_PRESENCE = 22;

  /**
   * The number of structural features of the '<em>IAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIReferenceValuePresence()
   * @generated
   */
  int IREFERENCE_VALUE_PRESENCE = 23;

  /**
   * The number of structural features of the '<em>IReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '<em>IEditable Tree Data Scope</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableTreeDataScope()
   * @generated
   */
  int IEDITABLE_TREE_DATA_SCOPE = 24;

  /**
   * The meta object id for the '<em>IMatch Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMatchPolicy()
   * @generated
   */
  int IMATCH_POLICY = 25;

  /**
   * The meta object id for the '<em>IDiff Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIDiffPolicy()
   * @generated
   */
  int IDIFF_POLICY = 26;

  /**
   * The meta object id for the '<em>IMerge Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMergePolicy()
   * @generated
   */
  int IMERGE_POLICY = 27;

  /**
   * The meta object id for the '<em>Role</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.api.Role
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getRole()
   * @generated
   */
  int ROLE = 28;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified <em>GIdentified</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GIdentified</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified
   * @generated
   */
  EClass getGIdentified();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified#getId()
   * @see #getGIdentified()
   * @generated
   */
  EAttribute getGIdentified_Id();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison <em>GComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GComparison</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison
   * @generated
   */
  EClass getGComparison();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getAncestorScope <em>Ancestor Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ancestor Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getAncestorScope()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_AncestorScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getReferenceScope <em>Reference Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getReferenceScope()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_ReferenceScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getTargetScope <em>Target Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getTargetScope()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_TargetScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMatchPolicy <em>Last Match Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Match Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMatchPolicy()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_LastMatchPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastDiffPolicy <em>Last Diff Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Diff Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastDiffPolicy()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_LastDiffPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMergePolicy <em>Last Merge Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Merge Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMergePolicy()
   * @see #getGComparison()
   * @generated
   */
  EAttribute getGComparison_LastMergePolicy();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getMapping()
   * @see #getGComparison()
   * @generated
   */
  EReference getGComparison_Mapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement <em>GComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GComparison Element</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement
   * @generated
   */
  EClass getGComparisonElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping <em>GMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GMapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping
   * @generated
   */
  EClass getGMapping();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getModifiableContents <em>Modifiable Contents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Contents</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getModifiableContents()
   * @see #getGMapping()
   * @generated
   */
  EReference getGMapping_ModifiableContents();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reference Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getReferenceCompletedMatches()
   * @see #getGMapping()
   * @generated
   */
  EReference getGMapping_ReferenceCompletedMatches();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Target Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping#getTargetCompletedMatches()
   * @see #getGMapping()
   * @generated
   */
  EReference getGMapping_TargetCompletedMatches();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch <em>GMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GMatch</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch
   * @generated
   */
  EClass getGMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getMatchID <em>Match ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Match ID</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getMatchID()
   * @see #getGMatch()
   * @generated
   */
  EAttribute getGMatch_MatchID();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Related Differences</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getModifiableRelatedDifferences()
   * @see #getGMatch()
   * @generated
   */
  EReference getGMatch_ModifiableRelatedDifferences();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getElementPresenceDifference <em>Element Presence Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Presence Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getElementPresenceDifference()
   * @see #getGMatch()
   * @generated
   */
  EReference getGMatch_ElementPresenceDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getReferenceOwnershipDifference()
   * @see #getGMatch()
   * @generated
   */
  EReference getGMatch_ReferenceOwnershipDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch#getTargetOwnershipDifference()
   * @see #getGMatch()
   * @generated
   */
  EReference getGMatch_TargetOwnershipDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference <em>GMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference
   * @generated
   */
  EClass getGMergeableDifference();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isAlignedWithAncestor <em>Aligned With Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Aligned With Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isAlignedWithAncestor()
   * @see #getGMergeableDifference()
   * @generated
   */
  EAttribute getGMergeableDifference_AlignedWithAncestor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isConflicting <em>Conflicting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Conflicting</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isConflicting()
   * @see #getGMergeableDifference()
   * @generated
   */
  EAttribute getGMergeableDifference_Conflicting();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isIgnored <em>Ignored</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ignored</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#isIgnored()
   * @see #getGMergeableDifference()
   * @generated
   */
  EAttribute getGMergeableDifference_Ignored();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getMergeDestination <em>Merge Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Merge Destination</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getMergeDestination()
   * @see #getGMergeableDifference()
   * @generated
   */
  EAttribute getGMergeableDifference_MergeDestination();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getPossibleMergeDestinations <em>Possible Merge Destinations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Possible Merge Destinations</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getPossibleMergeDestinations()
   * @see #getGMergeableDifference()
   * @generated
   */
  EAttribute getGMergeableDifference_PossibleMergeDestinations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getExplicitDependenciesForTarget <em>Explicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getExplicitDependenciesForTarget()
   * @see #getGMergeableDifference()
   * @generated
   */
  EReference getGMergeableDifference_ExplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getExplicitDependenciesForReference <em>Explicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getExplicitDependenciesForReference()
   * @see #getGMergeableDifference()
   * @generated
   */
  EReference getGMergeableDifference_ExplicitDependenciesForReference();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getImplicitDependenciesForTarget <em>Implicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getImplicitDependenciesForTarget()
   * @see #getGMergeableDifference()
   * @generated
   */
  EReference getGMergeableDifference_ImplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getImplicitDependenciesForReference <em>Implicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference#getImplicitDependenciesForReference()
   * @see #getGMergeableDifference()
   * @generated
   */
  EReference getGMergeableDifference_ImplicitDependenciesForReference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence <em>GElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence
   * @generated
   */
  EClass getGElementRelativePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getElementMatch <em>Element Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getElementMatch()
   * @see #getGElementRelativePresence()
   * @generated
   */
  EReference getGElementRelativePresence_ElementMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getPresenceRole <em>Presence Role</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Presence Role</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence#getPresenceRole()
   * @see #getGElementRelativePresence()
   * @generated
   */
  EAttribute getGElementRelativePresence_PresenceRole();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence <em>GElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence
   * @generated
   */
  EClass getGElementPresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence#getOwnerMatch <em>Owner Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Owner Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence#getOwnerMatch()
   * @see #getGElementPresence()
   * @generated
   */
  EReference getGElementPresence_OwnerMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence <em>GValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence
   * @generated
   */
  EClass getGValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence#isOrder <em>Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence#isOrder()
   * @see #getGValuePresence()
   * @generated
   */
  EAttribute getGValuePresence_Order();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence <em>GAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence
   * @generated
   */
  EClass getGAttributeValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence <em>GReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>GReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence
   * @generated
   */
  EClass getGReferenceValuePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence#getValueMatch <em>Value Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence#getValueMatch()
   * @see #getGReferenceValuePresence()
   * @generated
   */
  EReference getGReferenceValuePresence_ValueMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IComparison <em>IComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IComparison</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IComparison" typeParameters="E"
   * @generated
   */
  EClass getIComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IComparison.Editable <em>IEditable Comparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Comparison</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IComparison.Editable" typeParameters="E"
   * @generated
   */
  EClass getIEditableComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IMapping <em>IMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMapping" typeParameters="E"
   * @generated
   */
  EClass getIMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IMapping.Editable <em>IEditable Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Mapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMapping.Editable" typeParameters="E"
   * @generated
   */
  EClass getIEditableMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IMatch <em>IMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMatch</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMatch" typeParameters="E"
   * @generated
   */
  EClass getIMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.IMatch.Editable <em>IEditable Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMatch.Editable" typeParameters="E"
   * @generated
   */
  EClass getIEditableMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference <em>IMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference" typeParameters="E"
   * @generated
   */
  EClass getIMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Mergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable" typeParameters="E"
   * @generated
   */
  EClass getIEditableMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence" typeParameters="E"
   * @generated
   */
  EClass getIElementRelativePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence <em>IElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence" typeParameters="E"
   * @generated
   */
  EClass getIElementPresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence <em>IValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence" typeParameters="E"
   * @generated
   */
  EClass getIValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence" typeParameters="E"
   * @generated
   */
  EClass getIAttributeValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence" typeParameters="E"
   * @generated
   */
  EClass getIReferenceValuePresence();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope <em>IEditable Tree Data Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IEditable Tree Data Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope" typeParameters="E"
   * @generated
   */
  EDataType getIEditableTreeDataScope();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.generic.api.IMatchPolicy <em>IMatch Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IMatch Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMatchPolicy" typeParameters="E"
   * @generated
   */
  EDataType getIMatchPolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.generic.api.IDiffPolicy <em>IDiff Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IDiff Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IDiffPolicy" typeParameters="E"
   * @generated
   */
  EDataType getIDiffPolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.generic.api.IMergePolicy <em>IMerge Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IMerge Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.IMergePolicy" typeParameters="E"
   * @generated
   */
  EDataType getIMergePolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.generic.api.Role <em>Role</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Role</em>'.
   * @see org.eclipse.emf.diffmerge.generic.api.Role
   * @model instanceClass="org.eclipse.emf.diffmerge.generic.api.Role"
   * @generated
   */
  EDataType getRole();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  GdiffdataFactory getGdiffdataFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GIdentifiedImpl <em>GIdentified</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GIdentifiedImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGIdentified()
     * @generated
     */
    EClass GIDENTIFIED = eINSTANCE.getGIdentified();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GIDENTIFIED__ID = eINSTANCE.getGIdentified_Id();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl <em>GComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGComparison()
     * @generated
     */
    EClass GCOMPARISON = eINSTANCE.getGComparison();

    /**
     * The meta object literal for the '<em><b>Ancestor Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__ANCESTOR_SCOPE = eINSTANCE
        .getGComparison_AncestorScope();

    /**
     * The meta object literal for the '<em><b>Reference Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__REFERENCE_SCOPE = eINSTANCE
        .getGComparison_ReferenceScope();

    /**
     * The meta object literal for the '<em><b>Target Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__TARGET_SCOPE = eINSTANCE
        .getGComparison_TargetScope();

    /**
     * The meta object literal for the '<em><b>Last Match Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__LAST_MATCH_POLICY = eINSTANCE
        .getGComparison_LastMatchPolicy();

    /**
     * The meta object literal for the '<em><b>Last Diff Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__LAST_DIFF_POLICY = eINSTANCE
        .getGComparison_LastDiffPolicy();

    /**
     * The meta object literal for the '<em><b>Last Merge Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GCOMPARISON__LAST_MERGE_POLICY = eINSTANCE
        .getGComparison_LastMergePolicy();

    /**
     * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GCOMPARISON__MAPPING = eINSTANCE.getGComparison_Mapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement <em>GComparison Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGComparisonElement()
     * @generated
     */
    EClass GCOMPARISON_ELEMENT = eINSTANCE.getGComparisonElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl <em>GMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMapping()
     * @generated
     */
    EClass GMAPPING = eINSTANCE.getGMapping();

    /**
     * The meta object literal for the '<em><b>Modifiable Contents</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMAPPING__MODIFIABLE_CONTENTS = eINSTANCE
        .getGMapping_ModifiableContents();

    /**
     * The meta object literal for the '<em><b>Reference Completed Matches</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMAPPING__REFERENCE_COMPLETED_MATCHES = eINSTANCE
        .getGMapping_ReferenceCompletedMatches();

    /**
     * The meta object literal for the '<em><b>Target Completed Matches</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMAPPING__TARGET_COMPLETED_MATCHES = eINSTANCE
        .getGMapping_TargetCompletedMatches();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl <em>GMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMatch()
     * @generated
     */
    EClass GMATCH = eINSTANCE.getGMatch();

    /**
     * The meta object literal for the '<em><b>Match ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMATCH__MATCH_ID = eINSTANCE.getGMatch_MatchID();

    /**
     * The meta object literal for the '<em><b>Modifiable Related Differences</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMATCH__MODIFIABLE_RELATED_DIFFERENCES = eINSTANCE
        .getGMatch_ModifiableRelatedDifferences();

    /**
     * The meta object literal for the '<em><b>Element Presence Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMATCH__ELEMENT_PRESENCE_DIFFERENCE = eINSTANCE
        .getGMatch_ElementPresenceDifference();

    /**
     * The meta object literal for the '<em><b>Reference Ownership Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = eINSTANCE
        .getGMatch_ReferenceOwnershipDifference();

    /**
     * The meta object literal for the '<em><b>Target Ownership Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMATCH__TARGET_OWNERSHIP_DIFFERENCE = eINSTANCE
        .getGMatch_TargetOwnershipDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl <em>GMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGMergeableDifference()
     * @generated
     */
    EClass GMERGEABLE_DIFFERENCE = eINSTANCE.getGMergeableDifference();

    /**
     * The meta object literal for the '<em><b>Aligned With Ancestor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = eINSTANCE
        .getGMergeableDifference_AlignedWithAncestor();

    /**
     * The meta object literal for the '<em><b>Conflicting</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMERGEABLE_DIFFERENCE__CONFLICTING = eINSTANCE
        .getGMergeableDifference_Conflicting();

    /**
     * The meta object literal for the '<em><b>Ignored</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMERGEABLE_DIFFERENCE__IGNORED = eINSTANCE
        .getGMergeableDifference_Ignored();

    /**
     * The meta object literal for the '<em><b>Merge Destination</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMERGEABLE_DIFFERENCE__MERGE_DESTINATION = eINSTANCE
        .getGMergeableDifference_MergeDestination();

    /**
     * The meta object literal for the '<em><b>Possible Merge Destinations</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = eINSTANCE
        .getGMergeableDifference_PossibleMergeDestinations();

    /**
     * The meta object literal for the '<em><b>Explicit Dependencies For Target</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = eINSTANCE
        .getGMergeableDifference_ExplicitDependenciesForTarget();

    /**
     * The meta object literal for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = eINSTANCE
        .getGMergeableDifference_ExplicitDependenciesForReference();

    /**
     * The meta object literal for the '<em><b>Implicit Dependencies For Target</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = eINSTANCE
        .getGMergeableDifference_ImplicitDependenciesForTarget();

    /**
     * The meta object literal for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = eINSTANCE
        .getGMergeableDifference_ImplicitDependenciesForReference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl <em>GElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGElementRelativePresence()
     * @generated
     */
    EClass GELEMENT_RELATIVE_PRESENCE = eINSTANCE.getGElementRelativePresence();

    /**
     * The meta object literal for the '<em><b>Element Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = eINSTANCE
        .getGElementRelativePresence_ElementMatch();

    /**
     * The meta object literal for the '<em><b>Presence Role</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = eINSTANCE
        .getGElementRelativePresence_PresenceRole();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl <em>GElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGElementPresence()
     * @generated
     */
    EClass GELEMENT_PRESENCE = eINSTANCE.getGElementPresence();

    /**
     * The meta object literal for the '<em><b>Owner Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GELEMENT_PRESENCE__OWNER_MATCH = eINSTANCE
        .getGElementPresence_OwnerMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl <em>GValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGValuePresence()
     * @generated
     */
    EClass GVALUE_PRESENCE = eINSTANCE.getGValuePresence();

    /**
     * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GVALUE_PRESENCE__ORDER = eINSTANCE.getGValuePresence_Order();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl <em>GAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGAttributeValuePresence()
     * @generated
     */
    EClass GATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getGAttributeValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl <em>GReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getGReferenceValuePresence()
     * @generated
     */
    EClass GREFERENCE_VALUE_PRESENCE = eINSTANCE.getGReferenceValuePresence();

    /**
     * The meta object literal for the '<em><b>Value Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GREFERENCE_VALUE_PRESENCE__VALUE_MATCH = eINSTANCE
        .getGReferenceValuePresence_ValueMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IComparison <em>IComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IComparison
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIComparison()
     * @generated
     */
    EClass ICOMPARISON = eINSTANCE.getIComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IComparison.Editable <em>IEditable Comparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableComparison()
     * @generated
     */
    EClass IEDITABLE_COMPARISON = eINSTANCE.getIEditableComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IMapping <em>IMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMapping
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMapping()
     * @generated
     */
    EClass IMAPPING = eINSTANCE.getIMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IMapping.Editable <em>IEditable Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMapping()
     * @generated
     */
    EClass IEDITABLE_MAPPING = eINSTANCE.getIEditableMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IMatch <em>IMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMatch
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMatch()
     * @generated
     */
    EClass IMATCH = eINSTANCE.getIMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.IMatch.Editable <em>IEditable Match</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMatch()
     * @generated
     */
    EClass IEDITABLE_MATCH = eINSTANCE.getIEditableMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference <em>IMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMergeableDifference()
     * @generated
     */
    EClass IMERGEABLE_DIFFERENCE = eINSTANCE.getIMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableMergeableDifference()
     * @generated
     */
    EClass IEDITABLE_MERGEABLE_DIFFERENCE = eINSTANCE
        .getIEditableMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIElementRelativePresence()
     * @generated
     */
    EClass IELEMENT_RELATIVE_PRESENCE = eINSTANCE.getIElementRelativePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence <em>IElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIElementPresence()
     * @generated
     */
    EClass IELEMENT_PRESENCE = eINSTANCE.getIElementPresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence <em>IValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIValuePresence()
     * @generated
     */
    EClass IVALUE_PRESENCE = eINSTANCE.getIValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIAttributeValuePresence()
     * @generated
     */
    EClass IATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getIAttributeValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIReferenceValuePresence()
     * @generated
     */
    EClass IREFERENCE_VALUE_PRESENCE = eINSTANCE.getIReferenceValuePresence();

    /**
     * The meta object literal for the '<em>IEditable Tree Data Scope</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIEditableTreeDataScope()
     * @generated
     */
    EDataType IEDITABLE_TREE_DATA_SCOPE = eINSTANCE.getIEditableTreeDataScope();

    /**
     * The meta object literal for the '<em>IMatch Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMatchPolicy()
     * @generated
     */
    EDataType IMATCH_POLICY = eINSTANCE.getIMatchPolicy();

    /**
     * The meta object literal for the '<em>IDiff Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIDiffPolicy()
     * @generated
     */
    EDataType IDIFF_POLICY = eINSTANCE.getIDiffPolicy();

    /**
     * The meta object literal for the '<em>IMerge Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getIMergePolicy()
     * @generated
     */
    EDataType IMERGE_POLICY = eINSTANCE.getIMergePolicy();

    /**
     * The meta object literal for the '<em>Role</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.api.Role
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getRole()
     * @generated
     */
    EDataType ROLE = eINSTANCE.getRole();

  }

} //GdiffdataPackage
