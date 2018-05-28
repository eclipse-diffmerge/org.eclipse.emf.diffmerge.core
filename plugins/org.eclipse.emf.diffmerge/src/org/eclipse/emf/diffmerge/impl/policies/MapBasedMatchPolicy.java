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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A match policy with predefined pairs of matching elements.
 * @author Olivier Constant
 */
public class MapBasedMatchPolicy extends DefaultMatchPolicy {
  
  /** The non-null mapping of corresponding elements */
  private final Map<?, ?> _mapping;
  
  /**
   * Constructor
   * @param mapping_p a non-null mapping of matching elements (modifying it will alter this policy)
   */
  public MapBasedMatchPolicy(Map<?, ?> mapping_p) {
    _mapping = mapping_p;
  }
  
  /**
   * Return the mapping of matching elements. Modifying it will alter this policy.
   * @return a non-null, potentially empty map
   */
  public Map<?, ?> getMap() {
    return _mapping;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy#getMatchID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  public Object getMatchID(EObject element_p, IModelScope scope_p) {
    Object mapped = _mapping.get(element_p);
    EObject idProvider = mapped instanceof EObject? (EObject)mapped: element_p;
    return super.getMatchID(idProvider, scope_p);
  }
  
}
