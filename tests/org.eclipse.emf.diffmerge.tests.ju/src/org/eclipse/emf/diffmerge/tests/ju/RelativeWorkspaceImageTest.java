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
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.sirius.SiriusImageHelper;
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
    scope.setComparison(new EComparisonImpl(null, null, null));
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
    setPath(element, "cdo:/a/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Sub folder with project name shall be changed", "cdo:/./image.png",
        object.iterator().next().toString());
  }

  @Test
  public void rootWithSubFolder() {
    setPath(element, "a/b/image.png");
    List<Object> object = scope.get(element, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH);
    assertEquals("Image on sub folder of root project shall be changed", "./b/image.png",
        object.iterator().next().toString());
  }
  

  @Test
  public void addLocal() {
    setPath(element, "");
    Object value = new SiriusImageHelper(new EComparisonImpl(null, null, null)).adaptAddValue(element,
        DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, "./image.png");
    assertEquals("Relative image shall be replaced by project harcoded path",
        "a/image.png", value.toString());
  }
  
  @Test
  public void addLocalWithProtocol() {
    setPath(element, "");
    Object value = new SiriusImageHelper(new EComparisonImpl(null, null, null)).adaptAddValue(element,
        DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, "cdo:/./image.png");
    assertEquals("Relative image shall be replaced by project harcoded path",
        "cdo:/a/image.png", value.toString());
  }

  @Test
  public void removeLocal() {
    setPath(element, "My image is <img src=\"./image.png\"></img> src=\"c/image.png\"></img>");
    Object value = new SiriusImageHelper(new EComparisonImpl(null, null, null)).adaptRemoveValue(element,
        DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, "./image.png");
    assertEquals("Relative image shall be replaced by project harcoded path",
        "a/image.png", value.toString());
  }
  
  @Test
  public void removeLocalWithProtocol() {
    setPath(element, "My image is <img src=\"cdo:/./image.png\"></img> src=\"cdo:/c/image.png\"></img>");
    Object value = new SiriusImageHelper(new EComparisonImpl(null, null, null)).adaptRemoveValue(element,
        DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, "cdo:/./image.png");
    assertEquals("Relative image shall be replaced by project harcoded path",
        "cdo:/a/image.png", value.toString());
  }

}
