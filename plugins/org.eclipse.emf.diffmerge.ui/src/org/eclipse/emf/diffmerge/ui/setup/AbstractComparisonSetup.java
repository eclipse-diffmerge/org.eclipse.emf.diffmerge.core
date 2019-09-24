/*********************************************************************
 * Copyright (c) 2018-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.setup;

import org.eclipse.emf.diffmerge.generic.api.Role;
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
