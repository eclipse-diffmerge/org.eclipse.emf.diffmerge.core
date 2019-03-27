/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.*;

import org.eclipse.emf.ecore.EClass;
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
public class PojodiffdataFactoryImpl extends EFactoryImpl
    implements PojodiffdataFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PojodiffdataFactory init() {
    try {
      PojodiffdataFactory thePojodiffdataFactory = (PojodiffdataFactory) EPackage.Registry.INSTANCE
          .getEFactory(PojodiffdataPackage.eNS_URI);
      if (thePojodiffdataFactory != null) {
        return thePojodiffdataFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new PojodiffdataFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojodiffdataFactoryImpl() {
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
    case PojodiffdataPackage.ECOMPARISON:
      return createEComparison();
    case PojodiffdataPackage.EMAPPING:
      return createEMapping();
    case PojodiffdataPackage.EMATCH:
      return createEMatch();
    case PojodiffdataPackage.EELEMENT_PRESENCE:
      return createEElementPresence();
    case PojodiffdataPackage.EATTRIBUTE_VALUE_PRESENCE:
      return createEAttributeValuePresence();
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE:
      return createEReferenceValuePresence();
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY:
      return (EObject) createAttributeToDifferenceEntry();
    case PojodiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToElementToDifferenceEntry();
    case PojodiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToOrderDifferenceEntry();
    case PojodiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createElementToDifferenceEntry();
    case PojodiffdataPackage.ELEMENT_TO_MATCH_ENTRY:
      return (EObject) createElementToMatchEntry();
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
  public Map.Entry<Object, EList<IAttributeValuePresence<Object>>> createAttributeToDifferenceEntry() {
    AttributeToDifferenceEntryImpl attributeToDifferenceEntry = new AttributeToDifferenceEntryImpl();
    return attributeToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EMap<Object, IReferenceValuePresence<Object>>> createReferenceToElementToDifferenceEntry() {
    ReferenceToElementToDifferenceEntryImpl referenceToElementToDifferenceEntry = new ReferenceToElementToDifferenceEntryImpl();
    return referenceToElementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EList<IReferenceValuePresence<Object>>> createReferenceToOrderDifferenceEntry() {
    ReferenceToOrderDifferenceEntryImpl referenceToOrderDifferenceEntry = new ReferenceToOrderDifferenceEntryImpl();
    return referenceToOrderDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, IReferenceValuePresence<Object>> createElementToDifferenceEntry() {
    ElementToDifferenceEntryImpl elementToDifferenceEntry = new ElementToDifferenceEntryImpl();
    return elementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EMatch> createElementToMatchEntry() {
    ElementToMatchEntryImpl elementToMatchEntry = new ElementToMatchEntryImpl();
    return elementToMatchEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojodiffdataPackage getPojodiffdataPackage() {
    return (PojodiffdataPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  @SuppressWarnings("javadoc")
  public static PojodiffdataPackage getPackage() {
    return PojodiffdataPackage.eINSTANCE;
  }

} //PojodiffdataFactoryImpl
