/*******************************************************************************
 * Copyright (c) 2015-2018 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.core;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 */
public class EMFDiffMergeCoreConnectorPlugin extends AbstractUIPlugin {
  
  /** The key for retrieving the viewer label that is contributed by this plug-in,
   *  as defined in __plugin.properties */
  private static final String VIEWER_LABEL_KEY = "%viewerLabel"; //$NON-NLS-1$
  
  /** The shared instance */
  private static EMFDiffMergeCoreConnectorPlugin __plugin;
	
  
	/**
	 * Constructor
	 */
	public EMFDiffMergeCoreConnectorPlugin() {
    // Nothing needed
	}
	
	/**
   * Return the shared instance of this activator
   * @return a non-null object
	 */
	public static EMFDiffMergeCoreConnectorPlugin getDefault() {
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
   * Return the viewer label that is contributed by the plug-in
   * @return a non-null string
   */
  public String getViewerLabel() {
    return Platform.getResourceString(getBundle(), VIEWER_LABEL_KEY);
  }
  
  /**
   * Log the given exception as an error
   * @param e_p a non-null exception
   */
  public void logError(Exception e_p) {
    ILog logger = getLog();
    logger.log(new Status(IStatus.ERROR, getBundle().getSymbolicName(), e_p.getMessage()));
  }
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __plugin = this;
  }
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __plugin = null;
    super.stop(context_p);
  }
  
}
