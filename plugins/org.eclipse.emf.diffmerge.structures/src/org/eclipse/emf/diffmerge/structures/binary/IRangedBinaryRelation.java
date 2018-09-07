/*********************************************************************
 * Copyright (c) 2013-2016 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.binary;

import java.util.Collection;


/**
 * A finite binary relation whose field (actual domain and range) is
 * directly available.
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public interface IRangedBinaryRelation<T, U> extends IBinaryRelation<T, U> {
  
  /**
   * Return the source elements which are mapped to at least one target element
   * @return a non-null, potentially empty, unmodifiable set which is not
   *        guaranteed to remain in sync with the relation
   */
  Collection<T> getSources();
  
  /**
   * Return the target elements which are mapped to at least one source element
   * @return a non-null, potentially empty, unmodifiable set which is not
   *        guaranteed to remain in sync with the relation
   */
  Collection<U> getTargets();
  
  
  /**
   * A binary relation whose field can be directly modified.
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface Editable<T, U> extends IRangedBinaryRelation<T, U>,
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
  
  
  /**
   * A ranged binary relation in types (T, U) which supports inverse navigation.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface Invertible<T, U> extends IRangedBinaryRelation<T, U>,
  IBinaryRelation.Invertible<T, U> {
    // Nothing more
  }
  
  /**
   * A ranged binary relation in types (T, U) that supports inverse navigation
   * and that can be edited.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   */
  public interface InvertibleEditable<T, U> extends Invertible<T, U>, Editable<T, U> {
    // Nothing more
  }
  
}
