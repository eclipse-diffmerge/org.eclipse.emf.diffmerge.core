/*********************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.design;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 */
public class StructuresDesignPlugin extends Plugin {
  
  /** The shared instance */
  private static StructuresDesignPlugin plugin;
  
  
  /**
   * Constructor.
   */
  public StructuresDesignPlugin() {
    // Nothing
  }
  
  /**
   * Return the shared instance of the activator.
   * @return a non-null instance
   */
  public static StructuresDesignPlugin getDefault() {
    return plugin;
  }
  
  /**
   * Return the ID of this plug-in according to MANIFEST.MF.
   * @return a non-null string
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }
  
  /**
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }
  
}
