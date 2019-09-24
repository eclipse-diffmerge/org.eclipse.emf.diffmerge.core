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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfiguration;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A default implementation of IComparisonMethodFactory.
 * 
 * @param <E> The type of data elements to compare.
 *
 * @author Olivier Constant
 */
public abstract class DefaultComparisonMethodFactory<E> implements IComparisonMethodFactory<E> {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#createComparisonConfiguration()
   */
  public IComparisonConfiguration<E> createComparisonConfiguration() {
    return createComparisonMethod(
        EMPTY_MODEL_SCOPE_DEFINITION,
        EMPTY_MODEL_SCOPE_DEFINITION,
        EMPTY_MODEL_SCOPE_DEFINITION);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getLabel()
   */
  public String getLabel() {
    return Messages.DefaultComparisonMethodFactory_Label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory#getID()
   */
  public String getID() {
    return getClass().getName();
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
  
  
  /** A model scope definition that cannot do anything */
  public static final IModelScopeDefinition EMPTY_MODEL_SCOPE_DEFINITION =
    new IModelScopeDefinition() {
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
     */
    public IEditableTreeDataScope<?> createScope(Object context_p) {
      return null;
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getEntrypoint()
     */
    public Object getEntrypoint() {
      return this;
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getLabel()
     */
    public String getLabel() {
      return getShortLabel();
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getShortLabel()
     */
    public String getShortLabel() {
      return ""; //$NON-NLS-1$
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditable()
     */
    public boolean isEditable() {
      return false;
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditableSettable()
     */
    public boolean isEditableSettable() {
      return false;
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#setEditable(boolean)
     */
    public void setEditable(boolean editable_p) {
      // Ignore
    }
  };
  
}
