/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of a model scope in a comparison.
 * Input: EMFDiffNode ; Elements: EObject.
 * @author Olivier Constant
 */
public class ComparisonSideViewer extends TreeViewer implements IComparisonSideViewer {
  
  /** Whether the side of the viewer is left or right */
  private final boolean _sideIsLeft;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   */
  public ComparisonSideViewer(Composite parent_p, boolean isLeftSide_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, isLeftSide_p);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   * @param isLeftSide_p whether the side is left or right
   */
  public ComparisonSideViewer(Composite parent_p, int style_p, boolean isLeftSide_p) {
    super(parent_p, style_p);
    _sideIsLeft = isLeftSide_p;
    setContentProvider(new ContentProvider());
    setLabelProvider(new LabelProvider());
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getContentProvider()
   */
  @Override
  public ITreeContentProvider getContentProvider() {
    return (ITreeContentProvider)super.getContentProvider();
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return (EMFDiffNode)super.getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTreeViewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return (ITreeSelection)super.getSelection();
  }
  
  /**
   * Return the role that corresponds to the values being represented
   * @return a role which is null if and only if the input is null
   */
  protected Role getSideRole() {
    return getInput() == null? null:
      getInput().getRoleForSide(isLeftSide());
  }
  
  /**
   * Return the model scope represented by this viewer
   * @return a scope which is assumed non-null after setInput(Object) has been invoked
   */
  public IModelScope getSideScope() {
    return getInput() == null? null:
      getInput().getActualComparison().getScope(
          getSideRole());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IComparisonSideViewer#isLeftSide()
   */
  public boolean isLeftSide() {
    return _sideIsLeft;
  }
  
  
  /**
   * The content provider for this viewer.
   */
  protected class ContentProvider implements ITreeContentProvider {
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      // Nothing needed
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement_p) {
      EObject container = (EObject)parentElement_p;
      Object[] result;
      IModelScope scope = getSideScope();
      if (scope != null) {
        result = scope.getContents(container).toArray();
      } else {
        result = new Object[0];
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      Object[] result;
      IModelScope scope = getSideScope();
      if (scope != null) {
        result = scope.getContents().toArray();
      } else {
        result = new Object[0];
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      Object result = null;
      IModelScope scope = getSideScope();
      if (scope != null) {
        result = scope.getContainer((EObject)element_p);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element_p) {
      return getChildren(element_p).length > 0;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
      // Nothing needed
    }
  }
  
  
  /**
   * The label provider for this viewer.
   */
  protected class LabelProvider extends DiffDecoratingLabelProvider {
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getDiffNode()
     */
    @Override
    protected EMFDiffNode getDiffNode() {
      return getInput();
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getSide()
     */
    @Override
    protected Role getSide() {
      return getSideRole();
    }
  }
  
}
