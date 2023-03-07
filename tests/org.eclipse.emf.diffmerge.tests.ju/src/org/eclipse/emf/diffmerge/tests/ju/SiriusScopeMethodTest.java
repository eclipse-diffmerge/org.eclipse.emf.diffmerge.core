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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.sirius.SiriusImageHelper;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Test;

public class SiriusScopeMethodTest {

  @Test
  public void projectForAllElements() {
    XMIResourceImpl mainResource = new XMIResourceImpl(URI.createPlatformResourceURI("a/a.elements", true));
    Element element = ElementsFactory.eINSTANCE.createElement();

    XMIResourceImpl childResource = new XMIResourceImpl(URI.createPlatformResourceURI("a/subfolder/b.elements", true));
    Element element2 = ElementsFactory.eINSTANCE.createElement();

    mainResource.getContents().add(element);
    childResource.getContents().add(element2);
    element.setSingleContent(element2);

    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));
    assertTrue("The project of a root element is it's parent folder", helper.getMainProjectName(element).equals("a"));
    assertTrue("The project of an child element is the parent folder of its root container",
        helper.getMainProjectName(element2).equals("a"));
  }

  @Test
  public void projectForSeveralResources() {
    XMIResourceImpl aResource = new XMIResourceImpl(URI.createPlatformResourceURI("a/a.elements", true));
    Element element = ElementsFactory.eINSTANCE.createElement();

    XMIResourceImpl bResource = new XMIResourceImpl(URI.createPlatformResourceURI("b/b.elements", true));
    Element element2 = ElementsFactory.eINSTANCE.createElement();
    Element element3 = ElementsFactory.eINSTANCE.createElement();

    aResource.getContents().add(element);
    bResource.getContents().add(element2);
    bResource.getContents().add(element3);
    element.setSingleContent(element2);

    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));
    assertTrue("The project of a root element is it's parent folder", helper.getMainProjectName(element).equals("a"));
    assertTrue("The project of an child element is the parent folder of its root container",
        helper.getMainProjectName(element2).equals("a"));
    assertTrue("The project of a root element is it's parent folder", helper.getMainProjectName(element3).equals("b"));
  }

  @Test
  public void projectPlatformResource() {
    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));

    assertTrue(helper.getProjectFromUri(URI.createURI("platform:/resource/project/model.aird")).equals("project"));
    assertTrue(helper.getProjectFromUri(URI.createURI("platform:/resource/project/fragments/model.airdfragments"))
        .equals("project"));
    assertTrue(helper.getProjectFromUri(URI.createURI("platform:/resource/project/.representations/model.srm"))
        .equals("project"));
    assertTrue(
        helper.getProjectFromUri(URI.createURI("platform:/resource/project/fragments/.representations/model.srm"))
            .equals("project"));
  }

  @Test
  public void projectRemoteSirius() {
    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));

    assertTrue(helper.getProjectFromUri(URI.createURI("cdo://repository/project/model.aird")).equals("project"));
    assertTrue(helper.getProjectFromUri(URI.createURI("cdo://repository/project/.representations/model.srm"))
        .equals("project"));
    assertTrue(helper.getProjectFromUri(URI.createURI("cdo://repository/project/fragments/.representations/model.srm"))
        .equals("project"));

  }

  @Test
  public void projectByProtocol() {
    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));
    assertTrue(helper.getProjectFromUri(URI.createURI("commit:/root/sub/project/model.aird")).equals("project"));
    assertTrue(helper.getProjectFromUri(URI.createURI("commit:/model.aird")).equals("model"));

    assertTrue(helper.getProjectFromUri(URI.createURI("any:/projectm/project/model.aird")).equals("project"));
  }
  
  @Test
  public void projectName() {
    SiriusHelper helper = new SiriusHelper(new EComparisonImpl(null, null, null));
    assertTrue(helper.getNameFromDotProject(URI.createURI("commit:/root/sub/project/.project"), null).equals("project"));
  }

  private class SiriusHelper extends SiriusImageHelper {

    public SiriusHelper(IComparison comparison) {
      super(comparison);
    }

    public String getProjectFromUri(URI uri_p) {
      return super.getProjectFromUri(uri_p, new URIConverterImpl() {
        @Override
        public boolean exists(URI uri, Map<?, ?> options) {
          // We retrieve true only if we look for a .project on the project folder
          return uri.toString().endsWith("/project/.project");
        }
      });
    }

    @Override
    public String getMainProjectName(EObject source_p) {
      return super.getMainProjectName(source_p);
    }

    @Override
    public String getNameFromDotProject(URI projectUri, URIConverter converter) {
      return super.getNameFromDotProject(projectUri, converter);
    }
  }
}
