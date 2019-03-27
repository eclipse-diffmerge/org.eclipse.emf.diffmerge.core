/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.impl.helpers;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.ecore.EObject;


/**
 * An match operation for POJO data scopes.
 * @author Olivier Constant
 */
public class MatchOperation
extends org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation<Object> {
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison whose mapping is to be built
   * @param policy_p a non-null match policy
   * @param duplicateIDs_p an optional map that associates each role with an empty,
   *          modifiable set of duplicate match IDs, to be filled by this operation
   */
  public MatchOperation(EComparison comparison_p, IMatchPolicy<Object> policy_p,
      Map<Role, Set<Object>> duplicateIDs_p) {
    super(comparison_p, policy_p, duplicateIDs_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation#getComparison()
   */
  @Override
  public EComparison getComparison() {
    return (EComparison)super.getComparison();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation#scopeCovered(org.eclipse.emf.diffmerge.generic.api.Role)
   */
  @Override
  protected void scopeCovered(Role role_p) {
    getComparison().getMapping().crossReference(role_p);
  }
  
}
