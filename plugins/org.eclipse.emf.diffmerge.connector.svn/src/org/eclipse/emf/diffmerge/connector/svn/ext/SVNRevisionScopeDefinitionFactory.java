/*******************************************************************************
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
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.svn.ext;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.svn.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.resource.ILocalResource;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.synchronize.variant.BaseFileVariant;
import org.eclipse.team.svn.core.synchronize.variant.RemoteFileVariant;


/**
 * A scope definition factory for file revisions in SVN history.
 * It is used in the Team Synchronizing perspective for the ancestor and remote scopes.
 */
public class SVNRevisionScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant != null) {
      // Ancestor or remote
      result = String.format(
          Messages.SVNRevisionScopeDefinitionFactory_LabelRevision, variant.getContentIdentifier());
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
      // Ancestor
      ILocalResource res =((BaseFileVariant)variant).getResource();
      IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
      result = new SVNHistoryURIConverter(location, SVNRevision.fromString(variant.getContentIdentifier()));
    } else {
      // Remote
      result = new ExtensibleURIConverterImpl();
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
      // Ancestor
      ILocalResource res = ((BaseFileVariant)variant).getResource();
      IRepositoryLocation location = SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
      result = URI.createURI(location.getRepositoryRootUrl() +
          ((BaseFileVariant)variant).getResource().getResource().getFullPath(), true);
    } else {
      // Remote
      IStorage storage = getStorage(revision_p);
      if (storage != null)
        result = toFileURI(storage.getFullPath().toString());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    IResourceVariant variant = getVariant(revision_p);
    // Ancestor and remote
    return variant instanceof BaseFileVariant || variant instanceof RemoteFileVariant;
  }
  
}
