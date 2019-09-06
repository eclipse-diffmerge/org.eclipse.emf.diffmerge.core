package org.eclipse.emf.diffmerge.tests.wt.elements.cases;

import org.eclipse.emf.diffmerge.tests.wt.elements.DiffMergeTestCase;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
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
 * Deletion of an element referenced by elements present and absent in the opposite scope.
 * Persistence check.
 */
public class InterReferencedAdditions extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testInterReferencedAdditions() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Root (3)/Element E2", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 2, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    ui.wait(new ShellShowingCondition("Merge Operation"));
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
    ui.click(new TreeItemLocator("Root (1)/Element E3", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
  }
  
}