/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;


/**
 * A model difference with features related to merging.
 * @author Olivier Constant
 */
public interface IMergeableDifference extends IDifference {
  
  /**
   * Return the differences which have been marked as direcly, explicitly
   * dependent upon this difference for the given role.
   * The transitive closure must be antisymmetric.
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a non-null, possibly empty, non-modifiable set
   */
  Collection<IMergeableDifference> getDirectRequiresDependencies(Role role_p);
  
  /**
   * Return the differences which have been marked as directly, implicitly
   * dependent upon this difference for the given role.
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a non-null, possibly empty, non-modifiable set
   */
  Collection<IMergeableDifference> getDirectImpliesDependencies(Role role_p);
  
  /**
   * Return all the differences which are required to be explicitly merged
   * together with this difference for the given role.
   * It is the transitive closure of getDirectExplicitDependencies(Role).
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a non-null, potentially empty, unmodifiable set which
   *         does not contain the receiver
   */
  Collection<IMergeableDifference> getRequiresDependencies(Role role_p);
  
  /**
   * Return all the differences which would be implicitly merged (due
   * to the underlying model technology) together with this difference
   * for the given role.
   * It is the transitive closure of getDirectImplicitDependencies(Role).
   * It defines a partition of the whole set of differences.
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a non-null, potentially empty, unmodifiable set which
   *         does not contain the receiver
   */
  Collection<IMergeableDifference> getImpliesDependencies(Role role_p);
  
  /**
   * Merge this difference to the given role.
   * Precondition: canMergeTo(destination_p)
   * Postcondition: isMergedTo(destination_p)
   * @param destination_p a non-null role
   * @return a non-null, unmodifiable set of the differences which have been merged
   *         implicitly or explicitly, which contains at least this difference
   */
  Collection<IDifference> mergeTo(Role destination_p);
  
  
  /**
   * A mergeable difference with editing features.
   * All concrete classes implementing IMergeableDifference must also implement this interface.
   */
  public static interface Editable extends IMergeableDifference, IDifference.Editable {
    /**
     * Core behavior for mergeIn(Role, IMergePolicy) ignoring dependencies
     * @param destination_p a non-null destination role
     */
    void doMergeIn(Role destination_p);
    
    /**
     * Mark this difference as merged in the given role
     * @param destination_p a non-null role
     */
    void markAsMergedIn(Role destination_p);
    
    /**
     * Mark the given difference as directly, implicitly dependent upon
     * this difference if merged in the given role.
     * @param difference_p a non-null difference
     * @param role_p a non-null role which is TARGET or REFERENCE
     */
    void markImplies(IMergeableDifference difference_p, Role role_p);
    
    /**
     * Mark the given difference as directly, explicitly dependent upon
     * this difference if merged in the given role.
     * The transitive closure of getDirectExplicitDependencies(Role) must
     * be antisymmetric.
     * @param difference_p a non-null difference
     * @param role_p a non-null role which is TARGET or REFERENCE
     */
    void markRequires(IMergeableDifference difference_p, Role role_p);
  }
  
}
