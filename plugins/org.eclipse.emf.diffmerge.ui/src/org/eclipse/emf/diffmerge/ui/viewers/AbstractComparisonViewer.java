/**
 * <copyright>
 * 
 * Copyright (c) 2013 Thales Global Services S.A.S.
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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.INavigatable;
import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.compare.contentmergeviewer.IFlushable;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


/**
 * An abstract Viewer for comparisons. It only defines basic mechanisms and facilities but it does
 * not make assumptions about what is shown to the user or what user interactions are proposed.
 * Input: EMFDiffNode.
 * @author Olivier Constant
 */
public abstract class AbstractComparisonViewer extends Viewer
implements IFlushable, IPropertyChangeNotifier, IAdaptable {
  
  /** The name of the "current input" property */
  public static final String PROPERTY_CURRENT_INPUT = "PROPERTY_CURRENT_INPUT"; //$NON-NLS-1$
  
  /** The optional action bars */
  private IActionBars _actionBars;
  
  /** The non-null set of property change listeners */
  private final Set<IPropertyChangeListener> _changeListeners;
  
  /** The main control of the viewer */
  private Composite _control;
  
  /** The current input (initially null) */
  private EMFDiffNode _input;
  
  /** The last command that was executed before the last save */
  private Command _lastCommandBeforeSave;
  
  /** The (initially null) undo action */
  private UndoAction _undoAction;
  
  /** The (initially null) redo action */
  private RedoAction _redoAction;
  
  /** The optional navigatable for navigation from the workbench menu bar buttons */
  private INavigatable _navigatable;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   */
  public AbstractComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    _actionBars = actionBars_p;
    _changeListeners = new HashSet<IPropertyChangeListener>(1);
    _input = null;
    _lastCommandBeforeSave = null;
    setupUndoRedo();
    _control = createControls(parent_p);
    hookControl(_control);
    registerNavigatable(_control, createNavigatable());
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.add(listener_p);
  }
  
  /**
   * Create the controls for this viewer and return the main control
   * @param parent_p a non-null composite
   * @return a non-null composite
   */
  protected abstract Composite createControls(Composite parent_p);
  
  /**
   * Create and return the navigatable for this viewer, if relevant
   * @return a potentially null object
   */
  protected INavigatable createNavigatable() {
    return null;
  }
  
  /**
   * Execute the given runnable that may modify the model on the given side
   * and ignores transactional aspects
   * @param runnable_p a non-null object
   * @param onLeft_p whether the impacted scope is the one on the left-hand side
   */
  protected void executeOnModel(final Runnable runnable_p, boolean onLeft_p) {
    EMFDiffNode input = getInput();
    final boolean recordChanges = input != null && input.isUndoRedoSupported();
    final EditingDomain domain = getEditingDomain(onLeft_p);
    try {
      MiscUtil.executeWithBusyCursor(domain, null, runnable_p, recordChanges, getShell().getDisplay());
    } catch (Exception e) {
      throw new OperationCanceledException(e.getLocalizedMessage()); // Trigger transaction rollback
    }
  }
  
  /**
   * Execute the given runnable with progress that may modify the model on the given side
   * and ignores transactional aspects
   * @param behavior_p a non-null runnable with progress
   * @param onLeft_p whether the impacted scope is the one on the left-hand side
   */
  protected void executeOnModel(final IRunnableWithProgress behavior_p, boolean onLeft_p) {
    EMFDiffNode input = getInput();
    final boolean recordChanges = input != null && input.isUndoRedoSupported();
    final EditingDomain domain = getEditingDomain(onLeft_p);
    try {
      MiscUtil.executeWithProgress(domain, null, behavior_p, recordChanges);
    } catch (Exception e) {
      throw new OperationCanceledException(e.getLocalizedMessage()); // Trigger transaction rollback
    }
  }
  
  /**
   * Notify listeners of a property change event
   * @param propertyName_p the non-null name of the property
   * @param newValue_p the potentially null, new value of the property
   */
  protected void firePropertyChangeEvent(String propertyName_p, Object newValue_p) {
    PropertyChangeEvent event = new PropertyChangeEvent(
        this, propertyName_p, null, newValue_p);
    for (IPropertyChangeListener listener : _changeListeners) {
      listener.propertyChange(event);
    }
  }
  
  /**
   * @see org.eclipse.compare.contentmergeviewer.IFlushable#flush(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void flush(IProgressMonitor monitor_p) {
    IComparison comparison = getComparison();
    if (comparison != null) {
      try {
        if (getInput().isModified(true)) {
          IModelScope leftScope = comparison.getScope(getInput().getRoleForSide(true));
          if (leftScope instanceof IPersistentModelScope.Editable)
            ((IPersistentModelScope.Editable)leftScope).save();
        }
        if (getInput().isModified(false)) {
          IModelScope rightScope = comparison.getScope(getInput().getRoleForSide(false));
          if (rightScope instanceof IPersistentModelScope.Editable)
            ((IPersistentModelScope.Editable)rightScope).save();
        }
        firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(false));
        if (getEditingDomain() != null)
          _lastCommandBeforeSave = getEditingDomain().getCommandStack().getUndoCommand();
      } catch (Exception e) {
        MessageDialog.openError(
            getShell(), EMFDiffMergeUIPlugin.LABEL, Messages.ComparisonViewer_SaveFailed + e);
      }
    }
  }
  
  /**
   * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  public Object  getAdapter(Class adapter_p) {
    Object result = null;
    if (INavigatable.class.equals(adapter_p))
      result = getNavigatable();
    if (result == null)
      result = Platform.getAdapterManager().getAdapter(this, adapter_p);
    return result;
  }
  
  /**
   * Return the comparison for this viewer
   * @return a comparison which is assumed non-null after setInput(Object) has been invoked
   */
  protected EComparison getComparison() {
    UIComparison uiComparison = getUIComparison();
    return uiComparison == null? null: uiComparison.getActualComparison();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Composite getControl() {
    return _control;
  }
  
  /**
   * Return the editing domain for this viewer
   * @return an editing domain which may be non-null after setInput(Object) has been invoked
   */
  protected EditingDomain getEditingDomain() {
    return getInput() == null? null: getInput().getEditingDomain();
  }
  
  /**
   * Return the editing domain for the model on the given side, if any
   * @param onLeft_p whether the side is the left-hand side
   * @return a potentially null editing domain
   */
  protected EditingDomain getEditingDomain(boolean onLeft_p) {
    EditingDomain result = getEditingDomain();
    if (result == null) {
      EMFDiffNode input = getInput();
      if (input != null) {
        // Look for possible transactional editing domain
        IModelScope impactedScope = input.getActualComparison().getScope(
            input.getRoleForSide(onLeft_p));
        if (impactedScope instanceof IPersistentModelScope) {
          Resource resource = ((IPersistentModelScope)impactedScope).getHoldingResource();
          if (resource != null)
            result = TransactionUtil.getEditingDomain(resource);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the last command that was executed before the last save
   * @return a potentially null command
   */
  protected Command getLastCommandBeforeSave() {
    return _lastCommandBeforeSave;
  }
  
  /**
   * Return a name for the scope on the given side
   * @param onLeft_p whether the scope is the one on the left-hand side
   * @return a potentially null string
   */
  protected String getModelName(boolean onLeft_p) {
    IModelScope scope = getComparison().getScope(getInput().getRoleForSide(onLeft_p));
    return DiffMergeLabelProvider.getInstance().getText(scope);
  }
  
  /**
   * Return a selection provider that covers the selection of sub-viewers if any
   * @return a non-null selection provider
   */
  public ISelectionProvider getMultiViewerSelectionProvider() {
    return this;
  }
  
  /**
   * Return the navigatable for this viewer, if any
   * @return a potentially null object
   */
  public INavigatable getNavigatable() {
    return _navigatable;
  }
  
  /**
   * Return the resource manager for this viewer
   * @return a resource manager which is non-null iff input is not null
   */
  protected ComparisonResourceManager getResourceManager() {
    return getInput() == null? null: getInput().getResourceManager();
  }
  
  /**
   * Return the shell of this viewer
   * @return a non-null shell
   */
  protected Shell getShell() {
    return getControl().getShell();
  }
  
  /**
   * Return the workbench part site of this viewer, if any
   * @return a potentially null site
   */
  protected IWorkbenchPartSite getSite() {
    IWorkbenchPartSite result = null;
    try {
      IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      IWorkbenchSite site = window.getActivePage().getActiveEditor().getSite();
      if (site instanceof IWorkbenchPartSite)
        result = (IWorkbenchPartSite)site;
    } catch (Exception e) {
      // Just proceed
    }
    return result;
  }
  
  /**
   * Return the UI comparison for this viewer
   * @return a UI comparison which is assumed non-null after setInput(Object) has been invoked
   */
  protected UIComparison getUIComparison() {
    return getInput() == null? null: getInput().getUIComparison();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return _input;
  }
  
  /**
   * Dispose this viewer as a reaction to the disposal of its control
   */
  protected void handleDispose() {
    if (_actionBars != null)
      _actionBars.clearGlobalActionHandlers();
    _actionBars = null;
    _changeListeners.clear();
    _input = null;
    _control = null;
    _lastCommandBeforeSave = null;
    _undoAction = null;
    _redoAction = null;
    _navigatable = null;
  }
  
  /**
   * Ensure that the viewer is disposed when its control is disposed.
   * See ContentViewer#hookControl(Control).
   * @param control_p the non-null control of the viewer
   */
  private void hookControl(Control control_p) {
    control_p.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent event) {
        handleDispose();
      }
    });
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    if (_undoAction != null) {
      _undoAction.setEditingDomain(getEditingDomain());
      _undoAction.update();
    }
    if (_redoAction != null) {
      _redoAction.setEditingDomain(getEditingDomain());
      _redoAction.update();
    }
    if (_actionBars != null)
      _actionBars.updateActionBars();
    firePropertyChangeEvent(PROPERTY_CURRENT_INPUT, null);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    refreshTools();
    // Override if needed, but call super.refresh()
  }
  
  /**
   * Refresh the tools of the viewer
   */
  protected void refreshTools() {
    if (_undoAction != null)
      _undoAction.update();
    if (_redoAction != null)
      _redoAction.update();
    if (_actionBars != null)
      _actionBars.updateActionBars();
  }
  
  /**
   * Register the given navigatable for this viewer,
   * allowing navigation from the workbench menu bar buttons
   * @param control_p the non-null control of the viewer
   * @param navigatable_p the potentially null navigatable
   */
  protected void registerNavigatable(Control control_p, INavigatable navigatable_p) {
    _navigatable = navigatable_p;
    if (_navigatable != null)
      control_p.setData(INavigatable.NAVIGATOR_PROPERTY, _navigatable);
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.remove(listener_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    if (input_p == null || input_p instanceof EMFDiffNode) {
      Object oldInput = getInput();
      _input = (EMFDiffNode)input_p;
      inputChanged(_input, oldInput);
    }
  }
  
  /**
   * Set up the undo/redo mechanism
   */
  protected void setupUndoRedo() {
    // Undo
    _undoAction = new UndoAction(null) {
      /**
       * @see org.eclipse.emf.edit.ui.action.UndoAction#run()
       */
      @Override
      public void run() {
        undoRedo(true);
      }
      /**
       * @see org.eclipse.emf.edit.ui.action.UndoAction#update()
       */
      @Override
      public void update() {
        if (getEditingDomain() != null)
          super.update();
      }
    };
    _undoAction.setImageDescriptor(EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(
        EMFDiffMergeUIPlugin.ImageID.UNDO));
    // Redo
    _redoAction = new RedoAction() {
      /**
       * @see org.eclipse.emf.edit.ui.action.RedoAction#run()
       */
      @Override
      public void run() {
        undoRedo(false);
      }
      /**
       * @see org.eclipse.emf.edit.ui.action.RedoAction#update()
       */
      @Override
      public void update() {
        if (getEditingDomain() != null)
          super.update();
      }
    };
    _redoAction.setImageDescriptor(EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(
        EMFDiffMergeUIPlugin.ImageID.REDO));
    if (_actionBars != null) {
      _actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), _undoAction);
      _actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), _redoAction);
    }
  }
  
  /**
   * Apply the undo/redo mechanism
   * @param undo_p whether the action is undo or redo
   */
  protected void undoRedo(final boolean undo_p) {
    final EditingDomain editingDomain = getEditingDomain();
    if (editingDomain != null) {
      BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          final CommandStack stack = editingDomain.getCommandStack();
          final ComparisonSelection lastActionSelection = getUIComparison().getLastActionSelection();
          if (undo_p && stack.canUndo())
            stack.undo();
          else if (!undo_p && stack.canRedo())
            stack.redo();
          boolean dirty = stack.getUndoCommand() != getLastCommandBeforeSave();
          firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(dirty));
          undoRedoPerformed(undo_p);
          if (lastActionSelection != null)
            setSelection(lastActionSelection, true);
        }
      });
    }
  }
  
  /**
   * Called when undo/redo has been performed, override to react
   * @param undo_p whether it was undo or redo
   */
  protected void undoRedoPerformed(final boolean undo_p) {
    // Nothing by default
  }
  
}
