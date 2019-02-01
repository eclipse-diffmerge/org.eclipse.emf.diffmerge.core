/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.helpers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation;


/**
 * A simple, partial implementation of IExpensiveOperation.
 * @author Olivier Constant
 */
public abstract class AbstractExpensiveOperation implements IExpensiveOperation {

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
   * @see org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation#run(org.eclipse.core.runtime.IProgressMonitor)
   */
  public final IStatus run(IProgressMonitor monitor_p) {
    if (monitor_p != null)
      _monitor = SubMonitor.convert(monitor_p, getOperationName(), getWorkAmount());
    _monitor.subTask(getOperationName());
    return run();
  }
  
}
