/**
 * <copyright>
 * 
 * Copyright (c) 2014-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers;


/**
 * The specification of choices when defining a merge from GUI.
 * @author Olivier Constant
 */
public class MergeChoiceData {
  
  /** Whether differences on children must be covered */
  private boolean _coverChildren;
  
  /** Whether incremental mode is selected */
  private boolean _incrementalMode;
  
  /** Whether merge impact must be shown */
  private boolean _showImpact;
  
  /** Whether to proceed with merge */
  private boolean _proceed;
  
  
  /**
   * Default constructor
   */
  public MergeChoiceData() {
    _coverChildren = false;
    _incrementalMode = false;
    _showImpact = false;
    _proceed = true;
  }
  
  /**
   * Full constructor
   */
  public MergeChoiceData(boolean coverChildren_p, boolean incrementalMode_p, boolean showImpact_p) {
    _coverChildren = coverChildren_p;
    _incrementalMode = incrementalMode_p;
    _showImpact = showImpact_p;
    _proceed = true;
  }
  
  /**
   * Return whether differences on children must be covered
   */
  public boolean isCoverChildren() {
    return _coverChildren;
  }
  
  /**
   * Return whether incremental mode is selected
   */
  public boolean isIncrementalMode() {
    return _incrementalMode;
  }
  
  /**
   * Return whether proceeding of the merge operation is selected
   */
  public boolean isProceed() {
    return _proceed;
  }
  
  /**
   * Return whether merge impact must be shown
   */
  public boolean isShowImpact() {
    return _showImpact;
  }
  
  /**
   * Set whether differences on children must be covered
   */
  public void setCoverChildren(boolean newValue_p) {
    _coverChildren = newValue_p;
  }
  
  /**
   * Set whether incremental mode is selected
   */
  public void setIncrementalMode(boolean newValue_p) {
    _incrementalMode = newValue_p;
  }
  
  /**
   * Set whether proceeding of the merge operation must be done
   */
  public void setProceed(boolean newValue_p) {
    _proceed = newValue_p;
  }
  
  /**
   * Set whether merge impact must be shown
   */
  public void setShowImpact(boolean newValue_p) {
    _showImpact = newValue_p;
  }
  
}