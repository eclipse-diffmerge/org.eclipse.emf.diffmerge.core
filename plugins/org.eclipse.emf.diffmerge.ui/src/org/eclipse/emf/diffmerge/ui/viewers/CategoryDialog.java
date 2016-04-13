/**
 * <copyright>
 * 
 * Copyright (c) 2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A dialog for setting up difference categories.
 * @author Olivier Constant
 */
public class CategoryDialog extends MessageDialog {
  
  /** The non-null diff node */
  protected final EMFDiffNode _node;
  
  
  /**
   * Constructor
   * @param parentShell_p a non-null shell
   * @param node_p a non-null diff node
   */
  public CategoryDialog(Shell parentShell_p, EMFDiffNode node_p) {
    super(parentShell_p, "Filters and Categories", null,
        "Set up categories in order to define what differences are shown.",
        MessageDialog.NONE,
        new String[] { "Apply", IDialogConstants.CLOSE_LABEL }, 0);
    _node = node_p;
    setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE | SWT.RESIZE);
    setBlockOnOpen(false);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
   */
  @Override
  protected void buttonPressed(int buttonId_p) {
    if (buttonId_p == 0) {
      // Apply button: Do not close dialog
      _node.updateDifferenceNumbers();
    } else {
      super.buttonPressed(buttonId_p);
    }
  }
  
  /**
   * @see MessageDialog#createCustomArea(Composite)
   */
  @Override
  protected Control createCustomArea(Composite parent_p) {
    CategoryViewer viewer = new CategoryViewer(parent_p);
    viewer.setInput(_node);
    return viewer.getControl();
  }
  
}
