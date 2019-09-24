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
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.swt.graphics.Image;


/**
 * A base implementation of IDifferenceCategoryItem.
 * @author Olivier Constant
 */
public abstract class AbstractDifferenceCategoryItem implements IDifferenceCategoryItem {
  
  /** The optional parent of this item */
  private IDifferenceCategorySet _parent;
  
  
  /**
   * Constructor
   */
  protected AbstractDifferenceCategoryItem() {
    _parent = null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getImage(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public Image getImage(EMFDiffNode node_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getParent()
   */
  public IDifferenceCategorySet getParent() {
    return _parent;
  }
  
  /**
   * Set the parent of this item.
   * This operation must not lead to cycles in the parent-children structure.
   * @param parent_p a potentially null parent
   */
  protected void setParent(IDifferenceCategorySet parent_p) {
    _parent = parent_p;
  }
  
}
