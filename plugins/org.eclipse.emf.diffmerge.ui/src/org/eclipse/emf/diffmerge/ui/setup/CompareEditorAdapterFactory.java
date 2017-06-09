/**
 * <copyright>
 * 
 * Copyright (c) 2013-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.setup;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;


/**
 * An adapter factory for the Compare editor so that it can synchronize with specific
 * workbench views.
 * @author Olivier Constant
 */
public class CompareEditorAdapterFactory implements IAdapterFactory {
  
  /**
   * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
   */
  @SuppressWarnings({ "rawtypes", "unchecked" }) // Compatibility with old versions of Eclipse
  public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
    Object adapter = null;
    // IPropertySheetPage
    if (IPropertySheetPage.class.equals(adapterType_p) &&
        adaptableObject_p instanceof EditorPart) {
      IEditorInput input =  ((EditorPart)adaptableObject_p).getEditorInput();
      if (input instanceof EMFDiffMergeEditorInput)
        adapter = ((EMFDiffMergeEditorInput)input).getPropertySheetPage();
    }
    return adapter;
  }
  
  /**
   * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
   */
  public Class<?>[] getAdapterList() {
    return new Class[] {IPropertySheetPage.class};
  }
  
}
