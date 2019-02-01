/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.api.diff;


/**
 * A difference which represents the unmatched presence of an attribute value.
 *
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IAttributeValuePresence<E, A, R> extends IValuePresence<E, A, R> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getFeature()
   */
  A getFeature();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getSymmetrical()
   */
  IAttributeValuePresence<E, A, R> getSymmetrical();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getValue()
   * @return a non-null object
   */
  Object getValue();
  
}
