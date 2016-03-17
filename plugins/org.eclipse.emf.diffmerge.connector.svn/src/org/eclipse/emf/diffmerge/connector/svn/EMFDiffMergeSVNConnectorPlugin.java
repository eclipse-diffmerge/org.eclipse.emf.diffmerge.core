/*******************************************************************************
 * Copyright (c) 2015 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.svn;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 */
public class EMFDiffMergeSVNConnectorPlugin extends AbstractUIPlugin {
  
  /** The shared instance */
	private static EMFDiffMergeSVNConnectorPlugin __plugin;
	
	
	/**
	 * Constructor
	 */
	public EMFDiffMergeSVNConnectorPlugin() {
    // Nothing needed
	}
	
  /**
   * Return the shared instance of this activator
   * @return a non-null object
   */
  public static EMFDiffMergeSVNConnectorPlugin getDefault() {
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
