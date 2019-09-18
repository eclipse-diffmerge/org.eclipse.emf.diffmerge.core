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
package org.eclipse.emf.diffmerge.ui.specification;

import org.eclipse.emf.diffmerge.api.config.IComparisonConfiguration;

/**
 * A factory for comparison methods.
 * @author Olivier Constant
 */
public interface IComparisonMethodFactory extends IOverridableFactory {
  
  /**
   * Create and return a comparison method for the given scope definitions
   * @param leftScopeSpec_p a non-null scope definition
   * @param rightScopeSpec_p a non-null scope definition
   * @param ancestorScopeSpec_p an optional scope definition
   * @return a non-null comparison method if this factory is applicable to the entry points
   *         of the given scope definitions
   */
  IComparisonMethod createComparisonMethod(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p, IModelScopeDefinition ancestorScopeSpec_p);
  
  /**
   * Create and return a comparison configuration that is common to all
   * comparison methods this factory can create
   * @return a non-null configuration
   */
  IComparisonConfiguration createComparisonConfiguration();
  
  /**
   * Return a unique ID for this factory
   * @return a non-null string
   */
  String getID();
  
  /**
   * Return whether this factory is applicable to the given scope definitions.
   * The left/right/ancestor distinction is not assumed significant.
   * @param leftScopeSpec_p a non-null scope definition
   * @param rightScopeSpec_p a non-null scope definition
   * @param ancestorScopeSpec_p an optional scope definition
   */
  boolean isApplicableTo(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p, IModelScopeDefinition ancestorScopeSpec_p);
  
}
