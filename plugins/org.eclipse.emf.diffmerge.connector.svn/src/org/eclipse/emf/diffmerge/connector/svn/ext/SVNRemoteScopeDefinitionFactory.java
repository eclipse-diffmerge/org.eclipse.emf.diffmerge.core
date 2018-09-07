/*******************************************************************************
 * Copyright (c) 2015-2017 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.svn.ext;

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.svn.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.ui.compare.ResourceCompareInput.ResourceElement;


/**
 * A scope definition factory for remote SVN files.
 */
public class SVNRemoteScopeDefinitionFactory extends AbstractURIConvertingScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#convertToURI(java.lang.Object)
   */
  @Override
  protected URI convertToURI(Object entrypoint_p) {
    URI result;
    if (entrypoint_p instanceof ResourceElement)
      result = getResourceElementURI((ResourceElement)entrypoint_p);
    else
      result = super.convertToURI(entrypoint_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#getLabelFor(java.lang.Object)
   */
  @Override
  protected String getLabelFor(Object entrypoint_p) {
    final ResourceElement res = (ResourceElement) entrypoint_p;
    SVNRevision rev = res.getRepositoryResource().getSelectedRevision();
    String result = null;
    try {
      String url = res.getRepositoryResource().getUrl();
      if (SVNRevision.BASE.equals(rev))
        result = String.format(Messages.SVNRemoteScopeDefinitionFactory_LabelBase, url);
      else if (SVNRevision.HEAD.equals(rev))
        result = String.format(Messages.SVNRemoteScopeDefinitionFactory_LabelHead, url);
      else
        result = String.format(Messages.SVNRemoteScopeDefinitionFactory_LabelRev,
            url, Long.toString(res.getRepositoryResource().getRevision())); 
    } catch (SVNConnectorException e) {
      EMFDiffMergeCoreConnectorPlugin.getDefault().logError(e);
    }
    if (result == null)
      result = res.getName();
    return result;
  }
  
  /**
   * Return a URI for the given ResourceElement
   * @param resourceElement_p a non-null object
   * @return a non-null URI
   */
  protected URI getResourceElementURI(ResourceElement resourceElement_p) {
    return URI.createURI(resourceElement_p.getRepositoryResource().getUrl());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getStream(java.lang.Object)
   */
  @Override
  protected InputStream getStream(Object entrypoint_p) throws CoreException {
    InputStream result;
    if (entrypoint_p instanceof ResourceElement)
      result = ((ResourceElement)entrypoint_p).getContents();
    else
      result = super.getStream(entrypoint_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getURIConverter(java.lang.Object)
   */
  @Override
  protected URIConverter getURIConverter(Object entrypoint_p) {
    URIConverter result = null;
    if (entrypoint_p instanceof ResourceElement) {
      ResourceElement res = (ResourceElement)entrypoint_p;
      result = new SVNHistoryURIConverter(res.getRepositoryResource().getRepositoryLocation(),
          res.getRepositoryResource().getSelectedRevision());
    }
    return result;
  }
	
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  @Override
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof ResourceElement && !((ResourceElement)entrypoint_p).isEditable();
  }
  
}
