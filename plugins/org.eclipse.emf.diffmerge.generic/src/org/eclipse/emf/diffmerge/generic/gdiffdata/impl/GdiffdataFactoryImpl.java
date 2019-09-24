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
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataFactory;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GdiffdataFactoryImpl extends EFactoryImpl
    implements GdiffdataFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GdiffdataFactory init() {
    try {
      GdiffdataFactory theGdiffdataFactory = (GdiffdataFactory) EPackage.Registry.INSTANCE
          .getEFactory(GdiffdataPackage.eNS_URI);
      if (theGdiffdataFactory != null) {
        return theGdiffdataFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new GdiffdataFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GdiffdataFactoryImpl() {
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
    case GdiffdataPackage.IEDITABLE_TREE_DATA_SCOPE:
      return createIEditableTreeDataScopeFromString(eDataType, initialValue);
    case GdiffdataPackage.IMATCH_POLICY:
      return createIMatchPolicyFromString(eDataType, initialValue);
    case GdiffdataPackage.IDIFF_POLICY:
      return createIDiffPolicyFromString(eDataType, initialValue);
    case GdiffdataPackage.IMERGE_POLICY:
      return createIMergePolicyFromString(eDataType, initialValue);
    case GdiffdataPackage.ROLE:
      return createRoleFromString(eDataType, initialValue);
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
    case GdiffdataPackage.IEDITABLE_TREE_DATA_SCOPE:
      return convertIEditableTreeDataScopeToString(eDataType, instanceValue);
    case GdiffdataPackage.IMATCH_POLICY:
      return convertIMatchPolicyToString(eDataType, instanceValue);
    case GdiffdataPackage.IDIFF_POLICY:
      return convertIDiffPolicyToString(eDataType, instanceValue);
    case GdiffdataPackage.IMERGE_POLICY:
      return convertIMergePolicyToString(eDataType, instanceValue);
    case GdiffdataPackage.ROLE:
      return convertRoleToString(eDataType, instanceValue);
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
  public IEditableTreeDataScope<?> createIEditableTreeDataScopeFromString(
      EDataType eDataType, String initialValue) {
    return (IEditableTreeDataScope<?>) super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIEditableTreeDataScopeToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatchPolicy<?> createIMatchPolicyFromString(EDataType eDataType,
      String initialValue) {
    return (IMatchPolicy<?>) super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIMatchPolicyToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IDiffPolicy<?> createIDiffPolicyFromString(EDataType eDataType,
      String initialValue) {
    return (IDiffPolicy<?>) super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIDiffPolicyToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMergePolicy<?> createIMergePolicyFromString(EDataType eDataType,
      String initialValue) {
    return (IMergePolicy<?>) super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIMergePolicyToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Role createRoleFromString(EDataType eDataType, String initialValue) {
    return (Role) super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRoleToString(EDataType eDataType, Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GdiffdataPackage getGdiffdataPackage() {
    return (GdiffdataPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @SuppressWarnings("javadoc")
  @Deprecated
  public static GdiffdataPackage getPackage() {
    return GdiffdataPackage.eINSTANCE;
  }

} //GdiffdataFactoryImpl
