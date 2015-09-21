/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.AbstractCommand.NonDirtying;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.IThreadListener;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;


/**
 * Utility class for miscellaneous technical concerns.
 * @author Olivier Constant
 */
public final class MiscUtil {
  
  /**
   * A command that may execute in a transactional context but does not support undo/redo.
   * It essentially makes sense together with a consistent usage of CommandStack#flush().
   */
  protected static class ForgettingCommand extends AbstractCommand implements NonDirtying {
    /** The runnable to execute */
    private final Runnable _runnable;
    /**
     * Constructor
     * @param runnable_p a non-null runnable
     */
    protected ForgettingCommand(Runnable runnable_p) {
      super();
      _runnable = runnable_p;
    }
    /**
     * @see org.eclipse.emf.common.command.AbstractCommand#canUndo()
     */
    @Override
    public boolean canUndo() {
      return false;
    }
    /**
     * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
     */
    @Override
    protected boolean prepare() {
      return true;
    }
    /**
     * @see org.eclipse.emf.common.command.Command#execute()
     */
    public void execute() {
      _runnable.run();
    }
    /**
     * @see org.eclipse.emf.common.command.Command#redo()
     */
    public void redo() {
      // Nothing
    }
  }
  
  
  /**
   * An IRunnableWithProgress to be used by an IProgressService, that is capable to modify
   * models in a given editing domain regardless of the nature of the editing domain (transactional/
   * non-transactional/null) or the execution context (within or outside a transaction).
   * The principle is to wrap the behavior in a privileged runnable while executing in the calling
   * thread, if relevant, then provide a progress monitor at the time of the execution of the
   * behavior by the thread provided by the IProgressService.
   */
  protected static class RunnableWithProgressOnModel implements IRunnableWithProgress, IThreadListener {
    /** The optional editing domain in which the behavior operates */
    protected final EditingDomain _domain;
    /** Whether changes done by the behavior must be recorded if possible */
    protected final boolean _recordChanges;
    /** An non-null runnable that wraps the delegate behavior for the sake of transactional concerns */
    protected Runnable _wrappingRunnable;
    /** An initially null monitor for the execution of the delegate behavior */
    protected IProgressMonitor _monitor;
    /**
     * Constructor
     * @param behavior_p a non-null behavior that ignores transactional aspects
     * @param label_p an optional user-friendly name for the behavior
     * @param domain_p an optional editing domain in which the behavior operates
     * @param recordChanges_p whether changes done by the behavior must be recorded if possible
     */
    public RunnableWithProgressOnModel(final IRunnableWithProgress behavior_p,
        final String label_p, EditingDomain domain_p, boolean recordChanges_p) {
      _domain = domain_p;
      _recordChanges = recordChanges_p;
      _wrappingRunnable = new Runnable() {
        /**
         * To execute the behavior in a transaction if necessary
         * @see java.lang.Runnable#run()
         */
        public void run() {
          // Start a transaction if necessary
          MiscUtil.execute(_domain, label_p, new Runnable() {
            /**
             * The adaptation from IRunnableWithProgress to Runnable
             * @see java.lang.Runnable#run()
             */
            public void run() {
              try {
                behavior_p.run(_monitor); // Instance variable must be set at that time
              } catch (InvocationTargetException e) {
                throw new RuntimeException(e); // No choice
              } catch (InterruptedException e) {
                throw new RuntimeException(e); // No choice
              }
            }
          }, _recordChanges); 
        }
      };
      _monitor = null;
    }
    /**
     * @see org.eclipse.jface.operation.IThreadListener#threadChange(java.lang.Thread)
     */
    public void threadChange(Thread thread_p) {
      // We are still in the calling thread
      if (MiscUtil.isRunningInActiveTransaction(_domain)) {
        // Create a privileged runnable that can be executed by the thread provided by
        // the IProgressService
        TransactionalEditingDomain ted = (TransactionalEditingDomain)_domain;
        _wrappingRunnable = ted.createPrivilegedRunnable(_wrappingRunnable);
      }
    }
    /**
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor_p) throws InvocationTargetException,
        InterruptedException {
      _monitor = monitor_p; // Becomes accessible by the wrapping runnable
      _wrappingRunnable.run();
    }
  }
  
  
  /**
   * Return the concatenation of the String representation of the given objects
   */
  public static String buildString(Object... objects_p) {
    StringBuilder builder = new StringBuilder();
    for (Object object : objects_p) {
      if (null != object)
        builder.append(object);
    }
    return builder.toString();
  }
  
  /**
   * Execute the given behavior (Runnable) that affects models in the given editing domain
   * and ignores transactional aspects
   * @param domain_p an optional editing domain
   * @param label_p an optional user-friendly name for the behavior
   * @param runnable_p a non-null runnable
   * @param recordChanges_p whether changes must be recorded if possible
   */
  public static void execute(EditingDomain domain_p, String label_p,
      final Runnable runnable_p, boolean recordChanges_p) {
    if (recordChanges_p)
      executeOnDomain(domain_p, label_p, runnable_p);
    else
      executeAndForget(domain_p, runnable_p);
  }
  
  /**
   * Execute the given runnable that affects models in the given editing domain,
   * not supporting undo/redo and forgetting all changes. Must be used with caution,
   * in contexts where it makes sense, because no rollback is possible.
   * If the editing domain is missing, then the runnable is executed as-is.
   * @param domain_p an optional editing domain
   * @param runnable_p a non-null runnable
   */
  public static void executeAndForget(EditingDomain domain_p, Runnable runnable_p) {
    if (domain_p != null && !isRunningInActiveTransaction(domain_p)) {
      AbstractCommand cmd = new ForgettingCommand(runnable_p);
      CommandStack cStack = domain_p.getCommandStack();
      if (cStack instanceof TransactionalCommandStack) {
        try {
          ((TransactionalCommandStack)cStack).execute(cmd,
              Collections.singletonMap(Transaction.OPTION_NO_UNDO, Boolean.TRUE));
        } catch (Exception e) {
          // No rollback is possible
        }
      } else {
        cStack.execute(cmd);
      }
    } else {
      runnable_p.run();
    }
  }
  
  /**
   * Execute the given runnable that affects models in the given editing domain
   * and ignores transactional aspects
   * @param domain_p a non-null editing domain
   * @param label_p an optional user-friendly label to appear in the undo/redo menu items
   * @param runnable_p a non-null runnable
   */
  public static void executeOnDomain(EditingDomain domain_p, String label_p,
      final Runnable runnable_p) {
    if (isRunningInActiveTransaction(domain_p)) {
      // Already in the active transaction
      runnable_p.run();
    } else {
      final String commandLabel = label_p != null? label_p: Messages.MiscUtil_DefaultCommandName;
      ChangeCommand cmd = new ChangeCommand(domain_p.getResourceSet()) {
        {
          label = commandLabel;
        }
        /**
         * @see org.eclipse.emf.edit.command.ChangeCommand#doExecute()
         */
        @Override
        protected void doExecute() {
          runnable_p.run();
        }
      };
      domain_p.getCommandStack().execute(cmd);
    }
  }
  
  /**
   * Execute the given behavior (Runnable), that affects models in the given
   * editing domain and ignores transactional aspects, with a busy cursor.
   * @param domain_p an optional editing domain
   * @param label_p an optional user-friendly name for the behavior
   * @param runnable_p a non-null runnable
   * @param recordChanges_p whether changes must be recorded if possible
   * @param display_p a non-null display for the busy cursor
   */
  public static void executeWithBusyCursor(final EditingDomain domain_p,
      final String label_p, final Runnable runnable_p, final boolean recordChanges_p,
      Display display_p) {
    Runnable actualOperation = new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        MiscUtil.execute(domain_p, label_p, runnable_p, recordChanges_p);
      }
    };
    if (MiscUtil.isRunningInActiveTransaction(domain_p))
      actualOperation = ((TransactionalEditingDomain)domain_p).createPrivilegedRunnable(actualOperation);
    BusyIndicator.showWhile(display_p, actualOperation);
  }
  
  /**
   * Execute the given behavior (IRunnableWithProgress), that affects models in the given
   * editing domain and ignores transactional aspects, in a progress dialog.
   * @param domain_p an optional editing domain
   * @param label_p an optional user-friendly name for the behavior
   * @param behavior_p a non-null runnable with progress
   * @param recordChanges_p whether changes must be recorded if possible
   */
  public static void executeWithProgress(EditingDomain domain_p, String label_p,
      IRunnableWithProgress behavior_p, boolean recordChanges_p)
          throws InvocationTargetException, InterruptedException {
    IRunnableWithProgress operation = new RunnableWithProgressOnModel(
        behavior_p, label_p, domain_p, recordChanges_p);
    IProgressService progress = PlatformUI.getWorkbench().getProgressService();
    progress.busyCursorWhile(operation);
  }
  
  /**
   * Return the file that holds the given resource, if any.
   * If the file is not local to the platform and allowExternal_p is true,
   * then the file is imported (not copied) in the workspace via an ad-hoc
   * project.
   * @param resource_p a non-null resource
   * @param allowExternal_p whether the file is allowed to be external to the workspace
   * @return a potentially null Eclipse file
   */
  public static IFile getFileFor(Resource resource_p, boolean allowExternal_p) {
    IFile result = null;
    URI uri = resource_p.getURI();
    if (uri != null) {
      IWorkspace wk = ResourcesPlugin.getWorkspace();
      if (wk != null && wk.getRoot() != null) {
        if (uri.isPlatformResource()) {
          // Resource in workspace
          String platformResourcePath = uri.toPlatformString(true);
          result = wk.getRoot().getFile(new Path(platformResourcePath));
        } else if (allowExternal_p) {
          // Resource from external file
          IProject project = wk.getRoot().getProject("ExternalFiles"); //$NON-NLS-1$
          try {
            if (!project.exists())
              project.create(null);
            if (!project.isOpen())
              project.open(null);
            IPath path = new Path(uri.toFileString());
            result = project.getFile(path.lastSegment());
            result.createLink(path, IResource.NONE, null);
          } catch (CoreException e) {
            // Just proceed
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the current code executes in the context of the currently
   * active transaction on the given editing domain
   * @param domain_p a potentially null object
   */
  public static boolean isRunningInActiveTransaction(EditingDomain domain_p) {
    boolean result = false;
    if (domain_p instanceof InternalTransactionalEditingDomain) {
      Transaction activeTransaction =
          ((InternalTransactionalEditingDomain)domain_p).getActiveTransaction();
      if (activeTransaction != null)
        result = Thread.currentThread() == activeTransaction.getOwner();
    }
    return result;
  }
  
}
