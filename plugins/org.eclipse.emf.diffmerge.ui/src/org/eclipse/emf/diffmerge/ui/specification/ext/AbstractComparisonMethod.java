/**
 * <copyright>
 * 
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.setup.AbstractComparisonSetup;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryProvider;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;


/**
 * A base implementation of IComparisonMethod.
 * @author Olivier Constant
 */
public abstract class AbstractComparisonMethod extends AbstractComparisonSetup
implements IComparisonMethod {
  
  /** The optional factory that created this comparison method */
  protected IComparisonMethodFactory _factory;
  
  /** Whether the comparison method has been fully initialized */
  private boolean _initialized;
  
  /** The (initially null) editing domain */
  private EditingDomain _editingDomain;
  
  /** Whether the editing domain in which the comparison takes place, if any,
  is entirely dedicated to the comparison */
  protected boolean _isDedicatedEditingDomain;
  
  /** Whether the comparison and merge scenario is of a "source-target"
   * kind, that is, differences are relative to the TARGET side and merge may
   * only occur on that side */
  private boolean _isDirected;
  
  /** Whether this comparison method may provide additional information to the end-user */
  private boolean _verbose;
  
  
  /**
   * Constructor
   */
  protected AbstractComparisonMethod() {
    _editingDomain = null;
    _factory = null;
    _isDedicatedEditingDomain = false;
    _initialized = false;
    _verbose = true;
  }
  
  /**
   * Clear the given resource set
   * @param resourceSet_p a non-null object
   */
  protected void clearResourceSet(ResourceSet resourceSet_p) {
    List<Resource> resources =
        new ArrayList<Resource>(resourceSet_p.getResources());
    for (Resource resource : resources) {
      for (Adapter adapter : new ArrayList<Adapter>(resource.eAdapters())) {
        if (adapter instanceof ECrossReferenceAdapter)
          resource.eAdapters().remove(adapter);
      }
    }
    for (Resource resource : resources) {
      try {
        if (resource.isLoaded())
          resource.unload();
        resourceSet_p.getResources().remove(resource);
      } catch (Exception e) {
        // Proceed
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#configure()
   */
  public void configure() {
    // Override for configurable comparison methods
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#createComparison(org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope, org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope, org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope)
   */
  public EComparison createComparison(IEditableModelScope targetScope_p,
      IEditableModelScope referenceScope_p, IEditableModelScope ancestorScope_p) {
    return new EComparisonImpl(targetScope_p, referenceScope_p, ancestorScope_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#createComparisonViewer(org.eclipse.swt.widgets.Composite, org.eclipse.ui.IActionBars)
   */
  public AbstractComparisonViewer createComparisonViewer(Composite parent_p,
      IActionBars actionBars_p) {
    AbstractComparisonViewer result = doCreateComparisonViewer(parent_p, actionBars_p);
    IDifferenceCategoryProvider provider = getCustomCategoryProvider();
    if (provider != null) {
      result.setCategoryProvider(provider);
    }
    if (result instanceof ComparisonViewer) {
      ILabelProvider customLP = getCustomLabelProvider();
      if (customLP != null) {
        ((ComparisonViewer)result).setDelegateLabelProvider(customLP);
      }
      IDiffLabelDecorator customLD = getCustomDiffLabelDecorator();
      if (customLD != null) {
        ((ComparisonViewer)result).setDiffLabelDecorator(customLD);
      }
    }
    return result;
  }
  
  /**
   * Create and return an editing domain that is dedicated to the comparison
   * @return a non-null editing domain
   */
  protected EditingDomain createEditingDomain() {
    // Override for custom editing domain
    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
        ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
    return new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack());
  }
  
  /**
   * Dispose this comparison method.
   * This excludes scopes, comparison and Eclipse operation history.
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    final EditingDomain domain = getEditingDomain();
    if (domain != null && _isDedicatedEditingDomain) {
      // Dedicated editing domain: clear it entirely
      // (useful if comparison computation was cancelled)
      MiscUtil.executeAndForget(domain, new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          clearResourceSet(domain.getResourceSet());
        }
      });
      domain.getCommandStack().flush();
    }
    // Domain is empty: dispose adapter factories
    if (domain instanceof AdapterFactoryEditingDomain &&
        domain.getResourceSet().getResources().isEmpty()) {
      AdapterFactoryEditingDomain afed = (AdapterFactoryEditingDomain)domain;
      AdapterFactory af = afed.getAdapterFactory();
      if (af instanceof IDisposable)
        ((IDisposable)af).dispose();
    }
    // Dedicated transactional editing domain: dispose it
    if (domain instanceof TransactionalEditingDomain && _isDedicatedEditingDomain)
      ((TransactionalEditingDomain)domain).dispose();
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
   * Create and return the viewer for the comparison
   * @param parent_p a non-null composite
   * @param actionBars_p an optional IActionBars, typically for contributing global actions
   *          such as undo/redo
   */
  protected AbstractComparisonViewer doCreateComparisonViewer(Composite parent_p,
      IActionBars actionBars_p) {
    return new ComparisonViewer(parent_p, actionBars_p);
  }
  
  /**
   * Get the editing domain for this comparison (this method is only called once)
   * @return a potentially null editing domain
   */
  protected EditingDomain doGetEditingDomain() {
    // By default, create a dedicated editing domain
    _isDedicatedEditingDomain = true;
    return createEditingDomain();
  }
  
  /**
   * Return an optional difference category provider for customizing the categories
   * that are present in the viewer
   * @return a category provider, or null for the default one
   */
  protected IDifferenceCategoryProvider getCustomCategoryProvider() {
    return null;
  }
  
  /**
   * Return an optional diff label decorator for customizing the way the diff status
   * of model elements is represented in comparison widgets.
   * This operation only has an impact if the viewer created by this comparison method
   * is a ComparisonViewer.
   * @return a diff label decorator, or null for the default one
   */
  protected IDiffLabelDecorator getCustomDiffLabelDecorator() {
    return null;
  }
  
  /**
   * Return an optional label provider for customizing the way model elements
   * are represented in comparison widgets. The client is responsible for disposing
   * the label provider when appropriate. This operation only has an impact if the
   * viewer created by this comparison method is a ComparisonViewer.
   * @return a label provider, or null for the default label provider
   */
  protected ILabelProvider getCustomLabelProvider() {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  public final EditingDomain getEditingDomain() {
    // By default, try to obtain an editing domain if not initialized
    if (!_initialized) {
      _editingDomain = doGetEditingDomain();
      _initialized = true;
    }
    return _editingDomain;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getFactory()
   */
  public IComparisonMethodFactory getFactory() {
    return _factory;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getResourceSet(org.eclipse.emf.diffmerge.api.Role)
   */
  public ResourceSet getResourceSet(Role role_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isConfigurable()
   */
  public boolean isConfigurable() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isDedicatedEditingDomain()
   */
  public boolean isDedicatedEditingDomain() {
    return _isDedicatedEditingDomain;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isDirected()
   */
  public boolean isDirected() {
    return _isDirected;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isThreeWay()
   */
  public boolean isThreeWay() {
    return getModelScopeDefinition(Role.ANCESTOR) != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#isVerbose()
   */
  public boolean isVerbose() {
    return _verbose;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setDirected(boolean)
   */
  public void setDirected(boolean isDirected_p) {
    _isDirected = isDirected_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setEditingDomain(org.eclipse.emf.edit.domain.EditingDomain, boolean)
   */
  public void setEditingDomain(EditingDomain domain_p, boolean dedicated_p) {
    _editingDomain = domain_p;
    if (domain_p != null) {
      _isDedicatedEditingDomain = dedicated_p;
      _initialized = true;
    } else {
      _isDedicatedEditingDomain = false;
      _initialized = false;
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setVerbose(boolean)
   */
  public void setVerbose(boolean verbose_p) {
    _verbose = verbose_p;
  }
  
}
