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

import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers 3-way conflicting differences.
 * @author Olivier Constant
 */
public class ConflictCategory extends AbstractDifferenceCategory {
  
  /** The ID of this category */
  public static final String ID = "Technical.Conflicts"; //$NON-NLS-1$
  
  
  /**
   * Constructor
   */
  public ConflictCategory() {
    super();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.generic.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference<?> difference_p, EMFDiffNode node_p) {
    boolean result = difference_p.isConflicting();
    if (!result && difference_p instanceof IReferenceValuePresence<?>) {
      IReferenceValuePresence<?> rvp = (EReferenceValuePresence)difference_p;
      IReferenceValuePresence<?> peer = rvp.getSymmetricalOwnership();
      result = !rvp.isAlignedWithAncestor() && peer != null && !peer.isAlignedWithAncestor();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getID()
   */
  public String getID() {
    return ID;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#getImage(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Image getImage(EMFDiffNode node_p) {
    return node_p.getResourceManager().getStandaloneOverlay(ImageID.CONFLICT_STAT);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    return Messages.ConflictCategory_Text;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public boolean isApplicable(EMFDiffNode node_p) {
    return node_p.isThreeWay();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    return Messages.ConflictCategory_Description;
  }
  
}
