/*********************************************************************
 * Copyright (c) 2006-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.sirius;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.scopes.IPersistentDataScope;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;


/**
 * A scope for fragmented Viewpoint files which covers the semantic elements.
 */
public class SiriusScope extends GMFScope {
  
  /** The file extension for Sirius resource fragments */
  public static final String SESSION_RESOURCE_FRAGMENT_EXTENSION = "airdfragment"; //$NON-NLS-1$
  
  /** The set of Sirius file extensions */
  public static final Collection<String> SIRIUS_FILE_EXTENSIONS = Arrays.asList(
      SiriusUtil.SESSION_RESOURCE_EXTENSION,
      SESSION_RESOURCE_FRAGMENT_EXTENSION);
  
  /** The set of packages which can be used in Sirius resources */
  protected static final Collection<? extends EPackage> SIRIUS_PACKAGES =
    Arrays.asList(
        ViewpointPackage.eINSTANCE,
        StylePackage.eINSTANCE,
        NotationPackage.eINSTANCE,
        DiagramPackage.eINSTANCE
    );
  
  /** The Sirius feature from DRepresentationDescriptor to DRepresentations */
  protected static final EReference SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE =
      ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation();
  
  /** The non-null map from representation UIDs to the corresponding descriptors */
  protected final Map<String, DRepresentationDescriptor> _idToDescriptor =
      new HashMap<String, DRepresentationDescriptor>();
  
  /** An helper for image helpers */
  private SiriusImageHelper siriusImageHelper;
  
  /**
   * Constructor
   * @param uri_p a non-null URI
   * @param domain a non-null editing domain
   * @param readOnly_p whether the scope is read-only
   */
  public SiriusScope(URI uri_p, EditingDomain domain, boolean readOnly_p) {
    super(uri_p, domain, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uri_p a non-null URI
   * @param resourceSet_p a non-null resource set
   * @param readOnly_p whether the scope is read-only
   */
  public SiriusScope(URI uri_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    super(uri_p, resourceSet_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uris_p a non-null collection of URIs of resources to load as roots
   * @param editingDomain_p a non-null editing domain that encompasses the scope
   * @param readOnly_p whether the scope should be read-only, if supported
   */
  public SiriusScope(Collection<URI> uris_p, EditingDomain editingDomain_p, boolean readOnly_p) {
    super(uris_p, editingDomain_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uris_p a non-null collection of URIs of resources to load as roots
   * @param resourceSet_p a non-null resource set where the resources must be loaded
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  public SiriusScope(Collection<URI> uris_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    super(uris_p, resourceSet_p, readOnly_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    boolean isDescriptorToRepresentation =
        reference_p == SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE;
    if (isDescriptorToRepresentation) {
      add(value_p);
    }
    boolean result = super.add(source_p, reference_p, value_p);
    if (result && isDescriptorToRepresentation) {
      registerRepresentationDescriptor((DRepresentationDescriptor)source_p);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#get(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute)
   */
  @Override
  public List<Object> get(EObject source_p, EAttribute attribute_p) {
    List<Object> objects = super.get(source_p, attribute_p);
    objects = siriusImageHelper.adaptGetValue(source_p, attribute_p, objects);
    return objects;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#add(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
   */
  @Override
  public boolean add(EObject source_p, EAttribute attribute_p, Object value_p) {
    Object value = siriusImageHelper.adaptAddValue(source_p, attribute_p, value_p);
    return super.add(source_p, attribute_p, value);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
   */
  @Override
  public boolean remove(EObject source_p, EAttribute attribute_p, Object value_p) {
    Object value = siriusImageHelper.adaptRemoveValue(source_p, attribute_p, value_p);
    return super.remove(source_p, attribute_p, value);
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContainer(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EObject getContainer(EObject element_p) {
    EObject result;
    if (element_p instanceof DRepresentationDescriptor) {
      registerRepresentationDescriptor((DRepresentationDescriptor)element_p);
    }
    EObject basicContainer = super.getContainer(element_p);
    if (element_p instanceof DRepresentation && basicContainer == null) {
      result = getRepresentationDescriptor((DRepresentation)element_p);
    } else {
      result = basicContainer;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContainment(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EReference getContainment(EObject element_p) {
    EReference result;
    if (element_p instanceof DRepresentation) {
      result = getContainer(element_p) instanceof DRepresentationDescriptor?
          SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE: super.getContainment(element_p);
    } else {
      result = super.getContainment(element_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getContents()
   */
  @Override
  public List<EObject> getContents() {
    List<EObject> superResult = super.getContents();
    List<EObject> result = new FArrayList<EObject>(superResult, null);
    Iterator<EObject> it = result.iterator();
    while (it.hasNext()) {
      EObject current = it.next();
      if (current instanceof DRepresentation && getContainer(current) != null) {
        it.remove();
      }
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContents(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public List<EObject> getContents(EObject element_p) {
    List<EObject> result = super.getContents(element_p);
    if (element_p instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor descriptor = (DRepresentationDescriptor)element_p;
      registerRepresentationDescriptor(descriptor);
      DRepresentation referenced = descriptor.getRepresentation();
      if (referenced != null) {
        List<EObject> originalResult = result;
        result = new FArrayList<EObject>(originalResult.size() + 1, null);
        result.addAll(originalResult);
        result.add(referenced);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    Collection<EReference> result = super.getCrossReferencesInScope(element_p);
    if (element_p instanceof DSemanticDecorator) {
      // From Viewpoint to semantic
      result.add(ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target());
    } else if (element_p instanceof DAnalysis) {
      // From Viewpoint analysis to referenced AIRD fragments
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_ReferencedAnalysis());
      // From Viewpoint analysis to semantic models
      result.add(ViewpointPackage.eINSTANCE.getDAnalysis_Models());
    } else if (element_p instanceof DRepresentationDescriptor) {
      // From representation descriptors to representations
      result.add(SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE);
    }
    return result;
  }
  
  /**
   * Return the representation UID referenced by the given representation descriptor
   * @param descriptor_p a non-null representation descriptor
   * @return a potentially null string
   */
  protected String getReferencedUID(DRepresentationDescriptor descriptor_p) {
    String result = null;
    ResourceDescriptor rDescriptor = descriptor_p.getRepPath();
    if (rDescriptor != null) {
      URI uri = rDescriptor.getResourceURI();
      if (uri != null) {
        result = uri.fragment();
      }
    }
    return result;
  }
  
  /**
   * Return the descriptor of the given representation, if any and known
   * @param representation_p a non-null representation
   * @return a potentially null representation descriptor
   */
  public DRepresentationDescriptor getRepresentationDescriptor(
      DRepresentation representation_p) {
    DRepresentationDescriptor result;
    String repID = representation_p.getUid();
    result = _idToDescriptor.get(repID);
    if (result == null) {
      DRepresentationQuery rep2descQuery = new DRepresentationQuery(representation_p);
      result = rep2descQuery.getRepresentationDescriptor();
      if (result == null) {
        result = getRepresentationDescriptorByExploration(representation_p);
      }
      if (result != null) {
        registerRepresentationDescriptor(result);
      }
    }
    return result;
  }
  
  /**
   * Find and return the descriptor for the given representation, if any, by simple exploration
   * of this scope
   * @param representation_p a non-null representation
   * @return a potentially null descriptor
   */
  protected DRepresentationDescriptor getRepresentationDescriptorByExploration(
      DRepresentation representation_p) {
    return getRepresentationDescriptorByPhysicalExploration(representation_p, this);
  }
  
  /**
   * Find and return the descriptor for the given representation, if any, by simple exploration
   * of the given scope. It is assumed that Sirius DAnalyses are physical roots of the scope.
   * The scope does not have to be a Sirius scope.
   * @param representation_p a non-null representation
   * @param scope_p a non-null scope
   * @return a potentially null descriptor
   */
  public static DRepresentationDescriptor getRepresentationDescriptorByPhysicalExploration(
      DRepresentation representation_p, IPersistentDataScope<EObject> scope_p) {
    List<EObject> roots = scope_p.getRawRoots();
    for (EObject root : roots) {
      if (root instanceof DAnalysis) {
        for (DView view : ((DAnalysis)root).getOwnedViews()) {
          for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
            if (descriptor.getRepresentation() == representation_p) {
              return descriptor;
            }
          }
        }
      }
    }
    return null;
  }
  
  /**
   * Return a session resource of the scope, if any
   * @return a potentially null resource
   */
  protected Resource getSessionResource() {
    for (Resource candidate : getResources()) {
      if (isSessionResource(candidate)) {
        return candidate;
      }
    }
    return null;
  }
  
  /**
   * Return the Sirius session of the editing domain of the scope, if any
   * @return a potentially null session
   */
  public Session getSession() {
    Session result = null;
    if (getEditingDomain() != null) {
      Resource sessionResource = getSessionResource();
      if (sessionResource != null) {
        Session sessionForURI = SessionManager.INSTANCE.getExistingSession(
            sessionResource.getURI());
        if (sessionForURI != null && sessionForURI.getSessionResource() == sessionResource) {
          result = sessionForURI;
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the given resource is a session resource
   * @param resource_p a non-null resource
   */
  protected boolean isSessionResource(Resource resource_p) {
    URI uri = resource_p.getURI();
    return uri != null && isSessionResourceURI(uri);
  }
  
  /**
   * Return whether the given URI is a session resource URI
   * @param uri_p a non-null URI
   */
  protected boolean isSessionResourceURI(URI uri_p) {
    return SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(uri_p.fileExtension());
  }
  
  /**
   * Return whether the given element can be included in a Sirius resource
   * @param element_p a non-null element
   */
  protected boolean isSiriusElement(EObject element_p) {
    EPackage pack = element_p.eClass().getEPackage();
    return SIRIUS_PACKAGES.contains(pack) || element_p instanceof DRepresentation;
  }
  
  /**
   * Return whether the given resource is a Sirius model or model fragment
   * @param resource_p a non-null resource
   */
  protected boolean isSiriusResource(Resource resource_p) {
    boolean result = false;
    if (resource_p.getURI() != null) {
      String extension = resource_p.getURI().fileExtension();
      if (extension != null) {
        extension = extension.toLowerCase();
        result = SIRIUS_FILE_EXTENSIONS.contains(extension);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#isSuitableFor(org.eclipse.emf.ecore.resource.Resource, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected boolean isSuitableFor(Resource resource_p, EObject root_p) {
    boolean result = isSiriusResource(resource_p) == isSiriusElement(root_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.scopes.AbstractDataScope#mIsContainmentReference(java.lang.Object)
   */
  @Override
  public boolean mIsContainmentReference(Object reference_p) {
    return
        reference_p == SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE ||
        super.mIsContainmentReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#notifyExplored(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void notifyExplored(EObject element_p) {
    if (element_p instanceof DRepresentationDescriptor) {
      registerRepresentationDescriptor((DRepresentationDescriptor)element_p);
    }
  }
  
  /**
   * Register the given representation descriptor for (representation -> descriptor) navigation
   * @param descriptor_p a non-null representation descriptor
   */
  protected void registerRepresentationDescriptor(DRepresentationDescriptor descriptor_p) {
    String uid = getReferencedUID(descriptor_p);
      if (uid != null) {
      _idToDescriptor.put(uid, descriptor_p);
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject source_p, EReference reference_p, EObject value_p) {
    boolean isDescriptorToRepresentation =
        reference_p == SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE;
    String uid = null;
    if (isDescriptorToRepresentation) {
      uid = getReferencedUID((DRepresentationDescriptor)source_p);
    }
    boolean result =  super.remove(source_p, reference_p, value_p);
    if (result && isDescriptorToRepresentation) {
      _idToDescriptor.remove(uid);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#save()
   */
  @Override
  public IStatus save() {
    IStatus result;
    Session session = getSession();
    if (session != null) {
      session.save(new NullProgressMonitor());
      result = Status.OK_STATUS;
    } else {
      result = super.save();
    }
    return result;
  }

  @Override
  public void setComparison(IComparison comparison_p) {
    super.setComparison(comparison_p);
    siriusImageHelper = new SiriusImageHelper(comparison);
  }
  
}
