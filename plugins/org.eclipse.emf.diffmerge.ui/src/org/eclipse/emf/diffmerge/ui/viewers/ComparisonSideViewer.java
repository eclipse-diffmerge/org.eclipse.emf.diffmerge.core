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
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
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
 * Input: ModelComparisonDiffNode ; Elements: EMatch.
 * @author Olivier Constant
 */
public class ComparisonSideViewer extends TreeViewer {
  
  /** The non-null role of the scope being represented */
  protected Role _sideRole;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param sideRole_p the non-null role of the scope being represented
   */
  public ComparisonSideViewer(Composite parent_p, Role sideRole_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, sideRole_p);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   * @param sideRole_p the non-null role of the scope being represented
   */
  public ComparisonSideViewer(Composite parent_p, int style_p, Role sideRole_p) {
    super(parent_p, style_p);
    _sideRole = sideRole_p;
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
  public ModelComparisonDiffNode getInput() {
    return (ModelComparisonDiffNode)super.getInput();
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
    return EMFDiffMergeUIPlugin.getDefault().getDifferenceColor(
        _sideRole == Role.TARGET? DifferenceColorKind.LEFT: DifferenceColorKind.RIGHT);
  }
  
  /**
   * Return the model scope represented by this viewer
   * @return a scope which is assumed non-null after setInput(Object) has been invoked
   */
  public IModelScope getSideScope() {
    return getInput() == null? null:
      getInput().getActualComparison().getScope(_sideRole);
  }
  
  
  /**
   * The content provider for this viewer
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
      List<IMatch> result = getInput().getActualComparison().getContentsOf(match, _sideRole);
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      ModelComparisonDiffNode input = (ModelComparisonDiffNode)inputElement_p;
      List<IMatch> result = input.getActualComparison().getContents(_sideRole);
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      IMatch match = (IMatch)element_p;
      return getInput().getActualComparison().getContainerOf(match, _sideRole);
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
   * The label provider for this viewer
   */
  protected class LabelProvider extends DelegatingLabelProvider {
    
    /**
     * Constructor
     */
    public LabelProvider() {
      super(DiffMergeLabelProvider.getInstance());
    }
    
    /**
     * Return the element to represent for the given match
     * @param match_p a non-null match
     * @return a non-null element
     */
    private EObject getElementToRepresent(IMatch match_p) {
      EObject result;
      if (match_p.getUncoveredRole() == _sideRole)
        result = match_p.get(_sideRole.opposite());
      else
        result = match_p.get(_sideRole);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      EMatch match = (EMatch)element_p;
      Color result;
      if (getInput().getDifferenceNumber(match) > 0)
        result = getSideColor();
      else
        result = EMFDiffMergeUIPlugin.getDefault().getDifferenceColor(DifferenceColorKind.NONE);
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
