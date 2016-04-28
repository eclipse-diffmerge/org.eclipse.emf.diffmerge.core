/*******************************************************************************
 * Copyright (c) 2015-2016 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.egit.core.internal.storage.CommitFileRevision;
import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.egit.core.internal.storage.IndexFileRevision;
import org.eclipse.egit.core.internal.storage.WorkspaceFileRevision;
import org.eclipse.egit.core.synchronize.GitRemoteResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;


/**
 * A scope definition factory for file revisions in Git repositories,
 * index (staging area) excluded.
 */
@SuppressWarnings("restriction") // Specific EGit types
public class GitRevisionScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /** The minimal size of commit labels that requires abbreviation */
  protected static final int ABBREVIATE_COMMIT_LABEL_SIZE = 7;
  
  
  /**
   * Return an identifier for the given commit ID
   * @param revCommit_p a non-null commit ID
   * @return a non-null string
   */
  protected String getContentIdentifier(RevCommit revCommit_p) {
    StringBuilder s = new StringBuilder();
    if (revCommit_p != null) {
      s.append(revCommit_p.abbreviate(ABBREVIATE_COMMIT_LABEL_SIZE).name());
      s.append("..."); //$NON-NLS-1$
      PersonIdent authorIdent = revCommit_p.getAuthorIdent();
      if (authorIdent != null) {
        s.append(" ("); //$NON-NLS-1$
        s.append(authorIdent.getName());
        s.append(')');
      }
    }
    return s.toString();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  @SuppressWarnings("resource")
  protected URIConverter getURIConverterForRevision(IFileRevision revision) throws CoreException {
    URIConverter result = null;
    IResourceVariant variant = getVariant(revision);
    if (variant instanceof GitRemoteResource) {
      // Git remote
      IPath path = ((GitRemoteResource)variant).getDisplayPath();
      Repository repo = GitHelper.INSTANCE.getRepository(path);
      if (repo != null)
        result = new GitCommitURIConverter(((GitRemoteResource)variant).getCommitId(), repo);
    }
    if (result == null) {
      Repository repo = GitHelper.INSTANCE.getRepository(revision);
      if (repo != null) {
        if (revision instanceof IndexFileRevision) {
          // Git index
          result = new GitIndexTheirsURIConverter(repo);
        } else if (revision instanceof CommitFileRevision) {
          // Git commit
          result = new GitCommitURIConverter(((CommitFileRevision)revision).getRevCommit(), repo);
        }
      }
    }
    if (result == null)
      result = super.getURIConverterForRevision(revision);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision) throws CoreException {
    IResourceVariant variant = getVariant(revision);
    final String SCHEME_SEP = ":/"; //$NON-NLS-1$
    URI result;
    if (variant instanceof GitRemoteResource) {
      result = URI.createURI(
          GitHelper.INSTANCE.getSchemeRemote() + SCHEME_SEP + ((GitRemoteResource)variant).getPath());
    } else if (revision instanceof IndexFileRevision) {
      result = URI.createURI(
          GitHelper.INSTANCE.getSchemeIndex() + SCHEME_SEP + revision.getURI().toString());
    } else if (revision instanceof CommitFileRevision) {
      result = URI.createURI(
          GitHelper.INSTANCE.getSchemeCommit() + SCHEME_SEP + revision.getURI().toString());
    } else {
      result = super.getURIForRevision(revision);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result;
    IResourceVariant variant = getVariant(revision_p);
    if (variant instanceof GitRemoteResource) {
      result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelRemote,
          revision_p.getName(), variant.getContentIdentifier());
    } else if (revision_p instanceof IndexFileRevision) {
      result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelIndex, revision_p.getName());
    } else if (revision_p instanceof CommitFileRevision) {
      result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelCommit, revision_p.getName(),
          getContentIdentifier(((CommitFileRevision)revision_p).getRevCommit()));
    } else if (revision_p instanceof WorkspaceFileRevision) {
      result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelWorkspace, revision_p.getName());
    } else {
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return revision_p instanceof GitFileRevision ||
        getVariant(revision_p) instanceof GitRemoteResource;
  }
  
}