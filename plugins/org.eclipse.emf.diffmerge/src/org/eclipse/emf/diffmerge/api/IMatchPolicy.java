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

import java.util.Comparator;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A policy that defines how model elements from different model scopes are being
 * matched in a comparison.
 * @author Olivier Constant
 */
public interface IMatchPolicy {
  
  /**
   * Return an object which uniquely discriminates the given element within the given scope
   * and that can be used as a criterion for matching.
   * The returned object can classically be an ID, or anything else.
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
   * @return a potentially null object
   */
  Object getMatchID(EObject element_p, IModelScope scope_p);
  
  /**
   * Optionally return a comparator which is applicable to all objects that getMatchID
   * may return. Its behavior on other objects has no consequences.
   * If present, it is used to increase performance.
   * @return a potentially null comparator
   */
  Comparator<Object> getMatchIDComparator();
  
  
  /**
   * A predefined comparator that is solely based on the natural order of objects
   * that implement Comparable, such as Strings.
   * It never throws ClassCastException; instead, its compare method returns 0
   * when objects cannot be compared, either because they are not Comparable or
   * because they are incompatible sorts of Comparable. In these cases, this
   * comparator is thus inconsistent with equals.
   * @see Comparable
   */
  Comparator<Object> NATURAL_ORDER_COMPARATOR = new Comparator<Object>() {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int compare(Object o1_p, Object o2_p) {
      int result = 0;
      if (o1_p instanceof Comparable<?> && o2_p instanceof Comparable<?>) {
        try {
          Comparable c1 = (Comparable)o1_p;
          Comparable c2 = (Comparable)o2_p;
          result = c1.compareTo(c2);
        } catch (ClassCastException e) {
          // Objects cannot be compared: Proceed
        }
      }
      return result;
    }
  };
  
}
