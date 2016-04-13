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
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers 3-way element removals.
 * @author Olivier Constant
 */
public class ElementRemovalCategory extends AbstractSideRelatedDifferenceCategory {
  
  /**
   * Constructor
   * @param sideIsLeft_p whether the category is relative to differences on the left-hand side
   */
  public ElementRemovalCategory(boolean sideIsLeft_p) {
    super(sideIsLeft_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference difference_p, EMFDiffNode node_p) {
    boolean result = false;
    if (difference_p instanceof IElementPresence) {
      IElementPresence presence = (IElementPresence)difference_p;
      Role sideRoleOpposite = node_p.getRoleForSide(!isLeftSide());
      Role presenceRole = presence.getPresenceRole();
      if (node_p.isThreeWay())
        result = presenceRole == sideRoleOpposite && presence.isAlignedWithAncestor();
      else
        result = presenceRole == node_p.getReferenceRole();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#getImage(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Image getImage(EMFDiffNode node_p) {
    Image result;
    if (isLeftSide())
      result = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.OUT_REM_STAT);
    else
      result = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.INC_REM_STAT);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    String result;
    boolean noReference = node_p.getReferenceRole() == null;
    if (noReference)
      if (isLeftSide())
        result = "Elements removed on the left";
      else
        result = "Elements removed on the right";
    else
      result = "Removed elements";
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public boolean isApplicable(EMFDiffNode node_p) {
    return node_p.isThreeWay() ||
        node_p.getReferenceRole() == node_p.getRoleForSide(!isLeftSide());
  }
  
}
