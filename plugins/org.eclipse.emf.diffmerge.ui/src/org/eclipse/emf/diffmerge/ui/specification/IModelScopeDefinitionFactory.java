/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
 * A factory for model scope definitions.
 * @author Olivier Constant
 */
public interface IModelScopeDefinitionFactory extends IOverridableFactory {
  
  /**
   * Create and return a scope definition from the given scope entry point, if applicable
   * @param entrypoint_p a potentially null object
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   * @return a scope definition which is not null if isApplicableTo(entrypoint_p)
   */
  IModelScopeDefinition createScopeDefinition(Object entrypoint_p, String label_p,
      boolean editable_p);
  
  /**
   * Return whether this factory is applicable to the given scope entry point (model element,
   * resource, file...)
   * @param entrypoint_p a non-null object
   */
  boolean isApplicableTo(Object entrypoint_p);
  
}
