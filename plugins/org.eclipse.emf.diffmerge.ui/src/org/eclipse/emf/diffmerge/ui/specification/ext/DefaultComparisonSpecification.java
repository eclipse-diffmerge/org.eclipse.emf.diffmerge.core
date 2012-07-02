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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;


/**
 * A default implementation of IComparisonSpecification aimed at being sub-classed.
 * @author Olivier Constant
 */
public class DefaultComparisonSpecification implements IComparisonSpecification {
  
  /** The map from roles to the corresponding scope specifications */
  private final Map<Role, IScopeSpecification> _roleToScopeSpec;
  
  /** The (potentially null) match policy */
  private IMatchPolicy _matchPolicy;
  
  /** The (potentially null) diff policy */
  private IDiffPolicy _diffPolicy;
  
  /** The (potentially null) merge policy */
  private IMergePolicy _mergePolicy;
  
  /** The (initially null) editing domain */
  private EditingDomain _editingDomain;
  
  
  /**
   * Constructor
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  public DefaultComparisonSpecification(IScopeSpecification leftScopeSpec_p,
      IScopeSpecification rightScopeSpec_p, IScopeSpecification ancestorScopeSpec_p) {
    _roleToScopeSpec = new HashMap<Role, IScopeSpecification>();
    _roleToScopeSpec.put(Role.TARGET, leftScopeSpec_p);
    _roleToScopeSpec.put(Role.REFERENCE, rightScopeSpec_p);
    _roleToScopeSpec.put(Role.ANCESTOR, ancestorScopeSpec_p);
    _editingDomain = null;
    _matchPolicy = createMatchPolicy();
    _diffPolicy = createDiffPolicy();
    _mergePolicy = createMergePolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#configure()
   */
  public void configure() {
    // Do nothing
  }
  
  /**
   * Create and return the diff policy
   * @return a potentially null diff policy
   */
  protected IDiffPolicy createDiffPolicy() {
    return null;
  }
  
  /**
   * Create and return the match policy
   * @return a potentially null match policy
   */
  protected IMatchPolicy createMatchPolicy() {
    return null;
  }
  
  /**
   * Create and return the merge policy
   * @return a potentially null merge policy
   */
  protected IMergePolicy createMergePolicy() {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    EditingDomain domain = getEditingDomain();
    if (domain.getResourceSet().getResources().isEmpty() &&
        domain instanceof AdapterFactoryEditingDomain) {
      // Resource set is empty: dispose adapter factories if relevant
      AdapterFactoryEditingDomain afed = (AdapterFactoryEditingDomain)domain;
      AdapterFactory af = afed.getAdapterFactory();
      if (af instanceof IDisposable)
        ((IDisposable)af).dispose();
    }
  }
  
  /**
   * Get the editing domain for this comparison (this method is only called once)
   * @return a non-null editing domain
   */
  protected EditingDomain doGetEditingDomain() {
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
        ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
    return new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#getScopeSpecification(org.eclipse.emf.diffmerge.api.Role)
   */
  public IScopeSpecification getScopeSpecification(Role role_p) {
    return _roleToScopeSpec.get(role_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#getDiffPolicy()
   */
  public final IDiffPolicy getDiffPolicy() {
    return _diffPolicy;
  }
  
  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  public final EditingDomain getEditingDomain() {
    if (_editingDomain == null)
      _editingDomain = doGetEditingDomain();
    return _editingDomain;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#getMatchPolicy()
   */
  public final IMatchPolicy getMatchPolicy() {
    return _matchPolicy;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#getMergePolicy()
   */
  public final IMergePolicy getMergePolicy() {
    return _mergePolicy;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#isConfigurable()
   */
  public boolean isConfigurable() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#isThreeWay()
   */
  public final boolean isThreeWay() {
    return getScopeSpecification(Role.ANCESTOR) != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification#switchScopeSpecifications(org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.api.Role)
   */
  public boolean switchScopeSpecifications(Role role1_p, Role role2_p) {
    boolean result = false;
    IScopeSpecification scope1 = getScopeSpecification(role1_p);
    IScopeSpecification scope2 = getScopeSpecification(role2_p);
    if (scope1 != null && scope2 != null) {
      _roleToScopeSpec.put(role1_p, scope2);
      _roleToScopeSpec.put(role2_p, scope1);
      result = true;
    }
    return result;
  }
  
}
