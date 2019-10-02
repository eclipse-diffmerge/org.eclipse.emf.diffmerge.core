/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A scope covering the full EMF containment subtree of a given element.
 * The root cannot be removed from the scope and the load/unload life-cycle is not handled.
 * EMF undo/redo is supported because the local state never changes.
 * Complete deletion of elements which are cross-referenced outside the scope is not supported.
 * @author Olivier Constant
 */
public class SubtreeModelScope extends AbstractEditableModelScope
implements IPersistentModelScope.Editable {
  
  /** The root of this scope */
  private final EObject _root;
  
  
  /**
   * Constructor
   * @param root_p the root element
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
    return getRawContents();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getDefaultOriginator()
   */
  @Override
  protected Object getDefaultOriginator() {
    return _root;
  }
  
  /**
   * @see IPersistentModelScope#getExtrinsicID(EObject)
   */
  @Override
  public Object getExtrinsicID(EObject element_p) {
    // Increases visibility
    return super.getExtrinsicID(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#getHoldingResource()
   */
  public Resource getHoldingResource() {
    return getRoot().eResource();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#getRawContents()
   */
  public List<EObject> getRawContents() {
    return Collections.singletonList(getRoot());
  }
  
  /**
   * Return the root of this scope
   * @return a non-null element
   */
  public EObject getRoot() {
    return _root;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#isLoaded()
   */
  public boolean isLoaded() {
    return !_root.eIsProxy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#load()
   */
  public boolean load() throws Exception {
    return isLoaded(); // Load/unload life-cycle not handled
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element_p) {
    boolean result = false;
    if (element_p != getRoot())
      result = super.remove(element_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope.Editable#save()
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
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#setExtrinsicID(org.eclipse.emf.ecore.EObject, java.lang.Object)
   */
  @Override
  public boolean setExtrinsicID(EObject element_p, Object id_p) {
    // Increases visibility
    return super.setExtrinsicID(element_p, id_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope.Editable#setStream(java.io.InputStream)
   */
  public void setStream(InputStream stream_p) {
    // Ignore since scope is always loaded
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#unload()
   */
  public List<Resource> unload() {
    return Collections.emptyList();  // Load/unload life-cycle not handled
  }
  
}
