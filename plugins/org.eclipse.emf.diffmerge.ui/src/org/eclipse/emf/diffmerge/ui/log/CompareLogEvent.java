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

import static org.eclipse.emf.diffmerge.ui.log.DiffMergeLogger.LINE_SEP;

import java.util.Date;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
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
   * @param node_p a non-null diff node
   */
  public CompareLogEvent(EditingDomain domain_p, EMFDiffNode node_p) {
    super(node_p);
  }
  
  /**
   * Return the label describing the left-hand side
   * @return a non-null string
   */
  public String getLeftLabel() {
    return getSideLabel(true);
  }
  
  /**
   * Return the label describing the right-hand side
   * @return a non-null string
   */
  public String getRightLabel() {
    return getSideLabel(false);
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
  
  /**
   * Return the label describing the given side
   * @param left_p whether the side is left or right
   * @return a non-null string
   */
  protected String getSideLabel(boolean left_p) {
    EMFDiffNode node = getDiffNode();
    Role sideRole = node.getRoleForSide(left_p);
    IFeaturedModelScope sideScope = node.getActualComparison().getScope(sideRole);
    return DiffMergeLabelProvider.getInstance().getText(sideScope);
  }
  
}
