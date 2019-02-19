/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - Bug #489142 : use the delegate label provider to display text
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_TECHNICAL_LABELS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of the features of a match.
 * Input: FeaturesViewer.FeaturesInput ; Elements: MatchAndFeature.
 * @author Olivier Constant
 */
public class FeaturesViewer extends TableViewer implements IDifferenceRelatedViewer {
  
  /**
   * A simple structure for defining inputs for this viewer.
   */
  public static class FeaturesInput {
    /** The non-null comparison context */
    private final EMFDiffNode _context;
    /** The non-null specific part */
    private final IMatch<?> _match;
    /**
     * Constructor
     * @param context_p a non-null object
     * @param match_p a non-null object
     */
    public FeaturesInput(EMFDiffNode context_p, IMatch<?> match_p) {
      _context = context_p;
      _match = match_p;
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object_p) {
      boolean result = false;
      if (object_p instanceof FeaturesInput) {
        FeaturesInput peer = (FeaturesInput)object_p;
        result = _context == peer.getContext() &&
          _match.equals(peer.getMatch());
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
     * Return the match
     * @return a non-null object
     */
    public IMatch<?> getMatch() {
      return _match;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return _context.hashCode() + _match.hashCode();
    }
  }
  
  
  /** Whether all features must be shown, including those with no difference */
  private boolean _showAllFeatures;
  
  /** A listener to changes on properties of the input */
  protected final IPropertyChangeListener _inputPropertyChangeListener;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public FeaturesViewer(Composite parent_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   */
  public FeaturesViewer(Composite parent_p, int style_p) {
    super(parent_p, style_p);
    setContentProvider(new ContentProvider());
    setLabelProvider(new LabelProvider());
    _showAllFeatures = false;
    _inputPropertyChangeListener = createInputPropertyChangeListener();
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    setComparator(new ViewerComparator());
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
   * Return the first 'match and feature' to show for the given input, if any
   * @param input_p a potentially null input object
   * @return the first match and feature to show, or null if none
   */
  public MatchAndFeature getFirstIn(FeaturesInput input_p) {
    MatchAndFeature result = null;
    if (input_p != null) {
      Object[] elements = getSortedChildren(input_p);
      if (elements != null && elements.length > 0) {
        Object firstElement = elements[0];
        if (firstElement instanceof MatchAndFeature)
          result = (MatchAndFeature)firstElement;
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public FeaturesInput getInput() {
    return (FeaturesInput)super.getInput();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTableViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    if (oldInput_p instanceof FeaturesInput) {
      ((FeaturesInput)oldInput_p).getContext().removeUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    if (input_p instanceof FeaturesInput) {
      ((FeaturesInput)input_p).getContext().addUserPropertyChangeListener(
          P_TECHNICAL_LABELS, _inputPropertyChangeListener);
    }
    super.inputChanged(input_p, oldInput_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceRelatedViewer#isDifferenceAgnostic()
   */
  public boolean isDifferenceAgnostic() {
    return _showAllFeatures;
  }
  
  /**
   * Return whether the given object represents the virtual ownership feature
   * @param object_p a potentially null object
   */
  protected boolean isOwnershipFeature(Object object_p) {
    return EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature().equals(object_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceRelatedViewer#setDifferenceAgnostic(boolean)
   */
  public void setDifferenceAgnostic(boolean agnostic_p) {
    if (agnostic_p != isDifferenceAgnostic()) {
      _showAllFeatures = agnostic_p;
      refresh(false);
    }
  }
  
  
  /**
   * The content provider for this viewer.
   */
  protected class ContentProvider implements IStructuredContentProvider {
    /**
     * Return a list of all the relevant attributes for the given match
     * @param match_p a non-null match
     * @return a non-null, potentially empty, modifiable list
     */
    protected List<Object> getAllAttributes(IMatch<?> match_p) {
      Role elementSide = getInput().getContext().getDrivingRole();
      Object element = match_p.get(elementSide);
      if (element == null) {
        elementSide = elementSide.opposite();
        element = match_p.get(elementSide);
      }
      assert element != null; // An IMatch may not have null elements for both roles
      ITreeDataScope<?> sideScope =
          getInput().getContext().getActualComparison().getScope(elementSide);
      @SuppressWarnings({ "unchecked", "rawtypes" })
      List<Object> result = ((IScopePolicy)sideScope.getScopePolicy()).getAttributes(element);
      return result;
    }
    /**
     * Return a list of all the relevant references for the given match
     * @param match_p a non-null match
     * @return a non-null, potentially empty, modifiable list
     */
    protected List<Object> getAllReferences(IMatch<?> match_p) {
      Role elementSide = getInput().getContext().getDrivingRole();
      Object element = match_p.get(elementSide);
      if (element == null) {
        elementSide = elementSide.opposite();
        element = match_p.get(elementSide);
      }
      assert element != null; // An IMatch may not have null elements for both roles
      ITreeDataScope<?> sideScope =
          getInput().getContext().getActualComparison().getScope(elementSide);
      @SuppressWarnings({ "unchecked", "rawtypes" })
      List<Object> candidates = ((IScopePolicy)sideScope.getScopePolicy()).getReferences(element);
      List<Object> result = new LinkedList<Object>();
      for (Object candidate : candidates) {
        if (qualifies(candidate) || match_p.getReferenceOrderDifference(candidate, elementSide) != null) {
          result.add(candidate);
        }
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      List<MatchAndFeature> result = Collections.emptyList();
      FeaturesInput input = getInput();
      if (input != null) {
        EMFDiffNode context = input.getContext();
        Role drivingRole = context.getDrivingRole();
        IMatch<?> match = ((FeaturesInput)inputElement_p).getMatch();
        result = new ArrayList<MatchAndFeature>();
        // Attributes
        List<Object> attributes;
        if (isDifferenceAgnostic()) {
          attributes = getAllAttributes(match);
        } else {
          attributes = new ArrayList<Object>(match.getAttributesWithDifferences());
        }
        for (Object attribute : attributes) {
          MatchAndFeature maf = new MatchAndFeatureImpl(match, attribute, true);
          result.add(maf);
        }
        // References
        List<Object> references;
        if (isDifferenceAgnostic()) {
          references = getAllReferences(match);
        } else {
          references = new ArrayList<Object>();
          for (Object reference : match.getReferencesWithDifferences()) {
            if (!context.isContainment(reference) ||
                match.getReferenceOrderDifference(reference, drivingRole) != null) {
              references.add(reference);
            }
          }
        }
        if (getInput().getContext().getCategoryManager().representAsMove(match)) {
          references.add(EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature());
        }
        for (Object reference : references) {
          MatchAndFeature maf = new MatchAndFeatureImpl(match, reference, false);
          result.add(maf);
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
    /**
     * Return whether the given reference may be shown
     * @param reference_p a non-null reference
     */
    protected boolean qualifies(Object reference_p) {
      return isOwnershipFeature(reference_p) ||
        !getInput().getContext().isContainment(reference_p) &&
        !getInput().getContext().isContainer(reference_p);
    }
  }
  
  
  /**
   * The label provider for this viewer
   */
  protected class LabelProvider extends DiffDecoratingLabelProvider {
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getDiffNode()
     */
    @Override
    protected EMFDiffNode getDiffNode() {
      return getInput() == null? null: getInput().getContext();
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getSide()
     */
    @Override
    protected Role getSide() {
      return null;
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
