/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.util.structures;


/**
 * Data structures which are based on a user-defined equality function.
 * By default, this function is equality by object reference.
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
