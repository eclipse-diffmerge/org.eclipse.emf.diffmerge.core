package org.eclipse.emf.diffmerge.tests.wt.elements;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.PlatformUI;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.WT;
import com.windowtester.runtime.swt.UITestCaseSWT;
import com.windowtester.runtime.swt.condition.shell.ShellDisposedCondition;
import com.windowtester.runtime.swt.condition.shell.ShellShowingCondition;
import com.windowtester.runtime.swt.locator.ButtonLocator;
import com.windowtester.runtime.swt.locator.CTabItemLocator;
import com.windowtester.runtime.swt.locator.TreeItemLocator;
import com.windowtester.runtime.swt.locator.eclipse.ViewLocator;
import com.windowtester.runtime.swt.locator.eclipse.WorkbenchLocator;


/**
 * A common superclass for tests on diff/merge
 */
public abstract class DiffMergeTestCase extends UITestCaseSWT {
  
  private static final String PREDEFINED_PROJECT_NAME = "PredefinedExamples";
  private static final String CURRENT_PROJECT_NAME = "Example";
  private static boolean _initialized = false;
  private static IProject __predefinedProject, __currentProject;
  private static final String MODEL1 = "Ex1.elements";
  private static final String MODEL2 = "Ex2.elements";
  private static final char SEP = '/';
  
  
  /**
   * Apply the checks that must hold before and after save 
   * @throws Exception
   */
  protected void checkPersistency() throws Exception {
    IUIContext ui = getUI();
    persistencyChecks(ui);
    saveAndRecompareInFolder();
    persistencyChecks(ui);
  }
  
  /**
   * Close the firstly found compare editor
   * @param dirty whether the editor is expected to be dirty
   * @throws Exception
   */
  protected void closeCompareEditor(boolean dirty) throws Exception {
    IUIContext ui = getUI();
    final String editorName = (dirty? "*": "") + "Compare";
    ui.contextClick(new CTabItemLocator(editorName), "Close");
    ui.wait(new ShellDisposedCondition("Progress Information"));
    if (dirty) {
      ui.wait(new ShellShowingCondition("Save Resource"));
      ui.click(new ButtonLocator("&Yes"));
      ui.wait(new ShellDisposedCondition("Save Resource"));
    }
  }
  
  /**
   * Compare the 2 models at the current path
   * @param restore whether the saved version of the files should be restored first
   * @throws Exception
   */
  protected void compareInFolder(boolean restore) throws Exception {
    IUIContext ui = getUI();
    if (restore)
      restoreSavedModels(getModelPath());
    final String current1 = CURRENT_PROJECT_NAME + SEP + MODEL1;
    final String current2 = CURRENT_PROJECT_NAME + SEP + MODEL2;
    ui.click(new TreeItemLocator(current1,
        new ViewLocator("org.eclipse.jdt.ui.PackageExplorer")));
    ui.click(1, new TreeItemLocator(current2,
        new ViewLocator("org.eclipse.jdt.ui.PackageExplorer")), WT.CTRL);
    ui.contextClick(new TreeItemLocator(current1,
        new ViewLocator("org.eclipse.jdt.ui.PackageExplorer")),
        "Compare With/Each Other as models");
    ui.wait(new ShellShowingCondition("EMF Diff/Merge"));
    ui.click(new ButtonLocator("&Finish"));
    ui.wait(new ShellDisposedCondition("EMF Diff/Merge"));
  }
  
  /**
   * Return the path to the models to be compared
   * @return a non-null path in the examples folder, e.g., "FolderName"
   */
  protected abstract String getModelPath();
  
  /**
   * Define the checks that must hold before and after save
   * @param ui a non-null UI context
   * @throws Exception
   */
  protected void persistencyChecks(IUIContext ui) throws Exception {
    // Override if checkPersistency() is used
  }
  
  /**
   * Restore the models saved for comparison at the given path
   * @param path a non-null workspace path, e.g., "ProjectName/FolderName"
   * @throws Exception
   */
  private void restoreSavedModels(String path) throws Exception {
    // Destination files
    IFile file1 = __currentProject.getFile(MODEL1);
    if (file1.exists())
      file1.delete(true, null);
    IFile file2 = __currentProject.getFile(MODEL2);
    if (file2.exists())
      file2.delete(true, null);
    // Origin files
    IFolder folder = __predefinedProject.getFolder(path);
    IFile predefinedFile1 = folder.getFile(MODEL1);
    if (predefinedFile1.exists())
      predefinedFile1.copy(file1.getFullPath(), true, null);
    IFile predefinedFile2 = folder.getFile(MODEL2);
    if (predefinedFile2.exists())
      predefinedFile2.copy(file2.getFullPath(), true, null);
  }
  
  /**
   * Save and close the current comparison and re-open it at the given path
   * @param ui a non-null WindowTester UI context
   * @param path a non-null workspace path, e.g., "ProjectName/FolderName"
   * @throws Exception
   */
  protected void saveAndRecompareInFolder() throws Exception {
    closeCompareEditor(true);
    compareInFolder(false);
  }
  
  /* @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    IUIContext ui = getUI();
    ui.ensureThat(new WorkbenchLocator().hasFocus());
    ui.ensureThat(ViewLocator.forName("Welcome").isClosed());
    if (!_initialized)
      setupWorkspace();
  }
  
  /**
   * Setup the workspace
   * @throws Exception
   */
  private void setupWorkspace() throws Exception {
    IProgressMonitor monitor = new NullProgressMonitor();
    IWorkspace wk = ResourcesPlugin.getWorkspace();
    // Current test project
    __currentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(CURRENT_PROJECT_NAME);
    __currentProject.create(monitor);
    __currentProject.open(monitor);
    // Predefined test project
    IPath wkPath = wk.getRoot().getLocation();
    IPath containingFolder = wkPath.uptoSegment(wkPath.segmentCount()-1);
    IPath predefinedTestProjectPath = containingFolder.append(PREDEFINED_PROJECT_NAME);
    IProjectDescription description = wk.loadProjectDescription(predefinedTestProjectPath.append(".project"));
    __predefinedProject = ResourcesPlugin.getWorkspace().getRoot().getProject(PREDEFINED_PROJECT_NAME);
    __predefinedProject.create(description, monitor);
    __predefinedProject.open(monitor);
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        IPerspectiveDescriptor perspective =
          PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("org.eclipse.pde.ui.PDEPerspective");
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(perspective);
      }
    });
    _initialized = true;
  }

}