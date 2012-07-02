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
package org.eclipse.emf.diffmerge.api;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A policy that alters the construction of differences between model scopes
 * in a comparison.
 * @author Olivier Constant
 */
public interface IDiffPolicy {
  
  /**
   * Return whether the given attribute _values must be considered equal
   * @param value1_p the first non-null attribute value
   * @param value2_p the second non-null attribute value
   * @param attribute_p the optional attribute concerned
   */
  boolean considerEqual(Object value1_p, Object value2_p, EAttribute attribute_p);
  
  /**
   * Return whether the given feature must be considered as ordered
   * @param feature_p a structural feature within this scope
   *        (null stands for model root containment)
   */
  boolean considerOrdered(EStructuralFeature feature_p);
  
  /**
   * Return whether the given attribute or reference must be covered.
   * Precondition: feature_p instanceof EReference =>
   *  !((EReference)feature_p).isContainment && !((EReference)feature_p).isContainer
   * In other terms, this method is never called for containment or container
   * references. This is because those are implicitly determined by the elements
   * present in the model scopes which are being compared.
   * @param feature_p a non-null reference or attribute
   */
  boolean coverFeature(EStructuralFeature feature_p);
  
  /**
   * Return whether the given match must be considered for differences
   * @param match_p a non-null match
   */
  boolean coverMatch(IMatch match_p);
  
  /**
   * Return whether differences in terms of the physical storage of elements
   * must be covered
   */
  boolean coverPhysicalStorage();
  
  /**
   * Return whether the given value is significant for the given attribute.
   * If not, then its presence is ignored.
   * @param value_p a non-null attribute value
   * @param attribute_p a non-null attribute
   */
  boolean coverValue(Object value_p, EAttribute attribute_p);
  
}
