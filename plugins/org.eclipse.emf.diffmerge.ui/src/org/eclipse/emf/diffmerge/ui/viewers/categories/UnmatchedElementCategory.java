/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers unmatched elements that are present on a given side.
 * @author Olivier Constant
 */
public class UnmatchedElementCategory extends AbstractSideRelatedDifferenceCategory {
  
  /** The ID of this category in the left-hand-side case */
  public static final String ID_LEFT = "Technical.Unmatched.Left"; //$NON-NLS-1$
  
  /** The ID of this category in the right-hand-side case */
  public static final String ID_RIGHT = "Technical.Unmatched.Right"; //$NON-NLS-1$
  
  
  /**
   * Constructor
   * @param sideIsLeft_p whether the category is relative to differences on the left-hand side
   */
  public UnmatchedElementCategory(boolean sideIsLeft_p) {
    super(sideIsLeft_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference difference_p, EMFDiffNode node_p) {
    boolean result = false;
    if (difference_p instanceof IElementPresence) {
      IElementPresence presence = (IElementPresence)difference_p;
      Role sideRole = node_p.getRoleForSide(isLeftSide());
      result = presence.getPresenceRole() == sideRole;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    String result;
    if (isLeftSide())
      result = Messages.UnmatchedElementCategory_DescriptionLeft;
    else
      result = Messages.UnmatchedElementCategory_DescriptionRight;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getID()
   */
  public String getID() {
    return isLeftSide()? ID_LEFT: ID_RIGHT;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#getImage(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Image getImage(EMFDiffNode node_p) {
    ImageID imageID;
    Role referenceRole = node_p.getReferenceRole();
    Role leftRole = node_p.getRoleForSide(true);
    if (isLeftSide()) {
      if (referenceRole == leftRole)
        imageID = ImageID.INC_REM_STAT;
      else
        imageID = ImageID.OUT_ADD_STAT;
    } else {
      if (referenceRole == leftRole.opposite())
        imageID = ImageID.OUT_REM_STAT;
      else
        imageID = ImageID.INC_ADD_STAT;
    }
    return node_p.getResourceManager().getStandaloneOverlay(imageID);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    String result;
    if (isLeftSide())
      result = Messages.UnmatchedElementCategory_TextLeft;
    else
      result = Messages.UnmatchedElementCategory_TextRight;
    return result;
  }
  
}
