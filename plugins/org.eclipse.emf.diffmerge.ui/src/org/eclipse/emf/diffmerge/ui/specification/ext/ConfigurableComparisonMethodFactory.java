/*********************************************************************
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
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
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  @Override
  public IComparisonMethod createComparisonMethod(
      IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    return new ConfigurableComparisonMethod(
        leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p, this);
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
