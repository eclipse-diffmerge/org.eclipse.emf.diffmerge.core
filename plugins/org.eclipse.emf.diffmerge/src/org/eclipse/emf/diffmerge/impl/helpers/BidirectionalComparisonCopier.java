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

import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
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
  public EObject completeMatch(IMapping mapping_p, IMatch partialMatch_p) {
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
  public void completeReferences(IMapping mapping_p, Role role_p) {
    UnidirectionalComparisonCopier involvedCopier =
      (role_p == Role.TARGET)? _referenceToTargetCopier:
        _targetToReferenceCopier;
    involvedCopier.completeReferences(mapping_p.getComparison());
  }
  
}
