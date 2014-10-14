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

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProvider;
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
   * Return a label provider for header information
   * @return a non-null label provider
   */
  protected LabelProvider getHeaderLabelProvider() {
    return DiffMergeLabelProvider.getInstance();
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
    IModelScope scope = getInnerViewer().getSideScope();
    String label = getHeaderLabelProvider().getText(scope);
    Label textLabel = getTextLabel();
    textLabel.setText(label);
    textLabel.setToolTipText(label);
    textLabel.setForeground(getInnerViewer().getSideColor());
    getImageLabel().setImage(getHeaderLabelProvider().getImage(scope));
  }
  
}
