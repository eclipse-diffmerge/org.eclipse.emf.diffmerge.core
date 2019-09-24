/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.specification;

import java.util.Collection;


/**
 * A factory that can be registered and used for overriding the registration of others.
 * @author Olivier Constant
 */
public interface IOverridableFactory {
  
  /**
   * Return a label that describes the elements provided by this factory
   * @return a non-null string
   */
  String getLabel();
  
  /**
   * Return the classes that this factory overrides when applicable.
   * This "override" relation must be such that its transitive
   * closure is antisymmetric (no "loops"). Only instances of the returned classes are
   * concerned, not instances of sub-classes.
   * @return a non-null, potentially empty, non-modifiable collection
   */
  Collection<? extends Class<?>> getOverridenClasses();
  
}
