/*******************************************************************************
 * Copyright (c) 2015-2017 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *    Stephane Bouchet (Intel Corporation) - bug #496397
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import static org.eclipse.egit.core.internal.storage.GitFileRevision.inCommit;
import static org.eclipse.egit.core.internal.storage.GitFileRevision.inIndex;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.RevUtils;
import org.eclipse.egit.core.RevUtils.ConflictCommits;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A URI Converter for file revisions in the Git index that can be used for conflict resolution.
 */
@SuppressWarnings("restriction") // Specific EGit behaviors
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
  protected IFileRevision getGitFileRevision(String gitPath) {
    // Check if the file being loaded is conflicting locally and return the
    // expected version from the index
    try {
      if (GitHelper.INSTANCE.isConflicting(getRepository(), gitPath))
        return inIndex(getRepository(), gitPath, _conflictRole);
      ConflictCommits conflictCommits = RevUtils.getConflictCommits(
          getRepository(), _holdingResourcePath);
      // Current file not conflicting, but root resource is.
      if (DirCacheEntry.STAGE_2 == _conflictRole) {
        return inCommit(
            getRepository(), conflictCommits.getOurCommit(), gitPath, null);
      }
      // If Theirs, pick the git ancestor commitid for the current file.
      else if (DirCacheEntry.STAGE_3 == _conflictRole) {
        RevCommit commit = conflictCommits.getTheirCommit();
        if (commit == null)
          commit = conflictCommits.getOurCommit();
        return inCommit(getRepository(), commit, gitPath, null);
      }
    } catch (IOException e) {
      EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(
          IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
          e.getMessage(), e));
    }
    // Default goes to index
    return inIndex(getRepository(), gitPath);
  }
  
}