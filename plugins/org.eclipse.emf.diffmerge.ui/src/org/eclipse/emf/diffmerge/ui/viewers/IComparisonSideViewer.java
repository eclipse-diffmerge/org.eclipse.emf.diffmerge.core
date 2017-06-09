/**
 * <copyright>
 * 
 * Copyright (c) 2014-2017 Thales Global Services S.A.S.
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
