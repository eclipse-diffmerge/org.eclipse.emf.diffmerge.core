/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A policy that alters the construction of differences between model scopes
 * in a comparison.
 * @author Olivier Constant
 */
public interface IDiffPolicy {
  
  /**
   * Return whether the given attribute values must be considered equal
   * @param value1_p the first non-null attribute value
   * @param value2_p the second non-null attribute value
   * @param attribute_p the non-null attribute concerned
   */
  boolean considerEqual(Object value1_p, Object value2_p, EAttribute attribute_p);
  
  /**
   * Return whether the given out-of-scope value must be considered equal to the
   * other given value. True may only be returned if the other value is also out of scope.
   * @param outOfScopeValue_p the non-null out-of-scope value
   * @param candidateValue_p the non-null other value
   * @param reference_p the non-null reference concerned
   */
  boolean considerEqualOutOfScope(EObject outOfScopeValue_p, EObject candidateValue_p,
      EReference reference_p);
  
  /**
   * Return whether the given feature must be considered as ordered
   * @param feature_p a structural feature within this scope
   *        (null stands for model root containment)
   */
  boolean considerOrdered(EStructuralFeature feature_p);
  
  /**
   * Return whether the given attribute or reference must be covered by
   * the difference detection algorithm.
   * Precondition: if feature_p instanceof EReference then
   *  !((EReference)feature_p).isContainment() && !((EReference)feature_p).isContainer()
   * In other terms, this method is never called for containment or container
   * references. This is because those are implicitly determined by the elements
   * that are present in the compared model scopes.
   * @param feature_p a non-null reference or attribute
   */
  boolean coverFeature(EStructuralFeature feature_p);
  
  /**
   * Return whether the given match must be covered by the difference detection
   * algorithm
   * @param match_p a non-null match
   */
  boolean coverMatch(IMatch match_p);
  
  /**
   * Return whether the given element, even though it is outside the TARGET and
   * REFERENCE scopes, must be taken into account when it is a value of the given reference.
   * If so, then corresponding differences can be detected and merging them means copying
   * references to the element as they are. If not, then those differences are ignored.
   * If the element belongs to either scope, then the value returned by this operation
   * has no impact.
   * Precondition: coverFeature(reference_p)
   * @param value_p a non-null element
   * @param reference_p a non-null reference
   */
  boolean coverOutOfScopeValue(EObject value_p, EReference reference_p);
  
  /**
   * Return whether the given value is significant for the given attribute.
   * If not, then its presence is ignored.
   * @param value_p a non-null attribute value
   * @param attribute_p a non-null attribute
   */
  boolean coverValue(Object value_p, EAttribute attribute_p);
  
}
