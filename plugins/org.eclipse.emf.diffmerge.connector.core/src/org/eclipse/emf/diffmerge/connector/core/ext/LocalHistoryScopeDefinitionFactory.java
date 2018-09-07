/**
 * Copyright (c) 2015-2018 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 */
package org.eclipse.emf.diffmerge.connector.core.ext;

import java.text.DateFormat;
import java.util.Calendar;

import org.eclipse.compare.ITypedElement;
import org.eclipse.emf.diffmerge.connector.core.Messages;
import org.eclipse.team.core.history.IFileRevision;
import org.eclipse.team.core.variants.IResourceVariant;
import org.eclipse.team.internal.core.history.LocalFileRevision;


/**
 * A scope definition factory for file revisions in the Eclipse local history.
 */
@SuppressWarnings("restriction") // Specific IFileRevision sub-types
public class LocalHistoryScopeDefinitionFactory extends AbstractRevisionScopeDefinitionFactory {
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#getLabelForRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected String getLabelForRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    String result;
    IResourceVariant variant = getVariant(revision_p);
    if (variant != null) {
      // Known variant
      result = variant.getContentIdentifier();
    } else if (revision_p.getTimestamp() != -1) {
      // Known time stamp
      Calendar fileRevDate = Calendar.getInstance();
      fileRevDate.setTimeInMillis(revision_p.getTimestamp());
      result = String.format(
          Messages.LocalHistoryModelScopeDefinitionFactory_RevisionLabel,
          revision_p.getName(),
          DateFormat.getInstance().format(fileRevDate.getTime()));
    } else {
      // No variant and unknown time stamp
      result = super.getLabelForRevision(revision_p, entrypoint_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.core.ext.AbstractRevisionScopeDefinitionFactory#isApplicableToRevision(org.eclipse.team.core.history.IFileRevision, org.eclipse.compare.ITypedElement)
   */
  @Override
  protected boolean isApplicableToRevision(IFileRevision revision_p, ITypedElement entrypoint_p) {
    return revision_p instanceof LocalFileRevision;
  }
  
}