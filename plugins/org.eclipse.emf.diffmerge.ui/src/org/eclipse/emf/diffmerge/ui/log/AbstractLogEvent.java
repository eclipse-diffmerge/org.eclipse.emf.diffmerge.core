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
package org.eclipse.emf.diffmerge.ui.log;

import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;


/**
 * A common base class for Diff/Merge log events.
 * @author Olivier Constant
 */
public abstract class AbstractLogEvent {
  
  /** A string representing an arrow in ASCII style */
  protected static final String ARROW = " -> "; //$NON-NLS-1$
  
  /** The non-null diff node */
  private final EMFDiffNode _node;
  
  
  /**
   * Constructor
   * @param node_p a non-null diff node
   */
  protected AbstractLogEvent(EMFDiffNode node_p) {
    _node = node_p;
  }
  
  /**
   * Return the comparison to which this event is relative
   * @return a non-null comparison
   */
  public EMFDiffNode getDiffNode() {
    return _node;
  }
  
  /**
   * Return the intrinsic or, if not available, extrinsic ID of the given element
   * @param element_p a potentially null element
   * @param side_p the role of the element
   * @return a potentially null object
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected Object getID(Object element_p, Role side_p) {
    Object result = null;
    if (element_p != null) {
      ITreeDataScope sourceScope = getDiffNode().getActualComparison().getScope(side_p);
      result = sourceScope.tGetID(element_p, true);
      if (result == null) {
        result = sourceScope.tGetID(element_p, false);
      }
    }
    return result;
  }
  
  /**
   * Return a label provider for elements to log
   * @return a non-null label provider
   */
  protected DiffMergeLabelProvider getLabelProvider() {
    return DiffMergeLabelProvider.getInstance();
  }
  
  /**
   * Return the element of the match for the given role, or for the opposite role if null
   * @param match_p a non-null match
   * @param role_p a non-null role
   * @return a non-null element
   */
  protected Object getNonNull(IMatch<?> match_p, Role role_p) {
    Object result = match_p.get(role_p);
    if (result == null) {
      result = match_p.get(role_p.opposite());
    }
    return result;
  }
  
  /**
   * Return a representation for logging
   * @return a non-null string
   */
  public abstract String getRepresentation();
  
}
