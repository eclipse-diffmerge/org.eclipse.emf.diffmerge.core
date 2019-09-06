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
import com.windowtester.runtime.swt.locator.TableItemLocator;
import com.windowtester.runtime.swt.locator.TreeItemLocator;


/**
 * Element moved in an added element.
 * Persistence check.
 */
public class MovedInAdded extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testMovedInAdded() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (3)/Element A (2)/Element B (2)",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (3)/Element A (2)",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 0, new SWTWidgetLocator(
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
    ui.click(new TreeItemLocator(
        "Element A/Addition into Root  (via 'content')", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Group.class, "Required changes"))));
    ui.click(new TreeItemLocator(
        "Element B/Move into Element A  (via 'manyContent')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Required changes"))));
    ui.click(new TreeItemLocator("Element B/Move out of Root (from 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.click(new TreeItemLocator("Root (1)/Element A (1)/Element B (1)",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TableItemLocator("value"));
    ui.click(new TableItemLocator("1"));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 0, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    closeCompareEditor(true);
    checkModelIsValid(true);
    checkModelIsValid(false);
    checkIdentical();
    closeEditors();
  }

}