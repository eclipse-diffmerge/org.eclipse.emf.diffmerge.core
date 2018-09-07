/*******************************************************************************
 * Copyright (c) 2015-2018 Intel Corporation and others.
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.svn.EMFDiffMergeSVNConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.svn.Messages;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.AbstractGetFileContentOperation;
import org.eclipse.team.svn.core.operation.IActionOperation;
import org.eclipse.team.svn.core.operation.local.GetLocalFileContentOperation;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;


/**
 * A URI Converter for file revisions in the SVN history.
 */
public class SVNHistoryURIConverter extends ExtensibleURIConverterImpl {
  
  /** The scheme for SVN repo locations */
  protected static final String SVN_SCHEME = "svn"; //$NON-NLS-1$
  
  /** The non-null repository location */
  protected final IRepositoryLocation _repoLocation;
  
  /** The non-null SVN revision */
  protected final SVNRevision _revision;
  
  
  /**
   * Constructor
   * @param repositoryLocation_p a non-null repository location
   * @param revision_p a non-null SVN revision
   */
  public SVNHistoryURIConverter(IRepositoryLocation repositoryLocation_p, SVNRevision revision_p) {
    _repoLocation = repositoryLocation_p;
    _revision = revision_p;
  }
  
  /**
   * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI, java.util.Map)
   */
  @Override
  public InputStream createInputStream(URI uri_p, Map<?, ?> options_p) throws IOException {
    // Strip the URI that contains the repo location
    String scheme = uri_p.scheme();
    if (scheme != null && scheme.startsWith(SVN_SCHEME)) {
      // Try to locate the file locally
      String basepath = _repoLocation.getRepositoryRootUrl();
      List<String> basePathSegmentsList = URI.createURI(basepath).segmentsList();
      List<String> newPathList = new ArrayList<String>(uri_p.segmentsList());
      newPathList.removeAll(basePathSegmentsList);
      IPath newPath = new Path(
          URI.createHierarchicalURI(newPathList.toArray(new String[] {}), uri_p.query(),
              uri_p.fragment()).devicePath());
      IResource target = ResourcesPlugin.getWorkspace().getRoot().getFile(newPath);
      if (target.exists()) {
        AbstractGetFileContentOperation op;
        if (_revision.equals(SVNRevision.BASE)) {
          op = new GetLocalFileContentOperation(target, SVNRevision.BASE.getKind());
        } else {
          IRepositoryResource res = SVNRemoteStorage.instance().asRepositoryResource(target);
          // Get the remote contents associated with the revision ID
          res.setSelectedRevision(_revision);
          op=new GetFileContentOperation(res);
        }
        op.run(new NullProgressMonitor());
        if (op.getExecutionState() != IActionOperation.OK)
          EMFDiffMergeSVNConnectorPlugin.getDefault().getLog().log(
              new Status(IStatus.ERROR, EMFDiffMergeSVNConnectorPlugin.getDefault().getPluginId(),
                  String.format(Messages.SVNHistoryURIConverter_CannotLoad, op.getStatus().getMessage())));
        return op.getContent();
      }
      EMFDiffMergeSVNConnectorPlugin.getDefault().getLog().log(
          new Status(IStatus.ERROR, EMFDiffMergeSVNConnectorPlugin.getDefault().getPluginId(),
              String.format(Messages.SVNHistoryURIConverter_CannotLoad_Located, uri_p)));
    }
    return super.createInputStream(uri_p, options_p);
  }
  
}