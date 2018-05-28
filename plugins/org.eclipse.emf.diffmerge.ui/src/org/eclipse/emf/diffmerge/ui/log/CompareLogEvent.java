/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import static org.eclipse.emf.diffmerge.ui.log.DiffMergeLogger.LINE_SEP;

import java.util.Date;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * Log data representing the computation of a comparison.
 * @author Olivier Constant
 */
@SuppressWarnings("nls") // No localization for logging
public class CompareLogEvent extends AbstractLogEvent {
  
  /**
   * Constructor
   * @param domain_p an optional editing domain
   * @param comparison_p a non-null comparison
   */
  public CompareLogEvent(EditingDomain domain_p, IComparison comparison_p) {
    super(comparison_p);
  }
  
  /**
   * Return the label describing the left-hand side
   * @return a non-null string
   */
  public String getLeftLabel() {
    return DiffMergeLabelProvider.getInstance().getText(
        getComparison().getScope(Role.TARGET));
  }
  
  /**
   * Return the label describing the right-hand side
   * @return a non-null string
   */
  public String getRightLabel() {
    return DiffMergeLabelProvider.getInstance().getText(
        getComparison().getScope(Role.REFERENCE));
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.log.AbstractLogEvent#getRepresentation()
   */
  @Override
  public String getRepresentation() {
    StringBuilder builder = new StringBuilder();
    builder.append(LINE_SEP);
    // Header
    builder.append("*** Starting logging events on comparison - ");
    Date date = new Date();
    builder.append(date);
    builder.append(LINE_SEP);
    // Left-hand side
    builder.append(Messages.ComparisonSetupWizardPage_RoleLeft);
    builder.append(getLeftLabel());
    builder.append(LINE_SEP);
    // Right-hand side
    builder.append(Messages.ComparisonSetupWizardPage_RoleRight);
    builder.append(getRightLabel());
    builder.append(LINE_SEP);
    return builder.toString();
  }
  
}
