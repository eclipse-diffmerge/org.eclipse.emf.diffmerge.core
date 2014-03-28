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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * A partial implementation of IFeaturedModelScope based on unbounded EMF containment.
 * @author Olivier Constant
 */
public abstract class AbstractModelScope implements IFeaturedModelScope {
  
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#get(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#get(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EAttribute)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.ICoreModelScope#getAllContents()
   */
  public TreeIterator<EObject> getAllContents() {
    // Return an iterator which is derived from getAllContents(EObject)
    return new ModelScopeIterator(this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getAllContents(org.eclipse.emf.ecore.EObject)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getContainment(org.eclipse.emf.ecore.EObject)
   */
  public EReference getContainment(EObject element_p) {
    return element_p.eContainmentFeature();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContents(org.eclipse.emf.ecore.EObject)
   */
  public List<EObject> getContents(EObject element_p) {
    return element_p.eContents();
  }
  
  /**
   * @see IPersistentModelScope#getExtrinsicID(EObject)
   */
  protected Object getExtrinsicID(EObject element_p) {
    // Default implementation only covers XML/XMI Resources
    return ModelImplUtil.getXMLID(element_p);
  }
  
  /**
   * Return an object that characterizes or identifies this scope, if any
   * @return a potentially null object
   */
  public Object getOriginator() {
    return this;
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
