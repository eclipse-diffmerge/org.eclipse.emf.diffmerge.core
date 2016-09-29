/**
 * <copyright>
 * 
 * Copyright (c) 2016  Thales Global Services S.A.S and others.
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

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;


/**
 * A Viewer for comparisons with a read-only source side and a resulting model side.
 * Input: EMFDiffNode ; Elements: IMatch | IDifference.
 * @author Olivier Constant
 */
public class DirectedComparisonViewer extends ComparisonViewer {
  
  /** Whether the side of the resulting model is right */
  private final boolean _isLeftToRight;
  
  
  /**
   * Constructor (left-to-right)
   * @param parent_p a non-null composite
   */
  public DirectedComparisonViewer(Composite parent_p) {
    this(parent_p, null);
  }
  
  /**
   * Constructor (left-to-right)
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   */
  public DirectedComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    this(parent_p, actionBars_p, true);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   * @param isLeftToRight_p whether the side of the resulting model is right
   */
  public DirectedComparisonViewer(Composite parent_p, IActionBars actionBars_p,
      boolean isLeftToRight_p) {
    super(parent_p, actionBars_p);
    _isLeftToRight = isLeftToRight_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#acceptContextMenuAdditions(org.eclipse.jface.viewers.Viewer)
   */
  @Override
  protected boolean acceptContextMenuAdditions(Viewer viewer_p) {
    return false;
  }
  
  /**
   * Create and return the "accept" action
   * @return a potentially null action
   */
  protected Action createActionAccept() {
    final Action result = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#getText()
       */
      @Override
      public String getText() {
        return isLeftToRight()? Messages.ComparisonViewer_MergeRightTooltip:
          Messages.ComparisonViewer_MergeLeftTooltip;
      }
      /**
       * @see org.eclipse.jface.action.Action#getImageDescriptor()
       */
      @Override
      public ImageDescriptor getImageDescriptor() {
        return isLeftToRight()?
            EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.CHECKIN_ACTION):
              EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.CHECKOUT_ACTION);
      }
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(!isLeftToRight(), true);
      }
    };
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        boolean leftToRight = isLeftToRight();
        if (!leftToRight && PROPERTY_ACTIVATION_MERGE_TO_LEFT.equals(event_p.getProperty()) ||
            leftToRight && PROPERTY_ACTIVATION_MERGE_TO_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean)
            result.setEnabled(((Boolean)newValue).booleanValue());
        }
      }
    });
    result.setEnabled(false);
    return result;
  }
  
  /**
   * Create and return the "accept deletion" action
   * @return a potentially null action
   */
  protected Action createActionAcceptDeletion() {
    final Action result = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#getText()
       */
      @Override
      public String getText() {
        return isLeftToRight()? Messages.ComparisonViewer_DeleteRightTooltip:
          Messages.ComparisonViewer_DeleteLeftTooltip;
      }
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        merge(!isLeftToRight(), false);
      }
    };
    result.setImageDescriptor(EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.DELETE));
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        boolean leftToRight = isLeftToRight();
        if (!leftToRight && PROPERTY_ACTIVATION_DELETE_LEFT.equals(event_p.getProperty()) ||
            leftToRight && PROPERTY_ACTIVATION_DELETE_RIGHT.equals(event_p.getProperty())) {
          Object newValue = event_p.getNewValue();
          if (newValue instanceof Boolean)
            result.setEnabled(((Boolean)newValue).booleanValue());
        }
      }
    });
    result.setEnabled(false);
    return result;
  }
  
  /**
   * Create and return the "ignore" action
   * @return a potentially null action
   */
  protected Action createActionIgnore() {
    final IgnoreAction result = new IgnoreAction();
    addPropertyChangeListener(new IPropertyChangeListener() {
      /**
       * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
       */
      public void propertyChange(PropertyChangeEvent event_p) {
        Object newValue = event_p.getNewValue();
        if (newValue instanceof Boolean) {
          boolean boolValue = ((Boolean)newValue).booleanValue();
          String property = event_p.getProperty();
          if (PROPERTY_ACTIVATION_IGNORE_LEFT.equals(property)) {
            result.setActiveLeft(boolValue);
          } else if (PROPERTY_ACTIVATION_IGNORE_RIGHT.equals(property)) {
            result.setActiveRight(boolValue);
          }
        }
      }
    });
    return result;
  }
  
  /**
   * Return the role of the resulting model
   * @return TARGET or REFERENCE, or null if and only if the current input is null
   */
  protected Role getResultingModelRole() {
    Role result = null;
    EMFDiffNode input = getInput();
    if (input != null) {
      result = input.getRoleForSide(!isLeftToRight());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#inputChanged(java.lang.Object, java.lang.Object)
   */
  @Override
  protected void inputChanged(Object input_p, Object oldInput_p) {
    if (input_p instanceof EMFDiffNode) {
      EMFDiffNode node = (EMFDiffNode)input_p;
      node.setReferenceRole(node.getRoleForSide(!isLeftToRight()));
    }
    super.inputChanged(input_p, oldInput_p);
  }
  
  /**
   * Return whether the side of the resulting model is right
   */
  public boolean isLeftToRight() {
    return _isLeftToRight;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#populateContextMenu(org.eclipse.jface.action.MenuManager, org.eclipse.jface.viewers.Viewer, org.eclipse.jface.viewers.ISelectionProvider)
   */
  @Override
  protected void populateContextMenu(MenuManager menuManager_p,
      Viewer viewer_p, final ISelectionProvider selectionProvider_p) {
    if (_viewerSynthesisMain != null &&
        viewer_p != _viewerSynthesisMain.getInnerViewer()) return;
    // Action definitions
    final Action acceptAction = createActionAccept();
    final Action acceptDeletionAction = createActionAcceptDeletion();
    final Action ignoreAction = createActionIgnore();
    // Menu population
    menuManager_p.addMenuListener(new IMenuListener() {
      /**
       * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
       */
      public void menuAboutToShow(IMenuManager manager_p) {
        if (acceptAction != null && acceptAction.isEnabled())
          manager_p.add(acceptAction);
        if (acceptDeletionAction != null && acceptDeletionAction.isEnabled())
          manager_p.add(acceptDeletionAction);
        if (ignoreAction != null && ignoreAction.isEnabled())
          manager_p.add(ignoreAction);
      }
    });
  }
  
  
  /**
   * The "ignore" action.
   */
  protected class IgnoreAction extends Action {
    /** Whether the left-hand side ignore is active */
    private boolean _activeLeft = false;
    /** Whether the right-hand side ignore is active */
    private boolean _activeRight = false;
    /**
     * Constructor
     */
    public IgnoreAction() {
      super(Messages.DirectedComparisonViewer_Ignore);
      setImageDescriptor(
          EMFDiffMergeUIPlugin.getDefault().getImageDescriptor(ImageID.CHECKED));
    }
    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
      if (_activeLeft)
        ignore(true);
      if (_activeRight)
        ignore(false);
    }
    /**
     * Set whether the left-hand side ignore is active
     * @param activeLeft_p whether it is active
     */
    public void setActiveLeft(boolean activeLeft_p) {
      _activeLeft = activeLeft_p;
      update();
    }
    /**
     * Set whether the right-hand side ignore is active
     * @param activeRight_p whether it is active
     */
    public void setActiveRight(boolean activeRight_p) {
      _activeRight = activeRight_p;
      update();
    }
    /**
     * Update the state for complete consistency
     */
    protected void update() {
      setEnabled(_activeLeft || _activeRight);
    }
  }
  
}
