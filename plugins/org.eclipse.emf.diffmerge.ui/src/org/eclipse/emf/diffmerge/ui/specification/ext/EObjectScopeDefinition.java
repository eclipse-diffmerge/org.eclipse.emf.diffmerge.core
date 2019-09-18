/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.specification.ext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.impl.scopes.SubtreeModelScope;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A scope definition based on a model element.
 * @author Olivier Constant
 */
public class EObjectScopeDefinition extends AbstractScopeDefinition {
  
  /**
   * Constructor
   * @param element_p a non-null element
   * @param label_p an optional label
   * @param editable_p whether the scope can be edited
   */
  public EObjectScopeDefinition(EObject element_p, String label_p, boolean editable_p) {
    super(element_p,
        label_p != null? label_p: getLabelForElement(element_p), editable_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#createScope(java.lang.Object)
   */
  public IEditableModelScope createScope(Object context_p) {
    return new SubtreeModelScope(getEntrypoint());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.AbstractScopeDefinition#getEntrypoint()
   */
  @Override
  public EObject getEntrypoint() {
    return (EObject)super.getEntrypoint();
  }
  
  /**
   * Return a label for the given element
   * @param element_p a non-null element
   * @return a non-null string
   */
  protected static String getLabelForElement(EObject element_p) {
    String result = UIUtil.getEMFText(element_p);
    if (result == null)
      result = element_p.toString();
    else {
      Resource resource = element_p.eResource();
      if (resource != null) {
        URI uri = resource.getURI();
        if (uri != null) {
          String resourceName = UIUtil.simplifyURI(uri);
          result = String.format(
              Messages.EObjectScopeDefinition_LabelInResource, result, resourceName);
        }
      }
    }
    return result;
  }
  
}
