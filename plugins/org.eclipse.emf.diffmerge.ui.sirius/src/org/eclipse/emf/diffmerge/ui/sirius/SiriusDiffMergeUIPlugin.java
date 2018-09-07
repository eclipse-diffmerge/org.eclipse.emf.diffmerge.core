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
