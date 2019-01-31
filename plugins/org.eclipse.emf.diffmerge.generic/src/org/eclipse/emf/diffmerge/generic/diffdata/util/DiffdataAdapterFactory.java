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
package org.eclipse.emf.diffmerge.generic.diffdata.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.diffdata.*;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.generic.diffdata.DiffdataPackage
 * @generated
 */
public class DiffdataAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static DiffdataPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiffdataAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = DiffdataPackage.eINSTANCE;
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
  protected DiffdataSwitch<Adapter> modelSwitch = new DiffdataSwitch<Adapter>() {
    @Override
    public Adapter caseEIdentified(EIdentified object) {
      return createEIdentifiedAdapter();
    }

    @Override
    public Adapter caseEComparison(EComparison object) {
      return createEComparisonAdapter();
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
    public Adapter caseAttributeToValueToDifferenceEntry(
        Map.Entry<EAttribute, EMap<Object, IAttributeValuePresence>> object) {
      return createAttributeToValueToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseValueToDifferenceEntry(
        Map.Entry<Object, IAttributeValuePresence> object) {
      return createValueToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseReferenceToElementToDifferenceEntry(
        Map.Entry<EReference, EMap<EObject, IReferenceValuePresence>> object) {
      return createReferenceToElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseElementToDifferenceEntry(
        Map.Entry<EObject, IReferenceValuePresence> object) {
      return createElementToDifferenceEntryAdapter();
    }

    @Override
    public Adapter caseIComparison(IComparison object) {
      return createIComparisonAdapter();
    }

    @Override
    public Adapter caseIEditableComparison(Editable object) {
      return createIEditableComparisonAdapter();
    }

    @Override
    public Adapter caseIMapping(IMapping object) {
      return createIMappingAdapter();
    }

    @Override
    public Adapter caseIEditableMapping(
        org.eclipse.emf.diffmerge.generic.api.IMapping.Editable object) {
      return createIEditableMappingAdapter();
    }

    @Override
    public Adapter caseIMatch(IMatch object) {
      return createIMatchAdapter();
    }

    @Override
    public Adapter caseIEditableMatch(
        org.eclipse.emf.diffmerge.generic.api.IMatch.Editable object) {
      return createIEditableMatchAdapter();
    }

    @Override
    public Adapter caseIMergeableDifference(IMergeableDifference object) {
      return createIMergeableDifferenceAdapter();
    }

    @Override
    public Adapter caseIEditableMergeableDifference(
        org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable object) {
      return createIEditableMergeableDifferenceAdapter();
    }

    @Override
    public Adapter caseIElementRelativePresence(
        IElementRelativePresence object) {
      return createIElementRelativePresenceAdapter();
    }

    @Override
    public Adapter caseIElementPresence(IElementPresence object) {
      return createIElementPresenceAdapter();
    }

    @Override
    public Adapter caseIValuePresence(IValuePresence object) {
      return createIValuePresenceAdapter();
    }

    @Override
    public Adapter caseIAttributeValuePresence(IAttributeValuePresence object) {
      return createIAttributeValuePresenceAdapter();
    }

    @Override
    public Adapter caseIReferenceValuePresence(IReferenceValuePresence object) {
      return createIReferenceValuePresenceAdapter();
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EIdentified <em>EIdentified</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EIdentified
   * @generated
   */
  public Adapter createEIdentifiedAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EComparison <em>EComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EComparison
   * @generated
   */
  public Adapter createEComparisonAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EMapping <em>EMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EMapping
   * @generated
   */
  public Adapter createEMappingAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EMatch <em>EMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EMatch
   * @generated
   */
  public Adapter createEMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EMergeableDifference <em>EMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EMergeableDifference
   * @generated
   */
  public Adapter createEMergeableDifferenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EElementRelativePresence <em>EElement Relative Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EElementRelativePresence
   * @generated
   */
  public Adapter createEElementRelativePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EElementPresence <em>EElement Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EElementPresence
   * @generated
   */
  public Adapter createEElementPresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EValuePresence <em>EValue Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EValuePresence
   * @generated
   */
  public Adapter createEValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EAttributeValuePresence <em>EAttribute Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EAttributeValuePresence
   * @generated
   */
  public Adapter createEAttributeValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.diffdata.EReferenceValuePresence <em>EReference Value Presence</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.diffdata.EReferenceValuePresence
   * @generated
   */
  public Adapter createEReferenceValuePresenceAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Attribute To Value To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createAttributeToValueToDifferenceEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Value To Difference Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createValueToDifferenceEntryAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IComparison <em>IComparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison
   * @generated
   */
  public Adapter createIComparisonAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IMapping <em>IMapping</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping
   * @generated
   */
  public Adapter createIMappingAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.IMatch <em>IMatch</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.IMatch
   * @generated
   */
  public Adapter createIMatchAdapter() {
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference <em>IMergeable Difference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference
   * @generated
   */
  public Adapter createIMergeableDifferenceAdapter() {
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

} //DiffdataAdapterFactory
