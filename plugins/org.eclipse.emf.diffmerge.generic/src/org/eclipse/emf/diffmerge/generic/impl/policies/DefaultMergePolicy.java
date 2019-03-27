/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.policies;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * A typical implementation of IMergePolicy.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class DefaultMergePolicy<E> implements IMergePolicy<E> {
  
  /**
   * Default constructor
   */
  public DefaultMergePolicy() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#bindPresenceToOwnership(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean bindPresenceToOwnership(ITreeDataScope<E> scope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyAttribute(Object attribute_p, ITreeDataScope<E> scope_p) {
    return scope_p.mIsChangeableAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyReference(Object reference_p, ITreeDataScope<E> scope_p) {
    return scope_p.mIsChangeableReference(reference_p);
  }
  
  /**
   * Return whether the extrinsic IDs of elements must be preserved when elements
   * are copied from the given source scope to the given target scope, if applicable
   * @param sourceScope_p a non-null scope
   * @param targetScope_p a non-null scope
   */
  protected boolean copyExtrinsicIDs(ITreeDataScope<E> sourceScope_p,
      ITreeDataScope<E> targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyOutOfScopeCrossReferences(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyOutOfScopeCrossReferences(
      ITreeDataScope<E> sourceScope_p, ITreeDataScope<E> targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getAdditionGroup(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public Set<E> getAdditionGroup(E element_p, ITreeDataScope<E> scope_p) {
    Set<E> result = new FHashSet<E>();
    for (Object reference : scope_p.mGetReferences(element_p)) {
      if (scope_p.mIsChangeableReference(reference) &&
          !scope_p.mIsContainerReference(reference) &&
          (!scope_p.mIsContainmentReference(reference) || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForAddition(element_p, reference, scope_p))
        result.addAll(scope_p.getReferenceValues(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getDeletionGroup(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public Set<E> getDeletionGroup(E element_p, ITreeDataScope<E> scope_p) {
    Set<E> result = new FHashSet<E>();
    for (Object reference : scope_p.mGetReferences(element_p)) {
      if (scope_p.mIsChangeableReference(reference) &&
          !scope_p.mIsContainerReference(reference) &&
          (!scope_p.mIsContainmentReference(reference) || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForDeletion(element_p, reference, scope_p))
        result.addAll(scope_p.getReferenceValues(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getDesiredValuePosition(org.eclipse.emf.diffmerge.generic.api.IComparison, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object)
   */
  public int getDesiredValuePosition(IComparison<E> comparison_p,
      Role destination_p, IMatch<E> source_p, Object reference_p, E sourceValue_p) {
    E sourceHolder = source_p.get(destination_p.opposite());
    E destinationHolder = source_p.get(destination_p);
    if (sourceHolder != null && destinationHolder != null && sourceValue_p != null) {
      IDataScope<E> sourceScope = comparison_p.getScope(destination_p.opposite());
      IDataScope<E> destinationScope = comparison_p.getScope(destination_p);
      List<E> sourceValues = sourceScope.getReferenceValues(sourceHolder, reference_p);
      List<E> destinationValues = destinationScope.getReferenceValues(
          destinationHolder, reference_p);
      // Priority is given to the successor
      int start = sourceValues.indexOf(sourceValue_p) + 1;
      for (int i = start; i < sourceValues.size(); i++) {
        E nextSourceElement = sourceValues.get(i);
        IMatch<E> nextMatch = comparison_p.getMapping().getMatchFor(
            nextSourceElement, destination_p.opposite());
        if (nextMatch != null) { // Should be true since scope must be complete
          E nextDestinationElement = nextMatch.get(destination_p);
          if (nextDestinationElement != null) {
            int nextDestinationIndex = destinationValues.indexOf(nextDestinationElement);
            if (nextDestinationIndex >= 0)
              return nextDestinationIndex;
          }
        }
      }
    }
    return -1;
  }
  
  /**
   * Return a new intrinsic ID for the given element, if applicable
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which the element is being added by copy
   * @return a potentially null string
   */
  protected Object getNewIntrinsicID(E element_p, ITreeDataScope<E> scope_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#isMandatoryForAddition(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean isMandatoryForAddition(E element_p, Object reference_p,
      ITreeDataScope<E> scope_p) {
    return isSingleMandatory(reference_p, scope_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#isMandatoryForDeletion(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean isMandatoryForDeletion(E element_p, Object reference_p,
      ITreeDataScope<E> scope_p) {
    Object opposite = scope_p.mGetOppositeReference(reference_p);
    return opposite != null && isSingleMandatory(opposite, scope_p);
  }
  
  /**
   * Return whether the given reference is non-many and mandatory
   * @param reference_p a non-null reference
   * @param scope_p a non-null scope where the reference is to be used
   */
  protected boolean isSingleMandatory(Object reference_p, ITreeDataScope<E> scope_p) {
    return !scope_p.mIsManyReference(reference_p) &&
        !scope_p.mIsOptionalReference(reference_p);
  }
  
  /**
   * Return whether the given newly-added element must be given a new intrinsic ID.
   * This method has an impact only if getNewIntrinsicID does not return null.
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which the element is being added by copy
   */
  protected boolean requiresNewIntrinsicID(E element_p, ITreeDataScope<E> scope_p) {
    // By default, intrinsic IDs are copied like other attributes
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#setID(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public void setID(E source_p, ITreeDataScope<E> sourceScope_p,
      E target_p, ITreeDataScope<E> targetScope_p) {
    if (copyExtrinsicIDs(sourceScope_p, targetScope_p)) {
      Object extrinsicID = sourceScope_p.getID(source_p, false);
      targetScope_p.setID(target_p, extrinsicID, false);
    }
    // By default (!requiresNewIntrinsicID()), intrinsic IDs are copied like other attributes
    if (requiresNewIntrinsicID(target_p, targetScope_p)) {
      Object newID = getNewIntrinsicID(target_p, targetScope_p);
      targetScope_p.setID(target_p, newID, true);
    }
  }
  
}
