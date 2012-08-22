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
 * Same as InterReferencedAdditions, except that the element deleted is nested.
 * Persistence check.
 */
public class InterReferencedAdditionsNested extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testInterReferencedAdditionsNested() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (4)/Element E4 (1)/Element E2",
        new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(Composite.class,
            0, new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 2, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new TreeItemLocator("Element E2/Deletion", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Group.class, "Required changes"))));
    ui.click(new TreeItemLocator(
        "Element E1/Reference 'singleRef': deletion of Element E2",
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
    ui.click(new TreeItemLocator("Root (2)/Element E3", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new TreeItemLocator("Root (2)/Element E4", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
  }
  
}