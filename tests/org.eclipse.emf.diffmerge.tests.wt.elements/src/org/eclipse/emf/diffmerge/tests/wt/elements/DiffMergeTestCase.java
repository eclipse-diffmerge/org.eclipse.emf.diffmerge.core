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
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.WT;
import com.windowtester.runtime.swt.UITestCaseSWT;
import com.windowtester.runtime.swt.condition.shell.ShellDisposedCondition;
import com.windowtester.runtime.swt.condition.shell.ShellShowingCondition;
import com.windowtester.runtime.swt.locator.ButtonLocator;
import com.windowtester.runtime.swt.locator.CTabItemLocator;
import com.windowtester.runtime.swt.locator.SWTWidgetLocator;
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
  private static final String EDITOR_TITLE = "Compare ('Ex1' - 'Ex2')";
  private static final char SEP = '/';
  
  
  /**
   * Check that the persistent form of the 2 models at the current path are identical
   * @throws Exception
   */
  protected void checkIdentical() throws Exception {
    IUIContext ui = getUI();
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
    ui.wait(new ShellShowingCondition("Compare"));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Compare"));
  }
  
  /**
   * Check that the given model is valid
   * @param isModel1 whether model1 is considered, or model2
   * @throws Exception
   */
  protected void checkModelIsValid(boolean isModel1) throws Exception {
    IUIContext ui = getUI();
    String fileName = isModel1? MODEL1: MODEL2;
    ui.click(new TreeItemLocator(CURRENT_PROJECT_NAME + SEP + fileName,
        new ViewLocator("org.eclipse.jdt.ui.PackageExplorer")));
    ui.contextClick(new TreeItemLocator(CURRENT_PROJECT_NAME + SEP + fileName,
        new ViewLocator("org.eclipse.jdt.ui.PackageExplorer")),
        "Open With/Elements Model Editor");
    final String TREE_ROOT_LABEL = "platform:\\\\/resource\\\\/" + CURRENT_PROJECT_NAME + "\\\\/" + fileName;
    ui.contextClick(
        new TreeItemLocator(
            TREE_ROOT_LABEL,
            new SWTWidgetLocator(Tree.class, new SWTWidgetLocator(
                ViewForm.class))), "Validate");
    ui.wait(new ShellShowingCondition("Validation Information"));
    ui.click(new ButtonLocator("OK"));
    ui.wait(new ShellDisposedCondition("Validation Information"));
    ui.wait(new ShellDisposedCondition("Progress Information"));
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        activePage.closeEditor(activePage.getActiveEditor(), false);
      }
    });
  }
  
  /**
   * Apply the checks that must hold before and after save 
   * @throws Exception
   */
  protected void checkPersistence() throws Exception {
    IUIContext ui = getUI();
    persistenceChecks(ui);
    closeCompareEditor(true);
    checkModelIsValid(true);
    checkModelIsValid(false);
    compareInFolder(false);
    persistenceChecks(ui);
  }
  
  /**
   * Close the firstly found compare editor
   * @param dirty whether the editor is expected to be dirty
   * @throws Exception
   */
  protected void closeCompareEditor(boolean dirty) throws Exception {
    IUIContext ui = getUI();
    final String editorName = (dirty? "*": "") + EDITOR_TITLE;
    ui.contextClick(new CTabItemLocator(editorName), "Close");
    ui.wait(new ShellDisposedCondition("Progress Information"));
    if (dirty) {
      ui.wait(new ShellShowingCondition("Save Resource"));
      ui.click(new ButtonLocator("&Yes"));
      ui.wait(new ShellDisposedCondition("Save Resource"));
    }
  }
  
  /**
   * Close all editors
   */
  protected void closeEditors() {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        activePage.closeAllEditors(false);
      }
    });
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
  protected String getModelPath() {
    // Default implementation
    return getClass().getSimpleName();
  }
  
  /**
   * Define the checks that must hold before and after save
   * @param ui a non-null UI context
   * @throws Exception
   */
  protected void persistenceChecks(IUIContext ui) throws Exception {
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