/*********************************************************************
 * Copyright (c) 2013-2022 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.policies;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy;


/**
 * A base configurable match policy.
 * Matching is by unique IDs by default.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class ConfigurableMatchPolicy<E> extends CachingMatchPolicy<E>
implements IConfigurablePolicy {
  
  /** Whether match IDs must be maintained for better traceability */
  private boolean _keepMatchIDs;
  
  /** Whether the cache must be used */
  private boolean _useCache;
  
  /** The non-null, potentially empty, modifiable set of listeners */
  protected final Set<IConfigurationChangedListener> _listeners;
  
  
  /**
   * Default constructor
   */
  public ConfigurableMatchPolicy() {
    super();
    _keepMatchIDs = false;
    _useCache = false;
    _listeners = new LinkedHashSet<IConfigurationChangedListener>();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public ConfigurableMatchPolicy(ConfigurableMatchPolicy<E> policy_p) {
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
   * Notify all registered listeners that the configuration changed
   * @param property_p an optional object that describes the configuration property that changed
   */
  protected void fireConfigurationChanged(Object property_p) {
    for (IConfigurationChangedListener listener : _listeners) {
      listener.configurationChanged(this, property_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMatchPolicy#keepMatchIDs()
   */
  @Override
  public boolean keepMatchIDs() {
    return _keepMatchIDs;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#removeConfigurationChangedListener(org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy.IConfigurationChangedListener)
   */
  public void removeConfigurationChangedListener(IConfigurationChangedListener listener_p) {
    _listeners.remove(listener_p);
  }
  
  /**
   * Set whether match ID information must be maintained for better traceability
   * but at the price of a larger memory footprint
   * @param keep_p whether to maintain match ID information
   */
  public void setKeepMatchIDs(boolean keep_p) {
    _keepMatchIDs = keep_p;
    fireConfigurationChanged(null);
  }
  
  /**
   * Set whether the cache must be used
   * @param useCache_p whether it must be used
   */
  public void setUseCache(boolean useCache_p) {
    _useCache = useCache_p;
    fireConfigurationChanged(null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#update(org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy)
   */
  @SuppressWarnings("rawtypes")
  public boolean update(IConfigurablePolicy policy_p) {
    boolean result = false;
    if (policy_p instanceof IMatchPolicy) {
      setKeepMatchIDs(((IMatchPolicy)policy_p).keepMatchIDs());
      if (policy_p instanceof ConfigurableMatchPolicy) {
        ConfigurableMatchPolicy policy = (ConfigurableMatchPolicy)policy_p;
        setUseCache(policy.useCache());
        result = true;
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.CachingMatchPolicy#useCache()
   */
  @Override
  public boolean useCache() {
    return _useCache;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IConfigurablePolicy#copy()
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public ConfigurableMatchPolicy copy() {
    return new ConfigurableMatchPolicy(this);
  }
  
}
