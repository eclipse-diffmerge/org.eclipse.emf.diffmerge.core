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
 *    Stephane Bouchet (Intel Corporation) - bug #496397
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;


/**
 * A URI Converter for file revisions in the Git index (staging area), holding role "Theirs"
 * in the case of conflicts.
 */
public class GitIndexTheirsURIConverter extends AbstractGitConflictURIConverter {
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   * @param holdingResourcePath_p the non-null path of the holding resource in conflict
   */
  public GitIndexTheirsURIConverter(Repository repository_p,String holdingResourcePath_p) {
    super(repository_p, DirCacheEntry.STAGE_3, holdingResourcePath_p); // "Theirs" in conflict resolution
  }
  
}