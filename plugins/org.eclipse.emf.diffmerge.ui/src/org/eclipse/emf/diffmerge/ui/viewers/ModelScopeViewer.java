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

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;



/**
 * A viewer which provides a representation of a model scope.
 * Input: IModelScope ; Elements: EObject.
 * @author Olivier Constant
 */
public class ModelScopeViewer extends TreeViewer {
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public ModelScopeViewer(Composite parent_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   */
  public ModelScopeViewer(Composite parent_p, int style_p) {
    super(parent_p, style_p);
    setContentProvider(new ContentProvider());
    setLabelProvider(DiffMergeLabelProvider.getInstance());
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public IModelScope getInput() {
    return (IModelScope)super.getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTreeViewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return (ITreeSelection)super.getSelection();
  }
  
  
  /**
   * The content provider for this viewer
   */
  protected class ContentProvider implements ITreeContentProvider {
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement_p) {
      return getInput().getContents((EObject)parentElement_p).toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      return ((IModelScope)inputElement_p).getContents().toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      return getInput().getContainer((EObject)element_p);
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element_p) {
      return !getInput().getContents((EObject)element_p).isEmpty();
    }
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      // Nothing needed
    }
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
      // Nothing needed
    }
  }
  
}
