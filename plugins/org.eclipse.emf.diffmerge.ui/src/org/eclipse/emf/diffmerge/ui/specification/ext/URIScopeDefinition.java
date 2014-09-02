/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A scope definition based on a URI.
 * @author Olivier Constant
 */
public class URIScopeDefinition extends AbstractScopeDefinition {
  
  /**
   * Constructor
   * @param uri_p a non-null uri
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public URIScopeDefinition(URI uri_p, String label_p, boolean editable_p) {
    super(uri_p, (label_p != null)? label_p: UIUtil.simplifyURI(uri_p), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
   */
  public IEditableModelScope createScope(Object context_p) {
    IEditableModelScope result;
    if (context_p instanceof EditingDomain)
      result = createScopeOnEditingDomain((EditingDomain)context_p);
    else if (context_p instanceof ResourceSet)
      result = createScopeOnResourceSet((ResourceSet)context_p);
    else
      result = null;
    return result;
  }
  
  /**
   * Create and return a scope that corresponds to this scope definition within the given editing domain
   * @param editingDomain_p a non-null editing domain
   * @return a non-null scope
   */
  protected IEditableModelScope createScopeOnEditingDomain(EditingDomain editingDomain_p) {
    return new FragmentedModelScope(getEntrypoint(), editingDomain_p, !isEditable());
  }
  
  /**
   * Create and return a scope that corresponds to this scope definition within the given resource set
   * @param resourceSet_p a non-null resource set
   * @return a non-null scope
   */
  protected IEditableModelScope createScopeOnResourceSet(ResourceSet resourceSet_p) {
    return new FragmentedModelScope(getEntrypoint(), resourceSet_p, !isEditable());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition#getEntrypoint()
   */
  @Override
  public URI getEntrypoint() {
    return (URI)super.getEntrypoint();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition#getShortLabel()
   */
  @Override
  public String getShortLabel() {
    String result = super.getShortLabel();
    URI uri = getEntrypoint();
    if (uri.isFile() || uri.isPlatformResource())
      result = uri.trimFileExtension().lastSegment();
    result = UIUtil.simplifyURI(result);
    return result;
  }
  
}
