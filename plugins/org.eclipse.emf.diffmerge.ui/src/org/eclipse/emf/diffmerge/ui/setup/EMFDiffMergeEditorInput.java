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
package org.eclipse.emf.diffmerge.ui.setup;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.diffuidata.util.UidiffdataResourceFactoryImpl;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
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
  
  /** The comparison scopes (initially null iff URIs are not null) **/
  protected IEditableModelScope _leftScope, _rightScope, _ancestorScope;
  
  /** The initially null viewer */
  protected ComparisonViewer _viewer;
  
  /** Whether the comparison originally contained differences (initially true) */
  private boolean _foundDifferences;
  
  /** Whether the editor is dirty (required for compatibility with Indigo) */ //OCO
  private boolean _isDirty;
  
  /** The (initially null) property sheet page to show in the Properties view */
  protected PropertySheetPage _propertySheetPage;
  
  /** The (initially null) command stack listener on the editing domain, if any */
  protected CommandStackListener _commandStackListener;
  
  /** The non-null (unless dieposed) selection bridge between the viewer and the workbench site */
  protected SelectionBridge _selectionBridge;
  
  
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
    _viewer = new ComparisonViewer(parent_p, getActionBars());
    if (_selectionBridge != null)
      _viewer.addSelectionChangedListener(_selectionBridge);
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
    EditingDomain domain = getEditingDomain();
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
   * Return the editing domain in which comparison takes place, if any
   * @return a potentially null resource set
   */
  public ResourceSet getResourceSet() {
    ResourceSet result = null;
    EditingDomain domain = getEditingDomain();
    if (domain != null)
      result = domain.getResourceSet();
    else if (_comparisonMethod != null)
      result = _comparisonMethod.getResourceSet();
    return result;
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
   * @see org.eclipse.compare.CompareEditorInput#handleDispose()
   */
  @Override
  protected void handleDispose() {
    if (_propertySheetPage != null)
      _propertySheetPage.dispose();
    if (_commandStackListener != null && getEditingDomain() != null)
      getEditingDomain().getCommandStack().removeCommandStackListener(_commandStackListener);
    if (_viewer != null && _selectionBridge != null)
      _viewer.removeSelectionChangedListener(_selectionBridge);
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
        _viewer = null;
        _selectionBridge = null;
        _comparisonResource = null;
      }
    };
    Display display = Display.getDefault();
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
   * Create and return the diff node for the given comparison
   * @param comparison_p a non-null comparison
   * @return a non-null diff node
   */
  protected EMFDiffNode initializeDiffNode(EComparison comparison_p) {
    ResourceSet resourceSet = getResourceSet();
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
    result.updateDifferenceNumbers();
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
    Object scopeContext = (getEditingDomain() != null)? getEditingDomain(): getResourceSet();
    boolean threeWay = _comparisonMethod.isThreeWay();
    Role leftRole = EMFDiffMergeUIPlugin.getDefault().getDefaultLeftRole();
    String mainTaskName = Messages.EMFDiffMergeEditorInput_Loading;
    SubMonitor loadingMonitor = SubMonitor.convert(
        monitor_p, mainTaskName, threeWay ? 4 : 3);
    loadingMonitor.worked(1);
    // Loading left
    loadingMonitor.subTask(Messages.EMFDiffMergeEditorInput_LoadingLeft);
    _leftScope = _comparisonMethod.getModelScopeDefinition(leftRole).createScope(scopeContext);
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
    _rightScope = _comparisonMethod.getModelScopeDefinition(leftRole.opposite()).createScope(scopeContext);
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
      _ancestorScope = _comparisonMethod.getModelScopeDefinition(Role.ANCESTOR).createScope(scopeContext);
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
      EComparison comparison = new EComparisonImpl(_leftScope, _rightScope, _ancestorScope);
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
      final StringBuilder builder = new StringBuilder();
      builder.append(Messages.EMFDiffMergeEditorInput_DuplicateIDs);
      for (Role role : Role.values()) {
        Collection<Object> duplicates = comparison_p.getDuplicateMatchIDs(role);
        if (!duplicates.isEmpty()) {
          builder.append('\n');
          String scopeName = (role == Role.ANCESTOR)?
              Messages.EMFDiffMergeEditorInput_AncestorScope: (role == Role.REFERENCE)?
                  Messages.EMFDiffMergeEditorInput_ReferenceScope: Messages.EMFDiffMergeEditorInput_TargetScope;
          builder.append(scopeName);
          builder.append('\n');
          for (Object duplicate: duplicates) {
            builder.append(duplicate);
            builder.append('\n');
          }
        }
      }
      shell.getDisplay().syncExec(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          MessageDialog.openWarning(shell, EMFDiffMergeUIPlugin.LABEL, builder.toString());
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
        EditingDomain domain = getEditingDomain();
        MiscUtil.executeAndForget(domain, new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            _comparisonResource.getContents().add(getCompareResult().getUIComparison());
          }
        });
        if (domain != null)
          domain.getCommandStack().flush();
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
  
  
  /**
   * A wrapper for model scopes that implements ITypedElement for ICompareInputs.
   */
  public static class ScopeTypedElementWrapper implements ITypedElement {
    /** The non-null scope being wrapped */
    private final IModelScope _scope;
    /**
     * Constructor
     * @param scope_p a non-null model scope
     */
    public ScopeTypedElementWrapper(IEditableModelScope scope_p) {
      _scope = scope_p;
    }
    /**
     * @see org.eclipse.compare.ITypedElement#getImage()
     */
    public Image getImage() {
      Image result = null;
      if (!_scope.getContents().isEmpty()) {
        EObject root = _scope.getContents().get(0);
        if (root.eResource() != null)
          result = DiffMergeLabelProvider.getInstance().getImage(root.eResource());
      }
      return result;
    }
    /**
     * @see org.eclipse.compare.ITypedElement#getName()
     */
    public String getName() {
      String result = null;
      if (!_scope.getContents().isEmpty()) {
        EObject root = _scope.getContents().get(0);
        if (root.eResource() != null)
          result = DiffMergeLabelProvider.getInstance().getText(root.eResource());
      }
      return result;
    }
    /**
     * @see org.eclipse.compare.ITypedElement#getType()
     */
    public String getType() {
      return ITypedElement.UNKNOWN_TYPE;
    }
  }
  
  
  /**
   * A selection provider and listener which can be used as a bridge between the comparison viewer
   * and the selection service of the workbench site. Its usefulness comes from the fact that it
   * can be instantiated earlier than the comparison viewer, so it can be registered as a selection
   * provider for the workbench site when the compare editor is activated. Listeners from other
   * workbench parts that react to part activation, such as the PropertySheet, are thus able to
   * register this selection provider.
   */
  protected static class SelectionBridge implements ISelectionChangedListener, ISelectionProvider {
    /** The current, potentially null selection */
    private  ISelection _selection;
    /** The non-null, potentially empty set of listeners */
    private final Set<ISelectionChangedListener> _selectionListeners;
    
    /**
     * Constructor
     */
    public SelectionBridge() {
      _selection = StructuredSelection.EMPTY;
      _selectionListeners = new HashSet<ISelectionChangedListener>();
    }
    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
      _selectionListeners.add(listener_p);
    }
    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    public ISelection getSelection() {
      return _selection;
    }
    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
     */
    public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
      _selectionListeners.remove(listener_p);
    }
    /**
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(ISelection selection_p) {
      _selection = selection_p;
      SelectionChangedEvent event = new SelectionChangedEvent(this, _selection);
      for (ISelectionChangedListener listener : _selectionListeners) {
        listener.selectionChanged(event);
      }
    }
    /**
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event_p) {
      setSelection(event_p.getSelection());
    }
  }
}
