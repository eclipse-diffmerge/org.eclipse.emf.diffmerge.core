/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class EMFDiffMergeCoreConnectorPlugin extends AbstractUIPlugin {
  
  /** The key for retrieving the viewer label that is contributed by this plug-in,
   *  as defined in plugin.properties */
  private static final String VIEWER_LABEL_KEY = "%viewerLabel"; //$NON-NLS-1$
  
  /** The shared instance */
  private static EMFDiffMergeCoreConnectorPlugin plugin;
	
  
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
		return plugin;
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
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }
  
}
