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

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A policy that defines how model elements from different model scopes are being
 * matched in a comparison.
 * @author Olivier Constant
 */
public interface IMatchPolicy {
  
  /**
   * Return an object which uniquely characterizes the given element within the given scope
   * and that can be used as a criterion for matching.
   * The returned object can be a classic EMF ID (intrinsic/extrinsic) or anything else.
   * If null is returned, then the element will have no match.
   * Two elements E1, E2 from scopes S1, S2 will match if and only if
   * getMatchId(E1, S1) != null && getMatchId(E1, S1).equals(getMatchId(E2, S2)).
   * Precondition: scope_p.covers(element_p)
   * Class invariant (uniqueness):
   *  for every E1, E2 in scope_p.getAllContentsAsSet(),
   *  if E1 != E2 && getMatchId(E1, scope_p) != null then
   *  !getMatchId(E1, scope_p).equals(getMatchId(E2, scope_p))
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null comparable object
   */
  Comparable<?> getMatchId(EObject element_p, IModelScope scope_p);
  
}
