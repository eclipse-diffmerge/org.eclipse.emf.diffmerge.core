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

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.info.GitInfo;
import org.eclipse.egit.core.util.RevCommitUtils;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A URI Converter for file revisions in the Git index that can be used for conflict resolution.
 */
public abstract class AbstractGitConflictURIConverter extends AbstractGitURIConverter {
  
  /** One of (STAGE_2, STAGE_3) in DirCacheEntry that defines the role held in conflict resolution */
  protected final int _conflictRole;
  
  /** The path of the holding resource in conflict */
  protected final String _holdingResourcePath;
  
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   * @param conflictRole_p one of (STAGE_2: "Ours", STAGE_3: "Theirs") in DirCacheEntry
   *          that defines the role held in conflict resolution
   * @param holdingResourcePath_p the non-null path of the holding resource in conflict
   */
  public AbstractGitConflictURIConverter(Repository repository_p,
      int conflictRole_p, String holdingResourcePath_p) {
    super(repository_p);
    _conflictRole = conflictRole_p;
    _holdingResourcePath = holdingResourcePath_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getGitFileRevision(java.lang.String)
   */
  @Override
  @SuppressWarnings("resource") // Just passing the repository as parameter
  protected IFileRevision getGitFileRevision(String gitPath_p) {
    // Check if the file being loaded is conflicting locally and return the
    // expected version from the index
    final GitHelper helper = GitHelper.INSTANCE;
    Repository repository = getRepository();
    try {
      try (RevWalk revWalk = helper.getRevWalk(repository, gitPath_p)) {
        RevCommit commit = helper.getOurs(repository, revWalk);
        GitInfo gitInfo = helper.getGitInfo(commit);
        if (gitInfo != null && gitInfo.getGitState() != null &&
            gitInfo.getGitState().hasConflicts()) {
          return inIndex(repository, gitPath_p, _conflictRole);
        }
      }
      // Current file is not conflicting, but root resource is.
      try (RevWalk revWalk = helper.getRevWalk(repository, _holdingResourcePath)) {
        if (DirCacheEntry.STAGE_2 == _conflictRole) {
          // Case of Ours
          RevCommit ourCommit = helper.getOurs(repository, revWalk);
          return inCommit(repository, ourCommit, gitPath_p);
        } else if (DirCacheEntry.STAGE_3 == _conflictRole) {
          // Case of Theirs: pick the ancestor commitId for the current file.
          RevCommit commit = RevCommitUtils.getTheirs(repository, revWalk);
          if (commit == null) {
            revWalk.reset();
            commit = helper.getOurs(repository, revWalk);
          }
          return inCommit(repository, commit, gitPath_p);
        }
      }
    } catch (IOException e) {
      EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(
          IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
          e.getMessage(), e));
    }
    // Default goes to index
    return inIndex(repository, gitPath_p);
  }
  
}