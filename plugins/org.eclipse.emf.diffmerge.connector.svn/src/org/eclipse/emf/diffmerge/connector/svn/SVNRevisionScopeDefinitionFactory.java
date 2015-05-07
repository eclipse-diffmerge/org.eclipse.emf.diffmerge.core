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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.FileRevisionScopeDefinitionFactory;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.resource.ILocalResource;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.synchronize.variant.BaseFileVariant;

public class SVNRevisionScopeDefinitionFactory extends FileRevisionScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>>singleton(FileRevisionScopeDefinitionFactory.class);
	}

	@Override
	protected IFileRevision getFileRevision(ITypedElement entrypoint) {
		IFileRevision revision=super.getFileRevision(entrypoint);
		if (getVariant(revision) instanceof BaseFileVariant) {
			return revision;
		}
		return null;
	}

	@Override
	protected URI getUri(IFileRevision revision) throws CoreException {
		IResourceVariant variant=getVariant(revision);
		if (variant instanceof BaseFileVariant) {
			ILocalResource res=((BaseFileVariant)variant).getResource();
			IRepositoryLocation location=SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
			return URI.createURI(location.getRepositoryRootUrl() + ((BaseFileVariant)variant).getResource().getResource().getFullPath(), true);
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
		IResourceVariant variant=getVariant(revision);
		if (variant instanceof BaseFileVariant) {
			ILocalResource res=((BaseFileVariant)variant).getResource();
			IRepositoryLocation location=SVNRemoteStorage.instance().getRepositoryLocation(res.getResource());
			return new SVNStreamURIConverter(location, SVNRevision.fromString(variant.getContentIdentifier()));
		}
		return super.getURIConverter(revision);
	}

	@Override
	protected String getLabelFor(Object entrypoint) {
		IFileRevision revision=getFileRevision((ITypedElement)entrypoint);
		if (revision != null) {
			IResourceVariant variant=getVariant(revision);
			if (variant instanceof BaseFileVariant) {
				return "Remote File [Rev:" + variant.getContentIdentifier() + "]";
			}
		}
		return super.getLabelFor(entrypoint);
	}

}
