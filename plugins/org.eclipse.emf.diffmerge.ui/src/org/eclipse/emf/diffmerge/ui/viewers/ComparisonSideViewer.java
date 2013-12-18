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

import java.util.List;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of a model scope in a comparison.
 * Input: EMFDiffNode ; Elements: EMatch.
 * @author Olivier Constant
 */
public class ComparisonSideViewer extends TreeViewer {
  
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
   * Return the color that identifies the side of this viewer
   * @return a potentially null color
   */
  public Color getSideColor() {
    return getInput() == null? null:
      getInput().getDifferenceColor(
        isLeftSide()? DifferenceColorKind.LEFT: DifferenceColorKind.RIGHT);
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
          getInput().getRoleForSide(isLeftSide()));
  }
  
  /**
   * Return whether the side of this viewer is left or right
   * @return a non-null role
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
      IMatch match = (IMatch)parentElement_p;
      List<IMatch> result = getInput().getActualComparison().getContentsOf(
          match, getSideRole());
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      EMFDiffNode input = (EMFDiffNode)inputElement_p;
      List<IMatch> result = input.getActualComparison().getContents(
          getSideRole());
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      IMatch match = (IMatch)element_p;
      return getInput().getActualComparison().getContainerOf(match, getSideRole());
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
  protected class LabelProvider extends DelegatingLabelProvider {
    
    /**
     * Return the element to represent for the given match
     * @param match_p a non-null match
     * @return a non-null element
     */
    private EObject getElementToRepresent(IMatch match_p) {
      EObject result;
      Role sideRole = getSideRole();
      if (match_p.getUncoveredRole() == sideRole)
        result = match_p.get(sideRole.opposite());
      else
        result = match_p.get(sideRole);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      EMatch match = (EMatch)element_p;
      DifferenceKind kind = getInput().getDifferenceKind(match);
      Color result;
      if (!kind.isNeutral())
        result = getSideColor();
      else
        result = getInput().getDifferenceColor(DifferenceColorKind.NONE);
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      IMatch match = (IMatch)element_p;
      Image result = getDelegate().getImage(getElementToRepresent(match));
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      IMatch match = (IMatch)element_p;
      String result = getDelegate().getText(getElementToRepresent(match));
      return result;
    }
  }
  
}
