/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers;

import static org.eclipse.emf.diffmerge.ui.viewers.CategoryViewer.CategoryState.FILTERED;
import static org.eclipse.emf.diffmerge.ui.viewers.CategoryViewer.CategoryState.FOCUSED;
import static org.eclipse.emf.diffmerge.ui.viewers.CategoryViewer.CategoryState.NORMAL;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * A viewer for setting up difference categories.
 * Input: CategoryViewer.Input.
 * @author Olivier Constant
 */
public class CategoryViewer extends Viewer {
  
  /**
   * The UI states of difference categories.
   */
  public static enum CategoryState {
    /** The 'normal', i.e. non-active, state */
    NORMAL,
    /** The 'active and filtering' state */
    FILTERED,
    /** The 'active and focusing' state */
    FOCUSED
  }
  
  /** The non-null main sub-viewer */
  protected TreeViewer _viewer;
  
  /** The current input (initially null) */
  private Input _input;
  
  /** A non-null listener for configuration changes */
  private final IPropertyChangeListener _configUpdater;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public CategoryViewer(Composite parent_p) {
    _input = null;
    _configUpdater = new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        if (Input.PROPERTY_CONFIGURATION.equals(event_p.getProperty()))
          refresh();
      }
    };
    createControls(parent_p);
  }
  
  /**
   * Create the controls for this viewer and return the main control
   * @param parent_p a non-null composite
   */
  protected void createControls(Composite parent_p) {
    // Wrapper composite, required for TreeColumnLayout
    Composite wrapper = new Composite(parent_p, SWT.NONE);
    GridData wrapperData = new GridData(SWT.FILL, SWT.FILL, true, true);
    wrapper.setLayoutData(wrapperData);
    TreeColumnLayout layout = new TreeColumnLayout();
    wrapper.setLayout(layout);
    // Main
    _viewer = new TreeViewer(wrapper, SWT.SINGLE | SWT.BORDER | SWT.NO_SCROLL | SWT.V_SCROLL |
        SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
    _viewer.getTree().setHeaderVisible(true);
    _viewer.getTree().setLinesVisible(true);
    _viewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
    // Column 1: Hierarchy
    TreeViewerColumn catItemColumn = new TreeViewerColumn(_viewer, SWT.LEFT);
    catItemColumn.getColumn().setText(Messages.CategoryViewer_CategoryHeader);
    catItemColumn.setLabelProvider(new HierarchyLabelProvider());
    // Column 2: Normal
    TreeViewerColumn normalStateColumn = new TreeViewerColumn(_viewer, SWT.CENTER);
    normalStateColumn.getColumn().setText(Messages.CategoryViewer_NormalStateHeader);
    normalStateColumn.getColumn().setToolTipText(
        Messages.CategoryViewer_NormalStateTooltip);
    normalStateColumn.setLabelProvider(new StateLabelProvider(NORMAL));
    normalStateColumn.setEditingSupport(new StateEditingSupport(_viewer, NORMAL));
    normalStateColumn.getColumn().addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        Input input = getInput();
        if (input != null)
          input.setAll(NORMAL);
      }
    });
    // Column 3: Filtered
    TreeViewerColumn filteredStateColumn = new TreeViewerColumn(_viewer, SWT.CENTER);
    filteredStateColumn.getColumn().setText(Messages.CategoryViewer_FilteredStateHeader);
    filteredStateColumn.getColumn().setToolTipText(
        Messages.CategoryViewer_FilteredStateTooltip);
       filteredStateColumn.setLabelProvider(new StateLabelProvider(FILTERED));
    filteredStateColumn.setEditingSupport(new StateEditingSupport(_viewer, FILTERED));
    filteredStateColumn.getColumn().addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        Input input = getInput();
        if (input != null)
          input.setAll(FILTERED);
      }
    });
    // Column 4: Focused
    TreeViewerColumn focusedStateColumn = new TreeViewerColumn(_viewer, SWT.CENTER);
    focusedStateColumn.getColumn().setText(Messages.CategoryViewer_FocusedStateHeader);
    focusedStateColumn.getColumn().setToolTipText(
        Messages.CategoryViewer_FocusedStateTooltip);
    focusedStateColumn.setLabelProvider(new StateLabelProvider(FOCUSED));
    focusedStateColumn.setEditingSupport(new StateEditingSupport(_viewer, FOCUSED));
    focusedStateColumn.getColumn().addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        Input input = getInput();
        if (input != null)
          input.setAll(FOCUSED);
      }
    });
    // Overall
    layout.setColumnData(catItemColumn.getColumn(), new ColumnWeightData(1, 300, true));
    final int STATE_COLUMN_WIDTH = 70;
    layout.setColumnData(
        normalStateColumn.getColumn(), new ColumnWeightData(0, STATE_COLUMN_WIDTH, false));
    layout.setColumnData(
        filteredStateColumn.getColumn(), new ColumnWeightData(0, STATE_COLUMN_WIDTH, false));
    layout.setColumnData(
        focusedStateColumn.getColumn(), new ColumnWeightData(0, STATE_COLUMN_WIDTH, false));
    ColumnViewerToolTipSupport.enableFor(_viewer);
    _viewer.setContentProvider(new ContentProvider());
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getControl()
   */
  @Override
  public Control getControl() {
    return _viewer.getControl();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getInput()
   */
  @Override
  public Input getInput() {
    return _input;
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#getSelection()
   */
  @Override
  public ISelection getSelection() {
    return _viewer.getSelection();
  }
  
  /**
   * Return the shell of this viewer
   * @return a non-null shell
   */
  protected Shell getShell() {
    return getControl().getShell();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    if (oldInput_p instanceof Input)
      ((Input)oldInput_p).removePropertyChangeListener(_configUpdater);
    if (input_p instanceof Input)
      ((Input)input_p).addPropertyChangeListener(_configUpdater);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _viewer.refresh(true);
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    if (input_p instanceof Input) {
      Object oldInput = getInput();
      _input = (Input)input_p;
      inputChanged(_input, oldInput);
      _viewer.setInput(input_p);
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean)
   */
  @Override
  public void setSelection(ISelection selection_p, boolean reveal_p) {
    _viewer.setSelection(selection_p, reveal_p);
  }
  
  
  /**
   * The input for the viewer.
   */
  public static class Input implements IPropertyChangeNotifier {
    /** The name of the "has changes" property */
    public static final String PROPERTY_HAS_CHANGES = "PROPERTY_CATEGORIES_HAS_CHANGES"; //$NON-NLS-1$
    /** The name of the "configuration" property */
    public static final String PROPERTY_CONFIGURATION = "PROPERTY_CATEGORIES_CONFIGURATION_CHANGES"; //$NON-NLS-1$
    /** The non-null wrapped node */
    private final EMFDiffNode _node;
    /** The non-null set of property change listeners */
    private final Set<IPropertyChangeListener> _changeListeners;
    /** The non-null set of modified categories along with their new state */
    private final Map<IDifferenceCategory, CategoryState> _changedCategories;
    /**
     * Constructor
     * @param node_p a non-null node
     */
    public Input(EMFDiffNode node_p) {
      _node = node_p;
      _changedCategories = new HashMap<IDifferenceCategory, CategoryViewer.CategoryState>();
      _changeListeners = new HashSet<IPropertyChangeListener>(1);
    }
    /**
     * Register the given state for the given category as a change
     * @param category_p a non-null category
     * @param state_p a non-null state
     * @return whether this 'change' had any impact
     */
    public boolean addChange(IDifferenceCategory category_p, CategoryState state_p) {
      boolean hadChanges = hasChanges();
      boolean result = addSilentChange(category_p, state_p);
      boolean hasChanges = hasChanges();
      if (hasChanges != hadChanges) {
        // Lost its last change or gained its first change
        firePropertyChangeEvent(PROPERTY_HAS_CHANGES, Boolean.valueOf(hasChanges));
      }
      return result;
    }
    /**
     * Register the given state for the given category as a change without
     * notifying listeners
     * @param category_p a non-null category
     * @param state_p a non-null state
     * @return whether this 'change' had any impact
     */
    protected boolean addSilentChange(IDifferenceCategory category_p, CategoryState state_p) {
      boolean result;
      CategoryState currentState = getActualState(category_p);
      if (currentState == state_p) {
        // Re-set state to the original one
        CategoryState previousChange = _changedCategories.remove(category_p);
        result = previousChange != null;
      } else {
        // Actual change
        CategoryState previousState = _changedCategories.put(category_p, state_p);
        result = previousState != state_p;
      }
      return result;
    }
    /**
     * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
     */
    public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
      _changeListeners.add(listener_p);
    }
    /**
     * Apply the current changes
     */
    public void applyChanges() {
      if (hasChanges()) {
        for (Map.Entry<IDifferenceCategory, CategoryState> change :
            _changedCategories.entrySet()) {
          applyChange(change.getKey(), change.getValue());
        }
        _changedCategories.clear();
        _node.updateDifferenceNumbers();
        firePropertyChangeEvent(PROPERTY_HAS_CHANGES, Boolean.FALSE);
      }
    }
    /**
     * Set the given category to the given state
     * @param category_p a non-null category
     * @param state_p a non-null state
     */
    protected void applyChange(IDifferenceCategory category_p, CategoryState state_p) {
      switch (state_p) {
      case FILTERED:
        category_p.setInFocusMode(false);
        category_p.setActive(true);
        break;
      case FOCUSED:
        category_p.setInFocusMode(true);
        category_p.setActive(true);
        break;
      default: //NORMAL
        category_p.setActive(false);
      }
    }
    /**
     * Notify listeners of a property change event
     * @param propertyName_p the non-null name of the property
     * @param newValue_p the potentially null, new value of the property
     */
    protected void firePropertyChangeEvent(String propertyName_p, Object newValue_p) {
      PropertyChangeEvent event = new PropertyChangeEvent(
          this, propertyName_p, null, newValue_p);
      for (IPropertyChangeListener listener : _changeListeners) {
        listener.propertyChange(event);
      }
    }
    /**
     * Return the actual state of the given category, ignoring current changes
     * @param category_p a non-null difference category
     * @return a non-null object
     */
    protected CategoryState getActualState(IDifferenceCategory category_p) {
      return !category_p.isActive()? NORMAL: category_p.isInFocusMode()? FOCUSED: FILTERED;
    }
    /**
     * Return the node wrapped by this input
     * @return a non-null object
     */
    public EMFDiffNode getNode() {
      return _node;
    }
    /**
     * Return the state of the given category, taking into account current changes
     * @param category_p a non-null category
     * @return a non-null state
     */
    public CategoryState getStateWithChanges(IDifferenceCategory category_p) {
      CategoryState result = _changedCategories.get(category_p);
      if (result == null)
        result = getActualState(category_p);
      return result;
    }
    /**
     * Return whether there are non-applied changes
     */
    public boolean hasChanges() {
      return !_changedCategories.isEmpty();
    }
    /**
     * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
     */
    public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
      _changeListeners.remove(listener_p);
    }
    /**
     * Remove all listeners
     */
    public void removePropertyChangeListeners() {
      _changeListeners.clear();
    }
    /**
     * Reset the states of the current categories to match the default ones
     */
    public void resetToDefault() {
      boolean hadChanges = hasChanges();
      boolean hadImpact = false;
      CategoryManager manager = _node.getCategoryManager();
      Collection<IDifferenceCategory> defaultConfig = manager.getDefaultConfiguration();
      for (IDifferenceCategory defaultCat : defaultConfig) {
        String id = defaultCat.getID();
        IDifferenceCategory actualCat = manager.getCategory(id);
        if (actualCat != null) {
          CategoryState defaultState = getActualState(defaultCat);
          boolean hasImpact = addSilentChange(actualCat, defaultState);
          hadImpact = hadImpact || hasImpact;
        }
      }
      boolean hasChanges = hasChanges();
      if (hasChanges != hadChanges) {
        // Lost its last change or gained its first change
        firePropertyChangeEvent(PROPERTY_HAS_CHANGES, Boolean.valueOf(hasChanges));
      }
      if (hadImpact)
        firePropertyChangeEvent(PROPERTY_CONFIGURATION, null);
    }
    /**
     * Set the state of all categories to the given one, when possible
     * @param state_p a non-null state
     */
    public void setAll(CategoryState state_p) {
      boolean hadImpact = false;
      boolean hadChanges = hasChanges();
      for (IDifferenceCategory cat : getNode().getCategoryManager().getCategories()) {
        if (cat.isVisible() && cat.isModifiable()) {
          boolean hasImpact = addSilentChange(cat, state_p);
          hadImpact = hadImpact || hasImpact;
        }
      }
      boolean hasChanges = hasChanges();
      if (hasChanges != hadChanges) {
        // Lost its last change or gained its first change
        firePropertyChangeEvent(PROPERTY_HAS_CHANGES, Boolean.valueOf(hasChanges));
      }
      if (hadImpact)
        firePropertyChangeEvent(PROPERTY_CONFIGURATION, null);
    }
  }
  
  /**
   * The content provider for the viewer.
   */
  protected static class ContentProvider implements ITreeContentProvider {
    /** The current input */
    private Input _input = null;
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      _input = null;
    }
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement_p) {
      Object[] result;
      if (parentElement_p instanceof IDifferenceCategorySet) {
        IDifferenceCategorySet catSet = (IDifferenceCategorySet)parentElement_p;
        List<IDifferenceCategoryItem> listResult;
        if (_input == null)
          listResult = catSet.getChildren();
        else
          listResult = _input.getNode().getCategoryManager().getUIChildrenItems(catSet);
        result = listResult.toArray();
      } else {
        result = new Object[0];
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      EMFDiffNode node = ((Input)inputElement_p).getNode();
      return node.getCategoryManager().getUIRootItems().toArray();
    }
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element_p) {
      Object result = null;
      if (element_p instanceof IDifferenceCategoryItem)
        result = ((IDifferenceCategoryItem)element_p).getParent();
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element_p) {
      return getChildren(element_p).length > 0;
    }
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p,
        Object newInput_p) {
      if (newInput_p instanceof Input)
        _input = (Input)newInput_p;
    }
  }
  
  
  /**
   * The label provider for the hierarchical column.
   */
  protected class HierarchyLabelProvider extends ColumnLabelProvider {
    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      Image result = null;
      if (element_p instanceof IDifferenceCategoryItem) {
        IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
        EMFDiffNode node = CategoryViewer.this.getInput().getNode();
        result = catItem.getImage(node);
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      String result;
      if (element_p instanceof IDifferenceCategoryItem) {
        IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
        EMFDiffNode node = CategoryViewer.this.getInput().getNode();
        result = catItem.getText(node);
      } else {
        result = super.getText(element_p);
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      String result = null;
      if (element_p instanceof IDifferenceCategoryItem) {
        IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
        EMFDiffNode node = CategoryViewer.this.getInput().getNode();
        result = catItem.getDescription(node);
      }
      return result;
    }
  }
  
  /**
   * The label provider for the state columns.
   */
  protected class StateLabelProvider extends ColumnLabelProvider {
    /** The non-null state being handled */
    protected final CategoryState _state;
    /**
     * Constructor
     * @param state_p the non-null state to handle
     */
    public StateLabelProvider(CategoryState state_p) {
      _state = state_p;
    }
    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      Image result = null;
      if (element_p instanceof IDifferenceCategory) {
        IDifferenceCategory cat = (IDifferenceCategory)element_p;
        boolean selected = isSelected(cat);
        ImageID imageId;
        if (cat.isModifiable())
          imageId = selected? ImageID.CHECKED: ImageID.UNCHECKED;
        else
          imageId = selected? ImageID.CHECKED_DISABLED: ImageID.UNCHECKED_DISABLED;
        result = EMFDiffMergeUIPlugin.getDefault().getImage(imageId);
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      return null;
    }
    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      String result;
      switch (_state) {
      case FILTERED:
        result = Messages.CategoryViewer_FilteredStateTooltip;
        break;
      case FOCUSED:
        result = Messages.CategoryViewer_FocusedStateTooltip;
        break;
      default: //NORMAL
        result = Messages.CategoryViewer_NormalStateTooltip;
      }
      return result;
    }
    /**
     * Specify whether the given category is in the state represented by this label provider
     * @param category_p a non-null difference category
     */
    protected boolean isSelected(IDifferenceCategory category_p) {
      return _state == CategoryViewer.this.getInput().getStateWithChanges(category_p);
    }
  }
  
  /**
   * Editing support for the state cells.
   */
  protected class StateEditingSupport extends EditingSupport {
    /** The non-null state being handled */
    protected final CategoryState _state;
    /**
     * Constructor
     * @param viewer_p the non-null column viewer this editing support is for
     * @param state_p the non-null state to handle
     */
    public StateEditingSupport(ColumnViewer viewer_p, CategoryState state_p) {
      super(viewer_p);
      _state = state_p;
    }
    /**
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(Object element_p) {
      return new CellEditor() {
        // Inspired by EGit's ClickableCellEditor
        /**
         * @see org.eclipse.jface.viewers.CellEditor#activate
         */
        @Override
        public void activate() {
          // Trigger setValue on editing support
          fireApplyEditorValue();
        }
        /**
         * @see org.eclipse.jface.viewers.CellEditor#activate(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
         */
        @Override
        public void activate(ColumnViewerEditorActivationEvent activationEvent_p) {
          if (activationEvent_p.eventType != ColumnViewerEditorActivationEvent.TRAVERSAL)
            // All mouse, key and programmatic events, excluding mouse traversal events
            super.activate(activationEvent_p);
        }
        /**
         * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
         */
        @Override
        protected Control createControl(Composite parent_p) {
          return null;
        }
        /**
         * @see org.eclipse.jface.viewers.CellEditor#doGetValue()
         */
        @Override
        protected Object doGetValue() {
          return null;
        }
        /**
         * @see org.eclipse.jface.viewers.CellEditor#doSetFocus()
         */
        @Override
        protected void doSetFocus() {
          // Nothing needed
        }
        /**
         * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
         */
        @Override
        protected void doSetValue(Object value_p) {
          // Nothing needed
        }
      };
    }
    /**
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element_p) {
      boolean result = false;
      if (element_p instanceof IDifferenceCategory) {
        IDifferenceCategory cat = (IDifferenceCategory)element_p;
        result = cat.isModifiable();
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element_p) {
      return null;
    }
    /**
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element_p, Object value_p) {
      if (element_p instanceof IDifferenceCategory) {
        IDifferenceCategory cat = (IDifferenceCategory)element_p;
        selectState(cat);
        getViewer().update(cat, null);
      }
    }
    /**
     * Set the given category in the state handled by this editing support
     * @param category_p a non-null difference category
     */
    protected void selectState(IDifferenceCategory category_p) {
      if (category_p.isModifiable())
        CategoryViewer.this.getInput().addChange(category_p, _state);
    }
  }
  
}
