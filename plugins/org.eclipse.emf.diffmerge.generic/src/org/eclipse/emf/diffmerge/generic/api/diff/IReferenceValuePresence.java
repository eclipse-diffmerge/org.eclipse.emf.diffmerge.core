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

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A difference which represents the unmatched presence of a reference value
 * (an element being referenced).
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IReferenceValuePresence<E> extends IValuePresence<E> {
  
  /**
   * Return the difference corresponding to the opposite side of the same link, if any
   * @return a potentially null reference value presence
   */
  IReferenceValuePresence<E> getOpposite();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getSymmetrical()
   */
  IReferenceValuePresence<E> getSymmetrical();
  
  /**
   * Return the difference, if any, which is the symmetrical ownership
   * of the value in the opposite role
   * @see IReferenceValuePresence#isSymmetricalOwnershipTo(IReferenceValuePresence)
   * Class invariant: getSymmetricalOwnership() == null ||
   *                      isSymmetricalOwnershipTo(getSymmetricalOwnership())
   * @return a potentially null reference value presence
   */
  IReferenceValuePresence<E> getSymmetricalOwnership();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#getValue()
   */
  E getValue();
  
  /**
   * Return the match that corresponds to the value, if any.
   * Class invariant:
   *  isOutOfScope() || getValueMatch().get(getPresenceRole()) == getValue()
   * @return a match that is non-null if and only if the value is in the presence scope
   */
  IMatch<E> getValueMatch();
  
  /**
   * Return whether the reference of this value presence represents a containment,
   * independently of the fact that it may represent an order.
   */
  boolean isContainment();
  
  /**
   * Return whether the given reference value presence corresponds to
   * the same link as this difference on the opposite reference
   * @param peer_p a non-null reference value presence
   */
  boolean isOppositeOf(IReferenceValuePresence<E> peer_p);
  
  /**
   * Return whether the value is outside the presence scope.
   * Class invariant:
   *    isOutOfScope() == (getValueMatch() == null)
   * Class invariant:
   *    !isOutOfScope() || getFeature() != null
   * @see IDiffPolicy#coverOutOfScopeValue(Object, Object, ITreeDataScope)
   */
  boolean isOutOfScope();
  
  /**
   * Return whether this reference value presence represents an ownership,
   * i.e., it represents an arc in the containment tree.
   * Class invariant: isOwnership() == (!isOrder() && isContainment())
   */
  boolean isOwnership();
  
  /**
   * Return whether the given reference value presence corresponds to
   * the symmetrical ownership of the same value.
   * Postcondition: !result || isOwnership() &&
   *  getValueMatch() != null && getValueMatch() == peer_p.getValueMatch() &&
   *  getPresenceRole() == peer_p.getPresenceRole().opposite()
   * @param peer_p a non-null reference value presence
   */
  boolean isSymmetricalOwnershipTo(IReferenceValuePresence<E> peer_p);
  
}
