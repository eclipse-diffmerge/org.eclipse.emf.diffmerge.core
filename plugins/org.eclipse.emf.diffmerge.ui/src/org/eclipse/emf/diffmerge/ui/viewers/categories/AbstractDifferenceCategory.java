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
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;


/**
 * A base implementation of IDifferenceCategory.
 * @author Olivier Constant
 */
public abstract class AbstractDifferenceCategory extends AbstractDifferenceCategoryItem
implements IDifferenceCategory {
  
  /** Whether this category is visible */
  private boolean _visible;
  
  /** Whether this category is currently active */
  private boolean _active;
  
  /** Whether this category is modifiable by the user */
  private boolean _modifiable;
  
  /** Whether this category is in focus mode */
  private boolean _inFocusMode;
  
  
  /**
   * Constructor
   */
  protected AbstractDifferenceCategory() {
    _visible = true;
    _active = false;
    _modifiable = true;
    _inFocusMode = false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getDescription(EMFDiffNode node_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isActive()
   */
  public boolean isActive() {
    return _active;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean isApplicable(EMFDiffNode node_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isInFocusMode()
   */
  public boolean isInFocusMode() {
    return _inFocusMode;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isModifiable()
   */
  public boolean isModifiable() {
    return _modifiable;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isVisible()
   */
  public boolean isVisible() {
    return _visible;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#mayCoverPendingDifferences()
   */
  public boolean mayCoverPendingDifferences() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setActive(boolean)
   */
  public void setActive(boolean active_p) {
    _active = active_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setInFocusMode(boolean)
   */
  public void setInFocusMode(boolean inFocusMode_p) {
    _inFocusMode = inFocusMode_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setModifiable(boolean)
   */
  public void setModifiable(boolean modifiable_p) {
    _modifiable = modifiable_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setVisible(boolean)
   */
  public void setVisible(boolean visible_p) {
    _visible = visible_p;
  }
  
}
