/*********************************************************************
 * Copyright (c) 2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.operations.IUndoContext;


/**
 * An undo context that is made of several children undo contexts.
 * It matches any undo context that is matched by at least one of its children.
 * @author Olivier Constant
 */
public class CompositeUndoContext implements IUndoContext {
  
  /** The non-null set children undo contexts */
  protected final Set<IUndoContext> _children;
  
  
  /**
   * Constructor
   * @param children_p a non-null, potentially empty collection of undo contexts
   */
  public CompositeUndoContext(Collection<IUndoContext> children_p) {
    _children = new HashSet<IUndoContext>(children_p);
  }
  
  /**
   * @see org.eclipse.core.commands.operations.IUndoContext#getLabel()
   */
  public String getLabel() {
    StringBuilder builder = new StringBuilder();
    boolean first = true;
    for (IUndoContext child : _children) {
      if (first) {
        first = false;
      } else {
        builder.append(", "); //$NON-NLS-1$
      }
      builder.append(child.getLabel());
    }
    return builder.toString();
  }
  
  /**
   * @see org.eclipse.core.commands.operations.IUndoContext#matches(org.eclipse.core.commands.operations.IUndoContext)
   */
  public boolean matches(IUndoContext context_p) {
    for (IUndoContext child : _children) {
      if (child.matches(context_p)) {
        return true;
      }
    }
    return false;
  }
  
}
