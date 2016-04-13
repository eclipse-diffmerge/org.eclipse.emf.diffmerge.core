/**
 * <copyright>
 * 
 * Copyright (c) 2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.swt.graphics.Image;


/**
 * A category of differences for UI purposes.
 * @author Olivier Constant
 */
public interface IDifferenceCategory extends Cloneable {
  
  /**
   * Return whether the given difference belongs to this category in the context
   * of the given diff node
   * @param difference_p a non-null difference
   * @param node_p a non-null diff node
   */
  boolean covers(IDifference difference_p, EMFDiffNode node_p);
  
  /**
   * Return a user-friendly description for this category in the context of the given
   * diff node
   * @return a potentially null string
   */
  String getDescription(EMFDiffNode node_p);
  
  /**
   * Return an optional image for this category in the context of the given diff node.
   * The image must not be owned by the category.
   * @param node_p a non-null diff node
   * @return a potentially null image
   */
  Image getImage(EMFDiffNode node_p);
  
  /**
   * Return a user-friendly name for this category in the context of the given diff node
   * @param node_p a non-null diff node
   * @return a non-null string
   */
  String getText(EMFDiffNode node_p);
  
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
