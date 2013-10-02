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

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;



/**
 * A viewer which provides a representation of a model scope in a comparison
 * and additional controls.
 * Input: ModelComparisonDiffNode ; Elements: EMatch.
 * @author Olivier Constant
 */
public class EnhancedComparisonSideViewer extends Viewer {
  
  /** The main control */
  protected Composite _control;
  
  /** The comparison side viewer */
  protected ComparisonSideViewer _comparisonSideViewer;
  
  /** The image for the model tree */
  protected Label _image;
  
  /** The text for the model tree */
  protected Label _text;
  
  /** The tool bar */
  protected ToolBar _toolbar;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   */
  public EnhancedComparisonSideViewer(Composite parent_p, boolean isLeftSide_p) {
    super();
    createControls(parent_p, isLeftSide_p); 
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    _comparisonSideViewer.addSelectionChangedListener(listener_p);
  }
  
  /**
   * Create all controls
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   */
  protected void createControls(Composite parent_p, boolean isLeftSide_p) {
    // Main controls
    _control = UIUtil.createComposite(parent_p);
    Composite header = new Composite(_control, SWT.NONE);
    header.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    _comparisonSideViewer = new ComparisonSideViewer(_control, isLeftSide_p);
    // Header controls
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    header.setLayout(layout);
    Composite nameHeader = new Composite(header, SWT.NONE);
    nameHeader.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    GridLayout nameHeaderLayout = new GridLayout(3, false);
    nameHeaderLayout.marginTop = nameHeaderLayout.marginHeight;
    nameHeaderLayout.marginHeight = 0;
    nameHeaderLayout.marginWidth = 0;
    nameHeader.setLayout(nameHeaderLayout);
    _image = new Label(nameHeader, SWT.NONE);
    _image.setBackground(UIUtil.getColor(SWT.COLOR_WHITE));
    _text = new Label(nameHeader, SWT.NONE);
    Label space = new Label(nameHeader, SWT.WRAP);
    space.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    _toolbar = UIUtil.createToolBar(header);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Control getControl() {
    return _control;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public ModelComparisonDiffNode getInput() {
    return _comparisonSideViewer.getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return _comparisonSideViewer.getSelection();
  }
  
  /**
   * Return the tool bar of this viewer
   * @return a non-null tool bar
   */
  public ToolBar getToolbar() {
    return _toolbar;
  }
  
  /**
   * Return the model tree
   * @return a non-null tree
   */
  public Tree getTree() {
    return _comparisonSideViewer.getTree();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    _comparisonSideViewer.setInput(input_p);
    IModelScope scope = _comparisonSideViewer.getSideScope();
    String label = DiffMergeLabelProvider.getInstance().getText(scope);
    _text.setText(label);
    _text.setToolTipText(label);
    _text.setForeground(_comparisonSideViewer.getSideColor());
    _image.setImage(DiffMergeLabelProvider.getInstance().getImage(scope));
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _comparisonSideViewer.refresh();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public final void setInput(Object input_p) {
    Object oldInput = getInput();
    inputChanged(input_p, oldInput);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    _comparisonSideViewer.setSelection(selection_p, reveal_p);
  }
  
}
