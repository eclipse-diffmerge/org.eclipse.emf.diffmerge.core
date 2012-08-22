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
 * Addition of reference to added nested element.
 * Persistence check.
 */
public class NestedReferencedAddition extends DiffMergeTestCase {

  /**
   * Main test method.
   */
  public void testNestedReferenceAddition() throws Exception {
    IUIContext ui = getUI();
    compareInFolder(true);
    ui.click(new TreeItemLocator("Element ROOT (3)", new SWTWidgetLocator(
        Tree.class, new SWTWidgetLocator(Composite.class, 0,
            new SWTWidgetLocator(SashForm.class)))));
    ui.click(new SWTWidgetLocator(ToolItem.class, "", 0, new SWTWidgetLocator(
        ToolBar.class, new SWTWidgetLocator(Composite.class,
            new SWTWidgetLocator(Composite.class, 1, new SWTWidgetLocator(
                SashForm.class))))));
    ui.wait(new ShellShowingCondition("Merge Operation"));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Merge Operation"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
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