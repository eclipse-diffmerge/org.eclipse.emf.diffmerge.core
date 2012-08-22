package org.eclipse.emf.diffmerge.tests.wt.elements.cases;

import org.eclipse.emf.diffmerge.tests.wt.elements.DiffMergeTestCase;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swt.locator.SWTWidgetLocator;
import com.windowtester.runtime.swt.locator.TreeItemLocator;


/**
 * Basic differences on a graph model
 */
public class Graph extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testGraph() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (8)/Edge N3-to-NB-Container",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)/Node N2 (4)", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)", new SWTWidgetLocator(Tree.class,
        new SWTWidgetLocator(Composite.class, 0, new SWTWidgetLocator(
            SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)/Node N2 (4)/Node N22 (2)",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)/Node N2 (4)/Node N22 (2)/Node N221",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator(
        "Root (8)/Node N2 (4)/Node N22 (2)/Edge N221-to-N3",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)/Node N3 (2)", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (8)/Referencing Node ToN221", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    closeEditors();
  }
  
}