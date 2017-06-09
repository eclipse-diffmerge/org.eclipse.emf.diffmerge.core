/*******************************************************************************
 * Copyright (c) 2015-2017 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  public static String GitHelper_NoConflictInfoFound;
  public static String GitHelper_NoRepoFound;
  public static String GitIndexRevisionScopeDefinitionFactory_LabelIndexEditable;
  public static String GitIndexRevisionScopeDefinitionFactory_LabelIndexReadOnly;
  public static String GitIndexRevisionScopeDefinitionFactory_LabelLocal;

  public static String GitRevisionScopeDefinitionFactory_LabelCommit;
  public static String GitRevisionScopeDefinitionFactory_LabelIndex;
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
