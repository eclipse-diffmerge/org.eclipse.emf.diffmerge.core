/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IPureMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.DifferenceColorKind;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;


/**
 * A viewer which provides a representation of the model tree of a given comparison.
 * Input: EMFDiffNode ; Elements: IMatch.
 * @author Olivier Constant
 */
public class ComparisonTreeViewer extends TreeViewer {
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public ComparisonTreeViewer(Composite parent_p) {
    this(parent_p, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param style_p a style for the tree
   */
  public ComparisonTreeViewer(Composite parent_p, int style_p) {
    super(parent_p, style_p);
    setContentProvider(new ContentProvider());
    setLabelProvider(new LabelProvider());
    ColumnViewerToolTipSupport.enableFor(this);
    getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getContentProvider()
   */
  @Override
  public ITreePathContentProvider getContentProvider() {
    return (ITreePathContentProvider)super.getContentProvider();
  }
  
  /**
   * Return the role that drives the representation of the comparison
   * @return a non-null role
   */
  protected Role getDrivingRole() {
    return getInput().getDrivingRole();
  }
  
  /**
   * @see org.eclipse.jface.viewers.ContentViewer#getInput()
   */
  @Override
  public EMFDiffNode getInput() {
    return (EMFDiffNode)super.getInput();
  }
  
  /**
   * Return the first path whose father is the given one
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path which is not path_p
   */
  protected TreePath getFirstIn(TreePath path_p) {
    TreePath result = null;
    Object[] children = getSortedChildren(path_p);
    if (children.length > 0)
      result = path_p.createChildPath(children[0]);
    return result;
  }
  
  /**
   * Return the last path whose father is the given one
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path which is not path_p
   */
  protected TreePath getLastIn(TreePath path_p) {
    TreePath result = null;
    Object[] children = getSortedChildren(path_p);
    if (children.length > 0) {
      result = path_p;
      // Go down to last child until we reach a leaf
      while (children.length > 0) {
        Object last = children[children.length-1];
        result = result.createChildPath(last);
        children = getSortedChildren(result);
      }
    }
    return result;
  }
  
  /**
   * Return the successor of the given path in depth-first search (graphical order).
   * The successor of the empty path is the first path, if any.
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  protected TreePath getNextOf(TreePath path_p) {
    // Check first child
    TreePath result = getFirstIn(path_p);
    if (result == null && path_p.getSegmentCount() > 0) {
      // Try next sibling, if any
      result = getNextSiblingOf(path_p);
      if (result == null) {
        // Go up until a next sibling is found
        TreePath parentPath = path_p.getParentPath(); // not null since path_p is not empty
        while (result == null && parentPath.getSegmentCount() > 0) {
          result = getNextSiblingOf(parentPath);
          parentPath = parentPath.getParentPath();
        }
      }
    }
    return result;
  }
  
  /**
   * Return the path which is the next sibling to the given one, if any
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  protected TreePath getNextSiblingOf(TreePath path_p) {
    TreePath result = null;
    Object end = path_p.getLastSegment();
    if (end != null) {
      // Check siblings of last segment
      TreePath parentPath = path_p.getParentPath();
      Object[] siblingsArray = getSortedChildren(parentPath);
      List<?> siblings = Arrays.asList(siblingsArray);
      int nextPos = siblings.indexOf(end) + 1;
      if (nextPos < siblings.size())
        // Take next sibling
        result = parentPath.createChildPath(siblings.get(nextPos));
    }
    return result;
  }
  
  /**
   * Return the successor of the given path in depth-first search (graphical order)
   * which has user-level differences, if any
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  public TreePath getNextUserDifference(TreePath path_p) {
    TreePath result = null;
    TreePath next = getNextOf(path_p);
    while (result == null && next != null) {
      if (getInput().getCategoryManager().representAsUserDifference(next))
        result = next;
      else
        next = getNextOf(next);
    }
    return result;
  }
  
  /**
   * Return a list of lists representing the possible paths to the given match.
   * @param match_p a potentially null match
   * @param parentsOnly_p whether the given match must be excluded from the paths
   * @return a non-null list
   */
  protected List<List<IMatch>> getPathsFor(IMatch match_p, boolean parentsOnly_p) {
    List<List<IMatch>> result;
    EMFDiffNode input = getInput();
    if (match_p == null || input == null) {
      result = new ArrayList<List<IMatch>>();
    } else {
      IComparison comparison = input.getActualComparison();
      Role drivingRole = getDrivingRole();
      boolean isRoot = comparison.getContents(drivingRole).contains(match_p) ||
        match_p.getUncoveredRole() == drivingRole &&
        comparison.getContents(drivingRole.opposite()).contains(match_p);
      if (isRoot) {
        result = new ArrayList<List<IMatch>>();
        result.add(new ArrayList<IMatch>());
      } else {
        IMatch drivingContainer = comparison.getContainerOf(match_p, drivingRole);
        result = getPathsFor(drivingContainer, false);
        IMatch oppositeContainer = comparison.getContainerOf(
            match_p, drivingRole.opposite());
        if (oppositeContainer != null && oppositeContainer != drivingContainer) {
          for (List<IMatch> oppositePath : getPathsFor(oppositeContainer, false)) {
            if (!getInput().getCategoryManager().representAsMoveOrigin(UIUtil.toTreePath(oppositePath)))
              result.add(oppositePath);
          }
        }
      }
      if (!parentsOnly_p) {
        for (List<IMatch> path : result) {
          path.add(match_p);
        }
      }
    }
    return result;
  }
  
  /**
   * Return the predecessor of the given path in depth-first search (graphical order).
   * The predecessor of the empty path is the last path, if any.
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  protected TreePath getPreviousOf(TreePath path_p) {
    TreePath result = null;
    if (path_p.getSegmentCount() == 0) {
      // Empty path: take last path
      result = getLastIn(path_p);
    } else {
      TreePath siblingPath = getPreviousSiblingOf(path_p);
      if (siblingPath != null) {
        // Try last path in previous sibling
        result = getLastIn(siblingPath);
        if (result == null)
          // None: take previous sibling
          result = siblingPath;
      } else {
        // No previous sibling: take parent unless empty
        TreePath parentPath = path_p.getParentPath();
        if (parentPath.getSegmentCount() > 0)
          result = parentPath;
      }
    }
    return result;
  }
  
  /**
   * Return the path which is the previous sibling to the given one, if any
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  protected TreePath getPreviousSiblingOf(TreePath path_p) {
    TreePath result = null;
    Object end = path_p.getLastSegment();
    if (end != null) {
      // Check siblings of last segment
      TreePath parentPath = path_p.getParentPath();
      Object[] siblingsArray = getSortedChildren(parentPath);
      List<?> siblings = Arrays.asList(siblingsArray);
      int prevPos = siblings.indexOf(end) - 1;
      if (prevPos >= 0)
        // Take previous sibling
        result = parentPath.createChildPath(siblings.get(prevPos));
    }
    return result;
  }
  
  /**
   * Return the predecessor of the given path in depth-first search (graphical order)
   * which has user-level differences, if any
   * @param path_p a non-null path that belongs to the tree
   * @return a potentially null path
   */
  public TreePath getPreviousUserDifference(TreePath path_p) {
    TreePath result = null;
    TreePath previous = getPreviousOf(path_p);
    while (result == null && previous != null) {
      if (getInput().getCategoryManager().representAsUserDifference(previous))
        result = previous;
      else
        previous = getPreviousOf(previous);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.TreeViewer#getRawChildren(java.lang.Object)
   */
  @Override
  protected Object[] getRawChildren(Object parent_p) {
    Object[] result;
    if (parent_p instanceof TreePath && ((TreePath)parent_p).getSegmentCount() == 0) {
      result = getContentProvider().getElements(getInput());
    } else {
      result = super.getRawChildren(parent_p);
    }
    return result;
  }
  
  /**
   * Return the resource manager for this viewer
   * @return a resource manager which is non-null iff getInput() is not null
   */
  protected ComparisonResourceManager getResourceManager() {
    return getInput() == null? null: getInput().getResourceManager();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTreeViewer#getSelection()
   */
  @Override
  public ITreeSelection getSelection() {
    return (ITreeSelection)super.getSelection();
  }
  
  /**
   * @see org.eclipse.jface.viewers.AbstractTreeViewer#getSortedChildren(java.lang.Object)
   */
  @Override
  public Object[] getSortedChildren(Object parentElementOrTreePath_p) {
    // Increase visibility of this method
    return super.getSortedChildren(parentElementOrTreePath_p);
  }
  
  
  /**
   * The content provider for this viewer.
   */
  protected class ContentProvider implements ITreePathContentProvider {
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
      // Nothing needed
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreePathContentProvider#getChildren(org.eclipse.jface.viewers.TreePath)
     */
    public Object[] getChildren(TreePath parentPath_p) {
      IMatch end = (IMatch)parentPath_p.getLastSegment();
      List<IMatch> result;
      if (end == null)
        result = getInput().getActualComparison().getContents();
      else if (getInput().getCategoryManager().representAsMoveOrigin(parentPath_p))
        result = Collections.emptyList();
      else
        result = getInput().getActualComparison().getContentsOf(end);
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement_p) {
      EMFDiffNode input = (EMFDiffNode)inputElement_p;
      List<IMatch> result = input.getActualComparison().getContents();
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreePathContentProvider#getParents(java.lang.Object)
     */
    public TreePath[] getParents(Object element_p) {
      List<List<IMatch>> resultAsList = getPathsFor((IMatch)element_p, true);
      return UIUtil.toTreePaths(resultAsList);
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreePathContentProvider#hasChildren(org.eclipse.jface.viewers.TreePath)
     */
    public boolean hasChildren(TreePath path_p) {
      return getSortedChildren(path_p).length > 0;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
      // Nothing needed
    }
  }
  
  
  /**
   * The label provider for this viewer.
   */
  protected class LabelProvider extends DelegatingLabelProvider implements ITreePathLabelProvider {
    
    /**
     * Return the element to represent for the given match
     * @param match_p a non-null match
     * @return a non-null element
     */
    private EObject getElementToRepresent(IMatch match_p) {
      EObject result;
      Role drivingRole = getDrivingRole();
      if (match_p.getUncoveredRole() == drivingRole)
        result = match_p.get(drivingRole.opposite());
      else
        result = match_p.get(drivingRole);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    @Override
    public Font getFont(Object element_p) {
      IMatch match = (IMatch)element_p;
      Font result = getControl().getFont();
      if (getInput().getCategoryManager().representAsUserDifference(match))
        result = UIUtil.getBold(result);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(Object element_p) {
      EMatch match = (EMatch)element_p;
      DifferenceKind kind = getInput().getCategoryManager().getDifferenceKind(match);
      DifferenceColorKind colorKind =
        EMFDiffMergeUIPlugin.getDefault().getDifferenceColorKind(kind);
      return getInput().getDifferenceColor(colorKind);
    }
    
    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element_p) {
      IMatch match = (IMatch)element_p;
      Image result = getDelegate().getImage(getElementToRepresent(match));
      if (result != null && getInput().usesCustomIcons()) {
        DifferenceKind kind = getInput().getCategoryManager().getDifferenceKind(match);
        result = getResourceManager().adaptImage(result, kind);
      }
      return result;
    }
    
    /**
     * Return a font for the given tree path
     * @param path_p a non-null path
     * @return a potentially null font
     */
    private Font getPathFont(TreePath path_p) {
      Font result = getControl().getFont();
      Object last = path_p.getLastSegment();
      if (last != null && !getInput().getCategoryManager().representAsMoveOrigin(path_p))
        result = getFont(last);
      return result;
    }
    
    /**
     * Return an image for the given tree path
     * @param path_p a non-null path
     * @return a potentially null image
     */
    private Image getPathImage(TreePath path_p) {
      Image result = null;
      IMatch last = (IMatch)path_p.getLastSegment();
      if (last != null) {
        result = getDelegate().getImage(getElementToRepresent(last));
        if (getInput().getCategoryManager().representAsMoveOrigin(path_p) && result != null)
          result = getResourceManager().getDisabledVersion(result);
        if (result != null && getInput().usesCustomIcons()) {
          DifferenceKind kind = getInput().getCategoryManager().getDifferenceKind(last);
          result = getResourceManager().adaptImage(result, kind);
        }
      }
      return result;
    }
    
    /**
     * Return a label for the given tree path
     * @param path_p a non-null path
     * @return a potentially null string
     */
    private String getPathText(TreePath path_p) {
      String result = null;
      IMatch last = (IMatch)path_p.getLastSegment();
      if (last != null)
        result = getText(last);
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element_p) {
      EMatch match = (EMatch)element_p;
      String result = getDelegate().getText(getElementToRepresent(match));
      if (getInput().usesCustomLabels()) {
        DifferenceKind kind = getInput().getCategoryManager().getDifferenceKind(match);
        String prefix = EMFDiffMergeUIPlugin.getDefault().getDifferencePrefix(kind);
        result = prefix + result;
      }
      int nb = getInput().getCategoryManager().getUIDifferenceNumber(match);
      if (nb > 0)
        result = result + " (" + nb + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      String result = null;
      if (element_p instanceof IPureMatch) {
        Object matchID = ((IPureMatch)element_p).getMatchID();
        if (matchID != null) {
          String matchIDText = matchID.toString();
          result = Messages.ComparisonTreeViewer_MatchIDTooltip + matchIDText;
        }
      }
      return result;
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreePathLabelProvider#updateLabel(org.eclipse.jface.viewers.ViewerLabel, org.eclipse.jface.viewers.TreePath)
     */
    public void updateLabel(ViewerLabel label_p, TreePath elementPath_p) {
      String text = getPathText(elementPath_p);
      label_p.setText(text);
      Object element = elementPath_p.getLastSegment();
      label_p.setImage(getPathImage(elementPath_p));
      label_p.setBackground(getBackground(element));
      label_p.setForeground(getForeground(element));
      label_p.setFont(getPathFont(elementPath_p));
    }
  }
  
}
