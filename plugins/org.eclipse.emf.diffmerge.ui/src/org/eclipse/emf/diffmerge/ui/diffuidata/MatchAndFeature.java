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
package org.eclipse.emf.diffmerge.ui.diffuidata;

import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A simple data structure that aggregates an IMatch and an attribute or reference.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#isAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getMatch <em>Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature()
 * @model
 * @generated
 */
public interface MatchAndFeature extends EObject {
  /**
   * Returns the value of the '<em><b>Attribute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' attribute.
   * @see #setAttribute(boolean)
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature_Attribute()
   * @model required="true"
   * @generated
   */
  boolean isAttribute();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#isAttribute <em>Attribute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute</em>' attribute.
   * @see #isAttribute()
   * @generated
   */
  void setAttribute(boolean value);

  /**
   * Returns the value of the '<em><b>Match</b></em>' reference.
   * <!-- begin-user-doc -->
   * Returns the non-null match
   * <!-- end-user-doc -->
   * @return the value of the '<em>Match</em>' reference.
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature_Match()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatch&lt;?&gt;" required="true" changeable="false"
   * @generated
   */
  IMatch<?> getMatch();

  /**
   * Returns the value of the '<em><b>Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * Returns the non-null feature
   * <!-- end-user-doc -->
   * @return the value of the '<em>Feature</em>' attribute.
   * @see #setFeature(Object)
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage#getMatchAndFeature_Feature()
   * @model required="true"
   * @generated
   */
  Object getFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature#getFeature <em>Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Feature</em>' attribute.
   * @see #getFeature()
   * @generated
   */
  void setFeature(Object value);

} // MatchAndFeature
