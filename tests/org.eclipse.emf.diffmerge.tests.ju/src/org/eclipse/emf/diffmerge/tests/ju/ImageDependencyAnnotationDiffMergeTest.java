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

import org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Root;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

public class ImageDependencyAnnotationDiffMergeTest extends ImageDependencyDiffMergeTest {

	@Override
	protected int getRequiredDifferences() {
		return 0;
	}

	@Override
	protected void assertDifferences(IDifference diff) {
		assertNotImageDepsAnnotation(diff);

	}

	@Override
	protected EObject getSourceRoot() {
		DAnnotationEntry annotation = DescriptionFactory.eINSTANCE.createDAnnotationEntry();
		annotation.setSource(ImageDependenciesAnnotationHelper.IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME);

		return annotation;
	}

	@Override
	protected EObject getTargetRoot() {
		Root root = ElementsFactory.eINSTANCE.createRoot();

		return root;
	}

	private void assertNotImageDepsAnnotation(IDifference diff) {
		if (diff instanceof EElementPresenceImpl) {
			EElementPresenceImpl castDiff = (EElementPresenceImpl) diff;
			if (castDiff.getElementMatch().getReference() instanceof DAnnotationEntry) {
				assertEquals("Image dependency annotations should not be proposed for merging.", false,
						((DAnnotationEntry) castDiff.getElementMatch().getReference()).getSource()
								.equals(ImageDependenciesAnnotationHelper.IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME));
			}
		}
	}
}
