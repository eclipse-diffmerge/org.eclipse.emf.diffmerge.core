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
package org.eclipse.emf.diffmerge.ui.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;


/**
 * A simple structure that associates scope definitions with corresponding
 * compatible comparison method factories and allows selecting one of the factories
 * for creating a comparison method.
 * @author Olivier Constant
 */
public class ComparisonSetup implements IPropertyChangeNotifier {
  
  /** An identifier for changes to the roles of the scope definitions */
  public static final String PROPERTY_ROLES = "ComparisonSetup.Property.Roles"; //$NON-NLS-1$
  
  /** An identifier for changes to the roles of the scope definitions */
  public static final String PROPERTY_COMPARISON_METHOD =
    "ComparisonSetup.Property.ComparisonMethod"; //$NON-NLS-1$
  
  /** The map from roles to the corresponding scope definitions */
  private final Map<Role, IModelScopeDefinition> _roleToScopeDefinition;
  
  /** The potentially null role to use as a reference in a two-way comparison */
  private Role _twoWayReferenceRole;
  
  /** The non-null, non-empty list of applicable method factories */ 
  private final List<IComparisonMethodFactory> _compatibleMethodFactories;
  
  /** The potentially null selected factory (among the compatible factories) */ 
  private IComparisonMethodFactory _selectedFactory;
  
  /** The potentially null comparison method */ 
  private IComparisonMethod _comparisonMethod;
  
  /** A non-null set of listeners on this object */
  private final Set<IPropertyChangeListener> _listeners;
  
  
  /**
   * Constructor
   * @param scopeSpec1_p a non-null scope definition
   * @param scopeSpec2_p a non-null scope definition
   * @param scopeSpec3_p a potentially null scope definition
   * @param compatibleFactories_p a non-null, non-empty list
   */
  public ComparisonSetup(IModelScopeDefinition scopeSpec1_p, IModelScopeDefinition scopeSpec2_p,
      IModelScopeDefinition scopeSpec3_p, List<IComparisonMethodFactory> compatibleFactories_p) {
    _roleToScopeDefinition = new HashMap<Role, IModelScopeDefinition>();
    _roleToScopeDefinition.put(Role.TARGET, scopeSpec1_p);
    _roleToScopeDefinition.put(Role.REFERENCE, scopeSpec2_p);
    _roleToScopeDefinition.put(Role.ANCESTOR, scopeSpec3_p);
    _comparisonMethod = null;
    _compatibleMethodFactories = new ArrayList<IComparisonMethodFactory>(compatibleFactories_p);
    _listeners = new HashSet<IPropertyChangeListener>();
  }
  
  /**
   * Return the list of comparison factories which are compatible with the
   * scope definitions
   * @return a non-null, non-empty list
   */
  public List<IComparisonMethodFactory> getApplicableComparisonMethodFactories() {
    return Collections.unmodifiableList(_compatibleMethodFactories);
  }
  
  /**
   * Return the comparison method created by the selected factory, if any
   * @return a potentially null object
   */
  public IComparisonMethod getComparisonMethod() {
    return _comparisonMethod;
  }
  
  /**
   * Return the scope definition that plays the given role
   * @param role_p a non-null role
   * @return a scope definition which may only be null if role is ANCESTOR
   */
  public IModelScopeDefinition getScopeDefinition(Role role_p) {
    return _roleToScopeDefinition.get(role_p);
  }
  
  /**
   * Return the selected factory among the compatible ones
   * @return a potentially null factory
   */
  public IComparisonMethodFactory getSelectedFactory() {
    return _selectedFactory;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getTwoWayReferenceRole()
   */
  public Role getTwoWayReferenceRole() {
    return _twoWayReferenceRole;
  }
  
  /**
   * Return whether the comparison will be three-way
   */
  public boolean isThreeWay() {
    return getScopeDefinition(Role.ANCESTOR) != null;
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
   * Update the current comparison method with all available information
   */
  public void performFinish() {
    if (!isThreeWay() && _comparisonMethod != null)
      _comparisonMethod.setTwoWayReferenceRole(getTwoWayReferenceRole());
  }
  
  /**
   * Set the selected method factory and consequently update the comparison method
   * @param selectedFactory_p a factory which is null or belongs to getCompatibleFactories()
   */
  public void setSelectedFactory(IComparisonMethodFactory selectedFactory_p) {
    _selectedFactory = selectedFactory_p;
    if (_selectedFactory != null) {
      _comparisonMethod = _selectedFactory.createComparisonMethod(
          getScopeDefinition(Role.TARGET), getScopeDefinition(Role.REFERENCE),
          getScopeDefinition(Role.ANCESTOR));
    } else {
      _comparisonMethod = null;
    }
    notify(new PropertyChangeEvent(this, PROPERTY_COMPARISON_METHOD, null, null));
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setTwoWayReferenceRole(org.eclipse.emf.diffmerge.api.Role)
   */
  public void setTwoWayReferenceRole(Role role_p) {
    if (!isThreeWay() && Role.TARGET == role_p || Role.REFERENCE == role_p)
      _twoWayReferenceRole = role_p;
  }
  
  /**
   * Swap the scope definitions that play the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may only fail to prevent inconsistencies)
   */
  public boolean swapScopeDefinitions(Role role1_p, Role role2_p) {
    boolean result = true;
    if (_comparisonMethod != null)
      result = _comparisonMethod.swapScopeDefinitions(role1_p, role2_p);
    if (result) {
      IModelScopeDefinition scope1 = getScopeDefinition(role1_p);
      IModelScopeDefinition scope2 = getScopeDefinition(role2_p);
      if (scope1 != null && scope2 != null) {
        _roleToScopeDefinition.put(role1_p, scope2);
        _roleToScopeDefinition.put(role2_p, scope1);
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