/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - Bug #489142 : use the delegate label provider to display text
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of the features of a match.
 * Input: FeatureViewer.FeaturesInput ; Elements: EStructuralFeature.
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
    private final IMatch _match;
    /**
     * Constructor
     * @param context_p a non-null object
     * @param match_p a non-null object
     */
    public FeaturesInput(EMFDiffNode context_p, IMatch match_p) {
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
    public IMatch getMatch() {
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
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    setSorter(new ViewerSorter());
  }
  
  /**
   * Return the first feature to show for the given input, if any
   * @param input_p a potentially null input object
   * @return the first feature to show, or null if none
   */
  public EStructuralFeature getFirstIn(FeaturesInput input_p) {
    EStructuralFeature result = null;
    if (input_p != null) {
      Object[] elements = getSortedChildren(input_p);
      if (elements != null && elements.length > 0) {
        Object firstElement = elements[0];
        if (firstElement instanceof EStructuralFeature)
          result = (EStructuralFeature)firstElement;
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
   * Return the resource manager for this viewer
   * @return a resource manager which is non-null iff input is not null
   */
  protected ComparisonResourceManager getResourceManager() {
    return getInput() == null? null: getInput().getContext().getResourceManager();
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
   * The content provider for this viewer
   */
  protected class ContentProvider implements IStructuredContentProvider {
    
    /**
     * Return a list of all the relevant features for the given match
     * @param match_p a non-null match
     * @return a non-null, potentially empty, modifiable list
     */
    private List<EStructuralFeature> getAllFeatures(IMatch match_p) {
      Role drivingRole = getInput().getContext().getDrivingRole();
      EObject element = match_p.get(drivingRole);
      if (element == null)
        element = match_p.get(drivingRole.opposite());
      assert element != null; // An IMatch may not have null elements for both roles
      EClass eClass = element.eClass();
      List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
      result.addAll(eClass.getEAllAttributes());
      for (EReference ref : eClass.getEAllReferences()) {
        if (qualifies(ref) || match_p.getOrderDifference(ref, drivingRole) != null)
          result.add(ref);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      Role drivingRole = getInput().getContext().getDrivingRole();
      IMatch match = ((FeaturesInput)inputElement_p).getMatch();
      List<EStructuralFeature> result;
      if (isDifferenceAgnostic())
        result = getAllFeatures(match);
      else {
        result = new ArrayList<EStructuralFeature>(match.getAttributesWithDifferences());
        for (EReference ref : match.getReferencesWithDifferences()) {
          if (!ref.isContainment() || match.getOrderDifference(ref, drivingRole) != null)
            result.add(ref);
        }
      }
      if (getInput().getContext().getCategoryManager().representAsMove(match))
        result.add(EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature());
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
    private boolean qualifies(EReference reference_p) {
      return isOwnershipFeature(reference_p) ||
        !reference_p.isContainment() && !reference_p.isContainer() &&
        reference_p.isChangeable() && !reference_p.isDerived();
    }
  }
  
  
  /**
   * The label provider for this viewer
   */
  protected class LabelProvider extends DelegatingLabelProvider {
    
    /** An adapter factory for Ecore elements */
    private final EcoreItemProviderAdapterFactory _ecoreAdapterFactory;
    
    /**
     * Constructor
     */
    public LabelProvider() {
      super(DiffMergeLabelProvider.getInstance());
      _ecoreAdapterFactory = new EcoreItemProviderAdapterFactory();
    }
    
    /**
     * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
      super.dispose();
      _ecoreAdapterFactory.dispose();
    }
    
    /**
     * Return the difference kind that corresponds to the given feature for the current input
     * @param feature_p a non-null feature
     * @return a non-null kind
     */
    protected DifferenceKind getDifferenceKind(EStructuralFeature feature_p) {
      DifferenceKind result = DifferenceKind.NONE;
      if (getInput() != null) {
        EMatch match = (EMatch)getInput().getMatch();
        MatchAndFeature maf = new MatchAndFeatureImpl(match, feature_p);
        result = getInput().getContext().getCategoryManager().getDifferenceKind(maf);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getFont(java.lang.Object)
     */
    @Override
    public Font getFont(Object element_p) {
      Font result = getControl().getFont();
      EStructuralFeature feature = (EStructuralFeature)element_p;
      DifferenceKind kind = getDifferenceKind(feature);
      if (!kind.isNeutral())
        result = UIUtil.getBold(result);
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      EStructuralFeature feature = (EStructuralFeature)element_p;
      DifferenceKind kind = getDifferenceKind(feature);
      DifferenceColorKind colorKind = EMFDiffMergeUIPlugin.getDefault().getDifferenceColorKind(kind);
      if (colorKind == DifferenceColorKind.NONE)
        colorKind = DifferenceColorKind.DEFAULT;
      Color result = getInput().getContext().getDifferenceColor(colorKind);
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      Image result = null;
      if (isOwnershipFeature(element_p)) {
        result = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.TREE);
      } else {
        IItemLabelProvider provider = (IItemLabelProvider)_ecoreAdapterFactory.adapt(
            element_p, IItemLabelProvider.class);
        if (provider != null) {
          Object rawImage = provider.getImage(element_p);
          if (rawImage != null)
            result = ExtendedImageRegistry.getInstance().getImage(rawImage);
        }
      }
      if (getInput().getContext().usesCustomIcons() && element_p instanceof EStructuralFeature) {
        EStructuralFeature feature = (EStructuralFeature)element_p;
        DifferenceKind kind = getDifferenceKind(feature);
        result = getResourceManager().adaptImage(result, kind);
      }
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      EStructuralFeature feature = (EStructuralFeature)element_p;
      String result = getDelegate().getText(element_p);
      if (getInput().getContext().usesCustomLabels()) {
        DifferenceKind kind = getDifferenceKind(feature);
        String prefix = EMFDiffMergeUIPlugin.getDefault().getDifferencePrefix(kind);
        result = prefix + result;
      }
      return result;
    }
  }
  
}
