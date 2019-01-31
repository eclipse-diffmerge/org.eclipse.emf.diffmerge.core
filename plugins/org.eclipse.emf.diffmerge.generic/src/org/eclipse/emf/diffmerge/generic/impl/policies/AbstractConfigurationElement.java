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
package org.eclipse.emf.diffmerge.generic.impl.policies;

import org.eclipse.emf.diffmerge.generic.api.config.IConfigurationElement;


/**
 * A base implementation of IConfigurationElement.
 * @author Olivier Constant
 */
public abstract class AbstractConfigurationElement implements IConfigurationElement {
  
  /** A non-null label for the element */
  private final String _label;
  
  /** An optional description for the element */
  private final String _description;
  
  
  /**
   * Constructor
   * @param label_p a non-null label
   * @param description_p a potentially null description
   */
  protected AbstractConfigurationElement(String label_p, String description_p) {
    _label = label_p;
    _description = description_p;
  }
  
  /**
   * Return the description of this criterion, if any
   * @return a potentially null object
   */
  public String getDescription() {
    return _description;
  }
  
  /**
   * Return the label of this criterion
   * @return a non-null object
   */
  public String getLabel() {
    return _label;
  }
  
}