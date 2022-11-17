/*********************************************************************
 * Copyright (c) 2018-2022 Thales Global Services S.A.S.
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

import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;

/**
 * A DiffLabelDecorator that uses decorations inspired by the EGit Eclipse project.
 * @author Olivier Constant
 */
public class GitLikeDiffLabelDecorator extends DefaultDiffLabelDecorator {

  /** The instance of this class (singleton pattern) */
  private static GitLikeDiffLabelDecorator __instance = null;
  
  
  /**
   * Default constructor
   */
  public GitLikeDiffLabelDecorator() {
    super();
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
