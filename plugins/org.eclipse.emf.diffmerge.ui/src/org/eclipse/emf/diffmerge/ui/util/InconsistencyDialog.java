/**
 * <copyright>
 * 
 * Copyright (c) 2013-2014 Thales Global Services S.A.S.
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

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


/**
 * A dialog for indicating the inconsistencies of a comparison.
 * @author Olivier Constant
 */
public class InconsistencyDialog extends MessageDialog {
  
  /** The non-null comparison */
  protected final IComparison _comparison;
  
  /**
   * Constructor
   * @param shell_p a non-null shell
   * @param comparison_p a non-null comparison
   */
  public InconsistencyDialog(Shell shell_p, IComparison comparison_p) {
    super(shell_p, EMFDiffMergeUIPlugin.LABEL, null,
        Messages.InconsistencyDialog_DuplicateIDs,
        MessageDialog.WARNING,
        new String[] { IDialogConstants.OK_LABEL }, 0);
    _comparison = comparison_p;
    setShellStyle(getShellStyle() | SWT.RESIZE);
  }
  
  /**
   * Copy the selection of the given viewer into the clipboard, as text
   * @param viewer_p a non-null viewer
   */
  protected void copySelectionAsText(final StructuredViewer viewer_p) {
    ISelection selection = viewer_p.getSelection();
    if (selection != null && selection instanceof IStructuredSelection &&
        !selection.isEmpty()) {
      IBaseLabelProvider blp = viewer_p.getLabelProvider();
      if (blp instanceof ILabelProvider) {
        ILabelProvider lp = (ILabelProvider)blp;
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Object selected : ((IStructuredSelection)selection).toList()) {
          if (selected != null) {
            String label = lp.getText(selected);
            if (label != null) {
              if (first)
                first = false;
              else
                builder.append('\n');
              builder.append(label);
            }
          }
        }
        copyTextToClipboard(builder.toString(), viewer_p.getControl().getDisplay());
      }
    }
  }
  
  /**
   * Copy the given text into the clipboard for the given display
   * @param text_p a non-null string
   * @param display_p a non-null display
   */
  protected void copyTextToClipboard(String text_p, Display display_p) {
    Clipboard clipboard = new Clipboard(display_p);
    Transfer[] transfers = new Transfer[]{TextTransfer.getInstance()};
    Object[] data = new Object[] {text_p};
    clipboard.setContents(data, transfers);
    clipboard.dispose();
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createCustomArea(final Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.NONE);
    result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    result.setLayout(new GridLayout(1, false));
    for (Role role : Role.values()) {
      Collection<Object> duplicates = _comparison.getDuplicateMatchIDs(role);
      if (!duplicates.isEmpty())
        createDuplicateArea(result, role, duplicates);
    }
    return result;
  }
  
  /**
   * Create and return a menu item for copying the labels in the given viewer
   * @param viewer_p a non-null viewer
   * @return a non-null menu item
   */
  protected MenuItem createCopyMenuItem(final StructuredViewer viewer_p) {
    Menu menu = new Menu(viewer_p.getControl());
    final MenuItem result = new MenuItem(menu, SWT.PUSH);
    result.setText(Messages.InconsistencyDialog_CopyID);
    result.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_COPY));
    viewer_p.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection selection = event_p.getSelection();
        boolean enable = selection instanceof IStructuredSelection &&
            !selection.isEmpty();
        result.setEnabled(enable);
      }
    });
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        copySelectionAsText(viewer_p);
      }
    });
    viewer_p.getControl().setMenu(menu);
    return result;
  }
  
  /**
   * Create and return the area where duplicates for the given role as provided in
   * the given collection are displayed
   * @param parent_p a non-null composite
   * @param role_p a non-null role
   * @param duplicates_p a non-null set of objects
   */
  protected Control createDuplicateArea(Composite parent_p,
      Role role_p, Collection<Object> duplicates_p) {
    Group group = new Group(parent_p, SWT.NONE);
    group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    group.setLayout(new GridLayout(1, false));
    String scopeName = (role_p == Role.ANCESTOR)?
        Messages.InconsistencyDialog_AncestorScope: (role_p == Role.REFERENCE)?
            Messages.InconsistencyDialog_ReferenceScope: Messages.InconsistencyDialog_TargetScope;
    group.setText(scopeName);
    StructuredViewer viewer = new ListViewer(
        group, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.MULTI);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setSorter(new ViewerSorter());
    createCopyMenuItem(viewer);
    viewer.setInput(duplicates_p.toArray());
    return group;
  }
  
}
