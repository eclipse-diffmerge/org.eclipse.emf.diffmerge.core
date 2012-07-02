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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;



/**
 * A viewer which provides a representation of the values of a feature on a match.
 * Input: ValuesViewer.Input ; Elements: [IValuePresence (if !showAllValues)] or
 * [[Object (if feature instanceof EAttribute)] or
 * [IMatch (if feature instanceof EReference)] (if showAllValues)].
 * @author Olivier Constant
 */
public class ValuesViewer extends TableViewer {
  
  /**
   * A simple structure for defining inputs
   */
  public static class ValuesInput {
    /** The non-null comparison context */
    private final ModelComparisonDiffNode _context;
    /** The non-null specific part */
    private final MatchAndFeature _matchAndFeature;
    /**
     * Constructor
     * @param context_p a non-null object
     * @param matchAndFeature_p a non-null object
     */
    public ValuesInput(ModelComparisonDiffNode context_p,
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
    public ModelComparisonDiffNode getContext() {
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
  }
  
  
  /** The non-null role to which the values to show belong */
  protected final Role _role;
  
  /** The non-null role that drives the representation */
  protected Role _drivingRole;
  
  /** Whether all values must be shown, including those not related to a difference */
  private boolean _showAllValues;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param role_p a non-null role
   */
  public ValuesViewer(Composite parent_p, Role role_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, role_p);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   * @param role_p a non-null role
   */
  public ValuesViewer(Composite parent_p, int style_p, Role role_p) {
    super(parent_p, style_p);
    setContentProvider(new ContentProvider());
    setLabelProvider(new LabelProvider());
    _role = role_p;
    _showAllValues = false;
    _drivingRole = Role.TARGET;
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  }
  
  /**
   * Return the model element that corresponds to the given viewer value, if applicable
   * @param viewerElement_p a potentially null object
   * @return a potentially null element
   */
  public EObject getElementForValue(Object viewerValueElement_p) {
    EObject result;
    if (viewerValueElement_p instanceof IReferenceValuePresence)
      result = (EObject)getValueToRepresent((IValuePresence)viewerValueElement_p);
    else if (viewerValueElement_p instanceof IMatch)
      result = ((IMatch)viewerValueElement_p).get(getRole());
    else if (viewerValueElement_p instanceof EObject &&
        !(viewerValueElement_p instanceof IDifference))
      result = (EObject)viewerValueElement_p;
    else
      result = null;
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public ValuesInput getInput() {
    return (ValuesInput)super.getInput();
  }
  
  /**
   * Return the resource manager for this viewer
   * @return a resource manager which is non-null iff input is not null
   */
  protected ComparisonResourceManager getResourceManager() {
    return getInput() == null? null: getInput().getContext().getResourceManager();
  }
  
  /**
   * Return the role to which the values which are shown belong
   * @return a non-null role
   */
  public Role getRole() {
    return _role;
  }
  
  /**
   * @see org.eclipse.jface.viewers.StructuredViewer#getSelection()
   */
  @Override
  public IStructuredSelection getSelection() {
    return (IStructuredSelection)super.getSelection();
  }
  
  /**
   * Return the value object of the given value presence that should be represented
   * @param presence_p a non-null value presence
   * @return a non-null object
   */
  protected Object getValueToRepresent(IValuePresence presence_p) {
    Object result;
    if (presence_p.getFeature() instanceof EAttribute)
      result = presence_p.getValue();
    else
      if (isOwnership(getInput().getMatchAndFeature()))
        result = ((IReferenceValuePresence)presence_p).getElementMatch().get(presence_p.getPresenceRole());
      else
        result = ((IReferenceValuePresence)presence_p).getValue().get(presence_p.getPresenceRole());
    return result;
  }
  
  /**
   * Return whether the given input object represents a containment
   * @param object_p a potentially null object
   */
  protected boolean isContainment(Object object_p) {
    boolean result = false;
    if (object_p instanceof MatchAndFeature) {
      EStructuralFeature feature = ((MatchAndFeature)object_p).getFeature();
      result = feature instanceof EReference && ((EReference)feature).isContainment();
    }
    return result;
  }
  
  /**
   * Return whether the given input object represents the virtual ownership feature
   * @param object_p a potentially null object
   */
  protected boolean isOwnership(Object object_p) {
    boolean result = false;
    if (object_p instanceof MatchAndFeature) {
      EStructuralFeature feature = ((MatchAndFeature)object_p).getFeature();
      result = EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature().equals(feature);
    }
    return result;
  }
  
  /**
   * Return whether all values must be shown, including those unrelated to differences
   */
  public boolean mustShowAllValues() {
    return _showAllValues;
  }
  
  /**
   * Set the role that drives the representation
   * @param role_p a non-null role
   */
  public void setDrivingRole(Role role_p) {
    _drivingRole = role_p;
  }
  
  /**
   * Set whether all values must be shown, including those unrelated to differences
   * @param show_p whether all values must be shown
   */
  public void setShowAllValues(boolean show_p) {
    if (show_p != mustShowAllValues()) {
      _showAllValues = show_p;
      refresh(false);
    }
  }
  
  /**
   * Return whether the given object must be represented as a difference
   * @param element_p a potentially null object
   */
  protected boolean showAsDifference(Object element_p) {
    return element_p instanceof IValuePresence &&
      !((IValuePresence)element_p).isMerged() &&
      !getInput().getContext().shouldBeIgnored((IDifference)element_p);
  }
  
  
  /**
   * The content provider for this viewer
   */
  protected class ContentProvider implements IStructuredContentProvider {
    
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      MatchAndFeature input = ((ValuesInput)inputElement_p).getMatchAndFeature();
      Collection<Object> result = new ArrayList<Object>();
      if (isOwnership(input)) {
        // Ownership
        IReferenceValuePresence ownership = input.getMatch().getOwnershipDifference(getRole());
        if (ownership != null)
          result.add(ownership);
      } else {
        // Order
        IValuePresence orderDifference = input.getMatch().getOrderDifference(
            input.getFeature(), getRole());
        if (orderDifference != null)
          result.add(orderDifference);
        // Only show values if no containment
        if (!isContainment(input)) {
          if (mustShowAllValues()) {
            // All values
            if (input.getFeature() instanceof EAttribute) {
              EAttribute attribute = (EAttribute)input.getFeature();
              IComparison comparison = input.getMatch().getMapping().getComparison();
              IMatch match = input.getMatch();
              EObject source = match.get(getRole());
              if (source != null) {
                List<Object> values = comparison.getScope(getRole()).get(source, attribute);
                for (Object value : values) {
                  IAttributeValuePresence presence =
                    match.getAttributeValueDifference(attribute, value);
                  if (presence != null)
                    result.add(presence);
                  else
                    result.add(value);
                }
              }
            } else {
              EReference reference = (EReference)input.getFeature();
              IComparison comparison = input.getMatch().getMapping().getComparison();
              IMatch match = input.getMatch();
              EObject source = match.get(getRole());
              if (source != null) {
                List<EObject> values = comparison.getScope(getRole()).get(source, reference);
                for (EObject value : values) {
                  IMatch valueMatch = comparison.getMapping().getMatchFor(value, getRole());
                  if (valueMatch != null) {
                    IReferenceValuePresence presence =
                      match.getReferenceValueDifference(reference, valueMatch);
                    if (presence != null)
                      result.add(presence);
                    else
                      result.add(valueMatch);
                  }
                }
              }
            }
          } else {
            // Only differences
            Collection<? extends IValuePresence> bothSides;
            if (input.getFeature() instanceof EAttribute)
              bothSides = input.getMatch().getAttributeDifferences((EAttribute)input.getFeature());
            else
              bothSides = input.getMatch().getReferenceDifferences((EReference)input.getFeature());
            for (IValuePresence presence : bothSides) {
              if (!presence.isOrder() && presence.getPresenceRole() == getRole() &&
                  presence.getMergeDestination() != getRole() ||
                  !presence.isOrder() && presence.getPresenceRole() == getRole().opposite() &&
                  presence.getMergeDestination() == getRole())
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
   * The label provider for this viewer
   */
  protected class LabelProvider extends org.eclipse.jface.viewers.LabelProvider
  implements IFontProvider, IColorProvider {
    
    /** The non-null label provider for matched elements */
    private final ILabelProvider _innerProvider;
    
    /**
     * Constructor
     */
    public LabelProvider() {
      super();
      _innerProvider = DiffMergeLabelProvider.getInstance();
    }
    
    /**
     * Adapt the given label that describes the cross-reference of the given value
     * @param initialLabel_p a potentially null string
     * @param value_p a non-null element
     * @return a potentially null string
     */
    private String formatCrossReferenceValue(String initialLabel_p, EObject value_p) {
      StringBuilder builder = new StringBuilder();
      if (initialLabel_p != null)
        builder.append(initialLabel_p);
      EObject container = value_p.eContainer();
      String containerLabel = container == null? null:
        _innerProvider.getText(container);
      if (containerLabel != null) {
        builder.append(' ');
        builder.append(String.format(Messages.ValuesViewer_ContainerLabel, containerLabel));
      }
      return builder.toString();
    }
    /**
     * Adapt the given label of the owner of the given reference value presence so that it conveniently
     * describes a containment
     * @param ownerLabel_p a potentially null string
     * @param presence_p a non-null reference value presence
     * @return a potentially null string
     */
    private String formatOwnershipValue(String ownerLabel_p, IReferenceValuePresence presence_p) {
      StringBuilder builder = new StringBuilder();
      if (ownerLabel_p != null)
        builder.append(ownerLabel_p);
      EReference containment = presence_p.getFeature();
      if (containment != null) {
        builder.append(' ');
        builder.append(String.format(Messages.ValuesViewer_FeatureLabel, containment.getName()));
      }
      return builder.toString();
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(Object element_p) {
      return getControl().getBackground();
    }
    
    /**
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(Object element_p) {
      Font result = getControl().getFont();
      if (showAsDifference(element_p))
        result = UIUtil.getBold(result);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(Object element_p) {
      DifferenceColorKind result;
      if (showAsDifference(element_p)) {
        result = (getRole() == _drivingRole)? DifferenceColorKind.LEFT: DifferenceColorKind.RIGHT;
      } else {
        result = DifferenceColorKind.NONE;
      }
      return EMFDiffMergeUIPlugin.getDefault().getDifferenceColor(result);
    }
    
    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      Image result;
      if (element_p instanceof IValuePresence) {
        IValuePresence presence = (IValuePresence)element_p;
        if (presence.isOrder()) {
          result = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.SORT);
        } else {
          Object toRepresent = getValueToRepresent(presence);
          result = _innerProvider.getImage(toRepresent);
        }
        if (getInput().getContext().usesCustomIcons()) {
          DifferenceKind kind;
          if (isOwnership(getInput().getMatchAndFeature()) && !getInput().getContext().isThreeWay()) {
            kind = DifferenceKind.MODIFIED;
          } else {
            kind = getInput().getContext().getDifferenceKind(presence);
          }
          result = getResourceManager().adaptImage(result, kind);
        }
      } else if (element_p instanceof IMatch) {
        result = _innerProvider.getImage(((IMatch)element_p).get(getRole()));
      } else {
        result = _innerProvider.getImage(element_p);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      String result;
      if (element_p instanceof IValuePresence) {
        IValuePresence presence = (IValuePresence)element_p;
        if (presence.isOrder()) {
          result = Messages.ValuesViewer_OrderLabel;
        } else {
          Object toRepresent = getValueToRepresent(presence);
          result = _innerProvider.getText(toRepresent);
          if (isOwnership(getInput()))
            result = formatOwnershipValue(result, (IReferenceValuePresence)presence);
          else if (toRepresent instanceof EObject)
            result = formatCrossReferenceValue(result, (EObject)toRepresent);
        }
        if (getInput().getContext().usesCustomLabels()) {
          DifferenceKind kind = getInput().getContext().getDifferenceKind(presence);
          String prefix = EMFDiffMergeUIPlugin.getDefault().getDifferencePrefix(kind);
          result = prefix + result;
        }
      } else if (element_p instanceof IMatch) {
        result = _innerProvider.getText(((IMatch)element_p).get(getRole()));
      } else {
        result = _innerProvider.getText(element_p);
      }
      return result;
    }
  }
  
}
