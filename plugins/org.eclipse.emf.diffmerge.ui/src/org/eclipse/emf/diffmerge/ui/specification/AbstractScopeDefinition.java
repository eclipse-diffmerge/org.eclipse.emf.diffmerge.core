/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.specification;


/**
 * A simple base implementation for IModelScopeDefinition.
 * @author Olivier Constant
 */
public abstract class AbstractScopeDefinition implements IModelScopeDefinition {
  
  /** The non-null entry point object */
  private final Object _entrypoint;
  
  /** The non-null label */
  private final String _label;
  
  /** Whether the scope can be edited */
  private boolean _editable;
  
  
  /**
   * Constructor
   * @param entrypoint_p the non-null entry point object
   * @param label_p a non-null label
   * @param editable_p whether the scope can be edited
   */
  protected AbstractScopeDefinition(Object entrypoint_p, String label_p, boolean editable_p) {
    _entrypoint = entrypoint_p;
    _label = label_p;
    _editable = editable_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getLabel()
   */
  public String getLabel() {
    return _label;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getShortLabel()
   */
  public String getShortLabel() {
    // Redefine when applicable
    return getLabel();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#getEntrypoint()
   */
  public Object getEntrypoint() {
    return _entrypoint;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditable()
   */
  public boolean isEditable() {
    return _editable;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#isEditableSettable()
   */
  public boolean isEditableSettable() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition#setEditable(boolean)
   */
  public void setEditable(boolean editable_p) {
    _editable = editable_p;
  }
  
}
