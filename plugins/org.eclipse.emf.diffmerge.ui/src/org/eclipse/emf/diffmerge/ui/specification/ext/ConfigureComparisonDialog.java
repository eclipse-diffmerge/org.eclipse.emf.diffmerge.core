/**
 * <copyright>
 * 
 * Copyright (c) 2013-2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.FineGrainedMatchCriterion;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * A dialog for configuring a comparison method.
 * @author Olivier Constant
 */
public class ConfigureComparisonDialog extends MessageDialog {
  
  /** The non-null configuration data */
  protected final ComparisonMethodConfigurationData _data;
  
  /**
   * Constructor
   * @param shell_p a non-null shell
   * @param data_p a non-null data object
   */
  public ConfigureComparisonDialog(Shell shell_p, ConfigureComparisonDialog.ComparisonMethodConfigurationData data_p) {
    super(shell_p, Messages.ConfigureComparisonDialog_Title, null,
        Messages.ConfigureComparisonDialog_Label,
        MessageDialog.NONE,
        new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
    _data = data_p;
  }
  
  /**
   * Create the area dedicated to the criteria for absolute matching, if applicable
   * @param parent_p a non-null composite
   */
  protected void createAbsoluteMatchingArea(Composite parent_p) {
    Collection<MatchCriterionKind> applicableCiteria = _data.getApplicableCriteria();
    if (applicableCiteria.contains(MatchCriterionKind.EXTRINSIC_ID) ||
        applicableCiteria.contains(MatchCriterionKind.INTRINSIC_ID)) {
      Group group = new Group(parent_p, SWT.NONE);
      group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      group.setLayout(new GridLayout(1, false));
      group.setText(Messages.ConfigureComparisonDialog_AbsoluteCriteria);
      group.setToolTipText(Messages.ConfigureComparisonDialog_AbsoluteCriteriaTooltip);
      createMatchingCriterionArea(group, MatchCriterionKind.EXTRINSIC_ID,
          Messages.ConfigureComparisonDialog_EIDCriterion,
          Messages.ConfigureComparisonDialog_EIDCriterionTooltip);
      createMatchingCriterionArea(group, MatchCriterionKind.INTRINSIC_ID,
          Messages.ConfigureComparisonDialog_IIDCriterion,
          Messages.ConfigureComparisonDialog_IIDCriterionTooltip);
    }
  }
  
  /**
   * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createCustomArea(Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.NONE);
    result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    result.setLayout(new GridLayout(1, false));
    createMatchingArea(result);
    createDifferencingArea(result);
    createMergingArea(result);
    return result;
  }
  
  /**
   * Create the area dedicated to the customization of the differencing phase
   * @param parent_p a non-null composite
   */
  protected void createDifferencingArea(Composite parent_p) {
    // Matching group
    Group group = new Group(parent_p, SWT.NONE);
    group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    group.setLayout(new GridLayout(1, false));
    group.setText(Messages.ConfigureComparisonDialog_Differencing);
    createIgnoreOrdersArea(group);
  }
  
  /**
   * Create the area dedicated to the "ignore orders" option
   * @param parent_p a non-null composite
   */
  protected void createIgnoreOrdersArea(Composite parent_p) {
    Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    checkBox.setText(Messages.ConfigureComparisonDialog_IgnoreOrders);
    checkBox.setSelection(_data.isIgnoreOrders());
    checkBox.setToolTipText(
        Messages.ConfigureComparisonDialog_IgnoreOrdersTooltip);
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _data.setIgnoreOrders(!_data.isIgnoreOrders());
      }
    });
  }
  
  /**
   * Create the area dedicated to the "keep match keys" option
   * @param parent_p a non-null composite
   */
  protected void createKeepMatchIDsArea(Composite parent_p) {
    Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    checkBox.setText(Messages.ConfigureComparisonDialog_KeepMatchData);
    checkBox.setSelection(_data.isKeepMatchIDs());
    checkBox.setToolTipText(
        Messages.ConfigureComparisonDialog_KeepMatchDataTooltip);
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _data.setKeepMatchIDs(!_data.isKeepMatchIDs());
      }
    });
  }
  
  /**
   * Create a label displaying the given text, prefixed with a standard
   * "note:" bold text
   * @param parent_p a non-null composite
   * @param text_p a potentially null string
   * @return a non-null object
   */
  protected Control createLabelWithNote(Composite parent_p, String text_p) {
    Composite composite = new Composite(parent_p, SWT.NONE);
    composite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    composite.setLayout(layout);
    createNoteLabel(composite);
    Label label = new Label(composite, SWT.NONE);
    label.setText(text_p);
    return composite;
  }
  
  /**
   * Create the area dedicated to the customization of the matching phase
   * @param parent_p a non-null composite
   */
  protected void createMatchingArea(Composite parent_p) {
    // Matching group
    Group group = new Group(parent_p, SWT.NONE);
    group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    group.setLayout(new GridLayout(1, false));
    group.setText(Messages.ConfigureComparisonDialog_Matching);
    if (_data.getApplicableCriteria().size() > 1)
      createLabelWithNote(group, Messages.ConfigureComparisonDialog_MatchingTooltip);
    createAbsoluteMatchingArea(group);
    createRelativeMatchingArea(group);
    createUseCacheArea(group);
    createKeepMatchIDsArea(group);
  }
  
  /**
   * Create the area dedicated to the given match criterion, if applicable
   * @param parent_p a non-null composite
   * @param criterion_p a non-null criterion
   * @param label_p a non-null text for presenting the criterion
   * @param tooltip_p an optional tooltip text
   */
  protected void createMatchingCriterionArea(Composite parent_p,
      final MatchCriterionKind criterion_p, String label_p, String tooltip_p) {
    if (!_data.getApplicableCriteria().contains(criterion_p))
      return;
    // Main criterion
    final ConfigurableMatchPolicy policy = _data.getConfigurableMatchPolicy();
    Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    checkBox.setText(label_p);
    boolean selectedByDefault = policy.useMatchCriterion(criterion_p);
    checkBox.setSelection(selectedByDefault);
    checkBox.setToolTipText(tooltip_p);
    // Fine-grained criteria
    Composite fgComposite = new Composite(parent_p, SWT.NONE);
    fgComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    fgComposite.setLayout(layout);
    final Collection<Button> fgCheckBoxes = new ArrayList<Button>();
    for (final FineGrainedMatchCriterion fgCriterion : policy.getAvailableFineGrainedCriteria()) {
      if (fgCriterion.getCategory() == criterion_p) {
        // Fine-grained criterion is relevant
        new Label(fgComposite, SWT.NONE).setText("  "); //$NON-NLS-1$
        Button fgCheckBox = new Button(fgComposite, SWT.CHECK);
        fgCheckBoxes.add(fgCheckBox);
        fgCheckBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        fgCheckBox.setText(fgCriterion.getLabel());
        fgCheckBox.setEnabled(selectedByDefault);
        fgCheckBox.setSelection(policy.useFineGrainedMatchCriterion(fgCriterion));
        fgCheckBox.setToolTipText(fgCriterion.getDescription());
        fgCheckBox.addSelectionListener(new SelectionAdapter() {
          /**
           * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
           */
          @Override
          public void widgetSelected(SelectionEvent e_p) {
            _data.changeFineGrainedMatchCriterionUse(fgCriterion);
          }
        });
      }
    }
    // Synchronization
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        boolean selected = _data.changeMatchCriterionUse(criterion_p);
        for (Button fgCheckBox : fgCheckBoxes) {
          fgCheckBox.setEnabled(selected);
        }
      }
    });
  }
  
  /**
   * Create the area dedicated to the customization of the merging phase
   * @param parent_p a non-null composite
   */
  protected void createMergingArea(Composite parent_p) {
    // Nothing
  }
  
  /**
   * Create and return a classical "note:" label as used in Eclipse preferences
   * @param parent_p a non-null composite
   * @return a non-null label
   */
  protected Label createNoteLabel(Composite parent_p) {
    Label result = new Label(parent_p, SWT.NONE);
    result.setText(Messages.ConfigureComparisonDialog_Note);
    result.setFont(UIUtil.getBold(result.getFont()));
    return result;
  }
  
  /**
   * Create the area dedicated to the criteria for relative matching, if applicable
   * @param parent_p a non-null composite
   */
  protected void createRelativeMatchingArea(Composite parent_p) {
    Collection<MatchCriterionKind> applicableCiteria = _data.getApplicableCriteria();
    if (applicableCiteria.contains(MatchCriterionKind.NAME) ||
        applicableCiteria.contains(MatchCriterionKind.STRUCTURE) ||
        applicableCiteria.contains(MatchCriterionKind.SEMANTICS)) {
      Group group = new Group(parent_p, SWT.NONE);
      group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      group.setLayout(new GridLayout(1, false));
      group.setText(Messages.ConfigureComparisonDialog_RelativeCriteria);
      group.setToolTipText(Messages.ConfigureComparisonDialog_RelativeCriteriaTooltip);
      createMatchingCriterionArea(group, MatchCriterionKind.NAME,
          Messages.ConfigureComparisonDialog_NameCriterion,
          Messages.ConfigureComparisonDialog_NameCriterionTooltip);
      createMatchingCriterionArea(group, MatchCriterionKind.STRUCTURE,
          Messages.ConfigureComparisonDialog_StructureCriterion,
          Messages.ConfigureComparisonDialog_StructureCriterionTooltip);
      createMatchingCriterionArea(group, MatchCriterionKind.SEMANTICS,
          Messages.ConfigureComparisonDialog_SemanticCriteria,
          Messages.ConfigureComparisonDialog_SemanticCriteriaTooltip);
    }
  }
  
  /**
   * Create the area dedicated to the "use cache" option
   * @param parent_p a non-null composite
   */
  protected void createUseCacheArea(Composite parent_p) {
    final ConfigurableMatchPolicy policy = _data.getConfigurableMatchPolicy();
    if (policy != null) {
      Button checkBox = new Button(parent_p, SWT.CHECK);
      checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
      checkBox.setText("Use cache");
      checkBox.setSelection(policy.useCache());
      checkBox.setToolTipText("This technical option determines whether a cache is used for matching elements.\nIt may increase performance if relative matching criteria are being used and enough memory is available.\nIf memory is limited, the impact on performance will tend to be negative.");
      checkBox.addSelectionListener(new SelectionAdapter() {
        /**
         * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        @Override
        public void widgetSelected(SelectionEvent e_p) {
          policy.setUseCache(!policy.useCache());
        }
      });
    }
  }
  
  
  /**
   * Data for ConfigureComparisonDialog.
   */
  public static class ComparisonMethodConfigurationData {
    /** Whether IDs must be remembered */
    private boolean _keepMatchIDs;
    /** The optional match policy copy to configure */
    private ConfigurableMatchPolicy _matchPolicyCopy;
    /** Whether orders must be ignored */
    private boolean _ignoreOrders;
    
    /**
     * Constructor
     * @param comparisonMethod_p a non-null configuration method
     */
    public ComparisonMethodConfigurationData(
        ConfigurableComparisonMethod comparisonMethod_p) {
      // Match policy
      IMatchPolicy matchPolicy = comparisonMethod_p.getMatchPolicy();
      if (matchPolicy instanceof DefaultMatchPolicy) {
        _keepMatchIDs = matchPolicy.keepMatchIDs();
      } else {
        _keepMatchIDs = false;
      }
      if (matchPolicy instanceof ConfigurableMatchPolicy) {
        // Match policy is configurable
        try {
          _matchPolicyCopy = ((ConfigurableMatchPolicy)matchPolicy).clone();
        } catch (CloneNotSupportedException e) {
          _matchPolicyCopy = null;
        }
      } else {
        // Match policy is not configurable
        _matchPolicyCopy = null;
      }
      // Diff policy
      ConfigurableDiffPolicy diffPolicy =
        (ConfigurableDiffPolicy)comparisonMethod_p.getDiffPolicy();
      _ignoreOrders = diffPolicy.isIgnoreOrders();
    }
    /**
     * Change (enable/disable) the usage of the given fine-grained match criterion
     * @param criterion_p a non-null fine-grained match criterion
     */
    public boolean changeFineGrainedMatchCriterionUse(FineGrainedMatchCriterion criterion_p) {
      boolean result = false;
      ConfigurableMatchPolicy policy = getConfigurableMatchPolicy();
      if (policy != null) {
        result = !policy.useFineGrainedMatchCriterion(criterion_p);
        policy.setUseFineGrainedMatchCriterion(criterion_p, result);
      }
      return result;
    }
    /**
     * Change (enable/disable) the usage of the given match criterion
     * @param criterion_p a non-null match criterion
     * @return whether the criterion is used in the end
     */
    public boolean changeMatchCriterionUse(MatchCriterionKind criterion_p) {
      boolean result = false;
      ConfigurableMatchPolicy policy = getConfigurableMatchPolicy();
      if (policy != null) {
        result = !policy.useMatchCriterion(criterion_p);
        policy.setUseMatchCriterion(criterion_p, result);
      }
      return result;
    }
    /**
     * Return the set of applicable match criteria in decreasing priority
     * @return a non-null collection
     */
    public Collection<MatchCriterionKind> getApplicableCriteria() {
      Collection<MatchCriterionKind> result = Collections.emptySet();
      ConfigurableMatchPolicy policy = getConfigurableMatchPolicy();
      if (policy != null)
        result = policy.getApplicableCriteria();
      return result;
    }
    /**
     * Return the configurable match policy that is being configured, if any
     * @return a potentially null object
     */
    public ConfigurableMatchPolicy getConfigurableMatchPolicy() {
      return _matchPolicyCopy;
    }
    /**
     * Return whether orders must be ignored
     */
    public boolean isIgnoreOrders() {
      return _ignoreOrders;
    }
    /**
     * Return whether IDs must be remembered
     */
    public boolean isKeepMatchIDs() {
      return _keepMatchIDs;
    }
    /**
     * Set whether orders must be ignored
     */
    public void setIgnoreOrders(boolean ignore_p) {
      _ignoreOrders = ignore_p;
    }
    /**
     * Set whether IDs must be remembered
     */
    public void setKeepMatchIDs(boolean keep_p) {
      _keepMatchIDs = keep_p;
      if (_matchPolicyCopy != null)
        _matchPolicyCopy.setKeepMatchIDs(keep_p);
    }
  }
  
}