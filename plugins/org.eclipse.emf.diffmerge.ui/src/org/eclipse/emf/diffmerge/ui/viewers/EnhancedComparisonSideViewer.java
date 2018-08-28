/**
 * <copyright>
 * 
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
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
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * A ComparisonSideViewer with a header.
 * Input, Elements: see ComparisonSideViewer.
 * @see ComparisonSideViewer
 * @author Olivier Constant
 */
public class EnhancedComparisonSideViewer extends HeaderViewer<ComparisonSideViewer> {
  
  /** Whether the viewer represents information on the left-hand side of a comparison */
  protected final boolean _isLeftSide;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   */
  public EnhancedComparisonSideViewer(Composite parent_p, boolean isLeftSide_p) {
    super();
    _isLeftSide = isLeftSide_p;
    createControls(parent_p); 
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createInnerViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected ComparisonSideViewer createInnerViewer(Composite parent_p) {
    return new ComparisonSideViewer(parent_p, _isLeftSide);
  }
  
  /**
   * Refresh this viewer ignoring the inner viewer
   */
  protected void doRefresh() {
    EMFDiffNode input = getInput();
    IModelScope scope = getInnerViewer().getSideScope();
    Label textLabel = getTextLabel();
    if (textLabel != null) {
      updateHeaderText(textLabel, input, scope);
    }
    Label imageLabel = getImageLabel();
    if (imageLabel != null) {
      updateHeaderImage(imageLabel, input, scope);
    }
  }
  
  /**
   * Return a label provider for header information
   * @return a non-null label provider
   */
  protected ILabelProvider getHeaderLabelProvider() {
    ILabelProvider result = null;
    if (getInnerViewer() != null) {
      // Use LP of inner viewer if available
      IBaseLabelProvider baseLP = getInnerViewer().getLabelProvider();
      if (baseLP instanceof ILabelProvider) {
        result = (ILabelProvider)baseLP;
      }
    }
    if (result == null) {
      result = DiffMergeLabelProvider.getInstance();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return (EMFDiffNode)super.getInput();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return getInnerViewer().getSelection();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    super.inputChanged(input_p, oldInput_p);
    doRefresh();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#refresh()
   */
  @Override
  public void refresh() {
    super.refresh();
    doRefresh();
  }
  
  /**
   * Update the given "header image" widget so as to reflect the given scope
   * of the given input
   * @param headerImageWidget_p a non-null widget
   * @param input_p a potentially null input object
   * @param scope_p a potentially null model scope
   */
  protected void updateHeaderImage(Label headerImageWidget_p,
      EMFDiffNode input_p, IModelScope scope_p) {
    headerImageWidget_p.setImage(getHeaderLabelProvider().getImage(scope_p));
  }
  
  /**
   * Update the given "header text" widget so as to reflect the given scope
   * of the given input
   * @param headerTextWidget_p a non-null widget
   * @param input_p a potentially null input object
   * @param scope_p a potentially null model scope
   */
  protected void updateHeaderText(Label headerTextWidget_p,
      EMFDiffNode input_p, IModelScope scope_p) {
    ILabelProvider lp = getHeaderLabelProvider();
    if (lp instanceof IColorProvider) {
      Color newColor = ((IColorProvider)lp).getForeground(scope_p);
      headerTextWidget_p.setForeground(newColor);
    }
    String label = lp.getText(scope_p);
    headerTextWidget_p.setText(label);
    headerTextWidget_p.setToolTipText(label);
  }
  
}
