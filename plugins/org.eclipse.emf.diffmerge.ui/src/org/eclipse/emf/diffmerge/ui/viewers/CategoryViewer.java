/**
 * <copyright>
 * 
 * Copyright (c) 2016  Thales Global Services S.A.S.
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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;


/**
 * A viewer for setting up difference categories.
 * Input: EMFDiffNode.
 * @author Olivier Constant
 */
public class CategoryViewer extends Viewer {
  
  /** The name of the "active" column */
  protected static final String COLUMN_ACTIVE_LABEL = "On";
  
  /** The index of the "active" column */
  protected static final int COLUMN_ACTIVE_INDEX = 0;
  
  /** The name of the "name" column */
  protected static final String COLUMN_NAME_LABEL = "Category";
  
  /** The index of the "name" column */
  protected static final int COLUMN_NAME_INDEX = 1;
  
  /** The name of the "mode" column */
  protected static final String COLUMN_MODE_LABEL = "Mode";
  
  /** The index of the "active" column */
  protected static final int COLUMN_MODE_INDEX = 2;
  
  /** The column names */
  protected static final String[] COLUMNS =
      new String[] { COLUMN_ACTIVE_LABEL, COLUMN_NAME_LABEL, COLUMN_MODE_LABEL };
  
  /** The current input (initially null) */
  private EMFDiffNode _input;
  
  /** The non-null main sub-viewer */
  protected TreeViewer _viewer;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public CategoryViewer(Composite parent_p) {
    createControls(parent_p);
  }
  
  /**
   * Create and return the main control
   * @param parent_p a non-null composite
   * @return a non-null tree
   */
  protected Tree createMainControl(Composite parent_p) {
    int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
          SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
    Tree result = new Tree(parent_p, style);
    result.setLinesVisible(true);
    result.setHeaderVisible(true);
    result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Column 1: Active state
    TreeColumn column1 = new TreeColumn(result, SWT.CENTER, 0);   
    column1.setText(COLUMN_ACTIVE_LABEL);
    column1.setWidth(30);
    // Column 2: Name
    TreeColumn column2 = new TreeColumn(result, SWT.LEFT, 1);
    column2.setText(COLUMN_NAME_LABEL);
    column2.setWidth(400);
    // Column 3: Mode
    TreeColumn column3 = new TreeColumn(result, SWT.LEFT, 2);
    column3.setText(COLUMN_MODE_LABEL);
    column3.setWidth(70);
    return result;
  }
  
  /**
   * Create and return the main viewer
   * @param mainControl_p a non-null tree
   * @return a non-null viewer
   */
  private TreeViewer createViewer(Tree mainControl_p) {
    TreeViewer result = new TreeViewer(mainControl_p);
    result.setColumnProperties(COLUMNS);
    CellEditor[] editors = new CellEditor[COLUMNS.length];
    // Column 1: Check box editor
    editors[0] = new CheckboxCellEditor(mainControl_p);
    // Column 2: No editor
    editors[1] = null;
    // Column 3: Combo box editor
    editors[2] = new ComboBoxCellEditor(mainControl_p, new String[] {"Exclude", "Focus"}, SWT.READ_ONLY);
    result.setCellEditors(editors);
    result.setCellModifier(new CellModifier());
    return result;
  }
  
  /**
   * Create the controls for this viewer and return the main control
   * @param parent_p a non-null composite
   */
  protected void createControls(Composite parent_p) {
    Tree mainControl = createMainControl(parent_p);
    _viewer = createViewer(mainControl);
    _viewer.setLabelProvider(new LabelProvider());
    _viewer.setContentProvider(new ContentProvider());
    ColumnViewerToolTipSupport.enableFor(_viewer);
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
  public EMFDiffNode getInput() {
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
    refresh();
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#refresh()
   */
  @Override
  public void refresh() {
    _viewer.setInput(getInput());
  }
  
  /**
   * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    if (input_p instanceof EMFDiffNode) {
      Object oldInput = getInput();
      _input = (EMFDiffNode)input_p;
      inputChanged(_input, oldInput);
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
   * The content provider for the viewer.
   */
  protected static class ContentProvider implements ITreeContentProvider {
    /** The current input */
    private EMFDiffNode _input = null;
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
          listResult = _input.getCategoryManager().getUIChildrenItems(catSet);
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
      EMFDiffNode node = (EMFDiffNode)inputElement_p;
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
      if (newInput_p instanceof EMFDiffNode)
        _input = (EMFDiffNode)newInput_p;
    }
  }
  
  /**
   * The label provider for the viewer.
   */
  protected class LabelProvider extends ColumnLabelProvider implements ITableLabelProvider {
    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element_p, int columnIndex_p) {
      Image result;
      IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
      EMFDiffNode node = CategoryViewer.this.getInput();
      switch (columnIndex_p) {
      case COLUMN_ACTIVE_INDEX:
        if (catItem instanceof IDifferenceCategory) {
          ImageID id = ((IDifferenceCategory)catItem).isActive()? ImageID.CHECKED: ImageID.UNCHECKED;
          result = EMFDiffMergeUIPlugin.getDefault().getImage(id);
        } else {
          result = null;
        }
        break;
      case COLUMN_NAME_INDEX:
        result = catItem.getImage(node);
        break;
      default:
        result = null;
        break;
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element_p, int columnIndex_p) {
      String result;
      IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
      EMFDiffNode node = CategoryViewer.this.getInput();
      switch (columnIndex_p) {
      case COLUMN_ACTIVE_INDEX:
        result = ""; //$NON-NLS-1$
        break;
      case COLUMN_NAME_INDEX:
        result = catItem.getText(node);
        break;
      case COLUMN_MODE_INDEX:
        if (catItem instanceof IDifferenceCategory)
          result = ((IDifferenceCategory)catItem).isInFocusMode()? "Focus": "Exclude";
        else
          result = "-"; //$NON-NLS-1$
        break;
      default:
        result = null;
        break;
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
      return catItem.getDescription(CategoryViewer.this.getInput());
    }
  }
  
  /**
   * A modifier for the table cells.
   */
  protected class CellModifier implements ICellModifier {
    /** The combo box value for the "focus" mode */
    protected static final int FOCUS_MODE_VALUE = 1;
    /**
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element_p, String property_p) {
      return Arrays.asList(COLUMN_ACTIVE_LABEL, COLUMN_MODE_LABEL).contains(property_p);
    }
    /**
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element_p, String property_p) {
      Object result;
      IDifferenceCategoryItem catItem = (IDifferenceCategoryItem)element_p;
      if (COLUMN_ACTIVE_LABEL.equals(property_p)) {
        if (catItem instanceof IDifferenceCategory)
          result = Boolean.valueOf(((IDifferenceCategory)catItem).isActive());
        else
          result = Boolean.FALSE;
      } else if (COLUMN_MODE_LABEL.equals(property_p)) {
        if (catItem instanceof IDifferenceCategory) {
          boolean inFocusMode = ((IDifferenceCategory)catItem).isInFocusMode();
          result = inFocusMode? Integer.valueOf(FOCUS_MODE_VALUE): Integer.valueOf(0);
        } else {
          result = Integer.valueOf(0);
        }
      } else {
        result = null;
      }
      return result;
    }
    /**
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element_p, String property_p, Object value_p) {
      Object element = (element_p instanceof Item)? ((Item)element_p).getData(): element_p;
      boolean modified = false;
      if (element instanceof IDifferenceCategory) {
        IDifferenceCategory cat = (IDifferenceCategory)element;
        if (COLUMN_ACTIVE_LABEL.equals(property_p) && value_p instanceof Boolean) {
          boolean newValue = ((Boolean)value_p).booleanValue();
          if (newValue != cat.isActive()) {
            cat.setActive(newValue);
            modified = true;
          }
        } else if (COLUMN_MODE_LABEL.equals(property_p) && value_p instanceof Integer) {
          boolean newValue = ((Integer)value_p).intValue() == FOCUS_MODE_VALUE;
          if (newValue != cat.isInFocusMode()) {
            cat.setInFocusMode(newValue);
            modified = true;
          }
        }
      }
      if (modified)
        _viewer.update(element, new String[] {property_p});
    }
  }
  
}
