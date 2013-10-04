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
package org.eclipse.emf.diffmerge.ui.util;

import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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


/**
 * Utility class for miscellaneous concerns.
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
   * Execute the given runnable that affects models in the given editing domain
   * @param domain_p a non-null editing domain
   * @param label_p an optional label to appear in the undo/redo menu items
   * @param runnable_p a non-null runnable
   */
  public static void executeOnDomain(EditingDomain domain_p, String label_p,
      final Runnable runnable_p) {
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
  
  /**
   * Execute the given runnable that affects models in the given editing domain,
   * not supporting undo/redo and forgetting all changes. Must be used with caution,
   * in contexts where it makes sense, because no rollback is possible.
   * If the editing domain is missing, then the runnable is executed as-is.
   * @param domain_p an optional editing domain
   * @param runnable_p a non-null runnable
   */
  public static void executeAndForget(EditingDomain domain_p, Runnable runnable_p) {
    if (domain_p != null) {
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
    IWorkspace wk = ResourcesPlugin.getWorkspace();
    if (wk != null && wk.getRoot() != null) {
      if (uri != null && uri.isPlatformResource()) {
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
    return result;
  }
  
}
