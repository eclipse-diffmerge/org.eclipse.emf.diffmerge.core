/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;


/**
 * A label provider supporting colors, fonts and tooltips, that delegates to
 * another label provider for certain operations.
 * This class is intended to be sub-classed.
 * Sub-classes must define what part of the behavior diverges from the delegate.
 * @author Olivier Constant
 */
public class DelegatingLabelProvider extends ColumnLabelProvider {
  
  /** The non-null label provider to which behavior is delegated */
  private ILabelProvider _delegate;
  
  
  /**
   * Default constructor
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
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element_p) {
    Font result = null;
    if (_delegate instanceof IFontProvider)
      result = ((IFontProvider)_delegate).getFont(element_p);
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
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    return _delegate.getText(element_p);
  }
  
  /**
   * Set the delegate of this label provider (null for default)
   * @param delegate_p a potentially null label provider
   */
  public void setDelegate(ILabelProvider delegate_p) {
    _delegate = delegate_p;
    if (_delegate == null)
      _delegate = getDefaultDelegate();
  }
  
}
