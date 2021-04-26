/**
 * Copyright (c) 2015-2019 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import java.io.InputStream;

import org.eclipse.compare.ISharedDocumentAdapter;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.ui.IEditorInput;


/**
 * A base scope definition factory for file revisions in histories.
 */
public abstract class AbstractRevisionScopeDefinitionFactory
extends AbstractURIConvertingScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#convertToURI(java.lang.Object)
   */
  @Override
  protected URI convertToURI(Object entrypoint_p) {
    URI result = null;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement) entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      if (revision != null)
        try {
          result = getURIForRevision(revision, typedElement);
        } catch (CoreException e) {
          EMFDiffMergeCoreConnectorPlugin.getDefault().getLog().log(
              new Status(IStatus.ERROR, EMFDiffMergeCoreConnectorPlugin.getDefault().getPluginId(),
                  e.getMessage(), e));
        }
    }
    if (result == null) {
      result = super.convertToURI(entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.AbstractRevisionScopeDefinitionFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#getLabelFor(java.lang.Object)
   */
  @Override
  protected String getLabelFor(Object entrypoint_p) {
    String result = null;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement)entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      if (revision != null) {
        result = getLabelForRevision(revision, typedElement);
      }
    }
    if (result == null) {
      result = super.getLabelFor(entrypoint_p);
    }
    return result;
  }
  
  /**
   * Return a a user-friendly label for the given revision extracted from the given
   * typed element, if applicable
   * @param revision_p a non-null revision
   * @param entrypoint_p a non-null typed element
   * @return a potentially null string
   */
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return revision_p.getContentIdentifier();
  }
  
  /**
   * Return the file revision associated to the given typed element, if any
   * @param entrypoint_p a non-null typed element
   * @return a potentially null object
   */
  protected IFileRevision getRevision(ITypedElement entrypoint_p) {
    IFileRevision result = Adapters.adapt(entrypoint_p, IFileRevision.class);
    if (result == null) {
      ISharedDocumentAdapter sdAdapter = Adapters.adapt(entrypoint_p, ISharedDocumentAdapter.class);
      if (sdAdapter != null) {
        IEditorInput input = sdAdapter.getDocumentKey(entrypoint_p);
        result = Adapters.adapt(input, IFileRevision.class);
      }
    }
    return result;
  }
  
  /**
   * Return the storage associated to the given file revision, if any
   * @param revision_p a non-null revision
   * @return a potentially null object
   * @throws CoreException if an error occurs
   */
  protected IStorage getStorage(IFileRevision revision_p) throws CoreException {
    return revision_p.getStorage(new NullProgressMonitor());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getStream(java.lang.Object)
   */
  @Override
  @SuppressWarnings("resource")
  protected InputStream getStream(Object entrypoint_p) throws CoreException {
    InputStream result = null;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement) entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      if (revision != null) {
        IStorage storage = getStorage(revision);
        if (storage != null) {
          result = storage.getContents();
        }
      }
    }
    if (result == null) {
      result = super.getStream(entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getURIConverter(java.lang.Object)
   */
  @Override
  protected URIConverter getURIConverter(Object entrypoint_p) {
    URIConverter result = null;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement) entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      if (revision != null) {
        try {
          result = getURIConverterForRevision(revision, typedElement);
        } catch (CoreException e) {
          EMFDiffMergeCoreConnectorPlugin.getDefault().logError(e);
        }
      }
    }
    return result;
  }
  
  /**
   * Return a URI converter for the given file revision associated to the given
   * entry point, if possible.
   * Precondition: isApplicableToRevision(revision_p)
   * @param revision_p a non-null revision
   * @param entrypoint_p a non-null typed element
   * @return a potentially null object
   * @throws CoreException if an error occurs
   */
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p,
      ITypedElement entrypoint_p) throws CoreException {
    URIConverter result = null;
    URI uri = getURIForRevision(revision_p, entrypoint_p);
    if (uri != null) {
      String fullPath = uri.trimSegments(1).toString();
      final long timestamp = revision_p.getTimestamp();
      // Local history or current revision
      if (timestamp != -1) {
        result = new LocalHistoryURIConverter(timestamp, fullPath);
      } else {
        result = new ExtensibleURIConverterImpl();
      }
    }
    return result;
  }
  
  /**
   * Return a URI for the given file revision associated to the given entry point
   * @param revision_p a non-null file revision
   * @param entrypoint_p a non-null typed element
   * @return a potentially null URI
   * @throws CoreException if an error occurs
   */
  protected URI getURIForRevision(IFileRevision revision_p, ITypedElement entrypoint_p)
      throws CoreException {
    URI result = null;
    IStorage storage = getStorage(revision_p);
    if (storage instanceof IFile) {
      // Local resource
      result = toPlatformURI((IFile) storage);
    } else if (storage instanceof IFileState) {
      // Local file revision (local history)
      IPath fullPath = storage.getFullPath();
      IResource res = ResourcesPlugin.getWorkspace().getRoot()
          .findMember(fullPath);
      if (res.exists() && res instanceof IFile) {
        result = toPlatformURI((IFile) res);
      }
    } else if (storage != null) {
      result = toFileURI(storage.getFullPath().toString());
    }
    return result;
  }
  
  /**
   * Return the resource variant associated with the given file revision, if any
   * @param revision_p a non-null revision
   * @return a potentially null object
   */
  protected IResourceVariant getVariant(IFileRevision revision_p) {
    return Adapters.adapt(revision_p, IResourceVariant.class);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  @Override
  public boolean isApplicableTo(Object entrypoint_p) {
    boolean result = false;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement) entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      if (revision != null) {
        result = isApplicableToRevision(revision, typedElement);
      }
    }
    return result;
  }
  
  /**
   * Return whether this scope definition factory is applicable to the given revision
   * extracted from the given typed element
   * @param revision_p a non-null revision
   * @param entrypoint_p a non-null typed element
   */
  protected abstract boolean isApplicableToRevision(IFileRevision revision_p,
      ITypedElement entrypoint_p);
  
}