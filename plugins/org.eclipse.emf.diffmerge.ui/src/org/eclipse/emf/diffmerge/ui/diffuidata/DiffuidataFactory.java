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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage
 * @generated
 */
public interface DiffuidataFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DiffuidataFactory eINSTANCE = org.eclipse.emf.diffmerge.ui.diffuidata.impl.DiffuidataFactoryImpl
      .init();

  /**
   * Returns a new object of class '<em>UI Comparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>UI Comparison</em>'.
   * @generated
   */
  UIComparison createUIComparison();

  /**
   * Returns a new object of class '<em>Comparison Selection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Comparison Selection</em>'.
   * @generated
   */
  ComparisonSelection createComparisonSelection();

  /**
   * Returns a new object of class '<em>Match And Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Match And Feature</em>'.
   * @generated
   */
  MatchAndFeature createMatchAndFeature();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DiffuidataPackage getDiffuidataPackage();

} //DiffuidataFactory
