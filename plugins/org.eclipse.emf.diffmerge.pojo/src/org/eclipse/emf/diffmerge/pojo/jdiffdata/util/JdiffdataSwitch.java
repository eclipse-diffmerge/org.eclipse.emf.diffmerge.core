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
package org.eclipse.emf.diffmerge.pojo.jdiffdata.util;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified;

import org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage
 * @generated
 */
public class JdiffdataSwitch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static JdiffdataPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JdiffdataSwitch() {
    if (modelPackage == null) {
      modelPackage = JdiffdataPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    } else {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return eSuperTypes.isEmpty() ? defaultCase(theEObject)
          : doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
    case JdiffdataPackage.JCOMPARISON: {
      JComparison<?> jComparison = (JComparison<?>) theEObject;
      T result = caseJComparison(jComparison);
      if (result == null)
        result = caseGComparison(jComparison);
      if (result == null)
        result = caseGIdentified(jComparison);
      if (result == null)
        result = caseIEditableComparison(jComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JCOMPARISON_ELEMENT: {
      JComparisonElement<?> jComparisonElement = (JComparisonElement<?>) theEObject;
      T result = caseJComparisonElement(jComparisonElement);
      if (result == null)
        result = caseGComparisonElement(jComparisonElement);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JMAPPING: {
      JMapping<?> jMapping = (JMapping<?>) theEObject;
      T result = caseJMapping(jMapping);
      if (result == null)
        result = caseGMapping(jMapping);
      if (result == null)
        result = caseJComparisonElement(jMapping);
      if (result == null)
        result = caseGIdentified(jMapping);
      if (result == null)
        result = caseGComparisonElement(jMapping);
      if (result == null)
        result = caseIEditableMapping(jMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JMATCH: {
      JMatch<?> jMatch = (JMatch<?>) theEObject;
      T result = caseJMatch(jMatch);
      if (result == null)
        result = caseGMatch(jMatch);
      if (result == null)
        result = caseJComparisonElement(jMatch);
      if (result == null)
        result = caseGIdentified(jMatch);
      if (result == null)
        result = caseGComparisonElement(jMatch);
      if (result == null)
        result = caseIEditableMatch(jMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JMERGEABLE_DIFFERENCE: {
      JMergeableDifference<?> jMergeableDifference = (JMergeableDifference<?>) theEObject;
      T result = caseJMergeableDifference(jMergeableDifference);
      if (result == null)
        result = caseGMergeableDifference(jMergeableDifference);
      if (result == null)
        result = caseJComparisonElement(jMergeableDifference);
      if (result == null)
        result = caseGIdentified(jMergeableDifference);
      if (result == null)
        result = caseGComparisonElement(jMergeableDifference);
      if (result == null)
        result = caseIEditableMergeableDifference(jMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JELEMENT_RELATIVE_PRESENCE: {
      JElementRelativePresence<?> jElementRelativePresence = (JElementRelativePresence<?>) theEObject;
      T result = caseJElementRelativePresence(jElementRelativePresence);
      if (result == null)
        result = caseGElementRelativePresence(jElementRelativePresence);
      if (result == null)
        result = caseJMergeableDifference(jElementRelativePresence);
      if (result == null)
        result = caseGMergeableDifference(jElementRelativePresence);
      if (result == null)
        result = caseIElementRelativePresence(jElementRelativePresence);
      if (result == null)
        result = caseJComparisonElement(jElementRelativePresence);
      if (result == null)
        result = caseGIdentified(jElementRelativePresence);
      if (result == null)
        result = caseGComparisonElement(jElementRelativePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(jElementRelativePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JELEMENT_PRESENCE: {
      JElementPresence<?> jElementPresence = (JElementPresence<?>) theEObject;
      T result = caseJElementPresence(jElementPresence);
      if (result == null)
        result = caseGElementPresence(jElementPresence);
      if (result == null)
        result = caseJElementRelativePresence(jElementPresence);
      if (result == null)
        result = caseGElementRelativePresence(jElementPresence);
      if (result == null)
        result = caseIElementPresence(jElementPresence);
      if (result == null)
        result = caseJMergeableDifference(jElementPresence);
      if (result == null)
        result = caseGMergeableDifference(jElementPresence);
      if (result == null)
        result = caseIElementRelativePresence(jElementPresence);
      if (result == null)
        result = caseJComparisonElement(jElementPresence);
      if (result == null)
        result = caseGIdentified(jElementPresence);
      if (result == null)
        result = caseGComparisonElement(jElementPresence);
      if (result == null)
        result = caseIEditableMergeableDifference(jElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JVALUE_PRESENCE: {
      JValuePresence<?> jValuePresence = (JValuePresence<?>) theEObject;
      T result = caseJValuePresence(jValuePresence);
      if (result == null)
        result = caseGValuePresence(jValuePresence);
      if (result == null)
        result = caseJElementRelativePresence(jValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(jValuePresence);
      if (result == null)
        result = caseIValuePresence(jValuePresence);
      if (result == null)
        result = caseJMergeableDifference(jValuePresence);
      if (result == null)
        result = caseGMergeableDifference(jValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(jValuePresence);
      if (result == null)
        result = caseJComparisonElement(jValuePresence);
      if (result == null)
        result = caseGIdentified(jValuePresence);
      if (result == null)
        result = caseGComparisonElement(jValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(jValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE: {
      JAttributeValuePresence<?> jAttributeValuePresence = (JAttributeValuePresence<?>) theEObject;
      T result = caseJAttributeValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseGAttributeValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseJValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseGValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseIAttributeValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseJElementRelativePresence(jAttributeValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(jAttributeValuePresence);
      if (result == null)
        result = caseIValuePresence(jAttributeValuePresence);
      if (result == null)
        result = caseJMergeableDifference(jAttributeValuePresence);
      if (result == null)
        result = caseGMergeableDifference(jAttributeValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(jAttributeValuePresence);
      if (result == null)
        result = caseJComparisonElement(jAttributeValuePresence);
      if (result == null)
        result = caseGIdentified(jAttributeValuePresence);
      if (result == null)
        result = caseGComparisonElement(jAttributeValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(jAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE: {
      JReferenceValuePresence<?> jReferenceValuePresence = (JReferenceValuePresence<?>) theEObject;
      T result = caseJReferenceValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseGReferenceValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseJValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseGValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseIReferenceValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseJElementRelativePresence(jReferenceValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(jReferenceValuePresence);
      if (result == null)
        result = caseIValuePresence(jReferenceValuePresence);
      if (result == null)
        result = caseJMergeableDifference(jReferenceValuePresence);
      if (result == null)
        result = caseGMergeableDifference(jReferenceValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(jReferenceValuePresence);
      if (result == null)
        result = caseJComparisonElement(jReferenceValuePresence);
      if (result == null)
        result = caseGIdentified(jReferenceValuePresence);
      if (result == null)
        result = caseGComparisonElement(jReferenceValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(jReferenceValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EList<IAttributeValuePresence<?>>> attributeToDifferenceEntry = (Map.Entry<Object, EList<IAttributeValuePresence<?>>>) theEObject;
      T result = caseAttributeToDifferenceEntry(attributeToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EMap<Object, IReferenceValuePresence<?>>> referenceToElementToDifferenceEntry = (Map.Entry<Object, EMap<Object, IReferenceValuePresence<?>>>) theEObject;
      T result = caseReferenceToElementToDifferenceEntry(
          referenceToElementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EList<IReferenceValuePresence<?>>> referenceToOrderDifferenceEntry = (Map.Entry<Object, EList<IReferenceValuePresence<?>>>) theEObject;
      T result = caseReferenceToOrderDifferenceEntry(
          referenceToOrderDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, IReferenceValuePresence<?>> elementToDifferenceEntry = (Map.Entry<Object, IReferenceValuePresence<?>>) theEObject;
      T result = caseElementToDifferenceEntry(elementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case JdiffdataPackage.ELEMENT_TO_MATCH_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, JMatch<?>> elementToMatchEntry = (Map.Entry<Object, JMatch<?>>) theEObject;
      T result = caseElementToMatchEntry(elementToMatchEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJComparison(JComparison<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJComparisonElement(
      JComparisonElement<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJMapping(JMapping<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJMatch(JMatch<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJMergeableDifference(
      JMergeableDifference<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJElementRelativePresence(
      JElementRelativePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJElementPresence(JElementPresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJValuePresence(JValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJAttributeValuePresence(
      JAttributeValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E extends Object> T caseJReferenceValuePresence(
      JReferenceValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttributeToDifferenceEntry(
      Map.Entry<Object, EList<IAttributeValuePresence<?>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference To Element To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference To Element To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceToElementToDifferenceEntry(
      Map.Entry<Object, EMap<Object, IReferenceValuePresence<?>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference To Order Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference To Order Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceToOrderDifferenceEntry(
      Map.Entry<Object, EList<IReferenceValuePresence<?>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementToDifferenceEntry(
      Map.Entry<Object, IReferenceValuePresence<?>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element To Match Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element To Match Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementToMatchEntry(Map.Entry<Object, JMatch<?>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GIdentified</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GIdentified</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGIdentified(GIdentified object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Comparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Comparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableComparison(Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGComparison(GComparison<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGComparisonElement(
      GComparisonElement<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Mapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Mapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMapping(
      org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGMapping(GMapping<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Match</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Match</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMatch(
      org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGMatch(GMatch<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Mergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Mergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMergeableDifference(
      org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGMergeableDifference(
      GMergeableDifference<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIElementRelativePresence(
      IElementRelativePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGElementRelativePresence(
      GElementRelativePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIElementPresence(IElementPresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGElementPresence(GElementPresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIValuePresence(IValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGValuePresence(GValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIAttributeValuePresence(IAttributeValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGAttributeValuePresence(
      GAttributeValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIReferenceValuePresence(IReferenceValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>GReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>GReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGReferenceValuePresence(
      GReferenceValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object) {
    return null;
  }

} //JdiffdataSwitch
