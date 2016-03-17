/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A dialog for specifying a merge operation.
 * @author Olivier Constant
 */
public class MergeChoicesDialog extends MessageDialog {
	
  /** The non-null data that this dialog allows editing */
  private final MergeChoiceData _data;
  
  /** Whether the choice for children is enabled */
  private final boolean _askAboutChildren;
  
  
	/**
	 * Constructor
	 * @param parentShell_p the non-null shell for this dialog
	 * @param title_p a non-null string
	 * @param data_p the non-null data that this dialog allows editing
	 * @param askAboutChildren_p whether the choice for children is enabled
	 */
	public MergeChoicesDialog(Shell parentShell_p, String title_p, MergeChoiceData data_p,
	    boolean askAboutChildren_p) {
		super(parentShell_p, title_p, null,
		    Messages.MergeChoicesDialog_Question, MessageDialog.QUESTION, 
				new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
		_data = data_p;
		_askAboutChildren = askAboutChildren_p;
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
    coverChildrenButton.setText(Messages.MergeChoicesDialog_IncludeChildren);
    coverChildrenButton.setSelection(getData().isCoverChildren());
    coverChildrenButton.setEnabled(_askAboutChildren);
    coverChildrenButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setCoverChildren(!getData().isCoverChildren());
      }
    });
    // Incremental mode
    Button incrementalModeButton = new Button(result, SWT.CHECK);
    incrementalModeButton.setText(Messages.MergeChoicesDialog_IncrementalMode);
    incrementalModeButton.setSelection(getData().isIncrementalMode());
    incrementalModeButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setIncrementalMode(!getData().isIncrementalMode());
      }
    });
    // Show impact
    Button showImpactButton = new Button(result, SWT.CHECK);
    showImpactButton.setText(Messages.MergeChoicesDialog_ShowImpact);
    showImpactButton.setSelection(getData().isShowImpact());
    showImpactButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setShowImpact(!getData().isShowImpact());
      }
    });
    return result;
  }
	
	/**
	 * Return the data that this dialog allows editing
	 * @return a non-null object
	 */
	protected MergeChoiceData getData() {
	  return _data;
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.MessageDialog#open()
	 */
	@Override
  public int open() {
    int result = super.open();
    _data.setProceed(result == Window.OK);
    return result;
  }
  
}
