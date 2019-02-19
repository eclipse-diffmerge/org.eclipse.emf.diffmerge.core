/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers.categories;

import java.util.Set;

import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.ecore.EObject;


/**
 * A base implementation of IDifferenceCategory.
 * @author Olivier Constant
 */
public abstract class AbstractDifferenceCategory extends AbstractDifferenceCategoryItem
implements IDifferenceCategory {
  
  /** Whether this category is visible */
  private boolean _visible;
  
  /** Whether this category is currently active */
  private boolean _active;
  
  /** Whether this category is modifiable by the user */
  private boolean _modifiable;
  
  /** Whether this category is in focus mode */
  private boolean _inFocusMode;
  
  
  /**
   * Constructor
   */
  protected AbstractDifferenceCategory() {
    _active = false;
    _inFocusMode = false;
    _modifiable = true;
    _visible = true;
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public IDifferenceCategory clone() throws CloneNotSupportedException {
    // Increase visibility, see Cloneable
    return (IDifferenceCategory)super.clone();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#copyState(org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory)
   */
  public void copyState(IDifferenceCategory peer_p) {
    setActive(peer_p.isActive());
    setInFocusMode(peer_p.isInFocusMode());
    setModifiable(peer_p.isModifiable());
    setVisible(peer_p.isVisible());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getDescription(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public String getDescription(EMFDiffNode node_p) {
    return null;
  }
  
  /**
   * Return the elements bound together by the given match
   * @param match_p a non-null match
   * @return a non-null, potentially empty set
   */
  protected Set<EObject> getElements(EMatch match_p) {
    Set<EObject> result = new FOrderedSet<EObject>();
    for (Role role : Role.values()) {
      EObject element = match_p.get(role);
      if (element != null) {
        result.add(element);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isActive()
   */
  public boolean isActive() {
    return _active;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  public boolean isApplicable(EMFDiffNode node_p) {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isInFocusMode()
   */
  public boolean isInFocusMode() {
    return _inFocusMode;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isModifiable()
   */
  public boolean isModifiable() {
    return _modifiable;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#isVisible()
   */
  public boolean isVisible() {
    return _visible;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#mayCoverPendingDifferences()
   */
  public boolean mayCoverPendingDifferences() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setActive(boolean)
   */
  public void setActive(boolean active_p) {
    _active = active_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setInFocusMode(boolean)
   */
  public void setInFocusMode(boolean inFocusMode_p) {
    _inFocusMode = inFocusMode_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setModifiable(boolean)
   */
  public void setModifiable(boolean modifiable_p) {
    _modifiable = modifiable_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#setVisible(boolean)
   */
  public void setVisible(boolean visible_p) {
    _visible = visible_p;
  }
  
}
