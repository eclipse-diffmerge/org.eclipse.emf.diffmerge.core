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
package org.eclipse.emf.diffmerge.api.scopes;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.generic.api.scopes.IRawDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.IRawTreeDataScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A high-level definition of a model as a forest of model elements.
 * Model scopes are intended to be used as "logical views" over EMF instance models
 * with arbitrary boundaries. 
 * @author Olivier Constant
 */
public interface IModelScope extends IRawTreeDataScope<EObject> {
  
  /**
   * Return an iterator over all the contents of this scope.
   * Result must be consistent with getContents() and getAllContents(EObject).
   * Iterating via the result should not be expensive.
   * @return a non-null iterator
   */
  TreeIterator<EObject> getAllContents();
  
  /**
   * Return a set containing all the elements in this scope.
   * Operation is allowed to be computationally expensive.
   * @return a non-null, potentially empty, unmodifiable set
   */
  Set<EObject> getAllContentsAsSet();
  
  /**
   * Return the root elements of this scope.
   * Operation should not be expensive.
   * @return an unmodifiable non-null list which may become obsolete
   */
  List<EObject> getContents();
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IRawTreeDataScope#getRoots()
   * Here to avoid API breakage.
   */
  default List<EObject> getRoots() {
    return getContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IRawTreeDataScope#iterator()
   * Here to avoid API breakage.
   */
  default TreeIterator<EObject> iterator() {
    return getAllContents();
  }
  
  
  /**
   * A model scope which has the ability to be modified.
   * @author Olivier Constant
   */
  interface Editable extends IModelScope, IRawDataScope.Editable<EObject> {
    
    // Nothing added
    
  }
  
}
