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
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;

public final class GitEditableRevisionURIConverter extends GitURIConverter {

	public GitEditableRevisionURIConverter(Repository repo) {
		super(repo);
	}

	@Override
	protected IFileRevision getGitFileRevision(String gitPath) {
		// check if the file being loaded is conflicting locally and load the "ours" version from the index.
		try {
			if (EMFDiffMergeGitConnectorPlugin.getInstance().isConflicting(getRepository(), gitPath))
				return GitFileRevision.inIndex(getRepository(), gitPath, DirCacheEntry.STAGE_2);
		}
		catch (IOException e) {
			EMFDiffMergeGitConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		}
		return GitFileRevision.inIndex(getRepository(), gitPath);
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (getRepository() != null && uri.isPlatformResource()) {
			//try to locate the file locally 
			IPath newPath=new Path(getRepository().getWorkTree().getAbsolutePath()).append(uri.toPlatformString(true));
			File target=newPath.toFile();
			if (target != null && target.exists()) {
				try {
					return getGitFileRevision(uri.toPlatformString(true).substring(1)).getStorage(new NullProgressMonitor()).getContents();
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
}