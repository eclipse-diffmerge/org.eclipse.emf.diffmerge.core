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
package org.eclipse.emf.diffmerge.pojo.jdiffdata.impl;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.*;
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
public class JdiffdataFactoryImpl extends EFactoryImpl
    implements JdiffdataFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JdiffdataFactory init() {
    try {
      JdiffdataFactory theJdiffdataFactory = (JdiffdataFactory) EPackage.Registry.INSTANCE
          .getEFactory(JdiffdataPackage.eNS_URI);
      if (theJdiffdataFactory != null) {
        return theJdiffdataFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new JdiffdataFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JdiffdataFactoryImpl() {
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
    case JdiffdataPackage.JCOMPARISON:
      return createJComparison();
    case JdiffdataPackage.JMAPPING:
      return createJMapping();
    case JdiffdataPackage.JMATCH:
      return createJMatch();
    case JdiffdataPackage.JELEMENT_PRESENCE:
      return createJElementPresence();
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE:
      return createJAttributeValuePresence();
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE:
      return createJReferenceValuePresence();
    case JdiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY:
      return (EObject) createAttributeToDifferenceEntry();
    case JdiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToElementToDifferenceEntry();
    case JdiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY:
      return (EObject) createReferenceToOrderDifferenceEntry();
    case JdiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY:
      return (EObject) createElementToDifferenceEntry();
    case JdiffdataPackage.ELEMENT_TO_MATCH_ENTRY:
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
  public <E extends Object> JComparison<E> createJComparison() {
    JComparisonImpl<E> jComparison = new JComparisonImpl<E>();
    return jComparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <E extends Object> JMapping<E> createJMapping() {
    JMappingImpl<E> jMapping = new JMappingImpl<E>();
    return jMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <E extends Object> JMatch<E> createJMatch() {
    JMatchImpl<E> jMatch = new JMatchImpl<E>();
    return jMatch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <E extends Object> JElementPresence<E> createJElementPresence() {
    JElementPresenceImpl<E> jElementPresence = new JElementPresenceImpl<E>();
    return jElementPresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <E extends Object> JAttributeValuePresence<E> createJAttributeValuePresence() {
    JAttributeValuePresenceImpl<E> jAttributeValuePresence = new JAttributeValuePresenceImpl<E>();
    return jAttributeValuePresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <E extends Object> JReferenceValuePresence<E> createJReferenceValuePresence() {
    JReferenceValuePresenceImpl<E> jReferenceValuePresence = new JReferenceValuePresenceImpl<E>();
    return jReferenceValuePresence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EList<IAttributeValuePresence<?>>> createAttributeToDifferenceEntry() {
    AttributeToDifferenceEntryImpl attributeToDifferenceEntry = new AttributeToDifferenceEntryImpl();
    return attributeToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EMap<Object, IReferenceValuePresence<?>>> createReferenceToElementToDifferenceEntry() {
    ReferenceToElementToDifferenceEntryImpl referenceToElementToDifferenceEntry = new ReferenceToElementToDifferenceEntryImpl();
    return referenceToElementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, EList<IReferenceValuePresence<?>>> createReferenceToOrderDifferenceEntry() {
    ReferenceToOrderDifferenceEntryImpl referenceToOrderDifferenceEntry = new ReferenceToOrderDifferenceEntryImpl();
    return referenceToOrderDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, IReferenceValuePresence<?>> createElementToDifferenceEntry() {
    ElementToDifferenceEntryImpl elementToDifferenceEntry = new ElementToDifferenceEntryImpl();
    return elementToDifferenceEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map.Entry<Object, JMatch<?>> createElementToMatchEntry() {
    ElementToMatchEntryImpl elementToMatchEntry = new ElementToMatchEntryImpl();
    return elementToMatchEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JdiffdataPackage getJdiffdataPackage() {
    return (JdiffdataPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  @SuppressWarnings("javadoc")
  public static JdiffdataPackage getPackage() {
    return JdiffdataPackage.eINSTANCE;
  }

} //JdiffdataFactoryImpl
