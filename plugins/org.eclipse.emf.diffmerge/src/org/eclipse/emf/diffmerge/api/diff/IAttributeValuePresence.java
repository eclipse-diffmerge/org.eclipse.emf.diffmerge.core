/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api.diff;

import org.eclipse.emf.ecore.EAttribute;


/**
 * A difference which represents the unmatched presence of an attribute value.
 * @author Olivier Constant
 */
public interface IAttributeValuePresence extends IValuePresence {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getFeature()
   */
  EAttribute getFeature();
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getSymmetrical()
   */
  IAttributeValuePresence getSymmetrical();
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getValue()
   * @return a non-null object
   */
  Object getValue();
  
}
