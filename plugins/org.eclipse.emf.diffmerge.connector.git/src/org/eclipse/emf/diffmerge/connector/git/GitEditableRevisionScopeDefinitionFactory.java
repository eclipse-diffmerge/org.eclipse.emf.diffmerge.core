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
import java.util.Collection;
import java.util.Collections;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.storage.IndexFileRevision;
import org.eclipse.egit.ui.internal.revision.EditableRevision;
import org.eclipse.egit.ui.internal.revision.LocalFileRevision;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.FileRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.core.LocalRevisionURIConverter;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;

public class GitEditableRevisionScopeDefinitionFactory extends FileRevisionScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>>singleton(FileRevisionScopeDefinitionFactory.class);
	}

	@Override
	protected IFileRevision getFileRevision(ITypedElement entrypoint) {
		if (entrypoint instanceof EditableRevision)
			return ((EditableRevision)entrypoint).getFileRevision();
		return null;
	}

	@Override
	public IModelScopeDefinition createScopeDefinition(final Object entrypoint, final String label, final boolean editable) {
		try {
			ITypedElement typedElement=(ITypedElement)entrypoint;
			IFileRevision revision=getFileRevision(typedElement);
			final Repository repo=EMFDiffMergeGitConnectorPlugin.getInstance().getRepo(revision);
			if (repo != null) {
				final boolean conflicting=EMFDiffMergeGitConnectorPlugin.getInstance().isConflicting(repo, revision);
				final IStorage storage=revision.getStorage(new NullProgressMonitor());
				final String gitLabel=(label != null) ? label : getGitLabel(typedElement);
				final URI uri=getUri(typedElement, conflicting);
				final URIConverter uriConverter=getUriConverter(repo, revision);
				return new GitEditableRevisionScopeDefinition(entrypoint, gitLabel, uri, storage.getContents(), uriConverter, conflicting ? true : editable);
			}
		}
		catch (Exception e) {
			EMFDiffMergeGitConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		}
		return super.createScopeDefinition(entrypoint, label, editable);
	}

	private URIConverter getUriConverter(Repository repo, IFileRevision revision) {
		if (revision instanceof IndexFileRevision) {
			return new GitEditableRevisionURIConverter(repo);
		}
		if (revision instanceof LocalFileRevision) {
			String fullPath=getLocalFileRevisionPath((LocalFileRevision)revision);
			//local history or current
			final long timestamp=revision.getTimestamp();
			if (timestamp != -1) {
				return new LocalRevisionURIConverter(timestamp, fullPath);
			}
		}
		return null;
	}

	private String getLocalFileRevisionPath(LocalFileRevision revision) {
		return revision.getFile().getFullPath().toString();
	}

	private URI getUri(ITypedElement entrypoint, boolean conflicting) {
		IFileRevision revision=getFileRevision(entrypoint);
		if (revision instanceof IndexFileRevision) {
			if (conflicting)
				return URI.createPlatformResourceURI(revision.getURI().toString(), true);
			else
				return URI.createURI(GitSchemeConstants.INDEX_SCHEME + ":/" + revision.getURI().toString());
		}

		if (revision instanceof LocalFileRevision) {
			return URI.createPlatformResourceURI(getLocalFileRevisionPath((LocalFileRevision)revision), true);
		}
		return null;
	}

	private String getGitLabel(ITypedElement entrypoint) {
		IFileRevision revision=getFileRevision(entrypoint);
		if (revision instanceof IndexFileRevision) {
			if (entrypoint instanceof IEditableContent)
				return "Index (editable): " + revision.getName();
			return "Index: " + revision.getName();
		}
		if (revision instanceof LocalFileRevision) {
			return "Local: " + getLocalFileRevisionPath((LocalFileRevision)revision);
		}
		return super.getLabelFor(entrypoint);
	}

}
