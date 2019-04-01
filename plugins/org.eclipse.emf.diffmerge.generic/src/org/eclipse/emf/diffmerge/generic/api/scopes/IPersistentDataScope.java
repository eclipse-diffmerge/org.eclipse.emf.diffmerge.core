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

/**
 * A persistence-aware data scope.
 * 
 * @author Olivier Constant
 */
public interface IPersistentDataScope<E> extends IRawDataScope<E> {
  
  /**
   * Initialize the scope by loading at least the elements that are required
   * for exploring the scope
   * Postcondition: isLoaded() if no exception is thrown
   * @return whether the operation could be performed
   * @throws Exception an exception indicating that the operation failed in an unexpected way
   */
  boolean load() throws Exception;
  
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
   * @return an optional object that describes the result of the operation as a list of
   *          persistence units
   */
  List<?> unload();
  
  
  /**
   * An IPersistentDataScope which can be modified.
   */
  interface Editable<E> extends IPersistentDataScope<E> {
    /**
     * Save the scope
     * @return whether the operation could be performed
     * @throws Exception an exception indicating that the operation failed in an unexpected way
     */
    boolean save() throws Exception;
  }
  
}
