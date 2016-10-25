/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.diffdata;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IComparison.Editable <em>IEditable Comparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableComparison()
   * @generated
   */
  int IEDITABLE_COMPARISON = 14;

  /**
   * The number of structural features of the '<em>IEditable Comparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_COMPARISON_FEATURE_COUNT = 0;

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
   * The feature id for the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__ANCESTOR_SCOPE = IEDITABLE_COMPARISON_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__REFERENCE_SCOPE = IEDITABLE_COMPARISON_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__TARGET_SCOPE = IEDITABLE_COMPARISON_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MATCH_POLICY = IEDITABLE_COMPARISON_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_DIFF_POLICY = IEDITABLE_COMPARISON_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__LAST_MERGE_POLICY = IEDITABLE_COMPARISON_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON__MAPPING = IEDITABLE_COMPARISON_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>EComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECOMPARISON_FEATURE_COUNT = IEDITABLE_COMPARISON_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IMapping.Editable <em>IEditable Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMapping()
   * @generated
   */
  int IEDITABLE_MAPPING = 16;

  /**
   * The number of structural features of the '<em>IEditable Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MAPPING_FEATURE_COUNT = 0;

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
   * The feature id for the '<em><b>Modifiable Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__MODIFIABLE_CONTENTS = IEDITABLE_MAPPING_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Reference Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__REFERENCE_COMPLETED_MATCHES = IEDITABLE_MAPPING_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Target Completed Matches</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING__TARGET_COMPLETED_MATCHES = IEDITABLE_MAPPING_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>EMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMAPPING_FEATURE_COUNT = IEDITABLE_MAPPING_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IMatch.Editable <em>IEditable Match</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMatch.Editable
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMatch()
   * @generated
   */
  int IEDITABLE_MATCH = 18;

  /**
   * The number of structural features of the '<em>IEditable Match</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MATCH_FEATURE_COUNT = 0;

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
   * The feature id for the '<em><b>Match ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MATCH_ID = IEDITABLE_MATCH_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ANCESTOR = IEDITABLE_MATCH_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE = IEDITABLE_MATCH_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET = IEDITABLE_MATCH_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Modifiable Related Differences</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_RELATED_DIFFERENCES = IEDITABLE_MATCH_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Modifiable Attribute Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_ATTRIBUTE_MAP = IEDITABLE_MATCH_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Modifiable Reference Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__MODIFIABLE_REFERENCE_MAP = IEDITABLE_MATCH_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Element Presence Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__ELEMENT_PRESENCE_DIFFERENCE = IEDITABLE_MATCH_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Reference Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__REFERENCE_OWNERSHIP_DIFFERENCE = IEDITABLE_MATCH_FEATURE_COUNT
      + 8;

  /**
   * The feature id for the '<em><b>Target Ownership Difference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH__TARGET_OWNERSHIP_DIFFERENCE = IEDITABLE_MATCH_FEATURE_COUNT + 9;

  /**
   * The number of structural features of the '<em>EMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMATCH_FEATURE_COUNT = IEDITABLE_MATCH_FEATURE_COUNT + 10;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMergeableDifference()
   * @generated
   */
  int IEDITABLE_MERGEABLE_DIFFERENCE = 20;

  /**
   * The number of structural features of the '<em>IEditable Mergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl <em>EMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMergeableDifference()
   * @generated
   */
  int EMERGEABLE_DIFFERENCE = 3;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__COMPARISON = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 0;

  /**
   * The feature id for the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 1;

  /**
   * The feature id for the '<em><b>Conflicting</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__CONFLICTING = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 2;

  /**
   * The feature id for the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__MERGE_DESTINATION = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 3;

  /**
   * The feature id for the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 4;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 5;

  /**
   * The feature id for the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 6;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 7;

  /**
   * The feature id for the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 8;

  /**
   * The number of structural features of the '<em>EMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMERGEABLE_DIFFERENCE_FEATURE_COUNT = IEDITABLE_MERGEABLE_DIFFERENCE_FEATURE_COUNT
      + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl <em>EElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementRelativePresence()
   * @generated
   */
  int EELEMENT_RELATIVE_PRESENCE = 4;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_RELATIVE_PRESENCE__COMPARISON = EMERGEABLE_DIFFERENCE__COMPARISON;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementPresence()
   * @generated
   */
  int EELEMENT_PRESENCE = 5;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EELEMENT_PRESENCE__COMPARISON = EELEMENT_RELATIVE_PRESENCE__COMPARISON;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl <em>EValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEValuePresence()
   * @generated
   */
  int EVALUE_PRESENCE = 6;

  /**
   * The feature id for the '<em><b>Comparison</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__COMPARISON = EELEMENT_RELATIVE_PRESENCE__COMPARISON;

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
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__FEATURE = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE__ORDER = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>EValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVALUE_PRESENCE_FEATURE_COUNT = EELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT
      + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl <em>EAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEAttributeValuePresence()
   * @generated
   */
  int EATTRIBUTE_VALUE_PRESENCE = 7;

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
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__FEATURE = EVALUE_PRESENCE__FEATURE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EATTRIBUTE_VALUE_PRESENCE__ORDER = EVALUE_PRESENCE__ORDER;

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
  int EREFERENCE_VALUE_PRESENCE = 8;

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
   * The feature id for the '<em><b>Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__FEATURE = EVALUE_PRESENCE__FEATURE;

  /**
   * The feature id for the '<em><b>Order</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__ORDER = EVALUE_PRESENCE__ORDER;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE = EVALUE_PRESENCE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = EVALUE_PRESENCE_FEATURE_COUNT
      + 1;

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
  int ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY = 9;

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
  int VALUE_TO_DIFFERENCE_ENTRY = 10;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl <em>Element To Difference Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.diffdata.impl.ElementToDifferenceEntryImpl
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getElementToDifferenceEntry()
   * @generated
   */
  int ELEMENT_TO_DIFFERENCE_ENTRY = 12;

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
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IComparison <em>IComparison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IComparison
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIComparison()
   * @generated
   */
  int ICOMPARISON = 13;

  /**
   * The number of structural features of the '<em>IComparison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ICOMPARISON_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IMapping <em>IMapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMapping
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMapping()
   * @generated
   */
  int IMAPPING = 15;

  /**
   * The number of structural features of the '<em>IMapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMAPPING_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.IMatch <em>IMatch</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMatch
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMatch()
   * @generated
   */
  int IMATCH = 17;

  /**
   * The number of structural features of the '<em>IMatch</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMATCH_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference <em>IMergeable Difference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMergeableDifference()
   * @generated
   */
  int IMERGEABLE_DIFFERENCE = 19;

  /**
   * The number of structural features of the '<em>IMergeable Difference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMERGEABLE_DIFFERENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIElementRelativePresence()
   * @generated
   */
  int IELEMENT_RELATIVE_PRESENCE = 21;

  /**
   * The number of structural features of the '<em>IElement Relative Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IELEMENT_RELATIVE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IElementPresence <em>IElement Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IElementPresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIElementPresence()
   * @generated
   */
  int IELEMENT_PRESENCE = 22;

  /**
   * The number of structural features of the '<em>IElement Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IELEMENT_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IValuePresence <em>IValue Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIValuePresence()
   * @generated
   */
  int IVALUE_PRESENCE = 23;

  /**
   * The number of structural features of the '<em>IValue Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IVALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIAttributeValuePresence()
   * @generated
   */
  int IATTRIBUTE_VALUE_PRESENCE = 24;

  /**
   * The number of structural features of the '<em>IAttribute Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IATTRIBUTE_VALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIReferenceValuePresence()
   * @generated
   */
  int IREFERENCE_VALUE_PRESENCE = 25;

  /**
   * The number of structural features of the '<em>IReference Value Presence</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IREFERENCE_VALUE_PRESENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '<em>IEditable Model Scope</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableModelScope()
   * @generated
   */
  int IEDITABLE_MODEL_SCOPE = 26;

  /**
   * The meta object id for the '<em>IMatch Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMatchPolicy
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMatchPolicy()
   * @generated
   */
  int IMATCH_POLICY = 27;

  /**
   * The meta object id for the '<em>IDiff Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIDiffPolicy()
   * @generated
   */
  int IDIFF_POLICY = 28;

  /**
   * The meta object id for the '<em>IMerge Policy</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMergePolicy()
   * @generated
   */
  int IMERGE_POLICY = 29;

  /**
   * The meta object id for the '<em>Role</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.diffmerge.api.Role
   * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getRole()
   * @generated
   */
  int ROLE = 30;

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
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ancestor Scope</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getAncestorScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_AncestorScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getReferenceScope <em>Reference Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference Scope</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getReferenceScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_ReferenceScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getTargetScope <em>Target Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Scope</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getTargetScope()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_TargetScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Match Policy</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMatchPolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastMatchPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Diff Policy</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getLastDiffPolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastDiffPolicy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Merge Policy</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMergePolicy()
   * @see #getEComparison()
   * @generated
   */
  EAttribute getEComparison_LastMergePolicy();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping()
   * @see #getEComparison()
   * @generated
   */
  EReference getEComparison_Mapping();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getModifiableContents <em>Modifiable Contents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Contents</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getModifiableContents()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_ModifiableContents();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getReferenceCompletedMatches <em>Reference Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Reference Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getReferenceCompletedMatches()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_ReferenceCompletedMatches();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMapping#getTargetCompletedMatches <em>Target Completed Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Target Completed Matches</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getTargetCompletedMatches()
   * @see #getEMapping()
   * @generated
   */
  EReference getEMapping_TargetCompletedMatches();

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
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getMatchID <em>Match ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Match ID</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getMatchID()
   * @see #getEMatch()
   * @generated
   */
  EAttribute getEMatch_MatchID();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableRelatedDifferences <em>Modifiable Related Differences</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiable Related Differences</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getModifiableRelatedDifferences()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ModifiableRelatedDifferences();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getElementPresenceDifference <em>Element Presence Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Presence Difference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getElementPresenceDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ElementPresenceDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getReferenceOwnershipDifference <em>Reference Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getReferenceOwnershipDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_ReferenceOwnershipDifference();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMatch#getTargetOwnershipDifference <em>Target Ownership Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Ownership Difference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMatch#getTargetOwnershipDifference()
   * @see #getEMatch()
   * @generated
   */
  EReference getEMatch_TargetOwnershipDifference();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getComparison <em>Comparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Comparison</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getComparison()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_Comparison();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#isAlignedWithAncestor <em>Aligned With Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Aligned With Ancestor</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#isAlignedWithAncestor()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_AlignedWithAncestor();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#isConflicting <em>Conflicting</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Conflicting</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#isConflicting()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_Conflicting();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getMergeDestination <em>Merge Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Merge Destination</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getMergeDestination()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_MergeDestination();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getPossibleMergeDestinations <em>Possible Merge Destinations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Possible Merge Destinations</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getPossibleMergeDestinations()
   * @see #getEMergeableDifference()
   * @generated
   */
  EAttribute getEMergeableDifference_PossibleMergeDestinations();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getExplicitDependenciesForTarget <em>Explicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getExplicitDependenciesForTarget()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ExplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getExplicitDependenciesForReference <em>Explicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Explicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getExplicitDependenciesForReference()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ExplicitDependenciesForReference();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getImplicitDependenciesForTarget <em>Implicit Dependencies For Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Target</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getImplicitDependenciesForTarget()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ImplicitDependenciesForTarget();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getImplicitDependenciesForReference <em>Implicit Dependencies For Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Implicit Dependencies For Reference</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EMergeableDifference#getImplicitDependenciesForReference()
   * @see #getEMergeableDifference()
   * @generated
   */
  EReference getEMergeableDifference_ImplicitDependenciesForReference();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getElementMatch <em>Element Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element Match</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getElementMatch()
   * @see #getEElementRelativePresence()
   * @generated
   */
  EReference getEElementRelativePresence_ElementMatch();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getPresenceRole <em>Presence Role</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Presence Role</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence#getPresenceRole()
   * @see #getEElementRelativePresence()
   * @generated
   */
  EAttribute getEElementRelativePresence_PresenceRole();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EElementPresence#getOwnerMatch <em>Owner Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Owner Match</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EElementPresence#getOwnerMatch()
   * @see #getEElementPresence()
   * @generated
   */
  EReference getEElementPresence_OwnerMatch();

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
   * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.diffdata.EValuePresence#isOrder <em>Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Order</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EValuePresence#isOrder()
   * @see #getEValuePresence()
   * @generated
   */
  EAttribute getEValuePresence_Order();

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
   * Returns the meta object for the reference '{@link org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValueMatch <em>Value Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Value Match</em>'.
   * @see org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence#getValueMatch()
   * @see #getEReferenceValuePresence()
   * @generated
   */
  EReference getEReferenceValuePresence_ValueMatch();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute To Value To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute To Value To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EAttribute" keyRequired="true"
   *        valueMapType="org.eclipse.emf.diffmerge.diffdata.ValueToDifferenceEntry<org.eclipse.emf.ecore.EJavaObject, org.eclipse.emf.diffmerge.diffdata.IAttributeValuePresence>"
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
   *        valueType="org.eclipse.emf.diffmerge.diffdata.IAttributeValuePresence" valueRequired="true"
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
   *        valueMapType="org.eclipse.emf.diffmerge.diffdata.ElementToDifferenceEntry<org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.diffdata.IReferenceValuePresence>"
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
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element To Difference Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
   *        valueType="org.eclipse.emf.diffmerge.diffdata.IReferenceValuePresence" valueRequired="true"
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
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IComparison <em>IComparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IComparison</em>'.
   * @see org.eclipse.emf.diffmerge.api.IComparison
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IComparison"
   * @generated
   */
  EClass getIComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IComparison.Editable <em>IEditable Comparison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Comparison</em>'.
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IComparison.Editable"
   * @generated
   */
  EClass getIEditableComparison();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IMapping <em>IMapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMapping</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMapping
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMapping"
   * @generated
   */
  EClass getIMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IMapping.Editable <em>IEditable Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Mapping</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMapping.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMapping.Editable"
   * @generated
   */
  EClass getIEditableMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IMatch <em>IMatch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMatch</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMatch
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMatch"
   * @generated
   */
  EClass getIMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.IMatch.Editable <em>IEditable Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Match</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMatch.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMatch.Editable"
   * @generated
   */
  EClass getIEditableMatch();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference <em>IMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IMergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IMergeableDifference"
   * @generated
   */
  EClass getIMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IEditable Mergeable Difference</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable"
   * @generated
   */
  EClass getIEditableMergeableDifference();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IElement Relative Presence</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence"
   * @generated
   */
  EClass getIElementRelativePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IElementPresence <em>IElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IElement Presence</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IElementPresence
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IElementPresence"
   * @generated
   */
  EClass getIElementPresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IValuePresence <em>IValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IValue Presence</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IValuePresence"
   * @generated
   */
  EClass getIValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IAttribute Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence"
   * @generated
   */
  EClass getIAttributeValuePresence();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>IReference Value Presence</em>'.
   * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence
   * @model instanceClass="org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence"
   * @generated
   */
  EClass getIReferenceValuePresence();

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
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.api.IMatchPolicy <em>IMatch Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IMatch Policy</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMatchPolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMatchPolicy"
   * @generated
   */
  EDataType getIMatchPolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.api.IDiffPolicy <em>IDiff Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IDiff Policy</em>'.
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IDiffPolicy"
   * @generated
   */
  EDataType getIDiffPolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.api.IMergePolicy <em>IMerge Policy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>IMerge Policy</em>'.
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy
   * @model instanceClass="org.eclipse.emf.diffmerge.api.IMergePolicy"
   * @generated
   */
  EDataType getIMergePolicy();

  /**
   * Returns the meta object for data type '{@link org.eclipse.emf.diffmerge.api.Role <em>Role</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Role</em>'.
   * @see org.eclipse.emf.diffmerge.api.Role
   * @model instanceClass="org.eclipse.emf.diffmerge.api.Role"
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl <em>EMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EMappingImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMapping()
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl <em>EMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EMatchImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMatch()
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
     * The meta object literal for the '<em><b>Modifiable Related Differences</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMATCH__MODIFIABLE_RELATED_DIFFERENCES = eINSTANCE
        .getEMatch_ModifiableRelatedDifferences();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl <em>EMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEMergeableDifference()
     * @generated
     */
    EClass EMERGEABLE_DIFFERENCE = eINSTANCE.getEMergeableDifference();

    /**
     * The meta object literal for the '<em><b>Comparison</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EMERGEABLE_DIFFERENCE__COMPARISON = eINSTANCE
        .getEMergeableDifference_Comparison();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl <em>EElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementRelativePresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementRelativePresence()
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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl <em>EElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getEElementPresence()
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
     * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EVALUE_PRESENCE__ORDER = eINSTANCE.getEValuePresence_Order();

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
     * The meta object literal for the '<em><b>Value</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__VALUE = eINSTANCE
        .getEReferenceValuePresence_Value();

    /**
     * The meta object literal for the '<em><b>Value Match</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EREFERENCE_VALUE_PRESENCE__VALUE_MATCH = eINSTANCE
        .getEReferenceValuePresence_ValueMatch();

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
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IComparison <em>IComparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IComparison
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIComparison()
     * @generated
     */
    EClass ICOMPARISON = eINSTANCE.getIComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IComparison.Editable <em>IEditable Comparison</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IComparison.Editable
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableComparison()
     * @generated
     */
    EClass IEDITABLE_COMPARISON = eINSTANCE.getIEditableComparison();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IMapping <em>IMapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMapping
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMapping()
     * @generated
     */
    EClass IMAPPING = eINSTANCE.getIMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IMapping.Editable <em>IEditable Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMapping.Editable
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMapping()
     * @generated
     */
    EClass IEDITABLE_MAPPING = eINSTANCE.getIEditableMapping();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IMatch <em>IMatch</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMatch
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMatch()
     * @generated
     */
    EClass IMATCH = eINSTANCE.getIMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.IMatch.Editable <em>IEditable Match</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMatch.Editable
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMatch()
     * @generated
     */
    EClass IEDITABLE_MATCH = eINSTANCE.getIEditableMatch();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference <em>IMergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMergeableDifference()
     * @generated
     */
    EClass IMERGEABLE_DIFFERENCE = eINSTANCE.getIMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableMergeableDifference()
     * @generated
     */
    EClass IEDITABLE_MERGEABLE_DIFFERENCE = eINSTANCE
        .getIEditableMergeableDifference();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIElementRelativePresence()
     * @generated
     */
    EClass IELEMENT_RELATIVE_PRESENCE = eINSTANCE.getIElementRelativePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IElementPresence <em>IElement Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IElementPresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIElementPresence()
     * @generated
     */
    EClass IELEMENT_PRESENCE = eINSTANCE.getIElementPresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IValuePresence <em>IValue Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIValuePresence()
     * @generated
     */
    EClass IVALUE_PRESENCE = eINSTANCE.getIValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIAttributeValuePresence()
     * @generated
     */
    EClass IATTRIBUTE_VALUE_PRESENCE = eINSTANCE.getIAttributeValuePresence();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIReferenceValuePresence()
     * @generated
     */
    EClass IREFERENCE_VALUE_PRESENCE = eINSTANCE.getIReferenceValuePresence();

    /**
     * The meta object literal for the '<em>IEditable Model Scope</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIEditableModelScope()
     * @generated
     */
    EDataType IEDITABLE_MODEL_SCOPE = eINSTANCE.getIEditableModelScope();

    /**
     * The meta object literal for the '<em>IMatch Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMatchPolicy
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMatchPolicy()
     * @generated
     */
    EDataType IMATCH_POLICY = eINSTANCE.getIMatchPolicy();

    /**
     * The meta object literal for the '<em>IDiff Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IDiffPolicy
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIDiffPolicy()
     * @generated
     */
    EDataType IDIFF_POLICY = eINSTANCE.getIDiffPolicy();

    /**
     * The meta object literal for the '<em>IMerge Policy</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.IMergePolicy
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getIMergePolicy()
     * @generated
     */
    EDataType IMERGE_POLICY = eINSTANCE.getIMergePolicy();

    /**
     * The meta object literal for the '<em>Role</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.diffmerge.api.Role
     * @see org.eclipse.emf.diffmerge.diffdata.impl.DiffdataPackageImpl#getRole()
     * @generated
     */
    EDataType ROLE = eINSTANCE.getRole();

  }

} //DiffdataPackage
