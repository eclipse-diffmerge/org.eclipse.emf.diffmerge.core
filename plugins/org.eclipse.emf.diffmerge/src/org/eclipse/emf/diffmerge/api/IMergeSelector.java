/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api;

import org.eclipse.emf.diffmerge.api.diff.IDifference;


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
  Role getMergeDirection(IDifference difference_p);
  
}
