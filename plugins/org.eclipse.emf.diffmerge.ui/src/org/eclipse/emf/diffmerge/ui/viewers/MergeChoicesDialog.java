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
 * A dialog for specifying a merge operation.
 * @author Olivier Constant
 */
public class MergeChoicesDialog extends MessageDialog {
	
  /** The non-null data that this dialog allows editing */
  private final MergeChoiceDialogData _data;
  
  /** Whether the choice for children is enabled */
  private final boolean _askAboutChildren;
  
  
	/**
	 * Constructor
	 * @param parentShell_p the non-null shell for this dialog
	 * @param title_p a non-null string
	 * @param data_p the non-null data that this dialog allows editing
	 * @param askAboutChildren_p whether the choice for children is enabled
	 */
	public MergeChoicesDialog(Shell parentShell_p, String title_p, MergeChoiceDialogData data_p,
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
    coverChildrenButton.setSelection(getData().getCoverChildren());
    coverChildrenButton.setEnabled(_askAboutChildren);
    coverChildrenButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setCoverChildren(!getData().getCoverChildren());
      }
    });
    // Incremental mode
    Button incrementalModeButton = new Button(result, SWT.CHECK);
    incrementalModeButton.setText(Messages.MergeChoicesDialog_IncrementalMode);
    incrementalModeButton.setSelection(getData().getIncrementalMode());
    incrementalModeButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setIncrementalMode(!getData().getIncrementalMode());
      }
    });
    // Show impact
    Button showImpactButton = new Button(result, SWT.CHECK);
    showImpactButton.setText(Messages.MergeChoicesDialog_ShowImpact);
    showImpactButton.setSelection(getData().getShowImpact());
    showImpactButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        getData().setShowImpact(!getData().getShowImpact());
      }
    });
    return result;
  }
	
	/**
	 * Return the data that this dialog allows editing
	 * @return a non-null object
	 */
	protected MergeChoiceDialogData getData() {
	  return _data;
	}
	
	
  /**
   * The data that this dialog allows editing.
   */
  public static class MergeChoiceDialogData {
    
    /** Whether differences on children must be covered */
    private boolean _coverChildren;
    
    /** Whether incremental mode is selected */
    private boolean _incrementalMode;
    
    /** Whether merge impact must be shown */
    private boolean _showImpact;
    
    
    /**
     * Default constructor
     */
    public MergeChoiceDialogData() {
      _coverChildren = false;
      _incrementalMode = false;
      _showImpact = false;
    }
    
    /**
     * Full constructor
     */
    public MergeChoiceDialogData(boolean coverChildren_p, boolean incrementalMode_p, boolean showImpact_p) {
      _coverChildren = coverChildren_p;
      _incrementalMode = incrementalMode_p;
      _showImpact = showImpact_p;
    }
    
    /**
     * Return whether differences on children must be covered
     */
    public boolean getCoverChildren() {
      return _coverChildren;
    }
    
    /**
     * Return whether incremental mode is selected
     */
    public boolean getIncrementalMode() {
      return _incrementalMode;
    }
    
    /**
     * Return whether merge impact must be shown
     */
    public boolean getShowImpact() {
      return _showImpact;
    }
    
    /**
     * Set whether differences on children must be covered
     */
    public void setCoverChildren(boolean newValue_p) {
      _coverChildren = newValue_p;
    }
    
    /**
     * Set whether incremental mode is selected
     */
    public void setIncrementalMode(boolean newValue_p) {
      _incrementalMode = newValue_p;
    }
    
    /**
     * Set whether merge impact must be shown
     */
    public void setShowImpact(boolean newValue_p) {
      _showImpact = newValue_p;
    }
  }
  
}
