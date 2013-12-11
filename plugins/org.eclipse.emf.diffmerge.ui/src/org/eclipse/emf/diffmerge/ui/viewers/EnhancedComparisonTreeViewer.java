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
 * A viewer which provides a representation of a comparison tree and additional controls.
 * Input: EMFDiffNode ; Elements: IMatch.
 * @author Olivier Constant
 */
public class EnhancedComparisonTreeViewer extends Viewer {
  
  /** The main control */
  protected Composite _control;
  
  /** The comparison tree viewer */
  protected ComparisonTreeViewer _comparisonViewer;
  
  /** The header for the tree viewer */
  protected Label _header;
  
  /** The tool bar */
  private ToolBar _toolbar;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public EnhancedComparisonTreeViewer(Composite parent_p) {
    super();
    createControls(parent_p); 
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    _comparisonViewer.addSelectionChangedListener(listener_p);
  }
  
  /**
   * Create all controls
   * @param parent_p a non-null composite
   */
  protected void createControls(Composite parent_p) {
    // Main controls
    _control = UIUtil.createComposite(parent_p);
    Composite headerComposite = new Composite(_control, SWT.NONE);
    headerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    _comparisonViewer = new ComparisonTreeViewer(_control);
    // Header controls
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    headerComposite.setLayout(layout);
    _header = new Label(headerComposite, SWT.NONE);
    _header.setFont(UIUtil.getBold(_header.getFont()));
    _header.setText(getDefaultHeaderText());
    _header.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    _toolbar = UIUtil.createToolBar(headerComposite);
  }
  
  /**
   * Return the inner comparison tree viewer
   * @return a non-null viewer
   */
  public ComparisonTreeViewer getComparisonTreeViewer() {
    return _comparisonViewer;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Control getControl() {
    return _control;
  }
  
  /**
   * Return the default text for the header
   * @return a non-null string
   */
  public String getDefaultHeaderText() {
    return Messages.EnhancedComparisonTreeViewer_DefaultHeader;
  }
  
  /**
   * Return the header widget of this viewer
   * @return a non-null
   */
  public Label getHeader() {
    return _header;
  }
  
  /**
   * Return the inner viewer which is solely dedicated to the representation
   * of the comparison tree
   * @return a viewer which is not null if this viewer has been properly initialized
   */
  public ComparisonTreeViewer getInnerViewer() {
    return _comparisonViewer;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return _comparisonViewer.getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return _comparisonViewer.getSelection();
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
    return _comparisonViewer.getTree();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    _comparisonViewer.setInput(input_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _comparisonViewer.refresh();
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
    _comparisonViewer.setSelection(selection_p, reveal_p);
  }
  
}
