/*********************************************************************
 * Copyright (c) 2022 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.internal.WorkbenchMessages;

/**
 * 
 * @author Erwann Traisnel
 *
 *         ComparisonTreeViewer with a textual filter the textual filter updates
 *         a dynamic textual category filter that filter elements based on their
 *         label
 */
public class ComparisonTreeViewerWithTextualFilter extends Composite {

  /**
   * The filter text widget to be used by this tree. This value may be
   * <code>null</code> if there is no filter widget, or if the controls have not
   * yet been created.
   */
  protected Text filterText;

  /**
   * The Composite on which the filter controls are created. This is used to set
   * the background color of the filter controls to match the surrounding
   * controls.
   */
  protected Composite filterComposite;

  /**
   * The text to initially show in the filter text control.
   */
  protected String initialText = ""; //$NON-NLS-1$

  /**
   * The parent composite of the filtered tree.
   *
   * @since 3.3
   */
  protected Composite parent;

  /**
   * Whether or not to show the filter controls (text and clear button). The
   * default is to show these controls. This can be overridden by providing a
   * setting in the product configuration file. The setting to add to not show
   * these controls is:
   *
   * org.eclipse.ui/SHOW_FILTERED_TEXTS=false
   */
  protected boolean showFilterControls;

  /**
   * @since 3.3
   */
  protected Composite treeComposite;

  /**
   * The dynamic textual filter
   */
  protected TextualCategoryFilter textualCategoryFilter;

  /**
   * The original ComparisonTreeViewer
   */
  ComparisonTreeViewer treeViewer = null;

  /**
   * Constructor
   * 
   * @param parent_p
   *          a non-null composite
   */
  public ComparisonTreeViewerWithTextualFilter(Composite parent_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
  }

  /**
   * Constructor
   * 
   * @param parent_p
   *          a non-null composite
   * @param style_p
   *          a style for the tree
   */
  public ComparisonTreeViewerWithTextualFilter(Composite parent_p,
      int style_p) {
    super(parent_p, style_p);
    this.parent = parent_p;
    init(style_p);
  }

  /**
   * Create the filtered tree.
   *
   * @param treeStyle
   *          the style bits for the <code>Tree</code>
   *
   * @since 3.3
   */
  protected void init(int treeStyle) {
    createControl(parent, treeStyle);
    setInitialText(Messages.CapellaFilteredTree_FILTER_TEXT_PLACEHOLDER);
    setFont(parent.getFont());
    textualCategoryFilter = new TextualCategoryFilter(getViewer());
  }

  /**
   * Create the filtered tree's controls. Subclasses should override.
   *
   * @param p
   *          the parent
   * @param treeStyle
   *          SWT style bits used to create the tree
   */
  protected void createControl(Composite p, int treeStyle) {
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    setLayout(layout);

    if (p.getLayout() instanceof GridLayout) {
      setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    filterComposite = new Composite(this, SWT.NONE);
    GridLayout filterLayout = new GridLayout();
    filterLayout.marginHeight = 0;
    filterLayout.marginWidth = 0;
    filterComposite.setLayout(filterLayout);
    filterComposite.setFont(p.getFont());

    createFilterControls(filterComposite);
    filterComposite
        .setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

    treeComposite = new Composite(this, SWT.NONE);
    GridLayout treeCompositeLayout = new GridLayout();
    treeCompositeLayout.marginHeight = 0;
    treeCompositeLayout.marginWidth = 0;
    treeComposite.setLayout(treeCompositeLayout);
    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    treeComposite.setLayoutData(data);
    createTreeControl(treeComposite, treeStyle);
  }

  /**
   * 
   * @return the encapsulated ComparisonTreeViewer
   */
  protected ComparisonTreeViewer getViewer() {
    return treeViewer;
  }

  /**
   * Create the filter controls. By default, a text and corresponding tool bar
   * button that clears the contents of the text is created. Subclasses may
   * override.
   *
   * @param p
   *          parent <code>Composite</code> of the filter controls
   * @return the <code>Composite</code> that contains the filter controls
   */
  protected Composite createFilterControls(Composite p) {
    createFilterText(p);
    return p;
  }

  /**
   * Creates the filter text and adds listeners. This method calls
   * {@link #doCreateFilterText(Composite)} to create the text control.
   * Subclasses should override {@link #doCreateFilterText(Composite)} instead
   * of overriding this method.
   *
   * @param p
   *          <code>Composite</code> of the filter text
   */
  protected void createFilterText(Composite p) {
    filterText = doCreateFilterText(p);
    filterText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
      @Override
      public void getName(AccessibleEvent e) {
        String filterTextString = filterText.getText();
        if (filterTextString.isEmpty()
            || filterTextString.equals(initialText)) {
          e.result = initialText;
        } else {
          e.result = NLS.bind(
              WorkbenchMessages.FilteredTree_AccessibleListenerFiltered,
              new String[] { filterTextString,
                  String.valueOf(getFilteredItemsCount()) });
        }
      }

      /**
       * Return the number of filtered items
       *
       * @return int
       */
      private int getFilteredItemsCount() {
        int total = 0;
        TreeItem[] items = getViewer().getTree().getItems();
        for (TreeItem item : items) {
          total += itemCount(item);

        }
        return total;
      }

      /**
       * Return the count of treeItem and its children to infinite depth.
       *
       * @param treeItem
       *          : the treeItem to return children count from
       * @return int
       */
      private int itemCount(TreeItem treeItem) {
        int count = 1;
        TreeItem[] children = treeItem.getItems();
        for (TreeItem element : children) {
          count += itemCount(element);

        }
        return count;
      }
    });

    filterText.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        /*
         * Running in an asyncExec because the selectAll() does not appear to
         * work when using mouse to give focus to text.
         */
        Display display = filterText.getDisplay();
        display.asyncExec(() -> {
          if (!filterText.isDisposed()) {
            if (getInitialText().equals(filterText.getText().trim())) {
              filterText.selectAll();
            }
          }
        });
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (filterText.getText().equals(initialText)) {
          setFilterText(""); //$NON-NLS-1$
          textChanged();
        }
      }
    });

    filterText.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDown(MouseEvent e) {
        if (filterText.getText().equals(initialText)) {
          // XXX: We cannot call clearText() due to
          // https://bugs.eclipse.org/bugs/show_bug.cgi?id=260664
          setFilterText(""); //$NON-NLS-1$
          textChanged();
        }
      }
    });

    filterText.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        // on a CR we want to transfer focus to the list
        boolean hasItems = getViewer().getTree().getItemCount() > 0;
        if (hasItems && e.keyCode == SWT.ARROW_DOWN) {
          getViewer().getTree().setFocus();
          return;
        }
      }
    });

    // Add our own "ENTER" listener which will filter the tree based on
    // pattern text
    // Use the KeyListener instead of Traverse in order to support RCPTT test
    filterText.addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
          textChanged();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Nothing to be done here
      }
    });

    GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
    filterText.setLayoutData(gridData);
  }

  /**
   * Creates the text control for entering the filter text. Subclasses may
   * override.
   *
   * @param p
   *          the parent composite
   * @return the text widget
   *
   * @since 3.3
   */
  protected Text doCreateFilterText(Composite p) {
    return new Text(p, SWT.SINGLE | SWT.BORDER | SWT.SEARCH);
  }

  /**
   * Creates and set up the tree and tree viewer. This method calls
   * {@link #doCreateTreeViewer(Composite, int)} to create the tree viewer.
   * Subclasses should override {@link #doCreateTreeViewer(Composite, int)}
   * instead of overriding this method.
   *
   * @param p
   *          parent <code>Composite</code>
   * @param style
   *          SWT style bits used to create the tree
   * @return the tree
   */
  protected Control createTreeControl(Composite p, int style) {
    treeViewer = (ComparisonTreeViewer) doCreateTreeViewer(p, style);
    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    treeViewer.getControl().setLayoutData(data);
    return treeViewer.getControl();
  }

  /**
   * Creates the tree viewer. Subclasses may override.
   *
   * @param p
   *          the parent composite
   * @param style
   *          SWT style bits used to create the tree viewer
   * @return the tree viewer
   *
   * @since 3.3
   */
  protected TreeViewer doCreateTreeViewer(Composite p, int style) {
    return new ComparisonTreeViewer(p, style);
  }

  /**
   * Sets the initialText to be displayed in the filter area
   * 
   * @param text
   *          : the initialText
   */
  public void setInitialText(String text) {
    initialText = text;
    if (filterText != null) {
      filterText.setMessage(text);
      if (filterText.isFocusControl()) {
        setFilterText(initialText);
        textChanged();
      } else {
        getDisplay().asyncExec(() -> {
          if (!filterText.isDisposed() && filterText.isFocusControl()) {
            setFilterText(initialText);
            textChanged();
          }
        });
      }
    } else {
      setFilterText(initialText);
      textChanged();
    }
  }

  /**
   * Get the initial text for the receiver.
   *
   * @return String
   */
  protected String getInitialText() {
    return initialText;
  }

  /**
   * Convenience method to return the text of the filter control. If the text
   * widget is not created, then null is returned.
   *
   * @return String in the text, or null if the text does not exist
   */
  protected String getFilterString() {
    return filterText != null ? filterText.getText() : null;
  }

  /**
   * Update the receiver after the text has changed.
   */
  protected void textChanged() {
    String filterString = getFilterString();
    textualCategoryFilter.textChanged(filterString);
    getViewer().getInput().updateDifferenceNumbers();
  }

  /**
   * Ensures that the TExtualCategoryFilter is properly created once
   * 
   * @param newInput
   *          : the new Input
   * @param oldInput
   *          : the old Input
   */
  public void inputChanged(Object newInput, Object oldInput) {
    if (newInput != oldInput && newInput instanceof EMFDiffNode) {
      EMFDiffNode node = (EMFDiffNode) newInput;
      if (!node.getCategoryManager().getCategories().stream()
          .anyMatch(it -> it instanceof TextualCategoryFilter)) {
        node.getCategoryManager().addCategory(textualCategoryFilter);
        node.getCategoryManager().update();
      }
    }
  }

  /**
   * Set the text in the filter control.
   *
   * @param fText
   *          the text to set.
   */
  protected void setFilterText(String fText) {
    if (this.filterText != null) {
      this.filterText.setText(fText);
      selectAll();
    }
  }

  /**
   * Select all text in the filter text field.
   *
   */
  protected void selectAll() {
    if (filterText != null) {
      filterText.selectAll();
    }
  }

  /**
   * Ensures that all sub widgets are disposed as well
   * 
   * @see org.eclipse.swt.widgets.Widget#dispose()
   */
  @Override
  public void dispose() {
    filterText.dispose();
    filterComposite.dispose();
    treeComposite.dispose();
    treeViewer.getControl().dispose();
    super.dispose();

  }

  /**
   * 
   * @param value
   *          : true if left-to-right or right-to-left When true, the decorators
   *          are changed to git decorators
   */
  public void setDirected(boolean value) {
    treeViewer.setDirected(value);
  }
}
