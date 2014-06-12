/**
 * <copyright>
 * 
 * Copyright (c) 2014 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.TypedListener;


/**
 * A viewer which wraps another viewer in order to add a "header" section comprising
 * an optional image, text and tool bar.
 * @param <V> the type of the wrapped viewer
 * @author Olivier Constant
 */
public abstract class HeaderViewer<V extends Viewer> extends Viewer {
  
  /** The main control, non-null after createControls has been called */
  private Composite _control;
  
  /** The wrapped viewer, non-null after createControls has been called */
  private V _innerViewer;
  
  /** The optional image control */
  private Label _image;
  
  /** The optional text control */
  private Label _text;
  
  /** The tool bar, non-null after createControls has been called */
  private ToolBar _toolbar;
  
  
  /**
   * Constructor
   */
  public HeaderViewer() {
    super();
    // createControls(Composite) must be called explicitly
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    getInnerViewer().addSelectionChangedListener(listener_p);
  }
  
  /**
   * Add an SWT selection listener to the control of the wrapped viewer
   * @param listener_p a non-null listener
   */
  public void addSWTSelectionListener(SelectionListener listener_p) {
    TypedListener typedListener = new TypedListener (listener_p);
    Control innerControl = getInnerViewer().getControl();
    innerControl.addListener(SWT.Selection, typedListener);
    innerControl.addListener(SWT.DefaultSelection, typedListener);
  }
  
  /**
   * Create all controls
   * @param parent_p a non-null composite
   */
  protected void createControls(Composite parent_p) {
    // Main controls
    _control = UIUtil.createComposite(parent_p);
    // Header
    Composite headerComposite = new Composite(_control, SWT.NONE);
    headerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    headerComposite.setLayout(layout);
    // Inner viewer
    _innerViewer = createInnerViewer(_control);
    // Header content
    createHeaderContent(headerComposite);
  }
  
  /**
   * Create the content of the header control
   * @param parent_p a non-null composite
   */
  protected void createHeaderContent(Composite parent_p) {
    boolean isToolBarOnTheRight = isToolBarOnTheRight();
    // Text and image
    if (!isToolBarOnTheRight)
      _toolbar = createToolBar(parent_p);
    Composite textImageComposite = new Composite(parent_p, SWT.NONE);
    textImageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    GridLayout nameHeaderLayout = new GridLayout(3, false);
    nameHeaderLayout.marginTop = nameHeaderLayout.marginHeight;
    nameHeaderLayout.marginHeight = 0;
    nameHeaderLayout.marginWidth = 0;
    textImageComposite.setLayout(nameHeaderLayout);
    _image = createImageLabel(textImageComposite);
    _text = createTextLabel(textImageComposite);
    if (isToolBarOnTheRight) {
      Label space = new Label(textImageComposite, SWT.WRAP);
      space.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
      _toolbar = createToolBar(parent_p);
    }
  }
  
  /**
   * Create and return the control that holds the header image, if any
   * @param parent_p a non-null composite
   * @return a potentially null control
   */
  protected Label createImageLabel(Composite parent_p) {
    Label result = new Label(parent_p, SWT.NONE);
    result.setBackground(UIUtil.getColor(SWT.COLOR_WHITE));
    return result;
  }
  
  /**
   * Create and return the inner viewer. It must provide IStructuredSelections.
   * @see IStructuredSelection
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected abstract V createInnerViewer(Composite parent_p);
  
  /**
   * Create and return the control that holds the header text, if any
   * @param parent_p a non-null composite
   * @return a potentially null control
   */
  protected Label createTextLabel(Composite parent_p) {
    Label result = new Label(parent_p, SWT.NONE);
    result.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    return result;
  }
  
  /**
   * Create and return the tool bar
   * @param parent_p a non-null composite
   * @return a non-null tool bar
   */
  protected ToolBar createToolBar(Composite parent_p) {
    return UIUtil.createToolBar(parent_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Control getControl() {
    return _control;
  }
  
  /**
   * Return the control that holds the header image for this viewer, if any
   * @return a potentially null control
   */
  public Label getImageLabel() {
    return _image;
  }
  
  /**
   * Return the wrapped viewer
   */
  public V getInnerViewer() {
    return _innerViewer;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public Object getInput() {
    return getInnerViewer().getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public IStructuredSelection getSelection() {
    return (IStructuredSelection)getInnerViewer().getSelection();
  }
  
  /**
   * Return the control that holds the header text for this viewer, if any
   * @return a potentially null control
   */
  public Label getTextLabel() {
    return _text;
  }
  
  /**
   * Return the tool bar of this viewer
   * @return a non-null tool bar
   */
  public ToolBar getToolbar() {
    return _toolbar;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    _innerViewer.setInput(input_p);
  }
  
  /**
   * Return whether the tool bar must be on the right-hand side of the header
   */
  protected boolean isToolBarOnTheRight() {
    return true;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _innerViewer.refresh();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  @Override
  public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
    getInnerViewer().removeSelectionChangedListener(listener_p);
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
    _innerViewer.setSelection(selection_p, reveal_p);
  }
  
}
