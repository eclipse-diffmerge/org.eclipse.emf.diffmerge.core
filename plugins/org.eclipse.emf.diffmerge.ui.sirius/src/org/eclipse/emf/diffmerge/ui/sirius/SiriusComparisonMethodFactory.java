/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.sirius;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.EObject;


/**
 * A factory of Sirius comparison methods.
 */
public class SiriusComparisonMethodFactory extends GMFComparisonMethodFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.gmf.GMFComparisonMethodFactory#createComparisonMethod(org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition, org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition)
   */
  @Override
  public IComparisonMethod<EObject> createComparisonMethod(
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
