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
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A default merge policy.
 * Conformity to references of multiplicity [1] is enforced by default.
 * @author Olivier Constant
 */
public class DefaultMergePolicy implements IMergePolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#bindPresenceToOwnership()
   */
  public boolean bindPresenceToOwnership() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean copyFeature(EStructuralFeature feature_p) {
    return !feature_p.isDerived() && feature_p.isChangeable() &&
      !FeatureMapUtil.isFeatureMap(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyId(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   */
  public void copyId(EObject source_p, EObject target_p) {
    if (copyXmlIds())
      ModelImplUtil.copyXmlId(source_p, target_p);
    if (useNewAttributeIds()) {
      String newId = getNewIdFor(target_p);
      if (newId != null)
        ModelImplUtil.setAttributeId(target_p, newId);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMergePolicy#copyOutOfScopeCrossReferences()
   */
  public boolean copyOutOfScopeCrossReferences() {
    return true;
  }
  
  /**
   * Return whether extrinsic (XML) IDs must be copied when elements are being copied.
   * @see IMergePolicy#getNewIdFor(EObject)
   */
  protected boolean copyXmlIds() {
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
          (!reference.isContainment() || !bindPresenceToOwnership()) &&
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
          (!reference.isContainment() || !bindPresenceToOwnership()) &&
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
   * Return a new unique intrinsic ID for the given element, if relevant.
   * This may only be relevant for elements whose meta-class owns an ID attribute.
   * @see EAttribute#isID()
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getNewIdFor(EObject element_p) {
    return copyXmlIds()? null: EcoreUtil.generateUUID();
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
   * Return whether elements must be given a new intrinsic ID when added to a scope.
   * Returning true has an impact only for elements that generate a non-null ID via
   * getNewIdFor(EObject).
   */
  protected boolean useNewAttributeIds() {
    return false;
  }
  
}
