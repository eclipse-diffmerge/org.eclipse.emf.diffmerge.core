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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
import org.eclipse.emf.diffmerge.util.structures.HashBinaryRelation;
import org.eclipse.emf.diffmerge.util.structures.IModifiableBinaryRelation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;



/**
 * A model scope which covers the contents of a given resource plus the contents
 * of other resources which are being logically included or referenced according
 * to custom rules, recursively.
 * This kind of scope is typically useful for loading models which are fragmented
 * among multiple resources via containment references and cross-references.
 * The scope is built incrementally: the additional resources are discovered dynamically
 * the first time the scope is being explored using getAllContents().
 * A fix point is guaranteed to be found and the exploration of the scope is guaranteed
 * to be complete every time.
 * EMF undo/redo is supported because the local state does not change after full exploration.
 * @author Olivier Constant
 */
public class FragmentedModelScope extends AbstractModelScope implements IFragmentedModelScope {
  
  /** The non-null, non-empty ordered set of resources defining the scope */
  protected final List<Resource> _resources;
  
  /** The non-null, non-empty ordered subset of the resources which are roots */
  protected final List<Resource> _rootResources;
  
  /** The resource inclusion relationship */
  protected final IModifiableBinaryRelation<Resource, Resource> _includedResources;
  
  /** The resource referencing relationship */
  protected final IModifiableBinaryRelation<Resource, Resource> _referencedResources;
  
  /** Whether the scope has been fully explored at least once */
  protected boolean _hasBeenFullyExplored;
  
  
  /**
   * Constructor
   * @param resource_p a non-null resource
   */
  public FragmentedModelScope(Resource resource_p) {
    _resources = new ArrayList<Resource>();
    addNewResource(resource_p);
    _rootResources = new ArrayList<Resource>();
    _rootResources.add(resource_p);
    _includedResources = new HashBinaryRelation<Resource, Resource>();
    _referencedResources = new HashBinaryRelation<Resource, Resource>();
    _hasBeenFullyExplored = false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope#add(EObject)
   */
  public boolean add(EObject element_p) {
    boolean result = false;
    Resource defaultResource = getResourceForNewRoot(element_p);
    if (defaultResource != null) {
      defaultResource.getContents().add(element_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#add(EObject, EReference, EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    Resource oldResource = value_p.eResource();
    boolean wasRoot = oldResource != null && oldResource.getContents().contains(value_p);
    String formerId = ModelImplUtil.getXmlId(value_p);
    boolean result = super.add(source_p, reference_p, value_p);
    if (wasRoot && reference_p.isContainment())
      oldResource.getContents().remove(value_p); // Not handled by EMF!
    if (formerId != null)
      // In case resource has changed, thus changing XML ID
      ModelImplUtil.setXmlId(value_p, formerId);
    return result;
  }
  
  /**
   * Add the given resource to the set of covered resources
   * @param resource_p a non-null resource which is not contained in getResources()
   * Precondition: !getResources().contains(resource_p)
   * Postcondition: getResources().contains(resource_p)
   */
  protected void addNewResource(Resource resource_p) {
    _resources.add(resource_p);
  }
  
  /**
   * Return whether the given collection contains proxies relative to the
   * given holding element whose target is already loaded
   * @param collection_p a non-null collection of elements
   * @param source_p a non-null element potentially holding proxies
   */
  protected boolean containsUnnecessaryProxies(Collection<EObject> collection_p,
      EObject source_p) {
    for (EObject current : collection_p) {
      if (current.eIsProxy() && current != ModelImplUtil.resolveIfLoaded(current, source_p))
        return true;
    }
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#get(EObject, EReference)
   */
  @Override
  public List<EObject> get(EObject source_p, EReference reference_p) {
    // Current result, may require resolution of in-scope proxies
    List<EObject> result = super.get(source_p, reference_p);
    boolean requiresResolution = containsUnnecessaryProxies(result, source_p);
    if (requiresResolution) // Recompute result if needed
      result = get(source_p, reference_p, true);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getAllContents()
   */
  @Override
  public TreeIterator<EObject> getAllContents() {
    return new ExpandingMultiResourceTreeIterator();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IModelScope#getContents()
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public List<EObject> getContents() {
    List<EObject> result = new FArrayList<EObject>();
    for (Resource resource : _rootResources)
      result.addAll(resource.getContents());
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the cross-references, for the given element, which are in the scope
   * @param element_p a non-null element belonging to the scope
   * @return a non-null, potentially empty collection of non-containment, non-container references
   */
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    // Override if needed
    return Collections.emptyList();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPhysicalModelScope#getHoldingResource()
   */
  public Resource getHoldingResource() {
    return _rootResources.isEmpty()? null: _rootResources.get(0);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getIncludedResources(org.eclipse.emf.ecore.resource.Resource)
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public List<Resource> getIncludedResources(Resource resource_p) {
    return _includedResources.get(resource_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getReferencedResources(org.eclipse.emf.ecore.resource.Resource)
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public List<Resource> getReferencedResources(Resource resource_p) {
    return _referencedResources.get(resource_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getRootResources()
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public List<Resource> getRootResources() {
    return Collections.unmodifiableList(_rootResources);
  }
  
  /**
   * Return a list of resources that must be integrated to the scope, given a
   * contextual element. The resource of the element needs not be included.
   * @param element_p a non-null element belonging to the scope
   * @return a non-null, potentially empty list
   */
  protected List<Resource> getRelevantReferencedResources(EObject element_p) {
    List<Resource> result = new FOrderedSet<Resource>();
    Collection<EReference> refsInScope = getCrossReferencesInScope(element_p);
    for (EReference ref : refsInScope) {
      if (!ref.isContainment() && !ref.isContainer() && element_p.eIsSet(ref)) {
        List<EObject> values = get(element_p, ref, true);
        for (EObject value : values) {
          Resource valueResource = value.eResource();
          if (valueResource != null)
            result.add(valueResource);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the Resource to use for adding the given root element
   * @param newRoot_p a non-null element to be integrated to the scope as a root
   * @return a Resource, or null if none was found
   */
  protected Resource getResourceForNewRoot(EObject newRoot_p) {
    // Return the first suitable resource
    for (Resource resource : _resources) {
      if (isSuitableFor(resource, newRoot_p))
        return resource;
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getResources()
   * Result is guaranteed to be accurate only if hasBeenExplored().
   * @return a non-null, non-empty list of resources
   */
  public List<Resource> getResources() {
    return Collections.unmodifiableList(_resources);
  }
  
  /**
   * Return whether this scope has been fully explored, that is, at least one complete iteration
   * based on getAllContents has been performed
   */
  public boolean hasBeenExplored() {
    return _hasBeenFullyExplored;
  }
  
  /**
   * Return whether the given resource is suitable for storing the given element as a root
   * @param resource_p a non-null resource
   * @param root_p a non-null element
   */
  protected boolean isSuitableFor(Resource resource_p, EObject root_p) {
    // Override if needed
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPhysicalModelScope#load()
   */
  public boolean load() throws Exception {
    for (Resource rootResource : _rootResources) {
      rootResource.load(null);
    }
    return true;
  }
  
  /**
   * Get notified that the given resource is in this scope by inclusion
   * @param resource_p a non-null resource
   * @param element_p a non-null element via which the resource was retrieved
   */
  protected final void notifyInclusion(Resource resource_p, EObject element_p) {
    if (!_resources.contains(resource_p))
      addNewResource(resource_p);
    EObject container = getContainer(element_p);
    if (container != null) {
      Resource containerResource = container.eResource();
      if (containerResource != null && containerResource != resource_p) {
        // New inclusion
        _includedResources.add(containerResource, resource_p);
        // Remove from roots and referencing relation
        _rootResources.remove(resource_p);
        _referencedResources.remove(containerResource, resource_p);
      }
    }
  }
  
  /**
   * Get notified that the given source resource references the given target resource
   * @param source_p a non-null resource
   * @param target_p a non-null resource
   */
  protected final void notifyReference(Resource source_p, Resource target_p) {
    if (!_resources.contains(target_p)) {
      addNewResource(target_p);
      _rootResources.add(target_p);
      _referencedResources.add(source_p, target_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPhysicalModelScope#save()
   */
  public boolean save() throws Exception {
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
        Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
    for (Resource resource : getResources()) {
      resource.save(options);
    }
    return true;
  }
  
  
  /**
   * An iterator over the dynamic list of resources
   */
  class ExpandingMultiResourceTreeIterator extends MultiResourceTreeIterator {
    /**
     * Constructor
     */
    public ExpandingMultiResourceTreeIterator() {
      super(new DynamicUniqueListIterator<Resource>(_resources));
    }
    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.MultiResourceTreeIterator#next()
     */
    @Override
    public EObject next() {
      EObject result = super.next();
      if (!_hasBeenFullyExplored) {
        // It is the first time the scope is being explored:
        // find and remember additional relevant resources
        Resource resource = result.eResource();
        if (resource != null)
          notifyInclusion(resource, result);
        for (Resource additionalResource : getRelevantReferencedResources(result))
          notifyReference(resource, additionalResource);
        if (!hasNext())
          _hasBeenFullyExplored = true;
      }
      return result;
    }
  }
  
}
