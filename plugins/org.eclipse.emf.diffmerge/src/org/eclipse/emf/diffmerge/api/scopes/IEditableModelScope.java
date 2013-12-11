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
package org.eclipse.emf.diffmerge.api.scopes;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A featured model scope which has the ability to be modified.
 * @author Olivier Constant
 */
public interface IEditableModelScope extends IFeaturedModelScope {
  
  /**
   * Add the given element to the scope.
   * Whether its contents belong to the scope after execution is intentionally undefined.
   * If the element already belongs to the scope, then the behavior of this operation is
   * undefined.
   * Postcondition: if true is returned then covers(element_p)
   * @param element_p a non-null element
   * @return whether the operation succeeded
   */
  boolean add(EObject element_p);
  
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
  boolean add(EObject source_p, EAttribute attribute_p, Object value_p);
  
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
  boolean add(EObject source_p, EReference reference_p, EObject value_p);
  
  /**
   * Return whether this scope is read-only, which means that modification operations have no effect
   */
  boolean isReadOnly();
  
  /**
   * Move the value held by the given element via the given feature at the given
   * position to the given new position.
   * @param source_p a non-null element
   * @param feature_p a non-null feature
   * @param newPosition_p a positive int or 0
   * @param oldPosition_p an arbitrary int, where a negative value stands for the last element
   * @return the value moved or null if none
   */
  Object move(EObject source_p, EStructuralFeature feature_p, int newPosition_p,
      int oldPosition_p);
  
  /**
   * Remove the given element from this scope.
   * Whether its contents still belong to the scope after execution is intentionally undefined.
   * Precondition: covers(element_p)
   * Postcondition: !covers(element_p)
   * @param element_p a non-null element within the scope
   * @return whether the operation succeeded
   */
  boolean remove(EObject element_p);
  
  /**
   * Remove the given value on the given attribute from the given element.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param attribute_p a non-null attribute
   * @param value_p a non-null value
   * @return whether the operation succeeded
   */
  boolean remove(EObject source_p, EAttribute attribute_p, Object value_p);
  
  /**
   * Remove the given value on the given reference from the given element.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @param value_p a non-null element as value
   * @return whether the operation succeeded
   */
  boolean remove(EObject source_p, EReference reference_p, EObject value_p);
  
}
