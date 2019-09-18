/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
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


/**
 * An element, typically immutable, that is part of, or can act on, a configuration
 * and can be exposed to the user.
 * @author Olivier Constant
 */
public interface IConfigurationElement {
  
  /**
   * Return the description of this configuration element, if any
   * @return a potentially null object
   */
  String getDescription();
  
  /**
   * Return the label of this configuration element
   * @return a non-null object
   */
  String getLabel();
  
}