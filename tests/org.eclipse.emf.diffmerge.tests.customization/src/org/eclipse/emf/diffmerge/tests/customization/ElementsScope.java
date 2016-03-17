/**
 * <copyright>
 * 
 * Copyright (c) 2014-2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.tests.customization;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.gmf.GMFScope;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A scope for fragmented Elements test models.
 * @author Olivier Constant
 */
public class ElementsScope extends GMFScope {
  
  /** The set of cross-references in scope */
  protected static final List<EReference> REFERENCES_IN_SCOPE = Arrays.asList(
      ElementsPackage.eINSTANCE.getElement_ManyFromManyRef1(),
      ElementsPackage.eINSTANCE.getElement_ManyFromManyRef2(),
      ElementsPackage.eINSTANCE.getElement_ManyFromSingleRef(),
      ElementsPackage.eINSTANCE.getElement_ManyRef(),
      ElementsPackage.eINSTANCE.getElement_SingleFromManyRef(),
      ElementsPackage.eINSTANCE.getElement_SingleRef()
      );
  
  
  /**
   * Constructor
   * @param uri_p a non-null URI of the resource to load as root
   * @param editingDomain_p a non-null editing domain that encompasses the scope
   * @param readOnly_p whether the scope should be read-only, if supported
   */
  public ElementsScope(URI uri_p, EditingDomain editingDomain_p, boolean readOnly_p) {
    super(uri_p, editingDomain_p, readOnly_p);
  }
  
  /**
   * Constructor
   * @param uri_p a non-null resource URI
   * @param resourceSet_p a non-null resource set
   * @param readOnly_p whether the scope is in read-only mode, if applicable
   */
  public ElementsScope(URI uri_p, ResourceSet resourceSet_p, boolean readOnly_p) {
    super(uri_p, resourceSet_p, readOnly_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getCrossReferencesInScope(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Collection<EReference> getCrossReferencesInScope(EObject element_p) {
    Collection<EReference> result = super.getCrossReferencesInScope(element_p);
    if (element_p instanceof Element)
      result.addAll(REFERENCES_IN_SCOPE);
    return result;
  }
  
}
