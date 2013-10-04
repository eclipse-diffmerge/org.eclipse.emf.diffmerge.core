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

import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * A model scope definition relates an object selected by the user to a model scope.
 * @author Olivier Constant
 */
public interface IModelScopeDefinition {
  
  /**
   * Create and return a scope that corresponds to this scope definition
   * @param context_p an optional resource set for the case where resource creation is needed
   * @return a non-null scope
   */
  IEditableModelScope createScope(ResourceSet context_p);
  
  /**
   * Return the object that specifies the scope (model element, file, resource...)
   * @return a non-null object
   */
  Object getEntrypoint();
  
  /**
   * Return a label that identifies the scope
   * @return a non-null label
   */
  String getLabel();
  
  /**
   * Return whether the content of the scope can be modified
   */
  boolean isEditable();
  
  /**
   * Return whether the editable property can be changed
   */
  boolean isEditableSettable();
  
  /**
   * Set whether the content of the scope is allowed to be modified
   */
  void setEditable(boolean editable_p);
  
}
