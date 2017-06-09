/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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
