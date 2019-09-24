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
package org.eclipse.emf.diffmerge.ui.gmf;

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.diffmerge.gmf.GMFMergePolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;


/**
 * A definition of GMF-specific comparisons.
 * @author Olivier Constant
 */
public class GMFComparisonMethod extends ConfigurableComparisonMethod {
  
  /**
   * Constructor
   * @param leftScopeDef_p a non-null scope definition
   * @param rightScopeDef_p a non-null scope definition
   * @param ancestorScopeDef_p an optional scope definition
   * @param factory_p the optional factory this comparison method originates from
   */
  public GMFComparisonMethod(IModelScopeDefinition leftScopeDef_p,
      IModelScopeDefinition rightScopeDef_p, IModelScopeDefinition ancestorScopeDef_p,
      IComparisonMethodFactory<EObject> factory_p) {
    super(leftScopeDef_p, rightScopeDef_p, ancestorScopeDef_p, factory_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy<EObject> createDiffPolicy() {
    return new GMFDiffPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod#createMatchPolicy()
   */
  @Override
  protected IMatchPolicy<EObject> createMatchPolicy() {
    return new DefaultMatchPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createMergePolicy()
   */
  @Override
  protected IMergePolicy<EObject> createMergePolicy() {
    return new GMFMergePolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#getCustomLabelProvider()
   */
  @Override
  protected ILabelProvider getCustomLabelProvider() {
    return GMFDiffMergeLabelProvider.getInstance();
  }
  
}
