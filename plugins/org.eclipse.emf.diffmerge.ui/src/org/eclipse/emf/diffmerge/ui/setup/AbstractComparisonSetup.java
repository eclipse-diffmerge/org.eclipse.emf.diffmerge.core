/**
 * <copyright>
 * 
 * Copyright (c) 2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.setup;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;


/**
 * A base implementation for comparison setups and methods.
 * @author Olivier Constant
 */
public abstract class AbstractComparisonSetup {
  
  /** The non-null role that corresponds to the left-hand side, TARGET or REFERENCE */
  private Role _leftRole;
  
  
  /**
   * Constructor
   */
  protected AbstractComparisonSetup() {
    _leftRole = getDefaultLeftRole();
  }
  
  /**
   * Return the role that corresponds to the left-hand side by default
   * @return TARGET or REFERENCE
   */
  protected Role getDefaultLeftRole() {
    return EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod#getLeftRole()
   */
  public Role getLeftRole() {
    return _leftRole;
  }
  
  /**
   * Swap the role that corresponds to the left-hand side.
   * As a result, the left role is the opposite of what it was.
   */
  public void swapLeftRole() {
    _leftRole = _leftRole.opposite();
  }
  
}
