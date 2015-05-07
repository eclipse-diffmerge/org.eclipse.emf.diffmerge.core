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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.internal.storage.CommitFileRevision;
import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.egit.core.internal.storage.IndexFileRevision;
import org.eclipse.egit.core.synchronize.GitRemoteResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.FileRevisionScopeDefinitionFactory;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;

public class GitStreamScopeDefinitionFactory extends FileRevisionScopeDefinitionFactory {

	private static final int ABBREVIATE_COMMIT_LABEL_SIZE=7;

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>>singleton(FileRevisionScopeDefinitionFactory.class);
	}

	@Override
	protected IFileRevision getFileRevision(ITypedElement entrypoint) {
		IFileRevision revision=super.getFileRevision(entrypoint);
		if (getVariant(revision) instanceof GitRemoteResource) {
			return revision;
		}
		if (revision instanceof GitFileRevision) {
			return revision;
		}
		return null;
	}

	@Override
	protected URI getUri(IFileRevision revision) throws CoreException {
		IResourceVariant variant=getVariant(revision);
		if (variant instanceof GitRemoteResource) {
			return URI.createURI(GitSchemeConstants.REMOTE_SCHEME + ":/" + ((GitRemoteResource)variant).getPath());
		}
		if (revision instanceof IndexFileRevision) {
			return URI.createURI(GitSchemeConstants.INDEX_SCHEME + ":/" + revision.getURI().toString());
		}
		if (revision instanceof CommitFileRevision) {
			return URI.createURI(GitSchemeConstants.COMMIT_SCHEME + ":/" + revision.getURI().toString());
		}
		return super.getUri(revision);
	}

	@Override
	protected IStorage getStorage(IFileRevision revision) throws CoreException {
		IResourceVariant variant=getVariant(revision);
		if (variant != null) {
			return variant.getStorage(new NullProgressMonitor());
		}
		else
			return super.getStorage(revision);
	}

	@Override
	protected URIConverter getURIConverter(IFileRevision revision) throws CoreException {
		// git remote
		IResourceVariant variant=getVariant(revision);
		if (variant instanceof GitRemoteResource) {
			IPath path=((GitRemoteResource)variant).getDisplayPath();
			Repository repo=EMFDiffMergeGitConnectorPlugin.getInstance().getRepo(path);
			if (repo != null)
				return new GitRemoteURIConverter(((GitRemoteResource)variant).getCommitId(), repo);

		}
		Repository repo=EMFDiffMergeGitConnectorPlugin.getInstance().getRepo(revision);
		if (repo != null) {
			// git index
			if (revision instanceof IndexFileRevision) {
				return new GitIndexURIConverter(repo);
			}
			// git commit
			if (revision instanceof CommitFileRevision) {
				return new GitRemoteURIConverter(((CommitFileRevision)revision).getRevCommit(), repo);
			}
		}
		return super.getURIConverter(revision);
	}

	@Override
	protected String getLabelFor(Object entrypoint) {
		IFileRevision revision=getFileRevision((ITypedElement)entrypoint);
		if (revision != null && getVariant(revision) instanceof GitRemoteResource) {
			return revision.getName() + " " + getVariant(revision).getContentIdentifier();
		}
		if (revision instanceof IndexFileRevision) {
			return "Index: " + revision.getName();
		}
		if (revision instanceof CommitFileRevision) {
			return "Commit: " + revision.getName() + " " + getContentIdentifier(((CommitFileRevision)revision).getRevCommit());
		}
		return super.getLabelFor(entrypoint);
	}

	public String getContentIdentifier(RevCommit commitId) {
		if (commitId == null)
			return "";

		StringBuilder s=new StringBuilder();
		s.append(commitId.abbreviate(ABBREVIATE_COMMIT_LABEL_SIZE).name());
		s.append("...");

		PersonIdent authorIdent=commitId.getAuthorIdent();
		if (authorIdent != null) {
			s.append(" (");
			s.append(authorIdent.getName());
			s.append(")");
		}
		return s.toString();
	}

}