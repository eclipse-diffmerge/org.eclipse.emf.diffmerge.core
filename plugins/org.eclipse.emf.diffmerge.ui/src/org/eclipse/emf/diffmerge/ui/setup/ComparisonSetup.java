/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
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
public class ComparisonSetup extends AbstractComparisonSetup
implements IPropertyChangeNotifier {
  
  /**
   * The visual side in a graphically-presented comparison.
   */
  public static enum Side {
    /** The left-hand side */
    LEFT,
    /** The right-hand side */
    RIGHT,
    /** The ancestor side */
    ANCESTOR
  }
  
  /** An identifier for changes to the roles of the scope definitions */
  public static final String PROPERTY_ROLES = "ComparisonSetup.Property.Roles"; //$NON-NLS-1$
  
  /** An identifier for changes to the roles of the scope definitions */
  public static final String PROPERTY_COMPARISON_METHOD =
    "ComparisonSetup.Property.ComparisonMethod"; //$NON-NLS-1$
  
  /** The initially null lastly used comparison method factory */
  protected static IComparisonMethodFactory<?> __lastComparisonMethodFactory = null;
  
  /** The map from roles to the corresponding scope definitions */
  private final Map<Role, IModelScopeDefinition> _roleToScopeDefinition;
  
  /** Whether scope definitions can be swapped by the end user */
  private boolean _canSwapScopeDefinitions;
  
  /** The potentially null role to use as a reference in a two-way comparison */
  private Role _twoWayReferenceRole;
  
  /** Whether the target side of the merge can be set */
  private boolean _canChangeTargetSide;
  
  /** The non-null, non-empty list of applicable method factories */ 
  private final List<IComparisonMethodFactory<?>> _compatibleMethodFactories;
  
  /** The potentially null selected factory (among the compatible factories) */ 
  private IComparisonMethodFactory<?> _selectedFactory;
  
  /** The potentially null target side of the merge */
  protected Side _targetSide;
  
  /** The potentially null comparison method */ 
  protected IComparisonMethod<?> _comparisonMethod;
  
  /** A non-null set of listeners on this object */
  protected final Set<IPropertyChangeListener> _listeners;
  
  
  /**
   * Constructor
   * @param method_p a non-null pre-selected and configured comparison method
   */
  public ComparisonSetup(IComparisonMethod<?> method_p) {
    this(
        method_p.getModelScopeDefinition(method_p.getLeftRole()),
        method_p.getModelScopeDefinition(method_p.getLeftRole().opposite()),
        method_p.getModelScopeDefinition(Role.ANCESTOR),
        EMFDiffMergeUIPlugin.getDefault().getSetupManager().getApplicableComparisonMethodFactories(
            method_p.getModelScopeDefinition(method_p.getLeftRole()),
            method_p.getModelScopeDefinition(method_p.getLeftRole().opposite()),
            method_p.getModelScopeDefinition(Role.ANCESTOR))
        );
    _comparisonMethod = method_p;
    _twoWayReferenceRole = method_p.getTwoWayReferenceRole();
    _targetSide = method_p.isDirected()? method_p.getLeftRole() == Role.TARGET?
        Side.LEFT: Side.RIGHT: null;
    if (getLeftRole() != method_p.getLeftRole()) {
      // Align left role and consequently swap scope definitions
      super.swapLeftRole();
      swapScopeDefinitions(getLeftRole(), getLeftRole().opposite());
    }
    _selectedFactory = method_p.getFactory();
  }
  
  /**
   * Constructor
   * @param scopeSpec1_p a non-null scope definition for the left-hand side
   * @param scopeSpec2_p a non-null scope definition for the right-hand side
   * @param scopeSpec3_p a potentially null scope definition for the ancestor side
   * @param compatibleFactories_p a non-null, non-empty list
   */
  public ComparisonSetup(IModelScopeDefinition scopeSpec1_p, IModelScopeDefinition scopeSpec2_p,
      IModelScopeDefinition scopeSpec3_p, List<IComparisonMethodFactory<?>> compatibleFactories_p) {
    this();
    Role leftRole = getLeftRole();
    _roleToScopeDefinition.put(leftRole, scopeSpec1_p);
    _roleToScopeDefinition.put(leftRole.opposite(), scopeSpec2_p);
    _roleToScopeDefinition.put(Role.ANCESTOR, scopeSpec3_p);
    _compatibleMethodFactories.addAll(compatibleFactories_p);
  }
  
  /**
   * Default constructor
   */
  protected ComparisonSetup() {
    super();
    _comparisonMethod = null;
    _compatibleMethodFactories = new ArrayList<IComparisonMethodFactory<?>>();
    _canChangeTargetSide = true;
    _canSwapScopeDefinitions = true;
    _twoWayReferenceRole = null;
    _targetSide = null;
    _roleToScopeDefinition = new HashMap<Role, IModelScopeDefinition>();
    _listeners = new HashSet<IPropertyChangeListener>();
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    _listeners.add(listener_p);
  }
  
  /**
   * Set the "editable" property of scope definitions whenever possible to match
   * the role allocation and the target side policy
   */
  protected void applyEditableRules() {
    IModelScopeDefinition targetScopeDef = _roleToScopeDefinition.get(Role.TARGET);
    if (targetScopeDef.isEditableSettable()) {
      targetScopeDef.setEditable(true);
    }
    IModelScopeDefinition referenceScopeDef = _roleToScopeDefinition.get(Role.REFERENCE);
    if (referenceScopeDef.isEditableSettable()) {
      referenceScopeDef.setEditable(getTargetSide() == null);
    }
    IModelScopeDefinition ancestorScopeDef =
        _roleToScopeDefinition.get(Role.ANCESTOR);
    if (ancestorScopeDef != null && ancestorScopeDef.isEditableSettable()) {
      ancestorScopeDef.setEditable(false);
    }
  }
  
  /**
   * Return whether the target side of the merge can be set
   */
  public boolean canChangeTargetSide() {
    return _canChangeTargetSide;
  }
  
  /**
   * Return whether scope definitions can be swapped by the end user
   */
  public boolean canSwapScopeDefinitions() {
    return _canSwapScopeDefinitions;
  }
  
  /**
   * Swap the role that corresponds to the left-hand side without sending notifications
   */
  protected void doSwapLeftRole() {
    super.swapLeftRole();
    if (_comparisonMethod != null) {
      _comparisonMethod.swapLeftRole();
    }
  }
  
  /**
   * Swap the scope definitions that play the given roles without sending notifications
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may only fail to prevent inconsistencies)
   */
  protected boolean doSwapScopeDefinitions(Role role1_p, Role role2_p) {
    boolean result = true;
    if (_comparisonMethod != null) {
      result = _comparisonMethod.swapScopeDefinitions(role1_p, role2_p);
    }
    if (result) {
      IModelScopeDefinition scope1 = getScopeDefinition(role1_p);
      IModelScopeDefinition scope2 = getScopeDefinition(role2_p);
      if (scope1 != null && scope2 != null) {
        _roleToScopeDefinition.put(role1_p, scope2);
        _roleToScopeDefinition.put(role2_p, scope1);
      }
    }
    return result;
  }
  
  /**
   * Return the list of comparison factories which are compatible with the
   * scope definitions
   * @return a non-null, non-empty list
   */
  public List<IComparisonMethodFactory<?>> getApplicableComparisonMethodFactories() {
    return Collections.unmodifiableList(_compatibleMethodFactories);
  }
  
  /**
   * Return the comparison method created by the selected factory, if any
   * @return a potentially null object
   */
  public IComparisonMethod<?> getComparisonMethod() {
    return _comparisonMethod;
  }
  
  /**
   * Return the role that corresponds to the given side
   * @param side_p a potentially null side
   * @return a role which is non-null if and only if side_p is not null
   */
  public Role getRoleForSide(Side side_p) {
    Role result = null;
    if (side_p != null) {
      switch(side_p) {
      case ANCESTOR:
        result = Role.ANCESTOR; break;
      case LEFT:
        result = getLeftRole(); break;
      default: // RIGHT
        result = getLeftRole().opposite(); break;
      }
    }
    return result;
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
  public IComparisonMethodFactory<?> getSelectedFactory() {
    return _selectedFactory;
  }
  
  /**
   * Return the target side of the merge, if any
   * @return LEFT, RIGHT or null
   */
  public Side getTargetSide() {
    return _targetSide;
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
   * Notify listeners of a change in roles
   */
  protected void notifyRolesChanged() {
    notify(new PropertyChangeEvent(this, PROPERTY_ROLES, null, null));
  }
  
  /**
   * Update the current comparison method with all available information
   * @param remember_p whether the configuration of this setup must be remembered.
   *          It affects the behavior of setSelectedFactoryToLast().
   */
  public void performFinish(boolean remember_p) {
    if (_comparisonMethod != null) {
      IComparisonMethodFactory<?> selectedFactory = getSelectedFactory();
      if (remember_p && selectedFactory != null) {
        __lastComparisonMethodFactory = selectedFactory;
      }
      _comparisonMethod.setDirected(getTargetSide() != null);
      if (!isThreeWay()) {
        _comparisonMethod.setTwoWayReferenceRole(getTwoWayReferenceRole());
      }
    }
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
  
  /**
   * Set whether the target side of the merge can be changed by the end user
   * @param canChange_p whether it can be changed
   */
  public void setCanChangeTargetSide(boolean canChange_p) {
    _canChangeTargetSide = canChange_p;
  }
  
  /**
   * Set whether scope definitions can be swapped by the end user
   * @param canSwap_p whether they can be swapped
   */
  public void setCanSwapScopeDefinitions(boolean canSwap_p) {
    _canSwapScopeDefinitions = canSwap_p;
  }
  
  /**
   * Set the selected method factory and consequently update the comparison method
   * @param selectedFactory_p a factory which is null or belongs to getCompatibleFactories()
   */
  public void setSelectedFactory(IComparisonMethodFactory<?> selectedFactory_p) {
    _selectedFactory = selectedFactory_p;
    if (_selectedFactory != null) {
      Role leftRole = getLeftRole();
      _comparisonMethod = _selectedFactory.createComparisonMethod(
          getScopeDefinition(leftRole), getScopeDefinition(leftRole.opposite()),
          getScopeDefinition(Role.ANCESTOR));
      if (_comparisonMethod.getLeftRole() != leftRole) {
        _comparisonMethod.swapLeftRole();
        _comparisonMethod.swapScopeDefinitions(leftRole, leftRole.opposite());
      }
    } else {
      _comparisonMethod = null;
    }
    notify(new PropertyChangeEvent(this, PROPERTY_COMPARISON_METHOD, null, null));
  }
  
  /**
   * Set the selected method factory to the one lastly used, if any and applicable
   * @return whether the operation had any impact
   */
  public boolean setSelectedFactoryToLast() {
    boolean result = false;
    if (__lastComparisonMethodFactory != null &&
        __lastComparisonMethodFactory != getSelectedFactory() &&
        getApplicableComparisonMethodFactories().contains(__lastComparisonMethodFactory)) {
      setSelectedFactory(__lastComparisonMethodFactory);
      result = true;
    }
    return result;
  }
  
  /**
   * Set the target side of the merge regardless of whether it can be changed
   * by the end-user or not
   * @param targetSide_p LEFT, RIGHT or null
   * @see ComparisonSetup#canChangeTargetSide()
   */
  public void setTargetSide(Side targetSide_p) {
    assert targetSide_p != Side.ANCESTOR;
    if (Side.LEFT == targetSide_p || Side.RIGHT == targetSide_p) {
      if (getRoleForSide(targetSide_p) != Role.TARGET) {
        // Enforce TARGET as the target role
        doSwapLeftRole();
        doSwapScopeDefinitions(Role.TARGET, Role.REFERENCE);
      }
      _targetSide = targetSide_p;
      setTwoWayReferenceRole(Role.TARGET);
    } else {
      // No target side defined
      _targetSide = null;
      setTwoWayReferenceRole(null);
    }
    applyEditableRules();
    notifyRolesChanged();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#setTwoWayReferenceRole(org.eclipse.emf.diffmerge.generic.api.Role)
   */
  public void setTwoWayReferenceRole(Role role_p) {
    if (!isThreeWay()) {
      _twoWayReferenceRole = role_p;
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.setup.AbstractComparisonSetup#swapLeftRole()
   */
  @Override
  public void swapLeftRole() {
    doSwapLeftRole();
    notifyRolesChanged();
  }
  
  /**
   * Swap the scope definitions that play the given roles
   * @param role1_p a non-null role
   * @param role2_p a non-null role
   * @return whether the operation succeeded (it may only fail to prevent inconsistencies)
   */
  public boolean swapScopeDefinitions(Role role1_p, Role role2_p) {
    boolean result = doSwapScopeDefinitions(role1_p, role2_p);
    if (result) {
      applyEditableRules();
      notifyRolesChanged();
    }
    return result;
  }
  
}