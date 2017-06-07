/**
 * <copyright>
 * 
 * Copyright (c) 2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.sirius;

import java.util.Arrays;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;


/**
 * A difference category that covers merged differences.
 * @author Olivier Constant
 */
public class SiriusTechnicalDifferenceCategory extends AbstractDifferenceCategory {
  
  /** The ID of this category */
  public static final String ID = "Sirius.Technical"; //$NON-NLS-1$
  
  
  /**
   * Constructor
   */
  public SiriusTechnicalDifferenceCategory() {
    super();
    setActive(true);
    setInFocusMode(false);
    setVisible(true);
    setModifiable(true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.api.diff.IDifference, org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean covers(IDifference difference_p, EMFDiffNode node_p) {
    boolean result = false;
    if (difference_p instanceof IElementRelativePresence) {
      IMatch match = ((IElementRelativePresence)difference_p).getElementMatch();
      result = isSiriusTechnicalMatch(match);
      if (!result && difference_p instanceof IReferenceValuePresence) {
        IMatch valueMatch = ((IReferenceValuePresence)difference_p).getElementMatch();
        result = isSiriusTechnicalMatch(valueMatch);
      }
      if (!result && difference_p instanceof IValuePresence) {
        Object value = ((IValuePresence)difference_p).getValue();
        result = isSiriusTechnicalObject(value);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    return Messages.SiriusTechnicalDifferenceCategory_Description;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getID()
   */
  public String getID() {
    return ID;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getText(EMFDiffNode node_p) {
    return Messages.SiriusTechnicalDifferenceCategory_Text;
  }
  
  /**
   * Return whether the given object is a Sirius technical object
   * @param object_p a non-null object
   */
  protected boolean isSiriusTechnicalObject(Object object_p) {
    return object_p instanceof ResourceDescriptor;
  }
  
  /**
   * Return whether the given match corresponds to Sirius technical objects
   * @param match_p a non-null match
   */
  protected boolean isSiriusTechnicalMatch(IMatch match_p) {
    for (Role role : Arrays.asList(Role.REFERENCE, Role.TARGET)) {
      EObject element = match_p.get(role);
      if (element != null && isSiriusTechnicalObject(element))
        return true;
    }
    return false;
  }
  
}
