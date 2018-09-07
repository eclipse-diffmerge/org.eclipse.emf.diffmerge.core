/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
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

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.ecore.EObject;


/**
 * A match policy that supports caching of match IDs.
 * @author Olivier Constant
 */
public abstract class CachingMatchPolicy extends DefaultMatchPolicy {
  
  /** An object that represents a null match ID, to distinguish from non-computed or
   * absent match IDs */
  protected static final Object NULL_MATCH_ID = new Object();
  
  /** The cache for match IDs */
  protected final Map<EObject, WeakReference<Object>> _matchCache;
  
  
  /**
   * Constructor
   */
  public CachingMatchPolicy() {
    super();
    _matchCache = new WeakHashMap<EObject, WeakReference<Object>>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy#getMatchID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  public Object getMatchID(EObject element_p, IModelScope scope_p) {
    Object result = null;
    if (!useCache()) {
      result = getUncachedMatchID(element_p, scope_p);
    } else {
      result = getMatchIDThroughCache(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * Return the match ID of the given element using the cache
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null object
   */
  protected Object getMatchIDThroughCache(EObject element_p, IModelScope scope_p) {
    Object result;
    WeakReference<Object> cachedRef = _matchCache.get(element_p);
    Object cachedValue = (cachedRef == null)? null: cachedRef.get();
    if (cachedValue == null) {
      // Match ID is not in cache
      result = getUncachedMatchID(element_p, scope_p);
      Object toCache = (result == null)? NULL_MATCH_ID: result;
      _matchCache.put(element_p, new WeakReference<Object>(toCache));
    } else {
      // Match ID is in cache
      result = (cachedValue == NULL_MATCH_ID)? null: cachedValue;
    }
    return result;
  }
  
  /**
   * Return the match ID of the given element
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null object
   */
  protected abstract Object getUncachedMatchID(EObject element_p, IModelScope scope_p);
  
  /**
   * Return whether the cache must be used
   */
  protected boolean useCache() {
    return false;
  }
  
}
