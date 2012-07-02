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

import java.util.List;


/**
 * A modifiable finitary binary relation.
 * The order in which couples are introduced is assumed to be preserved (add/get consistency).
 * @param T the type of the domain elements
 * @param U the type of the codomain elements
 * @author Olivier Constant
 */
public interface IModifiableBinaryRelation<T, U> extends IBinaryRelation<T, U> {
  
  /**
   * Clear the set of couples of this relation.
   */
  void clear();
  
  /**
   * Add the given couple to the relation.
   * @param source_p a non-null element
   * @param target_p a non-null element which may or not differ from source_p
   */
  void add(T source_p, U target_p);
  
  /**
   * A refinement of get(T) with an ordering of the result.
   * It is the order in which couples were introduced (add/get consistency).
   * @see org.eclipse.emf.diffmerge.util.structures.IBinaryRelation#get(Object)
   */
  List<U> get(T element_p);
  
  /**
   * Remove the given couple from the relation if present.
   * @param source_p a non-null element
   * @param target_p a non-null element which may or not differ from source_p
   */
  void remove(T source_p, U target_p);
  
}
