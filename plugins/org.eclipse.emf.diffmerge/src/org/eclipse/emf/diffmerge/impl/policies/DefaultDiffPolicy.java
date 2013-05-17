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
package org.eclipse.emf.diffmerge.impl.policies;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
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
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#considerOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean considerOrdered(EStructuralFeature feature_p) {
    return feature_p != null && feature_p.isOrdered();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public boolean coverFeature(EStructuralFeature feature_p) {
    boolean result = !feature_p.isDerived() && !feature_p.isTransient() &&
      !FeatureMapUtil.isFeatureMap(feature_p);
    if (result && feature_p instanceof EAttribute)
      result = !((EAttribute)feature_p).isID();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverMatch(IMatch)
   */
  public boolean coverMatch(IMatch match_p) {
    return match_p.coversRole(Role.TARGET) || match_p.coversRole(Role.REFERENCE);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IDiffPolicy#coverValue(Object, EAttribute)
   */
  public boolean coverValue(Object value_p, EAttribute attribute_p) {
    return true;
  }
  
}
