/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.policies;

import java.util.Comparator;

import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A typical match policy.
 * It is based on unique IDs (primarily intrinsic, otherwise extrinsic) of data elements.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class DefaultMatchPolicy<E> implements IMatchPolicy<E> {
  
  /**
   * Default constructor
   */
  public DefaultMatchPolicy() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy#getMatchID(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public Object getMatchID(E element_p, ITreeDataScope<E> scope_p) {
    // Intrinsic ID
    Object result = scope_p.getID(element_p, true);
    if (result == null) {
      // Fall-back: extrinsic ID
      result = scope_p.getID(element_p, false);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy#getMatchIDComparator()
   */
  public Comparator<?> getMatchIDComparator() {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMatchPolicy#keepMatchIDs()
   */
  public boolean keepMatchIDs() {
    return false;
  }
  
}
