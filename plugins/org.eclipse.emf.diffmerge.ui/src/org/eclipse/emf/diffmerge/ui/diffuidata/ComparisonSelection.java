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
package org.eclipse.emf.diffmerge.ui.diffuidata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;

/**
 * <!-- begin-user-doc -->
 * A selection model for Comparison Viewer.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getDiffNode <em>Diff Node</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatches <em>Selected Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedMatchAndFeature <em>Selected Match And Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedTreePath <em>Selected Tree Path</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#getSelectedValuePresences <em>Selected Value Presences</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection()
 * @model superTypes="org.eclipse.emf.diffmerge.ui.diffuidata.IStructuredSelection"
 * @generated
 */
public interface ComparisonSelection extends EObject, IStructuredSelection {
  /**
   * Returns the value of the '<em><b>Diff Node</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Diff Node</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Diff Node</em>' attribute.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection_DiffNode()
   * @model dataType="org.eclipse.emf.diffmerge.ui.diffuidata.EMFDiffNode" required="true" transient="true" changeable="false"
   * @generated
   */
  EMFDiffNode getDiffNode();

  /**
   * Returns the value of the '<em><b>Selected Matches</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;?&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selected Matches</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selected Matches</em>' reference list.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection_SelectedMatches()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;?&gt;" changeable="false"
   * @generated
   */
  EList<IMatch<?>> getSelectedMatches();

  /**
   * Returns the value of the '<em><b>Selected Match And Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selected Match And Feature</em>' containment reference.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection_SelectedMatchAndFeature()
   * @model containment="true" changeable="false"
   * @generated
   */
  MatchAndFeature getSelectedMatchAndFeature();

  /**
   * Returns the value of the '<em><b>Selected Tree Path</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.IMatch}<code>&lt;?&gt;</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selected Tree Path</em>' reference list.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection_SelectedTreePath()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;?&gt;" changeable="false"
   * @generated
   */
  EList<IMatch<?>> getSelectedTreePath();

  /**
   * Returns the value of the '<em><b>Selected Value Presences</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence}<code>&lt;?&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selected Value Presences</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selected Value Presences</em>' reference list.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getComparisonSelection_SelectedValuePresences()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IValuePresence&lt;?&gt;" changeable="false"
   * @generated
   */
  EList<IValuePresence<?>> getSelectedValuePresences();

  /**
   * <!-- begin-user-doc -->
   * Return the set of differences to merge according to this selection
   * @return a non-null, potentially empty, unmodifiable collection
   * <!-- end-user-doc -->
   * @model type="org.eclipse.emf.diffmerge.ui.diffuidata.IDifference&lt;?&gt;"
   * @generated
   */
  EList<IDifference<?>> asDifferencesToMerge();

  /**
   * <!-- begin-user-doc -->
   * Return the content of the selection in terms of structural feature
   * @return a potentially null structural feature
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  Object asFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  MatchAndFeature asMatchAndFeature();

  /**
   * <!-- begin-user-doc -->
   * Return the content of the selection in terms of match
   * @return a potentially null match
   * <!-- end-user-doc -->
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;?&gt;"
   * @generated
   */
  IMatch<?> asMatch();

  /**
   * <!-- begin-user-doc -->
   * Return the content of the selection in terms of a set of matches
   * <!-- end-user-doc -->
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;?&gt;"
   * @generated
   */
  EList<IMatch<?>> asMatches();

  /**
   * <!-- begin-user-doc -->
   * Return the content of the selection as a tree path of matches
   * @return a potentially null tree path
   * <!-- end-user-doc -->
   * @model dataType="org.eclipse.emf.diffmerge.ui.diffuidata.TreePath"
   * @generated
   */
  TreePath asMatchPath();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IValuePresence&lt;?&gt;"
   * @generated
   */
  IValuePresence<?> asValuePresence();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IValuePresence&lt;?&gt;"
   * @generated
   */
  EList<IValuePresence<?>> asValuePresences();

  /**
   * <!-- begin-user-doc -->
   * Dispose the receiver
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void dispose();

} // ComparisonSelection
