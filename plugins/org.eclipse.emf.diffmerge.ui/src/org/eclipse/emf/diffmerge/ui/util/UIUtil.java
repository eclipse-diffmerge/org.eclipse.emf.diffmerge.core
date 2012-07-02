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
package org.eclipse.emf.diffmerge.ui.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;


/**
 * Utility class for UI-related issues.
 * @author Olivier Constant
 */
public final class UIUtil {
  
  /**
   * Constructor
   */
	private UIUtil() {
	  //Forbid instantiation
	}
	
  /**
   * Return the concatenation of the String representation of the given objects
   */
  public static String buildString(Object... objects_p) {
    StringBuilder builder = new StringBuilder();
    for (Object object : objects_p) {
      if (null != object)
        builder.append(object);
    }
    return builder.toString();
  }
  
  /**
   * Center the given shell graphically
   */
  public static void centerShell(Shell shell_p) {
    Rectangle pbounds = shell_p.getDisplay().getBounds();
    Rectangle bounds = shell_p.getBounds();
    shell_p.setBounds(
        pbounds.x + (pbounds.width-bounds.width)/2,
        pbounds.y + (pbounds.height-bounds.height)/2,
        bounds.width,
        bounds.height);
  }
  
  /**
   * Create and return a composite within the given one with default characteristics
   * @param parent_p a non-null composite
   * @return a non-null composite
   */
  public static Composite createComposite(Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.BORDER);
    GridLayout layout = new GridLayout(1, true);
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    result.setLayout(layout);
    return result;
  }
  
  /**
   * Create a tool item in the given toolbar which displays a drop-down menu 
   * @param toolbar_p a non-null tool bar
   * @return the non-null menu
   */
  public static Menu createMenuTool(ToolBar toolbar_p) {
    ToolItem menuTool = new ToolItem(toolbar_p, SWT.PUSH);
    menuTool.setImage(EMFDiffMergeUIPlugin.getDefault().getImage(
        EMFDiffMergeUIPlugin.ImageID.VIEW_MENU));
    final Menu result = new Menu(toolbar_p.getShell());
    menuTool.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
          ToolItem item = (ToolItem) event.widget;
          Rectangle rect = item.getBounds();
          Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
          result.setLocation(pt.x, pt.y + rect.height);
          result.setVisible(true);
      }
    });
    menuTool.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e) {
        result.dispose();
      }
    });
    return result;
  }
  
  /**
   * Create and return a tool bar within the given composite with default characteristics
   * @param parent_p a non-null composite
   * @return a non-null tool bar
   */
  public static ToolBar createToolBar(Composite parent_p) {
    ToolBar result = new ToolBar(parent_p, SWT.HORIZONTAL | SWT.RIGHT);
    GridData data = new GridData(SWT.RIGHT, SWT.TOP, false, false);
    result.setData(data);
    return result;
  }
  
  /**
   * Return the bold variant of the given font
   * @param font_p a non-null font
   * @return a non-null font
   */
  public static Font getBold(Font font_p) {
    FontData data = font_p.getFontData()[0];
    Font result = JFaceResources.getFontRegistry().getBold(data.getName());
    return result;
  }
  
  /**
   * Return the color corresponding to the given code
   * @param code_p one of the SWT.COLOR_... constants
   * @return a non-null color (black by default)
   */
  public static final Color getColor(int code_p) {
    return Display.getDefault().getSystemColor(code_p);
  }
  
  /**
   * Return an image for the given element solely based on its editing domain, if any
   * @param element_p a non-null element
   * @return a potentially null string
   */
  public static Image getImage(Object element_p) {
    Image result = null;
    EditingDomain rawEditingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(element_p);
    if (rawEditingDomain == null)
      rawEditingDomain = TransactionUtil.getEditingDomain(element_p);
    if (rawEditingDomain instanceof AdapterFactoryEditingDomain) {
      AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain)rawEditingDomain;
      IItemLabelProvider provider = (IItemLabelProvider)editingDomain.getAdapterFactory().adapt(
          element_p, IItemLabelProvider.class);
      if (provider != null) {
        Object rawImage = provider.getImage(element_p);
        if (rawImage != null)
          result = ExtendedImageRegistry.getInstance().getImage(rawImage);
      }
    }
    return result;
  }
  
  /**
   * Return the italic variant of the given font
   * @param font_p a non-null font
   * @return a non-null font
   */
  public static Font getItalic(Font font_p) {
    FontData data = font_p.getFontData()[0];
    Font result = JFaceResources.getFontRegistry().getItalic(data.getName());
    return result;
  }
  
	/**
	 * Return a label for the given element solely based on its editing domain, if any
	 * @param element_p a potentially null object
	 * @return a potentially null string
	 */
  public static String getText(Object element_p) {
    String result = null;
    EditingDomain rawEditingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(element_p);
    if (rawEditingDomain == null)
      rawEditingDomain = TransactionUtil.getEditingDomain(element_p);
    if (rawEditingDomain instanceof AdapterFactoryEditingDomain) {
      AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain)rawEditingDomain;
      IItemLabelProvider provider = (IItemLabelProvider)editingDomain.getAdapterFactory().adapt(
          element_p, IItemLabelProvider.class);
      if (provider != null)
        result = provider.getText(element_p);
    }
    return result;
	}
	
	/**
	 * Return a UI variant of the URI representation
	 * @param uri_p a potentially null string
	 * @return a string which is null iff uri_p is null
	 */
	public static String simplifyURI(String uri_p) {
	  String result = uri_p;
	  if (result != null) {
	    result = result.replaceAll("platform:/resource/", ""); //$NON-NLS-1$ //$NON-NLS-2$
	    int pos = result.lastIndexOf('#');
	    if (pos >= 0)
	      result = result.substring(0, pos);
	    result = result.replaceAll("%20", " "); //$NON-NLS-1$ //$NON-NLS-2$
	  }
	  return result;
	}
	
  /**
   * Convert the given list of elements to a TreePath
   * @param path_p a non-null, potentially empty list
   * @return a non-null tree path
   */
  public static TreePath toTreePath(List<?> path_p) {
    return new TreePath(path_p.toArray());
  }
  
  /**
   * Convert the given list of [paths as lists] to an array of TreePaths
   * @param paths_p a non-null, potentially empty list
   * @return a non-null array of tree paths
   */
  public static TreePath[] toTreePaths(List<? extends List<?>> paths_p) {
    List<TreePath> result = new ArrayList<TreePath>(paths_p.size());
    for (List<?> path : paths_p)
      result.add(toTreePath(path));
    return result.toArray(new TreePath[result.size()]);
  }
  
  
  /**
   * A comparator based on labels of objects
   */
  public static class LabelBasedComparator implements Comparator<Object> {
    
    /** The label provider whose labels define the ordering */
    private final ILabelProvider _labelProvider;
    
    /**
     * Constructor
     * @param labelProvider_p the non-null label provider whose labels define the ordering
     */
    public LabelBasedComparator(ILabelProvider labelProvider_p) {
      _labelProvider = labelProvider_p;
    }
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1_p, Object o2_p) {
      String label1 = _labelProvider.getText(o1_p);
      if (label1 == null) label1 = o1_p.toString();
      String label2 = _labelProvider.getText(o2_p);
      if (label2 == null) label2 = o2_p.toString();
      return label1.compareTo(label2);
    }
  }
  
}
