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
package org.eclipse.emf.diffmerge.connector.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

/**
 * An URI Converter for localRevisions. 
 */
public final class LocalRevisionURIConverter extends ExtensibleURIConverterImpl {
	private final long timestamp;
	private final String basePath;

	public LocalRevisionURIConverter(long timestamp, String basePath) {
		super();
		this.timestamp=timestamp;
		this.basePath=basePath;
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		IFile targetFile;
		if (uri.isPlatform()) {
			targetFile=ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
		}
		else {
			IPath fullPath=new Path(basePath).append(uri.toString());
			targetFile=ResourcesPlugin.getWorkspace().getRoot().getFile(fullPath);
		}
		if (targetFile.exists()) {
			try {
				if (targetFile.getLocalTimeStamp() <= timestamp)
					return targetFile.getContents();
				IFileState[] states=targetFile.getHistory(new NullProgressMonitor());
				for (IFileState iFileState: states) {
					if (iFileState.getModificationTime() <= timestamp)
						return iFileState.getContents();
				}
			}
			catch (CoreException e) {
				EMFDiffMergeCoreConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, EMFDiffMergeCoreConnectorPlugin.getDefault().getPluginId(), e.getMessage(),e));
			}
		}
		return super.createInputStream(uri, options);
	}

}