/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.binary.qualified;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;


/**
 * A qualified ranged binary relation.
 * @see IRangedBinaryRelation
 * @see IQBinaryRelation
 * 
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface IRangedQBinaryRelation<T, U, Q> extends IQBinaryRelation<T, U, Q>,
IRangedBinaryRelation<T, U> {
  
  /**
   * Return the set of qualifiers that are used in the relation.
   * Class invariant: it is the set of all qualifiers returned by
   * getWithDetails(...) when applied to all getSources().
   * @return a non-null, potentially empty, unmodifiable set
   */
  Collection<Q> getQualifiers();
  
  
  /**
   * A modifiable qualified ranged binary relation.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   * @param <Q> the type of the qualifiers
   */
  public interface Editable<T, U, Q> extends IRangedQBinaryRelation<T, U, Q>,
  IRangedBinaryRelation.Editable<T, U>, IQBinaryRelation.Editable<T, U, Q> {
    /**
     * Remove the qualifier from the relation
     * @param qualifier_p a potentially null qualifier, where null stands for the unqualified case
     * @return whether the operation had any impact
     */
    boolean removeQualifier(Q qualifier_p);
  }
  
  
  /**
   * An invertible qualified ranged binary relation.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   * @param <Q> the type of the qualifiers
   */
  public interface Invertible<T, U, Q> extends IRangedQBinaryRelation<T, U, Q>,
  IRangedBinaryRelation.Invertible<T, U>, IQBinaryRelation.Invertible<T, U, Q> {
    // Nothing to add
  }
  
  
  /**
   * A ranged qualified binary relation that supports inverse navigation
   * and that can be edited.
   * 
   * @param <T> the type of the domain elements
   * @param <U> the type of the codomain elements
   * @param <Q> the type of the qualifiers
   */
  public interface InvertibleEditable<T, U, Q> extends Invertible<T, U, Q>, Editable<T, U, Q> {
    // Nothing more
  }
  
}
