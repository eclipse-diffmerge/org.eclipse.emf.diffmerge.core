/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.CompareUI;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.diffmerge.ui.log.DiffMergeLogger;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator for this plug-in.
 * @author Olivier Constant
 */
public class EMFDiffMergeUIPlugin extends AbstractUIPlugin {
  
  /** The default file extension for UI diff models */
  public static final String UI_DIFF_DATA_FILE_EXTENSION = "diffuidata"; //$NON-NLS-1$
  
  /** Identifiers for UI images */
  public static enum ImageID {
    CHECKIN_ACTION, CHECKOUT_ACTION, COLLAPSEALL, CONFLICT_STAT, CONTAINER, DELETE, DONE, DOWN,
    EMPTY, EXPANDALL, INC_STAT, INC_ADD_STAT, INC_REM_STAT, LEFT, LOCK, MODIFIED_STAT,
    NEXT_CHANGE_NAV, NEXT_DIFF_NAV, OUT_STAT, OUT_ADD_STAT, OUT_REM_STAT, PLUS, PREV_CHANGE_NAV,
    PREV_DIFF_NAV, REDO, RIGHT, SHOW, SORT, SWAP, SYNCED, UNDO, UP, VIEW_MENU }
  
  /** Identifiers for the side to which a difference presence is relative */
  public static enum DifferenceColorKind {
    LEFT, RIGHT, BOTH, NONE, CONFLICT, DEFAULT
  }
  
  /** The local path to icons */
  private static final String ICON_PATH = "icons/full/"; //$NON-NLS-1$
  
  /** A label for dialogs */
  public static final String LABEL = Messages.EMFDiffMergeUIPlugin_Label;
  
	/** The shared instance */
	private static EMFDiffMergeUIPlugin plugin;
	
	/** The manager for comparison contexts */
	private final ComparisonContextManager _comparisonContextManager;
	
	/** The logger for diff/merge events */
	private final DiffMergeLogger _diffMergeLogger;
	
	/** A symbolic representation of the virtual ownership feature */
  private final EReference _ownershipFeature;
  
  /** The "very dark gray" non-system color (initially null) */
  private Color _veryDarkGray;
  
  /** A label provider based on the registered .edit plugins (initially null) */
  private AdapterFactoryLabelProvider _composedAdapterFactoryLabelProvider;
  
	
	/**
	 * Constructor
	 */
	public EMFDiffMergeUIPlugin() {
	  _diffMergeLogger = new DiffMergeLogger();
	  _comparisonContextManager = new ComparisonContextManager();
	  _ownershipFeature = EcoreFactory.eINSTANCE.createEReference();
	  _ownershipFeature.setName("container"); //$NON-NLS-1$
	  _ownershipFeature.setEType(EcorePackage.eINSTANCE.getEObject());
    _ownershipFeature.setLowerBound(0);
	  _ownershipFeature.setUpperBound(1);
	  _veryDarkGray = null;
	  _composedAdapterFactoryLabelProvider = null;
	}
	
	/**
	 * Return a label provider based on the registered .edit plugins
	 * @return a non-null object
	 */
	public AdapterFactoryLabelProvider getComposedAdapterFactoryLabelProvider() {
	  if (_composedAdapterFactoryLabelProvider == null)
	    _composedAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
	        new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	  return _composedAdapterFactoryLabelProvider;
	}
	
	/**
	 * Return the comparison context manager
	 * @return a non-null ComparisonContextManager
	 */
	public ComparisonContextManager getContextManager() {
	  return _comparisonContextManager;
	}
	
  /**
   * Return the shared instance of this activator
   * @return a non-null object
   */
  public static EMFDiffMergeUIPlugin getDefault() {
    return plugin;
  }
  
  /**
   * Return the color that corresponds to the given color kind
   * @param colorKind_p a non-null color kind
   * @return a non-null color
   */
  public Color getDifferenceColor(DifferenceColorKind colorKind_p) {
    int colorCode;
    switch (colorKind_p) {
      case LEFT:
        colorCode = SWT.COLOR_DARK_RED; break;
      case RIGHT:
        colorCode = SWT.COLOR_BLUE; break;
      case BOTH:
        colorCode = SWT.COLOR_DARK_MAGENTA; break;
      case NONE:
        colorCode = SWT.COLOR_GRAY; break;
      case CONFLICT:
        colorCode = SWT.COLOR_RED; break;
      default:
        colorCode = SWT.COLOR_BLACK; break;
    }
    return UIUtil.getColor(colorCode);
  }
  
  /**
   * Return the color kind that corresponds to the given difference kind
   * @param originKind_p a potentially null difference kind
   * @return a non-null color kind
   */
  public DifferenceColorKind getDifferenceColorKind(DifferenceKind originKind_p) {
    DifferenceColorKind result;
    if (originKind_p == null) {
      result = DifferenceColorKind.DEFAULT;
    } else {
      switch (originKind_p) {
        case NONE:
          result = DifferenceColorKind.NONE; break;
        case CONFLICT:
          result = DifferenceColorKind.CONFLICT; break;
        case MODIFIED: case FROM_BOTH:
          result = DifferenceColorKind.BOTH; break;
        case FROM_LEFT: case FROM_LEFT_ADD: case FROM_RIGHT_DEL:
          result = DifferenceColorKind.LEFT; break;
        case FROM_RIGHT: case FROM_RIGHT_ADD: case FROM_LEFT_DEL:
          result = DifferenceColorKind.RIGHT; break;
        default:
          result = DifferenceColorKind.DEFAULT; break;
      }
    }
    return result;
  }
  
  /**
   * Return the image ID that corresponds to the given difference origin kind
   * @param originKind_p a non-null difference origin kind
   * @return a potentially null image ID
   */
  public ImageID getDifferenceOverlay(DifferenceKind originKind_p) {
    ImageID result;
    switch (originKind_p) {
      case FROM_LEFT:
        result = ImageID.OUT_STAT; break;
      case FROM_LEFT_ADD:
        result = ImageID.OUT_ADD_STAT; break;
      case FROM_LEFT_DEL:
        result = ImageID.OUT_REM_STAT; break;
      case FROM_RIGHT:
        result = ImageID.INC_STAT; break;
      case FROM_RIGHT_ADD:
        result = ImageID.INC_ADD_STAT; break;
      case FROM_RIGHT_DEL:
        result = ImageID.INC_REM_STAT; break;
      case MODIFIED:
      case FROM_BOTH:
        result = ImageID.MODIFIED_STAT; break;
      case CONFLICT:
        result = ImageID.CONFLICT_STAT; break;
      default:
        result = null; break;
    }
    return result;
  }
  
  /**
   * Return the prefix that corresponds to the given difference kind
   * @param originKind_p a non-null difference origin kind
   * @return a non-null string
   */
  public String getDifferencePrefix(DifferenceKind originKind_p) {
    String result;
    switch (originKind_p) {
      case FROM_LEFT:
        result = "±› "; break; //$NON-NLS-1$
      case FROM_LEFT_ADD:
        result = "+› "; break; //$NON-NLS-1$
      case FROM_LEFT_DEL:
        result = "-› "; break; //$NON-NLS-1$
      case FROM_RIGHT:
        result = "‹± "; break; //$NON-NLS-1$
      case FROM_RIGHT_ADD:
        result = "‹+ "; break; //$NON-NLS-1$
      case FROM_RIGHT_DEL:
        result = "‹- "; break; //$NON-NLS-1$
      case CONFLICT:
        result = "! "; break; //$NON-NLS-1$
      case FROM_BOTH:
        result = "‹› "; break; //$NON-NLS-1$
      case MODIFIED:
        result = "›‹ "; break; //$NON-NLS-1$
      default:
        result = ""; break; //$NON-NLS-1$
    }
    return result;
  }
  
  /**
   * Return the logger for diff/merge events
   * @return a non-null logger
   */
  public Logger getDiffMergeLogger() {
    return _diffMergeLogger;
  }
  
  /**
   * Return the image of the given ID
   * @param id_p a non-null image ID
   * @return a (normally) non-null image
   */
  public Image getImage(ImageID id_p) {
    Image result = null;
    switch (id_p) {
      case DELETE:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_DELETE);
        break;
      case LEFT:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_BACK);
        break;
      case REDO:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_REDO);
        break;
      case RIGHT:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_FORWARD);
        break;
      case SHOW:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_OBJS_INFO_TSK);
        break;
      case UNDO:
        result = PlatformUI.getWorkbench().getSharedImages().getImage(
            ISharedImages.IMG_TOOL_UNDO);
        break;
      default:
        result = getImageRegistry().get(id_p.name());
    }
    return result;
  }
  
  /**
   * Return the image descriptor of the given ID
   * @param id_p a non-null image ID
   * @return a (normally) non-null image
   */
  public ImageDescriptor getImageDescriptor(ImageID id_p) {
    ImageDescriptor result = null;
    switch (id_p) {
      case DELETE:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_TOOL_DELETE);
        break;
      case LEFT:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_TOOL_BACK);
        break;
      case REDO:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_TOOL_REDO);
        break;
      case RIGHT:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_TOOL_FORWARD);
        break;
      case SHOW:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_OBJS_INFO_TSK);
        break;
      case UNDO:
        result = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
            ISharedImages.IMG_TOOL_UNDO);
        break;
      default:
        result = getImageRegistry().getDescriptor(id_p.name());
    }
    return result;
  }
  
  /**
   * Return a symbolic reference that represents the virtual "ownership" feature
   * @return a non-null EReference
   */
  public EReference getOwnershipFeature() {
    return _ownershipFeature;
  }
  
  /**
   * Get the plug-in ID according to MANIFEST.MF definition.
   * @return a non-null String
   */
  public String getPluginId() {
    return getBundle().getSymbolicName();
  }
  
  /**
   * Return the "very dark gray" non-system color
   * @return a non-null color
   */
  public Color getVeryDarkGray() {
    if (_veryDarkGray == null)
      _veryDarkGray = new Color(Display.getDefault(), 75, 75, 75);
    return _veryDarkGray;
  }
  
  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
   */
  @Override
  protected void initializeImageRegistry(ImageRegistry reg_p) {
    super.initializeImageRegistry(reg_p);
    reg_p.put(ImageID.UP.name(), CompareUI.DESC_CTOOL_PREV);
    reg_p.put(ImageID.DOWN.name(), CompareUI.DESC_CTOOL_NEXT);
    Set<ImageID> toRegister = new HashSet<EMFDiffMergeUIPlugin.ImageID>(
        Arrays.asList(ImageID.values()));
    toRegister.removeAll(Arrays.asList(new ImageID[] {
        ImageID.DELETE, ImageID.LEFT, ImageID.REDO, ImageID.RIGHT, ImageID.SHOW,
        ImageID.UNDO, ImageID.DOWN, ImageID.UP}));
    for (ImageID imageId : toRegister)
      registerLocalIcon(imageId, reg_p);
  }
  
  /**
   * Register and return the image descriptor obtained from the given ID of a local icon
   * @param imageID_p a non-null image ID
   * @param reg_p the non-null image registry in which to register
   * @return a potentially null image descriptor
   */
  private ImageDescriptor registerLocalIcon(ImageID imageID_p, ImageRegistry reg_p) {
    ImageDescriptor result = null;
    String path = ICON_PATH + imageID_p.name().toLowerCase() + ".gif"; //$NON-NLS-1$
    try {
      result = ImageDescriptor.createFromURL(FileLocator.toFileURL(
          getBundle().getEntry(path)));
    } catch (IOException e) {
      // Nothing needed
    }
    if (result != null)
      reg_p.put(imageID_p.name(), result);
    return result;
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
	  _diffMergeLogger.close();
	  if (_veryDarkGray != null)
	    _veryDarkGray.dispose();
	  if (_composedAdapterFactoryLabelProvider != null)
	    _composedAdapterFactoryLabelProvider.dispose();
		plugin = null;
		super.stop(context);
	}
	
}
