/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;


/**
 * A difference between models or model scopes.
 * @see IElementPresence
 * @see IAttributeValuePresence
 * @see IReferenceValuePresence
 * @author Olivier Constant
 */
public interface IDifference {
  
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
  IComparison getComparison();
  
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
   * Return whether this difference has been merged.
   */
  boolean isMerged();
  
  
  /**
   * A difference with editing features.
   * All concrete classes implementing IDifference must also implement this interface.
   */
  interface Editable extends IDifference {
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
  }
  
}
