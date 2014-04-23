/**
 * <copyright>
 * 
 * Copyright (c) 2013 Thales Global Services S.A.S.
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
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A contribution to the Diff/Merge UI extension point.
 * @author Olivier Constant
 */
public class ConfigurableComparisonMethodFactory extends DefaultComparisonMethodFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#createComparisonSpecification(org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification)
   */
  @Override
  public IComparisonMethod createComparisonMethod(
      IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    return new ConfigurableComparisonMethod(
        leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.ConfigurableComparisonMethodFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>emptySet();
  }
  
}
