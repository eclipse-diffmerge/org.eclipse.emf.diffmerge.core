/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The roles played by model scopes in a comparison.
 * @author Olivier Constant
 */
public enum Role {
  
  /**
   * The TARGET role in a comparison, which is symmetrical to and arbitrarily distinguished
   * from the REFERENCE role.
   */
  TARGET,
  
  /**
   * The REFERENCE role in a comparison, which is symmetrical to and arbitrarily distinguished
   * from the TARGET role.
   */
  REFERENCE,
  
  /**
   * The optional ANCESTOR role in a comparison, which usually corresponds to a common ancestor
   * in a version tree. This role makes it possible to distinguish the role from which a given
   * difference originates.
   */
  ANCESTOR;
  
  
  /**
   * Return the role which is opposite to this. TARGET and REFERENCE are opposite,
   * ANCESTOR is considered to be the opposite of itself.
   * @return a non-null role
   */
  public Role opposite() {
    switch (this) {
      case TARGET: return REFERENCE;
      case REFERENCE: return TARGET;
      default: return ANCESTOR;
    }
  }
  
  /**
   * Return a role which is not among the given ones
   * @param roles_p a non-null, potentially empty set of roles
   * @return a potentially null role
   */
  public static Role otherThan(Role... roles_p) {
    List<Role> rolesLeft = new ArrayList<Role>(Arrays.asList(Role.values()));
    for (Role role : roles_p)
      rolesLeft.remove(role);
    return rolesLeft.isEmpty()? null: rolesLeft.get(0);
  }
  
}
