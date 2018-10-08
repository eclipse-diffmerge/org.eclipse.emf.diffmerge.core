/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 *    Stephane Bouchet (Intel Corporation) - Bug #442492 : hide number of differences in the UI
 *    Stephane Bouchet (Intel Corporation) - Bug #489274 : added API viewers creation methods
 *    Jeremy Aubry (Obeo) - Bug #500417 : Cannot call a merge with a given selection programmatically
 *    Stephane Bouchet (Intel Corporation) - Bug # : added external editor for text differences
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_CUSTOM_ICONS;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_CUSTOM_LABELS;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_DEFAULT_COVER_CHILDREN;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_DEFAULT_INCREMENTAL_MODE;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_DEFAULT_SHOW_MERGE_IMPACT;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_LOG_EVENTS;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SHOW_DIFFERENCE_NUMBERS;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SHOW_MERGE_IMPACT;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SHOW_SIDES_POSSIBLE;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SUPPORT_UNDO_REDO;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SUPPORT_UNDO_REDO_OPTIONAL;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_SYNC_SYNTHESIS_AND_SIDES;
import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.P_TECHNICAL_LABELS;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.INavigatable;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.diffdata.EValuePresence;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl;
import org.eclipse.emf.diffmerge.ui.log.CompareLogEvent;
import org.eclipse.emf.diffmerge.ui.log.DiffMergeLogger;
import org.eclipse.emf.diffmerge.ui.log.MergeLogEvent;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetupManager;
import org.eclipse.emf.diffmerge.ui.setup.EMFDiffMergeEditorInput;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator;
import org.eclipse.emf.diffmerge.ui.util.InconsistencyDialog;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.diffmerge.ui.util.SymmetricMatchComparer;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.util.UIUtil.MenuDropDownAction;
import org.eclipse.emf.diffmerge.ui.viewers.FeaturesViewer.FeaturesInput;
import org.eclipse.emf.diffmerge.ui.viewers.MergeImpactViewer.ImpactInput;
import org.eclipse.emf.diffmerge.ui.viewers.TextMergerDialog.TextDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.ValuesViewer.ValuesInput;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.progress.IProgressService;


/**
 * A Viewer for comparisons which is composed of six sub-viewers that show the scopes being
 * compared, a synthesis of the differences, features of the selected element, and the contents
 * of the selected feature in each scope.
 * Input: EMFDiffNode ; Elements: IMatch | IDifference.
 * @author Olivier Constant
 */
public class ComparisonViewer extends AbstractComparisonViewer {
  
  /** The location for contributions to the Synthesis tool bar in Menu API URI format */
  public static final String LOCATION_TOOLBAR_SYNTHESIS =
      "toolbar:org.eclipse.emf.diffmerge.ui.toolbars.synthesis"; //$NON-NLS-1$
  
  /** The location for contributions to the Details tool bar in Menu API URI format */
  public static final String LOCATION_TOOLBAR_DETAILS =
      "toolbar:org.eclipse.emf.diffmerge.ui.toolbars.details"; //$NON-NLS-1$
  
  /** The name of the corresponding group of tool/menu items in the GUI */
  public static final String LOCATION_TOOLBAR_GROUP_CONSISTENCY = "consistencyGroup"; //$NON-NLS-1$
  
  /** The name of the corresponding group of tool/menu items in the GUI */
  public static final String LOCATION_TOOLBAR_GROUP_NAVIGATION = "navigationGroup"; //$NON-NLS-1$
  
  /** The name of the corresponding group of tool/menu items in the GUI */
  public static final String LOCATION_TOOLBAR_GROUP_EXPANSION = "expansionGroup"; //$NON-NLS-1$
  
  /** The name of the corresponding group of tool/menu items in the GUI */
  public static final String LOCATION_TOOLBAR_GROUP_FILTERING = "filteringGroup"; //$NON-NLS-1$
  
  /** The name of the corresponding group of tool/menu items in the GUI */
  public static final String LOCATION_TOOLBAR_GROUP_MENU = "menuGroup"; //$NON-NLS-1$
  
  /** The name of the "filtering state" property */
  public static final String PROPERTY_FILTERING = "PROPERTY_FILTERING"; //$NON-NLS-1$
  
  /** The name of the "delete left activation" property */
  public static final String PROPERTY_ACTIVATION_DELETE_LEFT = "PROPERTY_ACTIVATION_DELETE_LEFT"; //$NON-NLS-1$
  
  /** The name of the "delete right activation" property */
  public static final String PROPERTY_ACTIVATION_DELETE_RIGHT = "PROPERTY_ACTIVATION_DELETE_RIGHT"; //$NON-NLS-1$
  
  /** The name of the "merge to left activation" property */
  public static final String PROPERTY_ACTIVATION_MERGE_TO_LEFT = "PROPERTY_ACTIVATION_MERGE_TO_LEFT"; //$NON-NLS-1$
  
  /** The name of the "merge to right activation" property */
  public static final String PROPERTY_ACTIVATION_MERGE_TO_RIGHT = "PROPERTY_ACTIVATION_MERGE_TO_RIGHT"; //$NON-NLS-1$
  
  /** The name of the "ignore left activation" property */
  public static final String PROPERTY_ACTIVATION_IGNORE_LEFT = "PROPERTY_ACTIVATION_IGNORE_LEFT"; //$NON-NLS-1$
  
  /** The name of the "ignore right activation" property */
  public static final String PROPERTY_ACTIVATION_IGNORE_RIGHT = "PROPERTY_ACTIVATION_IGNORE_RIGHT"; //$NON-NLS-1$
  
  /** The name of the "open in dedicated viewer activation" property */
  public static final String PROPERTY_ACTIVATION_OPEN_DEDICATED = "PROPERTY_ACTIVATION_OPEN_DEDICATED"; //$NON-NLS-1$
  
  /** The synthesis model tree viewer */
  protected EnhancedComparisonTreeViewer _viewerSynthesisMain;
  
  /** The left model tree viewer */
  protected EnhancedComparisonSideViewer _viewerSynthesisLeft;
  
  /** The right model tree viewer */
  protected EnhancedComparisonSideViewer _viewerSynthesisRight;

  /** The features viewer */
  protected EnhancedFeaturesViewer _viewerFeatures;

  /** The left values viewer */
  protected EnhancedValuesViewer _viewerValuesLeft;
  
  /** The right values viewer */
  protected EnhancedValuesViewer _viewerValuesRight;
  
  /** A filter for move origins */
  protected ViewerFilter _filterMoveOrigins;
  
  /** A filter for unchanged elements */
  protected ViewerFilter _filterUnchangedElements;
  
  /** An alphanumeric sorter */
  protected ViewerComparator _sorterSynthesis;
  
  /** The potentially null last selection */
  private ComparisonSelection _lastUserSelection;
  
  /** The non-null selection provider covering certain sub-viewers */
  protected SelectionBridge.SingleSource _multiViewerSelectionProvider;
  
  /** The non-null (after init) selection listener for filter items */
  protected FilterSelectionListener _filterSelectionListener;
  
  
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
   * Return whether context menus can be contributed via the usual ADDITIONS group
   * in the given viewer
   */
  protected boolean acceptContextMenuAdditions(Viewer viewer_p) {
    return true;
  }
  
  /**
   * Add differences to merge on the given match to the given list according
   * to the given criteria
   * @param toMerge_p a non-null, modifiable list
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFEREBCE
   * @param incrementalMode_p whether optional deletions must be skipped
   */
  protected void addDifferencesToMerge(List<IDifference> toMerge_p, IMatch match_p,
      Role destination_p, boolean incrementalMode_p) {
    for (IDifference difference : match_p.getAllDifferences()) {
      if (!getInput().getCategoryManager().isFiltered(difference)) {
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
   */
  protected void addDifferencesToMergeRec(List<IDifference> toMerge_p, IMatch match_p,
      Role destination_p, boolean incrementalMode_p) {
    addDifferencesToMerge(toMerge_p, match_p, destination_p, incrementalMode_p);
    for (IMatch child : getInput().getCategoryManager().getChildrenForMerge(match_p)) {
      addDifferencesToMergeRec(toMerge_p, child, destination_p, incrementalMode_p);
    }
  }
  
  /**
   * Convert the given structured selection to a comparison selection
   * @param selection_p a non-null selection
   * @return a non-null comparison selection
   */
  protected ComparisonSelection asComparisonSelection(IStructuredSelection selection_p) {
    Collection<IMatch> matches = new ArrayList<IMatch>();
    EMFDiffNode input = getInput();
    if (input != null) {
      EComparison comparison = getComparison();
      if (comparison != null) {
        Role mainRole = input.getDrivingRole();
        for (Object selected : selection_p.toArray()) {
          if (selected instanceof EObject) {
            EObject selectedElement = (EObject)selected;
            IMatch match = comparison.getMapping().getMatchFor(selectedElement, mainRole);
            if (match == null)
              match = comparison.getMapping().getMatchFor(
                  selectedElement, mainRole.opposite());
            if (match != null)
              matches.add(match);
          }
        }
      }
    }
    ComparisonSelection result = new ComparisonSelectionImpl(matches, null, input);
    return result;
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
   * Create and return the "accept" action
   * @return a potentially null action
   */
  protected DirectedAction createActionAccept() {
    final DirectedAction result = new DirectedAction() {
      /**
       * @see org.eclipse.jface.action.Action#getImageDescriptor()
       */
      @Override
      public ImageDescriptor getImageDescriptor() {
        return isLeftToRight()?
            EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.CHECKIN_ACTION):
              EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.CHECKOUT_ACTION);
      }
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(!isLeftToRight(), true);
      }
    };
    result.setText(Messages.ComparisonViewer_AcceptAction_Text);
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        boolean leftToRight = result.isLeftToRight();
        if (!leftToRight && PROPERTY_ACTIVATION_MERGE_TO_LEFT.equals(event_p.getProperty()) ||
            leftToRight && PROPERTY_ACTIVATION_MERGE_TO_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean) {
            result.setEnabled(((Boolean)newValue).booleanValue());
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Create and return the "accept deletion" action
   * @return a potentially null action
   */
  protected DirectedAction createActionAcceptDeletion() {
    final DirectedAction result = new DirectedAction() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(!isLeftToRight(), false);
      }
    };
    result.setText(Messages.ComparisonViewer_DeleteAction_Text);
    result.setImageDescriptor(EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.DELETE));
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        boolean leftToRight = result.isLeftToRight();
        if (!leftToRight && PROPERTY_ACTIVATION_DELETE_LEFT.equals(event_p.getProperty()) ||
            leftToRight && PROPERTY_ACTIVATION_DELETE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean) {
            result.setEnabled(((Boolean)newValue).booleanValue());
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Create and return the "ignore" action
   * @return a potentially null action
   */
  protected DirectedAction createActionIgnore() {
    final IgnoreAction result = new IgnoreAction();
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        Object newValue = event_p.getNewValue();
        if (newValue instanceof Boolean) {
          boolean boolValue = ((Boolean)newValue).booleanValue();
          String property = event_p.getProperty();
          if (PROPERTY_ACTIVATION_IGNORE_LEFT.equals(property)) {
            result.setActiveLeft(boolValue);
          } else if (PROPERTY_ACTIVATION_IGNORE_RIGHT.equals(property)) {
            result.setActiveRight(boolValue);
          }
        }
      }
    });
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#createControls(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Composite createControls(Composite parent_p) {
    // Non-graphical instance variables
    initialize();
    // Main control
    SashForm result = new SashForm(parent_p, SWT.VERTICAL);
    // Upper and lower parts
    SashForm upperPart = createRowUpper(result);
    SashForm lowerPart = createRowLower(result);
    setupColumns(upperPart, lowerPart);
    result.setWeights(getDefaultRowWeights());
    // Tools: buttons and menus
    setupToolBars();
    return result;
  }
  
  /**
   * Create the "collapse all" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemCollapse(IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        BusyIndicator.showWhile(getDisplay(), new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            _viewerSynthesisMain.getInnerViewer().collapseAll();
          }
        });
      }
    };
    action.setImageDescriptor(getImageDescriptor(ImageID.COLLAPSEALL));
    action.setToolTipText(Messages.ComparisonViewer_CollapseTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "delete" item for the given side in the given context and return it
   * @param context_p a non-null object
   * @param onLeft_p whether the side is left
   * @return a potentially null item
   */
  protected ActionContributionItem createItemDelete(IContributionManager context_p, final boolean onLeft_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(onLeft_p, false);
      }
    };
    // Image
    action.setImageDescriptor(getImageDescriptor(ImageID.DELETE));
    // Tool tip
    action.setToolTipText(onLeft_p? Messages.ComparisonViewer_DeleteLeftTooltip:
      Messages.ComparisonViewer_DeleteRightTooltip);
    action.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (onLeft_p && PROPERTY_ACTIVATION_DELETE_LEFT.equals(event_p.getProperty()) ||
            !onLeft_p && PROPERTY_ACTIVATION_DELETE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean) {
            action.setEnabled(((Boolean)newValue).booleanValue());
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "expand all" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemExpand(IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        BusyIndicator.showWhile(getDisplay(), new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            _viewerSynthesisMain.getInnerViewer().expandAll();
          }
        });
      }
    };
    action.setImageDescriptor(getImageDescriptor(ImageID.EXPANDALL));
    action.setToolTipText(Messages.ComparisonViewer_ExpandTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "filter" item in the given context and return it
   * @param context_p a non-null context
   * @return a potentially null item
   */
  protected ActionContributionItem createItemFilter(IContributionManager context_p) {
    final IAction action = new Action(null, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#runWithEvent(org.eclipse.swt.widgets.Event)
       */
      @Override
      public void runWithEvent(Event event_p) {
        _filterSelectionListener.runWithEvent(event_p);
      }
    };
    String text = (context_p instanceof ToolBar)?
        Messages.ComparisonViewer_FilterToolTip:
          Messages.ComparisonViewer_FilterText;
    action.setText(text);
    action.setToolTipText(Messages.ComparisonViewer_EnhancedFilterToolTip);
    action.setImageDescriptor(getImageDescriptor(ImageID.FILTER));
    action.setChecked(false);
    if (_filterSelectionListener == null) {
      _filterSelectionListener = new FilterSelectionListener();
    }
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    _filterSelectionListener.addItem(result);
    return result;
  }
  
  /**
   * Create the "ignore" item for the given side in the given context and return it
   * @param context_p a non-null object
   * @param onLeft_p whether the side is left
   * @return a potentially null item
   */
  protected ActionContributionItem createItemIgnore(IContributionManager context_p, final boolean onLeft_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        ignore(onLeft_p);
      }
    };
    // Image
    action.setImageDescriptor(getImageDescriptor(ImageID.CHECKED));
    // Tool tip
    action.setToolTipText(onLeft_p? Messages.ComparisonViewer_IgnoreLeftTooltip:
      Messages.ComparisonViewer_IgnoreRightTooltip);
    action.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (onLeft_p && PROPERTY_ACTIVATION_IGNORE_LEFT.equals(event_p.getProperty()) ||
            !onLeft_p && PROPERTY_ACTIVATION_IGNORE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean) {
            action.setEnabled(((Boolean)newValue).booleanValue());
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "inconsistency" item in the given context and return it
   * @param context_p a non-null object
   */
  protected ActionContributionItem createItemInconsistency(
      IContributionManager context_p) {
    final Action action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
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
    };
    action.setImageDescriptor(getImageDescriptor(ImageID.WARNING));
    action.setDisabledImageDescriptor(getImageDescriptor(ImageID.EMPTY));
    action.setEnabled(false);
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          IComparison comparison = getComparison();
          boolean enabled = comparison != null && !comparison.isConsistent();
          action.setEnabled(enabled);
          action.setToolTipText(
              enabled ? Messages.ComparisonViewer_InconsistencyTooltip : null);
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the locking item in the given context for the given side and return it
   * @param context_p a non-null object
   * @param onLeft_p whether the side is left or right
   * @return a potentially null item
   */
  protected ActionContributionItem createItemLock(IContributionManager context_p,
      final boolean onLeft_p) {
    final ImageDescriptor openLockImage = getImageDescriptor(ImageID.LOCK_OPEN);
    final ImageDescriptor closedLockImage = getImageDescriptor(ImageID.LOCK_CLOSED);
    final String lockedTooltip = Messages.ComparisonViewer_LockTooltip_Locked;
    final String unlockedTooltip = Messages.ComparisonViewer_LockTooltip_Unlocked;
    final IAction action = new Action(null, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        boolean editable = !isChecked();
        getInput().setEditable(editable, onLeft_p);
        setImageDescriptor(editable? openLockImage: closedLockImage);
        setToolTipText(editable? unlockedTooltip: lockedTooltip);
        refreshTools();
      }
    };
    action.setToolTipText(unlockedTooltip);
    action.setImageDescriptor(openLockImage);
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null) {
            boolean editable = input.isEditable(onLeft_p);
            action.setChecked(!editable);
            action.setImageDescriptor(editable? openLockImage:closedLockImage);
            action.setToolTipText(editable? unlockedTooltip: lockedTooltip);
            action.setEnabled(input.isEditionPossible(onLeft_p));
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "log events" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemLogEvents(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_LogEventsMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        boolean logEvents = isChecked();
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_LOG_EVENTS, logEvents);
          if (logEvents) {
            getLogger().log(new CompareLogEvent(getEditingDomain(), input));
          }
        }
      }
    };
    String tooltip = Messages.ComparisonViewer_LogTooltipNoFile;
    Logger logger = getLogger();
    if (logger instanceof DiffMergeLogger) {
      IPath logFile = ((DiffMergeLogger)logger).getLogFile();
      tooltip = String.format(Messages.ComparisonViewer_LogTooltipFile, logFile.toOSString());
    }
    action.setToolTipText(tooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null) {
            action.setChecked(input.isUserPropertyTrue(P_LOG_EVENTS));
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "merge" item to the given side in the given context and return it
   * @param context_p a non-null object
   * @param toLeft_p whether the side is left
   * @return a potentially null item
   */
  protected ActionContributionItem createItemMerge(
      IContributionManager context_p, final boolean toLeft_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(toLeft_p, true);
      }
    };
    // Image
    ImageID imageID = toLeft_p? ImageID.CHECKOUT_ACTION: ImageID.CHECKIN_ACTION;
    action.setImageDescriptor(getImageDescriptor(imageID));
    // Tool tip
    action.setToolTipText(toLeft_p? Messages.ComparisonViewer_MergeLeftTooltip:
      Messages.ComparisonViewer_MergeRightTooltip);
    action.setEnabled(false);
    // Activation
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (toLeft_p && PROPERTY_ACTIVATION_MERGE_TO_LEFT.equals(event_p.getProperty()) ||
            !toLeft_p && PROPERTY_ACTIVATION_MERGE_TO_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean) {
            action.setEnabled(((Boolean)newValue).booleanValue());
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "next" navigation item in the given context and return it
   * @param context_p a non-null context
   * @return a potentially null item
   */
  protected ActionContributionItem createItemNavigationNext(
      IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        navigate(true);
      }
    };
    action.setImageDescriptor(getImageDescriptor(ImageID.NEXT_DIFF_NAV));
    action.setToolTipText(Messages.ComparisonViewer_NextTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "previous" navigation item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null tool item
   */
  protected ActionContributionItem createItemNavigationPrevious(
      IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        navigate(false);
      }
    };
    action.setImageDescriptor(getImageDescriptor(ImageID.PREV_DIFF_NAV));
    action.setToolTipText(Messages.ComparisonViewer_PreviousTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "open in dedicated editor" item in the given context
   * and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemOpenDedicated(IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode input = getInput();
        ComparisonSelection selection = getSelection();
        if (isDedicatedViewerApplicable(input, selection)) {
          openDedicatedViewer(getInput(), getSelection().asMatch(),
              (EAttribute)selection.asFeature());
        }
      }
    };
    // Image
    action.setImageDescriptor(getImageDescriptor(ImageID.COMPARE));
    // Tool tip text
    action.setToolTipText(Messages.ComparisonViewer_OpenDedicated_ToolTip);
    action.setEnabled(false);
    // Enabled/visible state
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event) {
        if (PROPERTY_ACTIVATION_OPEN_DEDICATED.equals(event.getProperty())) {
          boolean newEnabled = ((Boolean)event.getNewValue()).booleanValue();
          action.setEnabled(newEnabled);
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "restart" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemRestart(IContributionManager context_p) {
    final IAction action = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        restart();      }
    };
    action.setText(Messages.ComparisonViewer_ToolUpdate);
    action.setToolTipText(Messages.ComparisonViewer_ToolUpdate_Tooltip);
    action.setImageDescriptor(getImageDescriptor(ImageID.UPDATE));
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          boolean enable = false;
          EMFDiffNode input = getInput();
          if (input != null) {
            enable = input.getEditorInput() != null;
          }
          action.setEnabled(enable);
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show all values and properties" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowAllFeatures(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowAllFeatures, IAction.AS_RADIO_BUTTON) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        _viewerFeatures.getInnerViewer().setDifferenceAgnostic(true);
        _viewerValuesLeft.getInnerViewer().setDifferenceAgnostic(true);
        _viewerValuesRight.getInnerViewer().setDifferenceAgnostic(true);
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ShowAllFeaturesTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show all values" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowAllValues(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowAllValues, IAction.AS_RADIO_BUTTON) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        _viewerFeatures.getInnerViewer().setDifferenceAgnostic(false);
        _viewerValuesLeft.getInnerViewer().setDifferenceAgnostic(true);
        _viewerValuesRight.getInnerViewer().setDifferenceAgnostic(true);
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ShowAllValuesTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show difference numbers per match" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowDifferenceNumbers(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowDifferenceNumbersMenuItem,
        IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_SHOW_DIFFERENCE_NUMBERS, isChecked());
          refresh();
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ShowDifferenceNumbersTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_SHOW_DIFFERENCE_NUMBERS));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show values on differences" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowDiffValues(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowValueDiffs, IAction.AS_RADIO_BUTTON) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        _viewerFeatures.getInnerViewer().setDifferenceAgnostic(false);
        _viewerValuesLeft.getInnerViewer().setDifferenceAgnostic(false);
        _viewerValuesRight.getInnerViewer().setDifferenceAgnostic(false);
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ShowValueDiffsTooltip);
    action.setChecked(true);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show merge impact" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowImpact(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ImpactMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        boolean showImpact = isChecked();
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_SHOW_MERGE_IMPACT, showImpact);
          input.setUserPropertyValue(P_DEFAULT_SHOW_MERGE_IMPACT, showImpact);
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ImpactMenuItemTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_SHOW_MERGE_IMPACT));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show left/right contents" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowSides(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowSidesMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        showSides(isChecked());
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_ShowSidesMenuItemTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          boolean isShowSidesPossible = isUserPropertyTrue(P_SHOW_SIDES_POSSIBLE);
          action.setChecked(isShowSidesPossible);
          action.setEnabled(isShowSidesPossible);
          showSides(isShowSidesPossible);
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "show uncounted elements" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemShowUncounted(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_ShowUncountedMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        if (isChecked()) {
          _viewerSynthesisMain.getInnerViewer().removeFilter(_filterUnchangedElements);
        } else {
          _viewerSynthesisMain.getInnerViewer().addFilter(_filterUnchangedElements);
        }
      }
    };
    action.setChecked(false);
    action.setToolTipText(Messages.ComparisonViewer_ShowUncountedMenuItemTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "support undo/redo" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemSupportUndoRedo(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_SupportUndoRedoMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_SUPPORT_UNDO_REDO, isChecked());
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_SupportUndoRedoMenuItemTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          EMFDiffNode input = getInput();
          if (input != null) {
            action.setChecked(input.isUndoRedoSupported());
            action.setEnabled(input.getEditingDomain() != null &&
                !isUserPropertyFalse(P_SUPPORT_UNDO_REDO_OPTIONAL));
          }
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "sort" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemSort(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_SortTooltip, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        if (isChecked()) {
          _viewerSynthesisMain.getInnerViewer().setComparator(_sorterSynthesis);
        } else {
          _viewerSynthesisMain.getInnerViewer().setComparator(null);
        }
      }
    };
    action.setChecked(false);
    action.setImageDescriptor(getImageDescriptor(ImageID.SORT));
    action.setToolTipText(Messages.ComparisonViewer_EnhancedSortTooltip);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "sync" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemSync(IContributionManager context_p) {
    final MenuDropDownAction action = new MenuDropDownAction();
    action.setText(Messages.ComparisonViewer_LinkViews);
    action.setImageDescriptor(getImageDescriptor(ImageID.SYNCED));
    createItemSyncInternal(action.getMenuManager());
    createItemSyncExternal(action.getMenuManager());
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "sync with external" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemSyncExternal(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_LinkViewsExternal, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        _isExternallySynced = isChecked();
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_LinkViewsExternalToolTip);
    action.setChecked(_isExternallySynced);
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "sync internally" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemSyncInternal(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_LinkViewsInternal, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        boolean synced = isChecked();
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_SYNC_SYNTHESIS_AND_SIDES, synced);
          if (isLeftRightSynced()) {
            BusyIndicator.showWhile(getDisplay(), new Runnable() {
              /**
               * @see java.lang.Runnable#run()
               */
              public void run() {
                IStructuredSelection selection = _viewerSynthesisMain.getSelection();
                _viewerSynthesisLeft.setSelection(getSelectionAsSide(selection, true), true);
                _viewerSynthesisRight.setSelection(getSelectionAsSide(selection, false), true);
              }
            });
          }
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_LinkViewsInternalTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_SYNC_SYNTHESIS_AND_SIDES));
          action.setEnabled(isUserPropertyTrue(P_SHOW_SIDES_POSSIBLE));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "use custom icons" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemUseCustomIcons(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_IconsMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_CUSTOM_ICONS, isChecked());
          _viewerSynthesisMain.refresh();
          _viewerFeatures.refresh();
          _viewerValuesLeft.refresh();
          _viewerValuesRight.refresh();
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_IconsMenuItemTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_CUSTOM_ICONS));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "use custom labels" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemUseCustomLabels(IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_LabelsMenuItem, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode input = getInput();
        if (input != null) {
          input.setUserPropertyValue(P_CUSTOM_LABELS, isChecked());
          _viewerSynthesisMain.refresh();
          _viewerFeatures.refresh();
          _viewerValuesLeft.refresh();
          _viewerValuesRight.refresh();
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_LabelsMenuItemTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_CUSTOM_LABELS));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * Create the "Use technical representation" item in the given context and return it
   * @param context_p a non-null object
   * @return a potentially null item
   */
  protected ActionContributionItem createItemUseTechnicalRepresentation(
      IContributionManager context_p) {
    final IAction action = new Action(
        Messages.ComparisonViewer_UseTechnicalRepresentation, IAction.AS_CHECK_BOX) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        EMFDiffNode node = getInput();
        if (node != null) {
          node.setUserPropertyValue(P_TECHNICAL_LABELS, isChecked());
        }
      }
    };
    action.setToolTipText(Messages.ComparisonViewer_UseTechnicalRepresentationTooltip);
    // Initialization
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_CURRENT_INPUT.equals(event_p.getProperty())) {
          action.setChecked(isUserPropertyTrue(P_TECHNICAL_LABELS));
        }
      }
    });
    ActionContributionItem result = new ActionContributionItem(action);
    context_p.add(result);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#createNavigatable()
   */
  @Override
  protected INavigatable createNavigatable() {
    INavigatable result = new INavigatable() {
      /**
       * @see org.eclipse.compare.INavigatable#getInput()
       */
      public Object getInput() {
        return ComparisonViewer.this.getInput();
      }
      /**
       * @see org.eclipse.compare.INavigatable#hasChange(int)
       */
      public boolean hasChange(int changeFlag_p) {
        return true;
      }
      /**
       * @see org.eclipse.compare.INavigatable#openSelectedChange()
       */
      public boolean openSelectedChange() {
        return false;
      }
      /**
       * @see org.eclipse.compare.INavigatable#selectChange(int)
       */
      public boolean selectChange(int changeFlag_p) {
        boolean innerResult = false;
        switch (changeFlag_p) {
        case INavigatable.NEXT_CHANGE:
          innerResult = navigate(true); break;
        case INavigatable.PREVIOUS_CHANGE:
          innerResult = navigate(false); break;
        }
        return innerResult;
      }
    };
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
    _viewerFeatures = createViewerFeatures(result);
    // Values section
    _viewerValuesLeft = createViewerValues(result, true);
    _viewerValuesRight = createViewerValues(result, false);
    return result;
  }
  
  /**
   * Create and return the upper row of the GUI
   * @param parent_p a non-null composite
   * @return a non-null widget
   */
  protected SashForm createRowUpper(Composite parent_p) {
    SashForm result = new SashForm(parent_p, SWT.HORIZONTAL);
    _viewerSynthesisMain = createViewerSynthesis(result);
    _viewerSynthesisLeft = createViewerSynthesisSide(result, true);
    _viewerSynthesisRight = createViewerSynthesisSide(result, false);
    return result;
  }
  
  /**
   * Create context menus for the given viewer
   * @param viewer_p a non-null viewer
   * @param useLocalSelectionProvider_p whether the selection provider of the viewer must be used
   * @return a potentially null menu manager for the context menus
   */
  protected MenuManager createViewerContextMenus(HeaderViewer<?> viewer_p,
      boolean useLocalSelectionProvider_p) {
    MenuManager result = new MenuManager();
    result.setRemoveAllWhenShown(true);
    Control control = viewer_p.getInnerViewer().getControl();
    Menu contextMenu = result.createContextMenu(control);
    control.setMenu(contextMenu);
    ISelectionProvider selectionProvider = useLocalSelectionProvider_p?
        viewer_p.getInnerViewer(): getMultiViewerSelectionProvider();
    // Diff/merge-specific menu items
    populateContextMenu(result, viewer_p, selectionProvider);
    // External contributions
    if (acceptContextMenuAdditions(viewer_p)) {
      IWorkbenchPartSite site = getSite();
      if (site != null) {
        result.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        site.registerContextMenu(result, selectionProvider);
      }
    }
    return result;
  }
  
  /**
   * Create, configure and return a features viewer
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedFeaturesViewer createViewerFeatures(Composite parent_p) {
    final EnhancedFeaturesViewer result = doCreateViewerFeatures(parent_p);
    // User selection: send to global viewer
    result.addSWTSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        IStructuredSelection selection = result.getSelection();
        if (selection.size() == 1) {
          MatchAndFeature maf = (MatchAndFeature)selection.getFirstElement();
          setSelection(new ComparisonSelectionImpl(
              maf, getDrivingRole(), getInput()), true, result.getInnerViewer());
        }
      }
    });
    // Global selection change: update local selection
    addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection rawSelection = event_p.getSelection();
        Object source = event_p.getSource();
        if (rawSelection instanceof ComparisonSelection && source != result.getInnerViewer()) {
          ComparisonSelection selection = (ComparisonSelection)rawSelection;
          if (selection.getSelectedMatches().size() <= 1) {
            // No more than one match
            EMatch match = selection.asMatch();
            if (match != null) {
              // One match: new input
              FeaturesInput newInput = new FeaturesInput(getInput(), match);
              boolean changeInput = !newInput.equals(result.getInput());
              if (changeInput) {
                result.setInput(newInput);
              }
              // New selection
              MatchAndFeature maf = selection.asMatchAndFeature();
              if (maf != null) {
                IStructuredSelection newSelection = new StructuredSelection(maf);
                result.setSelection(newSelection, true);
              } else if (changeInput) {
                // New input and no feature selected: select first feature if any
                MatchAndFeature firstMAF =
                    getDefaultFeatureSelection(newInput, result.getInnerViewer());
                if (firstMAF != null) {
                  result.setSelection(new StructuredSelection(firstMAF));
                }
              }
            } else {
              // No match: no input
              result.setInput(null);
            }
          } else {
            // More than one match: no input
            result.setInput(null);
          }
        }
      }
    });
    // Double click: open dedicated viewer
    result.getInnerViewer().addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        ISelection selection = event.getViewer().getSelection();
        if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
          Object first = ((IStructuredSelection)selection).getFirstElement();
          if (first instanceof MatchAndFeature) {
            MatchAndFeature maf = (MatchAndFeature)first;
            if (TextMergerDialog.isApplicableTo(maf.getFeature())) {
              openDedicatedViewer(getInput(), maf.getMatch(), (EAttribute)maf.getFeature());
            }
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Create, configure and return the main viewer of the synthesis row
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedComparisonTreeViewer createViewerSynthesis(Composite parent_p) {
    final EnhancedComparisonTreeViewer result = doCreateViewerSynthesis(parent_p);
    result.getInnerViewer().addFilter(_filterUnchangedElements);
    result.getInnerViewer().addFilter(_filterMoveOrigins);
    // Preserve expansion and selection whenever possible when sides are swapped
    result.getInnerViewer().setComparer(new SymmetricMatchComparer());
    // Update header when filtering is activated
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (PROPERTY_FILTERING.equals(event_p.getProperty())) {
          Boolean filtered = (Boolean)event_p.getNewValue();
          if (filtered != null) {
            String newHeader = result.getDefaultHeaderText() +
                (filtered.booleanValue()? Messages.ComparisonViewer_Filtered: ""); //$NON-NLS-1$
            result.setHeaderText(newHeader);
          }
        }
      }
    });
    // User selection: send to global viewer
    result.addSWTSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        IStructuredSelection selection = result.getSelection();
        if (!selection.isEmpty())
          setSelection(new ComparisonSelectionImpl(
              selection.toList(), getDrivingRole(), getInput()), true, result.getInnerViewer());
      }
    });
    // Global selection change: update local selection
    addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection rawSelection = event_p.getSelection();
        Object source = event_p.getSource();
        if (rawSelection instanceof ComparisonSelection && source != result.getInnerViewer()) {
          ComparisonSelection selection = (ComparisonSelection)rawSelection;
          // New selection
          IStructuredSelection newSelection = StructuredSelection.EMPTY;
          int matchesSize = selection.getSelectedMatches().size();
          if (matchesSize > 1) {
            newSelection = new StructuredSelection(selection.getSelectedMatches());
          } else {
            TreePath path = selection.asMatchPath();
            if (path != null) {
              newSelection = new TreeSelection(path);
            } else {
              IMatch match = selection.asMatch();
              if (match != null)
                newSelection = new StructuredSelection(match);
            }
          }
          result.setSelection(newSelection, true);
        }
      }
    });
    // ... and enable context menus
    createViewerContextMenus(result, false);
    return result;
  }
  
  /**
   * Create, configure and return the viewer in the synthesis row for the given side
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   * @return a non-null viewer
   */
  protected EnhancedComparisonSideViewer createViewerSynthesisSide(Composite parent_p,
      final boolean isLeftSide_p) {
    final EnhancedComparisonSideViewer result = doCreateViewerSynthesisSide(parent_p, isLeftSide_p);
    // User selection: send to global viewer
    result.addSWTSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        if (isLeftRightSynced()) {
          IStructuredSelection selection = result.getSelection();
          Role sideRole = getInput().getRoleForSide(isLeftSide_p);
          IStructuredSelection synthesisSelection = getSelectionAsSynthesis(selection, isLeftSide_p);
          if (!synthesisSelection.isEmpty())
            setSelection(new ComparisonSelectionImpl(
                synthesisSelection.toList(), sideRole, getInput()), true, result.getInnerViewer());
        }
      }
    });
    // Global selection change: update local selection
    addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection rawSelection = event_p.getSelection();
        Object source = event_p.getSource();
        if (rawSelection instanceof ComparisonSelection && source != result.getInnerViewer() &&
            (source != _viewerSynthesisMain.getInnerViewer() || isLeftRightSynced())) {
          ComparisonSelection selection = (ComparisonSelection)rawSelection;
          // New selection
          IStructuredSelection newSelection = StructuredSelection.EMPTY;
          int matchesSize = selection.getSelectedMatches().size();
          if (matchesSize > 1) {
            newSelection = new StructuredSelection(selection.getSelectedMatches());
          } else {
            TreePath path = selection.asMatchPath();
            if (path != null) {
              newSelection = new TreeSelection(path);
            } else {
              IMatch match = selection.asMatch();
              if (match != null)
                newSelection = new StructuredSelection(match);
            }
          }
          result.setSelection(getSelectionAsSide(newSelection, isLeftSide_p), true);
        }
      }
    });
    // Register as selection provider ...
    result.getInnerViewer().getControl().addFocusListener(new FocusListener() {
      /**
       * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
       */
      public void focusGained(FocusEvent e_p) {
        _multiViewerSelectionProvider.setSource(result.getInnerViewer());
      }
      /**
       * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
       */
      public void focusLost(FocusEvent e_p) {
        _multiViewerSelectionProvider.setSource(ComparisonViewer.this);
      }
    });
    // ... and enable context menus
    createViewerContextMenus(result, true);
    return result;
  }
  
  /**
   * Create, configure and return the values viewer for the given side
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   * @return a non-null viewer
   */
  protected EnhancedValuesViewer createViewerValues(Composite parent_p,
      final boolean isLeftSide_p) {
    final EnhancedValuesViewer result = doCreateViewerValues(parent_p, isLeftSide_p);
    // User selection: send to global viewer
    result.addSWTSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        IStructuredSelection selection = result.getSelection();
        if (!selection.isEmpty()) {
          if (selection.getFirstElement() instanceof EObject) { // Skip attribute values
            setSelection(new ComparisonSelectionImpl(
                selection.toList(), getInput().getRoleForSide(isLeftSide_p), getInput()), true, result.getInnerViewer());
            // One element selected: show it in scope viewer
            if (selection.size() == 1) {
              EObject selectedElement = (EObject)selection.getFirstElement();
              IMatch match;
              if (selectedElement instanceof IMatch) {
                match = (IMatch)selectedElement;
              } else if (selectedElement instanceof IReferenceValuePresence) {
                IReferenceValuePresence rvp = (IReferenceValuePresence)selectedElement;
                boolean containment = rvp.isContainment();
                match = containment? rvp.getElementMatch(): rvp.getValueMatch();
              } else {
                match = null;
              }
              if (match != null)
                getModelScopeViewer(isLeftSide_p).setSelection(
                    new StructuredSelection(match.get(getInput().getRoleForSide(isLeftSide_p))));
            }
          }
        }
      }
    });
    // Global selection change: update local selection
    addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection rawSelection = event_p.getSelection();
        Object source = event_p.getSource();
        if (rawSelection instanceof ComparisonSelection && source != result.getInnerViewer()) {
          ComparisonSelection selection = (ComparisonSelection)rawSelection;
          MatchAndFeature maf = selection.asMatchAndFeature();
          if (maf != null) {
            // New input
            ValuesInput newInput = new ValuesInput(getInput(), maf);
            if (!newInput.equals(result.getInput()))
              result.setInput(newInput);
            // New selection
            List<EValuePresence> values = selection.getSelectedValuePresences();
            result.setSelection(new StructuredSelection(values), true);
          } else {
            // No 'match and feature' in selection
            // Determine whether there is a change of match, triggering a new input and selection
            ValuesInput newInput = null;
            if (selection.getSelectedMatches().size() <= 1 && getInput() != null) {
              // No more than one match
              EMatch newMatch = selection.asMatch();
              if (newMatch != null) {
                // One match
                EMatch currentMatch = null;
                ValuesInput currentInput = result.getInput();
                if (currentInput != null && currentInput.getMatchAndFeature() != null)
                  currentMatch = currentInput.getMatchAndFeature().getMatch();
                if (newMatch != currentMatch) {
                  // New match is different from current match
                  FeaturesInput featuresInput = new FeaturesInput(getInput(), newMatch);
                  MatchAndFeature firstMAF = getDefaultFeatureSelection(featuresInput);
                  if (firstMAF != null) {
                    // Feature selected by default
                    newInput = new ValuesInput(getInput(), firstMAF);
                  }
                } else {
                  // Same match and no feature
                  newInput = currentInput;
                }
              }
            }
            result.setInput(newInput);
          }
        }
      }
    });
    // Double click: open dedicated viewer
    result.getInnerViewer().addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        ValuesInput input = (ValuesInput) event.getViewer().getInput();
        MatchAndFeature maf = input.getMatchAndFeature();
        if (TextMergerDialog.isApplicableTo(maf.getFeature())) {
          openDedicatedViewer(getInput(), maf.getMatch(), (EAttribute)maf.getFeature());
        }
      }
    });
    return result;
  }
  
  /**
   * Create and return a features viewer
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedFeaturesViewer doCreateViewerFeatures(Composite parent_p) {
    return new EnhancedFeaturesViewer(parent_p);
  }
  
  /**
   * Create and return the main viewer of the synthesis row
   * @param parent_p a non-null composite
   * @return a non-null viewer
   */
  protected EnhancedComparisonTreeViewer doCreateViewerSynthesis(Composite parent_p) {
    return new EnhancedComparisonTreeViewer(parent_p);
  }
  
  /**
   * Create and return a viewer in the synthesis row for the given side
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   * @return a non-null viewer
   */
  protected EnhancedComparisonSideViewer doCreateViewerSynthesisSide(
      Composite parent_p, boolean isLeftSide_p) {
    return new EnhancedComparisonSideViewer(parent_p, isLeftSide_p);
  }
  
  /**
   * Create a values viewer for the given side
   * @param parent_p a non-null composite
   * @param isLeftSide_p whether the side is left or right
   * @return a non-null viewer
   */
  protected EnhancedValuesViewer doCreateViewerValues(Composite parent_p, boolean isLeftSide_p) {
    return new EnhancedValuesViewer(parent_p, isLeftSide_p);
  }
  
  /**
   * Return the currently selected tree path from the Synthesis viewer
   * @return a non-null, potentially empty path
   */
  protected TreePath getCurrentPath() {
    ComparisonTreeViewer treeViewer = getSynthesisViewer().getInnerViewer();
    ITreeSelection selection = treeViewer.getSelection();
    TreePath result = (selection == null || selection.isEmpty())? TreePath.EMPTY:
      selection.getPaths()[0];
    return result;
  }
  
  /**
   * Return the default respective weights of the columns (sashes) of the GUI
   * @return an int array whose size is equal to the number of columns
   */
  protected int[] getDefaultColumnWeights() {
    return new int[] {3, 2, 2};
  }
  
  /**
   * Return the match and feature that must be selected by default in the Features Viewer
   * when its input is the given one
   * @param nodeAndMatch_p a non-null object
   * @return a potentially null object
   */
  protected MatchAndFeature getDefaultFeatureSelection(FeaturesInput nodeAndMatch_p) {
    MatchAndFeature result = null;
    HeaderViewer<?> rawFeaturesViewer = getFeaturesViewer();
    if (rawFeaturesViewer instanceof EnhancedFeaturesViewer) {
      FeaturesViewer viewer = ((EnhancedFeaturesViewer)rawFeaturesViewer).getInnerViewer();
      if (viewer != null) {
        result = getDefaultFeatureSelection(nodeAndMatch_p, viewer);
      }
    }
    return result;
  }
  
  /**
   * Return the match and feature that must be selected by default in the given
   * Features Viewer when its input is the given one
   * @param nodeAndMatch_p a non-null object
   * @param viewer_p a non-null viewer
   * @return a potentially null object
   */
  protected MatchAndFeature getDefaultFeatureSelection(FeaturesInput nodeAndMatch_p,
      FeaturesViewer viewer_p) {
    MatchAndFeature result = viewer_p.getFirstIn(nodeAndMatch_p);
    return result;
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
    return _viewerFeatures;
  }
  
  /**
   * Return the set of inner viewers of this viewer
   * @return a non-null collection
   */
  protected Collection<Viewer> getInnerViewers() {
    return Arrays.<Viewer>asList(
        _viewerSynthesisMain.getInnerViewer(),
        _viewerSynthesisLeft.getInnerViewer(),
        _viewerSynthesisRight.getInnerViewer(),
        _viewerFeatures.getInnerViewer(),
        _viewerValuesLeft.getInnerViewer(),
        _viewerValuesRight.getInnerViewer());
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
    EnhancedComparisonSideViewer result = left_p? _viewerSynthesisLeft:
      _viewerSynthesisRight;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#getMultiViewerSelectionProvider()
   */
  @Override
  protected ISelectionProvider getMultiViewerSelectionProvider() {
    return _multiViewerSelectionProvider;
  }
  
  /**
   * Return the set of selected matches from the given selection in the
   * purpose of performing actions on differences
   * @param selection_p a non-null selection
   * @return a non-null, potentially empty list
   */
  protected List<EMatch> getSelectedMatchesForInteractions(
      final ComparisonSelection selection_p) {
    List<EMatch> selectedMatches = selection_p.getSelectedMatches();
    if (selectedMatches.isEmpty()) {
      List<EMatch> treePath = selection_p.getSelectedTreePath();
      if (!treePath.isEmpty())
        selectedMatches = Collections.singletonList(treePath.get(treePath.size()-1));
    }
    return selectedMatches;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ComparisonSelection getSelection() {
    return _lastUserSelection;
  }
  
  /**
   * Return a variant of the given match-based selection for the given side.
   * Matches are converted to their elements on the given side.
   * Elements other than matches are ignored.
   * @param selection_p a non-null selection
   * @param onLeft_p whether the desired side is left or right
   * @return a non-null, potentially empty selection
   */
  protected IStructuredSelection getSelectionAsSide(
      IStructuredSelection selection_p, boolean onLeft_p) {
    List<EObject> result = new FArrayList<EObject>();
    if (getInput() != null) {
      Role role = getInput().getRoleForSide(onLeft_p);
      for (Object selected : selection_p.toArray()) {
        if (selected instanceof IMatch) {
          EObject element = ((IMatch)selected).get(role);
          if (element != null)
            result.add(element);
        }
      }
    }
    return new StructuredSelection(result);
  }
  
  /**
   * Return a variant of the given element-based selection as a
   * match-based selection. Elements from the given side are converted to
   * their corresponding matches. Other elements are ignored.
   * @param selection_p a non-null selection
   * @param onLeft_p whether the original side is left or right
   * @return a non-null, potentially empty selection
   */
  protected IStructuredSelection getSelectionAsSynthesis(
      IStructuredSelection selection_p, boolean onLeft_p) {
    List<IMatch> result = new FArrayList<IMatch>();
    EMFDiffNode input = getInput();
    if (input != null) {
      IComparison comparison = input.getActualComparison();
      if (comparison != null) {
        Role role = input.getRoleForSide(onLeft_p);
        for (Object selected : selection_p.toArray()) {
          if (selected instanceof EObject) {
            IMatch match = comparison.getMapping().getMatchFor(
                (EObject)selected, role);
            if (match != null)
              result.add(match);
          }
        }
      }
    }
    return new StructuredSelection(result);
  }
  
  /**
   * Return the inner viewer for the comparison tree
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public EnhancedComparisonTreeViewer getSynthesisViewer() {
    return _viewerSynthesisMain;
  }
  
  /**
   * Return the inner viewer for differences on values for the given side
   * @param left_p whether the side is left or right
   * @return a viewer which is non-null if this viewer has been properly initialized
   */
  public EnhancedValuesViewer getValuesViewer(boolean left_p) {
    EnhancedValuesViewer result = left_p? _viewerValuesLeft: _viewerValuesRight;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#handleCompareInputChanged(org.eclipse.compare.structuremergeviewer.ICompareInput)
   */
  @Override
  protected void handleCompareInputChanged(final ICompareInput source_p) {
    if (source_p instanceof EMFDiffNode) {
      EMFDiffNode node = (EMFDiffNode)source_p;
      boolean isFiltering = node.getCategoryManager().isUIMoreFilteringThanDefault();
      firePropertyChangeEvent(PROPERTY_FILTERING, Boolean.valueOf(isFiltering));
    }
    super.handleCompareInputChanged(source_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#handleDispose()
   */
  @Override
  protected void handleDispose() {
    super.handleDispose();
    if (_lastUserSelection != null) {
      _lastUserSelection.dispose();
      _lastUserSelection = null;
    }
    _viewerSynthesisLeft = null;
    _viewerSynthesisRight = null;
    _viewerValuesLeft = null;
    _viewerValuesRight = null;
    _viewerSynthesisMain = null;
    _viewerFeatures = null;
    _sorterSynthesis = null;
    _filterUnchangedElements = null;
    _filterMoveOrigins = null;
    if (_filterSelectionListener != null) {
      _filterSelectionListener.dispose();
      _filterSelectionListener = null;
    }
  }
  
  /**
   * Ignore the current selection
   * @param onLeft_p whether ignore occurs on the left
   */
  protected void ignore(boolean onLeft_p) {
    final ComparisonSelection selection = getSelection();
    if (selection == null) return; // Should not happen according to ignore tool activation
    EMFDiffNode input = getInput();
    if (input == null) return; // Should not happen according to ignore tool activation
    List<EMatch> selectedMatches = getSelectedMatchesForInteractions(selection);
      // Make choices
    IgnoreChoiceData choices = new IgnoreChoiceData(
        input.isUserPropertyTrue(P_DEFAULT_COVER_CHILDREN), false);
    makeIgnoreChoices(choices, input, selectedMatches);
    if (!choices.isProceed()) return;
    // Ignore operation is set to proceed and choices have been made
    Collection<IDifference> toIgnore;
    if (!selectedMatches.isEmpty()) {
      toIgnore = getDifferencesToMerge(
        selectedMatches, input.getRoleForSide(onLeft_p),
        choices.isCoverChildren(), choices.isSideExclusive());
    } else {
      toIgnore = getInput().getCategoryManager().getPendingDifferencesFiltered(
          selection.asDifferencesToMerge());
    }
    ignore(toIgnore);
  }
  
  /**
   * Ignore the given set of differences
   * @param differences_p a non-null, potentially empty set of differences
   */
  protected void ignore(final Collection<IDifference> differences_p) {
    final EMFDiffNode input = getInput();
    final ComparisonSelection selection = getSelection();
    if (input != null && !differences_p.isEmpty()) {
      executeOnComparison(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          input.ignore(differences_p);
          getUIComparison().setLastActionSelection(selection);
        }
      });
      if (!input.isReactive()) {
        firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(true));
        input.updateDifferenceNumbers();
      }
    }
  }
  
  /**
   * Initialize the non-graphical instance variables
   */
  protected void initialize() {
    _lastUserSelection = null;
    _multiViewerSelectionProvider = new SelectionBridge.SingleSource();
    _multiViewerSelectionProvider.setSource(this);
    _sorterSynthesis = new ViewerComparator();
    _filterUnchangedElements = new ViewerFilter() {
      /**
       * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
       */
      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        EMatch match = (EMatch)element_p;
        return getInput().getCategoryManager().getDifferenceNumber(match) > 0;
      }
    };
    _filterMoveOrigins = new ViewerFilter() {
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
        return !getInput().getCategoryManager().isMoveOrigin(path);
      }
    };
  }
  
  /**
   * Return whether user interactions must occur to determine how to execute
   * the ignore operation on the given list of matches in the context of the
   * given input and the given specification of choices
   * @param choices_p the non-null specification of the ignore choices
   * @param input_p a non-null input
   * @param selectedMatches a non-null, potentially empty list
   */
  protected boolean interactionsRequiredForIgnore(IgnoreChoiceData choices_p,
      EMFDiffNode input_p, List<EMatch> selectedMatches) {
    boolean childrenForMerge = false;
    boolean ownDifferences = false;
    // Determining whether selected matches have proper differences
    // and differences in children
    for (EMatch selectedMatch : selectedMatches) {
      ownDifferences = ownDifferences ||
          !input_p.getCategoryManager().getDifferenceKind(selectedMatch).isNeutral();
      if (childrenForMerge && ownDifferences)
        break;
      childrenForMerge = childrenForMerge ||
          input_p.getCategoryManager().hasChildrenForMergeFiltered(selectedMatch);
      if (childrenForMerge && ownDifferences)
        break;
    }
    if (!ownDifferences && childrenForMerge)
      // No own difference but differences in children: operation only
      // makes sense if children are covered
      choices_p.setCoverChildren(true);
    return ownDifferences && childrenForMerge;
  }
  
  /**
   * Return whether user interactions must occur to determine how to execute
   * the merge operation on the given list of matches in the context of the
   * given input and the given specification of choices
   * @param choices_p the non-null specification of the merge choices
   * @param input_p a non-null input
   * @param selectedMatches a non-null, potentially empty list
   */
  protected boolean interactionsRequiredForMerge(MergeChoiceData choices_p,
      EMFDiffNode input_p, List<EMatch> selectedMatches) {
    boolean result = !selectedMatches.isEmpty();
    if (result && selectedMatches.size() == 1) {
      EMatch selectedMatch = selectedMatches.get(0);
      if (!input_p.getCategoryManager().hasChildrenForMergeFiltered(selectedMatch)) {
        DifferenceKind kind = input_p.getCategoryManager().getDifferenceKind(selectedMatch);
        result = !(kind.isAddition() || kind.isDeletion());
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    super.inputChanged(input_p, oldInput_p);
    _viewerFeatures.setInput(null);
    _viewerValuesLeft.setInput(null);
    _viewerValuesRight.setInput(null);
    _viewerSynthesisMain.setInput(input_p);
    _viewerSynthesisLeft.setInput(input_p);
    _viewerSynthesisRight.setInput(input_p);
    EMFDiffNode input = getInput();
    if (input != null && input.isUserPropertyTrue(P_LOG_EVENTS)) {
      getLogger().log(new CompareLogEvent(getEditingDomain(), input));
    }
  }
  
  /**
   * Return whether the "open dedicated viewer" tool is applicable in the given context
   * @param input_p a potentially null input
   * @param selection_p a potentially null selection
   */
  protected boolean isDedicatedViewerApplicable(EMFDiffNode input_p,
      ComparisonSelection selection_p) {
    boolean result = false;
    if (input_p != null && selection_p != null && !selection_p.isEmpty()) {
      EStructuralFeature feature = selection_p.asFeature();
      if (feature == null) {
        EMatch selectedMatch = selection_p.asMatch();
        if (selectedMatch != null) {
          FeaturesInput nodeAndMatch = new FeaturesInput(input_p, selectedMatch);
          MatchAndFeature defaultMAF = getDefaultFeatureSelection(nodeAndMatch);
          if (defaultMAF != null) {
            feature = defaultMAF.getFeature();
          }
        }
      }
      result = TextMergerDialog.isApplicableTo(feature);
    }
    return result;
  }
  
  /**
   * Return whether the left/right model viewers must be synchronized with the synthesis viewer
   * and can actually be visible
   */
  protected boolean isLeftRightSynced() {
    return isUserPropertyTrue(P_SYNC_SYNTHESIS_AND_SIDES) &&
        isUserPropertyTrue(P_SHOW_SIDES_POSSIBLE);
  }
  
  /**
   * Set the given specification of ignore choices according to the given
   * input and set of selected matches
   * @param choices_p the non-null specification of the ignore choices
   * @param input_p a non-null diff node input
   * @param selectedMatches_p the non-null, potentially empty set of matches that have
   *          been selected for merge
   */
  protected void makeIgnoreChoices(IgnoreChoiceData choices_p,
      EMFDiffNode input_p, List<EMatch> selectedMatches_p) {
    boolean requiresInteractions = interactionsRequiredForIgnore(choices_p, input_p, selectedMatches_p);
    if (requiresInteractions) {
      IgnoreChoicesDialog choicesDialog =
          new IgnoreChoicesDialog(getShell(), Messages.ComparisonViewer_IgnoreCommandName, choices_p);
      choicesDialog.open();
      if (choices_p.isProceed()) {
        getInput().setUserPropertyValue(P_DEFAULT_COVER_CHILDREN, choices_p.isCoverChildren());
      }
    }
  }
  
  /**
   * Set the given specification of merge choices according to the given
   * input and set of selected matches
   * @param choices_p the non-null specification of the merge choices
   * @param input_p a non-null diff node input
   * @param selectedMatches_p the non-null, potentially empty set of matches that have
   *          been selected for merge
   * @param acceptIncrementalMode_p whether the incremental mode is acceptable in this context
   */
  protected void makeMergeChoices(MergeChoiceData choices_p, EMFDiffNode input_p,
      List<EMatch> selectedMatches_p, boolean acceptIncrementalMode_p) {
    boolean requiresInteractions = interactionsRequiredForMerge(
        choices_p, input_p, selectedMatches_p);
    if (requiresInteractions) {
      // Group of differences
      boolean mayAskAboutChildren = false;
      for (EMatch selectedMatch : selectedMatches_p) {
        if (input_p.getCategoryManager().getDifferenceKind(selectedMatch) == DifferenceKind.COUNTED) {
          choices_p.setCoverChildren(true);
          break;
        } else if (input_p.getCategoryManager().hasChildrenForMergeFiltered(selectedMatch)) {
          mayAskAboutChildren = true;
          break;
        }
      }
      // Choice dialog
      MergeChoicesDialog choicesDialog =
          new MergeChoicesDialog(getShell(), Messages.ComparisonViewer_MergeHeader,
              choices_p, mayAskAboutChildren, acceptIncrementalMode_p);
      choicesDialog.open();
      if (choices_p.isProceed()) {
        if (mayAskAboutChildren) {
          input_p.setUserPropertyValue(P_DEFAULT_COVER_CHILDREN, choices_p.isCoverChildren());
        }
        input_p.setUserPropertyValue(P_DEFAULT_INCREMENTAL_MODE, choices_p.isIncrementalMode());
        input_p.setUserPropertyValue(P_DEFAULT_SHOW_MERGE_IMPACT, choices_p.isShowImpact());
      }
    }
  }
  
  /**
   * Merge the current selection to the given side
   * @param toLeft_p whether destination is left or right
   * @param acceptIncrementalMode_p whether the incremental mode is acceptable in this context
   */
  protected void merge(boolean toLeft_p, boolean acceptIncrementalMode_p) {
    merge(toLeft_p, acceptIncrementalMode_p, getSelection());
  }
  
  /**
   * Merge the given selection to the given side
   * @param toLeft_p whether destination is left or right
   * @param acceptIncrementalMode_p whether the incremental mode is acceptable in this context
   * @param selection_p the potentially null selection (e.g., set of matches) to merge
   */
  protected void merge(final boolean toLeft_p, boolean acceptIncrementalMode_p,
      final ComparisonSelection selection_p) {
    if (selection_p == null) return; // Should not happen according to merge tool activation
    final EMFDiffNode input = getInput();
    // Define the set of selected matches
    List<EMatch> selectedMatches = getSelectedMatchesForInteractions(selection_p);
    // Make choices
    MergeChoiceData choices = new MergeChoiceData(
        input.isUserPropertyTrue(P_DEFAULT_COVER_CHILDREN),
        input.isUserPropertyTrue(P_DEFAULT_INCREMENTAL_MODE) && acceptIncrementalMode_p,
        input.isUserPropertyTrue(P_DEFAULT_SHOW_MERGE_IMPACT));
    makeMergeChoices(choices, input, selectedMatches, acceptIncrementalMode_p);
    if (!choices.isProceed()) return;
    // Merge is set to proceed and choices have been made
    final Role destination = input.getRoleForSide(toLeft_p);
    final Collection<IDifference> toMerge = !selectedMatches.isEmpty()? getDifferencesToMerge(
            selectedMatches, destination, choices.isCoverChildren(), choices.isIncrementalMode()):
          input.getCategoryManager().getPendingDifferencesFiltered(selection_p.asDifferencesToMerge());
    final Collection<IDifference> merged = new ArrayList<IDifference>();
    boolean done = false;
    if (!toMerge.isEmpty()) {
      // Merge is possible
      boolean proceed = true;
      if (choices.isShowImpact()) {
        // Show merge impact
        proceed = showMergeImpact(toMerge, toLeft_p, input);
      }
      if (proceed) {
        // Merge is confirmed
        executeOnModel(new IRunnableWithProgress() {
          /**
           * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
           */
          public void run(IProgressMonitor monitor_p) throws InvocationTargetException,
          InterruptedException {
            merged.addAll(getComparison().merge(toMerge, destination, true, monitor_p));
            getUIComparison().setLastActionSelection(selection_p);
          }
        }, toLeft_p);
        done = true;
      }
    } else {
      // Nothing to merge
      MessageDialog.openInformation(getShell(), Messages.ComparisonViewer_MergeHeader,
          Messages.ComparisonViewer_NoDiffsToMerge);
    }
    if (!merged.isEmpty() && done) {
      // React to merge
      input.setModified(true, toLeft_p);
      if (!input.isReactive()) {
        firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(true));
        input.updateDifferenceNumbers();
      }
      if (input.isUserPropertyTrue(P_LOG_EVENTS)) {
        getLogger().log(new MergeLogEvent(input, merged, toLeft_p));
      }
    }
  }
  
  /**
   * Apply the merge of a value defined through a dedicated viewer
   * @param node_p the non-null diff node
   * @param match_p the non-null match element concerned
   * @param feature_p the non-null feature concerned
   * @param textDiffNode_p the non-null output of the dedicated merge operation
   */
  protected void mergeFromDedicatedViewer(final EMFDiffNode node_p, final IMatch match_p,
      final EAttribute feature_p, TextDiffNode textDiffNode_p) {
    final ComparisonSelection selection = getSelection();
    final List<EMergeableDifference> toIgnore = selection.asDifferencesToMerge();
    TextCompareContent left = textDiffNode_p.getLeft();
    TextCompareContent right = textDiffNode_p.getRight();
    String leftMergedValue = left.getEditedContent();
    String rightMergedValue = right.getEditedContent();
    if (leftMergedValue != null || rightMergedValue != null) {
      // They cannot be both non-null due to TextMergerDialog.isEditable(boolean)
      final boolean onLeft = leftMergedValue != null;
      final String newValue = onLeft? leftMergedValue: rightMergedValue;
      final IEditableModelScope impactedScope = node_p.getScope(onLeft);
      executeOnModel(new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
         */
        public void run(IProgressMonitor monitor_p)
            throws InvocationTargetException, InterruptedException {
          EObject holder = match_p.get(node_p.getRoleForSide(onLeft));
          impactedScope.add(holder, feature_p, newValue);
          node_p.setModified(true, onLeft);
          node_p.ignore(toIgnore);
          getUIComparison().setLastActionSelection(selection);
        }
      }, onLeft);
      setSelection(null);
      firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, Boolean.TRUE);
      getInput().updateDifferenceNumbers();
    }
  }
  
  /**
   * Navigate to the next/previous difference according to the given flag
   * @param next_p whether navigation must be forward or back
   * @return whether the operation could not be completed due to the
   * last/first difference being reached
   */
  protected boolean navigate(boolean next_p) {
    return navigate(getCurrentPath(), next_p);
  }
  
  /**
   * Navigate to the next/previous difference from the given origin path
   * according to the given flag
   * @param origin_p a non-null tree path
   * @param next_p whether navigation must be forward or back
   * @return whether the operation could not be completed due to the
   * last/first difference being reached
   */
  protected boolean navigate(TreePath origin_p, boolean next_p) {
    ComparisonTreeViewer treeViewer = _viewerSynthesisMain.getInnerViewer();
    TreePath newPath = next_p? treeViewer.getNextUserDifference(origin_p):
      treeViewer.getPreviousUserDifference(origin_p);
    if (newPath != null) {
      setSelection(new ComparisonSelectionImpl(newPath, getDrivingRole(), getInput()), true);
    }
    return newPath == null;
  }
  
  /**
   * Open a dedicated comparison viewer in order to view or merge a value
   * on a given match through a given attribute.
   * Precondition: the match and attribute qualify for a dedicated viewer.
   * @param node_p a non-null diff node
   * @param match_p the non-null match concerned
   * @param feature_p the potentially null feature concerned where null stands for default
   */
  protected void openDedicatedViewer(final EMFDiffNode node_p, EMatch match_p,
      EAttribute feature_p) {
    assert feature_p == null || TextMergerDialog.isApplicableTo(feature_p);
    EAttribute attribute = feature_p;
    if (attribute == null) {
      FeaturesInput nodeAndMatch = new FeaturesInput(node_p, match_p);
      MatchAndFeature defaultMAF = getDefaultFeatureSelection(nodeAndMatch);
      if (defaultMAF != null &&
          TextMergerDialog.isApplicableTo(defaultMAF.getFeature())) {
        attribute = (EAttribute)defaultMAF.getFeature();
      }
    }
    if (attribute != null) {
      TextMergerDialog dialog = new TextMergerDialog(
          getShell(), node_p, match_p, attribute);
      if (dialog.open() == Window.OK) {
        // Merge confirmed
        mergeFromDedicatedViewer(
            node_p, match_p, attribute, dialog.getViewerInput());
      }
    }
  }
  
  /**
   * Define the diff/merge-specific content of the contextual menu for
   * the given viewer and associated (possibly the same) selection provider
   * @param menuManager_p a non-null menu manager
   * @param viewer_p a non-null viewer
   * @param selectionProvider_p a non-null selection provider
   */
  protected void populateContextMenu(MenuManager menuManager_p,
      Viewer viewer_p, ISelectionProvider selectionProvider_p) {
    if (_viewerSynthesisMain != null &&
        viewer_p != _viewerSynthesisMain.getInnerViewer()) {
      return;
    }
    // Action definitions
    final DirectedAction acceptAction = createActionAccept();
    final DirectedAction acceptDeletionAction = createActionAcceptDeletion();
    final DirectedAction ignoreAction = createActionIgnore();
    // Menu population
    menuManager_p.addMenuListener(new IMenuListener() {
      /**
       * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
       */
      public void menuAboutToShow(IMenuManager manager_p) {
        boolean atLeastOne = false;
        if (acceptAction != null && acceptAction.isEnabled()) {
          manager_p.add(acceptAction);
          atLeastOne = true;
        }
        if (acceptDeletionAction != null && acceptDeletionAction.isEnabled()) {
          manager_p.add(acceptDeletionAction);
          atLeastOne = true;
        }
        if (ignoreAction != null && ignoreAction.isEnabled()) {
          manager_p.add(ignoreAction);
          atLeastOne = true;
        }
        if (atLeastOne) {
          manager_p.add(new Separator());
        }
      }
    });
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _viewerSynthesisLeft.refresh();
    _viewerSynthesisRight.refresh();
    _viewerValuesLeft.refresh();
    _viewerValuesRight.refresh();
    _viewerFeatures.refresh();
    _viewerSynthesisMain.refresh();
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
        DifferenceKind kind = input.getCategoryManager().getDifferenceKind(presence);
        onLeft = canAddToTheRight(kind);
        onRight = canAddToTheLeft(kind);
        allowDeletion = input.getCategoryManager().isMany(presence) &&
            !input.getCategoryManager().isOwnership(presence);
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
              DifferenceKind kind = input.getCategoryManager().getDifferenceKind(current);
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
            if (input.getCategoryManager().representAsModification(match) ||
                input.getCategoryManager().representAsMove(match) ||
                input.getCategoryManager().getDifferenceKind(match) == DifferenceKind.COUNTED) {
              // Modification or move or inner differences
              onLeft = true;
              onRight = true;
              allowDeletion = false;
            } else {
              // Partial match
              DifferenceKind kind = input.getCategoryManager().getDifferenceKind(match);
              onLeft = canAddToTheRight(kind);
              onRight = canAddToTheLeft(kind);
              allowDeletion = true;
            }
          }
        }
      }
    }
    if (input != null) {
      firePropertyChangeEvent(PROPERTY_ACTIVATION_MERGE_TO_RIGHT,
          Boolean.valueOf(input.isEditable(false) && onLeft));
      firePropertyChangeEvent(PROPERTY_ACTIVATION_DELETE_LEFT,
          Boolean.valueOf(input.isEditable(true) && onLeft && allowDeletion));
      firePropertyChangeEvent(PROPERTY_ACTIVATION_MERGE_TO_LEFT,
          Boolean.valueOf(input.isEditable(true) && onRight));
      firePropertyChangeEvent(PROPERTY_ACTIVATION_DELETE_RIGHT,
          Boolean.valueOf(input.isEditable(false) && onRight && allowDeletion));
    }
    firePropertyChangeEvent(PROPERTY_ACTIVATION_IGNORE_LEFT,
        Boolean.valueOf(onLeft && allowIgnoring));
    firePropertyChangeEvent(PROPERTY_ACTIVATION_IGNORE_RIGHT,
        Boolean.valueOf(onRight && allowIgnoring));
    firePropertyChangeEvent(PROPERTY_ACTIVATION_OPEN_DEDICATED,
        Boolean.valueOf(isDedicatedViewerApplicable(input, selection)));
    super.refreshTools();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer#registerUserProperties(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  protected void registerUserProperties(EMFDiffNode input_p) {
    input_p.addUserProperty(P_CUSTOM_ICONS, Boolean.TRUE);
    input_p.addUserProperty(P_CUSTOM_LABELS, Boolean.FALSE);
    input_p.addUserProperty(P_DEFAULT_COVER_CHILDREN, Boolean.TRUE);
    input_p.addUserProperty(P_DEFAULT_INCREMENTAL_MODE, Boolean.FALSE);
    input_p.addUserProperty(P_DEFAULT_SHOW_MERGE_IMPACT, Boolean.FALSE);
    input_p.addUserProperty(P_LOG_EVENTS, Boolean.FALSE);
    input_p.addUserProperty(P_SHOW_DIFFERENCE_NUMBERS, Boolean.TRUE);
    input_p.addUserProperty(P_SHOW_MERGE_IMPACT, Boolean.FALSE);
    input_p.addUserProperty(P_SHOW_SIDES_POSSIBLE, Boolean.TRUE);
    input_p.addUserProperty(P_SUPPORT_UNDO_REDO, Boolean.TRUE);
    input_p.addUserProperty(P_SUPPORT_UNDO_REDO_OPTIONAL, Boolean.TRUE);
    input_p.addUserProperty(P_SYNC_SYNTHESIS_AND_SIDES,
        input_p.getUserPropertyValue(P_SHOW_SIDES_POSSIBLE));
    input_p.addUserProperty(P_TECHNICAL_LABELS, Boolean.FALSE);
  }
  
  /**
   * Restart the comparison via a GUI
   */
  protected void restart() {
    final EMFDiffNode input = getInput();
    IEditorInput rawEditorInput = input == null? null: input.getEditorInput();
    if (input != null && rawEditorInput instanceof EMFDiffMergeEditorInput) {
      EMFDiffMergeEditorInput editorInput = (EMFDiffMergeEditorInput)rawEditorInput;
      IComparisonMethod origMethod = editorInput.getComparisonMethod();
      IModelScopeDefinition originalTargetScopeDef =
          origMethod.getModelScopeDefinition(Role.TARGET);
      ComparisonSetupManager manager = EMFDiffMergeUIPlugin.getDefault().getSetupManager();
      boolean confirmed = manager.updateEditorInputWithUI(getShell(), editorInput);
      if (confirmed) {
        IModelScopeDefinition newTargetScopeDef =
            editorInput.getComparisonMethod().getModelScopeDefinition(Role.TARGET);
        boolean sidesSwapped = newTargetScopeDef != originalTargetScopeDef;
        Job job = new RestartJob(editorInput, sidesSwapped);
        job.setUser(true);
        job.schedule();
      }
    }
  }
  
  /**
   * Set the "base" label provider for representing model elements
   * @param labelProvider_p a potentially null label provider, where null stands for default
   */
  public void setDelegateLabelProvider(ILabelProvider labelProvider_p) {
    for (Viewer viewer : getInnerViewers()) {
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
   * Set the diff label decorator for representing the diff status of model elements and features
   * @param diffDecorator_p a potentially null diff label decorator, where null stands for default
   */
  public void setDiffLabelDecorator(IDiffLabelDecorator diffDecorator_p) {
    for (Viewer viewer : getInnerViewers()) {
      if (viewer instanceof ContentViewer) {
        IBaseLabelProvider rawLP = ((ContentViewer)viewer).getLabelProvider();
        if (rawLP instanceof DiffDecoratingLabelProvider) {
          DiffDecoratingLabelProvider enhancedLP =
              (DiffDecoratingLabelProvider)rawLP;
          enhancedLP.setDiffLabelDecorator(diffDecorator_p);
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    setSelection(selection_p, reveal_p, this);
  }
  
  /**
   * Set the selection of this viewer, indicating the source of this setting
   * @see Viewer#setSelection(ISelection, boolean)
   * @param selection_p a potentially null selection
   * @param reveal_p whether the selected elements must be revealed
   * @param source_p the potentially null source of the setting
   */
  protected void setSelection(ISelection selection_p, boolean reveal_p, Viewer source_p) {
    ComparisonSelection newSelection;
    if (selection_p instanceof ComparisonSelection &&
        (source_p == this || getInnerViewers().contains(source_p)))
      newSelection = (ComparisonSelection)selection_p; // Local selection
    else if (selection_p instanceof IStructuredSelection)
      newSelection = asComparisonSelection((IStructuredSelection)selection_p); // External selection
    else
      newSelection = new ComparisonSelectionImpl(null, null, getInput()); // Invalid selection
    _lastUserSelection = newSelection;
    fireSelectionChanged(new SelectionChangedEvent(source_p, getSelection()));
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
   * in the given context
   * @param context_p a non-null object
   * @return a potentially null menu
   */
  protected IMenuManager setupMenuDetails(ToolBarManager context_p) {
    MenuManager result = UIUtil.createMenuTool(context_p);
    // Only differences
    createItemShowDiffValues(result);
    // All values
    createItemShowAllValues(result);
    // All values and features
    createItemShowAllFeatures(result);
    // Technical representation
    result.add(new Separator());
    createItemUseTechnicalRepresentation(result);
    return result;
  }
  
  /**
   * Create and return the synthesis menu in the given context
   * @param context_p a non-null object
   * @return a potentially null menu
   */
  protected IMenuManager setupMenuSynthesis(ToolBarManager context_p) {
    MenuManager result = UIUtil.createMenuTool(context_p);
    createItemRestart(result);
    result.add(new Separator());
    // Show all elements in synthesis
    createItemShowUncounted(result);
    createItemFilter(result);
    result.add(new Separator());
    // Common presentation features
    createItemSync(result);
    createItemSort(result);
    // UI options
    result.add(new Separator());
    setupMenuSynthesisMisc(result);
    return result;
  }
  
  /**
   * Contribute miscellaneous synthesis features to the given context
   * @param context_p a non-null object
   */
  protected void setupMenuSynthesisMisc(IContributionManager context_p) {
    createItemUseCustomIcons(context_p);
    createItemUseCustomLabels(context_p);
    context_p.add(new Separator());
    createItemShowSides(context_p);
    createItemShowDifferenceNumbers(context_p);
    createItemShowImpact(context_p);
    context_p.add(new Separator());
        createItemSupportUndoRedo(context_p);
    createItemLogEvents(context_p);
  }
  
  /**
   * Set up the different tool bars
   */
  protected void setupToolBars() {
    // Tools: upper row
    setupToolsSynthesis(_viewerSynthesisMain.getToolbar());
    setupToolsSynthesisSide(_viewerSynthesisLeft.getToolbar(), true);
    setupToolsSynthesisSide(_viewerSynthesisRight.getToolbar(), false);
    // Tools: lower row
    setupToolsDetails(_viewerFeatures.getToolbar());
    setupToolsDetailsSide(_viewerValuesLeft.getToolbar(), true);
    setupToolsDetailsSide(_viewerValuesRight.getToolbar(), false);
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
    ToolBarManager toolbarManager = new ToolBarManager(toolbar_p);
    createItemOpenDedicated(toolbarManager);
    // Integrate contributions
    IMenuService menuService = PlatformUI.getWorkbench().getService(IMenuService.class);
    menuService.populateContributionManager(toolbarManager, LOCATION_TOOLBAR_DETAILS);
    // Drop-down menu
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_MENU));
    setupMenuDetails(toolbarManager);
    toolbarManager.update(true);
  }
  
  /**
   * Set up the tools related to the "details" row in the given tool bar
   * for the given side
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left or right
   */
  protected void setupToolsDetailsSide(ToolBar toolbar_p, boolean onLeft_p) {
    ToolBarManager toolbarManager = new ToolBarManager(toolbar_p);
    createItemMerge(toolbarManager, !onLeft_p);
    createItemIgnore(toolbarManager, onLeft_p);
    createItemDelete(toolbarManager, onLeft_p);
    toolbarManager.update(true);
  }
  
  /**
   * Set up the "synthesis" tools in the given tool bar
   * @param toolbar_p a non-null tool bar
   */
  protected void setupToolsSynthesis(ToolBar toolbar_p) {
    ToolBarManager toolbarManager = new ToolBarManager(toolbar_p);
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_CONSISTENCY));
    createItemInconsistency(toolbarManager);
    // Next / Previous
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_NAVIGATION));
    createItemNavigationNext(toolbarManager);
    createItemNavigationPrevious(toolbarManager);
    // Expand / Collapse
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_EXPANSION));
    createItemExpand(toolbarManager);
    createItemCollapse(toolbarManager);
    // Filters and sync
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_FILTERING));
    createItemFilter(toolbarManager);
    // Integrate contributions
    IMenuService menuService = PlatformUI.getWorkbench().getService(IMenuService.class);
    menuService.populateContributionManager(toolbarManager, LOCATION_TOOLBAR_SYNTHESIS);
    // Drop-down menu
    toolbarManager.add(new Separator(LOCATION_TOOLBAR_GROUP_MENU));
    setupMenuSynthesis(toolbarManager);
    toolbarManager.update(true);
  }
  
  /**
   * Set up the tools related to the "synthesis" row in the given tool bar
   * for the given side
   * @param toolbar_p a non-null tool bar
   * @param onLeft_p whether the side is left or right
   */
  protected void setupToolsSynthesisSide(ToolBar toolbar_p, boolean onLeft_p) {
    ToolBarManager toolbarManager = new ToolBarManager(toolbar_p);
    createItemLock(toolbarManager, onLeft_p);
    toolbarManager.update(true);
  }
  
  /**
   * Show a UI representing the merge impact and return whether merge is confirmed
   * @param toMerge_p the non-null collection of differences to merge
   * @param toLeft_p whether the destination is the left-hand side
   * @param input_p a non-null object
   * @return whether to proceed with merge
   */
  protected boolean showMergeImpact(final Collection<IDifference> toMerge_p,
      final boolean toLeft_p, final EMFDiffNode input_p) {
    boolean result = true;
    final ImpactInput mergeInput = new ImpactInput(toMerge_p, toLeft_p, input_p);
    IProgressService progress = PlatformUI.getWorkbench().getProgressService();
    try {
      progress.busyCursorWhile(new IRunnableWithProgress() {
        /**
         * @see org.eclipse.jface.operation.IRunnableWithProgress#run(IProgressMonitor)
         */
        public void run(final IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
          mergeInput.compute(monitor_p);
        }
      });
      MergeImpactMessageDialog dialog = new MergeImpactMessageDialog(
          getShell(), mergeInput, getResourceManager(),
          _viewerSynthesisMain.getInnerViewer().getLabelProvider());
      result = dialog.openAndConfirm();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  
  /**
   * Set whether the contents of the left and right sides is visible
   * @param show_p whether it is visible
   */
  protected void showSides(boolean show_p) {
    Control leftControl = _viewerSynthesisLeft.getControl();
    if (leftControl != null && !leftControl.isDisposed()) {
      // Do not check whether show_p != isVisible() because at workbench
      // init time, isVisible()==false while setVisible(false) has an impact
      leftControl.setVisible(show_p);
      _viewerSynthesisRight.getControl().setVisible(show_p);
      leftControl.getParent().layout();
    }
  }
  
  
  /**
   * A selection listener common to all "filter" items.
   */
  protected class FilterSelectionListener implements IDisposable {
    /** The non-null, potentially empty set of action contributions that show/hide the
     * non-modal filter dialog */
    protected final Set<ActionContributionItem> _filterItems;
    /** The potentially null filter dialog lastly opened */
    protected CategoryDialog _lastDialog;
    /**
     * Constructor
     */
    public FilterSelectionListener() {
      _lastDialog = null;
      _filterItems = new HashSet<ActionContributionItem>();
    }
    /**
     * Register the given item as being a "filter" item
     * @param item_p a non-null action contribution item
     */
    public void addItem(ActionContributionItem item_p) {
      _filterItems.add(item_p);
    }
    /**
     * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
     */
    public void dispose() {
      if (_lastDialog != null && !_lastDialog.getShell().isDisposed()) {
        _lastDialog.close();
      }
    }
    /**
     * Handle the given event
     * @param event_p a potentially null event
     */
    public void runWithEvent(Event event_p) {
      Widget widget = event_p.widget;
      if (widget instanceof MenuItem || widget instanceof ToolItem) {
        boolean selected = UIUtil.itemGetSelection((Item)widget);
        if (selected) {
          // Just selected
          _lastDialog = new CategoryDialog(getShell(), getInput());
          _lastDialog.open();
          _lastDialog.getShell().addDisposeListener(new DisposeListener() {
            /**
             * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
             */
            public void widgetDisposed(DisposeEvent e_p) {
              // Toggle tool item when closing
              _lastDialog = null;
              setSelectionForAll(false);
            }
          });
        } else {
          // Not selected any longer
          if (_lastDialog != null)
            _lastDialog.close();
        }
        setSelectionForAll(selected);
      }
    }
    /**
     * Set the selection state of all related items
     * @param selected_p the new selection state
     */
    protected void setSelectionForAll(boolean selected_p) {
      for (ActionContributionItem item : _filterItems) {
        IAction action = item.getAction();
        if (action != null && action.isChecked() != selected_p) {
          action.setChecked(selected_p);
        }
      }
    }
  }
  
  /**
   * An action that is enabled only if there is an identified merge target side.
   */
  protected class DirectedAction extends Action {
    /**
     * Return the target role of the merge, if defined
     * @return a potentially null role
     */
    protected Role getTargetRole() {
      Role result = null;
      EMFDiffNode input = getInput();
      if (input != null) {
        boolean leftEditable = input.isEditable(true);
        boolean rightEditable = input.isEditable(false);
        if (leftEditable && !rightEditable) {
          result = input.getRoleForSide(true);
        } else if (!leftEditable && rightEditable) {
          result = input.getRoleForSide(false);
        }
      }
      return result;
    }
    /**
     * Return whether this action is applicable in the current context
     * independently of the current selection
     */
    public boolean isApplicable() {
      return getTargetRole() != null;
    }
    /**
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
      return isApplicable() && super.isEnabled();
    }
    /**
     * Return whether the target role of the merge corresponds to the
     * right-hand side. If there is no target role the result is undefined.
     */
    public boolean isLeftToRight() {
      boolean result = false;
      EMFDiffNode input = getInput();
      if (input != null) {
        result = getTargetRole() == input.getRoleForSide(false);
      }
      return result;
    }
  }
  
  /**
   * The "ignore" action.
   */
  protected class IgnoreAction extends DirectedAction {
    /** Whether the left-hand side ignore is active */
    private boolean _activeLeft = false;
    /** Whether the right-hand side ignore is active */
    private boolean _activeRight = false;
    /**
     * Constructor
     */
    public IgnoreAction() {
      setText(Messages.ComparisonViewer_IgnoreAction_Text);
      setImageDescriptor(
          ComparisonViewer.this.getImageDescriptor(ImageID.CHECKED));
    }
    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
      if (_activeLeft) {
        ignore(true);
      }
      if (_activeRight) {
        ignore(false);
      }
    }
    /**
     * Set whether the left-hand side ignore is active
     * @param activeLeft_p whether it is active
     */
    public void setActiveLeft(boolean activeLeft_p) {
      _activeLeft = activeLeft_p;
      update();
    }
    /**
     * Set whether the right-hand side ignore is active
     * @param activeRight_p whether it is active
     */
    public void setActiveRight(boolean activeRight_p) {
      _activeRight = activeRight_p;
      update();
    }
    /**
     * Update the state for complete consistency
     */
    protected void update() {
      setEnabled(_activeLeft || _activeRight);
    }
  }
  
  /**
   * The job that executes the non-interactive part of "comparison restart".
   */
  protected class RestartJob extends Job {
    /** The non-null updated editor input */
    protected final EMFDiffMergeEditorInput _editorInput;
    /** Whether sides have been swapped during editor input update */
    protected final boolean _sidesSwapped;
    /**
     * Constructor
     * @param editorInput_p the non-null updated editor input
     * @param sidesSwapped_p whether sides have been swapped during editor input update
     */
    protected RestartJob(EMFDiffMergeEditorInput editorInput_p, boolean sidesSwapped_p) {
      super(Messages.ComparisonViewer_RestartInProgress);
      _editorInput = editorInput_p;
      _sidesSwapped = sidesSwapped_p;
    }
    /**
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor_p) {
      final EMFDiffNode diffNode = _editorInput.getCompareResult();
      final boolean editionPossibleLeft = diffNode.isEditionPossible(true);
      final boolean editionPossibleRight = diffNode.isEditionPossible(false);
      Display.getDefault().syncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          diffNode.setEditionPossible(false, true);
          diffNode.setEditionPossible(false, false);
          refreshTools();
        }
      });
      Resource comparisonResource = diffNode.getUIComparison().eResource();
      ResourceSet rs = comparisonResource == null? null: comparisonResource.getResourceSet();
      if (rs != null) {
        rs.getResources().remove(comparisonResource);
      }
      MiscUtil.executeAndForget(getEditingDomain(), new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          IComparisonMethod newMethod = _editorInput.getComparisonMethod();
          newMethod.setVerbose(false);
          diffNode.getUIComparison().clear();
          if (_sidesSwapped) {
            diffNode.setLeftRole(diffNode.getRoleForSide(false));
            diffNode.getActualComparison().swapScopes();
          }
          diffNode.setReferenceRole(newMethod.getTwoWayReferenceRole());
          diffNode.setDrivingRole(newMethod.getTwoWayReferenceRole());
          boolean leftEditable = newMethod.getModelScopeDefinition(
              diffNode.getRoleForSide(true)).isEditable();
          boolean rightEditable = newMethod.getModelScopeDefinition(
              diffNode.getRoleForSide(false)).isEditable();
          diffNode.setEditionPossible(leftEditable, true);
          diffNode.setEditionPossible(rightEditable, false);
          diffNode.getActualComparison().compute(
              newMethod.getMatchPolicy(), newMethod.getDiffPolicy(),
              newMethod.getMergePolicy(), monitor_p);
          diffNode.getCategoryManager().update();
        }
      });
      if (rs != null) {
        rs.getResources().add(comparisonResource);
      }
      Display.getDefault().syncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          diffNode.setEditionPossible(editionPossibleLeft, true);
          diffNode.setEditionPossible(editionPossibleRight, false);
          firePropertyChangeEvent(PROPERTY_CURRENT_INPUT, null);
          refresh();
        }
      });
      _editorInput.checkInconsistency(diffNode.getActualComparison());
      return Status.OK_STATUS;
    }
  }
  
}
