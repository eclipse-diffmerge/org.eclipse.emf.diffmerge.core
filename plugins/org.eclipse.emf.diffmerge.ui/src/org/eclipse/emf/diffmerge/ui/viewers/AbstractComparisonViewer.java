/**
 * <copyright>
 * 
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
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
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.compare.structuremergeviewer.ICompareInputChangeListener;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
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
implements IFlushable, IPropertyChangeNotifier, ICompareInputChangeListener, IAdaptable {
  
  /** The name of the "current input" property */
  public static final String PROPERTY_CURRENT_INPUT = "PROPERTY_CURRENT_INPUT"; //$NON-NLS-1$
  
  /** The optional action bars */
  private IActionBars _actionBars;
  
  /** The non-null set of property change listeners */
  private final Set<IPropertyChangeListener> _changeListeners;
  
  /** The main control of the viewer */
  private Composite _control;
  
  /** Whether the selection is provided outside the viewer */
  protected boolean _isExternallySynced;
  
  /** The current input (initially null) */
  private EMFDiffNode _input;
  
  /** The non-null difference category provider */
  private IDifferenceCategoryProvider _categoryProvider;
  
  /** The last command/operation that was executed before the last save */
  private Object _lastCommandBeforeSave;
  
  /** The (initially null) undo action */
  private UndoAction _undoAction;
  
  /** The (initially null) redo action */
  private RedoAction _redoAction;
  
  /** The optional navigatable for navigation from the workbench menu bar buttons */
  private INavigatable _navigatable;
  
  /** The (initially null) selection bridge from this viewer to the outside */
  protected SelectionBridge _selectionBridgeToOutside;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   */
  public AbstractComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    _actionBars = actionBars_p;
    _changeListeners = new HashSet<IPropertyChangeListener>(1);
    _input = null;
    _isExternallySynced = true;
    _lastCommandBeforeSave = null;
    _categoryProvider = new DefaultDifferenceCategoryProvider();
    _selectionBridgeToOutside = null;
    setupUndoRedo();
    _control = createControls(parent_p);
    setupSelectionProvider();
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
   * @see org.eclipse.compare.structuremergeviewer.ICompareInputChangeListener#compareInputChanged(org.eclipse.compare.structuremergeviewer.ICompareInput)
   */
  public void compareInputChanged(final ICompareInput source_p) {
    final Display display = getDisplay();
    display.syncExec(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        BusyIndicator.showWhile(display, new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            handleCompareInputChanged(source_p);
          }
        });
      }
    });
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
      MiscUtil.executeWithBusyCursor(domain, null, runnable_p, recordChanges, getDisplay());
    } catch (Exception e) {
      throw new OperationCanceledException(e.getLocalizedMessage()); // Trigger transaction rollback
    }
  }
  
  /**
   * Notify that save has just occurred
   */
  protected void didSave() {
    // Update the last command before save so as to later determine the dirty state
    Object[] currentUndoCommand = getUndoCommand();
    if (currentUndoCommand.length > 0) {
      _lastCommandBeforeSave = currentUndoCommand[0];
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
      e.printStackTrace();
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
        didSave();
      } catch (Exception e) {
        MessageDialog.openError(
            getShell(), EMFDiffMergeUIPlugin.LABEL, Messages.ComparisonViewer_SaveFailed + e);
      }
    }
  }
  
  /**
   * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
   */
  @SuppressWarnings({ "rawtypes", "unchecked" }) // Compatibility with old versions of Eclipse
  public Object  getAdapter(Class adapter_p) {
    Object result = null;
    if (INavigatable.class.equals(adapter_p))
      result = getNavigatable();
    if (result == null)
      result = Platform.getAdapterManager().getAdapter(this, adapter_p);
    return result;
  }
  
  /**
   * Return the provider of difference categories of this viewer
   * @return a potentially null object
   */
  public IDifferenceCategoryProvider getCategoryProvider() {
    return _categoryProvider;
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
   * Return an appropriate display for the UI without specific assumptions
   * @return a non-null display
   */
  protected Display getDisplay() {
    Display result = Display.getCurrent();
    if (result == null) {
      result = Display.getDefault();
    }
    return result;
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
        IComparison comparison = input.getActualComparison();
        if (comparison != null) {
          IModelScope impactedScope = comparison.getScope(
              input.getRoleForSide(onLeft_p));
          if (impactedScope instanceof IPersistentModelScope) {
            Resource resource = ((IPersistentModelScope)impactedScope).getHoldingResource();
            if (resource != null)
              result = TransactionUtil.getEditingDomain(resource);
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return the last command/operation that was executed before the last save
   * @return a potentially null object
   */
  protected Object getLastCommandBeforeSave() {
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
   * Return a selection provider that covers the selection of the sub-viewers
   * @return a non-null selection provider
   */
  protected abstract ISelectionProvider getMultiViewerSelectionProvider();
  
  /**
   * Return the navigatable for this viewer, if any
   * @return a potentially null object
   */
  public INavigatable getNavigatable() {
    return _navigatable;
  }
  
  /**
   * Return the workbench page of this viewer, if any
   * @return a potentially null page
   */
  protected IWorkbenchPage getPage() {
    IWorkbenchPage result = null;
    try {
      IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      result = window.getActivePage();
    } catch (Exception e) {
      // Just proceed
    }
    return result;
  }
  
  /**
   * Return the resource manager for this viewer
   * @return a resource manager which is non-null iff input is not null
   */
  protected ComparisonResourceManager getResourceManager() {
    return getInput() == null? null: getInput().getResourceManager();
  }
  
  /**
   * Return a selection provider for outside this viewer which may selectively reflect
   * the selection of this viewer, e.g., for performance reasons
   * @return a non-null selection provider
   */
  public ISelectionProvider getSelectionProvider() {
    return _selectionBridgeToOutside;
  }
  
  /**
   * Return the shell of this viewer. Must only be called from the UI thread.
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
      IWorkbenchPage page = getPage();
      IWorkbenchSite site = page.getActivePart().getSite();
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
   * Return the image of the given ID
   * @param id_p a non-null image ID
   * @return a (normally) non-null image
   */
  protected Image getImage(ImageID id_p) {
    return EMFDiffMergeUIPlugin.getDefault().getImage(id_p);
  }
  
  /**
   * Return the image descriptor of the given ID
   * @param id_p a non-null image ID
   * @return a (normally) non-null image descriptor
   */
  protected ImageDescriptor getImageDescriptor(ImageID id_p) {
    return EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(id_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return _input;
  }
  
  /**
   * Return the current undo command, if any
   * @return a singleton array in case of success that may contain null, an empty array otherwise
   */
  protected Object[] getUndoCommand() {
    Object[] result = new Object[0];
    EMFDiffNode input = getInput();
    if (input != null) {
      IUndoContext undoContext = input.getUndoContext();
      if (undoContext != null) {
        IOperationHistory opHistory =
            PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
        result = new Object[] { opHistory.getUndoOperation(undoContext) };
      } else {
        EditingDomain domain = input.getEditingDomain();
        if (domain != null) {
          result = new Object[] { getEditingDomain().getCommandStack().getUndoCommand() };
        }
      }
    }
    return result;
  }
  
  /**
   * Dispose this viewer as a reaction to the disposal of its control
   */
  protected void handleDispose() {
    setSelection(StructuredSelection.EMPTY, false);
    if (_selectionBridgeToOutside != null) {
      getMultiViewerSelectionProvider().removeSelectionChangedListener(
          _selectionBridgeToOutside);
      _selectionBridgeToOutside.clearListeners();
      _selectionBridgeToOutside = null;
    }
    if (_actionBars != null) {
      _actionBars.clearGlobalActionHandlers();
      _actionBars = null;
    }
    _changeListeners.clear();
    _input = null;
    _control = null;
    _lastCommandBeforeSave = null;
    _undoAction = null;
    _redoAction = null;
    _navigatable = null;
  }
  
  /**
   * Delegated from compareInputChanged. It can be assumed that the current thread is the UI thread.
   * @see org.eclipse.compare.structuremergeviewer.ICompareInputChangeListener#compareInputChanged(org.eclipse.compare.structuremergeviewer.ICompareInput)
   */
  protected void handleCompareInputChanged(ICompareInput source_p) {
    // Dirty state
    if (source_p instanceof IEditingDomainProvider) {
      EditingDomain domain = ((IEditingDomainProvider)source_p).getEditingDomain();
      if (domain != null) {
        Object[] undoCommand = getUndoCommand();
        if (undoCommand.length > 0) {
          boolean newDirty = undoCommand[0] != getLastCommandBeforeSave();
          firePropertyChangeEvent(CompareEditorInput.DIRTY_STATE, new Boolean(newDirty));
        }
      }
    }
    // Viewer contents and tools
    refresh();
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
    if (oldInput_p instanceof ICompareInput) {
      ((ICompareInput)oldInput_p).removeCompareInputChangeListener(this);
    }
    if (_undoAction != null) {
      _undoAction.setEditingDomain(getEditingDomain());
      _undoAction.update();
    }
    if (_redoAction != null) {
      _redoAction.setEditingDomain(getEditingDomain());
      _redoAction.update();
    }
    if (_actionBars != null) {
      _actionBars.updateActionBars();
    }
    if (input_p instanceof EMFDiffNode) {
      EMFDiffNode node = (EMFDiffNode)input_p;
      registerUserProperties(node);
      registerCategories(node);
      node.updateDifferenceNumbers();
      node.getCategoryManager().setDefaultConfiguration();
    }
    if (input_p instanceof ICompareInput) {
      final ICompareInput compareInput = (ICompareInput)input_p;
      compareInput.addCompareInputChangeListener(this);
      getControl().addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          compareInput.removeCompareInputChangeListener(AbstractComparisonViewer.this);
        }
      });
    }
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
   * Register the difference categories that are applicable to the given input diff node
   * @param node_p a non-null diff node
   */
  protected void registerCategories(EMFDiffNode node_p) {
    IDifferenceCategoryProvider provider = getCategoryProvider();
    if (provider != null)
      provider.provideCategories(node_p);
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
   * Register the user properties this viewer supports in the given input
   * @param input_p a non-null viewer input supporting user properties
   */
  protected void registerUserProperties(EMFDiffNode input_p) {
    // Override for specific user properties
  }
  
/**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.remove(listener_p);
  }
  
  /**
   * Set the provider of difference categories of this viewer.
   * To have an actual impact, this operation requires that the input of this
   * viewer be set afterwards.
   * @param provider_p a potentially null object
   */
  public void setCategoryProvider(
      IDifferenceCategoryProvider provider_p) {
    _categoryProvider = provider_p;
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
   * Set up the selection provider
   */
  protected void setupSelectionProvider() {
    final IWorkbenchSite site = getSite();
    if (site != null) {
      _selectionBridgeToOutside = new SelectionBridge() {
        /**
         * @see org.eclipse.emf.diffmerge.ui.viewers.SelectionBridge#notifyListeners()
         */
        @Override
        protected void notifyListeners() {
          if (_isExternallySynced) {
            super.notifyListeners();
          }
        }
      };
      getMultiViewerSelectionProvider().addSelectionChangedListener(_selectionBridgeToOutside);
      site.setSelectionProvider(_selectionBridgeToOutside);
      // Eclipse 4.x compatibility layer workaround: selection changed event propagation
      ISelectionChangedListener selectionChangedListener = new ISelectionChangedListener() {
        /**
         * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
         */
        public void selectionChanged(SelectionChangedEvent event_p) {
          // Force propagation to selection listeners through the selection service
          IWorkbenchWindow window = site.getWorkbenchWindow();
          if (window != null && !window.getWorkbench().isClosing()) {
            ISelectionService service = window.getSelectionService();
            if (service instanceof ISelectionChangedListener)
              ((ISelectionChangedListener)service).selectionChanged(event_p);
          }
        }
      };
      _selectionBridgeToOutside.addSelectionChangedListener(selectionChangedListener);
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
    _undoAction.setImageDescriptor(getImageDescriptor(ImageID.UNDO));
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
    _redoAction.setImageDescriptor(getImageDescriptor(ImageID.REDO));
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
      BusyIndicator.showWhile(getDisplay(), new Runnable() {
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
          EMFDiffNode input = getInput();
          if (input != null && !input.isReactive()) {
            input.updateDifferenceNumbers();
          }
          if (lastActionSelection != null) {
            setSelection(lastActionSelection, true);
          }
        }
      });
    }
  }
  
}
