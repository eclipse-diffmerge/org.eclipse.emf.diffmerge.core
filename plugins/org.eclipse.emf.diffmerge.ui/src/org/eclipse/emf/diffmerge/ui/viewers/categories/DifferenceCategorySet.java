/**
 * <copyright>
 * 
 * Copyright (c) 2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;


/**
 * A base implementation of IDifferenceCategory.
 * @author Olivier Constant
 */
public class DifferenceCategorySet extends AbstractDifferenceCategoryItem
implements IDifferenceCategorySet {
  
  /** The default text for this item */
  protected static final String DEFAULT_TEXT = ""; //$NON-NLS-1$
  
  /** The non-null, potentially empty, modifiable list of children items */
  private EList<IDifferenceCategoryItem> _children;
  
  /** The non-null text for this item */
  private String _text;
  
  /** The optional description for this item */
  private String _description;
  
  
  /**
   * Default constructor (default text and no description)
   */
  public DifferenceCategorySet() {
    this(null);
  }
  
  /**
   * Constructor
   * @param text_p an optional text for the category set, or null for default
   */
  public DifferenceCategorySet(String text_p) {
    this(text_p, null);
  }
  
  /**
   * Constructor
   * @param text_p an optional text for the category set, or null for default
   * @param description_p an optional description for the category set
   */
  public DifferenceCategorySet(String text_p, String description_p) {
    setText(text_p);
    setDescription(description_p);
    _children = new BasicEList<IDifferenceCategoryItem>() {
      /** Default, no serialization intended */
      private static final long serialVersionUID = 1L;
      /**
       * @see org.eclipse.emf.common.util.AbstractEList#didAdd(int, java.lang.Object)
       */
      @Override
      protected void didAdd(int index_p, IDifferenceCategoryItem newObject_p) {
        if (newObject_p instanceof AbstractDifferenceCategoryItem)
          ((AbstractDifferenceCategoryItem)newObject_p).setParent(DifferenceCategorySet.this);
      }
      /**
       * @see org.eclipse.emf.common.util.AbstractEList#didRemove(int, java.lang.Object)
       */
      @Override
      protected void didRemove(int index_p, IDifferenceCategoryItem oldObject_p) {
        if (oldObject_p.getParent() == DifferenceCategorySet.this &&
            oldObject_p instanceof AbstractDifferenceCategoryItem)
          ((AbstractDifferenceCategoryItem)oldObject_p).setParent(null);
      }
      /**
       * @see org.eclipse.emf.common.util.AbstractEList#didSet(int, java.lang.Object, java.lang.Object)
       */
      @Override
      protected void didSet(int index_p, IDifferenceCategoryItem newObject_p,
          IDifferenceCategoryItem oldObject_p) {
        didAdd(index_p, newObject_p);
        didRemove(index_p, oldObject_p);
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet#getChildren()
   */
  public EList<IDifferenceCategoryItem> getChildren() {
    return _children;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getDescription(EMFDiffNode node_p) {
    return _description;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    return _text;
  }
  
  /**
   * Set the optional description of this item
   * @see IDifferenceCategoryItem#getText(EMFDiffNode)
   * @param description_p the potentially null description
   */
  public void setDescription(String description_p) {
    _description = description_p;
  }
  
  /**
   * Set the text for this item. If null, a default one will be used.
   * @see IDifferenceCategoryItem#getText(EMFDiffNode)
   * @param text_p the potentially null text
   */
  public void setText(String text_p) {
    _text = (text_p != null)? text_p: DEFAULT_TEXT;
  }
  
}
