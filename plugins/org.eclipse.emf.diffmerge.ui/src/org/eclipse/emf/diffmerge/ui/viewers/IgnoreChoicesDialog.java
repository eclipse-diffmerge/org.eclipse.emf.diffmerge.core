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
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A dialog for specifying an ignore operation on differences.
 * @author Olivier Constant
 */
public class IgnoreChoicesDialog extends MessageDialog {
	
  /** The non-null data that this dialog allows editing */
  private final IgnoreChoiceData _data;
  
  
	/**
	 * Constructor
	 * @param parentShell_p the non-null shell for this dialog
	 * @param title_p a non-null string
	 * @param data_p the non-null data that this dialog allows editing
	 */
	public IgnoreChoicesDialog(Shell parentShell_p, String title_p, IgnoreChoiceData data_p) {
		super(parentShell_p, title_p, null,
		    Messages.IgnoreChoicesDialog_Question, MessageDialog.QUESTION, 
				new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
		_data = data_p;
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(Composite)
	 */
	@Override
  protected Control createCustomArea(Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.NONE);
    GridLayout layout = new GridLayout(1, true);
    result.setLayout(layout);
    // Cover children
    Button coverChildrenButton = new Button(result, SWT.CHECK);
    coverChildrenButton.setText(Messages.IgnoreChoicesDialog_IncludeChildren);
    coverChildrenButton.setSelection(getData().isCoverChildren());
    coverChildrenButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setCoverChildren(!getData().isCoverChildren());
      }
    });
    return result;
  }
	
	/**
	 * Return the data that this dialog allows editing
	 * @return a non-null object
	 */
	protected IgnoreChoiceData getData() {
	  return _data;
	}
  
	/**
	 * @see org.eclipse.jface.dialogs.MessageDialog#open()
	 */
	@Override
	public int open() {
	  int result = super.open();
	  _data.setProceed(result == 0);
	  return result;
	}
	
}
