/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A partial implementation of IEditableModelScope based on unbounded EMF containment.
 * @author Olivier Constant
 */
public abstract class AbstractEditableModelScope extends AbstractModelScope
implements IEditableModelScope {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#add(EObject, EAttribute, Object)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#add(EObject, EReference, EObject)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#isReadOnly()
   */
  public boolean isReadOnly() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#move(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, int, int)
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#remove(EObject)
   */
  public boolean remove(EObject element_p) {
    // Warning: this implementation ignores cross-references from outside the scope.
    // Override if complete deletion of the element is needed.
    EcoreUtil.remove(element_p);
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#remove(EObject, EAttribute, Object)
   */
  public boolean remove(EObject source_p, EAttribute attribute_p, Object value_p) {
    return removeValue(source_p, attribute_p, value_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#remove(EObject, EReference, EObject)
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
   * @see IPersistentModelScope#setExtrinsicID(EObject, Object)
   */
  protected boolean setExtrinsicID(EObject element_p, Object id_p) {
    // Default implementation only covers XML/XMI Resources
    boolean result = false;
    if (id_p instanceof String)
      result = ModelImplUtil.setXMLID(element_p, (String)id_p);
    return result;
  }
  
}
