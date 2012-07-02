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

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecification;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A scope specification based on a resource.
 * @author Olivier Constant
 */
public class ResourceScopeSpecification extends AbstractScopeSpecification {
  
  /**
   * Constructor
   * @param resource_p a non-null resource
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public ResourceScopeSpecification(Resource resource_p, String label_p, boolean editable_p) {
    super(resource_p,
        label_p != null? label_p: getLabelForResource(resource_p), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification#createScope(org.eclipse.emf.edit.domain.EditingDomain)
   */
  public IFeaturedModelScope createScope(EditingDomain domain_p) {
    return new FragmentedModelScope(getEntrypoint());
  }
  
  /**
   * Return a label for the given resource
   * @param resource_p a non-null resource
   * @return a non-null string
   */
  protected static String getLabelForResource(Resource resource_p) {
    String result;
    if (resource_p.getURI() != null)
      result = UIUtil.simplifyURI(resource_p.getURI().toString());
    else
      result = resource_p.toString();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecification#getEntrypoint()
   */
  @Override
  public Resource getEntrypoint() {
    return (Resource)super.getEntrypoint();
  }
  
}
