/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.scopes;

import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * A (trivial) scope policy for EMF models.
 * @author Olivier Constant
 */
public class ModelScopePolicy implements IScopePolicy<EObject> {
  
  /** The singleton object */
  protected static final ModelScopePolicy __instance = new ModelScopePolicy();
  
  
  /**
   * Default constructor
   */
  protected ModelScopePolicy() {
    // Stateless
  }
  
  /**
   * Return the singleton instance
   * @return a non-null object
   */
  public static ModelScopePolicy getInstance() {
    return __instance;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#getOppositeReference(java.lang.Object)
   */
  public Object getOppositeReference(Object reference_p) {
    return ((EReference)reference_p).getEOpposite();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isContainerReference(java.lang.Object)
   */
  public boolean isContainerReference(Object reference_p) {
    return ((EReference)reference_p).isContainer();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isContainmentReference(java.lang.Object)
   */
  public boolean isContainmentReference(Object reference_p) {
    return ((EReference)reference_p).isContainment();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isChangeableAttribute(java.lang.Object)
   */
  public boolean isChangeableAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isChangeable();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isChangeableReference(java.lang.Object)
   */
  public boolean isChangeableReference(Object reference_p) {
    return ((EReference)reference_p).isChangeable();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isManyAttribute(java.lang.Object)
   */
  public boolean isManyAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isMany();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isManyReference(java.lang.Object)
   */
  public boolean isManyReference(Object reference_p) {
    return ((EReference)reference_p).isMany();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#isIDAttribute(java.lang.Object)
   */
  public boolean isIDAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isID();
  }
  
}
