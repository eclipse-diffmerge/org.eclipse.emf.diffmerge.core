/*******************************************************************************
 * Copyright (c) 2016-2018 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import java.util.List;

import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A URI Converter for file revisions in the Git index (staging area), for no
 * conflicting cases.
 */
public class GitIndexURIConverter extends AbstractGitURIConverter {
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   */
  public GitIndexURIConverter(Repository repository_p) {
    super(repository_p);
  }
  
  /**
   * Constructor
   * @param uriHandlers_p a non-null list
   * @param contentHandlers_p a non-null list
   * @param repository_p a non-null Git repository
   */
  public GitIndexURIConverter(List<URIHandler> uriHandlers_p,
      List<ContentHandler> contentHandlers_p, Repository repository_p) {
    super(uriHandlers_p, contentHandlers_p, repository_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getGitFileRevision(java.lang.String)
   */
  @Override
  protected IFileRevision getGitFileRevision(String gitPath_p) {
    return inIndex(getRepository(), gitPath_p);
  }
  
}