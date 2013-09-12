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
package org.eclipse.emf.diffmerge.ui;

import org.eclipse.osgi.util.NLS;



/**
 * String management.
 * @author Olivier Constant
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.ui.messages"; //$NON-NLS-1$
  public static String CompareModelsAction_ModelsOnly;
  public static String ComparisonSetupWizardPage_ConfigureButton;
  public static String ComparisonSetupWizardPage_Description;
  public static String ComparisonSetupWizardPage_GroupMethod;
  public static String ComparisonSetupWizardPage_GroupRoles;
  public static String ComparisonSetupWizardPage_RoleAncestor;
  public static String ComparisonSetupWizardPage_RoleLeft;
  public static String ComparisonSetupWizardPage_RoleRight;
  public static String ComparisonSetupWizardPage_SwapLeftRight;
  public static String ComparisonSetupWizardPage_SwapRightAncestor;
  public static String ComparisonSetupWizardPage_Title;
  public static String ComparisonTreeViewer_MatchIDTooltip;
  public static String ComparisonViewer_CollapseTooltip;
  public static String ComparisonViewer_CountAddLeftMenuItem;
  public static String ComparisonViewer_CountAddRightMenuItem;
  public static String ComparisonViewer_CountMovesMenuItem;
  public static String ComparisonViewer_CountProperMenuItem;
  public static String ComparisonViewer_DeleteLeftTooltip;
  public static String ComparisonViewer_DeleteRightTooltip;
  public static String ComparisonViewer_Details;
  public static String ComparisonViewer_ExpandTooltip;
  public static String ComparisonViewer_Filtered;
  public static String ComparisonViewer_IconsMenuItem;
  public static String ComparisonViewer_IgnoreCommandName;
  public static String ComparisonViewer_IgnoreLeftTooltip;
  public static String ComparisonViewer_IgnoreRightTooltip;
  public static String ComparisonViewer_ImpactDescription;
  public static String ComparisonViewer_ImpactMenuItem;
  public static String ComparisonViewer_LabelsMenuItem;
  public static String ComparisonViewer_Left;
  public static String ComparisonViewer_LinkViewsTooltip;
  public static String ComparisonViewer_LockTooltip;
  public static String ComparisonViewer_LogEventsMenuItem;
  public static String ComparisonViewer_MergeHeader;
  public static String ComparisonViewer_MergeLeftTooltip;
  public static String ComparisonViewer_MergeRightTooltip;
  public static String ComparisonViewer_NextTooltip;
  public static String ComparisonViewer_NoDiffsToMerge;
  public static String ComparisonViewer_PreviousTooltip;
  public static String ComparisonViewer_Right;
  public static String ComparisonViewer_SaveFailed;
  public static String ComparisonViewer_ShowAllFeatures;
  public static String ComparisonViewer_ShowAllValues;
  public static String ComparisonViewer_ShowMovesMenuItem;
  public static String ComparisonViewer_ShowUncountedMenuItem;
  public static String ComparisonViewer_ShowValueDiffs;
  public static String ComparisonViewer_SortTooltip;
  public static String ComparisonViewer_SupportUndoRedoMenuItem;
  public static String EMFDiffMergeEditorInput_AncestorScope;
  public static String EMFDiffMergeEditorInput_DuplicateIDs;
  public static String EMFDiffMergeEditorInput_Failure;
  public static String EMFDiffMergeEditorInput_Loading;
  public static String EMFDiffMergeEditorInput_LoadingAncestor;
  public static String EMFDiffMergeEditorInput_LoadingLeft;
  public static String EMFDiffMergeEditorInput_LoadingRight;
  public static String EMFDiffMergeEditorInput_MigrationNeeded;
  public static String EMFDiffMergeEditorInput_ReferenceScope;
  public static String EMFDiffMergeEditorInput_TargetScope;
  public static String EMFDiffMergeEditorInput_WrongMetamodel;
  public static String EMFDiffMergeLabelProvider_Addition;
  public static String EMFDiffMergeLabelProvider_AdditionInto;
  public static String EMFDiffMergeLabelProvider_Attribute;
  public static String EMFDiffMergeLabelProvider_Deletion;
  public static String EMFDiffMergeLabelProvider_MoveFrom;
  public static String EMFDiffMergeLabelProvider_MoveInto;
  public static String EMFDiffMergeLabelProvider_OrderAdd;
  public static String EMFDiffMergeLabelProvider_OrderDel;
  public static String EMFDiffMergeLabelProvider_Reference;
  public static String EMFDiffMergeLabelProvider_RootContainment;
  public static String EMFDiffMergeLabelProvider_ValueAddition;
  public static String EMFDiffMergeLabelProvider_ValueDeletion;
  public static String EMFDiffMergeUIPlugin_Label;
  public static String DefaultComparisonSpecificationFactory_Label;
  public static String EnhancedComparisonTreeViewer_DefaultHeader;
  public static String EObjectScopeSpecification_LabelInResource;
  public static String EObjectScopeSpecificationFactory_Label;
  public static String FileScopeSpecificationFactory_Label;
  public static String IgnoreChoicesDialog_IncludeChildren;
  public static String IgnoreChoicesDialog_Question;
  public static String MergeChoicesDialog_IncludeChildren;
  public static String MergeChoicesDialog_IncrementalMode;
  public static String MergeChoicesDialog_Question;
  public static String MergeChoicesDialog_ShowImpact;
  public static String MergeImpactViewer_ComputationName;
  public static String MergeImpactViewer_Implied;
  public static String MergeImpactViewer_Required;
  public static String MiscUtil_DefaultCommandName;
  public static String ResourceScopeSpecificationFactory_Label;
  public static String ValuesViewer_ContainerLabel;
  public static String ValuesViewer_FeatureLabel;
  public static String ValuesViewer_OrderLabel;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
