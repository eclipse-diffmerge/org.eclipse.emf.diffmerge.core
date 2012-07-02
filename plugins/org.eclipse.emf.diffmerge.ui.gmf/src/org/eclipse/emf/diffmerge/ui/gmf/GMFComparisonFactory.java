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
package org.eclipse.emf.diffmerge.ui.gmf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory;


/**
 * A ComparisonConfigurationFactory with GMF-specific features.
 * @author Olivier Constant
 */
public class GMFComparisonFactory extends DefaultComparisonSpecificationFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory#getLabel()
   */
  @Override
  public String getLabel() {
    return Messages.GmfComparisonFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory#getOverridenClasses()
   */
  @Override
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.<Class<?>>singleton(DefaultComparisonSpecificationFactory.class);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecificationFactory#createComparisonSpecification(org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification)
   */
  @Override
  public IComparisonSpecification createComparisonSpecification(
      IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p,
      IScopeSpecification ancestorScopeSpec_p) {
    return new GMFComparisonSpecification(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
}
