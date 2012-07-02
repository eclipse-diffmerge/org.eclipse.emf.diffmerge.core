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
package org.eclipse.emf.diffmerge.impl.helpers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.diffmerge.util.IExpensiveOperation;


/**
 * A simple, partial implementation of IExpensiveOperation.
 * @author Olivier Constant
 */
public abstract class AbstractExpensiveOperation implements IExpensiveOperation {

  /** Common String constants for building messages */
  protected static final String SPACE = " "; //$NON-NLS-1$
  protected static final String COLON = ":"; //$NON-NLS-1$
  
  /** The non-null progress monitor for this operation */
  private SubMonitor _monitor;
  
  /**
   * Constructor
   */
  protected AbstractExpensiveOperation() {
    _monitor = SubMonitor.convert(new NullProgressMonitor());
  }
  
  /**
   * Check that progress is still possible, i.e., the user has not canceled the operation.
   * If he has, stop the operation by throwing an OperationCanceledException
   */
  protected void checkProgress() {
    if (_monitor.isCanceled())
      throw new OperationCanceledException();
  }
  
  /**
   * Return a non-null progress monitor for this operation
   */
  protected final SubMonitor getMonitor() {
    return _monitor;
  }
  
  /**
   * Return the total amount of work needed for the operation execution
   */
  protected abstract int getWorkAmount();
  
  /**
   * @see org.eclipse.emf.diffmerge.util.IExpensiveOperation#run(IProgressMonitor)
   */
  public final IStatus run(IProgressMonitor monitor_p) {
    if (monitor_p != null)
      _monitor = SubMonitor.convert(monitor_p, getOperationName(), getWorkAmount());
    _monitor.subTask(getOperationName());
    return run();
  }
  
}
