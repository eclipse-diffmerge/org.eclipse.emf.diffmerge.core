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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A default merge policy.
 * Conformity to references of multiplicity [1] is enforced by default.
 * @author Olivier Constant
 */
public class DefaultMergePolicy implements IMergePolicy<EObject> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#bindPresenceToOwnership(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean bindPresenceToOwnership(ITreeDataScope<EObject> scope_p) {
    return true;
  }
  
  /**
   * Return whether the extrinsic IDs of elements must be preserved when elements
   * are copied from the given source scope to the given target scope, if applicable
   * @param sourceScope_p a non-null scope
   * @param targetScope_p a non-null scope
   */
  protected boolean copyExtrinsicIDs(ITreeDataScope<EObject> sourceScope_p,
      ITreeDataScope<EObject> targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyAttribute(Object attribute_p, ITreeDataScope<EObject> scope_p) {
    return copyFeature((EStructuralFeature)attribute_p);
  }
  
  /**
   * Return whether the given feature must be copied when elements are being copied
   */
  protected boolean copyFeature(EStructuralFeature feature_p) {
    return !feature_p.isDerived() && feature_p.isChangeable() &&
        !FeatureMapUtil.isFeatureMap(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyOutOfScopeCrossReferences(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyOutOfScopeCrossReferences(
      ITreeDataScope<EObject> sourceScope_p, ITreeDataScope<EObject> targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#copyReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean copyReference(Object reference_p, ITreeDataScope<EObject> scope_p) {
    return copyFeature((EStructuralFeature)reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getAdditionGroup(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public Set<EObject> getAdditionGroup(EObject element_p, ITreeDataScope<EObject> scope_p) {
    Set<EObject> result = new FHashSet<EObject>();
    IScopePolicy<EObject> scopePolicy = scope_p.getScopePolicy();
    for (Object reference : scopePolicy.getReferences(element_p)) {
      if (scopePolicy.isChangeableReference(reference) &&
          !scopePolicy.isContainerReference(reference) &&
          (!scope_p.isContainment(reference) || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForAddition(reference))
        result.addAll(scope_p.getReferenceValues(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getDeletionGroup(Object, ITreeDataScope)
   */
  public Set<EObject> getDeletionGroup(EObject element_p,
      ITreeDataScope<EObject> scope_p) {
    Set<EObject> result = new FHashSet<EObject>();
    IScopePolicy<EObject> scopePolicy = scope_p.getScopePolicy();
    for (Object reference : scopePolicy.getReferences(element_p)) {
      if (scopePolicy.isChangeableReference(reference) &&
          !scopePolicy.isContainerReference(reference) &&
          (!scope_p.isContainment(reference) || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForDeletion(reference))
        result.addAll(scope_p.getReferenceValues(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#getDesiredValuePosition(org.eclipse.emf.diffmerge.generic.api.IComparison, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object)
   */
  public int getDesiredValuePosition(IComparison<EObject> comparison_p,
      Role destination_p, IMatch<EObject> source_p,
      Object reference_p, EObject sourceValue_p) {
    EObject sourceHolder = source_p.get(destination_p.opposite());
    EObject destinationHolder = source_p.get(destination_p);
    if (sourceHolder != null && destinationHolder != null && sourceValue_p != null) {
      IDataScope<EObject> sourceScope = comparison_p.getScope(destination_p.opposite());
      IDataScope<EObject> destinationScope = comparison_p.getScope(destination_p);
      List<EObject> sourceValues = sourceScope.getReferenceValues(sourceHolder, reference_p);
      List<EObject> destinationValues = destinationScope.getReferenceValues(
          destinationHolder, reference_p);
      // Priority is given to the successor
      int start = sourceValues.indexOf(sourceValue_p) + 1;
      for (int i = start; i < sourceValues.size(); i++) {
        EObject nextSourceElement = sourceValues.get(i);
        IMatch<EObject> nextMatch = comparison_p.getMapping().getMatchFor(
            nextSourceElement, destination_p.opposite());
        if (nextMatch != null) { // Should be true since scope must be complete
          EObject nextDestinationElement = nextMatch.get(destination_p);
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
  protected String getNewIntrinsicID(EObject element_p,
      IDataScope<EObject> scope_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#isMandatoryForAddition(java.lang.Object)
   */
  public boolean isMandatoryForAddition(Object reference_p) {
    return isSingleMandatory(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#isMandatoryForDeletion(java.lang.Object)
   */
  public boolean isMandatoryForDeletion(Object reference_p) {
    EReference reference = (EReference)reference_p;
    Object opposite = reference.getEOpposite();
    return opposite != null && isSingleMandatory(opposite);
  }
  
  /**
   * Return whether the given reference is of multiplicity [1]
   * @param reference_p a non-null reference
   */
  protected boolean isSingleMandatory(Object reference_p) {
    EReference reference = (EReference)reference_p;
    return !reference.isMany() && reference.getLowerBound() > 0;
  }
  
  /**
   * Return whether the given newly-added element must be given a new intrinsic ID.
   * This method has an impact only if getNewIntrinsicID does not return null.
   * @param element_p a non-null element
   * @param scope_p a non-null scope to which the element is being added by copy
   */
  protected boolean requiresNewIntrinsicID(EObject element_p,
      IDataScope<EObject> scope_p) {
    // By default, intrinsic IDs are copied like other attributes
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMergePolicy#setID(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public void setID(EObject source_p, ITreeDataScope<EObject> sourceScope_p,
      EObject target_p, ITreeDataScope<EObject> targetScope_p) {
    // By default (requiresNewIntrinsicID == false), intrinsic IDs are copied like other attributes
    if (requiresNewIntrinsicID(target_p, targetScope_p)) {
      String newID = getNewIntrinsicID(target_p, targetScope_p);
      targetScope_p.getScopePolicy().setID(target_p, newID, true);
    }
  }
  
}
