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
