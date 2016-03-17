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
package org.eclipse.emf.diffmerge.connector.perforce;

import org.eclipse.osgi.util.NLS;


/**
 * String management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.connector.perforce.messages"; //$NON-NLS-1$
  public static String PerforceHistoryURIConverter_CannotFindRes;
  public static String PerforceHistoryURIConverter_CannotLoadChangelist;
  public static String PerforceHistoryURIConverter_CannotLoadRevision;
  public static String PerforceHistoryURIConverter_RemoteFile;

  public static String PerforceRevisionScopeDefinitionFactory_CannotLoadChangelist;
  public static String PerforceRevisionScopeDefinitionFactory_RemoteLabel;
  public static String PerforceStreamScopeDefinitionFactory_CannotLoadChangelist;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
