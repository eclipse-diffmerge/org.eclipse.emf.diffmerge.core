/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A dialog for setting up difference categories.
 * @author Olivier Constant
 */
public class CategoryDialog extends MessageDialog {
  
  /** The non-null input */
  protected final CategoryViewer.Input _input;
  
  /** The initially null reset button */
  protected Button _resetButton;
  
  
  /**
   * Constructor
   * @param parentShell_p a non-null shell
   * @param node_p a non-null diff node
   */
  public CategoryDialog(Shell parentShell_p, EMFDiffNode node_p) {
    super(parentShell_p, Messages.CategoryDialog_Header, null,
        Messages.CategoryDialog_Description,
        MessageDialog.NONE,
        new String[] { Messages.CategoryDialog_ApplyLabel, IDialogConstants.OK_LABEL,
            IDialogConstants.CANCEL_LABEL },
        1);
    _input = new CategoryViewer.Input(node_p);
    _resetButton = null;
    setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE | SWT.RESIZE);
    setBlockOnOpen(false);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
   */
  @Override
  protected void buttonPressed(int buttonId_p) {
    switch (buttonId_p) {
    case 0:
      // Apply button: Do not close dialog
      _input.applyChanges();
      break;
    case 1:
      // OK button: Apply and close
      _input.applyChanges();
      //$FALL-THROUGH$
    default:
      super.buttonPressed(buttonId_p);
    }
  }
  
  /**
   * @see org.eclipse.jface.dialogs.IconAndMessageDialog#createButtonBar(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createButtonBar(Composite parent_p) {
    Control result = super.createButtonBar(parent_p);
    getCustomOKButton().setEnabled(false);
    getCustomApplyButton().setEnabled(false);
    // Sizing the reset button according to the other buttons
    GridData resetButtonData = (GridData)_resetButton.getLayoutData();
    Object okButtonDataRaw = getCustomOKButton().getLayoutData();
    if (okButtonDataRaw instanceof GridData) {
      GridData okButtonData = (GridData)okButtonDataRaw;
      resetButtonData.widthHint = okButtonData.widthHint;
      _resetButton.getParent().layout();
    }
    return result;
  }
  
  /**
   * @see MessageDialog#createCustomArea(Composite)
   */
  @Override
  protected Control createCustomArea(Composite parent_p) {
    // Main viewer
    CategoryViewer viewer = new CategoryViewer(parent_p);
    _input.addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (CategoryViewer.Input.PROPERTY_HAS_CHANGES.equals(event_p.getProperty())) {
          // Update buttons according to the current changes
          boolean unappliedChanges = ((Boolean)event_p.getNewValue()).booleanValue();
          getCustomOKButton().setEnabled(unappliedChanges);
          getCustomApplyButton().setEnabled(unappliedChanges);
        }
      }
    });
    Control result = viewer.getControl();
    result.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e_p) {
        _input.removePropertyChangeListeners();
      }
    });
    // Reset button
    _resetButton = new Button(parent_p, SWT.PUSH);
    _resetButton.setText(Messages.CategoryDialog_ResetButton);
    GridData resetButtonData = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
    _resetButton.setLayoutData(resetButtonData);
    _resetButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _input.resetToDefault();
      }
    });
    viewer.setInput(_input);
    return result;
  }
  
  /**
   * Return the Apply button, given its specific location in this dialog
   */
  protected Button getCustomApplyButton() {
    return getButton(0);
  }
  
  /**
   * Return the OK button, given its specific location in this dialog
   */
  protected Button getCustomOKButton() {
    return getButton(1);
  }
  
}
