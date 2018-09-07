/*********************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.qualified.EditableQEndorelation;
import org.eclipse.emf.diffmerge.structures.impacts.IImpactRule;


/**
 * An EMF-based implementation of a qualified endorelation.
 * 
 * @author Olivier Constant
 */
public class EImpactExploredSubset extends EditableQEndorelation<Object, IImpactRule> {
  
  /** The potentially null default qualifier */
  private final IImpactRule _defaultQualifier;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   * @param defaultQualifier_p the potentially null default qualifier
   */
  public EImpactExploredSubset(IImpactRule defaultQualifier_p) {
    this(defaultQualifier_p, null);
  }
  
  /**
   * Constructor
   * @param defaultQualifier_p the potentially null default qualifier
   * @param tester_p a potentially null equality tester for comparing elements
   */
  public EImpactExploredSubset(IImpactRule defaultQualifier_p, IEqualityTester tester_p) {
    super(tester_p);
    _defaultQualifier = defaultQualifier_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.AbstractQBinaryRelation#defaultQualifier()
   */
  @Override
  public IImpactRule defaultQualifier() {
    return _defaultQualifier;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.endo.qualified.EditableQEndorelation#newContents(org.eclipse.emf.diffmerge.util.structures.IEqualityTester)
   */
  @Override
  protected IRangedQBinaryRelation.Editable<Object, Object, IImpactRule> newContents(
      IEqualityTester tester_p) {
    return new GraphQBinaryRelation(tester_p);
  }
  
}
