/**
 * <copyright>
 * 
 * Copyright (c) 2006-2017  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.sirius;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator for this plug-in.
 */
public class SiriusDiffMergeUIPlugin extends AbstractUIPlugin {
  
	/** The shared instance */
	private static SiriusDiffMergeUIPlugin __plugin;
  
  
	/**
	 * Constructor
	 */
	public SiriusDiffMergeUIPlugin() {
	  // Nothing needed
	}
  
  /**
   * Return the shared instance
   * @return a non-null instance of this class
   */
  public static SiriusDiffMergeUIPlugin getDefault() {
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
	public void start(BundleContext context) throws Exception {
		super.start(context);
		__plugin = this;
	}
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
	public void stop(BundleContext context) throws Exception {
		__plugin = null;
		super.stop(context);
	}
  
}
