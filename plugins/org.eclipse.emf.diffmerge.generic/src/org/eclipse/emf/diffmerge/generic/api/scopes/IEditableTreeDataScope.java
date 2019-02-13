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
package org.eclipse.emf.diffmerge.generic.api.scopes;


/**
 * An IDataScope whose elements are organized as a forest and that can be directly
 * modified/
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IEditableTreeDataScope<E> extends ITreeDataScope<E>,
IEditableDataScope<E> {
  
  // Nothing added
  
}
