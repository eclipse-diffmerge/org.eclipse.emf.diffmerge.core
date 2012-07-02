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
package org.eclipse.emf.diffmerge;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
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
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * Log the given status message in the platform
   * @param status_p a non-null status message
   */
  private void log(IStatus status_p) {
    Platform.getLog(getBundle()).log(status_p);
  }
  
  /**
   * Set whether this plug-in must be verbose
   */
  public void setVerbose(boolean verbose_p) {
    _verbose = verbose_p;
  }
  
  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		__plugin = this;
	}
  
	/**
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		__plugin = null;
		super.stop(context);
	}
	
  /**
   * Log the given warning message
   * @param message_p a non-null warning message
   */
  public void warn(String message_p) {
    if (_verbose)
      log(new Status(IStatus.WARNING, getPluginId(), message_p));
  }
  
}
