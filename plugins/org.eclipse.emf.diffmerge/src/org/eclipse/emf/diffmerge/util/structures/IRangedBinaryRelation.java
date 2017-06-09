/**
 * <copyright>
 * 
 * Copyright (c) 2013-2017 Thales Global Services S.A.S.
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

import java.util.Collection;


/**
 * A binary relation whose field (actual domain and range) is directly available.
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public interface IRangedBinaryRelation<T, U> extends IBinaryRelation<T, U> {
  
  /**
   * Return the domain elements which are mapped to at least one codomain element
   * @return a non-null, potentially empty, unmodifiable set which is not
   *        guaranteed to remain in sync with the relation
   */
  Collection<T> getSources();
  
  /**
   * Return the codomain elements which are mapped to at least one domain element
   * @return a non-null, potentially empty, unmodifiable set which is not
   *        guaranteed to remain in sync with the relation
   */
  Collection<U> getTargets();
  
  /**
   * Return whether the relation contains zero couple
   */
  boolean isEmpty();
  
  
  /**
   * A binary relation whose field can be directly modified.
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  interface Editable<T, U> extends IRangedBinaryRelation<T, U>,
  IBinaryRelation.Editable<T, U>{
    /**
     * Remove all couples whose domain element is the given one 
     * @param source_p a non-null domain element
     * @return whether the operation had any impact, which is true if and only if
     *         getSources().contains(source_p)
     */
    boolean removeSource(T source_p);
    
    /**
     * Remove all couples whose codomain element is the given one 
     * @param target_p a non-null codomain element
     * @return whether the operation had any impact, which is true if and only if
     *         getTargets().contains(source_p)
     */
    boolean removeTarget(U target_p);
  }
  
}
