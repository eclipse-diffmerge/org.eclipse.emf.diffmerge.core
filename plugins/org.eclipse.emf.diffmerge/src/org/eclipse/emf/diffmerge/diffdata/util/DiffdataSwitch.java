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
package org.eclipse.emf.diffmerge.diffdata.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.diffdata.*;
import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage
 * @generated
 */
public class DiffdataSwitch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static DiffdataPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffdataSwitch() {
    if (modelPackage == null) {
      modelPackage = DiffdataPackage.eINSTANCE;
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
    case DiffdataPackage.ECOMPARISON: {
      EComparison eComparison = (EComparison) theEObject;
      T result = caseEComparison(eComparison);
      if (result == null)
        result = caseGdiffdata_EComparison(eComparison);
      if (result == null)
        result = caseEIdentified(eComparison);
      if (result == null)
        result = caseIEditableComparison(eComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.ECOMPARISON_ELEMENT: {
      EComparisonElement eComparisonElement = (EComparisonElement) theEObject;
      T result = caseEComparisonElement(eComparisonElement);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EMAPPING: {
      EMapping eMapping = (EMapping) theEObject;
      T result = caseEMapping(eMapping);
      if (result == null)
        result = caseGdiffdata_EMapping(eMapping);
      if (result == null)
        result = caseEComparisonElement(eMapping);
      if (result == null)
        result = caseEIdentified(eMapping);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eMapping);
      if (result == null)
        result = caseIEditableMapping(eMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EMATCH: {
      EMatch eMatch = (EMatch) theEObject;
      T result = caseEMatch(eMatch);
      if (result == null)
        result = caseGdiffdata_EMatch(eMatch);
      if (result == null)
        result = caseEComparisonElement(eMatch);
      if (result == null)
        result = caseEIdentified(eMatch);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eMatch);
      if (result == null)
        result = caseIEditableMatch(eMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EELEMENT_PRESENCE: {
      EElementPresence eElementPresence = (EElementPresence) theEObject;
      T result = caseEElementPresence(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EElementPresence(eElementPresence);
      if (result == null)
        result = caseEComparisonElement(eElementPresence);
      if (result == null)
        result = caseEElementRelativePresence(eElementPresence);
      if (result == null)
        result = caseIElementPresence(eElementPresence);
      if (result == null)
        result = caseEMergeableDifference(eElementPresence);
      if (result == null)
        result = caseIElementRelativePresence(eElementPresence);
      if (result == null)
        result = caseEIdentified(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eElementPresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EVALUE_PRESENCE: {
      EValuePresence eValuePresence = (EValuePresence) theEObject;
      T result = caseEValuePresence(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eValuePresence);
      if (result == null)
        result = caseEComparisonElement(eValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eValuePresence);
      if (result == null)
        result = caseIValuePresence(eValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eValuePresence);
      if (result == null)
        result = caseEIdentified(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EATTRIBUTE_VALUE_PRESENCE: {
      EAttributeValuePresence eAttributeValuePresence = (EAttributeValuePresence) theEObject;
      T result = caseEAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseIAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEComparisonElement(eAttributeValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eAttributeValuePresence);
      if (result == null)
        result = caseIValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEIdentified(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eAttributeValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.EREFERENCE_VALUE_PRESENCE: {
      EReferenceValuePresence eReferenceValuePresence = (EReferenceValuePresence) theEObject;
      T result = caseEReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseIReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEComparisonElement(eReferenceValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eReferenceValuePresence);
      if (result == null)
        result = caseIValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEIdentified(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eReferenceValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.ATTRIBUTE_TO_VALUE_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<EAttribute, EMap<Object, IAttributeValuePresence<EObject>>> attributeToValueToDifferenceEntry = (Map.Entry<EAttribute, EMap<Object, IAttributeValuePresence<EObject>>>) theEObject;
      T result = caseAttributeToValueToDifferenceEntry(
          attributeToValueToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.VALUE_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, IAttributeValuePresence<EObject>> valueToDifferenceEntry = (Map.Entry<Object, IAttributeValuePresence<EObject>>) theEObject;
      T result = caseValueToDifferenceEntry(valueToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> referenceToElementToDifferenceEntry = (Map.Entry<EReference, EMap<EObject, IReferenceValuePresence<EObject>>>) theEObject;
      T result = caseReferenceToElementToDifferenceEntry(
          referenceToElementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<EReference, EList<IReferenceValuePresence<EObject>>> referenceToOrderDifferenceEntry = (Map.Entry<EReference, EList<IReferenceValuePresence<EObject>>>) theEObject;
      T result = caseReferenceToOrderDifferenceEntry(
          referenceToOrderDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case DiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<EObject, IReferenceValuePresence<EObject>> elementToDifferenceEntry = (Map.Entry<EObject, IReferenceValuePresence<EObject>>) theEObject;
      T result = caseElementToDifferenceEntry(elementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EComparison(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison<E, A, R, S> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EComparisonElement(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EMapping(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EMatch(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseEMergeableDifference(
      EMergeableDifference<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseEElementRelativePresence(
      EElementRelativePresence<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EElementPresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EAttributeValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence<E, A, R, S> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R, S> T caseGdiffdata_EReferenceValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence<E, A, R, S> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEComparison(EComparison object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEComparisonElement(EComparisonElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEMapping(EMapping object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEMatch(EMatch object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEElementPresence(EElementPresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEValuePresence(EValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEAttributeValuePresence(EAttributeValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEReferenceValuePresence(EReferenceValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute To Value To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute To Value To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttributeToValueToDifferenceEntry(
      Map.Entry<EAttribute, EMap<Object, IAttributeValuePresence<EObject>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Value To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseValueToDifferenceEntry(
      Map.Entry<Object, IAttributeValuePresence<EObject>> object) {
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
      Map.Entry<EReference, EMap<EObject, IReferenceValuePresence<EObject>>> object) {
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
      Map.Entry<EReference, EList<IReferenceValuePresence<EObject>>> object) {
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
      Map.Entry<EObject, IReferenceValuePresence<EObject>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EIdentified</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EIdentified</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEIdentified(EIdentified object) {
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

} //DiffdataSwitch
