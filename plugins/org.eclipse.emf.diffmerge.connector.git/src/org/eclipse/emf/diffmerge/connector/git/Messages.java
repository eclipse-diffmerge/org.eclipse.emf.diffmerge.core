/*******************************************************************************
 * Copyright (c) 2015-2019 Intel Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git;

import org.eclipse.osgi.util.NLS;


/**
 * String management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.connector.git.messages"; //$NON-NLS-1$
  public static String AbstractGitURIConverter_CannotFindResource;
  public static String GitHelper_NoRepoFound;

  public static String GitRevisionScopeDefinitionFactory_LabelCommit;
  public static String GitRevisionScopeDefinitionFactory_LabelIndex;
  public static String GitRevisionScopeDefinitionFactory_LabelIndexEditable;
  public static String GitRevisionScopeDefinitionFactory_LabelRemote;
  public static String GitRevisionScopeDefinitionFactory_LabelWorkspace;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
