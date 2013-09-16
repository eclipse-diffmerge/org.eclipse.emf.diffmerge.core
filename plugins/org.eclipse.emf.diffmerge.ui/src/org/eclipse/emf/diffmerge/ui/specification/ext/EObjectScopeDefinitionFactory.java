/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.EObject;


/**
 * A factory for scopes based on a single model element.
 * @author Olivier Constant
 */
public class EObjectScopeDefinitionFactory extends AbstractScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#createScopeDefinition(java.lang.Object, java.lang.String, boolean)
   */
  public IModelScopeDefinition createScopeDefinition(Object entrypoint_p, String label_p,
      boolean editable_p) {
    IModelScopeDefinition result = null;
    if (entrypoint_p instanceof EObject)
      result = new EObjectScopeDefinition((EObject)entrypoint_p, label_p, editable_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeDefinitionFactory#getLabel()
   */
  public String getLabel() {
    return Messages.EObjectScopeDefinitionFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof EObject;
  }
  
}
