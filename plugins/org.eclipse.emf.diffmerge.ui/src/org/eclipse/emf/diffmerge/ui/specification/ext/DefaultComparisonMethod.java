/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;


/**
 * A default implementation of IComparisonMethod aimed at being sub-classed.
 * @author Olivier Constant
 */
public class DefaultComparisonMethod implements IComparisonMethod {
  
  /** Whether the comparison method has been fully initialized */
  private boolean _initialized;
  
  /** The map from roles to the corresponding scope definitions */
  private final Map<Role, IModelScopeDefinition> _roleToScopeDefinition;
  
  /** The potentially null role to use as a reference in a two-way comparison */
  private Role _twoWayReferenceRole;
  
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
   * @param leftScopeSpec_p a non-null scope definition
   * @param rightScopeSpec_p a non-null scope definition
   * @param ancestorScopeSpec_p an optional scope definition
   */
  public DefaultComparisonMethod(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p, IModelScopeDefinition ancestorScopeSpec_p) {
    _roleToScopeDefinition = new HashMap<Role, IModelScopeDefinition>();
    _roleToScopeDefinition.put(Role.TARGET, leftScopeSpec_p);
    _roleToScopeDefinition.put(Role.REFERENCE, rightScopeSpec_p);
    _roleToScopeDefinition.put(Role.ANCESTOR, ancestorScopeSpec_p);
    _twoWayReferenceRole = null;
    _editingDomain = null;
    _matchPolicy = createMatchPolicy();
    _diffPolicy = createDiffPolicy();
    _mergePolicy = createMergePolicy();
    _initialized = false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#configure()
   */
  public void configure() {
    // Override for configurable comparison methods
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#createComparisonViewer(org.eclipse.swt.widgets.Composite, org.eclipse.ui.IActionBars)
   */
  public AbstractComparisonViewer createComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    ComparisonViewer result = new ComparisonViewer(parent_p, actionBars_p);
    ILabelProvider customLP = getCustomLabelProvider();
    if (customLP != null)
      result.setDelegateLabelProvider(customLP);
    return result;
  }
  
  /**
   * Create and return the diff policy
   * @return a potentially null diff policy
   */
  protected IDiffPolicy createDiffPolicy() {
    return null;
  }
  
  /**
   * Create and return an editing domain that is dedicated to the comparison
   * @return a non-null editing domain
   */
  protected EditingDomain createEditingDomain() {
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
        ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
    return new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
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
    final EditingDomain domain = getEditingDomain();
    // If resource set is empty, dispose adapter factories
    if (domain instanceof AdapterFactoryEditingDomain &&
        domain.getResourceSet().getResources().isEmpty()) {
      AdapterFactoryEditingDomain afed = (AdapterFactoryEditingDomain)domain;
      AdapterFactory af = afed.getAdapterFactory();
      if (af instanceof IDisposable)
        ((IDisposable)af).dispose();
    }
    // Also clean shared adapter factory: icons associated to resources
    AdapterFactory af =
        EMFDiffMergeUIPlugin.getDefault().getAdapterFactoryLabelProvider().getAdapterFactory();
    if (af instanceof ComposedAdapterFactory) {
      ComposedAdapterFactory composed = (ComposedAdapterFactory)af;
      AdapterFactory afForType = composed.getFactoryForType(Resource.class.getPackage());
      if (afForType instanceof ResourceItemProviderAdapterFactory) {
        ResourceItemProviderAdapterFactory ripaf = (ResourceItemProviderAdapterFactory)afForType;
        ripaf.dispose();
      }
    }
  }
  
  /**
   * Get the editing domain for this comparison (this method is only called once)
   * @return a potentially null editing domain
   */
  protected EditingDomain doGetEditingDomain() {
    return createEditingDomain();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getModelScopeDefinition(org.eclipse.emf.diffmerge.api.Role)
   */
  public IModelScopeDefinition getModelScopeDefinition(Role role_p) {
    return _roleToScopeDefinition.get(role_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getDiffPolicy()
   */
  public final IDiffPolicy getDiffPolicy() {
    return _diffPolicy;
  }
  
  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  public final EditingDomain getEditingDomain() {
    if (!_initialized) {
      _editingDomain = doGetEditingDomain();
      _initialized = true;
    }
    return _editingDomain;
  }
  
  /**
   * Return an optional label provider for customizing the way model elements
   * are represented in comparison widgets. The client is responsible for disposing
   * the label provider when appropriate.
   * @return a label provider, or null for the default label provider
   */
  protected ILabelProvider getCustomLabelProvider() {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getMatchPolicy()
   */
  public final IMatchPolicy getMatchPolicy() {
    return _matchPolicy;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getMergePolicy()
   */
  public final IMergePolicy getMergePolicy() {
    return _mergePolicy;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getResourceSet(org.eclipse.emf.diffmerge.api.Role)
   */
  public ResourceSet getResourceSet(Role role_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getTwoWayReferenceRole()
   */
  public Role getTwoWayReferenceRole() {
    return _twoWayReferenceRole;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isConfigurable()
   */
  public boolean isConfigurable() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isThreeWay()
   */
  public final boolean isThreeWay() {
    return getModelScopeDefinition(Role.ANCESTOR) != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setTwoWayReferenceRole(org.eclipse.emf.diffmerge.api.Role)
   */
  public void setTwoWayReferenceRole(Role role_p) {
    if (!isThreeWay() && Role.TARGET == role_p || Role.REFERENCE == role_p)
      _twoWayReferenceRole = role_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#swapScopeDefinitions(org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.api.Role)
   */
  public boolean swapScopeDefinitions(Role role1_p, Role role2_p) {
    boolean result = false;
    IModelScopeDefinition scope1 = getModelScopeDefinition(role1_p);
    IModelScopeDefinition scope2 = getModelScopeDefinition(role2_p);
    if (scope1 != null && scope2 != null) {
      _roleToScopeDefinition.put(role1_p, scope2);
      _roleToScopeDefinition.put(role2_p, scope1);
      result = true;
    }
    return result;
  }
  
}
