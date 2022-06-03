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

import org.eclipse.emf.diffmerge.sirius.SiriusDiffPolicy;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.junit.Test;

public class DummyTest {

  @Test
  public void ter() {
    assertTrue("okok" == "okok");
    
    SiriusDiffPolicy policy = new SiriusDiffPolicy();
    assertTrue(policy != null);
    assertTrue(SessionManager.INSTANCE!=null);
    assertTrue(ElementsFactory.eINSTANCE.createElement() != null);
    
  }
}
