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
package org.eclipse.emf.diffmerge.generic.api.diff;

import org.eclipse.emf.ecore.EAttribute;


/**
 * A difference which represents the unmatched presence of an attribute value.
 * @author Olivier Constant
 */
public interface IAttributeValuePresence extends IValuePresence {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getFeature()
   */
  EAttribute getFeature();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getSymmetrical()
   */
  IAttributeValuePresence getSymmetrical();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getValue()
   * @return a non-null object
   */
  Object getValue();
  
}
