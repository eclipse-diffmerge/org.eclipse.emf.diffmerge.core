/**
 * <copyright>
 * 
 * Copyright (c) 2013-2018 Thales Global Services S.A.S.
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.config.IComparisonConfigurator;
import org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy;
import org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.FineGrainedMatchCriterion;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMergePolicy;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;


/**
 * A dialog for configuring a comparison method.
 * @author Olivier Constant
 */
public class ConfigureComparisonDialog extends Dialog {
  
  /** The non-null configuration data */
  protected final ComparisonConfiguration _data;
  
  /** The set of SWT controls that represent advanced configuration areas
   * together with the optionally associated tab items or tab item titles */
  protected final Map<Control, Object> _advancedSettingsAreas;
  
  /** The [intially null then non-null] control that contains advanced configuration controls */
  protected TabFolder _advancedSettingsContainer;
  
  
  /**
   * Constructor
   * @param shell_p a non-null shell
   * @param data_p a non-null data object
   */
  public ConfigureComparisonDialog(Shell shell_p, ComparisonConfiguration data_p) {
    super(shell_p);
    setShellStyle(getShellStyle() | SWT.RESIZE);
    _advancedSettingsAreas = new LinkedHashMap<Control, Object>();
    _data = data_p;
  }
  
  /**
   * Update the GUI so as to reflect the "show advanced settings" flag
   */
  protected void applyShowAdvancedSettings() {
    if (_advancedSettingsContainer != null) {
      boolean toShow = _data.isShowAdvancedSettings();
      for (Map.Entry<Control, Object> config :
          new LinkedList<Map.Entry<Control, Object>>(_advancedSettingsAreas.entrySet())) {
        Control control = config.getKey();
        Object data = config.getValue();
        if (toShow && data instanceof String) {
          TabItem newItem = new TabItem(_advancedSettingsContainer, SWT.NONE);
          newItem.setText((String)data);
          newItem.setControl(control);
          _advancedSettingsAreas.put(control, newItem);
        } else if (!toShow && data instanceof TabItem) {
          String title = ((TabItem)data).getText();
          _advancedSettingsAreas.put(control, title);
          ((TabItem)data).dispose();
        }
      }
    }
  }
  
  /**
   * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void configureShell(Shell newShell_p) {
    super.configureShell(newShell_p);
    newShell_p.setText(Messages.ConfigureComparisonDialog_Title);
  }
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog#create()
   */
  @Override
  public void create() {
    super.create();
    // Here so that bounds have been set for the "show advanced settings" case
    applyShowAdvancedSettings();
  }
  
  /**
   * Create the area dedicated to the configuration
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaConfiguration(Composite parent_p) {
    TabFolder result = new TabFolder(parent_p, SWT.NONE);
    result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    createAreaMain(result);
    createAreaMatching(result);
    createAreaDifferencing(result);
    createAreaMerging(result);
    createAreaMisc(result);
    _advancedSettingsContainer = result;
    return result;
  }
  
  /**
   * Create the area dedicated to the customization of the differencing phase
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaDifferencing(Composite parent_p) {
    if (_data.getDiffPolicy() == null) return null;
    Composite result = createAreaTopic(
        parent_p, Messages.ConfigureComparisonDialog_Differencing, true);
    createOptionIgnoreOrders(result);
    return result;
  }
  
  /**
   * Create the area dedicated to the main configuration options
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaMain(Composite parent_p) {
    Composite result = createAreaTopic(
        parent_p, Messages.ConfigureComparisonDialog_Topic_Main, false);
    // Predefined usages
    createAreaUsages(result);
    // Advanced settings on/off
    createAreaShowAdvancedSettings(result);
    return result;
  }
  
  /**
   * Create the area dedicated to the customization of the matching phase
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaMatching(Composite parent_p) {
    Composite result = null;
    ConfigurableMatchPolicy matchPolicy = _data.getMatchPolicy();
    if (matchPolicy != null) {
      result = createAreaTopic(parent_p, Messages.ConfigureComparisonDialog_Matching, true);
      if (matchPolicy.getVisibleCriteria().size() > 1)
        createLabelWithNote(result, Messages.ConfigureComparisonDialog_MatchingTooltip);
      createAreaMatchingAbsolute(result);
      createAreaMatchingRelative(result);
    }
    return result;
  }
  
  /**
   * Create the area dedicated to the criteria for absolute matching, if applicable
   * @param parent_p a non-null composite
   * @return a optional created composite
   */
  protected Composite createAreaMatchingAbsolute(Composite parent_p) {
    assert _data.getMatchPolicy() != null;
    Composite result = null;
    Collection<MatchCriterionKind> visibleCriteria =
        _data.getMatchPolicy().getVisibleCriteria();
    if (visibleCriteria.contains(MatchCriterionKind.EXTRINSIC_ID) ||
        visibleCriteria.contains(MatchCriterionKind.INTRINSIC_ID)) {
      Group group = new Group(parent_p, SWT.NONE);
      group.setLayoutData(createLayoutData());
      group.setLayout(new GridLayout(1, false));
      group.setText(Messages.ConfigureComparisonDialog_AbsoluteCriteria);
      group.setToolTipText(Messages.ConfigureComparisonDialog_AbsoluteCriteriaTooltip);
      createAreaMatchingCriterion(group, MatchCriterionKind.EXTRINSIC_ID,
          Messages.ConfigureComparisonDialog_EIDCriterion,
          Messages.ConfigureComparisonDialog_EIDCriterionTooltip);
      createAreaMatchingCriterion(group, MatchCriterionKind.INTRINSIC_ID,
          Messages.ConfigureComparisonDialog_IIDCriterion,
          Messages.ConfigureComparisonDialog_IIDCriterionTooltip);
      result = group;
    }
    return result;
  }
  
  /**
   * Create the area dedicated to the given match criterion, if applicable
   * @param parent_p a non-null composite
   * @param criterion_p a non-null criterion
   * @param label_p a non-null text for presenting the criterion
   * @param tooltip_p an optional tooltip text
   * @return a optional created composite
   */
  protected Composite createAreaMatchingCriterion(Composite parent_p,
      final MatchCriterionKind criterion_p, String label_p, String tooltip_p) {
    ConfigurableMatchPolicy matchPolicy = _data.getMatchPolicy();
    assert matchPolicy != null;
    if (!matchPolicy.getVisibleCriteria().contains(criterion_p))
      return null;
    // Criterion
    createOptionMatchingCriterion(parent_p, criterion_p, label_p, tooltip_p);
    // Fine-grained criteria
    Composite result = new Composite(parent_p, SWT.NONE);
    result.setLayoutData(createLayoutData());
    GridLayout layout = new GridLayout(2, false);
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    result.setLayout(layout);
    for (final FineGrainedMatchCriterion fgCriterion :
        matchPolicy.getAvailableFineGrainedCriteria()) {
      if (fgCriterion.getParentCriterion() == criterion_p)
        createOptionMatchingFineGrainedCriterion(result, fgCriterion);
    }
    return result;
  }
  
  /**
   * Create the area dedicated to the criteria for relative matching, if applicable
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaMatchingRelative(Composite parent_p) {
    assert _data.getMatchPolicy() != null;
    Composite result = null;
    Collection<MatchCriterionKind> visibleCriteria =
        _data.getMatchPolicy().getVisibleCriteria();
    if (visibleCriteria.contains(MatchCriterionKind.NAME) ||
        visibleCriteria.contains(MatchCriterionKind.STRUCTURE) ||
        visibleCriteria.contains(MatchCriterionKind.SEMANTICS)) {
      Group group = new Group(parent_p, SWT.NONE);
      group.setLayoutData(createLayoutData());
      group.setLayout(new GridLayout(1, false));
      group.setText(Messages.ConfigureComparisonDialog_RelativeCriteria);
      group.setToolTipText(Messages.ConfigureComparisonDialog_RelativeCriteriaTooltip);
      createAreaMatchingCriterion(group, MatchCriterionKind.NAME,
          Messages.ConfigureComparisonDialog_NameCriterion,
          Messages.ConfigureComparisonDialog_NameCriterionTooltip);
      createAreaMatchingCriterion(group, MatchCriterionKind.STRUCTURE,
          Messages.ConfigureComparisonDialog_StructureCriterion,
          Messages.ConfigureComparisonDialog_StructureCriterionTooltip);
      createAreaMatchingCriterion(group, MatchCriterionKind.SEMANTICS,
          Messages.ConfigureComparisonDialog_SemanticCriteria,
          Messages.ConfigureComparisonDialog_SemanticCriteriaTooltip);
      result = group;
    }
    return result;
  }
  
  /**
   * Create the area dedicated to the customization of the merging phase
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaMerging(Composite parent_p) {
    return null;
  }
  
  /**
   * Create the area dedicated to miscellaneous configuration options
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaMisc(Composite parent_p) {
    Composite result = createAreaTopic(
        parent_p, Messages.ConfigureComparisonDialog_Topic_Misc, true);
    createOptionKeepMatchIDs(result);
    createOptionUseCache(result);
    return result;
  }
  
  /**
   * Create the area dedicated to predefined usages
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Composite createAreaUsages(Composite parent_p) {
    Group result = new Group(parent_p, SWT.NONE);
    result.setLayoutData(createLayoutData());
    result.setText(Messages.ConfigureComparisonDialog_PredefinedUsages);
    result.setLayout(new GridLayout(1, false));
    for (IComparisonConfigurator configurator : _data.getConfigurators()) {
      createOptionUsage(result, configurator);
    }
    return result;
  }
  
  /**
   * Create the area dedicated to the advanced settings on/off switch
   * @param parent_p a non-null composite
   * @return an optional created composite
   */
  protected Control createAreaShowAdvancedSettings(Composite parent_p) {
    final Button switchButton = new Button(parent_p, SWT.PUSH);
    final String labelOn = Messages.ConfigureComparisonDialog_ShowAdvanced;
    final String labelOff = Messages.ConfigureComparisonDialog_HideAdvanced;
    switchButton.setText(_data.isShowAdvancedSettings()? labelOff: labelOn);
    switchButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
    switchButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        boolean toShow = !_data.isShowAdvancedSettings();
        _data.setShowAdvancedSettings(toShow);
        switchButton.setText(toShow? labelOff: labelOn);
        applyShowAdvancedSettings();
      }
    });
    return switchButton;
  }
  
  /**
   * Create and return a new top-level configuration area
   * @param parent_p a non-null composite
   * @param title_p a potentially null string
   * @param advanced_p whether the configuration area is dedicated to advanced settings
   * @return a non-null composite
   */
  protected Composite createAreaTopic(Composite parent_p, final String title_p,
      boolean advanced_p) {
    Composite result;
    if (parent_p instanceof TabFolder) {
      result = new Composite(parent_p, SWT.NONE);
      TabItem tab = new TabItem((TabFolder)parent_p, SWT.NONE);
      tab.setText(title_p);
      tab.setControl(result);
      if (advanced_p)
        _advancedSettingsAreas.put(result, tab);
    } else {
      Group group = new Group(parent_p, SWT.NONE);
      group.setText(title_p);
      result = group;
      if (advanced_p)
        _advancedSettingsAreas.put(result, null);
    }
    result.setLayout(new GridLayout(1, false));
    result.setLayoutData(createLayoutData());
    return result;
  }
  
  /**
   * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {
    Composite result = new Composite(parent_p, SWT.NONE);
    result.setLayout(new GridLayout(1, false));
    result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Label
    Label label = new Label(result, SWT.NONE);
    label.setText(Messages.ConfigureComparisonDialog_Label);
    label.setLayoutData(createLayoutData());
    // Content
    createAreaConfiguration(result);
    return result;
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
   * Create and return default layout data
   * @return non-null layout data
   */
  protected Object createLayoutData() {
    return new GridData(SWT.FILL, SWT.TOP, true, false);
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
   * Create the area dedicated to the "ignore orders" option
   * @param parent_p a non-null composite
   */
  protected void createOptionIgnoreOrders(Composite parent_p) {
    final ConfigurableDiffPolicy policy = _data.getDiffPolicy();
    assert policy != null;
    final Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(createLayoutData());
    checkBox.setText(Messages.ConfigureComparisonDialog_IgnoreOrders);
    checkBox.setSelection(_data.getDiffPolicy().isIgnoreOrders());
    checkBox.setToolTipText(
        Messages.ConfigureComparisonDialog_IgnoreOrdersTooltip);
    // Click
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        policy.setIgnoreOrders(!policy.isIgnoreOrders());
      }
    });
    // Update
    final IConfigurationChangedListener updateListener = new IConfigurationChangedListener() {
      /**
       * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
       */
      public void configurationChanged(IConfigurablePolicy notifier_p, Object property_p) {
        checkBox.setSelection(policy.isIgnoreOrders());
      }
    };
    policy.addConfigurationChangedListener(updateListener);
    // Disposal
    parent_p.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e_p) {
        policy.removeConfigurationChangedListener(updateListener);
      }
    });
  }
  
  /**
   * Create the area dedicated to the "keep match keys" option
   * @param parent_p a non-null composite
   */
  protected void createOptionKeepMatchIDs(Composite parent_p) {
    final Button checkBox = new Button(parent_p, SWT.CHECK);
    checkBox.setLayoutData(createLayoutData());
    checkBox.setText(Messages.ConfigureComparisonDialog_KeepMatchData);
    checkBox.setSelection(_data.isKeepMatchIDs());
    checkBox.setToolTipText(
        Messages.ConfigureComparisonDialog_KeepMatchDataTooltip);
    // Click
    checkBox.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        _data.setKeepMatchIDs(!_data.isKeepMatchIDs());
      }
    });
    final ConfigurableMatchPolicy policy = _data.getMatchPolicy();
    if (policy != null) {
      // Update
      final IConfigurationChangedListener updateListener = new IConfigurationChangedListener() {
        /**
         * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
         */
        public void configurationChanged(IConfigurablePolicy notifier_p, Object property_p) {
          checkBox.setSelection(_data.isKeepMatchIDs());
        }
      };
      policy.addConfigurationChangedListener(updateListener);
      // Disposal
      parent_p.addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          policy.removeConfigurationChangedListener(updateListener);
        }
      });
    }
  }
  
  /**
   * Create the area dedicated to the given match criterion, if applicable
   * @param parent_p a non-null composite
   * @param criterion_p a non-null fine-grained criterion
   * @return a button if created, null otherwise
   */
  protected Button createOptionMatchingCriterion(Composite parent_p,
      final MatchCriterionKind criterion_p, String label_p, String tooltip_p) {
    final ConfigurableMatchPolicy matchPolicy = _data.getMatchPolicy();
    assert matchPolicy != null;
    final Button result = new Button(parent_p, SWT.CHECK);
    result.setLayoutData(createLayoutData());
    result.setText(label_p);
    result.setSelection(matchPolicy.useCriterion(criterion_p));
    result.setToolTipText(tooltip_p);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        matchPolicy.setUseCriterion(criterion_p, result.getSelection());
      }
    });
    // Update
    final IConfigurationChangedListener updateListener = new IConfigurationChangedListener() {
      /**
       * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
       */
      public void configurationChanged(IConfigurablePolicy notifier_p, Object property_p) {
        if (ConfigurableMatchPolicy.PROPERTY_MATCH_CRITERIA == property_p ||
            criterion_p == property_p) {
          // Checked state
          boolean criterionSelected = matchPolicy.useCriterion(criterion_p);
          result.setSelection(criterionSelected);
        }
      }
    };
    matchPolicy.addConfigurationChangedListener(updateListener);
    // Disposal
    parent_p.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e_p) {
        matchPolicy.removeConfigurationChangedListener(updateListener);
      }
    });
    return result;
  }
  
  /**
   * Create the area dedicated to the given fine-grained match criterion, if applicable
   * @param parent_p a non-null composite
   * @param criterion_p a non-null fine-grained criterion
   * @return a button if created, null otherwise
   */
  protected Button createOptionMatchingFineGrainedCriterion(Composite parent_p,
      final FineGrainedMatchCriterion criterion_p) {
    final ConfigurableMatchPolicy matchPolicy = _data.getMatchPolicy();
    assert matchPolicy != null;
    // Indent
    final String INDENT = "  "; //$NON-NLS-1$
    new Label(parent_p, SWT.NONE).setText(INDENT);
    // Check box
    final Button result = new Button(parent_p, SWT.CHECK);
    result.setLayoutData(createLayoutData());
    result.setText(criterion_p.getLabel());
    result.setEnabled(matchPolicy.useCriterion(criterion_p.getParentCriterion()));
    result.setSelection(matchPolicy.useFineGrainedCriterion(criterion_p));
    result.setToolTipText(criterion_p.getDescription());
    // Click
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        matchPolicy.setUseFineGrainedCriterion(
            criterion_p, result.getSelection());
      }
    });
    // Update
    final IConfigurationChangedListener updateListener = new IConfigurationChangedListener() {
      /**
       * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
       */
      public void configurationChanged(IConfigurablePolicy notifier_p, Object property_p) {
        if (ConfigurableMatchPolicy.PROPERTY_MATCH_CRITERIA == property_p ||
            criterion_p.getParentCriterion() == property_p) {
          // Enabled state
          boolean criterionSelected = matchPolicy.useCriterion(criterion_p.getParentCriterion());
          result.setEnabled(criterionSelected);
        } else if (ConfigurableMatchPolicy.PROPERTY_FINE_GRAINED_MATCH_CRITERIA == property_p ||
            criterion_p == property_p) {
          // Checked state
          boolean criterionSelected = matchPolicy.useFineGrainedCriterion(criterion_p);
          result.setSelection(criterionSelected);
        }
      }
    };
    matchPolicy.addConfigurationChangedListener(updateListener);
    // Disposal
    parent_p.addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      public void widgetDisposed(DisposeEvent e_p) {
        matchPolicy.removeConfigurationChangedListener(updateListener);
      }
    });
    return result;
  }
  
  /**
   * Create the controls dedicated to predefined usage represented by the given configurator
   * @param parent_p a non-null composite
   * @param configurator_p a non-null configurator
   */
  protected void createOptionUsage(Composite parent_p,
      final IComparisonConfigurator configurator_p) {
    final Button button = new Button(parent_p, SWT.RADIO);
    button.setText(configurator_p.getLabel());
    button.setToolTipText(configurator_p.getDescription());
    button.setSelection(configurator_p.isCompliant(_data));
    button.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        configurator_p.apply(_data);
      }
    });
    final IConfigurationChangedListener listener = new IConfigurationChangedListener() {
      /**
       * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
       */
      public void configurationChanged(IConfigurablePolicy notifier_p,
          Object property_p) {
        boolean fits = configurator_p.isCompliant(_data);
        button.setSelection(fits);
      }
    };
    // Coverage: match policy
    final ConfigurableMatchPolicy matchPolicy = _data.getMatchPolicy();
    if (matchPolicy != null) {
      matchPolicy.addConfigurationChangedListener(listener);
      button.addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          matchPolicy.removeConfigurationChangedListener(listener);
        }
      });
    }
    // Coverage: diff policy
    final ConfigurableDiffPolicy diffPolicy = _data.getDiffPolicy();
    if (diffPolicy != null) {
      diffPolicy.addConfigurationChangedListener(listener);
      button.addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          diffPolicy.removeConfigurationChangedListener(listener);
        }
      });
    }
    // Coverage: merge policy
    final ConfigurableMergePolicy mergePolicy = _data.getMergePolicy();
    if (mergePolicy != null) {
      mergePolicy.addConfigurationChangedListener(listener);
      button.addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          mergePolicy.removeConfigurationChangedListener(listener);
        }
      });
    }
  }
  
  /**
   * Create the area dedicated to the "use cache" option
   * @param parent_p a non-null composite
   */
  protected void createOptionUseCache(Composite parent_p) {
    final ConfigurableMatchPolicy policy = _data.getMatchPolicy();
    if (policy != null) {
      final Button checkBox = new Button(parent_p, SWT.CHECK);
      checkBox.setLayoutData(createLayoutData());
      checkBox.setText(Messages.ConfigureComparisonDialog_UseCache);
      checkBox.setSelection(policy.useCache());
      checkBox.setToolTipText(Messages.ConfigureComparisonDialog_UseCache_Tooltip);
      // Click
      checkBox.addSelectionListener(new SelectionAdapter() {
        /**
         * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        @Override
        public void widgetSelected(SelectionEvent e_p) {
          policy.setUseCache(!policy.useCache());
        }
      });
      // Update
      final IConfigurationChangedListener updateListener = new IConfigurationChangedListener() {
        /**
         * @see org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy.IConfigurationChangedListener#configurationChanged(org.eclipse.emf.diffmerge.api.config.IConfigurablePolicy, java.lang.Object)
         */
        public void configurationChanged(IConfigurablePolicy notifier_p, Object property_p) {
          checkBox.setSelection(policy.useCache());
        }
      };
      policy.addConfigurationChangedListener(updateListener);
      // Disposal
      parent_p.addDisposeListener(new DisposeListener() {
        /**
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e_p) {
          policy.removeConfigurationChangedListener(updateListener);
        }
      });
    }
  }
  
}