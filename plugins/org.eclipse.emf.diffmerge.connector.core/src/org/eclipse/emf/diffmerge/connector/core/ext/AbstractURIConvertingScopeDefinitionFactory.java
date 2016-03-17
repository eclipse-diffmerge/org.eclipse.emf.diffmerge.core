/**
 * Copyright (c) 2015 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetupManager;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * A base scope definition factory for scopes that require a URIConverter.
 * It converts the scope entry point into a URI and looks for another factory that can handle
 * the URI. The scope definition obtained through that factory is wrapped into a dedicated
 * scope definition that alters its behavior through a URIConverter.
 */
public abstract class AbstractURIConvertingScopeDefinitionFactory extends URIScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#createScopeDefinition(java.lang.Object, java.lang.String, boolean)
   */
  @Override
  public IModelScopeDefinition createScopeDefinition(
      Object entrypoint_p, String label_p, boolean editable_p) {
    URIConvertingScopeDefinition result = null;
    try {
      URI uri = convertToURI(entrypoint_p);
      URIConverter uriConverter = getURIConverter(entrypoint_p);
      if (uri != null && uriConverter != null) {
        // Get scope definition factory for refined URI
        ComparisonSetupManager setupManager = EMFDiffMergeUIPlugin.getDefault().getSetupManager();
        List<IModelScopeDefinitionFactory> factories = setupManager.getApplicableModelScopeFactories(uri);
        factories = new ArrayList<IModelScopeDefinitionFactory>(factories);
        factories.remove(this); // The factory must be different from this one (the receiver)
        if (!factories.isEmpty()) {
          IModelScopeDefinitionFactory factory = factories.get(0);
          // Create scope definition and wrap it in a revision scope definition
          String label = (label_p != null) ? label_p : getLabelFor(entrypoint_p);
          IModelScopeDefinition scopeDefinition = factory.createScopeDefinition(uri, label, editable_p);
          if (scopeDefinition != null) {
            result = new URIConvertingScopeDefinition(scopeDefinition, uriConverter, label);
            result.setStream(getStream(entrypoint_p));
          }
        }
      }
    } catch (Exception e) {
      EMFDiffMergeCoreConnectorPlugin.getDefault().logError(e);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinitionFactory#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.singleton(URIScopeDefinitionFactory.class);
  }
  
  /**
   * Return an input stream for the given scope entry point, if applicable and relevant.
   * This is relevant in the case where the returned input stream is not equivalent to
   * the one that would be created via the URI Converter when loading resources.
   * @param entrypoint_p a potentially null object
   * @return a potentially null stream
   * @throws CoreException if the stream could not be retrieved
   */
  protected InputStream getStream(Object entrypoint_p) throws CoreException {
    return null;
  }
  
  /**
   * Return a URI converter for the given scope entry point, if possible.
   * Precondition: isApplicableTo(entrypoint_p)
   * @param entrypoint_p a non-null object
   * @return a potentially null object
   */
  protected abstract URIConverter getURIConverter(Object entrypoint_p);
  
}