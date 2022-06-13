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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.junit.BeforeClass;
import org.junit.Test;

public class RelativeWorkspaceImageTest {

  static SiriusScope scope;
  static WorkspaceImage element;

  @BeforeClass
  public static void createSessionWithElement() {
    WorkspaceImage img = DiagramFactory.eINSTANCE.createWorkspaceImage();
    URI semantic = URI.createPlatformResourceURI("a/a.elements", true);
    ProjectHelper.saveSemanticResource(semantic, img);
    Session session = ProjectHelper.createSessionOn(semantic);
    scope = new SiriusScope(session.getSessionResource().getURI(), session.getTransactionalEditingDomain(), true);
    element = (WorkspaceImage) session.getSemanticResources().iterator().next().getContents().iterator().next();
  }

  protected void setPath(WorkspaceImage element, String path) {
    scope.getEditingDomain().getCommandStack()
        .execute(new RecordingCommand((TransactionalEditingDomain) scope.getEditingDomain()) {
          @Override
          protected void doExecute() {
            element.setWorkspacePath(path);
          }
        });
  }

  @Test
  public void rootProject() {
    setPath(element, "a/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Image on root project shall be changed", "./image.png", object.iterator().next().toString());
  }

  @Test
  public void otherProject() {
    setPath(element, "b/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Image on other project shall not be changed", "b/image.png", object.iterator().next().toString());
  }

  @Test
  public void subFolderWithProjectName() {
    setPath(element, "b/a/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Sub folder with project name shall not be changed", "b/a/image.png",
        object.iterator().next().toString());
  }

  @Test
  public void subFolderWithProtocol() {
    setPath(element, "protocol:/a/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Sub folder with project name shall be changed", "protocol:/./image.png",
        object.iterator().next().toString());
  }

  @Test
  public void rootWithSubFolder() {
    setPath(element, "a/b/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Image on sub folder of root project shall be changed", "./b/image.png",
        object.iterator().next().toString());
  }

}
