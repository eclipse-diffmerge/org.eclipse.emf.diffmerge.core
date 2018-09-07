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
package org.eclipse.emf.diffmerge.impl.helpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * An operation which builds a mapping between model scopes for a comparison.
 * @author Olivier Constant
 */
public class MatchOperation extends AbstractExpensiveOperation {
  
  /** The non-null match policy */
  private final IMatchPolicy _policy;
  
  /** The non-null comparison whose mapping is being built */
  private final IComparison.Editable _comparison;
  
  /** The optional map from roles to sets of duplicate match IDs */
  protected final Map<Role, Set<Object>> _duplicateIDs;
  
  /** Duplicate IDs in role 1 which may have an impact on the mapping */
  protected final Set<Object> _duplicateCandidatesRole1;
  
  /** Duplicate IDs in role 2 which may have an impact on the mapping */
  protected final Set<Object> _duplicateCandidatesRole2;
  
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison whose mapping is to be built
   * @param policy_p a non-null match policy
   * @param duplicateIDs_p an optional map that associates each role with an empty,
   *          modifiable set of duplicate match IDs, to be filled by this operation
   */
  public MatchOperation(IComparison.Editable comparison_p, IMatchPolicy policy_p,
      Map<Role, Set<Object>> duplicateIDs_p) {
    super();
    _comparison = comparison_p;
    _policy = policy_p;
    _duplicateIDs = duplicateIDs_p;
    _duplicateCandidatesRole1 = new HashSet<Object>(0);
    _duplicateCandidatesRole2 = new HashSet<Object>(0);
  }
  
  /**
   * Create and return a new (match ID, element) empty map 
   * @return a non-null map
   */
  protected Map<Object, EObject> createMatchIDToElementMap() {
    Map<Object, EObject> result;
    @SuppressWarnings("unchecked") // No issue if properly defined, see IMatchPolicy
    Comparator<Object> comparator =
      (Comparator<Object>)getMatchPolicy().getMatchIDComparator();
    if (comparator == null)
      result = new HashMap<Object, EObject>();
    else
      result = new TreeMap<Object, EObject>(comparator);
    return result;
  }
  
  /**
   * Explore the scope of the given role and fill the mapping with its elements,
   * not attempting to match them
   * @param role_p a non-null role
   * @param fillIDMap_p whether match IDs must be remembered and returned in a map
   * @return an unmodifiable map of (criterion, element) with no null value and which is empty if !rememberIDs_p
   */
  protected Map<Object, EObject> explore(Role role_p, boolean fillIDMap_p) {
    Map<Object, EObject> result;
    if (fillIDMap_p)
      result = createMatchIDToElementMap();
    else
      result = Collections.emptyMap();
    IModelScope scope = getComparison().getScope(role_p);
    boolean rememberMatchIDs = getMatchPolicy().keepMatchIDs();
    if (scope != null) {
      // Explore the scope, marking its elements as unmatched
      // and registering their match IDs
      Iterator<EObject> it = scope.getAllContents();
      IMapping.Editable mapping = getComparison().getMapping();
      while (it.hasNext()) {
        checkProgress();
        EObject current = it.next();
        IMatch.Editable match = mapping.map(current, role_p);
        if (rememberMatchIDs || fillIDMap_p) {
          Object matchID = getMatchPolicy().getMatchID(current, scope);
          if (matchID != null) {
            if (rememberMatchIDs)
              match.setMatchID(matchID);
            if (fillIDMap_p) {
              EObject squatter = result.put(matchID, current);
              if (squatter != null && squatter != current && _duplicateIDs != null)
                _duplicateCandidatesRole1.add(matchID);
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Explore the scope of the given role and fill the mapping with its elements,
   * attempting to match them by match ID according to the ID registries for the
   * given secondary roles
   * @param role_p a non-null role
   * @param idRegistry1_p a non-null map of (ID, element)
   * @param secondaryRole1_p a non-null role which is different from role_p
   * @param idRegistry2_p a potentially null map of (ID, element)
   * @param secondaryRole2_p a role which is different from role_p and secondaryRole1_p
   *        and which is null iff idRegistry2_p is null
   * @param fillIDMap_p whether match IDs must be remembered and returned in a map
   * @return an unmodifiable map of (ID, element) with no null value and which is empty if !rememberIDs_p
   */
  protected Map<Object, EObject> exploreAndMatch(Role role_p,
      Map<Object, EObject> idRegistry1_p, Role secondaryRole1_p,
      Map<Object, EObject> idRegistry2_p, Role secondaryRole2_p,
      boolean fillIDMap_p) {
    Map<Object, EObject> result;
    if (fillIDMap_p)
      result = createMatchIDToElementMap();
    else
      result = Collections.emptyMap();
    IModelScope scope = getComparison().getScope(role_p);
    boolean rememberMatchIDs = getMatchPolicy().keepMatchIDs();
    if (scope != null) {
      Iterator<EObject> targetIt = scope.getAllContents();
      IMapping.Editable mapping = getComparison().getMapping();
      while (targetIt.hasNext()) {
        checkProgress();
        EObject current = targetIt.next();
        EObject counterpart1 = null;
        EObject counterpart2 = null;
        Object matchID = getMatchPolicy().getMatchID(current, scope);
        if (matchID != null) {
          if (fillIDMap_p) {
            EObject squatter = result.put(matchID, current);
            if (squatter != null && squatter != current && _duplicateIDs != null)
              _duplicateCandidatesRole2.add(matchID);
          }
          counterpart1 = idRegistry1_p.get(matchID);
          counterpart2 = idRegistry2_p != null? idRegistry2_p.get(matchID): null;
        }
        if (counterpart1 == null && counterpart2 == null) {
          IMatch.Editable match = mapping.map(current, role_p);
          if (rememberMatchIDs)
            match.setMatchID(matchID);
        } else {
          boolean contradiction = false;
          if (counterpart1 != null) {
            if (_duplicateCandidatesRole1.contains(matchID) && _duplicateIDs != null)
              _duplicateIDs.get(secondaryRole1_p).add(matchID);
            contradiction = mapping.mapIncrementally(current, role_p, counterpart1, secondaryRole1_p);
          }
          if (counterpart2 != null) {
            if (_duplicateCandidatesRole2.contains(matchID) && _duplicateIDs != null)
              _duplicateIDs.get(secondaryRole2_p).add(matchID);
            contradiction = contradiction ||
              mapping.mapIncrementally(current, role_p, counterpart2, secondaryRole2_p);
          }
          if (contradiction && _duplicateIDs != null)
            _duplicateIDs.get(role_p).add(matchID); // matchID cannot be null since current was matched
        }
      }
    }
    return result;
  }
  
  /**
   * Return the match policy
   * @return a non-null match policy
   */
  protected IMatchPolicy getMatchPolicy() {
    return _policy;
  }
  
  /**
   * Return the comparison which is being built
   * @return a non-null comparison
   */
  public IComparison.Editable getComparison() {
    return _comparison;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#getOperationName()
   */
  public String getOperationName() {
    return Messages.MatchBuilder_Task_Main;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.helpers.AbstractExpensiveOperation#getWorkAmount()
   */
  @Override
  protected int getWorkAmount() {
    return _comparison.isThreeWay()? 5: 6; // 1 init, 2|3 for ID-based matching, 2 for cross-refs
  }
  
  /**
   * Fill the mapping destructively
   * Postcondition: getOutput().isCompleteFor(TARGET)
   * Postcondition: getOutput().isCompleteFor(REFERENCE)
   * Postcondition: !getOutput().isThreeWay() || getOutput().isCompleteFor(ANCESTOR)
   */
  protected void match() {
    final Role firstSide = getComparison().getMapping().getOrderingRole();
    final Role secondSide = firstSide.opposite();
    boolean threeWay = _comparison.isThreeWay();
    getMonitor().subTask(Messages.MatchBuilder_Task_RegisteringIDs);
    Map<Object, EObject> firstSideIDRegistry = explore(firstSide, true);
    getMonitor().worked(1);
    getMonitor().subTask(Messages.MatchBuilder_Task_MappingIDs);
    Map<Object, EObject> secondSideIDRegistry = exploreAndMatch(
        secondSide, firstSideIDRegistry, firstSide, null, null, threeWay);
    getMonitor().worked(1);
    if (threeWay) {
      exploreAndMatch(Role.ANCESTOR, firstSideIDRegistry, firstSide,
          secondSideIDRegistry, secondSide, false);
      getMonitor().worked(1);
    }
    _duplicateCandidatesRole1.clear();
    _duplicateCandidatesRole2.clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#run()
   * Postconditions: see MatchOperation#match()
   */
  public IStatus run() {
    getMonitor().worked(1);
    match();
    IMapping.Editable mapping = _comparison.getMapping();
    mapping.crossReference(Role.TARGET);
    getMonitor().worked(1);
    mapping.crossReference(Role.REFERENCE);
    getMonitor().worked(1);
    return Status.OK_STATUS;
  }
  
}
