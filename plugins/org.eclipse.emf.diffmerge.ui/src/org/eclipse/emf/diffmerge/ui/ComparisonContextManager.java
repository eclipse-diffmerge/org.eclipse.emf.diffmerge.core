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
package org.eclipse.emf.diffmerge.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.diffmerge.ui.actions.ComparisonSetup;
import org.eclipse.emf.diffmerge.ui.actions.EMFDiffMergeEditorInput;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IOverridableFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory;


/**
 * A manager for model comparison contexts that are contributed through the dedicated
 * extension point.
 * @author Olivier Constant
 */
public class ComparisonContextManager {
  
  /** The ModelComparisonContext extension point */
  private static final String MODEL_COMPARISON_CONTEXT_EXTENSION_POINT =
    "org.eclipse.emf.diffmerge.ui.modelComparisonContext"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point property for configurations */
  private static final String EXTENSION_POINT_PROPERTY_CONFIGURATION = "comparisonMethodFactory"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point property for scopes */
  private static final String EXTENSION_POINT_PROPERTY_SCOPE = "scopeDefinitionFactory"; //$NON-NLS-1$
  
  /** The registered comparison method factories (initially null) */
  private Map<Class<?>, IComparisonMethodFactory> _comparisonFactories;
  
  /** The registered scope factories (initially null) */
  private Map<Class<?>, IModelScopeDefinitionFactory> _scopeFactories;
  
  
  /**
   * Constructor
   */
  public ComparisonContextManager() {
    _comparisonFactories = null;
    _scopeFactories = null;
  }
  
  /**
   * Return a default editor input for the given entry points
   * @param entrypoint1_p a non-null object
   * @param entrypoint2_p a non-null object
   * @param entrypoint3_p an optional object
   * @return a potentially null object (null means failure)
   */
  public EMFDiffMergeEditorInput createDefaultEditorInput(Object entrypoint1_p,
      Object entrypoint2_p, Object entrypoint3_p) {
    EMFDiffMergeEditorInput result = null;
    ComparisonSetup selection =
      createComparisonSetup(entrypoint1_p, entrypoint2_p, entrypoint3_p);
    if (selection != null && selection.getComparisonMethod() != null)
      result = new EMFDiffMergeEditorInput(selection.getComparisonMethod());
    return result;
  }
  
  /**
   * Return a comparison setup for the given entry points
   * @param entrypoint1_p a non-null object
   * @param entrypoint2_p a non-null object
   * @param entrypoint3_p an optional object
   * @return a potentially null object (null means failure)
   */
  public ComparisonSetup createComparisonSetup(Object entrypoint1_p,
      Object entrypoint2_p, Object entrypoint3_p) {
    ComparisonSetup result = null;
    List<IModelScopeDefinitionFactory> factories1 =
      getApplicableScopeFactories(entrypoint1_p);
    List<IModelScopeDefinitionFactory> factories2 =
      getApplicableScopeFactories(entrypoint2_p);
    List<IModelScopeDefinitionFactory> factories3 =
      entrypoint3_p == null? Collections.<IModelScopeDefinitionFactory>emptyList():
        getApplicableScopeFactories(entrypoint3_p);
    if (!factories1.isEmpty() && !factories2.isEmpty()) {
      IModelScopeDefinition scopeSpec1 =
        factories1.get(0).createScopeDefinition(entrypoint1_p, null, true);
      IModelScopeDefinition scopeSpec2 =
        factories2.get(0).createScopeDefinition(entrypoint2_p, null, true);
      IModelScopeDefinition scopeSpec3 = factories3.isEmpty()? null:
        factories3.get(0).createScopeDefinition(entrypoint3_p, null, true);
      List<IComparisonMethodFactory> cFactories =
        getApplicableComparisonMethodFactories(scopeSpec1, scopeSpec2, scopeSpec3);
      if (!cFactories.isEmpty())
        result = new ComparisonSetup(scopeSpec1, scopeSpec2, scopeSpec3, cFactories);
    }
    return result;
  }
  
  /**
   * Discover the comparison contexts which are registered through the dedicated
   * extension point, if any
   * Postcondition: _comparisonFactories != null && _scopeFactories != null
   */
  private void discoverRegisteredComparisonContexts() {
    _comparisonFactories = new HashMap<Class<?>, IComparisonMethodFactory>();
    _scopeFactories = new HashMap<Class<?>, IModelScopeDefinitionFactory>();
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IConfigurationElement[] config = registry.getConfigurationElementsFor(
        MODEL_COMPARISON_CONTEXT_EXTENSION_POINT);
    for (IConfigurationElement e : config) {
      try {
        Object o = e.createExecutableExtension(EXTENSION_POINT_PROPERTY_CONFIGURATION);
        if (o instanceof IComparisonMethodFactory)
          _comparisonFactories.put(o.getClass(), (IComparisonMethodFactory)o);
      } catch (CoreException ex) {
        // Proceed
      }
      try {
        Object o = e.createExecutableExtension(EXTENSION_POINT_PROPERTY_SCOPE);
        if (o instanceof IModelScopeDefinitionFactory)
          _scopeFactories.put(o.getClass(), (IModelScopeDefinitionFactory)o);
      } catch (CoreException ex) {
        // Proceed
      }
    }
  }
  
  /**
   * Return the set of comparison method factories that are applicable to the given
   * entry point
   * @param leftScopeSpec_p a non-null scope definition
   * @param rightScopeSpec_p a non-null scope definition
   * @param ancestorScopeSpec_p an optional scope definition
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IComparisonMethodFactory> getApplicableComparisonMethodFactories(
      IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    List<IComparisonMethodFactory> result = new ArrayList<IComparisonMethodFactory>();
    for (IComparisonMethodFactory factory : getRegisteredComparisonMethodFactories()) {
      if (factory.isApplicableTo(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p))
        result.add(factory);
    }
    result = reduceByOverride(result, _comparisonFactories);
    return result;
  }
  
  /**
   * Return the set of scope definition factories that are applicable to the given
   * entry point
   * @param entrypoint_p a non-null object
   * @return a non-null, unmodifiable list which cannot be empty if isValidEntrypoint(entrypoint_p)
   */
  public List<IModelScopeDefinitionFactory> getApplicableScopeFactories(
      Object entrypoint_p) {
    List<IModelScopeDefinitionFactory> result = new ArrayList<IModelScopeDefinitionFactory>();
    for (IModelScopeDefinitionFactory factory : getRegisteredScopeDefinitionFactories()) {
      if (factory.isApplicableTo(entrypoint_p))
        result.add(factory);
    }
    result = reduceByOverride(result, _scopeFactories);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the comparison method factories which are registered through the dedicated
   * extension point, if any
   * @return a non-null, potentially empty list
   */
  protected final Collection<IComparisonMethodFactory> getRegisteredComparisonMethodFactories() {
    if (_comparisonFactories == null)
      discoverRegisteredComparisonContexts();
    return _comparisonFactories.values();
  }
  
  /**
   * Return the scope definition factories which are registered through the dedicated
   * extension point, if any
   * @return a non-null, potentially empty list
   */
  protected final Collection<IModelScopeDefinitionFactory> getRegisteredScopeDefinitionFactories() {
    if (_scopeFactories == null)
      discoverRegisteredComparisonContexts();
    return _scopeFactories.values();
  }
  
  /**
   * Return whether the given object is a valid entry point for a comparison, i.e.,
   * whether a model scope can be derived from it
   * @param entrypoint_p a non-null object
   */
  public boolean isValidEntrypoint(Object entrypoint_p) {
    for (IModelScopeDefinitionFactory factory : getRegisteredScopeDefinitionFactories()) {
      if (factory.isApplicableTo(entrypoint_p))
        return true;
    }
    return false;
  }
  
  /**
   * Return the reduced version of the given list of factories based on the "override" relation
   * @param factories_p a non-null, potentially empty list
   * @param configurationFactories_p a map from factory classes to the corresponding registered instances
   * @return a non-null, unmodifiable list which is not empty if factories_p is not empty
   */
  protected <T extends IOverridableFactory> List<T> reduceByOverride(List<T> factories_p,
      Map<Class<?>, T> configurationFactories_p) {
    List<T> result = new ArrayList<T>(factories_p);
    for (T factory : factories_p) {
      for (Class<?> factoryClass : factory.getOverridenClasses()) {
        result.remove(configurationFactories_p.get(factoryClass));
      }
    }
    return Collections.unmodifiableList(result);
  }
  
}
