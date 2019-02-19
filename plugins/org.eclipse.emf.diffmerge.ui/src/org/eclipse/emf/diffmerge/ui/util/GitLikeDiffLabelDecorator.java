/*********************************************************************
 * Copyright (c) 2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.util;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;


/**
 * A DiffLabelDecorator that uses decorations inspired by the EGit Eclipse project.
 * @author Olivier Constant
 */
public class GitLikeDiffLabelDecorator extends DiffLabelDecorator {
  
  /** The instance of this class (singleton pattern) */
  private static GitLikeDiffLabelDecorator __instance = null;
  
  
  /**
   * Default constructor
   */
  public GitLikeDiffLabelDecorator() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffLabelDecorator#getFont(java.lang.Object, org.eclipse.swt.graphics.Font, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Font getFont(Object object_p, Font base_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    Font result = super.getFont(object_p, base_p, diffKind_p, side_p, node_p);
    if (result != null && diffKind_p == DifferenceKind.COUNTED) {
      result = UIUtil.getItalic(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffLabelDecorator#getForeground(java.lang.Object, org.eclipse.swt.graphics.Color, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Color getForeground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    Color result;
    if (!(object_p instanceof IModelScope) &&
        (diffKind_p == DifferenceKind.COUNTED || diffKind_p == DifferenceKind.NONE)) {
      result = UIUtil.getColor(SWT.COLOR_DARK_GRAY);
    } else {
      result = super.getForeground(object_p, base_p, diffKind_p, side_p, node_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffLabelDecorator#getImageOverlay(java.lang.Object, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  protected ImageID getImageOverlay(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    ImageID result = null;
    if (diffKind_p != null) {
      switch (diffKind_p) {
      case MODIFIED:
      case FROM_LEFT:
      case FROM_RIGHT:
      case FROM_BOTH:
        result = ImageID.GIT_DIRTY; break;
      case FROM_LEFT_ADD:
      case FROM_RIGHT_ADD:
        result = ImageID.GIT_ADDED; break;
      case FROM_LEFT_DEL:
      case FROM_RIGHT_DEL:
        result = ImageID.GIT_REMOVED; break;
      case CONFLICT:
        result = ImageID.CONFLICT_STAT; break;
      default:
        /* result == null */break;
      }
    }
    return result;
  }
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static GitLikeDiffLabelDecorator getInstance() {
    if (__instance == null)
      __instance = new GitLikeDiffLabelDecorator();
    return __instance;
  }
  
}
