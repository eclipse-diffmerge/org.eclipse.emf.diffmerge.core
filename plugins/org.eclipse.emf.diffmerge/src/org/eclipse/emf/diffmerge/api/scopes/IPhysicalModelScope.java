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

import org.eclipse.emf.ecore.resource.Resource;


/**
 * A model scope whose contents are, at least for a subset, stored in a resource.
 * @author Olivier Constant
 */
public interface IPhysicalModelScope extends IModelScope {
  
  /**
   * Return the resource holding all or a subset of the model tree
   * @return a potentially null resource
   */
  Resource getHoldingResource();
  
  /**
   * Initialize the scope by loading at least the elements that are required
   * for exploring the scope
   * @return whether the operation could performed
   * @throws Exception an exception indicating that the operation failed in an unexpected way
   */
  boolean load() throws Exception;
  
  /**
   * Save the scope
   * @return whether the operation could performed
   * @throws Exception an exception indicating that the operation failed in an unexpected way
   */
  boolean save() throws Exception;
  
}
