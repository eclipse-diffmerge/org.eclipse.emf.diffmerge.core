/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.ecore.EObject;


/**
 * A difference which represents the presence of an unmatched element.
 * 
 * - If the difference is merged as an addition of the element, i.e., the destination
 *   role is getPresenceRole().opposite(), then the element is copied and added to the
 *   destination scope. For performance reasons, references between added elements are
 *   not copied until completeReferences(destinationRole) is called on the mapping
 *   (IMapping) of the comparison. This is automatically done when merge(...) is called
 *   on the comparison with the updateReferences_p parameter set to true.
 * 
 * - If the difference is merged as a deletion of the element, i.e., the destination
 *   role is getPresenceRole(), then the element is removed from its scope. The exact
 *   semantics of the removal depends on the scope.
 * 
 * @author Olivier Constant
 */
public interface IElementPresence extends IElementRelativeDifference,
IPresenceDifference, IMergeableDifference {
  
  /**
   * Return the element whose presence is represented by this difference
   * @return a non-null element
   */
  EObject getElement();
  
  /**
   * Return the match for the owner of the element
   * @return a potentially null match
   */
  IMatch getOwnerMatch();
  
  /**
   * Return whether the element is a root in its scope.
   * Class invariant: isRoot() == (getOwnerMatch() == null)
   */
  boolean isRoot();
  
}
