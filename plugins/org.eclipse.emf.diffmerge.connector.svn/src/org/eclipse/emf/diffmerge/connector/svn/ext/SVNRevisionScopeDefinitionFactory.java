/*******************************************************************************
 * Copyright (c) 2015-2016 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.svn.ext;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.svn.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.resource.ILocalResource;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.synchronize.variant.BaseFileVariant;


/**
 * A scope definition factory for file revisions in SVN history.
 * It is used in the Team Synchronizing perspective.
 */
public class SVNRevisionScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof BaseFileVariant) {
      result = String.format(
          Messages.SVNRevisionScopeDefinitionFactory_LabelRevision, variant.getContentIdentifier());
    } else {
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p) throws CoreException {
    URIConverter result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof BaseFileVariant) {
      ILocalResource res =((BaseFileVariant)variant).getResource();
      IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
      result = new SVNHistoryURIConverter(location, SVNRevision.fromString(variant.getContentIdentifier()));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision_p) throws CoreException {
    URI result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof BaseFileVariant) {
      ILocalResource res = ((BaseFileVariant)variant).getResource();
      IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
      result = URI.createURI(location.getRepositoryRootUrl() +
          ((BaseFileVariant)variant).getResource().getResource().getFullPath(), true);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return getVariant(revision_p) instanceof BaseFileVariant;
  }
  
}
