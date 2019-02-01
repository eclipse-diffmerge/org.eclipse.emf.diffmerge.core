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
package org.eclipse.emf.diffmerge.generic.api;

import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;


/**
 * A decision maker about merging differences.
 * @see IComparison#merge(IMergeSelector, boolean, org.eclipse.core.runtime.IProgressMonitor)
 * @author Olivier Constant
 */
public interface IMergeSelector {
  
  /**
   * Return the role into which the given difference must be merged, if any.
   * Postcondition: result is in { TARGET, REFERENCE, null }
   * @param difference_p a non-null difference
   * @return a potentially null role, where null stands for no merge
   */
  Role getMergeDirection(IDifference<?, ?, ?> difference_p);
  
}
