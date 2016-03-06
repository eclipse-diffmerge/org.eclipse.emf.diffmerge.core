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
package org.eclipse.emf.diffmerge.connector.core;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.compare.ISharedDocumentAdapter;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.ui.IEditorInput;

/**
 * A Scope Definition Factory for File revisioned resources.
 */
public class FileRevisionScopeDefinitionFactory extends GMFScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>>singleton(GMFScopeDefinitionFactory.class);
	}

	@Override
	public boolean isApplicableTo(Object entrypoint) {
		return entrypoint instanceof ITypedElement && getFileRevision((ITypedElement)entrypoint) != null;
	}

	@Override
	public IModelScopeDefinition createScopeDefinition(final Object entrypoint, final String label, final boolean editable) {
		final IFileRevision revision=getFileRevision((ITypedElement)entrypoint);
		if (revision != null) {
			try {
				final IStorage storage=getStorage(revision);
				final URI uri=getUri(revision);
				return new StreamScopeDefinition(entrypoint, (label != null) ? label : getLabelFor(entrypoint), uri, storage.getContents(), getURIConverter(revision));
			}
			catch (CoreException e) {
				EMFDiffMergeCoreConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeCoreConnectorPlugin.getDefault().getPluginId(), e.getMessage(),e));
			}
		}
		return super.createScopeDefinition(entrypoint, label, editable);
	}

	protected IFileRevision getFileRevision(ITypedElement entrypoint) {
		// avoid internal API using Adapter
		if (entrypoint instanceof IAdaptable) {
			IAdaptable adaptable=(IAdaptable)entrypoint;
			ISharedDocumentAdapter adapter=(ISharedDocumentAdapter)adaptable.getAdapter(ISharedDocumentAdapter.class);
			if (adapter != null) {
				IEditorInput input=adapter.getDocumentKey(entrypoint);
				if (input != null)
					return (IFileRevision)input.getAdapter(IFileRevision.class);
			}
		}
		return null;
	}

	protected URI getUri(IFileRevision revision) throws CoreException {
		IStorage storage=getStorage(revision);
		if (storage instanceof IFile) {
			// local resource
			return toPlatformURI((IFile)storage);
		}
		if (storage instanceof IFileState) {
			// local fileRevision (local history)
			IPath fullPath=storage.getFullPath();
			IResource res=ResourcesPlugin.getWorkspace().getRoot().findMember(fullPath);
			if (res.exists() && res instanceof IFile)
				return toPlatformURI((IFile)res);
		}
		return toFileURI(storage.getFullPath().toString());
	}

	protected IStorage getStorage(IFileRevision revision) throws CoreException {
		return revision.getStorage(new NullProgressMonitor());
	}

	protected URIConverter getURIConverter(IFileRevision revision) throws CoreException {
		String fullPath=getUri(revision).trimSegments(1).toString();
		//local history or current
		final long timestamp=revision.getTimestamp();
		if (timestamp != -1) {
			return new FileRevisionURIConverter(timestamp, fullPath);
		}
		return new ExtensibleURIConverterImpl();
	}

	protected final IResourceVariant getVariant(IFileRevision revision) {
		if (revision instanceof IAdaptable && ((IAdaptable)revision).getAdapter(IResourceVariant.class) instanceof IResourceVariant) {
			return (IResourceVariant)((IAdaptable)revision).getAdapter(IResourceVariant.class);
		}
		return null;
	}

	@Override
	protected String getLabelFor(Object entrypoint) {
		IFileRevision revision=getFileRevision((ITypedElement)entrypoint);
		if (revision != null) {
			IResourceVariant variant=getVariant(revision);
			if (variant != null)
				return variant.getContentIdentifier();
			if (revision.getTimestamp() != -1) {
				Calendar fileRevDate=Calendar.getInstance();
				fileRevDate.setTimeInMillis(revision.getTimestamp());
				return "Local history: " + revision.getName() + " " + DateFormat.getInstance().format(fileRevDate.getTime());
			}
			return revision.getContentIdentifier();
		}
		return super.getLabelFor(entrypoint);
	}

}