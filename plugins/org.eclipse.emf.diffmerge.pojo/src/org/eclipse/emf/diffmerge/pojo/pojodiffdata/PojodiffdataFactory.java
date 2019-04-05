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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage
 * @generated
 */
public interface PojodiffdataFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PojodiffdataFactory eINSTANCE = org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.PojodiffdataFactoryImpl
      .init();

  /**
   * Returns a new object of class '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EComparison</em>'.
   * @generated
   */
  <E extends Object> EComparison<E> createEComparison();

  /**
   * Returns a new object of class '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EMapping</em>'.
   * @generated
   */
  <E extends Object> EMapping<E> createEMapping();

  /**
   * Returns a new object of class '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EMatch</em>'.
   * @generated
   */
  <E extends Object> EMatch<E> createEMatch();

  /**
   * Returns a new object of class '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EElement Presence</em>'.
   * @generated
   */
  <E extends Object> EElementPresence<E> createEElementPresence();

  /**
   * Returns a new object of class '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EAttribute Value Presence</em>'.
   * @generated
   */
  <E extends Object> EAttributeValuePresence<E> createEAttributeValuePresence();

  /**
   * Returns a new object of class '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>EReference Value Presence</em>'.
   * @generated
   */
  <E extends Object> EReferenceValuePresence<E> createEReferenceValuePresence();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  PojodiffdataPackage getPojodiffdataPackage();

} //PojodiffdataFactory
