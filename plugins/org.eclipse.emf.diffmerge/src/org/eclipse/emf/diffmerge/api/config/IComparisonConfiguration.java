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
