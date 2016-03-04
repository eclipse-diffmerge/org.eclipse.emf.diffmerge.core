/**
 * <copyright>
 * 
 * Copyright (c) 2015 Thales Global Services S.A.S and others.
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
package org.eclipse.emf.diffmerge.connector;

import org.eclipse.osgi.util.NLS;


/**
 * String management.
 * @author Olivier Constant
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.connector.core.messages"; //$NON-NLS-1$
  public static String FileRevisionModelScopeDefinitionFactory_EntrypointLabel;
  public static String FileRevisionModelScopeDefinitionFactory_Label;
  public static String TeamComparisonViewer_NoDiff_Message;
  public static String TeamComparisonViewer_NoDiff_Title;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
