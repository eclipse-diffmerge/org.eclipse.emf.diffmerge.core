/*********************************************************************
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
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

import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_TECHNICAL_LABELS;

import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.FeaturesViewer.FeaturesInput;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * A Features Viewer with a header.
 * Input, Elements: see FeaturesViewer.
 * @see FeaturesViewer
 * @author Olivier Constant
 */
public class EnhancedFeaturesViewer extends HeaderViewer<FeaturesViewer> {
  
  /** A listener to changes on properties of the input */
  protected final IPropertyChangeListener _inputPropertyChangeListener;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public EnhancedFeaturesViewer(Composite parent_p) {
    super();
    _inputPropertyChangeListener = createInputPropertyChangeListener();
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
   * Create and return a listener to changes on properties of the input
   * @return a non-null object
   */
  protected IPropertyChangeListener createInputPropertyChangeListener() {
    return new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (P_TECHNICAL_LABELS.matches(event_p)) {
          updateTextLabel();
        }
      }
    };
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
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected String getContextualText(FeaturesInput input_p) {
    Object element = getDrivingElement(input_p);
    boolean useTechnicalLabels =
        input_p.getContext().isUserPropertyTrue(P_TECHNICAL_LABELS);
    String formattedTypeText;
    if (element instanceof EObject) {
      if (useTechnicalLabels) {
        EClass type = ((EObject)element).eClass();
        formattedTypeText = type.getEPackage().getName() + '.' + type.getName();
      } else {
        formattedTypeText = UIUtil.getFormattedTypeText((EObject)element);
      }
    } else {
      EMFDiffNode node = input_p.getContext();
      IDataScope drivingScope = 
          node.getActualComparison().getScope(node.getDrivingRole());
      Object type = drivingScope.mGetType(element);
      ILabelProvider labelProvider = getLabelProvider();
      formattedTypeText = labelProvider.getText(type);
    }
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
  protected Object getDrivingElement(FeaturesInput input_p) {
    IMatch<?> match = input_p.getMatch();
    Role drivingRole = input_p.getContext().getDrivingRole();
    Object element = match.get(drivingRole);
    if (element == null) {
      element = match.get(drivingRole.opposite());
    }
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
    super.inputChanged(input_p, oldInput_p);
    if (oldInput_p instanceof FeaturesInput) {
      ((FeaturesInput)oldInput_p).getContext().removeUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    if (input_p instanceof FeaturesInput) {
      ((FeaturesInput)input_p).getContext().addUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    updateTextLabel();
  }
  
  /**
   * Update the header text according to the current input
   */
  protected void updateTextLabel() {
    Label textLabel = getTextLabel();
    if (textLabel != null && !textLabel.isDisposed()) {
      FeaturesInput input = getInput();
      String newText;
      if (input != null) {
        newText = getContextualText(input);
      } else {
        newText = getDefaultText();
      }
      textLabel.setText(newText);
    }
  }
  
}
