/*********************************************************************
 * Copyright (c) 2015-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.setup;

import org.eclipse.compare.ITypedElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Image;


/**
 * A wrapping of model scopes into comparable entities.
 * @author Olivier Constant
 */
public class ModelScopeTypedElement implements ITypedElement {
  
  /** The non-null scope being wrapped */
  private final IModelScope _scope;
  
  
  /**
   * Constructor
   * @param scope_p a non-null model scope
   */
  public ModelScopeTypedElement(IFeaturedModelScope scope_p) {
    _scope = scope_p;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getImage()
   */
  public Image getImage() {
    Object originator = _scope.getOriginator();
    Image result = DiffMergeLabelProvider.getInstance().getImage(originator);
    return result;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getName()
   */
  public String getName() {
    Object originator = _scope.getOriginator();
    String result = DiffMergeLabelProvider.getInstance().getText(originator);
    return result;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getType()
   */
  public String getType() {
    String result = ITypedElement.UNKNOWN_TYPE;
    if (_scope instanceof IPersistentModelScope) {
      Resource resource = ((IPersistentModelScope)_scope).getHoldingResource();
      if (resource != null) {
        URI uri = resource.getURI();
        String extension = uri.fileExtension();
        if (extension != null)
          result = extension;
      }
    }
    return result;
  }
  
}
