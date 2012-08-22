package org.eclipse.emf.diffmerge.tests.wt.elements.cases;

import org.eclipse.emf.diffmerge.tests.wt.elements.DiffMergeTestCase;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swt.condition.shell.ShellDisposedCondition;
import com.windowtester.runtime.swt.condition.shell.ShellShowingCondition;
import com.windowtester.runtime.swt.locator.ButtonLocator;
import com.windowtester.runtime.swt.locator.SWTWidgetLocator;
import com.windowtester.runtime.swt.locator.TreeItemLocator;


/**
 * Merge addition/deletion in a hierarchy (container-child dependency).
 * Persistence check.
 */
public class HierarchyAddition extends DiffMergeTestCase {
  
  /**
   * Main test method.
   */
  public void testHierarchyAddition() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (8)/Element B (7)/Element D (2)",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 2, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new ButtonLocator("Include differences in children"));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator("Element E/Deletion", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Group.class, "Implied changes"))));
    ui.click(new TreeItemLocator("Element F/Deletion", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Group.class, "Implied changes"))));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.click(new TreeItemLocator(
        "Root (5)/Element B (4)/Element G (2)/Element H", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Composite.class, 0,
                new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 0, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator(
        "Element B/Addition into Root  (via 'content')", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Group.class, "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Element G/Addition into Element B  (via 'manyContent')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    checkPersistence();
    closeEditors();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.tests.wt.elements.DiffMergeTestCase#persistenceChecks(com.windowtester.runtime.IUIContext)
   */
  @Override
  protected void persistenceChecks(IUIContext ui) throws Exception {
    ui.click(new TreeItemLocator(
        "Root (2)/Element B (2)/Element G (1)/Element I", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Composite.class, 0,
                new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (2)/Element B (2)/Element C",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
  }
  
}