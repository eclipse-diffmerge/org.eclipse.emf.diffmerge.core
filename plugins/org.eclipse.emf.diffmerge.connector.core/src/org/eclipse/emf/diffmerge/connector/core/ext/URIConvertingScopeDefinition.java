/**
 * Copyright (c) 2015-2019 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import java.io.InputStream;

import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.impl.scopes.AbstractDataScope;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ITimestampProvider;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * A model scope definition that wraps a regular model scope definition and alters
 * its behavior through a given URI Converter.
 */
public class URIConvertingScopeDefinition implements IModelScopeDefinition,
ITimestampProvider {
  
  /** The non-null wrapped model scope definition */
  protected final IModelScopeDefinition _wrapped;
  
  /** Whether the scope can be edited */
  private boolean _editable;
  
  /** The non-null URI converter */
  protected final URIConverter _uriConverter;
  
  /** The optional input stream for loading the scope */
  protected InputStream _loadingStream;
  
  
  /**
   * Constructor
   * @param wrapped_p a non-null model scope definition wrapped by this one
   * @param uriConverter_p a non-null URI converter for the resource set of the scope
   * @param isEditable_p whether the scope can be edited
   */
  public URIConvertingScopeDefinition(IModelScopeDefinition wrapped_p, URIConverter uriConverter_p,
      boolean isEditable_p) {
    _wrapped = wrapped_p;
    _uriConverter = uriConverter_p;
    _editable = isEditable_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
   */
  public IEditableTreeDataScope<?> createScope(Object context_p) {
    IEditableTreeDataScope<?> result;
    if (_editable) {
      result = _wrapped.createScope(context_p); // Use the same context for editable contents
    } else {
      result = _wrapped.createScope(null); // Ignore context for a fresh resource set
    }
    if (result instanceof AbstractDataScope<?>) {
      ((AbstractDataScope<?>)result).setOriginator(_wrapped.getLabel());
    }
    if (result instanceof IPersistentModelScope) {
      IPersistentModelScope casted = (IPersistentModelScope)result;
      if (_loadingStream != null && casted instanceof IPersistentModelScope.Editable) {
        ((IPersistentModelScope.Editable)casted).setStream(_loadingStream);
      }
      Resource holdingResource = casted.getHoldingResource();
      if (holdingResource != null) {
        ResourceSet rs = holdingResource.getResourceSet();
        if (rs != null)
          rs.setURIConverter(_uriConverter);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getEntrypoint()
   */
  public Object getEntrypoint() {
    return _wrapped.getEntrypoint();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getLabel()
   */
  public String getLabel() {
    return _wrapped.getLabel();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getShortLabel()
   */
  public String getShortLabel() {
    return _wrapped.getShortLabel();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ITimestampProvider#getTimestamp()
   */
  public long getTimestamp() {
    long result = -1;
    if (_uriConverter instanceof ITimestampProvider)
      result = ((ITimestampProvider)_uriConverter).getTimestamp();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditable()
   */
  public boolean isEditable() {
    return _editable;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditableSettable()
   */
  public boolean isEditableSettable() {
    return false; // Imposed by SCM constraints
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#setEditable(boolean)
   */
  public void setEditable(boolean editable_p) {
    _editable = editable_p;
  }
  
  /**
   * Set an input stream for scope loading
   * @param stream_p a potentially null stream
   */
  public void setStream(InputStream stream_p) {
    _loadingStream = stream_p;
  }
  
}
