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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.ui.specification.ext.URIScopeDefinition;
import org.eclipse.emf.edit.domain.EditingDomain;

public final class LocalScopeDefinition extends URIScopeDefinition {
	public LocalScopeDefinition(URI uri, String label, boolean editable) {
		super(uri, label, editable);
	}

	@Override
	public IEditableModelScope createScope(Object context) {
		if (context instanceof EditingDomain)
			return new LocalScope(getEntrypoint(), ((EditingDomain)context).getResourceSet(), !isEditable());
		return null;
	}
}