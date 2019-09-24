/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
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

  /** A composed adapter factory based on the EMF Edit registry (initially null) */
  private ComposedAdapterFactory _adapterFactory;
	
	
	/**
	 * Constructor
	 */
	public EMFDiffMergePlugin() {
	  _verbose = false;
	  _adapterFactory = null;
	}
	
  /**
   * Create and return a multi-status with OK severity
   * @return a non-null status
   */
  public MultiStatus createMultiStatus() {
    return new MultiStatus(getPluginId(), IStatus.OK, null, null);
  }
  
	/**
	 * Create and return an error status with the given message
	 * @param message_p a non-null string
	 * @return a non-null status
	 */
	public IStatus createErrorStatus(String message_p) {
	  return new Status(IStatus.ERROR, getPluginId(), message_p);
	}
	
  /**
   * Create and return an error status with the given throwable
   * @param throwable_p a non-null throwable
   * @return a non-null status
   */
  public IStatus createErrorStatus(Throwable throwable_p) {
    return new Status(IStatus.ERROR, getPluginId(),
        throwable_p.getLocalizedMessage(), throwable_p);
  }
  
  /**
   * Return an adapter factory that is based on the EMF Edit registry
   * @return a non-null object
   */
  public AdapterFactory getAdapterFactory() {
    if (_adapterFactory == null)
      _adapterFactory = new ComposedAdapterFactory(
          ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    return _adapterFactory;
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
   * @return a non-null string
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * Log the given status if this plug-in is in verbose mode
   * @param status_p a non-null status
   */
  public void log(IStatus status_p) {
    if (_verbose) {
      getLog().log(status_p);
    }
  }
  
  /**
   * Set whether calls to operation log(...) have an actual effect
   * @param verbose_p whether they have an effect
   */
  public void setVerbose(boolean verbose_p) {
    _verbose = verbose_p;
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
		if(_adapterFactory != null){
		  _adapterFactory.dispose();
		  _adapterFactory = null;
		}
		super.stop(context_p);
	}
	
}
