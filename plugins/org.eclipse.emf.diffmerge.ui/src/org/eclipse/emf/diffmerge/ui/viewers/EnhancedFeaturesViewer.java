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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.FeaturesViewer.FeaturesInput;
import org.eclipse.emf.ecore.EObject;
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
    result.setText(getDefaultText());
    return result;
  }
  
  /**
   * Return context-sensitive text for the header
   * @param input_p a non-null input object
   * @return a potentially null string
   */
  protected String getContextualText(FeaturesInput input_p) {
    EObject element = getDrivingElement(input_p);
    String formattedTypeText = input_p.getContext().usesTechicalLabels()?
        element.eClass().getName(): UIUtil.getFormattedTypeText(element);
    String result = String.format(
        Messages.EnhancedFeaturesViewer_DetailsWithSelection, formattedTypeText);
    return result;
  }
  
  /**
   * Return the default text for the header
   * @return a potentially null string
   */
  protected String getDefaultText() {
    return Messages.ComparisonViewer_Details;
  }
  
  /**
   * Return the element to represent for the given input
   * @param input_p a non-null input
   * @return a non-null element
   */
  protected EObject getDrivingElement(FeaturesInput input_p) {
    IMatch match = input_p.getMatch();
    Role drivingRole = input_p.getContext().getDrivingRole();
    EObject element = match.get(drivingRole);
    if (element == null)
      element = match.get(drivingRole.opposite());
    return element;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#getInput()
   */
  @Override
  public FeaturesInput getInput() {
    return (FeaturesInput)super.getInput();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    Label textLabel = getTextLabel();
    if (textLabel != null && !textLabel.isDisposed()) {
      String newText;
      if (input_p instanceof FeaturesInput) {
        FeaturesInput input = (FeaturesInput)input_p;
        newText = getContextualText(input);
      } else {
        newText = getDefaultText();
      }
      textLabel.setText(newText);
    }
    super.inputChanged(input_p, oldInput_p);
  }
  
}
