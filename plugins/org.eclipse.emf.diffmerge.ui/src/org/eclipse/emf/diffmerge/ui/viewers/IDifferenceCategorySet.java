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

import org.eclipse.emf.common.util.EList;

/**
 * A set of difference categories for UI purposes.
 * @author Olivier Constant
 */
public interface IDifferenceCategorySet extends IDifferenceCategoryItem {
  
  /**
   * Return the list of children category items
   * @return a non-null, potentially empty, modifiable list containing no duplicate
   */
  EList<IDifferenceCategoryItem> getChildren();
  
}
