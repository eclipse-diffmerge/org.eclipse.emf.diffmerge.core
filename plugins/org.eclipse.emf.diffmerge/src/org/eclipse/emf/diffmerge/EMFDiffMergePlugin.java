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
package org.eclipse.emf.diffmerge;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 * @author Olivier Constant
 */
public class EMFDiffMergePlugin extends Plugin {
  
	/** The shared instance */
	private static EMFDiffMergePlugin __plugin;
	
	/** Whether this plug-in is verbose */
	private boolean _verbose;
	
	
	/**
	 * Constructor
	 */
	public EMFDiffMergePlugin() {
	  _verbose = false;
	}
	
  /**
   * Return the shared instance of the activator
   * @return a non-null instance
   */
  public static EMFDiffMergePlugin getDefault() {
    return __plugin;
  }
  
  /**
   * Return the ID of this plug-in according to MANIFEST.MF
   * @return a non-null string
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * Log the given message if this plug-in is in verbose mode
   * @param severity_p a severity as defined in IStatus
   * @param message_p a non-null warning message
   */
  public void log(int severity_p, String message_p) {
    if (_verbose)
      getLog().log(new Status(severity_p, getPluginId(), message_p));
  }
  
  /**
   * Set whether this plug-in must be verbose
   * @param verbose_p whether it must be verbose
   */
  public void setVerbose(boolean verbose_p) {
    _verbose = verbose_p;
  }
  
  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
	public void start(BundleContext context_p) throws Exception {
		super.start(context_p);
		__plugin = this;
	}
  
	/**
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context_p) throws Exception {
		__plugin = null;
		super.stop(context_p);
	}
	
  /**
   * Log the given warning message if this plug-in is in verbose mode
   * @param message_p a non-null warning message
   */
  public void warn(String message_p) {
    log(IStatus.WARNING, message_p);
  }
  
}
