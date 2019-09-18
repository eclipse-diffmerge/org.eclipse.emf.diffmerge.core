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
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.viewers.categories.ConflictCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.DifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.IgnoredDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.MergedDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.MoveCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.OrderDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.PropertyChangeCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.ThreeWayOriginCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.UnmatchedElementCategory;


/**
 * A provider of default difference categories.
 * @author Olivier Constant
 */
public class DefaultDifferenceCategoryProvider implements IDifferenceCategoryProvider {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryProvider#provideCategories(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public void provideCategories(EMFDiffNode node_p) {
    provideBasicCategories(node_p);
    provideBasic3WayCategories(node_p);
    provideMergeProcessCategories(node_p);
  }
  
  /**
   * Provide difference categories related to 3-way comparisons
   * @param node_p a non-null diff node
   */
  protected void provideBasic3WayCategories(EMFDiffNode node_p) {
    IDifferenceCategorySet threeWayCategorySet = new DifferenceCategorySet(
        Messages.AbstractComparisonViewer_CatSetText3Way,
        Messages.AbstractComparisonViewer_CatSetDescription3Way);
    threeWayCategorySet.getChildren().add(new ThreeWayOriginCategory(true));
    threeWayCategorySet.getChildren().add(new ThreeWayOriginCategory(false));
    threeWayCategorySet.getChildren().add(new ConflictCategory());
    node_p.getCategoryManager().addCategories(threeWayCategorySet);
  }
  
  /**
   * Provide basic difference categories
   * @param node_p a non-null diff node
   */
  protected void provideBasicCategories(EMFDiffNode node_p) {
    IDifferenceCategorySet basicCategorySet = new DifferenceCategorySet(
        Messages.AbstractComparisonViewer_CatSetTextBasic,
        Messages.AbstractComparisonViewer_CatSetDescriptionBasic);
    basicCategorySet.getChildren().add(new PropertyChangeCategory());
    basicCategorySet.getChildren().add(new MoveCategory());
    basicCategorySet.getChildren().add(new OrderDifferenceCategory());
    basicCategorySet.getChildren().add(new UnmatchedElementCategory(true));
    basicCategorySet.getChildren().add(new UnmatchedElementCategory(false));
    node_p.getCategoryManager().addCategories(basicCategorySet);
  }
  
  /**
   * Provide difference categories related to the merge process
   * @param node_p a non-null diff node
   */
  protected void provideMergeProcessCategories(EMFDiffNode node_p) {
    IDifferenceCategorySet mergeCategorySet = new DifferenceCategorySet(
        Messages.AbstractComparisonViewer_CatSetTextMerge,
        Messages.AbstractComparisonViewer_CatSetDescriptionMerge);
    // Merge process, non-pending (already handled by the user)
    mergeCategorySet.getChildren().add(new MergedDifferenceCategory());
    mergeCategorySet.getChildren().add(new IgnoredDifferenceCategory());
    node_p.getCategoryManager().addCategories(mergeCategorySet);
  }
  
}
