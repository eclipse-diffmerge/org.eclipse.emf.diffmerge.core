/**
 * <copyright>
 * 
 * Copyright (c) 2017-2018 Intel Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - Bug # : initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * The textual dialog editor that displays the content of a feature to be
 * reviwed and optionnaly edited.
 * 
 * @author Stephane Bouchet (Intel Corporation)
 *
 */
public class TextMergerViewerDialog extends MessageDialog {

  /** the number of toolbar columns. */
  private static final int TOOLBAR_COLUMNS = 3;

  /** the default size of the window. */
  private static final Point DEFAULT_SIZE = new Point(800, 600);

  /** the diffNode element. */
  protected final EMFDiffNode diffNode;

  /** the match element. */
  protected final EMatch match;

  /** the feature containing the values to display on both sides. */
  protected final EStructuralFeature feature;

  /** the label provider. */
  private final ILabelProvider labelProvider = DiffMergeLabelProvider
      .getInstance();

  /** the compare configuration. */
  private final CompareConfiguration compareConfiguration = new CompareConfiguration();

  /** the ancestor model. */
  private final IEditableModelScope ancestor;

  /** the left model. */
  private final IEditableModelScope left;

  /** the right model. */
  private final IEditableModelScope right;

  /** the textViewer. */
  private TextMergeViewer textViewer;

  /** the diffNode wrapper. */
  private EMFDiffNodeWrapper viewerInput;

  /**
   * This Utility class wrapps a diffNode element to a texttual element that the
   * textviewer can handle.
   * 
   * @author Stephane Bouchet (Intel Corporation)
   *
   */
  class EMFDiffNodeWrapper extends DiffNode {

    /** the ancestor mergerContent. */
    private MergerContent ancestorMergerContent;

    /** the left mergerContent. */
    private MergerContent leftMergerContent;

    /** the right mergerContent. */
    private MergerContent rightMergerContent;

    /**
     * The constructor.
     * 
     * @param kind
     *          the difference Kind
     */
    public EMFDiffNodeWrapper(int kind) {
      super(kind);
    }

    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getAncestor()
     */
    @Override
    public ITypedElement getAncestor() {
      if (match.get(Role.ANCESTOR) != null) {
        ancestorMergerContent = new MergerContent(
            match.get(Role.ANCESTOR).eGet(feature), false);
      }
      return ancestorMergerContent;
    }

    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getLeft()
     */
    @Override
    public ITypedElement getLeft() {
      if (leftMergerContent == null) {
        leftMergerContent = new MergerContent(
            match.get(Role.TARGET).eGet(feature), diffNode.isEditable(true));
      }
      return leftMergerContent;
    }

    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getRight()
     */
    @Override
    public ITypedElement getRight() {
      if (rightMergerContent == null) {
        rightMergerContent = new MergerContent(
            match.get(Role.REFERENCE).eGet(feature),
            diffNode.isEditable(false));
      }
      return rightMergerContent;
    }

  }

  /**
   * The constructor.
   * 
   * @param parentShell
   *          the parent shell.
   * @param diffNode_p
   *          the diffNode element
   * @param match_p
   *          the match element
   * @param feature_p
   *          the feature containing the values to display
   */
  public TextMergerViewerDialog(Shell parentShell, EMFDiffNode diffNode_p,
      EMatch match_p, EStructuralFeature feature_p) {
    super(parentShell, null, null, null, 0,
        new String[] { "Merge", IDialogConstants.CANCEL_LABEL }, 0); //$NON-NLS-1$
    diffNode = diffNode_p;
    this.match = match_p;
    this.feature = feature_p;
    setShellStyle(getShellStyle() | SWT.RESIZE);
    ancestor = diffNode.getActualComparison().getScope(Role.ANCESTOR);
    left = diffNode.getActualComparison().getScope(Role.TARGET);
    right = diffNode.getActualComparison().getScope(Role.REFERENCE);
  }

  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell shell) {
    shell.setText("Compare Contents"); //$NON-NLS-1$
    shell.setSize(DEFAULT_SIZE);
    super.configureShell(shell);
  }

  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent) {
    GridLayout layout = new GridLayout();
    layout.verticalSpacing = 0;
    parent.setLayout(layout);
    final Composite toolbar = new Composite(parent, SWT.NONE);
    GridLayout toolbarLayout = new GridLayout(TOOLBAR_COLUMNS, false);
    toolbarLayout.marginHeight = 0;
    toolbarLayout.marginWidth = 0;
    toolbar.setLayout(toolbarLayout);
    toolbar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    Label labelImage = new Label(toolbar, SWT.NONE);
    labelImage.setImage(PlatformUI.getWorkbench().getSharedImages()
        .getImage(ISharedImages.IMG_OBJ_FILE));
    labelImage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    Label labelText = new Label(toolbar, SWT.TOP);
    labelText.setText("Text Compare"); //$NON-NLS-1$
    labelText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
    final Composite main = new Composite(parent, SWT.NONE);
    GridLayout mainLayout = new GridLayout();
    mainLayout.marginHeight = 2;
    mainLayout.marginWidth = 0;
    main.setLayout(mainLayout);
    main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    if (ancestor != null) {
      compareConfiguration.setAncestorLabel(labelProvider.getText(ancestor));
    }

    compareConfiguration.setLeftLabel(labelProvider.getText(left));
    compareConfiguration.setLeftEditable(!left.isReadOnly());

    compareConfiguration.setRightLabel(labelProvider.getText(right));
    compareConfiguration.setRightEditable(!right.isReadOnly());

    textViewer = new TextMergeViewer(main, SWT.BORDER, compareConfiguration) {
      private ToolBarManager toolBarManager;

      @Override
      protected IToolBarManager getToolBarManager(Composite parentComposite) {
        if (toolBarManager != null && toolBarManager.getControl() == null)
          return null;
        if (toolBarManager == null) {
          final ToolBar tb = new ToolBar(toolbar, SWT.FLAT);
          tb.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
          toolBarManager = new ToolBarManager(tb);
        }
        return toolBarManager;
      }

    };
    textViewer.setLabelProvider(labelProvider);

    ICompareInput input = new EMFDiffNodeWrapper(
        ancestor != null ? Differencer.CONFLICTING : Differencer.CHANGE);

    textViewer.setInput(input);

    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    data.horizontalSpan = 2;
    textViewer.getControl().setLayoutData(data);
    return main;
  }

  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
   */
  @Override
  protected void buttonPressed(int buttonId) {
    // save the mergedContent before closing, will be used afterward by
    // comparisonViewer
    if (buttonId == Window.OK) {
      textViewer.flush(new NullProgressMonitor());
      viewerInput = (EMFDiffNodeWrapper) textViewer.getInput();
    }
    super.buttonPressed(buttonId);
  }

  /**
   * Returns the viewer input.
   * 
   * @return the viewer input
   */
  protected EMFDiffNodeWrapper getViewerInput() {
    return viewerInput;
  }

}
