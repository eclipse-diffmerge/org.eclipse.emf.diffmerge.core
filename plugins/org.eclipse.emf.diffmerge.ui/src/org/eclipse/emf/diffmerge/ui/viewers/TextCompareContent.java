/*********************************************************************
 * Copyright (c) 2017-2018 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - Bug #516234 : initial API and implementation
 *    Olivier Constant (Thales Global Services) - final integration
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;


/**
 * This class is used to display textual elements in the TextMergeViewer.
 * @see TextMergeViewer
 * 
 * @author Stephane Bouchet (Intel Corporation)
 */
public class TextCompareContent implements ITypedElement, IEditableContent,
IStreamContentAccessor {
  
  /** The non-null content to display */
  private final String _content;
  
  /** Whether the content is editable */
  private final boolean _editable;
  
  /** The edited content if the initial content has been edited */
  protected String _editedContent;
  
  
  /**
   * Constructor
   * @param content_p the non-null initial content to display
   * @param editable_p whether the content can be edited
   */
  public TextCompareContent(String content_p, boolean editable_p) {
    _content = content_p;
    _editable = editable_p;
  }
  
  /**
   * @see org.eclipse.compare.IStreamContentAccessor#getContents()
   */
  public InputStream getContents() throws CoreException {
    return new ByteArrayInputStream(_content.getBytes());
  }
  
  /**
   * Return the edited content, if any
   * @return a potentially null string
   */
  public String getEditedContent() {
    return _editedContent;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getImage()
   */
  public Image getImage() {
    return null;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getName()
   */
  public String getName() {
    return null;
  }
  
  /**
   * @see org.eclipse.compare.ITypedElement#getType()
   */
  public String getType() {
    return ITypedElement.TEXT_TYPE;
  }
  
  /**
   * @see org.eclipse.compare.IEditableContent#isEditable()
   */
  public boolean isEditable() {
    return _editable;
  }
  
  /**
   * @see org.eclipse.compare.IEditableContent#replace(org.eclipse.compare.ITypedElement, org.eclipse.compare.ITypedElement)
   */
  public ITypedElement replace(ITypedElement dest_p, ITypedElement src_p) {
    return null;
  }
  
  /**
   * @see org.eclipse.compare.IEditableContent#setContent(byte[])
   */
  public void setContent(byte[] newContent_p) {
    _editedContent = new String(newContent_p);
  }
  
}