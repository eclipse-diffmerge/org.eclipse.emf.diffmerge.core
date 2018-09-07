/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.api.config;

import java.util.List;

/**
 * A configurator for comparisons.
 * @author Olivier Constant
 */
public interface IComparisonConfigurator extends IConfigurationElement {
  
  /**
   * Change the given configuration so that it complies with this configurator
   * Postcondition: !result || isCompliant(configuration_p)
   * @param configuration_p a non-null configuration
   * @return whether the operation fully succeeded
   */
  boolean apply(IComparisonConfiguration configuration_p);
  
  /**
   * Return whether the given configuration complies with this configurator,
   * i.e., applying the configurator would not make any change to the configuration
   * @param configuration_p a non-null configuration
   */
  boolean isCompliant(IComparisonConfiguration configuration_p);
  
  
  /**
   * A provider for comparison configurators.
   */
  public interface Provider {
    /**
     * Return the provided configurators
     * @return a non-null, potentially empty ordered set
     */
    List<IComparisonConfigurator> getConfigurators();
  }
  
}
