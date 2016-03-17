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

import static org.eclipse.emf.diffmerge.connector.perforce.ext.PerforceHistoryURIConverter.DEPOT_FILE_REVISION_LABEL;
import static org.eclipse.emf.diffmerge.connector.perforce.ext.PerforceHistoryURIConverter.PERFORCE_HASH;

import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.connector.perforce.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.perforce.Messages;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.perforce.p4java.core.IChangelistSummary;
import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.p4java.impl.mapbased.server.Server;
import com.perforce.p4java.option.server.GetChangelistsOptions;
import com.perforce.team.core.ConnectionParameters;
import com.perforce.team.core.PerforceTeamProvider;
import com.perforce.team.core.p4java.IP4Connection;
import com.perforce.team.core.p4java.IP4Resource;
import com.perforce.team.core.p4java.P4Workspace;
import com.perforce.team.ui.editor.P4CompareNode;


/**
 * A scopes definition factory for remote Perforce files.
 */
public class PerforceRemoteScopeDefinitionFactory extends AbstractURIConvertingScopeDefinitionFactory {
  
  /** The "double slash" constant */
  protected static final String DOUBLE_SLASH = "//"; //$NON-NLS-1$
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#convertToURI(java.lang.Object)
   */
  @Override
  protected URI convertToURI(Object entrypoint_p) {
    return toFileURI(((P4CompareNode)entrypoint_p).getName());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#getLabelFor(java.lang.Object)
   */
  @Override
  protected String getLabelFor(Object entrypoint_p) {
    String result;
    String label = ((P4CompareNode) entrypoint_p).getLabel();
    if (label.indexOf(DOUBLE_SLASH) != -1) {
      result = ((P4CompareNode) entrypoint_p).getName() + " [" //$NON-NLS-1$
          + label.substring(label.indexOf(PERFORCE_HASH.charAt(0)),
              label.indexOf(DOUBLE_SLASH) - 3) + "]"; //$NON-NLS-1$
    } else {
      result = ((P4CompareNode) entrypoint_p).getName() + " [" + label + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    return result;
  }
  
  /**
   * Return a URIConverter for the given Perforce compare node
   * @param node_p a non-null object
   * @return a potentially null object
   * @throws NoSuchFieldException
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  protected URIConverter getNodeURIConverter(P4CompareNode node_p)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    // Cannot have a direct link between the p4CompareNode and the IResource.
    // Need to parse all known connections and depots.
    String nodeLabel = node_p.getLabel();
    if (nodeLabel.indexOf(DOUBLE_SLASH) != -1) {
      for (IP4Connection conn : P4Workspace.getWorkspace().getConnections()) {
        IP4Resource ip4Resource = conn.getResource(
            nodeLabel.substring(nodeLabel.indexOf(DOUBLE_SLASH),
                nodeLabel.length()) + DOUBLE_SLASH + node_p.getName());
        if (ip4Resource.isFile()) {
          IResource res = ResourcesPlugin.getWorkspace().getRoot()
              .getFileForLocation(new Path(ip4Resource.getLocalPath()));
          return new PerforceHistoryURIConverter(res,
              nodeLabel.substring(nodeLabel.indexOf(PERFORCE_HASH.charAt(0)),
                  nodeLabel.indexOf(DOUBLE_SLASH) - 3));
        }
      }
    }
    if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
      ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
          .getSelection();
      if (selection instanceof IStructuredSelection) {
        Object element = ((IStructuredSelection) selection).getFirstElement();
        if (element instanceof IAdaptable) {
          @SuppressWarnings("cast") // Compatibility with older versions of Eclipse
          IResource res = (IResource) ((IAdaptable) element).getAdapter(IResource.class);
          if (DEPOT_FILE_REVISION_LABEL.equals(nodeLabel)) {
            return new PerforceHistoryURIConverter(res,
                DEPOT_FILE_REVISION_LABEL);
          } else if (nodeLabel.indexOf(PERFORCE_HASH) != -1) {
            try {
              ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(res)
                  .getProjectProperties(false);
              IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
              String revision = nodeLabel.substring(nodeLabel.indexOf(PERFORCE_HASH), nodeLabel.length() - 1);
              List<IFileSpec> fileSpecList = FileSpecBuilder
                  .makeFileSpecList(res.getLocation() + revision);
              GetChangelistsOptions opts = new GetChangelistsOptions();
              opts.setMaxMostRecent(1);
              if (connection.getServer() instanceof Server && !((Server) connection.getServer())
                  .getChangelists(fileSpecList, opts).isEmpty()) {
                IChangelistSummary changeList = ((Server) connection.getServer())
                    .getChangelists(fileSpecList, opts).get(0);
                return new PerforceHistoryURIConverter(res, changeList);
              }
              EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(
                  IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(),
                  Messages.PerforceStreamScopeDefinitionFactory_CannotLoadChangelist + res));
            } catch (CoreException e) {
              EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                  EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
            } catch (P4JavaException e) {
              EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
                  EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
            }
          }
        }
      }
    }
    return URIConverter.INSTANCE;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getStream(java.lang.Object)
   */
  @Override
  protected InputStream getStream(Object entrypoint_p) throws CoreException {
    InputStream result;
    if (entrypoint_p instanceof P4CompareNode) {
      try {
        result = ((P4CompareNode)entrypoint_p).getContents();
      } catch (CoreException e) {
        EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
            EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
        result = null;
      }
    } else {
      result = super.getStream(entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractURIConvertingScopeDefinitionFactory#getURIConverter(java.lang.Object)
   */
  @Override
  protected URIConverter getURIConverter(Object entrypoint_p) {
    URIConverter result = null;
    try {
      result = getNodeURIConverter((P4CompareNode)entrypoint_p);
    } catch (Exception e) {
      EMFDiffMergePerforceConnectorPlugin.getDefault().getLog().log(new Status(IStatus.ERROR,
          EMFDiffMergePerforceConnectorPlugin.getDefault().getPluginId(), e.getMessage(), e));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  @Override
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof P4CompareNode;
  }
  
}
