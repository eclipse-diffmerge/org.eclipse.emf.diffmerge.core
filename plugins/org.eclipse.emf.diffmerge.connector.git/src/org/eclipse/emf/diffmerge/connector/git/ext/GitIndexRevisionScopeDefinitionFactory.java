/*******************************************************************************
 * Copyright (c) 2015-2018 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *    Stephane Bouchet (Intel Corporation) - bug #496397
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import java.io.IOException;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.storage.IndexFileRevision;
import org.eclipse.egit.ui.internal.revision.EditableRevision;
import org.eclipse.egit.ui.internal.revision.LocalFileRevision;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.core.ext.LocalHistoryURIConverter;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A scope definition factory for file revisions in the Git index (staging area),
 * also used for handling conflicts (role "Ours").
 */
@SuppressWarnings("restriction") // Specific EGit types
public class GitIndexRevisionScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result = null;
    if (entrypoint_p instanceof EditableRevision) {
      if (revision_p instanceof IndexFileRevision) {
        if (isScopeEditable(entrypoint_p)) {
          result = String.format(
              Messages.GitIndexRevisionScopeDefinitionFactory_LabelIndexEditable,
              revision_p.getName());
        } else {
          result = String.format(
              Messages.GitIndexRevisionScopeDefinitionFactory_LabelIndexReadOnly,
              revision_p.getName());
        }
      } else if (revision_p instanceof LocalFileRevision) {
        result = String.format(
            Messages.GitIndexRevisionScopeDefinitionFactory_LabelLocal,
            getLocalFileRevisionPath((LocalFileRevision)revision_p));
      }
    }
    if (result == null)
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    return result;
  }
  
  /**
   * Return the path of the given local file revision
   * @param revision_p a non-null object
   * @return a non-null object
   */
  protected String getLocalFileRevisionPath(LocalFileRevision revision_p) {
    return revision_p.getFile().getFullPath().toString();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getRevision(org.eclipse.compare.ITypedElement)
   */
  @Override
  protected IFileRevision getRevision(ITypedElement typedElement_p) {
    IFileRevision result;
    if (typedElement_p instanceof EditableRevision)
      result = ((EditableRevision)typedElement_p).getFileRevision();
    else
      result = super.getRevision(typedElement_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p)
      throws CoreException {
    if (revision_p instanceof IndexFileRevision) {
      try {
        if (GitHelper.INSTANCE.isConflicting(revision_p)) {
          return new GitIndexOursURIConverter(
              GitHelper.INSTANCE.getRepository(revision_p),
              ((IndexFileRevision)revision_p).getGitPath());
        }
        return new GitIndexURIConverter(GitHelper.INSTANCE.getRepository(revision_p));
      } catch (IOException e) {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      } catch (NoWorkTreeException e) {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
    }
    if (revision_p instanceof LocalFileRevision) {
      String fullPath = getLocalFileRevisionPath((LocalFileRevision)revision_p);
      // Local history or current
      final long timestamp = revision_p.getTimestamp();
      if (timestamp != -1) {
        return new LocalHistoryURIConverter(timestamp, fullPath);
      }
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision_p) throws CoreException {
    URI result = null;
    if (revision_p instanceof IndexFileRevision) {
      boolean conflicting;
      try {
        conflicting = GitHelper.INSTANCE.isConflicting(revision_p);
        if (conflicting)
          result = URI.createPlatformResourceURI(revision_p.getURI().toString(), true);
        else
          result = URI.createURI(
              GitHelper.INSTANCE.getSchemeIndex() + GitHelper.INSTANCE.getSchemeSeparator() +
              revision_p.getURI().toString());
      } catch (Exception e) {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
    } else if (revision_p instanceof LocalFileRevision) {
      result = URI.createPlatformResourceURI(getLocalFileRevisionPath((LocalFileRevision)revision_p), true);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return entrypoint_p instanceof EditableRevision;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#isScopeEditable(java.lang.Object)
   */
  @Override
  protected boolean isScopeEditable(Object entrypoint_p) {
    return entrypoint_p instanceof IEditableContent;
  }
  
}
