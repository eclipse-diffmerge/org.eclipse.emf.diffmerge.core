/*******************************************************************************
 * Copyright (c) 2015-2016 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.svn;

import org.eclipse.osgi.util.NLS;


/**
 * String management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.connector.svn.messages"; //$NON-NLS-1$
  public static String SVNHistoryURIConverter_CannotLoad;
  public static String SVNHistoryURIConverter_CannotLoad_Located;
  public static String SVNRemoteScopeDefinitionFactory_LabelBase;
  public static String SVNRemoteScopeDefinitionFactory_LabelHead;
  public static String SVNRemoteScopeDefinitionFactory_LabelRev;
  public static String SVNRevisionScopeDefinitionFactory_LabelRevision;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
