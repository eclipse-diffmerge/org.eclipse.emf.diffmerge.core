/**
 * <copyright>
 * 
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
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
 * A viewer which represents difference-related data.
 * @author Olivier Constant
 */
public interface IDifferenceRelatedViewer {
  
  /**
   * Return whether data unrelated to differences is shown
   */
  boolean isDifferenceAgnostic();
  
  /**
   * Set whether data unrelated to differences must be shown
   * @param agnostic_p whether all data must be shown
   */
  void setDifferenceAgnostic(boolean agnostic_p);
  
}
