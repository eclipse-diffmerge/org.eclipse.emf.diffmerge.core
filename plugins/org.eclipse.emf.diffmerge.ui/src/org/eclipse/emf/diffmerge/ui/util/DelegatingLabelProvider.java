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
package org.eclipse.emf.diffmerge.ui.util;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;

import java.util.Arrays;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;


/**
 * A label provider that supports colors, fonts, tool tips and styled labels and
 * delegates to another label provider for certain operations.
 * The delegate can be changed. It is not automatically disposed.
 * If both a label and a styled label are provided then the latter is used.
 * Sub-classes are free to define parts of the behavior that diverge from the delegate.
 * @author Olivier Constant
 */
public class DelegatingLabelProvider extends StyledCellLabelProvider
implements IColorProvider, IFontProvider, ILabelProvider, IStyledLabelProvider {
  
  /** The non-null label provider to which behavior is delegated */
  private ILabelProvider _delegate;
  
  /** A potentially null font to use by default if none is otherwise provided */
  protected Font _defaultFont;
  
  
  /**
   * Default constructor with default delegate
   */
  public DelegatingLabelProvider() {
    this(null);
  }
  
  /**
   * Constructor
   * @param delegate_p the optional label provider to which behavior is delegated
   */
  public DelegatingLabelProvider(ILabelProvider delegate_p) {
    setDelegate(delegate_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.BaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void addListener(ILabelProviderListener listener_p) {
    super.addListener(listener_p);
    getDelegate().addListener(listener_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element_p) {
    Font result = null;
    if (_delegate instanceof IFontProvider)
      result = ((IFontProvider)_delegate).getFont(element_p);
    if (result == null)
      result = _defaultFont;
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element_p) {
    Color result = null;
    if (_delegate instanceof IColorProvider)
      result = ((IColorProvider)_delegate).getBackground(element_p);
    return result;
  }
  
  /**
   * Return the default delegate
   * @return a non-null label provider
   */
  protected ILabelProvider getDefaultDelegate() {
    return DiffMergeLabelProvider.getInstance();
  }
  
  /**
   * Return the label provider to which behavior is delegated
   * @return a non-null label provider
   */
  public ILabelProvider getDelegate() {
    return _delegate;
  }
  
  /**
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element_p) {
    Color result = null;
    if (_delegate instanceof IColorProvider)
      result = ((IColorProvider)_delegate).getForeground(element_p);
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element_p) {
    return _delegate.getImage(element_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider#getStyledText(java.lang.Object)
   */
  public StyledString getStyledText(Object element_p) {
    StyledString result = null;
    if (_delegate instanceof IStyledLabelProvider)
      result = ((IStyledLabelProvider)_delegate).getStyledText(element_p);
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    return _delegate.getText(element_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.BaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
   */
  @Override
  public void removeListener(ILabelProviderListener listener_p) {
    super.removeListener(listener_p);
    getDelegate().removeListener(listener_p);
  }
  
  /**
   * Set the delegate of this label provider (null for default)
   * @param delegate_p a potentially null label provider
   */
  public void setDelegate(ILabelProvider delegate_p) {
    ILabelProvider old = _delegate;
    _delegate = delegate_p;
    if (_delegate == null) {
      _delegate = getDefaultDelegate();
    }
    if (_delegate != old) {
      fireLabelProviderChanged(new LabelProviderChangedEvent(this));
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.StyledCellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
   */
  @Override
  public void update(ViewerCell cell_p) {
    Object element = cell_p.getElement();
    StyledString styledText = getStyledText(element);
    // Inspired by DelegatingStyledCellLabelProvider#update(ViewerCell)
    String newText = (styledText == null)? getText(element): styledText.toString();
    StyleRange[] oldStyleRanges = cell_p.getStyleRanges();
    StyleRange[] newStyleRanges = (isOwnerDrawEnabled() && styledText != null)?
        styledText.getStyleRanges(): null;
    if (!Arrays.equals(oldStyleRanges, newStyleRanges)) {
      cell_p.setStyleRanges(newStyleRanges);
      if (cell_p.getText().equals(newText)) {
        // Text is not changed but style ranges are: force a refresh
        cell_p.setText(""); //$NON-NLS-1$
      }
    }
    cell_p.setText(newText);
    cell_p.setImage(getImage(element));
    cell_p.setBackground(getBackground(element));
    cell_p.setForeground(getForeground(element));
    _defaultFont = cell_p.getFont();
    cell_p.setFont(getFont(element));
    _defaultFont = null;
    // No need to call super.update(ViewerCell) similarly to
    // DelegatingStyledCellLabelProvider#update(ViewerCell)
  }
  
}
