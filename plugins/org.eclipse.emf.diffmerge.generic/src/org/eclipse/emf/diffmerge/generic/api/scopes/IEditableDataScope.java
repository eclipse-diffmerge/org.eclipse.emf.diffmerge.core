/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.api.scopes;


/**
 * A data scope which can be directly modified.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IEditableDataScope<E> extends IDataScope<E>,
IRawDataScope.Editable<E> {
  
  /**
   * Add the given value to the given element on the given attribute if possible,
   * otherwise do nothing.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p an non-null element
   * @param attribute_p a non-null attribute
   * @param value_p a non-null attribute value which is type-compatible with the attribute
   * @return whether the operation succeeded
   */
  boolean addAttributeValue(E source_p, Object attribute_p, Object value_p);
  
  /**
   * Add the given value to the given element on the given reference if possible,
   * otherwise do nothing.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * If the given value does not belong to the scope, it may belong to it after execution
   * of this operation as a side effect.
   * @param source_p an non-null element
   * @param reference_p a non-null reference
   * @param value_p a non-null element as value which is type-compatible with the reference
   * @return whether the operation succeeded
   */
  boolean addReferenceValue(E source_p, Object reference_p, E value_p);
  
  /**
   * Move the value held by the given element via the given attribute at the given
   * position to the given new position.
   * @param source_p a non-null element
   * @param attribute_p a non-null attribute
   * @param newPosition_p a positive int or 0
   * @param oldPosition_p an arbitrary int, where a negative value stands for the last element
   * @return the value moved or null if none
   */
  Object moveAttributeValue(E source_p, Object attribute_p, int newPosition_p,
      int oldPosition_p);
  
  /**
   * Move the value held by the given element via the given reference at the given
   * position to the given new position.
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @param newPosition_p a positive int or 0
   * @param oldPosition_p an arbitrary int, where a negative value stands for the last element
   * @return the value moved or null if none
   */
  E moveReferenceValue(E source_p, Object reference_p, int newPosition_p,
      int oldPosition_p);
  
  /**
   * Remove the given value on the given attribute from the given element.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param attribute_p a non-null attribute
   * @param value_p a non-null value
   * @return whether the operation succeeded
   */
  boolean removeAttributeValue(E source_p, Object attribute_p, Object value_p);
  
  /**
   * Remove the given value on the given reference from the given element.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @param value_p a non-null element as value
   * @return whether the operation succeeded
   */
  boolean removeReferenceValue(E source_p, Object reference_p, E value_p);
  
}
