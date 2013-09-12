/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge;

import org.eclipse.osgi.util.NLS;


/**
 * Utility class for the externalization mechanism
 * @author Olivier Constant
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.messages"; //$NON-NLS-1$
  public static String AbstractDifference_UnableToMerge;
  public static String DiffBuilder_NoMatch;
  public static String DiffBuilder_Task_Main;
  public static String Comparison_Task_Main;
  public static String MatchBuilder_Task_Main;
  public static String MatchBuilder_Task_MappingIDs;
  public static String MatchBuilder_Task_RegisteringIDs;
  public static String MergeOperation_Name;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
  
}
