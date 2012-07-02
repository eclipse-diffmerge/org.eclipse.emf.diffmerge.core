package org.eclipse.emf.diffmerge.gmf;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 * @author Olivier Constant
 */
public class GMFDiffMergePlugin extends Plugin {

	/** The shared instance */
	private static GMFDiffMergePlugin plugin;
	
	
	/**
	 * Constructor
	 */
	public GMFDiffMergePlugin() {
	  // Nothing needed
	}
	
	/**
	 * Return the shared instance of the activator
	 * @return a non-null instance
	 */
	public static GMFDiffMergePlugin getDefault() {
	  return plugin;
	}
	
  /**
   * Return the ID of this plug-in according to MANIFEST.MF
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
