/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.model.integration;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.endo.qualified.IRangedQEndorelation;
import org.eclipse.emf.diffmerge.structures.impacts.CachingImpact;
import org.eclipse.emf.diffmerge.structures.impacts.IImpactRule;


/**
 * An EMF-based implementation of an impact.
 * 
 * @author Olivier Constant
 */
public class EImpact extends CachingImpact {
  
  /**
   * Constructor
   * @param origins_p the non-null set of sources of the impact
   * @param rules_p the non-null set of impact rules
   */
  public EImpact(Collection<?> origins_p, Collection<IImpactRule> rules_p) {
    this(origins_p, rules_p, null);
  }
  
  /**
   * Constructor
   * @param origins_p the non-null set of sources of the impact
   * @param rules_p the non-null set of impact rules
   * @param tester_p a potentially null equality tester
   */
  public EImpact(Collection<?> origins_p, Collection<IImpactRule> rules_p,
      IEqualityTester tester_p) {
    super(origins_p, rules_p, tester_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.endo.qualified.IncQEndorelation#newExploredSubset()
   */
  @Override
  protected IRangedQEndorelation.Editable<Object, IImpactRule> newExploredSubset() {
    return new EImpactExploredSubset(defaultQualifier(), getEqualityTester());
  }
  
}
