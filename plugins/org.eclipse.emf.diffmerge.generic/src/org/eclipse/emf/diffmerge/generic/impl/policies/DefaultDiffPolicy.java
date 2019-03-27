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

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A typical implementation of IDiffPolicy.
 * It covers everything except orders and out-of-scope values.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class DefaultDiffPolicy<E> implements IDiffPolicy<E> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerEqual(java.lang.Object, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean considerEqual(Object value1_p, Object value2_p,
      Object attribute_p, ITreeDataScope<E> scope_p) {
    return value1_p.equals(value2_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerEqualOutOfScope(java.lang.Object, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean considerEqualOutOfScope(E outOfScopeValue_p,
      E candidateValue_p, Object reference_p, ITreeDataScope<E> scope_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerOrderedAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean considerOrderedAttribute(Object attribute_p, ITreeDataScope<E> scope_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerOrderedReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean considerOrderedReference(Object reference_p, ITreeDataScope<E> scope_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean coverAttribute(Object attribute_p, ITreeDataScope<E> scope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.generic.api.IMatch)
   */
  public boolean coverMatch(IMatch<E> match_p) {
    // Ignore elements solely present in the ancestor
    return match_p.coversRole(Role.TARGET) || match_p.coversRole(Role.REFERENCE);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverOutOfScopeValue(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean coverOutOfScopeValue(E value_p, Object reference_p, ITreeDataScope<E> scope_p) {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean coverReference(Object reference_p, ITreeDataScope<E> scope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverValue(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  public boolean coverValue(Object value_p, Object attribute_p, ITreeDataScope<E> scope_p) {
    return true;
  }
  
}
