/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers differences originating from a specific side in a
 * three-way comparison.
 * @author Olivier Constant
 */
public class ThreeWayOriginCategory extends AbstractSideRelatedDifferenceCategory {
  
  /** The ID of this category in the left-hand-side case */
  public static final String ID_LEFT = "Technical.ThreeWayOrigin.Left"; //$NON-NLS-1$
  
  /** The ID of this category in the right-hand-side case */
  public static final String ID_RIGHT = "Technical.ThreeWayOrigin.Right"; //$NON-NLS-1$
  
  
  /**
   * Constructor
   * @param sideIsLeft_p whether the category is relative to differences on the left-hand side
   */
  public ThreeWayOriginCategory(boolean sideIsLeft_p) {
    super(sideIsLeft_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.generic.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference<?> difference_p, EMFDiffNode node_p) {
    boolean result = false;
    if (difference_p instanceof IPresenceDifference<?>) {
      IPresenceDifference<?> presence = (IPresenceDifference<?>)difference_p;
      Role sideRole = node_p.getRoleForSide(isLeftSide());
      Role presenceRole = presence.getPresenceRole();
      result =
          presenceRole == sideRole && !presence.isAlignedWithAncestor() ||
          presenceRole != sideRole && presence.isAlignedWithAncestor();
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
      result = Messages.ThreeWayOriginCategory_DescriptionLeft;
    else
      result = Messages.ThreeWayOriginCategory_DescriptionRight;
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
    ImageID imageID = isLeftSide()? ImageID.OUT_STAT: ImageID.INC_STAT;
    return node_p.getResourceManager().getStandaloneOverlay(imageID);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    String result;
    if (isLeftSide())
      result = Messages.ThreeWayOriginCategory_TextLeft;
    else
      result = Messages.ThreeWayOriginCategory_TextRight;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public boolean isApplicable(EMFDiffNode node_p) {
    return node_p.isThreeWay();
  }
  
}
