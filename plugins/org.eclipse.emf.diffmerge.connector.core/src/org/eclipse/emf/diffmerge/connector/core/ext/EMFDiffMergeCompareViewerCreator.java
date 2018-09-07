/**
 * Copyright (c) 2015-2018 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IViewerCreator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;


/**
 * A factory for model comparison viewers used by the Team mechanism through an extension point.
 */
public class EMFDiffMergeCompareViewerCreator implements IViewerCreator {
  
  /**
   * @see org.eclipse.compare.IViewerCreator#createViewer(org.eclipse.swt.widgets.Composite, org.eclipse.compare.CompareConfiguration)
   */
	public Viewer createViewer(Composite parent_p, CompareConfiguration configuration_p) {
		return new TeamComparisonViewer(parent_p, configuration_p);
	}
	
}
