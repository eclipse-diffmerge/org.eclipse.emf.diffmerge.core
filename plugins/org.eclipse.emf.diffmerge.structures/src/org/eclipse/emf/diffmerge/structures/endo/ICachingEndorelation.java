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
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;


/**
 * A recursively-defined endorelation that has a known exploration state
 * that evolves incrementally.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface ICachingEndorelation<T> extends IRecursivelyDefinedEndorelation<T>,
IRangedEndorelation<T> {
  
  /**
   * Explore the endorelation completely.
   * Precondition: the endorelation is finite.
   * Postcondition: result == getExplorationDepth().
   * @return the maximal exploration depth that has been reached
   */
  long explore();
  
  /**
   * Explore the endorelation for the given number of depth steps
   * @param depthSteps_p a positive number
   * @return the number actually explored (may be lesser than depthSteps_p due to
   *          the endorelation being fully explored
   */
  long exploreNext(long depthSteps_p);
  
  /**
   * Explore the endorelation so that the given exploration depth is reached.
   * It has no effect if the current exploration depth is already greater or equal.
   * Postcondition: isExplored() || getExplorationDepth() == depth_p
   * @param depth_p a positive number
   * @return the number of depth steps actually explored
   */
  long exploreUntil(long depth_p);
  
  /**
   * Return the current exploration depth
   * @return a positive int or -1 if exploration has not started
   */
  long getExplorationDepth();
  
  /**
   * Return the explored subset of the endorelation
   * @return a non-null finite endorelation
   */
  IRangedEndorelation<T> getExploredSubset();
  
  /**
   * Return whether the endorelation has been fully explored.
   * Class invariant: !isExplored() || getExplorationDepth() >= 0 || getOrigins().isEmpty()
   */
  boolean isExplored();
  
  /**
   * Reset the exploration phase after clearing the cache.
   * Postcondition: getExplorationDepth() == -1
   */
  void resetExploration();
  
  
  /**
   * An extension of ICachingQEndorelation with inverse navigation.
   *
   * @param <T> the type of the elements
   */
  public interface Invertible<T> extends ICachingEndorelation<T>, IEndorelation.Invertible<T> {
    /**
     * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#getExploredSubset()
     * Refined.
     */
    IRangedEndorelation.Invertible<T> getExploredSubset();
    
    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(java.lang.Object)
     * Refined. In the case where the given element has not been explored (at least as target),
     * the result is empty.
     */
    Collection<T> getInverse(T element_p);
  }
  
}