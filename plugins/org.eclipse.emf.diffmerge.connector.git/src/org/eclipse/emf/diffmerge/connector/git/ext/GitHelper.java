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

import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.COMMIT;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.INDEX;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.INDEX_CONFLICT_OURS;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.INDEX_CONFLICT_THEIRS;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.NOT_MANAGED;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.REMOTE;
import static org.eclipse.emf.diffmerge.connector.git.ext.GitHelper.GitFileRevisionKind.WORKING_TREE;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.info.GitInfo;
import org.eclipse.egit.core.info.GitInfo.Source;
import org.eclipse.egit.core.info.GitItemState;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.git.EMFDiffMergeGitConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.git.Messages;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilterGroup;
import org.eclipse.jgit.treewalk.filter.TreeFilter;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.CachedResourceVariant;


/**
 * A helper for Git behaviors.
 */
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
   * A characterization of an IFileRevision w.r.t. Git.
   */
  public enum GitFileRevisionKind {
    /** Not managed by Git */
    NOT_MANAGED,
    /** In working tree */
    WORKING_TREE,
    /** In the index without conflict */
    INDEX,
    /** In the index, conflicting and on side 'Ours' */
    INDEX_CONFLICT_OURS,
    /** In the index, conflicting and on side 'Theirs' */
    INDEX_CONFLICT_THEIRS,
    /** In a commit */
    COMMIT,
    /** In remote resource variant */
    REMOTE
  }
  
	
	/**
	 * Constructor
	 */
	protected GitHelper() {
	  // Nothing needed
	}
	
  /**
   * Return a GitInfo for the given object, if applicable and if any
   * @param object_p a potentially null object
   * @return a potentially null object
   */
  public GitInfo getGitInfo(Object object_p) {
    return Adapters.adapt(object_p, GitInfo.class);
  }
  
  /**
   * Return the Git revision kind for the given GitInfo associated to the given
   * typed element
   * @param info_p a potentially null GitInfo
   * @param typedElement_p a potentially null typed element
   * @return a non-null object
   */
  @SuppressWarnings("resource") // See GitInfo#getRepository()
  public GitFileRevisionKind getGitKind(GitInfo info_p, ITypedElement typedElement_p) {
    GitFileRevisionKind result;
    if (info_p == null || info_p.getRepository() == null) {
      // Not managed by Git
      result = NOT_MANAGED;
    } else {
      Source source = info_p.getSource();
      if (source == Source.WORKING_TREE) {
        // Working tree
        result = WORKING_TREE;
      } else if (source == Source.INDEX) {
        // Index
        if (GitHelper.INSTANCE.isConflicting(info_p)) {
          // Conflicting
          if (typedElement_p instanceof IEditableContent) {
            result = INDEX_CONFLICT_OURS;
          } else {
            result = INDEX_CONFLICT_THEIRS;
          }
        } else {
          // Index not conflicting
          result = INDEX;
        }
      } else if (info_p instanceof CachedResourceVariant) {
        // Remote resource variant
        result = REMOTE;
      } else {
        // Commit
        result = COMMIT;
      }
    }
    return result;
  }
  
  /**
   * Return all supported Git schemes
   * @return a non-null set
   */
  public Collection<String> getGitSchemes() {
    return Arrays.asList(getSchemeCommit(), getSchemeIndex(), getSchemeRemote());
  }
  
  /**
   * Get the 'ours' commit in a conflict state for the given repository
   * @param repository_p a non-null repository
   * @param revWalk_p a non-null rev walk to use for parsing commits
   * @return a non-null commit
   * @throws IOException if the commit cannot be determined
   */
  public RevCommit getOurs(Repository repository_p, RevWalk revWalk_p)
      throws IOException {
    ObjectId ours = repository_p.resolve(Constants.HEAD);
    return revWalk_p.parseCommit(ours); // walk.markStart(head); walk.next();
  }
  
  /**
   * Return the Git repository for the given object, if any.
   * Typically applies to workspace resources (IResource) or EGit objects (IFileRevisions,
   * ITypedElements, remote resources, blob storage).
   * @param object_p a potentially null object
   * @return a potentially null object
   */
  public Repository getRepository(Object object_p) {
    Repository result = null;
    GitInfo gitInfo = getGitInfo(object_p);
    if (gitInfo != null) {
      result = gitInfo.getRepository();
    }
    if (result == null) {
      // Failure: log error
      String path = null;
      if (gitInfo != null) {
        path = gitInfo.getGitPath();
      } else  {
        IPath iPath = null;
        if (object_p instanceof IResource) {
          iPath = ((IResource) object_p).getFullPath();
        } else if (object_p instanceof IFileRevision) {
          iPath = toPath((IFileRevision) object_p);
        }
        if (iPath != null) {
          path = iPath.toString();
        }
      }
      EMFDiffMergeGitConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
          EMFDiffMergeGitConnectorPlugin.getDefault().getPluginId(),
          String.format(Messages.GitHelper_NoRepoFound, path)));
    }
    return result;
  }
  
  /**
   * Return the rev walk for the given relative path in the given repository
   * @param repository_p a non-null repository
   * @param path_p a non-null string
   * @return a non-null object
   */
  public RevWalk getRevWalk(Repository repository_p, String path_p) {
    RevWalk result = new RevWalk(repository_p);
    result.setTreeFilter(AndTreeFilter.create(
          PathFilterGroup.createFromStrings(path_p),
          TreeFilter.ANY_DIFF));
    return result;
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
   * Return whether the given GitInfo indicates a conflict (false if null)
   * @param info_p a potentially null GitInfo
   */
  public boolean isConflicting(GitInfo info_p) {
    boolean result = false;
    if (info_p != null) {
      GitItemState state = info_p.getGitState();
      result = state != null && state.hasConflicts();
    }
    return result;
  }
  
  /**
   * Return an Eclipse path for the given file revision
   * @param revision_p a non-null file revision
   * @return a potentially null path
   */
  public IPath toPath(IFileRevision revision_p) {
    IPath result = null;
    java.net.URI uri = revision_p.getURI();
    if (uri != null) {
      String uriString = URI.decode(uri.toString());
      result = new Path(uriString);
    }
    return result;
  }
  
}
