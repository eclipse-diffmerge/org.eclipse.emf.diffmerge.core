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
 * Addition/deletion of a chain of elements due to a mandatory reference.
 */
public class InterReferencedAdditionsMandatory extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testInterReferencedAdditionsMandatory() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (5)/Node N1", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 2, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator("Node N1/Deletion", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Group.class, "Required changes"))));
    ui.click(new TreeItemLocator("Referencing Node RN1/Deletion",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator("Referencing Node RN2/Deletion",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator("Referencing Node RN3/Deletion",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator("Referencing Node RN4/Deletion",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new ButtonLocator("Cancel"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.click(new TreeItemLocator("Root (5)/Referencing Node RN4",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 0, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator(
        "Referencing Node RN4/Addition into Root  (via 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Required changes"))));
    ui.click(new TreeItemLocator("Node N1/Addition into Root  (via 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Referencing Node RN1/Addition into Root  (via 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Referencing Node RN2/Addition into Root  (via 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new TreeItemLocator(
        "Referencing Node RN3/Addition into Root  (via 'content')",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Group.class,
            "Implied changes"))));
    ui.click(new ButtonLocator("OK"));
    closeEditors();
  }
  
}