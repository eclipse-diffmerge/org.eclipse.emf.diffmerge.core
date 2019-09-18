/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
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
 * A provider of difference categories.
 * @author Olivier Constant
 */
public interface IDifferenceCategoryProvider {
  
  /**
   * Provide difference categories for the given node through its category manager
   * @see EMFDiffNode#getCategoryManager()
   * @param node_p a non-null diff node
   */
  void provideCategories(EMFDiffNode node_p);
  
}
