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
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * A custom scope definition for stream resources.
 */
public class StreamScopeDefinition extends AbstractScopeDefinition {
	protected final URI uri;
	protected final InputStream stream;
	protected final URIConverter uriConverter;

	public StreamScopeDefinition(Object entrypoint, String label, URI uri, InputStream stream, URIConverter uriConverter) {
		super(entrypoint, label, false);
		this.uri=uri;
		this.stream=stream;
		this.uriConverter=uriConverter;
	}

	/**
	 * The resourceSet for StreamScope is independent.
	 * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
	 */
	@Override
	public IEditableModelScope createScope(Object context) {
		if (context instanceof EditingDomain) {
			ResourceSet rs= new ResourceSetImpl();
			rs.setURIConverter(uriConverter);
			return new StreamScope(uri, rs, stream, getLabel());
		}
		return null;
	}
}