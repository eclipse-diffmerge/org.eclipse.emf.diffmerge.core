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
package org.eclipse.emf.diffmerge.ui.gmf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecification;
import org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecificationFactory;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A factory for comparison scopes within GMF models.
 * @author Olivier Constant
 */
public class GMFScopeFactory extends FileScopeSpecificationFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecificationFactory#createScopeSpecificationFromUri(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
   */
  @Override
  protected IScopeSpecification createScopeSpecificationFromUri(URI uri_p, String label_p,
      boolean editable_p) {
    return new FileScopeSpecification(uri_p, label_p, editable_p) {
      /**
       * @see org.eclipse.emf.diffmerge.ui.specification.ext.FileScopeSpecification#createScope(org.eclipse.emf.edit.domain.EditingDomain)
       */
      @Override
      public IEditableModelScope createScope(EditingDomain domain_p) {
        return new GMFScope(getEntrypointResource(domain_p));
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecificationFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(FileScopeSpecificationFactory.class);
  }
  
}
