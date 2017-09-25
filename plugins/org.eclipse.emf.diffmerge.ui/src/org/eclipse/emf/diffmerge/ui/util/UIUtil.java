/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
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
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;


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
   * Return an image for the given element solely based on EMF mechanisms
   * @param element_p a non-null object
   * @return a potentially null image
   */
  public static Image getEMFImage(Object element_p) {
    Image result =
        EMFDiffMergeUIPlugin.getDefault().getAdapterFactoryLabelProvider().getImage(element_p);
    if (result == null) {
      // Try editing domain
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
    }
    return result;
  }
  
  /**
   * Return a label for the given element solely based on EMF mechanisms (editing domain, .edit plugins)
   * @param element_p a potentially null object
   * @return a potentially null string
   */
  public static String getEMFText(Object element_p) {
    String result =
        EMFDiffMergeUIPlugin.getDefault().getAdapterFactoryLabelProvider().getText(element_p);
    if (result == null) {
      // Try editing domain
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
    }
    return result;
  }
  
  /**
   * Return a formatted label for the given feature
   * @param feature_p a non-null feature
   * @return a non-null string
   */
  public static String getFormattedFeatureText(EStructuralFeature feature_p) {
    return FormattedTextProvider.getInstance().getFeatureText(feature_p);
  }
  
  /**
   * Return a formatted label for the type of the given element
   * @param element_p a non-null element
   * @return a non-null string
   */
  public static String getFormattedTypeText(EObject element_p) {
    return FormattedTextProvider.getInstance().getTypeText(element_p);
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
   * Add the given dispose listener to the given tool/menu item
   * @param item_p a non-null tool item or menu item
   * @param listener_p a non-null listener
   */
  public static void itemAddDisposeListener(Item item_p, DisposeListener listener_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    if (item_p instanceof ToolItem) {
      ((ToolItem)item_p).addDisposeListener(listener_p);
    } else {
      ((MenuItem)item_p).addDisposeListener(listener_p);
    }
  }
  
  /**
   * Add the given selection listener to the given tool/menu item
   * @param item_p a non-null tool item or menu item
   * @param listener_p a non-null listener
   */
  public static void itemAddSelectionListener(Item item_p, SelectionListener listener_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    if (item_p instanceof ToolItem) {
      ((ToolItem)item_p).addSelectionListener(listener_p);
    } else {
      ((MenuItem)item_p).addSelectionListener(listener_p);
    }
  }
  
  /**
   * Create an item in the given parent context with the given index and return it
   * @param context_p a non-null tool bar or menu
   * @param index_p a positive index or null
   * @param style_p an SWT style
   * @return a non-null tool/menu item
   */
  public static Item itemCreate(Widget context_p, int style_p, Integer index_p) {
    assert context_p instanceof ToolBar || context_p instanceof Menu;
    Item result;
    if (context_p instanceof ToolBar) {
      ToolBar parent = (ToolBar)context_p;
      if (index_p == null) {
        result = new ToolItem(parent, style_p);
      } else {
        result = new ToolItem(parent, style_p, index_p.intValue());
      }
    } else {
      Menu parent = (Menu)context_p;
      if (index_p == null) {
        result = new MenuItem(parent, style_p);
      } else {
        result = new MenuItem(parent, style_p, index_p.intValue());
      }
    }
    return result;
  }
  
  /**
   * Return whether the given tool/menu item is selected
   * @param item_p a non-null tool item or menu item
   */
  public static boolean itemGetSelection(Item item_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    boolean result;
    if (item_p instanceof ToolItem) {
      result = ((ToolItem)item_p).getSelection();
    } else {
      result = ((MenuItem)item_p).getSelection();
    }
    return result;
  }
  
  /**
   * Set whether the given tool/menu item is selected
   * @param item_p a non-null tool item or menu item
   * @param selected_p whether it is selected
   */
  public static void itemSetSelection(Item item_p, boolean selected_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    if (item_p instanceof ToolItem) {
      ((ToolItem)item_p).setSelection(selected_p);
    } else {
      ((MenuItem)item_p).setSelection(selected_p);
    }
  }
  
  /**
   * Set the text of the given tool/menu item. If a tool item,
   * then the tool tip is affected instead.
   * @param item_p a non-null tool item or menu item
   * @param text_p a potentially null string
   */
  public static void itemSetText(Item item_p, String text_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    if (item_p instanceof ToolItem) {
      ((ToolItem)item_p).setToolTipText(text_p);
    } else {
      ((MenuItem)item_p).setText(text_p);
    }
  }
  
  /**
   * Set the tool tip text of the given tool/menu item. If a tool item,
   * then this operation has no impact - use itemSetText instead.
   * @param item_p a non-null tool item or menu item
   * @param tooltip_p a potentially null string
   */
  public static void itemSetToolTipText(Item item_p, String tooltip_p) {
    assert item_p instanceof ToolItem || item_p instanceof MenuItem;
    if (item_p instanceof MenuItem) {
      ((MenuItem)item_p).setToolTipText(tooltip_p);
    }
  }
  
	/**
	 * Return a UI variant of the representation of the given URI
	 * @param uri_p a potentially null URI
	 * @return a string which is null iff uri_p is null
	 */
	public static String simplifyURI(URI uri_p) {
	  String result = null;
	  if (uri_p != null) {
	    if (uri_p.isPlatform()) {
	      result = uri_p.toPlatformString(true);
      } else if (uri_p.isFile()) {
        result = uri_p.toFileString();
	    } else {
	      String path = uri_p.path();
	      if (path == null)
	        path = uri_p.toString();
	      result = URI.decode(path);
	    }
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
  
  
  /**
   *  A provider for feature and type labels.
   */
  protected static class FormattedTextProvider extends ReflectiveItemProvider {
    /** The singleton instance */
    protected static FormattedTextProvider __instance = null;
    /**
     * Constructor
     */
    protected FormattedTextProvider() {
      super(null); // OK with its current usage
    }
    /**
     * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getFeatureText(java.lang.Object)
     */
    @Override
    public String getFeatureText(Object feature_p) {
      // Increases visibility
      return super.getFeatureText(feature_p);
    }
    /**
     * @see org.eclipse.emf.edit.provider.ReflectiveItemProvider#getTypeText(java.lang.Object)
     */
    @Override
    public String getTypeText(Object object_p) {
      // Increases visibility
      return super.getTypeText(object_p);
    }
    /**
     * Return the singleton instance
     * @return a non-null object
     */
    public static FormattedTextProvider getInstance() {
      if (__instance == null)
        __instance = new FormattedTextProvider();
      return __instance;
    }
  }
  
}
