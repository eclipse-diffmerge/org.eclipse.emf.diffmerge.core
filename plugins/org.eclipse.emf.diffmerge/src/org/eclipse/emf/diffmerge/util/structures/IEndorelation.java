/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
import java.util.List;


/**
 * An endorelation over type T.
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public interface IEndorelation<T> extends IBinaryRelation<T, T> {
  
  /**
   * Compute the transitive closure of this relation starting on the given element,
   * encompassing cycles if there are any
   * @param element_p a non-null element
   * @return a non-null, unmodifiable set of elements excluding element_p
   *         whose order is that of a breadth-first exploration of the relation graph
   */
  List<T> getTransitiveClosure(T element_p);
  
  /**
   * Compute the transitive closure of this relation starting on the given elements,
   * encompassing cycles if there are any
   * @param elements_p a non-null, potentially empty collection
   * @return a non-null, unmodifiable set of elements excluding elements_p
   *         whose order is that of a breadth-first exploration of the relation graph
   */
  List<T> getTransitiveClosure(Collection<? extends T> elements_p);
  
}
