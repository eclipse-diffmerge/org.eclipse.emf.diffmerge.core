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

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMapUtil;


/**
 * A default merge policy.
 * Conformity to references of multiplicity [1] is enforced by default.
 * @author Olivier Constant
 */
public class DefaultMergePolicy
extends org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy<EObject> {
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy#bindPresenceToOwnership(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean bindPresenceToOwnership(ITreeDataScope<EObject> scope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy#copyAttribute(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean copyAttribute(Object attribute_p, ITreeDataScope<EObject> scope_p) {
    return super.copyAttribute(attribute_p, scope_p) &&
        copyChangeableFeature((EStructuralFeature)attribute_p);
  }
  
  /**
   * Return whether the given changeable feature must be copied when elements are being copied,
   * independently of whether it is changeable or not
   * @param feature_p a non-null feature
   */
  protected boolean copyChangeableFeature(EStructuralFeature feature_p) {
    return !feature_p.isDerived() && !FeatureMapUtil.isFeatureMap(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy#copyOutOfScopeCrossReferences(org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean copyOutOfScopeCrossReferences(
      ITreeDataScope<EObject> sourceScope_p, ITreeDataScope<EObject> targetScope_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy#copyReference(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope)
   */
  @Override
  public boolean copyReference(Object reference_p, ITreeDataScope<EObject> scope_p) {
    return super.copyReference(reference_p, scope_p) &&
        copyChangeableFeature((EStructuralFeature)reference_p);
  }

}
