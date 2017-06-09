/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * A base implementation of IComparisonDefinitionFactory.
 * @author Olivier Constant
 */
public abstract class AbstractScopeDefinitionFactory implements IModelScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IOverridableFactory#getOverridenClasses()
   */
  public Collection<? extends Class<?>> getOverridenClasses() {
    return Collections.emptySet();
  }
  
  /**
   * Return whether the given file extension is registered as a model file in the platform
   * @param extension_p a potentially null string
   */
  protected boolean isModelFileExtension(String extension_p) {
    Map<String, Object> extensionToFactoryMap =
      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    boolean result = extensionToFactoryMap.containsKey(extension_p);
    return result;
  }
  
}
