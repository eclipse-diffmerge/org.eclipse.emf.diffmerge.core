/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage
 * @generated
 */
public interface DiffdataFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DiffdataFactory eINSTANCE = org.eclipse.emf.diffmerge.diffdata.impl.DiffdataFactoryImpl
      .init();

  /**
   * Returns a new object of class '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EComparison</em>'.
   * @generated
   */
  EComparison createEComparison();

  /**
   * Returns a new object of class '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EMapping</em>'.
   * @generated
   */
  EMapping createEMapping();

  /**
   * Returns a new object of class '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EMatch</em>'.
   * @generated
   */
  EMatch createEMatch();

  /**
   * Returns a new object of class '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EElement Presence</em>'.
   * @generated
   */
  EElementPresence createEElementPresence();

  /**
   * Returns a new object of class '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EAttribute Value Presence</em>'.
   * @generated
   */
  EAttributeValuePresence createEAttributeValuePresence();

  /**
   * Returns a new object of class '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EReference Value Presence</em>'.
   * @generated
   */
  EReferenceValuePresence createEReferenceValuePresence();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DiffdataPackage getDiffdataPackage();

} //DiffdataFactory
