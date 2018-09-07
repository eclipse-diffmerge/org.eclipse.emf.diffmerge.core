/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.design;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;


/**
 * An action for resetting.
 * 
 * @author Olivier COnstant
 */
public class ResetAction extends AbstractSiriusAction {
  
  /**
   * Constructor
   */
  public ResetAction() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  public void execute(Collection<? extends EObject> selection_p,
      Map<String, Object> parameters_p) {
    if (IMPACT != null)
     IMPACT.resetExploration();
  }
  
}
