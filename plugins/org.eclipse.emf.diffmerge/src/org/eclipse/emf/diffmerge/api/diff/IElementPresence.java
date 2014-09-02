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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.ecore.EObject;


/**
 * A difference which represents the presence of an unmatched element.
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
   * Class invariant: isRoot() <=> getOwnerMatch() == null
   */
  boolean isRoot();
  
}
