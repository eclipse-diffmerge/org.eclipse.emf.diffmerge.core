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
package org.eclipse.emf.diffmerge.diffdata.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataFactory;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiffdataFactoryImpl extends EFactoryImpl
    implements DiffdataFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DiffdataFactory init() {
    try {
      DiffdataFactory theDiffdataFactory = (DiffdataFactory) EPackage.Registry.INSTANCE
          .getEFactory(DiffdataPackage.eNS_URI);
      if (theDiffdataFactory != null) {
        return theDiffdataFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DiffdataFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffdataFactoryImpl() {
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
    case DiffdataPackage.ECOMPARISON:
      return createEComparison();
    case DiffdataPackage.EMAPPING:
      return createEMapping();
    case DiffdataPackage.EMATCH:
      return createEMatch();
    case DiffdataPackage.EELEMENT_PRESENCE:
      return createEElementPresence();
    case DiffdataPackage.EATTRIBUTE_VALUE_PRESENCE:
      return createEAttributeValuePresence();
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE:
      return createEReferenceValuePresence();
    case DiffdataPackage.ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY:
      return (EObject) createAttributeToValueToDifferenceEntry();
    case DiffdataPackage.VALUE_TO_DIFFERENCE_ENTRY:
      return (EObject) createValueToDifferenceEntry();
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToElementToDifferenceEntry();
    case DiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToOrderDifferenceEntry();
    case DiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createElementToDifferenceEntry();
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
    case DiffdataPackage.SETTING:
      return createSettingFromString(eDataType, initialValue);
    case DiffdataPackage.IEDITABLE_MODEL_SCOPE:
      return createIEditableModelScopeFromString(eDataType, initialValue);
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
    case DiffdataPackage.SETTING:
      return convertSettingToString(eDataType, instanceValue);
    case DiffdataPackage.IEDITABLE_MODEL_SCOPE:
      return convertIEditableModelScopeToString(eDataType, instanceValue);
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
  public EComparison createEComparison() {
    EComparisonImpl eComparison = new EComparisonImpl();
    return eComparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMapping createEMapping() {
    EMappingImpl eMapping = new EMappingImpl();
    return eMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMatch createEMatch() {
    EMatchImpl eMatch = new EMatchImpl();
    return eMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EElementPresence createEElementPresence() {
    EElementPresenceImpl eElementPresence = new EElementPresenceImpl();
    return eElementPresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttributeValuePresence createEAttributeValuePresence() {
    EAttributeValuePresenceImpl eAttributeValuePresence = new EAttributeValuePresenceImpl();
    return eAttributeValuePresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReferenceValuePresence createEReferenceValuePresence() {
    EReferenceValuePresenceImpl eReferenceValuePresence = new EReferenceValuePresenceImpl();
    return eReferenceValuePresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<EAttribute, EMap<Object, IAttributeValuePresence<EObject>>> createAttributeToValueToDifferenceEntry() {
    AttributeToValueToDifferenceEntryImpl attributeToValueToDifferenceEntry = new AttributeToValueToDifferenceEntryImpl();
    return attributeToValueToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, IAttributeValuePresence<EObject>> createValueToDifferenceEntry() {
    ValueToDifferenceEntryImpl valueToDifferenceEntry = new ValueToDifferenceEntryImpl();
    return valueToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> createReferenceToElementToDifferenceEntry() {
    ReferenceToElementToDifferenceEntryImpl referenceToElementToDifferenceEntry = new ReferenceToElementToDifferenceEntryImpl();
    return referenceToElementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<EReference, EList<IReferenceValuePresence<EObject>>> createReferenceToOrderDifferenceEntry() {
    ReferenceToOrderDifferenceEntryImpl referenceToOrderDifferenceEntry = new ReferenceToOrderDifferenceEntryImpl();
    return referenceToOrderDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<EObject, IReferenceValuePresence<EObject>> createElementToDifferenceEntry() {
    ElementToDifferenceEntryImpl elementToDifferenceEntry = new ElementToDifferenceEntryImpl();
    return elementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature.Setting createSettingFromString(EDataType eDataType,
      String initialValue) {
    return (EStructuralFeature.Setting) super.createFromString(eDataType,
        initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSettingToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableModelScope createIEditableModelScopeFromString(
      EDataType eDataType, String initialValue) {
    return (IEditableModelScope) super.createFromString(eDataType,
        initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIEditableModelScopeToString(EDataType eDataType,
      Object instanceValue) {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffdataPackage getDiffdataPackage() {
    return (DiffdataPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @SuppressWarnings("javadoc")
  @Deprecated
  public static DiffdataPackage getPackage() {
    return DiffdataPackage.eINSTANCE;
  }

} //DiffdataFactoryImpl
