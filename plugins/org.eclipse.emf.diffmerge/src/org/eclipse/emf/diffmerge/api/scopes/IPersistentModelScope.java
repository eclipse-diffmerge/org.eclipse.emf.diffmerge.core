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
package org.eclipse.emf.diffmerge.api.scopes;

import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.scopes.IPersistentDataScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A resource-aware model scope: a scope whose contents are, at least for a subset, stored in a
 * known resource.
 * 
 * @author Olivier Constant
 */
public interface IPersistentModelScope extends IModelScope, IPersistentDataScope<EObject> {
  
  /**
   * Return the resource holding all or a subset of the model tree
   * @return a potentially null resource
   */
  Resource getHoldingResource();
  
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
  interface Editable extends IPersistentModelScope, IPersistentDataScope.Editable<EObject> {
    /**
     * Set an input stream for loading. This has no impact after load() has been called.
     * If the stream is not null, it is used to load getHoldingResource().
     * @param stream_p a potentially null stream
     * @throws UnsupportedOperationException if the operation is not supported
     */
    void setStream(InputStream stream_p);
  }
  
}
