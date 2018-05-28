/**
 * <copyright>
 * 
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.util;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A simple Dialog for displaying comparisons.
 * It provides a Cancel button, and an OK button if edition is permitted.
 * @author Olivier Constant
 */
public class DiffMergeDialog extends Dialog {
  
  /** The optional title */
  protected String _title;
  
  /** The non-null input */
  protected final EMFDiffNode _input;
  
  
  /**
   * Constructor
   * @param shell_p a non-null shell
   * @param title_p an optional title for the dialog
   * @param input_p a non-null input for the dialog
   */
  public DiffMergeDialog(Shell shell_p, String title_p, EMFDiffNode input_p) {
    super(shell_p);
    _title = title_p;
    _input = input_p;
  }
  
  /**
   * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell newShell_p) {
    super.configureShell(newShell_p);
    newShell_p.setText(_title != null? _title: EMFDiffMergeUIPlugin.LABEL);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent_p) {
    boolean editable = isEditable();
    if (editable)
      createOKButton(parent_p);
    createButton(parent_p, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, !editable);
  }
  
  /**
   * Return whether the end user may edit either side
   */
  protected boolean isEditable() {
    return _input.isEditionPossible(true) || _input.isEditionPossible(false);
  }
  
  /**
   * Create and return the comparison viewer
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected AbstractComparisonViewer createComparisonViewer(Composite parent_p) {
    return new ComparisonViewer(parent_p);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {
    Composite composite = (Composite)super.createDialogArea(parent_p);
    AbstractComparisonViewer viewer = createComparisonViewer(composite);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    viewer.setInput(_input);
    return composite;
  }
  
  /**
   * Create a validation button
   * @param parent_p a non-null composite
   */
  protected Button createOKButton(Composite parent_p) {
    Button result = createButton(
        parent_p, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    IComparison comparison = _input.getActualComparison();
    boolean enabled = comparison != null? comparison.hasRemainingDifferences(): false;
    result.setEnabled(enabled);
    return result;
  }
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog#isResizable()
   */
  @Override
  protected boolean isResizable() {
    return true;
  }
  
}
