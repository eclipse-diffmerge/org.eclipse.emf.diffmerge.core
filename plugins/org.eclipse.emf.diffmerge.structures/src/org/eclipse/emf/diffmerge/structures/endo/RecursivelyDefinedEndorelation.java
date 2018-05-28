/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;


/**
 * An implementation of recursively-defined endorelations.
 * It is based on a set of "origin" elements (base case) and a rule (recursion step)
 * which is another endorelation that is typically not defined recursively.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class RecursivelyDefinedEndorelation<T>
extends AbstractRecursivelyDefinedEndorelation<T> {
  
  /** The non-null rule for the recursion step */
  private final IEndorelation<T> _rule;
  
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   */
  public RecursivelyDefinedEndorelation(Collection<? extends T> origins_p,
      IEndorelation<T> rule_p) {
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
  public RecursivelyDefinedEndorelation(Collection<? extends T> origins_p,
      IEndorelation<T> rule_p, boolean noMultipleInverseOrCycles_p) {
    super(origins_p, noMultipleInverseOrCycles_p, rule_p.getEqualityTester());
    _rule = rule_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   */
  public Collection<T> get(T element_p) {
    return getRule().get(element_p);
  }
  
  /**
   * Return the rule for the recursion step
   * @return a non-null endorelation
   */
  public IEndorelation<T> getRule() {
    return _rule;
  }
  
  /**
   * Return the rule for the recursion step as a rule with properties
   * if available as such, or null otherwise
   * @return a potentially null endorelation
   */
  protected IEndorelation.WithProperties<T> getRuleWithProperties() {
    IEndorelation.WithProperties<T> result = null;
    IEndorelation<T> rule = getRule();
    if (rule instanceof IEndorelation.WithProperties<?>)
      result = (IEndorelation.WithProperties<T>)rule;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isFunctional()
   */
  @Override
  public IPropertyValue<Boolean> isFunctional() {
    IEndorelation.WithProperties<T> rule = getRuleWithProperties();
    IPropertyValue<Boolean> result = (rule != null)? rule.isFunctional():
      PropertyValue.<Boolean>unknownValue();
    if (!result.is(Boolean.TRUE))
      result = super.isFunctional();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractRecursivelyDefinedEndorelation#isInjective()
   */
  @Override
  public IPropertyValue<Boolean> isInjective() {
    IEndorelation.WithProperties<T> rule = getRuleWithProperties();
    IPropertyValue<Boolean> result = (rule != null)? rule.isInjective():
      PropertyValue.<Boolean>unknownValue();
    if (!result.is(Boolean.TRUE))
      result = super.isInjective();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelation#isIrreflexive()
   */
  @Override
  public IPropertyValue<Boolean> isIrreflexive() {
    IEndorelation.WithProperties<T> rule = getRuleWithProperties();
    IPropertyValue<Boolean> result = (rule != null)? rule.isIrreflexive():
      PropertyValue.<Boolean>unknownValue();
    if (!result.is(Boolean.TRUE))
      result = super.isIrreflexive();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractRecursivelyDefinedEndorelation#isWithoutCycles()
   */
  @Override
  public IPropertyValue<Boolean> isWithoutCycles() {
    IEndorelation.WithProperties<T> rule = getRuleWithProperties();
    IPropertyValue<Boolean> result = (rule != null)? rule.isWithoutCycles():
      PropertyValue.<Boolean>unknownValue();
    if (!result.is(Boolean.TRUE))
      result = super.isWithoutCycles();
    return result;
  }
  
}
