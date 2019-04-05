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
package org.eclipse.emf.diffmerge;

import org.eclipse.osgi.util.NLS;


/**
 * Utility class for the externalization mechanism.
 * @author Olivier Constant
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.messages"; //$NON-NLS-1$
  public static String ConfigurableMatchPolicy_Criterion_QNames_Labels;
  public static String ConfigurableMatchPolicy_Criterion_QNames_Labels_Tooltip;
  public static String ConfigurableMatchPolicy_Criterion_Semantics_DefaultContents;
  public static String ConfigurableMatchPolicy_Criterion_Semantics_DefaultContents_Tooltip;
  public static String ConfigurableMatchPolicy_Criterion_Structure_Roots;
  public static String ConfigurableMatchPolicy_Criterion_Structure_Roots_Tooltip;
  public static String ConfigurableMatchPolicy_Criterion_Structure_UnambiguousChildren;
  public static String ConfigurableMatchPolicy_Criterion_Structure_UnambiguousChildren_Tooltip;
  public static String ConfigurableMatchPolicy_Criterion_Structure_UniqueChildren;
  public static String ConfigurableMatchPolicy_Criterion_Structure_UniqueChildren_Tooltip;
  public static String FragmentedModelScope_ResourceNotDefined;
  public static String SubtreeModelScope_NotLoaded;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
  
}
