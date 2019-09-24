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
package org.eclipse.emf.diffmerge.generic.gdiffdata.util;

import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.*;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
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
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage
 * @generated
 */
public class GdiffdataSwitch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static GdiffdataPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GdiffdataSwitch() {
    if (modelPackage == null) {
      modelPackage = GdiffdataPackage.eINSTANCE;
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
    case GdiffdataPackage.EIDENTIFIED: {
      EIdentified eIdentified = (EIdentified) theEObject;
      T result = caseEIdentified(eIdentified);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.ECOMPARISON: {
      EComparison<?, ?, ?> eComparison = (EComparison<?, ?, ?>) theEObject;
      T result = caseEComparison(eComparison);
      if (result == null)
        result = caseEIdentified(eComparison);
      if (result == null)
        result = caseIEditableComparison(eComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.ECOMPARISON_ELEMENT: {
      EComparisonElement<?, ?, ?> eComparisonElement = (EComparisonElement<?, ?, ?>) theEObject;
      T result = caseEComparisonElement(eComparisonElement);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EMAPPING: {
      EMapping<?, ?, ?> eMapping = (EMapping<?, ?, ?>) theEObject;
      T result = caseEMapping(eMapping);
      if (result == null)
        result = caseEIdentified(eMapping);
      if (result == null)
        result = caseEComparisonElement(eMapping);
      if (result == null)
        result = caseIEditableMapping(eMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EMATCH: {
      EMatch<?, ?, ?> eMatch = (EMatch<?, ?, ?>) theEObject;
      T result = caseEMatch(eMatch);
      if (result == null)
        result = caseEIdentified(eMatch);
      if (result == null)
        result = caseEComparisonElement(eMatch);
      if (result == null)
        result = caseIEditableMatch(eMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EMERGEABLE_DIFFERENCE: {
      EMergeableDifference<?, ?, ?> eMergeableDifference = (EMergeableDifference<?, ?, ?>) theEObject;
      T result = caseEMergeableDifference(eMergeableDifference);
      if (result == null)
        result = caseEIdentified(eMergeableDifference);
      if (result == null)
        result = caseEComparisonElement(eMergeableDifference);
      if (result == null)
        result = caseIEditableMergeableDifference(eMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EELEMENT_RELATIVE_PRESENCE: {
      EElementRelativePresence<?, ?, ?> eElementRelativePresence = (EElementRelativePresence<?, ?, ?>) theEObject;
      T result = caseEElementRelativePresence(eElementRelativePresence);
      if (result == null)
        result = caseEMergeableDifference(eElementRelativePresence);
      if (result == null)
        result = caseIElementRelativePresence(eElementRelativePresence);
      if (result == null)
        result = caseEIdentified(eElementRelativePresence);
      if (result == null)
        result = caseEComparisonElement(eElementRelativePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eElementRelativePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EELEMENT_PRESENCE: {
      EElementPresence<?, ?, ?> eElementPresence = (EElementPresence<?, ?, ?>) theEObject;
      T result = caseEElementPresence(eElementPresence);
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
        result = caseEComparisonElement(eElementPresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EVALUE_PRESENCE: {
      EValuePresence<?, ?, ?> eValuePresence = (EValuePresence<?, ?, ?>) theEObject;
      T result = caseEValuePresence(eValuePresence);
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
        result = caseEComparisonElement(eValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EATTRIBUTE_VALUE_PRESENCE: {
      EAttributeValuePresence<?, ?, ?> eAttributeValuePresence = (EAttributeValuePresence<?, ?, ?>) theEObject;
      T result = caseEAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseIAttributeValuePresence(eAttributeValuePresence);
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
        result = caseEComparisonElement(eAttributeValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.EREFERENCE_VALUE_PRESENCE: {
      EReferenceValuePresence<?, ?, ?> eReferenceValuePresence = (EReferenceValuePresence<?, ?, ?>) theEObject;
      T result = caseEReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseIReferenceValuePresence(eReferenceValuePresence);
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
        result = caseEComparisonElement(eReferenceValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.ICOMPARISON: {
      IComparison<?> iComparison = (IComparison<?>) theEObject;
      T result = caseIComparison(iComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IEDITABLE_COMPARISON: {
      Editable<?> iEditableComparison = (Editable<?>) theEObject;
      T result = caseIEditableComparison(iEditableComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IMAPPING: {
      IMapping<?> iMapping = (IMapping<?>) theEObject;
      T result = caseIMapping(iMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IEDITABLE_MAPPING: {
      org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<?> iEditableMapping = (org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<?>) theEObject;
      T result = caseIEditableMapping(iEditableMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IMATCH: {
      IMatch<?> iMatch = (IMatch<?>) theEObject;
      T result = caseIMatch(iMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IEDITABLE_MATCH: {
      org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<?> iEditableMatch = (org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<?>) theEObject;
      T result = caseIEditableMatch(iEditableMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IMERGEABLE_DIFFERENCE: {
      IMergeableDifference<?> iMergeableDifference = (IMergeableDifference<?>) theEObject;
      T result = caseIMergeableDifference(iMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IEDITABLE_MERGEABLE_DIFFERENCE: {
      org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<?> iEditableMergeableDifference = (org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<?>) theEObject;
      T result = caseIEditableMergeableDifference(iEditableMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IELEMENT_RELATIVE_PRESENCE: {
      IElementRelativePresence<?> iElementRelativePresence = (IElementRelativePresence<?>) theEObject;
      T result = caseIElementRelativePresence(iElementRelativePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IELEMENT_PRESENCE: {
      IElementPresence<?> iElementPresence = (IElementPresence<?>) theEObject;
      T result = caseIElementPresence(iElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IVALUE_PRESENCE: {
      IValuePresence<?> iValuePresence = (IValuePresence<?>) theEObject;
      T result = caseIValuePresence(iValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IATTRIBUTE_VALUE_PRESENCE: {
      IAttributeValuePresence<?> iAttributeValuePresence = (IAttributeValuePresence<?>) theEObject;
      T result = caseIAttributeValuePresence(iAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.IREFERENCE_VALUE_PRESENCE: {
      IReferenceValuePresence<?> iReferenceValuePresence = (IReferenceValuePresence<?>) theEObject;
      T result = caseIReferenceValuePresence(iReferenceValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
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
  public <E, A, R> T caseEComparison(EComparison<E, A, R> object) {
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
  public <E, A, R> T caseEComparisonElement(
      EComparisonElement<E, A, R> object) {
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
  public <E, A, R> T caseEMapping(EMapping<E, A, R> object) {
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
  public <E, A, R> T caseEMatch(EMatch<E, A, R> object) {
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
  public <E, A, R> T caseEMergeableDifference(
      EMergeableDifference<E, A, R> object) {
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
  public <E, A, R> T caseEElementRelativePresence(
      EElementRelativePresence<E, A, R> object) {
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
  public <E, A, R> T caseEElementPresence(EElementPresence<E, A, R> object) {
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
  public <E, A, R> T caseEValuePresence(EValuePresence<E, A, R> object) {
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
  public <E, A, R> T caseEAttributeValuePresence(
      EAttributeValuePresence<E, A, R> object) {
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
  public <E, A, R> T caseEReferenceValuePresence(
      EReferenceValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIComparison(IComparison<E> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>IMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIMapping(IMapping<E> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>IMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIMatch(IMatch<E> object) {
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
   * Returns the result of interpreting the object as an instance of '<em>IMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIMergeableDifference(IMergeableDifference<E> object) {
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

} //GdiffdataSwitch
