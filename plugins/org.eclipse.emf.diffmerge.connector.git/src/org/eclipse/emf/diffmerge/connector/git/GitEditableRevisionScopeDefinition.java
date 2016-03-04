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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.connector.core.StreamScopeDefinition;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.domain.EditingDomain;

public class GitEditableRevisionScopeDefinition extends StreamScopeDefinition {

	public GitEditableRevisionScopeDefinition(Object entrypoint, String label, URI uri, InputStream stream, URIConverter uriConverter, boolean editable) {
		super(entrypoint, label, uri, stream, uriConverter);
		setEditable(editable);
	}

	@Override
	public IEditableModelScope createScope(Object context) {
		if (context instanceof EditingDomain) {
			ResourceSet rs=((EditingDomain)context).getResourceSet();
			rs.setURIConverter(uriConverter);
			return new GitEditableRevisionScope(uri, rs, stream, getLabel(), !isEditable());
		}
		return null;
	}
}