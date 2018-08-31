/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
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
   * @param coverOppositeSide_p whether paths in the non-driving side must be covered too
   * @return a non-null list
   */
  protected List<List<IMatch>> getPathsFor(IMatch match_p, boolean parentsOnly_p,
      boolean coverOppositeSide_p) {
    List<List<IMatch>> result;
    EMFDiffNode input = getInput();
    IComparison comparison = (input != null)? input.getActualComparison(): null;
    if (match_p == null || comparison == null) {
      // Cannot be computed: return the empty set
      result = new ArrayList<List<IMatch>>();
    } else {
      // Comparison and match are well-defined
      Role drivingRole = getDrivingRole();
      // Must the element be represented as a root?
      boolean isRoot = comparison.getContents(drivingRole).contains(match_p) ||
        match_p.getUncoveredRole() == drivingRole &&
        comparison.getContents(drivingRole.opposite()).contains(match_p);
      if (isRoot) {
        result = new ArrayList<List<IMatch>>();
        result.add(new ArrayList<IMatch>());
      } else {
        // We only consider paths to containers on the driving side because
        // the children of a move origin are not provided by the content provider
        // in order to prevent infinite recursion cases due to "cyclic" moves
        final boolean coverContainerOppositeSide = false;
        // Get the paths of the driving container
        IMatch drivingContainer = comparison.getContainerOf(match_p, drivingRole);
        result = getPathsFor(drivingContainer, false, coverContainerOppositeSide);
        // Consider the non-driving container if required
        if (coverOppositeSide_p) {
          IMatch oppositeContainer = comparison.getContainerOf(
              match_p, drivingRole.opposite());
          if (oppositeContainer != null && oppositeContainer != drivingContainer) {
            List<List<IMatch>> oppositePaths =
                getPathsFor(oppositeContainer, false, coverContainerOppositeSide);
            CategoryManager categoryManager = getInput().getCategoryManager();
            for (List<IMatch> oppositePath : oppositePaths) {
              if (!categoryManager.representAsMoveOrigin(UIUtil.toTreePath(oppositePath)))
                result.add(oppositePath);
            }
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
      List<IMatch> result = Collections.emptyList();
      EMFDiffNode input = (EMFDiffNode)inputElement_p;
      if (input != null) {
        result = input.getActualComparison().getContents();
      }
      return result.toArray();
    }
    
    /**
     * @see org.eclipse.jface.viewers.ITreePathContentProvider#getParents(java.lang.Object)
     */
    public TreePath[] getParents(Object element_p) {
      List<List<IMatch>> resultAsList = getPathsFor((IMatch)element_p, true, true);
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
  protected class LabelProvider extends DiffDecoratingLabelProvider {
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getDiffNode()
     */
    @Override
    protected EMFDiffNode getDiffNode() {
      return getInput();
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getSide()
     */
    @Override
    protected Role getSide() {
      return null;
    }
    /**
     * @see org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element_p) {
      return getMatchIDText(element_p);
    }
  }
  
}
