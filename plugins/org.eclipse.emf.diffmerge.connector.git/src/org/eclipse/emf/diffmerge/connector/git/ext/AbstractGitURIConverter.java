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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.revisions.FileRevisionFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A base URI Converter for Git.
 */
public abstract class AbstractGitURIConverter extends ExtensibleURIConverterImpl {
  
  /** The non-null Git repository */
  private final Repository _repository;
  
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   */
  public AbstractGitURIConverter(Repository repository_p){
    super();
    _repository = repository_p;
  }
  
  /**
   * Constructor
   * @param uriHandlers_p a non-null list
   * @param contentHandlers_p a non-null list
   * @param repository_p a non-null Git repository
   */
  public AbstractGitURIConverter(List<URIHandler> uriHandlers_p,
      List<ContentHandler> contentHandlers_p, Repository repository_p) {
    super(uriHandlers_p, contentHandlers_p);
    _repository = repository_p;
  }
  
  /**
   * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#exists(org.eclipse.emf.common.util.URI,
   *      java.util.Map)
   */
  @Override
  public boolean exists(URI uri_p, Map<?, ?> options_p) {
    try {
      Repository repo = _repository;
      if (repo != null && isSupportedURI(uri_p)) {
        // Try to locate the file locally
        IPath absoluteFilePath = getAbsoluteFilePath(uri_p, repo);
        IPath absoluteRepoPath = new Path(
            repo.getWorkTree().getCanonicalPath());

        String gitPath = absoluteFilePath.makeRelativeTo(absoluteRepoPath).toString();
        return getGitFileRevision(gitPath).exists();
      }
    } catch (Exception e) {
      // Nothing here
    }
    return super.exists(uri_p, options_p);
  }

  /**
   * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI,
   *      java.util.Map)
   */
  @Override
  public InputStream createInputStream(URI uri_p, Map<?, ?> options_p)
      throws IOException {
    Repository repo = _repository;
    if (repo != null && isSupportedURI(uri_p)) {
      // Try to locate the file locally  
      IPath absoluteFilePath = getAbsoluteFilePath(uri_p, repo);
      IPath absoluteRepoPath = new Path(repo.getWorkTree().getCanonicalPath());
      File target = absoluteFilePath.toFile();
      String gitPath = absoluteFilePath.makeRelativeTo(absoluteRepoPath)
          .toString();
      if (target != null && target.exists()) {
        try {
          return getGitFileRevision(gitPath).getStorage(new NullProgressMonitor()).getContents();
        } catch (CoreException e) {
          EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(
              new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
                  e.getMessage(), e));
        }
      } else {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(
            new Status(IStatus.ERROR, EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
                String.format(
                    Messages.AbstractGitURIConverter_CannotFindResource,
                    absoluteFilePath)));
      }
    }
    return super.createInputStream(uri_p, options_p);
  }
  
  /**
   * Return the absolute file path for the given URI
   * @param uri_p a non-null URI
   * @param repository_p a non-null repository
   * @return a non-null path
   */
  protected IPath getAbsoluteFilePath(URI uri_p, Repository repository_p) {
    String pathRepresentation = getURIPathRepresentation(uri_p);
    IPath result;
    if (uri_p.isPlatform()) {
      result = new Path(pathRepresentation);
    } else {
      result = new Path(repository_p.getWorkTree().getAbsolutePath()).append(pathRepresentation);
    }
    return result;
  }
  
  /**
   * Return the file revision for the given path
   * @param gitPath_p a non-null string
   * @return a potentially null object
   */
  protected abstract IFileRevision getGitFileRevision(String gitPath_p);
  
  /**
   * Return the Git repository for this URIConverter
   * @return a non-null object
   */
  protected Repository getRepository() {
    return _repository;
  }
  
  /**
   * Return a representation of the given URI as a usable path
   * @param uri_p a non-null URI
   * @return a non-null string
   */
  protected String getURIPathRepresentation(URI uri_p) {
    String result = uri_p.devicePath();
    if (result != null) {
      result = URI.decode(result);
    }
    return result;
  }
  
  /**
   * See FileRevisionFactory#inCommit(Repository, RevCommit, String)
   */
  protected IFileRevision inCommit(Repository db_p, RevCommit commit_p, String path_p) {
    return FileRevisionFactory.inCommit(db_p, commit_p, path_p);
    }
  
  /**
   * @see FileRevisionFactory#inIndex(Repository, String)
   */
  protected IFileRevision inIndex(Repository db_p, String path_p) {
    return FileRevisionFactory.inIndex(db_p, path_p);
  }
  
  /**
   * @see FileRevisionFactory#inIndex(Repository, String, int)
   */
  protected IFileRevision inIndex(Repository db_p, String path_p, int stage_p) {
    return FileRevisionFactory.inIndex(db_p, path_p, stage_p);
  }
  
  /**
   * Return whether the given URI us supported
   * @param uri_p a non-null URI
   */
  protected boolean isSupportedURI(URI uri_p) {
    return GitHelper.INSTANCE.getGitSchemes().contains(uri_p.scheme());
  }
  
}