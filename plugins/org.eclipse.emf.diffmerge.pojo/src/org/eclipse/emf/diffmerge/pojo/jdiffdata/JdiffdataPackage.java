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
package org.eclipse.emf.diffmerge.pojo.jdiffdata;

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
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataFactory
 * @model kind="package"
 * @generated
 */
public interface JdiffdataPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "jdiffdata"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/diffmerge/pojo/jdiffdata/1.0.0"; //$NON-NLS-1$

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
  JdiffdataPackage eINSTANCE = org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl
      .init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JComparisonImpl <em>JComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JComparisonImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJComparison()
   * @generated
   */
  int JCOMPARISON = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__ID = GdiffdataPackage.GCOMPARISON__ID;

  /**
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__ANCESTOR_SCOPE = GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__REFERENCE_SCOPE = GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__TARGET_SCOPE = GdiffdataPackage.GCOMPARISON__TARGET_SCOPE;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__LAST_MATCH_POLICY = GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__LAST_DIFF_POLICY = GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__LAST_MERGE_POLICY = GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON__MAPPING = GdiffdataPackage.GCOMPARISON__MAPPING;

  /**
   * The number of structural features of the '<em>JComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON_FEATURE_COUNT = GdiffdataPackage.GCOMPARISON_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement <em>JComparison Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJComparisonElement()
   * @generated
   */
  int JCOMPARISON_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>JComparison Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JCOMPARISON_ELEMENT_FEATURE_COUNT = GdiffdataPackage.GCOMPARISON_ELEMENT_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMappingImpl <em>JMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMappingImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMapping()
   * @generated
   */
  int JMAPPING = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__ID = GdiffdataPackage.GMAPPING__ID;

  /**
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__MODIFIABLE_CONTENTS = GdiffdataPackage.GMAPPING__MODIFIABLE_CONTENTS;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__REFERENCE_COMPLETED_MATCHES = GdiffdataPackage.GMAPPING__REFERENCE_COMPLETED_MATCHES;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__TARGET_COMPLETED_MATCHES = GdiffdataPackage.GMAPPING__TARGET_COMPLETED_MATCHES;

  /**
   * The feature id for the '<em><b>Ancestor Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__ANCESTOR_MATCH_MAP = GdiffdataPackage.GMAPPING_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Reference Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__REFERENCE_MATCH_MAP = GdiffdataPackage.GMAPPING_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Target Match Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING__TARGET_MATCH_MAP = GdiffdataPackage.GMAPPING_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>JMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMAPPING_FEATURE_COUNT = GdiffdataPackage.GMAPPING_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl <em>JMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMatch()
   * @generated
   */
  int JMATCH = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__ID = GdiffdataPackage.GMATCH__ID;

  /**
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__MATCH_ID = GdiffdataPackage.GMATCH__MATCH_ID;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__MODIFIABLE_RELATED_DIFFERENCES = GdiffdataPackage.GMATCH__MODIFIABLE_RELATED_DIFFERENCES;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__ELEMENT_PRESENCE_DIFFERENCE = GdiffdataPackage.GMATCH__ELEMENT_PRESENCE_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = GdiffdataPackage.GMATCH__REFERENCE_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__TARGET_OWNERSHIP_DIFFERENCE = GdiffdataPackage.GMATCH__TARGET_OWNERSHIP_DIFFERENCE;

  /**
   * The feature id for the '<em><b>Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__ANCESTOR = GdiffdataPackage.GMATCH_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__REFERENCE = GdiffdataPackage.GMATCH_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__TARGET = GdiffdataPackage.GMATCH_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Modifiable Attribute Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__MODIFIABLE_ATTRIBUTE_MAP = GdiffdataPackage.GMATCH_FEATURE_COUNT
      + 3;

  /**
   * The feature id for the '<em><b>Modifiable Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__MODIFIABLE_REFERENCE_MAP = GdiffdataPackage.GMATCH_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Modifiable Order Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = GdiffdataPackage.GMATCH_FEATURE_COUNT
      + 5;

  /**
   * The number of structural features of the '<em>JMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMATCH_FEATURE_COUNT = GdiffdataPackage.GMATCH_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference <em>JMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMergeableDifference()
   * @generated
   */
  int JMERGEABLE_DIFFERENCE = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__ID = GdiffdataPackage.GMERGEABLE_DIFFERENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__CONFLICTING = GdiffdataPackage.GMERGEABLE_DIFFERENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__IGNORED = GdiffdataPackage.GMERGEABLE_DIFFERENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__MERGE_DESTINATION = GdiffdataPackage.GMERGEABLE_DIFFERENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The number of structural features of the '<em>JMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JMERGEABLE_DIFFERENCE_FEATURE_COUNT = GdiffdataPackage.GMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence <em>JElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJElementRelativePresence()
   * @generated
   */
  int JELEMENT_RELATIVE_PRESENCE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__ID = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__CONFLICTING = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__IGNORED = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The number of structural features of the '<em>JElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JElementPresenceImpl <em>JElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJElementPresence()
   * @generated
   */
  int JELEMENT_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__ID = GdiffdataPackage.GELEMENT_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__CONFLICTING = GdiffdataPackage.GELEMENT_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__IGNORED = GdiffdataPackage.GELEMENT_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.GELEMENT_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.GELEMENT_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.GELEMENT_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE__OWNER_MATCH = GdiffdataPackage.GELEMENT_PRESENCE__OWNER_MATCH;

  /**
   * The number of structural features of the '<em>JElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JELEMENT_PRESENCE_FEATURE_COUNT = GdiffdataPackage.GELEMENT_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence <em>JValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJValuePresence()
   * @generated
   */
  int JVALUE_PRESENCE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__ID = GdiffdataPackage.GVALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__CONFLICTING = GdiffdataPackage.GVALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__IGNORED = GdiffdataPackage.GVALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.GVALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.GVALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.GVALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__ORDER = GdiffdataPackage.GVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE__FEATURE = GdiffdataPackage.GVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>JValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.GVALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl <em>JAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJAttributeValuePresence()
   * @generated
   */
  int JATTRIBUTE_VALUE_PRESENCE = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__ID = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__CONFLICTING = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__IGNORED = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__ORDER = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__FEATURE = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE__VALUE = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>JAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl <em>JReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJReferenceValuePresence()
   * @generated
   */
  int JREFERENCE_VALUE_PRESENCE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__ID = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__CONFLICTING = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__IGNORED = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__ORDER = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__VALUE_MATCH = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE__VALUE_MATCH;

  /**
   * The feature id for the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__FEATURE = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE__VALUE = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>JReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = GdiffdataPackage.GREFERENCE_VALUE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.AttributeToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getAttributeToDifferenceEntry()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToOrderDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getElementToDifferenceEntry()
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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToMatchEntryImpl <em>Element To Match Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToMatchEntryImpl
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getElementToMatchEntry()
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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison <em>JComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JComparison</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison
   * @generated
   */
  EClass getJComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement <em>JComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JComparison Element</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement
   * @generated
   */
  EClass getJComparisonElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping <em>JMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JMapping</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping
   * @generated
   */
  EClass getJMapping();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getAncestorMatchMap <em>Ancestor Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Ancestor Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getAncestorMatchMap()
   * @see #getJMapping()
   * @generated
   */
  EReference getJMapping_AncestorMatchMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getReferenceMatchMap <em>Reference Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Reference Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getReferenceMatchMap()
   * @see #getJMapping()
   * @generated
   */
  EReference getJMapping_ReferenceMatchMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getTargetMatchMap <em>Target Match Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Target Match Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping#getTargetMatchMap()
   * @see #getJMapping()
   * @generated
   */
  EReference getJMapping_TargetMatchMap();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch <em>JMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JMatch</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch
   * @generated
   */
  EClass getJMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getAncestor <em>Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getAncestor()
   * @see #getJMatch()
   * @generated
   */
  EAttribute getJMatch_Ancestor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getReference()
   * @see #getJMatch()
   * @generated
   */
  EAttribute getJMatch_Reference();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getTarget()
   * @see #getJMatch()
   * @generated
   */
  EAttribute getJMatch_Target();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Attribute Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableAttributeMap()
   * @see #getJMatch()
   * @generated
   */
  EReference getJMatch_ModifiableAttributeMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableReferenceMap <em>Modifiable Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableReferenceMap()
   * @see #getJMatch()
   * @generated
   */
  EReference getJMatch_ModifiableReferenceMap();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Modifiable Order Reference Map</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch#getModifiableOrderReferenceMap()
   * @see #getJMatch()
   * @generated
   */
  EReference getJMatch_ModifiableOrderReferenceMap();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference <em>JMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference
   * @generated
   */
  EClass getJMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence <em>JElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence
   * @generated
   */
  EClass getJElementRelativePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence <em>JElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence
   * @generated
   */
  EClass getJElementPresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence <em>JValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence
   * @generated
   */
  EClass getJValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Feature</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence#getFeature()
   * @see #getJValuePresence()
   * @generated
   */
  EAttribute getJValuePresence_Feature();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence <em>JAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence
   * @generated
   */
  EClass getJAttributeValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence#getValue()
   * @see #getJAttributeValuePresence()
   * @generated
   */
  EAttribute getJAttributeValuePresence_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence <em>JReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence
   * @generated
   */
  EClass getJReferenceValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence#getValue()
   * @see #getJReferenceValuePresence()
   * @generated
   */
  EAttribute getJReferenceValuePresence_Value();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IAttributeValuePresence&lt;?&gt;" valueMany="true"
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
   * @model keyDataType="org.eclipse.emf.ecore.EJavaObject" keyRequired="true"
   *        valueMapType="org.eclipse.emf.diffmerge.pojo.jdiffdata.ElementToDifferenceEntry&lt;org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;&gt;"
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
   *        valueType="org.eclipse.emf.diffmerge.generic.gdiffdata.IReferenceValuePresence&lt;?&gt;" valueMany="true"
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
   *        valueType="org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch&lt;?&gt;" valueRequired="true"
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
  JdiffdataFactory getJdiffdataFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JComparisonImpl <em>JComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JComparisonImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJComparison()
     * @generated
     */
    EClass JCOMPARISON = eINSTANCE.getJComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement <em>JComparison Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJComparisonElement()
     * @generated
     */
    EClass JCOMPARISON_ELEMENT = eINSTANCE.getJComparisonElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMappingImpl <em>JMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMappingImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMapping()
     * @generated
     */
    EClass JMAPPING = eINSTANCE.getJMapping();

    /**
     * The meta object literal for the '<em><b>Ancestor Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMAPPING__ANCESTOR_MATCH_MAP = eINSTANCE
        .getJMapping_AncestorMatchMap();

    /**
     * The meta object literal for the '<em><b>Reference Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMAPPING__REFERENCE_MATCH_MAP = eINSTANCE
        .getJMapping_ReferenceMatchMap();

    /**
     * The meta object literal for the '<em><b>Target Match Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMAPPING__TARGET_MATCH_MAP = eINSTANCE
        .getJMapping_TargetMatchMap();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl <em>JMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMatch()
     * @generated
     */
    EClass JMATCH = eINSTANCE.getJMatch();

    /**
     * The meta object literal for the '<em><b>Ancestor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JMATCH__ANCESTOR = eINSTANCE.getJMatch_Ancestor();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JMATCH__REFERENCE = eINSTANCE.getJMatch_Reference();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JMATCH__TARGET = eINSTANCE.getJMatch_Target();

    /**
     * The meta object literal for the '<em><b>Modifiable Attribute Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMATCH__MODIFIABLE_ATTRIBUTE_MAP = eINSTANCE
        .getJMatch_ModifiableAttributeMap();

    /**
     * The meta object literal for the '<em><b>Modifiable Reference Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMATCH__MODIFIABLE_REFERENCE_MAP = eINSTANCE
        .getJMatch_ModifiableReferenceMap();

    /**
     * The meta object literal for the '<em><b>Modifiable Order Reference Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP = eINSTANCE
        .getJMatch_ModifiableOrderReferenceMap();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference <em>JMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJMergeableDifference()
     * @generated
     */
    EClass JMERGEABLE_DIFFERENCE = eINSTANCE.getJMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence <em>JElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJElementRelativePresence()
     * @generated
     */
    EClass JELEMENT_RELATIVE_PRESENCE = eINSTANCE.getJElementRelativePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JElementPresenceImpl <em>JElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJElementPresence()
     * @generated
     */
    EClass JELEMENT_PRESENCE = eINSTANCE.getJElementPresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence <em>JValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJValuePresence()
     * @generated
     */
    EClass JVALUE_PRESENCE = eINSTANCE.getJValuePresence();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JVALUE_PRESENCE__FEATURE = eINSTANCE.getJValuePresence_Feature();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl <em>JAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJAttributeValuePresence()
     * @generated
     */
    EClass JATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getJAttributeValuePresence();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JATTRIBUTE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getJAttributeValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl <em>JReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getJReferenceValuePresence()
     * @generated
     */
    EClass JREFERENCE_VALUE_PRESENCE = eINSTANCE.getJReferenceValuePresence();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JREFERENCE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getJReferenceValuePresence_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.AttributeToDifferenceEntryImpl <em>Attribute To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.AttributeToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getAttributeToDifferenceEntry()
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
     * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE_TO_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getAttributeToDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToElementToDifferenceEntryImpl <em>Reference To Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getReferenceToElementToDifferenceEntry()
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToOrderDifferenceEntryImpl <em>Reference To Order Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ReferenceToOrderDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getReferenceToOrderDifferenceEntry()
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
     * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFERENCE_TO_ORDER_DIFFERENCE_ENTRY__VALUE = eINSTANCE
        .getReferenceToOrderDifferenceEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToDifferenceEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getElementToDifferenceEntry()
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToMatchEntryImpl <em>Element To Match Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.ElementToMatchEntryImpl
     * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataPackageImpl#getElementToMatchEntry()
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

} //JdiffdataPackage
