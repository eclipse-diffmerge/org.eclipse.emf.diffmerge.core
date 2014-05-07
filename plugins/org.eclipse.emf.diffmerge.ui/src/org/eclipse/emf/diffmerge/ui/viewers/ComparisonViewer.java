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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.diffdata.EValuePresence;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl;
import org.eclipse.emf.diffmerge.ui.log.CompareLogEvent;
import org.eclipse.emf.diffmerge.ui.log.MergeLogEvent;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.InconsistencyDialog;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode.UserDifferenceKind;
import org.eclipse.emf.diffmerge.ui.viewers.FeaturesViewer.FeaturesInput;
import org.eclipse.emf.diffmerge.ui.viewers.IgnoreChoicesDialog.IgnoreChoiceData;
import org.eclipse.emf.diffmerge.ui.viewers.MergeChoicesDialog.MergeChoiceDialogData;
import org.eclipse.emf.diffmerge.ui.viewers.MergeImpactViewer.ImpactInput;
import org.eclipse.emf.diffmerge.ui.viewers.ValuesViewer.ValuesInput;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


/**
 * A Viewer for comparisons which is composed of six sub-viewers that show the scopes being
 * compared, a synthesis of the differences, features of the selected element, and the contents
 * of the selected feature in each scope.
 * Input: EMFDiffNode ; Elements: IMatch | IDifference.
 * @author Olivier Constant
 */
public class ComparisonViewer extends AbstractComparisonViewer {
  
  /** The name of the "filtering state" property */
  protected static final String PROPERTY_FILTERING = "PROPERTY_FILTERING"; //$NON-NLS-1$
  
  /** The name of the "difference numbers" property */
  protected static final String PROPERTY_DIFFERENCE_NUMBERS = "PROPERTY_DIFFERENCE_NUMBERS"; //$NON-NLS-1$
  
  /** The name of the "delete left activation" property */
  protected static final String PROPERTY_ACTIVATION_DELETE_LEFT = "PROPERTY_ACTIVATION_DELETE_LEFT"; //$NON-NLS-1$
  
  /** The name of the "delete right activation" property */
  protected static final String PROPERTY_ACTIVATION_DELETE_RIGHT = "PROPERTY_ACTIVATION_DELETE_RIGHT"; //$NON-NLS-1$
  
  /** The name of the "merge to left activation" property */
  protected static final String PROPERTY_ACTIVATION_MERGE_TO_LEFT = "PROPERTY_ACTIVATION_MERGE_TO_LEFT"; //$NON-NLS-1$
  
  /** The name of the "merge to right activation" property */
  protected static final String PROPERTY_ACTIVATION_MERGE_TO_RIGHT = "PROPERTY_ACTIVATION_MERGE_TO_RIGHT"; //$NON-NLS-1$
  
  /** The name of the "ignore left activation" property */
  protected static final String PROPERTY_ACTIVATION_IGNORE_LEFT = "PROPERTY_ACTIVATION_IGNORE_LEFT"; //$NON-NLS-1$
  
  /** The name of the "ignore right activation" property */
  protected static final String PROPERTY_ACTIVATION_IGNORE_RIGHT = "PROPERTY_ACTIVATION_IGNORE_RIGHT"; //$NON-NLS-1$
  
  
  /** The synthesis model tree viewer */
  protected EnhancedComparisonTreeViewer _synthesisModelTreeViewer;
  
  /** The left model tree viewer */
  protected EnhancedComparisonSideViewer _leftModelTreeViewer;
  
  /** The right model tree viewer */
  protected EnhancedComparisonSideViewer _rightModelTreeViewer;

  /** The features viewer */
  protected EnhancedFeaturesViewer _featuresViewer;

  /** The left values viewer */
  protected EnhancedValuesViewer _leftValuesViewer;
  
  /** The right values viewer */
  protected EnhancedValuesViewer _rightValuesViewer;
  
  /** The last selection directly made by the user */
  private ComparisonSelection _lastUserSelection;
  
  /** A filter for unchanged elements */
  protected ViewerFilter _unchangedElementsFilter;
  
  /** A filter for move origins */
  protected ViewerFilter _moveOriginsFilter;
  
  /** A sorter for the synthesis view */
  protected ViewerSorter _synthesisSorter;
  
  /** Whether the left and right trees are synchronized with the synthesis tree */
  protected boolean _isLeftRightSynced;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public ComparisonViewer(Composite parent_p) {
    this(parent_p, null);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   */
  public ComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    super(parent_p, actionBars_p);
  }
  
  /**
   * Add differences to merge on the given match to the given list according
   * to the given criteria
   * @param toMerge_p a non-null, modifiable list
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFEREBCE
   * @param incrementalMode_p whether optional deletions must be skipped
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected void addDifferencesToMerge(List<IDifference> toMerge_p, IMatch match_p,
      Role destination_p, boolean incrementalMode_p) {
    for (IDifference difference : match_p.getAllDifferences()) {
      if (!getInput().shouldBeIgnored(difference)) {
        if (!incrementalMode_p || difference instanceof IPresenceDifference &&
            ((IPresenceDifference)difference).getPresenceRole() != destination_p)
          toMerge_p.add(difference);
      }
    }
  }
  
  /**
   * Add differences to merge on the given match and its children to the given list according
   * to the given criteria
   * @param toMerge_p a non-null, modifiable list
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFEREBCE
   * @param incrementalMode_p whether optional deletions must be skipped
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected void addDifferencesToMergeRec(List<IDifference> toMerge_p, IMatch match_p,
      Role destination_p, boolean incrementalMode_p) {
    addDifferencesToMerge(toMerge_p, match_p, destination_p, incrementalMode_p);
    for (IMatch child : getInput().getChildrenForMerge(match_p)) {
      addDifferencesToMergeRec(toMerge_p, child, destination_p, incrementalMode_p);
    }
  }
  
  /**
   * Return whether the given situation allows adding to the left
   * @param originKind_p a non-null kind
   */
  protected boolean canAddToTheLeft(DifferenceKind originKind_p) {
    final Collection<DifferenceKind> allowed = Arrays.asList(
        DifferenceKind.CONFLICT,
        DifferenceKind.MODIFIED,
        DifferenceKind.FROM_BOTH,
        DifferenceKind.FROM_RIGHT,
        DifferenceKind.FROM_RIGHT_ADD,
        DifferenceKind.FROM_LEFT_DEL);
    return allowed.contains(originKind_p);
  }
  
  /**
   * Return whether the given situation allows adding to the right
   * @param originKind_p a non-null kind
   */
  protected boolean canAddToTheRight(DifferenceKind originKind_p) {
    final Collection<DifferenceKind> allowed = Arrays.asList(
        DifferenceKind.CONFLICT,
        DifferenceKind.MODIFIED,
        DifferenceKind.FROM_BOTH,
        DifferenceKind.FROM_LEFT,
        DifferenceKind.FROM_LEFT_ADD,
        DifferenceKind.FROM_RIGHT_DEL);
    return allowed.contains(originKind_p);
  }
  
  /**
   * Set the given difference counting feature to the given value
   * @param kind_p the kind of difference
   * @param newValue_p the new boolean value specifying whether to count it or not
   */
  protected void changeCounting(final UserDifferenceKind kind_p, final boolean newValue_p) {
    BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        getInput().setCount(kind_p, newValue_p);
        firePropertyChangeEvent(PROPERTY_DIFFERENCE_NUMBERS, null);
        firePropertyChangeEvent(PROPERTY_FILTERING, Boolean.valueOf(getInput().isFiltering()));
      }
    });
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#createControls(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Composite createControls(Composite parent_p) {
    // Non-graphical instance variables
    initialize();
    // Refresh viewer when number of differences changes
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_DIFFERENCE_NUMBERS.equals(event_p.getProperty())) {
          getInput().updateDifferenceNumbers();
          refresh();
        }
      }
    });
    // Main control
    SashForm result = new SashForm(parent_p, SWT.VERTICAL);
    // Upper and lower parts
    SashForm upperPart = createRowUpper(result);
    SashForm lowerPart = createRowLower(result);
    setupColumns(upperPart, lowerPart);
    result.setWeights(getDefaultRowWeights());
    // Overall synchronization
    setupSynchronizationListeners();
    setupToolBars();
    return result;
  }
  
  /**
   * Create the "log events" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuLogEvents(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_LogEventsMenuItem);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.isLogEvents());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        boolean logEvents = result.getSelection();
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setLogEvents(logEvents);
          if (logEvents)
            getLogger().log(new CompareLogEvent(getEditingDomain(), getComparison()));
        }
      }
    });
    return result;
  }
  
  /**
   * Create the "show additions" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowAdditions(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_CountAddLeftMenuItem);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.OUT_ADD_STAT));
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.counts(UserDifferenceKind.PRESENCE_LEFT));
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        changeCounting(UserDifferenceKind.PRESENCE_LEFT, result.getSelection());
      }
    });
    return result;
  }
  
  /**
   * Create the "show all values" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowAllFeatures(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.RADIO);
    result.setText(Messages.ComparisonViewer_ShowAllFeatures);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _featuresViewer.getInnerViewer().setDifferenceAgnostic(true);
        _leftValuesViewer.getInnerViewer().setDifferenceAgnostic(true);
        _rightValuesViewer.getInnerViewer().setDifferenceAgnostic(true);
      }
    });
    return result;
  }
  
  /**
   * Create the "show all values" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowAllValues(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.RADIO);
    result.setText(Messages.ComparisonViewer_ShowAllValues);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _featuresViewer.getInnerViewer().setDifferenceAgnostic(false);
        _leftValuesViewer.getInnerViewer().setDifferenceAgnostic(true);
        _rightValuesViewer.getInnerViewer().setDifferenceAgnostic(true);
      }
    });
    return result;
  }
  
  /**
   * Create the "show deletions" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowDeletions(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_CountAddRightMenuItem);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.INC_ADD_STAT));
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.counts(UserDifferenceKind.PRESENCE_RIGHT));
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        changeCounting(UserDifferenceKind.PRESENCE_RIGHT, result.getSelection());
      }
    });
    return result;
  }
  
  /**
   * Create the "show values on differences" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowDiffValues(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.RADIO);
    result.setText(Messages.ComparisonViewer_ShowValueDiffs);
    result.setSelection(true);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _featuresViewer.getInnerViewer().setDifferenceAgnostic(false);
        _leftValuesViewer.getInnerViewer().setDifferenceAgnostic(false);
        _rightValuesViewer.getInnerViewer().setDifferenceAgnostic(false);
      }
    });
    return result;
  }
  
  /**
   * Create the "show merge impact" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowImpact(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_ImpactMenuItem);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.isShowMergeImpact());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        boolean showImpact = result.getSelection();
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setShowMergeImpact(showImpact);
          input.setDefaultShowImpact(showImpact);
        }
      }
    });
    return result;
  }
  
  /**
   * Create the "show moves" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowMoves(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_CountMovesMenuItem);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.MODIFIED_STAT));
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.counts(UserDifferenceKind.MOVE));
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        changeCounting(UserDifferenceKind.MOVE, result.getSelection());
      }
    });
    return result;
  }
  
  /**
   * Create the "show non-containment differences" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowNonContainmentDifferences(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_CountProperMenuItem);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.MODIFIED_STAT));
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.counts(UserDifferenceKind.NO_CONTAINMENT));
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        changeCounting(UserDifferenceKind.NO_CONTAINMENT, result.getSelection());
      }
    });
    return result;
  }
  
  /**
   * Create the "show uncounted elements" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuShowUncounted(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_ShowUncountedMenuItem);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        if (result.getSelection())
          _synthesisModelTreeViewer.getInnerViewer().removeFilter(_unchangedElementsFilter);
        else
          _synthesisModelTreeViewer.getInnerViewer().addFilter(_unchangedElementsFilter);
      }
    });
    return result;
  }
  
  /**
   * Create the "support undo/redo" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuSupportUndoRedo(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_SupportUndoRedoMenuItem);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed()) {
            result.setSelection(input.isUndoRedoSupported());
            result.setEnabled(input.getEditingDomain() != null);
          }
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        EMFDiffNode input = getInput();
        if (input != null)
          input.setUndoRedoSupported(result.getSelection());
      }
    });
    return result;
  }
  
  /**
   * Create the "use custom icons" menu item in the given menu and return it
   * @param menu_p a non-null menu
   * @return result a potentially null menu item
   */
  protected MenuItem createMenuUseCustomIcons(Menu menu_p) {
    final MenuItem result = new MenuItem(menu_p, SWT.CHECK);
    result.setText(Messages.ComparisonViewer_IconsMenuItem);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null && !result.isDisposed())
            result.setSelection(input.usesCustomIcons());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUseCustomIcons(result.getSelection());
          _synthesisModelTreeViewer.refresh();
          _featuresViewer.refresh();
          _leftValuesViewer.refresh();
          _rightValuesViewer.refresh();
        }
      }
    });
    return result;
  }
  
  /**
   * Create and return the lower row of the GUI
   * @param parent_p a non-null composite
   * @return a non-null widget
   */
  protected SashForm createRowLower(Composite parent_p) {
    SashForm result = new SashForm(parent_p, SWT.HORIZONTAL);
    // Features section
    _featuresViewer = createViewerFeatures(result);
    // Values section
    _leftValuesViewer = createViewerValues(result, true);
    _rightValuesViewer = createViewerValues(result, false);
    return result;
  }
  
  /**
   * Create and return the upper row of the GUI
   * @param parent_p a non-null composite
   * @return a non-null widget
   */
  protected SashForm createRowUpper(Composite parent_p) {
    SashForm result = new SashForm(parent_p, SWT.HORIZONTAL);
    _synthesisModelTreeViewer = createViewerSynthesis(result);
    _leftModelTreeViewer = createViewerSynthesisSide(result, true);
    _rightModelTreeViewer = createViewerSynthesisSide(result, false);
    return result;
  }
  
  /**
   * Create and return a features viewer
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedFeaturesViewer createViewerFeatures(Composite parent_p) {
    EnhancedFeaturesViewer result = new EnhancedFeaturesViewer(parent_p);
    return result;
  }
  
  /**
   * Create and return the main viewer of the synthesis row
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedComparisonTreeViewer createViewerSynthesis(Composite parent_p) {
    final EnhancedComparisonTreeViewer result = new EnhancedComparisonTreeViewer(parent_p);
    result.getInnerViewer().addFilter(_unchangedElementsFilter);
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_FILTERING.equals(event_p.getProperty())) {
          Boolean filtered = (Boolean)event_p.getNewValue();
          if (filtered != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(_synthesisModelTreeViewer.getDefaultHeaderText());
            if (filtered.booleanValue())
              builder.append(Messages.ComparisonViewer_Filtered);
            result.getTextLabel().setText(builder.toString());
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Create and return the viewer in the synthesis row for the given side
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left
   * @return a non-null viewer
   */
  protected EnhancedComparisonSideViewer createViewerSynthesisSide(Composite parent_p, boolean isLeftSide_p) {
    return new EnhancedComparisonSideViewer(parent_p, isLeftSide_p);
  }
  
  /**
   * Create and return the values viewer for the given side
   * @param parent_p a non-null composite
   * @param left_p whether the side is left or right
   * @return a non-null viewer
   */
  protected EnhancedValuesViewer createViewerValues(Composite parent_p, boolean left_p) {
    EnhancedValuesViewer result = new EnhancedValuesViewer(parent_p, left_p);
    return result;
  }
  
  /**
   * Create the "collapse all" tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolCollapse(ToolBar toolbar_p) {
    ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.COLLAPSEALL));
    result.setToolTipText(Messages.ComparisonViewer_CollapseTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            _synthesisModelTreeViewer.getInnerViewer().collapseAll();
          }
        });
      }
    });
    return result;
  }
  
  /**
   * Create the "delete" tool for the given side in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left
   * @return a potentially null tool item
   */
  protected ToolItem createToolDelete(ToolBar toolbar_p, final boolean onLeft_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    // Image
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.DELETE));
    // Tool tip
    result.setToolTipText(onLeft_p? Messages.ComparisonViewer_DeleteLeftTooltip:
      Messages.ComparisonViewer_DeleteRightTooltip);
    result.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (onLeft_p && PROPERTY_ACTIVATION_DELETE_LEFT.equals(event_p.getProperty()) ||
            !onLeft_p && PROPERTY_ACTIVATION_DELETE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean)
            result.setEnabled(((Boolean)newValue).booleanValue());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        merge(onLeft_p);
      }
    });
    return result;
  }
  
  /**
   * Create the "expand all" tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolExpand(ToolBar toolbar_p) {
    ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.EXPANDALL));
    result.setToolTipText(Messages.ComparisonViewer_ExpandTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            _synthesisModelTreeViewer.getInnerViewer().expandAll();
          }
        });
      }
    });
    return result;
  }
  
  /**
   * Create the "ignore" tool for the given side in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left
   * @return a potentially null tool item
   */
  protected ToolItem createToolIgnore(ToolBar toolbar_p, final boolean onLeft_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    // Image
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.DONE));
    // Tool tip
    result.setToolTipText(onLeft_p? Messages.ComparisonViewer_IgnoreLeftTooltip:
      Messages.ComparisonViewer_IgnoreRightTooltip);
    result.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (onLeft_p && PROPERTY_ACTIVATION_IGNORE_LEFT.equals(event_p.getProperty()) ||
            !onLeft_p && PROPERTY_ACTIVATION_IGNORE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean)
            result.setEnabled(((Boolean)newValue).booleanValue());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        ignore(onLeft_p);
      }
    });
    return result;
  }
  
  /**
   * Create the "inconsistency" tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolInconsistency(ToolBar toolbar_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.WARNING));
    result.setToolTipText(Messages.ComparisonViewer_InconsistencyTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        final Shell shell = getShell();
        final EComparison comparison = getComparison();
        if (shell != null && comparison != null) {
          shell.getDisplay().syncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              InconsistencyDialog dialog = new InconsistencyDialog(shell, comparison);
              dialog.open();
            }
          });
        }
      }
    });
    result.setEnabled(false);
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          IComparison comparison = getComparison();
          result.setEnabled(comparison != null && !comparison.isConsistent());
        }
      }
    });
    return result;
  }
  
  /**
   * Create the locking tool in the given tool bar for the given side and return it
   * @param toolbar_p a non-null toolbar
   * @param onLeft_p whether the side is left or right
   * @return a potentially null tool
   */
  protected ToolItem createToolLock(ToolBar toolbar_p, final boolean onLeft_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.CHECK);
    Image lockImage = EMFDiffMergeUIPlugin.getDefault().getImage(EMFDiffMergeUIPlugin.ImageID.LOCK);
    result.setImage(lockImage);
    String lockTooltip = Messages.ComparisonViewer_LockTooltip;
    result.setToolTipText(lockTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        getInput().setEditable(!result.getSelection(), onLeft_p);
        refreshTools();
      }
    });
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null) {
            result.setSelection(!input.isEditable(onLeft_p));
            result.setEnabled(input.isEditionPossible(onLeft_p));
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Create the "merge" tool to the given side in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @param toLeft_p whether the side is left
   * @return a potentially null tool item
   */
  protected ToolItem createToolMerge(ToolBar toolbar_p, final boolean toLeft_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    // Image
    EMFDiffMergeUIPlugin.ImageID imageID = toLeft_p?
        EMFDiffMergeUIPlugin.ImageID.CHECKOUT_ACTION:
          EMFDiffMergeUIPlugin.ImageID.CHECKIN_ACTION;
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(imageID));
    // Tool tip
    result.setToolTipText(toLeft_p? Messages.ComparisonViewer_MergeLeftTooltip:
      Messages.ComparisonViewer_MergeRightTooltip);
    result.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (toLeft_p && PROPERTY_ACTIVATION_MERGE_TO_LEFT.equals(event_p.getProperty()) ||
            !toLeft_p && PROPERTY_ACTIVATION_MERGE_TO_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean)
            result.setEnabled(((Boolean)newValue).booleanValue());
        }
      }
    });
    // Selection
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        merge(toLeft_p);
      }
    });
    return result;
  }
  
  /**
   * Create the "next" navigation tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolNavigationNext(ToolBar toolbar_p) {
    ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.NEXT_DIFF_NAV));
    result.setToolTipText(Messages.ComparisonViewer_NextTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        navigate(true);
      }
    });
    return result;
  }
  
  /**
   * Create the "previous" navigation tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolNavigationPrevious(ToolBar toolbar_p) {
    ToolItem result = new ToolItem(toolbar_p, SWT.PUSH);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.PREV_DIFF_NAV));
    result.setToolTipText(Messages.ComparisonViewer_PreviousTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        navigate(false);
      }
    });
    return result;
  }
  
  /**
   * Create the "sort" tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolSort(ToolBar toolbar_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.CHECK);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.SORT));
    result.setToolTipText(Messages.ComparisonViewer_SortTooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        if (result.getSelection())
          _synthesisModelTreeViewer.getInnerViewer().setSorter(_synthesisSorter);
        else
          _synthesisModelTreeViewer.getInnerViewer().setSorter(null);
      }
    });
    return result;
  }
  
  /**
   * Create the "sync" tool in the given tool bar and return it
   * @param toolbar_p a non-null tool bar
   * @return a potentially null tool item
   */
  protected ToolItem createToolSync(ToolBar toolbar_p) {
    final ToolItem result = new ToolItem(toolbar_p, SWT.CHECK);
    result.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.SYNCED));
    result.setToolTipText(Messages.ComparisonViewer_LinkViewsTooltip);
    result.setSelection(_isLeftRightSynced);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        boolean synced = result.getSelection();
        _isLeftRightSynced = synced;
        if (_isLeftRightSynced) {
          BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              ISelection selection = _synthesisModelTreeViewer.getSelection();
              _leftModelTreeViewer.setSelection(selection, true);
              _rightModelTreeViewer.setSelection(selection, true);
            }
          });
        }
      }
    });
    return result;
  }
  
  /**
   * Ignore the current selection
   * @param onLeft_p whether ignore occurs on the left
   */
  protected void ignore(boolean onLeft_p) {
    final ComparisonSelection selection = getSelection();
    if (selection == null) return;
    boolean coverChildren = getInput().isDefaultCoverChildren();
    boolean sideExclusive = false;
    List<EMatch> selectedMatches = selection.getSelectedMatches();
    if (selectedMatches.isEmpty()) {
      List<EMatch> treePath = selection.getSelectedTreePath();
      if (!treePath.isEmpty())
        selectedMatches = Collections.singletonList(treePath.get(treePath.size()-1));
    }
    boolean basedOnMatches = !selectedMatches.isEmpty();
    boolean requiresChoices = false;
    if (basedOnMatches) {
      for (EMatch selectedMatch : selectedMatches) {
        if (getInput().hasChildrenForMerge(selectedMatch)) {
          requiresChoices = true;
          break;
        }
      }
    }
    if (requiresChoices) {
      // Group of differences
      IgnoreChoiceData choice = new IgnoreChoiceData(coverChildren, false);
      IgnoreChoicesDialog choicesDialog =
        new IgnoreChoicesDialog(getShell(), Messages.ComparisonViewer_IgnoreCommandName, choice);
      int answer = choicesDialog.open();
      if (0 != answer) return;
      coverChildren = choice.getCoverChildren();
      getInput().setDefaultCoverChildren(choice.getCoverChildren());
      sideExclusive = choice.getSideExclusive();
    }
    final Collection<IDifference> toIgnore = basedOnMatches?
        getDifferencesToMerge(selectedMatches, getInput().getRoleForSide(onLeft_p), coverChildren, sideExclusive):
          getInput().getNonIgnoredDifferences(selection.asDifferencesToMerge());
    if (!toIgnore.isEmpty()) {
      executeOnModel(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          for (IDifference diff : toIgnore) {
            if (diff instanceof EElementRelativePresence) {
              EElementRelativePresence presence = (EElementRelativePresence)diff;
              getUIComparison().getDifferencesToIgnore().add(presence);
              // Also on symmetrical if any
              if (diff instanceof EValuePresence) {
                IValuePresence symmetrical = ((EValuePresence)diff).getSymmetrical();
                if (symmetrical instanceof EMergeableDifference)
                  getUIComparison().getDifferencesToIgnore().add(
                      (EMergeableDifference)symmetrical);
              }
            }
          }
          getUIComparison().setLastActionSelection(selection);
        }
      });
      firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(true));
      firePropertyChangeEvent(PROPERTY_DIFFERENCE_NUMBERS, null);
    }
  }
  
  /**
   * Return the default respective weights of the columns (sashes) of the GUI
   * @return an int array whose size is equal to the number of columns
   */
  protected int[] getDefaultColumnWeights() {
    return new int[] {3, 2, 2};
  }
  
  /**
   * Return the default respective weights of the rows (sashes) of the GUI
   * @return an int array whose size is equal to the number of rows
   */
  protected int[] getDefaultRowWeights() {
    return new int[] {5, 2};
  }
  
  /**
   * Return the differences to merge from a given list of selected matches and the given
   * criteria
   * @param selectedMatches_p a non-null list
   * @param coverChildren_p whether children of the matches must be covered
   * @param incrementalMode_p whether optional deletions must be skipped
   * @return a non-null, potentially empty, unmodifiable list
   */
  protected List<IDifference> getDifferencesToMerge(final List<EMatch> selectedMatches_p,
      final Role destination_p, final boolean coverChildren_p, final boolean incrementalMode_p) {
    final List<IDifference> result = new ArrayList<IDifference>();
    IProgressService progress = PlatformUI.getWorkbench().getProgressService();
    try {
      progress.busyCursorWhile(new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(IProgressMonitor)
         */
        public void run(final IProgressMonitor monitor_p)
        throws InvocationTargetException, InterruptedException {
          for (EMatch selectedMatch : selectedMatches_p) {
            if (coverChildren_p)
              addDifferencesToMergeRec(result, selectedMatch, destination_p, incrementalMode_p);
            else
              addDifferencesToMerge(result, selectedMatch, destination_p, incrementalMode_p);
          }
        }
      });
    } catch (Exception e) {
      // Proceed
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the driving role for this viewer
   * @return a role which is assumed non-null after setInput(Object) has been invoked
   */
  public Role getDrivingRole() {
    return getInput() == null? null: getInput().getDrivingRole();
  }
  
  /**
   * Return the inner viewer for the features of elements with differences
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public HeaderViewer<?> getFeaturesViewer() {
    return _featuresViewer;
  }
  
  /**
   * Return the logger for diff/merge events
   * @return a non-null logger
   */
  protected Logger getLogger() {
    return EMFDiffMergeUIPlugin.getDefault().getDiffMergeLogger();
  }
  
  /**
   * Return the inner viewer for the model from the given side
   * @param left_p whether the side is left or right
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public EnhancedComparisonSideViewer getModelScopeViewer(boolean left_p) {
    EnhancedComparisonSideViewer result = left_p? _leftModelTreeViewer:
      _rightModelTreeViewer;
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ComparisonSelection getSelection() {
    return _lastUserSelection;
  }
  
  /**
   * Return the inner viewer for the comparison tree
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public EnhancedComparisonTreeViewer getSynthesisViewer() {
    return _synthesisModelTreeViewer;
  }
  
  /**
   * Return the inner viewer for differences on values for the given side
   * @param left_p whether the side is left or right
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public EnhancedValuesViewer getValuesViewer(boolean left_p) {
    EnhancedValuesViewer result = left_p? _leftValuesViewer: _rightValuesViewer;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#handleDispose()
   */
  @Override
  protected void handleDispose() {
    super.handleDispose();
    _lastUserSelection = null;
    _leftModelTreeViewer = null;
    _rightModelTreeViewer = null;
    _leftValuesViewer = null;
    _rightValuesViewer = null;
    _synthesisModelTreeViewer = null;
    _featuresViewer = null;
    _synthesisSorter = null;
    _unchangedElementsFilter = null;
    _moveOriginsFilter = null;
  }
  
  /**
   * Initialize the non-graphical instance variables
   */
  protected void initialize() {
    _isLeftRightSynced = true;
    _lastUserSelection = null;
    _synthesisSorter = new ViewerSorter();
    _unchangedElementsFilter = new ViewerFilter() {
      /**
       * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
       */
      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        EMatch match = (EMatch)element_p;
        return getInput().getDifferenceNumber(match) > 0;
      }
    };
    _moveOriginsFilter = new ViewerFilter() {
      /**
       * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
       */
      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        TreePath path;
        if (parentElement_p instanceof TreePath)
          path = ((TreePath)parentElement_p).createChildPath(element_p);
        else
          path = new TreePath(new Object[] {element_p});
        return !getInput().isMoveOrigin(path);
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    _featuresViewer.setInput(null);
    _leftValuesViewer.setInput(null);
    _rightValuesViewer.setInput(null);
    _synthesisModelTreeViewer.setInput(input_p);
    _leftModelTreeViewer.setInput(input_p);
    _rightModelTreeViewer.setInput(input_p);
    super.inputChanged(input_p, oldInput_p);
    if (getInput() != null && getInput().isLogEvents())
      getLogger().log(new CompareLogEvent(getEditingDomain(), getComparison()));
  }
  
  /**
   * Merge the current selection to the given side
   * @param toLeft_p whether destination is left or right
   */
  protected void merge(final boolean toLeft_p) {
    final ComparisonSelection selection = getSelection();
    if (selection == null) return;
    final EMFDiffNode input = getInput();
    boolean showMergeImpact = input.isShowMergeImpact();
    boolean coverChildren = input.isDefaultCoverChildren();
    boolean incrementalMode = false;
    List<EMatch> selectedMatches = selection.getSelectedMatches();
    if (selectedMatches.isEmpty()) {
      List<EMatch> treePath = selection.getSelectedTreePath();
      if (!treePath.isEmpty())
        selectedMatches = Collections.singletonList(treePath.get(treePath.size()-1));
    }
    boolean basedOnMatches = !selectedMatches.isEmpty();
    boolean requiresChoices = basedOnMatches;
    if (requiresChoices && selectedMatches.size() == 1) {
      EMatch selectedMatch = selectedMatches.get(0);
      if (!input.hasChildrenForMerge(selectedMatch)) {
        DifferenceKind kind = input.getDifferenceKind(selectedMatch);
        requiresChoices = !(kind.isAddition() || kind.isDeletion());
      }
    }
    if (requiresChoices) {
      // Group of differences
      boolean askAboutChildren = false;
      for (EMatch selectedMatch : selectedMatches) {
        if (input.getDifferenceKind(selectedMatch) == DifferenceKind.COUNTED) {
          coverChildren = true;
          break;
        } else if (input.hasChildrenForMerge(selectedMatch)) {
          askAboutChildren = true;
          break;
        }
      }
      MergeChoiceDialogData choice = new MergeChoiceDialogData(coverChildren,
          input.isDefaultIncrementalMode(), input.isDefaultShowImpact());
      MergeChoicesDialog choicesDialog =
        new MergeChoicesDialog(getShell(), Messages.ComparisonViewer_MergeHeader,
            choice, askAboutChildren);
      int answer = choicesDialog.open();
      if (0 != answer) return;
      showMergeImpact = choice.getShowImpact();
      coverChildren = choice.getCoverChildren();
      incrementalMode = choice.getIncrementalMode();
      if (askAboutChildren)
        input.setDefaultCoverChildren(choice.getCoverChildren());
      input.setDefaultIncrementalMode(choice.getIncrementalMode());
      input.setDefaultShowImpact(choice.getShowImpact());
    }
    final Role destination = input.getRoleForSide(toLeft_p);
    final Collection<IDifference> toMerge = basedOnMatches?
        getDifferencesToMerge(selectedMatches, destination, coverChildren, incrementalMode):
          input.getNonIgnoredDifferences(selection.asDifferencesToMerge());
    final Collection<IDifference> merged = new ArrayList<IDifference>();
    boolean done = false;
    if (!toMerge.isEmpty()) {
      final ImpactInput mergeInput = new ImpactInput(toMerge, toLeft_p, input);
      boolean proceed = true;
      IProgressService progress = PlatformUI.getWorkbench().getProgressService();
      if (showMergeImpact) {
        try {
          progress.busyCursorWhile(new IRunnableWithProgress() {
            /**
             * @see org.eclipse.jface.operation.IRunnableWithProgress#run(IProgressMonitor)
             */
            public void run(final IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
              mergeInput.compute(monitor_p);
            }
          });
          MergeImpactMessageDialog dialog = new MergeImpactMessageDialog(getShell(), mergeInput);
          proceed = dialog.openAndConfirm();
        } catch (Exception exception_p) {
          // Proceed
        }
      }
      if (proceed) {
        try {
          progress.busyCursorWhile(new IRunnableWithProgress() {
            /**
             * @see org.eclipse.jface.operation.IRunnableWithProgress#run(IProgressMonitor)
             */
            public void run(final IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
              Runnable mergeRunnable = new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                  merged.addAll(getComparison().merge(toMerge, destination, true, monitor_p));
                  getUIComparison().setLastActionSelection(selection);
                }
              };
              if (input.isUndoRedoSupported())
                MiscUtil.executeOnDomain(getEditingDomain(), null, mergeRunnable);
              else
                MiscUtil.executeAndForget(getEditingDomain(), mergeRunnable);
            }
          });
          done = true;
        } catch (Exception e) {
          throw new OperationCanceledException(e.getLocalizedMessage()); // Trigger transaction rollback
        }
      }
    } else {
      MessageDialog.openInformation(getShell(), Messages.ComparisonViewer_MergeHeader,
          Messages.ComparisonViewer_NoDiffsToMerge);
    }
    if (!merged.isEmpty() && done) {
      input.setModified(true, toLeft_p);
      firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(true));
      firePropertyChangeEvent(PROPERTY_DIFFERENCE_NUMBERS, null);
      if (input.isLogEvents())
        getLogger().log(
            new MergeLogEvent(getEditingDomain(), getComparison(), merged, toLeft_p));
    }
  }
  
  /**
   * Navigate to the next/previous difference according to the given flag
   * @param next_p whether navigation must be forward or back
   */
  protected void navigate(boolean next_p) {
    ITreeSelection selection = _synthesisModelTreeViewer.getSelection();
    TreePath current = (selection == null || selection.isEmpty())? TreePath.EMPTY:
      selection.getPaths()[0];
    ComparisonTreeViewer treeViewer = _synthesisModelTreeViewer.getInnerViewer();
    TreePath newPath = next_p? treeViewer.getNextUserDifference(current):
      treeViewer.getPreviousUserDifference(current);
    if (newPath != null)
      setSelection(new ComparisonSelectionImpl(newPath, getDrivingRole()), true);
  }
  
  /**
   * Return whether the given difference is represented as a virtual "ownership"
   * (an opposite to a containment value presence)
   * @param difference_p a potentially null difference
   */
  protected boolean representAsOwnership(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence presence = (IReferenceValuePresence)difference_p;
      EReference ref = presence.getFeature();
      result = !presence.isOrder() && ref != null && ref.isContainment();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _leftModelTreeViewer.refresh();
    _rightModelTreeViewer.refresh();
    _leftValuesViewer.refresh();
    _rightValuesViewer.refresh();
    _featuresViewer.refresh();
    _synthesisModelTreeViewer.refresh();
    super.refresh();
  }
  
  /**
   * Refresh the tools of the viewer. This behavior is centralized instead of being
   * delegated to each tool via listeners in order to improve performance.
   */
  @Override
  protected void refreshTools() {
    ComparisonSelection selection = getSelection();
    EMFDiffNode input = getInput();
    // Merge
    boolean onLeft = false, onRight = false;
    boolean allowDeletion = false;
    boolean allowIgnoring = false;
    if (selection != null && input != null) {
      allowIgnoring = true;
      IValuePresence presence = selection.asValuePresence();
      if (presence != null && !presence.isMerged()) {
        // Value presence
        DifferenceKind kind = input.getDifferenceKind(presence);
        onLeft = canAddToTheRight(kind);
        onRight = canAddToTheLeft(kind);
        allowDeletion = input.isMany(presence) && !input.isOwnershipOpposite(presence);
      } else if (selection.asFeature() == null) {
        List<EMatch> matches = selection.asMatches();
        if (!matches.isEmpty()) {
          // Matches selected
          if (matches.size() > 1) {
            // Several matches selected
            allowDeletion = true;
            Iterator<EMatch> it = matches.iterator();
            while (it.hasNext() && (!onLeft || !onRight || allowDeletion)) {
              EMatch current = it.next();
              DifferenceKind kind = input.getDifferenceKind(current);
              if (kind.isAddition()) {
                onLeft = onLeft || kind.isLeft(true);
                onRight = onRight || kind.isRight(true);
              } else {
                onLeft = true;
                onRight = true;
                allowDeletion = false;
              }
            }
            allowDeletion = allowDeletion && (onLeft != onRight);
          } else {
            // Only one match selected
            IMatch match = matches.get(0);
            if (input.representAsModification(match) || input.representAsMove(match) ||
                input.getDifferenceKind(match) == DifferenceKind.COUNTED) {
              // Modification or move or inner differences
              onLeft = true;
              onRight = true;
              allowDeletion = false;
            } else {
              // Partial match
              DifferenceKind kind = input.getDifferenceKind(match);
              onLeft = canAddToTheRight(kind);
              onRight = canAddToTheLeft(kind);
              allowDeletion = true;
            }
          }
        }
      }
    }
    if (input != null) {
      firePropertyChangeEvent(
          PROPERTY_ACTIVATION_MERGE_TO_RIGHT, new Boolean(input.isEditable(false) && onLeft));
      firePropertyChangeEvent(
          PROPERTY_ACTIVATION_DELETE_LEFT, new Boolean(input.isEditable(true) && onLeft && allowDeletion));
      firePropertyChangeEvent(
          PROPERTY_ACTIVATION_MERGE_TO_LEFT, new Boolean(input.isEditable(true) && onRight));
      firePropertyChangeEvent(
          PROPERTY_ACTIVATION_DELETE_RIGHT, new Boolean(input.isEditable(false) && onRight && allowDeletion));
    }
    firePropertyChangeEvent(
        PROPERTY_ACTIVATION_IGNORE_LEFT, new Boolean(onLeft && allowIgnoring));
    firePropertyChangeEvent(
        PROPERTY_ACTIVATION_IGNORE_RIGHT, new Boolean(onRight && allowIgnoring));
    super.refreshTools();
  }
  
  /**
   * Set the "base" label provider for representing model elements
   * @param labelProvider_p a potentially null label provider, where null stands for default
   */
  public void setDelegateLabelProvider(ILabelProvider labelProvider_p) {
    List<Viewer> viewers = Arrays.<Viewer>asList(
        _synthesisModelTreeViewer.getInnerViewer(),
        _leftModelTreeViewer.getInnerViewer(),
        _rightModelTreeViewer.getInnerViewer(),
        _featuresViewer, _leftValuesViewer, _rightValuesViewer);
    for (Viewer viewer : viewers) {
      if (viewer instanceof ContentViewer) {
        IBaseLabelProvider rawLP = ((ContentViewer)viewer).getLabelProvider();
        if (rawLP instanceof DelegatingLabelProvider) {
          DelegatingLabelProvider delegatingLP = (DelegatingLabelProvider)rawLP;
          delegatingLP.setDelegate(labelProvider_p);
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    final IStructuredSelection emptySelection = new StructuredSelection();
    IStructuredSelection synthesisSelection = emptySelection;
    IStructuredSelection leftModelSelection = emptySelection;
    IStructuredSelection rightModelSelection = emptySelection;
    IStructuredSelection featureSelection = emptySelection;
    IStructuredSelection leftValueSelection = emptySelection;
    IStructuredSelection rightValueSelection = emptySelection;
    if (selection_p instanceof ComparisonSelection) {
      ComparisonSelection selection = (ComparisonSelection)selection_p;
      boolean representAsOwnership = representAsOwnership(selection.asValuePresence());
      // Synthesis
      List<EMatch> matches = selection.asMatches();
      if (!matches.isEmpty()) {
        if (representAsOwnership && matches.size() == 1)
          synthesisSelection = new StructuredSelection(selection.asValuePresence().getValue());
        else
          synthesisSelection = new StructuredSelection(matches);
        leftModelSelection = synthesisSelection;
        rightModelSelection = synthesisSelection;
      }
      // Features: input
      EMatch match = matches.size() == 1? matches.get(0): null;
      if (match != null) {
        if (representAsOwnership(selection.asValuePresence()))
          match = (EMatch)selection.asValuePresence().getValue();
        if (_featuresViewer.getInput() == null ||
            _featuresViewer.getInput().getMatch() != match)
          _featuresViewer.setInput(new FeaturesInput(getInput(), match));
      } else if (!matches.isEmpty()) {
        _featuresViewer.setInput(null);
      }
      // Features: selection
      EStructuralFeature feature = !representAsOwnership? selection.asFeature():
        EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature();
      if (feature == null && matches.size() == 1)
        // If a single match is selected and no feature, select the first relevant feature
        feature = (EStructuralFeature)_featuresViewer.getInnerViewer().getElementAt(0);
      // Values: input
      ValuesInput valuesInput = feature == null? null:
        new ValuesInput(getInput(), new MatchAndFeatureImpl(match, feature));
      if (valuesInput == null || !valuesInput.equals(_leftValuesViewer.getInput()))
        _leftValuesViewer.setInput(valuesInput);
      if (valuesInput == null || !valuesInput.equals(_rightValuesViewer.getInput()))
        _rightValuesViewer.setInput(valuesInput);
      if (feature != null) {
        featureSelection = new StructuredSelection(feature);
        // Values
        List<EValuePresence> presences = selection.asValuePresences();
        leftValueSelection = new StructuredSelection(presences);
        rightValueSelection = leftValueSelection;
        if (presences.size() == 1) {
          // If a single value presence is selected, select the referenced element
          // in the corresponding model viewer
          IValuePresence presence = presences.get(0);
          if (presence instanceof IReferenceValuePresence) {
            IReferenceValuePresence rvp = (IReferenceValuePresence)presence;
            IMatch value = representAsOwnership(rvp)? rvp.getElementMatch(): rvp.getValue();
            IStructuredSelection rvpSelection = new StructuredSelection(value);
            if (rvp.getPresenceRole() == getInput().getRoleForSide(true))
              leftModelSelection = rvpSelection;
            else
              rightModelSelection = rvpSelection;
          }
        }
      }
    }
    _synthesisModelTreeViewer.setSelection(synthesisSelection, reveal_p);
    if (_isLeftRightSynced) {
      _leftModelTreeViewer.setSelection(leftModelSelection, reveal_p);
      _rightModelTreeViewer.setSelection(rightModelSelection, reveal_p);
    }
    _featuresViewer.setSelection(featureSelection, reveal_p);
    _leftValuesViewer.setSelection(leftValueSelection, reveal_p);
    _rightValuesViewer.setSelection(rightValueSelection, reveal_p);
    _lastUserSelection =
      selection_p instanceof ComparisonSelection? (ComparisonSelection)selection_p:
        new ComparisonSelectionImpl(null, null);
    fireSelectionChanged(new SelectionChangedEvent(this, getSelection()));
  }
  
  /**
   * Configure the columns (sashes) of the UI in terms of weights and synchronization
   * @param upperRow_p the sash form on the up side
   * @param lowerRow_p the sash form on the down side
   */
  protected void setupColumns(final SashForm upperRow_p, final SashForm lowerRow_p) {
    final int[] horizontalWeights = getDefaultColumnWeights();
    upperRow_p.setWeights(horizontalWeights);
    lowerRow_p.setWeights(horizontalWeights);
    // Synchronize lower row on upper row when the latter is subject to resize
    Control upperMiddleControl = upperRow_p.getChildren()[1];
    upperMiddleControl.addControlListener(new ControlListener() {
      /**
       * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
       */
      public void controlResized(ControlEvent e_p) {
        int[] weights = upperRow_p.getWeights();
        lowerRow_p.setWeights(weights);
      }
      /**
       * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
       */
      public void controlMoved(ControlEvent e_p) {
        // Nothing
      }
    });
    // Synchronize upper row on lower row when the latter is subject to resize
    Control lowerMiddleControl = lowerRow_p.getChildren()[1];
    lowerMiddleControl.addControlListener(new ControlListener() {
      /**
       * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
       */
      public void controlResized(ControlEvent e_p) {
        int[] weights = lowerRow_p.getWeights();
        upperRow_p.setWeights(weights);
      }
      /**
       * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
       */
      public void controlMoved(ControlEvent e_p) {
        // Nothing
      }
    });
  }
  
  /**
   * Create and return the menu related to the detailed representation of differences
   * in the given tool bar
   * @param toolbar_p a non-null tool bar
   * @return a potentially null menu
   */
  @SuppressWarnings("unused")
  protected Menu setupMenuDetails(ToolBar toolbar_p) {
    new ToolItem(toolbar_p, SWT.SEPARATOR);
    Menu result = UIUtil.createMenuTool(_featuresViewer.getToolbar());
    // Only differences
    createMenuShowDiffValues(result);
    // All values
    createMenuShowAllValues(result);
    // All values and features
    createMenuShowAllFeatures(result);
    return result;
  }
  
  /**
   * Create and return the synthesis menu in the given tool bar
   * @param toolbar_p a non-null tool bar
   * @return a potentially null menu
   */
  @SuppressWarnings("unused")
  protected Menu setupMenuSynthesis(ToolBar toolbar_p) {
    Menu synthesisMenu = UIUtil.createMenuTool(toolbar_p);
    // Show uncounted elements
    createMenuShowUncounted(synthesisMenu);
    // Show...
    new MenuItem(synthesisMenu, SWT.SEPARATOR);
    createMenuShowAdditions(synthesisMenu);
    createMenuShowDeletions(synthesisMenu);
    createMenuShowMoves(synthesisMenu);
    createMenuShowNonContainmentDifferences(synthesisMenu);
    // UI options
    new MenuItem(synthesisMenu, SWT.SEPARATOR);
    createMenuUseCustomIcons(synthesisMenu);
    createMenuShowImpact(synthesisMenu);
    createMenuSupportUndoRedo(synthesisMenu);
    createMenuLogEvents(synthesisMenu);
    return synthesisMenu;
  }
  
  /**
   * Add listeners for synchronizing the viewers
   */
  protected void setupSynchronizationListeners() {
    // Selection synchronization: Synthesis[user] -> Global
    _synthesisModelTreeViewer.addSWTSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        IStructuredSelection selection = _synthesisModelTreeViewer.getSelection();
        if (!selection.isEmpty())
          ComparisonViewer.this.setSelection(new ComparisonSelectionImpl(
              selection.toList(), getDrivingRole()), true);
      }
    });
    // Selection synchronization: Models[user] -> Global
    _leftModelTreeViewer.addSWTSelectionListener(new ComparisonSideSelectionListener(true));
    _rightModelTreeViewer.addSWTSelectionListener(new ComparisonSideSelectionListener(false));
    // Selection synchronization: Values[user] -> Model
    _leftValuesViewer.addSWTSelectionListener(new ValuesSelectionListener(true));
    _rightValuesViewer.addSWTSelectionListener(new ValuesSelectionListener(false));
    // Selection synchronization: Features[user] -> Values
    _featuresViewer.addSWTSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        IStructuredSelection selection = _featuresViewer.getSelection();
        if (selection.size() == 1) {
          IMatch match = _featuresViewer.getInput() == null? null:
            _featuresViewer.getInput().getMatch();
          if (match instanceof EMatch) {
            EStructuralFeature feature = (EStructuralFeature)selection.getFirstElement();
            MatchAndFeature newInputDetails = new MatchAndFeatureImpl((EMatch)match, feature);
            setSelection(new ComparisonSelectionImpl(newInputDetails, getDrivingRole()), true);
          }
        }
      }
    });
  }
  
  /**
   * Set up the different tool bars
   */
  protected void setupToolBars() {
    // Tools: upper row
    setupToolsSynthesis(_synthesisModelTreeViewer.getToolbar());
    setupToolsSynthesisSide(_leftModelTreeViewer.getToolbar(), true);
    setupToolsSynthesisSide(_rightModelTreeViewer.getToolbar(), false);
    // Tools: lower row
    setupToolsDetails(_featuresViewer.getToolbar());
    setupToolsDetailSide(_leftValuesViewer.getToolbar(), true);
    setupToolsDetailSide(_rightValuesViewer.getToolbar(), false);
    // Menus
    setupMenuSynthesis(_synthesisModelTreeViewer.getToolbar());
    setupMenuDetails(_featuresViewer.getToolbar());
    // Tool refresh on selection change
    addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        refreshTools();
      }
    });
    refreshTools();
  }
  
  /**
   * Set up the navigation tools in the given tool bar
   */
  protected void setupToolsDetails(ToolBar toolbar_p) {
    // Navigation tools
    createToolNavigationNext(toolbar_p);
    createToolNavigationPrevious(toolbar_p);
  }
  
  /**
   * Set up the tools related to the "details" row in the given tool bar
   * for the given side
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left or right
   */
  protected void setupToolsDetailSide(ToolBar toolbar_p, boolean onLeft_p) {
    createToolMerge(toolbar_p, !onLeft_p);
    createToolIgnore(toolbar_p, onLeft_p);
    createToolDelete(toolbar_p, onLeft_p);
  }
  
  /**
   * Set up the "synthesis" tools in the given tool bar
   * @param toolbar_p a non-null tool bar
   */
  @SuppressWarnings("unused")
  protected void setupToolsSynthesis(ToolBar toolbar_p) {
    new ToolItem(toolbar_p, SWT.SEPARATOR);
    createToolInconsistency(toolbar_p);
    createToolExpand(toolbar_p);
    createToolCollapse(toolbar_p);
    createToolSort(toolbar_p);
    createToolSync(toolbar_p);
    new ToolItem(toolbar_p, SWT.SEPARATOR);
  }
  
  /**
   * Set up the tools related to the "synthesis" row in the given tool bar
   * for the given side
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left or right
   */
  protected void setupToolsSynthesisSide(ToolBar toolbar_p, boolean onLeft_p) {
    createToolLock(toolbar_p, onLeft_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#undoRedoPerformed(boolean)
   */
  @Override
  protected void undoRedoPerformed(final boolean undo_p) {
    super.undoRedoPerformed(undo_p);
    firePropertyChangeEvent(PROPERTY_DIFFERENCE_NUMBERS, null);
  }
  
  
  /**
   * A selection listener for Values[user] -> Global synchronization
   */
  protected class ValuesSelectionListener extends SelectionAdapter {
    /** Whether the viewer that is being tracked is on the left-hand side  */
    private final boolean _sideIsLeft;
    /**
     * Constructor
     * @param sideIsLeft_p whether the viewer that is being tracked is on the left-hand side
     */
    public ValuesSelectionListener(boolean sideIsLeft_p) {
      _sideIsLeft = sideIsLeft_p;
    }
    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent event_p) {
      HeaderViewer<?> valuesViewer = _sideIsLeft? _leftValuesViewer: _rightValuesViewer;
      IStructuredSelection selection = valuesViewer.getSelection();
      if (!selection.isEmpty()) {
        if (selection.getFirstElement() instanceof EObject) // Skip attribute values
          ComparisonViewer.this.setSelection(
              new ComparisonSelectionImpl(
                  selection.toList(), getInput().getRoleForSide(_sideIsLeft)), true);
      }
    }
  }
  
  
  /**
   * A selection listener for Model[user] -> Global synchronization
   */
  protected class ComparisonSideSelectionListener extends SelectionAdapter {
    /** Whether the viewer that is being tracked is on the left-hand side  */
    private final boolean _sideIsLeft;
    /**
     * Constructor
     * @param sideIsLeft_p whether the viewer that is being tracked is on the left-hand side
     */
    public ComparisonSideSelectionListener(boolean sideIsLeft_p) {
      _sideIsLeft = sideIsLeft_p;
    }
    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent event_p) {
      if (_isLeftRightSynced) {
        EnhancedComparisonSideViewer modelViewer = _sideIsLeft? _leftModelTreeViewer:
          _rightModelTreeViewer;
        IStructuredSelection selection = modelViewer.getSelection();
        if (!selection.isEmpty())
          ComparisonViewer.this.setSelection(new ComparisonSelectionImpl(
              selection.toList(), getInput().getRoleForSide(_sideIsLeft)), true);
      }
    }
  }
  
  /**
   * A message dialog which uses MergeImpactViewer.
   */
  protected class MergeImpactMessageDialog extends MessageDialog {
    /** The non-null input */
    private final ImpactInput _dialogInput;
    
    /**
     * Constructor
     * @param parentShell_p a non-null shell
     */
    public MergeImpactMessageDialog(Shell parentShell_p, ImpactInput input_p) {
      super(parentShell_p, Messages.ComparisonViewer_MergeHeader, null,
          String.format(
              Messages.ComparisonViewer_ImpactDescription, input_p.isOnTheLeft()?
                  Messages.ComparisonViewer_Left: Messages.ComparisonViewer_Right),
          MessageDialog.INFORMATION,
          new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
      _dialogInput = input_p;
      setShellStyle(getShellStyle() | SWT.RESIZE);
    }
    
    /**
     * @see MessageDialog#createCustomArea(Composite)
     */
    @Override
    protected Control createCustomArea(Composite parent_p) {
      MergeImpactViewer viewer = new MergeImpactViewer(parent_p, getResourceManager());
      // Reuse label provider from synthesis viewer
      IBaseLabelProvider lProvider = _synthesisModelTreeViewer.getInnerViewer().getLabelProvider();
      if (lProvider instanceof DelegatingLabelProvider)
        viewer.setDelegateLabelProvider(((DelegatingLabelProvider)lProvider).getDelegate());
      viewer.setInput(_dialogInput);
      return viewer.getControl();
    }
    
    /**
     * Open the dialog and return whether the user pressed OK
     */
    public boolean openAndConfirm() {
      return open() == 0;
    }
  }
  
}
