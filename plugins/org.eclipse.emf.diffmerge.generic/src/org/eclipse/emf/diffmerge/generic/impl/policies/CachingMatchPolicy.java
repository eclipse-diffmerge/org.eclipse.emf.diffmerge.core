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
package org.eclipse.emf.diffmerge.generic.impl.policies;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A match policy that supports caching of match IDs.
 * Caching is disabled by default. Matching is by unique IDs by default.
 *
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class CachingMatchPolicy<E> extends DefaultMatchPolicy<E> {
  
  /** An object that represents a null match ID, to distinguish from non-computed or
   * absent match IDs */
  protected static final Object NULL_MATCH_ID = new Object();
  
  /** The cache for match IDs */
  protected final Map<E, WeakReference<Object>> _matchCache;
  
  
  /**
   * Constructor
   */
  public CachingMatchPolicy() {
    super();
    _matchCache = new WeakHashMap<E, WeakReference<Object>>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMatchPolicy#getMatchID(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public Object getMatchID(E element_p,
      ITreeDataScope<E> scope_p) {
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
  protected Object getMatchIDThroughCache(E element_p,
      ITreeDataScope<E> scope_p) {
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
  protected Object getUncachedMatchID(E element_p,
      ITreeDataScope<E> scope_p) {
    // Override for custom match criteria
    return super.getMatchID(element_p, scope_p);
  }
  
  /**
   * Return whether the cache must be used
   */
  protected boolean useCache() {
    // Override to activate cache
    return false;
  }
  
}
