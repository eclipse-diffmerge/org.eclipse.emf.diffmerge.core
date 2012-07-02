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

import java.util.Collection;


/**
 * A binary relation from type T to type U.
 * @param T the type of the domain elements
 * @param U the type of the codomain elements
 * @author Olivier Constant
 */
public interface IBinaryRelation<T, U> extends IEqualityBasedStructure {
  
  /**
   * Return the elements which are mapped to the given one.
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable set which is not guaranteed
   *         to remain in sync with the relation
   */
  Collection<U> get(T element_p);
  
  /**
   * Return whether the given couple is present in this relation.
   * Class invariant: maps(e1, e2) == get(e1).contains(e2)
   * @param source_p a non-null element
   * @param target_p a non-null element which is allowed to be source_p
   */
  boolean maps(T source_p, U target_p);
  
}
