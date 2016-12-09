/**
 * <copyright>
 * 
 * Copyright (c) 2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.impacts;

import org.eclipse.emf.diffmerge.structures.endo.qualified.ICachingQEndorelation;
import org.eclipse.emf.diffmerge.structures.endo.qualified.IRecursivelyDefinedQEndorelation;


/**
 * An impact is a directed multigraph, defined recursively by a set of 'origin' objects
 * and a set of impact rules.
 * Its nodes may refer to arbitrary objects and its edges refer to an impact rule.
 * An impact can be computed incrementally.
 * 
 * @author Olivier Constant
 */
public interface IImpact extends IRecursivelyDefinedQEndorelation<Object, IImpactRule> {
  
  /**
   * An extension of IImpact with a known exploration state.
   */
  public interface Caching extends IImpact, ICachingQEndorelation<Object, IImpactRule> {
    // Nothing more
  }
  
  /**
   * An extension of IImpact with a known exploration state and inverse navigation.
   */
  public interface Invertible extends Caching,
  ICachingQEndorelation.Invertible<Object, IImpactRule> {
    // Nothing more
  }
  
}
