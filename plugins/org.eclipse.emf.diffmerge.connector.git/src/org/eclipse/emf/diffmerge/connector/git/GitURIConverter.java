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
package org.eclipse.emf.diffmerge.connector.git;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;

public abstract class GitURIConverter extends ExtensibleURIConverterImpl {

	private final Repository repo;

	
	public GitURIConverter(Repository repo){
		super();
		this.repo = repo;
	}

	public GitURIConverter(List<URIHandler> uriHandlers, List<ContentHandler> contentHandlers, Repository repo) {
		super(uriHandlers, contentHandlers);
		this.repo=repo;
	}

	protected abstract IFileRevision getGitFileRevision(String gitPath);

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (repo != null
			&& (uri.scheme().equals(GitSchemeConstants.REMOTE_SCHEME) || uri.scheme().equals(GitSchemeConstants.INDEX_SCHEME) || uri.scheme().equals(
					GitSchemeConstants.COMMIT_SCHEME))) {
			//try to locate the file locally 
			IPath newPath=new Path(repo.getWorkTree().getAbsolutePath()).append(uri.devicePath());
			File target=newPath.toFile();
			if (target != null && target.exists()) {
				try {
					String gitPath=uri.devicePath().substring(1);
					return getGitFileRevision(gitPath).getStorage(new NullProgressMonitor()).getContents();
				}
				catch (CoreException e) {
					EMFDiffMergeGitConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
				}
			}
			else {
				EMFDiffMergeGitConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.PLUGIN_ID, "Cannot find IResource at: " + newPath));
			}
		}
		return super.createInputStream(uri, options);
	}

	protected Repository getRepository() {
		return repo;
	}

}