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
package org.eclipse.emf.diffmerge.ui.specification.ext;

import java.io.File;

import org.eclipse.compare.IResourceProvider;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinitionFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;


/**
 * A factory for files and other URI-based scopes.
 * @author Olivier Constant
 */
public class URIScopeDefinitionFactory extends AbstractScopeDefinitionFactory {
  
  /**
   * Return whether the given object can be converted to a URI.
   * Result must be consistent with convertToURI(Object).
   * @param entrypoint_p a non-null object
   */
  protected boolean canConvertToURI(Object entrypoint_p) {
    return entrypoint_p instanceof IFile || entrypoint_p instanceof IResourceProvider ||
        entrypoint_p instanceof String;
  }
  
  /**
   * Convert the given object to a URI which can be used to define a model scope
   * @param entrypoint_p a non-null object
   * @return a potentially null URI
   */
  protected URI convertToURI(Object entrypoint_p) {
    URI result = null;
    if (entrypoint_p instanceof URI) {
      result = (URI)entrypoint_p;
    } else if (entrypoint_p instanceof IResourceProvider) {
      IResource wkResource = ((IResourceProvider)entrypoint_p).getResource();
      if (wkResource instanceof IFile)
        result = toPlatformURI((IFile)wkResource);
    } else if (entrypoint_p instanceof IFile) {
      result = toPlatformURI((IFile)entrypoint_p);
    } else if (entrypoint_p instanceof String) {
      result = toFileURI(entrypoint_p.toString());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#createScopeDefinition(java.lang.Object, java.lang.String, boolean)
   */
  public IModelScopeDefinition createScopeDefinition(Object entrypoint_p, String label_p,
      boolean editable_p) {
    IModelScopeDefinition result = null;
    URI uri = convertToURI(entrypoint_p);
    if (uri != null) {
      String label = (label_p != null)? label_p: getLabelFor(entrypoint_p);
      result = createScopeDefinitionFromURI(uri, label, editable_p);
    }
    return result;
  }
  
  /**
   * Create and return a scope definition corresponding to the given URI
   * @param uri_p a non-null URI
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   * @return a non-null scope definition
   */
  protected IModelScopeDefinition createScopeDefinitionFromURI(URI uri_p, String label_p,
      boolean editable_p) {
    return new URIScopeDefinition(uri_p, label_p, editable_p);
  }
  
  /**
   * Return the file extension (in lowercase) that corresponds to the given scope
   * entry point, if applicable
   * @param entrypoint_p a non-null object
   * @return a potentially null string
   */
  protected String getFileExtension(Object entrypoint_p) {
    String result = null;
    if (entrypoint_p instanceof IFile) {
      result = ((IFile)entrypoint_p).getFileExtension();
    } else if (entrypoint_p instanceof URI) {
      result = ((URI)entrypoint_p).fileExtension();
    } else if (entrypoint_p instanceof String) {
      String location = (String)entrypoint_p;
      int dotPosition = location.lastIndexOf(File.separator);
      if (dotPosition > -1 && dotPosition < location.length())
        result = location.substring(dotPosition + 1);
    }
    if (result != null)
      result = result.toLowerCase();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#getLabel()
   */
  public String getLabel() {
    return Messages.FileScopeDefinitionFactory_Label;
  }
  
  /**
   * Return a user-friendly label for the given entry point object, if applicable
   * @param entrypoint_p a non-null object
   * @return a potentially null label
   */
  protected String getLabelFor(Object entrypoint_p) {
    String result = null;
    if (entrypoint_p instanceof IFile)
      result = getLabelForFile((IFile)entrypoint_p);
    return result;
  }
  
  /**
   * Return a label for the given file to display in the compare editor
   * @param file_p a potentially null file
   * @return a potentially null string
   */
  protected String getLabelForFile(IFile file_p) {
    String result = null;
    if (file_p != null) {
      result = file_p.getFullPath().toPortableString();
      if (result.startsWith("/") && result.length() > 1) //$NON-NLS-1$
        result = result.substring(1);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinitionFactory#isApplicableTo(java.lang.Object)
   */
  public boolean isApplicableTo(Object entrypoint_p) {
    return entrypoint_p instanceof URI || canConvertToURI(entrypoint_p);
  }
  
  /**
   * Return the file URI corresponding to the given file location (based on java.io.File)
   * @param file_p a potentially null string
   * @return a potentially null URI
   */
  protected URI toFileURI(String file_p) {
    URI result = null;
    if (file_p != null)
      result = URI.createFileURI(file_p);
    return result;
  }
  
  /**
   * Return the EMF platform URI corresponding to the given platform file
   * @param file_p a potentially null file
   * @return a potentially null URI
   */
  protected URI toPlatformURI(IFile file_p) {
    URI result = null;
    if (file_p != null)
      result = URI.createPlatformResourceURI(file_p.getFullPath().toPortableString(), true);
    return result;
  }
  
}
