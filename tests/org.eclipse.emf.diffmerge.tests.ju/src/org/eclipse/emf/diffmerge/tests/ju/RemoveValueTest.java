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

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Root;
import org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl;
import org.junit.Before;
import org.junit.Test;

public class RemoveValueTest {

  Element element;

  RootedModelScope scope;

  @Before
  public void init() {
    Root root = ElementsFactory.eINSTANCE.createRoot();
    element = ElementsFactory.eINSTANCE.createElement();
    root.getContent().add(element);
    scope = new RootedModelScope(Arrays.asList(root));
  }

  @Test
  public void attributeSingleSameHash() {
    String name = "name";
    element.setName(name);
    scope.remove(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME, name);
    assertNull("The attribute shall have been removed", element.eGet(ElementsPackage.Literals.NAMED_ELEMENT__NAME));
  }

  @Test
  public void attributeSingleDifferentHash() {
    String name = "name";
    element.setName(name);
    String name2 = "" + name;
    assertNotEquals("Two string instances shall have different hash even if equals", System.identityHashCode(name),
        System.identityHashCode(name2));
    scope.remove(element, ElementsPackage.Literals.NAMED_ELEMENT__NAME, name2);
    assertNull("The attribute shall have been removed even if we use another instance",
        element.eGet(ElementsPackage.Literals.NAMED_ELEMENT__NAME));
  }

  @Test
  public void attributeManySameHash() {
    Integer hash = Integer.parseInt("5000");
    element.getValues().add(hash);
    scope.remove(element, ElementsPackage.Literals.ELEMENT__VALUES, hash);
    assertTrue("The attribute shall have been removed", element.getValues().size() == 0);
  }

  @Test
  public void attributeManyDifferentHash() {
    Integer hash = Integer.parseInt("5000");
    Integer new5000 = Integer.parseInt("5000");
    element.getValues().add(hash);
    assertNotEquals("Two integer over 127 shall have different hash even if equals", System.identityHashCode(hash),
        System.identityHashCode(new5000));
    scope.remove(element, ElementsPackage.Literals.ELEMENT__VALUES, new5000);
    assertTrue("The attribute shall have been removed even if we use another instance",
        element.getValues().size() == 0);
  }

  @Test
  public void referenceSingleSameHash() {
    Element element2 = ElementsFactory.eINSTANCE.createElement();
    element.setSingleContent(element2);
    scope.remove(element, ElementsPackage.Literals.ELEMENT__SINGLE_CONTENT, element2);
    assertNull("The reference shall have been removed", element.getSingleContent());
  }

  @Test
  public void referenceSingleNotEquals() {
    Element element2 = new MyElement();
    element.setSingleContent(element2);
    scope.remove(element, ElementsPackage.Literals.ELEMENT__SINGLE_CONTENT, element2);
    assertNull("The reference shall have been removed even if we use another instance", element.getSingleContent());
  }

  @Test
  public void referenceManySameHash() {
    Element element2 = ElementsFactory.eINSTANCE.createElement();
    element.getManyContent().add(element2);
    scope.remove(element, ElementsPackage.Literals.ELEMENT__MANY_CONTENT, element2);
    assertTrue("The reference shall have been removed", element.getManyContent().size() == 0);
  }

  @Test
  public void referenceManyNotEquals() {
    Element element2 = new MyElement();
    element.getManyContent().add(element2);
    scope.remove(element, ElementsPackage.Literals.ELEMENT__MANY_CONTENT, element2);
    assertTrue("The reference shall have been removed even if we use another instance",
        element.getManyContent().size() == 0);
  }

  /**
   * An element without equals. Ensure that == has been used while remove value, not equals
   */
  private class MyElement extends ElementImpl {
    @Override
    public boolean equals(Object obj) {
      assertTrue(false);
      return false;
    }
  }
}
