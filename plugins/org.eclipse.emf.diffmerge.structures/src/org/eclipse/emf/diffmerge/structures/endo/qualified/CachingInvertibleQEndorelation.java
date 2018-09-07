/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import java.util.Collection;
import java.util.Map;


/**
 * An implementation of a recursively-defined qualified endorelation that has a
 * known exploration state that evolves incrementally and is also invertible.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class CachingInvertibleQEndorelation<T, Q> extends CachingQEndorelation<T, Q>
implements ICachingQEndorelation.Invertible<T, Q> {
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   */
  public CachingInvertibleQEndorelation(Collection<? extends T> origins_p,
      IQEndorelation<T, Q> rule_p) {
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
  public CachingInvertibleQEndorelation(Collection<? extends T> origins_p,
      IQEndorelation<T, Q> rule_p, boolean noMultipleInverseOrCycles_p) {
    super(origins_p, rule_p, noMultipleInverseOrCycles_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#getExploredSubset()
   */
  @Override
  public IRangedQEndorelation.Invertible<T, Q> getExploredSubset() {
    @SuppressWarnings("unchecked") // OK because consistent with newExploredSubset() 
    IRangedQEndorelation.Invertible<T, Q> result =
        (IRangedQEndorelation.Invertible<T, Q>)super.getExploredSubset();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Invertible#getInverse(java.lang.Object)
   */
  public Collection<T> getInverse(T element_p) {
    return getExploredSubset().getInverse(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverse(java.lang.Object, java.lang.Object)
   */
  public Collection<T> getInverse(T element_p, Q qualifier_p) {
    return getExploredSubset().getInverse(element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseQualifiers(java.lang.Object)
   */
  public Collection<Q> getInverseQualifiers(T element_p) {
    return getExploredSubset().getInverseQualifiers(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation.Invertible#getInverseWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getInverseWithDetails(T element_p) {
    return getExploredSubset().getInverseWithDetails(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.qualified.CachingQEndorelation#newExploredSubset()
   */
  @Override
  protected IRangedQEndorelation.InvertibleEditable<T, Q> newExploredSubset() {
    return new EditableInvertibleQEndorelation<T, Q>(getEqualityTester()) {
      /**
       * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#defaultQualifier()
       */
      @Override
      public Q defaultQualifier() {
        return CachingInvertibleQEndorelation.this.defaultQualifier();
      }
    };
  }
  
}
