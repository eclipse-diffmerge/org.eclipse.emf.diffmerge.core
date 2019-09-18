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
package org.eclipse.emf.diffmerge.api.diff;


/**
 * A difference which is relative to some unmatched presence on at most one element per role.
 * @author Olivier Constant
 */
public interface IElementRelativePresence
extends IElementRelativeDifference, IPresenceDifference {
  
  // Nothing specific
  
}
