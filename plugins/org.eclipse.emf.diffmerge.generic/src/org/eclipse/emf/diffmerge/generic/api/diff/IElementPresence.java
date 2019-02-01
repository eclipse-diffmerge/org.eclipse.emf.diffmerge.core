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
package org.eclipse.emf.diffmerge.generic.api.diff;

import org.eclipse.emf.diffmerge.generic.api.IMatch;


/**
 * A difference which represents the presence of an unmatched element.
 * 
 * - If the difference is merged as an addition of the element, i.e., the destination
 *   role is getPresenceRole().opposite(), then the element is copied and added to the
 *   destination scope. For performance reasons, references between added elements are
 *   not copied until completeReferences(destinationRole) is called on the mapping
 *   (IMapping) of the comparison. This is automatically done when merge(...) is called
 *   on the comparison with the updateReferences_p parameter set to true.
 * 
 * - If the difference is merged as a deletion of the element, i.e., the destination
 *   role is getPresenceRole(), then the element is removed from its scope. The exact
 *   semantics of the removal depends on the scope.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IElementPresence<E, A, R> extends IElementRelativeDifference<E, A, R>,
IPresenceDifference<E, A, R>, IMergeableDifference<E, A, R> {
  
  /**
   * Return the element whose presence is represented by this difference
   * @return a non-null element
   */
  E getElement();
  
  /**
   * Return the match for the owner of the element
   * @return a potentially null match
   */
  IMatch<E, A, R> getOwnerMatch();
  
  /**
   * Return whether the element is a root in its scope.
   * Class invariant: isRoot() == (getOwnerMatch() == null)
   */
  boolean isRoot();
  
}
