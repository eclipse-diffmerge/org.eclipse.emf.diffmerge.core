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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EIdentifiedImpl <em>EIdentified</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EIdentifiedImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEIdentified()
   * @generated
   */
  int EIDENTIFIED = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIED__ID = 0;

  /**
   * The number of structural features of the '<em>EIdentified</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EIDENTIFIED_FEATURE_COUNT = 1;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEComparison()
   * @generated
   */
  int ECOMPARISON = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ID = EIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ANCESTOR_SCOPE = EIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__REFERENCE_SCOPE = EIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__TARGET_SCOPE = EIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MATCH_POLICY = EIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_DIFF_POLICY = EIDENTIFIED_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MERGE_POLICY = EIDENTIFIED_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__MAPPING = EIDENTIFIED_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>EComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_FEATURE_COUNT = EIDENTIFIED_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement <em>EComparison Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEComparisonElement()
   * @generated
   */
  int ECOMPARISON_ELEMENT = 2;

  /**
   * The number of structural features of the '<em>EComparison Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_ELEMENT_FEATURE_COUNT = 0;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl <em>EMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMapping()
   * @generated
   */
  int EMAPPING = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__ID = EIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__MODIFIABLE_CONTENTS = EIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__REFERENCE_COMPLETED_MATCHES = EIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__TARGET_COMPLETED_MATCHES = EIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>EMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING_FEATURE_COUNT = EIDENTIFIED_FEATURE_COUNT + 3;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl <em>EMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMatch()
   * @generated
   */
  int EMATCH = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ID = EIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MATCH_ID = EIDENTIFIED_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_RELATED_DIFFERENCES = EIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ELEMENT_PRESENCE_DIFFERENCE = EIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = EIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET_OWNERSHIP_DIFFERENCE = EIDENTIFIED_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>EMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH_FEATURE_COUNT = EIDENTIFIED_FEATURE_COUNT + 5;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl <em>EMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMergeableDifference()
   * @generated
   */
  int EMERGEABLE_DIFFERENCE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__ID = EIDENTIFIED__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = EIDENTIFIED_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__CONFLICTING = EIDENTIFIED_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IGNORED = EIDENTIFIED_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__MERGE_DESTINATION = EIDENTIFIED_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = EIDENTIFIED_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EIDENTIFIED_FEATURE_COUNT
      + 5;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EIDENTIFIED_FEATURE_COUNT
      + 6;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EIDENTIFIED_FEATURE_COUNT
      + 7;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EIDENTIFIED_FEATURE_COUNT
      + 8;

  /**
   * The number of structural features of the '<em>EMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE_FEATURE_COUNT = EIDENTIFIED_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl <em>EElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEElementRelativePresence()
   * @generated
   */
  int EELEMENT_RELATIVE_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ID = EMERGEABLE_DIFFERENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR = EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__CONFLICTING = EMERGEABLE_DIFFERENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IGNORED = EMERGEABLE_DIFFERENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION = EMERGEABLE_DIFFERENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = EMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = EMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 1;

  /**
   * The number of structural features of the '<em>EElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = EMERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEElementPresence()
   * @generated
   */
  int EELEMENT_PRESENCE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ID = EELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ALIGNED_WITH_ANCESTOR = EELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__CONFLICTING = EELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IGNORED = EELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__MERGE_DESTINATION = EELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = EELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__ELEMENT_MATCH = EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__PRESENCE_ROLE = EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Owner Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__OWNER_MATCH = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>EElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE_FEATURE_COUNT = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl <em>EValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEValuePresence()
   * @generated
   */
  int EVALUE_PRESENCE = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ID = EELEMENT_RELATIVE_PRESENCE__ID;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ALIGNED_WITH_ANCESTOR = EELEMENT_RELATIVE_PRESENCE__ALIGNED_WITH_ANCESTOR;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__CONFLICTING = EELEMENT_RELATIVE_PRESENCE__CONFLICTING;

  /**
   * The feature id for the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IGNORED = EELEMENT_RELATIVE_PRESENCE__IGNORED;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__MERGE_DESTINATION = EELEMENT_RELATIVE_PRESENCE__MERGE_DESTINATION;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS = EELEMENT_RELATIVE_PRESENCE__POSSIBLE_MERGE_DESTINATIONS;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = EELEMENT_RELATIVE_PRESENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = EELEMENT_RELATIVE_PRESENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE;

  /**
   * The feature id for the '<em><b>Element Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ELEMENT_MATCH = EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH;

  /**
   * The feature id for the '<em><b>Presence Role</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__PRESENCE_ROLE = EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ORDER = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>EValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE_FEATURE_COUNT = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEAttributeValuePresence()
   * @generated
   */
  int EATTRIBUTE_VALUE_PRESENCE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ID = EVALUE_PRESENCE__ID;

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
   * The number of structural features of the '<em>EAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEReferenceValuePresence()
   * @generated
   */
  int EREFERENCE_VALUE_PRESENCE = 10;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ID = EVALUE_PRESENCE__ID;

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
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = EVALUE_PRESENCE_FEATURE_COUNT
      + 0;

  /**
   * The number of structural features of the '<em>EReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = EVALUE_PRESENCE_FEATURE_COUNT
      + 1;

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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified <em>EIdentified</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EIdentified</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified
   * @generated
   */
  EClass getEIdentified();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified#getId()
   * @see #getEIdentified()
   * @generated
   */
  EAttribute getEIdentified_Id();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison
   * @generated
   */
  EClass getEComparison();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ancestor Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getAncestorScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_AncestorScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getReferenceScope <em>Reference Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getReferenceScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_ReferenceScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getTargetScope <em>Target Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Scope</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getTargetScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_TargetScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Match Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMatchPolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastMatchPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Diff Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastDiffPolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastDiffPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Merge Policy</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMergePolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastMergePolicy();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getMapping()
   * @see #getEComparison()
   * @generated
   */
  EReference getEComparison_Mapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement <em>EComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EComparison Element</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement
   * @generated
   */
  EClass getEComparisonElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMapping</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping
   * @generated
   */
  EClass getEMapping();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getModifiableContents <em>Modifiable Contents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Contents</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getModifiableContents()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_ModifiableContents();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reference Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getReferenceCompletedMatches()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_ReferenceCompletedMatches();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Target Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping#getTargetCompletedMatches()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_TargetCompletedMatches();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMatch</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch
   * @generated
   */
  EClass getEMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getMatchID <em>Match ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Match ID</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getMatchID()
   * @see #getEMatch()
   * @generated
   */
  EAttribute getEMatch_MatchID();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Related Differences</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getModifiableRelatedDifferences()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableRelatedDifferences();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Presence Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getElementPresenceDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ElementPresenceDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getReferenceOwnershipDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ReferenceOwnershipDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch#getTargetOwnershipDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_TargetOwnershipDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference
   * @generated
   */
  EClass getEMergeableDifference();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isAlignedWithAncestor <em>Aligned With Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Aligned With Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isAlignedWithAncestor()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_AlignedWithAncestor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isConflicting <em>Conflicting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Conflicting</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isConflicting()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_Conflicting();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isIgnored <em>Ignored</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ignored</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isIgnored()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_Ignored();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getMergeDestination <em>Merge Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Merge Destination</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getMergeDestination()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_MergeDestination();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getPossibleMergeDestinations <em>Possible Merge Destinations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Possible Merge Destinations</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getPossibleMergeDestinations()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_PossibleMergeDestinations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForTarget <em>Explicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForTarget()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ExplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForReference <em>Explicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForReference()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ExplicitDependenciesForReference();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForTarget <em>Implicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForTarget()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ImplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForReference <em>Implicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForReference()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ImplicitDependenciesForReference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence
   * @generated
   */
  EClass getEElementRelativePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence#getElementMatch <em>Element Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence#getElementMatch()
   * @see #getEElementRelativePresence()
   * @generated
   */
  EReference getEElementRelativePresence_ElementMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence#getPresenceRole <em>Presence Role</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Presence Role</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence#getPresenceRole()
   * @see #getEElementRelativePresence()
   * @generated
   */
  EAttribute getEElementRelativePresence_PresenceRole();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence
   * @generated
   */
  EClass getEElementPresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence#getOwnerMatch <em>Owner Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Owner Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence#getOwnerMatch()
   * @see #getEElementPresence()
   * @generated
   */
  EReference getEElementPresence_OwnerMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence
   * @generated
   */
  EClass getEValuePresence();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence#isOrder <em>Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence#isOrder()
   * @see #getEValuePresence()
   * @generated
   */
  EAttribute getEValuePresence_Order();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence
   * @generated
   */
  EClass getEAttributeValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>EReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence
   * @generated
   */
  EClass getEReferenceValuePresence();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence#getValueMatch <em>Value Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value Match</em>'.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence#getValueMatch()
   * @see #getEReferenceValuePresence()
   * @generated
   */
  EReference getEReferenceValuePresence_ValueMatch();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EIdentifiedImpl <em>EIdentified</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EIdentifiedImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEIdentified()
     * @generated
     */
    EClass EIDENTIFIED = eINSTANCE.getEIdentified();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EIDENTIFIED__ID = eINSTANCE.getEIdentified_Id();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl <em>EComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEComparison()
     * @generated
     */
    EClass ECOMPARISON = eINSTANCE.getEComparison();

    /**
     * The meta object literal for the '<em><b>Ancestor Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__ANCESTOR_SCOPE = eINSTANCE
        .getEComparison_AncestorScope();

    /**
     * The meta object literal for the '<em><b>Reference Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__REFERENCE_SCOPE = eINSTANCE
        .getEComparison_ReferenceScope();

    /**
     * The meta object literal for the '<em><b>Target Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__TARGET_SCOPE = eINSTANCE
        .getEComparison_TargetScope();

    /**
     * The meta object literal for the '<em><b>Last Match Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__LAST_MATCH_POLICY = eINSTANCE
        .getEComparison_LastMatchPolicy();

    /**
     * The meta object literal for the '<em><b>Last Diff Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__LAST_DIFF_POLICY = eINSTANCE
        .getEComparison_LastDiffPolicy();

    /**
     * The meta object literal for the '<em><b>Last Merge Policy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ECOMPARISON__LAST_MERGE_POLICY = eINSTANCE
        .getEComparison_LastMergePolicy();

    /**
     * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ECOMPARISON__MAPPING = eINSTANCE.getEComparison_Mapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement <em>EComparison Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEComparisonElement()
     * @generated
     */
    EClass ECOMPARISON_ELEMENT = eINSTANCE.getEComparisonElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl <em>EMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMapping()
     * @generated
     */
    EClass EMAPPING = eINSTANCE.getEMapping();

    /**
     * The meta object literal for the '<em><b>Modifiable Contents</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__MODIFIABLE_CONTENTS = eINSTANCE
        .getEMapping_ModifiableContents();

    /**
     * The meta object literal for the '<em><b>Reference Completed Matches</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__REFERENCE_COMPLETED_MATCHES = eINSTANCE
        .getEMapping_ReferenceCompletedMatches();

    /**
     * The meta object literal for the '<em><b>Target Completed Matches</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMAPPING__TARGET_COMPLETED_MATCHES = eINSTANCE
        .getEMapping_TargetCompletedMatches();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl <em>EMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMatchImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMatch()
     * @generated
     */
    EClass EMATCH = eINSTANCE.getEMatch();

    /**
     * The meta object literal for the '<em><b>Match ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMATCH__MATCH_ID = eINSTANCE.getEMatch_MatchID();

    /**
     * The meta object literal for the '<em><b>Modifiable Related Differences</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__MODIFIABLE_RELATED_DIFFERENCES = eINSTANCE
        .getEMatch_ModifiableRelatedDifferences();

    /**
     * The meta object literal for the '<em><b>Element Presence Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__ELEMENT_PRESENCE_DIFFERENCE = eINSTANCE
        .getEMatch_ElementPresenceDifference();

    /**
     * The meta object literal for the '<em><b>Reference Ownership Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = eINSTANCE
        .getEMatch_ReferenceOwnershipDifference();

    /**
     * The meta object literal for the '<em><b>Target Ownership Difference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__TARGET_OWNERSHIP_DIFFERENCE = eINSTANCE
        .getEMatch_TargetOwnershipDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl <em>EMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEMergeableDifference()
     * @generated
     */
    EClass EMERGEABLE_DIFFERENCE = eINSTANCE.getEMergeableDifference();

    /**
     * The meta object literal for the '<em><b>Aligned With Ancestor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = eINSTANCE
        .getEMergeableDifference_AlignedWithAncestor();

    /**
     * The meta object literal for the '<em><b>Conflicting</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMERGEABLE_DIFFERENCE__CONFLICTING = eINSTANCE
        .getEMergeableDifference_Conflicting();

    /**
     * The meta object literal for the '<em><b>Ignored</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMERGEABLE_DIFFERENCE__IGNORED = eINSTANCE
        .getEMergeableDifference_Ignored();

    /**
     * The meta object literal for the '<em><b>Merge Destination</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMERGEABLE_DIFFERENCE__MERGE_DESTINATION = eINSTANCE
        .getEMergeableDifference_MergeDestination();

    /**
     * The meta object literal for the '<em><b>Possible Merge Destinations</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = eINSTANCE
        .getEMergeableDifference_PossibleMergeDestinations();

    /**
     * The meta object literal for the '<em><b>Explicit Dependencies For Target</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = eINSTANCE
        .getEMergeableDifference_ExplicitDependenciesForTarget();

    /**
     * The meta object literal for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = eINSTANCE
        .getEMergeableDifference_ExplicitDependenciesForReference();

    /**
     * The meta object literal for the '<em><b>Implicit Dependencies For Target</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = eINSTANCE
        .getEMergeableDifference_ImplicitDependenciesForTarget();

    /**
     * The meta object literal for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = eINSTANCE
        .getEMergeableDifference_ImplicitDependenciesForReference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl <em>EElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEElementRelativePresence()
     * @generated
     */
    EClass EELEMENT_RELATIVE_PRESENCE = eINSTANCE.getEElementRelativePresence();

    /**
     * The meta object literal for the '<em><b>Element Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EELEMENT_RELATIVE_PRESENCE__ELEMENT_MATCH = eINSTANCE
        .getEElementRelativePresence_ElementMatch();

    /**
     * The meta object literal for the '<em><b>Presence Role</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EELEMENT_RELATIVE_PRESENCE__PRESENCE_ROLE = eINSTANCE
        .getEElementRelativePresence_PresenceRole();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEElementPresence()
     * @generated
     */
    EClass EELEMENT_PRESENCE = eINSTANCE.getEElementPresence();

    /**
     * The meta object literal for the '<em><b>Owner Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EELEMENT_PRESENCE__OWNER_MATCH = eINSTANCE
        .getEElementPresence_OwnerMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl <em>EValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEValuePresence()
     * @generated
     */
    EClass EVALUE_PRESENCE = eINSTANCE.getEValuePresence();

    /**
     * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EVALUE_PRESENCE__ORDER = eINSTANCE.getEValuePresence_Order();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EAttributeValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEAttributeValuePresence()
     * @generated
     */
    EClass EATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getEAttributeValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl <em>EReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl
     * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GdiffdataPackageImpl#getEReferenceValuePresence()
     * @generated
     */
    EClass EREFERENCE_VALUE_PRESENCE = eINSTANCE.getEReferenceValuePresence();

    /**
     * The meta object literal for the '<em><b>Value Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = eINSTANCE
        .getEReferenceValuePresence_ValueMatch();

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
