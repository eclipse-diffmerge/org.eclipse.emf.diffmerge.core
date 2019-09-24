/*********************************************************************
 * Copyright (c) 2014-2019 Thales Global Services S.A.S.
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


/**
 * A viewer which is characterized by the side of a comparison that it represents.
 * @author Olivier Constant
 */
public interface IComparisonSideViewer {
  
  /**
   * Return whether the information represented by the viewer belongs to
   * the left-hand side
   */
  boolean isLeftSide();
  
}
