/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.helpers;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;


/**
 * An match operation for EMF models.
 * @author Olivier Constant
 */
public class MatchOperation
extends org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation<EObject> {
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison whose mapping is to be built
   * @param policy_p a non-null match policy
   * @param duplicateIDs_p an optional map that associates each role with an empty,
   *          modifiable set of duplicate match IDs, to be filled by this operation
   */
  public MatchOperation(IComparison.Editable<EObject> comparison_p,
      IMatchPolicy<EObject> policy_p,
      Map<Role, Set<Object>> duplicateIDs_p) {
    super(comparison_p, policy_p, duplicateIDs_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation#scopeCovered(org.eclipse.emf.diffmerge.generic.api.Role)
   */
  @Override
  protected void scopeCovered(Role role_p) {
    ((EMapping)getComparison().getMapping()).crossReference(role_p);
  }
  
}
