/*********************************************************************
 * Copyright (c) 2014-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * A ComparisonTreeViewer with a header.
 * Input, Elements: see ComparisonTreeViewer.
 * @see ComparisonTreeViewer
 * @author Olivier Constant
 */
public class EnhancedComparisonTreeViewer extends HeaderViewer<ComparisonTreeViewer> {
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public EnhancedComparisonTreeViewer(Composite parent_p) {
    super();
    createControls(parent_p); 
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createHeaderContent(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createHeaderContent(Composite parent_p) {
    super.createHeaderContent(parent_p);
    setHeaderText(getDefaultHeaderText());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createImageLabel(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Label createImageLabel(Composite parent_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createInnerViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected ComparisonTreeViewer createInnerViewer(Composite parent_p) {
    return new ComparisonTreeViewer(parent_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createTextLabel(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Label createTextLabel(Composite parent_p) {
    Label result = super.createTextLabel(parent_p);
    result.setFont(UIUtil.getBold(result.getFont()));
    return result;
  }
  
  /**
   * Return the default text for the header
   * @return a non-null string
   */
  public String getDefaultHeaderText() {
    return Messages.EnhancedComparisonTreeViewer_DefaultHeader;
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
    return (ITreeSelection)super.getSelection();
  }
  
}
