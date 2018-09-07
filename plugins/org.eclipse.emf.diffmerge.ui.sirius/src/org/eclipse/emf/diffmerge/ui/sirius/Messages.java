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
package org.eclipse.emf.diffmerge.ui.sirius;

import org.eclipse.osgi.util.NLS;


/**
 * String management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.ui.sirius.messages"; //$NON-NLS-1$
  public static String SiriusComparisonFactory_Label;
  public static String SiriusComparisonMethod_UndoRedoWarning;
  public static String SiriusDifferenceCategoryProvider_SiriusSet_Description;
  public static String SiriusDifferenceCategoryProvider_SiriusSet_Text;
  public static String SiriusDiffMergeLabelProvider_DAnalysisCustomDataLabel;
  public static String SiriusDiffMergeLabelProvider_DRepresentationLabel;
  public static String SiriusTechnicalDifferenceCategory_Description;
  public static String SiriusTechnicalDifferenceCategory_Text;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
  
  /**
   * Constructor
   */
  private Messages() {
    // Nothing needed
  }
}
