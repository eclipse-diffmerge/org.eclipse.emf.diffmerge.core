/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.perforce.ext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.perforce.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.perforce.Messages;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

import com.perforce.p4java.core.IChangelistSummary;
import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.team.core.ConnectionParameters;
import com.perforce.team.core.PerforceTeamProvider;
import com.perforce.team.core.p4java.IP4Connection;
import com.perforce.team.core.p4java.IP4Resource;
import com.perforce.team.core.p4java.P4Workspace;


/**
 * A URI Converter for file revisions in the Perforce history.
 */
public class PerforceHistoryURIConverter extends ExtensibleURIConverterImpl {
  
  /** The Perforce hash symbol */
  public static final String PERFORCE_HASH = "#"; //$NON-NLS-1$
  
  /** The "Depot File" Perforce revision label */
  public static final String DEPOT_FILE_REVISION_LABEL = "Depot File"; //$NON-NLS-1$
  
  /** The non-null Eclipse resource */
  private final IResource _resource;
  
  /** The optional Perforce changelist */
  private IChangelistSummary _changelist;
  
  /** The optional Perforce revision label */
  private String _revision;
  
  
  /**
   * Constructor
   * @param resource_p a non-null Eclipse resource
   * @param changelist_p an optional Perforce changelist summary
   */
  public PerforceHistoryURIConverter(IResource resource_p, IChangelistSummary changelist_p) {
    _resource = resource_p;
    _changelist = changelist_p;
    _revision = null;
  }
  
  /**
   * Constructor
   * @param resource_p a non-null Eclipse resource
   * @param revision_p an optional Perforce revision label
   */
  public PerforceHistoryURIConverter(IResource resource_p, String revision_p) {
    _resource = resource_p;
    _changelist = null;
    _revision = revision_p;
  }
  
  /**
   * Constructor
   * @param resource_p a non-null Eclipse resource
   */
  public PerforceHistoryURIConverter(IResource resource_p) {
    _resource = resource_p;
    _changelist = null;
    _revision = null;
  }
  
  /**
   * The related model needs to be on the same changeList. If not, get the closest by date.
   * @see org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI,
   *      java.util.Map)
   */
  @Override
  public InputStream createInputStream(URI uri_p, Map<?, ?> options_p) throws IOException {
    if (_resource != null && _resource.exists()) {
      try {
        ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(_resource).getProjectProperties(false);
        IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
        IP4Resource p4Res = connection.getResource(getModelResource(uri_p));
        if (p4Res != null) {
          List<IFileSpec> fileSpecList = getFileSpecList(p4Res);
          if (fileSpecList != null) {
            InputStream content = connection.getServer().getFileContents(fileSpecList, false, true);
            if (content != null)
              return content;
          }
          String message;
          if (_revision != null)
            message = String.format(
                Messages.PerforceHistoryURIConverter_CannotLoadRevision, uri_p.path(), _revision);
          else
            message = String.format(
                Messages.PerforceHistoryURIConverter_CannotLoadChangelist,
                uri_p.path(), String.valueOf(_changelist.getId()));
          EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(
              IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), message));
        } else {
          EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
              EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(),
              String.format(Messages.PerforceHistoryURIConverter_CannotFindRes, uri_p.path())));
        }
      } catch (CoreException e) {
        throw new IOException(e.getMessage(), e);
      } catch (P4JavaException e) {
        throw new IOException(e.getMessage(), e);
      }
    }
    return super.createInputStream(uri_p, options_p);
  }
  
  /**
   * Return the list of file specs that correspond to the given Perforce resource
   * @param p4Res_p a non-null object
   * @return a potentially null list
   */
  protected List<IFileSpec> getFileSpecList(IP4Resource p4Res_p) {
    if (_revision == null && _changelist == null) {
      return FileSpecBuilder.makeFileSpecList(p4Res_p.getRemotePath() + PERFORCE_HASH + IFileSpec.HEAD_REVISION_STRING);
    } else if (_revision != null) {
      String revSpec = PERFORCE_HASH + IFileSpec.NONE_REVISION_STRING;
      if (_revision.startsWith(Messages.PerforceHistoryURIConverter_RemoteFile))
        revSpec = _revision.substring(_revision.indexOf(PERFORCE_HASH.charAt(0)), _revision.indexOf(')'));
      else if (_revision.startsWith(PERFORCE_HASH))
        revSpec = _revision;
      else if (DEPOT_FILE_REVISION_LABEL.equals(_revision))
        revSpec = PERFORCE_HASH + IFileSpec.HEAD_REVISION_STRING;
      return FileSpecBuilder.makeFileSpecList(p4Res_p.getRemotePath() + revSpec);
    } else if (_changelist != null) {
      return FileSpecBuilder.makeFileSpecList(p4Res_p.getRemotePath() + '@' + _changelist.getId());
    }
    return null;
  }
  
  /**
   * Return the Eclipse resource that corresponds to the given URI
   * @param uri_p a non-null URI
   * @return a potentially null object
   */
  protected IResource getModelResource(URI uri_p) {
    IResource result;
    if (uri_p.isPlatform()) {
      result = ResourcesPlugin.getWorkspace().getRoot().getFile(
          new Path(uri_p.toPlatformString(true)));
    } else {
      IPath fullPath = _resource.getFullPath().removeLastSegments(1).append(uri_p.toString());
      result = ResourcesPlugin.getWorkspace().getRoot().getFile(fullPath);
    }
    return result;
  }
  
}