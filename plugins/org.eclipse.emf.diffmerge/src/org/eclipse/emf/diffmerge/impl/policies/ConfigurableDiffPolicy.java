/**
 * <copyright>
 * 
 * Copyright (c) 2013-2016 Thales Global Services S.A.S.
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
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * A diff policy that provides a few characteristics which can be configured.
 * @author Olivier Constant
 */
public class ConfigurableDiffPolicy extends DefaultDiffPolicy {
  
  /** Whether orders must be ignored */
  private boolean _ignoreOrders;
  
  
  /**
   * Constructor
   */
  public ConfigurableDiffPolicy() {
    _ignoreOrders = false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#considerOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public final boolean considerOrdered(EStructuralFeature feature_p) {
    boolean result;
    if (_ignoreOrders)
      result = false;
    else
      result = doConsiderOrdered(feature_p);
    return result;
  }
  
  /**
   * Return whether the given feature must be considered as ordered, independently of the
   * "ignore orders" flag
   * @see IDiffPolicy#considerOrdered(EStructuralFeature)
   * @param feature_p a non-null feature
   */
  protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
    return super.considerOrdered(feature_p);
  }
  
  /**
   * Return whether this policy ignores orders
   */
  public boolean isIgnoreOrders() {
    return _ignoreOrders;
  }
  
  /**
   * Set whether orders must be ignored
   * @param ignore_p whether orders must be ignored
   */
  public void setIgnoreOrders(boolean ignore_p) {
    _ignoreOrders = ignore_p;
  }
  
}
