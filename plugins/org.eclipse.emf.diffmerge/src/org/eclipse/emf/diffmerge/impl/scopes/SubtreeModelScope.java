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

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A single-resource model scope covering the full EMF containment subtree of a given element.
 * The root cannot be removed from the scope.
 * EMF undo/redo is supported because the local state never changes.
 * Complete deletion of elements which are cross-referenced outside the scope is not supported.
 * @author Olivier Constant
 */
public class SubtreeModelScope extends AbstractModelScope implements IPersistentModelScope {
  
  /** The root of this scope */
  private final EObject _root;
  
  
  /**
   * Constructor
   * @param roots_p a non-null list of elements whose containment trees are disjoint,
   *        that is, no element is the ancestor of another one
   */
  public SubtreeModelScope(EObject root_p) {
    _root = root_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#add(org.eclipse.emf.ecore.EObject)
   */
  public boolean add(EObject element_p) {
    if (element_p != getRoot() && getHoldingResource() != null)
      getHoldingResource().getContents().add(element_p);
    // The element does not belong to the scope after execution
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContainer(EObject)
   */
  @Override
  public EObject getContainer(EObject element_p) {
    return getRoot() == element_p? null: super.getContainer(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContainment(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EReference getContainment(EObject element_p) {
    return getRoot() == element_p? null: super.getContainment(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContents()
   */
  public List<EObject> getContents() {
    return Collections.singletonList(getRoot());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#getHoldingResource()
   */
  public Resource getHoldingResource() {
    return getRoot().eResource();
  }
  
  /**
   * Return the root of this scope
   * @return a non-null element
   */
  public EObject getRoot() {
    return _root;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#load()
   */
  public boolean load() throws Exception {
    return true; // Resource is already loaded since we already have an EObject
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element_p) {
    boolean result = false;
    if (element_p != getRoot())
      result = super.remove(element_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#save()
   */
  public boolean save() throws IOException {
    boolean result = false;
    Resource resource = getHoldingResource();
    if (resource != null) {
      Map<Object, Object> options = new HashMap<Object, Object>();
      options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
          Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
      resource.save(options);
      result = true;
    }
    return result;
  }
  
}
