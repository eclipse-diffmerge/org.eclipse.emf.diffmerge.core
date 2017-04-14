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
package org.eclipse.emf.diffmerge.ui.specification.ext;


import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * A configurable multi-criteria comparison method.
 * @author Olivier Constant
 */
public class ConfigurableComparisonMethod extends DefaultComparisonMethod {
  
  /** The initially null last configuration for the comparison method */
  protected static ConfigureComparisonDialog.ComparisonMethodConfigurationData __lastConfiguration = null;
  
  
  /**
   * Constructor
   * @param leftScopeSpec_p a non-null scope specification
   * @param rightScopeSpec_p a non-null scope specification
   * @param ancestorScopeSpec_p an optional scope specification
   */
  public ConfigurableComparisonMethod(IModelScopeDefinition leftScopeSpec_p,
      IModelScopeDefinition rightScopeSpec_p, IModelScopeDefinition ancestorScopeSpec_p) {
    super(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p);
    update(__lastConfiguration);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#configure()
   */
  @Override
  public void configure() {
    Shell shell = getShell();
    if (shell != null) {
      ConfigureComparisonDialog.ComparisonMethodConfigurationData data =
          new ConfigureComparisonDialog.ComparisonMethodConfigurationData(this);
      int confirmed = new ConfigureComparisonDialog(shell, data).open();
      if (Window.OK == confirmed)
        configurationConfirmed(data);
    }
  }
  
  /**
   * Handle the confirmation of the given configuration
   * @param data_p a non-null object
   */
  protected void configurationConfirmed(ConfigureComparisonDialog.ComparisonMethodConfigurationData data_p) {
    update(data_p);
    __lastConfiguration = data_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new ConfigurableDiffPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createMatchPolicy()
   */
  @Override
  protected IMatchPolicy createMatchPolicy() {
    return new ConfigurableMatchPolicy();
  }
  
  /**
   * Return a shell if available
   * @return a potentially null shell (always null if current thread is not the UI thread)
   */
  public Shell getShell() {
    Shell result;
    try {
      result = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    } catch (Exception e) {
      result = null;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#isConfigurable()
   */
  @Override
  public boolean isConfigurable() {
    return true;
  }
  
  /**
   * Update this comparison method according to the given configuration
   * @param data_p a potentially null configuration
   */
  protected void update(ConfigureComparisonDialog.ComparisonMethodConfigurationData data_p) {
    if (data_p == null) return;
    // Match policy
    IMatchPolicy matchPolicy = getMatchPolicy();
    if (matchPolicy instanceof DefaultMatchPolicy)
      ((DefaultMatchPolicy)matchPolicy).setKeepMatchIDs(data_p.isKeepMatchIDs());
    if (matchPolicy instanceof ConfigurableMatchPolicy) {
      ConfigurableMatchPolicy cMatchPolicy = (ConfigurableMatchPolicy)matchPolicy;
      ConfigurableMatchPolicy configuredCopy = data_p.getConfigurableMatchPolicy();
      if (configuredCopy != null)
        cMatchPolicy.configureAccordingTo(configuredCopy);
    }
    // Diff Policy
    IDiffPolicy diffPolicy = getDiffPolicy();
    if (diffPolicy instanceof ConfigurableDiffPolicy) {
      ConfigurableDiffPolicy cDiffPolicy = (ConfigurableDiffPolicy)diffPolicy;
      cDiffPolicy.setIgnoreOrders(data_p.isIgnoreOrders());
    }
  }
  
}
