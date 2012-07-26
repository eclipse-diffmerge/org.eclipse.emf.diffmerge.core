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
package org.eclipse.emf.diffmerge.ui.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecification;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonSpecificationFactory;
import org.eclipse.emf.diffmerge.ui.specification.IScopeSpecification;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;


/**
 * A simple structure that associates scope specifications with corresponding
 * compatible comparison factories and allows selecting one of the factories
 * for creating a comparison specification.
 * @author Olivier Constant
 */
public class ComparisonSetup implements IPropertyChangeNotifier {
  
  /** An identifier for changes to the roles of the scope specifications */
  public static final String PROPERTY_ROLES = "ComparisonSetup.Property.Roles"; //$NON-NLS-1$
  
  /** An identifier for changes to the roles of the scope specifications */
  public static final String PROPERTY_COMPARISON_METHOD =
    "ComparisonSetup.Property.ComparisonMethod"; //$NON-NLS-1$
  
  /** The map from roles to the corresponding scope specifications */
  private final Map<Role, IScopeSpecification> _roleToScopeSpec;
  
  /** The non-null, non-empty list of applicable comparison factories */ 
  private final List<IComparisonSpecificationFactory> _compatibleFactories;
  
  /** The potentially null selected factory (among the compatible factories) */ 
  private IComparisonSpecificationFactory _selectedFactory;
  
  /** The potentially null comparison specification */ 
  private IComparisonSpecification _comparisonSpecification;
  
  private final Set<IPropertyChangeListener> _listeners;
  
  
  /**
   * Constructor
   * @param scopeSpec1_p a non-null scope specification
   * @param scopeSpec2_p a non-null scope specification
   * @param scopeSpec3_p a potentially null scope specification
   * @param compatibleFactories_p a non-null, non-empty list
   */
  public ComparisonSetup(IScopeSpecification scopeSpec1_p, IScopeSpecification scopeSpec2_p,
      IScopeSpecification scopeSpec3_p, List<IComparisonSpecificationFactory> compatibleFactories_p) {
    _roleToScopeSpec = new HashMap<Role, IScopeSpecification>();
    _roleToScopeSpec.put(Role.TARGET, scopeSpec1_p);
    _roleToScopeSpec.put(Role.REFERENCE, scopeSpec2_p);
    _roleToScopeSpec.put(Role.ANCESTOR, scopeSpec3_p);
    _comparisonSpecification = null;
    _compatibleFactories = new ArrayList<IComparisonSpecificationFactory>(compatibleFactories_p);
    _listeners = new HashSet<IPropertyChangeListener>();
  }
  
  /**
   * Return the comparison specification created by the selected factory, if any
   * @return a potentially null object
   */
  public IComparisonSpecification getComparisonSpecification() {
    return _comparisonSpecification;
  }
  
  /**
   * Return the scope specification that plays the given role
   * @param role_p a non-null role
   * @return a scope specification which may only be null if role is ANCESTOR
   */
  public IScopeSpecification getScopeSpecification(Role role_p) {
    return _roleToScopeSpec.get(role_p);
  }
  
  /**
   * Return the selected factory among the compatible ones
   * @return a potentially null factory
   */
  public IComparisonSpecificationFactory getSelectedFactory() {
    return _selectedFactory;
  }
  
  /**
   * Return the list of comparison factories which are compatible with the
   * scope specifications
   * @return a non-null, non-empty list
   */
  public List<IComparisonSpecificationFactory> getCompatibleFactories() {
    return Collections.unmodifiableList(_compatibleFactories);
  }
  
  /**
   * Return whether the comparison will be three-way
   */
  public boolean isThreeWay() {
    return getScopeSpecification(Role.ANCESTOR) != null;
  }
  
  /**
   * Notify listeners of the given property change event
   * @param event_p a non-null event
   */
  protected void notify(PropertyChangeEvent event_p) {
    for (IPropertyChangeListener listener : _listeners) {
      listener.propertyChange(event_p);
    }
  }
  
  /**
   * Set the selected factory and consequently update the comparison specification
   * @param selectedFactory_p a factory which is null or belongs to getCompatibleFactories()
   */
  public void setSelectedFactory(IComparisonSpecificationFactory selectedFactory_p) {
    _selectedFactory = selectedFactory_p;
    if (_selectedFactory != null) {
      _comparisonSpecification = _selectedFactory.createComparisonSpecification(
          getScopeSpecification(Role.TARGET), getScopeSpecification(Role.REFERENCE),
          getScopeSpecification(Role.ANCESTOR));
    } else {
      _comparisonSpecification = null;
    }
    notify(new PropertyChangeEvent(this, PROPERTY_COMPARISON_METHOD, null, null));
  }
  
  /**
   * Swap the scope specifications that play the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may only fail to prevent inconsistencies)
   */
  public boolean swapScopeSpecifications(Role role1_p, Role role2_p) {
    boolean result = true;
    if (_comparisonSpecification != null)
      result = _comparisonSpecification.swapScopeSpecifications(role1_p, role2_p);
    if (result) {
      IScopeSpecification scope1 = getScopeSpecification(role1_p);
      IScopeSpecification scope2 = getScopeSpecification(role2_p);
      if (scope1 != null && scope2 != null) {
        _roleToScopeSpec.put(role1_p, scope2);
        _roleToScopeSpec.put(role2_p, scope1);
      }
      notify(new PropertyChangeEvent(this, PROPERTY_ROLES, null, null));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    _listeners.add(listener_p);
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    _listeners.remove(listener_p);
  }
  
  /**
   * Remove all property change listeners
   */
  public void removePropertyChangeListeners() {
    _listeners.clear();
  }
  
}