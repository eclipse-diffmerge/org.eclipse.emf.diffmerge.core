/**
 * <copyright>
 * 
 * Copyright (c) 2017  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.config.IComparisonConfiguration;
import org.eclipse.emf.diffmerge.api.config.IComparisonConfigurator;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMergePolicy;


/**
 * Data for ConfigureComparisonDialog.
 * @author Olivier Constant
 */
public class ComparisonConfiguration implements IComparisonConfiguration,
IComparisonConfigurator.Provider {
  
  /** Whether IDs must be remembered */
  protected boolean _keepMatchIDs;
  
  /** The optional configurable match policy */
  protected ConfigurableMatchPolicy _matchPolicy;
  
  /** The optional configurable diff policy */
  protected ConfigurableDiffPolicy _diffPolicy;
  
  /** The optional configurable merge policy */
  protected ConfigurableMergePolicy _mergePolicy;
  
  /** The non-null, potentially empty list of configurators */
  protected final List<IComparisonConfigurator> _configurators;
  
  /** Whether advanced configuration settings must be visible */
  protected boolean _showAdvancedSettings;
  
  
  /**
   * Constructor
   * @param comparisonMethod_p a non-null configuration method
   */
  public ComparisonConfiguration(ConfigurableComparisonMethod comparisonMethod_p) {
    _matchPolicy = null;
    _diffPolicy = null;
    _mergePolicy = null;
    // Match policy
    IMatchPolicy matchPolicy = comparisonMethod_p.getMatchPolicy();
    _keepMatchIDs = (matchPolicy == null)? false: matchPolicy.keepMatchIDs();
    if (matchPolicy instanceof ConfigurableMatchPolicy) {
      try {
        _matchPolicy = ((ConfigurableMatchPolicy)matchPolicy).clone();
      } catch (CloneNotSupportedException e) {
        // Keep null value
      }
    }
    // Diff policy
    IDiffPolicy diffPolicy = comparisonMethod_p.getDiffPolicy();
    if (diffPolicy instanceof ConfigurableDiffPolicy) {
      try {
        _diffPolicy = ((ConfigurableDiffPolicy)diffPolicy).clone();
      } catch (CloneNotSupportedException e) {
        // Keep null value
      }
    }
    // Merge policy
    IMergePolicy mergePolicy = comparisonMethod_p.getMergePolicy();
    if (mergePolicy instanceof ConfigurableMergePolicy) {
      try {
        _mergePolicy = ((ConfigurableMergePolicy)mergePolicy).clone();
      } catch (CloneNotSupportedException e) {
        // Keep null value
      }
    }
    _configurators = new ArrayList<IComparisonConfigurator>(
        comparisonMethod_p.getConfigurators());
    _showAdvancedSettings = getCurrentConfigurator() == null;
  }
  
  /**
   * Return the first configurator among the provided ones to which this configuration
   * complies, if any
   * @return a potentially null object
   */
  protected IComparisonConfigurator getCurrentConfigurator() {
    for (IComparisonConfigurator configurator : getConfigurators()) {
      if (configurator.isCompliant(this))
        return configurator;
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.config.IComparisonConfigurator.Provider#getConfigurators()
   */
  public List<IComparisonConfigurator> getConfigurators() {
    return _configurators;
  }
  
  /**
   * Return the configurable diff policy that is being configured, if any
   * @return a potentially null object
   */
  public ConfigurableDiffPolicy getDiffPolicy() {
    return _diffPolicy;
  }
  
  /**
   * Return the configurable match policy that is being configured, if any
   * @return a potentially null object
   */
  public ConfigurableMatchPolicy getMatchPolicy() {
    return _matchPolicy;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.config.IComparisonConfiguration#getMergePolicy()
   */
  public ConfigurableMergePolicy getMergePolicy() {
    return _mergePolicy;
  }
  
  /**
   * Return whether IDs must be remembered
   */
  public boolean isKeepMatchIDs() {
    return _keepMatchIDs;
  }
  
  /**
   * Return whether advanced configuration settings must be visible
   */
  public boolean isShowAdvancedSettings() {
    return _showAdvancedSettings;
  }
  
  /**
   * Set whether IDs must be remembered
   */
  public void setKeepMatchIDs(boolean keep_p) {
    _keepMatchIDs = keep_p;
    if (_matchPolicy != null)
      _matchPolicy.setKeepMatchIDs(keep_p);
  }
  
  /**
   * Return whether advanced configuration settings must be visible
   */
  public void setShowAdvancedSettings(boolean show_p) {
    _showAdvancedSettings = show_p;
  }
  
}