/**
 * <copyright>
 * 
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.impacts;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.endo.CompositeEndorelation;
import org.eclipse.emf.diffmerge.structures.endo.ICompositeEndorelation;


/**
 * A utility class related to impacts.
 * 
 * @author Olivier Constant
 */
public final class Impacts {
  
  /**
   * Create and result the composite rule consisting of the union of the provided rules
   * based on the given equality tester
   * @param rules_p a non-null set of rules
   * @param tester_p a potentially null equality tester
   * @return a non-null rule
   */
  protected static ICompositeEndorelation<Object, IImpactRule> createCompositeRule(
      Collection<IImpactRule> rules_p, IEqualityTester tester_p) {
    CompositeEndorelation<Object, IImpactRule> result =
        new CompositeEndorelation<Object, IImpactRule>(Object.class, tester_p);
    for (IImpactRule rule : rules_p) {
      result.addSubRelation(rule);
    }
    return result;
  }
  
  /**
   * Create and return an impact based on the given origins and the given rules
   * @param origins_p a non-null, potentially empty set
   * @param rules_p a non-null, potentially empty set
   * @return a non-null impact
   */
  public static IImpact create(
      Collection<?> origins_p, Collection<IImpactRule> rules_p) {
    return new Impact(origins_p, rules_p);
  }
  
  /**
   * Create and return an invertible impact based on the given origins and the given rules
   * @param origins_p a non-null, potentially empty set
   * @param rules_p a non-null, potentially empty set
   * @return a non-null impact
   */
  public static IImpact.Invertible createInvertible(
      Collection<?> origins_p, Collection<IImpactRule> rules_p) {
    return new InvertibleImpact(origins_p, rules_p);
  }
  
  /**
   * Create and return a aching impact based on the given origins and the given rules
   * @param origins_p a non-null, potentially empty set
   * @param rules_p a non-null, potentially empty set
   * @return a non-null impact
   */
  public static IImpact.Caching createCaching(
      Collection<?> origins_p, Collection<IImpactRule> rules_p) {
    return new CachingImpact(origins_p, rules_p);
  }
  
}