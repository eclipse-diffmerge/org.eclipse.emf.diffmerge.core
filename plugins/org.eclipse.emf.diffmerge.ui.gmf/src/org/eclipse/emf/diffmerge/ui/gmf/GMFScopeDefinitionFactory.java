/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.gmf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A factory for model scopes within GMF models.
 * @author Olivier Constant
 */
public class GMFScopeDefinitionFactory extends URIScopeDefinitionFactory {
  
  /**
   * A scope definition for GMF.
   */
  public static class GMFScopeDefinition extends URIScopeDefinition {
    /**
     * Constructor
     * @param uri_p a non-null URI
     * @param label_p an optional label
     * @param editable_p whether the scope can be edited
     */
    public GMFScopeDefinition(URI uri_p, String label_p, boolean editable_p) {
      super(uri_p, label_p, editable_p);
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnEditingDomain(org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    protected IEditableModelScope createScopeOnEditingDomain(EditingDomain editingDomain_p) {
      return new GMFScope(getEntrypoint(), editingDomain_p, !isEditable());
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnResourceSet(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    @Override
    protected IEditableModelScope createScopeOnResourceSet(ResourceSet resourceSet_p) {
      return new GMFScope(getEntrypoint(), resourceSet_p, !isEditable());
    }
  }
  
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri_p, String label_p,
      boolean editable_p) {
    return new GMFScopeDefinition(uri_p, label_p, editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinitionFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(URIScopeDefinitionFactory.class);
  }
  
}
