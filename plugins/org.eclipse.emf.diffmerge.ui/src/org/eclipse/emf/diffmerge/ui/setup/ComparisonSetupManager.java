/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.setup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IOverridableFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;


/**
 * A manager for model comparison setups that are contributed through the dedicated
 * extension point.
 * @author Olivier Constant
 */
public class ComparisonSetupManager {
  
  /** The ModelComparisonContext extension point */
  private static final String MODEL_COMPARISON_CONTEXT_EXTENSION_POINT =
    "org.eclipse.emf.diffmerge.ui.modelComparisonContext"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point node for configurations */
  private static final String EXTENSION_POINT_METHOD = "comparisonMethod"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point property for configurations */
  private static final String EXTENSION_POINT_PROPERTY_METHOD = "factory"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point node for scopes */
  private static final String EXTENSION_POINT_SCOPE = "scopeDefinition"; //$NON-NLS-1$
  
  /** The ModelComparisonContext extension point property for scopes */
  private static final String EXTENSION_POINT_PROPERTY_SCOPE = "factory"; //$NON-NLS-1$
  
  /** The registered comparison method factories (initially null) */
  private Map<Class<?>, IComparisonMethodFactory> _comparisonFactories;
  
  /** The registered scope factories (initially null) */
  private Map<Class<?>, IModelScopeDefinitionFactory> _scopeFactories;
  
  
  /**
   * Constructor
   * This should normally not be used by clients: get {@link EMFDiffMergeUIPlugin#getDefault()}
   * and use {@link EMFDiffMergeUIPlugin#getSetupManager()} instead.
   */
  public ComparisonSetupManager() {
    _comparisonFactories = null;
    _scopeFactories = null;
  }
  
  /**
   * Return a default compare editor input for the given entry points
   * @param entrypoint1_p a non-null object
   * @param entrypoint2_p a non-null object
   * @param entrypoint3_p an optional object
   * @return a potentially null object (null means failure)
   */
  public EMFDiffMergeEditorInput createDefaultEditorInput(Object entrypoint1_p,
      Object entrypoint2_p, Object entrypoint3_p) {
    EMFDiffMergeEditorInput result = null;
    try {
      ComparisonSetup setup =
          createComparisonSetup(entrypoint1_p, entrypoint2_p, entrypoint3_p);
      if (setup != null && setup.getComparisonMethod() != null)
        result = new EMFDiffMergeEditorInput(setup.getComparisonMethod());
    } catch (IllegalArgumentException e) {
      handleSetupError(null, e.getLocalizedMessage());
    }
    return result;
  }
  
  /**
   * Create and return a compare editor input as a result of user interactions
   * for the given entry points, if possible
   * @param shell_p a non-null shell
   * @param entrypoint1_p a non-null object
   * @param entrypoint2_p a non-null object
   * @param entrypoint3_p an optional object
   */
  public EMFDiffMergeEditorInput createEditorInputWithUI(Shell shell_p, Object entrypoint1_p,
      Object entrypoint2_p, Object entrypoint3_p) {
    EMFDiffMergeEditorInput result = null;
    try {
      ComparisonSetupManager manager = EMFDiffMergeUIPlugin.getDefault().getSetupManager();
      ComparisonSetup setup = manager.createComparisonSetup(
          entrypoint1_p, entrypoint2_p, entrypoint3_p);
      result = createEditorInputWithUI(shell_p, setup);
    } catch (IllegalArgumentException e) {
      handleSetupError(shell_p, e.getLocalizedMessage());
    }
    return result;
  }
  
  /**
   * Create and return a compare editor input as a result of user interactions
   * for the given comparison setup, if possible
   * @param shell_p a non-null shell
   * @param setup_p a comparison setup or null if none could be computed
   */
  public EMFDiffMergeEditorInput createEditorInputWithUI(Shell shell_p, ComparisonSetup setup_p) {
    EMFDiffMergeEditorInput result = null;
    if (setup_p != null) {
      IComparisonMethod method = openSetupWizard(shell_p, setup_p);
      if (method != null)
        result = new EMFDiffMergeEditorInput(method);
    } else {
      handleSetupError(shell_p, null);
    }
    return result;
  }
  
  /**
   * Return a comparison setup for the given entry points
   * @param entrypoint1_p a non-null object
   * @param entrypoint2_p a non-null object
   * @param entrypoint3_p an optional object
   * @throws IllegalArgumentException if the entry points are not supported
   * @return a potentially null object (null means failure)
   */
  public ComparisonSetup createComparisonSetup(Object entrypoint1_p,
      Object entrypoint2_p, Object entrypoint3_p) {
    ComparisonSetup result = null;
    List<IModelScopeDefinitionFactory> factories1 =
        getApplicableModelScopeFactories(entrypoint1_p);
    List<IModelScopeDefinitionFactory> factories2 =
        getApplicableModelScopeFactories(entrypoint2_p);
    List<IModelScopeDefinitionFactory> factories3 = entrypoint3_p == null?
        Collections.<IModelScopeDefinitionFactory>emptyList():
          getApplicableModelScopeFactories(entrypoint3_p);
    if (!factories1.isEmpty() && !factories2.isEmpty()) {
      try {
        IModelScopeDefinition scopeSpec1 =
            factories1.get(0).createScopeDefinition(entrypoint1_p, null, true);
        IModelScopeDefinition scopeSpec2 =
            factories2.get(0).createScopeDefinition(entrypoint2_p, null, true);
        IModelScopeDefinition scopeSpec3 = factories3.isEmpty()? null:
          factories3.get(0).createScopeDefinition(entrypoint3_p, null, true);
        List<IComparisonMethodFactory> cFactories =
            getApplicableComparisonMethodFactories(scopeSpec1, scopeSpec2, scopeSpec3);
        if (!cFactories.isEmpty())
          result = new ComparisonSetup(scopeSpec1, scopeSpec2, scopeSpec3, cFactories);
      } catch (Exception e) {
        EMFDiffMergeUIPlugin.getDefault().getLog().log(new Status(
            IStatus.ERROR, EMFDiffMergeUIPlugin.getDefault().getPluginId(), e.getMessage(), e));
      }
    } else {
      handleUnsupportedEntrypoints(
          factories1.isEmpty()? entrypoint1_p: null,
          factories2.isEmpty()? entrypoint2_p: null);
    }
    return result;
  }
  
  /**
   * Discover the comparison contexts which are registered through the dedicated
   * extension point, if any
   * Postcondition: _comparisonFactories != null && _scopeFactories != null
   */
  protected void discoverRegisteredComparisonContexts() {
    _comparisonFactories = new HashMap<Class<?>, IComparisonMethodFactory>();
    _scopeFactories = new HashMap<Class<?>, IModelScopeDefinitionFactory>();
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IConfigurationElement[] config = registry.getConfigurationElementsFor(
        MODEL_COMPARISON_CONTEXT_EXTENSION_POINT);
    for (IConfigurationElement e : config) {
      String name = e.getName();
      if (EXTENSION_POINT_METHOD.equals(name)) {
        try {
          Object o = e.createExecutableExtension(EXTENSION_POINT_PROPERTY_METHOD);
          if (o instanceof IComparisonMethodFactory)
            _comparisonFactories.put(o.getClass(), (IComparisonMethodFactory)o);
        } catch (CoreException ex) {
          // Proceed
        }
      } else if (EXTENSION_POINT_SCOPE.equals(name)) {
        try {
          Object o = e.createExecutableExtension(EXTENSION_POINT_PROPERTY_SCOPE);
          if (o instanceof IModelScopeDefinitionFactory)
            _scopeFactories.put(o.getClass(), (IModelScopeDefinitionFactory)o);
        } catch (CoreException ex) {
          // Proceed
        }
      }
    }
  }
  
  /**
   * Return the set of comparison method factories that are applicable to the given
   * entry point
   * @param leftScopeSpec_p a non-null scope definition
   * @param rightScopeSpec_p a non-null scope definition
   * @param ancestorScopeSpec_p an optional scope definition
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IComparisonMethodFactory> getApplicableComparisonMethodFactories(
      IModelScopeDefinition leftScopeSpec_p, IModelScopeDefinition rightScopeSpec_p,
      IModelScopeDefinition ancestorScopeSpec_p) {
    List<IComparisonMethodFactory> result = new ArrayList<IComparisonMethodFactory>();
    for (IComparisonMethodFactory factory : getRegisteredComparisonMethodFactories()) {
      if (factory.isApplicableTo(leftScopeSpec_p, rightScopeSpec_p, ancestorScopeSpec_p))
        result.add(factory);
    }
    result = reduceByOverride(result, _comparisonFactories);
    return result;
  }
  
  /**
   * Return the set of scope definition factories that are applicable to the given
   * entry point
   * @param entrypoint_p a non-null object
   * @return a non-null, unmodifiable list which cannot be empty if isValidEntrypoint(entrypoint_p)
   */
  public List<IModelScopeDefinitionFactory> getApplicableModelScopeFactories(
      Object entrypoint_p) {
    List<IModelScopeDefinitionFactory> result = new ArrayList<IModelScopeDefinitionFactory>();
    for (IModelScopeDefinitionFactory factory : getRegisteredModelScopeDefinitionFactories()) {
      if (factory.isApplicableTo(entrypoint_p))
        result.add(factory);
    }
    result = reduceByOverride(result, _scopeFactories);
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the comparison method factories which are registered through the dedicated
   * extension point, if any
   * @return a non-null, potentially empty list
   */
  protected final Collection<IComparisonMethodFactory> getRegisteredComparisonMethodFactories() {
    if (_comparisonFactories == null)
      discoverRegisteredComparisonContexts();
    return _comparisonFactories.values();
  }
  
  /**
   * Return the scope definition factories which are registered through the dedicated
   * extension point, if any
   * @return a non-null, potentially empty list
   */
  protected final Collection<IModelScopeDefinitionFactory> getRegisteredModelScopeDefinitionFactories() {
    if (_scopeFactories == null)
      discoverRegisteredComparisonContexts();
    return _scopeFactories.values();
  }
  
  /**
   * Handle the given user-level setup error message
   * @param shell_p an optional shell (if null, error message is being logged)
   * @param message_p an optional message (if null, replaced by a default setup error message)
   */
  public void handleSetupError(Shell shell_p, String message_p) {
    String message = (message_p != null)? message_p: Messages.CompareModelsAction_ModelsOnly;
    if (shell_p != null) {
      MessageDialog.openError(shell_p, EMFDiffMergeUIPlugin.LABEL, message);
    } else {
      EMFDiffMergeUIPlugin.getDefault().getLog().log(new Status(
          IStatus.ERROR, EMFDiffMergeUIPlugin.getDefault().getPluginId(), message, null));
    }
  }
  
  /**
   * Handle the fact that the given left and/or right entry points are not supported by
   * available scope factories
   * @param unsupportedLeft_p an optional entry point for the left-hand side
   * @param unsupportedRight_p an optional entry point for the right-hand side
   */
  protected void handleUnsupportedEntrypoints(Object unsupportedLeft_p, Object unsupportedRight_p) {
    StringBuilder msgBuilder = new StringBuilder();
    boolean leftKO = unsupportedLeft_p != null;
    boolean rightKO = unsupportedRight_p != null;
    if (leftKO) {
      String leftMessage;
      if (unsupportedLeft_p instanceof ITypedElement) {
        String leftName = ((ITypedElement)unsupportedLeft_p).getName();
        leftMessage = String.format(Messages.ComparisonSetupManager_CannotLoadLeftKnown, leftName);
      } else {
        leftMessage = Messages.ComparisonSetupManager_CannotLoadLeftUnknown;
      }
      msgBuilder.append(leftMessage);
    }
    if (leftKO && rightKO)
      msgBuilder.append('\n');
    if (rightKO) {
      String rightMessage;
      if (unsupportedRight_p instanceof ITypedElement) {
        String rightName = ((ITypedElement)unsupportedRight_p).getName();
        rightMessage = String.format(Messages.ComparisonSetupManager_CannotLoadRightKnown, rightName);
      } else {
        rightMessage = Messages.ComparisonSetupManager_CannotLoadRightUnknown;
      }
      msgBuilder.append(rightMessage);
    }
    throw new IllegalArgumentException(msgBuilder.toString());
  }
  
  /**
   * Return whether the given object is a valid entry point for a comparison, i.e.,
   * whether a model scope can be derived from it
   * @param entrypoint_p a non-null object
   */
  public boolean isValidEntrypoint(Object entrypoint_p) {
    for (IModelScopeDefinitionFactory factory : getRegisteredModelScopeDefinitionFactories()) {
      if (factory.isApplicableTo(entrypoint_p))
        return true;
    }
    return false;
  }
  
  /**
   * Open a setup wizard for the given setup
   * @param shell_p a non-null shell
   * @param setup_p a non-null setup
   * @return the resulting comparison method of the setup, or null if the user did not confirm
   */
  protected IComparisonMethod openSetupWizard(Shell shell_p, ComparisonSetup setup_p) {
    IComparisonMethod result = null;
    ComparisonSetupWizard wizard = new ComparisonSetupWizard(setup_p);
    WizardDialog dialog = new WizardDialog(shell_p, wizard);
    dialog.setHelpAvailable(false);
    int openResult = dialog.open();
    if (Window.OK == openResult)
      result = setup_p.getComparisonMethod();
    return result;
  }
  
  /**
   * Return the reduced version of the given list of factories based on the "override" relation
   * @param factories_p a non-null, potentially empty list
   * @param configurationFactories_p a map from factory classes to the corresponding registered instances
   * @return a non-null, unmodifiable list which is not empty if factories_p is not empty
   */
  protected <T extends IOverridableFactory> List<T> reduceByOverride(List<T> factories_p,
      Map<Class<?>, T> configurationFactories_p) {
    List<T> result = new ArrayList<T>(factories_p);
    for (T factory : factories_p) {
      for (Class<?> factoryClass : factory.getOverridenClasses()) {
        result.remove(configurationFactories_p.get(factoryClass));
      }
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Update the given editor input through a configuration GUI
   * @param shell_p a non-null shell
   * @param input_p a non-null, non-disposed editor input
   * @return whether the operation succeeded
   */
  public boolean updateEditorInputWithUI(Shell shell_p,
      EMFDiffMergeEditorInput input_p) {
    assert input_p.getComparisonMethod() != null;
    boolean result = false;
    ComparisonSetup setup = new ComparisonSetup(input_p.getComparisonMethod());
    IComparisonMethod method = openSetupWizard(shell_p, setup);
    if (method != null) {
      input_p.setComparisonMethod(method);
      result = true;
    }
    return result;
  }
  
}
