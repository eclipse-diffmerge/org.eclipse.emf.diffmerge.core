/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.specification.ext;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A factory for scopes based on a Resource.
 * @author Olivier Constant
 */
public class ResourceScopeDefinitionFactory extends AbstractScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#createScopeDefinition(java.lang.Object, java.lang.String, boolean)
   */
  public IModelScopeDefinition createScopeDefinition(Object entrypoint_p, String label_p,
      boolean editable_p) {
    IModelScopeDefinition result = null;
    if (entrypoint_p instanceof Resource) {
      result = new ResourceScopeDefinition((Resource)entrypoint_p, label_p, editable_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#getLabel()
   */
  public String getLabel() {
    return Messages.ResourceScopeDefinitionFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof Resource;
  }
  
}
