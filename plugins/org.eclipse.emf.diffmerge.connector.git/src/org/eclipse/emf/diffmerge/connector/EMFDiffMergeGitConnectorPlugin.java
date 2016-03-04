/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.revision.LocalFileRevision;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class EMFDiffMergeGitConnectorPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID="org.eclipse.emf.diffmerge.connector.git";
	private static EMFDiffMergeGitConnectorPlugin instance;

	public EMFDiffMergeGitConnectorPlugin() {
		super();
	}

	public static EMFDiffMergeGitConnectorPlugin getInstance() {
		return instance;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance=this;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		instance=null;
		super.stop(context);
	}

	public Repository getRepo(IPath path) {
		// first look directly using the repositorymapping for connected projects
		if (RepositoryMapping.getMapping(path) != null) {
			return RepositoryMapping.getMapping(path).getRepository();
		}
		// then iterate over known repositories.
		for (Repository repo: Activator.getDefault().getRepositoryCache().getAllRepositories()) {
			Path fullPath=new Path(repo.getWorkTree().toString().concat(path.makeAbsolute().toString()));
			if (fullPath.toFile().exists())
				return repo;
		}
		getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "Cannot find Git repository for resource at: " + path));
		return null;
	}

	public Repository getRepo(IFileRevision revision) {
		if (revision != null) {
			IPath revisionPath=new Path(revision.getURI().toString());
			if (!revisionPath.isAbsolute())
				return getRepo(revisionPath);
			else if (revision instanceof LocalFileRevision) {
				IFile file=((LocalFileRevision)revision).getFile();
				return getRepo(file.getFullPath());
			}
			getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "Cannot find Git repository for resource at: " + revisionPath));
		}
		return null;
	}

	public boolean isConflicting(Repository repo, IFileRevision revision) throws NoWorkTreeException, CorruptObjectException, IOException {
		IPath revisionPath=new Path(revision.getURI().toString());
		if (!revisionPath.isAbsolute())
			return isConflicting(repo, revisionPath.toString());
		else if (revision instanceof LocalFileRevision) {
			IFile file=((LocalFileRevision)revision).getFile();
			return isConflicting(repo, file.getFullPath().makeRelative().toString());
		}
		getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "Cannot find conflict information for resource at: " + revisionPath));
		return false;
	}

	public boolean isConflicting(Repository repo, String path) throws NoWorkTreeException, CorruptObjectException, IOException {
		return repo.readDirCache().getEntry(path).getStage() > 0;
	}
}
