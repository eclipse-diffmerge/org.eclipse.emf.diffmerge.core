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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A default merge policy.
 * Conformity to references of multiplicity [1] is enforced by default.
 * @author Olivier Constant
 */
public class DefaultMergePolicy implements IMergePolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#bindPresenceToOwnership(org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public boolean bindPresenceToOwnership(IFeaturedModelScope scope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyExtrinsicIDs(org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public boolean copyExtrinsicIDs(IFeaturedModelScope sourceScope_p,
      IFeaturedModelScope targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyFeature(org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public boolean copyFeature(EStructuralFeature feature_p, IFeaturedModelScope scope_p) {
    return !feature_p.isDerived() && feature_p.isChangeable() &&
      !FeatureMapUtil.isFeatureMap(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyOutOfScopeCrossReferences(org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public boolean copyOutOfScopeCrossReferences(
      IFeaturedModelScope sourceScope_p, IFeaturedModelScope targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#getAdditionGroup(EObject, IFeaturedModelScope)
   */
  public Set<EObject> getAdditionGroup(EObject element_p,
      IFeaturedModelScope scope_p) {
    Set<EObject> result = new FHashSet<EObject>();
    for (EReference reference : element_p.eClass().getEAllReferences()) {
      if (!reference.isDerived() && !reference.isContainer() &&
          (!reference.isContainment() || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForAddition(reference))
        result.addAll(scope_p.get(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#getDeletionGroup(EObject, IFeaturedModelScope)
   */
  public Set<EObject> getDeletionGroup(EObject element_p,
      IFeaturedModelScope scope_p) {
    Set<EObject> result = new FHashSet<EObject>();
    for (EReference reference : element_p.eClass().getEAllReferences()) {
      if (!reference.isDerived() && !reference.isContainer() &&
          (!reference.isContainment() || !bindPresenceToOwnership(scope_p)) &&
          isMandatoryForDeletion(reference))
        result.addAll(scope_p.get(element_p, reference));
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#getDesiredValuePosition(org.eclipse.emf.diffmerge.api.IComparison, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.api.IMatch)
   */
  public int getDesiredValuePosition(IComparison comparison_p, Role destination_p,
      IMatch source_p, EReference reference_p, IMatch value_p) {
    EObject sourceHolder = source_p.get(destination_p.opposite());
    EObject destinationHolder = source_p.get(destination_p);
    EObject sourceValue = value_p.get(destination_p.opposite());
    if (sourceHolder != null && destinationHolder != null && sourceValue != null) {
      List<EObject> sourceValues = comparison_p.getScope(destination_p.opposite()).get(
          sourceHolder, reference_p);
      List<EObject> destinationValues = comparison_p.getScope(destination_p).get(
          destinationHolder, reference_p);
      // Priority is given to the successor
      int start = sourceValues.indexOf(sourceValue) + 1;
      for (int i = start; i < sourceValues.size(); i++) {
        EObject nextSourceElement = sourceValues.get(i);
        IMatch nextMatch = comparison_p.getMapping().getMatchFor(
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
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#isMandatoryForAddition(org.eclipse.emf.ecore.EReference)
   */
  public boolean isMandatoryForAddition(EReference reference_p) {
    return isSingleMandatory(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#isMandatoryForDeletion(org.eclipse.emf.ecore.EReference)
   */
  public boolean isMandatoryForDeletion(EReference reference_p) {
    EReference opposite = reference_p.getEOpposite();
    return opposite != null && isSingleMandatory(opposite);
  }
  
  /**
   * Return whether the given reference is of multiplicity [1]
   * @param reference_p a non-null reference
   */
  protected boolean isSingleMandatory(EReference reference_p) {
    return !reference_p.isMany() && reference_p.getLowerBound() > 0;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#setIntrinsicID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope, org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  public void setIntrinsicID(EObject source_p, IFeaturedModelScope sourceScope_p,
      EObject target_p, IFeaturedModelScope targetScope_p) {
    // Nothing by default: intrinsic IDs are copied like other attributes
  }
  
}
