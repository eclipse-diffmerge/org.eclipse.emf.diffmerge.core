package org.eclipse.emf.diffmerge.tests.wt.elements;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swt.condition.shell.ShellDisposedCondition;
import com.windowtester.runtime.swt.locator.ShellLocator;


/**
 * A utility class for tests
 */
public class TestUtil {
  
  private TestUtil() {}
  
  
  /**
   * Wait for the shell to be closed
   * @param ui a non-null WindowTester UI context
   * @throws Exception
   */
  public static void waitForClosing(IUIContext ui) throws Exception {
    ui.ensureThat(new ShellLocator(
    "Plug-in Development - Compare - Eclipse Platform").isClosed());
    ui.wait(new ShellDisposedCondition("Eclipse Platform"));
  }
  
}