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

import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * A model scope which can be queried via the EMF notions of attribute,
 * reference and containment.
 * @author Olivier Constant
 */
public interface IFeaturedModelScope extends IModelScope,
ITreeDataScope<EObject> {
  
  /**
   * Return the values which are held by the given element via the given attribute.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param attribute_p a non-null attribute
   * @return an unmodifiable non-null list of the corresponding values,
   *         not containing null
   */
  List<Object> get(EObject source_p, EAttribute attribute_p);
  
  /**
   * Return the values which are held by the given element via the given
   * reference, if any. The values may not belong to the scope.
   * If the given element does not belong to this scope, the behavior of this
   * operation is undefined.
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @return an unmodifiable non-null list of the corresponding elements
   *         within this scope, not containing null
   */
  List<EObject> get(EObject source_p, EReference reference_p);
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope#getAttributeValues(java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default List<?> getAttributeValues(EObject element_p, Object attribute_p) {
    return get(element_p, (EAttribute)attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope#getReferenceValues(java.lang.Object, java.lang.Object)
   * Here to avoid API breakage.
   */
  default List<EObject> getReferenceValues(EObject element_p, Object reference_p) {
    return get(element_p, (EReference)reference_p);
  }
  
}
