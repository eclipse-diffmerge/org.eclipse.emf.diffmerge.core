/**
 * <copyright>
 * 
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy;


/**
 * A merge policy that provides a few characteristics which can be configured.
 * @author Olivier Constant
 */
public class ConfigurableMergePolicy extends DefaultMergePolicy
implements IConfigurablePolicy {
  
  /** The non-null, potentially empty, modifiable set of listeners */
  protected final Set<IConfigurationChangedListener> _listeners;
  
 
  /**
   * Constructor
   */
  public ConfigurableMergePolicy() {
    _listeners = new LinkedHashSet<IConfigurationChangedListener>();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public ConfigurableMergePolicy(ConfigurableMergePolicy policy_p) {
    this();
    update(policy_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy#addConfigurationChangedListener(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener)
   */
  public void addConfigurationChangedListener(IConfigurationChangedListener listener_p) {
    _listeners.add(listener_p);
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public ConfigurableMergePolicy clone() throws CloneNotSupportedException {
    // Override in subclasses if the configurable state is extended or modified
    return new ConfigurableMergePolicy(this);
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
   * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy#removeConfigurationChangedListener(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener)
   */
  public void removeConfigurationChangedListener(IConfigurationChangedListener listener_p) {
    _listeners.remove(listener_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy#update(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy)
   */
  public boolean update(IConfigurablePolicy policy_p) {
    return policy_p instanceof ConfigurableMergePolicy;
  }
  
}
