/**
 * <copyright>
 * 
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
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
