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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind;
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
   * Create the area dedicated to the "keep match IDs" option
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
    createAbsoluteMatchingArea(group);
    createRelativeMatchingArea(group);
    if (_data.getApplicableCriteria().size() > 1)
      createLabelWithNote(group, Messages.ConfigureComparisonDialog_MatchingTooltip);
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
    Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    checkBox.setText(label_p);
    checkBox.setSelection(_data.useMatchCriterion(criterion_p));
    checkBox.setToolTipText(tooltip_p);
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _data.invertMatchCriterionUse(criterion_p);
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
   * Data for ConfigureComparisonDialog.
   */
  public static class ComparisonMethodConfigurationData {
    /** Whether IDs must be remembered */
    private boolean _keepMatchIDs;
    /** The non-null set of applicable match criteria */
    private final Set<MatchCriterionKind> _applicableCriteria;
    /** The non-null set of match criteria to use */
    private final Set<MatchCriterionKind> _selectedCriteria;
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
      if (matchPolicy instanceof ConfigurableMatchPolicy) {
        // Match policy is configurable
        _keepMatchIDs = matchPolicy.keepMatchIDs();
        _applicableCriteria = Collections.unmodifiableSet(
            new HashSet<ConfigurableMatchPolicy.MatchCriterionKind>(
                ((ConfigurableMatchPolicy)matchPolicy).getApplicableCriteria()));
        _selectedCriteria = new HashSet<ConfigurableMatchPolicy.MatchCriterionKind>();
        for (MatchCriterionKind criterion : _applicableCriteria) {
          if (((ConfigurableMatchPolicy)matchPolicy).useMatchCriterion(criterion))
            _selectedCriteria.add(criterion);
        }
      } else {
        // Match policy is not configurable
        _keepMatchIDs = false;
        _applicableCriteria = Collections.emptySet();
        _selectedCriteria = _applicableCriteria;
      }
      // Diff policy
      ConfigurableDiffPolicy diffPolicy =
        (ConfigurableDiffPolicy)comparisonMethod_p.getDiffPolicy();
      _ignoreOrders = diffPolicy.isIgnoreOrders();
    }
    /**
     * Return the set of applicable match criteria in no particular order
     * @return a non-null, unmodifiable collection
     */
    public Collection<MatchCriterionKind> getApplicableCriteria() {
      return _applicableCriteria;
    }
    /**
     * Invert (enable/disable) the usage of the given match criteria
     * @param criterion_p a non-null match criterion
     */
    public void invertMatchCriterionUse(MatchCriterionKind criterion_p) {
      if (_selectedCriteria.contains(criterion_p))
        _selectedCriteria.remove(criterion_p);
      else
        _selectedCriteria.add(criterion_p);
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
    }
    /**
     * Set whether the given match criterion must be used
     * @param criterion_p a non-null criterion
     * @param use_p whether it must be used
     */
    public void setUseMatchCriterion(MatchCriterionKind criterion_p, boolean use_p) {
      if (use_p)
        _selectedCriteria.add(criterion_p);
      else
        _selectedCriteria.remove(criterion_p);
    }
    /**
     * Return whether the given match criterion is used by this match policy
     * @param criterion_p a non-null criterion
     */
    public boolean useMatchCriterion(MatchCriterionKind criterion_p) {
      return _selectedCriteria.contains(criterion_p);
    }
  }
  
}