/*********************************************************************
 * Copyright (c) 2023 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.tests.ju;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.WorkspaceImage;

public class ImageDependencyWorkspaceImageDiffMergeTest extends ImageDependencyDiffMergeTest {
	private final String NODE_ID = "node";

	@Override
	protected int getRequiredDifferences() {
		return 1;
	}

	@Override
	protected void assertDifferences(IDifference diff) {
		assertPathWorkspaceImage(diff);

	}

	@Override
	protected EObject getSourceRoot() {
		DNodeContainer node = DiagramFactory.eINSTANCE.createDNodeContainer();
		node.setUid(NODE_ID);
		WorkspaceImage workspaceImage = DiagramFactory.eINSTANCE.createWorkspaceImage();
		workspaceImage.setWorkspacePath("source project/test.PNG");
		node.setOwnedStyle(workspaceImage);

		return node;
	}

	@Override
	protected EObject getTargetRoot() {
		DNodeContainer node = DiagramFactory.eINSTANCE.createDNodeContainer();
		node.setUid(NODE_ID);
		return node;
	}

	private void assertPathWorkspaceImage(IDifference diff) {
		if (diff instanceof GElementRelativePresenceImpl) {
			GElementRelativePresenceImpl castDiff = (GElementRelativePresenceImpl) diff;
			if (castDiff.getElementMatch().getTarget() instanceof WorkspaceImage) {
				WorkspaceImage workspaceImage = ((WorkspaceImage) castDiff.getElementMatch().getTarget());
				assertEquals("Image path should be hardcoded to target project.", "target project/test.PNG",
						workspaceImage.getWorkspacePath());
				increasePassedAsserts();
			}
		}
	}
}
