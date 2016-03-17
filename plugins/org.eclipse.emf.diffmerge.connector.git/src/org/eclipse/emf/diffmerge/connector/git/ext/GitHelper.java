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

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.revision.LocalFileRevision;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;


/**
 * A helper for Git behaviors.
 */
@SuppressWarnings("restriction") // Specific EGit behaviors
public final class GitHelper {
  
  /** The singleton instance */
  public static final GitHelper INSTANCE = new GitHelper();
  
  /** Scheme for remote resources */
	private static final String REMOTE_SCHEME = "remote"; //$NON-NLS-1$

  /** Scheme for index revisions */
	private static final String INDEX_SCHEME = "index"; //$NON-NLS-1$
  
	/** Scheme for commit revisions */
	private static final String COMMIT_SCHEME = "commit"; //$NON-NLS-1$
	
  /** Post-scheme separator */
  private static final String SCHEME_SEP = ":/"; //$NON-NLS-1$
	
	
	/**
	 * Constructor
	 */
	protected GitHelper() {
	  // Nothing needed
	}
	
  /**
   * Return all supported Git schemes
   * @return a non-null set
   */
  public Collection<String> getGitSchemes() {
    return Arrays.asList(getSchemeCommit(), getSchemeIndex(), getSchemeRemote());
  }
  
  /**
   * Return the Git repository for the given resource path, if any
   * @param path_p a non-null path
   * @return a potentially null object
   */
  public Repository getRepository(IPath path_p) {
    // First look directly for connected projects using the repository mapping
    if (RepositoryMapping.getMapping(path_p) != null)
      return RepositoryMapping.getMapping(path_p).getRepository();
    // Then iterate over known repositories.
    for (Repository repo: Activator.getDefault().getRepositoryCache().getAllRepositories()) {
      Path fullPath=new Path(repo.getWorkTree().toString().concat(path_p.makeAbsolute().toString()));
      if (fullPath.toFile().exists())
        return repo;
    }
    EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
        EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
        "Cannot find Git repository for resource at: " + path_p)); //$NON-NLS-1$
    return null;
  }
  
  /**
   * Return the Git repository for the given revision
   * @param revision_p a potentially null revision
   * @return a potentially null object
   */
  public Repository getRepository(IFileRevision revision_p) {
    if (revision_p != null) {
      IPath revisionPath = new Path(revision_p.getURI().toString());
      if (!revisionPath.isAbsolute())
        return getRepository(revisionPath);
      else if (revision_p instanceof LocalFileRevision) {
        IFile file = ((LocalFileRevision)revision_p).getFile();
        return getRepository(file.getFullPath());
      }
      EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
          EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
          String.format(Messages.GitHelper_NoRepoFound, revisionPath)));
    }
    return null;
  }
  
  /**
   * Return the scheme for commit revisions
   * @return a non-null string
   */
  public String getSchemeCommit() {
    return COMMIT_SCHEME;
  }
  
  /**
   * Return the scheme for index revisions
   * @return a non-null string
   */
  public String getSchemeIndex() {
    return INDEX_SCHEME;
  }
  
  /**
   * Return the scheme for remote revisions
   * @return a non-null string
   */
  public String getSchemeRemote() {
    return REMOTE_SCHEME;
  }
  
  /**
   * Return the separator that follows the scheme
   * @return a non-null string
   */
  public String getSchemeSeparator() {
    return SCHEME_SEP;
  }
  
  /**
   * Return whether there is a conflict on the given revision
   * @param revision_p a non-null revision
   */
  @SuppressWarnings("resource")
  public boolean isConflicting(IFileRevision revision_p)
      throws NoWorkTreeException, CorruptObjectException, IOException {
    boolean result = false;
    Repository repo = getRepository(revision_p);
    if (repo != null)
      result = isConflicting(repo, revision_p);
    return result;
  }
  
  /**
   * Return whether there is a conflict on the given revision in the given repository
   * @param repository_p a non-null repository
   * @param revision_p a non-null file revision
   * @throws NoWorkTreeException
   * @throws CorruptObjectException
   * @throws IOException
   */
  public boolean isConflicting(Repository repository_p, IFileRevision revision_p)
      throws NoWorkTreeException, CorruptObjectException, IOException {
    IPath revisionPath=new Path(revision_p.getURI().toString());
    if (!revisionPath.isAbsolute())
      return isConflicting(repository_p, revisionPath.toString());
    else if (revision_p instanceof LocalFileRevision) {
      IFile file = ((LocalFileRevision)revision_p).getFile();
      return isConflicting(repository_p, file.getFullPath().makeRelative().toString());
    }
    EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
        EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
        String.format(Messages.GitHelper_NoConflictInfoFound, revisionPath)));
    return false;
  }
  
  /**
   * Return whether there is a conflict on the given path in the given repository
   * @param repository_p a non-null repository
   * @param path_p a non-null string
   * @throws NoWorkTreeException
   * @throws CorruptObjectException
   * @throws IOException
   */
  public boolean isConflicting(Repository repository_p, String path_p)
      throws NoWorkTreeException, CorruptObjectException, IOException {
    return repository_p.readDirCache().getEntry(path_p).getStage() > 0;
  }
  
}
