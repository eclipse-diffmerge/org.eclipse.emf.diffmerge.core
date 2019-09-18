/*********************************************************************
 * Copyright (c) 2017-2019 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - Bug #516234 : initial API and implementation
 *    Olivier Constant (Thales Global Services) - final integration
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import static org.eclipse.emf.diffmerge.api.Role.ANCESTOR;

import java.util.Collection;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


/**
 * This dialog show the compared content of a String feature to be reviewed
 * and optionally edited.
 * 
 * @author Stephane Bouchet (Intel Corporation)
 */
public class TextMergerDialog extends MessageDialog {
  
  /**
   * A diff node with text content. It can be used as input of a Text Merge Viewer.
   * @author Stephane Bouchet (Intel Corporation)
   */
  protected class TextDiffNode extends DiffNode {
    /** The optional ancestor content */
    private TextCompareContent _ancestorContent;
    /** The non-null left content */
    private TextCompareContent _leftContent;
    /** The non-null right content */
    private TextCompareContent _rightContent;
    /**
     * Constructor
     * @param kind_p the kind of the node according to Differencer
     */
    public TextDiffNode(int kind_p) {
      super(kind_p);
    }
    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getAncestor()
     */
    @Override
    public TextCompareContent getAncestor() {
      if (_diffNode.isThreeWay()) {
        _ancestorContent = new TextCompareContent(getValue(Role.ANCESTOR), false);
      }
      return _ancestorContent;
    }
    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getLeft()
     */
    @Override
    public TextCompareContent getLeft() {
      if (_leftContent == null) {
        _leftContent = new TextCompareContent(
            getValue(_diffNode.getRoleForSide(true)),
            isEditable(true));
      }
      return _leftContent;
    }
    /**
     * @see org.eclipse.compare.structuremergeviewer.DiffNode#getRight()
     */
    @Override
    public TextCompareContent getRight() {
      if (_rightContent == null) {
        _rightContent = new TextCompareContent(
            getValue(_diffNode.getRoleForSide(false)),
            isEditable(false));
      }
      return _rightContent;
    }
  }
  
  
  /** The number of tool bar columns */
  protected static final int TOOLBAR_COLUMNS = 4;
  
  /** The default size of the window */
  protected static final Point DEFAULT_SIZE = new Point(800, 600);
  
  /** The (initially null) label of the "switch left and right view" action */
  protected static String __switchSidesActionLabel = null;
  
  /** The non-null diff node */
  protected final EMFDiffNode _diffNode;
  
  /** The non-null match concerned */
  protected final IMatch _match;
  
  /** The non-null feature concerned */
  protected final EAttribute _feature;
  
  /** The non-null label provider */
  protected final ILabelProvider _labelProvider = DiffMergeLabelProvider.getInstance();
  
  /** The (non-null after initialization) text merge viewer */
  protected TextMergeViewer _textViewer;
  
  /** The (initially null, non-null after first attempt of use) tool bar manager */
  protected ToolBarManager _toolBarManager;
  
  /** The (non-null if the confirmation button has been pressed) input of the text merge viewer */
  protected TextDiffNode _viewerInput;
  
  
  /**
   * Constructor
   * @param shell_p a non-null shell
   * @param diffNode_p a non-null diff node for the global comparison
   * @param match_p the non-null match concerned
   * @param feature_p the non-null attribute concerned
   */
  public TextMergerDialog(Shell shell_p, EMFDiffNode diffNode_p,
      IMatch match_p, EAttribute feature_p) {
    super(shell_p, null, null, null, 0,
        new String[] {
            IDialogConstants.OK_LABEL,
            IDialogConstants.CANCEL_LABEL }, 0);
    assert isApplicableTo(feature_p);
    _diffNode = diffNode_p;
    _match = match_p;
    _feature = feature_p;
    _toolBarManager = null;
    setShellStyle(getShellStyle() | SWT.RESIZE);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
   */
  @Override
  protected void buttonPressed(int buttonId) {
    // Store the merged content before closing so that it can be used afterwards
    if (buttonId == Window.OK) {
      _textViewer.flush(new NullProgressMonitor());
      _viewerInput = (TextDiffNode)_textViewer.getInput();
    }
    super.buttonPressed(buttonId);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell shell) {
    shell.setText(Messages.TextMergerDialog_ShellLabel);
    shell.setSize(DEFAULT_SIZE);
    super.configureShell(shell);
  }
  
  /**
   * Create, configure and return a compare configuration for the Text Merge Viewer
   * @return a non-null object
   */
  protected CompareConfiguration createCompareConfiguration() {
    CompareConfiguration result = new CompareConfiguration();
    result.setProperty(UIUtil.CC_MIRRORED_PROPERTY, Boolean.FALSE);
    if (_diffNode.isThreeWay()) {
      result.setAncestorLabel(_labelProvider.getText(getScope(ANCESTOR)));
    }
    result.setLeftLabel(_labelProvider.getText(getScope(true)));
    result.setLeftEditable(_diffNode.isEditable(true));
    result.setRightLabel(_labelProvider.getText(getScope(false)));
    result.setRightEditable(_diffNode.isEditable(false));
    return result;
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {
    // Parent layout
    GridLayout layout = new GridLayout();
    parent_p.setLayout(layout);
    // Tool bar
    final Composite toolbar = new Composite(parent_p, SWT.NONE);
    GridLayout toolbarLayout = new GridLayout(TOOLBAR_COLUMNS, false);
    toolbarLayout.marginHeight = 0;
    toolbarLayout.marginWidth = 0;
    toolbar.setLayout(toolbarLayout);
    toolbar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    // Tool bar contents: image
    Label labelImage = new Label(toolbar, SWT.NONE);
    labelImage.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(
        ISharedImages.IMG_OBJ_FILE));
    labelImage.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    // Tool bar contents: text
    Label labelText = new Label(toolbar, SWT.TOP);
    labelText.setText(Messages.TextMergerDialog_HeaderLabel);
    labelText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    // Main composite
    final Composite main = new Composite(parent_p, SWT.NONE);
    GridLayout mainLayout = new GridLayout();
    mainLayout.marginHeight = 2;
    mainLayout.marginWidth = 0;
    main.setLayout(mainLayout);
    main.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Compare configuration for Text Merge Viewer
    CompareConfiguration cc = createCompareConfiguration();
    // Text Merge Viewer in main composite
    _textViewer = createTextMergeViewer(main, toolbar, cc);
    _textViewer.setLabelProvider(_labelProvider);
    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    data.horizontalSpan = 2;
    _textViewer.getControl().setLayoutData(data);
    // Input
    ICompareInput input = new TextDiffNode(getDifferencerKind());
    _textViewer.setInput(input);
    return main;
  }
  
  /**
   * Create and return the Text Merge Viewer of this dialog
   * @param parent_p a non-null composite for the main control
   * @param toolbar_p a non-null composite for the tool bar
   * @param cc_p a non-null compare configuration
   * @return a non-null Text Merge Viewer
   */
  protected TextMergeViewer createTextMergeViewer(Composite parent_p,
      final Composite toolbar_p, CompareConfiguration cc_p) {
    TextMergeViewer result = new TextMergeViewer(parent_p, SWT.BORDER, cc_p) {
      /**
       * @see org.eclipse.compare.contentmergeviewer.ContentMergeViewer#getToolBarManager(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected IToolBarManager getToolBarManager(Composite localParent_p) {
        if (_toolBarManager != null && _toolBarManager.getControl() == null)
          return null;
        if (_toolBarManager == null) {
          _toolBarManager = createToolBarManager(toolbar_p);
        }
        return _toolBarManager;
      }
    };
    return result;
  }
  
  /**
   * Create and return the tool bar manager of the main viewer in the given context
   * @param parent_p a non-null composite
   * @return a non-null tool bar manager
   */
  protected ToolBarManager createToolBarManager(Composite parent_p) {
    final ToolBar tb = new ToolBar(parent_p, SWT.FLAT);
    tb.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
    ToolBarManager result = new ToolBarManager(tb);
    result.setOverrides(new IContributionManagerOverrides() {
      /**
       * @see org.eclipse.jface.action.IContributionManagerOverrides#getAccelerator(org.eclipse.jface.action.IContributionItem)
       */
      public Integer getAccelerator(IContributionItem item_p) {
        return null;
      }
      /**
       * @see org.eclipse.jface.action.IContributionManagerOverrides#getAcceleratorText(org.eclipse.jface.action.IContributionItem)
       */
      public String getAcceleratorText(IContributionItem item_p) {
        return null;
      }
      /**
       * @see org.eclipse.jface.action.IContributionManagerOverrides#getEnabled(org.eclipse.jface.action.IContributionItem)
       */
      public Boolean getEnabled(IContributionItem item_p) {
        // Disable "switch left and right view" action
        Boolean localResult = null;
        if (isSwitchSidesAction(item_p)) {
          localResult = Boolean.FALSE;
        }
        return localResult;
      }
      /**
       * @see org.eclipse.jface.action.IContributionManagerOverrides#getText(org.eclipse.jface.action.IContributionItem)
       */
      public String getText(IContributionItem item_p) {
        return null;
      }
      /**
       * @see org.eclipse.jface.action.IContributionManagerOverrides#getVisible(org.eclipse.jface.action.IContributionItem)
       */
      public Boolean getVisible(IContributionItem item_p) {
        // Hide "switch left and right view" action
        Boolean localResult = null;
        if (isSwitchSidesAction(item_p)) {
          localResult = Boolean.FALSE;
        }
        return localResult;
      }
    });
    return result;
  }
  
  /**
   * Return the difference related to the current state
   * @return an attribute value presence which may have a symmetrical, or null if none
   */
  protected IAttributeValuePresence getDifference() {
    IAttributeValuePresence result = null;
    Collection<IAttributeValuePresence> allDiffs = _match.getAttributeDifferences(_feature);
    // Size is between 0 and 2 since the upper bound is 1 according to method isApplicableTo
    if (!allDiffs.isEmpty()) {
      result = allDiffs.iterator().next();
    }
    return result;
  }
  
  /**
   * Return the kind of the overall difference according to Differencer
   * @return a positive int
   */
  protected int getDifferencerKind() {
    return _diffNode.isThreeWay()? Differencer.CONFLICTING: Differencer.CHANGE;
  }
  
  /**
   * Return the model scope on the given side
   * @param left_p whether the side is left or right
   * @return a non-null model scope
   */
  protected IFeaturedModelScope getScope(boolean left_p) {
    return getScope(_diffNode.getRoleForSide(left_p));
  }
  
  /**
   * Return the model scope that plays the given role
   * @param role_p a non-null role
   * @return a model scope that is non-null except for the ancestor role on a two-way comparison
   */
  protected IFeaturedModelScope getScope(Role role_p) {
    return _diffNode.getActualComparison().getScope(role_p);
  }
  
  /**
   * Return the label of the "switch left and right view" action
   * @return a potentially null string
   */
  protected String getSwitchSidesActionLabel() {
    String result = __switchSidesActionLabel;
    if (result == null) {
      final String BUNDLE_NAME=
          "org.eclipse.compare.contentmergeviewer.TextMergeViewerResources"; //$NON-NLS-1$
      ResourceBundle bundle = null;
      try {
        bundle = ResourceBundle.getBundle(BUNDLE_NAME);
      } catch (MissingResourceException e) {
        // Proceed
      }
      if (bundle != null) {
        final String KEY = "action.SwitchLeftAndRight.label"; //$NON-NLS-1$
        result = bundle.getString(KEY);
      }
      __switchSidesActionLabel = (result != null)? result:
        "FAILED_TO_COMPUTE_SWITCH_SIDES_ACTION_LABEL"; //$NON-NLS-1$
    }
    return result;
  }
  
  /**
   * Return the value for merge on the side of the given role
   * @param role_p a non-null role
   * @return a value which is non-null except for the ancestor role on a two-way comparison
   */
  protected String getValue(Role role_p) {
    String result = null;
    IFeaturedModelScope scope = getScope(role_p);
    if (scope != null) {
      result = ""; // Default //$NON-NLS-1$
      EObject element = _match.get(role_p);
      if (element != null) {
        List<Object> rawValue = scope.get(element, _feature); // Current value in case of previous text merge
        if (rawValue.size() == 1) { // Value is present
          result = (String)rawValue.get(0);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the viewer input
   * @return an object which is non-null if and only if the confirmation button has been pressed
   */
  public TextDiffNode getViewerInput() {
    return _viewerInput;
  }
  
  /**
   * Return whether text merge is applicable to the given structural feature
   * @param feature_p a potentially null feature
   */
  public static boolean isApplicableTo(EStructuralFeature feature_p) {
    return feature_p instanceof EAttribute &&
        feature_p.getUpperBound() == 1 &&
        String.class.equals(feature_p.getEType().getInstanceClass());
  }
  
  /**
   * Return whether the given side is editable
   * @param left_p whether the side is left
   */
  protected boolean isEditable(boolean left_p) {
    // Forbid double edition: editable only if this side is the only editable one
    return !_match.isPartial() && _diffNode.isEditable(left_p) && !_diffNode.isEditable(!left_p);
  }
  
  /**
   * Return whether the given contribution item refers to the "switch left and right view" action
   * @param item_p a non-null contribution item
   */
  protected boolean isSwitchSidesAction(IContributionItem item_p) {
    boolean result = false;
    // The contribution item has no ID so we fall back to using the action label
    // as a criterion
    if (item_p instanceof ActionContributionItem) {
      ActionContributionItem actionItem = (ActionContributionItem)item_p;
      String itemLabel = actionItem.getAction().getText();
      if (itemLabel != null) {
        result = itemLabel.equals(getSwitchSidesActionLabel());
      }
    }
    return result;
  }
  
}
