/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - [439901] support multi-line in table item.
 *    Stephane Bouchet (Intel Corporation) - Bug #489137 : delegate tooltip of values viewer to proper label provider
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_TECHNICAL_LABELS;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of the values of a feature on a match.
 * Input: ValuesViewer.ValuesInput ;
 * Elements:
 *  if !showAllValues: [IValuePresence]
 *  if showAllValues: if feature instanceof EAttribute: [Object]
 *                    if feature instanceof EReference: [IMatch].
 * @author Olivier Constant
 */
public class ValuesViewer extends TableViewer implements IComparisonSideViewer,
IDifferenceRelatedViewer {
  
  /**
   * A simple structure for defining inputs for this viewer.
   */
  public static class ValuesInput {
    /** The non-null comparison context */
    private final EMFDiffNode _context;
    /** The non-null specific part */
    private final MatchAndFeature _matchAndFeature;
    /**
     * Constructor
     * @param context_p a non-null object
     * @param matchAndFeature_p a non-null object
     */
    public ValuesInput(EMFDiffNode context_p,
        MatchAndFeature matchAndFeature_p) {
      _context = context_p;
      _matchAndFeature = matchAndFeature_p;
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object_p) {
      boolean result = false;
      if (object_p instanceof ValuesInput) {
        ValuesInput peer = (ValuesInput)object_p;
        result = _context == peer.getContext() &&
          _matchAndFeature.getFeature() == peer.getMatchAndFeature().getFeature() &&
          _matchAndFeature.getMatch().equals(peer.getMatchAndFeature().getMatch());
      }
      return result;
    }
    /**
     * Return the comparison context
     * @return a non-null object
     */
    public EMFDiffNode getContext() {
      return _context;
    }
    /**
     * Return the match and feature
     * @return a non-null object
     */
    public MatchAndFeature getMatchAndFeature() {
      return _matchAndFeature;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return _context.hashCode() + _matchAndFeature.getMatch().hashCode() +
        _matchAndFeature.getFeature().hashCode();
    }
    /**
     * Return whether this input corresponds to a containment
     */
    public boolean isContainment() {
      return _context.isContainment(_matchAndFeature.getFeature());
    }
  }
  
  
  /** Whether the side of the viewer is left or right */
  private final boolean _sideIsLeft;
  
  /** Whether all values must be shown, including those not related to a difference */
  private boolean _showAllValues;
  
  /** A listener to changes on properties of the input */
  protected final IPropertyChangeListener _inputPropertyChangeListener;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param sideIsLeft_p whether the side is left or right
   */
  public ValuesViewer(Composite parent_p, boolean sideIsLeft_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, sideIsLeft_p);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   * @param sideIsLeft_p whether the side is left or right
   */
  public ValuesViewer(Composite parent_p, int style_p, boolean sideIsLeft_p) {
    super(parent_p, style_p);
    setContentProvider(new ContentProvider());
    setLabelProvider(new LabelProvider());
    _sideIsLeft = sideIsLeft_p;
    _showAllValues = false;
    _inputPropertyChangeListener = createInputPropertyChangeListener();
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
          refresh(true);
        }
      }
    };
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public ValuesInput getInput() {
    return (ValuesInput)super.getInput();
  }
  
  /**
   * Return the role that corresponds to the values being represented
   * @return a role which is null if and only if the input is null
   */
  protected Role getSideRole() {
    return getInput() == null? null:
      getInput().getContext().getRoleForSide(isLeftSide());
  }
  
  /**
   * @see org.eclipse.jface.viewers.StructuredViewer#getSelection()
   */
  @Override
  public IStructuredSelection getSelection() {
    return (IStructuredSelection)super.getSelection();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTableViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    if (oldInput_p instanceof ValuesInput) {
      ((ValuesInput)oldInput_p).getContext().removeUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    if (input_p instanceof ValuesInput) {
      ((ValuesInput)input_p).getContext().addUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    super.inputChanged(input_p, oldInput_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceRelatedViewer#isDifferenceAgnostic()
   */
  public boolean isDifferenceAgnostic() {
    return _showAllValues;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IComparisonSideViewer#isLeftSide()
   */
  public boolean isLeftSide() {
    return _sideIsLeft;
  }
  
  /**
   * Return whether the given input object represents the virtual ownership feature
   * @param object_p a potentially null object
   */
  protected boolean isOwnership(Object object_p) {
    boolean result = false;
    Object object = object_p;
    if (object instanceof ValuesInput)
      object = ((ValuesInput)object).getMatchAndFeature();
    if (object instanceof MatchAndFeature) {
      EStructuralFeature feature = ((MatchAndFeature)object).getFeature();
      result = EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature().equals(feature);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceRelatedViewer#setDifferenceAgnostic(boolean)
   */
  public void setDifferenceAgnostic(boolean agnostic_p) {
    if (agnostic_p != isDifferenceAgnostic()) {
      _showAllValues = agnostic_p;
      refresh(false);
    }
  }
  
  
  /**
   * The content provider for this viewer.
   */
  protected class ContentProvider implements IStructuredContentProvider {
    
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      ValuesInput valuesInput = (ValuesInput)inputElement_p;
      MatchAndFeature maf = valuesInput.getMatchAndFeature();
      Collection<Object> result = new ArrayList<Object>();
      if (isOwnership(maf)) {
        // Ownership
        IReferenceValuePresence ownership = maf.getMatch().getOwnershipDifference(getSideRole());
        if (ownership != null)
          result.add(ownership);
      } else {
        // Order
        IValuePresence orderDifference = maf.getMatch().getOrderDifference(
            maf.getFeature(), getSideRole());
        if (orderDifference != null)
          result.add(orderDifference);
        // Only show values if no containment
        if (!valuesInput.isContainment()) {
          if (isDifferenceAgnostic()) {
            // All feature values
            IMatch match = maf.getMatch();
            EObject source = match.get(getSideRole());
            if (source != null) {
              IComparison comparison = getInput() == null? null:
                getInput().getContext().getActualComparison();
              if (comparison != null) {
                if (maf.getFeature() instanceof EAttribute) {
                  EAttribute attribute = (EAttribute)maf.getFeature();
                  List<Object> values = comparison.getScope(getSideRole()).get(source, attribute);
                  for (Object value : values) {
                    IAttributeValuePresence presence =
                        match.getAttributeValueDifference(attribute, value);
                    if (presence != null)
                      result.add(presence);
                    else
                      result.add(value);
                  }
                } else {
                  EReference reference = (EReference)maf.getFeature();
                  List<EObject> values = comparison.getScope(getSideRole()).get(source, reference);
                  for (EObject value : values) {
                    IReferenceValuePresence presence =
                        match.getReferenceValueDifference(reference, value);
                    if (presence != null)
                      result.add(presence);
                    else
                      result.add(value);
                  }
                }
              }
            }
          } else {
            // Only differences
            Collection<? extends IValuePresence> bothSides;
            if (maf.getFeature() instanceof EAttribute)
              bothSides = maf.getMatch().getAttributeDifferences((EAttribute)maf.getFeature());
            else
              bothSides = maf.getMatch().getReferenceDifferences((EReference)maf.getFeature());
            for (IValuePresence presence : bothSides) {
              if (!presence.isOrder() && presence.getPresenceRole() == getSideRole() &&
                  presence.getMergeDestination() != getSideRole() ||
                  !presence.isOrder() && presence.getPresenceRole() == getSideRole().opposite() &&
                  presence.getMergeDestination() == getSideRole())
                result.add(presence);
            }
          }
        }
      }
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      // Nothing needed
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
      ValuesInput input = getInput();
      return input == null? null: input.getContext();
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getSide()
     */
    @Override
    protected Role getSide() {
      return getSideRole();
    }
    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      return getText(element_p);
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#isFromValue(org.eclipse.emf.diffmerge.api.diff.IValuePresence)
     */
    @Override
    protected boolean isFromValue(IValuePresence valuePresence_p) {
      return isOwnership(getInput());
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#isTextTechnicalForMeta()
     */
    @Override
    protected boolean isTextTechnicalForMeta() {
      return getInput() == null? false:
        getInput().getContext().isUserPropertyTrue(P_TECHNICAL_LABELS);
    }
  }
  
}
