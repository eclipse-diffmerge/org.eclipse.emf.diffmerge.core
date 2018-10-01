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
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.storage.GitFileRevision;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jgit.dircache.DirCacheCheckout;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A base URI Converter for Git.
 */
@SuppressWarnings("restriction")
public abstract class AbstractGitURIConverter extends ExtensibleURIConverterImpl {
  
  /** The "inCommit" method for EGit version 5 */
  private static Method __inCommitEGit5Method = null;
  /** The "inCommit" method for EGit prior to version 5 */
  private static Method __inCommitLegacyMethod = null;
  
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
   * Check which "inCommit" method is provided by EGit/JGit
   */
  @SuppressWarnings("nls")
  private static void checkEGitMethod() {
    if (__inCommitEGit5Method == null && __inCommitLegacyMethod == null) {
      // Not initialized (successfully) yet
      final String inCommitMethodName = "inCommit";
      // Looking for EGit 5 method
      // First, looking for EGit 5 parameter class for the method
      final String egit5ParameterClassName = "CheckoutMetadata";
      Class<?> egit5ParameterClass = null;
      for (Class<?> nested : DirCacheCheckout.class.getDeclaredClasses()) {
        if (egit5ParameterClassName.equals(nested.getSimpleName())) {
          egit5ParameterClass = nested;
          break;
        }
      }
      if (egit5ParameterClass != null) {
        // EGit 5 parameter class found: using it to retrieve EGit 5 method
        try {
          __inCommitEGit5Method = GitFileRevision.class.getMethod(
              inCommitMethodName, Repository.class,
              RevCommit.class, String.class, ObjectId.class,
              egit5ParameterClass);
        } catch (NoSuchMethodException e) {
          // Failure: method not found
        }
      }
      if (__inCommitEGit5Method == null) {
        // EGit 5 method not found, looking for legacy method
        try {
          __inCommitLegacyMethod = GitFileRevision.class.getMethod(
              inCommitMethodName, Repository.class,
              RevCommit.class, String.class, ObjectId.class);
        } catch (Exception e) {
          // Failure too, which is unexpected
          e.printStackTrace();
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI, java.util.Map)
   */
  @Override
  public InputStream createInputStream(URI uri_p, Map<?, ?> options_p) throws IOException {
    Repository repo = _repository;
    if (repo != null && isSupportedURI(uri_p)) {
      // Try to locate the file locally
      String pathRepresentation = getURIPathRepresentation(uri_p);
      IPath newPath = new Path(repo.getWorkTree().getAbsolutePath()).append(pathRepresentation);
      File target = newPath.toFile();
      if (target != null && target.exists()) {
        try {
          String gitPath = pathRepresentation.substring(1);
          return getGitFileRevision(gitPath).getStorage(new NullProgressMonitor()).getContents();
        } catch (CoreException e) {
          EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
              EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
        }
      } else {
        EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
            String.format(Messages.AbstractGitURIConverter_CannotFindResource, newPath)));
      }
    }
    return super.createInputStream(uri_p, options_p);
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
   * See GitFileRevision#inCommit(...)
   */
  protected GitFileRevision inCommit(Repository db_p, RevCommit commit_p, String path_p,
      ObjectId blobId_p) {
    checkEGitMethod();
    Object returned = null;
    try {
      if (__inCommitEGit5Method != null) {
        returned = __inCommitEGit5Method.invoke(
            null, db_p, commit_p, path_p, blobId_p, null);
      } else if (__inCommitLegacyMethod != null) {
        returned = __inCommitLegacyMethod.invoke(
            null, db_p, commit_p, path_p, blobId_p);
      }
    } catch (Exception e) {
      // Unexpected
      e.printStackTrace();
    }
    GitFileRevision result = (returned instanceof GitFileRevision)?
        (GitFileRevision)returned: null;
    return result;
  }
  
  /**
   * @see GitFileRevision#inIndex(Repository, String)
   */
  protected GitFileRevision inIndex(Repository db_p, String path_p) {
    return GitFileRevision.inIndex(db_p, path_p);
  }
  
  /**
   * @see GitFileRevision#inIndex(Repository, String, int)
   */
  protected GitFileRevision inIndex(Repository db_p, String path_p, int stage_p) {
    return GitFileRevision.inIndex(db_p, path_p, stage_p);
  }
  
  /**
   * Return whether the given URI us supported
   * @param uri_p a non-null URI
   */
  protected boolean isSupportedURI(URI uri_p) {
    return GitHelper.INSTANCE.getGitSchemes().contains(uri_p.scheme());
  }
  
}