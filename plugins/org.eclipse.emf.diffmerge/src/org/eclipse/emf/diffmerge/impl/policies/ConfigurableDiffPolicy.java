/*********************************************************************
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A diff policy that provides a few characteristics which can be configured.
 * @author Olivier Constant
 */
public class ConfigurableDiffPolicy extends DefaultDiffPolicy
implements IConfigurablePolicy {
  
  /** Whether orders must be ignored */
  private boolean _ignoreOrders;
  
  /** The non-null, potentially empty, modifiable set of listeners */
  protected final Set<IConfigurationChangedListener> _listeners;
  
 
  /**
   * Constructor
   */
  public ConfigurableDiffPolicy() {
    _ignoreOrders = false;
    _listeners = new LinkedHashSet<IConfigurationChangedListener>();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public ConfigurableDiffPolicy(ConfigurableDiffPolicy policy_p) {
    this();
    update(policy_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#addConfigurationChangedListener(org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy.IConfigurationChangedListener)
   */
  public void addConfigurationChangedListener(IConfigurationChangedListener listener_p) {
    _listeners.add(listener_p);
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public ConfigurableDiffPolicy clone() throws CloneNotSupportedException {
    // Override in subclasses if the configurable state is extended or modified
    return new ConfigurableDiffPolicy(this);
  }
 
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#considerOrderedFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean considerOrderedFeature(EStructuralFeature feature_p) {
    boolean result;
    if (_ignoreOrders) {
      result = false;
    } else {
      result = doConsiderOrdered(feature_p);
    }
    return result;
  }
  
  /**
   * Return whether the given feature must be considered as ordered, independently of the
   * "ignore orders" flag
   * @see DefaultDiffPolicy#considerOrderedFeature(EStructuralFeature)
   * @param feature_p a non-null feature
   */
  protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
    return super.considerOrderedFeature(feature_p);
  }
  
  /**
   * Notify all registered listeners that the configuration changed
   * @param property_p an optional object that describes the configuration property that changed
   */
  protected void fireConfigurationChanged(Object property_p) {
    for (IConfigurationChangedListener listener : _listeners) {
      listener.configurationChanged(this, property_p);
    }
  }
  
  /**
   * Return whether this policy ignores orders
   */
  public boolean isIgnoreOrders() {
    return _ignoreOrders;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#removeConfigurationChangedListener(org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy.IConfigurationChangedListener)
   */
  public void removeConfigurationChangedListener(IConfigurationChangedListener listener_p) {
    _listeners.remove(listener_p);
  }
  
  /**
   * Set whether orders must be ignored
   * @param ignore_p whether orders must be ignored
   */
  public void setIgnoreOrders(boolean ignore_p) {
    _ignoreOrders = ignore_p;
    fireConfigurationChanged(null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#update(org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy)
   */
  public boolean update(IConfigurablePolicy policy_p) {
    boolean result = false;
    if (policy_p instanceof ConfigurableDiffPolicy) {
      ConfigurableDiffPolicy policy = (ConfigurableDiffPolicy)policy_p;
      setIgnoreOrders(policy.isIgnoreOrders());
      result = true;
    }
    return result;
  }
  
}
