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

import org.eclipse.emf.common.util.URI;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;


/**
 * A URI Converter for file revisions in the Git index (staging area), holding role "Ours"
 * in the case of conflicts.
 */
public class GitIndexOursURIConverter extends AbstractGitConflictURIConverter {

  /**
   * Constructor
   * @param repository_p a non-null Git repository
   * @param holdingResourcePath_p the non-null path of the holding resource in conflict
   */
  public GitIndexOursURIConverter(Repository repository_p,String holdingResourcePath_p) {
    super(repository_p, DirCacheEntry.STAGE_2, holdingResourcePath_p); // "Ours" in conflict resolution
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getURIPathRepresentation(org.eclipse.emf.common.util.URI)
   */
  @Override
  protected String getURIPathRepresentation(URI uri_p) {
    return uri_p.toPlatformString(true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#isSupportedURI(org.eclipse.emf.common.util.URI)
   */
  @Override
  protected boolean isSupportedURI(URI uri_p) {
    return uri_p.isPlatformResource();
  }
  
}