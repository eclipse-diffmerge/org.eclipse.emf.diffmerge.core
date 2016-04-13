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
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.setup;

import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.ICompareNavigator;
import org.eclipse.compare.INavigatable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.util.UidiffdataResourceFactoryImpl;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.util.InconsistencyDialog;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.SelectionBridge;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;


/**
 * A CompareEditorInput dedicated to model Diff/Merge.
 * @see CompareEditorInput
 * @author Olivier Constant
 */
public class EMFDiffMergeEditorInput extends CompareEditorInput {
  
  /** The non-null (unless disposed) comparison method **/
  protected IComparisonMethod _comparisonMethod;
  
  /** The initially null resource that holds the comparison */
  protected Resource _comparisonResource;
  
  /** The left comparison scope (initially null) **/
  protected IEditableModelScope _leftScope;
  
  /** The right comparison scope (initially null) **/
  protected IEditableModelScope _rightScope;
  
  /** The ancestor comparison scope (initially null) **/
  protected IEditableModelScope _ancestorScope;
  
  /** The initially null viewer */
  protected AbstractComparisonViewer _viewer;
  
  /** Whether the comparison originally contained differences (initially true) */
  private boolean _foundDifferences;
  
  /** Whether the editor is dirty (required for compatibility with Indigo) */ //OCO
  private boolean _isDirty;
  
  /** The (initially null) property sheet page to show in the Properties view */
  protected PropertySheetPage _propertySheetPage;
  
  /** The (initially null) command stack listener on the editing domain, if any */
  protected CommandStackListener _commandStackListener;
  
  /** The non-null (unless disposed) selection bridge between the viewer and the workbench site */
  protected SelectionBridge _selectionBridge;
  
  /** The (initially null) compare navigator for the workbench navigation buttons */
  private ICompareNavigator _navigator;
  
  
  /**
   * Constructor
   * @param method_p a non-null comparison method
   */
  public EMFDiffMergeEditorInput(IComparisonMethod method_p) {
    super(new CompareConfiguration());
    _comparisonMethod = method_p;
    _leftScope = null;
    _rightScope = null;
    _ancestorScope = null;
    _comparisonResource = null;
    _foundDifferences = true;
    _isDirty = false;
    _navigator = createNavigator();
    _selectionBridge = new SelectionBridge();
    initializeCompareConfiguration();
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#canRunAsJob()
   */
  @Override
  public boolean canRunAsJob() {
    return true;
  }
  
  /**
   * Ensure that the selection provider of the workbench site is the intended one
   */
  protected void checkSelectionProvider() {
    IWorkbenchSite site = getSite();
    if (site != null && site.getSelectionProvider() != _selectionBridge)
      site.setSelectionProvider(_selectionBridge);
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#contentsCreated()
   */
  @Override
  protected void contentsCreated() {
    super.contentsCreated();
    _viewer.getControl().addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent ev) {
        handleDispose();
      }
    });
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#contributeToToolBar(org.eclipse.jface.action.ToolBarManager)
   */
  @Override
  public void contributeToToolBar(ToolBarManager toolBarManager) {
    // Nothing
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#createContents(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public Control createContents(Composite parent_p) {
    // Create viewer
    _viewer = _comparisonMethod.createComparisonViewer(parent_p, getActionBars());
    // Plug it to the selection provider
    if (_selectionBridge != null)
      _viewer.getMultiViewerSelectionProvider().addSelectionChangedListener(_selectionBridge);
    _viewer.addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        String propertyName = event_p.getProperty();
        if (CompareEditorInput.DIRTY_STATE.equals(propertyName)) {
          boolean dirty = ((Boolean)event_p.getNewValue()).booleanValue();
          setDirty(dirty);
        }
      }
    });
    // Create viewer contents
    _viewer.setInput(getCompareResult());
    contentsCreated();
    return _viewer.getControl();
  }
  
  /**
   * Create and return a navigator
   * @see EMFDiffMergeEditorInput#getNavigator()
   * @return a non-null object
   */
  protected ICompareNavigator createNavigator() {
    return new ICompareNavigator() {
      /**
       * @see org.eclipse.compare.ICompareNavigator#selectChange(boolean)
       */
      public boolean selectChange(boolean next_p) {
        boolean result = false;
        int change = next_p? INavigatable.NEXT_CHANGE: INavigatable.PREVIOUS_CHANGE;
        if (_viewer != null)
          result = _viewer.getNavigatable().selectChange(change);
        return result;
      }
    };
  }
  
  /**
   * Generate a title for the editor
   * @return a potentially null string
   */
  protected String createTitle() {
    Role leftRole = EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
    String leftDesc = _comparisonMethod.getModelScopeDefinition(leftRole).getShortLabel();
    String rightDesc = _comparisonMethod.getModelScopeDefinition(leftRole.opposite()).getShortLabel();
    String result = String.format(Messages.EMFDiffMergeEditorInput_Title, leftDesc, rightDesc);
    return result;
  }
  
  /**
   * Dispose the resources which have been added during the comparison process
   */
  protected void disposeResources() {
    final EditingDomain domain = getEditingDomain();
    final Set<Resource> unloaded = new HashSet<Resource>();
    MiscUtil.executeAndForget(domain, new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        if (_comparisonResource != null) {
          for (EObject root : _comparisonResource.getContents()) {
            if (root instanceof UIComparison) {
              UIComparison uiComparison = (UIComparison)root;
              uiComparison.dispose();
            }
          }
          _comparisonResource.unload();
          domain.getResourceSet().getResources().remove(_comparisonResource);
          unloaded.add(_comparisonResource);
        }
        if (_leftScope instanceof IPersistentModelScope)
          unloaded.addAll(((IPersistentModelScope)_leftScope).unload());
        if (_rightScope instanceof IPersistentModelScope)
          unloaded.addAll(((IPersistentModelScope)_rightScope).unload());
        if (_ancestorScope instanceof IPersistentModelScope)
          unloaded.addAll(((IPersistentModelScope)_ancestorScope).unload());
      }
    });
    if (domain != null)
      domain.getCommandStack().flush();
    if (domain instanceof TransactionalEditingDomain) {
      for (Resource resource : unloaded) {
        TransactionUtil.disconnectFromEditingDomain(resource);
        // Cleaning up Eclipse operation history
        try {
          ResourceUndoContext context = new ResourceUndoContext(
              (TransactionalEditingDomain)domain, resource);
          PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().dispose(
              context, true, true, false);
        } catch (Exception e) {
          // Workbench being disposed: proceed
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#flushViewers(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  protected void flushViewers(IProgressMonitor monitor_p) {
    _viewer.flush(monitor_p);
  }
  
  /**
   * Return whether the comparison originally contained differences
   * @return true by default before the comparison has actually been computed
   */
  public boolean foundDifferences() {
    return _foundDifferences;
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#getCompareResult()
   */
  @Override
  public EMFDiffNode getCompareResult() {
    return (EMFDiffNode)super.getCompareResult();
  }
  
  /**
   * Return the editing domain in which comparison takes place, if any
   * @return a potentially null editing domain
   * @see IEditingDomainProvider#getEditingDomain()
   */
  public EditingDomain getEditingDomain() {
    return _comparisonMethod != null? _comparisonMethod.getEditingDomain(): null;
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#getNavigator()
   */
  @Override
  public synchronized ICompareNavigator getNavigator() {
    return _navigator;
  }
  
  /**
   * Return a property sheet page for the Properties view if possible
   * @return a potentially null object
   */
  public IPropertySheetPage getPropertySheetPage() {
    if (_propertySheetPage == null) {
      EditingDomain domain = getEditingDomain();
      if (domain instanceof AdapterFactoryEditingDomain) {
        // Property sheet page
        AdapterFactoryEditingDomain afDomain = (AdapterFactoryEditingDomain)domain;
        _propertySheetPage = new ExtendedPropertySheetPage(afDomain);
        _propertySheetPage.setPropertySourceProvider(
            new AdapterFactoryContentProvider(afDomain.getAdapterFactory()));
        // Command stack listener for property sheet page update
        _commandStackListener = new CommandStackListener() {
          public void commandStackChanged(final EventObject event_p) {
            Shell shell = getShell();
            if (shell != null) {
              shell.getDisplay().asyncExec(new Runnable() {
                public void run() {
                  if (_propertySheetPage != null && !_propertySheetPage.getControl().isDisposed())
                    _propertySheetPage.refresh();
                }
              });
            }
          }
        };
        afDomain.getCommandStack().addCommandStackListener(_commandStackListener);
      }
    }
    return _propertySheetPage;
  }
  
  /**
   * Return the current shell, if available
   * @return a potentially null shell
   */
  protected Shell getShell() {
    Shell result = null;
    IWorkbenchSite site = getSite();
    if (site != null)
      result = site.getShell();
    return result;
  }
  
  /**
   * Return the contextual workbench site, if any
   * @return a potentially null workbench site
   */
  protected IWorkbenchSite getSite() {
    IWorkbenchSite result = null;
    IWorkbenchPart part = getWorkbenchPart();
    if (part != null)
      result = part.getSite();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.common.ui.viewer.IViewerProvider#getViewer()
   */
  public AbstractComparisonViewer getViewer() {
    return _viewer; // Non-null after createContents(Composite) has returned
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#handleDispose()
   */
  @Override
  protected void handleDispose() {
    _navigator = null;
    Display display = Display.getDefault();
    if (_propertySheetPage != null)
      display.asyncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          _propertySheetPage.dispose();
        }
      });
    if (_viewer != null) {
      display.asyncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          _viewer.setSelection(StructuredSelection.EMPTY, false);
          if (_selectionBridge != null) {
            _viewer.getMultiViewerSelectionProvider().removeSelectionChangedListener(_selectionBridge);
            _selectionBridge = null;
          }
          _viewer = null;
        }
      });
    }
    if (_commandStackListener != null && getEditingDomain() != null)
      getEditingDomain().getCommandStack().removeCommandStackListener(_commandStackListener);
    super.handleDispose();
    Runnable disposeBehavior = new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        if (getCompareResult() != null)
          getCompareResult().dispose();
        disposeResources();
        if (_comparisonMethod != null)
          _comparisonMethod.dispose();
        _comparisonMethod = null;
        _ancestorScope = null;
        _leftScope = null;
        _rightScope = null;
        _comparisonResource = null;
      }
    };
    boolean inUIThread = display.getThread() == Thread.currentThread();
    if (inUIThread)
      BusyIndicator.showWhile(display, disposeBehavior);
    else
      disposeBehavior.run();
    // Clear the input to avoid memory leak
    try {
      super.run(null);
    } catch (Exception e) {
      // Nothing
    }
  }
  
  /**
   * Display appropriate messages according to the problem that happened during the comparison process
   */
  protected void handleExecutionProblem(Throwable problem_p) {
    Throwable diagnostic = problem_p;
    if (diagnostic instanceof WrappedException)
      diagnostic = ((WrappedException)diagnostic).exception();
    String message;
    if (diagnostic instanceof PackageNotFoundException) {
      PackageNotFoundException pnfe = (PackageNotFoundException)diagnostic;
      message = MiscUtil.buildString(
          Messages.EMFDiffMergeEditorInput_WrongMetamodel, "\n", //$NON-NLS-1$
          pnfe.getLocation(), ".\n", //$NON-NLS-1$
          Messages.EMFDiffMergeEditorInput_MigrationNeeded);
    } else {
      String msg = diagnostic.getLocalizedMessage();
      if (msg == null)
        msg = diagnostic.toString();
      message = MiscUtil.buildString(
          Messages.EMFDiffMergeEditorInput_Failure, "\n", msg);  //$NON-NLS-1$
    }
    final Shell shell = getShell();
    if (shell != null) {
      final String finalMessage = message;
      shell.getDisplay().syncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          MessageDialog.openError(shell, EMFDiffMergeUIPlugin.LABEL, finalMessage);
        }
      });
    }
  }
  
  /**
   * Initialize the CompareConfiguration of this CompareEditorInput
   */
  protected void initializeCompareConfiguration() {
    CompareConfiguration cc = getCompareConfiguration();
    cc.setLeftLabel(_comparisonMethod.getModelScopeDefinition(Role.TARGET).getLabel());
    cc.setRightLabel(_comparisonMethod.getModelScopeDefinition(Role.REFERENCE).getLabel());
    IModelScopeDefinition ancestorDefinition =
      _comparisonMethod.getModelScopeDefinition(Role.ANCESTOR);
    cc.setAncestorLabel((ancestorDefinition == null) ? "" : ancestorDefinition.getLabel()); //$NON-NLS-1$
    cc.setLeftEditable(_comparisonMethod.getModelScopeDefinition(Role.TARGET).isEditable());
    cc.setRightEditable(_comparisonMethod.getModelScopeDefinition(Role.REFERENCE).isEditable());
  }
  
  /**
   * Create and return the comparison
   * @return a non-null comparison
   */
  protected EComparison initializeComparison() {
    EComparison result = new EComparisonImpl(_leftScope, _rightScope, _ancestorScope);
    return result;
  }
  
  /**
   * Create and return the diff node for the given comparison
   * @param comparison_p a non-null comparison
   * @return a non-null diff node
   */
  protected EMFDiffNode initializeDiffNode(EComparison comparison_p) {
    ResourceSet resourceSet = (getEditingDomain() != null)? getEditingDomain().getResourceSet(): null;
    if (resourceSet != null) {
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
          EMFDiffMergeUIPlugin.UI_DIFF_DATA_FILE_EXTENSION, new UidiffdataResourceFactoryImpl());
      String resourceURI = "platform:/resource/comparison/comparison." + //$NON-NLS-1$
          EMFDiffMergeUIPlugin.UI_DIFF_DATA_FILE_EXTENSION;
      _comparisonResource = resourceSet.createResource(URI.createURI(resourceURI));
    }
    CompareConfiguration cc = getCompareConfiguration();
    EMFDiffNode result = new EMFDiffNode(
        comparison_p, getEditingDomain(), cc.isLeftEditable(), cc.isRightEditable());
    result.setReferenceRole(_comparisonMethod.getTwoWayReferenceRole());
    return result;
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#isSaveNeeded()
   */
  @Override
  public boolean isSaveNeeded() {
    // Redefined for compatibility with Indigo and synchronization with workbench views
    checkSelectionProvider(); // This is a hack
    return _isDirty;
  }
  
  /**
   * Load the model scopes
   * @param monitor_p a non-null monitor for reporting progress
   */
  protected void loadScopes(IProgressMonitor monitor_p) {
    EditingDomain domain = getEditingDomain();
    boolean threeWay = _comparisonMethod.isThreeWay();
    Role leftRole = EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
    String mainTaskName = Messages.EMFDiffMergeEditorInput_Loading;
    SubMonitor loadingMonitor = SubMonitor.convert(
        monitor_p, mainTaskName, threeWay ? 4 : 3);
    loadingMonitor.worked(1);
    // Loading left
    loadingMonitor.subTask(Messages.EMFDiffMergeEditorInput_LoadingLeft);
    Object leftLoadingContext = (domain != null)? domain: _comparisonMethod.getResourceSet(leftRole);
    _leftScope = _comparisonMethod.getModelScopeDefinition(leftRole).createScope(leftLoadingContext);
    if (_leftScope == null)
      throw new RuntimeException(Messages.EMFDiffMergeEditorInput_LeftScopeNull);
    if (_leftScope instanceof IPersistentModelScope) {
      try {
        ((IPersistentModelScope)_leftScope).load();
      } catch (Exception e) {
        throw new WrappedException(e);
      }
    }
    loadingMonitor.worked(1);
    if (loadingMonitor.isCanceled())
      throw new OperationCanceledException();
    // Loading right
    loadingMonitor.subTask(Messages.EMFDiffMergeEditorInput_LoadingRight);
    Object rightLoadingContext = (domain != null)? domain: _comparisonMethod.getResourceSet(leftRole.opposite());
    _rightScope = _comparisonMethod.getModelScopeDefinition(leftRole.opposite()).createScope(rightLoadingContext);
    if (_rightScope == null)
      throw new RuntimeException(Messages.EMFDiffMergeEditorInput_RightScopeNull);
    if (_rightScope instanceof IPersistentModelScope) {
      try {
        ((IPersistentModelScope)_rightScope).load();
      } catch (Exception e) {
        throw new WrappedException(e);
      }
    }
    loadingMonitor.worked(1);
    if (loadingMonitor.isCanceled())
      throw new OperationCanceledException();
    // Loading ancestor
    if (threeWay) {
      loadingMonitor.subTask(Messages.EMFDiffMergeEditorInput_LoadingAncestor);
      Object ancestorLoadingContext = (domain != null)? domain: _comparisonMethod.getResourceSet(Role.ANCESTOR);
      _ancestorScope = _comparisonMethod.getModelScopeDefinition(Role.ANCESTOR).createScope(ancestorLoadingContext);
      if (_ancestorScope == null)
        throw new RuntimeException(Messages.EMFDiffMergeEditorInput_AncestorScopeNull);
      if (_ancestorScope instanceof IPersistentModelScope) {
        try {
          ((IPersistentModelScope)_ancestorScope).load();
        } catch (Exception e) {
          throw new WrappedException(e);
        }
      }
      loadingMonitor.worked(1);
      if (loadingMonitor.isCanceled())
        throw new OperationCanceledException();
    }
  }
  
  /**
   * Return whether merge can be considered complete
   */
  public boolean mergeIsComplete() {
    return !isDirty() && (_viewer.getInput() == null || _viewer.getInput().isEmpty());
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#prepareInput(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  protected Object prepareInput(IProgressMonitor monitor_p) throws
      InvocationTargetException, InterruptedException {
    if (monitor_p == null) // True when called from handleDispose()
      return null;
    String title = createTitle();
    setTitle(title);
    boolean scopesReady = _leftScope != null;
    SubMonitor monitor = SubMonitor.convert(monitor_p, EMFDiffMergeUIPlugin.LABEL, 2);
    EMFDiffNode result = null;
    try {
      if (!scopesReady)
        loadScopes(monitor.newChild(1));
      EComparison comparison = initializeComparison();
      comparison.compute(_comparisonMethod.getMatchPolicy(), _comparisonMethod.getDiffPolicy(),
          _comparisonMethod.getMergePolicy(), monitor.newChild(scopesReady? 2: 1));
      if (!comparison.isConsistent())
        handleInconsistency(comparison);
      _foundDifferences = comparison.hasRemainingDifferences();
      if (_foundDifferences)
        result = initializeDiffNode(comparison);
      else
        handleDispose();
    } catch (OperationCanceledException e) {
      // No user feedback is needed
      handleDispose();
    } catch (Throwable t) {
      // Cannot load models
      setMessage(Messages.EMFDiffMergeEditorInput_CannotLoad); 
      handleExecutionProblem(t);
      handleDispose();
    }
    return result;
  }
  
  /**
   * Warn the user that the comparison is not consistent due to duplicate match IDs
   * @see IComparison#isConsistent()
   * @param comparison_p a non-null inconsistent comparison
   */
  protected void handleInconsistency(final IComparison comparison_p) {
    final Shell shell = getShell();
    if (shell != null) {
      final int[] pressed = new int[1];
      shell.getDisplay().syncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          MessageDialog dialog = new MessageDialog(
              shell, EMFDiffMergeUIPlugin.LABEL, null,
              Messages.InconsistencyDialog_DuplicateIDs,
              MessageDialog.WARNING,
              new String[] { IDialogConstants.OK_LABEL, IDialogConstants.SHOW_DETAILS_LABEL },
              0);
          pressed[0] = dialog.open();
        }
      });
      if (0 != pressed[0])
        shell.getDisplay().syncExec(new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            InconsistencyDialog dialog = new InconsistencyDialog(shell, comparison_p);
            dialog.open();
          }
        });
    }
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#run(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void run(IProgressMonitor monitor_p) throws InterruptedException, InvocationTargetException {
    if (getCompareResult() != null)
      getCompareResult().dispose();
    super.run(monitor_p);
    if (getCompareResult() != null) {
      if (foundDifferences()) {
        // This is done here because the compare result must have been assigned:
        // directly referencing the UIComparison would result in a memory leak
        if (_comparisonResource != null) {
          EditingDomain domain = getEditingDomain();
          if (domain != null) {
            MiscUtil.executeAndForget(domain, new Runnable() {
              /**
               * @see java.lang.Runnable#run()
               */
              public void run() {
                _comparisonResource.getContents().add(getCompareResult().getUIComparison());
              }
            });
            domain.getCommandStack().flush();
          }
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#setDirty(boolean)
   */
  @Override
  public void setDirty(boolean dirty_p) {
    // Redefined for compatibility with Indigo
    boolean oldDirty = isDirty();
    if (dirty_p != oldDirty) {
      _isDirty = dirty_p;
      PropertyChangeEvent event = new PropertyChangeEvent(
          this, DIRTY_STATE, Boolean.valueOf(oldDirty), Boolean.valueOf(_isDirty));
      firePropertyChange(event);
    }
  }
  
  /**
   * @see org.eclipse.compare.CompareEditorInput#setFocus2()
   */
  @Override
  public boolean setFocus2() {
    boolean result = false;
    if (_viewer != null)
      result = _viewer.getControl().setFocus();
    return result;
  }
  
}
