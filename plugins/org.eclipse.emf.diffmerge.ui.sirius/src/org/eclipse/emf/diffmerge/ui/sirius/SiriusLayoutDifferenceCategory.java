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
package org.eclipse.emf.diffmerge.ui.sirius;

import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers Sirius layout-related differences.
 * @author Olivier Constant
 */
public class SiriusLayoutDifferenceCategory extends AbstractDifferenceCategory {
  
  /** The ID of this category */
  public static final String ID = "Sirius.Layout"; //$NON-NLS-1$
  
  /** The path to the Sirius file icon in the sirius.ui plug-in */
  protected static final String SIRIUS_ICON_PATH = "icons/obj16/SiriusFile.gif"; //$NON-NLS-1$
  
  
  /**
   * Constructor
   */
  public SiriusLayoutDifferenceCategory() {
    super();
    setActive(false);
    setInFocusMode(false);
    setVisible(true);
    setModifiable(true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.generic.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference<?> difference_p, EMFDiffNode node_p) {
    Object element = getDifferenceElement(difference_p);
    boolean result = element instanceof LayoutConstraint || element instanceof Bendpoints ||
        element instanceof IdentityAnchor;
    if (!result) {
      result = getDifferenceFeature(difference_p) == DiagramPackage.eINSTANCE.getAbstractDNode_ArrangeConstraints();
    }
    if (!result && difference_p instanceof IReferenceValuePresence<?>) {
      IReferenceValuePresence<?> casted = (IReferenceValuePresence<?>) difference_p;
      result = casted.isOrder() && casted.getFeature() == DiagramPackage.eINSTANCE.getDNodeList_OwnedElements();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    return Messages.SiriusLayoutDifferenceCategory_Description;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getID()
   */
  public String getID() {
    return ID;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategoryItem#getImage(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Image getImage(EMFDiffNode node_p) {
    return SiriusEditPlugin.getPlugin().getBundledImage(SIRIUS_ICON_PATH);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    return Messages.SiriusLayoutDifferenceCategory_Name;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#copy()
   */
  @Override
  public IDifferenceCategory copy() {
    SiriusLayoutDifferenceCategory copied = new SiriusLayoutDifferenceCategory();
    copied.copyState(this);
    return copied;
  }
  
}
