/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
   * Class invariant: isPartial() => getUncoveredRole() != null
   */
  boolean isPartial();
  
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
  public static interface Editable extends IPureMatch {
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
