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