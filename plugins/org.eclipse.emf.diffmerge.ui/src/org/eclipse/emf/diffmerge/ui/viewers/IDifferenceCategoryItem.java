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

import org.eclipse.swt.graphics.Image;


/**
 * An item related to difference categories for UI purposes.
 * Difference category items are structurally organized as a forest.
 * @author Olivier Constant
 */
public interface IDifferenceCategoryItem {
  
  /**
   * Return a user-friendly description for this category item in the context of the given
   * diff node
   * @return a potentially null string
   */
  String getDescription(EMFDiffNode node_p);
  
  /**
   * Return an optional image for this category item in the context of the given diff node.
   * The image must not be owned by the category.
   * @param node_p a non-null diff node
   * @return a potentially null image
   */
  Image getImage(EMFDiffNode node_p);
  
  /**
   * Return the parent of this item, if any
   * @return a potentially null category set
   */
  IDifferenceCategorySet getParent();
  
  /**
   * Return a user-friendly name for this category item in the context of the given diff node
   * @param node_p a non-null diff node
   * @return a non-null string
   */
  String getText(EMFDiffNode node_p);
  
}
