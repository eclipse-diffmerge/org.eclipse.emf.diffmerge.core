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


/**
 * A difference which is relative to some unmatched presence on at most one element per role.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IElementRelativePresence<E>
extends IElementRelativeDifference<E>, IPresenceDifference<E> {
  
  // Nothing specific
  
}
