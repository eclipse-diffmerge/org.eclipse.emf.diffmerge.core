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
