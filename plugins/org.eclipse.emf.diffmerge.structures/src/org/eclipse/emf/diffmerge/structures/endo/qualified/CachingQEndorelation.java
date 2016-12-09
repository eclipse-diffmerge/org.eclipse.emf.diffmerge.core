/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedGet;
import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedGetQualifiers;
import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedMaps;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation;


/**
 * An implementation of a recursively-defined qualified endorelation that has a
 * known exploration state that evolves incrementally.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class CachingQEndorelation<T, Q> extends CachingEndorelation<T>
implements ICachingQEndorelation<T, Q> {
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   */
  public CachingQEndorelation(Collection<? extends T> origins_p,
      IQEndorelation<T, Q> rule_p) {
    this(origins_p, rule_p, false);
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
  public CachingQEndorelation(Collection<? extends T> origins_p,
      IQEndorelation<T, Q> rule_p, boolean noMultipleInverseOrCycles_p) {
    super(origins_p, rule_p, noMultipleInverseOrCycles_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#defaultQualifier()
   */
  public Q defaultQualifier() {
    return getRule().defaultQualifier();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#get(java.lang.Object)
   */
  @Override
  public Collection<T> get(T element_p) {
    return qualifiedGet(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#get(java.lang.Object, java.lang.Object)
   */
  public Collection<T> get(T element_p, Q qualifier_p) {
    return qualifiedGet(this, element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#getEditableExploredSubset()
   */
  @Override
  @SuppressWarnings("unchecked")
  protected IRangedQEndorelation.Editable<T, Q> getEditableExploredSubset() {
    return (IRangedQEndorelation.Editable<T, Q>)super.getEditableExploredSubset();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object)
   */
  public Collection<Q> getQualifiers(T element_p) {
    return qualifiedGetQualifiers(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object, java.lang.Object)
   */
  public Collection<Q> getQualifiers(T source_p, T target_p) {
    return qualifiedGetQualifiers(this, source_p, target_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.RecursivelyDefinedEndorelation#getRule()
   */
  @Override
  public IQEndorelation<T, Q> getRule() {
    @SuppressWarnings("unchecked") // OK because consistent with constructors
    IQEndorelation<T, Q> result = (IQEndorelation<T, Q>)super.getRule();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getWithDetails(T element_p) {
    Map<Q, Collection<T>> result;
    IRangedQEndorelation.Editable<T, Q> explored =
        getEditableExploredSubset();
    if (_coveredElements.contains(element_p)) {
      result = explored.getWithDetails(element_p);
    } else {
      result = getRule().getWithDetails(element_p);
      for (Map.Entry<Q, Collection<T>> entry : result.entrySet()) {
        explored.addAll(element_p, entry.getValue(), entry.getKey());
      }
      _coveredElements.add(element_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean maps(T source_p, T target_p, Q qualifier_p) {
    return qualifiedMaps(this, source_p, target_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.CachingEndorelation#newExploredSubset()
   */
  @Override
  protected IRangedQEndorelation.Editable<T, Q> newExploredSubset() {
    return new EditableQEndorelation<T, Q>(
        getEqualityTester()) {
      /**
       * @see org.eclipse.emf.diffmerge.structures.binary.qualified.HashQBinaryRelation#defaultQualifier()
       */
      @Override
      public Q defaultQualifier() {
        return CachingQEndorelation.this.defaultQualifier();
      }
    };
  }
  
}
