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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

import com.perforce.p4java.core.IChangelistSummary;
import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.AccessException;
import com.perforce.p4java.exception.ConnectionException;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.p4java.exception.RequestException;
import com.perforce.team.core.ConnectionParameters;
import com.perforce.team.core.PerforceTeamProvider;
import com.perforce.team.core.p4java.IP4Connection;
import com.perforce.team.core.p4java.IP4Resource;
import com.perforce.team.core.p4java.P4Workspace;

/**
 * Remote URI Converter for perforce.
 */
public final class PerforceStreamURIConverter extends ExtensibleURIConverterImpl {

	public static final String DEPOT_FILE_REVISION_LABEL = "Depot File";
	private final IResource res;
	private IChangelistSummary changelist = null;
	private String revision = null;

	public PerforceStreamURIConverter(IResource res, IChangelistSummary changelist) {
		super();
		this.res = res;
		this.changelist = changelist;
	}

	public PerforceStreamURIConverter(IResource res, String revision) {
		super();
		this.res = res;
		this.revision = revision;
	}

	/**
	 * Depot file URIConverter
	 * 
	 * @param res
	 */
	public PerforceStreamURIConverter(IResource res) {
		super();
		this.res = res;
	}

	/**
	 * The related model needs to be on the same changeList. if not, get the
	 * most approaching by date.
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI,
	 *      java.util.Map)
	 */
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (res != null && res.exists()) {
			try {
				ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(res).getProjectProperties(false);
				IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
				IP4Resource p4Res = connection.getResource(getModelResource(uri));
				if (p4Res != null) {
					List<IFileSpec> fileSpecList = getFileSpecList(p4Res);
					if (fileSpecList != null) {
						InputStream content = connection.getServer().getFileContents(fileSpecList, false, true);
						if (content != null)
							return content;
					}
					String message = "Cannot load IResource (" + uri.path() + ") from ";
					if (revision != null)
						message = message + "Revision: " + revision;
					else
						message = message + "ChangeList id:" + changelist.getId();
					EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, message));
				} else {
					EMFDiffMergePerforceConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR,
							EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, "Cannot find IResource at: " + uri.path()));
				}
			} catch (CoreException e) {
				throw new IOException(e.getMessage(), e);
			} catch (P4JavaException e) {
				throw new IOException(e.getMessage(), e);
			}
		}
		return super.createInputStream(uri, options);
	}

	private List<IFileSpec> getFileSpecList(IP4Resource p4Res) {
		if (revision == null && changelist == null) {
			return FileSpecBuilder.makeFileSpecList(p4Res.getRemotePath() + "#" + IFileSpec.HEAD_REVISION_STRING);
		}

		else if (revision != null) {
			String revSpec = "#" + IFileSpec.NONE_REVISION_STRING;
			if (revision.startsWith("Remote File"))
				revSpec = revision.substring(revision.indexOf('#'), revision.indexOf(')'));
			else if (revision.startsWith("#"))
				revSpec = revision;
			else if (DEPOT_FILE_REVISION_LABEL.equals(revision))
				revSpec = "#" + IFileSpec.HEAD_REVISION_STRING;
			return FileSpecBuilder.makeFileSpecList(p4Res.getRemotePath() + revSpec);
		} else if (changelist != null) {
			return FileSpecBuilder.makeFileSpecList(p4Res.getRemotePath() + '@' + changelist.getId());
		}
		return null;
	}

	private IResource getModelResource(URI uri) {
		if (uri.isPlatform()) {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
		}
		IPath fullPath = res.getFullPath().removeLastSegments(1).append(uri.toString());
		return ResourcesPlugin.getWorkspace().getRoot().getFile(fullPath);
	}
}