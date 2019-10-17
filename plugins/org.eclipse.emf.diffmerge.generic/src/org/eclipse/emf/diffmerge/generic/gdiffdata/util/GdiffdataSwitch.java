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
import org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence;
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
    case GdiffdataPackage.GIDENTIFIED: {
      GIdentified gIdentified = (GIdentified) theEObject;
      T result = caseGIdentified(gIdentified);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GCOMPARISON: {
      GComparison<?, ?, ?> gComparison = (GComparison<?, ?, ?>) theEObject;
      T result = caseGComparison(gComparison);
      if (result == null)
        result = caseGIdentified(gComparison);
      if (result == null)
        result = caseIEditableComparison(gComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GCOMPARISON_ELEMENT: {
      GComparisonElement<?, ?, ?> gComparisonElement = (GComparisonElement<?, ?, ?>) theEObject;
      T result = caseGComparisonElement(gComparisonElement);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GMAPPING: {
      GMapping<?, ?, ?> gMapping = (GMapping<?, ?, ?>) theEObject;
      T result = caseGMapping(gMapping);
      if (result == null)
        result = caseGIdentified(gMapping);
      if (result == null)
        result = caseGComparisonElement(gMapping);
      if (result == null)
        result = caseIEditableMapping(gMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GMATCH: {
      GMatch<?, ?, ?> gMatch = (GMatch<?, ?, ?>) theEObject;
      T result = caseGMatch(gMatch);
      if (result == null)
        result = caseGIdentified(gMatch);
      if (result == null)
        result = caseGComparisonElement(gMatch);
      if (result == null)
        result = caseIEditableMatch(gMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GMERGEABLE_DIFFERENCE: {
      GMergeableDifference<?, ?, ?> gMergeableDifference = (GMergeableDifference<?, ?, ?>) theEObject;
      T result = caseGMergeableDifference(gMergeableDifference);
      if (result == null)
        result = caseGIdentified(gMergeableDifference);
      if (result == null)
        result = caseGComparisonElement(gMergeableDifference);
      if (result == null)
        result = caseIEditableMergeableDifference(gMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GELEMENT_RELATIVE_PRESENCE: {
      GElementRelativePresence<?, ?, ?> gElementRelativePresence = (GElementRelativePresence<?, ?, ?>) theEObject;
      T result = caseGElementRelativePresence(gElementRelativePresence);
      if (result == null)
        result = caseGMergeableDifference(gElementRelativePresence);
      if (result == null)
        result = caseIElementRelativePresence(gElementRelativePresence);
      if (result == null)
        result = caseGIdentified(gElementRelativePresence);
      if (result == null)
        result = caseGComparisonElement(gElementRelativePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(gElementRelativePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GELEMENT_PRESENCE: {
      GElementPresence<?, ?, ?> gElementPresence = (GElementPresence<?, ?, ?>) theEObject;
      T result = caseGElementPresence(gElementPresence);
      if (result == null)
        result = caseGElementRelativePresence(gElementPresence);
      if (result == null)
        result = caseIElementPresence(gElementPresence);
      if (result == null)
        result = caseGMergeableDifference(gElementPresence);
      if (result == null)
        result = caseIElementRelativePresence(gElementPresence);
      if (result == null)
        result = caseGIdentified(gElementPresence);
      if (result == null)
        result = caseGComparisonElement(gElementPresence);
      if (result == null)
        result = caseIEditableMergeableDifference(gElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GVALUE_PRESENCE: {
      GValuePresence<?, ?, ?> gValuePresence = (GValuePresence<?, ?, ?>) theEObject;
      T result = caseGValuePresence(gValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(gValuePresence);
      if (result == null)
        result = caseIValuePresence(gValuePresence);
      if (result == null)
        result = caseGMergeableDifference(gValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(gValuePresence);
      if (result == null)
        result = caseGIdentified(gValuePresence);
      if (result == null)
        result = caseGComparisonElement(gValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(gValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GATTRIBUTE_VALUE_PRESENCE: {
      GAttributeValuePresence<?, ?, ?> gAttributeValuePresence = (GAttributeValuePresence<?, ?, ?>) theEObject;
      T result = caseGAttributeValuePresence(gAttributeValuePresence);
      if (result == null)
        result = caseGValuePresence(gAttributeValuePresence);
      if (result == null)
        result = caseIAttributeValuePresence(gAttributeValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(gAttributeValuePresence);
      if (result == null)
        result = caseIValuePresence(gAttributeValuePresence);
      if (result == null)
        result = caseGMergeableDifference(gAttributeValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(gAttributeValuePresence);
      if (result == null)
        result = caseGIdentified(gAttributeValuePresence);
      if (result == null)
        result = caseGComparisonElement(gAttributeValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(gAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case GdiffdataPackage.GREFERENCE_VALUE_PRESENCE: {
      GReferenceValuePresence<?, ?, ?> gReferenceValuePresence = (GReferenceValuePresence<?, ?, ?>) theEObject;
      T result = caseGReferenceValuePresence(gReferenceValuePresence);
      if (result == null)
        result = caseGValuePresence(gReferenceValuePresence);
      if (result == null)
        result = caseIReferenceValuePresence(gReferenceValuePresence);
      if (result == null)
        result = caseGElementRelativePresence(gReferenceValuePresence);
      if (result == null)
        result = caseIValuePresence(gReferenceValuePresence);
      if (result == null)
        result = caseGMergeableDifference(gReferenceValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(gReferenceValuePresence);
      if (result == null)
        result = caseGIdentified(gReferenceValuePresence);
      if (result == null)
        result = caseGComparisonElement(gReferenceValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(gReferenceValuePresence);
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
