/*********************************************************************
 * Copyright (c) 2018-2019 Thales Global Services S.A.S.
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;


/**
 * The default DiffLabelDecorator.
 * It is inspired by Eclipse Team UI for icon decoration and associates a color to each side
 * of the comparison.
 * @author Olivier Constant
 */
public class DefaultDiffLabelDecorator extends DiffLabelDecorator {
  
  /** Identifiers for colors according to the side to which a difference presence is relative */
  @SuppressWarnings("javadoc")
  public enum DifferenceColorKind {
    LEFT, RIGHT, BOTH, NONE,
    CONFLICT, DEFAULT
  }
  
  /** The instance of this class (singleton pattern) */
  private static DefaultDiffLabelDecorator __instance = null;
  
  /** A map from color kind to SWT color code from the SWT class */
  private final Map<DifferenceColorKind, Integer> _differenceColors;
  
  
  /**
   * Default constructor
   */
  public DefaultDiffLabelDecorator() {
    _differenceColors = new HashMap<DifferenceColorKind, Integer>();
    initializeDifferenceColors(_differenceColors);
  }
  
  /**
   * Return the color that corresponds to the given color kind
   * @param colorKind_p a non-null color kind
   * @return a non-null color
   */
  public Color getDifferenceColor(DifferenceColorKind colorKind_p) {
    int colorCode = SWT.COLOR_BLACK;
    Integer colorCodeI = _differenceColors.get(colorKind_p);
    if (colorCodeI != null) {
      colorCode = colorCodeI.intValue();
    }
    return UIUtil.getColor(colorCode);
  }
  
  /**
   * Return the color kind that corresponds to the given difference kind
   * @param originKind_p a potentially null difference kind
   * @return a non-null color kind
   */
  protected DifferenceColorKind getDifferenceColorKind(DifferenceKind originKind_p) {
    DifferenceColorKind result;
    if (originKind_p == null) {
      result = DifferenceColorKind.DEFAULT;
    } else {
      switch (originKind_p) {
        case NONE:
          result = DifferenceColorKind.NONE; break;
        case CONFLICT:
          result = DifferenceColorKind.CONFLICT; break;
        case MODIFIED: case FROM_LEFT: case FROM_RIGHT: case FROM_BOTH:
          result = DifferenceColorKind.BOTH; break;
        case FROM_LEFT_ADD: case FROM_RIGHT_DEL:
          result = DifferenceColorKind.LEFT; break;
        case FROM_RIGHT_ADD: case FROM_LEFT_DEL:
          result = DifferenceColorKind.RIGHT; break;
        case COUNTED:
          result = DifferenceColorKind.NONE; break;
        default:
          result = DifferenceColorKind.DEFAULT; break;
      }
    }
    return result;
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
    if (result != null && diffKind_p != null && !diffKind_p.isNeutral()) {
      result = UIUtil.getBold(result);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffLabelDecorator#getForeground(java.lang.Object, org.eclipse.swt.graphics.Color, org.eclipse.emf.diffmerge.ui.util.DifferenceKind, org.eclipse.emf.diffmerge.generic.api.Role, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public Color getForeground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p) {
    DifferenceColorKind colorKind = DifferenceColorKind.NONE;
    if (side_p != null && node_p != null &&
        (diffKind_p != null && !diffKind_p.isNeutral() ||
        object_p == node_p.getActualComparison().getScope(side_p))) { // Scope color
      boolean isLeftSide = isLeftSide(side_p, node_p);
      colorKind = isLeftSide? DifferenceColorKind.LEFT: DifferenceColorKind.RIGHT;
    } else if (side_p == null) {
      colorKind = getDifferenceColorKind(diffKind_p);
      if (object_p instanceof EStructuralFeature && colorKind == DifferenceColorKind.NONE) {
        colorKind = DifferenceColorKind.DEFAULT;
      }
    }
    Color result = getDifferenceColor(colorKind);
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
      case FROM_LEFT:
        result = ImageID.OUT_STAT; break;
      case FROM_LEFT_ADD:
        result = ImageID.OUT_ADD_STAT; break;
      case FROM_LEFT_DEL:
        result = ImageID.OUT_REM_STAT; break;
      case FROM_RIGHT:
        result = ImageID.INC_STAT; break;
      case FROM_RIGHT_ADD:
        result = ImageID.INC_ADD_STAT; break;
      case FROM_RIGHT_DEL:
        result = ImageID.INC_REM_STAT; break;
      case MODIFIED:
      case FROM_BOTH:
        result = ImageID.MODIFIED_STAT; break;
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
  public static DefaultDiffLabelDecorator getInstance() {
    if (__instance == null)
      __instance = new DefaultDiffLabelDecorator();
    return __instance;
  }
  
  /**
   * Initialize the given map from color kinds to SWT color codes
   * @param differenceColorsMap_p a non-null, modifiable map
   */
  protected void initializeDifferenceColors(
      Map<DifferenceColorKind, Integer> differenceColorsMap_p) {
    differenceColorsMap_p.put(DifferenceColorKind.LEFT, Integer.valueOf(SWT.COLOR_DARK_RED));
    differenceColorsMap_p.put(DifferenceColorKind.RIGHT, Integer.valueOf(SWT.COLOR_BLUE));
    differenceColorsMap_p.put(DifferenceColorKind.BOTH, Integer.valueOf(SWT.COLOR_DARK_MAGENTA));
    differenceColorsMap_p.put(DifferenceColorKind.NONE, Integer.valueOf(SWT.COLOR_DARK_GRAY));
    differenceColorsMap_p.put(DifferenceColorKind.CONFLICT, Integer.valueOf(SWT.COLOR_RED));
    differenceColorsMap_p.put(DifferenceColorKind.DEFAULT, Integer.valueOf(SWT.COLOR_BLACK));
  }
  
  /**
   * Set the color that corresponds to the given color kind
   * @param colorKind_p a non-null color kind
   * @param swtColor_p an identifier of an SWT color from class SWT
   */
  public void setDifferenceColor(DifferenceColorKind colorKind_p, int swtColor_p) {
    _differenceColors.put(colorKind_p, Integer.valueOf(swtColor_p));
  }
  
}
