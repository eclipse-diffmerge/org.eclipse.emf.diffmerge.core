/**
 * <copyright>
 * 
 * Copyright (c) 2006-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.sirius;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A factory of Sirius comparison methods.
 */
public class SiriusComparisonMethodFactory extends GMFComparisonMethodFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  @Override
  public IComparisonMethod createComparisonMethod(
      IModelScopeDefinition leftScopeSpec,
      IModelScopeDefinition rightScopeSpec,
      IModelScopeDefinition ancestorScopeSpec) {
    return new SiriusComparisonMethod(
        leftScopeSpec, rightScopeSpec, ancestorScopeSpec, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.SiriusComparisonFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(GMFComparisonMethodFactory.class);
  }
  
}
