/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A default implementation of IComparisonMethodFactory.
 * @author Olivier Constant
 */
public class DefaultComparisonMethodFactory implements IComparisonMethodFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  public IComparisonMethod createComparisonMethod(
      IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    return new DefaultComparisonMethod(
        leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getLabel()
   */
  public String getLabel() {
    return Messages.DefaultComparisonMethodFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getOverridenClasses()
   */
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.emptySet();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#isApplicableTo(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  public boolean isApplicableTo(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    return true;
  }
  
}
