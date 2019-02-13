/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.policies;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A simple implementation of IDiffPolicy.
 * @see IDiffPolicy
 * @author Olivier Constant
 */
public class DefaultDiffPolicy implements IDiffPolicy<EObject> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerEqual(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean considerEqual(Object value1_p, Object value2_p, Object attribute_p) {
    return value1_p.equals(value2_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerEqualOutOfScope(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean considerEqualOutOfScope(EObject outOfScopeValue_p,
      EObject candidate_p, Object reference_p) {
    boolean result = false;
    URI uri = EcoreUtil.getURI(outOfScopeValue_p);
    if (uri != null) {
      URI candidateUri = EcoreUtil.getURI(candidate_p);
      result = uri.equals(candidateUri);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerOrderedAttribute(java.lang.Object)
   */
  public boolean considerOrderedAttribute(Object attribute_p) {
    return considerOrderedFeature((EStructuralFeature)attribute_p);
  }
  
  /**
   * Return whether the given feature must be considered as ordered
   * @param feature_p a non-null feature
   */
  protected boolean considerOrderedFeature(EStructuralFeature feature_p) {
    return feature_p.isOrdered();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#considerOrderedAttribute(java.lang.Object)
   */
  public boolean considerOrderedReference(Object reference_p) {
    return reference_p != null && considerOrderedFeature((EStructuralFeature)reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverAttribute(java.lang.Object)
   */
  public boolean coverAttribute(Object attribute_p) {
    EAttribute attribute = (EAttribute)attribute_p;
    boolean result = !attribute.isDerived() && !FeatureMapUtil.isFeatureMap(attribute) &&
        (coverTransientFeatures() || !attribute.isTransient()) &&
        (coverIDAttributes() || !attribute.isID());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverReference(java.lang.Object)
   */
  public boolean coverReference(Object reference_p) {
    EReference reference = (EReference)reference_p;
    boolean result = !reference.isDerived() && !FeatureMapUtil.isFeatureMap(reference) &&
        (coverTransientFeatures() || !reference.isTransient());
    return result;
  }
  
  /**
   * Return whether ID attributes must be covered
   */
  protected boolean coverIDAttributes() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.generic.api.IMatch)
   */
  public boolean coverMatch(IMatch<EObject> match_p) {
    boolean result = match_p.coversRole(Role.TARGET) || match_p.coversRole(Role.REFERENCE);
    if (result && !coverTransientFeatures() && match_p.isPartial()) {
      // Ignore elements owned by a transient containment
      EObject element = match_p.get(match_p.getUncoveredRole().opposite());
      EReference containment = element.eContainmentFeature();
      if (containment != null) {
        result = !containment.isTransient();
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverOutOfScopeValue(java.lang.Object, java.lang.Object)
   */
  public boolean coverOutOfScopeValue(EObject element_p, Object reference_p) {
    EReference reference = (EReference)reference_p;
    return !reference.isContainment() && !reference.isContainer() &&
        !reference.isTransient() && isPluginElement(element_p);
  }
  
  /**
   * Return whether transient (non-serialized) features must be covered
   */
  protected boolean coverTransientFeatures() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDiffPolicy#coverValue(java.lang.Object, java.lang.Object)
   */
  public boolean coverValue(Object value_p, Object attribute_p) {
    return true;
  }
  
  /**
   * Return whether the given element is provided by a plug-in of the current platform
   * @param element_p a non-null element
   */
  protected boolean isPluginElement(EObject element_p) {
    URI uri = EcoreUtil.getURI(element_p);
    return uri != null && !uri.isPlatformResource();
  }
  
}
