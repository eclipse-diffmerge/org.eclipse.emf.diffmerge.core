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

import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IPersistentModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A bidirectional immutable copier for a given mapping.
 * @author Olivier Constant
 */
public class BidirectionalComparisonCopier {
  
  /** The non-null copier from REFERENCE to TARGET */
  private final UnidirectionalComparisonCopier _referenceToTargetCopier;
  
  /** The non-null copier from TARGET to REFERENCE */
  private final UnidirectionalComparisonCopier _targetToReferenceCopier;
  
  
  /**
   * Constructor
   */
  public BidirectionalComparisonCopier() {
    _referenceToTargetCopier = new UnidirectionalComparisonCopier(Role.REFERENCE);
    _targetToReferenceCopier = new UnidirectionalComparisonCopier(Role.TARGET);
  }
  
  
  /**
   * Complete the given partial match by copying its unmatched element and
   * updating this mapping accordingly.
   * The references of the element are not completed.
   * Postcondition: !partialMatch_p.isPartial()
   * @param mapping_p a non-null mapping
   * @param partialMatch_p a non-null partial match
   * @return a non-null element which is a clone of the element in partialMatch_p
   */
  public EObject completeMatch(IMapping.Editable mapping_p, IMatch partialMatch_p) {
    assert partialMatch_p.isPartial();
    Role sourceRole = partialMatch_p.getUncoveredRole().opposite();
    UnidirectionalComparisonCopier involvedCopier =
      (sourceRole == Role.REFERENCE)? _referenceToTargetCopier:
        _targetToReferenceCopier;
    EObject result = involvedCopier.completeMatch(
        partialMatch_p, mapping_p.getComparison());
    return result;
  }
  
  /**
   * Complete the references between all completed elements in the given role
   * @param mapping_p a non-null mapping
   * @param role_p a role which is TARGET or REFERENCE
   */
  public void completeReferences(IMapping.Editable mapping_p, Role role_p) {
    UnidirectionalComparisonCopier involvedCopier =
      (role_p == Role.TARGET)? _referenceToTargetCopier:
        _targetToReferenceCopier;
    involvedCopier.completeReferences(mapping_p.getComparison());
  }
  
  /**
   * Handle ID copy for the given target element from the given target scope
   * according to the given source element from the given source scope and
   * the given merge policy
   * @param source_p a non-null element
   * @param sourceScope_p a non-null scope
   * @param target_p a non-null element
   * @param targetScope_p a non-null scope
   * @param mergePolicy_p a non-null merge policy
   */
  public static void handleIDCopy(EObject source_p, IFeaturedModelScope sourceScope_p,
      EObject target_p, IFeaturedModelScope targetScope_p, IMergePolicy mergePolicy_p) {
    if (mergePolicy_p.copyExtrinsicIDs(sourceScope_p, targetScope_p) &&
        sourceScope_p instanceof IPersistentModelScope &&
        targetScope_p instanceof IPersistentModelScope.Editable) {
      Object extrinsicID = ((IPersistentModelScope)sourceScope_p).getExtrinsicID(source_p);
      ((IPersistentModelScope.Editable)targetScope_p).setExtrinsicID(target_p, extrinsicID);
    }
    mergePolicy_p.setIntrinsicID(source_p, sourceScope_p, target_p, targetScope_p);
  }
  
}
