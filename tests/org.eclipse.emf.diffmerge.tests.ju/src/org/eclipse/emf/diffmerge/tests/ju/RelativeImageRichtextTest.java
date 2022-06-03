/*********************************************************************
 * Copyright (c) 2022 Thales Global Services S.A.S.
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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.sirius.SiriusScope;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Root;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.junit.BeforeClass;
import org.junit.Test;

public class RelativeImageRichtextTest {

  static Element element;
  static SiriusScope scope;

  @BeforeClass
  public static void createSessionWithElement() {
    Root root = ElementsFactory.eINSTANCE.createRoot();
    Element e = ElementsFactory.eINSTANCE.createElement();
    e.setId("a");
    RichTextAttributeRegistry.INSTANCE.add(ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    root.getContent().add(e);

    URI semantic = URI.createPlatformResourceURI("a/a.elements", true);
    ProjectHelper.saveSemanticResource(semantic, root);
    Session session = ProjectHelper.createSessionOn(semantic);
    scope = new SiriusScope(session.getSessionResource().getURI(), session.getTransactionalEditingDomain(), true);
    element = (Element) session.getSemanticResources().iterator().next().getEObject("a");
  }

  protected void setName(Element element, String name) {
    scope.getSession().getTransactionalEditingDomain().getCommandStack()
        .execute(new RecordingCommand(scope.getSession().getTransactionalEditingDomain()) {
          @Override
          protected void doExecute() {
            element.setName(name);
          }
        });
  }

  @Test
  public void rootProject() {
    setName(element, "My image is <img src=\"a/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Image on root project shall be changed", "My image is <img src=\"./image.png\"></img>",
        object.iterator().next().toString());
  }

  @Test
  public void otherProject() {
    setName(element, "My image is <img src=\"b/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Image on other project shall not be changed", "My image is <img src=\"b/image.png\"></img>",
        object.iterator().next().toString());
  }

  @Test
  public void many() {
    setName(element,
        "My image is <img src=\"a/image.png\"></img> <img src=\"b/image.png\"></img> <img src=\"a/image.png\"></img> <img src=\"b/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Many images shall be changed",
        "My image is <img src=\"./image.png\"></img> <img src=\"b/image.png\"></img> <img src=\"./image.png\"></img> <img src=\"b/image.png\"></img>",
        object.iterator().next().toString());
  }

  @Test
  public void otherReferences() {
    setName(element, "My image is <a href=\"a/image.png\"> ../a/image.png");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Other kind of references shall not be changed", "My image is <a href=\"a/image.png\"> ../a/image.png",
        object.iterator().next().toString());
  }

  @Test
  public void subFolderWithProjectName() {
    setName(element, "My image is <img src=\"b/a/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Sub folder with project name shall not be changed", "My image is <img src=\"b/a/image.png\"></img>",
        object.iterator().next().toString());
  }

  @Test
  public void subFolderWithProtocol() {
    setName(element, "My image is <img src=\"protocol:/a/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Sub folder with project name shall be changed", "My image is <img src=\"protocol:/./image.png\"></img>",
        object.iterator().next().toString());
  }
  
  @Test
  public void rootWithSubFolder() {
    setName(element, "My image is <img src=\"a/b/image.png\"></img>");
    List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    assertEquals("Image on sub folder of root project shall be changed",
        "My image is <img src=\"./b/image.png\"></img>", object.iterator().next().toString());
  }

  @Test
  public void nonRegisteredAttribute() {
    try {
      RichTextAttributeRegistry.INSTANCE.getEAttributes().remove(ElementsPackage.Literals.NAMED_ELEMENT__NAME);
      setName(element, "My image is <img src=\"a/image.png\"></img>");
      List<Object> object = scope.get(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME);
      assertEquals("Relative on unregistered attribute shall not be changed", "My image is <img src=\"a/image.png\"></img>",
          object.iterator().next().toString());
      
    } finally {
      RichTextAttributeRegistry.INSTANCE.add(ElementsPackage.Literals.NAMED_ELEMENT__NAME);
    }
  }

  
}
