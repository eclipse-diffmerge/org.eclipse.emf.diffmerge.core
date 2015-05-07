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
package org.eclipse.emf.diffmerge.connector.svn;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.connector.EMFDiffmergeSVNConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.LocalScopeDefinition;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.team.svn.ui.compare.ResourceCompareInput.ResourceElement;

public class SVNLocalScopeDefinitionFactory extends GMFScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>> singleton(GMFScopeDefinitionFactory.class);
	}

	@Override
	public boolean isApplicableTo(Object entrypoint) {
		return entrypoint instanceof ResourceElement && ((ResourceElement) entrypoint).isEditable();
	}

	@Override
	public IModelScopeDefinition createScopeDefinition(Object entrypoint, String label, boolean editable) {
		try {
			final ResourceElement res = (ResourceElement) entrypoint;
			final URI uri = getUri(res);
			return new LocalScopeDefinition(uri, label, editable);
		} catch (SecurityException e) {
			EMFDiffmergeSVNConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffmergeSVNConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		} catch (IllegalArgumentException e) {
			EMFDiffmergeSVNConnectorPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, EMFDiffmergeSVNConnectorPlugin.PLUGIN_ID, e.getMessage(), e));
		}
		return null;
	}

	private URI getUri(ResourceElement res) {
		return URI.createPlatformResourceURI(res.getLocalResource().getResource().getFullPath().toOSString(), true);
	}

}
