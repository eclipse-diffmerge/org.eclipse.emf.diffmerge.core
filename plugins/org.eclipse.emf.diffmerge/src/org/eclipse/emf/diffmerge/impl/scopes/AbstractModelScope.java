/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.impl.scopes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;



/**
 * A partial implementation of IFeaturedModelScope based on unbounded EMF containment.
 * @author Olivier Constant
 */
public abstract class AbstractModelScope implements IFeaturedModelScope {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#add(EObject, EAttribute, Object)
   */
  public boolean add(EObject source_p, EAttribute attribute_p, Object value_p) {
    boolean result;
    if (FeatureMapUtil.isMany(source_p, attribute_p)) {
      @SuppressWarnings("unchecked")
      List<Object> values = (List<Object>)source_p.eGet(attribute_p, resolveProxies());
      result = values.add(value_p);
    } else {
      source_p.eSet(attribute_p, value_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#add(EObject, EReference, EObject)
   */
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    boolean result;
    if (FeatureMapUtil.isMany(source_p, reference_p)) {
      @SuppressWarnings("unchecked")
      List<EObject> values = (List<EObject>)source_p.eGet(reference_p, resolveProxies());
      result = values.add(value_p); // Guarantees uniqueness
    } else {
      source_p.eSet(reference_p, value_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#covers(EObject)
   */
  public boolean covers(EObject element_p) {
    Iterator<EObject> it = getAllContents();
    while (it.hasNext()) {
      EObject current = it.next();
      if (current == element_p)
        return true;
    }
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#get(EObject, EReference)
   */
  public List<EObject> get(EObject source_p, EReference reference_p) {
    return get(source_p, reference_p, resolveProxies());
  }
  
  /**
   * Return the values which are held by the given element via the given
   * reference, if any. The values may not belong to the scope.
   * If the given element does not belong to this scope, the behavior of this
   * method is undefined.
   * @param source_p a non-null element
   * @param reference_p a non-null reference
   * @param resolveProxies_p whether proxies must be resolved
   * @return an unmodifiable non-null list of the corresponding elements
   *         within this scope, not containing null
   */
  @SuppressWarnings("unchecked")
  protected List<EObject> get(EObject source_p, EReference reference_p,
      boolean resolveProxies_p) {
    List<EObject> result = Collections.emptyList();
    try {
      if (source_p.eIsSet(reference_p)) {
        Object value = source_p.eGet(reference_p, resolveProxies_p);
        if (FeatureMapUtil.isMany(source_p, reference_p)) {
          List<EObject> values = (List<EObject>)value;
          if (!resolveProxies_p && values instanceof InternalEList)
            values = ((InternalEList<EObject>)values).basicList();
          result = Collections.unmodifiableList(values);
        } else if (value != null) {
          result = Collections.singletonList((EObject)value);
        }
      }
    } catch (RuntimeException e) {
      // Proceed
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#get(EObject, EAttribute)
   */
  @SuppressWarnings("unchecked")
  public List<Object> get(EObject source_p, EAttribute attribute_p) {
    List<Object> result;
    try {
      if (source_p.eIsSet(attribute_p)) {
        Object value = source_p.eGet(attribute_p, resolveProxies());
        if (FeatureMapUtil.isMany(source_p, attribute_p))
          result = Collections.unmodifiableList((List<Object>)value);
        else if (value != null)
          result = Collections.singletonList(value);
        else
          result = Collections.emptyList();
      } else {
        result = Collections.emptyList();
      }
    } catch (RuntimeException e) {
      result = Collections.emptyList();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getAllContents()
   */
  public TreeIterator<EObject> getAllContents() {
    // Return an iterator which is derived from getAllContents(EObject)
    return new MultiRootTreeIterator(this, getContents().iterator());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getAllContents(EObject)
   */
  @SuppressWarnings("serial")
  public TreeIterator<EObject> getAllContents(EObject root_p) {
    // Return an iterator which is derived from getContents(EObject)
    return new AbstractTreeIterator<EObject>(root_p, false) {
      /**
       * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(java.lang.Object)
       */
      @Override
      public Iterator<EObject> getChildren(Object object_p) {
        return getContents((EObject)object_p).iterator();
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getAllContentsAsSet()
   */
  public Set<EObject> getAllContentsAsSet() {
    Set<EObject> result = new FHashSet<EObject>();
    Iterator<EObject> it = getAllContents();
    while (it.hasNext())
      result.add(it.next());
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContainer(EObject)
   */
  public EObject getContainer(EObject element_p) {
    return element_p.eContainer();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getContainment(EObject)
   */
  public EReference getContainment(EObject element_p) {
    return element_p.eContainmentFeature();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getContents(EObject)
   */
  public List<EObject> getContents(EObject element_p) {
    return element_p.eContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#move(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, int, int)
   */
  public Object move(EObject source_p, EStructuralFeature feature_p, int newPosition_p,
      int oldPosition_p) {
    Object result = null;
    if (FeatureMapUtil.isMany(source_p, feature_p) && source_p.eIsSet(feature_p) &&
        newPosition_p >= 0) {
      @SuppressWarnings("unchecked")
      EList<Object> values = (EList<Object>)source_p.eGet(feature_p, false);
      int size = values.size();
      int oldPosition = oldPosition_p >= 0? oldPosition_p: size-1;
      int newPosition = oldPosition >= newPosition_p? newPosition_p:
        newPosition_p-1;
      if (newPosition < size && oldPosition < size) {
        try {
          result = values.move(newPosition, oldPosition);
        } catch (RuntimeException e) {
          // Move is not supported on this setting: return null
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#remove(EObject)
   */
  public boolean remove(EObject element_p) {
    // Warning: this implementation ignores cross-references from outside the scope.
    // Override if complete deletion of the element is needed.
    EcoreUtil.remove(element_p);
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#remove(EObject, EAttribute, Object)
   */
  public boolean remove(EObject source_p, EAttribute attribute_p, Object value_p) {
    return removeValue(source_p, attribute_p, value_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#remove(EObject, EReference, EObject)
   */
  public boolean remove(EObject source_p, EReference reference_p, EObject value_p) {
    return removeValue(source_p, reference_p, value_p);
  }
  
  /**
   * Remove the given value on the given feature from the given element.
   * @param source_p a non-null element
   * @param feature_p a non-null feature
   * @param value_p a non-null value
   * @return whether the operation succeeded
   */
  protected boolean removeValue(EObject source_p, EStructuralFeature feature_p, Object value_p) {
    boolean result = false;
    // Differs from EcoreUtil.remove in the non-many case
    if (FeatureMapUtil.isMany(source_p, feature_p)) {
      result = ((List<?>)source_p.eGet(feature_p)).remove(value_p);
    } else if (source_p.eGet(feature_p) == value_p) {
      source_p.eUnset(feature_p);
      result = true;
    }
    return result;
  }
  
  /**
   * Return whether proxies must be resolved when this scope is navigated
   */
  protected boolean resolveProxies() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#size()
   */
  public int size() {
    int result = 0;
    Iterator<EObject> it = getAllContents();
    while (it.hasNext())
      result++;
    return result;
  }
  
}
