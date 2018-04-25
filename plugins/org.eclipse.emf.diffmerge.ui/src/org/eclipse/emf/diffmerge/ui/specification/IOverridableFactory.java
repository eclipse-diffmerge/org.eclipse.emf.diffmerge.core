/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
