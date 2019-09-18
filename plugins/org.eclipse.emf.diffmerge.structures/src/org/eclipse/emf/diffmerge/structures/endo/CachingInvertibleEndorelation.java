/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;


/**
 * An implementation of a recursively-defined endorelation that has a
 * known exploration state that evolves incrementally and is also invertible.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class CachingInvertibleEndorelation<T> extends CachingEndorelation<T>
implements ICachingEndorelation.Invertible<T> {
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   */
  public CachingInvertibleEndorelation(Collection<T> origins_p, IEndorelation<T> rule_p) {
    super(origins_p, rule_p);
  }
  
  /**
   * Constructor
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   * @param noMultipleInverseOrCycles_p whether elements may not have several
   *          inverse elements in the relation and there cannot be cycles; this
   *          is used for optimization. True means that the endorelation is
   *          injective and its transitive closure is antisymmetric.
   */
  public CachingInvertibleEndorelation(Collection<T> origins_p,
      IEndorelation<T> rule_p, boolean noMultipleInverseOrCycles_p) {
    super(origins_p, rule_p, noMultipleInverseOrCycles_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#getExploredSubset()
   */
  @Override
  public IRangedEndorelation.Invertible<T> getExploredSubset() {
    return (IRangedEndorelation.Invertible<T>)super.getExploredSubset();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(java.lang.Object)
   */
  public Collection<T> getInverse(T element_p) {
    return getExploredSubset().getInverse(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#newExploredSubset()
   */
  @Override
  protected IRangedEndorelation.Editable<T> newExploredSubset() {
    return new EditableInvertibleEndorelation<T>(getEqualityTester());
  }
  
}
