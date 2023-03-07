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

import static org.eclipse.emf.diffmerge.EMFDiffMergePlugin.getDefault;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.scopes.IComparisonDependantScope;
import org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.structures.binary.HashBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.diffmerge.util.ModelsUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;


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
 * This implementation assumes an initialization phase that consists in a full exploration
 * of the scope. No other action on the scope or modification of the resource set is
 * expected to happen during this initialization phase.
 * EMF undo/redo is supported because the local state does not change after full exploration,
 * unless unload() has been called.
 * @author Olivier Constant
 */
public class FragmentedModelScope extends AbstractEditableModelScope
    implements IFragmentedModelScope.Editable, IEditingDomainProvider,
    IComparisonDependantScope {
  
  /** Whether the resources should be opened in read-only mode */
  private final boolean _isReadOnly;
  
  /** The optional editing domain that encompasses the scope */
  protected EditingDomain _editingDomain;
  
  /** The non-null resource set that encompasses the scope */
  protected final ResourceSet _resourceSet;
  
  /** The optional input stream for loading */
  protected InputStream _loadingStream;
  
  /** The non-null, non-empty ordered set of resources defining the scope.
   *  It includes _rootResources, _includedResources and _referencedResources. */
  protected final List<Resource> _resources;
  
  /** The non-null, non-empty ordered subset of the resources which are roots */
  protected final List<Resource> _rootResources;
  
  /** The non-null, potentially empty set of resources initially present
   * in the resource set before the scope was loaded (content is temporary) */
  protected final Set<Resource> _initiallyPresentResources;
  
  /** The non-null, initially empty set of resources that have been loaded due
   *  to the exploration of the scope */
  protected final Set<Resource> _loadedResources;
  
  /** The resource inclusion relationship */
  protected final IBinaryRelation.Editable<Resource, Resource> _includedResources;
  
  /** The resource referencing relationship */
  protected final IBinaryRelation.Editable<Resource, Resource> _referencedResources;
  
  /** The possible states of the scope, ordered */
  protected enum ScopeState {
    /**
     * The scope has just been created.
     */
    INITIALIZED,
    /**
     * The scope has been loaded but not fully explored.
     */
    LOADED,
    /**
     * The scope has been loaded and fully explored.
     */
    FULLY_EXPLORED,
    /**
     * The scope has been unloaded.
     */
    UNLOADED
  }
  
  /** The current state of the scope */
  protected ScopeState _state;
  
  /** The comparison that uses this scope */
  protected IComparison comparison;
  
  /**
   * Constructor
   * @param resource_p a non-null resource that belongs to a non-null resource set
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   * Precondition: resource_p != null && resource_p.getResourceSet() != null
   */
  public FragmentedModelScope(Resource resource_p, boolean readOnly_p) {
    this(resource_p.getURI(), resource_p.getResourceSet(), readOnly_p);
  }
  
  /**
   * Constructor
   * @param uri_p a non-null URI of the resource to load as root
   * @param editingDomain_p a non-null editing domain that encompasses the scope
   * @param readOnly_p whether the scope should be read-only, if supported
   */
  public FragmentedModelScope(URI uri_p, EditingDomain editingDomain_p, boolean readOnly_p) {
    this(Collections.singleton(uri_p), editingDomain_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uri_p a non-null resource URI
   * @param resourceSet_p a non-null resource set where the resources must be loaded
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  public FragmentedModelScope(URI uri_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    this(Collections.singleton(uri_p), resourceSet_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uris_p a non-null collection of URIs of resources to load as roots
   * @param editingDomain_p a non-null editing domain that encompasses the scope
   * @param readOnly_p whether the scope should be read-only, if supported
   */
  public FragmentedModelScope(Collection<URI> uris_p, EditingDomain editingDomain_p, boolean readOnly_p) {
    this(uris_p, editingDomain_p.getResourceSet(), readOnly_p);
    _editingDomain = editingDomain_p;
  }
  
  /**
   * Constructor
   * @param uris_p a non-null collection of URIs of resources to load as roots
   * @param resourceSet_p a non-null resource set where the resources must be loaded
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  public FragmentedModelScope(Collection<URI> uris_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    this(resourceSet_p, readOnly_p);
    for (URI uri : uris_p) {
      Resource rootResource = getResourceFromURI(_resourceSet, uri);
      if (rootResource != null) {
        _rootResources.add(rootResource);
        addNewResource(rootResource);
      }
    }
  }
  
  /**
   * Common constructor
   * @param resourceSet_p the non-null resource set that encompasses the scope
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  protected FragmentedModelScope(ResourceSet resourceSet_p, boolean readOnly_p) {
    _state = ScopeState.INITIALIZED;
    _loadingStream = null;
    _isReadOnly = readOnly_p;
    _resourceSet = resourceSet_p;
    _resources = new ArrayList<Resource>();
    _rootResources = new ArrayList<Resource>();
    _includedResources = new HashBinaryRelation<Resource, Resource>();
    _referencedResources = new HashBinaryRelation<Resource, Resource>();
    _initiallyPresentResources = new HashSet<Resource>();
    _initiallyPresentResources.addAll(_resourceSet.getResources());
    _loadedResources = new HashSet<Resource>();
    if (_resourceSet instanceof IEditingDomainProvider)
      _editingDomain = ((IEditingDomainProvider)_resourceSet).getEditingDomain();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope#add(org.eclipse.emf.ecore.EObject)
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
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  @SuppressWarnings("null") // To avoid irrelevant warning
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    Resource oldResource = value_p.eResource();
    boolean wasRoot = oldResource != null && oldResource.getContents().contains(value_p);
    Object formerId = tGetID(value_p, false);
    boolean result = super.add(source_p, reference_p, value_p);
    if (wasRoot && reference_p.isContainment()) { // Intentionally not isContainment(reference_p)
      oldResource.getContents().remove(value_p); // Not automatically handled
    }
    if (formerId != null) {
      // In case resource has changed, thus changing the extrinsic ID
      tSetID(value_p, formerId, false);
    }
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
    if (!_initiallyPresentResources.contains(resource_p)) {
      _loadedResources.add(resource_p);
    }
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
      if (current.eIsProxy() && current != ModelImplUtil.resolveIfLoaded(current, source_p)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Called as soon as full scope exploration has been done
   */
  protected void explorationFinished() {
    _state = ScopeState.FULLY_EXPLORED;
    // Completion of _loadedResources: additional resources may be involved because
    // of automatic proxy resolving. A consequence of this update of _loadedResources
    // is that the loaded resources of a scope S1 may wrongly include those of another
    // scope S2 on the same resource set (S2 loaded after S1 and explored after S1),
    // which is OK as long as both scopes are unloaded together.
    _loadedResources.addAll(_resourceSet.getResources());
    _loadedResources.removeAll(_initiallyPresentResources);
    _initiallyPresentResources.clear();
    // Handling read-only on loaded resources
    if (isReadOnly() && _editingDomain instanceof AdapterFactoryEditingDomain) {
      AdapterFactoryEditingDomain afEditingDomain = (AdapterFactoryEditingDomain)_editingDomain;
      Map<Resource, Boolean> readOnlyMap = afEditingDomain.getResourceToReadOnlyMap();
      if (readOnlyMap != null) {
        for (Resource loadedResource : _loadedResources) {
          readOnlyMap.put(loadedResource, Boolean.TRUE);
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#get(EObject, EReference)
   */
  @Override
  public List<EObject> get(EObject source_p, EReference reference_p) {
    // Current result, may require resolution of in-scope proxies
    List<EObject> result = super.get(source_p, reference_p);
    boolean requiresResolution = containsUnnecessaryProxies(result, source_p);
    if (requiresResolution) { // Recompute result if needed
      result = get(source_p, reference_p, true);
    }
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
    return getRawRoots();
  }
  
  /**
   * Return the cross-references, for the given element, which are in the scope
   * @param element_p a non-null element belonging to the scope
   * @return a non-null, potentially empty collection of non-containment, non-container references
   */
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    // Override if needed
    return new ArrayList<EReference>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getDefaultOriginator()
   */
  @Override
  protected Object getDefaultOriginator() {
    return getHoldingResource();
  }
  
  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  public EditingDomain getEditingDomain() {
    return _editingDomain;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#getHoldingResource()
   */
  public Resource getHoldingResource() {
    return _rootResources.isEmpty()? null: _rootResources.get(0);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getIncludedResources(org.eclipse.emf.ecore.resource.Resource)
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public Collection<Resource> getIncludedResources(Resource resource_p) {
    return _includedResources.get(resource_p);
  }
  
  /**
   * Return load options for loading the given resource
   * @param resource_p a non-null resource
   * @return a non-null, potentially empty, modifiable option map
   */
  protected Map<Object, Object> getLoadOptions(Resource resource_p) {
    // Override if needed
    return new HashMap<Object, Object>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IPersistentDataScope#getRawRoots()
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public List<EObject> getRawRoots() {
    List<EObject> result = new FArrayList<EObject>();
    for (Resource resource : _rootResources)
      result.addAll(resource.getContents());
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#getReferencedResources(org.eclipse.emf.ecore.resource.Resource)
   * Result is guaranteed to be accurate only if hasBeenExplored().
   */
  public Collection<Resource> getReferencedResources(Resource resource_p) {
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
          if (valueResource != null) {
            result.add(valueResource);
          }
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
      if (isSuitableFor(resource, newRoot_p)) {
        return resource;
      }
    }
    return null;
  }
  
  /**
   * Return a resource of the given URI obtained on the given resource set.
   * The returned resource does not have to be loaded.
   * @param resourceSet_p a non-null resource set
   * @param uri_p a non-null URI
   * @return a potentially null resource, where null means failure
   */
  protected Resource getResourceFromURI(ResourceSet resourceSet_p, URI uri_p) {
    Resource rootResource = resourceSet_p.getResource(uri_p, false);
    if (rootResource == null) {
      rootResource = resourceSet_p.createResource(uri_p);
    }
    return rootResource;
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
   * based on getAllContents has been performed and the contents are still available
   * @see org.eclipse.emf.diffmerge.api.scopes.IFragmentedModelScope#isFullyExplored()
   */
  public boolean isFullyExplored() {
    return _state == ScopeState.FULLY_EXPLORED;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#isLoaded()
   */
  public boolean isLoaded() {
    return _state != ScopeState.INITIALIZED && _state != ScopeState.UNLOADED;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#isReadOnly()
   */
  @Override
  public boolean isReadOnly() {
    return _isReadOnly;
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
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#load()
   */
  public IStatus load() {
    IStatus result;
    if (_state == ScopeState.INITIALIZED || _state == ScopeState.LOADED) {
      if (_loadingStream != null) {
        result = loadFromStream(_loadingStream);
      } else {
        result = getDefault().createMultiStatus();
        for (Resource rootResource : _rootResources) {
          IStatus subStatus = loadResource(rootResource);
          ((MultiStatus)result).merge(subStatus);
        }
      }
      _state = ScopeState.LOADED;
    } else {
      result = Status.OK_STATUS;
    }
    return result;
  }
  
  /**
   * Load the scope from the given input stream
   * @param stream_p a non-null input stream
   * @return a non-null status
   */
  protected IStatus loadFromStream(InputStream stream_p) {
    IStatus result;
    Resource holdingResource = getHoldingResource();
    if (holdingResource != null) {
      Map<?,?> options = getLoadOptions(holdingResource);
      try {
        holdingResource.load(stream_p, options);
        result = Status.OK_STATUS;
      } catch (IOException e) {
        result = getDefault().createErrorStatus(e);
      }
    } else {
      result = getDefault().createErrorStatus(
          Messages.FragmentedModelScope_ResourceNotDefined);
    }
    return result;
  }
  
  /**
   * Load the given root resource
   * @param resource_p a non-null resource
   * @return a non-null status
   */
  protected IStatus loadResource(Resource resource_p) {
    IStatus result;
    Map<?,?> options = getLoadOptions(resource_p);
    try {
      resource_p.load(options);
      result = Status.OK_STATUS;
    } catch (IOException e) {
      result = getDefault().createErrorStatus(e);
    }
    return result;
  }
  
  /**
   * Get notified that the given element has been found as a result of the exploration
   * of the scope, so covers(element_p) will be true immediately after the exploration
   * is over.
   * Precondition: !isFullyExplored()
   * @param element_p a non-null element
   */
  protected void notifyExplored(EObject element_p) {
    // Nothing by default
  }
  
  /**
   * Get notified that the given resource is included via the containment tree
   * into the other given resource. We assume that all roots of the included resource
   * are reachable from the including resource as is normally the case with
   * the fragmentation mechanism.
   * @param including_p a non-null resource
   * @param included_p a non-null resource which is not including_p
   */
  protected void notifyInclusion(Resource including_p, Resource included_p) {
    if (!_resources.contains(included_p)) {
      addNewResource(included_p);
    }
    // New inclusion
    _includedResources.add(including_p, included_p);
    // Remove from roots and referencing relation
    _rootResources.remove(included_p);
    // Inclusion takes precedence over referencing
    _referencedResources.remove(including_p, included_p);
  }
  
  /**
   * Get notified that the given source resource references the given target resource
   * @param source_p a non-null resource
   * @param target_p a non-null resource which is not source_p
   */
  protected void notifyReference(Resource source_p, Resource target_p) {
    if (!_resources.contains(target_p)) {
      addNewResource(target_p);
      _rootResources.add(target_p);
      _referencedResources.add(source_p, target_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope.Editable#save()
   */
  public IStatus save() {
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
        Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
    MultiStatus result = getDefault().createMultiStatus();
    for (Resource resource : getResources()) {
      try {
        resource.save(options);
      } catch (IOException e) {
        result.merge(getDefault().createErrorStatus(e));
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope.Editable#setStream(java.io.InputStream)
   */
  public void setStream(InputStream stream_p) {
    _loadingStream = stream_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope#unload()
   */
  public List<Resource> unload() {
    ModelsUtil.Unloader.getDefault().unloadAdapters(_loadedResources);
    for (Resource loadedResource : _loadedResources) {
      unloadResource(loadedResource);
    }
    List<Resource> result = new ArrayList<Resource>(_loadedResources);
    _loadedResources.clear();
    if (!result.isEmpty()) {
      _state = ScopeState.UNLOADED;
    }
    return result;
  }
  
  /**
   * Unload the given resource
   * @param resource_p a non-null resource
   */
  protected void unloadResource(Resource resource_p) {
    ModelsUtil.Unloader.getDefault().unloadResource(resource_p);
  }
  
  
  /**
   * An iterator over the dynamic list of resources
   */
  protected class ExpandingMultiResourceTreeIterator extends MultiResourceTreeIterator {
    /** The non-null, non-empty ordered set of resources whose exploration has started */
    protected final Set<Resource> _exploredResources;
    /** The potentially null next element */
    protected EObject _next;
    /** The potentially null actual resource of the preceding element, if any */
    protected Resource _currentResource;
    /** Whether iteration has finished */
    protected boolean _finished;
    /**
     * Constructor
     */
    public ExpandingMultiResourceTreeIterator() {
      super(new DynamicUniqueListIterator<Resource>(_resources));
      _exploredResources = new LinkedHashSet<Resource>();
      _next = null;
      _currentResource = null;
      _finished = false;
    }
    /**
     * Update to the next resource if relevant, and return whether it was
     */
    protected boolean checkNextResource() {
      boolean result = false;
      while ((_contentIterator == null || !_contentIterator.hasNext()) &&
          _resourceIterator.hasNext()) {
        result = true;
        Resource nextResource = _resourceIterator.next();
        if (!_exploredResources.contains(nextResource)) {
          _contentIterator = nextResource.getAllContents();
        }
      }
      return result;
    }
    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.MultiResourceTreeIterator#hasNext()
     */
    @Override
    public boolean hasNext() {
      update();
      return _next != null;
    }
    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.MultiResourceTreeIterator#next()
     */
    @Override
    public EObject next() {
      if (hasNext()) {
        EObject result = _next;
        _currentResource = _next.eResource();
        _next = null;
        if (!isFullyExplored()) {
          notifyExplored(result);
        }
        return result;
      }
      throw new NoSuchElementException();
    }
    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.MultiResourceTreeIterator#update()
     */
    @Override
    protected void update() {
      while (_next == null && !_finished) {
        boolean resourceChangedInList = checkNextResource();
        boolean firstExploration = !isFullyExplored();
        if (_contentIterator == null || !_contentIterator.hasNext()) {
          // Iteration finished
          _finished = true;
          _exploredResources.clear();
          _currentResource = null;
          if (firstExploration) {
            // First exploration finished
            explorationFinished();
          }
        } else {
          // Elements remaining
          EObject candidate = _contentIterator.next();
          boolean candidateOK = true;
          Resource candidateResource = candidate.eResource();
          if (candidateResource != null) {
            boolean resourceAlreadyExplored = !_exploredResources.add(candidateResource);
            // Since the element is in a resource, we know the resource will be explored
            // because we assume all resource roots are reachable in the case of resource inclusion
            boolean resourceChangedByInclusion = false;
            Resource candidateContainerResource = null;
            // Determine whether the current element leads to a new resource by inclusion
            if (!resourceChangedInList && _currentResource != null && _currentResource != candidateResource) {
              EObject candidateContainer = candidate.eContainer();
              if (candidateContainer != null) {
                candidateContainerResource = candidateContainer.eResource();
                resourceChangedByInclusion =
                    candidateContainerResource != null && candidateContainerResource != candidateResource;
              }
            }
            if (resourceChangedByInclusion && firstExploration) {
              // Resource reached by inclusion: Notify (candidateContainerResource cannot be null)
              notifyInclusion(candidateContainerResource, candidateResource);
            }
            if (resourceChangedByInclusion && resourceAlreadyExplored) {
              // Resource reached by inclusion but already visited: Skip element and its subtree
              _contentIterator.prune();
              candidateOK = false;
            }
          }
          if (candidateOK) {
            _next = candidate;
            if (firstExploration && candidateResource != null) {
              for (Resource additionalResource : getRelevantReferencedResources(_next)) {
                notifyReference(candidateResource, additionalResource);
              }
            }
          }
        }
      }
    }
  }

  public void setComparison(IComparison comparison_p) {
    comparison = comparison_p;
  }
  
}
