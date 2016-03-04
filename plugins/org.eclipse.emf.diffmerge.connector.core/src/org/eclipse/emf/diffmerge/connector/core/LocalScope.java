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
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A custom scope for local resources.
 */
public final class LocalScope extends GMFScope {
	public LocalScope(URI uri, ResourceSet resourceSet, boolean readOnly) {
		super(uri, resourceSet, readOnly);
	}

	@Override
	public Object getOriginator() {
		return "Local: " + UIUtil.simplifyURI(getHoldingResource().getURI());
	}

}