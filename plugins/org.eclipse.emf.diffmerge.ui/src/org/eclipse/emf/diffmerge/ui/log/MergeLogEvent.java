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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EObject;


/**
 * Log data representing a merge action.
 * @author Olivier Constant
 */
@SuppressWarnings("nls")
public class MergeLogEvent extends AbstractLogEvent {
  
  /** The non-null, potentially empty list of differences */
  private final List<IDifference> _diffs;
  
  /** Whether merge is to the left */
  private final boolean _mergeToLeft;
  
  
  /**
   * Constructor
   * @param node_p a non-null diff node
   * @param diff_p the non-null difference being merged
   * @param mergeToLeft_p whether the direction of the merge event is left
   */
  public MergeLogEvent(EMFDiffNode node_p, IDifference diff_p, boolean mergeToLeft_p) {
    this(node_p, Collections.singletonList(diff_p), mergeToLeft_p);
  }
  
  /**
   * Constructor
   * @param node_p a non-null diff node
   * @param diffs_p the non-null differences being merged
   * @param mergeToLeft_p whether the direction of the merge event is left
   */
  public MergeLogEvent(EMFDiffNode node_p,
      Collection<? extends IDifference> diffs_p, boolean mergeToLeft_p) {
    super(node_p);
    _mergeToLeft = mergeToLeft_p;
    _diffs = new FArrayList<IDifference>(diffs_p, null);
  }
  
  /**
   * Return the difference being merged
   * @return a non-null difference
   */
  public List<IDifference> getDifferences() {
    return Collections.unmodifiableList(_diffs);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.log.AbstractLogEvent#getRepresentation()
   */
  @Override
  public String getRepresentation() {
    StringBuilder builder = new StringBuilder();
    EMFDiffNode node = getDiffNode();
    Role destination = node.getRoleForSide(isToLeft());
    String destinationName = isToLeft()? "Left": "Right";
    for (IDifference difference : getDifferences()) {
      builder.append(LINE_SEP);
      if (difference instanceof IElementRelativeDifference) {
        IMatch match;
        if (difference instanceof IReferenceValuePresence &&
            ((IReferenceValuePresence)difference).isOwnership())
          match = ((IReferenceValuePresence)difference).getValueMatch(); // Move
        else
          match = ((IElementRelativeDifference)difference).getElementMatch();
        if (match != null) {
          EObject location = getNonNull(match, destination);
          String type = location.eClass().getName();
          String name = DiffMergeLabelProvider.getInstance().getMatchText(
              match, destination, node.getEditingDomain());
          String id = getID(location);
          builder.append('[');
          builder.append(destinationName);
          builder.append(']');
          builder.append(' ');
          builder.append(type);
          builder.append(' ');
          builder.append('\'');
          builder.append(name);
          builder.append('\'');
          builder.append("  (ID:");
          builder.append(id);
          builder.append(')');
          builder.append(LINE_SEP);
        }
      }
      String msg = DiffMergeLabelProvider.getInstance().getDifferenceText(
          difference, destination, node.getEditingDomain());
      DiffMergeLogger.appendAtLevel(builder, 1, msg);
    }
    return builder.toString();
  }
  
  /**
   * Return whether the merge action is to the left
   */
  public boolean isToLeft() {
    return _mergeToLeft;
  }
  
}
