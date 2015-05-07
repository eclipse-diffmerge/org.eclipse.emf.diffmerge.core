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

import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;

public final class GitRemoteURIConverter extends GitURIConverter {

	private final RevCommit commitId;

	public GitRemoteURIConverter(RevCommit commitId, Repository repo) {
		super(repo);
		this.commitId=commitId;
	}

	@Override
	protected IFileRevision getGitFileRevision(String gitPath) {
		return GitFileRevision.inCommit(getRepository(), commitId, gitPath, null);
	}

}