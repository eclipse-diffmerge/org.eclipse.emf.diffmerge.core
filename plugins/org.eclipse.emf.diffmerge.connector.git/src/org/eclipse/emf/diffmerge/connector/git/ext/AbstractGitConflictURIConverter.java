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

import static org.eclipse.egit.core.internal.storage.GitFileRevision.inIndex;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A URI Converter for file revisions in the Git index that can be used for conflict resolution.
 */
public abstract class AbstractGitConflictURIConverter extends AbstractGitURIConverter {
  
  /** One of (STAGE_2, STAGE_3) in DirCacheEntry that defines the role held in conflict resolution */
  protected final int _conflictRole;
  
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   * @param conflictRole_p one of (STAGE_2: "Ours", STAGE_3: "Theirs") in DirCacheEntry
   *          that defines the role held in conflict resolution
   */
  public AbstractGitConflictURIConverter(Repository repository_p, int conflictRole_p) {
    super(repository_p);
    _conflictRole = conflictRole_p;
  }
  
  /**
   * Constructor
   * @param uriHandlers_p a non-null list
   * @param contentHandlers_p a non-null list
   * @param repository_p a non-null Git repository
   * @param conflictRole_p one of (STAGE_2: "Ours", STAGE_3: "Theirs") in DirCacheEntry
   *          that defines the role held in conflict resolution
   */
  public AbstractGitConflictURIConverter(List<URIHandler> uriHandlers_p, List<ContentHandler> contentHandlers_p, Repository repository_p, int conflictRole_p) {
    super(uriHandlers_p, contentHandlers_p, repository_p);
    _conflictRole=conflictRole_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getGitFileRevision(java.lang.String)
   */
  @Override
  @SuppressWarnings("restriction")
  protected IFileRevision getGitFileRevision(String gitPath) {
    // Check if the file being loaded is conflicting locally and return the expected version from the index
    try {
      if (GitHelper.INSTANCE.isConflicting(getRepository(), gitPath))
        return inIndex(getRepository(), gitPath, _conflictRole);
    } catch (IOException e) {
      EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
          EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
    }
    // Return the default version from the index
    return inIndex(getRepository(), gitPath);
  }
  
}