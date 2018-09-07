/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.impacts;

import static org.eclipse.emf.diffmerge.structures.impacts.Impacts.createCompositeRule;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.endo.qualified.RecursivelyDefinedQEndorelation;


/**
 * An implementation of IImpact.
 * @see IImpact
 * 
 * @author Olivier Constant
 */
public class Impact extends RecursivelyDefinedQEndorelation<Object, IImpactRule>
implements IImpact {
  
  /**
   * Constructor
   * @param origins_p the non-null set of sources of the impact
   * @param rules_p the non-null set of impact rules
   */
  public Impact(Collection<?> origins_p, Collection<IImpactRule> rules_p) {
    this(origins_p, rules_p, null);
  }
  
  /**
   * Constructor
   * @param origins_p the non-null set of sources of the impact
   * @param rules_p the non-null set of impact rules
   * @param tester_p a potentially null equality tester
   */
  public Impact(Collection<?> origins_p, Collection<IImpactRule> rules_p,
      IEqualityTester tester_p) {
    super(origins_p, createCompositeRule(rules_p, tester_p));
  }
  
}
