/*******************************************************************************
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
 *    Stephane Bouchet (Intel Corporation) - bug #496397
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.INDEX_CONFLICT_OURS;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.NOT_MANAGED;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.WORKING_TREE;

import java.util.Arrays;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.info.GitInfo;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind;
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
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIConverterForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  @SuppressWarnings("resource") // See GitInfo#getRepository()
  protected URIConverter getURIConverterForRevision(IFileRevision revision_p,
      ITypedElement entrypoint_p) throws CoreException {
    URIConverter result = null;
    GitInfo info = GitHelper.INSTANCE.getGitInfo(revision_p);
    GitFileRevisionKind kind = GitHelper.INSTANCE.getGitKind(info, entrypoint_p);
    switch (kind) {
    case NOT_MANAGED: case WORKING_TREE:
      result = super.getURIConverterForRevision(revision_p, entrypoint_p);
      break;
    case INDEX:
      result = new GitIndexURIConverter(info.getRepository());
      break;
    case INDEX_CONFLICT_OURS:
      result = new GitIndexOursURIConverter(info.getRepository(), info.getGitPath());
      break;
    case INDEX_CONFLICT_THEIRS:
      result = new GitIndexTheirsURIConverter(info.getRepository(), info.getGitPath());
      break;
    case COMMIT: case REMOTE:
      try {
        RevCommit commit = info.getRepository().parseCommit(info.getCommitId());
        result = new GitCommitURIConverter(commit, info.getRepository());
      } catch (Exception e) {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(
            IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
      break;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getURIForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected URI getURIForRevision(IFileRevision revision_p, ITypedElement entrypoint_p)
      throws CoreException {
    GitInfo info = GitHelper.INSTANCE.getGitInfo(revision_p);
    GitFileRevisionKind kind = GitHelper.INSTANCE.getGitKind(info, entrypoint_p);
    URI result = null;
    String scheme;
    switch (kind) {
    case INDEX_CONFLICT_OURS:
      scheme = null;
      result = createPlatformResourceUriFromFileRevision(revision_p);
      break;
    case INDEX:
    case INDEX_CONFLICT_THEIRS:
      scheme = GitHelper.INSTANCE.getSchemeIndex();
      break;
    case COMMIT:
      scheme = GitHelper.INSTANCE.getSchemeCommit();
      break;
    case REMOTE:
      scheme = GitHelper.INSTANCE.getSchemeRemote();
      break;
    default:
      scheme = null;
    }
    if (result == null) {
      if (scheme != null) {
        result = URI.createURI(
            scheme + GitHelper.INSTANCE.getSchemeSeparator() + info.getGitPath());
      } else {
        result = super.getURIForRevision(revision_p, entrypoint_p);
      }
    }
    return result;
  }
  
  /**
   * Return the workspace-relative URI for the given file revision
   * @param revision_p a non-null file revision
   * @return a potentially null URI
   */
  @SuppressWarnings("resource") // See GitInfo#getRepository()
  protected URI createPlatformResourceUriFromFileRevision(IFileRevision revision_p) {
    URI result = null;
    Repository repo = GitHelper.INSTANCE.getRepository(revision_p);
    if (repo != null) {
      java.net.URI revisionAbsoluteUri = repo.getWorkTree().toURI().resolve(revision_p.getURI());
      IWorkspaceRoot wkRoot = ResourcesPlugin.getWorkspace().getRoot();
      IFile[] platformFiles = wkRoot.findFilesForLocationURI(revisionAbsoluteUri);
      if (platformFiles.length > 0 && platformFiles[0].isAccessible()) {
        IFile iFile = platformFiles[0];
        result = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  @SuppressWarnings({ "resource", "incomplete-switch" }) // See GitInfo#getRepository()
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result = null;
    GitInfo info = GitHelper.INSTANCE.getGitInfo(revision_p);
    GitFileRevisionKind kind = GitHelper.INSTANCE.getGitKind(info, entrypoint_p);
    switch (kind) {
    case WORKING_TREE:
      result = String.format(
          Messages.GitRevisionScopeDefinitionFactory_LabelWorkspace, revision_p.getName());
      break;
    case INDEX_CONFLICT_OURS:
      result = String.format(
          Messages.GitRevisionScopeDefinitionFactory_LabelIndexEditable, revision_p.getName());
      break;
    case INDEX:
    case INDEX_CONFLICT_THEIRS:
      result = String.format(
          Messages.GitRevisionScopeDefinitionFactory_LabelIndex, revision_p.getName());
      break;
    case COMMIT:
      try {
        RevCommit commit = info.getRepository().parseCommit(info.getCommitId());
        result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelCommit, revision_p.getName(),
            getContentIdentifier(commit));
      } catch (Exception e) {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(
            IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
      break;
    case REMOTE:
      IResourceVariant variant = getVariant(revision_p);
      result = String.format(Messages.GitRevisionScopeDefinitionFactory_LabelRemote,
          revision_p.getName(), variant == null? null: variant.getContentIdentifier());
      break;
    }
    if (result == null) {
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    GitInfo info = GitHelper.INSTANCE.getGitInfo(revision_p);
    return GitHelper.INSTANCE.getGitKind(info, entrypoint_p) != NOT_MANAGED;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#isScopeEditable(java.lang.Object)
   */
  @Override
  protected boolean isScopeEditable(Object entrypoint_p) {
    boolean result = false;
    if (entrypoint_p instanceof ITypedElement) {
      ITypedElement typedElement = (ITypedElement) entrypoint_p;
      IFileRevision revision = getRevision(typedElement);
      GitInfo info = GitHelper.INSTANCE.getGitInfo(revision);
      GitFileRevisionKind kind = GitHelper.INSTANCE.getGitKind(info, typedElement);
      result = Arrays.asList(NOT_MANAGED, WORKING_TREE, INDEX_CONFLICT_OURS).contains(kind);
    }
    return result;
  }
  
}