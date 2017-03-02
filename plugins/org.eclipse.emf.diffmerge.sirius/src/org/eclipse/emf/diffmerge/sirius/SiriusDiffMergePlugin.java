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
package org.eclipse.emf.diffmerge.sirius;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * The activator for this plug-in.
 */
public class SiriusDiffMergePlugin extends Plugin {
  
	/** The shared instance */
	private static SiriusDiffMergePlugin __plugin;
  
  
	/**
	 * Constructor
	 */
	public SiriusDiffMergePlugin() {
	  // Nothing needed
	}
  
  /**
   * Return the shared instance
   * @return a non-null instance of this class
   */
  public static SiriusDiffMergePlugin getDefault() {
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
  
}
