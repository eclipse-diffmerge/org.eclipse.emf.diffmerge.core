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
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A scope specification based on a file.
 * @author Olivier Constant
 */
public class FileScopeSpecification extends AbstractScopeSpecification {
  
  /**
   * Constructor
   * @param uri_p a non-null uri
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public FileScopeSpecification(URI uri_p, String label_p, boolean editable_p) {
    super(uri_p,
        label_p != null? label_p: uri_p.toString(), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification#createScope(org.eclipse.emf.edit.domain.EditingDomain)
   */
  public IEditableModelScope createScope(EditingDomain domain_p) {
    Resource loadedResource = getEntrypointResource(domain_p);
    return new FragmentedModelScope(loadedResource);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecification#getEntrypoint()
   */
  @Override
  public URI getEntrypoint() {
    return (URI)super.getEntrypoint();
  }
  
  /**
   * Get or create the resource corresponding to the entrypoint in the given editing domain
   * @param domain_p a non-null editing domain to which the resulting resource must belong
   * @return a non-null resource
   */
  protected Resource getEntrypointResource(EditingDomain domain_p) {
    Resource result = domain_p.getResourceSet().getResource(getEntrypoint(), false);
    if (result == null)
      result = domain_p.getResourceSet().createResource(getEntrypoint());
    return result;
  }
  
}
