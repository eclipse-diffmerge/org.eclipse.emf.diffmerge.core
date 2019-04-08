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
package org.eclipse.emf.diffmerge.generic.impl.helpers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.generic.Messages;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergeSelector;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;


/**
 * An operation which merges a given set of differences in a given direction.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class MergeOperation<E> extends AbstractExpensiveOperation {
  
  /** The non-null comparison */
  protected final IComparison<E> _comparison;
  
  /** The optional merger, non-null iff isGlobal() */
  protected final IMergeSelector<E> _merger;
  
  /** The non-null set of differences to merge (relevant only if !isGlobal()) */
  protected final Collection<? extends IDifference<E>> _toMerge;
  
  /** The optional destination role (TARGET or REFERENCE), null iff isGlobal() */
  protected final Role _destinationRole;
  
  /** Whether references of the elements added must be set */
  protected final boolean _updateReferences;
  
  /** The non-null set of differences that have actually been merged (initially empty) */
  protected final Collection<IDifference<E>> _actuallyMerged;
  
  
  /**
   * Constructor for a selected subset of differences
   * @param comparison_p a non-null comparison
   * @param differences_p a non-null, potentially empty set of differences to merge
   * @param destination_p a role which is TARGET or REFERENCE
   * @param updateReferences_p whether references of the elements added must be set
   */
  public MergeOperation(IComparison<E> comparison_p,
      Collection<? extends IDifference<E>> differences_p, Role destination_p,
      boolean updateReferences_p) {
    super();
    _comparison = comparison_p;
    _toMerge = differences_p;
    _destinationRole = destination_p;
    _merger = null;
    _updateReferences = updateReferences_p;
    _actuallyMerged = new FArrayList<IDifference<E>>();
  }
  
  /**
   * Constructor for a global merger
   * @param comparison_p a non-null comparison
   * @param merger_p a non-null merger
   * @param updateReferences_p whether references of the elements added must be set
   */
  public MergeOperation(IComparison<E> comparison_p, IMergeSelector<E> merger_p,
      boolean updateReferences_p) {
    super();
    _comparison = comparison_p;
    _toMerge = Collections.emptySet();
    _destinationRole = null;
    _merger = merger_p;
    _updateReferences = updateReferences_p;
    _actuallyMerged = new FArrayList<IDifference<E>>();
  }
  
  /**
   * Return the set of differences which have actually been merged
   * @return a non-null, potentially empty, unmodifiable collection
   */
  public Collection<IDifference<E>> getOutput() {
    return Collections.unmodifiableCollection(_actuallyMerged);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation#getOperationName()
   */
  public String getOperationName() {
    return Messages.MergeOperation_Name;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.helpers.AbstractExpensiveOperation#getWorkAmount()
   */
  @Override
  protected int getWorkAmount() {
    // 1 init, 1 for each diff, 1 for ref update if required
    int nbDiffs = isGlobal()? _comparison.getMapping().size(): _toMerge.size();
    return 1 + nbDiffs + (_updateReferences? 1: 0); 
  }
  
  /**
   * Return whether the merge operation is global (based on a merger) or local
   * (based on a predefined subset of the differences)
   */
  protected boolean isGlobal() {
    return _merger != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation#run()
   */
  public IStatus run() {
    getMonitor().worked(1);
    IStatus result;
    if (isGlobal()) {
      result = runOnComparison();
    } else {
      result = runOnSet();
    }
    if (_updateReferences && result != null && result.isOK()) {
      checkProgress();
      IMapping.Editable<E> mapping = (IMapping.Editable<E>)_comparison.getMapping();
      if (_destinationRole != null) {
        mapping.completeReferences(_destinationRole);
      } else {
        mapping.completeReferences(Role.TARGET);
        mapping.completeReferences(Role.REFERENCE);
      }
      getMonitor().worked(1);
    }
    return result;
  }
  
  /**
   * Run the merge operation on the whole comparison with a merger
   * @return a non-null status
   */
  protected IStatus runOnComparison() {
    for (IMatch<E> match : _comparison.getMapping().getContents()) {
      for (IDifference<E> difference : match.getAllDifferences()) {
        checkProgress();
        Role mergeDirection = _merger.getMergeDirection(difference);
        if (mergeDirection != null && difference.canMergeTo(mergeDirection)) {
          try {
            Collection<IDifference<E>> merged =
              ((IMergeableDifference<E>)difference).mergeTo(mergeDirection);
            _actuallyMerged.addAll(merged);
          } catch (UnsupportedOperationException e) {
            // Required differences cannot be merged: proceed
          }
        }
      }
      getMonitor().worked(1);
    }
    return Status.OK_STATUS;
  }
  
  /**
   * Run the merge operation on a selected subset of differences
   * @return a non-null status
   */
  protected IStatus runOnSet() {
    for (IDifference<E> difference : _toMerge) {
      checkProgress();
      try {
        if (difference instanceof IMergeableDifference) {
          Collection<IDifference<E>> merged =
            ((IMergeableDifference<E>)difference).mergeTo(_destinationRole);
          _actuallyMerged.addAll(merged);
        }
      } catch (UnsupportedOperationException e) {
        // Cannot merge this difference: proceed
      }
      getMonitor().worked(1);
    }
    return Status.OK_STATUS;
  }
  
}
