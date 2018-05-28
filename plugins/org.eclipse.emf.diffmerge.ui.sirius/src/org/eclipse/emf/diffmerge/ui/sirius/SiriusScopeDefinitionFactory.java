/**
 * <copyright>
 * 
 * Copyright (c) 2006-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.sirius;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.sirius.SiriusScope;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A factory for comparison scopes within Sirius models.
 */
public class SiriusScopeDefinitionFactory extends GMFScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri_p, String label_p,
      boolean editable_p) {
    return new URIScopeDefinition(uri_p, label_p, editable_p) {
      /**
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnEditingDomain(org.eclipse.emf.edit.domain.EditingDomain)
       */
      @Override
      protected IEditableModelScope createScopeOnEditingDomain(EditingDomain editingDomain) {
        return new SiriusScope(getEntrypoint(), editingDomain, !isEditable());
      }
      /**
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition#createScopeOnResourceSet(org.eclipse.emf.ecore.resource.ResourceSet)
       */
      @Override
      protected IEditableModelScope createScopeOnResourceSet(ResourceSet resourceSet_p) {
        return new SiriusScope(getEntrypoint(), resourceSet_p, !isEditable());
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(GMFScopeDefinitionFactory.class);
  }
  
}
