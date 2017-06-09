/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;


/**
 * An operation which may be time-consuming.
 * @author Olivier Constant
 */
public interface IExpensiveOperation {
  
  /**
   * Return the name of the operation
   */
  String getOperationName();

  /**
   * Execute this operation
   * @return the status of the execution of this operation
   */
  IStatus run();
  
  /**
   * Execute this operation
   * @param monitor_p the progress monitor to use for reporting progress to the user.
       It is the caller's responsibility to call done() on the given monitor.
       Accepts null, indicating that no progress should be reported and that the operation
       cannot be canceled.
   * @return the status of the execution of this operation
   */
  IStatus run(IProgressMonitor monitor_p);
  
}
