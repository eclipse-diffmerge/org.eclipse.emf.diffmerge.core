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

import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;


/**
 * A model scope definition relates an object selected by the user to a model scope.
 * @author Olivier Constant
 */
public interface IModelScopeDefinition {
  
  /**
   * Create and return a scope that corresponds to this scope definition
   * @param context_p an optional context for the scope, for example a resource set or editing domain
   *          for the case where resource creation is needed
   * @return a scope which is null if operation failed
   */
  IEditableModelScope createScope(Object context_p);
  
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
   * Return a short label that identifies the scope
   * @return a non-null label
   */
  String getShortLabel();
  
  /**
   * Return whether the content of the scope can be modified
   */
  boolean isEditable();
  
  /**
   * Return whether the editable property can be changed by the end-user
   */
  boolean isEditableSettable();
  
  /**
   * Set whether the content of the scope is allowed to be modified
   */
  void setEditable(boolean editable_p);
  
}
