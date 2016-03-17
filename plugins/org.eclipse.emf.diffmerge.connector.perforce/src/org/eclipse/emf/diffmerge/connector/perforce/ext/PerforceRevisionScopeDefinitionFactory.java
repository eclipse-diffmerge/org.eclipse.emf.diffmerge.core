/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.perforce.ext;

import static org.eclipse.emf.diffmerge.connector.perforce.ext.PerforceHistoryURIConverter.PERFORCE_HASH;

import java.util.List;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.perforce.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.perforce.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;

import com.perforce.p4java.core.IChangelistSummary;
import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.p4java.impl.mapbased.server.Server;
import com.perforce.p4java.option.server.GetChangelistsOptions;
import com.perforce.team.core.ConnectionParameters;
import com.perforce.team.core.PerforceTeamProvider;
import com.perforce.team.core.p4java.IP4Connection;
import com.perforce.team.core.p4java.P4Workspace;
import com.perforce.team.core.p4java.synchronize.PerforceSyncFile;


/**
 * A scope definition factory for file revisions in Perforce history.
 */
public class PerforceRevisionScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof PerforceSyncFile)
      result = String.format(Messages.PerforceRevisionScopeDefinitionFactory_RemoteLabel,
          variant.getName(), variant.getContentIdentifier());
    else
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p)
      throws CoreException {
    URIConverter result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof PerforceSyncFile) {
      try {
        String localPath = ((PerforceSyncFile) variant).getFile().getLocalPath();
        IResource res = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(localPath));
        ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(res).getProjectProperties(false);
        IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
        String revisionSpec = variant.getContentIdentifier();
        if (!revisionSpec.startsWith(PERFORCE_HASH) && revisionSpec.lastIndexOf(PERFORCE_HASH) != -1)
          revisionSpec = revisionSpec.substring(revisionSpec.lastIndexOf(PERFORCE_HASH), revisionSpec.length());
        List<IFileSpec> fileSpecList = FileSpecBuilder
            .makeFileSpecList(((PerforceSyncFile) variant).getFile().getRemotePath() + revisionSpec);
        GetChangelistsOptions opts = new GetChangelistsOptions();
        opts.setMaxMostRecent(1);
        if (connection.getServer() instanceof Server
            && !((Server) connection.getServer()).getChangelists(fileSpecList, opts).isEmpty()) {
          IChangelistSummary changeList = ((Server) connection.getServer()).getChangelists(fileSpecList, opts)
              .get(0);
          result = new PerforceHistoryURIConverter(res, changeList);
        } else {
          EMFDiffMergePerforceConnectorPlugin.getDefault().getLog()
          .log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(),
              Messages.PerforceRevisionScopeDefinitionFactory_CannotLoadChangelist + res));
        }
      } catch (P4JavaException e) {
        EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision_p)
      throws CoreException {
    URI result = null;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof PerforceSyncFile)
      result = URI.createURI(((PerforceSyncFile) variant).getFile().getRemotePath());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return getVariant(revision_p) instanceof PerforceSyncFile;
  }
  
}
