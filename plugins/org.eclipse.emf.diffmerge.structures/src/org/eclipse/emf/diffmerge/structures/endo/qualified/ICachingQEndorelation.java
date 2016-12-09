/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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