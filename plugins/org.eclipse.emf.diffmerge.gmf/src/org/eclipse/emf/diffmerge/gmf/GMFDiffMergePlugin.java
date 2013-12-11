package org.eclipse.emf.diffmerge.gmf;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class for this plug-in.
 * @author Olivier Constant
 */
public class GMFDiffMergePlugin extends Plugin {

	/** The shared instance */
	private static GMFDiffMergePlugin __plugin;
	
	
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
