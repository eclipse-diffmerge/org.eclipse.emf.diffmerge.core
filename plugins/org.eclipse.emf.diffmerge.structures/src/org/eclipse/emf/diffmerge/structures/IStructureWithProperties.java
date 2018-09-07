/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures;


/**
 * A structure that may have certain mathematical properties.
 * 
 * @author Olivier Constant
 */
public interface IStructureWithProperties {
  
  /**
   * Return the value of the given property.
   * If the property is not applicable then unknown is returned.
   * @param <V> the type of the values of the property
   * @param property_p the non-null property
   * @return a non-null property value
   */
  <V> IPropertyValue<V> getProperty(IProperty<V> property_p);
  
}
