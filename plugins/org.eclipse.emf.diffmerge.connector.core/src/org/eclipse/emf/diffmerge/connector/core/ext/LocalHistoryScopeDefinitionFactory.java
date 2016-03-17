/**
 * Copyright (c) 2015-2016 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import java.text.DateFormat;
import java.util.Calendar;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.team.internal.core.history.LocalFileRevision;


/**
 * A scope definition factory for file revisions in the Eclipse local history.
 */
@SuppressWarnings("restriction") // Specific IFileRevision sub-types
public class LocalHistoryScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result;
    IResourceVariant variant = getVariant(revision_p);
    if (variant != null) {
      // Known variant
      result = variant.getContentIdentifier();
    } else if (revision_p.getTimestamp() != -1) {
      // Known time stamp
      Calendar fileRevDate = Calendar.getInstance();
      fileRevDate.setTimeInMillis(revision_p.getTimestamp());
      result = String.format(
          Messages.LocalHistoryModelScopeDefinitionFactory_RevisionLabel,
          revision_p.getName(),
          DateFormat.getInstance().format(fileRevDate.getTime()));
    } else {
      // No variant and unknown time stamp
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p) throws CoreException {
    URIConverter result = null;
    URI uri = getURIForRevision(revision_p);
    if (uri != null) {
      String fullPath = uri.trimSegments(1).toString();
      final long timestamp = revision_p.getTimestamp();
      // Local history or current revision
      if (timestamp != -1)
        result = new LocalHistoryURIConverter(timestamp, fullPath);
      else
        result = new ExtensibleURIConverterImpl();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision_p) throws CoreException {
    URI result = null;
    IStorage storage = getStorage(revision_p);
    if (storage instanceof IFile) {
      // Local resource
      result = toPlatformURI((IFile)storage);
    } else if (storage instanceof IFileState) {
      // Local file revision (local history)
      IPath fullPath = storage.getFullPath();
      IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(fullPath);
      if (res.exists() && res instanceof IFile)
        result = toPlatformURI((IFile)res);
    } else if (storage != null) {
      result = toFileURI(storage.getFullPath().toString());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return revision_p instanceof LocalFileRevision;
  }
  
}