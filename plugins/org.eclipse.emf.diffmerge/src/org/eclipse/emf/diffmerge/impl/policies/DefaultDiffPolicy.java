/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.impl.policies;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
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
public class DefaultDiffPolicy implements IDiffPolicy {
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#considerEqual(Object, Object, EAttribute)
   */
  public boolean considerEqual(Object value1_p, Object value2_p, EAttribute attribute_p) {
    return value1_p.equals(value2_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#considerEqualOutOfScope(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  public boolean considerEqualOutOfScope(EObject outOfScopeValue_p,
      EObject candidate_p, EReference reference_p) {
    boolean result = false;
    URI uri = EcoreUtil.getURI(outOfScopeValue_p);
    if (uri != null) {
      URI candidateUri = EcoreUtil.getURI(candidate_p);
      result = uri.equals(candidateUri);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#considerOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean considerOrdered(EStructuralFeature feature_p) {
    return feature_p != null && feature_p.isOrdered();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean coverFeature(EStructuralFeature feature_p) {
    boolean result = !feature_p.isDerived() && !FeatureMapUtil.isFeatureMap(feature_p) &&
        (coverTransientFeatures() || !feature_p.isTransient());
    if (result && feature_p instanceof EAttribute && !coverIDAttributes())
      result = !((EAttribute)feature_p).isID();
    return result;
  }
  
  /**
   * Return whether ID attributes must be covered
   */
  protected boolean coverIDAttributes() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverMatch(IMatch)
   */
  public boolean coverMatch(IMatch match_p) {
    boolean result = match_p.coversRole(Role.TARGET) || match_p.coversRole(Role.REFERENCE);
    if (result && !coverTransientFeatures() && match_p.isPartial()) {
      // Ignore elements owned by a transient containment
      EObject element = match_p.get(match_p.getUncoveredRole().opposite());
      EReference containment = element.eContainmentFeature();
      if (containment != null)
        result = !containment.isTransient();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverOutOfScopeValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  public boolean coverOutOfScopeValue(EObject element_p, EReference reference_p) {
    return !reference_p.isContainment() && !reference_p.isContainer() &&
        !reference_p.isTransient() && isPluginElement(element_p);
  }
  
  /**
   * Return whether transient (non-serialized) features must be covered
   */
  protected boolean coverTransientFeatures() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverValue(Object, EAttribute)
   */
  public boolean coverValue(Object value_p, EAttribute attribute_p) {
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
