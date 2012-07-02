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
package org.eclipse.emf.diffmerge.ui.specification;


/**
 * A factory for specifications of visual comparisons.
 * @author Olivier Constant
 */
public interface IComparisonSpecificationFactory extends IOverridableFactory {
  
  /**
   * Create and return a comparison specification for the given scope specifications
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   * @return a non-null comparison specification if this factory is applicable to the entry points
   *         of the given scope specifications
   */
  IComparisonSpecification createComparisonSpecification(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p);
  
  /**
   * Return whether this factory is applicable to the given scope specifications
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  boolean isApplicableTo(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p);
  
}
