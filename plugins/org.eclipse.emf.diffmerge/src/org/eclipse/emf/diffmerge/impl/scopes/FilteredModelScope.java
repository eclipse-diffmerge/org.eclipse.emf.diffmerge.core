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
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.util.ModelsUtil;
import org.eclipse.emf.diffmerge.util.ModelsUtil.IElementFilter;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.FeatureMapUtil;



/**
 * A model scope which covers a subset of EMF containment trees based on a filter.
 * Root addition leaves the underlying model untouched. Root removal modifies the model;
 * use removeFromScope(EObject, boolean) for side-effect-free removal.
 * EMF undo/redo is not supported due to the local non-EMF state.
 * Removal of elements which are cross-referenced outside the scope is not supported.
 * @author Olivier Constant
 */
public class FilteredModelScope extends RootedModelScope {
  
  /** The elements which actually belong to this scope */
  private final Set<EObject> _inScope;
  
  
  /**
   * Constructor
   * @param elements_p a non-null list of the elements initially in the scope
   */
  public FilteredModelScope(List<? extends EObject> elements_p) {
    super(ModelsUtil.getRoots(elements_p));
    _inScope = new FHashSet<EObject>(elements_p, null);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject element_p) {
    return add(element_p, false);
  }
  
  /**
   * Add the given element to the scope
   * @param element_p a non-null element which is out of the scope
   * @param includeChildren_p whether all children must be added too
   * @return whether the operation succeeded
   */
  public boolean add(EObject element_p, boolean includeChildren_p) {
    boolean result = true;
    // If parent is not in scope, then add as root
    if ((element_p.eContainer() == null || !_inScope.contains(element_p.eContainer()))
        && !_roots.contains(element_p))
      result = super.add(element_p);
    if (result) {
      _inScope.add(element_p);
      // Remove direct children from roots
      _roots.removeAll(element_p.eContents());
      if (includeChildren_p)
        addAllChildrenToScope(element_p, null);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    // Remember previously owned value if relevant
    EObject previouslyContainedValue = null;
    if (!FeatureMapUtil.isMany(source_p, reference_p) && reference_p.isContainment()) {
      List<EObject> values = get(source_p, reference_p);
      if (!values.isEmpty())
        previouslyContainedValue = values.get(0);
    }
    boolean result = super.add(source_p, reference_p, value_p);
    if (result) {
      // Remove previously owned value from scope
      if (previouslyContainedValue != null)
        _inScope.remove(previouslyContainedValue);
      // Add new value in scope
      _inScope.add(value_p);
    }
    return result;
  }
  
  /**
   * Redefine the whole content of the scope solely based on the current roots,
   * all their children and the given filter
   * @param filter_p an optional filter, where null stands for no filtering
   */
  public void build(IElementFilter filter_p) {
    _inScope.clear();
    for (EObject root : super.getContents()) {
      if (filter_p == null || filter_p.accepts(root)) {
        _inScope.add(root);
        addAllChildrenToScope(root, filter_p);
      }
    }
  }
  
  /**
   * Add all children of the given in-scope element that are accepted by the
   * given filter. The rejection of an element leads to the rejection of all
   * its children.
   * @param element_p a non-null element which already belongs to the scope
   * @param filter_p an optional filter, where null stands for no filtering
   */
  protected void addAllChildrenToScope(EObject element_p, IElementFilter filter_p) {
    TreeIterator<EObject> technicalIterator = element_p.eAllContents();
    while (technicalIterator.hasNext()) {
      EObject child = technicalIterator.next();
      if (filter_p == null || filter_p.accepts(child))
        _inScope.add(child);
      else
        technicalIterator.prune();
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope#covers(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean covers(EObject element_p) {
    return _inScope.contains(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#get(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, boolean)
   */
  @Override
  protected List<EObject> get(EObject source_p, EReference reference_p,
      boolean resolveProxies_p) {
    List<EObject> originalValues = super.get(source_p, reference_p, resolveProxies_p);
    List<EObject> result = new FArrayList<EObject>(originalValues, null);
    result.retainAll(_inScope);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#getContents()
   */
  @Override
  public List<EObject> getContents() {
    List<EObject> result = new FArrayList<EObject>();
    result.addAll(super.getContents());
    result.retainAll(_inScope);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContents(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public List<EObject> getContents(EObject element_p) {
    List<EObject> result = new FArrayList<EObject>(
        super.getContents(element_p), IEqualityTester.BY_REFERENCE);
    result.retainAll(_inScope);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getAllContentsAsSet()
   * This implementation is computationally efficient.
   */
  @Override
  public Set<EObject> getAllContentsAsSet() {
    return Collections.unmodifiableSet(_inScope);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element_p) {
    boolean result = super.remove(element_p);
    if (result)
      removeFromScope(element_p, true);
    return result;
  }
  
  /**
   * Remove the given element and optionally its children from the scope while leaving
   * the underlying model unchanged
   * @param element_p a non-null element
   * @param includeChildren_p whether all children must be removed too
   */
  public void removeFromScope(EObject element_p, boolean includeChildren_p) {
    _inScope.remove(element_p);
    if (includeChildren_p) {
      TreeIterator<EObject> technicalIterator = element_p.eAllContents();
      while (technicalIterator.hasNext()) {
        EObject child = technicalIterator.next();
        _inScope.remove(child);
      }
    } else {
      for (EObject child : getContents(element_p)) {
        add(child, false);
      }
    }
  }
  
}
