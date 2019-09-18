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
