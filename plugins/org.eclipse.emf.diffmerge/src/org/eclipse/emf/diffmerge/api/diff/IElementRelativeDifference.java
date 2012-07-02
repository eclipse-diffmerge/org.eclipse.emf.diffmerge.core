/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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


/**
 * A model difference which is relative to at most one element per role.
 * @author Olivier Constant
 */
public interface IElementRelativeDifference extends IDifference {
  
  /**
   * Return the match which defines the element, or the matching elements,
   * to which this difference is relative.
   * @return a match which is non-null unless it represents the model root container
   */
  IMatch getElementMatch();
  
  /**
   * Return whether this difference is disconnected from the container/contents
   * relationship.
   */
  boolean isProperToElement();
  
}
