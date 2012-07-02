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
package org.eclipse.emf.diffmerge.impl.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.EMFDiffMergePlugin;
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMapping;
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
  private final IComparison _comparison;
  
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison whose mapping is to be built
   * @param policy_p a non-null match policy
   */
  public MatchOperation(IComparison comparison_p, IMatchPolicy policy_p) {
    super();
    _comparison = comparison_p;
    _policy = policy_p;
  }
  
  /**
   * Explore the scope of the given role and fill the mapping with its elements,
   * not attempting to match them
   * @param role_p a non-null role
   * @param rememberIds_p whether match IDs must be remembered and returned in a map
   * @return an unmodifiable map of (criterion, element) with no null value and which is empty if !rememberIds_p
   */
  protected Map<Comparable<?>, EObject> explore(Role role_p, boolean rememberIds_p) {
    Map<Comparable<?>, EObject> result;
    if (rememberIds_p)
      // Use the natural ordering of comparables in the TreeMap
      result = new TreeMap<Comparable<?>, EObject>();
    else
      result = Collections.emptyMap();
    IModelScope scope = _comparison.getScope(role_p);
    if (scope != null) {
      // Explore the scope, marking its elements as unmatched
      // and registering their match IDs
      Iterator<EObject> it = scope.getAllContents();
      IMapping.Editable mapping = (IMapping.Editable)_comparison.getMapping();
      while (it.hasNext()) {
        checkProgress();
        EObject current = it.next();
        mapping.map(current, role_p);
        if (rememberIds_p) {
          Comparable<?> id = getMatchPolicy().getMatchId(current, scope);
          if (id != null) {
            EObject squatter = result.put(id, current);
            if (squatter != null)
              EMFDiffMergePlugin.getDefault().warn(
                  Messages.MatchBuilder_WarningDuplicateIDs + id);
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
   *        and which is null iff IdRegistry2_p is null
   * @param rememberIds_p whether match IDs must be remembered and returned in a map
   * @return an unmodifiable map of (ID, element) with no null value and which is empty if !rememberIds_p
   */
  protected Map<Comparable<?>, EObject> exploreAndMatch(Role role_p,
      Map<Comparable<?>, EObject> idRegistry1_p, Role secondaryRole1_p,
      Map<Comparable<?>, EObject> idRegistry2_p, Role secondaryRole2_p,
      boolean rememberId_p) {
    Map<Comparable<?>, EObject> result;
    if (rememberId_p)
      // Use the ordering of Comparables in the TreeMap
      result = new TreeMap<Comparable<?>, EObject>();
    else
      result = Collections.emptyMap();
    IModelScope scope = _comparison.getScope(role_p);
    if (scope != null) {
      Iterator<EObject> targetIt = scope.getAllContents();
      IMapping.Editable mapping = (IMapping.Editable)_comparison.getMapping();
      while (targetIt.hasNext()) {
        checkProgress();
        EObject current = targetIt.next();
        EObject counterpart1 = null;
        EObject counterpart2 = null;
        Comparable<?> id = getMatchPolicy().getMatchId(current, scope);
        if (id != null) {
          if (rememberId_p) {
            EObject squatter = result.put(id, current);
            if (squatter != null)
              EMFDiffMergePlugin.getDefault().warn(
                  Messages.MatchBuilder_WarningDuplicateIDs + id);
          }
          counterpart1 = idRegistry1_p.get(id);
          counterpart2 = idRegistry2_p != null? idRegistry2_p.get(id): null;
        }
        if (counterpart1 != null)
          mapping.mapIncrementally(current, role_p, counterpart1, secondaryRole1_p);
        if (counterpart2 != null)
          mapping.mapIncrementally(current, role_p, counterpart2, secondaryRole2_p);
        if (counterpart1 == null && counterpart2 == null)
          mapping.map(current, role_p);
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
  public IComparison getOutput() {
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
    return _comparison.isThreeWay()? 3: 4; // 1 init, 2|3 for ID-based matching
  }
  
  /**
   * Fill the mapping destructively
   * Postcondition: getOutput().isCompleteFor(TARGET)
   * Postcondition: getOutput().isCompleteFor(REFERENCE)
   * Postcondition: !getOutput().isThreeWay() || getOutput().isCompleteFor(ANCESTOR)
   */
  protected void match() {
    boolean threeWay = _comparison.isThreeWay();
    getMonitor().subTask(Messages.MatchBuilder_Task_RegisteringIDs);
    Map<Comparable<?>, EObject> referenceIdRegistry = explore(Role.REFERENCE, true);
    getMonitor().worked(1);
    getMonitor().subTask(Messages.MatchBuilder_Task_MappingIDs);
    Map<Comparable<?>, EObject> targetIdRegistry = exploreAndMatch(
        Role.TARGET, referenceIdRegistry, Role.REFERENCE, null, null, threeWay);
    getMonitor().worked(1);
    if (threeWay) {
      exploreAndMatch(Role.ANCESTOR, referenceIdRegistry, Role.REFERENCE,
          targetIdRegistry, Role.TARGET, false);
      getMonitor().worked(1);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#run()
   * Postconditions: see MatchOperation#match()
   */
  public IStatus run() {
    getMonitor().worked(1);
    match();
    return Status.OK_STATUS;
  }
  
}
