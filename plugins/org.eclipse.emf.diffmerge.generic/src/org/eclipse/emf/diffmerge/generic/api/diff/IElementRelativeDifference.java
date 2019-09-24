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
package org.eclipse.emf.diffmerge.generic.api.diff;

import org.eclipse.emf.diffmerge.generic.api.IMatch;


/**
 * A model difference which is relative to at most one element per role.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IElementRelativeDifference<E> extends IDifference<E> {
  
  /**
   * Return the match which defines the element, or the matching elements,
   * to which this difference is relative
   * @return a match which is non-null unless it represents the model root container
   */
  IMatch<E> getElementMatch();
  
  /**
   * Return whether this difference, if merged, does not affect the containment tree
   */
  boolean isUnrelatedToContainmentTree();
  
}
