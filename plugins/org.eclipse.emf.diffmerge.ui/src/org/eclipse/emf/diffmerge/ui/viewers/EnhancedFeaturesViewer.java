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

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.FeaturesViewer.FeaturesInput;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * A Features Viewer with a header.
 * Input, Elements: see FeaturesViewer.
 * @see FeaturesViewer
 * @author Olivier Constant
 */
public class EnhancedFeaturesViewer extends HeaderViewer<FeaturesViewer> {
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public EnhancedFeaturesViewer(Composite parent_p) {
    super();
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
  protected FeaturesViewer createInnerViewer(Composite parent_p) {
    return new FeaturesViewer(parent_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#createTextLabel(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Label createTextLabel(Composite parent_p) {
    Label result = super.createTextLabel(parent_p);
    result.setFont(UIUtil.getBold(result.getFont()));
    result.setText(Messages.ComparisonViewer_Details);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#getInput()
   */
  @Override
  public FeaturesInput getInput() {
    return (FeaturesInput)super.getInput();
  }
  
}
