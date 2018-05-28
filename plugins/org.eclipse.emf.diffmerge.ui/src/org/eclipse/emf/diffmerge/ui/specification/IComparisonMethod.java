/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.config.IComparisonConfiguration;
import org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;


/**
 * A comparison method defines what and how to compare.
 * @see DefaultComparisonMethod
 * @author Olivier Constant
 */
public interface IComparisonMethod extends IComparisonConfiguration,
IEditingDomainProvider, IDisposable {
  
  /**
   * Let the user configure this comparison method, if this capability is available
   * @see IComparisonMethod#isConfigurable()
   */
  void configure();
  
  /**
   * Create and return the viewer for the comparison.
   * Although a default viewer is available, it can be customized or replaced by a different
   * one in this operation.
   * @see ComparisonViewer
   * @param parent_p a non-null composite
   * @param actionBars_p an optional IActionBars, typically for contributing global actions
   *          such as undo/redo
   */
  AbstractComparisonViewer createComparisonViewer(Composite parent_p, IActionBars actionBars_p);
  
  /**
   * Return the editing domain in which comparison must take place, if any.
   * If null is returned, then undo/redo operations will not be available.
   * @return a potentially null editing domain
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  EditingDomain getEditingDomain();
  
  /**
   * Return the factory that created this comparison method, if any and if known
   * @return a potentially null object
   */
  IComparisonMethodFactory getFactory();
  
  /**
   * Return the scope definition that plays the given role
   * @param role_p a non-null role
   * @return a scope definition which may only be null if role is ANCESTOR
   */
  IModelScopeDefinition getModelScopeDefinition(Role role_p);
  
  /**
   * Return the resource set for the model of the given role, if any.
   * The returned resource set is expected to be the location for loading
   * the necessary model resources if needed. The same resource set can be
   * returned for different roles.
   * This is only useful if getEditingDomain() returns null.
   * @return a potentially null resource set
   */
  ResourceSet getResourceSet(Role role_p);
  
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
   * Return whether the editing domain in which the comparison takes place, if any,
   * is entirely dedicated to the comparison.
   * Class invariant: !isOnDedicatedEditingDomain() || getEditingDomain() != null
   */
  boolean isDedicatedEditingDomain();
  
  /**
   * Return whether this is a three-way comparison, i.e., the ancestor scope is defined
   */
  boolean isThreeWay();
  
  /**
   * Return whether this comparison method provides additional information to the end-user
   */
  boolean isVerbose();
  
  /**
   * Set the editing domain in which comparison must take place.
   * If null and the comparison method does not have the ability to create
   * its own editing domain, then undo/redo operations will not be available.
   * @param domain_p a potentially null editing domain
   * @param dedicated_p whether the editing domain is entirely dedicated to the comparison
   *          (no effect if the passed editing domain is null)
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  void setEditingDomain(EditingDomain domain_p, boolean dedicated_p);
  
  /**
   * Set the reference role in a two-way comparison.
   * This operation has no effect in a three-way comparison.
   * @see EMFDiffNode#getReferenceRole()
   * @param role_p TARGET, REFERENCE, or null
   */
  void setTwoWayReferenceRole(Role role_p);
  
  /**
   * Set whether this comparison method may provide additional information to the end-user
   * @param verbose_p whether additional information may be provided
   */
  void setVerbose(boolean verbose_p);
  
  /**
   * Swap the scope definitions that play the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may fail only to prevent inconsistencies)
   */
  boolean swapScopeDefinitions(Role role1_p, Role role2_p);
  
}
