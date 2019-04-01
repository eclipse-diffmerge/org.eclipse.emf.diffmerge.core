/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.policies;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A default match policy based on unique IDs (intrinsic/extrinsic/URI) of model elements.
 * @author Olivier Constant
 */
public class DefaultMatchPolicy extends
org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMatchPolicy<EObject> {
  
  /**
   * Default constructor
   */
  public DefaultMatchPolicy() {
    super();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMatchPolicy#getMatchID(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public Object getMatchID(EObject element_p, ITreeDataScope<EObject> scope_p) {
    Object result = scope_p.tGetID(element_p, true);
    if (result == null) {
      result = scope_p.tGetID(element_p, false);
    }
    if (result == null) {
      result = getURIBasedMatchID(element_p);
    }
    return result;
  }
  
  /**
   * Return a match ID for the given element based on its URI, if any
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getURIBasedMatchID(EObject element_p) {
    String result = null;
    URI uri = EcoreUtil.getURI(element_p);
    if (uri != null) {
      if (uri.isPlatformResource() && uri.fragment() != null)
        result = uri.fragment(); // Ignore name of containing file
      else
        result = uri.toString();
    }
    return result;
  }
  
}
