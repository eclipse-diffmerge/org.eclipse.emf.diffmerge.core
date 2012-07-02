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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;


/**
 * A default implementation of IComparisonSpecificationFactory.
 * @author Olivier Constant
 */
public class DefaultComparisonSpecificationFactory implements IComparisonSpecificationFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#createComparisonSpecification(org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification)
   */
  public IComparisonSpecification createComparisonSpecification(
      IScopeSpecification leftScopeSpec_p, IScopeSpecification rightScopeSpec_p,
      IScopeSpecification ancestorScopeSpec_p) {
    return new DefaultComparisonSpecification(
        leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#getLabel()
   */
  public String getLabel() {
    return Messages.DefaultComparisonSpecificationFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#getOverridenClasses()
   */
  public Collection<Class<?>> getOverridenClasses() {
    return Collections.emptySet();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory#isApplicableTo(org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification, org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification)
   */
  public boolean isApplicableTo(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p,
      IScopeSpecification ancestorScopeSpec_p) {
    return true;
  }
  
}
