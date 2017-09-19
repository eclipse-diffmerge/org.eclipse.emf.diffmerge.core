/**
 * <copyright>
 * 
 * Copyright (c) 2006-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.sirius;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
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
  protected final Map<String, DRepresentationDescriptor> _idToDescriptor;
  
  
  /**
   * Constructor
   * @param uri_p a non-null URI
   * @param domain a non-null editing domain
   * @param readOnly_p whether the scope is read-only
   */
  public SiriusScope(URI uri_p, EditingDomain domain, boolean readOnly_p) {
    super(uri_p, domain, readOnly_p);
    _idToDescriptor = new HashMap<String, DRepresentationDescriptor>();
  }
  
  /**
   * Constructor
   * @param uri_p a non-null URI
   * @param resourceSet_p a non-null resource set
   * @param readOnly_p whether the scope is read-only
   */
  public SiriusScope(URI uri_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    super(uri_p, resourceSet_p, readOnly_p);
    _idToDescriptor = new HashMap<String, DRepresentationDescriptor>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    boolean result = super.add(source_p, reference_p, value_p);
    if (reference_p == SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE) {
      DRepresentationDescriptor descriptor = (DRepresentationDescriptor)source_p;
      String uid = getReferencedUID(descriptor);
      _idToDescriptor.put(uid, descriptor);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#getContainer(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public EObject getContainer(EObject element_p) {
    EObject result;
    if (element_p instanceof DRepresentation) {
      DRepresentation representation = (DRepresentation)element_p;
      result = _idToDescriptor.get(representation.getUid());
    } else {
      result = super.getContainer(element_p);
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
          SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE: null;
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
      if (current instanceof DRepresentation && getContainer(current) != null)
        it.remove();
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
      if (uri != null)
        result = uri.fragment();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope#isContainment(org.eclipse.emf.ecore.EReference)
   */
  @Override
  public boolean isContainment(EReference reference_p) {
    return
        reference_p == SIRIUS_DESCRIPTOR_TO_REPRESENTATION_FEATURE ||
        super.isContainment(reference_p);
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
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#notifyExplored(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void notifyExplored(EObject element_p) {
    if (element_p instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor descriptor = (DRepresentationDescriptor)element_p;
      String uid = getReferencedUID(descriptor);
      if (uid != null)
        _idToDescriptor.put(uid, descriptor);
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
    if (isDescriptorToRepresentation)
      uid = getReferencedUID((DRepresentationDescriptor)source_p);
    boolean result =  super.remove(source_p, reference_p, value_p);
    if (result && isDescriptorToRepresentation)
      _idToDescriptor.remove(uid);
    return result;
  }
  
}
