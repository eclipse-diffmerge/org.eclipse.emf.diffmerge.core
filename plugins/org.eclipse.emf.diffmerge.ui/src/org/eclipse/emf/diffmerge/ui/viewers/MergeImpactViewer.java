/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.impl.helpers.AbstractExpensiveOperation;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.DefaultDiffLabelDecorator;
import org.eclipse.emf.diffmerge.ui.util.DefaultDiffLabelDecorator.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;


/**
 * A viewer for showing the impact of a merge.
 * Input: MergeImpactViewer.Input ; Elements: IDifference.
 * @author Olivier Constant
 */
public class MergeImpactViewer extends Viewer {
  
  /**
   * The type of accepted inputs.
   */
  public static class ImpactInput {
    
    /** Whether modifications occur on the left-hand side or the right-hand side */
    private final boolean _sideIsLeft;
    
    /** The non-null comparison context */
    private final EMFDiffNode _context_p;
    
    /** The non-null, unmodifiable, potentially empty set of differences to merge */
    private final Collection<IDifference<?>> _toMerge;
    
    /** Whether this input has processed its internal data */
    private boolean _isComputed;
    
    /** The non-null map from matches to differences to merge implicitly */
    protected final EMap<IMatch<?>, Collection<IDifference<?>>> _implicitImpact;
    
    /** The non-null map from matches to differences to merge explicitly */
    protected final EMap<IMatch<?>, Collection<IDifference<?>>> _explicitImpact;
    
    /**
     * The operation that computes the internal data of the input
     */
    protected class ComputationOperation extends AbstractExpensiveOperation {
      /**
       * @see AbstractExpensiveOperation#getOperationName()
       */
      public String getOperationName() {
        return Messages.MergeImpactViewer_ComputationName;
      }
      /**
       * @see AbstractExpensiveOperation#run()
       */
      public IStatus run() {
        Collection<IDifference<?>> toMerge = getDifferencesToMerge();
        for (IDifference<?> anyToMerge : toMerge) {
          if (anyToMerge instanceof IMergeableDifference) {
            IMergeableDifference<?> difference = (IMergeableDifference<?>)anyToMerge;
            registerDifference(difference, true);
            for (IDifference<?> required : difference.getRequiresDependencies(getDestination())) {
              if (!required.isMerged() && !toMerge.contains(required))
                registerDifference(required, false);
            }
            for (IDifference<?> implied : difference.getImpliesDependencies(getDestination())) {
              if (!implied.isMerged() && !toMerge.contains(implied))
                registerDifference(implied, false);
            }
            checkProgress();
            getMonitor().worked(1);
          }
        }
        return Status.OK_STATUS;
      }
      /**
       * @see AbstractExpensiveOperation#getWorkAmount()
       */
      @Override
      protected int getWorkAmount() {
        return getDifferencesToMerge().size();
      }
      /**
       * Register the given difference
       * @param difference_p a non-null difference
       * @param explicit_p whether its merge is explicit or implicit
       */
      protected void registerDifference(IDifference<?> difference_p, boolean explicit_p) {
        // Getting relevant match
        IMatch<?> match = null;
        if (difference_p instanceof IElementRelativeDifference) {
          IElementRelativeDifference<?> elementDifference = (IElementRelativeDifference<?>)difference_p;
          if (elementDifference instanceof IReferenceValuePresence) {
            IReferenceValuePresence<?> presence = (IReferenceValuePresence<?>)elementDifference;
            if (presence.isOwnership()) {
              match = presence.getValueMatch(); // Move
            }
          }
          if (match == null) {
            match = elementDifference.getElementMatch();
          }
        }
        // Registering difference on match
        if (match != null) {
          EMap<IMatch<?>, Collection<IDifference<?>>> map = explicit_p? _explicitImpact: _implicitImpact;
          Collection<IDifference<?>> differences = map.get(match);
          if (differences == null) {
            differences = new FHashSet<IDifference<?>>();
            map.put(match, differences);
          }
          differences.add(difference_p);
        }
      }
    }
    
    /**
     * Constructor
     * @param toMerge_p the non-null set of differences to merge
     * @param toLeft_p whether modifications occur on the left-hand side or the right-hand side
     * @param context_p the non-null comparison context
     */
    public ImpactInput(Collection<? extends IDifference<?>> toMerge_p, boolean toLeft_p,
        EMFDiffNode context_p) {
      _toMerge = new FHashSet<IDifference<?>>(toMerge_p, null);
      _sideIsLeft = toLeft_p;
      _context_p = context_p;
      _isComputed = false;
      _explicitImpact = new FHashMap<IMatch<?>, Collection<IDifference<?>>>();
      _implicitImpact = new FHashMap<IMatch<?>, Collection<IDifference<?>>>();
    }
    
    /**
     * Process the internal data of this input
     * @param monitor_p an optional monitor
     */
    public void compute(IProgressMonitor monitor_p) {
      ComputationOperation op = new ComputationOperation();
      op.run(monitor_p);
      _isComputed = true;
    }
    
    /**
     * Return the comparison context for this input
     * @return a non-null object
     */
    public EMFDiffNode getContext() {
      return _context_p;
    }
    
    /**
     * Return the destination of the merge
     * @return a non-null role which is TARGET or REFERENCE
     */
    public Role getDestination() {
      return _context_p.getRoleForSide(_sideIsLeft);
    }
    
    /**
     * Return the set of differences to merge
     * @return a non-null, unmodifiable, potentially empty set
     */
    public Collection<IDifference<?>> getDifferencesToMerge() {
      return _toMerge;
    }
    
    /**
     * Return the map from matches to related differences to merge
     * @param explicit_p whether the differences are those to merge explicitly or implicitly
     * @return a non-null, potentially empty, unmodifiable map
     */
    public EMap<IMatch<?>, Collection<IDifference<?>>> getImpact(boolean explicit_p) {
      return ECollections.unmodifiableEMap(explicit_p? _explicitImpact: _implicitImpact);
    }
    
    /**
     * Return whether this input has processed its internal data
     */
    public boolean isComputed() {
      return _isComputed;
    }
    
    /**
     * Return whether modifications occur on the left-hand side or the right-hand side
     */
    public boolean isOnTheLeft() {
      return _sideIsLeft;
    }
    
    /**
     * Return whether the comparison is 3-way
     */
    public boolean isThreeWay() {
      return _context_p.getActualComparison().isThreeWay();
    }
  }
  
  
  /** The current input (initially null) */
  private ImpactInput _input;
  
  /** The non-null resource manager for SWT resources in this viewer */
  protected final ComparisonResourceManager _resourceManager;
  
  /** The main control of the viewer */
  protected SashForm _control;
  
  /** The upper tree viewer */
  protected TreeViewer _upperViewer;
  
  /** The lower tree viewer */
  protected TreeViewer _lowerViewer;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param resourceManager_p a non-null resource manager for SWT resources
   */
  public MergeImpactViewer(Composite parent_p, ComparisonResourceManager resourceManager_p) {
    super();
    _input = null;
    _resourceManager = resourceManager_p;
    createControls(parent_p);
  }
  
  /**
   * Create the controls for this viewer and return the main control
   * @param parent_p a non-null composite
   */
  protected void createControls(Composite parent_p) {
    _control = new SashForm(parent_p, SWT.VERTICAL);
    _control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Upper section
    Group upperWrapper = new Group(_control, SWT.NONE);
    upperWrapper.setText(Messages.MergeImpactViewer_Required);
    upperWrapper.setLayout(new GridLayout());
    _upperViewer = new TreeViewer(upperWrapper);
    _upperViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Lower section
    Group lowerWrapper = new Group(_control, SWT.NONE);
    lowerWrapper.setText(Messages.MergeImpactViewer_Implied);
    lowerWrapper.setLayout(new GridLayout());
    _lowerViewer = new TreeViewer(lowerWrapper);
    _lowerViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    setupViewers();
//    _control.setWeights(new int[] {2, 1});
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Control getControl() {
    return _control;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public ImpactInput getInput() {
    return _input;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ISelection getSelection() {
    return null;
  }
  
  /**
   * Return the shell of this viewer
   * @return a non-null shell
   */
  protected Shell getShell() {
    return getControl().getShell();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    _upperViewer.setInput(input_p);
    _lowerViewer.setInput(input_p);
    _upperViewer.expandAll();
    _lowerViewer.expandAll();
  }
  
  /**
   * Return whether merging the given difference would conflict with non-merged
   * opposite changes
   * @param difference_p a non-null difference
   */
  protected boolean isConflicting(IDifference<?> difference_p) {
    boolean result = false;
    if (getInput() != null && getInput().isThreeWay()) {
      if (difference_p instanceof IPresenceDifference) {
        IPresenceDifference<?> presence = (IPresenceDifference<?>)difference_p;
        result = !presence.isAlignedWithAncestor() &&
          presence.getPresenceRole() == getInput().getDestination();
      }
    }
    return result;
  }
  
  /**
   * Return whether the given match is concerned with conflicting differences
   * @param match_p a non-null match
   */
  protected boolean isConflicting(IMatch<?> match_p) {
    Object[] upperChildren = ((ITreeContentProvider)_upperViewer.getContentProvider()).getChildren(match_p);
    for (Object child : upperChildren) {
      if (child instanceof IDifference &&
          isConflicting((IDifference<?>)child))
        return true;
    }
    Object[] lowerChildren = ((ITreeContentProvider)_lowerViewer.getContentProvider()).getChildren(match_p);
    for (Object child : lowerChildren) {
      if (child instanceof IDifference &&
          isConflicting((IDifference<?>)child))
        return true;
    }
    return false;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _upperViewer.refresh();
    _lowerViewer.refresh();
  }
  
  /**
   * Set the "base" label provider for representing model elements
   * @param labelProvider_p a potentially null label provider, where null stands for default
   */
  public void setDelegateLabelProvider(ILabelProvider labelProvider_p) {
    ((DelegatingLabelProvider)_upperViewer.getLabelProvider()).setDelegate(labelProvider_p); // Same on both viewers
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    if (input_p instanceof ImpactInput) {
      Object oldInput = getInput();
      _input = (ImpactInput)input_p;
      if (!_input.isComputed())
        _input.compute(null);
      inputChanged(_input, oldInput);
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    // Nothing
  }
  
  /**
   * Setup the viewers that compose this viewer
   */
  protected void setupViewers() {
    // Content provider
    _upperViewer.setContentProvider(new MergeImpactContentProvider(true));
    _lowerViewer.setContentProvider(new MergeImpactContentProvider(false));
    // Label provider
    _upperViewer.setLabelProvider(new MergeImpactLabelProvider());
    _lowerViewer.setLabelProvider(new MergeImpactLabelProvider());
    // Sorter
    ViewerComparator sorter = new ViewerComparator();
    _upperViewer.setComparator(sorter);
    _lowerViewer.setComparator(sorter);
  }
  
  
  /**
   * The content provider for the tree viewers
   */
  protected static class MergeImpactContentProvider implements ITreeContentProvider {
    
    /** Whether impact is on explicit or implicit dependencies */
    private final boolean _isOnExplicit;
    
    /** The current input */
    private ImpactInput _input;
    
    /**
     * Constructor
     * @param explicit_p whether impact is on explicit or implicit dependencies
     */
    public MergeImpactContentProvider(boolean explicit_p) {
      _isOnExplicit = explicit_p;
      _input = null;
    }
    
    /**
     * @see ITreeContentProvider#dispose()
     */
    public void dispose() {
      _input = null;
    }
    
    /**
     * @see ITreeContentProvider#getChildren(Object)
     */
    public Object[] getChildren(Object parentElement_p) {
      Object[] result = new Object[] {};
      if (parentElement_p instanceof IMatch) {
        Collection<IDifference<?>> subDifferences = getImpact().get(parentElement_p);
        if (subDifferences != null) {
          List<IDifference<?>> filteredDifferences = new ArrayList<IDifference<?>>(
              subDifferences.size());
          for (IDifference<?> difference : subDifferences) {
            if (!isMoveOfAdded(difference))
              filteredDifferences.add(difference);
          }
          result = filteredDifferences.toArray();
        }
      }
      return result;
    }
    
    /**
     * Return whether the given difference represents the move of an element
     * which has also been added
     * @param difference_p a non-null difference
     */
    private boolean isMoveOfAdded(IDifference<?> difference_p) {
      boolean result = false;
      if (difference_p instanceof IReferenceValuePresence) {
        IReferenceValuePresence<?> valuePresence = (IReferenceValuePresence<?>)difference_p;
        if (valuePresence.isOwnership()) {
          IMatch<?> valueMatch = valuePresence.getValueMatch();
          result = valueMatch != null && valueMatch.getElementPresenceDifference() != null;
        }
      }
      return result;
    }
    
    /**
     * @see ITreeContentProvider#getElements(Object)
     */
    public Object[] getElements(Object inputElement_p) {
      Object[] result;
      if (inputElement_p instanceof ImpactInput)
        result = ((ImpactInput)inputElement_p).getImpact(_isOnExplicit).keySet().toArray();
      else
        result = new Object[] {};
      return result;
    }
    
    /**
     * Return the relevant impact map
     * @return a non-null impact map
     */
    private EMap<IMatch<?>, Collection<IDifference<?>>> getImpact() {
      EMap<IMatch<?>, Collection<IDifference<?>>> result = ECollections.emptyEMap();
      if (_input != null) {
        result = _input.getImpact(_isOnExplicit);
      }
      return result;
    }
    
    /**
     * @see ITreeContentProvider#getParent(Object)
     */
    public Object getParent(Object element_p) {
      return null; // Disables setSelection()
    }
    
    /**
     * @see ITreeContentProvider#hasChildren(Object)
     */
    public boolean hasChildren(Object element_p) {
      return getChildren(element_p).length > 0;
    }
    
    /**
     * @see ITreeContentProvider#inputChanged(Viewer, Object, Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
      if (newInput_p instanceof ImpactInput)
        _input = (ImpactInput)newInput_p;
    }
  }
  
  
  /**
   * The label provider for the tree viewers
   */
  protected class MergeImpactLabelProvider extends DelegatingLabelProvider {
    
    /**
     * Constructor
     */
    public MergeImpactLabelProvider() {
      super(DiffMergeLabelProvider.getInstance());
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getDelegate()
     */
    @Override
    public DiffMergeLabelProvider getDelegate() {
      return (DiffMergeLabelProvider)super.getDelegate();
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      DifferenceColorKind result;
      if (element_p instanceof IDifference && isConflicting((IDifference<?>)element_p))
        result = DifferenceColorKind.CONFLICT;
      else
        result = getInput().isOnTheLeft()? DifferenceColorKind.LEFT:
          DifferenceColorKind.RIGHT;
      return DefaultDiffLabelDecorator.getInstance().getDifferenceColor(result);
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      Image result = null;
      if (element_p instanceof IMatch) {
        IMatch<?> match = (IMatch<?>)element_p;
        result = getDelegate().getMatchImage(match, getInput().getDestination());
        if (result != null && isConflicting(match))
          result = _resourceManager.getOverlayVersion(result, ImageID.CONFLICT_STAT);
      } else if (element_p instanceof IDifference) {
        IDifference<?> difference = (IDifference<?>)element_p;
        Role destination = getInput().getDestination();
        result = getDelegate().getDifferenceImage(difference, destination);
        if (isConflicting(difference)) {
          ImageID overlay = getInput().isOnTheLeft()? ImageID.OUT_STAT:
            ImageID.INC_STAT;
          result = _resourceManager.getOverlayVersion(result, overlay);
        }
      }
      return result;
    }
    
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      String result = null;
      if (element_p instanceof IMatch) {
        IMatch<?> match = (IMatch<?>)element_p;
        result = getDelegate().getMatchText(match, getInput().getDestination(), null);
      } else if (element_p instanceof IDifference) {
        Role destination = getInput().getDestination();
        result = getDelegate().getDifferenceText((IDifference<?>)element_p, destination, null);
      } else {
        result = getDelegate().getText(element_p);
      }
      return result;
    }
  }
  
}
