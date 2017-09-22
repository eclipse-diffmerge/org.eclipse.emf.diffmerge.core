/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.setup;

import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.jface.wizard.Wizard;


/**
 * The wizard for setting up a comparison.
 * @author Olivier Constant
 */
public class ComparisonSetupWizard extends Wizard {
  
  /** The non-null data object for the wizard */
  final protected ComparisonSetup _setup;
  
  
  /**
   * Constructor
   * @param setup_p a non-null data object for the wizard
   */
  public ComparisonSetupWizard(ComparisonSetup setup_p) {
    setWindowTitle(EMFDiffMergeUIPlugin.LABEL);
    _setup = setup_p;
  }
  
  /**
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    addPage(new ComparisonSetupWizardPage("ComparisonSetupPage", _setup)); //$NON-NLS-1$
  }
  
  /**
   * @see org.eclipse.jface.wizard.Wizard#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    _setup.removePropertyChangeListeners();
  }
  
  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    _setup.performFinish(true);
    return true;
  }
  
}
