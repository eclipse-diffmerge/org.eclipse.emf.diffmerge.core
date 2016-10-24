/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api.diff;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * A difference which represents the unmatched presence of a reference value
 * (an element being referenced).
 * @author Olivier Constant
 */
public interface IReferenceValuePresence extends IValuePresence {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getFeature()
   */
  EReference getFeature();
  
  /**
   * Return the difference corresponding to the opposite side of the same link, if any
   * @return a potentially null reference value presence
   */
  IReferenceValuePresence getOpposite();
  
  /**
   * Return the out-of-scope value being held, if any
   * @return an element that is null if and only if !isOutOfScope()
   */
  EObject getOutOfScopeValue();
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getSymmetrical()
   */
  IReferenceValuePresence getSymmetrical();
  
  /**
   * Return the difference, if any, which is the symmetrical ownership
   * of the value in the opposite role
   * @see IReferenceValuePresence#isSymmetricalOwnershipTo(IReferenceValuePresence)
   * @return a potentially null reference value presence
   */
  IReferenceValuePresence getSymmetricalOwnership();
  
  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IValuePresence#getValue()
   * @return a match that is null if and only if isOutOfScope()
   */
  IMatch getValue();
  
  /**
   * Return whether the given reference value presence corresponds to
   * the same link as this difference on the opposite reference
   * @param peer_p a non-null reference value presence
   */
  boolean isOppositeOf(IReferenceValuePresence peer_p);
  
  /**
   * Return whether the value is outside the presence scope.
   * Class invariant:
   *    isOutOfScope() == (getOutOfScopeValue() != null) == (getValue() == null)
   * @see IDiffPolicy#isSharedOutOfScopeElement(EObject)
   */
  boolean isOutOfScope();
  
  /**
   * Return whether the given reference value presence corresponds to
   * the symmetrical ownership of the same value
   * @param peer_p a non-null reference value presence
   */
  boolean isSymmetricalOwnershipTo(IReferenceValuePresence peer_p);
  
}
