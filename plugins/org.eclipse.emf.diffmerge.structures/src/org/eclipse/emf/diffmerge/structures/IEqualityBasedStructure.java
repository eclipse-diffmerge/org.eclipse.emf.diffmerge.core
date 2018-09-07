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
package org.eclipse.emf.diffmerge.structures;


/**
 * Data structures which are based on a user-defined equality function.
 * By default, this function is equality by object reference.
 * 
 * @author Olivier Constant
 */
public interface IEqualityBasedStructure {
  
  /**
   * Return the equality tester of this structure
   * @return a non-null equality tester
   */
  IEqualityTester getEqualityTester();
  
  /**
   * The default equality tester for the case where none has been explicitly provided
   */
  IEqualityTester DEFAULT_TESTER = IEqualityTester.BY_REFERENCE;
  
}
