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
package org.eclipse.emf.diffmerge.generic.api.scopes;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;


/**
 * A high-level definition of a model as a forest of model elements.
 * Model scopes are intended to be used as "logical views" over EMF instance models
 * with arbitrary boundaries. 
 * @author Olivier Constant
 */
public interface IModelScope {
  
  /**
   * Return whether the given element belongs to this scope.
   * Operation is allowed to be computationally expensive.
   * @param element_p a non-null element
   */
  boolean covers(EObject element_p);
  
  /**
   * Return an iterator over all the contents of this scope.
   * Result must be consistent with getContents() and getAllContents(EObject).
   * Iterating via the result should not be expensive.
   * @return a non-null iterator
   */
  TreeIterator<EObject> getAllContents();
  
  /**
   * Return an iterator over all the contents of the given non-null element
   * which are within this scope.
   * Result must be consistent with getContents(EObject).
   * Iterating via the result should not be expensive.
   * @param root_p a non-null element within this scope
   * @return a non-null iterator
   */
  TreeIterator<EObject> getAllContents(EObject root_p);
  
  /**
   * Return a set containing all the elements in this scope.
   * Operation is allowed to be computationally expensive.
   * @return a non-null, potentially empty, unmodifiable set
   */
  Set<EObject> getAllContentsAsSet();
  
  /**
   * Return the element whose direct scope contents include the given element, if any.
   * The returned element must belong to the scope.
   * Result must be consistent with getContents(EObject).
   * Operation should not be expensive.
   * Postcondition:
   *   (covers(element_p) && result == null) == getContents().contains(element_p)
   * @param element_p a non-null element which belongs to this scope
   * @return a potentially null element
   */
  EObject getContainer(EObject element_p);
  
  /**
   * Return the root elements of this scope.
   * Operation should not be expensive.
   * @return an unmodifiable non-null list which may become obsolete
   */
  List<EObject> getContents();
  
  /**
   * Return the direct contents of the given element within this scope.
   * Operation should not be expensive.
   * @return an unmodifiable non-null list which may become obsolete
   */
  List<EObject> getContents(EObject element_p);
  
  /**
   * Return an object that characterizes or identifies this scope
   * @return a non-null object
   */
  Object getOriginator();
  
  /**
   * Return the number of elements in this scope.
   * Operation is allowed to be computationally expensive.
   */
  int size();
  
  
  /**
   * A model scope which has the ability to be modified.
   * @author Olivier Constant
   */
  interface Editable extends IModelScope {
    /**
     * Add the given element to the scope.
     * Whether its contents belong to the scope after execution is intentionally undefined.
     * If the element already belongs to the scope, then the behavior of this operation is
     * undefined.
     * Postcondition: !result || covers(element_p)
     * @param element_p a non-null element
     * @return whether the operation succeeded
     */
    boolean add(EObject element_p);
    
   /**
     * Return whether this scope is read-only, which means that modification operations have
     * no effect and always return false
     */
    boolean isReadOnly();
    
    /**
     * Remove the given element from this scope.
     * Whether its contents still belong to the scope after execution is intentionally undefined.
     * Precondition: covers(element_p)
     * Postcondition: !result || !covers(element_p)
     * @param element_p a non-null element within the scope
     * @return whether the operation succeeded
     */
    boolean remove(EObject element_p);
  }
  
}
