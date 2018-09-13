/*********************************************************************
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.viewers;


/**
 * The specification of choices when ignoring differences from GUI.
 * @author Olivier Constant
 */
public class IgnoreChoiceData {
  
  /** Whether differences on children must be covered */
  private boolean _coverChildren;
  
  /** Whether only differences on one side are concerned */
  private boolean _sideExclusive;
  
  /** Whether to proceed with merge */
  private boolean _proceed;
  
  
  /**
   * Default constructor
   */
  public IgnoreChoiceData() {
    _coverChildren = false;
    _sideExclusive = false;
    _proceed = true;
  }
  
  /**
   * Full constructor
   */
  public IgnoreChoiceData(boolean coverChildren_p, boolean sideExclusive_p) {
    _coverChildren = coverChildren_p;
    _sideExclusive = sideExclusive_p;
    _proceed = true;
  }
  
  /**
   * Return whether differences on children must be covered
   */
  public boolean isCoverChildren() {
    return _coverChildren;
  }
  
  /**
   * Return whether proceeding of the merge operation is selected
   */
  public boolean isProceed() {
    return _proceed;
  }
  
  /**
   * Return whether only differences on one side are concerned
   */
  public boolean isSideExclusive() {
    return _sideExclusive;
  }
  
  /**
   * Set whether differences on children must be covered
   */
  public void setCoverChildren(boolean newValue_p) {
    _coverChildren = newValue_p;
  }
  
  /**
   * Set whether proceeding of the merge operation must be done
   */
  public void setProceed(boolean newValue_p) {
    _proceed = newValue_p;
  }
  
  /**
   * Set whether only differences on one side are concerned
   */
  public void setSideExclusive(boolean newValue_p) {
    _sideExclusive = newValue_p;
  }
  
}