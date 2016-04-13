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


/**
 * A base category for differences that are relative to a given side.
 * @author Olivier Constant
 */
public abstract class AbstractSideRelatedDifferenceCategory extends AbstractDifferenceCategory {
  
  /** Whether the category is relative to 3-way changes on the left-hand side */
  private final boolean _sideIsLeft;
  
  
  /**
   * Constructor
   * @param sideIsLeft_p whether the category is relative to differences on the left-hand side
   */
  public AbstractSideRelatedDifferenceCategory(boolean sideIsLeft_p) {
    _sideIsLeft = sideIsLeft_p;
  }
  
  /**
   * Return whether the category is relative to differences on the left-hand side
   */
  protected boolean isLeftSide() {
    return _sideIsLeft;
  }
  
}
