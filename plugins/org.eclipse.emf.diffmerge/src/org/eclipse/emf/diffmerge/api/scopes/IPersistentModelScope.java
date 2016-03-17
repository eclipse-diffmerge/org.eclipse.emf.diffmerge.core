/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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

import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A resource-aware model scope: a scope whose contents are, at least for a subset, stored in a
 * known resource.
 * @author Olivier Constant
 */
public interface IPersistentModelScope extends IModelScope {
  
  /**
   * Return the resource holding all or a subset of the model tree
   * @return a potentially null resource
   */
  Resource getHoldingResource();
  
  /**
   * Return the extrinsic ID of the given element, if any.
   * An extrinsic ID is an ID which is bound to the persistence format.
   * @param element_p a non-null element
   * @return a potentially null object
   */
  Object getExtrinsicID(EObject element_p);
  
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
   * Unload from memory the elements and resources that have been loaded as a result
   * of calling load() and exploring the scope. Elements and resources that were already
   * loaded when load() was called are not unloaded.
   * A scope which has been unloaded may not be able to be loaded again.
   * @return the non-null, potentially empty set of impacted resources
   */
  List<Resource> unload();
  
  
  /**
   * An IPersistentModelScope which can be modified.
   */
  public static interface Editable extends IPersistentModelScope {
    /**
     * Save the scope
     * @return whether the operation could be performed
     * @throws Exception an exception indicating that the operation failed in an unexpected way
     */
    boolean save() throws Exception;
    
    /**
     * Set the extrinsic ID of the given element if applicable and if it does
     * not break ID uniqueness
     * @see IPersistentModelScope#getExtrinsicID(EObject)
     * @param element_p a non-null element
     * @param id_p a potentially null extrinsic ID
     * @return whether the ID was actually set
     */
    boolean setExtrinsicID(EObject element_p, Object id_p);
    
    /**
     * Set an input stream for loading. This has no impact after load() has been called.
     * If the stream is not null, it is used to load getHoldingResource().
     * @param stream_p a potentially null stream
     * @throws UnsupportedOperationException if the operation is not supported
     */
    void setStream(InputStream stream_p);
  }
  
}
