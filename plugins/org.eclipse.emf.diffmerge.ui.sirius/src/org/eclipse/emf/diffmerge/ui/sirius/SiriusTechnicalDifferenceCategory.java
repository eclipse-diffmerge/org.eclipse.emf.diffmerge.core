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
package org.eclipse.emf.diffmerge.ui.sirius;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;


/**
 * A difference category that covers technical Sirius differences.
 * @author Olivier Constant
 */
public class SiriusTechnicalDifferenceCategory extends AbstractDifferenceCategory {
  
  /** The ID of this category */
  public static final String ID = "Sirius.Technical"; //$NON-NLS-1$
  
  /** The path to the Sirius file icon in the sirius.ui plug-in */
  protected static final String SIRIUS_ICON_PATH = "icons/obj16/SiriusFile.gif"; //$NON-NLS-1$
  
  /** The path to the Sirius file icon in the sirius.ui plug-in */
  protected static final Collection<EStructuralFeature> SIRIUS_TECHNICAL_FEATURES =
      Arrays.<EStructuralFeature>asList(
          ViewpointPackage.eINSTANCE.getDAnalysis_Version(),
          ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources(),
          ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_RepPath(),
          ViewpointPackage.eINSTANCE.getDRepresentation_Uid());
  
  
  /**
   * Constructor
   */
  public SiriusTechnicalDifferenceCategory() {
    super();
    setActive(true);
    setInFocusMode(false);
    setVisible(true);
    setModifiable(true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference difference_p, EMFDiffNode node_p) {
    boolean result = false;
    if (difference_p instanceof IValuePresence) {
      IValuePresence vp = (IValuePresence)difference_p;
      result = isSiriusTechnicalFeature(vp.getFeature());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    return Messages.SiriusTechnicalDifferenceCategory_Description;
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
    return Messages.SiriusTechnicalDifferenceCategory_Text;
  }
  
  /**
   * Return whether the given Sirius structural feature is technical
   * @param feature_p a non-null structural feature
   */
  protected boolean isSiriusTechnicalFeature(EStructuralFeature feature_p) {
    return SIRIUS_TECHNICAL_FEATURES.contains(feature_p);
  }
  
}
