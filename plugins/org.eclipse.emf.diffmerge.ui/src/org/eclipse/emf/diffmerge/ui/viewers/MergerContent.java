/**
 * <copyright>
 * 
 * Copyright (c) 2017-2018 Intel Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - Bug # : initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

/**
 * The merger content class is used to display textual elements in the textViewer.
 * 
 * @author Stephane Bouchet (Intel Corporation)
 *
 */
public class MergerContent implements ITypedElement, IEditableContent, IStreamContentAccessor {

  /** the content to display. */
	private final Object content;
	
	/** indicates if the content is editable. */
	private final boolean editable;
	
	/** the editedContent if the initial content is edited. */
	private String editedContent;

	/**
	 * The constructor.
	 * 
	 * @param content_p the inital content to display.
	 * @param editable_p true if the content can be edited.
	 */
	public MergerContent(Object content_p, boolean editable_p) {
		this.content=content_p;
		this.editable=editable_p;
	}

	/**
	 * @see org.eclipse.compare.IEditableContent#isEditable()
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @see org.eclipse.compare.IEditableContent#setContent(byte[])
	 */
	public void setContent(byte[] newContent) {
		try {
			editedContent=new String(newContent, "UTF-8"); //$NON-NLS-1$
		}
		catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.compare.IEditableContent#replace(org.eclipse.compare.ITypedElement, org.eclipse.compare.ITypedElement)
	 */
	public ITypedElement replace(ITypedElement dest, ITypedElement src) {
		return null;
	}

	/**
	 * @see org.eclipse.compare.ITypedElement#getName()
	 */
	public String getName() {
		return null;
	}

	/**
	 * @see org.eclipse.compare.ITypedElement#getImage()
	 */
	public Image getImage() {
		return null;
	}

	/**
	 * @see org.eclipse.compare.ITypedElement#getType()
	 */
	public String getType() {
		return ITypedElement.TEXT_TYPE;
	}

	/**
	 * @see org.eclipse.compare.IStreamContentAccessor#getContents()
	 */
	public InputStream getContents() throws CoreException {
		try {
			if (content instanceof String) {
				return new ByteArrayInputStream(((String)content).getBytes("UTF8")); //$NON-NLS-1$
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Returns the edited content.
	 * @return the edited content.
	 */
	public String getEditedContent() {
		return editedContent;
	}

}