/**
 * <copyright>
 * 
 * Copyright (c) 2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.util;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;


/**
 * Given a base representation of an object, provide a decorated variant that takes
 * into account the diff status of the object.
 * @author Olivier Constant
 */
public interface IDiffLabelDecorator {
  
  /**
   * @see IColorProvider#getBackground(Object)
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  Color getBackground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
  /**
   * @see IFontProvider#getFont(Object)
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  Font getFont(Object object_p, Font base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
  /**
   * @see IColorProvider#getForeground(Object)
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  Color getForeground(Object object_p, Color base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
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
  Image getImage(Object object_p, Image base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
  /**
   * @see ILabelProvider#getText(Object)
   * @see IStyledLabelProvider#getStyledText(Object)
   * If the returned CharSequence is a StyledString then it can be used as such, otherwise its
   * String representation is expected to be used.
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  CharSequence getText(Object object_p, CharSequence base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
  /**
   * @see IToolTipProvider#getToolTipText(Object)
   * @param object_p the non-null object to represent
   * @param base_p the potentially null base representation element for the object
   * @param diffKind_p the difference kind that applies to the object, nor null if unknown
   *          or not applicable
   * @param side_p the comparison side the object belongs to, or null if unknown or not applicable
   * @param node_p the diff node that governs the representation of differences, or null
   *          if unknown or not applicable
   * @return a potentially null object
   */
  String getToolTipText(Object object_p, String base_p,
      DifferenceKind diffKind_p, Role side_p, EMFDiffNode node_p);
  
  
  /**
   * An object that can provide an IDiffLabelDecorator.
   */
  interface Provider {
    /**
     * Return the diff label decorator this object can provide
     * @return a non-null object
     */
    IDiffLabelDecorator getDiffLabelDecorator();
  }
  
  
}
