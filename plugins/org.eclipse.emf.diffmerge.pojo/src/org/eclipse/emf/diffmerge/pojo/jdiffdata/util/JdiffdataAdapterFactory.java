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

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage
 * @generated
 */
public class JdiffdataAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static JdiffdataPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JdiffdataAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = JdiffdataPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JdiffdataSwitch<Adapter> modelSwitch = new JdiffdataSwitch<Adapter>() {
    @Override
    public <E extends Object> Adapter caseJComparison(JComparison<E> object) {
      return createJComparisonAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJComparisonElement(
        JComparisonElement<E> object) {
      return createJComparisonElementAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJMapping(JMapping<E> object) {
      return createJMappingAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJMatch(JMatch<E> object) {
      return createJMatchAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJMergeableDifference(
        JMergeableDifference<E> object) {
      return createJMergeableDifferenceAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJElementRelativePresence(
        JElementRelativePresence<E> object) {
      return createJElementRelativePresenceAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJElementPresence(
        JElementPresence<E> object) {
      return createJElementPresenceAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJValuePresence(
        JValuePresence<E> object) {
      return createJValuePresenceAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJAttributeValuePresence(
        JAttributeValuePresence<E> object) {
      return createJAttributeValuePresenceAdapter();
    }

    @Override
    public <E extends Object> Adapter caseJReferenceValuePresence(
        JReferenceValuePresence<E> object) {
      return createJReferenceValuePresenceAdapter();
    }

    @Override
    public Adapter caseAttributeToDifferenceEntry(
        Map.Entry<Object, EList<IAttributeValuePresence<?>>> object) {
      return createAttributeToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseReferenceToElementToDifferenceEntry(
        Map.Entry<Object, EMap<Object, IReferenceValuePresence<?>>> object) {
      return createReferenceToElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseReferenceToOrderDifferenceEntry(
        Map.Entry<Object, EList<IReferenceValuePresence<?>>> object) {
      return createReferenceToOrderDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseElementToDifferenceEntry(
        Map.Entry<Object, IReferenceValuePresence<?>> object) {
      return createElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseElementToMatchEntry(
        Map.Entry<Object, JMatch<?>> object) {
      return createElementToMatchEntryAdapter();
    }

    @Override
    public Adapter caseGIdentified(GIdentified object) {
      return createGIdentifiedAdapter();
    }

    @Override
    public <E> Adapter caseIEditableComparison(Editable<E> object) {
      return createIEditableComparisonAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGComparison(GComparison<E, A, R> object) {
      return createGComparisonAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGComparisonElement(
        GComparisonElement<E, A, R> object) {
      return createGComparisonElementAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMapping(
        org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<E> object) {
      return createIEditableMappingAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGMapping(GMapping<E, A, R> object) {
      return createGMappingAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMatch(
        org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<E> object) {
      return createIEditableMatchAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGMatch(GMatch<E, A, R> object) {
      return createGMatchAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMergeableDifference(
        org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<E> object) {
      return createIEditableMergeableDifferenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGMergeableDifference(
        GMergeableDifference<E, A, R> object) {
      return createGMergeableDifferenceAdapter();
    }

    @Override
    public <E> Adapter caseIElementRelativePresence(
        IElementRelativePresence<E> object) {
      return createIElementRelativePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGElementRelativePresence(
        GElementRelativePresence<E, A, R> object) {
      return createGElementRelativePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIElementPresence(IElementPresence<E> object) {
      return createIElementPresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGElementPresence(
        GElementPresence<E, A, R> object) {
      return createGElementPresenceAdapter();
    }

    @Override
    public <E> Adapter caseIValuePresence(IValuePresence<E> object) {
      return createIValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGValuePresence(
        GValuePresence<E, A, R> object) {
      return createGValuePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIAttributeValuePresence(
        IAttributeValuePresence<E> object) {
      return createIAttributeValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGAttributeValuePresence(
        GAttributeValuePresence<E, A, R> object) {
      return createGAttributeValuePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIReferenceValuePresence(
        IReferenceValuePresence<E> object) {
      return createIReferenceValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGReferenceValuePresence(
        GReferenceValuePresence<E, A, R> object) {
      return createGReferenceValuePresenceAdapter();
    }

    @Override
    public Adapter defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison <em>JComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison
   * @generated
   */
  public Adapter createJComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement <em>JComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement
   * @generated
   */
  public Adapter createJComparisonElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping <em>JMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping
   * @generated
   */
  public Adapter createJMappingAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch <em>JMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch
   * @generated
   */
  public Adapter createJMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference <em>JMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference
   * @generated
   */
  public Adapter createJMergeableDifferenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence <em>JElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence
   * @generated
   */
  public Adapter createJElementRelativePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence <em>JElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementPresence
   * @generated
   */
  public Adapter createJElementPresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence <em>JValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence
   * @generated
   */
  public Adapter createJValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence <em>JAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence
   * @generated
   */
  public Adapter createJAttributeValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence <em>JReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence
   * @generated
   */
  public Adapter createJReferenceValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Attribute To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createAttributeToDifferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Reference To Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createReferenceToElementToDifferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Reference To Order Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createReferenceToOrderDifferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Element To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createElementToDifferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Element To Match Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createElementToMatchEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified <em>GIdentified</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified
   * @generated
   */
  public Adapter createGIdentifiedAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IComparison.Editable <em>IEditable Comparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable
   * @generated
   */
  public Adapter createIEditableComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison <em>GComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison
   * @generated
   */
  public Adapter createGComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement <em>GComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GComparisonElement
   * @generated
   */
  public Adapter createGComparisonElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IMapping.Editable <em>IEditable Mapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping.Editable
   * @generated
   */
  public Adapter createIEditableMappingAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping <em>GMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping
   * @generated
   */
  public Adapter createGMappingAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IMatch.Editable <em>IEditable Match</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch.Editable
   * @generated
   */
  public Adapter createIEditableMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch <em>GMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch
   * @generated
   */
  public Adapter createGMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable <em>IEditable Mergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable
   * @generated
   */
  public Adapter createIEditableMergeableDifferenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference <em>GMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GMergeableDifference
   * @generated
   */
  public Adapter createGMergeableDifferenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence <em>IElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence
   * @generated
   */
  public Adapter createIElementRelativePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence <em>GElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementRelativePresence
   * @generated
   */
  public Adapter createGElementRelativePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence <em>IElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence
   * @generated
   */
  public Adapter createIElementPresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence <em>GElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GElementPresence
   * @generated
   */
  public Adapter createGElementPresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence <em>IValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence
   * @generated
   */
  public Adapter createIValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence <em>GValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GValuePresence
   * @generated
   */
  public Adapter createGValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence <em>IAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence
   * @generated
   */
  public Adapter createIAttributeValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence <em>GAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence
   * @generated
   */
  public Adapter createGAttributeValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence <em>IReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence
   * @generated
   */
  public Adapter createIReferenceValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence <em>GReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GReferenceValuePresence
   * @generated
   */
  public Adapter createGReferenceValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} //JdiffdataAdapterFactory
