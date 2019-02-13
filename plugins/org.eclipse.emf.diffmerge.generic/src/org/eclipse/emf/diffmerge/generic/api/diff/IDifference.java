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

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.Role;


/**
 * A difference between models or model scopes.
 * @see IElementPresence
 * @see IAttributeValuePresence
 * @see IReferenceValuePresence
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public interface IDifference<E> {
  
  /**
   * Return whether this difference can be merged to the given role,
   * independently of other differences.
   * @param destination_p a non-null role
   */
  boolean canMergeTo(Role destination_p);
  
  /**
   * Return the comparison to which this difference belongs
   * @return a non-null comparison
   */
  IComparison<E> getComparison();
  
  /**
   * Return the role into which this difference has been merged, if any.
   * Class invariant: isMerged() == getMergeDestination() != null
   * @return a potentially null role
   */
  Role getMergeDestination();
  
  /**
   * Return whether this difference also exists in the ancestor in a three-way
   * comparison. In a two-way comparison, always return true.
   */
  boolean isAlignedWithAncestor();
  
  /**
   * Return whether this difference is conflicting with another one.
   * In a two-way comparison, always return false.
   * Class invariant: !isConflicting() || !isAlignedWithAncestor()
   */
  boolean isConflicting();
  
  /**
   * Return whether this difference has been explicitly ignored.
   */
  boolean isIgnored();
  
  /**
   * Return whether this difference has been merged.
   */
  boolean isMerged();
  
  
  /**
   * A difference with editing features.
   * All concrete classes implementing IDifference must also implement this interface.
   */
  interface Editable<E> extends IDifference<E> {
    /**
     * Specify that this difference is not present in the common ancestor
     * in a three-way comparison
     */
    void markAsDifferentFromAncestor();
    
    /**
     * Specify that this difference is a conflict with another one in a
     * three-way comparison
     */
    void markAsConflicting();
    
    /**
     * Set whether this difference must be ignored
     * @param ignored_p whether it must be ignored
     */
    void setIgnored(boolean ignored_p);
  }
  
}
