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

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * A model scope which can be queried via the EMF notions of attribute,
 * reference and containment.
 * @author Olivier Constant
 */
public interface IFeaturedModelScope extends IModelScope {
  
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
   * Return the containment reference through which the given element is
   * being contained, if any. Result must be consistent with getContainer(EObject).
   * Postcondition: result == null || result.isContainment()
   * Postcondition: (result == null) == (getContainer(element_p) == null)
   * @param element_p a non-null element
   * @return a potentially null containment reference
   */
  EReference getContainment(EObject element_p);
  
}
