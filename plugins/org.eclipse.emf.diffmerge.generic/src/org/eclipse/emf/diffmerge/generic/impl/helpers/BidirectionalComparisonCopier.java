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
import org.eclipse.emf.diffmerge.generic.api.Role;


/**
 * A bidirectional immutable copier for a given mapping.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public class BidirectionalComparisonCopier<E, A, R> {
  
  /** The non-null copier from REFERENCE to TARGET */
  protected final UnidirectionalComparisonCopier<E, A, R> _referenceToTargetCopier;
  
  /** The non-null copier from TARGET to REFERENCE */
  protected final UnidirectionalComparisonCopier<E, A, R> _targetToReferenceCopier;
  
  
  /**
   * Constructor
   * @param targetCopier_p a non-null copier for the TARGET side
   * @param referenceCopier_p a non-null copier for the REFERENCE side
   */
  public BidirectionalComparisonCopier(UnidirectionalComparisonCopier<E, A, R> targetCopier_p,
      UnidirectionalComparisonCopier<E, A, R> referenceCopier_p) {
    _targetToReferenceCopier = targetCopier_p;
    _referenceToTargetCopier = referenceCopier_p;
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
  public E completeMatch(IMapping.Editable<E, A, R> mapping_p, IMatch<E, A, R> partialMatch_p) {
    assert partialMatch_p.isPartial();
    Role sourceRole = partialMatch_p.getUncoveredRole().opposite();
    UnidirectionalComparisonCopier<E, A, R> involvedCopier =
      (sourceRole == Role.REFERENCE)? _referenceToTargetCopier:
        _targetToReferenceCopier;
    E result = involvedCopier.completeMatch(
        partialMatch_p, mapping_p.getComparison());
    return result;
  }
  
  /**
   * Complete the references between all completed elements in the given role
   * @param mapping_p a non-null mapping
   * @param role_p a role which is TARGET or REFERENCE
   */
  public void completeReferences(IMapping.Editable<E, A, R> mapping_p, Role role_p) {
    UnidirectionalComparisonCopier<E, A, R> involvedCopier =
      (role_p == Role.TARGET)? _referenceToTargetCopier:
        _targetToReferenceCopier;
    involvedCopier.completeReferences(mapping_p.getComparison());
  }
  
}
