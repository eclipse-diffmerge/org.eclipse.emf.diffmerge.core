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
package org.eclipse.emf.diffmerge.ui.specification;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IDisposable;


/**
 * A comparison method defines what and how to compare.
 * @author Olivier Constant
 */
public interface IComparisonMethod extends IEditingDomainProvider, IDisposable {
  
  /**
   * Let the user configure this comparison method, if this capability is available
   * @see IComparisonMethod#isConfigurable()
   */
  void configure();
  
  /**
   * Return the diff policy for the comparison
   * @return a potentially null diff policy (null is for default)
   */
  IDiffPolicy getDiffPolicy();
  
  /**
   * Return the editing domain in which comparison must take place, if any.
   * If null is returned, then undo/redo operations will not be available.
   * @return a potentially null editing domain
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  EditingDomain getEditingDomain();
  
  /**
   * Return the match policy for the comparison
   * @return a potentially null match policy (null is for default)
   */
  IMatchPolicy getMatchPolicy();
  
  /**
   * Return the merge policy for the comparison
   * @return a potentially null merge policy (null is for default)
   */
  IMergePolicy getMergePolicy();
  
  /**
   * Return the scope definition that plays the given role
   * @param role_p a non-null role
   * @return a scope definition which may only be null if role is ANCESTOR
   */
  IModelScopeDefinition getModelScopeDefinition(Role role_p);
  
  /**
   * Return the resource set in which comparison must take place, if any.
   * This is only useful if getEditingDomain() returns null.
   * @return a potentially null resource set
   */
  ResourceSet getResourceSet();
  
  /**
   * Return the reference role in a two-way comparison if any, or null
   * in a three-way comparison
   * @see EMFDiffNode#getReferenceRole()
   * @return TARGET, REFERENCE, or null
   */
  Role getTwoWayReferenceRole();
  
  /**
   * Return whether this comparison method can be configured by the user
   * @see IComparisonMethod#configure()
   */
  boolean isConfigurable();
  
  /**
   * Return whether this is a three-way comparison, i.e., the ancestor scope is defined
   */
  boolean isThreeWay();
  
  /**
   * Set the reference role in a two-way comparison.
   * This operation has no effect in a three-way comparison.
   * @see EMFDiffNode#getReferenceRole()
   * @param role_p TARGET, REFERENCE, or null
   */
  void setTwoWayReferenceRole(Role role_p);
  
  /**
   * Swap the scope definitions that play the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may fail only to prevent inconsistencies)
   */
  boolean swapScopeDefinitions(Role role1_p, Role role2_p);
  
}
