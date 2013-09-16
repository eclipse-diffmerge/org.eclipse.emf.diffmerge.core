/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.specification;


/**
 * A base implementation for IVisualScopeSpecification.
 * @author Olivier Constant
 */
public abstract class AbstractScopeDefinition implements IModelScopeDefinition {
  
  /** The non-null entry point object */
  private final Object _entrypoint;
  
  /** The non-null label */
  private final String _label;
  
  /** Whether the scope can be edited */
  private final boolean _editable;
  
  
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
  
}
