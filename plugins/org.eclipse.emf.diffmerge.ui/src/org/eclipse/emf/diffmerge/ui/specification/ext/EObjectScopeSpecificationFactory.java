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
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeSpecificationFactory;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.ecore.EObject;


/**
 * A factory for scopes based on a single model element.
 * @author Olivier Constant
 */
public class EObjectScopeSpecificationFactory extends AbstractScopeSpecificationFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#createScopeSpecification(java.lang.Object, java.lang.String, boolean)
   */
  public IScopeSpecification createScopeSpecification(Object entrypoint_p, String label_p,
      boolean editable_p) {
    IScopeSpecification result = null;
    if (entrypoint_p instanceof EObject)
      result = new EObjectScopeSpecification((EObject)entrypoint_p, label_p, editable_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeSpecificationFactory#getLabel()
   */
  public String getLabel() {
    return Messages.EObjectScopeSpecificationFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IScopeSpecificationFactory#isApplicableTo(java.lang.Object)
   */
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof EObject;
  }
  
}
