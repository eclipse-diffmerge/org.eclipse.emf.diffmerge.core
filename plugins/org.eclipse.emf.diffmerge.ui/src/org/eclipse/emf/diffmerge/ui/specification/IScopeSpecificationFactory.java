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
 * A factory for specifications of model comparison scopes.
 * @author Olivier Constant
 */
public interface IScopeSpecificationFactory extends IOverridableFactory {
  
  /**
   * Create and return a scope specification from the given scope entry point, if applicable
   * @param entrypoint_p a potentially null object
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   * @return a scope specification which is not null if isApplicableTo(entrypoint_p)
   */
  IScopeSpecification createScopeSpecification(Object entrypoint_p, String label_p,
      boolean editable_p);
  
  /**
   * Return whether this factory is applicable to the given scope entry point (model element,
   * resource, file...)
   * @param entrypoint_p a non-null object
   */
  boolean isApplicableTo(Object entrypoint_p);
  
}
