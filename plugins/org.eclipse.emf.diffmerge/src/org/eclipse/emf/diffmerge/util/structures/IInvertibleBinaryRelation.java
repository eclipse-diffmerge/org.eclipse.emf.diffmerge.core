/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
 * A binary relation in types (T, U) which supports inverse navigation.
 * @param T the type of the domain elements
 * @param U the type of the codomain elements
 * @author Olivier Constant
 */
public interface IInvertibleBinaryRelation<T, U> extends IBinaryRelation<T, U> {
  
  /**
   * Return the elements which map to the given one
   * @param element_p a non-null element
   * @return a non-null, potentially empty unmodifiable set which is not guaranteed
   *         to be kept in sync with the relation
   */
  Collection<T> getInverse(U element_p);
  
}
