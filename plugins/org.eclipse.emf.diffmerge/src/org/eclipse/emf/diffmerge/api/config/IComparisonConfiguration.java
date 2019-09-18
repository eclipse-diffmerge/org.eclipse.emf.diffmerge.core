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

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;


/**
 * The configuration of a comparison.
 * @author Olivier Constant
 */
public interface IComparisonConfiguration {
  
  /**
   * Return the diff policy for the comparison
   * @return a potentially null diff policy (null is for default)
   */
  IDiffPolicy getDiffPolicy();
  
  /**
   * Return the match policy for the comparison
   * @return a potentially null match policy (null is for default)
   */
  IMatchPolicy getMatchPolicy();
  
  /**
   * Return the merge policy for the comparison
   * @return a potentially null merge policy (null is for default)
   */
  IMergePolicy getMergePolicy();
  
}
