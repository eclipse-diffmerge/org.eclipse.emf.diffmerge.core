/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
import java.util.List;

import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A model scope covering the full EMF containment tree of a modifiable set of root elements.
 * EMF undo/redo is not supported due to the local non-EMF state.
 * Removal of elements which are cross-referenced outside the scope is not supported.
 * @author Olivier Constant
 */
public class RootedModelScope extends AbstractEditableModelScope {
  
  /** The roots of this scope */
  protected final List<EObject> _roots;
  
  
  /**
   * Constructor
   * @param roots_p a non-null list of elements whose containment trees are disjoint,
   *        that is, no element is the ancestor of another one
   */
  public RootedModelScope(List<? extends EObject> roots_p) {
    _roots = new FArrayList<EObject>(roots_p, IEqualityTester.BY_REFERENCE);
  }
  
  /**
   * Constructor
   * @param roots_p a non-null list of elements whose containment trees are disjoint,
   *        that is, no element is the ancestor of another one
   * @param operateOnList_p whether the list must be used directly as the root container,
   *        where true implies that roots_p is modifiable
   */
  public RootedModelScope(List<EObject> roots_p, boolean operateOnList_p) {
    _roots = operateOnList_p? roots_p:
      new FArrayList<EObject>(roots_p, IEqualityTester.BY_REFERENCE);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#add(org.eclipse.emf.ecore.EObject)
   */
  public boolean add(EObject element_p) {
    // Must be called by redefinitions in subclasses
    return _roots.add(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    boolean result = super.add(source_p, reference_p, value_p);
    if (result && reference_p.isContainment())
      _roots.remove(value_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#covers(EObject)
   */
  @Override
  public boolean covers(EObject element_p) {
    return EcoreUtil.isAncestor(_roots, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContainer(EObject)
   */
  @Override
  public EObject getContainer(EObject element_p) {
    return getContents().contains(element_p)? null: super.getContainer(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContainment(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EReference getContainment(EObject element_p) {
    return getContents().contains(element_p)? null: super.getContainment(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContents()
   */
  public List<EObject> getContents() {
    return Collections.unmodifiableList(_roots);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element_p) {
    // Must be called by redefinitions in subclasses
    boolean result = super.remove(element_p);
    if (result)
      _roots.remove(element_p);
    return result;
  }
  
}
