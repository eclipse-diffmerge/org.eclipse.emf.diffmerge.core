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

import static org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties.*;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;


/**
 * A base implementation of IDiffLabelDecorator that preserves the original
 * representation of objects, thus ignoring diff concerns.
 * Sub-classes must define the parts of the representation that are diff-specific.
 * @author Olivier Constant
 */
public class DiffLabelDecorator implements IDiffLabelDecorator {
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getBackground(java.lang.Object, org.eclipse.swt.graphics.Color, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public Color getBackground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return base_p;
  }
  
  /**
   * @see ILabelProvider#getImage(Object)
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  protected Image getDecoratedImage(Object object_p, Image base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    Image result = base_p;
    if (node_p != null) {
      ImageID overlay = getImageOverlay(object_p, diffKind_p, side_p, node_p);
      result = node_p.getResourceManager().getOverlayVersion(base_p, overlay);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getFont(java.lang.Object, org.eclipse.swt.graphics.Font, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public Font getFont(Object object_p, Font base_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    return base_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getForeground(java.lang.Object, org.eclipse.swt.graphics.Color, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public Color getForeground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return base_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getImage(java.lang.Object, org.eclipse.swt.graphics.Image, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public Image getImage(Object object_p, Image base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    Image result = base_p;
    if (needsIconDecoration(object_p, result, diffKind_p, side_p, node_p)) {
      result = getDecoratedImage(object_p, result, diffKind_p, side_p, node_p);
    }
    return result;
  }
  
  /**
   * Return the image ID that reflects the diff status of the given object
   * in the given context
   * @param object_p the non-null object to represent
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null image ID
   */
  protected ImageID getImageOverlay(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    return null;
  }
  
  /**
   * Return the difference number that corresponds to the given object in the given context
   * @param object_p the non-null object to represent
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a non-null string
   */
  protected int getLabelDifferenceNumber(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    int result = 0;
    if (node_p != null && object_p instanceof IMatch) {
      result = node_p.getCategoryManager().getUIDifferenceNumber((IMatch)object_p);
    }
    return result;
  }
  
  /**
   * Return the prefix that corresponds to the given object in the given context
   * @param object_p the non-null object to represent
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a non-null string
   */
  protected String getLabelPrefix(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    String result = ""; //$NON-NLS-1$
    if (diffKind_p != null) {
      switch (diffKind_p) {
      case FROM_LEFT:
        result = "|> "; break; //$NON-NLS-1$
      case FROM_LEFT_ADD:
        result = "+> "; break; //$NON-NLS-1$
      case FROM_LEFT_DEL:
        result = "-> "; break; //$NON-NLS-1$
      case FROM_RIGHT:
        result = "<| "; break; //$NON-NLS-1$
      case FROM_RIGHT_ADD:
        result = "<+ "; break; //$NON-NLS-1$
      case FROM_RIGHT_DEL:
        result = "<- "; break; //$NON-NLS-1$
      case CONFLICT:
        result = "! "; break; //$NON-NLS-1$
      case MODIFIED:
      case FROM_BOTH:
        result = "| "; break; //$NON-NLS-1$
      default:
        result = ""; break; //$NON-NLS-1$
      }
    }
    return result;
  }
  
  /**
   * Return the styled difference number that corresponds to the given object in the given context
   * @param object_p the non-null object to represent
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a non-null styled string
   */
  protected StyledString getStyledLabelDifferenceNumber(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    int diffNb = getLabelDifferenceNumber(object_p, diffKind_p, side_p, node_p);
    StyledString result;
    if (diffNb > 0) {
      result = new StyledString(
          " (" + String.valueOf(diffNb) + ")", //$NON-NLS-1$ //$NON-NLS-2$
          StyledString.COUNTER_STYLER);
    } else {
      result = new StyledString();
    }
    return result;
  }
  
  /**
   * Return the styled prefix that corresponds to the given object in the given context
   * @param object_p the non-null object to represent
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a non-null styled string
   */
  protected StyledString getStyledLabelPrefix(Object object_p, DifferenceKind diffKind_p,
      Role side_p, EMFDiffNode node_p) {
    String rawPrefix = getLabelPrefix(object_p, diffKind_p, side_p, node_p);
    StyledString result = new StyledString(rawPrefix, StyledString.COUNTER_STYLER);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getText(java.lang.Object, java.lang.CharSequence, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public CharSequence getText(Object object_p, CharSequence base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    CharSequence result = base_p;
    // Label prefix
    if (needsLabelPrefix(object_p, base_p, diffKind_p, side_p, node_p)) {
      result = getStyledLabelPrefix(object_p, diffKind_p, side_p, node_p);
      if (base_p instanceof StyledString) {
        ((StyledString)result).append((StyledString)base_p);
      } else if (result != null) {
        ((StyledString)result).append(base_p.toString());
      }
    }
    // Label postfix (difference number)
    if (needsLabelDifferenceNumber(object_p, base_p, diffKind_p, side_p, node_p)) {
      StyledString postFix = getStyledLabelDifferenceNumber(object_p, diffKind_p, side_p, node_p);
      if (!(result instanceof StyledString)) {
        result = (result == null)? new StyledString(): new StyledString(result.toString());
      }
      ((StyledString)result).append(postFix);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator#getToolTipText(java.lang.Object, java.lang.String, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getToolTipText(Object object_p, String base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return base_p;
  }
  
  /**
   * Return whether the given side is the left side for the given diff node (convenience method)
   * @param side_p a non-null role
   * @param node_p a non-null diff node
   */
  protected boolean isLeftSide(Role side_p, EMFDiffNode node_p) {
    return node_p.getRoleForSide(true) == side_p;
  }
  
  /**
   * Return whether a decorated icon indicating the difference status must be used for
   * the given object in the given context
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   */
  protected boolean needsIconDecoration(Object object_p, Image base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return node_p != null && node_p.isUserPropertyTrue(P_CUSTOM_ICONS) &&
        !(side_p != null && object_p instanceof IMatch &&
        node_p.getCategoryManager().isComparisonPart((IMatch)object_p));
  }
  
  /**
   * Return whether a label postfix indicating the number of differences must be used for
   * the given object in the given context
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   */
  protected boolean needsLabelDifferenceNumber(Object object_p, CharSequence base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return object_p instanceof IMatch && side_p == null &&
        (node_p == null || node_p.isUserPropertyTrue(P_SHOW_DIFFERENCE_NUMBERS));
  }
  
  /**
   * Return whether a label prefix indicating the difference status must be used for
   * the given object in the given context
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   */
  protected boolean needsLabelPrefix(Object object_p, CharSequence base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    return side_p == null && node_p != null &&
        node_p.isUserPropertyTrue(P_CUSTOM_LABELS);
  }
  
}
