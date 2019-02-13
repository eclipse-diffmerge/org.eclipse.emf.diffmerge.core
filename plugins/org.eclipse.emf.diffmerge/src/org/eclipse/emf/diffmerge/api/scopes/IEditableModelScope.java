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
package org.eclipse.emf.diffmerge.api.scopes;

import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A featured model scope which has the ability to be modified.
 * @author Olivier Constant
 */
public interface IEditableModelScope extends IFeaturedModelScope, IModelScope.Editable,
IEditableTreeDataScope<EObject>{
  
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
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#addAttributeValue(java.lang.Object, java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default boolean addAttributeValue(EObject source_p, Object attribute_p, Object value_p) {
    return add(source_p, (EAttribute)attribute_p, value_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#addReferenceValue(java.lang.Object, java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default boolean addReferenceValue(EObject source_p, Object reference_p, EObject value_p) {
    return add(source_p, (EReference)reference_p, value_p);
  }
  
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
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#moveAttributeValue(java.lang.Object, java.lang.Object, int, int)
   * Here to avoid API breakage.
   */
  default Object moveAttributeValue(EObject source_p, Object attribute_p,
      int newPosition_p, int oldPosition_p) {
    return move(source_p, (EAttribute)attribute_p, newPosition_p, oldPosition_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#moveReferenceValue(java.lang.Object, java.lang.Object, int, int)
   * Here to avoid API breakage.
   */
  default EObject moveReferenceValue(EObject source_p, Object reference_p,
      int newPosition_p, int oldPosition_p) {
    return (EObject)move(source_p, (EReference)reference_p, newPosition_p, oldPosition_p);
  }
  
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
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#removeAttributeValue(java.lang.Object, java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default boolean removeAttributeValue(EObject source_p, Object attribute_p,
      Object value_p) {
    return remove(source_p, (EAttribute)attribute_p, value_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope#removeReferenceValue(java.lang.Object, java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default boolean removeReferenceValue(EObject source_p, Object reference_p,
      EObject value_p) {
    return remove(source_p, (EReference)reference_p, value_p);
  }
  
}
