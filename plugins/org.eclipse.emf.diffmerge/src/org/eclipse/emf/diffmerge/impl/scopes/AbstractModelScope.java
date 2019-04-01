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
package org.eclipse.emf.diffmerge.impl.scopes;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.generic.impl.scopes.AbstractDataScope;
import org.eclipse.emf.diffmerge.impl.policies.ModelScopePolicy;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * A partial implementation of IFeaturedModelScope based on unbounded EMF containment.
 * @author Olivier Constant
 */
public abstract class AbstractModelScope extends AbstractDataScope<EObject>
implements IFeaturedModelScope {
  
  /**
   * Default constructor
   */
  protected AbstractModelScope() {
    super();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.scopes.AbstractDataScope#defineScopePolicy()
   */
  @Override
  protected IScopePolicy<EObject> defineScopePolicy() {
    return ModelScopePolicy.getInstance();
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
      e.printStackTrace();
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
        // Attribute is set
        Object value = source_p.eGet(attribute_p, resolveProxies());
        if (FeatureMapUtil.isMany(source_p, attribute_p)) {
          // Set, many (may contain null values)
          result = new LinkedList<Object>();
          for (Object inValue : (List<Object>)value) {
            if (inValue != null)
              result.add(inValue);
          }
          result = Collections.unmodifiableList(result);
        } else if (value != null) {
          // Set, not many, not null
          result = Collections.singletonList(value);
        } else {
          // Set, not many, null
          result = Collections.emptyList();
        }
      } else {
        // Attribute is not set
        result = Collections.emptyList();
      }
    } catch (RuntimeException e) {
      result = Collections.emptyList();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getAllContents()
   */
  public TreeIterator<EObject> getAllContents() {
    // Return an iterator which is derived from getAllContents(EObject)
    return new ModelScopeIterator(this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getAllContents(org.eclipse.emf.ecore.EObject)
   */
  public TreeIterator<EObject> getAllContents(EObject root_p) {
    // Return an iterator which is derived from getContents(EObject)
    return new AbstractTreeIterator<EObject>(root_p, false) {
      /** The serial version ID */
      private static final long serialVersionUID = 1L;
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
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IRawTreeDataScope#getContents(java.lang.Object)
   */
  public List<EObject> getContents(EObject element_p) {
    return element_p.eContents();
  }
  
  /**
   * @see IPersistentModelScope#getExtrinsicID(EObject)
   */
  protected Object getExtrinsicID(EObject element_p) {
    return tGetID(element_p, false);
  }
  
  /**
   * Return whether proxies must be resolved when this scope is navigated
   */
  protected boolean resolveProxies() {
    return false;
  }
  
}
