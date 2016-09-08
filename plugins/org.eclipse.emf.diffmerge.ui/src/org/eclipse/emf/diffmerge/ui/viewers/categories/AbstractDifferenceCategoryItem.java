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
