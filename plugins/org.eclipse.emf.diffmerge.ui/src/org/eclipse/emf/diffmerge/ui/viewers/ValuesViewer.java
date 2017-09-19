/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S and others.
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
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;


/**
 * A viewer which provides a representation of the values of a feature on a match.
 * Input: ValuesViewer.ValuesInput ;
 * Elements:
 *  if !showAllValues: [IValuePresence]
 *  if showAllValues: if feature instanceof EAttribute: [Object]
 *                    if feature instanceof EReference: [IMatch].
 * @author Olivier Constant
 */
public class ValuesViewer extends TableViewer implements IComparisonSideViewer, IDifferenceRelatedViewer {
  
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
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    setMultilineSupport(getControl());
    getTable().setToolTipText(""); //$NON-NLS-1$
    ColumnViewerToolTipSupport.enableFor(this, ToolTip.NO_RECREATE);
  }
  
  /**
   * Return the model element that corresponds to the given viewer value, if applicable
   * @param viewerValueElement_p a potentially null object
   * @return a potentially null element
   */
  public EObject getElementForValue(Object viewerValueElement_p) {
    EObject result;
    if (viewerValueElement_p instanceof IReferenceValuePresence)
      result = (EObject)getValueToRepresent((IValuePresence)viewerValueElement_p);
    else if (viewerValueElement_p instanceof IMatch)
      result = ((IMatch)viewerValueElement_p).get(getSideRole());
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
   * Return the value object of the given value presence that should be represented
   * @param presence_p a non-null value presence
   * @return a non-null object
   */
  protected Object getValueToRepresent(IValuePresence presence_p) {
    Object result;
    if (presence_p.getFeature() instanceof EAttribute) {
      result = presence_p.getValue();
    } else {
      IReferenceValuePresence presence = (IReferenceValuePresence)presence_p;
      Role presenceRole = presence.getPresenceRole();
      if (isOwnership(getInput().getMatchAndFeature())) {
        result = presence.getElementMatch().get(presenceRole);
      } else {
        result = presence.getValue();
      }
    }
    return result;
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
   * Add multi-line support for long strings to the table of this viewer.
   * See http://git.eclipse.org/c/platform/eclipse.platform.swt.git/tree/examples/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet231.java for reference. 
   * @param control_p the non-null control (presumably a table) to which multi-line support must be added
   */
  protected void setMultilineSupport(Control control_p) {
    control_p.addListener(SWT.MeasureItem, new Listener() {
      /**
       * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
       */
      public void handleEvent(Event event_p) {
        TableItem item = (TableItem)event_p.item;
        if (item != null) {
          String text = item.getText(event_p.index);
          Point size = event_p.gc.textExtent(text);
          event_p.width = size.x + item.getImageBounds(0).width + 4;
          event_p.height = size.y;
        }
      }
    });
    control_p.addListener(SWT.EraseItem, new Listener() {
      /**
       * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
       */
      public void handleEvent(Event event_p) {
        event_p.detail &= ~SWT.FOREGROUND;
      }
    });
    control_p.addListener(SWT.PaintItem, new Listener() {
      /**
       * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
       */
      public void handleEvent(Event event_p) {
        TableItem item = (TableItem)event_p.item;
        if (item != null) {
          String text = item.getText(event_p.index);
          Point size = (text != null)? event_p.gc.textExtent(text): new Point(0,0);
          event_p.width = size.x + 8;
          event_p.height = Math.max(event_p.height, size.y);
          // Based on the offset before the image, draw image and text
          int offset = event_p.x;
          Image image = item.getImage();
          if (image != null)
            event_p.gc.drawImage(image, offset, event_p.y);
          if (text != null)
            event_p.gc.drawText(text, offset + item.getImageBounds(0).width + 4, event_p.y, true);
        }
      }
    });
  }
  
  /**
   * Return whether the given object must be represented as a difference
   * @param element_p a potentially null object
   */
  protected boolean showAsDifference(Object element_p) {
    return element_p instanceof IValuePresence &&
      !getInput().getContext().getCategoryManager().isFiltered((IDifference)element_p);
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
            // All values
            if (maf.getFeature() instanceof EAttribute) {
              // All attribute values
              EAttribute attribute = (EAttribute)maf.getFeature();
              IComparison comparison = maf.getMatch().getMapping().getComparison();
              IMatch match = maf.getMatch();
              EObject source = match.get(getSideRole());
              if (source != null) {
                List<Object> values = comparison.getScope(getSideRole()).get(source, attribute);
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
              // All reference values
              EReference reference = (EReference)maf.getFeature();
              IComparison comparison = maf.getMatch().getMapping().getComparison();
              IMatch match = maf.getMatch();
              EObject source = match.get(getSideRole());
              if (source != null) {
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
  protected class LabelProvider extends DelegatingLabelProvider {

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
        getDelegate().getText(container);
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
        String containmentText = UIUtil.getFormattedFeatureText(containment);
        builder.append(String.format(Messages.ValuesViewer_FeatureLabel, containmentText));
      }
      return builder.toString();
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getFont(java.lang.Object)
     */
    @Override
    public Font getFont(Object element_p) {
      Font result = getControl().getFont();
      if (showAsDifference(element_p))
        result = UIUtil.getBold(result);
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      DifferenceColorKind result;
      if (showAsDifference(element_p)) {
        result = (getSideRole() == getInput().getContext().getDrivingRole())?
            DifferenceColorKind.LEFT: DifferenceColorKind.RIGHT;
      } else {
        result = DifferenceColorKind.DEFAULT;
      }
      return getInput().getContext().getDifferenceColor(result);
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getImage(java.lang.Object)
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
          result = getDelegate().getImage(toRepresent);
        }
        if (getInput().getContext().usesCustomIcons()) {
          DifferenceKind kind;
          if (isOwnership(getInput().getMatchAndFeature()) &&
              getInput().getContext().getReferenceRole() == null) {
            kind = DifferenceKind.MODIFIED;
          } else {
            kind = getInput().getContext().getCategoryManager().getDifferenceKind(presence);
          }
          result = getResourceManager().adaptImage(result, kind);
        }
      } else if (element_p instanceof IMatch) {
        result = getDelegate().getImage(((IMatch)element_p).get(getSideRole()));
      } else {
        result = getDelegate().getImage(element_p);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getText(java.lang.Object)
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
          result = getDelegate().getText(toRepresent);
          if (isOwnership(getInput()))
            result = formatOwnershipValue(result, (IReferenceValuePresence)presence);
          else if (toRepresent instanceof EObject)
            result = formatCrossReferenceValue(result, (EObject)toRepresent);
        }
        if (getInput().getContext().usesCustomLabels()) {
          DifferenceKind kind = getInput().getContext().getCategoryManager().getDifferenceKind(presence);
          String prefix = EMFDiffMergeUIPlugin.getDefault().getDifferencePrefix(kind);
          result = prefix + result;
        }
      } else if (element_p instanceof IMatch) {
        result = getDelegate().getText(((IMatch)element_p).get(getSideRole()));
      } else {
        result = getDelegate().getText(element_p);
      }
      return result;
    }

    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      return getText(element_p);
    }

  }
  
}
