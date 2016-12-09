/**
 * <copyright>
 * 
 * Copyright (c) 2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelation;


/**
 * An base implementation of IImpactRule.
 * 
 * @see IImpactRule
 * @author Olivier Constant
 */
public abstract class ImpactRule extends AbstractEndorelation<Object>
implements IImpactRule {
  
  /** The potentially null name of the rule */
  private String _name;
  
  /** The potentially null description of the rule */
  private String _description;
  
  
  /**
   * Constructor
   * @param name_p a potentially null string
   */
  public ImpactRule(String name_p) {
    this(name_p, null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester
   */
  public ImpactRule(String name_p, IEqualityTester tester_p) {
    super(tester_p);
    _name = name_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.impacts.IImpactRule#getDescription()
   */
  public String getDescription() {
    return _description;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.impacts.IImpactRule#getName()
   */
  public String getName() {
    return _name;
  }
  
  /**
   * Set the description of this impact rule
   * @param description_p a potentially null string
   */
  public void setDescription(String description_p) {
    _description = description_p;
  }
  
  /**
   * Set the name of this impact rule
   * @param name_p a potentially null string
   */
  public void setName(String name_p) {
    _name = name_p;
  }
  
  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String result = getName();
    if (result == null)
      result = super.toString();
    return result;
  }
  
}
