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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * A scope definition based on a file.
 * @author Olivier Constant
 */
public class FileScopeDefinition extends AbstractScopeDefinition {
  
  /**
   * Constructor
   * @param uri_p a non-null uri
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public FileScopeDefinition(URI uri_p, String label_p, boolean editable_p) {
    super(uri_p,
        label_p != null? label_p: uri_p.toString(), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(org.eclipse.emf.ecore.resource.ResourceSet)
   */
  public IEditableModelScope createScope(ResourceSet resourceSet_p) {
    Resource loadedResource = getEntrypointResource(resourceSet_p);
    return new FragmentedModelScope(loadedResource);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition#getEntrypoint()
   */
  @Override
  public URI getEntrypoint() {
    return (URI)super.getEntrypoint();
  }
  
  /**
   * Get or create the resource corresponding to the entrypoint in the given resource set
   * @param resourceSet_p an optional resource set to which the resource must belong
   * @return a non-null resource
   */
  protected Resource getEntrypointResource(ResourceSet resourceSet_p) {
    ResourceSet nonNullRS = (resourceSet_p != null)? resourceSet_p:
      new ResourceSetImpl();
    Resource result = nonNullRS.getResource(getEntrypoint(), false);
    if (result == null)
      result = nonNullRS.createResource(getEntrypoint());
    return result;
  }
  
}
