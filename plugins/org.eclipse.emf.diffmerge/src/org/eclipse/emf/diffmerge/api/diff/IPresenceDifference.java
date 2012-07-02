/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api.diff;

import org.eclipse.emf.diffmerge.api.Role;



/**
 * A model difference which is relative to the presence of elements or values
 * in a given role.
 * @author Olivier Constant
 */
public interface IPresenceDifference extends IDifference {
  
  /**
   * Return the role in which presence is effective.
   * @return Role.TARGET or Role.REFERENCE
   */
  Role getPresenceRole();
  
}
