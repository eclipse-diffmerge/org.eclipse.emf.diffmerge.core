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

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecification;


/**
 * A definition of GMF-specific comparisons.
 * @author Olivier Constant
 */
public class GMFComparisonSpecification extends DefaultComparisonSpecification {
  
  /**
   * Constructor
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  public GMFComparisonSpecification(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p) {
    super(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonSpecification#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new GMFDiffPolicy();
  }
  
}
