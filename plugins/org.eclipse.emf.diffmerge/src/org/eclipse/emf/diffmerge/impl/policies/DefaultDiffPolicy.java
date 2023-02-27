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
package org.eclipse.emf.diffmerge.impl.policies;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A simple implementation of IDiffPolicy for model elements.
 * @see IDiffPolicy
 * @author Olivier Constant
 */
public class DefaultDiffPolicy extends
org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy<EObject> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#considerEqualOutOfScope(java.lang.Object, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean considerEqualOutOfScope(EObject outOfScopeValue_p,
      EObject candidate_p, Object reference_p, ITreeDataScope<EObject> scope_p) {
    boolean result = false;
    URI uri = EcoreUtil.getURI(outOfScopeValue_p);
    if (uri != null) {
      URI candidateUri = EcoreUtil.getURI(candidate_p);
      result = uri.equals(candidateUri);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#considerOrderedAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean considerOrderedAttribute(Object attribute_p, ITreeDataScope<EObject> scope_p) {
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
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#considerOrderedReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean considerOrderedReference(Object reference_p, ITreeDataScope<EObject> scope_p) {
    return reference_p != null && considerOrderedFeature((EStructuralFeature)reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#coverAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean coverAttribute(Object attribute_p, ITreeDataScope<EObject> scope_p) {
    return coverFeature((EStructuralFeature)attribute_p) &&
        (coverIDAttributes() || !scope_p.mIsIDAttribute(attribute_p));
  }
  
  /**
   * Return whether the given feature must be covered by the difference
   * detection algorithm
   * @param feature_p a non-null feature
   */
  protected boolean coverFeature(EStructuralFeature feature_p) {
    return !feature_p.isDerived() && !FeatureMapUtil.isFeatureMap(feature_p) &&
        (coverTransientFeatures() || !feature_p.isTransient());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#coverReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean coverReference(Object reference_p, ITreeDataScope<EObject> scope_p) {
    return coverFeature((EReference)reference_p);
  }
  
  /**
   * Return whether ID attributes must be covered
   */
  protected boolean coverIDAttributes() {
    return false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.generic.api.IMatch)
   */
  @Override
  public boolean coverMatch(IMatch<EObject> match_p) {
    
    boolean result = super.coverMatch(match_p);
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
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy#coverOutOfScopeValue(java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean coverOutOfScopeValue(EObject element_p, Object reference_p,
      ITreeDataScope<EObject> scope_p) {
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
   * Return whether the given element is provided by a plug-in of the current platform
   * @param element_p a non-null element
   */
  protected boolean isPluginElement(EObject element_p) {
    URI uri = EcoreUtil.getURI(element_p);
    return uri != null && !uri.isPlatformResource();
  }
  
}
