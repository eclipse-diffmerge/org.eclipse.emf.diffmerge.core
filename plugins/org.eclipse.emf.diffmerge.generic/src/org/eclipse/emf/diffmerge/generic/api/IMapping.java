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
package org.eclipse.emf.diffmerge.generic.api;

import java.util.Collection;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.scopes.IRawDataScope;


/**
 * A mapping between model scopes which is such that every element of either scope
 * is mapped at most once.
 * A mapping is defined as a set of IMatch.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IMapping<E, A, R> {
  
  /**
   * Return whether the given element is covered by this mapping in the given role
   * @param element_p a non-null element
   * @param role_p a non-null role
   */
  boolean covers(E element_p, Role role_p);
  
  /**
   * Return the comparison this mapping belongs to
   * @return a non-null comparison
   */
  IComparison<E, A, R> getComparison();
  
  /**
   * Return the formerly partial matches which have been completed
   * by addition in the given role
   * Postcondition: for every match m in the result, !m.isPartial()
   * @see IMatch#isPartial()
   * @param destinationRole_p a role which is TARGET or REFERENCE
   * @return a non-null, potentially empty, unmodifiable collection
   */
  Collection<IMatch<E, A, R>> getCompletedMatches(Role destinationRole_p);
  
  /**
   * Return the content of this mapping.
   * The order in the returned collection must be such that every match appears before
   * matches of its children in side getOrderingRole().
   * @return a non-null, potentially empty, unmodifiable collection of matches
   */
  Collection<IMatch<E, A, R>> getContents();
  
  /**
   * Return the match for the given element playing the given role,
   * or null if no such match exists or if any of the parameters is null
   * @param element_p a potentially null element
   * @param role_p a potentially null role
   * @return a potentially null match
   */
  IMatch<E, A, R> getMatchFor(E element_p, Role role_p);
  
  /**
   * Return the number of non-partial matches in this mapping
   */
  int getNbFullMatches();
  
  /**
   * Return the number of partial matches in this mapping
   * @param covered_p an optional role covered by the matches concerned
   */
  int getNbPartialMatches(Role covered_p);
  
  /**
   * Return the side in which the contents of the mapping are ordered
   * so that the match of a container appears before the match of a child
   * @see IMapping#getContents()
   * @return a role which is TARGET or REFERENCE
   */
  Role getOrderingRole();
  
  /**
   * Return whether every element in the given scope is handled by this mapping in the given role
   * @param scope_p a non-null model scope
   * @param role_p a non-null comparison role
   */
  boolean isCompleteFor(IRawDataScope<E> scope_p, Role role_p);
  
  /**
   * Return whether this mapping is empty
   */
  boolean isEmpty();
  
  /**
   * Return whether there is a match corresponding to the given elements.
   * maps(null, null) returns false.
   * @param target_p a potentially null element playing the TARGET role
   * @param reference_p a potentially null element playing the REFERENCE role
   */
  boolean maps(E target_p, E reference_p);
  
  /**
   * Return whether there is a match corresponding to the given elements.
   * maps(null, null, null) returns false.
   * @param target_p a potentially null element playing the TARGET role
   * @param reference_p a potentially null element playing the REFERENCE role
   * @param ancestor_p a potentially null element playing the ANCESTOR role
   */
  boolean maps(E target_p, E reference_p, E ancestor_p);
  
  /**
   * Return whether there is a match corresponding to the given elements for
   * the given roles.
   * Note that !maps(null, _, null, _).
   * @param element1_p a potentially null element playing role role1_p
   * @param role1_p a non-null role
   * @param element2_p a potentially null element playing role role2_p
   * @param role2_p a non-null role which is different from role1_p
   */
  boolean maps(E element1_p, Role role1_p, E element2_p, Role role2_p);
  
  /**
   * Return the number of matches, partial or not, in this mapping
   * Class invariant: size() == getNbPartialMatches(null) + getNbFullMatches()
   */
  int size();
  
  /**
   * Return the number of matches, partial or not, in this mapping for the
   * given role
   * @param role_p a non-null role
   */
  int size(Role role_p);
  
  /**
   * Return a map of the elements of the given key role to the elements of the given
   * value role
   * @param keyRole_p a non-null role
   * @param valueRole_p a non-null role
   * @return a non-null, unmodifiable map based on equality by object reference
   *         which is not kept in sync with the mapping
   */
  EMap<E, E> toMap(Role keyRole_p, Role valueRole_p);
  
  
  /**
   * A mapping with editing features.
   * All concrete classes implementing IMapping must also implement this interface.
   */
  interface Editable<E, A, R> extends IMapping<E, A, R> {
    
    /**
     * Clear this mapping
     */
    void clear();
    
    /**
     * Complete the given partial match by copying its unmatched element and
     * updating this mapping accordingly.
     * The references of the element are not completed.
     * Postcondition: !partialMatch_p.isPartial()
     * @param partialMatch_p a non-null match such that partialMatch_p.isPartial()
     * @return a non-null element which is a clone of the element in partialMatch_p
     */
    E completeMatch(IMatch<E, A, R> partialMatch_p);
    
    /**
     * Complete the references between all completed elements in the given role
     * @param role_p a role which is TARGET or REFERENCE
     */
    void completeReferences(Role role_p);
    
    /**
     * Register cross-references which are not covered by differences
     * @param role_p a role which is TARGET or REFERENCE
     */
    void crossReference(Role role_p);
    
    /**
     * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getComparison()
     */
    IComparison.Editable<E, A, R> getComparison();
    
    /**
     * Return a modifiable collection of the formerly partial matches
     * which have been completed by addition in the given role
     * @param destinationRole_p a role which is TARGET or REFERENCE
     * @return a non-null, potentially empty, modifiable collection
     */
    Collection<IMatch<E, A, R>> getModifiableCompletedMatches(Role destinationRole_p);
    
    /**
     * Return the content of this mapping as a modifiable collection
     * Class invariant: getModifiableContents().equals(getContents())
     * @return a non-null, potentially empty, modifiable collection of matches
     */
    Collection<? extends IMatch<E, A, R>> getModifiableContents();
    
    /**
     * Map the given element from the given role to no other element.
     * If a match for the given element is already present, it is removed
     * to enforce consistency.
     * @param element_p a non-null element playing role role_p
     * @param role_p a non-null role
     * @return the non-null new match
     */
    IMatch.Editable<E, A, R> map(E element_p, Role role_p);
    
    /**
     * Map the given elements from the given roles, reusing existing matches
     * when possible, and return whether a contradiction has been detected.
     * Redundant and contradicting matches are removed to enforce consistency.
     * @param element1_p a potentially null element playing role role1_p
     * @param role1_p a non-null role
     * @param element2_p a potentially null element playing role role2_p
     * @param role2_p a non-null role which is different from role1_p
     * @return true if a contradicting match existed before, false otherwise
     */
    boolean mapIncrementally(E element1_p, Role role1_p,
        E element2_p, Role role2_p);
  }
  
}
