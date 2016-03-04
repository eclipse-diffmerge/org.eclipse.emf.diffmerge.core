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

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A custom scope for stream resources.
 */
public final class StreamScope extends GMFScope {
	private final InputStream stream;
	private final String label;

	public StreamScope(URI uri, ResourceSet rs, InputStream stream, String label) {
		super(uri, rs, true);
		this.stream=stream;
		this.label=label;
	}

	@Override
	public boolean load() throws Exception {
		// there is only one resource to be loaded, the graphical one. the semantic is handled by the URIConverter
		if (!_rootResources.isEmpty() && stream != null) {
			_rootResources.get(0).load(stream, getLoadOptions(_rootResources.get(0)));
			return true;
		}
		return false;
	}

	@Override
	public Object getOriginator() {
		return label;
	}

}