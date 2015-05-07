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
package org.eclipse.emf.diffmerge.connector.git;

import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A custom scope for git editable resources.
 */
public final class GitEditableRevisionScope extends GMFScope {

	private final InputStream stream;
	private final String label;

	public GitEditableRevisionScope(URI uri, ResourceSet rs, InputStream stream, String label, boolean readOnly) {
		super(uri, rs, readOnly);
		this.stream = stream;
		this.label = label;
	}

	/**
	 * Loading a Git Editable revision via an InputStream. The first one is
	 * loaded, no multi root resource is supported.
	 */
	@Override
	public boolean load() throws Exception {
		if (_state == ScopeState.INITIALIZED || _state == ScopeState.LOADED) {
			if (!_rootResources.isEmpty() && stream != null) {
				Resource rootResource = _rootResources.get(0);
				Map<?, ?> options = getLoadOptions(rootResource);
				rootResource.load(stream, options);
				_state = ScopeState.LOADED;
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getOriginator() {
		return label;
	}

}