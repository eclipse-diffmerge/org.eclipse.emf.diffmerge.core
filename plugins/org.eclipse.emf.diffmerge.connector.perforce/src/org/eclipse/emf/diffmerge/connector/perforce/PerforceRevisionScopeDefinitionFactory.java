/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.perforce;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.FileRevisionScopeDefinitionFactory;
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

public class PerforceRevisionScopeDefinitionFactory extends FileRevisionScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>> singleton(FileRevisionScopeDefinitionFactory.class);
	}

	@Override
	protected IFileRevision getFileRevision(ITypedElement entrypoint) {
		IFileRevision revision = super.getFileRevision(entrypoint);
		if (getVariant(revision) instanceof PerforceSyncFile) {
			return revision;
		}
		return null;
	}

	@Override
	protected URI getUri(IFileRevision revision) throws CoreException {
		// remote Perforce
		IResourceVariant variant = getVariant(revision);
		if (variant instanceof PerforceSyncFile) {
			return URI.createURI(((PerforceSyncFile) variant).getFile().getRemotePath());
		}
		return super.getUri(revision);
	}

	@Override
	protected IStorage getStorage(IFileRevision revision) throws CoreException {
		// remote Perforce
		IResourceVariant variant = getVariant(revision);
		if (variant != null) {
			return variant.getStorage(new NullProgressMonitor());
		} else
			return super.getStorage(revision);
	}

	@Override
	protected URIConverter getURIConverter(IFileRevision revision) throws CoreException {
		// remote Perforce
		IResourceVariant variant = getVariant(revision);
		if (variant instanceof PerforceSyncFile) {
			try {
				String localPath = ((PerforceSyncFile) variant).getFile().getLocalPath();
				IResource res = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(localPath));
				ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(res).getProjectProperties(false);
				IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
				String revisionSpec = variant.getContentIdentifier();
				if (!revisionSpec.startsWith("#") && revisionSpec.lastIndexOf("#") != -1) {
					revisionSpec = revisionSpec.substring(revisionSpec.lastIndexOf("#"), revisionSpec.length());
				}
				List<IFileSpec> fileSpecList = FileSpecBuilder
						.makeFileSpecList(((PerforceSyncFile) variant).getFile().getRemotePath() + revisionSpec);
				GetChangelistsOptions opts = new GetChangelistsOptions();
				opts.setMaxMostRecent(1);
				if (connection.getServer() instanceof Server
						&& !((Server) connection.getServer()).getChangelists(fileSpecList, opts).isEmpty()) {
					IChangelistSummary changeList = ((Server) connection.getServer()).getChangelists(fileSpecList, opts)
							.get(0);
					return new PerforceStreamURIConverter(res, changeList);
				} else {
					EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID,
									"Cannot load changeList for resource: " + res));
				}
			} catch (P4JavaException e) {
				EMFDiffMergePerforceConnectorPlugin.getInstance().getLog().log(
						new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
			}
		}
		return super.getURIConverter(revision);
	}

	@Override
	protected String getLabelFor(Object entrypoint) {
		IFileRevision revision = getFileRevision((ITypedElement) entrypoint);
		if (revision != null) {
			IResourceVariant variant = getVariant(revision);
			if (variant instanceof PerforceSyncFile) {
				return variant.getName() + " [Remote File " + variant.getContentIdentifier() + "]";
			}
		}
		return super.getLabelFor(entrypoint);
	}

}
