/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.ui.viewers.DefaultDifferenceCategoryProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.DifferenceCategorySet;


/**
 * A provider of difference categories with a customization for Sirius.
 * @author Olivier Constant
 */
public class SiriusDifferenceCategoryProvider extends DefaultDifferenceCategoryProvider {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.DefaultDifferenceCategoryProvider#provideCategories(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public void provideCategories(EMFDiffNode node_p) {
    super.provideCategories(node_p);
    provideSiriusCategories(node_p);
  }
  
  /**
   * Provide difference categories related to Sirius
   * @param node_p a non-null diff node
   */
  protected void provideSiriusCategories(EMFDiffNode node_p) {
    IDifferenceCategorySet diagramCategorySet = new DifferenceCategorySet(
        Messages.SiriusDifferenceCategoryProvider_SiriusSet_Text,
        Messages.SiriusDifferenceCategoryProvider_SiriusSet_Description);
    diagramCategorySet.getChildren().add(new SiriusLayoutDifferenceCategory());
    diagramCategorySet.getChildren().add(new SiriusNameDifferenceCategory());
    diagramCategorySet.getChildren().add(new SiriusVisibilityDifferenceCategory());
    diagramCategorySet.getChildren().add(new SiriusStylingDifferenceCategory());
    diagramCategorySet.getChildren().add(new SiriusTechnicalDifferenceCategory());
    node_p.getCategoryManager().addCategories(diagramCategorySet);
  }
  
}
