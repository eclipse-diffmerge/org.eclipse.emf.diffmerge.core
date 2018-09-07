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
   * Return an object ("match ID") which uniquely discriminates the given element
   * within the given scope and that can be used as a criterion for matching.
   * Two elements from different scopes match if their "match IDs" are equal.
   * The "match ID" can be an actual ID or any other object.
   * If null is returned, then the corresponding element will have no match.
   * Two elements from the same scope must not have the same "match ID", otherwise
   * the match policy is considered to be non-applicable to the scope.
   * More formally:
   * Two elements E1, E2 from scopes S1, S2 will match if and only if
   * getMatchId(E1, S1) != null && getMatchId(E1, S1).equals(getMatchId(E2, S2)).
   * Precondition: scope_p.covers(element_p)
   * Class invariant (uniqueness):
   *  FOR EVERY E1, E2 IN scope_p.getAllContentsAsSet() :
   *  E1 != E2 && getMatchId(E1, scope_p) != null IMPLIES
   *  !getMatchId(E1, scope_p).equals(getMatchId(E2, scope_p))
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null object
   */
  Object getMatchID(EObject element_p, IModelScope scope_p);
  
  /**
   * Optionally return a comparator which is applicable to all objects that getMatchID
   * may return. Its behavior on other objects has no consequence.
   * If present, this comparator is used to alter the performance of the matching phase
   * by using TreeMaps instead of HashMaps (log(n) time cost for the main operations
   * but no issue related to HashMap's capacity or load factor).
   * @return a comparator or null
   * @see java.util.TreeMap
   */
  Comparator<?> getMatchIDComparator();
  
  /**
   * Return whether match ID information must be maintained for better traceability
   * but at the price of a larger memory footprint. Such information can typically
   * be used in a GUI to allow users to understand how elements have been matched.
   * @see IPureMatch#getMatchID() 
   */
  boolean keepMatchIDs();
  
  
  /**
   * A simple comparator that is solely based on the natural order of objects
   * that implement Comparable, such as Strings.
   * It never throws ClassCastException; instead, its compare method returns 0
   * when objects cannot be compared, either because they are not Comparable or
   * because they are Comparables which are not mutually comparable.
   * Note that this comparator is thus inconsistent with equals in this situation.
   * @see Comparable
   * @see Comparator
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
