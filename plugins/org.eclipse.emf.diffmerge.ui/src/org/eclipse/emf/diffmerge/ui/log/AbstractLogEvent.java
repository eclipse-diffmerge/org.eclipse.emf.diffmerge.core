/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.log;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
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
  
  /** The non-null comparison */
  private final IComparison _comparison;
  
  
  /**
   * Constructor
   * @param comparison_p a non-null comparison
   */
  protected AbstractLogEvent(IComparison comparison_p) {
    _comparison = comparison_p;
  }
  
  /**
   * Return the comparison to which this event is relative
   * @return a non-null comparison
   */
  public IComparison getComparison() {
    return _comparison;
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
