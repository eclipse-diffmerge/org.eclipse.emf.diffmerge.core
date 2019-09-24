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

import org.eclipse.emf.diffmerge.ui.viewers.ValuesViewer.ValuesInput;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * A Values Viewer with a header.
 * Input, Elements: see ValuesViewer.
 * @see ValuesViewer
 * @author Olivier Constant
 */
public class EnhancedValuesViewer extends HeaderViewer<ValuesViewer> {
  
  /** Whether the viewer represents information on the left-hand side of a comparison */
  protected final boolean _isLeftSide;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   */
  public EnhancedValuesViewer(Composite parent_p, boolean isLeftSide_p) {
    super();
    _isLeftSide = isLeftSide_p;
    createControls(parent_p); 
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
  protected ValuesViewer createInnerViewer(Composite parent_p) {
    return new ValuesViewer(parent_p, _isLeftSide);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createTextLabel(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Label createTextLabel(Composite parent_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#getInput()
   */
  @Override
  public ValuesInput getInput() {
    return (ValuesInput)super.getInput();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#isToolBarOnTheRight()
   */
  @Override
  protected boolean isToolBarOnTheRight() {
    return _isLeftSide;
  }
  
}
