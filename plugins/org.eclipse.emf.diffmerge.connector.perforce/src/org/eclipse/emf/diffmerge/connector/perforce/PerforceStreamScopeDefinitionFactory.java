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
package org.eclipse.emf.diffmerge.connector.perforce;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffMergePerforceConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.StreamScopeDefinition;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
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
 * Stream-based perforce resource ( distant )
 */
public class PerforceStreamScopeDefinitionFactory extends GMFScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>> singleton(GMFScopeDefinitionFactory.class);
	}

	@Override
	public boolean isApplicableTo(Object entrypoint) {
		return entrypoint instanceof P4CompareNode;
	}

	@Override
	public IModelScopeDefinition createScopeDefinition(Object entrypoint, String label, boolean editable) {
		try {
			final P4CompareNode node = (P4CompareNode) entrypoint;
			final URI uri = getUri(node);
			return new StreamScopeDefinition(entrypoint, (label != null) ? label : getLabelFor(entrypoint), uri,
					node.getContents(), getURIConverter(node));
		} catch (CoreException e) {
			EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		} catch (NoSuchFieldException e) {
			EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		} catch (SecurityException e) {
			EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		} catch (IllegalArgumentException e) {
			EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		} catch (IllegalAccessException e) {
			EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		}
		return null;

	}

	protected URI getUri(P4CompareNode node) {
		return toFileURI(node.getName());
	}

	protected URIConverter getURIConverter(P4CompareNode node)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// cannot have a direct link between the p4CompareNode and the
		// IResource.
		// need to parse all known connections and depots
		String nodeLabel = node.getLabel();
		if (nodeLabel.indexOf("//") != -1) {
			for (IP4Connection conn : P4Workspace.getWorkspace().getConnections()) {
				IP4Resource ip4Resource = conn.getResource(
						nodeLabel.substring(nodeLabel.indexOf("//"), nodeLabel.length()) + "/" + node.getName());
				if (ip4Resource.isFile()) {
					IResource res = ResourcesPlugin.getWorkspace().getRoot()
							.getFileForLocation(new Path(ip4Resource.getLocalPath()));
					return new PerforceStreamURIConverter(res,
							nodeLabel.substring(nodeLabel.indexOf('#'), nodeLabel.indexOf("//") - 3));
				}
			}
		}
		if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
					.getSelection();
			if (selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection).getFirstElement();
				if (element instanceof IAdaptable) {
					IResource res = (IResource) ((IAdaptable) element).getAdapter(IResource.class);
					if (PerforceStreamURIConverter.DEPOT_FILE_REVISION_LABEL.equals(nodeLabel)) {
						return new PerforceStreamURIConverter(res,
								PerforceStreamURIConverter.DEPOT_FILE_REVISION_LABEL);
					} else if (nodeLabel.indexOf("#") != -1) {
						try {
							ConnectionParameters params = PerforceTeamProvider.getPerforceProvider(res)
									.getProjectProperties(false);
							IP4Connection connection = P4Workspace.getWorkspace().getConnection(params);
							String revision = nodeLabel.substring(nodeLabel.indexOf("#"), nodeLabel.length() - 1);
							List<IFileSpec> fileSpecList = FileSpecBuilder
									.makeFileSpecList(res.getLocation() + revision);
							GetChangelistsOptions opts = new GetChangelistsOptions();
							opts.setMaxMostRecent(1);
							if (connection.getServer() instanceof Server && !((Server) connection.getServer())
									.getChangelists(fileSpecList, opts).isEmpty()) {
								IChangelistSummary changeList = ((Server) connection.getServer())
										.getChangelists(fileSpecList, opts).get(0);
								return new PerforceStreamURIConverter(res, changeList);
							} else {
								EMFDiffMergePerforceConnectorPlugin.getInstance().getLog()
										.log(new Status(IStatus.ERROR, EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID,
												"Cannot load changeList for resource: " + res));
							}
						} catch (CoreException e) {
							EMFDiffMergePerforceConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR,
									EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
						} catch (P4JavaException e) {
							EMFDiffMergePerforceConnectorPlugin.getInstance().getLog().log(new Status(IStatus.ERROR,
									EMFDiffMergePerforceConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
						}
					}
				}
			}
		}
		return ExtensibleURIConverterImpl.INSTANCE;
	}

	@Override
	protected String getLabelFor(Object entrypoint) {
		String label = ((P4CompareNode) entrypoint).getLabel();
		if (label.indexOf("//") != -1) {
			return ((P4CompareNode) entrypoint).getName() + " ["
					+ label.substring(label.indexOf('#'), label.indexOf("//") - 3) + "]";
		}
		return ((P4CompareNode) entrypoint).getName() + " [" + label + "]";
	}
}
