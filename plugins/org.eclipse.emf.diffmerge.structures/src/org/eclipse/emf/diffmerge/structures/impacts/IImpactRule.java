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
package org.eclipse.emf.diffmerge.structures.impacts;

import org.eclipse.emf.diffmerge.structures.endo.IEndorelation;


/**
 * An impact rule.
 * @see IImpact
 * 
 * @author Olivier Constant
 */
public interface IImpactRule extends IEndorelation<Object> {
  
  /**
   * Return the name of the rule
   * @return a potentially null string
   */
  String getName();
  
  /**
   * Return a description and justification of the rule
   * @return a potentially null string
   */
  String getDescription();
  
}
