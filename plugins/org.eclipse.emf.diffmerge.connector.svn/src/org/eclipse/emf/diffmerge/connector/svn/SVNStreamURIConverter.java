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
package org.eclipse.emf.diffmerge.connector.svn;

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
import org.eclipse.emf.diffmerge.connector.EMFDiffmergeSVNConnectorPlugin;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.AbstractGetFileContentOperation;
import org.eclipse.team.svn.core.operation.IActionOperation;
import org.eclipse.team.svn.core.operation.local.GetLocalFileContentOperation;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;

public final class SVNStreamURIConverter extends ExtensibleURIConverterImpl {

	private final IRepositoryLocation repoLocation;
	private final SVNRevision revision;

	public SVNStreamURIConverter(IRepositoryLocation repositoryLocation, SVNRevision revision) {
		super();
		this.repoLocation=repositoryLocation;
		this.revision=revision;
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		// strip the uri that contains the repolocation.
		if ("svn".equals(uri.scheme())) {
			//try to locate the file locally
			String basepath=repoLocation.getRepositoryRootUrl();
			List<String> basePathSegmentsList=URI.createURI(basepath).segmentsList();
			List<String> newPathList=new ArrayList<String>(uri.segmentsList());
			newPathList.removeAll(basePathSegmentsList);
			IPath newPath=new Path(URI.createHierarchicalURI(newPathList.toArray(new String[] {}), uri.query(), uri.fragment()).devicePath());
			IResource target=ResourcesPlugin.getWorkspace().getRoot().getFile(newPath);
			if (target.exists()) {
				AbstractGetFileContentOperation op;
				if (revision.equals(SVNRevision.BASE)) {
					op=new GetLocalFileContentOperation(target, SVNRevision.BASE.getKind());
				}
				else {
					IRepositoryResource res=SVNRemoteStorage.instance().asRepositoryResource(target);
					// gets the remote contents associated with the revision id
					res.setSelectedRevision(revision);
					op=new GetFileContentOperation(res);
				}
				op.run(new NullProgressMonitor());
				if (op.getExecutionState() != IActionOperation.OK)
					EMFDiffmergeSVNConnectorPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, EMFDiffmergeSVNConnectorPlugin.PLUGIN_ID, "Cannot load resource: " + op.getStatus().getMessage()));
				return op.getContent();
			}
			else {
				EMFDiffmergeSVNConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffmergeSVNConnectorPlugin.PLUGIN_ID, "Cannot load resource at: " + uri));
			}
		}
		return super.createInputStream(uri, options);
	}
}