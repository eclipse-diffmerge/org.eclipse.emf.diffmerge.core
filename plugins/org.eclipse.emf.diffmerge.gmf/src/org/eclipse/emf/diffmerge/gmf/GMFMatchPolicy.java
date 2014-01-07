/**
 * Copyright (c) THALES, 2013. All rights reserved.
 */
package org.eclipse.emf.diffmerge.gmf;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;


/**
 * A match policy for GMF elements.
 * @author Olivier Constant
 */
public class GMFMatchPolicy extends ConfigurableMatchPolicy {
  
  /**
   * Constructor
   */
  public GMFMatchPolicy() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getUniqueName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope, boolean)
   */
  @Override
  protected String getUniqueName(EObject element_p, IModelScope scope_p, boolean inScopeOnly_p) {
    String result;
    if (element_p instanceof Diagram)
      result = ((Diagram)element_p).getName();
    else
      result = super.getUniqueName(element_p, scope_p, inScopeOnly_p);
    return result;
  }
  
}
