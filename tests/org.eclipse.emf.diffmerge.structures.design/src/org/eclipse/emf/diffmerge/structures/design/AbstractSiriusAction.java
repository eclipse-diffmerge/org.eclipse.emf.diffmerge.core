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

import org.eclipse.emf.diffmerge.structures.impacts.CachingImpact;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;


/**
 * A base action for Sirius with an impact object.
 * 
 * @author Olivier COnstant
 */
public abstract class AbstractSiriusAction implements IExternalJavaAction {
  
  /** The initially null shared impact */
  protected static CachingImpact IMPACT = null;
  
  
  /**
   * Constructor
   */
  public AbstractSiriusAction() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#canExecute(java.util.Collection)
   */
  @Override
  public boolean canExecute(Collection<? extends EObject> selections) {
    return true;
  }
  
  /**
   * Return the graph associated to the given selection, if any
   * @param selection_p a potentially null collection
   * @return a potentially null graph
   */
  protected EGraph getGraph(Collection<? extends EObject> selection_p) {
    EGraph result = null;
    if (selection_p != null && !selection_p.isEmpty()) {
      Object first = selection_p.iterator().next();
      if (first instanceof EGraph) {
        result = (EGraph)first;
      } else if (first instanceof EObject) {
        EObject root = EcoreUtil.getRootContainer((EObject)first);
        if (root instanceof EGraph)
          result = (EGraph)root;
      }
    }
    return result;
  }
  
}
