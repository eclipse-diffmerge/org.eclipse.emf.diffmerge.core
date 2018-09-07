/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation;


/**
 * A recursively-defined qualified endorelation that has a known exploration state
 * that evolves incrementally.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface ICachingQEndorelation<T, Q> extends IRecursivelyDefinedQEndorelation<T, Q>,
ICachingEndorelation<T>, IRangedQEndorelation<T, Q> {
  
  /**
   * An extension of ICachingQEndorelation with inverse navigation.
   *
   * @param <T> the type of the elements
   * @param <Q> the type of the qualifiers
   */
  public interface Invertible<T, Q> extends ICachingQEndorelation<T, Q>,
  IQEndorelation.Invertible<T, Q> {
    /**
     * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#getExploredSubset()
     */
    IRangedQEndorelation<T, Q> getExploredSubset();
  }
  
}