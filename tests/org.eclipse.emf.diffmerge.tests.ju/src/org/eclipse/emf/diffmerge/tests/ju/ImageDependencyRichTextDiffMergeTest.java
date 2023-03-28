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

import org.eclipse.emf.diffmerge.diffdata.impl.EAttributeValuePresenceImpl;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Root;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.junit.Before;

public class ImageDependencyRichTextDiffMergeTest extends ImageDependencyDiffMergeTest {

	private final String ROOT_ID = "root";
	private final String EXISTING_ELEMENT_ID = "existingElement";
	private final String NEW_ELEMENT_ID = "newElement";
	private final String NEW_ELEMENT_CHILD_ID = "newElementChild";

	@Override
	protected int getRequiredDifferences() {
		return 4;
	}

	@Override
	protected void assertDifferences(IDifference diff) {
		assertHardcodedPathElement(diff, EXISTING_ELEMENT_ID);
		assertHardcodedPathElement(diff, NEW_ELEMENT_ID);
		assertHardcodedPathElement(diff, NEW_ELEMENT_CHILD_ID);
		assertRelativePathElement(diff, EXISTING_ELEMENT_ID);
	}

	@Override
	protected EObject getSourceRoot() {
		Root root = ElementsFactory.eINSTANCE.createRoot();
		Element existingElement = ElementsFactory.eINSTANCE.createElement();
		Element newElement = ElementsFactory.eINSTANCE.createElement();
		Element newElementChild = ElementsFactory.eINSTANCE.createElement();
		
		root.setId(ROOT_ID);
		existingElement.setId(EXISTING_ELEMENT_ID);
		newElement.setId(NEW_ELEMENT_ID);
		newElementChild.setId(NEW_ELEMENT_CHILD_ID);
		
		existingElement.setName("<p><img src=\"source project/image.png\" /></p>\n");
		newElement.setName("<p><img src=\"source project/image.png\" /></p>\n");
		newElementChild.setName("<p><img src=\"source project/image.png\" /></p>\n");

		root.getContent().add(existingElement);
		newElement.getManyContent().add(newElementChild);
		root.getContent().add(newElement);

		return root;

	}

	@Override
	protected EObject getTargetRoot() { 
		Root root = ElementsFactory.eINSTANCE.createRoot();
		Element existingElement = ElementsFactory.eINSTANCE.createElement();
		root.setId(ROOT_ID);
		existingElement.setId(EXISTING_ELEMENT_ID);

		root.getContent().add(existingElement);
		return root;
	}

	@Before
	public void initializeElements() {
		RichTextAttributeRegistry.INSTANCE.add(ElementsPackage.Literals.NAMED_ELEMENT__NAME);
	}

	private void assertHardcodedPathElement(IDifference diff, String name) {
		if (diff instanceof GElementRelativePresenceImpl) {
			GElementRelativePresenceImpl castDiff = (GElementRelativePresenceImpl) diff;
			if (castDiff.getElementMatch().getTarget() instanceof Element
					&& ((Element) castDiff.getElementMatch().getTarget()).getId().equals(name)) {
				Element element = ((Element) castDiff.getElementMatch().getTarget());
				assertEquals(String.format("Image path for %s should be hardcoded to target project.", name),
						"<p><img src=\"target project/image.png\" /></p>\n", element.getName());
				increasePassedAsserts();
			}
		}
	}

	private void assertRelativePathElement(IDifference diff, String name) {
		if (diff instanceof EAttributeValuePresenceImpl) {
			EAttributeValuePresenceImpl castDiff = (EAttributeValuePresenceImpl) diff;
			if (castDiff.getElementMatch().getTarget() instanceof Element
					&& ((Element) castDiff.getElementMatch().getTarget()).getId().equals(name)) {
				assertEquals("Image path of modified element should be made relative.",
						"<p><img src=\"./image.png\" /></p>\n", castDiff.getValue());
				increasePassedAsserts();
			}
		}
	}

}
