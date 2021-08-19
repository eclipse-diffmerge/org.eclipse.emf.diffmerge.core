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
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import java.util.List;

import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A URI Converter for file revisions in a given Git commit.
 */
public class GitCommitURIConverter extends AbstractGitURIConverter {
  
  /** The non-null commit ID */
  private final RevCommit _commitId;
  
  
  /**
   * Constructor
   * @param commitId_p a non-null commit ID
   * @param repository_p a non-null Git repository
   */
  public GitCommitURIConverter(RevCommit commitId_p, Repository repository_p) {
    super(repository_p);
    _commitId = commitId_p;
  }
  
  /**
   * Constructor
   * @param uriHandlers_p a non-null list
   * @param contentHandlers_p a non-null list
   * @param commitId_p a non-null commit ID
   * @param repository_p a non-null Git repository
   */
  public GitCommitURIConverter(List<URIHandler> uriHandlers_p, List<ContentHandler> contentHandlers_p,
      RevCommit commitId_p, Repository repository_p) {
    super(uriHandlers_p, contentHandlers_p, repository_p);
    _commitId = commitId_p;
  }
  
  /**
   * Return the commit ID for this URI Converter
   * @return a non-null object
   */
  public RevCommit getCommitId() {
    return _commitId;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getGitFileRevision(java.lang.String)
   */
  @Override
  @SuppressWarnings("resource") // Just passing the repository as parameter
  protected IFileRevision getGitFileRevision(String gitPath_p) {
    return inCommit(getRepository(), _commitId, gitPath_p);
  }
  
}