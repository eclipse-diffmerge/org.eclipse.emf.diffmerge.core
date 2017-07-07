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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
    }
    return result;
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
  
}
