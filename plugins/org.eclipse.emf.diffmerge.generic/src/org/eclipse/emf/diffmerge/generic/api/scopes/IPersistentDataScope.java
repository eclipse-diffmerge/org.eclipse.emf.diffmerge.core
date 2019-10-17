/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.api.scopes;

import java.util.List;

import org.eclipse.core.runtime.IStatus;

/**
 * A persistence-aware data scope.
 * 
 * @author Olivier Constant
 */
public interface IPersistentDataScope<E> extends IRawDataScope<E> {
  
  /**
   * Return the root elements of this scope, if any, from a persistence perspective.
   * In the case where the scope is also an IRawTreeDataScope, this method returns
   * the physical roots while method {@link IRawTreeDataScope#getRoots()} returns the
   * logical roots.
   * @return a non-null, potentially empty list
   */
  List<E> getRawRoots();
  
  /**
   * Initialize the scope by loading at least the elements that are required
   * for exploring the scope
   * Postcondition: isLoaded() if no exception is thrown
   * @return a non-null status for the operation
   */
  IStatus load();
  
  /**
   * Return whether the scope is loaded, that is, at least the elements that are required for
   * exploring the scope are loaded
   */
  boolean isLoaded();
  
  /**
   * Unload from memory the elements and persistence units that have been loaded as a
   * result of calling load() and exploring the scope. Elements and persistence units
   * that were already loaded when load() was called are not unloaded.
   * A scope which has been unloaded may not be able to be loaded again.
   * @return a non-null, potentially empty list of persistence units that reflects
   *          the impact of the operation
   */
  List<?> unload();
  
  
  /**
   * An IPersistentDataScope which can be modified.
   */
  interface Editable<E> extends IPersistentDataScope<E> {
    /**
     * Save the scope
     * @return a non-null status for the operation
     */
    IStatus save();
  }
  
}
