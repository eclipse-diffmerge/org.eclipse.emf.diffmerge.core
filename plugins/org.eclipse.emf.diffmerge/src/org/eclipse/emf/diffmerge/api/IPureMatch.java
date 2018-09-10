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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A match between model elements that play different roles in a comparison.
 * @author Olivier Constant
 */
public interface IPureMatch {
  
  /**
   * Return whether the receiver involves an element that plays the given role
   * @param role_p a non-null role
   */
  boolean coversRole(Role role_p);
  
  /**
   * Return whether the given object is a PureMatch which matches the same elements
   * @see java.lang.Object#equals(Object)
   */
  boolean equals(Object object_p);
  
  /**
   * Return the element that plays the given role
   * @param role_p a non-null role
   * @return the element, or null if none
   */
  EObject get(Role role_p);
  
  /**
   * Return the mapping which owns this match
   * @return a non-null mapping
   */
  IMapping getMapping();
  
  /**
   * Return the match ID that corresponds to this match, if available
   * @return a potentially null object
   * @see IMatchPolicy#keepMatchIDs()
   */
  Object getMatchID();
  
  /**
   * Return the role (TARGET or REFERENCE only) which is not covered by this match, if any
   * @return a potentially null role
   */
  Role getUncoveredRole();
  
  /**
   * @see java.lang.Object#hashCode()
   */
  int hashCode();
  
  /**
   * Return whether the receiver maps an element belonging to the given resource.
   * @param resource_p a potentially null resource
   */
  boolean involves(Resource resource_p);
  
  /**
   * Return whether the TARGET or REFERENCE role is not covered by this match.
   * Class invariant: isPartial() == getUncoveredRole() != null
   */
  boolean isPartial();
  
  /**
   * Return whether at least one of the given roles is not covered by this match.
   * Class invariant: isPartial(role1_p, role2_p) == isPartial(role2_p, role1_p)
   * Class invariant: isPartial(TARGET, REFERENCE) == isPartial()
   */
  boolean isPartial(Role role1_p, Role role2_p);
  
  /**
   * Return whether this match corresponds to the given elements,
   * ignoring the ancestor if any.
   * @param target_p a potentially null element playing the TARGET role
   * @param reference_p a potentially null element playing the REFERENCE role
   */
  boolean maps(EObject target_p, EObject reference_p);
  
  /**
   * Return whether this match corresponds to the given elements.
   * @param target_p a potentially null element playing the TARGET role
   * @param reference_p a potentially null element playing the REFERENCE role
   * @param ancestor_p a potentially null element playing the ANCESTOR role
   */
  boolean maps(EObject target_p, EObject reference_p, EObject ancestor_p);
  
  
  /**
   * A match with editing features.
   * All concrete classes implementing IPureMatch must also implement this interface.
   */
  interface Editable extends IPureMatch {
    /**
     * Reset this match with the given target, reference and ancestor elements
     * Precondition: at least one of the elements is not null
     * @param target_p the optional element on the TARGET side
     * @param reference_p the optional element on the REFERENCE side
     * @param ancestor_p the optional element on the ANCESTOR side
     */
    void reset(EObject target_p, EObject reference_p, EObject ancestor_p);
    
    /**
     * Set the given role to the given element
     * @param role_p a non-null role
     * @param element_p a potentially null element
     */
    void set(Role role_p, EObject element_p);
    
    /**
     * Set the match ID that corresponds to this match
     * @param matchID_p a potentially null object
     */
    void setMatchID(Object matchID_p);
  }
  
}
