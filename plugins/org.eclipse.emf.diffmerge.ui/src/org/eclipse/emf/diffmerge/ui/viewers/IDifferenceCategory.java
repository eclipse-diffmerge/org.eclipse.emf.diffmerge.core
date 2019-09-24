/*********************************************************************
 * Copyright (c) 2016-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;


/**
 * A category of differences for UI purposes.
 * @author Olivier Constant
 */
public interface IDifferenceCategory extends IDifferenceCategoryItem, Cloneable {
  
  /**
   * @see Object#clone()
   */
  IDifferenceCategory clone() throws CloneNotSupportedException;
  
  /**
   * Set the state of this category to reflect the state of the given category
   * @param peer_p a non-null category
   */
  void copyState(IDifferenceCategory peer_p);
  
  /**
   * Return whether the given difference belongs to this category in the context
   * of the given diff node
   * @param difference_p a non-null difference
   * @param node_p a non-null diff node
   */
  boolean covers(IDifference<?> difference_p, EMFDiffNode node_p);
  
  /**
   * Return an identifier for this category, which must be unique and stable at instance level
   * @return a non-null string
   */
  String getID();
  
  /**
   * Return whether this category is currently active
   */
  boolean isActive();
  
  /**
   * Return whether this category is applicable to the given diff node
   * @param node_p a non-null object
   */
  boolean isApplicable(EMFDiffNode node_p);
  
  /**
   * Return whether this category is in focus mode, that is, differences that do not belong to it
   * are filtered out. In the opposite case, differences that belong to the category are filtered out.
   */
  boolean isInFocusMode();
  
  /**
   * Return whether this category can be modified by the user
   */
  boolean isModifiable();
  
  /**
   * Return whether this category is visible as such in the UI
   */
  boolean isVisible();
  
  /**
   * Return whether the category may cover differences that are pending for the user
   * @see CategoryManager#isPending(IDifference)
   */
  boolean mayCoverPendingDifferences();
  
  /**
   * Set whether this category is currently active
   * @param active_p whether it is active
   */
  void setActive(boolean active_p);
  
  /**
   * Set whether this category is in focus mode
   * @param inFocusMode_p whetehr it is in focus mode
   * @see IDifferenceCategory#isInFocusMode()
   */
  void setInFocusMode(boolean inFocusMode_p);
  
  /**
   * Set whether this category can be modified by the user
   * @param modifiable_p whether it is modifiable
   */
  void setModifiable(boolean modifiable_p);
  
  /**
   * Set whether this category is visible
   * @param visible_p whether it is visible
   */
  void setVisible(boolean visible_p);
  
}
