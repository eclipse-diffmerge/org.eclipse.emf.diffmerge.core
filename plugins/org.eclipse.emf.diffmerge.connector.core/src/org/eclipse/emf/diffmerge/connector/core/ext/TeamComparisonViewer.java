/**
 * Copyright (c) 2015-2019 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import static org.eclipse.emf.diffmerge.ui.util.UIUtil.CC_MIRRORED_PROPERTY;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.CompareViewerPane;
import org.eclipse.compare.ICompareContainer;
import org.eclipse.compare.INavigatable;
import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.compare.contentmergeviewer.IFlushable;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.diffmerge.connector.core.EMFDiffMergeCoreConnectorPlugin;
import org.eclipse.emf.diffmerge.connector.core.Messages;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetup;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetupManager;
import org.eclipse.emf.diffmerge.ui.setup.EMFDiffMergeEditorInput;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.ITimestampProvider;
import org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;


/**
 * A viewer that can convert an ICompareInput to a model comparison input in Team usage scenarios.
 * It wraps the usual comparison viewer.
 */
public class TeamComparisonViewer extends Viewer implements IFlushable, IPropertyChangeNotifier {
  
  /** The non-null control of the viewer */
  protected final Composite _control;
  
  /** The optional compare configuration */
  protected CompareConfiguration _configuration;
  
  /** The non-null set of listeners that are still to be registered */
  protected final Collection<IPropertyChangeListener> _pendingListeners;
  
  /** The initially null actual comparison viewer */
  protected AbstractComparisonViewer _innerViewer;
  
  /** The potentially null input of this viewer */
  protected Object _input;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param configuration_p an optional compare configuration
   */
  public TeamComparisonViewer(Composite parent_p, CompareConfiguration configuration_p) {
    _configuration = configuration_p;
    ToolBarManager manager = CompareViewerPane.getToolBarManager(parent_p);
    if (manager != null) {
      manager.removeAll(); // Remove text compare contributions to avoid side effects
    }
    _control = createControl(parent_p);
    _pendingListeners = new HashSet<IPropertyChangeListener>();
    _innerViewer = null;
    _input = null;
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    if (_innerViewer == null && !_control.isDisposed()) {
      // Inner viewer not created yet: register listener later
      _pendingListeners.add(listener_p);
    } else {
      _innerViewer.addPropertyChangeListener(listener_p);
    }
  }
  
  /**
   * Close the active editor without saving
   */
  protected void closeEditor() {
    IWorkbenchWindow activeWorkbenchWindow =
        EMFDiffMergeUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
    if (activeWorkbenchWindow != null) {
      IWorkbenchPage page = activeWorkbenchWindow.getActivePage();
      if (page != null) {
        IEditorPart editor = page.getActiveEditor();
        if (editor != null) {
          page.closeEditor(editor, false);
        }
      }
    }
  }
  
  /**
   * Set the orientation parameters of the given comparison setup
   * @param setup_p a non-null comparison setup
   */
  protected void configureOrientation(ComparisonSetup setup_p) {
    // Check order and orientation
    if (isLaterOnTheRight()) {
      if (!laterMayBeOnTheRight(setup_p)) {
        // Invert left and right
        if (_configuration != null) {
          // Ensure consistency with text comparison
          _configuration.setProperty(CC_MIRRORED_PROPERTY, Boolean.TRUE);
        }
      }
    }
    Object mirroredProp = _configuration.getProperty(CC_MIRRORED_PROPERTY);
    if (mirroredProp instanceof Boolean && ((Boolean)mirroredProp).booleanValue()) {
      setup_p.swapLeftRole();
    }
    Role leftRole = setup_p.getLeftRole();
    setup_p.setTwoWayReferenceRole(leftRole.opposite()); // The default remote side in Eclipse
    setup_p.setCanChangeTargetSide(false);
    setup_p.setCanSwapScopeDefinitions(false);
  }
  
  /**
   * Create and return the control of this viewer in the context of the given parent composite
   * @param parent_p a non-null composite
   * @return a non-null control
   */
  protected Composite createControl(Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.NONE);
    // For switching pane combo label, see CompareContentViewerSwitchingPane#setText(String)
    result.setData(CompareUI.COMPARE_VIEWER_TITLE,
        EMFDiffMergeCoreConnectorPlugin.getDefault().getViewerLabel());
    GridLayout layout = new GridLayout(1, true);
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    result.setLayout(layout);
    result.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e_p) {
        handleDispose();
      }
    });
    registerNavigatable(result);
    return result;
  }
  
  /**
   * Create and return a compare editor input from the given setup manager and
   * the given entry points
   * @param manager_p a non null comparison manager
   * @param left_p the left side model
   * @param right_p the right side model
   * @param ancestor_p the ancestor side model
   * @return a compare input or null
   */
  protected EMFDiffMergeEditorInput createEditorInput(
      ComparisonSetupManager manager_p, Object left_p, Object right_p,
      Object ancestor_p) {
    EMFDiffMergeEditorInput result = null;
    ComparisonSetup setup = createSetup(manager_p, left_p, right_p, ancestor_p);
    if (setup != null) {
      IComparisonMethod<?> method = setup.getComparisonMethod();
      if (method != null) {
        if (method.isConfigurable()) {
          // Setting comparison method to gconf configuration if applicable
          ConfigurableComparisonMethod.CONFIGURATOR_VERSIONS.apply(
              setup.getComparisonMethod());
        }
        result = new EMFDiffMergeEditorInput(method);
      }
    }
    if (result == null) {
      result = manager_p.createEditorInputWithUI(getShell(), setup);
    }
    return result;
  }
  
  /**
   * Create and return a comparison setup input from the given setup manager and
   * the given entry points
   * @param manager_p a non null comparison manager
   * @param left_p the left side model
   * @param right_p the right side model
   * @param ancestor_p the ancestor side model
   * @return a compare input or null
   */
  protected ComparisonSetup createSetup(ComparisonSetupManager manager_p,
      Object left_p, Object right_p, Object ancestor_p) {
    Object actualAncestor;
    if (right_p != null && right_p.equals(ancestor_p)) {
      actualAncestor = null; // Use a two-way reference role instead
    } else {
      actualAncestor = ancestor_p;
    }
    ComparisonSetup setup = manager_p.createComparisonSetup(left_p, right_p, actualAncestor);
    if (setup != null) {
      configureOrientation(setup);
      if (setup.getSelectedFactory() == null) {
        // Setting the comparison method factory if obvious
        if (setup.getApplicableComparisonMethodFactories().size() == 1) {
          setup.setSelectedFactory(
              setup.getApplicableComparisonMethodFactories().iterator().next());
        }
      }
      setup.performFinish(false);
    }
    return setup;
  }
  
  /**
   * @see org.eclipse.compare.contentmergeviewer.IFlushable#flush(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void flush(IProgressMonitor monitor_p) {
    if (_innerViewer != null) {
      _innerViewer.flush(monitor_p);
    }
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
  public Object getInput() {
    return _input;
  }
  
  /**
   * Return the role for the left-hand side
   * @return a role which is TARGET or REFERENCE
   */
  protected Role getLeftRole(ComparisonSetup setup_p) {
    Role result = setup_p.getLeftRole();
    if (_configuration != null) {
      Object mirrored = _configuration.getProperty(CC_MIRRORED_PROPERTY);
      if (mirrored instanceof Boolean && ((Boolean)mirrored).booleanValue()) {
        result = result.opposite();
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ISelection getSelection() {
    return (_innerViewer == null)? null: _innerViewer.getSelection();
  }
  
  /**
   * Handle the given compare input as viewer input
   * @param input_p a non-null compare input
   */
  protected void handleCompareInput(ICompareInput input_p) {
    // Requires preliminary work
    Object left = input_p.getLeft();
    Object right = input_p.getRight();
    Object ancestor = input_p.getAncestor();
    // Prompt user for comparison method
    ComparisonSetupManager manager = EMFDiffMergeUIPlugin.getDefault().getSetupManager();
    try {
      EMFDiffMergeEditorInput editorInput = createEditorInput(manager, left, right, ancestor);
      if (editorInput != null) {
        // Not failed/cancelled
        if (_configuration != null) {
          // Register container for action bars
          ICompareContainer compareContainer = _configuration.getContainer();
          if (compareContainer != null) {
            editorInput.setContainer(compareContainer);
          }
        }
        try {
          // Compute comparison
          ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
          dialog.run(true, true, editorInput);
        } catch (InvocationTargetException e) {
          EMFDiffMergeCoreConnectorPlugin.getDefault().logError(e);
        } catch (InterruptedException e) {
          EMFDiffMergeCoreConnectorPlugin.getDefault().logError(e);
        }
        handleComputedEditorInput(editorInput);
      }
    } catch (IllegalArgumentException e) {
      manager.handleSetupError(getShell(), e.getLocalizedMessage());
    }
  }
  
  /**
   * Handle the given editor input that has been computed to provide a comparison
   * @param editorInput_p a non-null editor input
   */
  protected void handleComputedEditorInput(EMFDiffMergeEditorInput editorInput_p) {
    EMFDiffNode compareResult = editorInput_p.getCompareResult();
    if (compareResult != null) {
      // Success: remove previous viewer, create new viewer and set the input
      if (_innerViewer != null) {
        Control innerControl = _innerViewer.getControl();
        if (innerControl != null && !innerControl.isDisposed()) {
          innerControl.dispose();
        }
      }
      Control contents = editorInput_p.createContents(_control);
      GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
      contents.setLayoutData(layoutData);
      _innerViewer = editorInput_p.getViewer();
      _control.pack();
      _control.getParent().layout();
      // Register pending listeners
      for (IPropertyChangeListener listener : _pendingListeners) {
        _innerViewer.addPropertyChangeListener(listener);
      }
      _pendingListeners.clear();
    } else {
      // Failure (no diff): close the viewer and open a dialog
      _innerViewer = null;
      String message = editorInput_p.getMessage();
      if (message == null || message.length() == 0) {
        MessageDialog.openInformation(getShell(),
            Messages.TeamComparisonViewer_NoDiff_Title,
            Messages.TeamComparisonViewer_NoDiff_Message);
      } else {
        MessageDialog.openError(
            getShell(), Messages.TeamComparisonViewer_NoDiff_Title, message);
      }
      closeEditor();
    }
  }
  
  /**
   * Return the shell of the graphical context
   * @return a non-null shell
   */
  protected Shell getShell() {
    return _control.getShell();
  }
  
  /**
   * Dispose this viewer as a reaction to the disposal of its control
   */
  protected void handleDispose() {
    _configuration = null; // Disposed by editor input if needed
    _pendingListeners.clear();
    _innerViewer = null;
    _input = null;
  }
  
  /**
   * Return whether the more recent of the compared objects must be on the right-hand side
   */
  protected boolean isLaterOnTheRight() {
    return false;
  }
  
  /**
   * Return whether the more recent of the compared objects is on the right-hand side
   * in the given setup or if it is unknown
   * @param setup_p a non-null setup
   */
  protected boolean laterMayBeOnTheRight(ComparisonSetup setup_p) {
    Role leftRole = getLeftRole(setup_p);
    IModelScopeDefinition leftScopeDef = setup_p.getScopeDefinition(leftRole);
    IModelScopeDefinition rightScopeDef = setup_p.getScopeDefinition(leftRole.opposite());
    long leftTimestamp = leftScopeDef instanceof ITimestampProvider?
        ((ITimestampProvider)leftScopeDef).getTimestamp(): -1;
    long rightTimestamp = rightScopeDef instanceof ITimestampProvider?
        ((ITimestampProvider)rightScopeDef).getTimestamp(): -1;
    boolean result = true;
    if (leftTimestamp >= 0) {
      // Left is known
      result = rightTimestamp > leftTimestamp;
    } else {
      // Left is unknown: considered later than right if right is known
      result = rightTimestamp < 0;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    if (_innerViewer != null) {
      _innerViewer.refresh();
    }
  }
  
  /**
   * Register a navigatable for navigation from the workbench toolbar buttons
   * @param control_p the non-null control of this viewer
   */
  protected void registerNavigatable(Composite control_p) {
    INavigatable navigatable = new INavigatable() {
      /**
       * Return the delegate navigatable, if any
       * @return a potentially null object
       */
      protected INavigatable getDelegate() {
        INavigatable navResult = null;
        if (_innerViewer != null) {
          navResult = _innerViewer.getNavigatable();
        }
        return navResult;
      }
      /**
       * @see org.eclipse.compare.INavigatable#getInput()
       */
      public Object getInput() {
        return TeamComparisonViewer.this.getInput();
      }
      /**
       * @see org.eclipse.compare.INavigatable#hasChange(int)
       */
      public boolean hasChange(int changeFlag_p) {
        boolean result = false;
        INavigatable delegate = getDelegate();
        if (delegate != null) {
          result = delegate.hasChange(changeFlag_p);
        }
        return result;
      }
      /**
       * @see org.eclipse.compare.INavigatable#openSelectedChange()
       */
      public boolean openSelectedChange() {
        boolean result = false;
        INavigatable delegate = getDelegate();
        if (delegate != null) {
          result = delegate.openSelectedChange();
        }
        return result;
      }
      /**
       * @see org.eclipse.compare.INavigatable#selectChange(int)
       */
      public boolean selectChange(int changeFlag_p) {
        boolean result = false;
        INavigatable delegate = getDelegate();
        if (delegate != null) {
          result = delegate.selectChange(changeFlag_p);
        }
        return result;
      }
    };
    control_p.setData(INavigatable.NAVIGATOR_PROPERTY, navigatable);
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    if (_innerViewer != null) {
      _innerViewer.removePropertyChangeListener(listener_p);
    } else {
      // In case inner viewer has not been created yet
      _pendingListeners.remove(listener_p);
      // Otherwise, listener list of inner viewer is automatically cleared at disposal time
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    if (input_p == _input) {
      return;
    }
    _input = input_p;
    if (input_p instanceof EMFDiffNode || input_p == null) {
      // Can be directly handled by inner viewer
      if (_innerViewer != null) {
        _innerViewer.setInput(input_p);
      }
    } else if (input_p instanceof ICompareInput) {
      handleCompareInput((ICompareInput)input_p);
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    if (_innerViewer != null) {
      _innerViewer.setSelection(selection_p, reveal_p);
    }
  }
  
}
