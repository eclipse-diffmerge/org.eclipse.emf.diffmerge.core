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

import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;


/**
 * A finite endorelation whose elements are directly available.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface IRangedEndorelation<T> extends IIterableEndorelation<T>,
IRangedBinaryRelation<T, T> {
  
  /**
   * Return the set of all elements in the endorelation.
   * It is the union of getSources() and getTarget().
   * @return a non-null, potentially empty, unmodifiable set which is not
   *        guaranteed to remain in sync with the relation
   */
  Collection<T> getElements();
  
  
  /**
   * A finite endorelation that can be directly modified.
   * 
   * @param <T> the type of the elements
   */
  public interface Editable<T> extends IRangedEndorelation<T>,
  IRangedBinaryRelation.Editable<T, T>{
    /**
     * Remove the given element from the endorelation
     * @param element_p a non-null element
     * @return whether the operation had any impact, which is true if and only if
     *         getElements().contains(element_p)
     */
    boolean removeElement(T element_p);
  }
  
  
  /**
   * A finite endorelation which supports inverse navigation.
   * 
   * @param <T> the type of the elements
   */
  public interface Invertible<T> extends IRangedEndorelation<T>, IEndorelation.Invertible<T>,
  IRangedBinaryRelation.Invertible<T, T> {
    // Nothing to add
  }
  
  
  /**
   * A ranged endorelation that supports inverse navigation
   * and that can be edited.
   * 
   * @param <T> the type of the elements
   */
  public interface InvertibleEditable<T> extends Invertible<T>, Editable<T>,
  IRangedBinaryRelation.InvertibleEditable<T, T> {
    // Nothing more
  }
  
}
