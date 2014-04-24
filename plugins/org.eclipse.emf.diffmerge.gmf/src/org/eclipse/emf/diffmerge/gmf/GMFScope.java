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
package org.eclipse.emf.diffmerge.gmf;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;


/**
 * A scope for fragmented GMF models which covers the semantic elements of GMF Views.
 * @author Olivier Constant
 */
public class GMFScope extends FragmentedModelScope {
  
  /** A representation of the NULL value for the 'element' reference of Views (null vs. UNSET) */
  protected static final EObject NULL_ELEMENT = EcoreFactory.eINSTANCE.createEObject();
  
  
  /**
   * Constructor
   * @param uri_p a non-null URI of the resource to load as root
   * @param editingDomain_p a non-null editing domain that encompasses the scope
   * @param readOnly_p whether the scope should be read-only, if supported
   */
  public GMFScope(URI uri_p, EditingDomain editingDomain_p, boolean readOnly_p) {
    super(uri_p, editingDomain_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uri_p a non-null resource URI
   * @param resourceSet_p a non-null resource set
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  public GMFScope(URI uri_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    super(uri_p, resourceSet_p, readOnly_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#add(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    boolean result = true;
    if (value_p == NULL_ELEMENT)
      source_p.eSet(reference_p, null);
    else
      result = super.add(source_p, reference_p, value_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#get(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  public List<EObject> get(EObject source_p, EReference reference_p) {
    List<EObject> result = super.get(source_p, reference_p);
    if (result.isEmpty() && reference_p == NotationPackage.eINSTANCE.getView_Element() &&
        source_p.eIsSet(reference_p))
      result = Collections.singletonList(NULL_ELEMENT);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    Collection<EReference> result = super.getCrossReferencesInScope(element_p);
    if (element_p instanceof View)
      result.add(NotationPackage.eINSTANCE.getView_Element());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getLoadOptions(org.eclipse.emf.ecore.resource.Resource)
   */
  @Override
  protected Map<Object, Object> getLoadOptions(Resource resource_p) {
    Map<Object, Object> result = super.getLoadOptions(resource_p);
    result.put(GMFResource.OPTION_ABORT_ON_ERROR, Boolean.TRUE);
    return result;
  }
  
}
