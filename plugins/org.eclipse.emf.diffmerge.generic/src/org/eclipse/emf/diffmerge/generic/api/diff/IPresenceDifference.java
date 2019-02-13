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
package org.eclipse.emf.diffmerge.generic.api.diff;

import org.eclipse.emf.diffmerge.generic.api.Role;


/**
 * A model difference which is relative to the presence of elements or values
 * in a given role.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IPresenceDifference<E> extends IDifference<E> {
  
  /**
   * Return the role in which presence is effective.
   * @return Role.TARGET or Role.REFERENCE
   */
  Role getPresenceRole();
  
}
