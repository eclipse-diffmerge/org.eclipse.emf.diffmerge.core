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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.compare.IResourceProvider;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.gmf.GMFScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;

/**
 * A Scope Definition Factory for local resources.
 */
public class LocalScopeDefinitionFactory extends GMFScopeDefinitionFactory {

	@Override
	public Collection<? extends Class<?>> getOverridenClasses() {
		return Collections.<Class<?>>singleton(GMFScopeDefinitionFactory.class);
	}

	@Override
	public boolean isApplicableTo(Object entrypoint) {
		return entrypoint instanceof IFile || entrypoint instanceof IResourceProvider;
	}

	@Override
	protected URI convertToURI(Object entrypoint) {
		if (entrypoint instanceof IResourceProvider)
			return super.convertToURI(((IResourceProvider)entrypoint).getResource());
		return super.convertToURI(entrypoint);
	}

	/**
	 * @see org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinitionFactory#createScopeDefinitionFromURI(org.eclipse.emf.common.util.URI, java.lang.String, boolean)
	 */
	@Override
	protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri, String label, boolean editable) {
		return new LocalScopeDefinition(uri, label, editable);
	}
}
