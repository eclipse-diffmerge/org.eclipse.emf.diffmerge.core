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
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.util;

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

import org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.*;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage
 * @generated
 */
public class PojodiffdataAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static PojodiffdataPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojodiffdataAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = PojodiffdataPackage.eINSTANCE;
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
  protected PojodiffdataSwitch<Adapter> modelSwitch = new PojodiffdataSwitch<Adapter>() {
    @Override
    public Adapter caseEComparison(EComparison object) {
      return createEComparisonAdapter();
    }

    @Override
    public Adapter caseEComparisonElement(EComparisonElement object) {
      return createEComparisonElementAdapter();
    }

    @Override
    public Adapter caseEMapping(EMapping object) {
      return createEMappingAdapter();
    }

    @Override
    public Adapter caseEMatch(EMatch object) {
      return createEMatchAdapter();
    }

    @Override
    public Adapter caseEMergeableDifference(EMergeableDifference object) {
      return createEMergeableDifferenceAdapter();
    }

    @Override
    public Adapter caseEElementRelativePresence(
        EElementRelativePresence object) {
      return createEElementRelativePresenceAdapter();
    }

    @Override
    public Adapter caseEElementPresence(EElementPresence object) {
      return createEElementPresenceAdapter();
    }

    @Override
    public Adapter caseEValuePresence(EValuePresence object) {
      return createEValuePresenceAdapter();
    }

    @Override
    public Adapter caseEAttributeValuePresence(EAttributeValuePresence object) {
      return createEAttributeValuePresenceAdapter();
    }

    @Override
    public Adapter caseEReferenceValuePresence(EReferenceValuePresence object) {
      return createEReferenceValuePresenceAdapter();
    }

    @Override
    public Adapter caseAttributeToDifferenceEntry(
        Map.Entry<Object, EList<IAttributeValuePresence<Object>>> object) {
      return createAttributeToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseReferenceToElementToDifferenceEntry(
        Map.Entry<Object, EMap<Object, IReferenceValuePresence<Object>>> object) {
      return createReferenceToElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseReferenceToOrderDifferenceEntry(
        Map.Entry<Object, EList<IReferenceValuePresence<Object>>> object) {
      return createReferenceToOrderDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseElementToDifferenceEntry(
        Map.Entry<Object, IReferenceValuePresence<Object>> object) {
      return createElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseElementToMatchEntry(Map.Entry<Object, EMatch> object) {
      return createElementToMatchEntryAdapter();
    }

    @Override
    public Adapter caseEIdentified(EIdentified object) {
      return createEIdentifiedAdapter();
    }

    @Override
    public <E> Adapter caseIEditableComparison(Editable<E> object) {
      return createIEditableComparisonAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EComparison(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison<E, A, R> object) {
      return createGdiffdata_EComparisonAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EComparisonElement(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement<E, A, R> object) {
      return createGdiffdata_EComparisonElementAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMapping(
        org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<E> object) {
      return createIEditableMappingAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EMapping(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping<E, A, R> object) {
      return createGdiffdata_EMappingAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMatch(
        org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<E> object) {
      return createIEditableMatchAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EMatch(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch<E, A, R> object) {
      return createGdiffdata_EMatchAdapter();
    }

    @Override
    public <E> Adapter caseIEditableMergeableDifference(
        org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<E> object) {
      return createIEditableMergeableDifferenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EMergeableDifference(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference<E, A, R> object) {
      return createGdiffdata_EMergeableDifferenceAdapter();
    }

    @Override
    public <E> Adapter caseIElementRelativePresence(
        IElementRelativePresence<E> object) {
      return createIElementRelativePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EElementRelativePresence(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence<E, A, R> object) {
      return createGdiffdata_EElementRelativePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIElementPresence(IElementPresence<E> object) {
      return createIElementPresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EElementPresence(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence<E, A, R> object) {
      return createGdiffdata_EElementPresenceAdapter();
    }

    @Override
    public <E> Adapter caseIValuePresence(IValuePresence<E> object) {
      return createIValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EValuePresence(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence<E, A, R> object) {
      return createGdiffdata_EValuePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIAttributeValuePresence(
        IAttributeValuePresence<E> object) {
      return createIAttributeValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EAttributeValuePresence(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence<E, A, R> object) {
      return createGdiffdata_EAttributeValuePresenceAdapter();
    }

    @Override
    public <E> Adapter caseIReferenceValuePresence(
        IReferenceValuePresence<E> object) {
      return createIReferenceValuePresenceAdapter();
    }

    @Override
    public <E, A, R> Adapter caseGdiffdata_EReferenceValuePresence(
        org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence<E, A, R> object) {
      return createGdiffdata_EReferenceValuePresenceAdapter();
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison
   * @generated
   */
  public Adapter createEComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement <em>EComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement
   * @generated
   */
  public Adapter createEComparisonElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping
   * @generated
   */
  public Adapter createEMappingAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch
   * @generated
   */
  public Adapter createEMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference
   * @generated
   */
  public Adapter createEMergeableDifferenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence
   * @generated
   */
  public Adapter createEElementRelativePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementPresence
   * @generated
   */
  public Adapter createEElementPresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence
   * @generated
   */
  public Adapter createEValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence
   * @generated
   */
  public Adapter createEAttributeValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence
   * @generated
   */
  public Adapter createEReferenceValuePresenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified <em>EIdentified</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified
   * @generated
   */
  public Adapter createEIdentifiedAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison
   * @generated
   */
  public Adapter createGdiffdata_EComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement <em>EComparison Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement
   * @generated
   */
  public Adapter createGdiffdata_EComparisonElementAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping
   * @generated
   */
  public Adapter createGdiffdata_EMappingAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch
   * @generated
   */
  public Adapter createGdiffdata_EMatchAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference
   * @generated
   */
  public Adapter createGdiffdata_EMergeableDifferenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence
   * @generated
   */
  public Adapter createGdiffdata_EElementRelativePresenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence
   * @generated
   */
  public Adapter createGdiffdata_EElementPresenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence
   * @generated
   */
  public Adapter createGdiffdata_EValuePresenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence
   * @generated
   */
  public Adapter createGdiffdata_EAttributeValuePresenceAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence
   * @generated
   */
  public Adapter createGdiffdata_EReferenceValuePresenceAdapter() {
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

} //PojodiffdataAdapterFactory
