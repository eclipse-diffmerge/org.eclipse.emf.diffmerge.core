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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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
	
  /** The CompareConfiguration "mirrored" property defined in Neon and later,
   * explicit here for compatibility with older versions of Eclipse */
  public static final String CC_MIRRORED_PROPERTY = "MIRRORED"; //$NON-NLS-1$
  
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
   * Create a tool item in the given context which displays a drop-down menu 
   * @param context_p a non-null object
   * @return the non-null menu
   */
  public static MenuManager createMenuTool(IToolBarManager context_p) {
    MenuDropDownAction action = new MenuDropDownAction(IAction.AS_PUSH_BUTTON);
    action.setImageDescriptor(EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(
        EMFDiffMergeUIPlugin.ImageID.VIEW_MENU));
    context_p.add(action);
    return action.getMenuManager();
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
   * Return the base (non-bold, non-italic) variant of the given font
   * @param font_p a non-null font
   * @return a non-null font
   */
  public static Font getBase(Font font_p) {
    FontData data = font_p.getFontData()[0];
    Font result = JFaceResources.getFontRegistry().get(data.getName());
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
  
  /**
   * A comparator based on labels of objects.
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
   * A drop-down action that shows a menu through a menu manager.
   * It can be used with a ToolBar Manager or a Menu Manager. In the former case
   * the action will be represented as a button (drop-down by default, unless otherwise
   * specified via the constructor), in the latter case as a cascading menu.
   */
  public static class MenuDropDownAction extends Action implements IMenuCreator {
    /** The non-null menu manager */
    private final MenuManager _menuManager;
    /**
     * Default constructor for drop-down style
     */
    public MenuDropDownAction() {
      this(IAction.AS_DROP_DOWN_MENU);
    }
    /**
     * Constructor for custom style
     * @param style_p a style as defined in IAction
     */
    public MenuDropDownAction(int style_p) {
      super(null, style_p);
      _menuManager = new MenuManager();
      setMenuCreator(this);
    }
    /**
     * @see org.eclipse.jface.action.IMenuCreator#dispose()
     */
    public void dispose() {
      if (_menuManager != null) {
        _menuManager.dispose();
      }
    }
    /**
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
     */
    public Menu getMenu(Control parent_p) {
      Menu result = _menuManager.createContextMenu(parent_p); // Does not recreate
      parent_p.setMenu(result);
      return result;
    }
    /**
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
     */
    public Menu getMenu(Menu parent_p) {
      Menu existingMenu = _menuManager.getMenu();
      if (existingMenu == null || existingMenu.isDisposed()) {
        Menu containerMenu = new Menu(parent_p); // Not to be shown
        _menuManager.fill(containerMenu, -1); // Contribution mechanism will issue copy items
      }
      return _menuManager.getMenu();
    }
    /**
     * Return the menu manager for the menu provided by this action
     * @return a non-null menu manager
     */
    public MenuManager getMenuManager() {
      return _menuManager;
    }
    /**
     * @see org.eclipse.jface.action.Action#runWithEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void runWithEvent(Event event_p) {
      Widget sourceWidget = event_p.widget;
      Menu menu = null;
      if (sourceWidget instanceof ToolItem) {
        ToolItem item = (ToolItem)sourceWidget;
        ToolBar toolBar = item.getParent();
        menu = getMenu(toolBar);
        Rectangle rect = item.getBounds();
        Point pt = toolBar.toDisplay(new Point(rect.x, rect.y));
        menu.setLocation(pt.x, pt.y + rect.height);
      } else if (sourceWidget instanceof MenuItem) {
        Menu parentMenu = ((MenuItem)sourceWidget).getMenu();
        menu = getMenu(parentMenu);
      }
      if (menu != null) {
        menu.setVisible(true);
      }
    }
  }
  
}
