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
 * Multiple reference changes resulting from the deletion of a reference value.
 * Persistence check.
 */
public class MandatoryRefChange extends DiffMergeTestCase {
  
  /**
   * Main test method.
   */
  public void testMandatoryRefChange() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (3)/Node C (1)", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TableItemLocator("Edge Z (in Root)"));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 2, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 2, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator(
        "Node C/Reference 'incoming': deletion of Edge Z",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Required changes"))));
    ui.click(new TreeItemLocator(
        "Edge Z/Reference 'target': addition of Node B", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Group.class, "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Edge Z/Reference 'target': deletion of Node C", new SWTWidgetLocator(
            Tree.class, new SWTWidgetLocator(Group.class, "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Node B/Reference 'incoming': addition of Edge Z",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
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