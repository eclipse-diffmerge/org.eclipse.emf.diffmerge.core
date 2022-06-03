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

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.sirius.SiriusScope;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.junit.Test;

public class SiriusScopeMethodTest {

  @Test
  public void projectForAllElements() {
    Scope scope = createDummyScope();
    XMIResourceImpl mainResource = new XMIResourceImpl(URI.createPlatformResourceURI("root/a/a.elements", true));
    Element element = ElementsFactory.eINSTANCE.createElement();
    
    XMIResourceImpl childResource = new XMIResourceImpl(URI.createPlatformResourceURI("root/a/subfolder/b.elements", true));
    Element element2 = ElementsFactory.eINSTANCE.createElement();

    mainResource.getContents().add(element);
    childResource.getContents().add(element2);
    element.setSingleContent(element2);
    
    assertTrue("The project of a root element is it's parent folder", scope.getMainProjectName(element).equals("a"));
    assertTrue("The project of an child element is the parent folder of its root container", scope.getMainProjectName(element2).equals("a"));
  }

  @Test
  public void projectForSeveralResources() {
    Scope scope = createDummyScope();
    XMIResourceImpl aResource = new XMIResourceImpl(URI.createPlatformResourceURI("root/a/a.elements", true));
    Element element = ElementsFactory.eINSTANCE.createElement();
    
    XMIResourceImpl bResource = new XMIResourceImpl(URI.createPlatformResourceURI("root/b/b.elements", true));
    Element element2 = ElementsFactory.eINSTANCE.createElement();
    Element element3 = ElementsFactory.eINSTANCE.createElement();

    aResource.getContents().add(element);
    bResource.getContents().add(element2);
    bResource.getContents().add(element3);
    element.setSingleContent(element2);
    
    assertTrue("The project of a root element is it's parent folder", scope.getMainProjectName(element).equals("a"));
    assertTrue("The project of an child element is the parent folder of its root container", scope.getMainProjectName(element2).equals("a"));
    assertTrue("The project of a root element is it's parent folder", scope.getMainProjectName(element3).equals("b"));
  }
  
  @Test
  public void projectByProtocol() {
    Scope scope = createDummyScope();
    assertTrue(scope.getProjectFromUri(URI.createURI("commit:/root/sub/project/model.aird")).equals("project"));
    assertTrue(scope.getProjectFromUri(URI.createURI("platform:/resource/project/model.aird")).equals("project"));
    assertTrue(scope.getProjectFromUri(URI.createURI("any:/projectm/project/model.aird")).equals("project"));
  }

  protected Scope createDummyScope() {
    WorkspaceImage img = DiagramFactory.eINSTANCE.createWorkspaceImage();
    URI semantic = URI.createPlatformResourceURI("a/a.elements", true);
    ProjectHelper.saveSemanticResource(semantic, img);
    Session session = ProjectHelper.createSessionOn(semantic);
    Scope scope = new SiriusScopeMethodTest().new Scope(session.getSessionResource().getURI(), session.getTransactionalEditingDomain(), true);
    return scope;
  }
  
  private class Scope extends SiriusScope {
    
    public Scope(URI uri_p, EditingDomain domain, boolean readOnly_p) {
      super(uri_p, domain, readOnly_p);
    }

    public String getProjectFromUri(URI uri_p) {
      return super.getProjectFromUri(uri_p);
    }
    
    @Override
    public String getMainProjectName(EObject source_p) {
      return super.getMainProjectName(source_p);
    }
  }
}
