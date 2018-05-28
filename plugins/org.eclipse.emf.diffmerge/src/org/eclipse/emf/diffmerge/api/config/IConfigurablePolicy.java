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
package org.eclipse.emf.diffmerge.api.config;


/**
 * A configurable policy that can be cloned, updated to reflect a similar one,
 * and that can notify about configuration changes.
 * @author Olivier Constant
 */
public interface IConfigurablePolicy extends Cloneable {
  
  /**
   * Register the given listener on this notifier
   * @param listener_p a non-null object
   */
  void addConfigurationChangedListener(IConfigurationChangedListener listener_p);
  
  /**
   * Unregister the given listener from this notifier
   * @param listener_p a potentially null object
   */
  void removeConfigurationChangedListener(IConfigurationChangedListener listener_p);
  
  /**
   * Update this policy so that its configuration reflects that of the given policy
   * @param policy_p a non-null policy
   * @return whether the operation fully succeeded
   */
  boolean update(IConfigurablePolicy policy_p);
  
  
  /**
   * An observer of a configuration.
   */
  public static interface IConfigurationChangedListener {
    /**
     * Notify that the observed configuration changed
     * @param notifier_p the notifier that fired the notification
     * @param property_p an optional object that describes the configuration property that changed
     */
    void configurationChanged(IConfigurablePolicy notifier_p, Object property_p);
  }
  
}