/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.api.diff;

import org.eclipse.emf.diffmerge.api.IMatch;


/**
 * A model difference which is relative to at most one element per role.
 * @author Olivier Constant
 */
public interface IElementRelativeDifference extends IDifference {
  
  /**
   * Return the match which defines the element, or the matching elements,
   * to which this difference is relative
   * @return a match which is non-null unless it represents the model root container
   */
  IMatch getElementMatch();
  
  /**
   * Return whether this difference, if merged, does not affect the containment tree
   */
  boolean isUnrelatedToContainmentTree();
  
}
