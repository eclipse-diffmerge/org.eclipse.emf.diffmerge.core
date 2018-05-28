/**
 * <copyright>
 * 
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
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
