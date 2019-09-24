/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.diffuidata.impl;

import java.util.Map;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.ui.diffuidata.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jface.viewers.TreePath;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiffuidataFactoryImpl extends EFactoryImpl
    implements DiffuidataFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DiffuidataFactory init() {
    try {
      DiffuidataFactory theDiffuidataFactory = (DiffuidataFactory) EPackage.Registry.INSTANCE
          .getEFactory(DiffuidataPackage.eNS_URI);
      if (theDiffuidataFactory != null) {
        return theDiffuidataFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DiffuidataFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffuidataFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
    case DiffuidataPackage.UI_COMPARISON:
      return createUIComparison();
    case DiffuidataPackage.COMPARISON_SELECTION:
      return createComparisonSelection();
    case DiffuidataPackage.MATCH_AND_FEATURE:
      return createMatchAndFeature();
    case DiffuidataPackage.MATCH_TO_NB_ENTRY:
      return (EObject) createMatchToNbEntry();
    default:
      throw new IllegalArgumentException(
          "The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
    case DiffuidataPackage.TREE_PATH:
      return createTreePathFromString(eDataType, initialValue);
    default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() //$NON-NLS-1$
          + "' is not a valid classifier"); //$NON-NLS-1$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
    case DiffuidataPackage.TREE_PATH:
      return convertTreePathToString(eDataType, instanceValue);
    default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() //$NON-NLS-1$
          + "' is not a valid classifier"); //$NON-NLS-1$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UIComparison createUIComparison() {
    UIComparisonImpl uiComparison = new UIComparisonImpl();
    return uiComparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComparisonSelection createComparisonSelection() {
    ComparisonSelectionImpl comparisonSelection = new ComparisonSelectionImpl();
    return comparisonSelection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MatchAndFeature createMatchAndFeature() {
    MatchAndFeatureImpl matchAndFeature = new MatchAndFeatureImpl();
    return matchAndFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<IMatch<?>, Integer> createMatchToNbEntry() {
    MatchToNbEntryImpl matchToNbEntry = new MatchToNbEntryImpl();
    return matchToNbEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TreePath createTreePathFromString(EDataType eDataType,
      String initialValue) {
    return (TreePath) super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTreePathToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffuidataPackage getDiffuidataPackage() {
    return (DiffuidataPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @SuppressWarnings("javadoc")
  @Deprecated
  public static DiffuidataPackage getPackage() {
    return DiffuidataPackage.eINSTANCE;
  }

} //DiffuidataFactoryImpl
