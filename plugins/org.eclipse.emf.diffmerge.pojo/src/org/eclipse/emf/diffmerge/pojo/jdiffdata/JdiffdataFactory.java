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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage
 * @generated
 */
public interface JdiffdataFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  JdiffdataFactory eINSTANCE = org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JdiffdataFactoryImpl
      .init();

  /**
   * Returns a new object of class '<em>JComparison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JComparison</em>'.
   * @generated
   */
  <E extends Object> JComparison<E> createJComparison();

  /**
   * Returns a new object of class '<em>JMapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JMapping</em>'.
   * @generated
   */
  <E extends Object> JMapping<E> createJMapping();

  /**
   * Returns a new object of class '<em>JMatch</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JMatch</em>'.
   * @generated
   */
  <E extends Object> JMatch<E> createJMatch();

  /**
   * Returns a new object of class '<em>JElement Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JElement Presence</em>'.
   * @generated
   */
  <E extends Object> JElementPresence<E> createJElementPresence();

  /**
   * Returns a new object of class '<em>JAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JAttribute Value Presence</em>'.
   * @generated
   */
  <E extends Object> JAttributeValuePresence<E> createJAttributeValuePresence();

  /**
   * Returns a new object of class '<em>JReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>JReference Value Presence</em>'.
   * @generated
   */
  <E extends Object> JReferenceValuePresence<E> createJReferenceValuePresence();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  JdiffdataPackage getJdiffdataPackage();

} //JdiffdataFactory
