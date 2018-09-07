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

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;


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
   * Return the business or, if not available, technical ID of the given element
   * @param element_p a potentially null element
   * @return a potentially null string
   */
  protected String getID(EObject element_p) {
    String result = null;
    if (element_p != null) {
      result = EcoreUtil.getID(element_p);
      if (result == null && element_p.eResource() instanceof XMLResource)
        result = ((XMLResource)element_p.eResource()).getID(element_p);
    }
    return result;
  }
  
  /**
   * Return the element of the match for the given role, or for the opposite role if null
   * @param match_p a non-null match
   * @param role_p a non-null role
   * @return a non-null element
   */
  protected EObject getNonNull(IMatch match_p, Role role_p) {
    EObject result = match_p.get(role_p);
    if (result == null)
      result = match_p.get(role_p.opposite());
    return result;
  }
  
  /**
   * Return a representation for logging
   * @return a non-null string
   */
  public abstract String getRepresentation();
  
}
