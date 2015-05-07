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

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;

public final class GitIndexURIConverter extends GitURIConverter {

	public GitIndexURIConverter(Repository repo) {
		super(repo);
	}

	@Override
	protected IFileRevision getGitFileRevision(String gitPath) {
		// check if the file being loaded is conflicting locally and load the "theirs" version from the index.
		try {
			if (EMFDiffMergeGitConnectorPlugin.getInstance().isConflicting(getRepository(), gitPath))
				return GitFileRevision.inIndex(getRepository(), gitPath, DirCacheEntry.STAGE_3);
		}
		catch (IOException e) {
			EMFDiffMergeGitConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		}
		return GitFileRevision.inIndex(getRepository(), gitPath);
	}

}