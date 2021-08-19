/**
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
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

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
import org.eclipse.emf.diffmerge.connector.core.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.ui.specification.ITimestampProvider;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;


/**
 * A URI Converter for file revisions in the local history.
 */
public class LocalHistoryURIConverter extends ExtensibleURIConverterImpl
implements ITimestampProvider {
  
  /** The time stamp of the revision, >= 0 */
  private final long _timestamp;
  
  /** The non-null base path for URIs */
  private final String _basePath;
  
  
  /**
   * Constructor
   * @param timestamp_p a positive long defining the time stamp of the revision
   * @param basePath_p a non-null string representing the base path for URIs
   */
	public LocalHistoryURIConverter(long timestamp_p, String basePath_p) {
    _timestamp = timestamp_p;
    _basePath = URI.decode(basePath_p);
	}
	
	/**
	 * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	@Override
	public InputStream createInputStream(URI uri_p, Map<?, ?> options_p) throws IOException {
	  IPath fullPath;
    if (uri_p.isPlatform()) {
      fullPath = new Path(uri_p.toPlatformString(true));
    } else {
      fullPath = new Path(_basePath).append(URI.decode(uri_p.toString()));
    }
    IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(fullPath);
    if (targetFile != null && targetFile.exists()) {
      try {
        if (targetFile.getLocalTimeStamp() <= _timestamp) {
          return targetFile.getContents();
        }
        IFileState[] states = targetFile.getHistory(new NullProgressMonitor());
        for (IFileState iFileState: states) {
          if (iFileState.getModificationTime() <= _timestamp) {
            return iFileState.getContents();
          }
        }
      } catch (CoreException e) {
        EMFDiffMergeCoreConnectorPlugin.getDefault().getLog().log(new Status(
            IStatus.ERROR, EMFDiffMergeCoreConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
    }
    return super.createInputStream(uri_p, options_p);
	}
	
	/**
	 * Return the base path for URIs
	 * @return a non-null string
	 */
	public String getBasePath() {
	  return _basePath;
	}
	
	/**
	 * @see org.eclipse.emf.diffmerge.ui.specification.ITimestampProvider#getTimestamp()
	 */
  public long getTimestamp() {
    // The time stamp of the revision, >= 0
    return _timestamp;
  }
  
}